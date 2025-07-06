/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.PromotionDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtil;

public class PromotionDAO {

    public static boolean insert(String name, double percent, String start, String end, String status) {
        String sql = "INSERT INTO tblPromotions(name, discountPercent, startDate, endDate, status) VALUES (?, ?, ?, ?, ?)";
        try ( Connection con = DBUtil.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setDouble(2, percent);
            ps.setString(3, start);
            ps.setString(4, end);
            ps.setString(5, status);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean update(PromotionDTO promo) {
        String sql = "UPDATE tblPromotions SET name=?, discountPercent=?, startDate=?, endDate=?, status=? WHERE promoID=?";
        try ( Connection con = DBUtil.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, promo.getName());
            ps.setDouble(2, promo.getDiscountPercent());
            ps.setString(3, promo.getStartDate());
            ps.setString(4, promo.getEndDate());
            ps.setString(5, promo.getStatus());
            ps.setString(6, promo.getPromoID());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean delete(String promoID) {
        String sql = "DELETE FROM tblPromotions WHERE promoID=?";
        try ( Connection con = DBUtil.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, promoID);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static PromotionDTO getByID(String promoID) {
        String sql = "SELECT * FROM tblPromotions WHERE promoID=?";
        try ( Connection con = DBUtil.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, promoID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new PromotionDTO(
                        rs.getString("promoID"),
                        rs.getString("name"),
                        rs.getDouble("discountPercent"),
                        rs.getString("startDate"),
                        rs.getString("endDate"),
                        rs.getString("status")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<PromotionDTO> getAll() {
        List<PromotionDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM tblPromotions";
        try ( Connection con = DBUtil.getConnection();  PreparedStatement ps = con.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                PromotionDTO promo = new PromotionDTO(
                        rs.getString("promoID"),
                        rs.getString("name"),
                        rs.getDouble("discountPercent"),
                        rs.getString("startDate"),
                        rs.getString("endDate"),
                        rs.getString("status")
                );
                list.add(promo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static boolean checkPromoID(String promoID) {
        String sql = "SELECT promoID FROM tblPromotions WHERE promoID = ?";
        try ( Connection con = DBUtil.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, promoID);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static List<PromotionDTO> search(String keyword) {
        List<PromotionDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM tblPromotions WHERE promoID LIKE ? OR name LIKE ?";
        try ( Connection con = DBUtil.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            String pattern = "%" + keyword + "%";
            ps.setString(1, pattern);
            ps.setString(2, pattern);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PromotionDTO promo = new PromotionDTO(
                        rs.getString("promoID"),
                        rs.getString("name"),
                        rs.getDouble("discountPercent"),
                        rs.getString("startDate"),
                        rs.getString("endDate"),
                        rs.getString("status")
                );
                list.add(promo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static boolean attachProductToPromo(String promoID, String productID) {
        String sql = "INSERT INTO tblPromotionProducts(promoID, productID) VALUES (?, ?)";
        try ( Connection con = DBUtil.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, promoID);
            ps.setString(2, productID);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    

}
