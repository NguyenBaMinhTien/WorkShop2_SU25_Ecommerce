/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.CustomerCaresDTO;
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
public class CustomerCaresDAO {

    public static boolean createCustomerCares(CustomerCaresDTO dto) throws Exception {
        Connection cnn = null;
        PreparedStatement preStm = null;

        try {
            cnn = DBUtil.getConnection();
            String sql = "INSERT INTO tblCustomerCares(userID,subject, content,status) VALUES (?,?,?,?)";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, dto.getUserID());
            preStm.setString(2, dto.getSubject());
            preStm.setString(3, dto.getContent());
            preStm.setString(4, "New");
            return preStm.executeUpdate() > 0;
        } catch (Exception e) {
            throw e;
        } finally {
            if (preStm != null) {
                preStm.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }
    }

    public static boolean replyToCustomerCare(int ticketID, String replyMessage, String status) throws Exception {
        Connection cnn = null;
        PreparedStatement preStm = null;

        try {
            cnn = DBUtil.getConnection();
            String sql = "UPDATE tblCustomerCares SET reply = ?, status = ? WHERE ticketID = ?";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, replyMessage);
            preStm.setString(2, status);
            preStm.setInt(3, ticketID);

            return preStm.executeUpdate() > 0;
        } catch (Exception e) {
            throw e;
        } finally {
            if (preStm != null) {
                preStm.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }
    }

    public static List<CustomerCaresDTO> getCustomerCaresList() throws Exception {
        List<CustomerCaresDTO> list = new ArrayList<>();
        Connection cnn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;

        try {
            cnn = DBUtil.getConnection();
            String sql = "SELECT ticketID, userID, subject, content, status, reply FROM tblCustomerCares ORDER BY ticketID DESC";
            preStm = cnn.prepareStatement(sql);
            rs = preStm.executeQuery();

            while (rs.next()) {
                int ticketID = rs.getInt("ticketID");
                String userID = rs.getString("userID");
                String subject = rs.getString("subject");
                String content = rs.getString("content");
                String status = rs.getString("status");
                String reply = rs.getString("reply");

                CustomerCaresDTO dto = new CustomerCaresDTO(ticketID, userID, subject, content, status, reply);
                list.add(dto);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStm != null) {
                preStm.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }

        return list.isEmpty() ? null : list;
    }

}
