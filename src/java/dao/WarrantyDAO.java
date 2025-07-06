package dao;

import dto.WarrantyDTO;
import utils.DBUtil;
import java.sql.*;
import java.util.*;

public class WarrantyDAO {
    public static boolean create(WarrantyDTO dto) {
        String sql = "INSERT INTO tblWarranty(productID, durationDays, terms, status, expiryDate) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, dto.getProductID());
            ps.setInt(2, dto.getDurationDays());
            ps.setString(3, dto.getTerms());
            ps.setString(4, dto.getStatus());
            ps.setDate(5, new java.sql.Date(dto.getExpiryDate().getTime()));
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    public static List<WarrantyDTO> getAll() {
        List<WarrantyDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM tblWarranty ORDER BY warrantyID DESC";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                WarrantyDTO dto = new WarrantyDTO(
                    rs.getInt("warrantyID"),
                    rs.getInt("productID"),
                    rs.getInt("durationDays"),
                    rs.getString("terms"),
                    rs.getString("status"),
                    rs.getDate("expiryDate")
                );
                list.add(dto);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public static boolean updateStatus(int warrantyID, String newStatus) {
        String sql = "UPDATE tblWarranty SET status = ? WHERE warrantyID = ?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, newStatus);
            ps.setInt(2, warrantyID);
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    public static boolean extend(int warrantyID, int extendDays) {
        String sql = "UPDATE tblWarranty SET expiryDate = DATEADD(day, ?, expiryDate) WHERE warrantyID = ?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, extendDays);
            ps.setInt(2, warrantyID);
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    public static boolean delete(int warrantyID) {
        String sql = "DELETE FROM tblWarranty WHERE warrantyID = ? AND status = 'expired'";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, warrantyID);
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }
}
