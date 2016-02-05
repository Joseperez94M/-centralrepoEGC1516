package es.us.agoraus.counting.algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.us.agoraus.counting.dto.Answer;
import es.us.agoraus.counting.dto.PartialSegmentResult;
import es.us.agoraus.counting.dto.AlgorithmResult;
import es.us.agoraus.counting.dto.SegmentedResult;
import es.us.agoraus.counting.dto.Vote;

public class SegmentationAlgorithm extends BaseAlgorithm {

	private SegmentationCriteria criteria;

	public SegmentationAlgorithm(final SegmentationCriteria criteria) {
		this.criteria = criteria;
	}

	@Override
	protected List<AlgorithmResult> countingLogic(final List<Vote> votes) {
		final List<AlgorithmResult> result = new ArrayList<AlgorithmResult>();
		final Map<String, AlgorithmResult> questionsKeys = new HashMap<String, AlgorithmResult>();
		PartialSegmentResult partialRes;
		for (Vote v : votes) {
			for (Answer a : v.getAnswers()) {
				final String answer = a.getAnswer();
				final String question = a.getQuestion();
				final String segment = getSegment(v);
				if (!questionsKeys.keySet().contains(question)) {
					final SegmentedResult segRes = new SegmentedResult(question);
					questionsKeys.put(question, segRes);
					result.add(segRes);
					partialRes = segRes.addSegment(segment);
					incrementCount(answer, partialRes);
				} else {
					final AlgorithmResult r = questionsKeys.get(question);
					partialRes = ((SegmentedResult) r).getSegments().get(segment);
					if (partialRes == null) {
						partialRes = ((SegmentedResult) r).addSegment(segment);
					}
					incrementCount(answer, partialRes);
				}
			}
		}
		return result;
	}

	public SegmentationCriteria getCriteria() {
		return criteria;
	}

	public void setCriteria(SegmentationCriteria criteria) {
		this.criteria = criteria;
	}

	private String getSegment(final Vote vote) {
		String result = null;
		switch (criteria) {
		case age:
			result = vote.getAge();
			break;
		case gender:
			result = vote.getGender();
			break;
		case aut_com:
			result = vote.getAutonomousCommunity();
		}
		return result;
	}

}
