package es.us.agoraus.counting.domain;

import java.util.HashMap;
import java.util.Map;

public class SegmentedResult implements Result {

	private String question;
	private Map<String, PartialSegmentResult> segments;

	public SegmentedResult(final String question) {
		this.question = question;
		this.segments = new HashMap<String, PartialSegmentResult>();
	}

	public PartialSegmentResult addSegment(final String segment) {
		final PartialSegmentResult result = new PartialSegmentResult();
		segments.put(segment, result);
		return result;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Map<String, PartialSegmentResult> getSegments() {
		return segments;
	}

	public void setSegments(Map<String, PartialSegmentResult> segments) {
		this.segments = segments;
	}

}
