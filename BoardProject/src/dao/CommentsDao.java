package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.CommentsDto;

public class CommentsDao {
	public void comments(int boardbno, String comments, String writer) {
		Connection conn = Jdbc.connect();
		 String sql = "INSERT INTO reply (boardbno, bno, comments, writer) VALUES (?, comment_seq.NEXTVAL, ?, ?)";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,boardbno);
			pstmt.setString(2,comments);
			pstmt.setString(3,writer);
			pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public ArrayList<CommentsDto> getAllComment(int boardbno) {
        ArrayList<CommentsDto> list1 = new ArrayList<>();
        Connection conn = Jdbc.connect();
        String sql = "SELECT * FROM reply WHERE boardbno=?";
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, boardbno);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int boardbno2 = rs.getInt("boardbno");
                int bno = rs.getInt("bno");
                String comments = rs.getString("comments");
                String writer = rs.getString("writer");
                String commentdate = rs.getString("commentdate");
                CommentsDto commentsDto = new CommentsDto(boardbno2, bno, comments, writer, commentdate);
                list1.add(commentsDto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list1;
    }
}
