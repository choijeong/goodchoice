<%@page import="com.hoon.goodchoice.gooddto.PageClass"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>



<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>



<meta charset="UTF-8">
<title>GOODCHOICE_GOODLIST</title>
</head>
<body>



	<%@include file="../header.jsp" %>
	<%@include file="../nav.jsp" %> 
<script>
// 페이지가 실행되면  시작되는 function들 
	$(function(){
		
		//order by 정렬 순서 // 최신순 좋아요순 
		goodorder();
		//검색어, 검색카테고리
		search();
		
	//	각 게시글별 indexkey를 가져오기
		console.log("게시글 목록 리스트 업로드파일이미지 가져오기 시작 ")
		$('#goodlist #ulgoodlist').each(function(index){
		console.log(index+'='+$(this).attr("data-index"))
		console.log("getFileimg("+$(this).attr('data-index')+")");
		//각 게시글별 첨부된 파일 이미지 불러오기 
//		getFileimg($(this).attr('data-index'));
		getFileimg3($(this).attr('data-index'));
		})
		
		
		
		var type= $('#hiddenType').val();
		
		
		if(type == 0){
			$('#listtype').text("전체");
		}else if(type == 1){
			$('#listtype').text("한식");
		}else if(type == 2){
			$('#listtype').text("중식");
		}else if(type == 3){
			$('#listtype').text("일식");
		}else if(type == 4){
			$('#listtype').text("양식");
		}else if(type == 5){
			$('#listtype').text("기타");
		} 
		for(var i=0; i < 6; i++){
		$('#btn'+i).attr("class","btn btn-secondary")
		}
		
		$('#btn'+type).attr("class","btn btn-primary");
		
	});
	

	function search(){
		var $searchType= $('#searchType');
		var $keyword= $('#keyword');
		/* searchtype의 val은 pageClass에담겨온 값 에 속성추가 ++selected는 true   */
		$searchType.val('${pageClass.pBase.searchType}').prop("selected",true);
		
		var $goodorder=$('#goodorder');
		$goodorder.val('${pageClass.pBase.goodorder}').prop("selected",true);
		
		$('#searchBtn').on('click',function(){
			var searchTypeVal=$searchType.val();
			var keywordVal=$keyword.val();
			var goodorderVal=$goodorder.val();
			var type= $('#hiddenType').val();
			
			console.log("123456789 rrrrrrr")
			console.log(searchTypeVal);
			if(!searchTypeVal){
				alert("검색 조건을 선택 해 주세요");
				$searchType.focus();
				return;
			}else if(!keywordVal){
				alert("검색어를 입력 해 주세요");
				$keyword.focus();
				return;
			}
			var url="";
			if(!goodorderVal){
				 url="goodList?userPage=1"
						+"&pageRec="+"${pageClass.pBase.pageRec}"
						+"&searchType="+encodeURIComponent(searchTypeVal)
						+"&keyword="+encodeURIComponent(keywordVal)
						+"&type="+encodeURIComponent(type)
			} else {
				url="goodList?userPage=1"
					+"&pageRec="+"${pageClass.pBase.pageRec}"
					+"&goodorder="+encodeURIComponent(goodorderVal)
					+"&searchType="+encodeURIComponent(searchTypeVal)
					+"&keyword="+encodeURIComponent(keywordVal);
					+"&type="+encodeURIComponent(type)
			}
			
					window.location.href = url;
		});
	}

     	function goodorder(){
    		var $goodorder=$('#goodorder');
    		$goodorder.val('${pageClass.pBase.goodorder}').prop("selected",true);
    		
    		// 해당 셀렉트 박스에 change event binding 하기
    		$("#goodorder").change(function() {
    		console.log($(this).val());
    		console.log($(this).children("option:selected").text());
    		
    		var goodorderVal = $(this).val();
			
    		var $searchType= $('#searchType');
    		var $keyword= $('#keyword');
    		var searchTypeVal=$searchType.val();
			var keywordVal=$keyword.val();
			var type= $('#hiddenType').val();
			var url="";
			console.log(searchTypeVal);
			
			if(!keywordVal){
				url="goodList?userPage=1"
					+"&pageRec="+"${pageClass.pBase.pageRec}"
					+"&goodorder="+encodeURIComponent(goodorderVal)
					+"&type="+encodeURIComponent(type)
			}
			else {
				url="goodList?userPage=1"
					+"&pageRec="+"${pageClass.pBase.pageRec}"
					+"&goodorder="+encodeURIComponent(goodorderVal)
					+"&searchType="+encodeURIComponent(searchTypeVal)
					+"&keyword="+encodeURIComponent(keywordVal)
					+"&type="+encodeURIComponent(type)
			}
			
				window.location.href = url;
				
    		});
    	}
