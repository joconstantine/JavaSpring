package com.photoapp.discovery.api.users.ui.controllers;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.photoapp.discovery.api.users.service.UserService;
import com.photoapp.discovery.api.users.shared.UserDto;
import com.photoapp.discovery.api.users.ui.model.CreateUserRequestModel;
import com.photoapp.discovery.api.users.ui.model.CreateUserResponseModel;
import com.photoapp.discovery.api.users.ui.model.UserResponseModel;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private Environment env;
	
	@GetMapping("/status/check")
	public String status() {
		return "Working on port " + env.getProperty("local.server.port")
			+ ", with token = " + env.getProperty("token.secret");
	}
	
	@PostMapping(
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
			)
	public ResponseEntity<CreateUserResponseModel> createUser(@Valid  @RequestBody CreateUserRequestModel userDetails) {
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		UserDto userDto = modelMapper.map(userDetails, UserDto.class);
		
		UserDto createdUser = userService.createUser(userDto);
		
		CreateUserResponseModel returnValue = modelMapper.map(createdUser, CreateUserResponseModel.class);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
	}
	
//	@PreAuthorize("principal == #userId")
	@PostAuthorize("principal == returnObject.body.userId")
	@GetMapping(path = "/{userId}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<UserResponseModel> getUser(@PathVariable("userId") String userId) {
		
		UserDto userDto = userService.getUserByUserId(userId);
		UserResponseModel returnValue = new ModelMapper().map(userDto, UserResponseModel.class);
		
		return ResponseEntity.status(HttpStatus.OK).body(returnValue);
	}

}
