package com.colin.app.service;

import com.colin.app.dto.request.LoginRequest;
import com.colin.app.dto.request.RegisterRequest;
import com.colin.app.dto.response.LoginResponse;

public interface IAuthenticationService {
	public LoginResponse login(LoginRequest loginRequest);
	
	public String register(RegisterRequest registerRequest);

}
