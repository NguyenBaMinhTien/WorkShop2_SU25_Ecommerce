package controllers;

import dao.WarrantyDAO;
import dto.WarrantyDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.*;

@WebServlet(name = "WarrantyController", urlPatterns = {"/WarrantyController"})
public class WarrantyController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("adminList".equals(action)) {
            List<WarrantyDTO> list = WarrantyDAO.getAll();
            request.setAttribute("WARRANTY_LIST", list);
            request.getRequestDispatcher("warrantyList.jsp").forward(request, response);

        } else if ("showForm".equals(action)) {
            request.getRequestDispatcher("warrantyForm.jsp").forward(request, response);

        } else if ("extendForm".equals(action)) {
            request.setAttribute("warrantyID", request.getParameter("warrantyID"));
            request.getRequestDispatcher("warrantyExtend.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");  // đảm bảo không lỗi tiếng Việt
        String action = request.getParameter("action");

        if ("create".equals(action)) {
            int productID = Integer.parseInt(request.getParameter("productID"));
            int durationDays = Integer.parseInt(request.getParameter("durationDays"));
            String terms = request.getParameter("terms");

            Date expiry = new Date(System.currentTimeMillis() + (long) durationDays * 24 * 60 * 60 * 1000);

            WarrantyDTO dto = new WarrantyDTO(
                0,
                productID,
                durationDays,
                terms,
                "active",
                expiry
            );

            boolean created = WarrantyDAO.create(dto);
            response.sendRedirect("WarrantyController?action=adminList");

        } else if ("updateStatus".equals(action)) {
            int id = Integer.parseInt(request.getParameter("warrantyID"));
            String status = request.getParameter("status");

            WarrantyDAO.updateStatus(id, status);
            response.sendRedirect("WarrantyController?action=adminList");

        } else if ("extend".equals(action)) {
            int id = Integer.parseInt(request.getParameter("warrantyID"));
            int extendDays = Integer.parseInt(request.getParameter("extendDays"));

            WarrantyDAO.extend(id, extendDays);
            response.sendRedirect("WarrantyController?action=adminList");

        } else if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("warrantyID"));

            boolean deleted = WarrantyDAO.delete(id);
            if (deleted) {
                request.setAttribute("MESSAGE", "🗑️ Đã xóa bảo hành thành công!");
            } else {
                request.setAttribute("MESSAGE", "❌ Không thể xóa! Chỉ xóa được bảo hành có trạng thái expired.");
            }

            List<WarrantyDTO> list = WarrantyDAO.getAll();
            request.setAttribute("WARRANTY_LIST", list);
            request.getRequestDispatcher("warrantyList.jsp").forward(request, response);
        }
    }
}
