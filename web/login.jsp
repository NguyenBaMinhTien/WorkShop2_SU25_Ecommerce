<%-- 
    Document   : login
    Created on : Jun 18, 2025, 4:50:35 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Login</title>
  <link href="https://fonts.googleapis.com/css2?family=Press+Start+2P&display=swap" rel="stylesheet">
  <style>
    body {
      background-color: #0b0e11;
      color: #f0b90b;
      font-family: 'Press Start 2P', monospace;
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
      margin: 0;
    }

    .login-box {
      background-color: #1e2329;
      padding: 30px 40px;
      border-radius: 12px;
      box-shadow: 0 0 12px rgba(240, 185, 11, 0.2);
      text-align: center;
    }

    h2 {
      font-size: 16px;
      margin-bottom: 20px;
    }

    label {
      display: block;
      margin: 12px 0 6px;
      font-size: 10px;
      color: #f0b90b;
    }

    input[type="text"],
    input[type="password"] {
      width: 100%;
      padding: 10px;
      border: none;
      border-radius: 6px;
      font-family: 'Press Start 2P', monospace;
      font-size: 10px;
      background-color: #121416;
      color: #f0b90b;
      outline: none;
      margin-bottom: 10px;
    }

    .error {
      color: red;
      font-size: 10px;
      margin-bottom: 12px;
    }

    button {
      margin-top: 10px;
      background-color: #f0b90b;
      color: #0b0e11;
      border: none;
      padding: 10px 20px;
      border-radius: 6px;
      font-family: 'Press Start 2P', monospace;
      font-size: 10px;
      cursor: pointer;
      transition: background 0.3s ease;
    }

    button:hover {
      background-color: #ffd131;
    }
  </style>
</head>
<body>
  <div class="login-box">
    <h2>LOGIN</h2>

    <c:if test="${not empty MSG}">
      <p class="error">${MSG}</p>
    </c:if>

    <form action="${pageContext.request.contextPath}/UserController?action=Login" method="post">
      <label for="userID">UserID</label>
      <input type="text" id="userID" name="userID" required />

      <label for="password">Password</label>
      <input type="password" id="password" name="password" required />

      <button type="submit">LOGIN</button>
    </form>
  </div>
</body>
</html>
