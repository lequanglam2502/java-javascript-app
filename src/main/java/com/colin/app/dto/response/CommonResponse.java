package com.colin.app.dto.response;

import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.List;

public class CommonResponse implements Serializable{
	
    /**
	 * The common response for general message and error
	 * with HTTP status
	 */
	private static final long serialVersionUID = 1L;
	private HttpStatus status;
    private String message;
    private List<String> errors;

    public CommonResponse() {
    }

    public CommonResponse(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public CommonResponse(HttpStatus status, String message, List<String> errors) {
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
