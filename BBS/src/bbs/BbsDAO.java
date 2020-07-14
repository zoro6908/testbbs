package bbs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BbsDAO {
	// dao : �����ͺ��̽� ���� ��ü�� ���ڷμ�
	// ���������� db���� ȸ������ �ҷ����ų� db�� ȸ������ ������
	private Connection conn; // connection:db�������ϰ� ���ִ� ��ü
	// private PreparedStatement pstmt; ������ ���뿡 ������ �Ͼ� ���� �ʵ��� �Լ� �������� ����
	private ResultSet rs;

	// mysql�� ������ �ִ� �κ�
	public BbsDAO() { // ������ ����ɶ����� �ڵ����� db������ �̷�� �� �� �ֵ�����
		try {
			String dbURL = "jdbc:mysql://localhost:3306/testdb?serverTimezone=UTC"; // localhost:3306 ��Ʈ�� ��ǻ�ͼ�ġ�� mysql�ּ�
			String dbID = "root";
			String dbPassword = "zoro123";
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
		} catch (Exception e) {
			e.printStackTrace(); // ������ �������� ���
		}
	}

	// ������ �ð��� �������� �Լ�
	public String getDate() {
		String SQL = "SELECT NOW()"; // ������ �ð��� �������� SQL��
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL); // �����غ� �ܰ�� �غ�
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ""; // �����ͺ��̽� ����
	}

	// bbsID �Խñ� ��ȣ �������� �Լ�
	public int getNext() {
		String SQL = "SELECT bbsID FROM BBS ORDER BY bbsID DESC"; // ������ �ð��� �������� SQL��
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL); // �����غ� �ܰ�� �غ�
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1) + 1;
			}
			return 1; // ���簡 ù��° �Խù��� ���
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // �����ͺ��̽� ����
	}

	// ������ ���� �ۼ��ϴ� �Լ�
	public int write(String bbsTitle, String userID, String bbsContent) {
		String SQL = "INSERT INTO BBS VLAUES (?, ?, ?, ?, ?, ?)"; // ������ �ð��� �������� SQL��
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL); // �����غ� �ܰ�� �غ�
			pstmt.setInt(1, getNext());     // bbsID
			pstmt.setString(2, bbsTitle);   // bbsTttle
			pstmt.setString(3, userID);     // bbsTttle
			pstmt.setString(4, getDate());  // bbsDate
			pstmt.setString(5, bbsContent); // bbsContent
			pstmt.setInt(6, 1);            // bbsAvailabe �Է´ܰ��̹Ƿ� ������ 1
			
			return pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // Insert ����
	}
}
