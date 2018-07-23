<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="DAO.BoardDAO" %>
<%@ page import="java.io.PrintWriter" %>
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:userBean id="Board" class="bbs.8bs" scope="page"/>
<jsp:setProperty name="" class="bbs.8bs" property="BOARD_SUBJECT"/>
<jsp:setProperty name="" class="bbs.8bs" property="BOARD_CONTENT" />

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>

</body>
</html>