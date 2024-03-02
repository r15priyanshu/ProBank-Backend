package com.probank.loans.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GlobalCustomException extends RuntimeException {
	private static final long serialVersionUID = -1222551122074210439L;
	private String message;
	private HttpStatus httpStatus;

	public GlobalCustomException(String message, HttpStatus httpStatus) {
		super(message);
		this.message = message;
		this.httpStatus = httpStatus;
	}
}
