package Action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CommentsDao;
import dto.CommentsDto;

public class CommentsListAction implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int boardbno = Integer.parseInt(request.getParameter("bno"));
		CommentsDao cDao = new CommentsDao();
		ArrayList<CommentsDto> listBoard = null;
		try {
			listBoard = cDao.getAllComment(boardbno);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("listBoard",listBoard);
        response.sendRedirect("Controller?command=board_detail_list&bno=" + boardbno);
	}
}
