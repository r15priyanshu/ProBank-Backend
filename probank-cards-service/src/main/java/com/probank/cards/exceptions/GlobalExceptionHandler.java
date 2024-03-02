package com.probank.cards.exceptions;

import java.time.LocalDateTime;
import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.probank.cards.dtos.ApiResponseDto;
import com.probank.cards.utils.GlobalUtils;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(GlobalCustomException.class)
	public ResponseEntity<ApiResponseDto> handleGlobalCustomException(GlobalCustomException e,
			HttpServletRequest request) {
		ApiResponseDto apiResponseDto = ApiResponseDto.builder()
				.apiPath(GlobalUtils.generateCompleteUrlWithRequestParams(request)).message(e.getMessage())
				.timeStamp(LocalDateTime.now()).status(e.getHttpStatus()).statusCode(e.getHttpStatus().value()).build();
		return new ResponseEntity<>(apiResponseDto, e.getHttpStatus());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponseDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException e,
			HttpServletRequest request) {

		HashMap<String, Object> validationErrors = new HashMap<>();
		e.getBindingResult().getAllErrors()
				.forEach((ex) -> validationErrors.put(((FieldError) ex).getField(), ex.getDefaultMessage()));

		ApiResponseDto apiResponseDto = ApiResponseDto.builder()
				.apiPath(GlobalUtils.generateCompleteUrlWithRequestParams(request)).message("Please resolve the errors before proceeding ahead !!")
				.timeStamp(LocalDateTime.now()).status(HttpStatus.BAD_REQUEST)
				.statusCode(HttpStatus.BAD_REQUEST.value()).validationErrors(validationErrors).build();

		return new ResponseEntity<>(apiResponseDto, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponseDto> handleException(Exception e,
			HttpServletRequest request) {
		ApiResponseDto apiResponseDto = ApiResponseDto.builder()
				.apiPath(GlobalUtils.generateCompleteUrlWithRequestParams(request)).message(e.getMessage())
				.timeStamp(LocalDateTime.now()).status(HttpStatus.INTERNAL_SERVER_ERROR).statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value()).build();
		return new ResponseEntity<>(apiResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
