/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dao.CategoryDAO;
import dto.CategoryDTO;
import dto.UserDTO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author DELL
 */
@WebServlet(name = "CategoryController", urlPatterns = {"/CategoryController"})
public class CategoryController extends HttpServlet {

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

        if (user == null || user.getRoleID() == null || !user.getRoleID().equals("AD")) {
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        if ("ListCategories".equals(action)) {
            try {
                request.setAttribute("CATEGORY_LIST", CategoryDAO.getAllCategories());
                request.getRequestDispatcher("categoryList.jsp").forward(request, response);
            } catch (Exception e) {
            }
        }

        String sr = request.getParameter("search");
        System.out.println(sr);
        List<CategoryDTO> list;
        try {
            // Bắt cả SQLException và ClassNotFoundException
            list = CategoryDAO.searchCategory(sr != null ? sr.trim() : "");
            for (CategoryDTO category : list) {
                System.out.println(category);
            }
        } catch (Exception e) {
            // Gói mọi ngoại lệ thành ServletException để deploy được
            throw new ServletException(e);
        }
        request.setAttribute("CATEGORY_LIST", list);
        request.getRequestDispatcher("/categoryList.jsp").forward(request, response);

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
        if ("AddCategory".equals(action)) {
            String categoryID = request.getParameter("categoryID");
            String categoryName = request.getParameter("categoryName");
            String description = request.getParameter("description");
            CategoryDTO category = new CategoryDTO(categoryID, categoryName, description);
            try {
                boolean ok = CategoryDAO.addCategory(category);
                if (ok) {
                    response.sendRedirect("CategoryController?action=ListCategories");
                } else {
                    request.setAttribute("ERROR", "Thêm category thất bại");
                    request.getRequestDispatcher("addCategory.jsp").forward(request, response);
                }
            } catch (Exception e) {

            }

        } else if ("update".equals(action)) {
            String categoryID = request.getParameter("categoryID");
            String categoryName = request.getParameter("categoryName");
            String description = request.getParameter("description");
            CategoryDTO category = new CategoryDTO(categoryID, categoryName, description);
            try {
                boolean ok = CategoryDAO.updateCategory(category);
                System.out.println(ok);
                if (ok) {
                    response.sendRedirect("CategoryController?action=ListCategories");
                } else {
                    request.setAttribute("ERROR", "Update category failed!");
                    request.getRequestDispatcher("categoryList.jsp").forward(request, response);
                }
            } catch (Exception e) {

            }
        } else if ("delete".equals(action)) {
            String categoryID = request.getParameter("categoryID");
            try {
                boolean ok = CategoryDAO.deleteCategory(categoryID);
                System.out.println(ok);
                if (ok) {
                    response.sendRedirect("CategoryController?action=ListCategories");
                } else {
                    request.setAttribute("ERROR", "Delete category failed!");
                    request.getRequestDispatcher("categoryList.jsp").forward(request, response);
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
