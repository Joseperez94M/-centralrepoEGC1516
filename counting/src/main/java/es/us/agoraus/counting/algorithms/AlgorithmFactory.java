package es.us.agoraus.counting.algorithms;

public class AlgorithmFactory {

	/**
	 * Algorithm factory main method. It creates the desired algorithm based on
	 * a criteria.
	 * 
	 * @param criteria
	 * @return an algorithm which interface is CountingAlgorithm
	 */
	public static CountingAlgorithm forCriteria(SegmentationCriteria criteria) {
		CountingAlgorithm result = new ReferendumAlgorithm();
		if (criteria != null) {
			result = new SegmentationAlgorithm(criteria);
		}
		return result;
	}

}
