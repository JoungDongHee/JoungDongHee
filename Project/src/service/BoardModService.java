package service;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import bean.BoardBean;
import DAO.BoardDAO;

public class BoardModService {

	public boolean boardInfo(BoardBean board) {
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstense();
		boardDAO.setConnection(con);
		
		boolean modResult = false;
		
		int result = boardDAO.modArticle(board);
		
		if(result>0) {
			modResult = true;
			commit(con);
		} else {
			modResult = false;
			rollback(con);
		}
		
		close(con);
		return modResult;
	}

}
