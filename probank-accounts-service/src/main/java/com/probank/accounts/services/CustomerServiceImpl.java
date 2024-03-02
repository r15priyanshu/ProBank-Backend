package com.probank.accounts.services;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.probank.accounts.dtos.CustomerDto;
import com.probank.accounts.entities.Customer;
import com.probank.accounts.repositories.CustomerRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Customer createCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	public Optional<Customer> findByEmail(String email) {
		return customerRepository.findByEmail(email);
	}

	public Optional<Customer> findByMobileNumber(String mobileNumber) {
		return customerRepository.findByMobileNumber(mobileNumber);
	}

	@Override
	public Optional<Customer> findByCustomerNumber(int customerNumber) {
		return customerRepository.findByCustomerNumber(customerNumber);
	}

	@Override
	public Customer mapCustomerDtoToCustomer(CustomerDto customerDto) {
		return modelMapper.map(customerDto, Customer.class);
	}

	@Override
	public CustomerDto mapCustomerToCustomerDto(Customer customer) {
		return modelMapper.map(customer, CustomerDto.class);
	}

}
