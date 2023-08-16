package Action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDao;
import dao.MemberDao;
import dto.BoardDto;
import dto.MemberDto;

public class MyPageAction implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = (String)request.getSession().getAttribute("id");
		MemberDao mDao = new MemberDao();
		MemberDto mdto = null;
		BoardDao bDao = new BoardDao();
		ArrayList<BoardDto> MyBoard = null;
		try {
			MyBoard = bDao.getMyBoardList(id);
			mdto = mDao.getMemberDto(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
			request.setAttribute("MyBoard",MyBoard);
			request.setAttribute("mdto",mdto);
			RequestDispatcher rd = request.getRequestDispatcher("MyPage.jsp");
			rd.forward(request, response);
	}

}
