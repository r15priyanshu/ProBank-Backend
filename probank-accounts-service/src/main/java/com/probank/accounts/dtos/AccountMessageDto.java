package com.probank.accounts.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AccountMessageDto {
	private int accountNumber;
	private String name;
	private String email;
	private String mobileNumber;
}
