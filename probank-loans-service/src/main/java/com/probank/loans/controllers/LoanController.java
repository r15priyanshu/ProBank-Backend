package com.probank.loans.controllers;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.probank.loans.dtos.LoanDto;
import com.probank.loans.entities.Loan;
import com.probank.loans.exceptions.GlobalCustomException;
import com.probank.loans.services.LoanService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@Tag(name = "LOANS CONTROLLER", description = "This controller will help you to perform loans related operations..!!")
public class LoanController {
	
	@Value("${app.environment}")
	private String appEnvironment;

	@Autowired
	private LoanService loanService;
	
	@Autowired
	private Environment environment;

	@Operation(summary = "Creating Loan", description = "Rest Api to create Loan")
	@ApiResponse(responseCode = "201", description = "Http Status CREATED")
	@PostMapping("/loans")
	public ResponseEntity<LoanDto> createLoan(@Valid @RequestBody LoanDto loanDto) {
		Loan createdLoan = loanService.createLoan(loanDto);
		LoanDto response = loanService.mapLoanToLoanDto(createdLoan);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@GetMapping("/loans/{loanNumber}")
	private ResponseEntity<Loan> getLoanDetailsByLoanNumber(@PathVariable Integer loanNumber) {
		Loan foundLoan = loanService.fetchLoanDetailsByLoanNumber(loanNumber)
				.orElseThrow(() -> new GlobalCustomException("Loan not found with loanNumber : " + loanNumber,
						HttpStatus.NOT_FOUND));

		return new ResponseEntity<>(foundLoan, HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/test")
	public ResponseEntity<HashMap<String, String>> testEndpoind() {
		HashMap<String, String> test=new HashMap<>();
		test.put("APP-ENV", appEnvironment);
		test.put("APP-NANE", environment.getProperty("spring.application.name"));
		test.put("APP-JAVA-HOME-PATH", environment.getProperty("JAVA_HOME"));
		return new ResponseEntity<>(test, HttpStatus.OK);
	}
}
