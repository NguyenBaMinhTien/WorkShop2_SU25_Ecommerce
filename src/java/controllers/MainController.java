/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author DELL
 */
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

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
        String action = request.getParameter("action");
        String controller = "login.jsp"; // default page

        try {
            if (action == null) {
                controller = "login.jsp";
            } else if (action.equals("Login")) {
                controller = "UserController?action=Login";
            } else if (action.equals("Logout")) {
                controller = "UserController?action=Logout";
            } else if (action.equals("AddUser")) {
                controller = "UserController?action=AddUser";
            } else if (action.equals("ListUsers")) {
                controller = "UserController?action=ListUsers";
            } else if (action.equals("UpdateUser")) {
                controller = "UserController?action=UpdateUser";
            } else if (action.equals("DeleteUser")) {
                controller = "UserController?action=DeleteUser";
            } else if (action.equals("AddProduct")) {
                controller = "ProductController?action=AddProduct";
            } else if (action.equals("ListProducts")) {
                controller = "ProductController?action=ListProducts";
            } else if (action.equals("UpdateProduct")) {
                controller = "ProductController?action=UpdateProduct";
            } else if (action.equals("DeleteProduct")) {
                controller = "ProductController?action=DeleteProduct";
            } else if (action.equals("AddCategory")) {
                controller = "CategoryController?action=AddCategory";
            } else if (action.equals("ListCategories")) {
                controller = "CategoryController?action=ListCategories";
            } else if (action.equals("AddPromotion")) {
                controller = "PromotionController?action=AddPromotion";
            } else if (action.equals("ViewCart")) {
                controller = "CartController?action=ViewCart";
            } else if (action.equals("Checkout")) {
                controller = "InvoiceController?action=Checkout";
            } else if (action.equals("TrackDelivery")) {
                controller = "DeliveryController?action=TrackDelivery";
            } else if (action.equals("SubmitReturn")) {
                controller = "ReturnController?action=SubmitReturn";
            } else if (action.equals("CustomerSupport")) {
                controller = "CustomerCareController?action=CustomerSupport";
            }

            request.getRequestDispatcher(controller).forward(request, response);
        } catch (Exception e) {
            request.setAttribute("ERROR", "Something went wrong: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
