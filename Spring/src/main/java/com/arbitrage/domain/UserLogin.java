package com.arbitrage.domain;

public class UserLogin {
	private String username;
	private String password;
	private int userId;

	
	public UserLogin(int userId, String username, String password) {
		super();
		this.username = username;
		this.password = password;
		this.userId=userId;
	}

	public UserLogin() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Book [username=" + username + ", password=" + password + "]";
	}
	
}
