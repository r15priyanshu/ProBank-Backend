package com.probank.accounts.services.processors;

import com.probank.accounts.dtos.MoneyDto;

public interface MoneyProcessor {
	public MoneyDto processMoney(int amount,MoneyDto moneyDto);
	
	public void nextMoneyProcessor(MoneyProcessor moneyProcessor);
}
