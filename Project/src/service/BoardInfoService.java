package service;

import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.*;
import java.sql.Connection;

import bean.BoardBean;
import DAO.BoardDAO;

public class BoardInfoService {

	public BoardBean boardInfo(int datailNum) {
		//Action 클래스에 대한 싱글톤 객체를 얻어옴.
		BoardDAO boardDAO = BoardDAO.getInstense();
		//14~15라인은 DB에 접속하는 과정
		Connection con = getConnection();
		boardDAO.setConnection(con);
		//DB에 (member)insert 쿼리문을 전송
		BoardBean ViewResult = boardDAO.boardView(datailNum);
		commit(con);
		close(con);
		return ViewResult;
	}

}
