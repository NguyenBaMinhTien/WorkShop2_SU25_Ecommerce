<%-- 
    Document   : trackDeliveryForm
    Created on : 02-Jul-2025, 13:50:29
    Author     : nguyễn duy phong
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Tra cứu đơn hàng</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
    <style>
        body {
            margin: 0;
            padding: 0;
            background-color: #f5f5f5;
            font-family: 'Roboto', sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .card {
            background-color: #ffffff;
            padding: 40px 30px;
            border-radius: 12px;
            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.08);
            width: 380px;
        }

        h2 {
            color: #222;
            text-align: center;
            margin-bottom: 30px;
            font-size: 22px;
        }

        label {
            font-size: 14px;
            color: #555;
            margin-bottom: 8px;
        }

        input[type="text"] {
            width: 100%;
            padding: 12px;
            font-size: 14px;
            border: 1px solid #ccc;
            border-radius: 6px;
            margin-bottom: 20px;
            box-sizing: border-box;
        }

        input[type="text"]:focus {
            border-color: #3f51b5;
            outline: none;
            box-shadow: 0 0 3px #3f51b580;
        }

        button {
            width: 100%;
            padding: 12px;
            font-size: 14px;
            font-weight: bold;
            background-color: #ff6f00;
            color: white;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        button:hover {
            background-color: #e65c00;
        }
    </style>
</head>
<body>
<div class="card">
    <h2>Tra cứu giao hàng</h2>
    <form action="DeliveryController" method="get">
        <input type="hidden" name="action" value="TrackDelivery">
        <label for="invoiceID">Mã hóa đơn:</label>
        <input type="text" id="invoiceID" name="invoiceID" placeholder="Nhập mã hóa đơn..." required>
        <button type="submit">Tra cứu đơn hàng</button>
    </form>
</div>
</body>
</html>