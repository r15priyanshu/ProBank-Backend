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
	public static final String TEN_RUPEE_NOTE = "TEN_RUPEE_NOTE";
	public static final int TEN_RUPEE_NOTE_INITIAL_COUNT = 10;
	public static final String ONE_HUNDRED_RUPEE_NOTE = "ONE_HUNDRED_RUPEE_NOTE";
	public static final int ONE_HUNDRED_RUPEE_NOTE_INITIAL_COUNT = 20;
	public static final String FIVE_HUNDRED_RUPEE_NOTE = "FIVE_HUNDRED_RUPEE_NOTE";
	public static final int FIVE_HUNDRED_RUPEE_NOTE_INITIAL_COUNT = 15;
	public static final String NOTE_DISPENSER_LOCK_NAME = "NOTE_DISPENSER_LOCK_NAME";
}
