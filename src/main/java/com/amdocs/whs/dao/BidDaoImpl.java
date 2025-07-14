package com.amdocs.whs.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.amdocs.whs.bean.Bid;
import com.amdocs.whs.interfaces.BidDao;
import com.amdocs.whs.services.DBConnection;

public class BidDaoImpl implements BidDao {

	@Override
	public boolean placeBid(Bid bid) {
		String sql = "INSERT INTO bids (project_id, freelancer_id, proposal, bid_amount, status) VALUES (?, ?, ?, ?, ?)";

		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, bid.getProjectId());
			ps.setInt(2, bid.getFreelancerId());
			ps.setString(3, bid.getProposal());
			ps.setDouble(4, bid.getBidAmount());
			ps.setString(5, bid.getStatus());

			return ps.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Bid> getBidsByProjectId(int projectId) {
		List<Bid> bids = new ArrayList<>();
		String sql = "SELECT * FROM bids WHERE project_id = ?";

		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, projectId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Bid bid = new Bid();
				bid.setBidId(rs.getInt("bid_id"));
				bid.setProjectId(rs.getInt("project_id"));
				bid.setFreelancerId(rs.getInt("freelancer_id"));
				bid.setProposal(rs.getString("proposal"));
				bid.setBidAmount(rs.getDouble("bid_amount"));
				bid.setStatus(rs.getString("status"));
				bids.add(bid);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bids;
	}

	@Override
	public List<Bid> getBidsByFreelancerId(int freelancerId) {
		List<Bid> bids = new ArrayList<>();
		String sql = "SELECT * FROM bids WHERE freelancer_id = ?";

		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, freelancerId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Bid bid = new Bid();
				bid.setBidId(rs.getInt("bid_id"));
				bid.setProjectId(rs.getInt("project_id"));
				bid.setFreelancerId(rs.getInt("freelancer_id"));
				bid.setProposal(rs.getString("proposal"));
				bid.setBidAmount(rs.getDouble("bid_amount"));
				bid.setStatus(rs.getString("status"));
				bids.add(bid);
			}

		} catch (SQLException e) {
		    System.err.println("SQL Error in BidDaoImpl: " + e.getMessage());
		}
		return bids;
	}
}
