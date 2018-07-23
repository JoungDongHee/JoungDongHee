package service;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import org.apache.catalina.connector.Request;

import bean.BoardBean;
import DAO.BoardDAO;

public class BoardWriteService {

	public boolean registArticle(BoardBean board) {

		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstense();
		boardDAO.setConnection(con);

		boolean WriteResult = false;

		int result = boardDAO.Write(board);

		System.out.println(result);
		
		if(result>0) {
			WriteResult = true;
			commit(con);
			System.out.println("1");
		} else {
			WriteResult = false;
			rollback(con);
			System.out.println("2");
		}
		
		close(con);
		return WriteResult;
	}

}
