package com.probank.loans.services;

import java.util.Optional;

import com.probank.loans.dtos.LoanDto;
import com.probank.loans.entities.Loan;

public interface LoanService {

	Loan createLoan(LoanDto loanDto);

	Optional<Loan> fetchLoanDetailsByLoanNumber(int loanNumber);

	Loan mapLoanDtoToLoan(LoanDto loanDto);
	
	LoanDto mapLoanToLoanDto(Loan loan);
}
