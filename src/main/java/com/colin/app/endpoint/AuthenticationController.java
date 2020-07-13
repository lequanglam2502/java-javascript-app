package com.colin.app.endpoint;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.colin.app.dto.request.LoginRequest;
import com.colin.app.dto.request.RegisterRequest;
import com.colin.app.dto.response.LoginResponse;
import com.colin.app.dto.response.MessageResponse;
import com.colin.app.service.IAuthenticationService;
import com.colin.app.util.Constants;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(path="/auth", produces = MediaType.APPLICATION_JSON_VALUE )
public class AuthenticationController {

	@Autowired
	IAuthenticationService authenticationService;
	
	/**
	 * call authentication service to authenticate user
	 * return login info if successfully authenticated
	 * 
	 * @param loginRequest
	 * @return ResponseEntity<LoginResponse>
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
		LoginResponse loginResponse = authenticationService.login(loginRequest);
		
		if (Objects.nonNull(loginResponse)) {
			return new ResponseEntity<LoginResponse>(loginResponse, new HttpHeaders(), HttpStatus.ACCEPTED);
		} else {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse(Constants.AUTHENTICATION_LOGIN_FAIL));
		}
	}
	
	/**
	 * call authentication service to authenticate user
	 * return login info if successfully authenticated
	 * 
	 * @param registerInfo
	 * @return
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<MessageResponse> register(@RequestBody RegisterRequest registerRequest) {
		String registerResult = authenticationService.register(registerRequest);
		
		if(registerResult.equals(Constants.AUTHENTICATION_REGISTER_SUCCESSFULLY)) {
			return ResponseEntity.ok(new MessageResponse(Constants.AUTHENTICATION_REGISTER_SUCCESSFULLY));
		} else {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse(registerResult));
		}
	}
}
