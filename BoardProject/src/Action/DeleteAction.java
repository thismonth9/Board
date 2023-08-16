package Action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDao;

public class DeleteAction implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int bno = Integer.parseInt(request.getParameter("bno"));
		String writer = request.getParameter("writer");
		BoardDao deleteDao = new BoardDao();
		deleteDao.delete(bno, writer);
		response.sendRedirect("Controller?command=board_list");
	}
}
