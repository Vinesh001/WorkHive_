package com.amdocs.whs.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.amdocs.whs.bean.Milestone;
import com.amdocs.whs.interfaces.MilestoneDao;
import com.amdocs.whs.services.DBConnection;

public class MilestoneDaoImpl implements MilestoneDao {

	@Override
	public boolean addMilestone(Milestone milestone) {
		String sql = "INSERT INTO milestones (contract_id, description, due_date, is_completed, is_paid) VALUES (?, ?, ?, ?, ?)";

		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, milestone.getContractId());
			ps.setString(2, milestone.getDescription());
			ps.setString(3, milestone.getDueDate());
			ps.setBoolean(4, milestone.isCompleted());
			ps.setBoolean(5, milestone.isPaid());

			return ps.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Milestone> getMilestonesByContractId(int contractId) {
		List<Milestone> list = new ArrayList<>();
		String sql = "SELECT * FROM milestones WHERE contract_id = ?";

		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, contractId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Milestone m = new Milestone();
				m.setMilestoneId(rs.getInt("milestone_id"));
				m.setContractId(rs.getInt("contract_id"));
				m.setDescription(rs.getString("description"));
				m.setDueDate(rs.getString("due_date"));
				m.setCompleted(rs.getBoolean("is_completed"));
				m.setPaid(rs.getBoolean("is_paid"));
				list.add(m);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean markMilestoneCompleted(int milestoneId) {
		String sql = "UPDATE milestones SET is_completed = TRUE WHERE milestone_id = ?";

		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, milestoneId);
			return ps.executeUpdate() > 0;

		} catch (SQLException e) {
		    System.err.println("SQL Error in MilestoneDaoImpl: " + e.getMessage());
		}
		return false;
	}
	@Override
	public int createMilestoneAndReturnId(Milestone milestone) {
	    String sql = "INSERT INTO milestones (contract_id, description, due_date, is_completed, is_paid) VALUES (?, ?, ?, ?, ?)";
	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

	        ps.setInt(1, milestone.getContractId());
	        ps.setString(2, milestone.getDescription());
	        ps.setString(3, milestone.getDueDate());
	        ps.setBoolean(4, milestone.isCompleted());
	        ps.setBoolean(5, milestone.isPaid());

	        ps.executeUpdate();

	        ResultSet rs = ps.getGeneratedKeys();
	        if (rs.next()) {
	            return rs.getInt(1);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return -1;
	}

}
