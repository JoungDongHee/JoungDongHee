package service;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import bean.BoardBean;
import DAO.BoardDAO;

public class BoardListService {

	public ArrayList<BoardBean> getBoardList() {
		//DB에 접속
		//접속성공시 connect success출력
		Connection con = getConnection();
		//Action 클래스에 대한 싱글톤 객체를 얻어옴.
		BoardDAO boardDAO = BoardDAO.getInstense();
		//DB에 인증확인된 정보를 넘겨줌
		boardDAO.setConnection(con);
		//DB에 (member)insert 쿼리문을 전송
		
		ArrayList<BoardBean> memberList = boardDAO.selectBoardList();
		close(con);
		return memberList;
	}
	
}
