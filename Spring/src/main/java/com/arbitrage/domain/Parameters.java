package com.arbitrage.domain;

public class Parameters {
String companyName;
String transaction;
public Parameters(String companyName, String transaction) {
	super();
	this.companyName = companyName;
	this.transaction = transaction;
}
public String getCompanyName() {
	return companyName;
}
public void setCompanyName(String companyName) {
	this.companyName = companyName;
}
public String getTransaction() {
	return transaction;
}
public void setTransaction(String transaction) {
	this.transaction = transaction;
}

}
