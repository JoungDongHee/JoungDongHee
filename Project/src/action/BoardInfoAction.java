package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ActionForward;
import bean.BoardBean;
import service.BoardViewService;


public class BoardInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println(request.getParameter("no"));
		int num = Integer.parseInt(request.getParameter("num"));
		int no = Integer.parseInt(request.getParameter("no"));
		
		BoardViewService boardInfoService = new BoardViewService();
		BoardBean infoResult = boardInfoService.boardInfo(num);
		
		//성공, 실패 여부를 판단
		ActionForward forward = null;
		if(infoResult!=null && no==1) {
			request.setAttribute("boardInfo", infoResult);
			forward = new ActionForward();
			forward.setPath("/board/board_mod.jsp");
		} else if(infoResult != null) {
			request.setAttribute("boardInfo", infoResult);
			forward = new ActionForward();
			forward.setPath("/board/board_view.jsp");
		} else if(infoResult == null && no!=1){
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('게시물 조회 실패')");
			out.println("location.href='./boardList.bo'</script>");
		} else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('수정 실패. 다시 시도해주세요.')");
			out.println("location.href='./boardList.bo'</script>");
		}
		return forward;
	}

}
