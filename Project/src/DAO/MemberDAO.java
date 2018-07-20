package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import static db.JdbcUtil.*;
import javax.servlet.http.HttpServletRequest;

import bean.MemberBean;

public class MemberDAO {

	private static MemberDAO MemberDAO;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	public static MemberDAO getInstense() {
		if(MemberDAO == null) {
			MemberDAO = new MemberDAO();
		}
		return MemberDAO;
	}
	public void setConnection(Connection con) {
		this.con = con;
	}
	public int insertMember(MemberBean memberBean) {
		int num = 0;
		String sql1 = "SELECT MAX(MEMBERNUM) FROM MEMBER";
		String sql = "INSERT INTO MEMBER VALUES (?,?,?,?,?,?,?,?)";
		int insertResult = 0;
		try {
			pstmt = con.prepareStatement(sql1);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				num = rs.getInt(1)+1;
			}else {
				num=1;
			}
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, memberBean.getNAME());
			pstmt.setInt(3, memberBean.getAGE());
			pstmt.setString(4, memberBean.getEMAIL());
			pstmt.setString(5, memberBean.getID());
			pstmt.setString(6, memberBean.getPASS());
			pstmt.setInt(7, memberBean.getPHONE());
			pstmt.setString(8, memberBean.getGENDER());
			insertResult = pstmt.executeUpdate();
		}catch(Exception e){
			System.out.println("joinMember 오류 !! : "+e);
		}finally {
			close(pstmt);
		}
		return insertResult;
	}
	public String loginMember(MemberBean memberBean) {
		String loginId=null;
		String sql = "SELECT * FROM MEMBER WHERE ID=? AND PASS=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberBean.getID());
			pstmt.setString(2, memberBean.getPASS());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				loginId = rs.getString("ID");
			}
		}catch(Exception e) {
			System.out.println("loginMember 오류");
		}finally {
			close(pstmt);
			}
		return loginId;
	}
	
	
	
}
