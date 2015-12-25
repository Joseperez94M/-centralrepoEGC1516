package es.us.agoraus.counting.algorithms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import es.us.agoraus.counting.domain.Answer;
import es.us.agoraus.counting.domain.ReferendumResult;
import es.us.agoraus.counting.domain.Result;
import es.us.agoraus.counting.domain.Vote;

public class ReferendumAlgorithm extends BaseAlgorithm {

	@Override
	protected List<Result> countingLogic(final List<Vote> votes) {
		final List<Result> results = new ArrayList<Result>();
		final Set<String> keys = new HashSet<String>();
		for (Vote v : votes) {
			for (Answer a : v.getAnswers()) {
				keys.add(a.getQuestion());
			}
		}
		for (String k : keys) {
			results.add(new ReferendumResult(k, 0, 0));
		}
		for (Vote v : votes) {
			for (Result r : results) {
				ReferendumResult refRes = (ReferendumResult) r;
				for (Answer a : v.getAnswers()) {
					if (a.getQuestion().equals(refRes.getQuestion())) {
						if (a.getAnswer_question().equals("SI")) {
							refRes.setYes(refRes.getYes() + 1);
						} else if (a.getAnswer_question().equals("NO")) {
							refRes.setNo(refRes.getNo() + 1);
						}
					}
				}
			}
		}
		return results;
	}

}
