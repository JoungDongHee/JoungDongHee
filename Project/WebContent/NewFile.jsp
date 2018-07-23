<%@ page language="java" contentType="text/html; charset=UTF-8"

    pageEncoding="UTF-8"%>

<!-- 만들어 놓은 클래스를 사용하기 위한 -->

<%@ page import="DAO.BoardDAO" %>

<!-- 스크립트문 편하게 사용하기 위한 -->


<%@ page import="java.io.PrintWriter" %>

<!-- 건너오는 데이터를 UTF-8 형태로 받아오기 위한 -->

<% request.setCharacterEncoding("UTF-8"); %>



<!-- 자바빈즈 사용하기 위해, 범위는 현재 페이지에서만 사용하기 위해 -->

<jsp:useBean id="content" class="bean.BoardBean" scope="page"/>

<!-- 로그인 페이지 넘긴 userEmail와 userPassword 사용하기 위한 -->

<jsp:setProperty name="content" property="BOARD_SUBJECT" />

<jsp:setProperty name="content" property="BOARD_CONTENT"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>게시물 처리 페이지</title>

</head>

<body>

<%

	// 세션을 통해 글쓴 이메일 찾아옴
    out.println(content.getBOARD_SUBJECT());
    out.println(content.getBOARD_CONTENT());

	

	// 하나라도 null 값이 있으면 처리하는 부분

	if(content.getBOARD_SUBJECT() == null || content.getBOARD_CONTENT() == null ){

		PrintWriter script = response.getWriter();

		script.println("<script>alert('입력이 안된 부분이 있습니다')</script>"); 

		script.println("<script>history.back()</script>"); 

	} else {

		BoardDAO Boarddao = new BoardDAO();

		int result = Boarddao.Write(content.getBOARD_SUBJECT(),content.getBOARD_CONTENT());

		if( result == -1 ){ // 글쓰기 실패시

			PrintWriter script = response.getWriter();

			script.println("<script>alert('글쓰기에 실패하였습니다')</script>"); 

			script.println("<script>history.back()</script>"); 

		} else { // 글쓰기 성공시

			PrintWriter script = response.getWriter();

			script.println("<script>location.href = 'Contentlist.jsp'</script>");

		}

	}

%>
</body>

</html>