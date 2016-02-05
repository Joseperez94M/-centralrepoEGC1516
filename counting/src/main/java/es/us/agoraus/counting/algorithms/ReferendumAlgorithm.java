package es.us.agoraus.counting.algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import es.us.agoraus.counting.dto.AlgorithmResult;
import es.us.agoraus.counting.dto.Answer;
import es.us.agoraus.counting.dto.ReferendumResult;
import es.us.agoraus.counting.dto.Vote;

public class ReferendumAlgorithm extends BaseAlgorithm {

	@Override
	protected List<AlgorithmResult> countingLogic(List<Vote> votes) {
		final Map<String, ReferendumResult> questionsKeys = new HashMap<String, ReferendumResult>();
		votes.stream().flatMap(vote -> vote.getAnswers().stream()).forEach(answer -> {
			ReferendumResult r;
			String question = answer.getQuestion();
			if (!questionsKeys.keySet().contains(question)) {
				r = new ReferendumResult(question);
				questionsKeys.put(question, r);
			} else {
				r = questionsKeys.get(question);
			}
			incrementCount(answer.getAnswer(), r);
		});
		return new ArrayList<AlgorithmResult>(questionsKeys.values());
	}

	/**
	 * Alternative implementation in a more functional but less efficient way.
	 */
	protected List<AlgorithmResult> moreFunctionalButLessPerformantLogic(List<Vote> votes) {
		Stream<Answer> answers = votes.stream().flatMap(vote -> vote.getAnswers().stream());
		Map<String, List<Answer>> mapping = answers.collect(Collectors.groupingBy(Answer::getQuestion));
		Stream<ReferendumResult> results = mapping.entrySet().stream().map(entrySet -> {
			String question = entrySet.getKey();
			List<Answer> qAnswers = entrySet.getValue();
			int yes = Math.toIntExact(qAnswers.stream().filter(answer -> isYes(answer)).count());
			int no = qAnswers.size() - yes;
			return new ReferendumResult(question, yes, no);
		});
		return results.collect(Collectors.toList());
	}

	protected boolean isYes(Answer answer) {
		return YES_ANSWER.equals(answer.getAnswer());
	}

}
