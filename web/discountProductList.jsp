<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Chương trình khuyến mãi</title>
        <style>
            body {
                background-color: #0b0e11;
                color: #f0b90b;
                font-family: 'Press Start 2P', cursive;
                padding: 20px;
            }
            h2 {
                text-align: center;
                font-size: 14px;
                margin-bottom: 25px;
            }
            table {
                width: 90%;
                margin: 0 auto;
                border-collapse: collapse;
                font-size: 10px;
            }
            th, td {
                border: 1px solid #2f3a45;
                padding: 10px;
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
            p {
                font-size: 12px;
                color: #f0b90b;
            }

            .btn-back {
                display: inline-block;
                margin: 20px;
                padding: 10px 20px;
                color: #f0b90b;
                background-color: #1e2329;
                border: 1px solid #f0b90b;
                text-decoration: none;
                font-size: 12px;
                border-radius: 8px;
                transition: background-color 0.3s ease, color 0.3s ease;
            }

            .btn-back:hover {
                background-color: #f0b90b;
                color: #0b0e11;
            }

        </style>
        <link href="https://fonts.googleapis.com/css2?family=Press+Start+2P&display=swap" rel="stylesheet">

    </head>
    <body>
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

        <a class="btn-back" href="welcomeMarketing.jsp">← Back</a>

    </body>
</html>
