package es.us.agoraus.counting.dto;

public class ApiResponse {

	private int code;
	private String message;
	private AlgorithmDetails algorithm;

	public ApiResponse(Status status) {
		this.code = status.getCode();
		this.message = status.getMessage();
	}
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public AlgorithmDetails getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(AlgorithmDetails algorithm) {
		this.algorithm = algorithm;
	}

}
