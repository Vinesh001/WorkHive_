package com.amdocs.whs.main;

import java.util.List;
import java.util.Scanner;

import com.amdocs.whs.bean.Bid;
import com.amdocs.whs.bean.Contract;
import com.amdocs.whs.bean.Project;
import com.amdocs.whs.bean.User;
import com.amdocs.whs.services.BidService;
import com.amdocs.whs.services.ContractService;
import com.amdocs.whs.services.ProjectService;

public class FreelancerDashboard {

	private static final Scanner sc = new Scanner(System.in);
	private static final ProjectService projectService = new ProjectService();
	private static final BidService bidService = new BidService();
	private static final ContractService contractService = new ContractService();

	public static void show(User freelancer) {
		boolean exit = false;

		while (!exit) {
			System.out.println("\n--- FREELANCER DASHBOARD ---");
			System.out.println("1. View Available Projects");
			System.out.println("2. Place a Bid on Project");
			System.out.println("3. View My Bids");
			System.out.println("4. View My Contracts");
			System.out.println("5. Logout");

			System.out.print("Choose option: ");
			int choice = Integer.parseInt(sc.nextLine());

			switch (choice) {
			case 1:
				viewAvailableProjects();
				break;
			case 2:
				placeBid(freelancer);
				break;
			case 3:
				viewMyBids(freelancer);
				break;
			case 4:
				viewMyContracts(freelancer);
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

	private static void viewAvailableProjects() {
		List<Project> projects = projectService.getAllProjects();

		if (projects.isEmpty()) {
			System.out.println("No projects available.");
			return;
		}

		System.out.println("\nOpen Projects:");
		for (Project p : projects) {
			if ("Open".equalsIgnoreCase(p.getStatus())) {
				System.out.println("ID: " + p.getProjectId() + " | Title: " + p.getTitle());
				System.out.println("Budget: " + p.getBudget() + " | Deadline: " + p.getDeadline());
				System.out.println("Description: " + p.getDescription());
				System.out.println("-----------------------------------");
			}
		}
	}

	private static void placeBid(User freelancer) {
		int projectId;
		double amount;
		try {
			System.out.print("Enter Project ID to bid on: ");
			projectId = Integer.parseInt(sc.nextLine());

			System.out.print("Enter your bid amount (INR): ");
			amount = Double.parseDouble(sc.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("Invalid input. Please enter valid numbers.");
			return;
		}

		System.out.print("Enter your proposal message: ");
		String proposal = sc.nextLine();

		Bid bid = new Bid();
		bid.setProjectId(projectId);
		bid.setFreelancerId(freelancer.getUserId());
		bid.setBidAmount(amount);
		bid.setProposal(proposal);
		bid.setStatus("Pending");

		boolean success = bidService.placeBid(bid);
		System.out.println(success ? "Bid placed!" : "Failed to place bid.");
	}

	private static void viewMyBids(User freelancer) {
		List<Bid> bids = bidService.getBidsForFreelancer(freelancer.getUserId());

		if (bids.isEmpty()) {
			System.out.println("No bids found.");
			return;
		}

		System.out.println("\nYour Bids:");
		for (Bid b : bids) {
			System.out.println("Bid ID: " + b.getBidId() + " | Project ID: " + b.getProjectId());
			System.out.println("Amount: " + b.getBidAmount() + " | Status: " + b.getStatus());
			System.out.println("Proposal: " + b.getProposal());
			System.out.println("-----------------------------------");
		}
	}

	private static void viewMyContracts(User freelancer) {
		List<Contract> list = contractService.getContractsByFreelancerId(freelancer.getUserId());

		if (list.isEmpty()) {
			System.out.println("No contracts assigned.");
		} else {
			System.out.println("\nYour Contracts:");
			for (Contract c : list) {
				System.out.println("Contract ID: " + c.getContractId() + " | Project ID: " + c.getProjectId()
						+ " | Status: " + c.getStatus());
				System.out.println("Start: " + c.getStartDate() + " | End: " + c.getEndDate());
				System.out.println("-----------------------------------");
			}
		}
	}
}
