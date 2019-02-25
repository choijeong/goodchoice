/**
 * 
 */

$(function(){
	
	var s=$('#hiddensessionId').val();
	console.log("hidden start")
		if(s > 0){
			hiddenmember();
		}
	});

	function hiddenmember(){
		
		hiddenindexkey=$('#hiddensessionId').val();
	/*	console.log($('#hiddensessionId'))
		console.log(hiddenindexkey)
		*/
		
		$.ajax({
			url : 'memberHidden',
			type : 'POST',
			data: {"indexkey": hiddenindexkey},
			
			dataType : 'json',
			success : function(rs2) {
			/*console.info(rs2)
			console.log("rs2")
			console.info(rs2.hidden2.nickname)
			console.log("==============")
			console.info(rs2.hidden2.indexkey)*/
			$('#hidden_nickname').val(rs2.hidden2.nickname);
			$('#span_nick').text(rs2.hidden2.nickname);
			$('#span_grade').text(rs2.hidden2.grade);
			$('#span_point').text(rs2.hidden2.point);
			$("#mInfoView").attr("href" , "memberInfoView?nickname="+rs2.hidden2.nickname);
			$("#mModifyView").attr("href" , "memberModifyView?indexkey="+rs2.hidden2.indexkey);
			
			}
	//$.ajax({
		});
		
		
	//function hiddenmember(){
		
	}