package com.crop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.crop.controller.UserController;
import com.crop.entity.Address;
import com.crop.entity.User;
import com.crop.entity.UserLogin;
import com.crop.exceptions.UserNotFoundException;
import com.crop.exceptions.WrongPasswordException;
import com.crop.repository.UserRepository;
import com.crop.service.UserService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserTest {

	@InjectMocks
	private UserController userController;

	@Mock
	private UserService userService;

	@Mock
	private UserRepository userRepository;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testAddNewUser() {
		User newUser = new User();

		when(userService.addUser(newUser)).thenReturn("User saved");

		ResponseEntity<String> response = userController.addNewUser(newUser);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals("User saved", response.getBody());
	}

	

	@Test
	public void testUserLogin_UserNotFoundException() throws UserNotFoundException, WrongPasswordException {
		UserLogin userLogin = new UserLogin("sana@gmail.com", "sana0104");

		when(userService.login(userLogin)).thenThrow(new UserNotFoundException("User Not Found"));

		try {
			userController.userLogin(userLogin);
		} catch (UserNotFoundException ex) {
			assertEquals("User Not Found", ex.getMessage());
		}

		verify(userService).login(userLogin);
	}

	@Test
	public void testUserLogin_WrongPasswordException() throws UserNotFoundException, WrongPasswordException {
		UserLogin userLogin = new UserLogin("sana@gmail.com", "sana0104");

		when(userService.login(userLogin)).thenThrow(new WrongPasswordException("Wrong Password Entered..."));

		try {
			userController.userLogin(userLogin);
		} catch (WrongPasswordException ex) {
			assertEquals("Wrong Password Entered...", ex.getMessage());
		}

		verify(userService).login(userLogin);
	}

	



	@Test
	public void testGetUser_Success() throws UserNotFoundException {
		String userId = "1";
		User user = new User(); // Create a user object or mock it as needed

		when(userService.fetchUser(userId)).thenReturn(user);

		User response = userController.getUser(userId);

		assertEquals(user, response);
	}

	@Test
	public void testGetUser_UserNotFoundException() throws UserNotFoundException {
		String userId = "2";

		when(userService.fetchUser(userId)).thenThrow(new UserNotFoundException("User Not Found"));

		try {
			userController.getUser(userId);
		} catch (UserNotFoundException ex) {
			assertEquals("User Not Found", ex.getMessage());
		}
	}

	@Test
	public void testFetchAllUsers_Success() {
		List<User> userList = new ArrayList<>();
		User user1 = new User();
		user1.setId("1");
		user1.setUserName("User1");
		user1.setUserEmail("sana@gmail.com");

		User user2 = new User();
		user2.setId("2");
		user2.setUserName("User2");
		user2.setUserEmail("minnu@gmail.com");

		userList.add(user1);
		userList.add(user2);

		when(userService.fetchAllUsers()).thenReturn(userList);

		ResponseEntity<List<User>> response = userController.getAllUsers();

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(userList, response.getBody());
	}
}
