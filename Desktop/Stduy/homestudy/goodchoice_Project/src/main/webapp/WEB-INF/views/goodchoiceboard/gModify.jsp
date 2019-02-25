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
<title>GOODCHOICE_수정하기</title>
<style type="text/css">
	#fileDrop{
	width:100%;
	height:200px;
	border: 2px dotted gray;
	background-color: #ccc;
	}
</style>
</head>
<body>
<%@include file="../header.jsp" %>
<%@include file="../nav.jsp" %>
	<script src="resources/js/good/goodModify.js"></script>

    <form method="post" id="modifyForm" name="modifyForm" enctype="mulitpart/form-data">
  <fieldset style="width: 60%; margin: 0 auto;">
    <legend style="text-align: center;">게시글 수정</legend>
    <div class="form-group row">
      <label for="staticEmail" class="col-sm-2 col-form-label">작성자</label>
      <div class="col-sm-10">
      <input type="hidden" name="indexkey" id="indexkey" value="${indexkey }" />
       <input type="hidden" name="member_indexkey" id="member_indexkey" value="${sessionId.indexkey }"> 
        <input type="text" name="member_nickname" readonly="" class="form-control-plaintext" id="staticEmail" value="${sessionId.nickname}">
      </div>
    </div>
    <div class="form-group">
      <label for="title">제목</label>
      <input type="text" class="form-control" id="title" name="title" aria-describedby="emailHelp" placeholder="제목을 입력해 주세요">
      
    </div>

    <div class="form-group">
      <label for="type">맛집 구분</label>
      <select class="form-control" id="type"  name="type">
      				<option>::분류::</option>
					<option value="1">한식</option>
					<option value="2">중식</option>
					<option value="3">일식</option>
					<option value="4">양식</option>
					<option value="5">기타</option>
      </select>
      <small id="typeHelp" class="form-text text-muted">종류를 꼭 선택해 주세요</small>
    </div>
    
    <div class="form-group">
      <label for="contents">내용</label>
      <textarea class="form-control" id="contents" name="contents" rows="3" placeholder="내용 입력" style="margin-top: 0px; margin-bottom: 0px; height: 173px;"></textarea>
    </div>
    <div class="form-group">
      <label for="fileDrop">File Upload</label>
     
     	<div id="fileDrop">
					<br />
					<br />
					<br />
					<br />
					<p style="text-align: center;">첨부파일을 드래그 해 주세요</p>	
		</div>
  
      <small id="fileHelp" class="form-text text-muted">파일을 하나씩 드래그 해주세요</small>
    </div>

   
  	<div class="box-footer">
		<div class="container">
  			<ul class="row" id="uploadFileList" >
		
		
			</ul>
		</div>
	</div>
	<div class="pull-right">		
   <!--  <button type="button" id="writeBtn" value="writeBtn"  class="btn btn-primary" href="{fullName}">글작성</button> -->
  	<button class="btn btn-lg btn-primary btn-block text-uppercase" type="button" id="goodModifyOkBtn" value="goodModifyOkBtn" href="{fullName}" >게시글수정</button>
	
	 <a class="d-block text-center mt-2 small" href="goodList${pBase.makeQuery()}">이전 게시글 목록</a> 
               <a class="d-block text-center mt-2 small" href="home">HOME</a>              
  	</div>
<hr class="my-4">

						<input id="userPage" type="hidden" name="hidden" value="${pBase.userPage}"/>
						<input id="pageRec" type="hidden" name="hidden" value="${pBase.pageRec}"/>
						<input id="searchType" type="hidden" name="hidden" value="${pBase.searchType}"/>
						<input id="keyword" type="hidden" name="hidden" value="${pBase.keyword}"/>
						<input id="goodorder" type="hidden" name="hidden" value="${pBase.goodorder}"/>
						<input id="hiddentype" type="hidden" name="hidden" value="${pBase.type}"/>
		

 	
  </fieldset>
</form>

	<script src="resources/js/good/goodModifyOkBtn.js"></script>

<%@include file="../footer.jsp" %>

<script id="fileTemplate" type="text/x-handlebars-template">

<li style="list-style:none;" class="col-xl-3 col-md-6 mb-4">
      <div class="card border-0 shadow">
        <img src="{{imgSrc}}" class="card-img-top" alt="...loding">
        <div class="card-body text-center">
        	<h5 class="card-title mb-0">  
        	<a href="{{originalFileUrl}}" id="filename" onclick="window.open(this.href, 'originalFile','scrollbars=yes,width=417,height=385,top=10,left=20');return false;" target="_blank">
				<i id="fileicon"></i>
				 {{originalFileName}}
				</a>	
			</h5>
          <div class="card-text text-black-50"><a href="{{fullName}}" id="delBtn">지우기</a></div>
        </div>	
      </div>
    </li>

</script>
<%--plugin_js.jsp--%>
<!-- 라이트박스 css 갖고있음  -->
<%@ include file="/resources/js/good/plugin_js.jsp" %>
<%--Handlebars JS--%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.0.11/handlebars.min.js"></script>
<%--파일업로드 JS--%>
<script type="text/javascript" src="resources/js/good/upload_file.js"></script>

</body>
</html>