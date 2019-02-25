package com.hoon.goodchoice.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.imgscalr.Scalr;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;


public class UploadFileUtils {

	
	//static 메서드 선언 인스턴스 없이 바로 사용 
	//파일업로드, 삭제, 출력  httpHeader설정 메서드는 public 선언 파일 업로드 컨트롤러에서 바로 접근 가능
		
	///////////////////////////////////////////섭
	
	//파일 업로드 처리
	public static String uploadFile(MultipartFile file, HttpServletRequest request ) throws IOException {
		 System.out.println("uploadFileUtils =============================");
	
		//유저가 업로드한 오리지널 파일명
		String originalFileName = file.getOriginalFilename();
		//파일 데이터
		byte[] fileData= file.getBytes();
		
		// 1. 파일명 중복 방지 처리 기능
		//UUID 유니크 
		String uuidFileName= getUuidFileName(originalFileName);
		System.out.println("uuidFileName="+uuidFileName);
		
		// 1. 파일명 중복 방지 처리 기능
		//UUID 유니크 
		//test_실제 경로 저장할 아이디  UUID 까지 따로만들면 다른 파일이 되므로 같은 이름을 다른객체 담아 사용한다 
		//String uuidFileName2= uuidFileName;
		//System.out.println("uuidFileName2="+uuidFileName2);
		
		
		
		
		//2. 
		//서버 파일 업로드 경로 설정
		//기본 경로 추출(이미지 or 일반 파일)
		String rootPath =getRootPath(originalFileName, request);
		System.out.println("rootPath="+rootPath);
		//날짜 경로 추출, 날짜 폴더 생성
		String datePath= getDatePath(rootPath);
		System.out.println("datePath="+datePath);
				
				
		//2. 
		//test 실제 파일 저장 경로 설정 
		//String rootPath2 = getRootPath2(originalFileName);
		//System.out.println("test_rootPath2="+rootPath2);
		//test 날짜 경로 추출, 날짜 폴더 생성
		//String datePath2= getDatePath(rootPath2);
		//System.out.println("test_datePath2="+datePath2);
		
		
		//3. 서버에 파일 저장
		//파일 객체 생성
		File target=new File(rootPath + datePath, uuidFileName); 
		System.out.println("target="+target);
		//파일 객체에 파일 데이터 복사
		FileCopyUtils.copy(fileData,target);
				
		//3. test 실제 경로 파일 저장
		//File target2=new File(rootPath2 + datePath2, uuidFileName2); 
		//System.out.println("test_target2="+target2);
		//test 파일객체에 파일 데이터 복사 
		//FileCopyUtils.copy(fileData,target2);
		
		
		//4. 이미지 파일인 경우 썸네일 이미지 생성

		if(MediaUtils.getMediaType(originalFileName) != null) {
			System.out.println("MediaUtils.getMediaType(originalFileName)="+MediaUtils.getMediaType(originalFileName));
			uuidFileName = makeThumbnail(rootPath, datePath, uuidFileName);
			System.out.println("uuidFileName="+uuidFileName);
			
			//test 실제 파일저장경로에 썸네일 이미지 저장 
			//uuidFileName2 = makeThumbnail(rootPath2, datePath2, uuidFileName2);
			//System.out.println("test_uuidFileName2="+uuidFileName2);
		}
	
		
		//test return받지 않고 그냥 메서드 실행 
			// replaceSavedFilePath(datePath2, uuidFileName2);
			// System.out.println("실제 경로 datePath2="+datePath2);
			// System.out.println("실제 파일명 uuidFileName2="+uuidFileName2);
			 
			 
		//5. 파일 저장 경로 치환
			 //마지막 작업이기 때문에 리턴 받는다 
			 //썸네일 만든 이미지 저장위치 다시 불러오기 
		return replaceSavedFilePath(datePath, uuidFileName);
	}
	
	
		
	// ajax 파일 출력을 위한 HttpHeader 설정
	public static HttpHeaders getHttpHeaders(String fileName) throws Exception {
		System.out.println("HttpHeader설정 시작");
		System.out.println("fileName="+fileName);
		//파일 타입 확인
		MediaType mediaType = MediaUtils.getMediaType(fileName);
		System.out.println("타입확인해라 mediaType="+mediaType);
		HttpHeaders httpHeaders=new HttpHeaders();
	
		//이미지 파일이 맞으
		if(mediaType != null) {
			httpHeaders.setContentType(mediaType);
			return httpHeaders;
		}
		//이미지 파일이 아니면 
		
		//UUID제거
		fileName =fileName.substring(fileName.indexOf("_") + 1);
		System.out.println("UUID제거한파일이름="+fileName);
		
		//다운로드 MIME 타입 설정
		httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		
		
		//파일명 한글 인코딩 처리
		httpHeaders.add("Content-Disposition" , 
				"attachment; fileName=\"" + new String(fileName.getBytes("UTF-8"),
						"ISO-8859-1")+"\"");
		System.out.println("결과는요 httpHeaders="+httpHeaders);
		System.out.println("==================================일반파일일때==============");
		return httpHeaders;
	}
	
