package com.probank.cards.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.probank.cards.entities.Card;

public interface CardRepository extends JpaRepository<Card, Integer> {
	Optional<Card> findByCardNumber(String cardNumber);
	
	List<Card> findByCustomerNumber(int customerNumber);
}
