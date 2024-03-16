package com.probank.accounts.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.probank.accounts.entities.Account;

import jakarta.transaction.Transactional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
	Optional<Account> findByAccountNumber(int accountNumber);

	@Transactional
	@Modifying
	@Query("UPDATE Account a SET a.isEmailNotificationSent=:status WHERE a.accountNumber=:accountNumber")
	int updateIsEmailNotificationSent(@Param("accountNumber") int accountNumber, @Param("status") boolean status);

	@Transactional
	@Modifying
	@Query("UPDATE Account a SET a.isSmsNotificationSent=:status WHERE a.accountNumber=:accountNumber")
	int updateIsSmsNotificationSent(@Param("accountNumber") int accountNumber, @Param("status") boolean status);
}
