package com.colin.app.util;

public class Constants {
	public static final String DATABASE_ID_MUST_BE_UNIQUE = "Id must be unique";

	public static final String AUTHENTICATION_REGISTER_SUCCESSFULLY = "User registered successfully";

	public static final String AUTHENTICATION_REGISTER_FAIL = "User registered fail";

	public static final String AUTHENTICATION_REGISTER_DUPPLICATE_USERNAME = "User name has been registered";

	public static final String AUTHENTICATION_LOGIN_FAIL = "User login fail";

	public static final String ITEM_ADD_FAIL = "Item create fail";

	public static final String ITEM_UPDATE_FAIL = "Item update fail";

	public static final String ITEM_DELETE_FAIL = "Item delete fail";

	public enum Action {
		find, add, update, delete
	}
	
}
