package com.probank.accounts.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MoneyDto {
	private int totalAmount;
	
	private boolean isWithdrawalSuccess;
	
	private int fiveHundredRupeeNoteCount;

	private int oneHundredRupeeNoteCount;

	private int tenRupeeNoteCount;
}
