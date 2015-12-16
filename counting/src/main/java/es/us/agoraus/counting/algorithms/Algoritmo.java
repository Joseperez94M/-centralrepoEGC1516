package es.us.agoraus.counting.algorithms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.gson.Gson;

import es.us.agoraus.counting.domain.Answer;
import es.us.agoraus.counting.domain.Resultado;
import es.us.agoraus.counting.domain.Voto;
import es.us.agoraus.counting.security.Token;
import main.java.AuthorityImpl;

public class Algoritmo {

	/**
	 * This function does the natural counting algorithm.
	 * 
	 * @param surveyId.
	 *            The id of the survey obtained in the controller
	 * @param votos
	 * @return
	 * @throws Exception
	 */
	public static List<Resultado> naturalCountingAlgorithm(String votationId, List<byte[]> votos) throws Exception {

		
		// First, the variables the algorithm needs are created
		Integer token;
		Integer numericSurveyId;
		AuthorityImpl auth;
		List<Voto> votes;

		// Second, the variables are initialized
		numericSurveyId = Integer.valueOf(votationId);
		token = Token.calculateToken(numericSurveyId);
		auth = new AuthorityImpl();
		votes = new ArrayList<Voto>();

		// Third,
		for (byte[] s : votos) {

			// bytesDecode = decoder.decodeBuffer(s);

			if (auth.checkVote(s, votationId, token)) {
				String res = null;
				res = auth.decrypt(votationId, s, token);

				// Voto voto = mapper.readValue(res,new
				// TypeReference<Voto>() {});

				Gson gson = new Gson();
				Voto vot = gson.fromJson(res, Voto.class);
				votes.add(vot);

			}

		}
		Set<String> claves = new HashSet<String>();
		for (Voto v : votes) {
			for (Answer a : v.getAnswers()) {
				claves.add(a.getQuestion());
			}
		}
		List<Resultado> resultados = new ArrayList<Resultado>();
		for (String c : claves) {
			resultados.add(new Resultado(c, 0, 0));

		}
		for (Voto v : votes) {
			for (Resultado r : resultados) {
				for (Answer a : v.getAnswers()) {

					if (a.getQuestion().equals(r.getPregunta())) {

						if (a.getAnswer_question().equals("SI")) {
							r.setNumeroSi(r.getNumeroSi() + 1);
						} else if (a.getAnswer_question().equals("NO")) {
							r.setNumeroNo(r.getNumeroNo() + 1);
						}
					}
				}
			}
		}

		return resultados;
	}
}