package com.arbitrage.domain;

import java.sql.Date;

public class UserWishlist {
	int userId;
	String companyName;
	double arbitrage;
	int quantity;
	double currentMarketPrice;
	Date transactionDate;
	double NSE;
	double BSE;
	public UserWishlist(int userId,String companyName, double arbitrage, int quantity, double currentMarketPrice,
			Date transactionDate,double NSE,double BSE) {
		super();
		this.userId=userId;
		this.companyName = companyName;
		this.arbitrage = arbitrage;
		this.quantity = quantity;
		this.currentMarketPrice = currentMarketPrice;
		this.transactionDate = transactionDate;
		this.NSE=NSE;
		this.BSE=BSE;
	}
	public String getcompanyName() {
		return companyName;
	}
	public void setcompanyName(String companyName) {
		this.companyName = companyName;
	}
	public double getArbitrage() {
		return arbitrage;
	}
	public double getNSE() {
		return NSE;
	}
	public double getBSE() {
		return BSE;
	}
	public void setArbitrage(double arbitrage) {
		this.arbitrage = arbitrage;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getCurrentMarketPrice() {
		return currentMarketPrice;
	}
	public void setCurrentMarketPrice(double currentMarketPrice) {
		this.currentMarketPrice = currentMarketPrice;
	}
	public void setNSE(double NSE) {
		this.NSE = NSE;
	}
	public void setBSE(double BSE) {
		this.BSE = BSE;
	}
	public Date getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "UserWishlist [userId=" + userId + ", companyName=" + companyName + ", arbitrage=" + arbitrage
				+ ", quantity=" + quantity + ", currentMarketPrice=" + currentMarketPrice + ", transactionDate="
				+ transactionDate + ", NSE=" + NSE + ", BSE=" + BSE + "]";
	}
	
	
	

}
