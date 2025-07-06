<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thêm mới bảo hành</title>
    <style>
        body {
            background-color: #0b0e11;
            font-family: Arial, sans-serif;
            color: #f0b90b;
            padding: 40px;
        }

        .container {
            max-width: 500px;
            margin: 0 auto;
            background-color: #1e2329;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 0 10px rgba(240, 185, 11, 0.3);
        }

        h2 {
            text-align: center;
            margin-bottom: 20px;
        }

        label {
            display: block;
            margin-top: 15px;
        }

        input[type="text"],
        textarea {
            width: 100%;
            padding: 8px;
            margin-top: 5px;
            background-color: #131a20;
            border: 1px solid #2f3a45;
            border-radius: 6px;
            color: #f0b90b;
        }

        .submit-btn {
            margin-top: 25px;
            width: 100%;
            padding: 10px;
            background-color: #f0b90b;
            color: #0b0e11;
            font-weight: bold;
            border: none;
            border-radius: 6px;
            cursor: pointer;
        }

        .submit-btn:hover {
            background-color: #ffcc00;
        }

        .back-link {
            display: block;
            text-align: center;
            margin-top: 20px;
            color: #f0b90b;
            text-decoration: underline;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Thêm bảo hành mới</h2>
    <form action="WarrantyController" method="post">
        <input type="hidden" name="action" value="create">

        <label for="productID">Mã sản phẩm:</label>
        <input type="text" id="productID" name="productID" required>

        <label for="durationDays">Thời hạn (ngày):</label>
        <input type="text" id="durationDays" name="durationDays" required>

        <label for="terms">Điều khoản:</label>
        <textarea id="terms" name="terms" required></textarea>

        <button type="submit" class="submit-btn">Tạo bảo hành</button>
    </form>
           

    <a class="back-link" href="WarrantyController?action=adminList">Quay về danh sách</a>
</div>
</body>
</html>
