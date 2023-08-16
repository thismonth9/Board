package Action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CommentsDao;
import dto.CommentsDto;

public class CommentsAction implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
	    int boardbno = Integer.parseInt(request.getParameter("boardbno"));
        String writer = (String) request.getSession().getAttribute("id");
        String comment = request.getParameter("commentInput");

        CommentsDao cDao = new CommentsDao();
		CommentsDto cDto = null;
		cDao.comments(boardbno,comment,writer);
        
        response.sendRedirect("Controller?command=board_detail_list&bno=" + boardbno);
	}
}
