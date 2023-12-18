package com.crop.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.crop.entity.User;
import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {
	Optional<User> findByUserEmail(String userEmail);

	User findByUserName(String username);
	
}
