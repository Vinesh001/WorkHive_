package com.amdocs.whs.bean;

public class ProgressUpdate {
	private int updateId;
    private int contractId;
    private String progressDescription;
    private String updateDate;
	public ProgressUpdate() {
		super();
	}
	public int getUpdateId() {
		return updateId;
	}
	public void setUpdateId(int updateId) {
		this.updateId = updateId;
	}
	public int getContractId() {
		return contractId;
	}
	public void setContractId(int contractId) {
		this.contractId = contractId;
	}
	public String getProgressDescription() {
		return progressDescription;
	}
	public void setProgressDescription(String progressDescription) {
		this.progressDescription = progressDescription;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public ProgressUpdate(int updateId, int contractId, String progressDescription, String updateDate) {
		super();
		this.updateId = updateId;
		this.contractId = contractId;
		this.progressDescription = progressDescription;
		this.updateDate = updateDate;
	}
    
    
}
