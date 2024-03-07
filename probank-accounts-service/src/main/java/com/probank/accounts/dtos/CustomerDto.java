package com.probank.accounts.dtos;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(name = "CUSTOMER DTO",description = "Schema to hold Customer Information !!")
public class CustomerDto {
	private int customerId;
	private int customerNumber;
	
	@NotEmpty(message = "Name cannot be empty !!")
	private String name;
	
	@NotEmpty(message = "Email cannot be empty !!")
	@Email(message = "Email not in valid form !!")
	private String email;
	
	@Pattern(regexp = "^[1-9]\\d{9}$",message = "Mobile number must be 10 digits with the first digit not being 0 !!")
	private String mobileNumber;
	
	private List<CardDto> cards;
}
