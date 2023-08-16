package Action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDao;
import dao.CommentsDao;
import dao.MemberDao;
import dto.BoardDto;
import dto.CommentsDto;
import dto.MemberDto;

public class BoardListDetailAction implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int bno = Integer.parseInt(request.getParameter("bno"));
		String id = (String)request.getSession().getAttribute("id");
		MemberDao mDao = new MemberDao();
		MemberDto mdto = null;
		BoardDao boardList = new BoardDao();
		CommentsDao cDao = new CommentsDao();
		
		BoardDto boardDto=null;
		ArrayList<CommentsDto> listBoard = null;
		try {
			listBoard = cDao.getAllComment(bno);
			boardDto = boardList.getBoardDto(bno);
			mdto = mDao.getMemberDto(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("listBoard", listBoard);
		request.setAttribute("bno",bno);
		request.setAttribute("boardDto",boardDto);
		request.setAttribute("mdto",mdto);
		RequestDispatcher rd = request.getRequestDispatcher("BoardListDetail.jsp");
		rd.forward(request, response);
	}

}
