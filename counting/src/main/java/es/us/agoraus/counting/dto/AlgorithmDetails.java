package es.us.agoraus.counting.dto;

import java.util.List;

import es.us.agoraus.counting.algorithms.SegmentationCriteria;

public class AlgorithmDetails {

	private String codification;
	private SegmentationCriteria segmentation;
	private List<AlgorithmResult> result;

	public String getCodification() {
		return codification;
	}

	public void setCodification(String codification) {
		this.codification = codification;
	}

	public SegmentationCriteria getSegmentation() {
		return segmentation;
	}

	public void setSegmentation(SegmentationCriteria segmentation) {
		this.segmentation = segmentation;
	}

	public List<AlgorithmResult> getResult() {
		return result;
	}

	public void setResult(List<AlgorithmResult> result) {
		this.result = result;
	}

}
