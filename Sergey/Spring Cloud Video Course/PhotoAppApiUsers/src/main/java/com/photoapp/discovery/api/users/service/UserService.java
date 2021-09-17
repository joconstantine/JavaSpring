package com.photoapp.discovery.api.users.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.photoapp.discovery.api.users.shared.UserDto;

public interface UserService extends UserDetailsService {
	public UserDto createUser(UserDto userDetails);
	public UserDto getUserDetailsByEmail(String email);
}
