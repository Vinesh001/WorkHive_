package com.amdocs.whs.dao;

import java.sql.*;
import com.amdocs.whs.bean.User;
import com.amdocs.whs.interfaces.UserDao;
import com.amdocs.whs.services.DBConnection;

public class UserDaoImpl implements UserDao {

	@Override
	public boolean registerUser(User user) {
		String sql = "INSERT INTO users (username, password, email, role, bio, skills, resume_link) VALUES (?, ?, ?, ?, ?, ?, ?)";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getRole());
			ps.setString(5, user.getBio());
			ps.setString(6, user.getSkills());
			ps.setString(7, user.getResumeLink());

			return ps.executeUpdate() > 0;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public User loginUser(String username, String password) {
		String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				User user = new User();
				user.setUserId(rs.getInt("user_id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setRole(rs.getString("role"));
				user.setBio(rs.getString("bio"));
				user.setSkills(rs.getString("skills"));
				user.setResumeLink(rs.getString("resume_link"));
				return user;
			}

		} catch (SQLException e) {
		    System.err.println("SQL Error in UserDaoImpl: " + e.getMessage());
		}

		return null;
	}
}
