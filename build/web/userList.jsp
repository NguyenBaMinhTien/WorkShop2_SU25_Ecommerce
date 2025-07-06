<%-- 
    Document   : userList
    Created on : Jun 19, 2025, 11:23:53 AM
    Author     : DELL
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>User List</title>
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

            .no-users {
                text-align: center;
                color: red;
                font-size: 10px;
                margin-top: 20px;
            }

            .btn-search {
                margin-top: 10px;
            }

            .action{
                display:flex;
                justify-content: space-between;
            }

        </style>
    </head>
    <body>
        <h2>USER LIST</h2>

        <c:if test="${empty USER_LIST}">
            <p class="no-users">No matching users found!</p>
        </c:if>
        <div>
            <form action="${pageContext.request.contextPath}/UserController?action=searchUser" method="GET">
                <input type="text" name="search" placeholder="Search User"/><br/>
                <button class="btn-search" type="submit" name="action" value="search">Search</button>
            </form>
        </div>

        <div class="action">
            <form action="${pageContext.request.contextPath}/UserController?action=ListUsers" method="GET">
                <button class="btn-get" type="submit" name="action" value="getUserList">Get All Users</button>
            </form>


            <a class="btn-create" href="addUser.jsp">+ Create New User</a>
        </div>
        <table>
            <thead>
                <tr>
                    <th>No</th>
                    <th>User ID</th>
                    <th>Full Name</th>
                    <th>Role ID</th>
                    <th>Password</th>
                    <th>Phone</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="user" items="${USER_LIST}" varStatus="us">
                    <tr>
                <form action="${pageContext.request.contextPath}/UserController" method="POST">
                    <td>${us.count}</td>
                    <td>
                        <input type="hidden" name="userID" value="${user.userID}" />
                        ${user.userID}
                    </td>
                    <td><input type="text" name="fullName" value="${user.fullName}" required /></td>
                    <td><input type="text" name="roleID" value="${user.roleID}" required /></td>
                    <td><input type="text" name="password" value="${user.password}" required /></td>
                    <td><input type="text" name="phone" value="${user.phone}" required/></td>
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
<a class="btn-back" href="welcomeAdmin.jsp">← Back</a>
</body>
</html>
