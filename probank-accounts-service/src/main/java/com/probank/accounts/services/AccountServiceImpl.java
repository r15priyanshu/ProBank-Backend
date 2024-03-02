package com.probank.accounts.services;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.probank.accounts.constants.GlobalConstants;
import com.probank.accounts.dtos.AccountDto;
import com.probank.accounts.dtos.CustomerDto;
import com.probank.accounts.entities.Account;
import com.probank.accounts.entities.Customer;
import com.probank.accounts.exceptions.GlobalCustomException;
import com.probank.accounts.repositories.AccountRepository;
import com.probank.accounts.utils.GlobalUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Account createCustomerAndAccount(CustomerDto customerDto, String accountType) {
		Optional<Customer> findByEmail = customerService.findByEmail(customerDto.getEmail());
		if (findByEmail.isPresent()) {
			throw new GlobalCustomException("Email is already Registered !! Kindly provide different email !!",
					HttpStatus.BAD_REQUEST);
		}

		Optional<Customer> findByMobileNumber = customerService.findByMobileNumber(customerDto.getMobileNumber());
		if (findByMobileNumber.isPresent()) {
			throw new GlobalCustomException("Mobile is already Registered !! Kindly provide different mobile number !!",
					HttpStatus.BAD_REQUEST);
		}
		
		//WE WILL MANUALLY CREATE NEW CUSTOMER OBJ
		Customer newCustomer=new  Customer();
		newCustomer.setCustomerNumber((int) GlobalUtils.randomNDigitNumber(GlobalConstants.CUSTOMER_NUMBER_LENGTH));
		newCustomer.setName(customerDto.getName());
		newCustomer.setEmail(customerDto.getEmail());
		newCustomer.setMobileNumber(customerDto.getMobileNumber());
		
		//WE WILL MANUALLY CREATE NEW ACCOUNT OBJ
		Account newAccount = new Account();
		newAccount.setAccountNumber((int) GlobalUtils.randomNDigitNumber(GlobalConstants.ACCOUNT_NUMBER_LENGTH));
		newAccount.setAccountType(accountType);
		newAccount.setCustomer(newCustomer);
		return accountRepository.save(newAccount);
	}

	public Account mapAccountDtoToAccount(AccountDto accountDto) {
		return modelMapper.map(accountDto, Account.class);
	}

	public AccountDto mapAccountToAccountDto(Account account) {
		return modelMapper.map(account, AccountDto.class);
	}

	
}
