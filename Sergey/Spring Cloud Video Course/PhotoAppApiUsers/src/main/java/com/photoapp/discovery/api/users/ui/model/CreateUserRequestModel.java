package com.photoapp.discovery.api.users.ui.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequestModel {
	
	@NotNull(message = "First name cannot be null")
	@Size(min = 2, message = "First name must not be less than 2 characters")
	private String firstName;
	
	
	@NotNull(message = "Last name cannot be null")
	@Size(min = 2, message = "Last name must not be less than 2 characters")
	private String lastName;
	
	
	@NotNull(message = "Password cannot be null")
	@Size(min = 8, max = 16, message = "Password must be greater than 8 characters and less than 16 characters")
	private String password;
	
	@NotNull(message = "Email cannot be null")
	@Email
	private String email;
	
}
