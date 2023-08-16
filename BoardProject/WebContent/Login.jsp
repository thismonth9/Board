<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	String LoginFail = (String)request.getAttribute("LoginFail"); 
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/Login.css">
</head>
<%=LoginFail== null? "":LoginFail %>
<body>
  <div class="login-container">
    <h1>Login</h1>
    <form action="Controller?" method="get">
      <input type="hidden" name="command" value="login">
      <label for="username">ID</label>
      <input type="text" id="username" name="username" placeholder="Enter your ID" required>

      <label for="password">Password</label>
      <input type="password" id="password" name="password" placeholder="Enter your password" required>

      <input type="submit" value="Login">
    </form>
  </div>
</body>
</html>