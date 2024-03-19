package com.probank.probankapigateway.dtos;

import java.time.LocalDateTime;
import java.util.HashMap;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiResponseDto {
	@JsonInclude(value = Include.NON_NULL)
	private String apiPath;
	private String message;
	private LocalDateTime timeStamp;
	private HttpStatus status;
	private int statusCode;

	@JsonInclude(value = Include.NON_NULL)
	private HashMap<String, Object> validationErrors;
}
