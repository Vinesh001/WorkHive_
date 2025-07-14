package com.amdocs.whs.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.amdocs.whs.bean.Payment;
import com.amdocs.whs.interfaces.PaymentDao;
import com.amdocs.whs.services.DBConnection;

public class PaymentDaoImpl implements PaymentDao {

	@Override
	public boolean releasePayment(Payment payment) {
		String sql = "INSERT INTO payments (milestone_id, amount, payment_date, status) VALUES (?, ?, ?, ?)";

		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, payment.getMilestoneId());
			ps.setDouble(2, payment.getAmount());
			ps.setString(3, payment.getPaymentDate());
			ps.setString(4, payment.getStatus());

			return ps.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Payment> getPaymentsByClient(int clientId) {
		List<Payment> list = new ArrayList<>();
		String sql = "SELECT p.* FROM payments p " + "JOIN milestones m ON p.milestone_id = m.milestone_id "
				+ "JOIN contracts c ON m.contract_id = c.contract_id " + "WHERE c.client_id = ?";

		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, clientId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Payment p = new Payment();
				p.setPaymentId(rs.getInt("payment_id"));
				p.setMilestoneId(rs.getInt("milestone_id"));
				p.setAmount(rs.getDouble("amount"));
				p.setPaymentDate(rs.getString("payment_date"));
				p.setStatus(rs.getString("status"));
				list.add(p);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Payment> getPaymentsByFreelancer(int freelancerId) {
		List<Payment> list = new ArrayList<>();
		String sql = "SELECT p.* FROM payments p " + "JOIN milestones m ON p.milestone_id = m.milestone_id "
				+ "JOIN contracts c ON m.contract_id = c.contract_id " + "WHERE c.freelancer_id = ?";

		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, freelancerId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Payment p = new Payment();
				p.setPaymentId(rs.getInt("payment_id"));
				p.setMilestoneId(rs.getInt("milestone_id"));
				p.setAmount(rs.getDouble("amount"));
				p.setPaymentDate(rs.getString("payment_date"));
				p.setStatus(rs.getString("status"));
				list.add(p);
			}

		} catch (SQLException e) {
		    System.err.println("SQL Error in PaymentDaoImpl: " + e.getMessage());
		}
		return list;
	}
}
