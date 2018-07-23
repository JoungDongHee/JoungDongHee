package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ActionForward;
import service.BoardDeleteService;

public class BoardDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int boardNum = Integer.parseInt(request.getParameter("boardNum"));
		String boardPass = request.getParameter("boardPass");
		BoardDeleteService boardDeleteService = new BoardDeleteService();
		boolean isPassRight = boardDeleteService.isArticlePass(boardNum, boardPass);
		ActionForward forward = null;
		if(!isPassRight) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alrert('삭제할 권한이 없습니다.');");
			out.println("history.back();");
			out.println("</script>");
		} else {
			boolean DeleteSuccess = boardDeleteService.DeleteArticle(boardNum);
			if(!DeleteSuccess) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alrert('삭제실패.');");
				out.println("history.back();");
				out.println("</script>");
			} else {
				forward = new ActionForward();
				forward.setRedirect(true);
				forward.setPath("boardList.bo");
			}
		}
		
		return forward;
	}

}
