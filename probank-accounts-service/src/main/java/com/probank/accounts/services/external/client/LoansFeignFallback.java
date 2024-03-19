package com.probank.accounts.services.external.client;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.probank.accounts.dtos.external.LoanDto;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LoansFeignFallback implements LoansFeignClient{

	@Override
	public ResponseEntity<List<LoanDto>> getAllLoansDetailsByCustomerNumber(int customerNumber) {
		// IF EXTERNAL CALL FAILS , THEN WE DON'T WANT TO GET RE , HENCE WE WILL RETURN
		// NULL EXPLICITLY
		log.error("Fallback Executed For getAllLoansDetailsByCustomerNumber !!");
		return null;
	}

}
