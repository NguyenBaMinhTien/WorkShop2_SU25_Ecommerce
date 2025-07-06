<%-- 
    Document   : welcomeBuyer
    Created on : Jun 24, 2025, 11:38:02 AM
    Author     : DELL
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome Buyer</title>
        <style>
            @import url('https://fonts.googleapis.com/css2?family=Press+Start+2P&display=swap');

            body {
                font-family: 'Press Start 2P', cursive;
                background-color: #0b0e11;
                color: #f0b90b;
                padding: 30px;
            }

            h1 {
                font-size: 14px;
                margin-bottom: 20px;
            }

            .logout {
                color: black;
                text-decoration: none;
                text-transform: uppercase;
                position: absolute;
                top: 10px;
                right: 10px;
                padding: 10px;
                border-radius: 10px;
                background-color: yellow;
                border: 2px solid #f0b90b;
            }

            .logout:hover {
                text-decoration: underline;
            }

            .action {
                margin-bottom: 10px;
            }

            button {
                font-family: 'Press Start 2P', cursive;
                font-size: 10px;
                background-color: #1e2329;
                color: #f0b90b;
                border: 1px solid #2f3a45;
                padding: 4px 8px;
                border-radius: 4px;
                cursor: pointer;
                transition: background-color 0.2s;
            }

            button:hover {
                background-color: #2a3138;
            }

            table {
                border-collapse: collapse;
                width: 100%;
                margin-top: 20px;
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
                width: 100px;
            }

            .no-products {
                margin-top: 20px;
                font-size: 12px;
                color: #f0b90b;
            }

            .actions {
                text-align: center;
            }
        </style>

    </head>
    <body>

        <h1>WELCOME <c:out value="${sessionScope.LOGIN_USER.fullName}" /></h1>
        <p><a class="logout" href="${pageContext.request.contextPath}/LogoutController">Logout</a></p>
        <div class="action">
            <form action="${pageContext.request.contextPath}/CartController" method="GET">
                <button a class="btn-get"  type="submit" name="action" value="viewCart">View Cart</button>
            </form>
        </div>
        <div class="action">
            <form action="${pageContext.request.contextPath}/FAQController" method="GET">
                <button a class="btn-get"  type="submit" name="action" value="viewFAQ">View FAQ</button>
            </form>
        </div>
        <div class="action">
            <form action="${pageContext.request.contextPath}/InvoiceController" method="GET">
                <button a class="btn-get"  type="submit" name="action" value="viewInvoices">View Invoice</button>
            </form>
        </div>
        <form action="${pageContext.request.contextPath}/ReturnController" method="GET">
            <button type="submit" class="btn-get" name="action" value="showForm">
                Return Request
            </button>
        </form>
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
                <th>Actions</th>
            </tr>
            <tbody>
                <c:forEach var="product" items="${PRODUCT_LIST}" varStatus="pr">
                    <tr>
                <form action="${pageContext.request.contextPath}/CartController?action=addToCart" method="POST">
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
                    <td class="actions">
                        <button type="submit" name="action" value="addToCart">Add To Cart</button>
                    </td>
                </form>
            </tr>
        </c:forEach>
    </tbody>
</table>
<h2>Sản phẩm đang khuyến mãi</h2>

<c:choose>
    <c:when test="${not empty DISCOUNTED_PRODUCTS}">
        <table>
            <tr>
                <th>Tên sản phẩm</th>
                <th>Giá gốc</th>
                <th>Chương trình</th>
                <th>Giảm (%)</th>
                <th>Giá sau giảm</th>
                <th>Thời gian áp dụng</th>
            </tr>
            <c:forEach var="item" items="${DISCOUNTED_PRODUCTS}">
                <tr>
                    <td>${item.name}</td>
                    <td><fmt:formatNumber value="${item.price}" type="currency" currencySymbol="" maxFractionDigits="0"/>₫</td>
                    <td>${item.promotionName}</td>
                    <td>${item.discountPercent}%</td>
                    <td><fmt:formatNumber value="${item.discountedPrice}" type="currency" currencySymbol="" maxFractionDigits="0"/>₫</td>
                    <td>${item.startDate} - ${item.endDate}</td>
                </tr>
            </c:forEach>
        </table>
    </c:when>
    <c:otherwise>
        <p style="text-align:center;">Không có sản phẩm nào đang khuyến mãi.</p>
    </c:otherwise>
</c:choose>
<c:if test="${empty PRODUCT_LIST}">
    <p class="no-products">No matching products found!</p>
</c:if>
</body>
</html>
