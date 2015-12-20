package es.us.agoraus.counting.algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;

import es.us.agoraus.counting.domain.Resultado;
import es.us.agoraus.counting.domain.Voto;
import es.us.agoraus.counting.security.Token;
import main.java.AuthorityImpl;

public abstract class BaseAlgorithm implements CountingAlgorithm {

	private static final Logger LOG = Logger.getLogger(BaseAlgorithm.class.getCanonicalName());

	public List<Voto> decryptVotes(final String pollId, final List<byte[]> votesArr) {
		Integer token;
		Integer intPollId;
		AuthorityImpl auth;
		List<Voto> result;

		intPollId = Integer.valueOf(pollId);
		token = Token.calculateToken(intPollId);
		auth = new AuthorityImpl();
		result = new ArrayList<Voto>();

		for (byte[] s : votesArr) {
			if (auth.checkVote(s, pollId, token)) {
				String res = null;
				try {
					res = auth.decrypt(pollId, s, token);
				} catch (Throwable oops) {
					LOG.log(Level.SEVERE, "Error decrypting the votes.");
				}
				Gson gson = new Gson();
				Voto vot = gson.fromJson(res, Voto.class);
				result.add(vot);
			}
		}
		return result;
	}

	public List<Resultado> count(final String pollId, final List<byte[]> votesArr) {
		final List<Voto> votes = decryptVotes(pollId, votesArr);
		return countingLogic(votes);
	}

	protected abstract List<Resultado> countingLogic(final List<Voto> votes);

}
