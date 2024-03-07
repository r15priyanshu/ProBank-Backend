package com.probank.accounts.dtos.external;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardDto {

	private int cardId;

	private String cardNumber;

	private String cardType;

	private int totalLimit;

	private int amountUsed;

	private int amountAvailable;
	
	private int customerNumber;
}
