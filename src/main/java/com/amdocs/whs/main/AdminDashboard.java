package com.amdocs.whs.main;

import com.amdocs.whs.bean.User;

public class AdminDashboard {
	public static void show(User user) {
		System.out.println("Welcome to Admin Dashboard, " + user.getUsername());
		// TODO: Add admin features here (view users, manage database, etc.)
	}
}
