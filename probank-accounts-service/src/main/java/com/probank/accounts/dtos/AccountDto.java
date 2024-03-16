package com.probank.accounts.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "ACCOUNT DTO", description = "Schema to hold Account Information !!")
public class AccountDto {
	private int accountId;

	@Pattern(regexp = "^[1-9]\\d{7}$", message = "Account number must be 8 digits with the first digit not being 0 !!")
	private int accountNumber;

	@NotEmpty(message = "Account Type cannot be empty !!")
	private String accountType;
	private boolean isEmailNotificationSent;
	private boolean isSmsNotificationSent;

	private CustomerDto customer;
}
