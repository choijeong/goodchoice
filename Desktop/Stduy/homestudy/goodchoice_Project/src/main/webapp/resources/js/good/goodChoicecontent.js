/**
 * 
 */
	$(function() {
		var good_indexkey= $('#indexkey').text();
		//첨부파일 목록 가져오기
		//upload_file.js 
		
		console.log("게시글정보 불러오기")
		goodChoicecontent();
		
		console.log("파일업로드정보 불러오기")
		getFiles(good_indexkey);
	
		});
		function goodChoicecontent() {
		
			var good_indexkey= $('#indexkey').text();
			var member_indexkey = $('#hiddensession').val();
			if(!member_indexkey){
				
				member_indexkey=0;
				/*console.log("세션값 없습니다");
				console.log(member_indexkey);*/
			}
			
			/*console.log("========");*/
			$.ajax({
				url : 'goodChoiceContent',
				type : 'POST',
				dataType : 'json',
				/* text공부 */
				/* data:{'userId'=$('#userId').text()}, */
				data : {
					'good_indexkey' : good_indexkey,
					'member_indexkey' : member_indexkey
						},
					
				
				success : function(rs) {
					/*console.log("rs=");
					console.log(rs);
					console.log("rs.content=");
					console.log(rs.content);
					console.log("rs.hitinfo=");
					console.log(rs.hitinfo);
					
					console.log(rs.content.reg_dttm);*/
					var reg_dttm=rs.content.reg_dttm;
					// 파씽된 데이터는 문자열 변수로 인식된 상태로 아래와 같이 숫자로 변경해야 한다.
					var reg=Number(reg_dttm);
					/*console.log(reg);*/

					// JavaScript는 날짜/시간을 다루기 위한 Date 객체를 제공하며 Timestamp 값을 받아 새로운 객체를 생성할 수 있다.
					var dttm = new Date(reg);
					/*console.log(dttm);*/
				
					var reg_dttm2=dttm.toLocaleDateString();					
					
				/*	console.log(reg_dttm2);*/

					//페이징 유지를 위해
					var userPage=$('#userPage').val();
					var pageRec=$('#pageRec').val();
					//검색어 유지
					var searchType=$('#searchType').val();
					var keyword=$('#keyword').val();
				
					//정렬유지
					var goodorder=$('#goodorder').val();
					//타입검색 유지
					var type=$('#hiddentype').val();
					
					$('#title').text(rs.content.title);
					$('#title2').text(rs.content.title);
					$('#anickname').attr('href', 'memberInfoView?nickname='+rs.content.member_nickname);
					$('#nickname').text(rs.content.member_nickname);
					$('#contents').text(rs.content.contents);
					$('#reg_dttm').text(reg_dttm2);
					$('#hit_count').text('좋아요 '+ rs.content.hit_count);
					$('#view_count').text('조회수 '+ rs.content.view_count);
				
				var type=rs.content.type;
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
						
					
					
					
					
					var hiddenindexkey=$('#hiddensession').val();
					
					if(hiddenindexkey == rs.content.member_indexkey){
						
						if(!goodorder || !keyword){
							$('#update').append('<a href="goodModifyView?userPage='+userPage+'&pageRec='+pageRec+'&type='+type+'&indexkey='+rs.content.indexkey+'" style="text-decoration:none; color:#000;">글 수정</a>');
						}else if(!goodorder || keyword != null){
							$('#update').append('<a href="goodModifyView?userPage='+userPage+'&pageRec='+pageRec+'&type='+type+'&searchType='+searchType+'&keyword='+keyword+'&indexkey='+rs.content.indexkey+'" style="text-decoration:none; color:#000;">글 수정</a>');
						}else if(!keyword || goodorder != null){
							$('#update').append('<a href="goodModifyView?userPage='+userPage+'&goodorder'+goodorder+'&type='+type+'&indexkey='+rs.content.indexkey+'" style="text-decoration:none; color:#000;">글 수정</a>');
						}else{
							$('#update').append('<a href="goodModifyView?userPage='+userPage+'&pageRec='+pageRec+'&type='+type+'&goodorder'+goodorder+'&searchType='+searchType+'&keyword='+keyword+'&indexkey='+rs.content.indexkey+'" style="text-decoration:none; color:#000;">글 수정</a>');
						}
						
						$('#del').append('<a id="goodDeleteBtn" value="goodDeleteBtn" onclick="goodDelete()" style="cursor: pointer;" style="text-decoration:none; color:#000;">글 삭제</a>');
					}
					
					if(!hiddenindexkey){
						$('#hit').append('<a onclick="needlogin()"><img src="resources/img/favorite-2.png" style="cursor: pointer; width: 20px;" style="text-decoration:none; color:#000;"></a>');
					
					}else{
						$('#hit_check').val(rs.hitinfo.hit_check);
						if(rs.hitinfo.hit_check == 0){
							$('#hit').append('<a onclick="upHit()" ><img id="img_hit" src="resources/img/favorite-2.png" style="cursor: pointer; width: 20px;" ></a>');
						}else if(rs.hitinfo.hit_check == 1){
							$('#hit').append('<a onclick="upHit()" ><img id="img_hit" src="resources/img/favorite-1.png" style="cursor: pointer; width: 20px;" ></a>');
						}
					}
					
					
					
					
					//게시글 목록 돌아가기
					if(!goodorder || !keyword){
						$('#list').append('<a href="goodList?userPage='+userPage+'&pageRec='+pageRec+'&type='+type+'" style="text-decoration:none; color:#000;">목록으로</a>');
					}else if(!goodorder || keyword != null){
						$('#list').append('<a href="goodList?userPage='+userPage+'&pageRec='+pageRec+'&type='+type+'&searchType='+searchType+'&keyword='+keyword+'" style="text-decoration:none; color:#000;">목록으로</a>');
					}else if(!keyword || goodorder != null){
						$('#list').append('<a href="goodList?userPage='+userPage+'&pageRec='+pageRec+'&type='+type+'&goodorder'+goodorder+'" style="text-decoration:none; color:#000;">목록으로</a>');
					}else{
						$('#list').append('<a href="goodList?userPage='+userPage+'&pageRec='+pageRec+'&type='+type+'&goodorder'+goodorder+'&searchType='+searchType+'&keyword='+keyword+'" style="text-decoration:none; color:#000;">목록으로</a>');
					}
					
				}
			});
			
			
	
			
			
		}