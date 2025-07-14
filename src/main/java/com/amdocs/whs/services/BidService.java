package com.amdocs.whs.services;

import java.util.List;
import com.amdocs.whs.bean.Bid;
import com.amdocs.whs.dao.BidDaoImpl;

public class BidService {

    private final BidDaoImpl bidDao;

    public BidService() {
        this.bidDao = new BidDaoImpl();
    }

    public boolean placeBid(Bid bid) {
        if (bid.getBidAmount() <= 0 || bid.getProposal() == null) {
            System.out.println("Invalid bid.");
            return false;
        }
        return bidDao.placeBid(bid);
    }

    public List<Bid> getBidsForProject(int projectId) {
        return bidDao.getBidsByProjectId(projectId);
    }

    public List<Bid> getBidsForFreelancer(int freelancerId) {
        return bidDao.getBidsByFreelancerId(freelancerId);
    }
}
