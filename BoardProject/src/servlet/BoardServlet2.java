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


@WebServlet("/BoardServlet2")
public class BoardServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		
		BoardDao bDao = new BoardDao();
		ArrayList<BoardDto> listBoard = bDao.getAllBoardList(pageNum);
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		JSONArray array = new JSONArray();
		for(BoardDto dto : listBoard) {
			JSONObject obj = new JSONObject();
			obj.put("bno",dto.getBno());
			obj.put("title",dto.getTitle());
			obj.put("writer",dto.getWriter());
			obj.put("writedate",dto.getWritedate());
			obj.put("views",dto.getViews());
			obj.put("likes",dto.getLikes());
			obj.put("dislikes",dto.getDislikes());
			array.add(obj);
		}
		out.println(array);
	}
}
