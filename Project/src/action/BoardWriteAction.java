package action;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import bean.ActionForward;
import bean.BoardBean;
import service.BoardWriteService;

public class BoardWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		
		
		
		
		
		
		//boardWrite 에서 입력받은 내용을 BoardBean 객체에 저장
		
		BoardBean boardBean = new BoardBean();
	    boardBean.setBOARD_SUBJECT(request.getParameter("BOARD_SUBJECT"));
	    boardBean.setBOARD_CONTENT(request.getParameter("BOARD_CONTENT"));
		//BoardWriteService 클래스 호출
	    System.out.println(boardBean.getBOARD_SUBJECT());
		BoardWriteService boardWriteService = new BoardWriteService();
		boolean WriteResult = boardWriteService.registArticle(boardBean);
		
		
		
		
		
		
		
		//성공, 실패 여부를 판단
		ActionForward forward = null;
		if(WriteResult == false) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('게시글 작성 실패')");
			out.println("history.back()</script>");
		} else {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("index.jsp");
			
		}
		return forward;
	}

}
