package com.probank.accounts.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminDto {
	private int adminId;
	private String adminFullName;
	private String adminEmail;
	private String adminMobileNumber;
}
