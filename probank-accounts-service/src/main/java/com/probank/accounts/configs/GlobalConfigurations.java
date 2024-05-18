package com.probank.accounts.configs;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
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
	RedissonClient redissonClient() {
		Config config = new Config();
		config.useSingleServer().setAddress("redis://localhost:6379");
		return Redisson.create(config);
	}

	@Bean
	MoneyProcessor getMoneyProcessor() {
		FiveHundredRupeeMoneyProcessor fiveHundredRupeeMoneyProcessor = new FiveHundredRupeeMoneyProcessor();
		OneHundredRupeeMoneyProcessor oneHundredRupeeMoneyProcessor = new OneHundredRupeeMoneyProcessor();
		TenRupeeMoneyProcessor tenRupeeMoneyProcessor = new TenRupeeMoneyProcessor();
		fiveHundredRupeeMoneyProcessor.nextMoneyProcessor(oneHundredRupeeMoneyProcessor);
		fiveHundredRupeeMoneyProcessor.setNoteService(noteService);
		oneHundredRupeeMoneyProcessor.nextMoneyProcessor(tenRupeeMoneyProcessor);
		oneHundredRupeeMoneyProcessor.setNoteService(noteService);
		tenRupeeMoneyProcessor.nextMoneyProcessor(null);
		tenRupeeMoneyProcessor.setNoteService(noteService);
		return fiveHundredRupeeMoneyProcessor;
	}
}
