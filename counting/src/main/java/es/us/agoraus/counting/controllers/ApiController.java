package es.us.agoraus.counting.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import es.us.agoraus.counting.algorithms.AlgorithmFactory;
import es.us.agoraus.counting.algorithms.CountingAlgorithm;
import es.us.agoraus.counting.algorithms.SegmentationCriteria;
import es.us.agoraus.counting.algorithms.Test;
import es.us.agoraus.counting.algorithms.Transformations;
import es.us.agoraus.counting.dto.AlgorithmDetails;
import es.us.agoraus.counting.dto.AlgorithmResult;
import es.us.agoraus.counting.dto.ApiResponse;
import es.us.agoraus.counting.dto.EncryptedVotes;
import es.us.agoraus.counting.dto.Status;
import es.us.agoraus.counting.exceptions.InvalidCodificationException;
import es.us.agoraus.counting.integration.StorageServiceImpl;

@RestController
@RequestMapping(value = "/count")
public class ApiController extends BaseController {

	@Autowired
	StorageServiceImpl storageService;

	/**
	 * The following method is used to test the algorithm. We simulate a poll,
	 * creating the some votes json and crypting them. After that we call the
	 * method as if we obtained that crypted votes from the database.
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/predefined")
	public List<AlgorithmResult> predefinedCounting() throws Exception {
		List<AlgorithmResult> result = Test.referendumAlgorithmTestVotation();
		return result;
	}

	/**
	 * The following method computes a poll retrieving the encrypted votes from
	 * a database. We offer two ways to code the votes of a certain poll. After
	 * we obtain the votes, they are transformed in order to the codification
	 * obtained in the method call, and finally it runs the algorithm where the
	 * decrypt is done and the votes are counted.
	 * 
	 * @param pollId
	 * @param codification
	 * @param segment
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/{pollId}")
	public ApiResponse referendum(@PathVariable String pollId,
			@RequestParam(value = "cod", required = false) String codification,
			@RequestParam(value = "segment", required = false) SegmentationCriteria segment) {

		Status status = Status.SUCCESS;
		List<AlgorithmResult> algorithmResult;
		EncryptedVotes votes = storageService.getVotesForPoll(pollId);
		if (votes.getVotes().isEmpty()) {
			return response(Status.EMPTY_VOTES);
		}
		List<byte[]> byteVotes = Transformations.forCodification(codification, votes.getVotes());
		CountingAlgorithm algorithm = AlgorithmFactory.forCriteria(segment);
		try {
			algorithmResult = algorithm.count(pollId, byteVotes);
		} catch (InvalidCodificationException oops) {
			// special codification fallback
			byteVotes = Transformations.specialCodification(votes.getVotes());
			algorithmResult = algorithm.count(pollId, byteVotes);
			status = Status.SPECIAL_COD_FALLBACK;
			codification = Transformations.SPECIAL_COD;
		}
		return response(codification, segment, algorithmResult, status);
	}

	@RequestMapping("/{pollId}/charts")
	public ModelAndView chart(@PathVariable String pollId,
			@RequestParam(value = "cod", required = false) String codification,
			@RequestParam(value = "segment", required = false) SegmentationCriteria segment) {
		final ModelAndView model = new ModelAndView("non-segmented-charts");
		if (segment != null) {
			model.addObject("criteria", segment);
			model.setViewName("segmented-charts");
		}
		final ApiResponse count = referendum(pollId, codification, segment);
		final AlgorithmDetails algDetails = count.getAlgorithm();
		final List<AlgorithmResult> result = algDetails != null ? algDetails.getResult() : null;
		model.addObject("data", result);
		return model;
	}

}