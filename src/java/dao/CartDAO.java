/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.CartDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtil;

/**
 *
 * @author DELL
 */
public class CartDAO {

    public static List<CartDTO> getCartByUser(String userID) {
        List<CartDTO> list = new ArrayList<>();
        String sql = "SELECT c.cartID, c.userID, cd.productID, p.name, cd.quantity, p.price "
                + "FROM tblCarts c "
                + "JOIN tblCartDetails cd ON c.cartID = cd.cartID "
                + "JOIN tblProducts p ON cd.productID = p.productID "
                + "WHERE c.userID = ?";
        try ( Connection con = DBUtil.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, userID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CartDTO dto = new CartDTO(
                        rs.getInt("cartID"),
                        rs.getString("userID"),
                        rs.getInt("productID"),
                        rs.getString("name"),
                        rs.getInt("quantity"),
                        rs.getDouble("price")
                );
                list.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    
    public static int insertCart(String userID) {
        String sql = "INSERT INTO tblCarts(userID, createdDate) VALUES (?, ?)";
        LocalDateTime now = LocalDateTime.now();
        Timestamp createdDate = Timestamp.valueOf(now);
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, userID);
//            ps.setInt(2, cartID);
            ps.setTimestamp(2, createdDate);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1); // trả về cartID mới tạo
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1; // thất bại
    }

    // Thêm sản phẩm vào chi tiết giỏ hàng
    public static boolean insertOrUpdateCartItem(int cartID, int productID, int quantity) {
    try (Connection con = DBUtil.getConnection()) {
        // 1. UPDATE nếu đã có
        String updateSQL = "UPDATE tblCartDetails SET quantity = quantity + ? WHERE cartID = ? AND productID = ?";
        try (PreparedStatement ps = con.prepareStatement(updateSQL)) {
            ps.setInt(1, quantity);
            ps.setInt(2, cartID);
            ps.setInt(3, productID);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                return true; // Đã tồn tại, chỉ cần tăng số lượng
            }
        }

        // 2. Nếu chưa có thì INSERT mới
        String insertSQL = "INSERT INTO tblCartDetails(cartID, productID, quantity) VALUES (?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(insertSQL)) {
            ps.setInt(1, cartID);
            ps.setInt(2, productID);
            ps.setInt(3, quantity);
            return ps.executeUpdate() > 0;
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
    return false;
}


    // Cập nhật số lượng sản phẩm trong giỏ
    public static boolean getOrCreateCartAndInsertProduct(String userID, int productID, int quantity) {
    try (Connection con = DBUtil.getConnection()) {

        // 1. Lấy cartID mới nhất của user
        int cartID = -1;
        String getCartSQL = "SELECT TOP 1 cartID FROM tblCarts WHERE userID = ? ORDER BY createdDate DESC";
        try (PreparedStatement ps = con.prepareStatement(getCartSQL)) {
            ps.setString(1, userID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                cartID = rs.getInt("cartID");
            }
        }

        // 2. Nếu chưa có cart thì tạo mới
        if (cartID == -1) {
            String insertCartSQL = "INSERT INTO tblCarts(userID, createdDate) VALUES (?, ?)";
            try (PreparedStatement ps = con.prepareStatement(insertCartSQL, PreparedStatement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, userID);
                ps.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
                int rows = ps.executeUpdate();
                if (rows > 0) {
                    ResultSet rs = ps.getGeneratedKeys();
                    if (rs.next()) {
                        cartID = rs.getInt(1);
                    }
                }
            }
        }

        if (cartID == -1) {
            System.out.println("❌ Không thể lấy hoặc tạo giỏ hàng.");
            return false;
        }

        // 3. Kiểm tra sản phẩm đã có trong cart chưa
        String checkProductSQL = "SELECT quantity FROM tblCartDetails WHERE cartID = ? AND productID = ?";
        boolean productExists = false;
        try (PreparedStatement ps = con.prepareStatement(checkProductSQL)) {
            ps.setInt(1, cartID);
            ps.setInt(2, productID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                productExists = true;
            }
        }

        // 4. Cập nhật hoặc thêm sản phẩm
        if (productExists) {
            String updateSQL = "UPDATE tblCartDetails SET quantity = quantity + ? WHERE cartID = ? AND productID = ?";
            try (PreparedStatement ps = con.prepareStatement(updateSQL)) {
                ps.setInt(1, quantity);
                ps.setInt(2, cartID);
                ps.setInt(3, productID);
                return ps.executeUpdate() > 0;
            }
        } else {
            String insertSQL = "INSERT INTO tblCartDetails(cartID, productID, quantity) VALUES (?, ?, ?)";
            try (PreparedStatement ps = con.prepareStatement(insertSQL)) {
                ps.setInt(1, cartID);
                ps.setInt(2, productID);
                ps.setInt(3, quantity);
                return ps.executeUpdate() > 0;
            }
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
    return false;
}


    // Xoá sản phẩm khỏi giỏ
    public static boolean deleteCartItem(int cartID, int productID) {
        String sql = "DELETE FROM tblCartDetails WHERE cartID=? AND productID=?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, cartID);
            ps.setInt(2, productID);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Xoá toàn bộ giỏ hàng của người dùng
    public static boolean deleteCart(int cartID) {
        String sql = "DELETE FROM tblCartDetails WHERE cartID=?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, cartID);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public static int getCartIDByUserID(String userID) throws Exception{
        Connection cnn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        try {
            cnn = DBUtil.getConnection();
            String sql = "SELECT distinct cartID from tblCarts where userID = ?";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(0, userID);
            while (rs.next()) {                
                return rs.getInt("cartID");
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (cnn != null) {
                cnn.close();
            }
            if (preStm != null) {
                preStm.close();
            }
        }
        return 0;
    }
    
}
