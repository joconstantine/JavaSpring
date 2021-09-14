package com.photoapp.discovery.api.users.service;

import java.util.UUID;

import com.photoapp.discovery.api.users.shared.UserDto;

public class UserServiceImpl implements UserService {

	@Override
	public UserDto createUser(UserDto userDetails) {
		userDetails.setUserId(UUID.randomUUID().toString());
		
		return null;
	}

}
