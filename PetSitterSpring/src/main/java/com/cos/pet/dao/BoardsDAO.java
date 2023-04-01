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
			
			String sql = "insert into boards(boardId,boardCategory,boardContent,boardCreatedDate,boardTitle, boardWriter) values(BOARDS_SEQ_GENERATOR.NEXTVAL,?,?,SYSDATE,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			try(conn;pstmt) {	//예외발생시 자동 클로즈 해주는 기법
				pstmt.setString(1, b.getBoardCategory());
				pstmt.setString(2, b.getBoardContent());
				pstmt.setString(3, b.getBoardTitle());
				pstmt.setString(4, b.getBoardWriter());
				pstmt.executeUpdate();
			}
		}
		
		public List<Boards> getAll() throws Exception {
			Connection conn = open();
			List<Boards> boardsList = new ArrayList<>();
			
			String sql = "select boardTitle, boardWriter, TO_CHAR(boardCreatedDate, 'YYYY-MM-DD') from boards";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			try(conn; pstmt; rs) {
				while(rs.next()) {	//모든 글 등록 가능
					Boards b = new Boards();
					b.setBoardTitle(rs.getString("boardTitle"));
					b.setBoardWriter(rs.getString("boardWriter"));
					b.setBoardCreatedDate(rs.getString("boardCreatedDate"));
					
					boardsList.add(b);
				}
				return boardsList;
			}
		}
		
		public Boards getBoards(int boardId) throws SQLException {
			Connection conn = open();
			
			Boards b = new Boards();		
			String sql = "select boardId, boardTitle, boardWriter, TO_CHAR(boardCreatedDate, 'YYYY-MM-DD'), content from boards where boardId=?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardId);
			ResultSet rs = pstmt.executeQuery();
			
			rs.next();	//하나의 뉴스만 보여주어야 하기 때문에 try 밖에 배치
			
			try(conn;pstmt;rs) {	//(null인 경우 예외 발생 시킴) - 자동으로 클로즈하기
				b.setBoardId(rs.getInt("boardId"));
				b.setBoardTitle(rs.getString("boardTitle"));
				b.setBoardWriter(rs.getString("boardWriter"));
				b.setBoardCreatedDate(rs.getString("boardCreatedDate"));
				b.setBoardContent(rs.getString("boardContent"));
				pstmt.executeQuery();
				return b;
			}
		}
		
		public void delBoards(int boardId) throws SQLException {
			Connection conn = open();
			
			String sql = "delete from boards where boardId=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			try(conn;pstmt) {
				pstmt.setInt(1, boardId);
				//삭제된 뉴스 기사가 없을 경우
				if(pstmt.executeUpdate() == 0) {
					throw new SQLException("DB에러");
				}
			}
		}
		
}
