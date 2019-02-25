/**
 * 
 */

// Handlebars 파일 템플릿 컴파일
	console.log("upload.file.js start")
	
	var t=$('#uploadFileList').length;
	if(t > 0){
		var fileTemplate = Handlebars.compile($("#fileTemplate").html());
	}

// 첨부파일 drag &drop 영역 선택자
var fileDropDiv = $("#fileDrop");

// 전체 페이지 첨부파일 drag & drop 기본 이벤트 방지
//.content-wrapper 이 없음 아직 
$(".content-wrapper").on("dragenter dargover drop", function(event){
	event.preventDefault();
});

// 첨부 파일 영역 drag & drop 기본 이벤트 방지
fileDropDiv.on("dragenter dragover", function(event){
	event.preventDefault();
})

// 첨부파일 drag & drop 이벤트 처리 : 파일업로드 처리 -> 파일 출력

fileDropDiv.on("drop",function(event){
	event.preventDefault();
	// drop이벤트 발생시 전달된 파일 데이터
	var files=event.originalEvent.dataTransfer.files;
	
	
	// 단일 파일 데이터만을 처리하기 때문에 첫번째 파일만 저장한다
	var file=files[0];
	
	// formData 객체 생성, 파일데이터 저장
	var formData=new FormData();
	formData.append("file",file);
	
	console.log(formData);
	// 파일 업로드 ajax
	uploadFile(formData);
})


// 파일 업로드 ajax
function uploadFile(formData){
	$.ajax({
		url: "uploadFile/file/upload",
		data:formData,
		dataType: "text",
		// processData : 데이터를 일반적인 query String 변환처리할 것인지 결정한다
		// 기본값은 true , application/x-www-form-urlencoded 타입
		// 자동변환 처리하지 않기 위해 false한다
		processData:false,
		// contentType : 기본값은 true , application/x-www-form-urlencoded 타입
		// multipart/form-data 방식으로 전송하기 위해 false
		contentType : false,
		type: "POST",
		success: function(data){
			// 첨부파일 출력 메서드 호출
			printFiles(data);
			$(".noGood").remove();
		}
	})
}


// 첨부파일 출력
function printFiles(data){
	
	
	// 파일 정보 처리
	var fileInfo=getFileInfo(data);
	// Handelbars 파일 템플릿에 파일 정보들을 바인딩하고 HTML 생성
	var html = fileTemplate(fileInfo);
	// Handlebars 파일 템플릿 컴파일을 통해 생성된 HTML 을 DOM에 주입한다
	// DOM Document Object Model 문서객체 모델
	// 문서 객체란 <html>이나 <body> 같은 html문서의 태그들을 JavaScript가 이용할 수 있는 객체(object)로
	// 만든 것
	
	$('#imgcom').attr('src',fileInfo.originalFileUrl);
	
	$('#uploadFileList').append(html);
	// 이미지 파일인 경우 파일 템플릿에 lightbox 속성 추가
	if(fileInfo.fullName.substr(12,2)=="s_"){
		// 마지막에 추가된 첨부파일 템플릿 선택자
		var that=$("#uploadFileList li").last();
		
		// lightbox 속성 추가
		that.find("#filename").attr("data-lightbox","uploadImages");
		
		// 파일 아이콘에서 이미지 아이콘으로 변경
		that.find("#fileicon").attr("id","fileimg");
	}
	
}

