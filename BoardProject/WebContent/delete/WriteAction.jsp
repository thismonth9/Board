<%@page import="dao.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	
	String title = request.getParameter("title");
	String content = request.getParameter("content");
// 	String writer = request.getParameter("writer");
	
	BoardDao writeDao = new BoardDao();
%>
<script> 
	alert("등록되었습니다.");
	location.href="Controller?command=board_list";
</script>