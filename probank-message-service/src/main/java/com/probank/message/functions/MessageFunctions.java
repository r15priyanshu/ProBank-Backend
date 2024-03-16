package com.probank.message.functions;

import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.probank.message.dtos.AccountMessageDto;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class MessageFunctions {

	@Bean
	public Function<AccountMessageDto, Integer> sendEmail() {
		// DELAYING JUST TO MAKE REAL TIME EXPERIENCE
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// NOTE : That the return type is integer , and if we just open the output
		// binding , spring cloud streams framework will bydefault send the response
		// back to the message broker , You don't need to send back response manually by
		// using StreamBridge
		return (msgDto) -> {
			log.info("Sending Email With Details : {}", msgDto);
			return msgDto.getAccountNumber();
		};
	}

	@Bean
	public Function<AccountMessageDto, Integer> sendSms() {
		// DELAYING JUST TO MAKE REAL TIME EXPERIENCE
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// NOTE : That the return type is integer , and if we just open the output
		// binding , spring cloud streams framework will bydefault send the response
		// back to the message broker , You don't need to send back response manually by
		// using StreamBridge
		return (msgDto) -> {
			log.info("Sending Sms With Details : {}", msgDto);
			return msgDto.getAccountNumber();
		};
	}
}
