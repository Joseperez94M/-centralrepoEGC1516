package es.us.agoraus.counting.integration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import es.us.agoraus.counting.domain.VotosCifrados;
import retrofit.Call;

@Component
public class StorageServiceImpl extends BaseIntegrationService<StorageService> {

	@Value("${storage.base.url}")
	private String storageBaseUrl;

	public VotosCifrados getVotesForPoll(String pollId) {
		final Call<VotosCifrados> call = getService().getVotesForPoll(pollId);
		return sync(call);
	}

	@Override
	protected String getBaseUrl() {
		return storageBaseUrl;
	}

}
