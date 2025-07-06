<%-- 
    Document   : welcome
    Created on : Jun 18, 2025, 4:50:43 PM
    Author     : DELL
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Welcome Admin Page</title>
    <link href="https://fonts.googleapis.com/css2?family=Press+Start+2P&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" />
    <style>
        body {
            background-color: #0b0e11;
            color: #f0b90b;
            font-family: 'Press Start 2P', cursive;
            margin: 0;
            padding: 40px 20px;
            text-align: center;
        }

        h1 {
            font-size: 16px;
            margin-bottom: 40px;
            text-shadow: 1px 1px 3px rgba(255, 255, 255, 0.2);
        }

        .circle-buttons {
            display: flex;
            flex-wrap: wrap;
            gap: 30px;
            justify-content: center;
            align-items: center;
        }

        .circle-button {
            width: 150px;
            height: 150px;
            border-radius: 50%;
            background-color: #1e1e1e;
            border: 2px solid #f0b90b;
            color: #ffffff;
            text-decoration: none;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            transition: transform 0.3s ease, box-shadow 0.3s ease;
        }

        .circle-button:hover {
            transform: scale(1.08);
            box-shadow: 0 0 15px rgba(240, 185, 11, 0.5);
        }

        .circle-button i {
            font-size: 28px;
            margin-bottom: 12px;
        }

        .circle-button span {
            font-size: 10px;
            color: #f0b90b;
        }
        .logout{
            color: black;
            text-decoration: none;
            text-transform: uppercase;
            position: absolute;
            top: 10px;
            right: 10px;
            padding: 10px;
            border-radius: 10px;
            background-color: yellow;
            border: 2px solid #f0b90b;
        }
    </style>
</head>
<body>
    <h1>WELCOME <c:out value="${sessionScope.LOGIN_USER.fullName}" /></h1>
    <p><a class="logout" href="${pageContext.request.contextPath}/LogoutController">Logout</a></p>

    <div class="circle-buttons">
        <a href="${pageContext.request.contextPath}/UserController?action=ListUsers" class="circle-button">
            <i class="fas fa-users-gear"></i>
            <span>Users Management</span>
        </a>

        <a href="${pageContext.request.contextPath}/ProductController?action=ListProducts" class="circle-button">
            <i class="fas fa-chart-line"></i>
            <span>View All Products</span>
        </a>

        <a href="${pageContext.request.contextPath}/CategoryController?action=ListCategories" class="circle-button">
            <i class="fas fa-blog"></i>
            <span>Categories Management</span>
        </a>
    </div>
</body>
</html>
