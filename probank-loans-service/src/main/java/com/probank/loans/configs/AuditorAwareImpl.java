package com.probank.loans.configs;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;

public class AuditorAwareImpl implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
		//This will help to set the createdby and modifiedBy in the entities
		//TODO Use Security to find the actual user instead of static value
		return Optional.ofNullable("Anshu-lms");
	}
}
