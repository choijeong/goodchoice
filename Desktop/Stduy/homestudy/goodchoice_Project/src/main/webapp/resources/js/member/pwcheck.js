/**
 * 
 */
			function pwchecked(){
				var password = document.getElementById('inputPassword');
				var password2 = document.getElementById('inputConfirmPassword');
				var pwcheckOk = document.getElementById('pwcheckOk');
				
				if(password.value != password2.value){
			/*	console.log(password.value)
				console.log(password2.value)*/
				pwcheckOk.innerHTML="비밀번호가 일치하지 않습니다";
					pwcheckOk.style.color="#f00";
					return false;
				}else if(password.value == password2.value){
				pwcheckOk.innerHTML="비밀번호가 일치합니다";
					pwcheckOk.style.color='#00f';
				 
					return true;
					
				}
				
				
				
			}
			
			/*
			<div class="form-group has-success">
  <label class="form-control-label" for="inputSuccess1">Valid input</label>
  <input type="text" value="correct value" class="form-control is-valid" id="inputValid">
  <div class="valid-feedback">Success! You've done it.</div>
</div>

<div class="form-group has-danger">
  <label class="form-control-label" for="inputDanger1">Invalid input</label>
  <input type="text" value="wrong value" class="form-control is-invalid" id="inputInvalid">
  <div class="invalid-feedback">Sorry, that username's taken. Try another?</div>
</div>

			*/
