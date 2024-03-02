package com.probank.cards.dtos;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "CARD DTO", description = "Schema to hold Card Information !!")
public class CardDto {

	private int cardId;

	private String cardNumber;

	private String cardType;

	private int totalLimit;

	private int amountUsed;

	private int amountAvailable;
	
	private int customerNumber;
}
