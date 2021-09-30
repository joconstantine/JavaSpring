package com.photoapp.discovery.api.users.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.photoapp.discovery.api.users.data.AlbumsServiceClient;
import com.photoapp.discovery.api.users.data.UserEntity;
import com.photoapp.discovery.api.users.data.UserRespository;
import com.photoapp.discovery.api.users.shared.UserDto;
import com.photoapp.discovery.api.users.ui.model.AlbumResponseModel;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRespository userRespository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
//	@Autowired
//	private RestTemplate restTemplate;
	
	@Autowired
	private AlbumsServiceClient albumsServiceClient;
	
	@Autowired
	private Environment environment;

	@Override
	public UserDto createUser(UserDto userDetails) {
		userDetails.setUserId(UUID.randomUUID().toString());
		userDetails.setEncryptedPassword(bCryptPasswordEncoder.encode(userDetails.getPassword()));
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		UserEntity userEntity = modelMapper.map(userDetails, UserEntity.class);
		
		userRespository.save(userEntity);
		
		UserDto returnValue = modelMapper.map(userEntity, UserDto.class);
		
		return returnValue;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = userRespository.findByEmail(username);
		
		if (userEntity == null) throw new UsernameNotFoundException(username);
		
		return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), 
				true, true, true, true, new ArrayList<>());
	}

	@Override
	public UserDto getUserDetailsByEmail(String email) {
		UserEntity userEntity = userRespository.findByEmail(email);
		
		if (userEntity == null) throw new UsernameNotFoundException(email);
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserDto returnValue = modelMapper.map(userEntity, UserDto.class);
		
		return returnValue;
	}

	@Override
	public UserDto getUserByUserId(String userId) {
		UserEntity userEntity = userRespository.findByUserId(userId);
		if (userEntity == null) throw new UsernameNotFoundException(userId);
		
		String albumsUrl = String.format(environment.getProperty("albums.url"), userId);
		
//		ResponseEntity<List<AlbumResponseModel>> albumsListResponse = restTemplate.exchange(albumsUrl, HttpMethod.GET, null, 
//				new ParameterizedTypeReference<List<AlbumResponseModel>>() {});
//		List<AlbumResponseModel> albumsList = albumsListResponse.getBody();
		
		log.info("Before calling albums Microservice");
		List<AlbumResponseModel> albumsList = albumsServiceClient.getAlbums(userId);
		log.info("After calling albums Microservice");
		
		UserDto userDto = new ModelMapper().map(userEntity, UserDto.class);
		userDto.setAlbums(albumsList);
		
		return userDto;
	}

}
