package es.us.agoraus.counting.integration;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.core.GenericTypeResolver;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public abstract class BaseIntegrationService<SERVICE> {

	private static final Logger LOG = Logger.getLogger(BaseIntegrationService.class);

	private SERVICE service;

	@PostConstruct
	public void init() {
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(getBaseUrl())
				.addConverterFactory(GsonConverterFactory.create()).build();
		service = retrofit.create(getServiceInterface());
	}

	public <RESPONSE> RESPONSE sync(final Call<RESPONSE> call) {
		RESPONSE result = null;
		try {
			final Response<RESPONSE> response = call.execute();
			if (response != null) {
				result = response.body();
			}
		} catch (Throwable oops) {
			LOG.error("There was an error executing the call synchronously", oops);
		}
		return result;
	}

	public <RESPONSE> void async(final Call<RESPONSE> call, final Callback<RESPONSE> callback) {
		call.enqueue(callback);
	}

	public SERVICE getService() {
		return service;
	}
	
	@SuppressWarnings("unchecked")
	protected Class<SERVICE> getServiceInterface() {
		return (Class<SERVICE>) GenericTypeResolver.resolveTypeArgument(getClass(), BaseIntegrationService.class);
	}

	protected abstract String getBaseUrl();

}