// 파일 정보 처리
function getFileInfo(data){
	
	var fullName=data;
	console.log("getFileInfo start")
	
	// 화면에 출력될 파일명 // 유저가 올린파일원 제목
	var originalFileName;
	
	// 썸네일 or 파일아이콘 이미지 파일 출력 요청 url
	var imgSrc;
	
	// 원본파일 요청 URL
	var originalFileUrl;
	
	// 날짜경로를 제외한 나머지 파일명 (UUID_파일명.확장자)
	var uuidFleName;
	
	// 이미지 파일이면
	if(checkImageType(fullName)){
		// 썸네일 이미지 링크
		imgSrc= "uploadFile/file/display?fileName="+fullName;
		uuidFileName= fullName.substr(14);
		var originalImg = fullName.substr(0,12)+fullName.substr(14);
		
		// 원본 이미지 요청 링크
		originalFileUrl="uploadFile/file/display?fileName="+originalImg;
	}else{
		
		/*console.log("일반파일 풀네임="+fullName)*/
		// 파일이미지 링크
		imgSrc="resources/upload/files/file-icon.png";
		uuidFileName = fullName.substr(12);
		/*console.log("일반파일이면 uuidFileName="+ uuidFileName)*/
		// 파일 다운로드 요청 링크
		originalFileUrl="uploadFile/file/display?fileName="+fullName;
	}
	
	originalFileName= uuidFileName.substr(uuidFileName.indexOf("_")+1);
	console.log("getFileInfo return")
	/*console.log("originalFileName="+originalFileName)
	console.log("imgSrc="+imgSrc)
	console.log("originalFileUrl="+originalFileUrl)
	console.log("fullName="+fullName)*/
	
	return {originalFileName: originalFileName, imgSrc : imgSrc, originalFileUrl : originalFileUrl, fullName : fullName};
}
	
	// 이미지 파일 유무 확인하기
	function checkImageType(fullName){
		var pattern=/jpg$|gif$|png$|jpge$/i;
		return fullName.match(pattern);
	}






// 
// 게시글 저장 버튼 클릭 이벤트 처리

	// 게시글 입력 / 수정 submit처리시 첨부파일 정보도 함께 처리한다
	function filesSubmit(that){
		/*console.log(that);*/
	var str="";
	// .each()배열관리 delBtn 갯수 == 업로드 파일 갯수
	$('#uploadFileList #delBtn').each(function(index){
		/*console.log("업로드파일 번지수=");
		console.log(index);*/
		// input hidden name =files[]//파일배열 WriteBtn.attr(href)
		str +="<input type='hidden' name='files["+index+"]' value='"+$(this).attr("href")+"'>"
		/*console.log("value");
		console.log(this);*/
	});
	
	that.append(str);
	/*console.log("that")
	console.log(that);
	console.log(that.val())
	console.log($('#writeBtn').val());*/
	console.log("업로드 파일 서브밋 끝났다 ")
	
	if(that.val() == $('#writeBtn').val()){
		console.log("파일저장하고 게시글 작성하러간다 ")
		writeOk(event);
	}
	
	if(that.val() == $('#goodDeleteBtn').val()){
		console.log("파일삭제끝내고 게시글 삭제하러 간다")
		//삭제 처리 하기
		goodDeleteOk(event);
	}
	
	if(that.val()== $('#goodModifyOkBtn').val()){
		console.log("파일업로드 후 수정글도 저장하러 간다 ")
		 goodModifyOk();
	}
}
// 파일 삭제 버튼 클릭 이벤트
	$(document).on("click","#delBtn",function(event){
		event.preventDefault();
		var that=$(this);
		deleteFileWrtPage(that);
	
	})
	
	
// 파일 삭제 (입력 페이지) : 첨부파일만 삭제처리
function deleteFileWrtPage(that){
	var url="uploadFile/file/delete";
	deleteFile(url,that);
}

//게시글 수정시 파일 삭제 // 서버에 저장된 첨부파일과 DB에 저장딘 첨부파일 정보를 같이 삭제한다
	function deleteFileModify(that, good_indexkey){
		var url="uploadFile/file/delete/"+good_indexkey;
		deleteFile(url, that);
	}
// ===========================================================================================

// 파일삭제 ajax

function deleteFile(url, that){
	$.ajax({
		url : url,
		type : "POST",
		data : {fileName : that.attr("href")}, // 속성 href에 있는 value
		dataType : "text",
		success: function(result){
			if(result== "DELETED"){
				alert("삭제되었습니다");
				// 삭제성공하면 상위 li 삭제
				that.parents('li').remove();
			}
		}
	})
}

//게시글 삭제 시 파일 전체 삭제하기

