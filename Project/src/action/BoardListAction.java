package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ActionForward;
import bean.BoardBean;
import bean.PageInfo;
import service.BoardListService;

public class BoardListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BoardListService boardListService = new BoardListService();
		//페이지 번호를 알기위해 사용하는 변수
		int page = 1;
		//한 페이지에 보여줄 글 갯수를 정하기 위해 사용하는 변수
		int limit = 10;
		
		//page 파라미터 값 검사
		if(request.getParameter("page")!=null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		 
		//전체 글 갯수를 가져오기 위한 ListService 크리래스의 메소드 호출
		int listCount = boardListService.getListCount();
		
		//내용만 가져오는 방법
		/*List<BoardBean> list = boardListService.BoardList();*/
		//limit 값을 걸어놓은 만큼 범위에 해당하는 글만 가져오는 방법
		List<BoardBean> list = boardListService.BoardList(page, limit);
		
		//페이지 계산을 위한 부분
		//최대로 필요한 페이지 갯수 계산
		int maxPage = (int)((double)listCount/limit+1);
		//현제 페이지에 보여줄 시작 페이지 번호
		int startPage = (((int)((double)page/10 + 0.9)) -1 ) * 10 + 1;
		
		int endPage = startPage + 10 -1;
		
		if(endPage>maxPage) {
			endPage = maxPage;
		}
		
		PageInfo pageInfo = new PageInfo();
		
		pageInfo.setPage(page);
		pageInfo.setStartPage(startPage);
		pageInfo.setEndPage(endPage);
		pageInfo.setMaxPage(maxPage);
		pageInfo.setListCount(listCount);
		
		
		
		
		request.setAttribute("board", list);
		request.setAttribute("pageInfo", pageInfo);
		ActionForward forward = new ActionForward();
		forward.setPath("/board/board_list.jsp");
		return forward;
		
		
		
		/*ActionForward forward = null;
		BoardListService boardListService  = new BoardListService();
		ArrayList<BoardBean> list = boardListService.BoardList();
		request.setAttribute("boardList", list);
		
		forward = new ActionForward();
		forward.setPath("/board/boardList.jsp");
		
		return forward;*/
	}

}
