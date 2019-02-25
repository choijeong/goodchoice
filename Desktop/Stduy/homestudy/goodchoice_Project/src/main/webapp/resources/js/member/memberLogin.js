/**
 * 
 */

function loginFn(){
			var loginForm = document.getElementById('loginForm');
			var id_email= document.getElementById('inputEmail');
			var password = document.getElementById('inputPassword');
			if(id_email.value=="" || id_email.value == null){
				alert("아이디 입력 오류입니다. 다시 입력해 주세요 :D")
				id_email.focus();
				return false;
			}
			if(password.value=="" || password.value == null){
				alert("비밀번호 입력 오류입니다. 다시 입력해 주세요 :D")
				password.focus();
				return false;
			}
			loginOkFn();
		
		//function loginFn(){
		}
		
		function loginOkFn(){
			var id_email=$('#inputEmail');
			var password=$('#inputPassword');
			var data={'id_email':id_email.val(), 'password':password.val()};
			console.log("id_email.val()="+id_email.val())
			$.ajax({
				url : 'memberLogin',
				type : 'POST',
				dataType : 'json',
				data : data,
				success : function(rs) {
					/*console.log(rs)
					console.log(rs.result)*/
					if(rs.result ==1){
						alert('로그인되었습니다.')
						location.href='home';
					}else{
						alert('로그인 실패. 다시 입력해주세요.')
					}
				//success : function(rs) {
				}
			//$.ajax({	
			});
		//function loginOkFn(){
		}
		