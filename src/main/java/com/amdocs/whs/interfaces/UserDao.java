package com.amdocs.whs.interfaces;

import com.amdocs.whs.bean.User;

public interface UserDao {
	boolean registerUser(User user);

	User loginUser(String username, String password);
}
