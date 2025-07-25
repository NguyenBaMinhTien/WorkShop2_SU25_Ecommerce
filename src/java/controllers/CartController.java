/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dao.CartDAO;
import dto.CartDTO;
import dto.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.websocket.Session;
import java.util.List;

/**
 *
 * @author DELL
 */
@WebServlet(name = "CartController", urlPatterns = {"/CartController"})
public class CartController extends HttpServlet {

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        UserDTO user = (UserDTO) request.getSession().getAttribute("LOGIN_USER");

        if ("viewCart".equals(action)) {
            try {

                List<CartDTO> list = CartDAO.getCartByUser(user.getUserID());
                System.out.println(action);

                System.out.println(list.toString());
                request.setAttribute("CART_LIST", list);
                request.getRequestDispatcher("viewCart.jsp").forward(request, response);
            } catch (Exception e) {
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
        UserDTO user = (UserDTO) request.getSession().getAttribute("LOGIN_USER");
        int productID = Integer.parseInt(request.getParameter("productID"));

        boolean success = CartDAO.getOrCreateCartAndInsertProduct(user.getUserID(), productID, 1);

        if (success) {
            response.sendRedirect("CartController?action=viewCart");
        } else {
            request.setAttribute("ERROR", "Thêm sản phẩm vào giỏ hàng thất bại!");
            request.getRequestDispatcher("welcomeBuyer.jsp").forward(request, response);
        }

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
