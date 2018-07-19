package service;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import DAO.MemberDAO;
import bean.MemberBean;

public class MemberJoinService {

	public boolean joinMember(MemberBean memberBean) {
		MemberDAO memberDAO = MemberDAO.getInstense();
		Connection con = getConnection();
		memberDAO.setConnection(con);
		
		boolean joinResult = false;
		
		int result = memberDAO.insertMember(memberBean);
		
		if(result > 0) {
			joinResult = true;
			commit(con);
		}else {
			joinResult = false;
			rollback(con);
		}
	close(con);
	return joinResult;
	}
}
