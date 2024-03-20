package com.probank.probankapigateway.configs;

import java.time.LocalDateTime;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.gateway.support.RouteMetadataUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GlobalConfigurations {

	@Bean
	RouteLocator getCustomRouteLocator(RouteLocatorBuilder builder) {

		// WE ADDED CUSTOM PREFIX ALSO "/probank" BEFORE EVERY URL STARTS AND WRITE THE
		// LOGIC TO TRANSFER IT TO DIFFERENT MS

		// NOTE : If you are using circuit breaker then metadata timeout will not work ,
		// if you are not using circuit breaker then it will work because circuit
		// breaker uses its own timeout [ You can change that if you want , bydefault it
		// will be 1000ms ]

		return builder.routes().route(p -> p.path("/probank/probank-accounts-service/**")
				.filters(f -> f.rewritePath("/probank/probank-accounts-service/?(?<remaining>.*)", "/${remaining}")
						.addRequestHeader("X-request-start-time", LocalDateTime.now().toString())
						.addResponseHeader("X-request-end-time", LocalDateTime.now().toString())
						.circuitBreaker(config -> {
							config.setName("accountsCircuitBreaker");
							config.setFallbackUri("forward:/fallBackForAccountsMicroservice");
						}).metadata(RouteMetadataUtils.CONNECT_TIMEOUT_ATTR, 1000)
						.metadata(RouteMetadataUtils.RESPONSE_TIMEOUT_ATTR, 5000))
				.uri("lb://PROBANK-ACCOUNTS-SERVICE"))
				.route(p -> p.path("/probank/probank-loans-service/**")
						.filters(f -> f.rewritePath("/probank/probank-loans-service/?(?<remaining>.*)", "/${remaining}")
								.addRequestHeader("X-request-start-time", LocalDateTime.now().toString())
								.addResponseHeader("X-request-end-time", LocalDateTime.now().toString())
								.circuitBreaker(config -> {
									config.setName("loansCircuitBreaker");
									config.setFallbackUri("forward:/fallBackForLoansMicroservice");
								}))
						.uri("lb://PROBANK-LOANS-SERVICE"))
				.route(p -> p.path("/probank/probank-cards-service/**")
						.filters(f -> f.rewritePath("/probank/probank-cards-service/?(?<remaining>.*)", "/${remaining}")
								.addRequestHeader("X-request-start-time", LocalDateTime.now().toString())
								.addResponseHeader("X-request-end-time", LocalDateTime.now().toString())
								.circuitBreaker(config -> {
									config.setName("cardsCircuitBreaker");
									config.setFallbackUri("forward:/fallBackForCardsMicroservice");
								}))
						.uri("lb://PROBANK-CARDS-SERVICE"))
				.build();
	}
}
