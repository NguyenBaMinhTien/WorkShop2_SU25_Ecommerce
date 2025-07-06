/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.DeliveryDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtil;

/**
 *
 * @author DELL
 */
public class DeliveryDAO {

    public static DeliveryDTO getByInvoiceID(int invoiceID) {
        String sql = "SELECT deliveryID, invoiceID, address, deliveryDate, status FROM tblDeliveries WHERE invoiceID = ?";
        try ( Connection con = DBUtil.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, invoiceID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new DeliveryDTO(
                        rs.getInt("deliveryID"),
                        rs.getInt("invoiceID"),
                        rs.getString("address"),
                        rs.getString("deliveryDate"),
                        rs.getString("status")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<DeliveryDTO> getAllDeliveries() throws Exception {
        List<DeliveryDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT deliveryID, invoiceID, address, deliveryDate, status FROM tblDeliveries";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                DeliveryDTO dto = new DeliveryDTO(
                        rs.getInt("deliveryID"),
                        rs.getInt("invoiceID"),
                        rs.getString("address"),
                        rs.getString("deliveryDate"),
                        rs.getString("status")
                );
                list.add(dto);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

}
