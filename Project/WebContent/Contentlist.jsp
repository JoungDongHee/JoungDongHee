<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ page import="bean.BoardBean" %>
<%@ page import="java.util.*" %>
<%@ page import="bean.PageInfo" %>   
<%@ page import = DAO.BoardDAO %>

<%
ArrayList<BoardBean> boardList = (ArrayList<BoardBean>)request.getAttribute("boardList");

//페이징 정보 가져오기
PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");

int nowPage = pageInfo.getPage();
int startPage = pageInfo.getStartPage();
int endPage = pageInfo.getEndPage();
int maxPage = pageInfo.getMaxPage();
int listCount = pageInfo.getListCount();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Semi Project</title>

        <!-- CSS -->
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,500,500i">
        <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/font-awesome/css/font-awesome.min.css">
        <link rel="stylesheet" href="assets/css/animate.css">
        <link rel="stylesheet" href="assets/css/style.css">

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->

        <!-- Favicon and touch icons -->
        <link rel="shortcut icon" href="assets/ico/favicon.png">
        <link rel="apple-touch-icon-precomposed" sizes="144x144" href="assets/ico/apple-touch-icon-144-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="114x114" href="assets/ico/apple-touch-icon-114-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="72x72" href="assets/ico/apple-touch-icon-72-precomposed.png">
        <link rel="apple-touch-icon-precomposed" href="assets/ico/apple-touch-icon-57-precomposed.png">

    </head>

    <body>
    <%
    String UserID = null;
    if(session.getAttribute("userID")!=null)
    {
    	UserID=(String)session.getAttribute("userID");
    }int PageNumber = 1;
    if(request.getParameter("PageNumber")!=null)
    {
    	PageNumber = Integer.parseInt(request.getParameter("PageNumber"));
    }
    
    %>
		<!-- Top menu -->
		<nav class="navbar navbar-inverse navbar-fixed-top navbar-no-bg" role="navigation">
			<div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#top-navbar-1">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="index.jsp">Semi Project</a>
				</div>
				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse" id="top-navbar-1">
					<ul class="nav navbar-nav navbar-right navbar-search-button">
						<li><a class="search-button" href="#"><i class="fa fa-search"></i></a></li>
					</ul>
					<form class="navbar-form navbar-right navbar-search-form disabled wow fadeInLeft" role="form" action="" method="post">
						<div class="form-group">
							<input type="text" name="search" placeholder="Search..." class="search form-control">
						</div>
					</form>
					<ul class="nav navbar-nav navbar-right navbar-menu-items wow fadeIn">
						<li><a href="#">Home</a></li>
						<li><a href="#">About</a></li>
						<li><a href="Contents.sjp">Contents</a></li>
						<li><a href="sigh.jsp?NAME=${memberBean.NAME}">Sigh</a></li>
						<li><a href="join.jsp">Join</a></li>
						<input type="hidden" name="name" value="${memberBean.NAME}">
					</ul>
				</div>
			</div>
		</nav>

        <!-- Top content  글 목록 -->
        <div class="top-content">
        <div class="container">
        <div class="row">
        <table class="table table-striped" style="text-align:center; border:1px solid #dddddd">
        <thead>
        <tr>
        <th style="background-color:#eeeeee;text-align:center;" >번호</th>
        <th style="background-color:#eeeeee;text-align:center;">글제목</th>
        <th style="background-color:#eeeeee;text-align:center;">작성자</th>
        <th style="background-color:#eeeeee;text-align:center;">조회수</th>
        <th style="background-color:#eeeeee;text-align:center;">작성일</th>
        </tr>
        </thead>
        <tbody>
        <%
        BoardDAO boarddao = new BoardDAO();
        ArrayList<BoardBean> list = (ArrayList<BoardBean>)request.getAttribute("board");
        for(int i=0;i<list.size();i++)
        {
        %>
        <tr>
         <td><%= list.get(i).getBOARD_NUM()%></td>
        <td><a href="boardInfoAction.bo?num=<%=list.get(i).getBOARD_NUM() %>&no=0&page=<%=nowPage%>"><%= list.get(i).getBOARD_SUBJECT()%></a></td>
        <td><%= list.get(i).getBOARD_SUBJECT()%></td>
        <td><%=list.get(i).getBOARD_READCOUNT() %></td>
        <td><%= list.get(i).getBOARD_DATE()%></td>
        </tr>
        
        <%
        }
        %>
        </tbody>
        </table>
        <a href="ContentsWrite.jsp" class="btn btn-primary pull-right">글쓰기</a>
        </div>
        
        
        </div>
        </div>
        <!-- Footer -->
        <footer>
	        <div class="container">
	        	<div class="row">
	        		<div class="col-sm-12 footer-copyright">
                    	&copy; Semi Project
                    </div>
                </div>
	        </div>
        </footer>
        <!-- Javascript -->
        <script src="assets/js/jquery-1.11.1.min.js"></script>
        <script src="assets/bootstrap/js/bootstrap.min.js"></script>
        <script src="assets/js/jquery.backstretch.min.js"></script>
        <script src="assets/js/wow.min.js"></script>
        <script src="assets/js/waypoints.min.js"></script>
        <script src="assets/js/scripts.js"></script>
        
        <!--[if lt IE 10]>
            <script src="assets/js/placeholder.js"></script>
        <![endif]-->

    </body>

</html>