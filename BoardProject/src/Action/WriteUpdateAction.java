package Action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDao;
import dao.MemberDao;
import dto.BoardDto;
import dto.MemberDto;

public class WriteUpdateAction implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int bno = Integer.parseInt(request.getParameter("bno"));
		BoardDao boardwrite = new BoardDao();
	    BoardDto dto = null;
	    try {
	        dto = boardwrite.getBoardDto(bno);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    request.setAttribute("dto", dto);
		RequestDispatcher rd = request.getRequestDispatcher("BoardWriteUpdate.jsp");
		rd.forward(request, response);
	}
}
