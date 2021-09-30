package com.photoapp.discovery.api.users.shared;

import java.util.List;

import com.photoapp.discovery.api.users.ui.model.AlbumResponseModel;

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
	private String email;
	private String userId;
	private String encryptedPassword;
	private List<AlbumResponseModel> albums;
}
