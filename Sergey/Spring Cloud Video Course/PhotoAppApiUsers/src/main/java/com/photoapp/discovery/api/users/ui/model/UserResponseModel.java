package com.photoapp.discovery.api.users.ui.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseModel {

	private String firstName;
	private String lastName;
	private String email;
	private String userId;
	private List<AlbumResponseModel> albums;
	
}
