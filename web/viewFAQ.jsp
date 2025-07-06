<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>FAQ List</title>
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

            input[type="text"] {
                background-color: #0b0e11;
                color: #f0b90b;
                border: 1px solid #f0b90b;
                padding: 6px;
                font-family: 'Press Start 2P', monospace;
                font-size: 10px;
                width: 100%;
            }

            select {
                background-color: #0b0e11;
                color: #f0b90b;
                border: 1px solid #f0b90b;
                font-family: 'Press Start 2P', monospace;
                font-size: 10px;
                width: 100%;
                padding: 6px;
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

            .no-faqs {
                text-align: center;
                color: red;
                font-size: 10px;
                margin-top: 20px;
            }

            .action{
                display:flex;
                justify-content: space-between;
            }

        </style>
    </head>
    <body>
        <h2>FAQ LIST</h2>

        <c:if test="${empty FAQ_LIST}">
            <p class="no-faqs">No matching FAQs found!</p>
        </c:if>

        <div class="action">
            <form action="${pageContext.request.contextPath}/FAQController?action=viewFAQ" method="GET">
                <button class="btn-get" type="submit" name="action" value="viewFAQ">Get All FAQs</button>
            </form>

            <a class="btn-create" href="addFAQ.jsp">+ Create New FAQ</a>
        </div>

        <table>
            <thead>
                <tr>
                    <th>No</th>
                    <th>FAQ ID</th>
                    <th>Question</th>
                    <th>Answer</th>
                    <th>Status</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="faq" items="${FAQ_LIST}" varStatus="st">
                    <tr>
                        <form action="${pageContext.request.contextPath}/FAQController" method="POST">
                            <td>${st.count}</td>
                            <td>
                                <input type="hidden" name="faqID" value="${faq.faqID}" />
                                ${faq.faqID}
                            </td>
                            <td><input type="text" name="question" value="${faq.question}" required /></td>
                            <td><input type="text" name="answer" value="${faq.answer}" required /></td>
                            <td>
                                <select name="status" required>
                                    <option value="active" ${faq.status == 'active' ? 'selected' : ''}>active</option>
                                    <option value="inactive" ${faq.status == 'inactive' ? 'selected' : ''}>inactive</option>
                                </select>
                            </td>
                            <td class="actions">
                                <button type="submit" name="action" value="update">Update</button>
                                <c:if test="${faq.status == 'inactive'}">
                                    <button type="submit" name="action" value="delete">Delete</button>
                                </c:if>
                                
                            </td>
                        </form>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <br>
        <a class="btn-back" href="ProductController?action=ListProducts">← Back</a>
    </body>
</html>
