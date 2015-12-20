package es.us.agoraus.counting.algorithms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import es.us.agoraus.counting.domain.Answer;
import es.us.agoraus.counting.domain.Resultado;
import es.us.agoraus.counting.domain.Voto;

public class ReferendumAlgorithm extends BaseAlgorithm {

	@Override
	protected List<Resultado> countingLogic(List<Voto> votes) {
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
