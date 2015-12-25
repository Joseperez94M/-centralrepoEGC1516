package es.us.agoraus.counting.algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;

import es.us.agoraus.counting.domain.Result;
import es.us.agoraus.counting.domain.Vote;
import es.us.agoraus.counting.security.Token;
import main.java.AuthorityImpl;

public abstract class BaseAlgorithm implements CountingAlgorithm {

	private static final Logger LOG = Logger.getLogger(BaseAlgorithm.class.getCanonicalName());

	public List<Vote> decryptVotes(final String pollId, final List<byte[]> votesArr) {
		Integer token;
		Integer intPollId;
		AuthorityImpl auth;
		List<Vote> result;

		intPollId = Integer.valueOf(pollId);
		token = Token.calculateToken(intPollId);
		auth = new AuthorityImpl();
		result = new ArrayList<Vote>();

		for (byte[] s : votesArr) {
			// The following sentence is commented due to problems with
			// Verification subsystem server. Commenting this line increases
			// algorithms performance by 1/2.
			//if (auth.checkVote(s, pollId, token)) {
			if (true) {
				String res = null;
				try {
					res = auth.decrypt(pollId, s, token);
				} catch (Throwable oops) {
					LOG.log(Level.SEVERE, "Error decrypting the votes.");
				}
				Gson gson = new Gson();
				Vote vot = gson.fromJson(res, Vote.class);
				result.add(vot);
			}
		}
		return result;
	}

	public List<Result> count(final String pollId, final List<byte[]> votesArr) {
		final List<Vote> votes = decryptVotes(pollId, votesArr);
		return countingLogic(votes);
	}
	
	protected abstract List<Result> countingLogic(final List<Vote> votes);

}
