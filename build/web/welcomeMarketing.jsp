<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Welcome Marketing</title>
        <style>
            body {
                background-color: #0b0e11;
                color: #f0b90b;
                font-family: 'Press Start 2P', cursive;
                padding: 30px;
            }
            h2 {
                text-align: center;
                font-size: 14px;
                margin-bottom: 20px;
            }
            table {
                width: 90%;
                margin: 0 auto;
                border-collapse: collapse;
                font-size: 10px;
            }
            th, td {
                border: 1px solid #2f3a45;
                padding: 8px;
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
            .btn-get {
                display: block;
                margin: 20px auto;
                padding: 10px 20px;
                font-size: 10px;
                background-color: #f0b90b;
                color: #0b0e11;
                border: none;
                cursor: pointer;
                font-family: 'Press Start 2P', cursive;
            }
            .btn-get:hover {
                background-color: #d99a00;
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
        <link href="https://fonts.googleapis.com/css2?family=Press+Start+2P&display=swap" rel="stylesheet">

    </head>
    <body>
        <form action="${pageContext.request.contextPath}/PromotionController?action=ListPromotions" method="GET">
            <button class="btn-get" type="submit" name="action" value="ListPromotions">Get Promotion List</button>
        </form>
        <form action="${pageContext.request.contextPath}/ProductController?action=ListProducts" method="GET">
            <button class="btn-get" type="submit" name="action" value="discounted">Get Discount Product List</button>
        </form>
        <p><a class="logout" href="${pageContext.request.contextPath}/LogoutController">Logout</a></p>
    </body>
</html>
