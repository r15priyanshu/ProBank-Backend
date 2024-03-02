package com.probank.accounts.constants;

public class GlobalConstants {

	private GlobalConstants() {
		throw new UnsupportedOperationException("Cannot create instance for this class !!");
	}

	public static final int MINIMUM_EIGHT_DIGIT_NUMBER = 10000000;
	public static final int CUSTOMER_NUMBER_LENGTH = 8;
	public static final int ACCOUNT_NUMBER_LENGTH = 8;
	public static final String ACCOUNT_TYPE_SAVINGS = "SAVINGS";
	public static final String ACCOUNT_TYPE_CURRENT = "CURRENT";
}
