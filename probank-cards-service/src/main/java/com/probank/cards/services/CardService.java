package com.probank.cards.services;

import java.util.List;
import java.util.Optional;

import com.probank.cards.dtos.CardDto;
import com.probank.cards.entities.Card;

public interface CardService {

	Card createCard(CardDto cardDto);

	Optional<Card> fetchCardDetailsByCardNumber(String cardNumber);

	Card mapCardDtoToCard(CardDto cardDto);

	CardDto mapCardToCardDto(Card card);
	
	List<Card> fetchAllCardsDetailsByCustomerNumber(int customerNumber);
}
