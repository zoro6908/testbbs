<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter"%>
<!DOCTYPE html>
<html>
<head>
<!-- HTML5 간단히 표현, KR보다는 공통인 UTF-8 -->
<meta charset="UTF-8">
<!-- 뷰포트 -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/bootstrap.css">
<title>JSP 게시판 웹 사이트</title>
</head>
<body>
	<%
		String userID = null;
	if (session.getAttribute("userID") != null) {
		userID = (String) session.getAttribute("userID");
	}
	%>
	<!-- 네비게이션바  -->
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand" href="main.jsp">JSP 게시판 웹 사이트</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item"><a class="nav-link" href="main.jsp">메인</a></li>
				<li class="nav-item active"><a class="nav-link" href="bbs.jsp">게시판 <span class="sr-only">(current)</span>
				</a></li>

				<!-- 로그인이 되어 있지 않으면 보여주는 화면 로그인/ 회원 가입 -->


				<%
					if (userID == null) {
				%>

				<!--  <li class="nav-item"><a class="nav-link" href="#">Link</a></li> -->
				<li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> 접속하기 </a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdown">
						<a class="dropdown-item" href="login.jsp">로그인</a> <a class="dropdown-item" href="join.jsp">회원 가입</a>
						<!-- <div class="dropdown-divider"></div> -->
						<!-- <a class="dropdown-item" href="#">Something else here</a> -->
					</div></li>
				<!-- <li class="nav-item"><a class="nav-link disabled" href="#"
					tabindex="-1" aria-disabled="true">Disabled</a></li> -->


				<!-- 로그인이 되어 있으면 보여주는 화면 로그인/회원 가입 -->
				<%
					} else {
				%>


				<!--  <li class="nav-item"><a class="nav-link" href="#">Link</a></li> -->
				<li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> 회원관리 </a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdown">
						<a class="dropdown-item" href="logoutAction.jsp">로그아웃</a>
						<!-- <div class="dropdown-divider"></div> -->
						<!-- <a class="dropdown-item" href="#">Something else here</a> -->
					</div></li>


				<!-- <li class="nav-item"><a class="nav-link disabled" href="#"
						tabindex="-1" aria-disabled="true">Disabled</a></li> -->


				<%
					}
				%>

			</ul>
			<form class="form-inline my-2 my-lg-0">
				<input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
				<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
			</form>


		</div>
	</nav>

	<!-- 8강 추가 -->
	<div class="jumbotron jumbotron-fluid">
		<div class="container">
			<h1 class="display-8">글쓰기 양식</h1>
		</div>
	</div>
	
	<div class="container">
		<form method="post" action="writeAction.jsp">
			<div class="form-group">
				<label for="InputTitle">글 제목</label> 
				<input type="text" class="form-control" id="InputTitle" placeholder="title" name="bbsTitle">
			</div>
			<label for="InputContent">글 내용</label>
			<div class="form-group">
				<textarea class="form-control" rows="5" id="InputContent" placeholder="contents" name="bbsContent"></textarea>
			</div>
			<button type="submit" class="btn btn-primary">글쓰기</button>
		</form>

	</div>

	<!-- 	<div class="container">
		<div class="row">
			<form method="post" action="writeAction.jsp">
				<table class="table">
					<thead class="table-dark">
						<tr>
							<th colspan="2" style="text-align: center">글쓰기 양식</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><input type="text" class="form-control" placeholder="글 제목" name="bbsTitle" maxlength="50"></td>
						</tr>
						<tr>
							<td><textarea class="form-control" placeholder="글 내용" name="bbsContent" maxlength="2048" style="height: 350px;"></textarea></td>
						</tr>
					</tbody>

				</table>
				<input type="submit" class="btn btn-primary" value="글쓰기">
			</form>
		</div>
	</div> -->

	<!-- 애니매이션 담당 JQUERY -->
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.js"></script>
</body>
</html>