package com.probank.accounts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.probank.accounts.entities.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

}
