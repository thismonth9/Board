package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dao.BoardDao;
import dto.BoardDto;

@WebServlet("/SearchMypage")
public class SearchMyPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
	    String searchTerm = request.getParameter("searchTerm");

	    // 실제 검색 로직을 구현하여 검색 결과를 가져옵니다.
	    BoardDao boardDao = new BoardDao();
	    ArrayList<BoardDto> searchMyResult = boardDao.getAjaxMyBoardList(id,searchTerm);
	    

	    // 검색 결과를 JSON 형식으로 변환하여 응답합니다.
	    JSONArray jsonArray = new JSONArray();
	    for (BoardDto dto : searchMyResult) {
	        JSONObject jsonObject = new JSONObject();
	        jsonObject.put("bno", dto.getBno());
	        jsonObject.put("title", dto.getTitle());
	        jsonObject.put("writer", dto.getWriter());
	        jsonObject.put("writedate", dto.getWritedate());
	        jsonObject.put("views", dto.getViews());
	        jsonObject.put("likes", dto.getLikes());
	        jsonObject.put("dislikes", dto.getDislikes());
	        jsonArray.add(jsonObject);
	    }

	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    PrintWriter out = response.getWriter();
	    out.print(jsonArray.toString());
	    out.flush();
	}
}
