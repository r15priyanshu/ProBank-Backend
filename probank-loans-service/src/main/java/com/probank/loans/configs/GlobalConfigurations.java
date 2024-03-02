package com.probank.loans.configs;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

@Configuration
public class GlobalConfigurations {

	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean(name = "auditorAware")
	AuditorAware<String> auditorAware() {
		return new AuditorAwareImpl();
	}
}