function deleteAllFile(){
	
	
	console.log("게시글 첨부파일 전체 삭제 시작한다")
	//첨부 파일명들을 배열에 저장한다
	var arr=[];
	$("#uploadFileList li").each(function(){
		arr.push($(this).attr("data-src"));
	/*	console.log($(this));*/
		
	});
	if(arr.length > 0){
		console.log(arr.length)
		$.post("uploadFile/file/deleteAll", {files:arr},function(){
			
		});
	}
	console.log("파일삭제끝내고 게시글 삭제하러간다 ")
	
	goodDeleteOk();
	
}


// 파일 목록 불러오기 // 게시글 조회, 수정 페이지에서 사용

function getFiles(good_indexkey){
	console.log("게시글조회, 수정 페이지에서 파일목록 불러오기")
	//ajax get으로 json data를 가져온다 
	$.getJSON("uploadFile/file/list/"+good_indexkey , function(list){
		if(list.lenght == 0){
			$("#uploadFileList").html("<span class='nogood'>첨부 파일이 없습니다</span>");
		}
		
		$(list).each(function(){
			/*console.log("게시글조회시 파일리스트불러오기 this=")
			console.log(this)*/
			printFiles(this);	
		});
	});
}


//게시글 목록 에서 각 게시글 마다 이미지 보여주기 
function getFileimg(good_indexkey){
	console.log("게시글목록에서 이미지파일 불러오기")
	event.preventDefault();
	$.ajax({
		url: "uploadFile/file/listimg",
		data: {'good_indexkey' : good_indexkey},
		dataType: "JSON",
		type: "POST",
		success: function(data){
			console.log("게시글리스트 파일이미지호출 성공")
			/*console.log(data)*/
		
			var listImg=data.listImg;
			var html="";
			/*console.log("listImg.length=")
			console.log(listImg.length)*/
			// 첨부파일이 있다면 
			if(listImg.length > 0){
				//첨부파일의 수만큼 반복하라 
				for(i=0; i< listImg.length; i++){

					var fullName=listImg[i].fileName;
					var imgSrc="";
					/*console.log("fullName="+fullName)*/
				////////////////////////////// 파일 확장자명 찾기 일반파일, 이미지파일
					// 이미지 파일이면
					if(checkImageType(fullName)){
						// 썸네일 이미지 링크
						imgSrc= "uploadFile/file/display?fileName="+fullName;
					}else{
						console.log("일반파일 풀네임="+fullName)
						// 파일이미지 링크
						imgSrc="resources/upload/files/file-icon.png";
					}
					
					////////////// i번째에 맞는 good_indexkey와 첨부파일 찾기 
					var good_indexkey= listImg[i].good_indexkey;
					/*console.log("1good_indexkey="+good_indexkey);*/
					
					//gList.jsp 에 있는 게시글 상세보기 url
					//var href= $('#goodChoice_href').attr("href");
					var href2= $('#goodChoice_href').prop("href");
					
					//console.log("href="+href)
				/*	console.log("href2="+href2) */
					
					html+='<a href="'+href2+'">';
					html += '<img src="'+imgSrc+'" alt="'+fullName+'"/>';
					html+='</a>';
					
					/*console.log("추가")
					console.log("i="+i)
					console.log(listImg[i].good_indexkey);
					console.log("('#li_'+listImg[i].good_indexkey).append(html)=");*/
				}
				//모든 과정 이후 이미지 추가 , listImg.length 가 남았다면 다시 반복하라 
				console.log("2good_indexkey="+good_indexkey);
				$('#li_'+good_indexkey).append(html);
			}
		}
	})
}

