package com.probank.probankapigateway.configs;

import java.util.UUID;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.probank.probankapigateway.constants.GlobalConstants;

import reactor.core.publisher.Mono;

@Order(-1) // Ensure this filter is executed first
@Component
public class RequestAndResponseTraceIdFilter implements GlobalFilter {

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		HttpHeaders headers = exchange.getRequest().getHeaders();
		String traceId = UUID.randomUUID().toString();
		if (!headers.containsKey(GlobalConstants.TRACE_ID_HEADER_NAME)) {
			exchange.getRequest().mutate()
					.headers(httpHeaders -> httpHeaders.add(GlobalConstants.TRACE_ID_HEADER_NAME, traceId));
		}

		exchange.getResponse().getHeaders().add(GlobalConstants.TRACE_ID_HEADER_NAME, traceId);
		return chain.filter(exchange);
	}

}
