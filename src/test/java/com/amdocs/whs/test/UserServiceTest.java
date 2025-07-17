package com.amdocs.whs.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.amdocs.whs.bean.User;
import com.amdocs.whs.services.UserService;

public class UserServiceTest {

	private UserService userService;

	@Before
	public void setup() {
		userService = new UserService(new MockUserDao());
	}

	@Test
	public void testRegisterValidUser() {
		User user = new User();
		user.setUsername("testuser");
		user.setPassword("password123");
		user.setEmail("testuser@example.com");
		user.setRole("Client");

		boolean result = userService.registerUser(user);
		assertTrue(result);
	}

	@Test
	public void testRegisterUserWithMissingFields() {
		User user = new User(); // All fields empty

		boolean result = userService.registerUser(user);
		assertFalse(result);
	}

	@Test
	public void testRegisterUserWithInvalidEmail() {
		User user = new User();
		user.setUsername("invalidemailuser");
		user.setPassword("password123");
		user.setEmail("invalide@mail");
		user.setRole("Client");

		boolean result = userService.registerUser(user);
		assertFalse(result);
	}

	@Test
	public void testLoginWithValidCredentials() {
		User user = new User();
		user.setUsername("freelancer1");
		user.setPassword("securepass");
		user.setEmail("freelancer1@example.com");
		user.setRole("Freelancer");

		userService.registerUser(user);
		User loggedInUser = userService.loginUser("freelancer1", "securepass");

		assertNotNull(loggedInUser);
		assertEquals("freelancer1", loggedInUser.getUsername());
	}

	@Test
	public void testLoginWithInvalidCredentials() {
		User loggedInUser = userService.loginUser("wronguser", "wrongpass");
		assertNull(loggedInUser);
	}
}
