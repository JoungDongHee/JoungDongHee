<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
                <a class="navbar-brand" href="index.html">Semi Project</a>
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
                    <li><a href="#">Contents</a></li>
                    <li><a href="#">Sigh</a></li>
                    <li><a href="#">Join</a></li>
                </ul>
            </div>
        </div>
    </nav>

    <!-- Top content -->
    <div class="top-content">
        <div class="container">

            <div class="row">
                <div class="col-sm-12 text wow fadeInLeft">
                    <h1>Please sign in</h1>
                    <form action="./MemberJoinAction.se?NAME=${membarBean.NAME }" class="form-signin" role="form" method="post">
                        <input type="text" name="name" class="form-control" placeholder="Name" id="name" required="required" autofocus>
                        <input type="text" name="age" class="form-control" placeholder="Age" id="age" required="required">
                        <input type="text" name="email" class="form-control" placeholder="Emailaddress" id="emailaddress" required="required" autofocus>
                        <input type="text" name="id" class="form-control" placeholder="Id" id="id" required="required">
                        <input type="password" name="pass" class="form-control" placeholder="Password" id="password" required="required">
                        <input type="text" name="phone" class="form-control" placeholder="Phone Number" id="phonenumber" required="required">
                        <input type="radio" name = "gender" value="male" id="gender"> male
                        <input type="radio" name = "gender" value="Female" id="gender">Female
                        <input class="btn btn-lg btn-primary btn-block" type="submit" value="Sigh in">
                    </form>
                </div>
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