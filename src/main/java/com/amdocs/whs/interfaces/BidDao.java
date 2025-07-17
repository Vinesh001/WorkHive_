package com.amdocs.whs.interfaces;

import com.amdocs.whs.bean.Bid;
import java.util.List;

public interface BidDao {
	boolean placeBid(Bid bid);
	boolean updateBidStatus(Bid bid);
	List<Bid> getBidsByProjectId(int projectId);
	List<Bid> getBidsByFreelancerId(int freelancerId);
	
}
