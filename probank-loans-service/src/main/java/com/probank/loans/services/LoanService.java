package com.probank.loans.services;

import java.util.List;
import java.util.Optional;

import com.probank.loans.dtos.LoanDto;
import com.probank.loans.entities.Loan;

public interface LoanService {

	Loan createLoan(LoanDto loanDto);

	Optional<Loan> fetchLoanDetailsByLoanNumber(int loanNumber);
	
	List<Loan> fetchAllLoansDetailsByCustomerNumber(int customerNumber);

	Loan mapLoanDtoToLoan(LoanDto loanDto);
	
	LoanDto mapLoanToLoanDto(Loan loan);
}
