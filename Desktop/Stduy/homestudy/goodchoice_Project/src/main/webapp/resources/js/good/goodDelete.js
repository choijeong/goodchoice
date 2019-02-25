/**
 * 
 */

	function goodDelete(){
		if($('#reply_count').text() > 0){
			/*console.log($('#reply_count').text());*/
			alert("댓글이 있는 게시글은 삭제할 수 없습니다");
			return;
		}
		
		var message=confirm("정말로 삭제 하시겠습니까?");
		if(message == true){
			//파일업로드 upload_file.js로 보냄
			deleteAllFile();
			
			
			
		}
	}
	
		function goodDeleteOk(event){
				var indexkey=$('#indexkey');
				var data ={'indexkey':indexkey.text()};	
			
				$.ajax({
					url: "goodDelete",
					type: "POST",
					dataType:"json",
					data: data,
					success: function(rs){
						//검색어 유지
						var searchType=$('#searchType').val();
						var keyword=$('#keyword').val();
						var goodorder=$('#goodorder').val();
						
						if(rs.result==1){
							alert("삭제 되었습니다")
							if(!goodorder || !keyword){
								location.href='goodList'
							}else if(!goodorder || keyword != null){
								location.href='goodList?searchType='+searchType+'&keyword='+keyword
							}else if(!keyword || goodorder != null){
								location.href='goodList?goodorder='+goodorder
							}else{
								location.href='goodList?goodorder='+goodorder+'&searchType='+searchType+'&keyword='+keyword
							}
						}else{
							alert("삭제 실패하였습니다")
						}
					}
				//$.ajax({	
				});	
			//function goodDeleteOk(){
			}			
		
	