</script>
<script>
// 타입별 목록 불러오기 
function goodType(type){
	
	
	$('#hiddenType').attr("value",type);
	var url="";
	var type= $('#hiddenType').val();
	var searchTypeVal=$('#searchType').val();
	var keywordVal=$('#keyword').val();
	var goodorderVal=$('#goodorder').val();
	console.log(type)

	
	
	//검색도 정렬도 안한 경우
	if(!goodorderVal && !keywordVal){
		url="goodList?userPage=1"
			+"&pageRec="+"${pageClass.pBase.pageRec}"
			+"&type="+encodeURIComponent(type)
	
			//굳 오더가 없고 검색만 한경우
	}else if(!goodorderVal){
		 url="goodList?userPage=1"
				+"&pageRec="+"${pageClass.pBase.pageRec}"
				+"&searchType="+encodeURIComponent(searchTypeVal)
				+"&keyword="+encodeURIComponent(keywordVal)
				+"&type="+encodeURIComponent(type)
	
	//검색하지 않고 오더만 한경우
	}else if(!keywordVal){
		url="goodList?userPage=1"
			+"&pageRec="+"${pageClass.pBase.pageRec}"
			+"&goodorder="+encodeURIComponent(goodorderVal)
			+"&type="+encodeURIComponent(type)
	//검색도 정렬도 타입도 다 선택된 경우
	}else {
		url="goodList?userPage=1"
			+"&pageRec="+"${pageClass.pBase.pageRec}"
			+"&goodorder="+encodeURIComponent(goodorderVal)
			+"&searchType="+encodeURIComponent(searchTypeVal)
			+"&keyword="+encodeURIComponent(keywordVal);
			+"&type="+encodeURIComponent(type)
	}
			window.location.href = url;
			
}
</script>
 
	 <br />
	  <div class="container">
    
     <input type="hidden" id="hiddenType" value="${pageClass.pBase.type}" />
    <button id="btn0" type="button" value="0" onclick="goodType(this.value)" class="btn btn-primary">전체</button>
	<button id="btn1" type="button" value="1" onclick="goodType(this.value)" class="btn btn-secondary">한식</button>
	<button id="btn2" type="button" value="2" onclick="goodType(this.value)" class="btn btn-secondary">중식</button>
	<button id="btn3" type="button" value="3" onclick="goodType(this.value)" class="btn btn-secondary">일식</button>
	<button id="btn4" type="button" value="4" onclick="goodType(this.value)" class="btn btn-secondary">양식</button>
	<button id="btn5" type="button" value="5" onclick="goodType(this.value)" class="btn btn-secondary">기타</button>
	<a style="background-color: #2700ff; border-color: #2700ff;" class="btn btn-primary" href="goodList" role="button">처음으로</a>
	<br />
	
		 <div class="search">
	 		정렬순서
	      <select style="width: 20%; text-indent: 36%;"  id = "goodorder" name="goodorder">
	   	   <option  value="">::구분::</option>
	       <option value="hit_count">좋아요순</option>
	       <option value="view_count">조회수순</option>
	       <option value="reg_dttm">최신순 </option>
	      </select>
	      
	  		   검색하기 
	      <select  style="width: 20%;  text-indent: 36%;"   id = "searchType" name="searchType">
	   	   <option value="">::검색::</option>
	       <option value="title">제목 </option>
	       <option value="contents">내용</option>
	       <option value="nickname">닉네임</option>
	       <option value="titlecon">제목+내용</option>
	       <option value="all">전체조건</option>
	      </select>
	   	  <input  style="width: 20%; text-indent: 10px;"  name="keyword" id="keyword" value="${pageClass.pBase.keyword}" class = "mr-sm-2"type = "text" placeholder = "검색어 입력">
	    	<button type="button" class="btn btn-outline-info" id="searchBtn">검색</button>
	    	&nbsp;
	    	&nbsp;
	    	<button type="button" class="btn btn-outline-info" ><a class="middle" href="goodWriteView">글작성</a></button>
 	   </div>
 	 
	 <hr />
			
	 
	<%--
	전체레코드= ${pageClass.totalRec} <br />
	유저페이지= ${pageClass.pBase.userPage}<br />
	서치타입= ${pageClass.pBase.searchType }<br />
	키워드= ${pageClass.pBase.keyword }<br />
	니드타입= ${pageClass.pBase.type }<br /> 
	니드서치= ${pageClass.needSearch }<br />
	--%>
	
	      <!-- Page Heading -->
      <h1 class="my-4">맛집 리스트
        <small id="listtype" style="color: #007bff;"> </small>
    
      </h1>
