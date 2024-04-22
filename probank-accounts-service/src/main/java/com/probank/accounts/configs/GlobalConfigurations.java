package com.probank.accounts.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import com.probank.accounts.services.NoteService;
import com.probank.accounts.services.processors.FiveHundredRupeeMoneyProcessor;
import com.probank.accounts.services.processors.MoneyProcessor;
import com.probank.accounts.services.processors.OneHundredRupeeMoneyProcessor;
import com.probank.accounts.services.processors.TenRupeeMoneyProcessor;

@Configuration
public class GlobalConfigurations {
	
	@Autowired
	private NoteService noteService;

	@Bean(name = "auditorAware")
	AuditorAware<String> auditorAware() {
		return new AuditorAwareImpl();
	}
	
	@Bean
	MoneyProcessor getMoneyProcessor() {
		FiveHundredRupeeMoneyProcessor fiveHundredRupeeMoneyProcessor=new FiveHundredRupeeMoneyProcessor();
		OneHundredRupeeMoneyProcessor oneHundredRupeeMoneyProcessor=new OneHundredRupeeMoneyProcessor();
		TenRupeeMoneyProcessor tenRupeeMoneyProcessor=new TenRupeeMoneyProcessor();
		fiveHundredRupeeMoneyProcessor.nextMoneyProcessor(oneHundredRupeeMoneyProcessor);
		fiveHundredRupeeMoneyProcessor.setNoteService(noteService);
		oneHundredRupeeMoneyProcessor.nextMoneyProcessor(tenRupeeMoneyProcessor);
		oneHundredRupeeMoneyProcessor.setNoteService(noteService);
		tenRupeeMoneyProcessor.nextMoneyProcessor(null);
		tenRupeeMoneyProcessor.setNoteService(noteService);
		return fiveHundredRupeeMoneyProcessor;
	}
}
