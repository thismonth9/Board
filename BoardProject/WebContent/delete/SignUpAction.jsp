<%@page import="dto.MemberDto"%>
<%@page import="dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	request.setCharacterEncoding("UTF-8");
	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	String name =  request.getParameter("name");
	String nickname = request.getParameter("nickname");
	
	MemberDao signup = new MemberDao();
	boolean signupSuccess = signup.overlab(id);
	if(signupSuccess) {
		signup.member(id,pw,name,nickname);
		%>
		<script>
			alert("가입되셨습니다!")
			location.href ="Controller?command=login";
		</script>
	<%
	} else {
		%> 
		<script>
			alert("중복된 아이디가 있습니다!")
			location.href ="Controller?commend=signup";
		</script>
		<%
	}
%>
