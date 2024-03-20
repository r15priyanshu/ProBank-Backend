package com.probank.probankapigateway.configs;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.gateway.support.RouteMetadataUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

@Configuration
public class GlobalConfigurations {

	@Bean
	RouteLocator getCustomRouteLocator(RouteLocatorBuilder builder) {

		/*
		 * WE ADDED CUSTOM PREFIX ALSO "/probank" BEFORE EVERY URL STARTS AND WRITE THE
		 * LOGIC TO TRANSFER IT TO DIFFERENT MS
		 */

		/*
		 * NOTE : If you are using circuit breaker then metadata timeout will not work ,
		 * if you are not using circuit breaker then it will work because circuit
		 * breaker uses its own timeout [ You can change that if you want , bydefault it
		 * will be 1000ms ]
		 */

		/*
		 * NOTE: Here we are enabling retry also for all the GET methods , so if on
		 * calling the corresponding GET methods of the microservices gives 500 ISE ,
		 * then it will retry for the configured no of times
		 */
		return builder.routes().route(p -> p.path("/probank/probank-accounts-service/**")
				.filters(f -> f.rewritePath("/probank/probank-accounts-service/?(?<remaining>.*)", "/${remaining}")
						.addRequestHeader("X-request-start-time", LocalDateTime.now().toString())
						.addResponseHeader("X-request-end-time", LocalDateTime.now().toString())
						.circuitBreaker(config -> {
							config.setName("accountsCircuitBreaker");
							config.setFallbackUri("forward:/fallBackForAccountsMicroservice");
						})
						.retry(retryConfig -> retryConfig.setRetries(3).setMethods(HttpMethod.GET)
								.setBackoff(Duration.ofSeconds(1), Duration.ofSeconds(15), 2, true))
						.metadata(RouteMetadataUtils.CONNECT_TIMEOUT_ATTR, 1000)
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
