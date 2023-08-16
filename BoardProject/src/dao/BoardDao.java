package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.BoardDto;

public class BoardDao {
	//home
	public ArrayList<BoardDto> getAllBoardList(int pageNum) {
	    ArrayList<BoardDto> list1 = new ArrayList<BoardDto>();
	    Connection conn = Jdbc.connect();
	    String sql = "SELECT b2.*"
				+ " FROM (SELECT rownum rnum, b1.*"
				+ " FROM (SELECT b.*, m.nickname FROM board b, member m WHERE b.writer = m.id AND visibility='공개' ORDER BY bno DESC) b1) b2"
				+ " WHERE b2.rnum>=? AND b2.rnum<=?";
	    int endNum = pageNum*20;
		int startNum = endNum - 19;
		
	    PreparedStatement pstmt = null;
	    ResultSet rs=null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startNum);
			pstmt.setInt(2, endNum);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int bno = rs.getInt("bno");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String writer = rs.getString("nickname");
				String writedate = rs.getString("writedate");
				int views = rs.getInt("views");
				String image = rs.getString("image");
				int likes = rs.getInt("likes");
				int dislikes = rs.getInt("dislikes");
				String visibility = rs.getString("visibility");
				BoardDto dto = new BoardDto(bno, title, content, writer, writedate, views, image, likes, dislikes, visibility);
				list1.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	    return list1;
	}
	public ArrayList<BoardDto> getAjaxMyBoardList(String id, String searchT) {
		ArrayList<BoardDto> list1 = new ArrayList<BoardDto>();
		
		Connection conn = Jdbc.connect();
		String sql = "SELECT * FROM board b, member m WHERE b.writer = m.id AND writer= ? AND title LIKE ? ORDER BY bno DESC";
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,id);
			pstmt.setString(2,"%"+searchT+"%");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int bno = rs.getInt("bno");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String writer = rs.getString("nickname");
				String writedate = rs.getString("writedate");
				int views = rs.getInt("views");
				String image = rs.getString("image");
				int likes = rs.getInt("likes");
				int dislikes = rs.getInt("dislikes");
				String visibility = rs.getString("visibility");
				//rs.getNString --> NVARCHAR2타입
				//rs.getString --> varchar2 타입
				BoardDto dto = new BoardDto(bno,title,content,writer,writedate,views,image,likes,dislikes,visibility);
				list1.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list1;
	}
	
	public ArrayList<BoardDto> getAjaxBoardList(String searchT) {
		ArrayList<BoardDto> list1 = new ArrayList<BoardDto>();
		
		Connection conn = Jdbc.connect();
		String sql = "SELECT * FROM board b, member m WHERE b.writer = m.id AND visibility='공개' AND title LIKE ? ORDER BY bno DESC";
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,"%"+searchT+"%");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int bno = rs.getInt("bno");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String writer = rs.getString("nickname");
				String writedate = rs.getString("writedate");
				int views = rs.getInt("views");
				String image = rs.getString("image");
				int likes = rs.getInt("likes");
				int dislikes = rs.getInt("dislikes");
				String visibility = rs.getString("visibility");
				//rs.getNString --> NVARCHAR2타입
				//rs.getString --> varchar2 타입
				BoardDto dto = new BoardDto(bno,title,content,writer,writedate,views,image,likes,dislikes,visibility);
				list1.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list1;
	}
	
	//mypage
	public ArrayList<BoardDto> getMyBoardList(String id) throws Exception {
		ArrayList<BoardDto> list1 = new ArrayList<BoardDto>();
		
		Connection conn = Jdbc.connect();
		String sql = "SELECT * FROM board b, member m WHERE b.writer = m.id AND writer= ? ORDER BY bno DESC";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,id);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			int bno = rs.getInt("bno");
			String title = rs.getString("title");
			String content = rs.getString("content");
			String writer = rs.getString("nickname");
			String writedate = rs.getString("writedate");
			int views = rs.getInt("views");
			String image = rs.getString("image");
			int likes = rs.getInt("likes");
			int dislikes = rs.getInt("dislikes");
			String visibility = rs.getString("visibility");
			//rs.getNString --> NVARCHAR2타입
			//rs.getString --> varchar2 타입
			BoardDto dto = new BoardDto(bno,title,content,writer,writedate,views,image,likes,dislikes,visibility);
			list1.add(dto);
		}
		rs.close();
		pstmt.close();
		conn.close();
		return list1;
	}
	//관리자
	public ArrayList<BoardDto> manager() throws Exception {
		ArrayList<BoardDto> list1 = new ArrayList<BoardDto>();
		
		Connection conn = Jdbc.connect();
		String sql = "SELECT * FROM board b, member m WHERE b.writer = m.id ORDER BY bno DESC";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			int bno = rs.getInt("bno");
			String title = rs.getString("title");
			String content = rs.getString("content");
			String writer = rs.getString("nickname");
			String writedate = rs.getString("writedate");
			int views = rs.getInt("views");
			String image = rs.getString("image");
			int likes = rs.getInt("likes");
			int dislikes = rs.getInt("dislikes");
			String visibility = rs.getString("visibility");
			//rs.getNString --> NVARCHAR2타입
			//rs.getString --> varchar2 타입
			BoardDto dto = new BoardDto(bno,title,content,writer,writedate,views,image,likes,dislikes,visibility);
			list1.add(dto);
		}
		rs.close();
		pstmt.close();
		conn.close();
		return list1;
	}
	//detail
	public BoardDto getBoardDto(int bno) throws Exception {
		Connection conn = Jdbc.connect();		
		String sql = "SELECT * FROM board WHERE bno=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, bno);
		ResultSet rs = pstmt.executeQuery();
		BoardDto dto = null;
		if(rs.next()) {
			int bno2 = rs.getInt("bno");
			String title = rs.getString("title");
			String content = rs.getString("content");
			String writer = rs.getString("writer");
			String writedate = rs.getString("writedate");
			int views = rs.getInt("views");
			String image = rs.getString("image");
			int likes = rs.getInt("likes");
			int dislikes = rs.getInt("dislikes");
			String visibility = rs.getString("visibility");
			//rs.getNString --> NVARCHAR2타입
			//rs.getString --> varchar2 타입
			dto = new BoardDto(bno2,title,content,writer,writedate,views,image,likes,dislikes,visibility);
		}
		rs.close();
		pstmt.close();
		conn.close();
		return dto;
	}
	//write
	public void write(String title, String content,String writer,String image,String visibility) {
		Connection conn = Jdbc.connect();
		String sql = "INSERT INTO board(bno,title,content,writer,views,image,likes,dislikes,visibility)"
				+ " VALUES(SEQ_bno.nextval,?,?,?,'0',?,'0','0',?)";
			 PreparedStatement pstmt = null;
		 try {
			 pstmt=conn.prepareStatement(sql);
			 pstmt.setString(1,title);
			 pstmt.setString(2,content);
			 pstmt.setString(3,writer);
			 pstmt.setString(4,image);
			 pstmt.setString(5,visibility);
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
	//delete
	public void delete(int bno, String id) {
		Connection conn = Jdbc.connect();
		String sql = "DELETE FROM board WHERE bno=? AND writer=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,bno);
			pstmt.setString(2,id);
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
	
	//paging
	public int count() throws Exception {
		Connection conn = Jdbc.connect();
		String sql = "SELECT count(*) FROM board WHERE visibility='공개'" ;
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		int cnt = 0;
		if(rs.next()) {
			cnt = rs.getInt("count(*)");
		}
		rs.close();
		pstmt.close();
		conn.close();
		
		return cnt;
	}
	
	public void hitcount(int bno) {
		Connection conn = Jdbc.connect();
		 String sql = "UPDATE board SET likes = likes + 1 WHERE bno = ?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,bno);
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
	public void dishitcount(int bno) {
		Connection conn = Jdbc.connect();
		 String sql = "UPDATE board SET dislikes = dislikes + 1 WHERE bno = ?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,bno);
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
	public void view(int bno) {
		Connection conn = Jdbc.connect();
		 String sql = "UPDATE board SET views = views + 1 WHERE bno = ?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,bno);
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
	//게시물 수정
	public void listupdate(String title, String content,String image, String visibility, int bno) {
		Connection conn = Jdbc.connect();
		String sql = "UPDATE board SET title=?, content=?, image=?, visibility=? WHERE bno=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,title);
			pstmt.setString(2,content);
			pstmt.setString(3,image);
			pstmt.setString(4,visibility);
			pstmt.setInt(5,bno);
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
}




