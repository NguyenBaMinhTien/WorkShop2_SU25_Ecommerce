<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Your Cart</title>
        <link href="https://fonts.googleapis.com/css2?family=Press+Start+2P&display=swap" rel="stylesheet">
        <style>
            body {
                background-color: #0b0e11;
                color: #f0b90b;
                font-family: 'Press Start 2P', monospace;
                padding: 30px;
            }

            h2 {
                text-align: center;
                font-size: 16px;
                margin-bottom: 20px;
            }

            table {
                width: 100%;
                border-collapse: collapse;
                background-color: #1e2329;
                box-shadow: 0 0 10px #f0b90b55;
                font-size: 10px;
            }

            th {
                background-color: #121417;
                color: #f0b90b;
                padding: 10px;
                border: 1px solid #f0b90b;
                text-transform: uppercase;
            }

            td {
                padding: 10px;
                border: 1px solid #f0b90b;
                text-align: center;
                color: white;
            }

            input[type="text"] {
                width: 90%;
                padding: 5px;
                background-color: #0b0e11;
                color: #f0b90b;
                border: 1px solid #f0b90b;
                border-radius: 4px;
                font-family: 'Press Start 2P', monospace;
                font-size: 10px;
            }

            input[type="submit"], button {
                background-color: #f0b90b;
                color: #0b0e11;
                font-family: 'Press Start 2P', monospace;
                padding: 6px 12px;
                font-size: 10px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
            }

            input[type="submit"]:hover, button:hover {
                background-color: #e0a800;
            }

            .no-categories {
                text-align: center;
                color: red;
                font-size: 10px;
                padding: 10px;
            }

            a {
                display: inline-block;
                margin-top: 20px;
                font-size: 10px;
                color: #f0b90b;
                text-decoration: none;
            }

            a:hover {
                text-decoration: underline;
            }
        </style>
    </head>
    <body>
        <h2>Cart</h2>
        <table border="1">
            <thead>
                <tr>
                    <th>No</th>
                    <th>Cart ID</th>
                    <th>User ID</th>
                    <th>Product ID</th>
                    <th>Name</th>
                    <th>Quantity</th>
                    <th>Price</th>

                </tr>
            </thead>
            <tbody>
                <c:set var="total" value="0" />
                <c:forEach var="cart" items="${CART_LIST}" varStatus="car">
                    <c:set var="total" value="${total + cart.getTotal()}" />
                    <tr>
                <form action="${pageContext.request.contextPath}/CartController" method="POST">
                    <td>${car.count}</td>
                    <td>
                        <input type="hidden" name="cartID" value="${cart.cartID}" />
                        ${cart.cartID}
                    </td>
                    <td><input type="text" name="userID" value="${cart.userID}" required /></td>
                    <td><input type="text" name="productID" value="${cart.productID}" required /></td>
                    <td><input type="text" name="name" value="${cart.productName}" required /></td>
                    <td><input type="text" name="quantity" value="${cart.quantity}" required /></td>
                    <td><input type="text" name="total" value="${cart.getTotal()}" required /></td>

                    <td class="actions">
                        <button type="submit" name="action" value="delete">Delete</button>
                    </td>
                </form>
            </tr>
        </c:forEach>
        <tr>
            <td colspan="6">Grand Total:</td>
            <td>${total}</td>
        </tr>
    </tbody>

    <c:if test="${empty CART_LIST}">
        <p class="no-categories">No matching carts found!</p>
    </c:if>

</table>
<form action="InvoiceController" method="post">
    <input type="hidden" name="action" value="Checkout"/>
    <input type="submit" value="Checkout"/>
</form>
<a href="ProductController?action=ListProducts">← Back</a>            
</body>
</html>
