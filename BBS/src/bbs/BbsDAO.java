package bbs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BbsDAO {
	// dao : 데이터베이스 접근 객체의 약자로서
	// 실질적으로 db에서 회원정보 불러오거나 db에 회원정보 넣을때
	private Connection conn; // connection:db에접근하게 해주는 객체
	// private PreparedStatement pstmt; 각각의 내용에 마찰이 일어 나지 않도록 함수 안쪽으로 정의
	private ResultSet rs;

	// mysql에 접속해 주는 부분
	public BbsDAO() { // 생성자 실행될때마다 자동으로 db연결이 이루어 질 수 있도록함
		try {
			String dbURL = "jdbc:mysql://localhost:3306/testdb?serverTimezone=UTC"; // localhost:3306 포트는 컴퓨터설치된 mysql주소
			String dbID = "root";
			String dbPassword = "zoro123";
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
		} catch (Exception e) {
			e.printStackTrace(); // 오류가 무엇인지 출력
		}
	}

	// 현재의 시간을 가져오는 함수
	public String getDate() {
		String SQL = "SELECT NOW()"; // 현재의 시간을 가져오는 SQL문
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL); // 실행준비 단계로 준비
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ""; // 데이터베이스 오류
	}

	// bbsID 게시글 번호 가져오는 함수
	public int getNext() {
		String SQL = "SELECT bbsID FROM BBS ORDER BY bbsID DESC"; // 현재의 시간을 가져오는 SQL문
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL); // 실행준비 단계로 준비
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1) + 1;
			}
			return 1; // 현재가 첫번째 게시물일 경우
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // 데이터베이스 오류
	}

	// 실제로 글을 작성하는 함수
	public int write(String bbsTitle, String userID, String bbsContent) {
		String SQL = "INSERT INTO BBS VLAUES (?, ?, ?, ?, ?, ?)"; // 현재의 시간을 가져오는 SQL문
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL); // 실행준비 단계로 준비
			pstmt.setInt(1, getNext());     // bbsID
			pstmt.setString(2, bbsTitle);   // bbsTttle
			pstmt.setString(3, userID);     // bbsTttle
			pstmt.setString(4, getDate());  // bbsDate
			pstmt.setString(5, bbsContent); // bbsContent
			pstmt.setInt(6, 1);            // bbsAvailabe 입력단계이므로 무조건 1
			
			return pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // Insert 오류
	}
}
