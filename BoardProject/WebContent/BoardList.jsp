<%@page import="dao.*"%>
<%@page import="dto.*"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
  ArrayList<BoardDto> listBoard = (ArrayList<BoardDto>)request.getAttribute("listBoard");
  String loginId = (String)session.getAttribute("id");
  int count = (int)request.getAttribute("count");
  MemberDto mdto = (MemberDto)request.getAttribute("mdto");
%>
<!DOCTYPE html>
<html>
<head>
  <title>게시판</title>
  <link rel="stylesheet" href="css/boardList.css">
  <script src="js/jquery-3.7.0.min.js"></script>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
  <script>
  	let page_requested = 1;
  	function request_one_page(){
  		page_requested++;
  		$.ajax({
  			type: 'get',
  			url: 'BoardServlet2',
  			data: {'pageNum':page_requested},
  			dataType: 'json',
  			success: function(data) { 
  			  for (var i = 0; i <= data.length - 1; i++) {
                  let str = "<tr>" +
                      "<td>" + data[i].bno + "</td>" +
                      "<td>" + data[i].title + "</td>" +
                      "<td>" + data[i].writer + "</td>" +
                      "<td>" + data[i].writedate + "</td>" +
                      "<td>" + data[i].views + "</td>" +
                      "<td>" + data[i].likes + "</td>" +
                      "<td>" + data[i].dislikes + "</td>" +
                      "<td>";

                  if (data[i].writer === '<%= (mdto == null) ? "" : mdto.getNickname() %>' || ('<%=loginId%>' != null && '<%=loginId%>' === "admin")) {
                      str += "<button class='deleteBtn'>삭제</button>";
                  } else {
                      str += "<button class='deleteBtn' style='display:none;'>삭제</button>";
                  }

                  str += "</td>" +
                      "</tr>";

                  $("#boardTable").append(str);
              }
  			},
  			 error: function (request, status, error) {
  	           console.log("code: " + request.status)
  	           console.log("message: " + request.responseText)
  	           console.log("error: " + error);
  	         }
  		});
  	}
    $(function() {
    	 $(".slider").bxSlider();
      // 테이블 행 클릭 시 상세 페이지로 이동
     $(".list").click(function() {
	  let bno = $(this).closest("tr").find(".bno").text();
	  location.href = "Controller?command=views&bno=" + bno;
	});
      
      $("#writeBtn").click(function() {
        location.href = "Controller?command=write";
      });
      $(window).scroll(function(){
    	  var scrT = $(window).scrollTop();
    	  if(scrT == $(document).height() - $(window).height()){
    		  request_one_page();
    	  }
   	  });
      
      // 세션 아이디로 로그인 상태에 따라 버튼 텍스트 변경
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
     $("#searchBtn").click(function() {
       let searchText = $("#searchText").val();

       $.ajax({
         url: 'search',
         type: 'POST',
         data: {
           'searchTerm': searchText
         },
         dataType: 'json',
         success: function(data) {
           displaySearchResult(data);
           $(".list").click(function() {
        		  let bno = $(this).closest("tr").find(".bno").text();
        		  location.href = "Controller?command=board_detail_list&bno=" + bno;
        		});
           $(".deleteBtn").click(function() {
          	 let bno = $(this).closest("tr").find(".bno").text();
          	 location.href ="Controller?command=delete&bno="+bno+"&writer=<%=loginId%>";
          	 alert("삭제되었습니다");
           });
         },
         error: function (request, status, error) {
           console.log("code: " + request.status)
           console.log("message: " + request.responseText)
           console.log("error: " + error);
         }
       });
     });

     function displaySearchResult(result) {
       var searchResultDiv = $("#searchResult");
       searchResultDiv.empty(); // 이전 검색 결과를 비웁니다.

       var table = $("<table>");
       for (var i = 0; i < result.length; i++) {
         var row = $("<tr>");
         row.append($("<td class='bno list'>").text(result[i].bno)); // .bno 클래스 추가
         row.append($("<td class='list'>").text(result[i].title));
         row.append($("<td class='writer list'>").text(result[i].writer));
         row.append($("<td class='list'>").text(result[i].writedate));
         row.append($("<td class='list'>").text(result[i].views));
         row.append($("<td class='list'>").text(result[i].likes));
         row.append($("<td class='list'>").text(result[i].dislikes));
         var deleteButton = $("<button class='deleteBtn'>삭제</button>");
         row.append($("<td>").append(deleteButton));

         // 아이디가 같으면 버튼을 보이도록 설정, 아이디가 다르면 버튼을 숨기도록 설정
         if (result[i].writer === "<%=loginId%>") {
           deleteButton;
         } else {
           deleteButton.css("display", "none");
         }
         searchResultDiv.append(row);
       }
     }

     $(".mypage").click(function() {
    	location.href = "Controller?command=mypage&id=<%=loginId%>"; 
     });
     $(".deleteBtn").click(function() {
    	 let bno = $(this).closest("tr").find(".bno").text();
    	 location.href ="Controller?command=delete&bno="+bno+"&writer=<%=loginId%>";
    	 alert("삭제되었습니다");
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
		<div class="slider_div">
			<div class="slider">
					<div class="img_div">
						<img class="topimg" src="https://sell.smartstore.naver.com/images/dashboard/top-banner-left/20230405-top-banner-left5-pc.png"/>
					</div>
					<div class="img_div">
						<img class="topimg" src="https://sell.smartstore.naver.com/images/dashboard/top-banner-left/top-banner-left1-pc.png"/>
					</div>
					<div class="img_div">
						<img class="topimg" src="https://sell.smartstore.naver.com/images/dashboard/top-banner-left/top-banner-left2-pc.png"/>
				</div>
			</div>
		</div>
  <!-- 게시판 컨텐츠 영역 -->
  <div class="content">
    <!-- 글쓰기 버튼 -->
    <button id="writeBtn">글쓰기</button>
    <table id="boardTable">
      <thead>
        <tr>
          <th>번호</th>
          <th>제목</th>
          <th>작성자</th>
          <th>작성일</th>
          <th>조회수</th>
          <th>좋아요</th>
          <th>싫어요</th>
          <th>삭제</th> 
        </tr>
      </thead>
      <tbody id="searchResult">
		<% for (BoardDto dto : listBoard) { %>
		  <tr>
		    <td class="bno list"><%= dto.getBno() %></td>
		    <td class="list"><%= dto.getTitle() %></td>
		    <td class="writer list"><%= dto.getWriter() %></td>
		    <td class="list"><%= dto.getWritedate() %></td>
		    <td class="list"><%= dto.getViews() %></td>
		    <td class="list"><%= dto.getLikes() %></td>
		    <td class="list"><%= dto.getDislikes() %></td>
		    <td>
		      <% if(mdto != null && ((mdto.getNickname() != null && dto.getWriter().equals(mdto.getNickname())) || (loginId != null && loginId.equals("admin")))) { %>
		        <% if (loginId != null) { %>
		          <button class="deleteBtn">삭제</button>
		        <% } else { %>
		          <button style="display:none" class="deleteBtn">삭제</button>
		        <% } %>
		      <% } %>
		    </td>
		  </tr>
		<% } %>
		</tbody>
    </table>
    
    <div class="searchBox">
      <input type="text" id="searchText" placeholder="검색어를 입력하세요">
      <button id="searchBtn">검색</button>
    </div>
  </div>
</body>
</html>
