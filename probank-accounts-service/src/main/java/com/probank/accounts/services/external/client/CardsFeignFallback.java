package com.probank.accounts.services.external.client;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.probank.accounts.dtos.external.CardDto;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CardsFeignFallback implements CardsFeignClient {

	@Override
	public ResponseEntity<List<CardDto>> getAllCardsDetailsByCustomerNumber(int customerNumber) {
		// IF EXTERNAL CALL FAILS , THEN WE DON'T WANT TO GET RE , HENCE WE WILL RETURN
		// NULL EXPLICITLY
		log.error("Fallback Executed For getAllCardsDetailsByCustomerNumber !!");
		return null;
	}

}
