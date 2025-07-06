<%-- 
    Document   : categoryList
    Created on : Jun 19, 2025, 12:04:25 PM
    Author     : DELL
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <!-- Retro Font -->
        <link href="https://fonts.googleapis.com/css2?family=Press+Start+2P&display=swap" rel="stylesheet">

        <style>
            body {
                font-family: 'Press Start 2P', cursive;
                background-color: #0b0e11;
                color: #f0b90b;
                padding: 30px;
            }

            h2 {
                font-size: 14px;
                margin-bottom: 20px;
            }

            p.no-categories {
                font-size: 12px;
                color: #f0b90b;
                margin: 10px 0;
            }

            table {
                border-collapse: collapse;
                width: 100%;
                margin-top: 10px;
            }

            th, td {
                border: 1px solid #2f3a45;
                padding: 6px 10px;
                white-space: nowrap;
                font-size: 10px;
            }

            th {
                background-color: #1e2329;
                color: #f0b90b;
            }

            td {
                color: #f0b90b;
                text-align: center;
            }

            tr:nth-child(even) {
                background-color: #131a20;
            }

            tr:hover {
                background-color: #1f2a35;
            }
            
            .actions button {
                background-color: #f0b90b;
                color: #0b0e11;
                border: none;
                padding: 6px 12px;
                font-size: 9px;
                margin: 2px;
                border-radius: 4px;
                cursor: pointer;
                font-family: 'Press Start 2P', monospace;
            }

            .actions button:hover {
                background-color: #ffd131;
            }

            input[type="text"], input[type="hidden"] {
                font-family: 'Press Start 2P', cursive;
                font-size: 10px;
                background: #1e2329;
                color: #f0b90b;
                border: 1px solid #2f3a45;
                padding: 4px 6px;
                border-radius: 4px;
                width: 100%;
            }

            a.btn-back {
                display: inline-block;
                margin-top: 20px;
                font-size: 10px;
                background: #1e2329;
                color: #f0b90b;
                border: 1px solid #2f3a45;
                padding: 6px 12px;
                border-radius: 4px;
                text-decoration: none;
            }

            a.btn-back:hover {
                background-color: #2a323a;
                text-decoration: underline;
            }
            
            .btn-create, .btn-search, .btn-get {
                background-color: #f0b90b;
                color: #0b0e11;
                border: none;
                padding: 12px 20px;
                border-radius: 6px;
                font-size: 10px;
                font-family: 'Press Start 2P', monospace;
                text-decoration: none;
                margin-bottom: 20px;
                display: inline-block;
            }
            
            .btn-create:hover, .btn-search:hover, .btn-get:hover {
                background-color: #ffd131;
            }
            
            .btn-search {
                margin-top: 10px;
            }

            .action{
                display:flex;
                justify-content: space-between;
            }
        </style>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Category Page</title>
    </head>
    <body>
        <h2>CATEGORY LIST</h2>

        <c:if test="${empty CATEGORY_LIST}">
            <p class="no-categories">No matching categories found!</p>
        </c:if>

        <div>
            <form action="${pageContext.request.contextPath}/CategoryController?action=searchCategory" method="GET">
                <input type="text" name="search" placeholder="Search Category"/><br/>
                <button class="btn-search" type="submit" name="action" value="search">Search</button>
            </form>
        </div>

        <div class="action">
            <form action="${pageContext.request.contextPath}/CategoryController?action=ListCategories" method="GET">
                <button class="btn-get" type="submit" name="action" value="searchCategory">Get All Categories</button>
            </form>


            <a class="btn-create" href="addCategory.jsp">+ Create New Category</a>
        </div>

        <table>
            <thead>
                <tr>
                    <th>No</th>
                    <th>Category ID</th>
                    <th>Category Name</th>
                    <th>Description</th>
                    <th>Actions</th>

                </tr>
            </thead>
            <tbody>
                <c:forEach var="category" items="${CATEGORY_LIST}" varStatus="ca">
                    <tr>
                <form action="${pageContext.request.contextPath}/CategoryController" method="POST">
                    <td>${ca.count}</td>
                    <td>
                        <input type="hidden" name="categoryID" value="${category.categoryID}" />
                        ${category.categoryID}
                    </td>
                    <td><input type="text" name="categoryName" value="${category.categoryName}" required /></td>
                    <td><input type="text" name="description" value="${category.description}" required /></td>
                    
                    <td class="actions">
                        <button type="submit" name="action" value="update">Update</button>
                        <button type="submit" name="action" value="delete">Delete</button>
                    </td>
                </form>
            </tr>
        </c:forEach>
    </tbody>
</table>

<br>
<a class="btn-back" href="welcomeAdmin.jsp">← Back</a>
</body>
</html>
