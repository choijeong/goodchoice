<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GOODCHOICE_회원가입</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<!-- 

 파일명 : Sample.jsp
 내  용 : 주소입력화면 (Sample.jsp ↔jusoPopup.jsp ↔ 도로명주소 팝업API )
 -->
<script language="javascript">
function goPopup(){
	// 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(http://www.juso.go.kr/addrlink/addrCoordUrl.do)를 호출하게 됩니다.
    var pop = window.open("jusoPopup","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
}
function jusoCallBack(roadFullAddr,roadAddrPart1,addrDetail,roadAddrPart2,engAddr, jibunAddr, zipNo, admCd, rnMgtSn, bdMgtSn
						, detBdNmList, bdNm, bdKdcd, siNm, sggNm, emdNm, liNm, rn, udrtYn, buldMnnm, buldSlno, mtYn, lnbrMnnm, lnbrSlno
						, emdNo, entX, entY){
	// 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.
	$('#roadAddrPart1').val(roadAddrPart1);
	document.forms.joinForm.roadAddrPart2.value = roadAddrPart2;
	document.forms.joinForm.addrDetail.value = addrDetail;
	document.forms.joinForm.zipNo.value = zipNo;
	document.forms.joinForm.entX.value = entX;
	document.forms.joinForm.entY.value = entY;
}
</script>

</head>
<body>
   <%@include file="../header.jsp" %> 
	   <%@include file="../nav.jsp" %> 


	
	<div class="container">
    <div class="row">
      <div class="col-lg-10 col-xl-9 mx-auto">
        <div class="card card-signin flex-row my-5">
          <div class="card-img-left d-none d-md-flex">
             <!-- Background image for card set in CSS! -->
          </div>
          <div class="card-body">
            <h5 class="card-title text-center">회원 가입</h5>
            <form class="form-signin" action="memberJoin" method="POST" id="joinForm">
              

              <div class="form-label-group">
                <label for="in putEmail">Email address</label>
                <input type="email" id="inputEmail" name="id_email" class="form-control" placeholder="Email address" required autofocus>
                <input type="hidden" name="id_checkOk" id="id_checkOk" value="unidchecked"/>
              </div>
                <span class="d-block text-center mt-2 small" ><button type="button" class="btn btn-outline-info"  name="id_checked" id="id_checked">아이디중복확인</button></span> 
              <hr>
              <div class="form-label-group">
                <label for="inputPassword">Password</label>
                <input type="password" id="inputPassword" name="password" class="form-control" placeholder="Password" required>
              </div>
            <hr>
              <div class="form-label-group">
                <label for="inputConfirmPassword">Confirm password</label>
                <input type="password" id="inputConfirmPassword" class="form-control" placeholder="비밀번호 확인" onmousedown="checkDown()" onkeyup="pwchecked()" required>
              </div>
			  <span id=pwcheckOk></span>
			  <hr>
			  <div id="namecheckID" class="form-label-group">
                <label for="nickname">nickname</label>
                <input type="text" id="nickname" name="nickname" class="form-control" placeholder="nickname"  onfocus="checkDown()" onkeyup="namechecked()" required>
              	<div id="nameinfo" class="info"></div>
              </div>
              <hr>
		
				
				
			  <div class="form-label-group">
                <label for="phone">Phone</label>
                <input type="text" id="phone" name="phone"  class="form-control" placeholder="Phone" required>
              </div>
              <hr>
              
              <table>
					<colgroup>
						<col style="width:20%"><col>
					</colgroup>
					<tbody>
						<tr>
							<th>우편번호</th>
							<td>
							    <input type="hidden" id="confmKey" value=""  >
								<input type="text" id="zipNo"  readonly style="width:100px">
								<input type="button"  value="주소검색" onclick="goPopup();">
							</td>
						</tr>
						<tr>
							<th><label>도로명주소</label></th>
							<td><input type="text" id="roadAddrPart1" name= "addr" style="width:85%"></td>
						</tr>
						<tr>s
							<th>상세주소</th>
							<td>
								<input type="text" id="addrDetail" name="addrdetail" style="width:40%" value="">
								<input type="text" id="roadAddrPart2"  style="width:40%" value="">
								<input type="hidden" id="entX" style="width:40%" value="">
								<input type="hidden" id="entY"  style="width:40%" value="">
							</td>
						</tr>
								
						
					</tbody>
				</table>
              <hr />
              <br />
              <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit">회원가입</button>
             
              <a class="d-block text-center mt-2 small" href="memberLoginView">로그인</a> 
              <hr class="my-4">
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
	
	
	
		
		<script src="resources/js/member/idchecked.js"></script>
		<script src="resources/js/member/checkdown.js"></script>
		<script src="resources/js/member/pwcheck.js"></script>
		<script src="resources/js/member/namechecked.js"></script>

		<%@include file="../footer.jsp" %>
</body>
</html>