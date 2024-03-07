package com.probank.accounts.services.external.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.probank.accounts.dtos.external.CardDto;

@FeignClient(name = "CARDS-SERVICE")
public interface CardsFeignClient {
	@GetMapping(value = "/cardsms/customers/cards/{customerNumber}", consumes = "application/json")
	ResponseEntity<List<CardDto>> getAllCardsDetailsByCustomerNumber(@PathVariable int customerNumber);
}
