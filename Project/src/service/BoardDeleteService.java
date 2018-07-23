package service;

import static db.JdbcUtil.*;

import java.sql.Connection;

import DAO.BoardDAO;

public class BoardDeleteService {

	public boolean isArticlePass(int boardNum, String boardPass) {
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstense();
		boardDAO.setConnection(con);
		
		boolean isPath;
		
		return false;
	}

	public boolean DeleteArticle(int boardNum) {
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstense();
		boardDAO.setConnection(con);
		
		int deleteCount = boardDAO.deleteArticle(boardNum);
		
		boolean DeleteSuccess = false;
		
		if(deleteCount>0) {
			DeleteSuccess = true;
			commit(con);
		} else {
			DeleteSuccess = false;
			rollback(con);
		}
		
		return false;
	}

}
