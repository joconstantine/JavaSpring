package com.photoapp.discovery.api.users.shared;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	
	private String firstName;
	private String lastName;
	private String password;
	private String emailString;
	private String userId;
	private String encryptedPassword;
}
