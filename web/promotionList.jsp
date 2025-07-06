<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Promotion List</title>
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
                font-size: 14px;
                margin-bottom: 30px;
            }

            .btn {
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

            .btn:hover {
                background-color: #ffd131;
            }

            table {
                width: 100%;
                border-collapse: collapse;
                background-color: #1e2329;
                color: #f0b90b;
                margin-top: 20px;
            }

            th, td {
                border: 1px solid #f0b90b;
                padding: 10px;
                font-size: 10px;
                text-align: center;
            }

            input[type="text"], input[type="date"], input[type="number"] {
                background-color: #0b0e11;
                color: #f0b90b;
                border: 1px solid #f0b90b;
                padding: 6px;
                font-family: 'Press Start 2P', monospace;
                font-size: 10px;
                width: 100%;
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

            .action {
                display: flex;
                justify-content: space-between;
            }

            .no-promotions {
                text-align: center;
                color: red;
                font-size: 10px;
                margin-top: 20px;
            }
        </style>
    </head>
    <body>

        <h2>PROMOTION LIST</h2>

        <c:if test="${empty PROMOTION_LIST}">
            <p class="no-promotions">No promotions available!</p>
        </c:if>

        <!-- Search Form -->
        <form action="PromotionController" method="get">
            <input type="text" name="search" placeholder="Search Promotion">
            <button class="btn" type="submit" name="action" value="search">Search</button>
        </form>

        <div class="action">
            <form action="PromotionController" method="get">
                <button class="btn" type="submit" name="action" value="ListPromotions">Get All Promotions</button>
            </form>
            <a href="addPromotion.jsp" class="btn">+ Create New Promotion</a>
        </div>

        <!-- Promotion Table -->
        <table>
            <thead>
                <tr>
                    <th>No</th>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Discount %</th>
                    <th>Start Date</th>
                    <th>End Date</th>
                    <th>Status</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="promo" items="${PROMOTION_LIST}" varStatus="stt">
                    <tr>
                <form action="PromotionController" method="post">
                    <td>${stt.count}</td>
                    <td>
                        <input type="hidden" name="promoID" value="${promo.promoID}"/>
                        ${promo.promoID}
                    </td>
                    <td><input type="text" name="name" value="${promo.name}" required></td>
                    <td><input type="number" step="0.01" name="percent" value="${promo.discountPercent}" required></td>
                    <td><input type="date" name="startDate" value="${promo.startDate}" required></td>
                    <td><input type="date" name="endDate" value="${promo.endDate}" required></td>
                    <td><input type="text" name="status" value="${promo.status}" required></td>
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
<a class="btn" href="welcomeMarketing.jsp">← Back</a>

</body>
</html>