	//기본 경로 추출
	public static String getRootPath(String fileName, HttpServletRequest request) {
		System.out.println("경로 추출 시작 ===========");
		
		//rooPath 설정
		String rootPath = "/resources/upload";	
		
		//파일 타입 확인
		MediaType mediaType = MediaUtils.getMediaType(fileName);
		System.out.println("mediaType="+mediaType);
		//한줄짜리는 {} 없이 바로 넣어도 된다 // if문
		if(mediaType != null) {
			//이미지 파일 경로
			System.out.println(request.getSession().getServletContext().getRealPath(rootPath+"/images"));
			/*test_path=test_path+"/images";*/
			return request.getSession().getServletContext().getRealPath(rootPath+"/images");
		}
		//일반 파일 경로
		return request.getSession().getServletContext().getRealPath(rootPath+"/files"); 
		/*test_path=test_path+"/files";*/
	}
	
	///test  실제 파일 저장할 기본 경로 추출
	/*	public static String getRootPath2(String fileName) {
			System.out.println("test_실제 저장할 경로 추출 시작 ===========");
			//rooPath 설정
			String rootPath2="/resources/upload2";
			String rootPath2="C:\\Users\\JeongHoon\\Desktop\\Stduy\\homestudy\\goodchoice_Project\\src\\main\\webapp\\resources\\upload";
			String rootPath2="C:\\Users\\JeongHoon\\Desktop\\uploadFile";
		//	/*String rootPath2="C:/Users/JeongHoon/Desktop/uploadFile";
		
			//파일 타입 확인
			MediaType mediaType = MediaUtils.getMediaType(fileName);
			System.out.println("test_mediaType="+mediaType);
			//한줄짜리는 {} 없이 바로 넣어도 된다 // if문
			if(mediaType != null) {
				//이미지 파일 경로
				System.out.println("test_(rootPath2+\"\\images\")="+(rootPath2+"\\images"));
				return rootPath2+"/images";
			}
			//일반 파일 경로
			return rootPath2+"/files";
		}*/
		
	
	
	//날짜 폴더명 추출
	private static String getDatePath(String uploadPath) {
		System.out.println("날짜 폴더명 추출하기 시작============");
		System.out.println("uploadPath="+uploadPath);
		Calendar calendar=Calendar.getInstance();
		String yearPath = File.separator+calendar.get(Calendar.YEAR);
		String monthPath = yearPath + File.separator+new DecimalFormat("00").format(calendar.get(Calendar.MONTH)+1);
		String datePath = monthPath + File.separator+new DecimalFormat("00").format(calendar.get(Calendar.DATE));
		
		System.out.println("calendar="+calendar);
		System.out.println("yearPath="+yearPath);
		System.out.println("monthPath="+monthPath);
		System.out.println("datePath="+datePath);
		
		makeDateDir(uploadPath, yearPath, monthPath, datePath);
		
		return datePath;
	
	}
	
	//날짜별 폴더 생성										// 문자열 여러개를 사용할수있다
	private static void makeDateDir(String uploadPath, String ...paths) {
		System.out.println("날짜별 폴더 생성 시작 =========================");
		//날짜별 폴더가 이미 존재하면 메서드 종료한다
		if(new File(uploadPath + paths[paths.length-1]).exists())
			return;
		
		for(String path : paths) {
			System.out.println("path="+path);
			File dirPath = new File(uploadPath + path);
			if(!dirPath.exists())
				dirPath.mkdir();
		}
	}
	
	
	//파일 저장 경로 치환
	private static String replaceSavedFilePath(String datePath, String fileName) {
		System.out.println("파일 저장 경로 치환 시작 ");
		//separator = Change separator used by output mode and .import 경로에서 구분자 역할을 한다 / , \
		String savedFilePath= datePath+File.separator + fileName;
		System.out.println("savedFilePath="+savedFilePath);
		//savedFilePath를 string 문자열로 가져오는데 파일 구분자를 / 로 바꿔라
		System.out.println(savedFilePath.replace(File.separatorChar, '/'));
		return savedFilePath.replace(File.separatorChar, '/');
	}
	
