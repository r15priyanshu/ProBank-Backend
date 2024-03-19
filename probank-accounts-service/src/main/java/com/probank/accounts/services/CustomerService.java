package com.probank.accounts.services;

import java.util.List;
import java.util.Optional;

import com.probank.accounts.dtos.CustomerDto;
import com.probank.accounts.entities.Customer;

public interface CustomerService {

	Customer createCustomer(Customer customer);

	Optional<Customer> findByEmail(String email);

	Optional<Customer> findByMobileNumber(String mobileNumber);
	
	Optional<Customer> findByCustomerNumber(int customerNumber);
	
	List<Customer> getAllCustomers();

	Customer mapCustomerDtoToCustomer(CustomerDto customerDto);

	CustomerDto mapCustomerToCustomerDto(Customer customer);
}
