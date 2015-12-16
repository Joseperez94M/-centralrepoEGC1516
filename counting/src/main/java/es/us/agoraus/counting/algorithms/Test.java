package es.us.agoraus.counting.algorithms;
 
import java.util.ArrayList;
import java.util.List;

import es.us.agoraus.counting.domain.Resultado;
import es.us.agoraus.counting.security.Token;
import main.java.AuthorityImpl;
public class Test {
 
	public static List<Resultado> naturalCountingAlgorithmTestVotation() throws Exception {
		
		Integer token;
		AuthorityImpl auth;
		List<Resultado> result;
		List<byte[]> cryptedVotes;
		
		cryptedVotes = new ArrayList<byte[]>();
		result= new ArrayList<Resultado>();
		auth = new AuthorityImpl();
		token = Token.calculateToken(1);
		
		String vote1 = "{\"age\": \"24\",\"answers\":[{\"question\":\"¿Es usuario de linux?\",\"answer_question\":\"SI\"}"
                + "],\"id\": 1,\"autonomous_community\": \"Andalucia\",\"genre\": \"Masculino\",\"id_poll\": 32778}";
        
        String vote2 = "{\"age\": \"24\",\"answers\":[{\"question\":\"¿Es usuario de linux?\",\"answer_question\":\"NO\"}"
				+ "],\"id\": 1,\"autonomous_community\": \"Andalucia\",\"genre\": \"Masculino\",\"id_poll\": 32778}";

        String vote3 = "{\"age\": \"24\",\"answers\":[{\"question\":\"¿Le ha gustado EGC?\",\"answer_question\":\"SI\"}"
				+ "],\"id\": 1,\"autonomous_community\": \"Andalucia\",\"genre\": \"Masculino\",\"id_poll\": 32778}";

        String vote4 = "{\"age\": \"24\",\"answers\":[{\"question\":\"¿Le ha gustado EGC?\",\"answer_question\":\"SI\"}"
				+ "],\"id\": 1,\"autonomous_community\": \"Andalucia\",\"genre\": \"Masculino\",\"id_poll\": 32778}";
        
        
        byte[] cryptedVote1 = auth.encrypt("1", vote1, token);
        byte[] cryptedVote2 = auth.encrypt("1", vote2, token);
        byte[] cryptedVote3 = auth.encrypt("1", vote3, token);
        byte[] cryptedVote4 = auth.encrypt("1", vote4, token);
        
        cryptedVotes.add(cryptedVote1);
        cryptedVotes.add(cryptedVote2);
        cryptedVotes.add(cryptedVote3);
        cryptedVotes.add(cryptedVote4);
        
        result = Algoritmo.naturalCountingAlgorithm("1", cryptedVotes);
        
        return result;
	
	}
 
 
}
