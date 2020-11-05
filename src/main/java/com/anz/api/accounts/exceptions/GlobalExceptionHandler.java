package com.anz.api.accounts.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
   
	@ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> detaultExceptionHandler(Exception ex, WebRequest request) {
        
		//Default internal server error
		int statusCode=500;
		
		if(ex instanceof HttpClientErrorException ) {
			//If it is RestAPI exception the status code any one this 400 or 401 or 403 or 404 or 500
			statusCode=((RestClientResponseException)ex).getRawStatusCode();
		}else if( ex instanceof HttpMessageNotReadableException || ex instanceof MethodArgumentNotValidException) {
			//If it is other Message Readable or Method Arguement exception make this code as 400 as bad request
			statusCode=400;
		}
		
		if(statusCode==HttpStatus.BAD_REQUEST.value()) {
			return apiError("Missing or Invalid Parameters",ex.getMessage(),HttpStatus.BAD_REQUEST);
		}else if(statusCode==HttpStatus.FORBIDDEN.value()) {
			return apiError("Access Not Configured",ex.getMessage(),HttpStatus.FORBIDDEN);
		}else if(statusCode==HttpStatus.UNAUTHORIZED.value()) {
			return apiError("Un Authorized",ex.getMessage(),HttpStatus.UNAUTHORIZED);
		}else if(statusCode==HttpStatus.NOT_FOUND.value()) {
			return apiError("Resource Not Found",ex.getMessage(),HttpStatus.NOT_FOUND);
		}else if(statusCode==HttpStatus.INTERNAL_SERVER_ERROR.value()) {
			return apiError("Server Unvailable",ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return null;
		
    }

	public ResponseEntity<ErrorDetails> apiError(String msg,String details, HttpStatus httpStatus){
		ErrorDetails error = new ErrorDetails(new Date(),msg,details);
		return new ResponseEntity<ErrorDetails>(error,httpStatus);
	}
}
