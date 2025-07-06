<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="dto.DeliveryDTO" %>
<%
    DeliveryDTO delivery = (DeliveryDTO) request.getAttribute("DELIVERY_INFO");
    String error = (String) request.getAttribute("ERROR");
%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <title>Thông tin giao hàng</title>
        <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
        <style>
            body {
                font-family: 'Roboto', sans-serif;
                background-color: #f5f5f5;
                padding: 40px;
                margin: 0;
            }

            .container {
                max-width: 550px;
                background-color: #ffffff;
                border-radius: 12px;
                padding: 30px;
                margin: 0 auto;
                box-shadow: 0 8px 20px rgba(0, 0, 0, 0.08);
            }

            h2 {
                text-align: center;
                color: #222;
                margin-bottom: 30px;
            }

            .field {
                margin-bottom: 16px;
                font-size: 15px;
                color: #333;
            }

            .label {
                font-weight: bold;
                display: inline-block;
                min-width: 140px;
                color: #444;
            }

            .error {
                color: red;
                font-weight: bold;
                text-align: center;
                margin-bottom: 20px;
            }

            .back-link {
                display: block;
                margin-top: 30px;
                text-align: center;
                text-decoration: none;
                color: #ff6f00;
                font-weight: bold;
            }

            .back-link:hover {
                text-decoration: underline;
            }

            .info-box {
                background-color: #fdf8f2;
                border-left: 5px solid #ff6f00;
                padding: 20px;
                border-radius: 8px;
            }
        </style>
    </head>
    <body>
        <form action="${pageContext.request.contextPath}/DeliveryController?action=ListDeliveries" method="GET">
            <button class="btn-get" type="submit" name="action" value="ListDeliveries">Get All Deliveries</button>
        </form>
        <div class="container">
            <h2>Thông tin giao hàng</h2>

            <%
                if (error != null) {
            %>
            <div class="error"><%= error %></div>
            <%
                } else if (delivery != null) {
            %>
            <div class="info-box">
                <div class="field"><span class="label">Mã giao hàng:</span> <%= delivery.getDeliveryID() %></div>
                <div class="field"><span class="label">Mã hóa đơn:</span> <%= delivery.getInvoiceID() %></div>
                <div class="field"><span class="label">Địa chỉ giao:</span> <%= delivery.getAddress() %></div>
                <div class="field"><span class="label">Ngày giao:</span> <%= delivery.getDeliveryDate() %></div>
                <div class="field"><span class="label">Trạng thái:</span> <%= delivery.getStatus() %></div>
            </div>
            <%
                } else {
            %>
            <div class="error">Không có dữ liệu hiển thị.</div>
            <%
                }
            %>

            <a class="back-link" href="trackDeliveryForm.jsp">← Quay lại tra cứu</a>
        </div>
    </body>
</html>