/**
 * 
 */
var id_checked = $('#id_checked');

		id_checked.on('click', function() {
			var id_email = $('#inputEmail');
			/*console.log(id_email.val())*/

			if(id_email.val()=="" || id_email.val() == null){
				alert("아이디 입력 오류입니다. 다시 입력해 주세요 :D")
				id_email.focus();
				return false;
			}
			
			
			$.ajax({
				url : 'memberIdChecked',
				type : 'POST',
				dataType : 'json',
				data : {'id_email' : id_email.val()	},
				success : function(rs) {
					console.log(rs.result)
					if (rs.result == 1) {
						alert('중복 아이디입니다. 다시 입력해 주세요');						
						id_email.focus();
					} else {
						alert('사용 가능한 아이디입니다.');
						//히든에 아이디값 저장 
						id_checkOk.value="idcheckedOK";
						$('#inputPassword').focus();
					}
				}
			});
		});