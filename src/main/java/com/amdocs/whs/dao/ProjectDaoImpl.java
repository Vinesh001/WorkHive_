package com.amdocs.whs.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.amdocs.whs.bean.Project;
import com.amdocs.whs.interfaces.ProjectDao;
import com.amdocs.whs.services.DBConnection;

public class ProjectDaoImpl implements ProjectDao {

	@Override
	public boolean postProject(Project project) {
		String sql = "INSERT INTO projects (client_id, title, description, budget, deadline, status) VALUES (?, ?, ?, ?, ?, ?)";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, project.getClientId());
			ps.setString(2, project.getTitle());
			ps.setString(3, project.getDescription());
			ps.setDouble(4, project.getBudget());
			ps.setString(5, project.getDeadline());
			ps.setString(6, project.getStatus());

			return ps.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Project> getAllProjects() {
		List<Project> projects = new ArrayList<>();
		String sql = "SELECT * FROM projects";

		try (Connection conn = DBConnection.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				Project p = new Project();
				p.setProjectId(rs.getInt("project_id"));
				p.setClientId(rs.getInt("client_id"));
				p.setTitle(rs.getString("title"));
				p.setDescription(rs.getString("description"));
				p.setBudget(rs.getDouble("budget"));
				p.setDeadline(rs.getString("deadline"));
				p.setStatus(rs.getString("status"));

				projects.add(p);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return projects;
	}

	@Override
	public List<Project> getProjectsByClientId(int clientId) {
		List<Project> projects = new ArrayList<>();
		String sql = "SELECT * FROM projects WHERE client_id = ?";

		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, clientId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Project p = new Project();
				p.setProjectId(rs.getInt("project_id"));
				p.setClientId(rs.getInt("client_id"));
				p.setTitle(rs.getString("title"));
				p.setDescription(rs.getString("description"));
				p.setBudget(rs.getDouble("budget"));
				p.setDeadline(rs.getString("deadline"));
				p.setStatus(rs.getString("status"));

				projects.add(p);
			}

		}catch (SQLException e) {
		    System.err.println("SQL Error in ProjectDaoImpl: " + e.getMessage());
		}

		return projects;
	}
	
	@Override
	public boolean updateProjectStatus(int projectId, String status) {
		String sql = "UPDATE projects SET status = ? WHERE project_id = ?";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, status);
			ps.setInt(2, projectId);
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
