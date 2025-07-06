<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Yêu cầu trả hàng - Admin</title>
    <style>
        body {
            background-color: #0b0e11;
            color: #f0b90b;
            font-family: Arial, sans-serif;
            padding: 30px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 30px;
        }

        th, td {
            border: 1px solid #2f3a45;
            padding: 10px;
            text-align: center;
        }

        th {
            background-color: #1e2329;
        }

        td {
            background-color: #131a20;
        }

        form {
            display: inline-block;
        }

        button {
            padding: 6px 10px;
            margin: 2px;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            font-weight: bold;
        }

        .approve {
            background-color: #4caf50;
            color: white;
        }

        .reject {
            background-color: #f44336;
            color: white;
        }
    </style>
</head>
<body>
    <h2>Danh sách yêu cầu trả hàng</h2>
<a href="welcomeAdmin.jsp" class="back-button">⬅ Quay về trang chính</a>
    <table>
        <tr>
            <th>ID</th>
            <th>Invoice</th>
            <th>Reason</th>
            <th>Status</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="r" items="${RETURN_LIST}">
            <tr>
                <td>${r.returnID}</td>
                <td>${r.invoiceID}</td>
                <td>${r.reason}</td>
                <td>${r.status}</td>
                <td>
                    <c:if test="${r.status eq 'Pending'}">
                        <form method="post" action="ReturnController">
                            <input type="hidden" name="action" value="updateStatus"/>
                            <input type="hidden" name="returnID" value="${r.returnID}"/>
                            <input type="hidden" name="status" value="Approved"/>
                            <button class="approve" type="submit">Approve</button>
                        </form>
                        <form method="post" action="ReturnController">
                            <input type="hidden" name="action" value="updateStatus"/>
                            <input type="hidden" name="returnID" value="${r.returnID}"/>
                            <input type="hidden" name="status" value="Rejected"/>
                            <button class="reject" type="submit">Reject</button>
                        </form>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>