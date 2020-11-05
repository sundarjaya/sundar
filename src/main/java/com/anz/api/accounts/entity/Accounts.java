package com.anz.api.accounts.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "TB_ACCOUNTS")
public class Accounts {

	public Accounts() {
		
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
		
	@Column(name = "ACCOUNT_NUMBER", nullable = false)
	private String accountNumber;
	
	@Column(name = "ACCOUNT_NAME", nullable = false)
	private String accountName;
	
	@Column(name = "ACCOUNT_TYPE", nullable = false)
    private String accountType;
	
	@Column(name = "BALANCE_DATE", nullable = false)
    private Date balanceDate;
  
	@Column(name = "CURRENCY", nullable = false)
	private String currency;
	
	@Column(name = "AVAILABLE_BALANCE", nullable = false)
    private BigDecimal openingAvailableBalance;
   
	public long getId() {
		return id;
	}
	
	

	public void setId(long id) {
		this.id = id;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public Date getBalanceDate() {
		return balanceDate;
	}
	public void setBalanceDate(Date balanceDate) {
		this.balanceDate = balanceDate;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public BigDecimal getOpeningAvailableBalance() {
		return openingAvailableBalance;
	}
	public void setOpeningAvailableBalance(BigDecimal openingAvailableBalance) {
		this.openingAvailableBalance = openingAvailableBalance;
	}

   
	
}
