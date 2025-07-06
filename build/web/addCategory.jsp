<%-- 
    Document   : addCategory
    Created on : Jun 20, 2025, 11:54:30 AM
    Author     : DELL
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <!-- Font Retro -->
<link href="https://fonts.googleapis.com/css2?family=Press+Start+2P&display=swap" rel="stylesheet">

<!-- Retro Font -->
<link href="https://fonts.googleapis.com/css2?family=Press+Start+2P&display=swap" rel="stylesheet">

<style>
    body {
        font-family: 'Press Start 2P', cursive;
        background-color: #0b0e11;
        color: #f0b90b;
        padding: 40px;
    }

    .container {
        max-width: 600px;
        margin: 0 auto;
    }

    h1 {
        font-size: 14px;
        margin-bottom: 20px;
        text-align: center;
    }

    form {
        display: flex;
        flex-direction: column;
        gap: 12px;
        background-color: #131a20;
        padding: 20px;
        border-radius: 6px;
        border: 1px solid #2f3a45;
    }

    input[type="text"],
    input[type="hidden"],
    input[type="submit"] {
        font-family: 'Press Start 2P', cursive;
        font-size: 10px;
        background-color: #1e2329;
        color: #f0b90b;
        border: 1px solid #2f3a45;
        padding: 6px 10px;
        border-radius: 4px;
    }

    input[type="submit"] {
        cursor: pointer;
        transition: background-color 0.2s ease-in-out;
    }

    input[type="submit"]:hover {
        background-color: #2a323a;
    }

    a {
        display: inline-block;
        margin-top: 16px;
        font-size: 10px;
        color: #f0b90b;
        text-decoration: none;
        padding: 6px 12px;
        border: 1px solid #2f3a45;
        background: #1e2329;
        border-radius: 4px;
    }

    a:hover {
        background-color: #2a323a;
        text-decoration: underline;
    }

    .message {
        font-size: 10px;
        background-color: #1e2329;
        color: #f0b90b;
        border: 1px solid #f0b90b;
        padding: 10px;
        border-radius: 4px;
        margin-top: 16px;
        text-align: center;
    }
</style>


        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Category Page</title>
    </head>
    <body>
        <div class="container">
        <h1>CREATE CATEGORY</h1>
        <form action="CategoryController?action=AddCategory" method="POST">
            <input type="hidden" name="action" value="create"/>
            <input type="text" name="categoryName" placeholder="Enter Category Name" />
            <input type="text" name="description" placeholder="Enter Description" />
            <input type="submit" value="Create" />
        </form>

        <a href="CategoryController?action=ListCategories">← Back to Category List</a>

        <c:set var="msg" value="${requestScope.MSG}"/>
        <c:if test="${msg != null}">
            <div class="message">${msg}</div>
        </c:if>
    </div>
    </body>
</html>
