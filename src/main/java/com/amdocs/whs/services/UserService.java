package com.amdocs.whs.services;

import com.amdocs.whs.bean.User;
import com.amdocs.whs.dao.UserDaoImpl;
import com.amdocs.whs.interfaces.UserDao;

public class UserService {

//    private final UserDaoImpl userDao;
	private final UserDao userDao;

    public UserService() {
        this.userDao = new UserDaoImpl();
    }
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean registerUser(User user) {
    	System.out.println(user.getUsername());
        if (user.getUsername() == null || user.getPassword() == null || user.getEmail() == null || !user.getEmail().matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$") || user.getRole() == null) {
            System.out.println("Validation failed: missing required fields.");
            return false;
        }
        return userDao.registerUser(user);
    }

    public User loginUser(String username, String password) {
        return userDao.loginUser(username, password);
    }
    
    public User getProfile(String username) {
    	return userDao.getUserByUsername(username);
    }

    public boolean updateProfile(User user) {
    	return userDao.updateUser(user);
    }

}
