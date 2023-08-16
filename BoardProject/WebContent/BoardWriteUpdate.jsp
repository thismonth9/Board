<%@page import="dto.BoardDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%  
	String loginId = (String)session.getAttribute("id");
	BoardDto dto = (BoardDto)request.getAttribute("dto");
	boolean success = (boolean)(request.getAttribute("success")==null?false:request.getAttribute("success"));
%>
<!DOCTYPE html>
<html>
<head>
  <title>게시판 글쓰기</title>
  <link rel="stylesheet" href="css/BoardWrite.css">
   <script src="js/jquery-3.7.0.min.js"></script>
   <script>
   		$(function() {
   			<% if (loginId != null) { %>
   	        $(".logbtn").text("로그아웃");
   	      <% } else { %>
   	        $(".logbtn").text("로그인");
   	      <% } %>
   	      
   	     $(".logbtn").click(function() {
   		     if($(this).text()=="로그아웃") {
   		    	 location.href="Controller?command=logout";
   		     }else {
   		    	 location.href="Controller?command=login";
   		     }
   	     });
   	     $(".mypage").click(function() {
   	    	location.href = "Controller?command=mypage&id=<%=loginId%>"; 
   	     });
   	     
   	
	});
   <%if(success){%>
   	alert("수정되었습니다.");
   	location.href="Controller?command=board_detail_list&bno=<%=dto.getBno()%>";
   <%}%>
   </script>
</head>
<body>
  <header>
    <div class="logo">
      <img src="logo.png" alt="로고 이미지">
    </div>
    <!-- 오른쪽에 버튼 -->
    <nav class="buttons">
      <a href="Controller?command=board_list">홈</a>
      <%if(loginId!=null) { %>
     	 <a class="mypage">마이페이지</a>
      <%}%>
      <a href="Controller?command=signup">회원가입</a>
      <a class="logbtn">로그인</a>
    </nav>
  </header>

  <main>
    <h1>게시판 수정하기</h1>
    <form action="WriteUpdateServlet" method="post" enctype="multipart/form-data">
      <label for="title">제목:</label><br>
      <input type="text" id="title" name="title" value="<%=dto.getTitle()%>" required><br>

      <label for="content">내용:</label><br>
      <textarea id="content" name="content" rows="10" required><%=dto.getContent()%></textarea><br>
       
      <label for="image">이미지 파일:</label>
      <input type="file" id="image" name="image">
      <br>
      
      <label for="visibility">공개 여부:</label>
   	  <select id="visibility" name="visibility">
	      <option value="공개">공개</option>
	      <option value="비공개">비공개</option>
      </select>
      <br>
      <input type="hidden" name="bno" value="<%=dto.getBno()%>"/>
      <input type="submit" class="insert" value="글쓰기">
    </form>
  </main>

  <!-- 스크립트 요소들이나 추가적인 내용은 여기에 추가해주세요. -->

</body>
</html>