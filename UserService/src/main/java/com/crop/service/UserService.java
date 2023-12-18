package com.crop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.crop.entity.User;
import com.crop.entity.UserLogin;
import com.crop.exceptions.UserNotFoundException;
import com.crop.exceptions.WrongPasswordException;

import jakarta.validation.Valid;

@Service
public interface UserService {
	
	public List<User> fetchAllUsers();
	public User fetchUser(String id) throws UserNotFoundException;
	public String addUser(User userInfo);
	public void deleteUserByUsername(String username);
	public User login(UserLogin user) throws UserNotFoundException , WrongPasswordException;
	public User updateUserByEmail(String userEmail, User user);
	public String deleteUser(String id);
	
}