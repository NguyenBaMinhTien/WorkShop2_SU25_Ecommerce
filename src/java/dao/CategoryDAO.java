/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.CategoryDTO;
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
public class CategoryDAO {

    public static List<CategoryDTO> getAllCategories() throws Exception {
        String categoryID;
        String categoryName;
        String description;
        ResultSet rs = null;
        PreparedStatement preStm = null;
        Connection cnn = null;
        List<CategoryDTO> categoryList = new ArrayList<>();
        try {
            cnn = DBUtil.getConnection();
            String sql = "SELECT categoryID,categoryName,description FROM tblCategories";
            preStm = cnn.prepareStatement(sql);
            rs = preStm.executeQuery();
            while (rs.next()) {
                categoryID = rs.getString("categoryID");
                categoryName = rs.getString("categoryName");
                description = rs.getString("description");
                CategoryDTO category = new CategoryDTO(categoryID, categoryName, description);
                categoryList.add(category);
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
        if (categoryList.isEmpty()) {
            return null;
        }
        return categoryList;

    }

    public static List<CategoryDTO> searchCategory(String keyword) throws SQLException, ClassNotFoundException, Exception {
        String categoryID, categoryName, description;
        List<CategoryDTO> categoryList = new ArrayList<>();
        PreparedStatement preStm = null;
        Connection cnn = null;
        ResultSet rs = null;
        try {
            cnn = DBUtil.getConnection();
            String sql = "Select categoryID,categoryName,description From tblCategories where categoryName LIKE ?";
            preStm = cnn.prepareStatement(sql);
            String pattern = "%" + keyword + "%";
            preStm.setString(1, pattern);
            rs = preStm.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    categoryID = rs.getString("categoryID");
                    categoryName = rs.getString("categoryName");
                    description = rs.getString("description");
                    CategoryDTO category = new CategoryDTO(categoryID, categoryName, description);

                    categoryList.add(category);
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
        if (categoryList.isEmpty()) {
            return null;
        }
        return categoryList;
    }

    public static boolean addCategory(CategoryDTO category) throws Exception {
        PreparedStatement preStm = null;
        Connection cnn = null;
        try {
            cnn = DBUtil.getConnection();
            String sql = "Insert Into tblCategories values(?,?)";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, category.getCategoryName());
            preStm.setString(2, category.getDescription());
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

    public static boolean updateCategory(CategoryDTO category) throws Exception {
        PreparedStatement preStm = null;
        Connection cnn = null;
        try {
            cnn = DBUtil.getConnection();
            String sql = "Update tblCategories SET categoryName = ?, description = ? where categoryID = ?";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, category.getCategoryName());
            preStm.setString(2, category.getDescription());
            preStm.setString(3, category.getCategoryID());
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

    public static boolean deleteCategory(String categoryID) throws Exception {
        PreparedStatement preStm = null;
        Connection cnn = null;
        try {
            cnn = DBUtil.getConnection();
            String sql = "DELETE From tblCategories where categoryID = ?";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, categoryID);
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

    
}
