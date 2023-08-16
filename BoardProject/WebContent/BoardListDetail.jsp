<%@page import="dto.CommentsDto"%>
<%@page import="dto.MemberDto"%>
<%@page import="dto.BoardDto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	int bno = (int) request.getAttribute("bno");
	BoardDto boardDto = (BoardDto)request.getAttribute("boardDto");
	String loginId = (String)session.getAttribute("id");
	MemberDto mdto = (MemberDto)request.getAttribute("mdto");
	ArrayList<CommentsDto> listBoard = (ArrayList<CommentsDto>)request.getAttribute("listBoard");
%>
<!DOCTYPE html>
<html>
<head>
  <title>내용</title>
  <link rel="stylesheet" href="css/BoardListDetail.css">
   <script src="js/jquery-3.7.0.min.js"></script>
   <script>
   	$(function() {
   		$(".list-button").click(function() {
   			location.href="Controller?command=board_list";
   		});
   		$(".like-button").click(function() {
   		  var bno = <%=boardDto.getBno()%>;
   		  location.href = "Controller?command=likes&bno=" + bno;
   		});
   		$(".dislike-button").click(function() {
     		  var bno = <%=boardDto.getBno()%>;
     		  location.href = "Controller?command=dislikes&bno=" + bno;
     		});
	   	 <% if (loginId != null) { %>
	     $(".logbtn").text("로그아웃");
	   <% } else { %>
	     $(".logbtn").text("로그인");
	     $(".comment-form").hide(); // 로그인되지 않은 상태에서 댓글 입력 폼 숨기기
	        // 댓글 입력 폼이 보이지 않을 때, 로그인 후 확인 메시지 표시
	        $(".comment-form").after("<p>로그인 후 댓글을 작성 하실 수 있습니다. <a href='Controller?command=login'>로그인</a></p>");
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
	   $(".update-btn").click(function() {
		  location.href = "Controller?command=writeupdate&bno=<%=boardDto.getBno()%>";
	   });
	  });
   </script>
</head>

<body>
 <header>
    <!-- 왼쪽에 로고 -->
    <div class="logo">
		Board
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
    <!-- 글 섹션 -->
    <section class="post">
      <h1 class="post-title"><%=boardDto.getTitle()%></h1>
      <p class="post-number">글 번호: #<%=boardDto.getBno() %></p>
      <p class="post-content">
      	<%if(boardDto.getImage() !=null) {%>
      		<img class="image" src='img/<%=boardDto.getImage() %>'/>
      	<%} %>
        <%=boardDto.getContent() %>
      </p>
    </section>
 	<div class="like-dislike-buttons">
	    <button class="like-button">좋아요 <span class="like-count"><%=boardDto.getLikes() %></span></button>
	    <button class="dislike-button">싫어요  <span class="dislike-count"><%=boardDto.getDislikes() %></span></button>
	    <button class="list-button">목록</button>
	    <%if(boardDto.getWriter().equals(loginId)) {%>
	    <button class="update-btn">수정</button>
	    <%} else {  %>
	   	  <button style="display:none" class="update-btn">수정</button>
	   	<%} %> 
 	 </div>
    <!-- 댓글 섹션 -->
    <section class="comments">
      <h2>댓글</h2>
      <!-- 댓글 입력 폼 -->
      <div class="comment-form">
        <form action="Controller?" method="get">
          <input type="hidden" name="command" value="comments"/>
          <input type="hidden" name="boardbno" value="<%= boardDto.getBno() %>">
          <label for="comment">댓글:</label><br>
          <textarea id="commentInput" name="commentInput" rows="2" required></textarea><br>
          <input type="submit" value="댓글 남기기">
        </form>
      </div>

      <!-- 댓글들 -->
      <div class="comment-box">
      	<% for (CommentsDto dto : listBoard) { %>
        <div class="comment">
            <p><strong><%=dto.getWriter()%>:</strong><%=dto.getComments() %> <span class="comment-date">작성일: <%=dto.getWritedate() %></span></p>
		</div>
		<%} %>
      </div>
    </section>
  </main>
</body>
</html>
