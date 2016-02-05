package es.us.agoraus.counting.algorithms;

import java.util.List;

import es.us.agoraus.counting.dto.AlgorithmResult;

public interface CountingAlgorithm {

	/**
	 * Count is the main algorithm method.
	 * 
	 * @param pollId
	 * @param votesArr
	 * @return results' list
	 */
	List<AlgorithmResult> count(final String pollId, final List<byte[]> votesArr);

}
