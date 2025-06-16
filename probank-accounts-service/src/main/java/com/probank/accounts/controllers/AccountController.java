package com.probank.accounts.controllers;

import java.util.HashMap;
import java.util.LinkedHashMap;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.probank.accounts.constants.GlobalConstants;
import com.probank.accounts.dtos.AccountDto;
import com.probank.accounts.dtos.CustomerDto;
import com.probank.accounts.entities.Account;
import com.probank.accounts.services.AccountService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@Tag(name = "ACCOUNTS CONTROLLER", description = "This controller will help you to perform accounts related operations..!!")
public class AccountController {
	
	@Value("${app.environment}")
	private String appEnvironment;

	@Autowired
	private AccountService accountService;

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private Environment environment;

	@Operation(summary = "Creating Account and Customer",description = "Rest Api to create Account and Customer")
	@ApiResponse(responseCode = "201",description = "Http Status CREATED")
	@PostMapping("/accounts")
	public ResponseEntity<AccountDto> createCustomerAndAccount(@Valid @RequestBody CustomerDto customerDto) {
		Account createdAccount = accountService.createCustomerAndAccount(customerDto,
				GlobalConstants.ACCOUNT_TYPE_SAVINGS);
		AccountDto accountDto = modelMapper.map(createdAccount, AccountDto.class);
		return new ResponseEntity<>(accountDto, HttpStatus.CREATED);
	}
	
	@GetMapping("/test")
	public ResponseEntity<HashMap<String, String>> testEndPoint() {
		LinkedHashMap<String, String> testHashMap = new LinkedHashMap<>();
		testHashMap.put("APP-ENV", appEnvironment);
		testHashMap.put("APP-NAME", environment.getProperty("spring.application.name"));
		testHashMap.put("APP-MAVEN-HOME", environment.getProperty("MAVEN_HOME"));
		testHashMap.put("APP-JAVA-HOME", environment.getProperty("JAVA_HOME"));
		testHashMap.put("APP-JAVA-VERSION", System.getProperty("java.version"));
		return new ResponseEntity<>(testHashMap, HttpStatus.OK);
	}
}
