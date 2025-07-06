<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Delivery List</title>
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
                margin-top: 20px;
                background-color: #1e2329;
                border: 2px solid #f0b90b;
                box-shadow: 0 0 15px #f0b90b55;
            }

            th {
                background-color: #121417;
                color: #f0b90b;
                padding: 12px 8px;
                font-size: 10px;
                border: 1px solid #f0b90b;
                text-transform: uppercase;
            }

            td {
                padding: 10px;
                font-size: 10px;
                border: 1px solid #f0b90b;
                text-align: center;
                color: #fff;
            }

            input[type="text"],
            input[type="date"]{
                width: 90%;
                padding: 4px;
                background-color: #0b0e11;
                color: #f0b90b;
                border: 1px solid #f0b90b;
                border-radius: 4px;
                font-family: 'Press Start 2P', monospace;
                font-size: 10px;
            }

            input[type="text"]:focus,
            input[type="date"]:focus
            {
                outline: none;
                box-shadow: 0 0 5px #f0b90b;
            }

            p {
                font-size: 12px;
                color: red;
            }
            
            .invoiceID{
                text-align: center;
            }
        </style>

    </head>
    <body>

        <h2>DELIVERY LIST</h2>

        <c:if test="${empty DELIVERY_LIST}">
            <p style="text-align:center; color:red;">No deliveries available!</p>
        </c:if>

        <table>
            <thead>
                <tr>
                    <th>No</th>
                    <th>Delivery ID</th>
                    <th>Invoice ID</th>
                    <th>Address</th>
                    <th>Delivery Date</th>
                    <th>Status</th>

                </tr>
            </thead>
            <tbody>
                <c:forEach var="delivery" items="${DELIVERY_LIST}" varStatus="dl">
                    <tr>
                <form action="ListDeliveries" method="post">
                    <td>${dl.count}</td>
                    <td>
                        <input type="hidden" name="deliveryID" value="${delivery.deliveryID}"/>
                        ${delivery.deliveryID}
                    </td>
                    <td><input class="invoiceID" type="text" name="invoiceID" value="${delivery.invoiceID}" required></td>
                    <td><input type="text" name="address" value="${delivery.address}" required></td>
                    <td><input type="date" name="deliveryDate" value="${delivery.deliveryDate}" required></td>
                    <td><input type="text" name="status" value="${delivery.status}" required></td>
                </form>
            </tr>
        </c:forEach>
    </tbody>
</table>

</body>
</html>
