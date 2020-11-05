package com.anz.api.accounts.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.anz.api.accounts.entity.AccountTransaction;

@Repository
public interface AccountTxnRepository extends JpaRepository<AccountTransaction, Long>{

	 @Query("SELECT a FROM AccountTransaction a WHERE a.accountNumber = :accountNumber")
	 public List<AccountTransaction> findByAccountNumber(@Param("accountNumber") String accountNumber);

}

