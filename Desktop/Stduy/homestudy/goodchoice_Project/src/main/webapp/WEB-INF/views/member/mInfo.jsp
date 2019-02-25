<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.hoon.goodchoice.memberdto.MemberDTO"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.If"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<title>GOODCHOICE_회원정보</title>

</head>
<body>
	<%@include file="../header.jsp"%>
<%@include file="../nav.jsp" %> 

	<%
		MemberDTO profile = (MemberDTO) request.getAttribute("profile");
		String id_email = profile.getId_email();
		String nickname = profile.getNickname();
		String password = profile.getPassword();
		String phone = profile.getPhone();
		String reg_dttm = new SimpleDateFormat("MM/dd/yyyy").format(profile.getReg_dttm());
		int indexkey=profile.getIndexkey();
	%>
<div class="container">
    <div class="row">
      <div class="col-lg-10 col-xl-9 mx-auto">
        <div class="card card-signin flex-row my-5">
          <div class="card-img-left d-none d-md-flex">
             <!-- Background image for card set in CSS! -->
          </div>
          <div class="card-body">
            <h5 class="card-title text-center">회원 정보</h5>
              
              <div class="form-label-group">
                <label for="in putEmail">이메일 아이디</label>
                <h6><%=id_email%></h6>
                
              </div>
              <hr>
			  
			  <div class="form-label-group">
                <label for="nickname">닉네임</label> <h6><%=nickname%></h6>
              </div>	
              <hr>
			  <div class="form-label-group">
                <label for="phone">연락처</label> <h6><%=phone%></h6>
              </div>
              <hr>
                <div class="form-label-group">
                <label for="phone">가입일시</label> <h6><%=reg_dttm%></h6>
              </div>
              <br />
              <hr>
       <c:choose>
		<c:when test="${sessionScope.sessionId.indexkey eq profile.indexkey  }">
             <a href="memberModifyView?indexkey=<%=indexkey%>"> <button class="btn btn-lg btn-primary btn-block text-uppercase" >회원수정</button></a>
              <a class="d-block text-center mt-2 small" href="memberDeleteView?indexkey=<%=indexkey%>">탈퇴하기</a> 
         </c:when>
        </c:choose> 
             
              <hr class="my-4">
          </div>
        </div>
      </div>
    </div>
  </div>
	

	<%@include file="../footer.jsp"%>
</body>
</html>