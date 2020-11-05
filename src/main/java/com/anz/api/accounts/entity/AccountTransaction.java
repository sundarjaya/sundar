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
@Table(name = "TB_ACCOUNT_TRANSACTION")
public class AccountTransaction  {

	public AccountTransaction() {
		
	}
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "ACCOUNT_NUMBER", nullable = false)
	private String accountNumber;
	
	@Column(name = "ACCOUNT_NAME", nullable = false)
	private String accountName;
	
	@Column(name = "VALUE_DATE", nullable = false)
	private Date valueDate;
	
	@Column(name = "CURRENCY", nullable = false)
	private String currency;
	
	@Column(name = "DEBIT_AMOUNT", nullable = true)
	private BigDecimal debitAmount;
	
	@Column(name = "CREDIT_AMOUNT", nullable = true)
	private BigDecimal creditAmount;
	
	@Column(name = "TYPE", nullable = true)
	private String type;
	
	@Column(name = "REMARKS", nullable = true)
	private String txnRemarks;
	
	
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
	public Date getValueDate() {
		return valueDate;
	}
	public void setValueDate(Date valueDate) {
		this.valueDate = valueDate;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public BigDecimal getDebitAmount() {
		return debitAmount;
	}
	public void setDebitAmount(BigDecimal debitAmount) {
		this.debitAmount = debitAmount;
	}
	public BigDecimal getCreditAmount() {
		return creditAmount;
	}
	public void setCreditAmount(BigDecimal creditAmount) {
		this.creditAmount = creditAmount;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTxnRemarks() {
		return txnRemarks;
	}
	public void setTxnRemarks(String txnRemarks) {
		this.txnRemarks = txnRemarks;
	}
	
	
	
	
}
