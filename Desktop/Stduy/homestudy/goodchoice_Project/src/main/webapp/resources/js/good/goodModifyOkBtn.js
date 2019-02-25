/**
 * 
 */


//수정 처리시 첨부파일 정보도 함께 처리해야 한다 

var goodModifyOkBtn = $('#goodModifyOkBtn');
		// serialize: form의 input name이 배열 형태로 인코딩됨
		goodModifyOkBtn.on('click', function() {
			console.log("게시글 수정 시작 요청받았다~~ goodmodifyOk.js")
			//수정버튼 클릭하면 다른 이벤트 멈추고 업로드파일도 서브밋한다~
			event.preventDefault();
			var that=$(this);
			/*console.log("that="+that)*/
			filesSubmit(that);
		});
		
		function goodModifyOk(){
			console.log("파일업로드 후 수정글도 저장하러 왔따 ")
			var data = $('#modifyForm').serialize();
			/*console.log("data=");
			console.log(data);*/
			$.ajax({
				url : 'goodModifyOk',
				type : 'POST',
				dataType : 'json',
				data : data,
				success : function(rs) {
					
					var userPage=$('#userPage').val();
					var pageRec=$('#pageRec').val();
					var indexkey=$('#indexkey').val();
					var searchType=$('#searchType').val();
					var keyword=$('#keyword').val();
					var goodorder=$('#goodorder').val();
					var type=$('#hiddentype').val();
					/*console.log(pageRec);
					console.log(userPage);
					console.log('goodChoiceView?userPage='+userPage+'&pageRec='+pageRec+'&indexkey='+ indexkey);
					*/
					if (rs.result == 1) {
						alert('게시글 수정 성공');
					
					if(!goodorder || !keyword){
						location.href = 'goodChoiceView?userPage='+userPage+'&pageRec='+pageRec+'&type='+type+'&indexkey='+ indexkey
					}else if(!goodorder || keyword != null){
						location.href = 'goodChoiceView?userPage='+userPage+'&pageRec='+pageRec+'&type='+type+'&searchType='+searchType+'&keyword='+keyword+'&indexkey='+ indexkey
					}else if(!keyword || goodorder != null){
						location.href = 'goodChoiceView?userPage='+userPage+'&pageRec='+pageRec+'&type='+type+'&goodorder'+goodorder+'&indexkey='+ indexkey
					}else{
						location.href = 'goodChoiceView?userPage='+userPage+'&pageRec='+pageRec+'&type='+type+'&goodorder='+goodorder+'&searchType='+searchType+'&keyword='+keyword+'&indexkey='+ indexkey
					}
					
					} else {
						alert('게시글 수정 실패');
					}
				}
			});
		}