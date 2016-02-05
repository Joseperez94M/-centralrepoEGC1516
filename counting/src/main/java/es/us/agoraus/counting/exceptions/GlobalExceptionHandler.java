package es.us.agoraus.counting.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import es.us.agoraus.counting.dto.ApiResponse;
import es.us.agoraus.counting.dto.Status;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { Exception.class, RuntimeException.class })
	@ResponseBody
	public ResponseEntity<ApiResponse> generalExceptionHandler(Exception e) {
		ApiResponse apiResponse = new ApiResponse(Status.INTERNAL_ERROR);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = InvalidVoteException.class)
	@ResponseBody
	public ResponseEntity<ApiResponse> invalidVoteExceptionHandler(Exception e) {
		ApiResponse apiResponse = new ApiResponse(Status.INVALID_VOTE);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
	}

}
