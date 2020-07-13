package com.colin.app.entity.security;

public class UserContext {
	private String userName;

	private String userPass;

	private String dataSourceLookupKey;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPass() {
		return userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	public String getDataSourceLookupKey() {
		return dataSourceLookupKey;
	}

	public void setDataSourceLookupKey(String dataSourceLookupKey) {
		this.dataSourceLookupKey = dataSourceLookupKey;
	}
	
}
