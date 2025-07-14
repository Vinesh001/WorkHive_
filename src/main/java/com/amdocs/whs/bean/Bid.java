package com.amdocs.whs.bean;

public class Bid {
	private int bidId;
	private int projectId;
	private int freelancerId;
	private String proposal;
	private double bidAmount;
	private String status;

	public int getBidId() {
		return bidId;
	}

	public void setBidId(int bidId) {
		this.bidId = bidId;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public int getFreelancerId() {
		return freelancerId;
	}

	public void setFreelancerId(int freelancerId) {
		this.freelancerId = freelancerId;
	}

	public String getProposal() {
		return proposal;
	}

	public void setProposal(String proposal) {
		this.proposal = proposal;
	}

	public double getBidAmount() {
		return bidAmount;
	}

	public void setBidAmount(double bidAmount) {
		this.bidAmount = bidAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	// Getters and Setters

}
