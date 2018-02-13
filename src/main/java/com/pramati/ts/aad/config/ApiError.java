package com.pramati.ts.aad.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiError {
	 
    private HttpStatus status;
    private String message;
    private List<String> errors;
 
    public ApiError(HttpStatus status, String message, List<String> errors) {
        super();
        this.setStatus(status);
        this.message = message;
        this.errors = errors;
    }
 
    public ApiError(HttpStatus status, String message, String error) {
        super();
        this.setStatus(status);
        this.message = message;
        errors = Arrays.asList(error);
    }

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}
}
