/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.ProductDTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtil;

/**
 *
 * @author DELL
 */
public class ProductDAO {

    public static boolean insert(ProductDTO product) {
        String sql = "INSERT INTO tblProducts(name, categoryID, price, quantity, sellerID, status) VALUES (?, ?, ?, ?, ?, ?)";
        try ( Connection con = DBUtil.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, product.getName());
            ps.setInt(2, product.getCategoryID());
            ps.setDouble(3, product.getPrice());
            ps.setInt(4, product.getQuantity());
            ps.setString(5, product.getSellerID());
            ps.setString(6, product.getStatus());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static List<ProductDTO> getAll() {
        List<ProductDTO> list = new ArrayList<>();
        String sql = "SELECT productID,name,categoryID,price,quantity,sellerID,status FROM tblProducts";
        try ( Connection con = DBUtil.getConnection();  PreparedStatement ps = con.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                ProductDTO p = new ProductDTO(
                        rs.getInt("productID"),
                        rs.getString("name"),
                        rs.getInt("categoryID"),
                        rs.getDouble("price"),
                        rs.getInt("quantity"),
                        rs.getString("sellerID"),
                        rs.getString("status")
                );
                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static boolean addProduct(ProductDTO product) throws Exception {
        Connection cnn = null;
        PreparedStatement preStm = null;

        try {
            cnn = DBUtil.getConnection();
            String sql = "INSERT INTO tblProducts(productID, name, categoryID, price, quantity, sellerID, status) VALUES(?,?,?,?,?,?,?)";
            preStm = cnn.prepareStatement(sql);
            preStm.setInt(1, product.getProductID());
            preStm.setString(2, product.getName());
            preStm.setInt(3, product.getCategoryID());
            preStm.setDouble(4, product.getPrice());
            preStm.setInt(5, product.getQuantity());
            preStm.setString(6, product.getSellerID());
            preStm.setString(7, product.getStatus());

            return preStm.executeUpdate() > 0;
        } finally {
            if (preStm != null) {
                preStm.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }
    }

    public static boolean updateProduct(ProductDTO product) throws Exception {
        Connection cnn = null;
        PreparedStatement preStm = null;

        try {
            cnn = DBUtil.getConnection();
            String sql = "UPDATE tblProducts SET name=?, categoryID=?, price=?, quantity=?, sellerID=?, status=? WHERE productID=?";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, product.getName());
            preStm.setInt(2, product.getCategoryID());
            preStm.setDouble(3, product.getPrice());
            preStm.setInt(4, product.getQuantity());
            preStm.setString(5, product.getSellerID());
            preStm.setString(6, product.getStatus());
            preStm.setInt(7, product.getProductID());

            return preStm.executeUpdate() > 0;
        } finally {
            if (preStm != null) {
                preStm.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }
    }

    public static boolean deleteProduct(int productID) throws Exception {
        Connection cnn = null;
        PreparedStatement preStm = null;

        try {
            cnn = DBUtil.getConnection();
            String sql = "DELETE FROM tblProducts WHERE productID=?";
            preStm = cnn.prepareStatement(sql);
            preStm.setInt(1, productID);

            return preStm.executeUpdate() > 0;
        } finally {
            if (preStm != null) {
                preStm.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }
    }

    public static List<ProductDTO> getProductList() throws Exception {
        Connection cnn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        List<ProductDTO> productList = new ArrayList<>();

        try {
            cnn = DBUtil.getConnection();
            String sql = "SELECT * FROM tblProducts";
            preStm = cnn.prepareStatement(sql);
            rs = preStm.executeQuery();

            while (rs.next()) {
                int productID = rs.getInt("productID");
                String name = rs.getString("name");
                int categoryID = rs.getInt("categoryID");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                String sellerID = rs.getString("sellerID");
                String status = rs.getString("status");
                Date startDate = rs.getDate("startDate");
                Date endDate = rs.getDate("endDate");

                productList.add(new ProductDTO(productID, name, categoryID, price, quantity, sellerID, status, startDate, endDate));
            }

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

        return productList.isEmpty() ? null : productList;
    }

    public static boolean checkProductID(int productID) throws Exception {
        Connection cnn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;

        try {
            cnn = DBUtil.getConnection();
            String sql = "SELECT productID FROM tblProducts WHERE productID=?";
            preStm = cnn.prepareStatement(sql);
            preStm.setInt(1, productID);
            rs = preStm.executeQuery();
            return rs.next();
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
    }

    public static List<ProductDTO> searchProduct(String keyword) throws Exception {
        Connection cnn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        List<ProductDTO> productList = new ArrayList<>();

        try {
            cnn = DBUtil.getConnection();
            System.out.println(keyword);
            String sql = "SELECT * FROM tblProducts WHERE name LIKE ? OR sellerID LIKE ?";
            preStm = cnn.prepareStatement(sql);
            String pattern = "%" + keyword + "%";
            preStm.setString(1, pattern);
            preStm.setString(2, pattern);
            rs = preStm.executeQuery();

            while (rs.next()) {
                int productID = rs.getInt("productID");
                String name = rs.getString("name");
                int categoryID = rs.getInt("categoryID");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                String sellerID = rs.getString("sellerID");
                String status = rs.getString("status");
                Date startDate = rs.getDate("startDate");
                Date endDate = rs.getDate("endDate");

                productList.add(new ProductDTO(productID, name, categoryID, price, quantity, sellerID, status, name, price, startDate, endDate));
            }

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

        return productList.isEmpty() ? null : productList;
    }

    public static List<ProductDTO> getDiscountedProducts() {
        List<ProductDTO> list = new ArrayList<>();
        String sql = "SELECT p.productID, p.name AS productName, p.price, "
                + "pr.promoID, pr.name AS promotionName, pr.discountPercent, "
                + "ROUND(p.price * (1 - pr.discountPercent / 100.0), 0) AS discountedPrice, "
                + "pr.startDate, pr.endDate, pr.status "
                + "FROM tblProductPromotion pp "
                + "JOIN tblProducts p ON pp.productID = p.productID "
                + "JOIN tblPromotions pr ON pp.promoID = pr.promoID "
                + "WHERE pr.status = 'Active'";
        try ( Connection con = DBUtil.getConnection();  PreparedStatement ps = con.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int productID = Integer.parseInt(rs.getString("productID"));
                String productName = rs.getString("productName");
                Double price = Double.parseDouble(rs.getString("price"));
                String promoID = rs.getString("promoID");
                String promotionName = rs.getString("promotionName");
                Double discountPercent = Double.parseDouble(rs.getString("discountPercent"));
                Double discountedPrice = Double.parseDouble(rs.getString("discountedPrice"));
                Date startDate = rs.getDate("startDate");
                Date endDate = rs.getDate("endDate");
                String status = rs.getString("status");
                ProductDTO discountedProduct = new ProductDTO(productID, productName, price, promoID, promotionName, discountPercent, discountedPrice, startDate, endDate, status);
                list.add(discountedProduct);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
