<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <link rel="stylesheet" href="resources/bootstrap/css/bootstrap.css" media="all" /> 
<script type="text/javascript">
	$(function(){
		goodReplyList();
	});
		var userPage=1;
		
		function goodReplyList(userPage){
			var good_indexkey = $('#indexkey').text();
			/* var userPage = $('#re_userPage').val();
			var pageRec = $('#re_pageRec').val(); */
			/* 초기화 */
		
				console.log(" 리플 ajax 시작")
				/* console.log(userPage)
				console.log(pageRec) */
			var data = {'good_indexkey' : good_indexkey ,'userPage' : userPage  }
			
			$.ajax({
				url : 'goodReplyList',
				type : 'POST',
				dataType : 'JSON',
				data : data,
				
				success : function(rs){
					console.log("goodReplyList start")
					/* console.log(rs.goodReplyList);
					console.log(rs.pageClass); */
					var $goodReplyList = rs.goodReplyList;
					
					var html="";
					//총댓글
					var reply_count = rs.pageClass.totalRec;
					/* console.log(reply_count); */
					var hiddennickname= $('#hiddennickname').val();
				/* 	console.log(hiddennickname) */
					
					
				
					
					
					
					
					//보여줄 리스트
				if($goodReplyList.length > 0){
					
					for(i=0; i <$goodReplyList.length; i++){
						
						console.log("reply dttm start")
						var reg_dttm= $goodReplyList[i].update_dttm;
						console.log(reg_dttm)
						// 파씽된 데이터는 문자열 변수로 인식된 상태로 아래와 같이 숫자로 변경해야 한다.
						var reg=Number(reg_dttm);
						console.log(reg);
						var reg2=reg-'32400000';
						console.log(reg2)
						// JavaScript는 날짜/시간을 다루기 위한 Date 객체를 제공하며 Timestamp 값을 받아 새로운 객체를 생성할 수 있다.
						var dttm = new Date(reg);
						var dttm2 = new Date(reg2);
						console.log(dttm);
						console.log(dttm2);
						var reg_dttm1=dttm2.toLocaleDateString();					
						var reg_dttm2=dttm2.toLocaleTimeString();					
						console.log(reg_dttm1 + reg_dttm2);
						var up_dttm=reg_dttm1 +" "+ reg_dttm2;
						
						html += "<div class='row'>";
						console.log($goodReplyList[i].reply_step)
						for(j=0; j<$goodReplyList[i].reply_step; j++){
							
							html += "<div class='col-md-1' style='max-width: 18px;'>";
							html +='<img src="resources/img/arrow.png" style="width:16px; text-align:left;" alt="덧글" />'							
		                    html += "</div>";
						}
					html += "<div class='col-md-11' style='max-width: 98%;'>";
	                    html += "<table class='table'><h6><strong>"+$goodReplyList[i].member_nickname+" 님</strong> ";
	                    
	                    if($goodReplyList[i].member_nickname == hiddennickname){
							html += '<button type="button" class="btn btn-outline-success" style="float:right;" onclick="goodReplyDelete('+$goodReplyList[i].indexkey+')">삭제</button>';
							html += '<button type="button" class="btn btn-outline-success" style="float:right;  margin-right: 10px;" onclick="goodReplyModifyView('+$goodReplyList[i].indexkey+')">수정</button>';
	                    }
	                    	html +="</h6>"
	                        html += "<pre id='outcontents"+$goodReplyList[i].indexkey+"'>"+ $goodReplyList[i].contents + "</pre>";
	                        html += "<p>"+up_dttm+"</p>";
	                        html += "<div class='container' id='replyModify"+$goodReplyList[i].indexkey+"' style='display: none' ></div>";
	                        html += '<tr><td><input id="outreplyBtn'+$goodReplyList[i].indexkey+'" class="btn pull-right btn-success" type="button" value="답글작성" onclick="goodReplyReplyFunc('+$goodReplyList[i].indexkey+')"/></td></tr>';
		                    html += "</table></div>";
		                    html += "</div>";
	                    }
	    
				
				/* 페이징 처리 */	
					var pageClass = rs.pageClass;
				// 데이터 삽입될 객체 이전 데이터 삭제
				var $ulpagination= $('#ulpagination');
				$ulpagination.empty();
				
					var re_pageAll = pageClass.pageAll;
					var re_page_startNum = pageClass.page_startNum;
					var re_page_endNum = pageClass.page_endNum;
					var re_page_block = pageClass.page_block;
					var re_pageRec=pageClass.pBase.pageRec;
					var re_userPage=pageClass.pBase.userPage;
					var re_totalRec=pageClass.totalRec;
					var re_next=pageClass.next;
					var re_prev=pageClass.prev;
					
					console.log("List.pageAll="+re_pageAll);
					console.log("List.userPage="+re_userPage);
					console.log("List.re_next="+re_next);
					
					
					
					
					if(re_prev != false){
					$ulpagination.append('<li class="page-item"><a  class="page-link" id="re_prev" style="cursor: pointer;">이전</a></li>');
					}
					
					for(var i=re_page_startNum; i <=re_page_endNum; i++) {
						
						if(re_userPage == i){
							$ulpagination.append('<li  class="page-item" id="disabled"><a class="page-link" >'+i+'</a></li>'); // 보고있는 페이지는 비활성화
							$('#hidden_userPage').val(i);
						}else{
//							$ulpagination.append('<li><a id="re_userPageGo" pagedata="'+i+'">['+i+']</a></li>'); //  안보고있는 페이지는 활성화
							$ulpagination.append('<li class="page-item"><a onclick="goodReplyList('+i+')" class="page-link" style="cursor: pointer;">'+i+'</a></li>'); //  안보고있는 페이지는 활성화
							console.log(i);
						}
					
					}
					
					if(re_next != false){
					$ulpagination.append('<li class="page-item"><a id="re_next" class="page-link" style="cursor: pointer;" >다음</a></li>');
					}
					
			
 
 					$('#re_prev').click(function(){
 						userPage= re_page_startNum-1;
 						userPageFlag=1;
 						goodReplyList(userPage);
 						userPageFlag=0;
 					})
 					
 					$('#re_next').click(function(){
 						userPage= re_page_endNum+1;
 						userPageFlag=1;
 						goodReplyList(userPage);
 						userPageFlag=0;
 					})
 					
 				
 						
					}else{
						
						// 데이터 삽입될 객체 이전 데이터 삭제
						var $ulpagination= $('#ulpagination');
						$ulpagination.empty();
						
						html += "<div>";
						html += "<div><table class='table'><h6><strong>등록된 댓글이 없습니다.</strong></h6>";
						html += "</table></div>";
						html += "</div>";
					}
					
					$('#reply_count').html(reply_count);
					$('#replycnt').text("댓글수 "+reply_count);
					$('#replyList').html(html);
			//success : function(rs){
				}
			//$.ajax({		
			});
	//function goodReplyList(){				
		}

