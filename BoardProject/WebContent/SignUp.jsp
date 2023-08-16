<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% 
	boolean signupCheck = (boolean)request.getAttribute("signupCheck");
	String id = (String)request.getAttribute("id");
 	%>
<!DOCTYPE html>
<html>
<head>
  <title>회원가입</title>
  <link rel="stylesheet" href="css/SignUp.css">
</head>
<%if(!signupCheck) {%>
	<script>
		alert("<%=id%>는 중복된 아이디입니다. 다시 입력해주세요.");
		location.href = "Controller?command=signup";
	</script>
<% } else if(signupCheck&&id!=null){%>
	<script>
		alert("<%=id%>님 회원가입되셨습니다.");
		location.href = "Controller?command=login";
	</script>
<% } %>
<body>
  <div class="signup-container">
    <h1>회원가입</h1>
    <form action="Controller?" method="get">
      <input type="hidden" name="command" value="signup">
      <div class="form-group">
        <label for="username">아이디:</label>
        <input type="text" id="username" name="id" required>
      </div>
      <div class="form-group">
        <label for="password">비밀번호:</label>
        <input type="password" id="password" name="pw" required>
      </div>
      <div class="form-group">
        <label for="name">이름:</label>
        <input type="text" id="name" name="name" required>
      </div>
      <div class="form-group">
        <label for="nickname">닉네임:</label>
        <input type="text" id="nickname" name="nickname" required>
      </div>
      <div class="form-group">
        <button type="submit">가입하기</button>
      </div>
    </form>
  </div>
</body>
</html>