	//파일명 중복 방지 처리
	private static String getUuidFileName(String originalFileName) {
		System.out.println("파일명 중복방지 처리를 위해 UUID.reandomUUID() 사용");
		return UUID.randomUUID().toString()+"_"+originalFileName;
	}
	
	//썸네일 이미지 생성
	private static String makeThumbnail(String uploadRootPath, String datePath, String fileName) throws IOException {
		System.out.println("썸네일 만들기 시작=====================");
		System.out.println("uploadRootPath+datePath="+uploadRootPath+datePath+", fileName="+fileName);
		
		//원본이미지를 메모리상에 로딩
		BufferedImage originalImg = ImageIO.read(new File(uploadRootPath + datePath, fileName));
		System.out.println("originalImg="+originalImg);
		//원본이미지를 축소
		BufferedImage thumbnailImg =Scalr.resize(originalImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, 100);
		System.out.println("thumbnailImg="+thumbnailImg);
		
		//썸네일 파일명 s_파일이름
		String thumbnailImgName="s_"+fileName;
		System.out.println("thumbnailImgName="+thumbnailImgName);
		//썸네일 업로드 경로
		String fullPath = uploadRootPath+datePath+File.separator+thumbnailImgName;
		System.out.println("fullPath="+fullPath);
		//썸네일 파일 객체 생성
		File newFile = new File(fullPath);
		System.out.println("newFile="+newFile);
		//썸네일 파일 확장자 추출
		String formatName = MediaUtils.getFormatName(fileName);
		
		//썸네일 파일 저장
		//이미지 저장 메서드 썸네일이미지, 확장자 , 파일
		ImageIO.write(thumbnailImg, formatName, newFile);
		
		
		return thumbnailImgName;
	}
	
	
	
	
	
	public static void deleteFile(String fileName, HttpServletRequest request) {
		System.out.println("deleteFile시작");
		//기본경로 추출 (이미지 or 일반파일)
		String rootPath = getRootPath(fileName, request);
		System.out.println("기본 경로 추출 // 이미지 or 일반파일");
		System.out.println("rootPath="+rootPath);
	
		//test_실제 저장된 기본경로 추출 (이미지 or 일반파일)
	//	String rootPath2 = getRootPath2(fileName);
		//System.out.println("기본 경로 추출 // 이미지 or 일반파일");
	//	System.out.println("rootPath2="+rootPath2);
		
		
		//1.원본 이미지 파일 삭제
		MediaType mediaType=MediaUtils.getMediaType(fileName);
		System.out.println("mediaType="+mediaType);
		if(mediaType != null) {
			//.substring(start,end) 문자열을 가져온다 0번지부터 12개  end값이 없으면 14부터 끝까지 
			String originalImg = fileName.substring(0,12) + fileName.substring(14);
			System.out.println("originalImg="+originalImg);
			System.out.println("rootPath + originalImg.replace('/',File.separatorChar)).delete()="+rootPath+originalImg);
			new File(rootPath + originalImg.replace('/',File.separatorChar)).delete();
		}
		
		
		/*//1.test_ 실제 경로에 저장된 원본 이미지 파일 삭제
				MediaType mediaType2=MediaUtils.getMediaType(fileName);
				System.out.println("test_mediaType2="+mediaType2);
				if(mediaType2 != null) {
					//.substring(start,end) 문자열을 가져온다 0번지부터 12개  end값이 없으면 14부터 끝까지 
					String originalImg2 = fileName.substring(0,12) + fileName.substring(14);
					System.out.println("test_originalImg2="+originalImg2);
					System.out.println("test_rootPath2 + originalImg2.replace('/',File.separatorChar)).delete()="+rootPath2+originalImg2);
					new File(rootPath2 + originalImg2.replace('/',File.separatorChar)).delete();
				}*/
		
		//2.파일 삭제 (썸네일이미지 or 일반파일)
		//separator 파일구분자 // 경로 구분 등 역할 
		//new File(rootPath + fileName.replace('/',File.separatorChar)).delete();
		
		//2.test_ 실제 저장 경로 파일 삭제 (썸네일이미지 or 일반파일)
				//separator 파일구분자 // 경로 구분 등 역할 
		//new File(rootPath2 + fileName.replace('/',File.separatorChar)).delete();
	}

	
	
}
