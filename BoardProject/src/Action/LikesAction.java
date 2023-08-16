package Action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDao;

public class LikesAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int bno = Integer.parseInt(request.getParameter("bno"));

        BoardDao boardDao = new BoardDao();
        boardDao.hitcount(bno);

        // 좋아요 증가 후에는 다시 게시물 상세 페이지로 이동하도록 하여 최신 정보를 보여줍니다.
        response.sendRedirect("Controller?command=board_detail_list&bno=" + bno);
    }
}