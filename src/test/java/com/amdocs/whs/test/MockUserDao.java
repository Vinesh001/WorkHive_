package com.amdocs.whs.test;

import com.amdocs.whs.bean.User;
import com.amdocs.whs.interfaces.UserDao;

public class MockUserDao implements UserDao {

	private User dummyUser;

	@Override
	public boolean registerUser(User user) {
		this.dummyUser = user;
		return true;
	}

	@Override
	public User loginUser(String username, String password) {
		if (dummyUser != null && dummyUser.getUsername().equals(username) && dummyUser.getPassword().equals(password)) {
			return dummyUser;
		}
		return null;
	}

	@Override
	public User getUserByUsername(String username) {
		if (dummyUser != null && dummyUser.getUsername().equals(username)) {
			return dummyUser;
		}
		return null;
	}

	@Override
	public boolean updateUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}
}