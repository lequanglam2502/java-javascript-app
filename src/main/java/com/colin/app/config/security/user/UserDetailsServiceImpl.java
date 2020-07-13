package com.colin.app.config.security.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.colin.app.entity.domain.User;
import com.colin.app.entity.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	UserRepository userRepository;

	/**
	 * find username and put to cache by username key
	 */
	@Override
	@Transactional
	@Cacheable(value = "usernameEhCache", key = "#username")
	public UserDetails loadUserByUsername(String username) {
		User user = (User) userRepository.findByUsername(username);

		return UserDetailsImpl.build(user);
	}
}
