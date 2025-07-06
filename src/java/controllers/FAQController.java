package controllers;

import dao.FaQDAO;
import dto.FaQDTO;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "FAQController", urlPatterns = {"/FAQController"})
public class FAQController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        FaQDAO dao = new FaQDAO();

        try {
            if ("viewFAQ".equals(action) || "ListFAQ".equals(action)) {
                List<FaQDTO> list = dao.getAllFAQs();
                request.setAttribute("FAQ_LIST", list);
                request.getRequestDispatcher("viewFAQ.jsp").forward(request, response);
            }
            // Bạn cần viết thêm phương thức filterByStatus trong FaQDAO nếu cần dùng:
            else if ("searchByStatus".equals(action)) {
                String status = request.getParameter("status");
                // Giả định bạn có hàm filterByStatus(status)
                List<FaQDTO> list = dao.getAllFAQs(); // tạm thời dùng lại
                request.setAttribute("FAQ_LIST", list);
                request.getRequestDispatcher("viewFAQ.jsp").forward(request, response);
            } 
            else if ("edit".equals(action)) {
                String faqID = request.getParameter("faqID");
                // Cần định nghĩa phương thức getFAQByID trong FaQDAO nếu muốn sử dụng
                request.setAttribute("ERROR", "Chức năng sửa chưa được hỗ trợ.");
                request.getRequestDispatcher("viewFAQ.jsp").forward(request, response);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        FaQDAO dao = new FaQDAO();

        try {
            if ("AddFAQ".equals(action)) {
                String faqID = request.getParameter("faqID");
                String question = request.getParameter("question");
                String answer = request.getParameter("answer");

                if (question.length() < 10) {
                    request.setAttribute("ERROR", "Câu hỏi phải dài ít nhất 10 ký tự.");
                    request.getRequestDispatcher("addFAQ.jsp").forward(request, response);
                    return;
                }

                FaQDTO faq = new FaQDTO(faqID, question, "active", answer);
                boolean ok = dao.insertFAQ(faq);

                if (ok) {
                    response.sendRedirect("FAQController?action=ListFAQ");
                } else {
                    request.setAttribute("ERROR", "Thêm FAQ thất bại");
                    request.getRequestDispatcher("addFAQ.jsp").forward(request, response);
                }

            } else if ("update".equals(action)) {
                String faqID = request.getParameter("faqID");
                String question = request.getParameter("question");
                String status = request.getParameter("status");
                String answer = request.getParameter("answer");

                FaQDTO faq = new FaQDTO(faqID, question, status, answer);
                boolean ok = dao.updateFAQ(faq);

                if (ok) {
                    response.sendRedirect("FAQController?action=ListFAQ");
                } else {
                    request.setAttribute("ERROR", "Cập nhật FAQ thất bại");
                    request.getRequestDispatcher("editFAQ.jsp").forward(request, response);
                }

            } else if ("delete".equals(action)) {
                String faqID = request.getParameter("faqID");
                boolean ok = dao.deleteFAQ(faqID);

                if (ok) {
                    response.sendRedirect("FAQController?action=ListFAQ");
                } else {
                    request.setAttribute("ERROR", "Chỉ được xóa khi status = 'inactive'");
                    response.sendRedirect("FAQController?action=ListFAQ");
                }
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    public String getServletInfo() {
        return "FAQ Controller handles FAQ management (CRUD)";
    }
}
