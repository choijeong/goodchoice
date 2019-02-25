<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<head>
<meta charset="UTF-8">
<title>GOODCHOICE_로그인</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link rel="stylesheet" href="resources/css/login.css" media="all" />

</head>

<body>
	<%@include file="../header.jsp" %> 
	<%@include file="../nav.jsp" %> 
<div class="container-fluid">
  <div class="row no-gutter">
    <div class="d-none d-md-flex col-md-4 col-lg-6 bg-image"></div>
    <div class="col-md-8 col-lg-6">
      <div class="login d-flex align-items-center py-5">
        <div class="container">
          <div class="row">
            <div class="col-md-9 col-lg-8 mx-auto">
              <h3 class="login-heading mb-4">로그인</h3>
              <form id="loginForm" method="post">
                <div class="form-label-group">
                  <input type="email" id="inputEmail" name="id_email" class="form-control" placeholder="Email address" required autofocus>
                  <label for="inputEmail">Email address</label>
                </div>

                <div class="form-label-group">
                  <input type="password" id="inputPassword"  name="password" class="form-control" placeholder="Password" required>
                  <label for="inputPassword">Password</label>
                </div>

                <input type="button" class="btn btn-lg btn-primary btn-block btn-login text-uppercase font-weight-bold mb-2" onclick="loginFn()" value="로그인"/>
                <div class="text-center">
                  <a class="small" href="#">비밀번호를 잊으셨습니까?</a></div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
		
		<script src="resources/js/member/memberLogin.js"></script>
</body>
	<%@include file="../footer.jsp" %>
