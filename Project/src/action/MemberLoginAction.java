package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.MemberLoginService;
import bean.ActionForward;
import bean.MemberBean;

public class MemberLoginAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MemberBean memberBean = new MemberBean();
		ActionForward forward = null;
		
		memberBean.setID(request.getParameter("id"));
		memberBean.setPASS(request.getParameter("pass"));
		memberBean.setNAME(request.getParameter("name"));
		
		boolean loginResult = false;
		MemberLoginService memberLoginService = new MemberLoginService();
		loginResult = memberLoginService.loginMember(memberBean);
		
		if(loginResult) {
			System.out.println("아이디 또는 비밀번호가 일치합니다.");
			HttpSession session = request.getSession();
			session.setAttribute("memberBean", memberBean);
			session.setAttribute("id", memberBean.getID());
			System.out.println(memberBean.getID());
			forward = new ActionForward();
			forward.setRedirect(true);
			response.sendRedirect("loginMain.jsp");
		}else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("아이디 또는 비밀번호가 일치하지 않습니다.");
			out.println("<script>");
			out.println("alert('이메일 또는 비밀번호가 일치하지 않습니다.')");
			out.println("location.href='sigh.jsp'</script>");
		}
		return forward;
	}
}

















