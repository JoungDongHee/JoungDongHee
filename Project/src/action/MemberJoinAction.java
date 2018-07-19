package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ActionForward;
import bean.MemberBean;
import service.MemberJoinService;

public class MemberJoinAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MemberBean memberBean = new MemberBean();
		HttpSession session = request.getSession();
		
		memberBean.setNAME(request.getParameter("name"));
		memberBean.setAGE(Integer.parseInt(request.getParameter("age")));
		memberBean.setEMAIL(request.getParameter("email"));
		memberBean.setPASS(request.getParameter("pass"));
		memberBean.setID(request.getParameter("id"));
		memberBean.setPHONE(Integer.parseInt(request.getParameter("phone")));
		memberBean.setGENDER(request.getParameter("gender"));
		
		boolean joinResult = false;
		MemberJoinService memberJoinService = new MemberJoinService();
		joinResult = memberJoinService.joinMember(memberBean);
						ActionForward forward = null;
		if(joinResult == false) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('회원 가입 실패')");
			out.println("history.back()</script>");
		}else {
			forward = new ActionForward();
			forward.setRedirect(true);
			session.setAttribute("id", memberBean.getID());
			response.sendRedirect("index.jsp");
		}
		return forward;
	}
}
