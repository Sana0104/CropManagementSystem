package com.crop.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.crop.entity.User;
import com.crop.entity.UserLogin;

import com.crop.exceptions.UserNotFoundException;
import com.crop.exceptions.WrongPasswordException;
import com.crop.repository.UserRepository;
import com.crop.service.UserService;
@Service
@Primary
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
    
	boolean flag;
	 
	private Set<String> setOfEmailIds = new HashSet<>();
	
	 private String userNotFound = "User Not Found With email Id - ";
	@Override
	public List<User> fetchAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User fetchUser(String id) throws UserNotFoundException {
	    User userinfo = null;
	    if (id != null && !id.isEmpty()) { // check if id is not null and not an empty string
	        flag = userRepository.existsById(id);
	    }
	    if (flag) {
	        userinfo = userRepository.findById(id).orElse(null); // using orElse to handle null case
	    } else {
	        throw new UserNotFoundException("User Not Found");
	    }
	    return userinfo;
	}

	@Override
	public String addUser(User userInfo) {

		List<User> userList = userRepository.findAll();

		if (null != userList) {
			for (User user : userList) {
				if (user.getUserEmail().equals(userInfo.getUserEmail()))
					flag = true;
				else
					flag = false;
			}
		}

		if (flag) {
			return "User already exists";
		} else {
			userInfo.setUserPassword(passwordEncoder.encode(userInfo.getUserPassword()));
			userRepository.save(userInfo);
			return "User saved";
		}
	}

	@Override
	@Transactional
    public void deleteUserByUsername(String username) {
        User userToDelete = userRepository.findByUserEmail(username)
                .orElseThrow(() -> new UserNotFoundException("User not found with username: " + username));

        
        String authenticatedUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        if (userToDelete.getUserEmail().equals(authenticatedUsername) || SecurityContextHolder.getContext()
                .getAuthentication().getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"))) {
            userRepository.delete(userToDelete);
        } else {
            throw new UserNotFoundException("You are not authorized to delete this user");
        }
    }
	@Override
	public User login(UserLogin user) throws UserNotFoundException, WrongPasswordException {
	    List<User> list = userRepository.findAll();

	    for (User c : list) {
	        String userEmail = c.getUserEmail();
	        if (userEmail != null && userEmail.equals(user.getEmailId())) {
	            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	            if (passwordEncoder.matches(user.getPassword(), c.getUserPassword())) {
	                return c; 
	            } else {
	                throw new WrongPasswordException("Wrong Password Entered...");
	            }
	        }
	    }

	    throw new UserNotFoundException(userNotFound + user.getEmailId());
	}

	@Override

	public User updateUserByEmail(String username, User user) throws UserNotFoundException {

	    Optional<User> userOptional = userRepository.findByUserEmail(username);

	    

	    if (userOptional.isPresent()) {

	        User usr = userOptional.get();

	        usr.setMobileNumber(user.getMobileNumber());;

	        usr.setUserName(user.getUserName());

	        usr.setAddress(user.getAddress());

	        usr.setUserPassword(user.getUserPassword());

	        userRepository.save(usr);

	        return usr;

	    } else {

	        throw new UserNotFoundException("User not found with email: " + user.getUserEmail());

	    }

	}

	 @Override
	    public String deleteUser(String id) {
	        User user = userRepository.findById(id)
	                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));
	        userRepository.delete(user);
	        return "User with ID " + id + " deleted successfully";
	    }


}
