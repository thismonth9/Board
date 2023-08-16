package Action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDao;
import dto.MemberDto;

public class LoginAction implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		String id= request.getParameter("username");
		String pw= request.getParameter("password");
		String nickname="";
		String name = "";
		
		MemberDao memberDao = new MemberDao();
		MemberDto dto = new MemberDto(id,pw,name,nickname);
		boolean Log = memberDao.Log(id, pw);
		
		if(Log) {
			session.setAttribute("id",dto.getId());
			session.setAttribute("pw",dto.getPw());
			response.sendRedirect("Controller?command=board_list");
		}else {
			if(id==null) {
				RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
				rd.forward(request, response);
			}else {
				request.setAttribute("LoginFail", "<script>alert('로그인에 실패하셨습니다. 다시 로그인 해주세요');</script>");
				RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
				rd.forward(request, response);
			}
		}
		
	}
}
