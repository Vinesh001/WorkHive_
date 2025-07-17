package com.amdocs.whs.dao;

import java.sql.*;
import java.util.*;
import com.amdocs.whs.bean.ProgressUpdate;
import com.amdocs.whs.interfaces.ProgressUpdateDao;
import com.amdocs.whs.services.DBConnection;

public class ProgressUpdateDaoImpl implements ProgressUpdateDao {

    @Override
    public boolean addProgressUpdate(ProgressUpdate update) {
        String sql = "INSERT INTO progress_updates (contract_id, progress_description) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, update.getContractId());
            ps.setString(2, update.getProgressDescription());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<ProgressUpdate> getUpdatesByContract(int contractId) {
        List<ProgressUpdate> list = new ArrayList<>();
        String sql = "SELECT * FROM progress_updates WHERE contract_id = ? ORDER BY update_date DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, contractId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ProgressUpdate pu = new ProgressUpdate();
                pu.setUpdateId(rs.getInt("update_id"));
                pu.setContractId(rs.getInt("contract_id"));
                pu.setProgressDescription(rs.getString("progress_description"));
                pu.setUpdateDate(rs.getString("update_date"));
                list.add(pu);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
