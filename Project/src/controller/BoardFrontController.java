package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.BoardDeleteAction;
import action.BoardInfoAction;
import action.BoardListAction;
import action.BoardModAction;
import action.BoardReplyAction;
import action.BoardWriteAction;
import bean.ActionForward;



@WebServlet("*.bo")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public BoardController() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	String RequestURI=request.getRequestURI();
    	String contextPath=request.getContextPath();
    	String command=RequestURI.substring(contextPath.length());
    	ActionForward forward=null;
    	Action action = null;
    	if(command.equals("/boardWritePro.bo")) {
    		action = new BoardWriteAction();
    		try {
    			forward = action.execute(request, response);
    		} catch(Exception e){
    			e.printStackTrace();
    		}
    	} else if(command.equals("/boardWriteForm.bo")) {
    		forward = new ActionForward();
    		forward.setPath("./ContentsWrite.bo");
    	} else if(command.equals("/boardList.bo")) {
    		action = new BoardListAction();
    		try {
    			forward = action.execute(request, response);
    		} catch(Exception e){
    			e.printStackTrace();
    		}
    	} else if(command.equals("/boardInfoAction.bo")) {
    		action = new BoardInfoAction();
    		try {
    			forward = action.execute(request, response);
    		} catch(Exception e){
    			e.printStackTrace();
    		}
    	} else if(command.equals("/boardMod.bo")) {
    		action = new BoardModAction();
    		try {
    			forward = action.execute(request, response);
    		} catch(Exception e){
    			e.printStackTrace();
    		}
    	} else if(command.equals("/boardDeleteForm.bo")) {
    		int boardNum = Integer.parseInt(request.getParameter("boardNum"));
    		request.setAttribute("boaradNum", boardNum);
    		forward = new ActionForward();
    		forward.setPath("/board/board_delete.jsp");
    	} else if(command.equals("/boardDelete.bo")) {
    		action = new BoardDeleteAction();
    		try {
    			forward = action.execute(request, response);
    		} catch(Exception e){
    			e.printStackTrace();
    		}
    	} else if(command.equals("/boardReplyAction.bo")) {
    		action = new BoardReplyAction();
    		try {
    			forward = action.execute(request, response);
    		} catch(Exception e){
    			e.printStackTrace();
    		}
    	}
    	if(forward != null) {
    		if(forward.isRedirect()) {
    			response.sendRedirect(forward.getPath());
    		} else {
    			RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
    			dispatcher.forward(request, response);
    		}
    	}
	}

}
