package service;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;
import java.sql.Connection;
import bean.BoardBean;
import DAO.BoardDAO;

public class BoardReplyService {

	public boolean registReply(BoardBean board) {
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstense();
		boardDAO.setConnection(con);
		
		boolean replyResult = false;
		
		int result = boardDAO.replyArticle(board);
		
		if(result>0) {
			replyResult = true;
			commit(con);
		} else {
			replyResult = false;
			rollback(con);
		}
		
		close(con);
		return replyResult;
	}

}
