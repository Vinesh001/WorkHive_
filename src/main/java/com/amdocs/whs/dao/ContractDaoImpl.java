package com.amdocs.whs.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.amdocs.whs.bean.Contract;
import com.amdocs.whs.interfaces.ContractDao;
import com.amdocs.whs.services.DBConnection;

public class ContractDaoImpl implements ContractDao {

	@Override
	public boolean createContract(Contract contract) {
		String sql = "INSERT INTO contracts (project_id, client_id, freelancer_id, start_date, end_date, status) VALUES (?, ?, ?, ?, ?, ?)";

		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, contract.getProjectId());
			ps.setInt(2, contract.getClientId());
			ps.setInt(3, contract.getFreelancerId());
			ps.setString(4, contract.getStartDate());
			ps.setString(5, contract.getEndDate());
			ps.setString(6, contract.getStatus());

			return ps.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Contract> getContractsByClientId(int clientId) {
		List<Contract> contracts = new ArrayList<>();
		String sql = "SELECT * FROM contracts WHERE client_id = ?";

		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, clientId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Contract c = new Contract();
				c.setContractId(rs.getInt("contract_id"));
				c.setProjectId(rs.getInt("project_id"));
				c.setClientId(rs.getInt("client_id"));
				c.setFreelancerId(rs.getInt("freelancer_id"));
				c.setStartDate(rs.getString("start_date"));
				c.setEndDate(rs.getString("end_date"));
				c.setStatus(rs.getString("status"));
				contracts.add(c);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return contracts;
	}

	@Override
	public List<Contract> getContractsByFreelancerId(int freelancerId) {
		List<Contract> contracts = new ArrayList<>();
		String sql = "SELECT * FROM contracts WHERE freelancer_id = ?";

		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, freelancerId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Contract c = new Contract();
				c.setContractId(rs.getInt("contract_id"));
				c.setProjectId(rs.getInt("project_id"));
				c.setClientId(rs.getInt("client_id"));
				c.setFreelancerId(rs.getInt("freelancer_id"));
				c.setStartDate(rs.getString("start_date"));
				c.setEndDate(rs.getString("end_date"));
				c.setStatus(rs.getString("status"));
				contracts.add(c);
			}

		} catch (SQLException e) {
		    System.err.println("SQL Error in ContractDaoImpl: " + e.getMessage());
		}
		return contracts;
	}
	
	@Override
	public boolean updateContractStatus(int contractId, String status) {
	    String sql = "UPDATE contracts SET status = ? WHERE contract_id = ?";
	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setString(1, status);
	        ps.setInt(2, contractId);
	        return ps.executeUpdate() > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}
	
	@Override
	public Contract getContractById(int contractId) {
	    String sql = "SELECT * FROM contracts WHERE contract_id = ?";
	    try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setInt(1, contractId);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            Contract contract = new Contract();
	            contract.setContractId(rs.getInt("contract_id"));
	            contract.setClientId(rs.getInt("client_id"));
	            contract.setFreelancerId(rs.getInt("freelancer_id"));
	            contract.setProjectId(rs.getInt("project_id"));
	            contract.setStatus(rs.getString("status"));
	            contract.setStartDate(rs.getString("start_date"));
	            contract.setEndDate(rs.getString("end_date"));
	            return contract;
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
}
