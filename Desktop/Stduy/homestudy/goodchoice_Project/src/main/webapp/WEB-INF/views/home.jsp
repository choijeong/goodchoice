<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<meta charset="UTF-8">
<title>GOODCHOICE_HOME</title>
<%--파일업로드 JS--%>
<script type="text/javascript" src="resources/js/good/upload_file.js"></script>
	
<script>
$(function(){
	//좋아요 순 
	hitList();
	/*
	//조회수 순 
  	viewList();
	//작성날짜 순
	dttmList(); */
})
	function hitList(){
	$.ajax({
		url: 'hitList',
		type: 'POST',
		dataType: 'JSON',
		success:function(rs){
			console.log("home hitlist start")
		/* 	
			console.log(rs)
			console.log(rs.hitList)
			console.log(rs.hitList[0])
			console.log(rs.hitList.length) */
			var hitlist=rs.hitList;
		
			/* console.log(hitlist[0].indexkey);
			console.log(hitlist[1].indexkey);
			console.log(hitlist[2].indexkey); */
			
			$('#carousel-item-one').attr('data-indexkey', hitlist[0].indexkey);
			$('#carousel-item-two').attr('data-indexkey', hitlist[1].indexkey);
			$('#carousel-item-three').attr('data-indexkey', hitlist[2].indexkey);
			
			$('#h2-one a').text(hitlist[0].title);
			$('#h2-two a').text(hitlist[1].title);
			$('#h2-three a').text(hitlist[2].title);
			
			$('#h2-one a').attr('href','goodChoiceView?indexkey='+hitlist[0].indexkey);
			$('#h2-two a').attr('href','goodChoiceView?indexkey='+hitlist[1].indexkey);
			$('#h2-three a').attr('href','goodChoiceView?indexkey='+hitlist[2].indexkey);
			 
			$('#p-one').text(hitlist[0].contents);
			$('#p-two').text(hitlist[1].contents);
			$('#p-three').text(hitlist[2].contents);
			
			if(hitlist.length > 0){
				for(i=0; i < hitlist.length; i++){
					console.log(hitlist[i].indexkey);
					
					getFileimg2(hitlist[i].indexkey);
				}
			} 
		}
	
	})
}
function viewList(){
	$.ajax({
		url: 'viewList',
		type: 'POST',
		dataType: 'JSON',
		success:function(rs){
			console.log("home viewList start") 
			var viewlist=rs.viewList;
			var html="";
		//	$('.viewList').empty();
			if(viewlist.length > 0){
				for(i=0; i < viewlist.length; i++){
					html += '<ul>'
					html += '<li>'+viewlist[i].indexkey+'</li>'
					html += '<li><a href="goodChoiceView?indexkey='+viewlist[i].indexkey+'">'+viewlist[i].title+'</a></li>'
					html += "</ul>"
				}
			$('#viewList').html(html);		
			}
		}
	})
}

function dttmList(){
	$.ajax({
		url: 'dttmList',
		type: 'POST',
		dataType: 'JSON',
		success:function(rs){
			console.log("home dttmList start")
			var dttmlist=rs.dttmList;
			var html="";
		//	$('.dttmLsit').empty();
			if(dttmlist.length > 0){
				for(i=0; i < dttmlist.length; i++){
					html += '<ul>'
					html += '<li>'+dttmlist[i].indexkey+'</li>'
					html += '<li><a href="goodChoiceView?indexkey='+dttmlist[i].indexkey+'">'+dttmlist[i].title+'</a></li>'
					html += "</ul>"
				}
			$('#dttmLsit').html(html);		
			}
		}
	})
}

</script>
<style type="text/css">
.carousel-item {
  height: 100vh;
  min-height: 350px;
  background: no-repeat center center scroll;
  -webkit-background-size: cover;
  -moz-background-size: cover;
  -o-background-size: cover;
  background-size: cover;
}
a:hover{
text-decoration:none !important;
}

</style>

</head>
<body>
  
	   <%@include file="header.jsp" %> 
	   <%@include file="nav.jsp" %> 
	
	<header>

  <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
    <ol class="carousel-indicators">
      <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
      <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
      <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
    </ol>
	<div class="carousel-inner" role="listbox">
	<span class="badge badge-danger">Hot BEST 3</span>
	<br />
	<!-- 	좋아요순 베스트 맛집 3곳// 슬라이드  -->
	 <!-- Slide One - Set the background image for this slide in the line below -->
      <div id="carousel-item-one" data-indexkey="" class="carousel-item active" style="background-image: url('https://source.unsplash.com/LAaSoL0LrYs/1920x1080')">
        <div class="carousel-caption d-none d-md-block">
          <h2 id="h2-one" class="display-4"><a href="" style="color: #fff;" >First Slide</a></h2>
          <p id="p-one" class="lead">This is a description for the first slide.</p>
        </div>
      </div>
      <!-- Slide Two - Set the background image for this slide in the line below -->
      <div id="carousel-item-two" data-indexkey="" class="carousel-item" style="background-image: url('https://source.unsplash.com/bF2vsubyHcQ/1920x1080')">
        <div class="carousel-caption d-none d-md-block">
          <h2 id="h2-two" class="display-4" ><a href="" style="color: #fff;">Second Slide</a></h2>
          <p id="p-two" class="lead">This is a description for the second slide.</p>
        </div>
      </div>
      <!-- Slide Three - Set the background image for this slide in the line below -->
      <div id="carousel-item-three" data-indexkey="" class="carousel-item" style="background-image: url('resources/img/cook.jpg')">
        <div class="carousel-caption d-none d-md-block">
          <h2 id="h2-three" class="display-4"><a href="" style="color: #fff;">Third Slide</a></h2>
          <p id="p-three" class="lead">This is a description for the third slide.</p>
        </div>
      </div>
	
	

    </div>
	   <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
          <span class="carousel-control-prev-icon" aria-hidden="true"></span>
          <span class="sr-only">Previous</span>
        </a>
    <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
          <span class="carousel-control-next-icon" aria-hidden="true"></span>
          <span class="sr-only">Next</span>
        </a>
	  </div>
</header>


	<div class="jumbotron">
  <h1 class="display-3">GOODCHOICE</h1>
  <p class="lead">맛집 정보를 공유하는 웹사이트 입니다 :D</p>
  <hr class="my-4">
  <p>버튼을 클릭하시면 맛집 리스트로 이동합니다</p>
  <p class="lead">
    <a class="btn btn-primary btn-lg" href="goodList" role="button">맛집 리스트 바로가기</a>
  </p>
</div>


	
	<%@include file="footer.jsp" %>
	
	<!-- 라이트박스 css 갖고있음  -->
<%@ include file="/resources/js/good/plugin_js.jsp" %>

</body>
</html>