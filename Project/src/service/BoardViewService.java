package service;

import static db.JdbcUtil.*;

import java.sql.Connection;

import bean.BoardBean;
import DAO.BoardDAO;

public class BoardViewService {

	public BoardBean boardInfo(int num) {
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstense();
		boardDAO.setConnection(con);
		
		BoardBean boardResult = boardDAO.boardInfo(num);
		
		
		/*BoardBean article = null;
		//조회수 올리기
		int updateCount = boardDAO.updateCount(num);
		
		if(updateCount>0) {
			commit(con);
		} else {
			rollback(con);
		}
		//선택된 게시글 정보 가져오기
		article = boardDAO.boardInfo(num);*/
		
		
		commit(con);
		close(con);
		return boardResult;
	}

}
