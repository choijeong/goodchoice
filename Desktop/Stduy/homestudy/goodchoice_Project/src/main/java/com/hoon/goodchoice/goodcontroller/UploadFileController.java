package com.hoon.goodchoice.goodcontroller;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hoon.goodchoice.gooddao.UploadFileDAO;
import com.hoon.goodchoice.gooddto.UploadFileDTO;
import com.hoon.goodchoice.util.UploadFileUtils;

@RestController
@RequestMapping("/uploadFile/file")
public class UploadFileController {

	private final UploadFileDAO uploadFiledao;

	@Inject
	public UploadFileController(UploadFileDAO uploadFiledao) {
		this.uploadFiledao = uploadFiledao;
	}

	// REST 더 다양한 접속방식을 처리한다
	// ResponseEntity

	// 게시글 파일 업로드
	@RequestMapping(value = "/upload", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public ResponseEntity<String> uploadFile(MultipartFile file, HttpServletRequest request) {
		ResponseEntity<String> entity = null;
		System.out.println("uploadfile controller 업로드 ㄱㄱ==========================================================");
		System.out.println("file=" + file);
		try {
			String savedFilePath = UploadFileUtils.uploadFile(file, request);
			entity = new ResponseEntity<>(savedFilePath, HttpStatus.CREATED);
			System.out.println("utilcontroller_savedFilePath=" + savedFilePath);
			System.out.println("entity=" + entity);

		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			System.out.println(entity);
		}
		return entity;
	}

	/*
	 * uploadFile()에서 파일업로드 처리한뒤 "/년/월/일/UUID_파일명"의 문자열을 리턴한다 리턴받은 문자열을 view로 보낸다
	 * 
	 */
	// 게시글 첨부파일 출력
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ResponseEntity<byte[]> displayFile(String fileName, HttpServletRequest request) throws Exception {
	System.out.println("업로드파일 보여주기~시작");
		
		// Http 헤더 설정 가져오기
		HttpHeaders httpHeaders = UploadFileUtils.getHttpHeaders(fileName);
		System.out.println("헤더설정 값 ="+httpHeaders);
	
		// 업로드 기본경로 가져오기
		//서버경로
		 String rootPath = UploadFileUtils.getRootPath(fileName, request);
		System.out.println("기본경로rootpath="+rootPath);	
		
		// 실제 저장 파일 경로
		//String rootPath2=UploadFileUtils.getRootPath2(fileName);
	//	System.out.println("실제루트="+rootPath2);
		ResponseEntity<byte[]> entity = null;

		// 파일데이터 , HttpHeader 전송
		try (InputStream inputStream = new FileInputStream(rootPath + fileName)) {
			entity = new ResponseEntity<>(IOUtils.toByteArray(inputStream), httpHeaders, HttpStatus.CREATED);

		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

	/*
	 * 클라이언트로 받은 첨부파일명(fileName)과 request를 UploadFileUtils.getHttpHeaders()로
	 * 
	 * fileName으로 파일타입 판별하고 MINE 타입 지정해서 HttpHeader에 객체를 리턴 HttpHeader와 첨부파일데이터는
	 * view로 호출
	 */

	// 게시글 작성중, 파일첨부한 것 삭제 // 수정시 삭제요청도 처리함 
/*	@RequestMapping(value = "/delete/{good_indexkey}", method = RequestMethod.POST)
*/	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ResponseEntity<String> deleteFile(String fileName, HttpServletRequest request) {
		ResponseEntity<String> entity = null;

		try {
			UploadFileUtils.deleteFile(fileName, request);
			//수정시 삭제처리할것 
			uploadFiledao.deleteFile(fileName);
			
			
			entity = new ResponseEntity<>("DELETED", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

	/*
	 * 클라이언트로부터 전달 받은 첨부파일명 (fileName)과 request를 UploadFileUtils의 deleteFile()로 보냄
	 * deleteFile()는 첨부파일명을 MediaUtils getMediaType() 으로 전달 이미지 타입여부를 판단한다 이미지파일인 경우
	 * 원본과 썸네일 둘다 삭제하고 일반파일 일 경우 파일만 삭제한다
	 */

	// @PathVariable @RequestParam과 다른점
	// ex ?idx=1&name=2 면 RequestParam ?idx/1 식이면 Rest api에서 값을 호출할 때 사용 하는 값으로
	// PathVariavle사용
	// 복합적으로 사용 가능하다
	// 게시글 첨부파일 목록 부르기
	@RequestMapping(value = "/list/{good_indexkey}", method = RequestMethod.GET)
	public ResponseEntity<List<String>> getUploadFiles(@PathVariable("good_indexkey") Integer good_indexkey) {
		ResponseEntity<List<String>> entity = null;
		System.out.println("게시글 조회시 파일목록 불러오기 요청 받았다 ");
		try {
			List<String> fileList = uploadFiledao.getUploadList(good_indexkey);
			System.out.println("fileList="+fileList);
			entity = new ResponseEntity<List<String>>(fileList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

	/*
	 * rest방식으로 good_indexkey 받아서 리스트 불러온 값을 넘긴다
	 */

	
	
	
	// 게시글 목록에서 첨부파일 이미지 목록 부르기
		@RequestMapping(value = "/listimg", method = RequestMethod.POST)
		@ResponseBody
		public Map<String, Object> getListImg(@RequestParam("good_indexkey") int good_indexkey) {
				Map<String, Object> map=new HashMap<>();
			System.out.println("게시글 목폭 페이지에서  파일목록이미지 불러오기 요청 받았다 ");
			
			try {
				List<UploadFileDTO> listImg = uploadFiledao.getListImg(good_indexkey);
				System.out.println("listImg="+listImg);
				
				map.put("listImg", listImg);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return map;
		}
	
	
	
	// 게시글 파일 전체 삭제하기 + 수정시 전체업로드파일 정보 한번 삭제하고 새로 추가 
	@RequestMapping(value = "/deleteAll", method = RequestMethod.POST)
	public ResponseEntity<String> deleteAllFiles(@RequestParam("files[]") String[] files, HttpServletRequest request) {
		
		System.out.println("게시글 전체 삭제 요청 처리 받았다~~");
		System.out.println("files[]="+ files);
		if (files == null || files.length == 0)
			return new ResponseEntity<>("DELETED", HttpStatus.OK);

		ResponseEntity<String> entity = null;

		try {
			for (String fileName : files) {
				System.out.println("fileName="+fileName);
				System.out.println("utils.deleteFile ㄱㄱ");
			UploadFileUtils.deleteFile(fileName , request);
			}
				
			entity = new ResponseEntity<>("DELETED", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

}
