package com.probank.accounts.functions;

import java.util.Optional;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.probank.accounts.entities.Account;
import com.probank.accounts.repositories.AccountRepository;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class AccountsFunctions {

	@Autowired
	private AccountRepository accountRepository;

	@Bean
	public Consumer<Integer> updateCommunicationEmail() {
		return (accountNumber) -> {
			Optional<Account> optional = accountRepository.findByAccountNumber(accountNumber);
			if (optional.isPresent()) {
				accountRepository.updateIsEmailNotificationSent(accountNumber, true);
				log.info("Account Information [ Email Notification ] Updated For Account Number : {}", accountNumber);
			} else {
				log.info("Account Not Found For Account Number : {}", accountNumber);
			}
		};
	}

	@Bean
	public Consumer<Integer> updateCommunicationSms() {
		return (accountNumber) -> {
			Optional<Account> optional = accountRepository.findByAccountNumber(accountNumber);
			if (optional.isPresent()) {
				accountRepository.updateIsSmsNotificationSent(accountNumber,true);
				log.info("Account Information [ Sms Notification ] Updated For Account Number : {}", accountNumber);
			} else {
				log.info("Account Not Found For Account Number : {}", accountNumber);
			}
		};
	}
}
