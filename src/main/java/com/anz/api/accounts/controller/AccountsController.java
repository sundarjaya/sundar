package com.anz.api.accounts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.anz.api.accounts.entity.AccountTransaction;
import com.anz.api.accounts.entity.Accounts;
import com.anz.api.accounts.services.AccountService;

@RestController
public class AccountsController {

   @Autowired
   private AccountService service;
  
   @RequestMapping(value = "/api/v1/accounts", method=RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<List<Accounts>> getAllAccounts() {
        return new ResponseEntity<List<Accounts>>(service.getUserAccounts(),HttpStatus.OK);
   }

 
    @RequestMapping(value = "/api/v1/accounts/{accountNumber}", method=RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AccountTransaction>> getAccountTxnById(@PathVariable(value = "accountNumber") String accountNumber) {
    	 return new ResponseEntity<List<AccountTransaction>>(service.getUserAccountTxn(accountNumber),HttpStatus.OK);
    }

   
    @RequestMapping(value = "/api/v1/accounts/save", method=RequestMethod.POST, produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Accounts> saveAccount(@RequestHeader HttpHeaders headers,@RequestBody Accounts account) {
    	return new ResponseEntity<Accounts>(service.saveAccount(account),HttpStatus.OK);
    }
  
       
    @RequestMapping(value = "/api/v1/accounts/txn/save", method=RequestMethod.POST, produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountTransaction> saveAccount(@RequestHeader HttpHeaders headers,@RequestBody AccountTransaction accountTxn) {
    	 return new ResponseEntity<AccountTransaction>(service.saveAccountTxn(accountTxn),HttpStatus.OK);
    }

    
    @RequestMapping("/test")
	public String index() {
		return "Greetings from Spring Boot!";
	}

    
}
