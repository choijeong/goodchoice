/**
 * 
 */

var memberDeleteBtn=$('#memberDeleteBtn');
memberDeleteBtn.on('click',function(){
	var message=confirm("정말로 탈퇴 하시겠습니까?");
	if(message == true){
		memberDeleteOk();
	
		function memberDeleteOk(){
			var data=$('#deleteForm').serialize();
		
			$.ajax({
				url: "memberDelete",
				type: "POST",
				dataType:"json",
				data: data,
				success: function(rs){
					if(rs.result==1){
						alert("탈퇴 되었습니다")
						location.href='home';
					}else{
						alert("탈퇴 실패하였습니다")
					}
				}
			//$.ajax({	
			});	
		//function memberDeleteOk(){
		}			
	}else{
	return false;
	}
});
