package com.amdocs.whs.services;

import java.util.Scanner;
import com.amdocs.whs.bean.User;
//import com.amdocs.whs.services.UserService;
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
			    continue; 
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
		user.setUsername(username);


		System.out.print("Enter password: ");
		String password = sc.nextLine().trim();
		if (InputValidator.isEmpty(password)) {
		    System.out.println("Password cannot be empty.");
		    return;
		}
		user.setPassword(password);


		System.out.print("Enter email: ");
		String email = sc.nextLine().trim();
		if (InputValidator.isEmpty(email)) {
		    System.out.println("Email cannot be empty.");
		    return;
		}
		user.setEmail(email);


		System.out.print("Enter role (Client/Freelancer): ");
		String role = sc.nextLine().trim();
		if (InputValidator.isEmpty(role)) {
		    System.out.println("Role cannot be empty.");
		    return;
		}
		user.setRole(role);

		if (!role.equalsIgnoreCase("Client") && !role.equalsIgnoreCase("Freelancer") ) {
		    System.out.println("Invalid role. Please enter Client, Freelancer.");
		    return;
		}

		
		System.out.print("Enter bio: ");
		user.setBio(sc.nextLine());
		
		if(role.equals("Freelancer")) {
			System.out.print("Enter skills: ");
			user.setSkills(sc.nextLine());
		}
		
		if(role.equals("Freelancer")) {
			System.out.print("Enter resume link (optional): ");
			user.setResumeLink(sc.nextLine());
		}

		

		boolean success = userService.registerUser(user);

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

		User user = userService.loginUser(username, password);

		if (user != null) {
			System.out.println("Login successful! Welcome " + user.getUsername());
			loadDashboard(user);
		} else {
			System.out.println("Invalid credentials.");
		}
	}

	
	private static void loadDashboard(User user) {
		String role = user.getRole().toLowerCase();

		int exit = 1;

		while (exit==1) {
			System.out.println("\nWelcome, " + user.getUsername() + " (" + role + ")");
			System.out.println("1. View Profile");
			System.out.println("2. Update Profile");
			System.out.println("3. View Dashboard");
			System.out.println("4. Logout");
			
			System.out.print("Choose an option: ");
			int choice = Integer.parseInt(sc.nextLine());
			
			if (choice == 3) { 
				if (role.equals("freelancer")) {
					choice = 31;
				} else {
					choice = 32;
				}
			}
			

			switch (choice) {
				case 1:
					viewProfile(user.getUsername());
					break;
				case 2:
					user = updateProfile(user);
					break;
				case 31:
					FreelancerDashboard.show(user);
					break;
				case 32:
					ClientDashboard.show(user);
					break;
				case 4:
					System.out.println("Logging out...");
					System.out.println("Logged out successfully.");
					exit = -1;
					break;
				default:
					System.out.println("Invalid option.");
			}
		}
	}
	
	private static void viewProfile(String username) {
		User user = userService.getProfile(username);
		if (user != null) {
			System.out.println("\n--- User Profile ---");
			System.out.println("Username: " + user.getUsername());
			System.out.println("Email: " + user.getEmail());
			System.out.println("Role: " + user.getRole());
			System.out.println("Bio: " + user.getBio());
			System.out.println("Skills: " + user.getSkills());
			System.out.println("Resume Link: " + user.getResumeLink());
		} else {
			System.out.println("User not found.");
		}
	}

	private static User updateProfile(User user) {
		System.out.println("\n--- Update Profile ---");
		System.out.print("Enter new password (leave blank to keep current): ");
		String password = sc.nextLine().trim();
		if (!password.isEmpty()) user.setPassword(password);

		System.out.print("Enter new email (leave blank to keep current): ");
		String email = sc.nextLine().trim();
		if (!email.isEmpty()) user.setEmail(email);

		System.out.print("Enter new bio (leave blank to keep current): ");
		String bio = sc.nextLine().trim();
		if (!bio.isEmpty()) user.setBio(bio);

		if (user.getRole().equalsIgnoreCase("Freelancer")) {
			System.out.print("Enter new skills (leave blank to keep current): ");
			String skills = sc.nextLine().trim();
			if (!skills.isEmpty()) user.setSkills(skills);

			System.out.print("Enter new resume link (leave blank to keep current): ");
			String resume = sc.nextLine().trim();
			if (!resume.isEmpty()) user.setResumeLink(resume);
		}

		boolean success = userService.updateProfile(user);

		if (success) {
			System.out.println("Profile updated successfully.");
		} else {
			System.out.println("Failed to update profile.");
		}

		return user;
	}
}
