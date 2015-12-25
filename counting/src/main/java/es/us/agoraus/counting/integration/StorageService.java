package es.us.agoraus.counting.integration;

import es.us.agoraus.counting.domain.EncryptedVotes;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

public interface StorageService {

	@GET("/get_votes.php")
	Call<EncryptedVotes> getVotesForPoll(@Query("votation_id") String pollId);
	
}
