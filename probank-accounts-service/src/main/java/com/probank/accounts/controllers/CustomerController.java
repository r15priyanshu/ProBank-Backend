package com.probank.accounts.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.probank.accounts.dtos.CardDto;
import com.probank.accounts.dtos.CustomerDto;
import com.probank.accounts.entities.Customer;
import com.probank.accounts.exceptions.GlobalCustomException;
import com.probank.accounts.services.CustomerService;
import com.probank.accounts.services.external.client.CardsFeignClient;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "CUSTOMERS CONTROLLER", description = "This controller will help you to perform customer related operations..!!")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private CardsFeignClient cardsFeignClient;

	@GetMapping("/customers/{customerNumber}")
	private ResponseEntity<CustomerDto> fetchCustomerDetailsByCustomerNumber(@PathVariable int customerNumber) {
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
		//CALLING CARDS MICROSERVICE
		ResponseEntity<List<CardDto>> cardsResponse = cardsFeignClient.getAllCardsDetailsByCustomerNumber(customerNumber);
		customerDto.setCards(cardsResponse.getBody());
		return new ResponseEntity<>(customerDto, HttpStatus.OK);
	}
}