</script>

<div class="container">
	<form id="goodReplyWriteForm" name="goodReplyWriteForm" method="POST">
	<br ><br >
		<div>
			<span><strong>Reply</strong></span> <span id="reply_count">댓글수 카운트</span>
		</div>
		<div>
			<table class="table">
				<tr>
					<td>
						<input type="hidden" name="member_nickname" id="member_nickname" value="${sessionId.nickname}"/>				
						<input type="hidden" name="good_indexkey" id="good_indexkey" value="${indexkey}" />	
						<textarea style="width:100%;" name="contents" id="replycontents" cols="30" rows="3" placeholder="내용을 입력해 주세요"></textarea>		
					<br>
						<div>
							<input class="btn pull-right btn-success"  type="button" value="댓글작성" onclick="goodReplyWriteFunc()"/>			
						</div>
					</td>
				</tr>
			</table>
		</div>
	</form>
</div>
<div class="container">
	<form id="replyListForm" name="replyListForm" method="POST">
		<div id="replyList">
		
		</div>
	</form>
</div>

		<div id="pagination">
			<ul id="ulpagination" class="pagination justify-content-center">
			<!-- 페이징 처리 되면 사라져야 하는 부분  -->
			<li ><a data-page="2" >123</a></li>
			<li id="re_userPageGo" data-page="2"><a>[22]</a></li>
			<li><button id="re_userPageGoBtn" value="+i+"></button></li>
			</ul>
			<!-- 현재 보고있는 댓글 페이지 정보를 갖는다 -->
			<input type="hidden" id="hidden_userPage" value="" />
			
		</div>
		
		
	<script>
	//댓글 작성
	function goodReplyWriteFunc(){
		var member_indexkey = $('#hiddensession').val();
		if(!member_indexkey){
			var message=confirm("로그인시 댓글 사용 가능합니다. 로그인 하시겠습니까?");
			if(message == true){
				loginGo();
				function loginGo(){
					location.href='memberLoginView'
				}
			return;
			}
		}
		var $contents= $('#replycontents');
		if(!$contents.val()){
			alert("내용을 입력해 주세요");
			$contents.focus();
			return;
		}
		var data  = $('#goodReplyWriteForm').serialize();
		console.log(data);
		
		$.ajax({
			url : 'goodReplyWrite',
			type : 'POST',
			dataType : 'JSON',
			data : data,
			success : function(rs){
				if(rs.result == 1){
					alert('게시글 작성 성공');
					goodReplyList(userPage);
					$('#replycontents').val("");
				} else {
					alert('게시글 작성 실패');
				}
				
			}
		});
	}
	
	</script> 

	
	<script>
 	//대댓글 작성 폼 가져오기 
	//대댓글을 작성하려면 원글의 정보가 있어야 원글 바로 아래서 작성 가능 , 수정하기와 같은 맥락
		function goodReplyReplyFunc(indexkey) {
 		
 			var member_indexkey = $('#hiddensession').val();
 			if(!member_indexkey){
 				var message=confirm("로그인시 댓글 사용 가능합니다. 로그인 하시겠습니까?");
 			
 				if(message == true){
 					loginGo();
 					
 					function loginGo(){
 						location.href='memberLoginView'
 					}
 				}
 			}	
 	
			$.ajax({
				url : 'goodReplyReply',
				type : 'POST',
				dataType : 'json',
				data : {
					'indexkey' : indexkey
				},
				success : function(rs) {
					//어느 원글,댓글에 댓글을 다는지 구분
					var indexkey=rs.replyinfo.indexkey;
					
					//원글의 indexkey ,원글과 댓글을 하나로 묶어줄 오리지날번호
					var reply_origin=rs.replyinfo.reply_origin;
					
					//부모 그룹//원글과 댓글의 순서를 그룹으로 묶어 원글-댓글-대댓글 순서를 잡아준다
					var reply_group=rs.replyinfo.reply_group;
					console.log("부모그룹가져오기")
					console.log(reply_group)
					
					
					//부모스텝 댓글 대댓글 대대댓글 스텝을 알려준다
					var reply_step=rs.replyinfo.reply_step;
					
					
					//댓글 다는 사용자의 닉네임 write폼에 저장해 둔 세션값으로 가져온다
					var member_nickname=$('#hiddennickname').val();
					console.log(member_nickname)
					
					
					//현제 페이지의 게시글 인덱스키
					var good_indexkey=rs.replyinfo.good_indexkey;
					
					var userPage=$('#hidden_userPage').val();
					var html ="";
					
					html += "<form id='goodReplyReplyForm' name='goodReplyReplyForm' method='POST'><br ><br ><div><table class='table'><tr><td>";
					html += '<input type="hidden" name="good_indexkey" id="re_good_indexkey" value="'+good_indexkey+'" />';
					html += '<input type="hidden" name="member_nickname" id="re_member_nickname" value="'+member_nickname+'"/>';
					html += '<input type="hidden" name="reply_origin" id="reply_origin" value="'+reply_origin+'"/>';
					html += '<input type="hidden" name="reply_group" id="reply_group" value="'+reply_group+'"/>';
					html += '<input type="hidden" name="reply_step" id="reply_step" value="'+reply_step+'"/>';
					html += '<textarea style="width:900px" name="contents" id="re_contents" cols="30" rows="3" placeholder="내용을 입력해 주세요"></textarea>';
					html += "<br><br>";
					html += '<input class="btn pull-left btn-success" type="button" value="답글" onclick="goodReplyReplyOk()"/>';
					html += '&nbsp&nbsp<input class="btn pull-left btn-success" type="button" value="취소" onclick="goodReplyList('+userPage+')"/>';
					html += "</td></tr></table></div></form>";
					$('#replyModify'+indexkey).show();
					/* $('#outcontents'+indexkey).hide(); */
					 $('#outreplyBtn'+indexkey).hide(); 
					$('#replyModify'+indexkey).html(html);
				}
			});
		}
		
	</script>
	
	<script>
	//계층형 대댓글 작성
	
		function goodReplyReplyOk(){
			var $contents= $('#re_contents');
			if(!$contents.val()){
				alert("내용을 입력해 주세요");
				$contents.focus();
				return;
			}
			
			var good_indexkey = $('#re_good_indexkey').val();
			var member_nickname = $('#re_member_nickname').val();
			var reply_origin = $('#reply_origin').val();
			var reply_group = $('#reply_group').val();
			var reply_step = $('#reply_step').val();
			var contents = $('#re_contents').val();
			console.log("집어넣을 부모 그룹");
			console.log(reply_group);
			var data= {'good_indexkey' : good_indexkey ,
					'member_nickname' : member_nickname ,	
					'reply_origin' : reply_origin ,	
					'reply_group' : reply_group ,	
					'reply_step' : reply_step ,	
					'contents' : contents ,	
			}
			/* var data  = $('#goodReplyReplyForm').serialize(); */
			console.log(data);
		
			$.ajax({
				url : 'goodReplyReplyOk',
				type : 'POST',
				dataType : 'JSON',
				data : data,
				success : function(rs){
					if(rs.result == 1){
						alert('게시글 작성 성공');
						goodReplyList(userPage);
					} else {
						alert('게시글 작성 실패');
					}
					
				}
			}); 
		}
	
	</script> 
	
	<script>
	//모디파이
	
		function goodReplyModifyView(indexkey) {

			$.ajax({
				url : 'goodReplyModifyView',
				type : 'POST',
				dataType : 'json',
				data : {
					'indexkey' : indexkey
				},
				success : function(rs) {
					var modify_indexkey=rs.replyinfo.indexkey;
					var modify_contents=rs.replyinfo.contents;
					
					var html ="";
				
					html += "<form id='goodReplyModifyForm' name='goodReplyModifyForm' method='POST'><br ><br ><div><table class='table'><tr><td>";
					html += '<input type="hidden" name="indexkey" id="modifyindexkey" value="'+modify_indexkey+'"/>';
					html += '<textarea style="width:900px" name="contents" id="modifycontents" cols="30" rows="3" placeholder="내용을 입력해 주세요">'+modify_contents+'</textarea>';
					html += "<br><div>";
					html += '<input class="btn pull-left btn-success" type="button" value="수정완료" onclick="goodReplyModifyFunc()"/>';
					html += "</div></td></tr></table></div></form>";
					$('#replyModify'+modify_indexkey).show();
					$('#outcontents'+modify_indexkey).hide();
					$('#replyModify'+modify_indexkey).html(html);
				}
			});
		}
		
	</script>
	<script>
	// serialize: form의 input name이 배열 형태로 인코딩됨
	function goodReplyModifyFunc(){
		/* 
		var data = $('#goodReplyModifyForm').serialize(); */
		var data = {'indexkey' : $('#modifyindexkey').val() , 'contents' : $('#modifycontents').val()}
		console.log("data=");
		console.log(data);
		$.ajax({
			url : 'goodReplyModify',
			type : 'POST',
			dataType : 'json',
			data : data,
			success : function(rs) {
				var userPage=$('#hidden_userPage').val();
				if (rs.result == 1) {
					alert('댓글 수정 성공');
					goodReplyList(userPage);
				} else {
					alert('댓글 수정 실패');
				}
			}
		});
	}
	</script>
	<script>
	function goodReplyDelete(indexkey){
		var message=confirm("정말로 삭제 하시겠습니까?");
		if(message == true){
			goodReplyDeleteOk(indexkey);
		
			function goodReplyDeleteOk(indexkey){
				
				console.log(indexkey)
				var data ={'indexkey':indexkey};	
			
				$.ajax({
					url: "goodReplyDelete",
					type: "POST",
					dataType:"json",
					data: data,
					success: function(rs){
						var userPage=$('#hidden_userPage').val();		
						if(rs.result==1){
							alert("삭제 되었습니다")
							goodReplyList(userPage);
						}else{
							alert("삭제 실패하였습니다")
						}
					}
				//$.ajax({	
				});	
			//function goodDeleteOk(){
			}			
		}else{
			return false;
		}
	}
	
	</script>
	
	
	
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	