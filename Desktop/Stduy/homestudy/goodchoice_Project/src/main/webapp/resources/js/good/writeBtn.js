/**


 * 
 */

/*	goodWrite() {*/


var writeBtn = $('#writeBtn');
	$type=$('#type');
	$title=$('#title');
	// serialize: form의 input name이 배열 형태로 인코딩됨
	writeBtn.on('click', function() {
		
		if(!$type.val()){
			alert("GOODCHICOE 분류 선택 해 주세요");
			$type.focus();
			return;
		}else if(!$title.val()){
			alert("제목을 입력 해 주세요");
			$title.focus();
			return;
		}
		// fileSubmit 호출
		event.preventDefault();
		var that=$(this);
		console.log("게시글 작성 버튼 start")
		filesSubmit(that);
		
		
		
	});
		
	
		function writeOk(event){
			console.log("파일 작성하고 게시글 작성하러 왔다")
		var data = $('#writeForm').serialize();			
		$.ajax({
			url : 'goodWrite',
			type : 'POST',
			dataType : 'json',
			data : data,
			success : function(rs) {
				if (rs.result == 1) {
					alert('게시글 작성 성공');
					location.href='goodList';
				} else {
					alert('게시글 작성 실패');
				}
			}
		});
		}
	
	
	
