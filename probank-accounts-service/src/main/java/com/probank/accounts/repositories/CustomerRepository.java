package com.probank.accounts.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.probank.accounts.entities.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	Optional<Customer> findByCustomerNumber(int customerNumber);
	Optional<Customer> findByMobileNumber(String mobileNumber);
	Optional<Customer> findByEmail(String email);
}
