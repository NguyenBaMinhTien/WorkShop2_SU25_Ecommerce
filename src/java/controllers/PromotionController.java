package controllers;

import dao.ProductDAO;
import dao.PromotionDAO;
import dto.ProductDTO;
import dto.PromotionDTO;
import dto.UserDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "PromotionController", urlPatterns = {"/PromotionController"})
public class PromotionController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        UserDTO user = (UserDTO) request.getSession().getAttribute("LOGIN_USER");

        if (user == null || user.getRoleID() == null || !user.getRoleID().equals("MK")) {
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        if ("ListPromotions".equals(action)) {
            try {
                List<PromotionDTO> list = PromotionDAO.getAll();
                request.setAttribute("PROMOTION_LIST", list);
                request.getRequestDispatcher("promotionList.jsp").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("ERROR", "Failed to list promotions.");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        } else if ("dashboard".equals(action)) {
            List<ProductDTO> discountedList = ProductDAO.getDiscountedProducts();
//            UserDTO user = (UserDTO) request.getSession().getAttribute("LOGIN_USER");
            if (user != null && "MK".equals(user.getRoleID())) {
                // Vai trò nhân viên marketing: đến trang quản lý khuyến mãi
                request.getRequestDispatcher("promotionList.jsp").forward(request, response);
            } else if (user != null && "BU".equals(user.getRoleID())) {
                // Người dùng thông thường: đến trang chào mừng
                request.getRequestDispatcher("welcomeBuyer.jsp").forward(request, response);
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("AddPromotion".equals(action)) {
            try {
                String name = request.getParameter("name");
                double percent = Double.parseDouble(request.getParameter("percent"));
                String status = request.getParameter("status");
                String startDate = request.getParameter("startDate");
                String endDate = request.getParameter("endDate");

                boolean success = PromotionDAO.insert(name, percent, startDate, endDate, status);

                if (success) {
                    response.sendRedirect("PromotionController?action=ListPromotions");
                } else {
                    request.setAttribute("ERROR", "Create Promotion Failed!");
                    request.getRequestDispatcher("addPromotion.jsp").forward(request, response);
                }
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("ERROR", "Error creating promotion.");
                request.getRequestDispatcher("addPromotion.jsp").forward(request, response);
            }
        } else if ("update".equals(action)) {
            try {
                String id = request.getParameter("promoID");
                String name = request.getParameter("name");
                double percent = Double.parseDouble(request.getParameter("percent"));
                String status = request.getParameter("status");
                String startDate = request.getParameter("startDate");
                String endDate = request.getParameter("endDate");

                PromotionDTO promo = new PromotionDTO(id, name, percent, startDate, endDate, status);
                boolean success = PromotionDAO.update(promo);

                if (success) {
                    response.sendRedirect("PromotionController?action=ListPromotions");
                } else {
                    request.setAttribute("ERROR", "Update Promotion Failed!");
                    request.getRequestDispatcher("promotionList.jsp").forward(request, response);
                }
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("ERROR", "Error updating promotion.");
                request.getRequestDispatcher("promotionList.jsp").forward(request, response);
            }
        } else if ("delete".equals(action)) {
            try {
                String id = request.getParameter("promoID");
                boolean success = PromotionDAO.delete(id);

                if (success) {
                    response.sendRedirect("PromotionController?action=ListPromotions");
                } else {
                    request.setAttribute("ERROR", "Delete Promotion Failed!");
                    request.getRequestDispatcher("promotionList.jsp").forward(request, response);
                }
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("ERROR", "Error deleting promotion.");
                request.getRequestDispatcher("promotionList.jsp").forward(request, response);
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Promotion management controller";
    }
}
