package com.cos.pet.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cos.pet.model.Boards;

@Component
public class BoardsDAO {
	final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:myoracle";
	
	//DB연결을 가져오는 메서드, DBCP를 사용하는 것이 좋음
		public Connection open() {
			Connection conn = null;
			try {
				Class.forName(JDBC_DRIVER);
				conn = DriverManager.getConnection(JDBC_URL,"petsitter","1234");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return conn;
		}
		
		public void addBoards(Boards b) throws Exception {
			Connection conn = open();
			
			String sql = "insert into boards(board_id,board_category,board_content,board_created_date,board_title, board_writer) values(BOARDS_SEQ_GENERATOR.NEXTVAL,?,?,SYSDATE,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			try(conn;pstmt) {	//예외발생시 자동 클로즈 해주는 기법
				pstmt.setString(1, b.getBoard_category());
				pstmt.setString(2, b.getBoard_content());
				pstmt.setString(3, b.getBoard_title());
				pstmt.setString(4, b.getBoard_writer());
				pstmt.executeUpdate();
			}
		}
		
		public List<Boards> getAll() throws Exception {
			Connection conn = open();
			List<Boards> boardsList = new ArrayList<>();
			
			String sql = "select board_title, board_writer, TO_CHAR(board_created_date, 'YYYY-MM-DD') from boards";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			try(conn;pstmt ;rs) {
				while(rs.next()) {	//모든 글 등록 가능
					Boards b = new Boards();
					b.setBoard_title(rs.getString("board_title"));
					b.setBoard_writer(rs.getString("board_writer"));
					b.setBoard_created_date(rs.getString("board_created_date"));
					
					boardsList.add(b);
				}
				return boardsList;
			}
		}
		
		public Boards getBoards(int board_id) throws SQLException {
			Connection conn = open();
			
			Boards b = new Boards();		
			String sql = "select board_id, board_title, board_writer, TO_CHAR(board_created_date, 'YYYY-MM-DD HH:MI:SS'), board_content from boards where board_id=?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_id);
			ResultSet rs = pstmt.executeQuery();
			
			rs.next();	//하나의 뉴스만 보여주어야 하기 때문에 try 밖에 배치
			
			try(conn;pstmt;rs) {	//(null인 경우 예외 발생 시킴) - 자동으로 클로즈하기
				b.setBoard_id(rs.getInt("board_id"));
				b.setBoard_title(rs.getString("board_title"));
				b.setBoard_writer(rs.getString("board_writer"));
				b.setBoard_created_date(rs.getString("board_created_date"));
				b.setBoard_content(rs.getString("board_content"));
				pstmt.executeQuery();
				return b;
			}
		}
		
		public void delBoards(int board_id) throws SQLException {
			Connection conn = open();
			
			String sql = "delete from boards where board_id=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			try(conn;pstmt) {
				pstmt.setInt(1, board_id);
				//삭제된 뉴스 기사가 없을 경우
				if(pstmt.executeUpdate() == 0) {
					throw new SQLException("DB에러");
				}
			}
		}
		
}
