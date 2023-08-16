<%@page import="dto.*"%>
<%@page import="dao.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
    String id = request.getParameter("username");
    String pw = request.getParameter("password");
    String name ="";
    String nickname="";
    
    MemberDao loginDao = new MemberDao();
    boolean loginSuccess = loginDao.Log(id, pw);

    if (loginSuccess) {
        // 세션에 로그인 정보 저장
        MemberDto dto = new MemberDto(id,pw,name,nickname);
        session.setAttribute("id", dto.getId());
        session.setAttribute("pw", dto.getPw());
        
        // 환영 메시지 출력
        String welcomeMessage = dto.getId() + "님, 환영합니다.";
%>
        <script>
            alert("<%= welcomeMessage %>");
            location.href = "Controller?command=board_list";
        </script>
<%
    } else {
%>
        <script>
            alert("로그인 실패.");
            location.href = "Login.jsp"
        </script>
  	<%} %>
