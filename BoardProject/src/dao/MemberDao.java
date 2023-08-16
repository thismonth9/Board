package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.MemberDto;

public class MemberDao { //로그인
	public boolean Log(String id, String pw) {
		Connection conn = Jdbc.connect();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT count(*) FROM member WHERE id = ? AND pw = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			pstmt.executeUpdate();
			rs = pstmt.executeQuery();
			while(rs.next()) { //다음 row가 있으면 손가락 이동, true 리턴.
				int cnt = rs.getInt(1);
				if(cnt == 1) {
					return true;
				} else {
					return false;
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null)
				rs.close();
				if(pstmt!=null)
				pstmt.close();
				if(conn!=null)
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
		public boolean overlab(String id) { // 중복검사
			Connection conn = Jdbc.connect();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String test = "SELECT count(*) FROM member WHERE id= ?";
			int cnt = 0;
			boolean b = false;
			try {
				pstmt = conn.prepareStatement(test);
				pstmt.setString(1,id);
				pstmt.executeUpdate();
				rs = pstmt.executeQuery();
				cnt = 0;
				while(rs.next()) {
					cnt= rs.getInt("count(*)");
					if(cnt==0) {
						b=true;
					} 
				} 
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				
				try {
					pstmt.close();
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return b;
		}
		//중복확인 후 아이디 생성 메서드
		public void member(String id, String pw, String name, String nickname) { //회원가입
			Connection conn = Jdbc.connect();
			PreparedStatement pstmt = null;
			String sql = "INSERT INTO member(id,pw,name,nickname)"
					+" VALUES(?,?,?,?)";
				try {	
					if(overlab(id)) {
					} else {
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1,id);
						pstmt.setString(2,pw);
						pstmt.setString(3,name);
						pstmt.setString(4,nickname);
						pstmt.executeUpdate();
					}
				}
			 catch(SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if(pstmt!=null)
					 pstmt.close();
					if(conn!=null)
					 conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		public MemberDto getMemberDto(String id) {
			Connection conn = Jdbc.connect();		
			String sql = "SELECT * FROM member WHERE id=?";
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			MemberDto dto = null;
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				 rs = pstmt.executeQuery();
				if(rs.next()) {
					String id2 = rs.getString("id");
					String pw = rs.getString("pw");
					String name = rs.getString("name");
					String nickname = rs.getString("nickname");
					dto = new MemberDto(id2, pw, name, nickname);
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
			return dto;
		}
	}

