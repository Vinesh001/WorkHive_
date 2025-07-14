package com.amdocs.whs.services;

import java.util.List;
import com.amdocs.whs.bean.Milestone;
import com.amdocs.whs.dao.MilestoneDaoImpl;

public class MilestoneService {

    private final MilestoneDaoImpl milestoneDao;

    public MilestoneService() {
        this.milestoneDao = new MilestoneDaoImpl();
    }

    public boolean addMilestone(Milestone m) {
        return milestoneDao.addMilestone(m);
    }

    public List<Milestone> getMilestonesByContract(int contractId) {
        return milestoneDao.getMilestonesByContractId(contractId);
    }

    public boolean completeMilestone(int milestoneId) {
        return milestoneDao.markMilestoneCompleted(milestoneId);
    }
}
