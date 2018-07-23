package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ActionForward;
import bean.BoardBean;
import service.BoardModService;

public class BoardModAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String realpass = request.getParameter("realpass");
		String inputpass = request.getParameter("pass");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('비밀번호가 틀렸습니다.')");
		out.println("history.back()</script>");
		if(!realpass.equals(inputpass)) {
			response.setContentType("text/html;charset=UTF-8");
			//PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('비밀번호가 틀렸습니다.')");
			out.println("history.back()</script>");
			System.out.println(realpass);
			System.out.println(inputpass);
		}
		int num = Integer.parseInt(request.getParameter("num"));
		
		BoardBean board = new BoardBean();
		board.setBOARD_SUBJECT((request.getParameter("subject")));
		board.setBOARD_CONTENT((request.getParameter("content")));
		
		
		BoardModService boardModService = new BoardModService();
		boolean modResult = boardModService.boardInfo(board);
		
		//성공, 실패 여부를 판단
		ActionForward forward = null;
		if(modResult) {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("./boardList.bo");
			//./boardInfoAction.bo?num=${board.num}&no=0
		} else {
			response.setContentType("text/html;charset=UTF-8");
			//PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('수정 실패')");
			out.println("history.back()</script>");
		}
		return forward;
	}

}
