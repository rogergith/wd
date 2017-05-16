package com.wd.app.controller.rest;

import java.io.Serializable;

public class LoginForm implements Serializable {

	private static final long serialVersionUID = 6077210638523258143L;

	private String username;
	private String password;
	
	public LoginForm(){}
		
	public LoginForm(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "LoginForm [username=" + username + ", password=" + password + "]";
	}
	
}
