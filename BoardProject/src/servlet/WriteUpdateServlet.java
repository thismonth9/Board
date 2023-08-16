package servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.BoardDao;
import dao.MemberDao;
import dto.BoardDto;
import dto.MemberDto;
@WebServlet("/WriteUpdateServlet")
public class WriteUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
        String path = request.getRealPath("img");
        File filePath = new File(path);
        if (!filePath.exists()) {
            filePath.mkdirs();
        }
        int size = 10 * 1024 * 1024; // 10MB (파일크기제한)
        MultipartRequest multi = new MultipartRequest(request, path, size, "UTF-8", new DefaultFileRenamePolicy());
        
        String title = multi.getParameter("title");
		String content = multi.getParameter("content");
		String image = multi.getFilesystemName("image");
		String visibility = multi.getParameter("visibility");
		int bno = Integer.parseInt(multi.getParameter("bno"));

	    BoardDao boardwrite = new BoardDao();
	    BoardDto dto = null;
	    boolean success = false;
	    try {
	        dto = boardwrite.getBoardDto(bno);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    if(title!=null) {
	    	boardwrite.listupdate(title, content, image, visibility, bno);
	    	success = true;
	    }
	    
	    request.setAttribute("dto", dto);
	    request.setAttribute("success", success);
	    RequestDispatcher rd = request.getRequestDispatcher("BoardWriteUpdate.jsp");
		rd.forward(request, response);
	}
}
