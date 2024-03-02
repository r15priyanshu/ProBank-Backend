package com.probank.cards.utils;

import java.util.Random;

import jakarta.servlet.http.HttpServletRequest;

public class GlobalUtils {

	public static long randomNDigitNumber(int digits) {
		Random random = new Random();
		long minValue = (long) Math.pow(10, digits - 1);
		long maxValue = (long) (Math.pow(10, digits) - 1);
		return random.nextLong(minValue, maxValue);
	}

	public static String generateCompleteUrlWithRequestParams(HttpServletRequest request) {
		StringBuilder urlBuilder = new StringBuilder();
		// Get the request URI (path)
		String requestUri = request.getRequestURI();
		urlBuilder.append(requestUri);
		// Get the query string
		String queryString = request.getQueryString();
		if (queryString != null) {
			urlBuilder.append("?").append(queryString);
		}
		return urlBuilder.toString();
	}
}
