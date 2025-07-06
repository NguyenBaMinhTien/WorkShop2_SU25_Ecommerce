<%-- 
    Document   : viewOrder
    Created on : Jun 26, 2025, 7:16:23 AM
    Author     : DELL
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Invoice Page</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Invoice Page</title>
        <link href="https://fonts.googleapis.com/css2?family=Press+Start+2P&display=swap" rel="stylesheet">
        <style>
            body {
                font-family: 'Press Start 2P', cursive;
                background-color: #0b0e11;
                color: #f0b90b;
                padding: 20px;
            }
            h1, h2 {
                font-size: 14px;
                margin-bottom: 15px;
            }
            .action {
                margin: 15px 0;
            }
            .btn-get {
                padding: 6px 12px;
                font-size: 10px;
                background-color: #f0b90b;
                border: none;
                color: #0b0e11;
                font-family: 'Press Start 2P', cursive;
                cursor: pointer;
            }
            .btn-get:hover {
                background-color: #d99a00;
            }
            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
                font-size: 10px;
            }
            th, td {
                border: 1px solid #2f3a45;
                padding: 6px 8px;
                text-align: center;
            }
            th {
                background-color: #1e2329;
                color: #f0b90b;
            }
            tr:nth-child(even) {
                background-color: #131a20;
            }
            tr:hover {
                background-color: #1f2a35;
            }
            input[type="text"] {
                width: 100%;
                background: #0b0e11;
                color: #f0b90b;
                border: 1px solid #2f3a45;
                padding: 4px;
                font-family: monospace;
                font-size: 10px;
            }
            .no-users {
                color: #f08080;
                font-size: 10px;
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
            
            .btn-back{
                margin-top: 15px;
            }
        </style>
    </head>
    <body>
        <c:if test="${empty INVOICE_LIST}">
            <p class="no-users">No matching invoices found!</p>
        </c:if>

        <div class="action">
            <form action="${pageContext.request.contextPath}/InvoiceController" method="GET">
                <button class="btn-get" type="submit" name="action" value="viewInvoices">Get All Invoices</button>
            </form>
        </div>
        <table>
            <thead>
                <tr>
                    <th>No</th>
                    <th>Invoice ID</th>
                    <th>User ID</th>
                    <th>Product Name</th>
                    <th>Product ID</th>
                    <th>Quantity</th>
                    <th>Total Amount</th>
                    <th>Status</th>
                    <th>Created Date</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="invoice" items="${INVOICE_LIST}" varStatus="in">
                    <tr>
                <form action="${pageContext.request.contextPath}/InvoiceController" method="POST">
                    <td>${in.count}</td>
                    <td>
                        <input type="hidden" name="invoiceID" value="${invoice.invoiceID}" />
                        ${invoice.invoiceID}
                    </td>
                    <td><input type="text" name="uID" value="${invoice.userID}" required /></td>
                    <td><input type="text" name="name" value="${invoice.name}" required /></td>
                    <td><input type="text" name="productID" value="${invoice.productID}" required /></td>
                    <td><input type="text" name="quantity" value="${invoice.quantity}" required /></td>
                    <td><input type="text" name="totalAmount" value="${invoice.totalAmount}" required /></td>
                    <td><input type="text" name="status" value="${invoice.status}" required /></td>
                    <td><input type="text" name="createdDate" value="${invoice.createdDate}" required /></td>
                </form>
            </tr>
        </c:forEach>
    </tbody>
</table>
<a class="btn-back" href="ProductController?action=ListProducts">← Back</a>
</body>
</html>
