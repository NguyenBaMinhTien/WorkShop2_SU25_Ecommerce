<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gia hạn bảo hành</title>
    <style>
        body {
            background-color: #0b0e11;
            color: #f0b90b;
            font-family: Arial, sans-serif;
            padding: 40px;
        }

        .form-container {
            max-width: 500px;
            margin: 0 auto;
            background-color: #1e2329;
            border: 1px solid #f0b90b;
            padding: 20px;
            border-radius: 10px;
        }

        h2 {
            text-align: center;
            margin-bottom: 25px;
            color: #f0b90b;
        }

        label {
            display: block;
            margin-bottom: 10px;
        }

        input[type="number"] {
            width: 100%;
            padding: 8px;
            margin-bottom: 20px;
            border: none;
            border-radius: 4px;
        }

        button {
            background-color: #f0b90b;
            color: #0b0e11;
            padding: 10px 20px;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            font-weight: bold;
            display: block;
            width: 100%;
        }

        button:hover {
            background-color: #d1a600;
        }
    </style>
</head>
<body>
<div class="form-container">
    <h2>Gia hạn bảo hành</h2>
    <form method="post" action="WarrantyController">
        <input type="hidden" name="action" value="extend"/>
        <input type="hidden" name="warrantyID" value="${warrantyID}"/>

        <label for="extendDays">Số ngày gia hạn:</label>
        <input type="number" id="extendDays" name="extendDays" required min="1" max="365"/>

        <button type="submit">Gia hạn</button>
    </form>
</div>
</body>
</html>