<div id="goodlist">
<c:forEach items="${goodList}" var="goodlist">

      <!-- Project -->
      <div class="row" id="ulgoodlist" data-index="${goodlist.indexkey}">
        <input type="hidden" id="goodList_indexkey" value="${goodlist.indexkey }"/>
        <div class="col-md-7" id="li_${goodlist.indexkey}">
          
        </div>
        <div class="col-md-5">
          <h3>${goodlist.title}</h3>
          <span class="badge badge-info">좋아요 ${goodlist.hit_count }</span>
          <span class="badge badge-warning">조회수 ${goodlist.view_count }</span>
          <pre>${goodlist.contents}</pre>
          <a class="btn btn-primary"id="goodChoice_href${goodlist.indexkey}" href="goodChoiceView${pageClass.makeQuery(pageClass.pBase.userPage,pageClass.needOrder,pageClass.needSearch,pageClass.needType)}&indexkey=${goodlist.indexkey }">View Project</a>
        </div>
      </div>
      <hr>
      <!-- /.row -->
</c:forEach>
      </div>
 
	

  <!-- Pagination -->
      <ul class="pagination justify-content-center">
       <c:if test="${pageClass.prev}">
        <li class="page-item">
          <a class="page-link" href="goodList${pageClass.makeQuery((pageClass.page_startNum)-1,pageClass.needOrder,pageClass.needSearch,pageClass.needType)}" aria-label="Previous">
            <span aria-hidden="true">&laquo;</span>
            <span class="sr-only">Previous</span>
          </a>
        </li>
         </c:if>	
          <c:forEach begin="${pageClass.page_startNum }" end="${pageClass.page_endNum }" var="index">
        <li class="page-item">
          <a class="page-link" href="goodList${pageClass.makeQuery(index,pageClass.needOrder,pageClass.needSearch,pageClass.needType) }">${index }</a>
        </li>
         </c:forEach>
        <c:if test="${pageClass.next }">
        <li class="page-item">
          <a class="page-link" href="goodList${pageClass.makeQuery((pageClass.page_endNum)+1,pageClass.needOrder,pageClass.needSearch,pageClass.needType)}" aria-label="Next">
            <span aria-hidden="true">&raquo;</span>
            <span class="sr-only">Next</span>
          </a>
        </li>
        </c:if> 
      </ul>
</div>

	<%@include file="../footer.jsp" %>


<!-- 라이트박스 css 갖고있음  -->
<%@ include file="/resources/js/good/plugin_js.jsp" %>
<%--파일업로드 JS--%>
<script type="text/javascript" src="resources/js/good/upload_file.js"></script>
	
</body>
</html>