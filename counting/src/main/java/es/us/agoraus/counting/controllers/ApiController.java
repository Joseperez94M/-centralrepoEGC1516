package es.us.agoraus.counting.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import es.us.agoraus.counting.algorithms.CountingAlgorithm;
import es.us.agoraus.counting.algorithms.ReferendumAlgorithm;
import es.us.agoraus.counting.algorithms.SegmentationAlgorithm;
import es.us.agoraus.counting.algorithms.SegmentationCriteria;
import es.us.agoraus.counting.algorithms.Test;
import es.us.agoraus.counting.algorithms.Transformations;
import es.us.agoraus.counting.domain.EncryptedVotes;
import es.us.agoraus.counting.domain.Result;
import es.us.agoraus.counting.integration.StorageServiceImpl;

@RestController
@RequestMapping(value="/count")
public class ApiController {
	
	@Autowired
	StorageServiceImpl storageService;
	
	/**
	 * The following method is used to test the algorithm.
	 * We simulate a votation, creating the some votes json and
	 * crypting them. After that we call the method as if we obtained 
	 * that crypted votes from the database.
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/predefined")
	public List<Result> predefinedCounting()
			throws Exception {

		List <Result> resultados = Test.referendumAlgorithmTestVotation();
		
		return resultados;

	}

	/**
	 * The following method computes a votation retrieving the encrypted 
	 * votes from a database. We offer two ways to code the votes
	 * of a certain votation. After we obtain the votes, they are transformed
	 * in order to the codification obtained in the method call, and 
	 * finally it runs the algorithm where the decrypt is done and the
	 * votes are counted.
	 * @param pollId
	 * @param codification
	 * @param segment
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/{pollId}")
	public List<Result> referendum(@PathVariable String pollId, @RequestParam (value = "cod", required = false) String codification, @RequestParam (value = "segment", required = false) SegmentationCriteria segment) throws Exception {
		
		EncryptedVotes votes;
		List<byte[]> byteVotes;
		
		votes = storageService.getVotesForPoll(pollId);
		
		if ((codification == null) || (codification == "normal")) {
			byteVotes = Transformations.transformStringToByteArray(votes.getVotes());
		} else {
			byteVotes = Transformations.transformByteArrayStringToByteArray(votes.getVotes());
		}
		
		CountingAlgorithm algorithm = new ReferendumAlgorithm();
		
		if (segment != null) {
			algorithm = new SegmentationAlgorithm(segment);
		}
		
		final List<Result> resultados = algorithm.count(pollId, byteVotes);
		
		return resultados;

	}
	
	@RequestMapping("/{pollId}/charts")
	public ModelAndView chart(@PathVariable String pollId, @RequestParam (value = "cod", required = false) String codification, @RequestParam (value = "segment", required = false) SegmentationCriteria segment) throws Exception {
		if (segment == null) {
			segment = SegmentationCriteria.age;
		}
		final List<Result> result = referendum(pollId, codification, segment);
		ModelAndView model = new ModelAndView("charts");
		model.addObject("data", result);
		model.addObject("criteria", segment);
		return model;
	}

}