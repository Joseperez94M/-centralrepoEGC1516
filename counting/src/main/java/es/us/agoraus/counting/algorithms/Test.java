package es.us.agoraus.counting.algorithms;
 
import java.util.ArrayList;
import java.util.List;

import es.us.agoraus.counting.dto.AlgorithmResult;
import es.us.agoraus.counting.security.Token;
import main.java.AuthorityImpl;
public class Test {
 
	/**
	 * This method is used to generate a test poll. In this method, some
	 * votes are created and crypted to check the main algorithm.
	 * It is used to debug our system in a isolated way.
	 * @return
	 * @throws Exception
	 */
	public static List<AlgorithmResult> referendumAlgorithmTestVotation() throws Exception {
		
		Integer pollId;
		String pollIdStr;
		Integer token;
		AuthorityImpl auth;
		List<AlgorithmResult> result;
		List<byte[]> encryptedVotes;
		
		pollId = 1;
		pollIdStr = String.valueOf(pollId);
		encryptedVotes = new ArrayList<byte[]>();
		result= new ArrayList<AlgorithmResult>();
		auth = new AuthorityImpl();
		token = Token.calculateToken(pollId);
		
		String vote1 = "{\"age\": \"22\",\"answers\":[{\"question\":\"¿Es usuario de linux?\",\"answer_question\":\"NO\"}"
                + "],\"id\": 1,\"autonomous_community\": \"Extremadura\",\"genre\": \"Masculino\",\"id_poll\": 32778}";
        
        String vote2 = "{\"age\": \"24\",\"answers\":[{\"question\":\"¿Es usuario de linux?\",\"answer_question\":\"NO\"}"
				+ "],\"id\": 1,\"autonomous_community\": \"Andalucia\",\"genre\": \"Masculino\",\"id_poll\": 32778}";

        String vote3 = "{\"age\": \"22\",\"answers\":[{\"question\":\"¿Le ha gustado EGC?\",\"answer_question\":\"SI\"}"
				+ "],\"id\": 1,\"autonomous_community\": \"Extremadura\",\"genre\": \"Masculino\",\"id_poll\": 32778}";

        String vote4 = "{\"age\": \"24\",\"answers\":[{\"question\":\"¿Le ha gustado EGC?\",\"answer_question\":\"SI\"}"
				+ "],\"id\": 1,\"autonomous_community\": \"Andalucia\",\"genre\": \"Masculino\",\"id_poll\": 32778}";
        
		String vote5 = "{\"age\": \"23\",\"answers\":[{\"question\":\"¿Es usuario de linux?\",\"answer_question\":\"NO\"}"
                + "],\"id\": 1,\"autonomous_community\": \"Extremadura\",\"genre\": \"Femenino\",\"id_poll\": 32778}";
        
        String vote6 = "{\"age\": \"23\",\"answers\":[{\"question\":\"¿Es usuario de linux?\",\"answer_question\":\"NO\"}"
				+ "],\"id\": 1,\"autonomous_community\": \"Andalucia\",\"genre\": \"Masculino\",\"id_poll\": 32778}";

		String vote7 = "{\"age\": \"23\",\"answers\":[{\"question\":\"¿Es usuario de linux?\",\"answer_question\":\"NO\"}"
                + "],\"id\": 1,\"autonomous_community\": \"Extremadura\",\"genre\": \"Masculino\",\"id_poll\": 32778}";
        
        String vote8 = "{\"age\": \"22\",\"answers\":[{\"question\":\"¿Es usuario de linux?\",\"answer_question\":\"NO\"}"
				+ "],\"id\": 1,\"autonomous_community\": \"Andalucia\",\"genre\": \"Femenino\",\"id_poll\": 32778}";
        
        String vote9 = "{\"age\": \"23\",\"answers\":[{\"question\":\"¿Le ha gustado EGC?\",\"answer_question\":\"SI\"}"
				+ "],\"id\": 1,\"autonomous_community\": \"Extremadura\",\"genre\": \"Femenino\",\"id_poll\": 32778}";

        String vote10 = "{\"age\": \"23\",\"answers\":[{\"question\":\"¿Le ha gustado EGC?\",\"answer_question\":\"SI\"}"
				+ "],\"id\": 1,\"autonomous_community\": \"Andalucia\",\"genre\": \"Masculino\",\"id_poll\": 32778}";

        String vote11 = "{\"age\": \"23\",\"answers\":[{\"question\":\"¿Le ha gustado EGC?\",\"answer_question\":\"NO\"}"
				+ "],\"id\": 1,\"autonomous_community\": \"Extremadura\",\"genre\": \"Masculino\",\"id_poll\": 32778}";

        String vote12 = "{\"age\": \"22\",\"answers\":[{\"question\":\"¿Le ha gustado EGC?\",\"answer_question\":\"NO\"}"
				+ "],\"id\": 1,\"autonomous_community\": \"Andalucia\",\"genre\": \"Femenino\",\"id_poll\": 32778}";
        

        byte[] encryptedVote1 = auth.encrypt(pollIdStr, vote1, token);
        byte[] encryptedVote2 = auth.encrypt(pollIdStr, vote2, token);
        byte[] encryptedVote3 = auth.encrypt(pollIdStr, vote3, token);
        byte[] encryptedVote4 = auth.encrypt(pollIdStr, vote4, token);
        byte[] encryptedVote5 = auth.encrypt(pollIdStr, vote5, token);
        byte[] encryptedVote6 = auth.encrypt(pollIdStr, vote6, token);
        byte[] encryptedVote7 = auth.encrypt(pollIdStr, vote7, token);
        byte[] encryptedVote8 = auth.encrypt(pollIdStr, vote8, token);
        byte[] encryptedVote9 = auth.encrypt(pollIdStr, vote9, token);
        byte[] encryptedVote10 = auth.encrypt(pollIdStr, vote10, token);
        byte[] encryptedVote11 = auth.encrypt(pollIdStr, vote11, token);
        byte[] encryptedVote12 = auth.encrypt(pollIdStr, vote12, token);
        
        encryptedVotes.add(encryptedVote1);
        encryptedVotes.add(encryptedVote2);
        encryptedVotes.add(encryptedVote3);
        encryptedVotes.add(encryptedVote4);
        encryptedVotes.add(encryptedVote5);
        encryptedVotes.add(encryptedVote6);
        encryptedVotes.add(encryptedVote7);
        encryptedVotes.add(encryptedVote8);
        encryptedVotes.add(encryptedVote9);
        encryptedVotes.add(encryptedVote10);
        encryptedVotes.add(encryptedVote11);
        encryptedVotes.add(encryptedVote12);
        
        final CountingAlgorithm algorithm = new ReferendumAlgorithm();
        result = algorithm.count(pollIdStr, encryptedVotes);

        return result;
	
	}
 
 
}
