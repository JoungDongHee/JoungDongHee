package service;

import bean.MemberBean;
import static db.JdbcUtil.*;

import java.sql.Connection;

import DAO.MemberDAO;

public class MemberLoginService {

	public boolean loginMember(MemberBean memberBean) {
		MemberDAO memberDAO = MemberDAO.getInstense();
		Connection con = getConnection();
		memberDAO.setConnection(con);
		
		boolean loginResult = false;
		
		String result = memberDAO.loginMember(memberBean);
		if(result == null) {
			loginResult = false;
		}else {
			loginResult = true;
		}
	close(con);
	return loginResult;
	}

}
