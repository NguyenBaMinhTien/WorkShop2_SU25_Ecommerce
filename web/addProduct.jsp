<%-- 
    Document   : addProduct
    Created on : Jun 18, 2025, 5:23:58 PM
    Author     : DELL
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Thêm sản phẩm</title></head>
<body>
<h2>Thêm sản phẩm</h2>
<form action="ProductController" method="post">
    <input type="hidden" name="action" value="AddProduct"/>
    Tên sản phẩm: <input type="text" name="name"/><br/>
    Mã ngành hàng: <input type="number" name="categoryID"/><br/>
    Giá: <input type="number" step="0.01" name="price"/><br/>
    Số lượng: <input type="number" name="quantity"/><br/>
    Mã người bán: <input type="text" name="sellerID"/><br/>
    Trạng thái: <select name="status">
        <option value="active">Active</option>
        <option value="inactive">Inactive</option>
    </select><br/>
    <input type="submit" value="Thêm"/>
</form>
<p style="color:red">${requestScope.ERROR}</p>
<a class="btn-back" href="ProductController?action=ListProducts">← Back</a>
</body>
</html>

