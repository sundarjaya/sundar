package com.anz.api.accounts.services;

import java.util.List;

import com.anz.api.accounts.entity.AccountTransaction;
import com.anz.api.accounts.entity.Accounts;

public interface AccountService {

	public List<Accounts> getUserAccounts();
	
	public List<AccountTransaction> getUserAccountTxn(String accountNumber);
	
	public Accounts saveAccount(Accounts entity);
	
	public AccountTransaction saveAccountTxn(AccountTransaction entity);
}
