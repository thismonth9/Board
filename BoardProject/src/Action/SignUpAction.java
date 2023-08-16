package Action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDao;

public class SignUpAction implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name =  request.getParameter("name");
		String nickname = request.getParameter("nickname");
		
		MemberDao signup = new MemberDao();
		boolean signupCheck = signup.overlab(id);
		request.setAttribute("id",id);
		request.setAttribute("signupCheck",signupCheck);
		if(signupCheck) {
			signup.member(id,pw,name,nickname);
		}
		RequestDispatcher rd = request.getRequestDispatcher("SignUp.jsp");
		rd.forward(request, response);
	}
}
