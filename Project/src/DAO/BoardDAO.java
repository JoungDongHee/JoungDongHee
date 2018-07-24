package DAO;

import static db.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
	
	//개시물의 작성 시간 
	public String getDate(){
		String sql1 = "select to_char(sysdate,'yyyy-mm-dd-hh24-mi') from dual";
		String DA = null;
		try {
			pstmt = con.prepareStatement(sql1);
			rs = pstmt.executeQuery();
			if(rs.next())
			{
				 DA = rs.getString(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		sql1 = DA;
		return DA;
	}
	
	
	
	//게시물 번호 
	public int getListCount() {
		int listCount = 0;
		String sql = "SELECT COUNT(*) FROM BOARD";	
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
	
	
	//글쓰기 실행 쿼리문
	public int Write(BoardBean board)
	{		 
		int a = 0;
		int num =0;
		String sql1 = "SELECT MAX(BOARD_NUM) FROM BOARD";
		String sql2 = "INSERT INTO BOARD VALUES(?,?,?,?,?,?)";
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
			pstmt.setString(2,"userID");
			pstmt.setString(3,board.getBOARD_SUBJECT());
			pstmt.setString(4, board.getBOARD_CONTENT());
			pstmt.setInt(5, 0);
			pstmt.setString(6,getDate());
			a =  pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return a;	
	}
	
	
	
	public List<BoardBean> getBoardList(int page, int limit) {
		String sql = "SELECT * FROM (SELECT ROWNUM RN2, V1. * FROM " + "(SELECT * FROM BOARD ORDER BY" + "BOARD_RE_REF DESC, BOARD_RE_SEQ ASC) V1) V2" + "WHERE V2.RN2 BETWEEN ? AND?";
		int startrow = (page-1)*limit +1;
		int endrow = page*limit;
		
		//boolean loginResult = false;
		List<BoardBean> boardList = new ArrayList<BoardBean>();
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MemberBean bean = new MemberBean();
				BoardBean board = new BoardBean();
				board.setBOARD_NUM(rs.getInt("BOARD_NUM"));
				bean.setEMAIL(rs.getString(2));
				board.setBOARD_SUBJECT(rs.getString(3));
				board.setBOARD_CONTENT(rs.getString(4));
				board.setBOARD_READCOUNT(rs.getInt(5));
				board.setBOARD_DATE(rs.getInt(6));
				boardList.add(board);
			}
		} catch (Exception e) {
			System.out.println("getBoardList 오류!! : "+e);
		} finally {
			close(rs);
			close(pstmt);
		}
		return boardList;
	}

	
	public BoardBean boardInfo(int num) {
		String sql1 = "SELECT * FROM BOARD WHERE BOARD_NUM=?";
		String sql2 = "UPDATE BOARD SET BOARD_READCOUNT=BOARD_READCOUNT+1 WHERE BOARD_NUM=?";
		BoardBean boardInfo = null;
		try {
			pstmt = con.prepareStatement(sql2);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			
			pstmt = con.prepareStatement(sql1);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				MemberBean bean = new MemberBean();
				boardInfo = new BoardBean();
				boardInfo.setBOARD_NUM(rs.getInt("BOARD_NUM"));
				boardInfo.setBOARD_NUM(rs.getInt("BOARD_NUM"));
				bean.setEMAIL(rs.getString(2));
				boardInfo.setBOARD_SUBJECT(rs.getString(3));
				boardInfo.setBOARD_CONTENT(rs.getString(4));
				boardInfo.setBOARD_READCOUNT(rs.getInt(5));
				boardInfo.setBOARD_DATE(rs.getInt(6));
			}
		} catch (Exception e) {
			System.out.println("memberInfo 오류!! : "+e);
		} finally {
			close(rs);
			close(pstmt);
		}
		return boardInfo;
	}
	
	public BoardBean boardView(int datailNum) {
		String sql1 = "SELECT * FROM BOARD WHERE BOARD_NUM=?";
		String sql2 = "UPDATE BOARD SET BOARD_READCOUNT=BOARD_READCOUNT+1 WHERE BOARD_NUM=?";
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
	
	public int modArticle(BoardBean board) {
		int modResult = 0;
		String sql = "UPDATE BOARD SET  BOARD_SUBJECT=?, BOARD_CONTENT=? WHERE BOARD_NUM=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(2, board.getBOARD_SUBJECT());
			pstmt.setString(3, board.getBOARD_CONTENT());
			pstmt.setInt(4, board.getBOARD_NUM());
			
			modResult = pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("글 수정 오류!! : "+e);
		} finally {
			close(pstmt);
		}
		return modResult;
	}
	
	public int deleteArticle(int boardnum) {
		String sql = "DELETE FROM BOARD WHERE BOARD_NUM=?";
		int deleteResult = 0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, boardnum);
			deleteResult = pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("DeletBoard 오류!! : "+e);
		}finally {
			close(pstmt);
		}
		return deleteResult;
	}

	public BoardBean A(int boardnum) {
		String sql = "SELECT * FROM BOARD WHERE BOARD_NUM=?";
		BoardBean board = new BoardBean();
		MemberBean bean = new MemberBean();
		try {
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, boardnum);
			rs = pstmt.executeQuery();
			rs.next();
			board.setBOARD_NUM(rs.getInt("BOARD_NUM"));
			bean.setEMAIL(rs.getString(1));
			board.setBOARD_SUBJECT(rs.getString(2));;
			board.setBOARD_CONTENT(rs.getString(3));
			board.setBOARD_READCOUNT(rs.getInt(4));
			board.setBOARD_DATE(rs.getInt(5));
		} catch (Exception e) {
			System.out.println("getBoardList 오류!! : "+e);
		} finally {
			close(rs);
			close(pstmt);
		}
		return board;
	}
	
	
	public int getListCount1() {
		int listCount = 0;
		
		String sql = "SELECT COUNT(*) FROM BOARD";
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
		}catch(Exception e) {
			System.out.println("글 갯수 오류 : " + e);
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return listCount;
	}
	
	public ArrayList<BoardBean> selectBoardList() {
		String sql = "SELECT * FROM BOARD";
		System.out.println("BoardList 메소드 호출됨");
		ArrayList<BoardBean> boardList = new ArrayList<BoardBean>();
		
	 
	    //DB로부터 조회한 결과를 가져와서 처리하기 위한 ResultSet 객체 사용
		BoardBean boardBean = null;
	      
	    try{
	    	 pstmt = con.prepareStatement(sql);
	    	 rs = pstmt.executeQuery();
	    	if(rs.next()){
	    		do {
	    		MemberBean memberbean = new MemberBean();
	    		boardBean = new BoardBean();
	    		boardBean.setBOARD_NUM(rs.getInt("BOARD_NUM"));
	    		memberbean.setEMAIL(rs.getString(2));
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
	
	
//	public int insertReply(BoardBean boardBean) {
//		/* 1. BOARD_NUM을 위한 MAX(BOARD_NUM)을 얻어오기.
//		 * 2. BOARD_RE_SEQ를 재조정
//		 * 3. 답글등록
//		 */
//		MemberBean memberbean = new MemberBean();
//		String sql1 = "SELECT MAX(BOARD_NUM) FROM BOARD";
//		//먼저단 답글이 위로가도록 나중답글 SEQ+1
//		String sql2 = "UPDATE BOARD SET BOARD_RE_SEQ=BOARD_RE_SEQ+1"
//					+ "WHERE BOARD_RE_REF=? AND BOARD_RE_SEQ>?";
//		String sql3 = "INSERT INTO BOARD VALUES(?,?,?,?,?,?,?,?,?,?,SYSDATE)";
//		
//		int num = 0;
//		int insertCount = 0;
//		int re_ref = boardBean.getRe_ref();
//		int re_lev = boardBean.getRe_lev();
//		int re_seq = boardBean.getRe_seq();
//		
//		try {
//			pstmt = con.prepareStatement(sql1);
//			rs = pstmt.executeQuery();
//			if(rs.next()) {
//				num = rs.getInt(1)+1;
//			}else {
//				num=1;
//			}
//			pstmt = con.prepareStatement(sql2);
//			pstmt.setInt(1, re_ref);
//			pstmt.setInt(2, re_seq);
//			int updateCount = pstmt.executeUpdate();
//			if(updateCount>0) {
//				commit(con);
//			}
//			
//			re_seq = re_seq + 1;
//			re_lev = re_lev + 1;
//			
//			pstmt = con.prepareStatement(sql3);
//			pstmt.setInt(1, num);
//			pstmt.setString(2, memberbean.getEMAIL());
//			pstmt.setString(3, boardBean.getBOARD_SUBJECT());
//			pstmt.setString(4, boardBean.getBOARD_CONTENT());
//			pstmt.setString(5, "");
//			insertCount = pstmt.executeUpdate();
//		}catch(Exception e) {
//			System.out.println("답글작성 오류 : " + e);
//		}finally{
//			close(rs);
//			close(pstmt);
//		}
//		
//		return insertCount;
//	}
//	

}
