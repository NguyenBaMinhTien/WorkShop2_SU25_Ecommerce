/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dao.DeliveryDAO;
import dto.DeliveryDTO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author DELL
 */
@WebServlet(name = "DeliveryController", urlPatterns = {"/DeliveryController"})
public class DeliveryController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DeliveryController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DeliveryController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("TrackDelivery".equals(action)) {
            try {
                String invoiceIDStr = req.getParameter("invoiceID");
                if (invoiceIDStr == null) {
                    req.setAttribute("ERROR", "Không có mã hóa đơn để tra cứu.");
                    req.getRequestDispatcher("delivery.jsp").forward(req, res);
                    return;
                }

                int invoiceID = Integer.parseInt(invoiceIDStr);
                DeliveryDTO delivery = DeliveryDAO.getByInvoiceID(invoiceID);
                if (delivery == null) {
                    req.setAttribute("ERROR", "Không tìm thấy thông tin giao hàng.");
                } else {
                    req.setAttribute("DELIVERY_INFO", delivery);
                }

                req.getRequestDispatcher("delivery.jsp").forward(req, res);

            } catch (Exception e) {
                e.printStackTrace();
                req.setAttribute("ERROR", "Lỗi khi truy xuất thông tin giao hàng.");
                req.getRequestDispatcher("error.jsp").forward(req, res);
            }
        } else if ("ListDeliveries".equals(action)) {
            try {
                List<DeliveryDTO> list = DeliveryDAO.getAllDeliveries();
                req.setAttribute("DELIVERY_LIST", list);
                req.getRequestDispatcher("welcomeDelivery.jsp").forward(req, res);
            } catch (Exception e) {
                e.printStackTrace();
                req.setAttribute("ERROR", "Lỗi khi lấy danh sách giao hàng.");
                req.getRequestDispatcher("error.jsp").forward(req, res);
            }
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
