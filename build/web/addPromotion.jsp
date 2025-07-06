<%-- 
    Document   : addPromotion
    Created on : Jun 18, 2025, 5:30:32 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head><title>Thêm khuyến mãi</title></head>
    <body>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f2f2f2;
                display: flex;
                justify-content: center;
                align-items: center;
                min-height: 100vh;
                margin: 0;
            }

            form {
                background-color: #ffffff;
                padding: 30px;
                border-radius: 10px;
                box-shadow: 0 0 15px rgba(0,0,0,0.1);
                width: 400px;
            }

            h2 {
                text-align: center;
                color: #333;
                margin-bottom: 24px;
                margin-left: 20px;
            }

            input[type="text"],
            input[type="number"],
            input[type="date"],
            select {
                width: 100%;
                padding: 10px;
                margin: 10px 0 20px 0;
                border: 1px solid #ccc;
                border-radius: 6px;
                box-sizing: border-box;
            }

            input[type="submit"] {
                background-color: #4CAF50;
                color: white;
                padding: 12px;
                border: none;
                border-radius: 6px;
                cursor: pointer;
                font-size: 16px;
                width: 100%;
            }

            input[type="submit"]:hover {
                background-color: #45a049;
            }
        </style>

        <h2>Thêm chương trình khuyến mãi</h2>
        <form action="PromotionController" method="post">
            <input type="hidden" name="action" value="AddPromotion"/>
            Tên chương trình: <input type="text" name="name"/><br/>
            % giảm giá: <input type="number" name="percent" step="0.1"/><br/>
            Ngày bắt đầu: <input type="date" name="startDate"/><br/>
            Ngày kết thúc: <input type="date" name="endDate"/><br/>
            Trạng thái: <select name="status">
                <option value="active">Active</option>
                <option value="inactive">Inactive</option>
            </select><br/>
            <input type="submit" value="Tạo khuyến mãi"/>
        </form>
        <a class="btn-back" href="PromotionController?action=ListPromotions">← Back</a>
    </body>
</html>
