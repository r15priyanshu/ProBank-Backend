package com.probank.loans.dtos;

import com.probank.loans.constants.GlobalConstants;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "LOAN DTO", description = "Schema to hold Loan Information !!")
public class LoanDto {

	private int loanId;
	private int loanNumber;

	@NotEmpty(message = "Loan Type cannot be empty !!")
	private String loanType;

	@Positive(message = "Total loan amount should be greater than 0")
	private int totalLoan;

	@PositiveOrZero(message = "Total loan amount paid should be equal or greater than 0")
	private int amountPaid;

	@PositiveOrZero(message = "Total outstanding amount should be equal or greater than 0")
	private int outstandingAmount;

	@Min(value = GlobalConstants.CUSTOMER_NUMBER_LENGTH, message = "Customer Number must be 8 digits with the first digit not being 0 !!")
	private int customerNumber;
}
