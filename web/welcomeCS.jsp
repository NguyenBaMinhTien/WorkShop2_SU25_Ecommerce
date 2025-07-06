<%-- 
    Document   : welcomeCS
    Created on : Jul 3, 2025, 6:23:06 PM
    Author     : DELL
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Customer Service Dashboard</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f8f8f8;
                padding: 20px;
                color: #333;
            }

            h1 {
                color: #2c3e50;
            }

            table {
                border-collapse: collapse;
                width: 100%;
                background-color: #fff;
                margin-top: 20px;
            }

            th, td {
                border: 1px solid #ccc;
                padding: 8px 12px;
                text-align: left;
            }

            th {
                background-color: #2c3e50;
                color: white;
            }

            tr:nth-child(even) {
                background-color: #f2f2f2;
            }

            .reply-form {
                margin-top: 20px;
            }
        </style>
    </head>
    <body>

        <h1>Customer Service Panel</h1>

    <c:if test="${not empty CUSTOMER_CARE_LIST}">
        <table>
            <tr>
                <th>Ticket ID</th>
                <th>User ID</th>
                <th>Subject</th>
                <th>Content</th>
                <th>Status</th>
                <th>Reply</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="ticket" items="${CUSTOMER_CARE_LIST}">
                <tr>
                    <td>${ticket.ticketID}</td>
                    <td>${ticket.userID}</td>
                    <td>${ticket.subject}</td>
                    <td>${ticket.content}</td>
                    <td>${ticket.status}</td>
                    <td>
                <c:choose>
                    <c:when test="${not empty ticket.reply}">
                        ${ticket.reply}
                    </c:when>
                    <c:otherwise>
                        <i>Chưa phản hồi</i>
                    </c:otherwise>
                </c:choose>
                </td>
                <td>
                <c:if test="${ticket.status ne 'closed'}">
                    <a class="btn-submit" href="replyCustomerCares.jsp?ticketID=${ticket.ticketID}">Reply</a>
                </c:if>
                </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

    <c:if test="${empty CUSTOMER_CARE_LIST}">
        <p>Không có ticket nào từ khách hàng.</p>
    </c:if>

</body>
</html>
