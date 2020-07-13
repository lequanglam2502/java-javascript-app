package com.colin.app.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.colin.app.config.security.jwt.JwtUtils;
import com.colin.app.config.security.user.UserDetailsImpl;
import com.colin.app.dto.request.LoginRequest;
import com.colin.app.dto.request.RegisterRequest;
import com.colin.app.dto.response.LoginResponse;
import com.colin.app.entity.domain.Role;
import com.colin.app.entity.domain.User;
import com.colin.app.entity.repository.RoleRepository;
import com.colin.app.entity.repository.UserRepository;
import com.colin.app.entity.value.ERole;
import com.colin.app.service.IAuthenticationService;
import com.colin.app.util.Constants;

@Service
public class AuthenticationService implements IAuthenticationService {
	
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	PasswordEncoder encoder;
	
	/**
	 * Authenticate user using Spring AuthenticationManager
	 * generate jwt Token
	 * return jwt Token, roles, user name and email
	 * 
	 * @param loginRequest
	 * @return LoginResponse
	 */
	@Override
	public LoginResponse login(LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwtToken = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());
		
		return new LoginResponse(jwtToken, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles);
	}

	@Override
	public String register(RegisterRequest registerRequest) {
		if (userRepository.existsByUsername(registerRequest.getUsername())) {
			return Constants.AUTHENTICATION_REGISTER_DUPPLICATE_USERNAME;
		}
		
		// not register by email currently
		/*if (userRepository.existsByEmail(registerRequest.getEmail())) {
			return null;
		}*/

		// Create new user's account
		User user = new User(registerRequest.getUsername(), registerRequest.getEmail(),
				encoder.encode(registerRequest.getPassword()));

		Set<String> strRoles = registerRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = (Role) roleRepository.findByName(ERole.ROLE_USER);
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = (Role) roleRepository.findByName(ERole.ROLE_ADMIN);
					roles.add(adminRole);

					break;
				case "moderator":
					Role modRole = (Role) roleRepository.findByName(ERole.ROLE_MODERATOR);
					roles.add(modRole);

					break;
				default:
					Role userRole = (Role) roleRepository.findByName(ERole.ROLE_USER);
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);
		
		// if successful put to cache here
		
		return Constants.AUTHENTICATION_REGISTER_SUCCESSFULLY;
	}
}
