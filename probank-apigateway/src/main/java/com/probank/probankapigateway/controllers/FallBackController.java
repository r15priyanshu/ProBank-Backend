package com.probank.probankapigateway.controllers;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.probank.probankapigateway.dtos.ApiResponseDto;

import reactor.core.publisher.Mono;

@RestController
public class FallBackController {

	@GetMapping("/fallBackForAccountsMicroservice")
	public ResponseEntity<Mono<ApiResponseDto>> fallBackForAccountsMicroservice() {
		ApiResponseDto response = ApiResponseDto.builder().message(
				"ACCOUNTS MICROSERVICE IS FACING SOME ISSUES !! THIS IS A FALLBACK RESPONSE CONFIGURED IN APIGATEWAY !!")
				.status(HttpStatus.SERVICE_UNAVAILABLE).statusCode(HttpStatus.SERVICE_UNAVAILABLE.value())
				.timeStamp(LocalDateTime.now()).build();

		return new ResponseEntity<>(Mono.just(response), HttpStatus.SERVICE_UNAVAILABLE);
	}

	@GetMapping("/fallBackForLoansMicroservice")
	public ResponseEntity<Mono<ApiResponseDto>> fallBackForLoansMicroservice() {
		ApiResponseDto response = ApiResponseDto.builder().message(
				"LOANS MICROSERVICE IS FACING SOME ISSUES !! THIS IS A FALLBACK RESPONSE CONFIGURED IN APIGATEWAY !!")
				.status(HttpStatus.SERVICE_UNAVAILABLE).statusCode(HttpStatus.SERVICE_UNAVAILABLE.value())
				.timeStamp(LocalDateTime.now()).build();

		return new ResponseEntity<>(Mono.just(response), HttpStatus.SERVICE_UNAVAILABLE);
	}

	@GetMapping("/fallBackForCardsMicroservice")
	public ResponseEntity<Mono<ApiResponseDto>> fallBackForCardsMicroservice() {
		ApiResponseDto response = ApiResponseDto.builder().message(
				"CARDS MICROSERVICE IS FACING SOME ISSUES !! THIS IS A FALLBACK RESPONSE CONFIGURED IN APIGATEWAY !!")
				.status(HttpStatus.SERVICE_UNAVAILABLE).statusCode(HttpStatus.SERVICE_UNAVAILABLE.value())
				.timeStamp(LocalDateTime.now()).build();

		return new ResponseEntity<>(Mono.just(response), HttpStatus.SERVICE_UNAVAILABLE);
	}
}
