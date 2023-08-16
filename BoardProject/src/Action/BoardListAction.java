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

public class BoardListAction implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pageNum = 0;
		int count = 0;
		try{ 
			pageNum = Integer.parseInt(request.getParameter("page"));
		} catch (Exception e) {
			pageNum=1;
		}
		String id = (String)request.getSession().getAttribute("id");
		BoardDao bDao = new BoardDao();
		ArrayList<BoardDto> listBoard = null;
		MemberDao mDao = new MemberDao();
		MemberDto mdto = null;
	try {
		count = bDao.count();
		mdto = mDao.getMemberDto(id);
		listBoard = bDao.getAllBoardList(pageNum);
	} catch (Exception e) {
		e.printStackTrace();
	}
		request.setAttribute("listBoard",listBoard);
		request.setAttribute("count",count);
		request.setAttribute("mdto",mdto);
		RequestDispatcher rd = request.getRequestDispatcher("BoardList.jsp");
		rd.forward(request, response);
	}

}
