<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
	if (session.getAttribute("sessionId") == null) {
		out.print("<script>");
		out.print("alert('로그인 후 이용.. 로그인 페이지로 이동');");
		out.print("location.href='memberLoginView';");
		out.print("</script>");
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GOODCHOICE_회원탈퇴</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link rel="stylesheet" href="resources/css/mdelete.css" media="all" />

</head>
<body>
<%@include file="../header.jsp"%>
<%@include file="../nav.jsp" %> 


<div class="container-fluid">
  <div class="row no-gutter">
    <div class="d-none d-md-flex col-md-4 col-lg-6 bg-image"></div>
    <div class="col-md-8 col-lg-6">
      <div class="login d-flex align-items-center py-5">
        <div class="container">
          <div class="row">
            <div class="col-md-9 col-lg-8 mx-auto">
              <h3 class="login-heading mb-4">회원탈퇴</h3>
              <form id="memberDelete" method="post" id="deleteForm">
                <div class="form-label-group">
                  <input type="email" id="inputEmail" name="id_email" class="form-control" placeholder="Email address" required autofocus>
                  <label for="inputEmail">Email address</label>
                </div>

                <div class="form-label-group">
                  <input type="password" id="inputPassword"  name="password" class="form-control" placeholder="Password" required>
                  <label for="inputPassword">Password</label>
                </div>

                <input type="button" class="btn btn-lg btn-primary btn-block btn-login text-uppercase font-weight-bold mb-2" id="memberDeleteBtn" value="회원탈퇴"/>
                <div class="text-center">
                  <a class="small" href="home">HOME</a></div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>





		<!-- 		<form action="memberDelete" method="post" id="deleteForm">
					<ul>
						<li>회원탈퇴</li>
						<li>
							<input type="text" name="id_email" id="id_email"placeholder="아이디 입력"/>
						</li>
						<li>
							<input type="password" name="password" id="password" placeholder="비밀번호 입력" />
						</li>
						<li><input type="button" value="회원탈퇴" id="memberDeleteBtn" /></li>
					</ul>
				</form> -->
				
<script src="resources/js/member/memberDeleteBtn.js">


</script>
<%@include file="../footer.jsp"%>
</body>
</html>