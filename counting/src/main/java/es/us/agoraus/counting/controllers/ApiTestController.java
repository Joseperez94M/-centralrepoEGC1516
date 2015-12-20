package es.us.agoraus.counting.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.us.agoraus.counting.algorithms.CountingAlgorithm;
import es.us.agoraus.counting.algorithms.ReferendumAlgorithm;
import es.us.agoraus.counting.algorithms.Test;
import es.us.agoraus.counting.algorithms.Transformations;
import es.us.agoraus.counting.domain.Resultado;
import es.us.agoraus.counting.domain.VotosCifrados;
import es.us.agoraus.counting.integration.StorageServiceImpl;

@RestController
@RequestMapping(value="/count")
public class ApiTestController {
	
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
	public List<Resultado> predefinedCounting()
			throws Exception {

		List <Resultado> resultados = Test.referendumAlgorithmTestVotation();
		
		return resultados;

	}

	/**
	 * The following method compute a votation, obtaining the crypted 
	 * votes from a database. We offer two ways to code the votes
	 * of a certain votation. After we obtain the votes, they are transformed
	 * in order to the codification obtained in the method call, and 
	 * finally it runs the algorithm where the decrypt is done and the
	 * votes are counted.
	 * @param pollId
	 * @param codification
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/referendum")
	public List<Resultado> referendum(@RequestParam(value = "pollId", required = true) String pollId, @RequestParam (value = "cod", required = false) String codification)
			throws Exception {
		
		VotosCifrados votes;
		List<byte[]> byteVotes;
		
		votes = storageService.getVotesForPoll(pollId);
		
		if ((codification == null) || (codification == "normal")) {
			byteVotes = Transformations.transformStringToByteArray(votes.getVotes());
		} else {
			byteVotes = Transformations.transformByteArrayStringToByteArray(votes.getVotes());
		}
		
		final CountingAlgorithm algorithm = new ReferendumAlgorithm();
		final List<Resultado> resultados = algorithm.count(pollId, byteVotes);
		
		return resultados;

	}

}