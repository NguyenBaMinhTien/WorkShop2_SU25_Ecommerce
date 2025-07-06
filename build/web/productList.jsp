<%-- 
    Document   : productList
    Created on : Jun 18, 2025, 5:24:32 PM
    Author     : DELL
--%>

<%@ page import="java.util.*, dto.ProductDTO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head><title>Product List</title></head>
    <body>
        <style>
            @import url('https://fonts.googleapis.com/css2?family=Press+Start+2P&display=swap');

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
                background-color: #0b0e11;
            }

            tr:nth-child(even) {
                background-color: #131a20;
            }

            tr:hover {
                background-color: #1f2a35;
            }

            input[type="text"] {
                background-color: #1e2329;
                color: #f0b90b;
                border: 1px solid #2f3a45;
                font-family: 'Press Start 2P', cursive;
                font-size: 10px;
                padding: 4px;
                width: 100%;
            }

            button {
                font-family: 'Press Start 2P', cursive;
                font-size: 10px;
                background-color: #1e2329;
                color: #f0b90b;
                border: 1px solid #2f3a45;
                padding: 4px 6px;
                border-radius: 4px;
                cursor: pointer;
                transition: background-color 0.2s;
            }

            button:hover {
                background-color: #2a3138;
            }

            .actions {
                text-align: center;
            }

            .no-products {
                font-size: 12px;
                color: #f0b90b;
                margin-top: 20px;
            }

            .btn-create, .btn-back, .btn-search, .btn-get {
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

            .btn-create:hover, .btn-back:hover, .btn-search:hover, .btn-get:hover {
                background-color: #ffd131;
            }

            .btn-search {
                margin-top: 10px;
            }

            .btn-back{
                margin-top: 10px;
            }

            .action{
                display:flex;
                justify-content: space-between;
            }

            .btn-create {
                height: fit-content;
            }
        </style>

        <link href="https://fonts.googleapis.com/css2?family=Press+Start+2P&display=swap" rel="stylesheet">

        <h2>Product List</h2>
        <div>
            <form action="${pageContext.request.contextPath}/ProductController?action=searchProduct" method="GET">
                <input type="text" name="search" placeholder="Search Product"/>
                <button class="btn-search" type="submit" name="action" value="searchProduct">Search</button>
            </form>
        </div>


        <div class="action">
            <form action="${pageContext.request.contextPath}/ProductController?action=ListProducts" method="GET">
                <button class="btn-get" type="submit" name="action" value="ListProducts">Get All Products</button>
            </form>
            <c:if test="${sessionScope.LOGIN_USER.roleID == 'SE'}">
                <a class="btn-create" href="addProduct.jsp">+ Create New Product</a>
            </c:if>
            
        </div>
        <table border="1">
            <tr>
                <th>No</th>
                <th>ProductID</th>
                <th>Product Name</th>
                <th>CategoryID</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>SellerID</th>
                <th>Status</th>
                    <c:if test="${sessionScope.LOGIN_USER.roleID == 'SE'}">
                    <th>Actions</th>
                    </c:if>
            </tr>
            <tbody>
                <c:forEach var="product" items="${PRODUCT_LIST}" varStatus="pr">
                    <tr>
                <form action="${pageContext.request.contextPath}/ProductController" method="POST">
                    <td>${pr.count}</td>
                    <td>
                        <input type="hidden" name="productID" value="${product.productID}" />
                        ${product.productID}
                    </td>
                    <td><input type="text" name="name" value="${product.name}" required /></td>
                    <td><input type="text" name="categoryID" value="${product.categoryID}" required /></td>
                    <td><input type="text" name="price" value="${product.price}" required /></td>
                    <td><input type="text" name="quantity" value="${product.quantity}" required/></td>
                    <td><input type="text" name="sellerID" value="${product.sellerID}" required/></td>
                    <td><input type="text" name="status" value="${product.status}" required/></td>
                        <c:if test="${sessionScope.LOGIN_USER.roleID == 'SE'}">

                        <td class="actions">
                            <button type="submit" name="action" value="update">Update</button>
                            <button type="submit" name="action" value="delete">Delete</button>
                        </td>
                    </c:if>
                </form>
            </tr>
        </c:forEach>
    </tbody>
</table>
<c:if test="${empty PRODUCT_LIST}">
    <p class="no-products">No matching products found!</p>
</c:if>
<c:choose>
    <c:when test="${sessionScope.LOGIN_USER.roleID == 'SE'}">
        <a class="btn-back" href="welcomeSeller.jsp">← Back</a>
    </c:when>
    <c:otherwise>
        <a class="btn-back" href="welcomeAdmin.jsp">← Back</a> 
    </c:otherwise>
</c:choose>



</body>
</html>

