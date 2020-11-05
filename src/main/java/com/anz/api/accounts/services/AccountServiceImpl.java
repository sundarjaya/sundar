package com.anz.api.accounts.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.anz.api.accounts.entity.AccountTransaction;
import com.anz.api.accounts.entity.Accounts;
import com.anz.api.accounts.repository.AccountRepository;
import com.anz.api.accounts.repository.AccountTxnRepository;

@Component
@Service
public class AccountServiceImpl implements AccountService{

	 @Autowired
	 private AccountRepository accountRepo;
	   
	 @Autowired
	 private AccountTxnRepository accountTxnRepo;

	 
	 public List<Accounts> getUserAccounts(){
		return accountRepo.findAll();
	 }
	 public List<AccountTransaction> getUserAccountTxn(String accountNumber){
		return accountTxnRepo.findByAccountNumber(accountNumber);
	}
	 
	 public Accounts saveAccount(Accounts entity){
		return accountRepo.save(entity);
	 }
	 public AccountTransaction saveAccountTxn(AccountTransaction entity){
		 return accountTxnRepo.save(entity);
	 }

}
