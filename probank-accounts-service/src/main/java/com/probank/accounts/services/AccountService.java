package com.probank.accounts.services;

import com.probank.accounts.dtos.AccountDto;
import com.probank.accounts.dtos.CustomerDto;
import com.probank.accounts.entities.Account;

public interface AccountService {
	Account createCustomerAndAccount(CustomerDto customerDto, String accountType);

	Account mapAccountDtoToAccount(AccountDto accountDto);

	AccountDto mapAccountToAccountDto(Account account);
}
