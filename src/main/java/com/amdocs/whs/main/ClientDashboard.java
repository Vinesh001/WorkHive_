package com.amdocs.whs.main;

import java.util.List;
import java.util.Scanner;
import com.amdocs.whs.util.InputValidator;

import com.amdocs.whs.bean.Bid;
import com.amdocs.whs.bean.Contract;
import com.amdocs.whs.bean.Project;
import com.amdocs.whs.bean.User;
import com.amdocs.whs.services.BidService;
import com.amdocs.whs.services.ContractService;
import com.amdocs.whs.services.ProjectService;

public class ClientDashboard {

	private static final Scanner sc = new Scanner(System.in);
	private static final ProjectService projectService = new ProjectService();
	private static final BidService bidService = new BidService();
	private static final ContractService contractService = new ContractService();

	public static void show(User client) {
		boolean exit = false;

		while (!exit) {
			System.out.println("\n--- CLIENT DASHBOARD ---");
			System.out.println("1. Post New Project");
			System.out.println("2. View My Projects");
			System.out.println("3. View Bids on a Project");
			System.out.println("4. Accept a Bid (Create Contract)");
			System.out.println("5. Logout");

			System.out.print("Choose option: ");
			int choice = Integer.parseInt(sc.nextLine());

			switch (choice) {
			case 1:
				postProject(client);
				break;
			case 2:
				viewProjects(client);
				break;
			case 3:
				viewBidsOnProject(client);
				break;
			case 4:
				acceptBid(client);
				break;
			case 5:
				System.out.println("Logging out...");
				exit = true;
				break;
			default:
				System.out.println("Invalid choice.");
			}
		}
	}

	private static void postProject(User client) {
		Project project = new Project();
		project.setClientId(client.getUserId());

		System.out.print("Project Title: ");
		project.setTitle(sc.nextLine());

		System.out.print("Project Description: ");
		project.setDescription(sc.nextLine());

		System.out.print("Budget (in INR): ");
		try {
			System.out.print("Budget (in INR): ");
			project.setBudget(Double.parseDouble(sc.nextLine()));
		} catch (NumberFormatException e) {
			System.out.println("Invalid budget format.");
			return;
		}

		System.out.print("Deadline (YYYY-MM-DD): ");
		String deadline = sc.nextLine();
		if (InputValidator.isEmpty(deadline)) {
			System.out.println("Deadline cannot be empty.");
			return;
		}
		project.setDeadline(deadline);

		project.setStatus("Open");

		boolean success = projectService.postProject(project);
		System.out.println(success ? "Project posted!" : "Failed to post project.");
	}

	private static void viewProjects(User client) {
		List<Project> list = projectService.getClientProjects(client.getUserId());

		if (list.isEmpty()) {
			System.out.println("No projects found.");
		} else {
			System.out.println("\nYour Projects:");
			for (Project p : list) {
				System.out.println(
						"ID: " + p.getProjectId() + " | Title: " + p.getTitle() + " | Status: " + p.getStatus());
			}
		}
	}

	private static void viewBidsOnProject(User client) {
		System.out.print("Enter Project ID: ");
		String pid = sc.nextLine();
		if (!InputValidator.isValidInteger(pid)) {
			System.out.println("Invalid Project ID.");
			return;
		}
		int projectId = Integer.parseInt(pid);

		List<Bid> bids = bidService.getBidsForProject(projectId);

		if (bids.isEmpty()) {
			System.out.println("No bids found.");
		} else {
			System.out.println("\nBids for Project ID " + projectId + ":");
			for (Bid b : bids) {
				System.out.println("Bid ID: " + b.getBidId() + " | Freelancer: " + b.getFreelancerId() + " | Amount: "
						+ b.getBidAmount() + " | Status: " + b.getStatus());
				System.out.println("Proposal: " + b.getProposal());
				System.out.println("-------------------------------------------------");
			}
		}
	}

	private static void acceptBid(User client) {
		int projectId = 0;
		int bidId = 0;
		try {
			System.out.print("Enter Project ID: ");
			projectId = Integer.parseInt(sc.nextLine());

			System.out.print("Enter Bid ID to accept: ");
			bidId = Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("Please enter valid numeric values.");
			return;
		}

		List<Bid> bids = bidService.getBidsForProject(projectId);

		Bid selectedBid = null;
		for (Bid bid : bids) {
			if (bid.getBidId() == bidId) {
				selectedBid = bid;
				break;
			}
		}

		if (selectedBid == null) {
			System.out.println("Bid not found.");
			return;
		}

		Contract contract = new Contract();
		contract.setProjectId(projectId);
		contract.setClientId(client.getUserId());
		contract.setFreelancerId(selectedBid.getFreelancerId());
		contract.setStartDate(java.time.LocalDate.now().toString());
		contract.setEndDate("2025-12-31");
		contract.setStatus("Active");

		boolean success = contractService.createContract(contract);

		if (success) {
			System.out.println("Contract created successfully.");
		} else {
			System.out.println("Failed to create contract.");
		}
	}
}
