/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dto.ProductDTO;
import dao.ProductDAO;
import dao.PromotionDAO;
import dto.UserDTO;
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
@WebServlet(name = "ProductController", urlPatterns = {"/ProductController"})
public class ProductController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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

        if (user == null || user.getRoleID() == null || !user.getRoleID().equals("AD") && !user.getRoleID().equals("SE") && !user.getRoleID().equals("BU") && !user.getRoleID().equals("MK")) {
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }
        if ("ListProducts".equals(action)) {
            request.setAttribute("PRODUCT_LIST", ProductDAO.getAll());
            request.setAttribute("DISCOUNTED_PRODUCTS", ProductDAO.getDiscountedProducts());
            if (user.getRoleID().equals("BU")) {
                System.out.println(user);
                request.getRequestDispatcher("welcomeBuyer.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("productList.jsp").forward(request, response);
            }

        } else if ("searchProduct".equals(action)) {
            String productName = request.getParameter("search");
            List<ProductDTO> list;
            try {
                list = ProductDAO.searchProduct(productName);
                request.setAttribute("PRODUCT_LIST", list);
                request.getRequestDispatcher("productList.jsp").forward(request, response);
            } catch (Exception e) {
            }
        } else if ("discounted".equals(action)) {
            List<ProductDTO> discounted = ProductDAO.getDiscountedProducts();
            request.setAttribute("DISCOUNTED_PRODUCTS", discounted);
            request.getRequestDispatcher("discountProductList.jsp").forward(request, response);
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
        String action = request.getParameter("action");

        if ("AddProduct".equals(action)) {
            String name = request.getParameter("name");
            int categoryID = Integer.parseInt(request.getParameter("categoryID"));
            double price = Double.parseDouble(request.getParameter("price"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            String sellerID = request.getParameter("sellerID");
            String status = request.getParameter("status");

            ProductDTO product = new ProductDTO(name, categoryID, price, quantity, sellerID, status);
            boolean success = ProductDAO.insert(product);

            if (success) {
                response.sendRedirect("ProductController?action=ListProducts");
            } else {
                request.setAttribute("ERROR", "Create Product Failed!");
                request.getRequestDispatcher("addProduct.jsp").forward(request, response);
            }
        } else if ("update".equals(action)) {
            int productID = Integer.parseInt(request.getParameter("productID"));
            String name = request.getParameter("name");
            int categoryID = Integer.parseInt(request.getParameter("categoryID"));
            double price = Double.parseDouble(request.getParameter("price"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            String sellerID = request.getParameter("sellerID");
            String status = request.getParameter("status");
            ProductDTO product = new ProductDTO(productID, name, categoryID, price, quantity, sellerID, status);
            try {
                boolean success = ProductDAO.updateProduct(product);

                if (success) {
                    response.sendRedirect("ProductController?action=ListProducts");
                } else {
                    request.setAttribute("ERROR", "Update Product Failed!");
                    request.getRequestDispatcher("productList.jsp").forward(request, response);
                }
            } catch (Exception e) {
            }
        } else if ("delete".equals(action)) {
            int productID = Integer.parseInt(request.getParameter("productID"));
            try {
                boolean success = ProductDAO.deleteProduct(productID);

                if (success) {
                    response.sendRedirect("ProductController?action=ListProducts");
                } else {
                    request.setAttribute("ERROR", "Delete Product Failed!");
                    request.getRequestDispatcher("productList.jsp").forward(request, response);
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
