package com.probank.accounts.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.probank.accounts.dtos.CustomerDto;
import com.probank.accounts.dtos.external.CardDto;
import com.probank.accounts.dtos.external.LoanDto;
import com.probank.accounts.entities.Customer;
import com.probank.accounts.exceptions.GlobalCustomException;
import com.probank.accounts.services.CustomerService;
import com.probank.accounts.services.external.client.CardsFeignClient;
import com.probank.accounts.services.external.client.LoansFeignClient;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@Tag(name = "CUSTOMERS CONTROLLER", description = "This controller will help you to perform customer related operations..!!")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private CardsFeignClient cardsFeignClient;

	@Autowired
	private LoansFeignClient loansFeignClient;

	@GetMapping("/customers/{customerNumber}")
	private ResponseEntity<CustomerDto> fetchCustomerDetailsByCustomerNumber(@PathVariable int customerNumber) {
		log.info("Inside fetchCustomerDetailsByCustomerNumber for : {}",customerNumber);
		Customer foundCustomer = customerService.findByCustomerNumber(customerNumber).orElseThrow(
				() -> new GlobalCustomException("No Customer Found With customer Number : " + customerNumber,
						HttpStatus.NOT_FOUND));
		return new ResponseEntity<>(customerService.mapCustomerToCustomerDto(foundCustomer), HttpStatus.OK);
	}

	@GetMapping("/customers/alldetails/{customerNumber}")
	private ResponseEntity<CustomerDto> fetchAllCustomerDetailsByCustomerNumber(@PathVariable int customerNumber) {
		Customer foundCustomer = customerService.findByCustomerNumber(customerNumber).orElseThrow(
				() -> new GlobalCustomException("No Customer Found With customer Number : " + customerNumber,
						HttpStatus.NOT_FOUND));
		CustomerDto customerDto = customerService.mapCustomerToCustomerDto(foundCustomer);

		// CALLING CARDS MICROSERVICE
		ResponseEntity<List<CardDto>> cardsResponse = cardsFeignClient
				.getAllCardsDetailsByCustomerNumber(customerNumber);
		if (cardsResponse != null) {
			customerDto.setCards(cardsResponse.getBody());
		}

		// CALLING LOANS MICROSERVICE
		ResponseEntity<List<LoanDto>> loansResponse = loansFeignClient
				.getAllLoansDetailsByCustomerNumber(customerNumber);
		if (loansResponse != null) {
			customerDto.setLoans(loansResponse.getBody());
		}
		return new ResponseEntity<>(customerDto, HttpStatus.OK);
	}

	@GetMapping("/customers")
	public ResponseEntity<List<CustomerDto>> fetchAllCustomers() {
		List<Customer> allCustomers = customerService.getAllCustomers();
		List<CustomerDto> allCustomersDto = allCustomers.stream()
				.map(customer -> customerService.mapCustomerToCustomerDto(customer)).collect(Collectors.toList());
		return new ResponseEntity<>(allCustomersDto, HttpStatus.OK);
	}
}
