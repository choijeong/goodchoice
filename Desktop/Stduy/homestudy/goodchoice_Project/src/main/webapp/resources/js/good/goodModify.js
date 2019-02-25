/**

 * 
 */
//전역 변수 선언 

	//조건 없이 바로 실행 
	$(function() {
		var good_indexkey=$('#indexkey').val();
		console.log("수정페이지 시작됬다 수정할 게시글 정보 불러온다")
		//수정할 게시글 정보 가져오기 	
		goodModify();
			//수정할 업로드파일 리스트 불러오기 
	/*	console.log(good_indexkey);*/
			getFiles(good_indexkey);
		});
	// 첨부파일 삭제 눌르면 
	$(document).on('click','#delBtn',function(event){
		var good_indexkey=$('#indexkey').val();
		event.preventDefault();
		if(confirm("삭제 하시겠습니까? 삭제된 파일은 복구할 수 없습니다")){
			var that = $(this);
			deleteFileModify(that, good_indexkey);
		}
	})

	
		function goodModify() {

			$.ajax({
				url : 'goodModify',
				type : 'POST',
				dataType : 'json',
				data : {
					'indexkey' : $('#indexkey').val()
				},
				success : function(rs) {
					$('#nickname').text(rs.content.nickname);
					$('#title').val(rs.content.title);
					$('#contents').val(rs.content.contents);
					$('#type').val(rs.content.type).prop("selected",true);
				}
			});
		}