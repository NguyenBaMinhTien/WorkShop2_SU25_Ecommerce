/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.UserDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtil;

/**
 *
 * @author DELL
 */
public class UserDAO {

    public static UserDTO login(String userID, String password) throws Exception {
        Connection cnn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        try {
            cnn = DBUtil.getConnection();
            String sql = "SELECT fullName, roleID, phone from tblUsers where userID=? and password=?";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, userID);
            preStm.setString(2, password);
            rs = preStm.executeQuery();
            if (rs.next()) {
                String fullName = rs.getString("fullName");
                String roleID = rs.getString("roleID");
                String phone = rs.getString("phone");
                return new UserDTO(userID, fullName, roleID, password, phone);
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
        return null;
    }

    public static boolean addUser(UserDTO user) throws Exception {
        PreparedStatement preStm = null;
        Connection cnn = null;
        try {
            cnn = DBUtil.getConnection();
            String sql = "Insert Into tblUsers values(?,?,?,?,?)";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, user.getUserID());
            preStm.setString(2, user.getFullName());
            preStm.setString(3, user.getRoleID());
            preStm.setString(4, user.getPassword());
            preStm.setString(5, user.getPhone());
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

    public static boolean updateUser(UserDTO user) throws Exception {
        PreparedStatement preStm = null;
        Connection cnn = null;
        try {
            cnn = DBUtil.getConnection();
            String sql = "Update tblUsers SET fullName =?, roleID =?, password=?, phone=? where userID = ?";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, user.getFullName());
            preStm.setString(2, user.getRoleID());
            preStm.setString(3, user.getPassword());
            preStm.setString(4, user.getPhone());
            preStm.setString(5, user.getUserID());
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
    
    public static boolean deleteUser(String userID)throws Exception{
        PreparedStatement preStm = null;
        Connection cnn = null;
        try {
            cnn = DBUtil.getConnection();
            String sql = "DELETE From tblUsers where userID = ?";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, userID);
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
    
    public static List<UserDTO> getUserList() throws Exception {
        String userID, fullName, roleID, password, phone;
        Connection cnn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        List<UserDTO> userList = new ArrayList();
        try {
            cnn = DBUtil.getConnection();
            String sql = "SELECT userID, fullName, roleID, password, phone FROM tblUsers";
            preStm = cnn.prepareStatement(sql);
            rs = preStm.executeQuery();

            while (rs.next()) {
                userID = rs.getString("userID");
                fullName = rs.getString("fullName");
                roleID = rs.getString("roleID");
                password = rs.getString("password");
                phone = rs.getString("phone");
                UserDTO user = new UserDTO(userID, fullName, roleID, password, phone);
                userList.add(user);
            }
            System.out.println("userlist " + userList.size());
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
        if (userList.isEmpty()) {
            return null;
        }
        return userList;
    }
    
    public static boolean checkID(String userID) throws Exception {
        PreparedStatement preStm = null;
        Connection cnn = null;
        ResultSet rs = null;

        try {
            cnn = DBUtil.getConnection();
            String sql = "SELECT * from tblUsers where userID = ?";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, userID);
            rs = preStm.executeQuery();
            if (rs.next()) {
                return true;
            }
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
        return false;
    }
    
    public static List<UserDTO> searchUser(String keyword) throws SQLException, ClassNotFoundException, Exception {
        String userID, fullName, roleID, phone;
        List<UserDTO> userList = new ArrayList<>();
        PreparedStatement preStm = null;
        Connection cnn = null;
        ResultSet rs = null;
        try {
            cnn = DBUtil.getConnection();
            String sql = "Select userID,fullName,roleID,phone From tblUsers where userID LIKE ? OR fullName LIKE ? OR roleID LIKE ?";
            preStm = cnn.prepareStatement(sql);
            String pattern = "%" + keyword + "%";
            preStm.setString(1, pattern);
            preStm.setString(2, pattern);
            preStm.setString(3, pattern);
            rs = preStm.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    userID = rs.getString("userID");
                    fullName = rs.getString("fullName");
                    roleID = rs.getString("roleID");
                    phone = rs.getString("phone");
                    UserDTO user = new UserDTO(userID, fullName, roleID, "***", phone);
                    
                    userList.add(user);
                }
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
        if (userList.isEmpty()) {
            return null;
        }
        return userList;
    }
    

}
