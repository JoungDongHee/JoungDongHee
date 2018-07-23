package DAO;

import static db.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import bean.BoardBean;
import bean.MemberBean;
public class BoardDAO {
	private static BoardDAO BoardDAO;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	public static BoardDAO getInstense() {
		if(BoardDAO == null) {
			BoardDAO = new BoardDAO();
		}
		return BoardDAO;
	}
	public void setConnection(Connection con) {
		this.con = con;
	}
	//개시물의 현제 시간 
	public String getDate(){
		String sql1 = "select to_char(sysdate,'yyyy-mm-dd-hh24-mi') from dual;";
		try {
			pstmt = con.prepareStatement(sql1);
			rs = pstmt.executeQuery();
			if(rs.next())
			{
				return rs.getString(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return "";
	}
	
	//게시물 번호 
	public int getListCount() {
		int listCount = 0;
		String sql = "SELECT COUNT(*) FROM BOARD1";	
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch(Exception e) {
			System.out.println("조회수 에러 발생  : "+e);
		} finally {
			close(rs);
			close(pstmt);
		}
		return listCount;
	}
	
	
	
	public int Write(BoardBean board)
	{		 
		int a = 0;
		int num =0;
		String sql1 = "SELECT MAX(BOARD_NUM) FROM BOARD";
		String sql2 = "INSERT INTO BOARD1 VALUES(?,?,?,?,?,?)";
		try {
			 pstmt = con.prepareStatement(sql1);
			 rs = pstmt.executeQuery();
			 if(rs.next()) {
				 num = rs.getInt(1)+1;
			 }else {
				 num = 1;
			 }
			pstmt = con.prepareStatement(sql2);
			pstmt.setInt(1, num);
			pstmt.setString(2,"userid");
			pstmt.setString(3,board.getBOARD_SUBJECT());
			pstmt.setString(4, board.getBOARD_CONTENT());
			pstmt.setInt(5, 2);
			pstmt.setString(6, null);
			a =  pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return a;	
	}
	
	
	
	public ArrayList<BoardBean> selectBoardList() {
		String sql = "SELECT * FROM BOARD1";
		System.out.println("BoardList 메소드 호출됨");
		ArrayList<BoardBean> boardList = new ArrayList<BoardBean>();
	    //DB로부터 조회한 결과를 가져와서 처리하기 위한 ResultSet 객체 사용
		BoardBean boardBean = null;  
	    try{
	    	 pstmt = con.prepareStatement(sql);
	    	 rs = pstmt.executeQuery();
	    	if(rs.next()){
	    		do {
	    		boardBean = new BoardBean();
	    		boardBean.setBOARD_NUM(rs.getInt(1));
	    		boardBean.setBOARD_SUBJECT(rs.getString(3));
	    		boardBean.setBOARD_CONTENT(rs.getString(4));
	    		boardBean.setBOARD_READCOUNT(rs.getInt(5));
	    		boardBean.setBOARD_DATE(rs.getInt(6));
		    	boardList.add(boardBean);
	    	}while(rs.next());
	    }
	    //DB 쿼리문 전송을 위한 준비
	    } catch(Exception e){
	    	System.out.println("BoardList 오류!!" + e);
	    } finally{
	    	close(rs);
	    	close(pstmt);
	    }
	    return boardList;
	
	}
	
	
	public BoardBean boardView(int datailNum) {
		String sql1 = "SELECT * FROM BOARD1 WHERE BOARD_NUM=?";
		String sql2 = "UPDATE BOARD1 SET BOARD_READCOUNT=BOARD_READCOUNT+1 WHERE BOARD_NUM=?";
	    BoardBean boardInfo = null;
	    try {
	       pstmt = con.prepareStatement(sql2);
	       pstmt.setInt(1, datailNum);
	       pstmt.executeUpdate();
	       
	       pstmt = con.prepareStatement(sql1);
	       pstmt.setInt(1, datailNum);
	       rs = pstmt.executeQuery();
	       if(rs.next()) {
	       boardInfo = new BoardBean();
	       boardInfo.setBOARD_NUM(rs.getInt(1));
	       boardInfo.setBOARD_SUBJECT((rs.getString("BOARD_SUBJECT")));
	       boardInfo.setBOARD_SUBJECT((rs.getString("BOARD_NAME")));
	       boardInfo.setBOARD_CONTENT((rs.getString("BOARD_CONTENT")));
		    System.out.println(boardInfo.getBOARD_SUBJECT());
	       	}
	      } catch (Exception e) {
	         System.out.println("borderInfo 오류!! : "+e);
	      } finally {
	    	  
	         close(pstmt);
	      }
	      return boardInfo;
	}

}
