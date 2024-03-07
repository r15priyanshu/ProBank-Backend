package com.probank.loans.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.probank.loans.entities.Loan;

public interface LoanRepository extends JpaRepository<Loan, Integer> {
	Optional<Loan> findByLoanNumber(int loanNumber);
	
	List<Loan> findByCustomerNumber(int customerNumber);
}
