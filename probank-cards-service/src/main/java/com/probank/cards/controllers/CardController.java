package com.probank.cards.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.probank.cards.dtos.CardDto;
import com.probank.cards.entities.Card;
import com.probank.cards.exceptions.GlobalCustomException;
import com.probank.cards.services.CardService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@Tag(name = "CARDS CONTROLLER", description = "This controller will help you to perform cards related operations..!!")
public class CardController {

	@Value("${app.environment}")
	private String appEnvironment;

	@Autowired
	private CardService cardService;

	@Autowired
	private Environment environment;

	@Operation(summary = "Creating Card", description = "Rest Api to create Card")
	@ApiResponse(responseCode = "201", description = "Http Status CREATED")
	@PostMapping("/cards")
	public ResponseEntity<CardDto> createCard(@Valid @RequestBody CardDto cardDto) {
		Card card = cardService.createCard(cardDto);
		CardDto response = cardService.mapCardToCardDto(card);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@GetMapping("/cards/{cardNumber}")
	private ResponseEntity<CardDto> getCardDetails(@PathVariable String cardNumber) {
		Card foundCard = cardService.fetchCardDetailsByCardNumber(cardNumber)
				.orElseThrow(() -> new GlobalCustomException("Card not found with card Number : " + cardNumber,
						HttpStatus.NOT_FOUND));
		CardDto response = cardService.mapCardToCardDto(foundCard);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/customers/cards/{customerNumber}")
	private ResponseEntity<List<CardDto>> getAllCardsDetailsByCustomerNumber(@PathVariable int customerNumber) {
		List<Card> foundCards = cardService.fetchAllCardsDetailsByCustomerNumber(customerNumber);
		List<CardDto> foundCardsDto = foundCards.stream().map(card -> cardService.mapCardToCardDto(card))
				.collect(Collectors.toList());
		return new ResponseEntity<>(foundCardsDto, HttpStatus.OK);
	}

	@GetMapping("/test")
	public ResponseEntity<HashMap<String, String>> testEndpoind() {
		HashMap<String, String> test = new HashMap<>();
		test.put("APP-ENV", appEnvironment);
		test.put("APP-NANE", environment.getProperty("spring.application.name"));
		test.put("APP-JAVA-HOME-PATH", environment.getProperty("JAVA_HOME"));
		return new ResponseEntity<>(test, HttpStatus.OK);
	}
}
