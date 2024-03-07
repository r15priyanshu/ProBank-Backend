package com.probank.accounts.services.external.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.probank.accounts.dtos.external.LoanDto;

@FeignClient(name = "LOANS-SERVICE")
public interface loansFeignClient {

	@GetMapping(value = "/lms/customers/loans/{customerNumber}", consumes = "application/json")
	ResponseEntity<List<LoanDto>> getAllLoansDetailsByCustomerNumber(@PathVariable int customerNumber);
}
