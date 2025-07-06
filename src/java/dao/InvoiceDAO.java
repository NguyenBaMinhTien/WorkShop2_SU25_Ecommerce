/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.CartDTO;
import dto.InvoiceDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtil;

/**
 *
 * @author DELL
 */
public class InvoiceDAO {

    public static boolean checkout(String userID, List<CartDTO> cartItems) {
        Connection con = null;
        PreparedStatement ps1 = null, ps2 = null, ps3 = null;
        ResultSet rs = null;
        try {
            con = DBUtil.getConnection();
            con.setAutoCommit(false);

            double total = 0;
            for (CartDTO c : cartItems) {
                total += c.getTotal();
            }

            String sql1 = "INSERT INTO tblInvoices(userID, totalAmount, status, createdDate) VALUES (?, ?, 'Pending', GETDATE())";
            ps1 = con.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
            ps1.setString(1, userID);
            ps1.setDouble(2, total);
            ps1.executeUpdate();
            rs = ps1.getGeneratedKeys();
            int invoiceID = 0;
            if (rs.next()) {
                invoiceID = rs.getInt(1);
            }

            String sql2 = "INSERT INTO tblInvoiceDetails(invoiceID, productID, quantity, price) VALUES (?, ?, ?, ?)";
            ps2 = con.prepareStatement(sql2);
            for (CartDTO item : cartItems) {
                ps2.setInt(1, invoiceID);
                ps2.setInt(2, item.getProductID());
                ps2.setInt(3, item.getQuantity());
                ps2.setDouble(4, item.getPrice());
                ps2.addBatch();
            }
            ps2.executeBatch();

            con.commit();
            return true;
        } catch (Exception e) {
            try {
                if (con != null) {
                    con.rollback();
                }
            } catch (Exception ex) {
            }
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.setAutoCommit(true);
                }
            } catch (Exception ex) {
            }
        }
        return false;
    }

    public static List<InvoiceDTO> getInvoiceList(String userID) throws Exception {
        List<InvoiceDTO> invoiceList = new ArrayList<>();
        Connection cnn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;

        try {
            cnn = DBUtil.getConnection();
            String sql = "SELECT inv.invoiceID, inv.userID, pr.name, ind.productID, ind.quantity, " +
                         "inv.totalAmount, inv.status, inv.createdDate " +
                         "FROM tblInvoices inv " +
                         "JOIN tblInvoiceDetails ind ON inv.invoiceID = ind.invoiceID " +
                         "JOIN tblProducts pr ON ind.productID = pr.productID " +
                         "WHERE inv.userID = ?";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, userID);
            rs = preStm.executeQuery();
            System.out.println(rs.next() + userID);
            while (rs.next()) {
                int invoiceID = rs.getInt("invoiceID");
                String uID = rs.getString("userID");
                String name = rs.getString("name");
                String productID = rs.getString("productID");
                String quantity = rs.getString("quantity");
                double totalAmount = rs.getDouble("totalAmount");
                String status = rs.getString("status");
                String createdDate = rs.getString("createdDate");

                InvoiceDTO invoice = new InvoiceDTO(invoiceID, uID, name, productID, quantity, totalAmount, status, createdDate);
                invoiceList.add(invoice);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null) rs.close();
            if (preStm != null) preStm.close();
            if (cnn != null) cnn.close();
        }

        if (invoiceList.isEmpty()) return null;
        return invoiceList;
    }
}
