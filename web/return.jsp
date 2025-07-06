<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String error = (String) request.getAttribute("ERROR");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Yêu cầu trả hàng</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background: linear-gradient(135deg, #e0f7fa, #f0f4ff);
            color: #333;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 600px;
            margin: 80px auto;
            background: #ffffff;
            border-radius: 16px;
            box-shadow: 0 12px 30px rgba(0, 200, 255, 0.25);
            padding: 30px 40px;
            border: 2px solid #00eaff;
        }

        h2 {
            text-align: center;
            color: #673ab7;
            font-weight: bold;
            font-size: 28px;
            margin-bottom: 30px;
            text-shadow: 1px 1px 0 #fff;
        }

        label {
            display: block;
            margin-top: 20px;
            font-weight: bold;
            color: #333;
        }

        input[type="text"],
        textarea {
            width: 100%;
            padding: 10px;
            border: 2px solid #00eaff;
            border-radius: 8px;
            margin-top: 8px;
            font-size: 15px;
        }

        textarea {
            resize: vertical;
            height: 100px;
        }

        .btn-submit {
            display: block;
            width: 100%;
            background: linear-gradient(90deg, #00eaff, #3f51b5);
            color: white;
            padding: 12px;
            border: none;
            border-radius: 10px;
            margin-top: 30px;
            font-size: 16px;
            cursor: pointer;
            transition: 0.3s ease;
        }

        .btn-submit:hover {
            background: linear-gradient(90deg, #00cfff, #5c6bc0);
        }

        .error {
            margin-top: 20px;
            color: red;
            font-weight: bold;
            text-align: center;
        }

        .footer {
            text-align: center;
            margin-top: 40px;
            color: #666;
            font-size: 13px;
        }
    </style>
</head>
<body>

    <div class="container">
        <h2>Yêu cầu trả hàng</h2>
        <form action="ReturnController" method="post">
            <input type="hidden" name="action" value="SubmitReturn"/>

            <label for="invoiceID">Mã hóa đơn:</label>
            <input type="text" id="invoiceID" name="invoiceID" required/>

            <label for="reason">Lý do trả hàng:</label>
            <textarea id="reason" name="reason" required></textarea>

            <button class="btn-submit" type="submit">Gửi yêu cầu</button>

            <% if (error != null) { %>
                <div class="error"><%= error %></div>
            <% } %>
        </form>
    </div>

    

</body>
</html>