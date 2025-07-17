package com.amdocs.whs.interfaces;

import com.amdocs.whs.bean.Milestone;
import java.util.List;

public interface MilestoneDao {
	boolean addMilestone(Milestone milestone);
	List<Milestone> getMilestonesByContractId(int contractId);
	int createMilestoneAndReturnId(Milestone milestone);
	boolean markMilestoneCompleted(int milestoneId);
}
