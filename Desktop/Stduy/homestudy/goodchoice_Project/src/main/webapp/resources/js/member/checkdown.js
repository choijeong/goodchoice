/**
 * 
 */
	function checkDown(){
				var id_email=document.getElementById('inputEmail');
				if(id_checkOk.value !="idcheckedOK"){
					alert("아이디 체크를 해주세요")
					id_email.focus();
					return false;
				}
			}