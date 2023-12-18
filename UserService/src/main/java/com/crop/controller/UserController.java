package com.crop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.Authentication;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crop.entity.AuthRequest;
import com.crop.entity.User;
import com.crop.entity.UserLogin;
import com.crop.exceptions.UserNotFoundException;
import com.crop.exceptions.WrongPasswordException;
import com.crop.service.UserService;
import com.crop.service.impl.JwtService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
	@Autowired
	private UserService userService;

	@Autowired
	private JwtService jwtService;
	@Autowired
	AuthenticationManager authenticationManager;

	@PostMapping("/new")
	public ResponseEntity<String> addNewUser(@Valid @RequestBody User userInfo) {
		return new ResponseEntity<String>(userService.addUser(userInfo), HttpStatus.CREATED);
	}

	@PostMapping("user_login")
	public ResponseEntity<User> userLogin(@Valid @RequestBody UserLogin user)
	        throws UserNotFoundException, WrongPasswordException {
	    User usr = userService.login(user);
	    // Assuming the role is stored in the User object, include it in the response
	    if(usr != null){
	        return new ResponseEntity<>(usr, HttpStatus.OK);
	    } else {
	        // Handle the case where the user is not found
	        throw new UserNotFoundException("User not found");
	    }
	}


	
	@GetMapping("/fetchAll")
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<List<User>> getAllUsers() {
		return new ResponseEntity<List<User>>(userService.fetchAllUsers(), HttpStatus.CREATED);
	}

	@GetMapping("/fetch/{id}")
	// @PreAuthorize("hasRole('ROLE_ADMIN')")
	public User getUser(@PathVariable String id) throws UserNotFoundException {
		return userService.fetchUser(id);
	}

	@DeleteMapping("/deleteUserByUsername/{username}")
	//@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_FARMER','ROLE_DEALER') or (#username == authentication.name)")
	public ResponseEntity<?> deleteUserByUsername(@PathVariable String username) {
		userService.deleteUserByUsername(username);
		return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
	}
	@PutMapping("update/{userEmail}")
    public ResponseEntity<User> updateUserById(@PathVariable String userEmail, @RequestBody User user) throws UserNotFoundException {

        User usr = userService.updateUserByEmail(userEmail, user);

        return new ResponseEntity<User>(usr,HttpStatus.OK);

    }
	
	@PostMapping("/authenticate")
	public String generateToken(@RequestBody AuthRequest authRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		if (authentication.isAuthenticated()) {
			return jwtService.generateToken(authRequest.getUsername());
		} else {
			throw new UsernameNotFoundException("Invalid User");
		}
	}
	 @GetMapping("/validate")
	    public String validateToken(@RequestParam("token") String token) {
		 jwtService.validateToken(token);
	        return "Token is valid";
	    }
	 
	 @DeleteMapping("deleteuser/{id}")
	    public String deleteUser(@PathVariable String id) {
	        return userService.deleteUser(id);
	    }

}
