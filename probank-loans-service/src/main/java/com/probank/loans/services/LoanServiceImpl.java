package com.probank.loans.services;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.probank.loans.constants.GlobalConstants;
import com.probank.loans.dtos.LoanDto;
import com.probank.loans.entities.Loan;
import com.probank.loans.repositories.LoanRepository;
import com.probank.loans.utils.GlobalUtils;

@Service
public class LoanServiceImpl implements LoanService {
	
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private LoanRepository loanRepository;

	@Override
	public Loan createLoan(LoanDto loanDto) {
		//WE WILL BE CREATING NEW LOAN OBJECT MANUALLY
		Loan newLoan = new Loan();
		newLoan.setLoanNumber((int) GlobalUtils.randomNDigitNumber(GlobalConstants.LOAN_NUMBER_LENGTH));
		newLoan.setLoanType(loanDto.getLoanType());
		newLoan.setTotalLoan(loanDto.getTotalLoan());
		newLoan.setAmountPaid(0);
		newLoan.setOutstandingAmount(loanDto.getTotalLoan());
		newLoan.setCustomerNumber(loanDto.getCustomerNumber());
		return loanRepository.save(newLoan);
	}

	@Override
	public Optional<Loan> fetchLoanDetailsByLoanNumber(int loanNumber) {
		return loanRepository.findByLoanNumber(loanNumber);
	}

	@Override
	public Loan mapLoanDtoToLoan(LoanDto loanDto) {
		return modelMapper.map(loanDto, Loan.class);
	}

	@Override
	public LoanDto mapLoanToLoanDto(Loan loan) {
		return modelMapper.map(loan, LoanDto.class);
	}
}
