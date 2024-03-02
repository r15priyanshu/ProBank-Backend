package com.probank.cards.services;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.probank.cards.constants.GlobalConstants;
import com.probank.cards.dtos.CardDto;
import com.probank.cards.entities.Card;
import com.probank.cards.repositories.CardRepository;
import com.probank.cards.utils.GlobalUtils;

@Service
public class CardServiceImpl implements CardService {

	@Autowired
	private CardRepository cardRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Card createCard(CardDto cardDto) {
		Card newCard = new Card();
		newCard.setCardNumber(GlobalUtils.randomNDigitNumber(GlobalConstants.CARD_NUMBER_LENGTH) + "");
		newCard.setCardType(cardDto.getCardType());
		newCard.setTotalLimit(cardDto.getTotalLimit());
		newCard.setAmountUsed(0);
		newCard.setAmountAvailable(cardDto.getTotalLimit());
		newCard.setCustomerNumber(cardDto.getCustomerNumber());
		return cardRepository.save(newCard);
	}

	@Override
	public Optional<Card> fetchCardDetailsByCardNumber(String cardNumber) {
		return cardRepository.findByCardNumber(cardNumber);
	}

	@Override
	public Card mapCardDtoToCard(CardDto cardDto) {
		return modelMapper.map(cardDto, Card.class);
	}

	@Override
	public CardDto mapCardToCardDto(Card card) {
		return modelMapper.map(card, CardDto.class);
	}

}
