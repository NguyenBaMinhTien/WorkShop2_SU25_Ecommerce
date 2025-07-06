<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Quản lý bảo hành</title>
        <style>
            body {
                background-color: #0b0e11;
                color: #f0b90b;
                font-family: Arial, sans-serif;
                padding: 30px;
            }
            .btn-create {
                display: inline-block;
                margin-bottom: 15px;
                padding: 10px 20px;
                background-color: #4caf50;
                color: white;
                font-weight: bold;
                border-radius: 6px;
                text-decoration: none;
            }
            .btn-delete {
                background-color: #dc3545;
                color: white;
            }
            h2 {
                text-align: center;
            }
            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
            }
            th, td {
                padding: 10px;
                border: 1px solid #2f3a45;
                text-align: center;
            }
            th {
                background-color: #1e2329;
            }
            td {
                background-color: #131a20;
            }
            .btn {
                padding: 6px 12px;
                border: none;
                border-radius: 5px;
                margin: 2px;
                cursor: pointer;
                font-weight: bold;
            }
            .btn-approve {
                background-color: #4caf50;
                color: white;
            }
            .btn-expire {
                background-color: #ff9800;
                color: white;
            }
            .btn-extend {
                background-color: #2196f3;
                color: white;
            }
            .back {
                display: inline-block;
                margin-bottom: 20px;
                padding: 10px 20px;
                background: #f0b90b;
                color: black;
                font-weight: bold;
                text-decoration: none;
                border-radius: 8px;
            }
        </style>
    </head>
    <body>
        <a class="back" href="welcomeAdmin.jsp">← Quay về trang Admin</a>
        <h2>Danh sách Bảo Hành</h2>
        <a class="btn btn-create" href="WarrantyController?action=showForm">+ Tạo mới bảo hành</a>
        <c:if test="${not empty MESSAGE}">
            <p style="text-align: center; color: yellow; font-weight: bold;">
                ${MESSAGE}
            </p>
        </c:if>
        <table>
            <tr>
                <th>ID</th>
                <th>Product ID</th>
                <th>Duration (days)</th>
                <th>Terms</th>
                <th>Status</th>
                <th>Expiry</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="w" items="${WARRANTY_LIST}">
                <tr>
                    <td>${w.warrantyID}</td>
                    <td>${w.productID}</td>
                    <td>${w.durationDays}</td>
                    <td>${w.terms}</td>
                    <td>${w.status}</td>
                    <td>${w.expiryDate}</td>
                    <td>
                        <form method="post" action="WarrantyController" style="display:inline;">
                            <input type="hidden" name="action" value="updateStatus"/>
                            <input type="hidden" name="warrantyID" value="${w.warrantyID}"/>
                            <input type="hidden" name="status" value="approved"/>
                            <button class="btn btn-approve" type="submit">Approve</button>
                        </form>
                        <form method="post" action="WarrantyController" style="display:inline;">
                            <input type="hidden" name="action" value="updateStatus"/>
                            <input type="hidden" name="warrantyID" value="${w.warrantyID}"/>
                            <input type="hidden" name="status" value="expired"/>
                            <button class="btn btn-expire" type="submit">Mark Expired</button>
                        </form>
                        <form method="get" action="WarrantyController" style="display:inline;">
                            <input type="hidden" name="action" value="extendForm"/>
                            <input type="hidden" name="warrantyID" value="${w.warrantyID}"/>
                            <button class="btn btn-extend" type="submit">Extend</button>
                        </form>
                        <form method="post" action="WarrantyController" style="display:inline;">
                            <input type="hidden" name="action" value="delete"/>
                            <input type="hidden" name="warrantyID" value="${w.warrantyID}"/>
                            <button class="btn btn-delete" type="submit">Delete</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