//메인 홈화면에서 슬라이드 사용할 img 주소 하나만   가져오기 
function getFileimg2(good_indexkey){
	console.log("게시글목록에서 이미지파일 불러오기2")
	event.preventDefault();
	$.ajax({
		url: "uploadFile/file/listimg",
		data: {'good_indexkey' : good_indexkey},
		dataType: "JSON",
		type: "POST",
		success: function(data){
			console.log("게시글리스트 파일이미지호출 성공")
		
			var listImg=data.listImg;
			var html="";
			/*console.log("listImg.length=")
			console.log(listImg.length)*/
			// 첨부파일이 있다면 
			if(listImg.length > 0){
					var fullName=listImg[0].fileName;
					var imgSrc="";
					/*console.log("fullName="+fullName)*/
				////////////////////////////// 파일 확장자명 찾기 일반파일, 이미지파일
					// 이미지 파일이면
					if(checkImageType(fullName)){
						// 썸네일 이미지 링크
						/*console.log("썸네일 풀네임="+fullName)*/
					//	imgSrc= "uploadFile/file/display?fileName="+fullName;
						//썸네일 말고 원본 이미지 
						//속도가 느려도 이미지 깨짐 현상이 덜하다 
						uuidFileName= fullName.substr(14);
						/*console.log(uuidFileName)*/
						var originalImg = fullName.substr(0,12)+fullName.substr(14);
						/*console.log(originalImg)*/
						imgSrc= "uploadFile/file/display?fileName="+originalImg;
					}else{
						/*console.log("일반파일 풀네임="+fullName)*/
						// 파일이미지 링크
						imgSrc="resources/upload/files/file-icon.png";
					}
					////////////// i번째에 맞는 good_indexkey와 첨부파일 찾기 
					var good_indexkey= listImg[0].good_indexkey;
					/*console.log("홈화면 이미지 게시글 pk good_indexkey="+good_indexkey)*/
					//home.jsp 에 이미지 넣기 
					var oneindexkey= $('#carousel-item-one').attr('data-indexkey');
					var twoindexkey= $('#carousel-item-two').attr('data-indexkey');
					var threeindexkey= $('#carousel-item-three').attr('data-indexkey');
						if(oneindexkey == good_indexkey ){
							$('#carousel-item-one').attr('style','background-image:url('+imgSrc+')');
						}else if(twoindexkey == good_indexkey){
							$('#carousel-item-two').attr('style','background-image:url('+imgSrc+')');
						}else if(threeindexkey == good_indexkey){
							$('#carousel-item-three').attr('style','background-image:url('+imgSrc+')');
						}
			}
		}
	})
}



//게시글 목록 에서 각 게시글 마다 이미지 보여주기 
function getFileimg3(good_indexkey){
	console.log("게시글목록에서 이미지파일 불러오기3")
	
	$.ajax({
		url: "uploadFile/file/listimg",
		data: {'good_indexkey' : good_indexkey},
		dataType: "JSON",
		type: "POST",
		success: function(data){
			console.log("게시글리스트 파일이미지호출 성공")
		/*	console.log(data)*/
		
			var listImg=data.listImg;
			
		/*	console.log("listImg.length=")
			console.log(listImg.length)*/
			// 첨부파일이 있다면 
			if(listImg.length > 0){
				
				//첫번째 이미지 하나만 가져오기 
				var fullName=listImg[0].fileName;
				var imgSrc="";
				/*console.log("fullName="+fullName)*/
			////////////////////////////// 파일 확장자명 찾기 일반파일, 이미지파일
				// 이미지 파일이면
				if(checkImageType(fullName)){
					// 썸네일 이미지 링크
				/*	console.log("썸네일 풀네임="+fullName)*/
				//	imgSrc= "uploadFile/file/display?fileName="+fullName;
					//썸네일 말고 원본 이미지 
					//속도가 느려도 이미지 깨짐 현상이 덜하다 
					uuidFileName= fullName.substr(14);
					var originalImg = fullName.substr(0,12)+fullName.substr(14);
					imgSrc= "uploadFile/file/display?fileName="+originalImg;
				}else{
					// 파일이미지 링크
					imgSrc="resources/upload/files/file-icon.png";
				}
				////////////// i번째에 맞는 good_indexkey와 첨부파일 찾기 
				var good_indexkey= listImg[0].good_indexkey;
					
					//gList.jsp 에 있는 게시글 상세보기 url
					var href= $('#goodChoice_href'+good_indexkey).prop("href");
					
					var html="";
					html+='<a href="'+href+'">';
					html += '<img class="img-fluid rounded mb-3 mb-md-0" src="'+imgSrc+'" alt="'+fullName+'"/>';
					html+='</a>';
				
				//모든 과정 이후 이미지 추가 , listImg.length 가 남았다면 다시 반복하라 
				$('#li_'+good_indexkey).append(html);
			}
		}
	})
}






































