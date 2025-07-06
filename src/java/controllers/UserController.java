/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dto.UserDTO;
import dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
@WebServlet(name = "UserController", urlPatterns = {"/UserController"})
public class UserController extends HttpServlet {

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

        if ("ListUsers".equals(action)) {
            try {
                List<UserDTO> list = UserDAO.getUserList();
                request.setAttribute("USER_LIST", list);
                for (UserDTO user : list){
                    System.out.println(user);
                }
                request.getRequestDispatcher("userList.jsp").forward(request, response);
            } catch (Exception e) {
                
            }

        }
        
        String sr = request.getParameter("search");
        System.out.println(sr);
        List<UserDTO> list;
        try {
            // Bắt cả SQLException và ClassNotFoundException
            list = UserDAO.searchUser(sr != null ? sr.trim() : "");
            for (UserDTO user : list){
                System.out.println(user);
            }
        } catch (Exception e) {
            // Gói mọi ngoại lệ thành ServletException để deploy được
            throw new ServletException(e);
        }
        request.setAttribute("USER_LIST", list);
        request.getRequestDispatcher("/userList.jsp").forward(request, response);
    
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

        String action = request.getParameter("action");

        if ("Login".equals(action)) {
            String userID = request.getParameter("userID");
            String password = request.getParameter("password");
            try {
                UserDTO user = UserDAO.login(userID, password);
                if (user != null) {
                    request.getSession().setAttribute("LOGIN_USER", user);
                    if (user.getRoleID().equals("AD")) {
                        response.sendRedirect("welcomeAdmin.jsp");
                    } else if (user.getRoleID().equals("SE")) {
                        response.sendRedirect("welcomeSeller.jsp");
                    } else if (user.getRoleID().equals("MK")) {
                        response.sendRedirect("welcomeMarketing.jsp");
                    } else if (user.getRoleID().equals("BU")) {
                        response.sendRedirect("ProductController?action=ListProducts");
//                          request.getRequestDispatcher("ProductController?action=ListProducts").forward(request, response);
                    } else if (user.getRoleID().equals("CS")) {
                        response.sendRedirect("CustomerCaresController?action=ListCustomerCares");
                    } else if (user.getRoleID().equals("DL")) {
                        response.sendRedirect("DeliveryController?action=ListDeliveries");
                    } else if (user.getRoleID().equals("AC")){
                        response.sendRedirect("welcomeAccountance.jsp");
                    }

                } else {
                    request.setAttribute("ERROR", "Sai tài khoản hoặc mật khẩu.");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            } catch (Exception e) {
                throw new ServletException(e);
            }

        } else if ("AddUser".equals(action)) {
            String userID = request.getParameter("userID");
            String fullName = request.getParameter("fullName");
            String roleID = request.getParameter("roleID");
            String password = request.getParameter("password");
            String phone = request.getParameter("phone");
            UserDTO user = new UserDTO(userID, fullName, roleID, password, phone);
            try {
                boolean ok = UserDAO.addUser(user);
                if (ok) {
                    response.sendRedirect("UserController?action=ListUsers");
                } else {
                    request.setAttribute("ERROR", "Thêm user thất bại");
                    request.getRequestDispatcher("addUser.jsp").forward(request, response);
                }
            } catch (Exception e) {
            }

        } else if ("update".equals(action)) {
            String userID = request.getParameter("userID");
            String fullName = request.getParameter("fullName");
            String roleID = request.getParameter("roleID");
            String password = request.getParameter("password");
            String phone = request.getParameter("phone");
            UserDTO user = new UserDTO(userID, fullName, roleID, password, phone);
            try {
                boolean ok = UserDAO.updateUser(user);
                if (ok) {
                    response.sendRedirect("UserController?action=ListUsers");
                } else {
                    request.setAttribute("ERROR", "Update user failed");
                    request.getRequestDispatcher("userList.jsp").forward(request, response);
                }
            } catch (Exception e) {
            }
        } else if("delete".equals(action)){
            String userID = request.getParameter("userID");
            try {
                boolean ok = UserDAO.deleteUser(userID);
                if (ok) {
                    response.sendRedirect("UserController?action=ListUsers");
                } else {
                    request.setAttribute("ERROR", "Delete user failed");
                    request.getRequestDispatcher("userList.jsp").forward(request, response);
                }
            } catch (Exception e) {
            }
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
