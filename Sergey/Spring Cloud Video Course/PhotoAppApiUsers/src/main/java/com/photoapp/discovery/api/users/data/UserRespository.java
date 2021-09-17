package com.photoapp.discovery.api.users.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRespository extends CrudRepository<UserEntity, Long>{
	UserEntity findByEmail(String email);
}
