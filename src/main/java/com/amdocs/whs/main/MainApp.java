package com.amdocs.whs.main;

import java.util.Scanner;
import com.amdocs.whs.bean.User;
import com.amdocs.whs.services.UserService;
import com.amdocs.whs.util.InputValidator;

public class MainApp {

	private static Scanner sc = new Scanner(System.in);
	private static UserService userService = new UserService();

	public static void main(String[] args) {
		boolean exit = false;

		System.out.println("===== Welcome to WorkHiveTFS Freelancing System =====");

		while (!exit) {
			System.out.println("\n1. Register");
			System.out.println("2. Login");
			System.out.println("3. Exit");
			System.out.print("Choose an option: ");
			int choice = -1;
			try {
			    choice = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException e) {
			    System.out.println("Invalid input. Please enter a valid number.");
			    continue; // skip rest of loop
			}


			switch (choice) {
			case 1:
				handleRegistration();
				break;
			case 2:
				handleLogin();
				break;
			case 3:
				System.out.println("Thank you for using WorkHiveTFS!");
				exit = true;
				break;
			default:
				System.out.println("Invalid option. Try again.");
			}
		}

		sc.close();
	}

	private static void handleRegistration() {
		User user = new User();

		System.out.print("Enter username: ");
		String username = sc.nextLine().trim();
		if (username.isEmpty()) {
		    System.out.println("Username cannot be empty.");
		    return;
		}


		System.out.print("Enter password: ");
		String password = sc.nextLine().trim();
		if (InputValidator.isEmpty(password)) {
		    System.out.println("Password cannot be empty.");
		    return;
		}


		System.out.print("Enter email: ");
		String email = sc.nextLine().trim();
		if (InputValidator.isEmpty(email)) {
		    System.out.println("Email cannot be empty.");
		    return;
		}


		System.out.print("Enter role (Client/Freelancer/Admin): ");
		String role = sc.nextLine().trim();
		if (InputValidator.isEmpty(role)) {
		    System.out.println("Role cannot be empty.");
		    return;
		}

		if (!role.equalsIgnoreCase("Client") && !role.equalsIgnoreCase("Freelancer") && !role.equalsIgnoreCase("Admin")) {
		    System.out.println("Invalid role. Please enter Client, Freelancer, or Admin.");
		    return;
		}


		System.out.print("Enter bio: ");
		user.setBio(sc.nextLine());

		System.out.print("Enter skills: ");
		user.setSkills(sc.nextLine());

		System.out.print("Enter resume link (optional): ");
		user.setResumeLink(sc.nextLine());

		boolean success = userService.register(user);

		if (success) {
			System.out.println("Registered successfully!");
		} else {
			System.out.println("Registration failed.");
		}
	}

	private static void handleLogin() {
		System.out.print("Enter username: ");
		String username = sc.nextLine();
		if (InputValidator.isEmpty(username)) {
		    System.out.println("Username cannot be empty.");
		    return;
		}


		System.out.print("Enter password: ");
		String password = sc.nextLine();

		User user = userService.login(username, password);

		if (user != null) {
			System.out.println("Login successful! Welcome " + user.getUsername());
			loadDashboard(user);
		} else {
			System.out.println("Invalid credentials.");
		}
	}

	private static void loadDashboard(User user) {
		String role = user.getRole();

		switch (role.toLowerCase()) {
		case "client":
			ClientDashboard.show(user);
			break;
		case "freelancer":
			FreelancerDashboard.show(user);
			break;
		case "admin":
			AdminDashboard.show(user);
			break;
		default:
			System.out.println("Unknown role.");
		}
	}
}
