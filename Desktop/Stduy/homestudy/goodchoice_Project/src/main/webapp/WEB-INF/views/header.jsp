<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- Bootstrap core CSS -->
    <link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="resources/bootstrap/css/bootstrap.css" rel="stylesheet">
	<script src="resources/bootstrap/js/bootstrap.bundle.min.js"></script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="resources/js/member/hiddenmember.js"></script>
<!-- 폰트 설정 -->
<link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/earlyaccess/notosanskr.css">
</head>

	
	
	  <!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top">
  <div class="container">
   <input id="hiddensessionId" type="hidden" name="sessionId" value="${sessionId.indexkey}"/> 
    <a class="navbar-brand" href="home">GoodChoice</a>
    <a class="nav-link" href="portfolio">WEBPORTFOLIO</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
    <div class="collapse navbar-collapse" id="navbarResponsive">
      <ul class="navbar-nav ml-auto">
      
        <%									
			if(session.getAttribute("sessionId") == null){		
		%>
        <li class="nav-item active">
          <a class="nav-link" href="memberJoinView">회원가입
                <span class="sr-only">(current)</span>
              </a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="memberLoginView">로그인</a>
        </li>
  		 <% 
			}else{
		%>
		<li>환영합니다
        <a id="mInfoView" class="py-2 d-none d-md-inline-block" href="#">
        <span id="span_nick" >닉네임가져오기</span>
        </a>님
        </li>
		 <li class="nav-item active">
          <a class="nav-link" href="memberLogout">로그아웃
                <span class="sr-only">(current)</span>
              </a>
        </li>
     
		<% 
			} 
		%>
		
      </ul>
    </div>
  </div>
</nav>


	
	
	
	
	
	

