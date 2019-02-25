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
<title>GOODCHOICE_회원수정</title>
</head>
<body>
	<%@include file="../header.jsp"%>
	<%@include file="../nav.jsp" %> 
		<div class="container">
    <div class="row">
      <div class="col-lg-10 col-xl-9 mx-auto">
        <div class="card card-signin flex-row my-5">
          <div class="card-img-left d-none d-md-flex">
             <!-- Background image for card set in CSS! -->
          </div>
          <div class="card-body">
            <h5 class="card-title text-center">회원정보수정</h5>
            <form class="form-signin" action="memberModify" method="POST" id="modifyForm">
              <div class="form-label-group">
                <label for="in putEmail">Email address</label>
                <input type="hidden" id="inputEmail" name="id_email" class="form-control" value="${member.id_email}" required >
             	<h6>${member.id_email}</h6>
              </div>
              <hr>
              <div class="form-label-group">
                <label for="inputPassword">Password</label>
                <input type="password" id="inputPassword" name="password" class="form-control" placeholder="Password" required>
              </div>
            
              <div class="form-label-group">
                <label for="inputConfirmPassword">Confirm password</label>
                <input type="password" id="inputConfirmPassword" class="form-control" placeholder="비밀번호 확인" onmousedown="checkDown()" onkeyup="pwchecked()" required>
              </div>
			  <span id=pwcheckOk></span>
			    <hr>
			  <div id="namecheckID" class="form-label-group">
                <label for="nickname">nickname</label>
                <input type="text" id="nickname" name="nickname" class="form-control" placeholder="nickname" value="${member.nickname }" onfocus="checkDown()" onkeyup="namechecked()" required>
              	<div id="nameinfo" class="info"></div>
              </div>	
                <hr>
			  <div class="form-label-group">
                <label for="phone">Phone</label>
                <input type="text" id="phone" name="phone"  class="form-control" placeholder="Phone" value="${member.phone }" required>
              </div>
                <hr>
              <br />
              <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit">회원정보수정</button>
             
              <a class="d-block text-center mt-2 small" href="home">HOME</a> 
              <hr class="my-4">
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
	
	
	<script src="resources/js/member/pwcheck.js"></script>
	<script src="resources/js/member/namechecked.js"></script>
		
	<%@include file="../footer.jsp"%>
</body>
</html>