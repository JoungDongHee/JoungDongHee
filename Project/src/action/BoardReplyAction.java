package action;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import bean.ActionForward;
import bean.BoardBean;
import service.BoardReplyService;

public class BoardReplyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		/*int ref = Integer.parseInt(request.getParameter("re_ref"));
		int lev = Integer.parseInt(request.getParameter("re_lev"));*/
		
		String realPath = "";
		
		
		ServletContext context = request.getServletContext();

		
		MultipartRequest multi = new MultipartRequest(
									request,
									realPath,
									size,
									"UTF-8",
									new DefaultFileRenamePolicy()
									);
		
		//boardWrite에서 입력받은 내용을 BoardBean 객체에 저장
		BoardBean board = new BoardBean();
		request.setCharacterEncoding("UTF-8");
		
		board.setBOARD_SUBJECT((multi.getParameter("name")));
		board.setBOARD_CONTENT((multi.getParameter("content")));
		board.setRe_ref(Integer.parseInt(multi.getParameter("re_ref")));
		board.setRe_lev(Integer.parseInt(multi.getParameter("re_lev"))+1);
		board.setRe_seq(Integer.parseInt(multi.getParameter("re_seq")));
		BoardReplyService boardReplyService = new BoardReplyService();
		boolean replyResult = boardReplyService.registReply(board);
		//성공, 실패 여부를 판단
		ActionForward forward = null;
		if(replyResult) {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("./boardList.bo");
			
		} else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('답글쓰기 실패')");
			out.println("history.back()</script>");
		}
		return forward;
	}

}
