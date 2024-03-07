package com.probank.accounts.dtos.external;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoanDto {

	private int loanId;

	private int loanNumber;

	private String loanType;

	private int totalLoan;

	private int amountPaid;

	private int outstandingAmount;

	private int customerNumber;
}
