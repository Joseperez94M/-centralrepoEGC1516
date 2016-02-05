package es.us.agoraus.counting.controllers;

import java.util.List;

import es.us.agoraus.counting.algorithms.SegmentationCriteria;
import es.us.agoraus.counting.algorithms.Transformations;
import es.us.agoraus.counting.dto.AlgorithmDetails;
import es.us.agoraus.counting.dto.AlgorithmResult;
import es.us.agoraus.counting.dto.ApiResponse;
import es.us.agoraus.counting.dto.Status;

public class BaseController {

	protected static ApiResponse response(String codification, SegmentationCriteria segment,
			List<AlgorithmResult> algorithmResult, Status status) {
		ApiResponse result = new ApiResponse(status);
		AlgorithmDetails algDetails = algorithmDetails(codification, segment, algorithmResult);
		result.setAlgorithm(algDetails);
		return result;
	}

	protected static ApiResponse response(Status status) {
		ApiResponse result = new ApiResponse(status);
		return result;
	}

	protected static AlgorithmDetails algorithmDetails(String codification, SegmentationCriteria segment,
			List<AlgorithmResult> algorithmResult) {
		AlgorithmDetails result = new AlgorithmDetails();
		String cod = codification == null || codification == Transformations.NORMAL_COD ? Transformations.NORMAL_COD
				: Transformations.SPECIAL_COD;
		result.setCodification(cod);
		result.setSegmentation(segment);
		result.setResult(algorithmResult);
		return result;
	}

}
