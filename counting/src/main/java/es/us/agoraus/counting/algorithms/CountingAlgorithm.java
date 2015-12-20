package es.us.agoraus.counting.algorithms;

import java.util.List;

import es.us.agoraus.counting.domain.Resultado;

public interface CountingAlgorithm {

	List<Resultado> count(final String pollId, final List<byte[]> votesArr);

}
