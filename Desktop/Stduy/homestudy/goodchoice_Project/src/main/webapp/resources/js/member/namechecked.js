/**
 * 
 */
/**
 * 
 */

function namechecked() {
	var nickname = $('#nickname');
	if (!nickname.val()) {
		$('#namecheckID').attr('class', 'form-label-group')
		$('#nickname').attr('class', 'form-control')
		$('#nameinfo').attr('class', 'info')
		$('#nameinfo').empty();
	} else {

		$.ajax({
			url : 'nicknameChecked',
			type : 'POST',
			dataType : 'json',
			data : {
				'nickname' : nickname.val()
			},
			success : function(rs) {
			/*	console.log(rs.result)*/
				if (rs.result == 1) {
					/*console.log("닉네임 불가능")*/
					$('#namecheckID').attr('class', 'form-group has-danger')
					$('#nickname').attr('class', 'form-control is-invalid')
					$('#nameinfo').attr('class', 'invalid-feedback')
					$('#nameinfo').text('중복 닉네임 입니다. 다시 입력해주세요')
					/*
					 * $('#nameOK').attr('style','display: none;');
					 * $('#nameNO').attr('style','display: block;');
					 * $('#inputInvalid').val(nickname.val());
					 */
				} else {
					/*console.log("닉네임 가능")*/
					$('#namecheckID').attr('class', 'form-group has-success')
					$('#nickname').attr('class', 'form-control is-valid')
					$('#nameinfo').attr('class', 'valid-feedback')
					$('#nameinfo').text('사용 가능한 닉네임 입니다')
					/*
					 * $('#nameNO').attr('style','display: none;');
					 * $('#nameOK').attr('style','display: block;');
					 * $('#inputValid').val(nickname.val());
					 */
				}
			}
		});
	}
}
