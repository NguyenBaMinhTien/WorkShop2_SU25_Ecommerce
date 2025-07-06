<%-- 
    Document   : addUser
    Created on : Jun 20, 2025, 11:54:50 AM
    Author     : DELL
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create User</title>
    <link href="https://fonts.googleapis.com/css2?family=Press+Start+2P&display=swap" rel="stylesheet">
    <style>
        body {
            background-color: #0b0e11;
            color: #f0b90b;
            font-family: 'Press Start 2P', cursive;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .container {
            background-color: #1e1e1e;
            padding: 30px 40px;
            border-radius: 16px;
            box-shadow: 0 0 15px rgba(240, 185, 11, 0.3);
            width: 90%;
            max-width: 500px;
            text-align: center;
        }

        h1 {
            font-size: 16px;
            margin-bottom: 30px;
            text-shadow: 1px 1px 3px rgba(255, 255, 255, 0.2);
        }

        input[type="text"], input[type="password"], input[type="tel"] {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            background-color: #0b0e11;
            color: #f0b90b;
            border: 1px solid #f0b90b;
            border-radius: 6px;
            font-family: 'Press Start 2P', cursive;
            font-size: 10px;
        }

        input[type="submit"] {
            background-color: #f0b90b;
            color: #0b0e11;
            border: none;
            padding: 12px 24px;
            margin-top: 12px;
            border-radius: 6px;
            cursor: pointer;
            font-size: 10px;
            font-family: 'Press Start 2P', cursive;
        }

        input[type="submit"]:hover {
            background-color: #ffe07a;
        }

        a {
            display: inline-block;
            margin-top: 16px;
            color: #f0b90b;
            text-decoration: none;
            font-size: 10px;
        }

        a:hover {
            text-decoration: underline;
        }

        .message {
            margin-top: 20px;
            color: red;
            font-size: 10px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>👤 CREATE USER</h1>
        <form action="UserController?action=AddUser" method="POST">
            <input type="hidden" name="action" value="create"/>
            <input type="text" name="userID" placeholder="Enter User ID" />
            <input type="text" name="fullName" placeholder="Enter Full Name" />
            <input type="text" name="roleID" placeholder="Enter Role ID" />
            <input type="password" name="password" placeholder="Enter Password" />
            <input type="tel" name="phone" placeholder="Enter Phone Number" />
            <input type="submit" value="Create" />
        </form>

        <a href="UserController?action=ListUsers">← Back to User List</a>

        <c:set var="msg" value="${requestScope.MSG}"/>
        <c:if test="${msg != null}">
            <div class="message">${msg}</div>
        </c:if>
    </div>
</body>
</html>
