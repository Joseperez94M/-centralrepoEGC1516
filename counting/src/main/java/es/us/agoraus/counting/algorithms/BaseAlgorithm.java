package es.us.agoraus.counting.algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;

import es.us.agoraus.counting.dto.AlgorithmResult;
import es.us.agoraus.counting.dto.Vote;
import es.us.agoraus.counting.dto.YesNoSettable;
import es.us.agoraus.counting.exceptions.InvalidCodificationException;
import es.us.agoraus.counting.exceptions.InvalidVoteException;
import es.us.agoraus.counting.security.Token;
import main.java.AuthorityImpl;

public abstract class BaseAlgorithm implements CountingAlgorithm {

	private static final Logger LOG = Logger.getLogger(BaseAlgorithm.class.getCanonicalName());

	protected static final String YES_ANSWER = "SI";
	protected static final String NO_ANSWER = "NO";

	/**
	 * Class's main method. It decrypts votes and calls the counting function.
	 */
	public List<AlgorithmResult> count(final String pollId, final List<byte[]> votesArr) {
		final List<Vote> votes = decryptVotes(pollId, votesArr);
		return countingLogic(votes);
	}

	/**
	 * The following method implements the necessary logic to decrypt votes.
	 * This method is used after we have transformed the votes from the stored
	 * string to a manipulable object.
	 * 
	 * @param pollId
	 * @param votesArr
	 * @return decrypted votes' list
	 */
	public List<Vote> decryptVotes(final String pollId, final List<byte[]> votesArr) {
		Integer token;
		Integer intPollId;
		AuthorityImpl auth;
		List<Vote> result;

		intPollId = Integer.valueOf(pollId);
		// Token used to communicate securely with Verification Subsystem
		token = Token.calculateToken(intPollId);
		auth = new AuthorityImpl();
		result = new ArrayList<Vote>();

		for (byte[] s : votesArr) {
			// The following sentence is commented due to problems with
			// Verification subsystem server. Commenting this line increases
			// algorithms performance by 1/2.
			// if (auth.checkVote(s, pollId, token)) {
			if (true) {
				String res = null;
				try {
					res = auth.decrypt(pollId, s, token);
				} catch (Throwable oops) {
					LOG.log(Level.SEVERE, "Error decrypting the votes.");
				}
				if (res == null) {
					throw new InvalidCodificationException();
				}
				Gson gson = new Gson();
				Vote vote = gson.fromJson(res, Vote.class);
				if (!vote.isValid()) {
					throw new InvalidVoteException();
				}
				result.add(vote);
			}
		}
		return result;
	}

	/**
	 * Method used to count poll's results, setting them in result parameter.
	 * 
	 * @param answer
	 * @param result
	 */
	protected void incrementCount(final String answer, final YesNoSettable result) {
		if (YES_ANSWER.equals(answer.toUpperCase())) {
			result.setYes(result.getYes() + 1);
		} else if ((NO_ANSWER.equals(answer.toUpperCase()))) {
			result.setNo(result.getNo() + 1);
		}
	}

	/**
	 * The following method does the own counting of a certain algorithm. It
	 * must be overridden by algorithms extending this class.
	 * 
	 * @param votes
	 * @return results' list
	 */
	protected abstract List<AlgorithmResult> countingLogic(final List<Vote> votes);

}
