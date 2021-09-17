package com.photoapp.discovery.api.users.ui.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserResponseModel {
	
	private String firstName;
	private String lastName;
	private String email;
	private String userId;

}
