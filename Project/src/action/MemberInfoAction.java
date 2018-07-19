package action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Service.MemberListService;
import bean.ActionForward;
import bean.MemberBean;

public class MemberInfoAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String name = (String)session.getAttribute("id");
		
		MemberBean memberbean = new MemberBean();
		ActionForward forward = null;
		
		if(name.equals("admin")) {
			forward = new ActionForward();
			AdminInfoAction memberListService = new AdminInfoAction();
			ArrayList<MemberBean> memberList = 
					memberListService.getMemberlist();
			request.setAttribute("adminInfo", adminInfo);
			forward.setPath("./member_list.jsp");
			
		}else if(name.equals(null)) {
			//id가 널이면 로그인 페이지로 이동
			response.sendRedirect("sigh.jsp");
		}else {
			forward = new ActionForward();
			MemberInfoAction memberListService = new MemberInfoAction();
			ArrayList<MemberBean> memberList = 
					memberListService.getMemberlist();
			//request영역에 호출결과를 저장
			request.setAttribute("memberInfo", memberInfo);
			forward.setPath("./member_list.jsp");
		}
		return forward;
	}

}
