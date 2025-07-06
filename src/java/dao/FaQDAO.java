package dao;

import dto.FaQDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtil;

public class FaQDAO {

    public List<FaQDTO> getAllFAQs() throws SQLException, Exception {
        List<FaQDTO> list = new ArrayList<>();
        String sql = "SELECT faqID, question, status, answer FROM tblFAQ";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                FaQDTO faq = new FaQDTO(
                    rs.getString("faqID"),
                    rs.getString("question"),
                    rs.getString("status"),
                    rs.getString("answer")
                );
                list.add(faq);
            }
        }

        return list;
    }

    public List<FaQDTO> searchFAQs(String keyword) throws SQLException, Exception {
        List<FaQDTO> list = new ArrayList<>();
        String sql = "SELECT faqID, question, status, answer FROM tblFAQ WHERE question LIKE ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + keyword + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    FaQDTO faq = new FaQDTO(
                        rs.getString("faqID"),
                        rs.getString("question"),
                        rs.getString("status"),
                        rs.getString("answer")
                    );
                    list.add(faq);
                }
            }
        }

        return list;
    }

    public boolean insertFAQ(FaQDTO faq) throws SQLException, Exception {
        if (faq.getQuestion().length() < 10) return false;
        String sql = "INSERT INTO tblFAQ (faqID, question, status, answer) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, faq.getFaqID());
            ps.setString(2, faq.getQuestion());
            ps.setString(3, faq.getStatus());
            ps.setString(4, faq.getAnswer());

            return ps.executeUpdate() > 0;
        }
    }

    public boolean updateFAQ(FaQDTO faq) throws SQLException, Exception {
        String sql = "UPDATE tblFAQ SET question = ?, status = ?, answer = ? WHERE faqID = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, faq.getQuestion());
            ps.setString(2, faq.getStatus());
            ps.setString(3, faq.getAnswer());
            ps.setString(4, faq.getFaqID());

            return ps.executeUpdate() > 0;
        }
    }

    public boolean deleteFAQ(String faqID) throws SQLException, Exception {
        String sql = "DELETE FROM tblFAQ WHERE faqID = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, faqID);
            return ps.executeUpdate() > 0;
        }
    }
}
