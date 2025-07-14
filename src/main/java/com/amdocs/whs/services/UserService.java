package com.amdocs.whs.services;

import com.amdocs.whs.bean.User;
import com.amdocs.whs.dao.UserDaoImpl;

public class UserService {

    private final UserDaoImpl userDao;

    public UserService() {
        this.userDao = new UserDaoImpl();
    }

    public boolean register(User user) {
        if (user.getUsername() == null || user.getPassword() == null || user.getEmail() == null || user.getRole() == null) {
            System.out.println("Validation failed: missing required fields.");
            return false;
        }
        return userDao.registerUser(user);
    }

    public User login(String username, String password) {
        return userDao.loginUser(username, password);
    }
}
