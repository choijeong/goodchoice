<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GOODCHOICE_글보기</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


</head>
<body>

<%@include file="../header.jsp" %> 
<%@include file="../nav.jsp" %> 

	<script src="resources/js/good/goodChoicecontent.js"></script>
	<script src="resources/js/good/goodDelete.js"></script>
	<script>
		function upHit(){
			var good_indexkey =$('#indexkey').text()
			var member_indexkey = $('#hiddensession').val()
			/* console.log(good_indexkey)
			console.log(member_indexkey) */
			data={'member_indexkey':member_indexkey, 'good_indexkey': good_indexkey}
			$.ajax({
				url: "goodUphit2",
				type: "POST",
				dataType:"json",
				data:data,
				success:function(data){
					var msg='';
					var img_hit='';
					msg+=data.msg;
					alert(msg);
					var hiddenindexkey=$('#hiddensession').val();
				 	if(data.hit_check == 0){
				 		img_hit="resources/img/favorite-2.png"; 
					}else if(data.hit_check == 1){
					 	img_hit="resources/img/favorite-1.png";	
					}
					$('#img_hit').attr('src', img_hit);
					$('#hit_count').text('좋아요 '+data.hit_count);
					$('#hit_check').val(data.hit_check); 
				}
			});
		}
	</script>
	<script>
	function needlogin(){
		var message=confirm("로그인시 좋아요 가능합니다. 로그인 하시겠습니까?");
		if(message == true){
			loginGo();
			
			function loginGo(){
				location.href='memberLoginView'
			}
		}
	}
	</script>

	
		<!-- Page Content -->
<div class="container">
			<!-- hidden정보들
				페이지, 검색, 타입 등 검색 정보 유지
			 -->
			
					<input id="hiddensession" type="hidden" name="hidden" value="${sessionId.indexkey}"/>
					<input id="hiddennickname" type="hidden" name="hidden" value="${sessionId.nickname}"/>
					<input id="hit_check" type="hidden" name="hidden" value="0"/>
					<input id="userPage" type="hidden" name="hidden" value="${pBase.userPage}"/>
					<input id="pageRec" type="hidden" name="hidden" value="${pBase.pageRec}"/>
					<input id="searchType" type="hidden" name="hidden" value="${pBase.searchType}"/>
					<input id="keyword" type="hidden" name="hidden" value="${pBase.keyword}"/>
					<input id="goodorder" type="hidden" name="hidden" value="${pBase.goodorder}"/>
					<input id="hiddentype" type="hidden" name="hidden" value="${pBase.type}"/>
				

  <!-- Portfolio Item Heading -->
  <h1 id="title" class="my-4"> 제목이와야되
     <small id="listtype"> </small>
  </h1>
	<div class="col-md-4">
<span class="badge badge-pill badge-success" style="color: 000;" id="update"></span>
<span class="badge badge-pill badge-danger" id="del"></span>
<span class="badge badge-pill badge-info" id="hit"></span>
<span class="badge badge-pill badge-light" style="color: 000;" id="list"></span>
	</div>
	<hr />
  <!-- Portfolio Item Row -->
  <div class="row">
	
    <div class="col-md-8" >
       <img id="imgcom" class="img-fluid" style=" height: 390px;" src="http://placehold.it/750x500" alt="...loding">
    
    </div>

    <div class="col-md-4">
   	 <span id="hit_count" class="badge badge-info">좋아요</span>
     <span  id="view_count" class="badge badge-warning">조회수</span>
     <span  id="replycnt" class="badge badge-success">댓글수</span>
     <span hidden="" id="indexkey">${indexkey}</span>
     	 <h1 class="my-1" id="title2">제목이와야해 </h1>
     
      <a id="anickname" href="#">  <h3 class="my-3" id="nickname" >닉네임이 와야해</h3></a>
    
      
    <h5  class="my-5"> <pre id="contents">  내용이와야해 </pre></h5>
      <p id="reg_dttm"> 작성일시 </p>
    </div>

  </div>
  <!-- /.row -->

  <!-- Related Projects Row -->
  <h3 class="my-4">맛집 이미지  </h3>
  <!-- 템플릿가져오기-->
  <div class="row" id="uploadFileList">

  </div>
  <!-- /.row -->
  <%@include file="../goodchoiceboard/gReply.jsp" %>

</div>
<!-- /.container -->
		
		
		<a style="cursor: pointer;"  onclick="imgON(this.data)" ><img src="" alt="" /></a>
		
	<!-- 템플릿 작성  -->
	<script id="fileTemplate" type="text/x-handlebars=template">
    <div class="col-md-3 col-sm-6 mb-4">
		
		<img id="{{originalFileUrl}}" src="{{imgSrc}}" class="img-fluid" alt="...loding" style="cursor: pointer;"  onclick="imgON(this.id)" />
		
</div>
	</script>
<%@include file="../footer.jsp" %>
<%--plugin_js.jsp--%>
<!-- 라이트박스 css 갖고있음  -->
<%@ include file="/resources/js/good/plugin_js.jsp" %>
<%--Handlebars JS--%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.0.11/handlebars.min.js"></script>
<%--파일업로드 JS--%>
<script type="text/javascript" src="resources/js/good/upload_file.js"></script>
<script>

 function imgON(src){
	var imgsrc=src;
	console.log(imgsrc);
	
	// 원본 이미지 요청 링크
	$('#imgcom').attr('src',imgsrc);
} 
</script>
</body>
</html>