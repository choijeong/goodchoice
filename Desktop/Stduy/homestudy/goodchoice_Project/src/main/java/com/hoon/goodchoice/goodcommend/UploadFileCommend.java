package com.hoon.goodchoice.goodcommend;

import java.util.List;

import com.hoon.goodchoice.gooddto.UploadFileDTO;


public interface UploadFileCommend {

	//파일 추가
	void addFile(int good_indexket ,String fileName) throws Exception;


	//파일 목록 부르기
	List<String> getUploadList(Integer good_indexkey) ;
	
	//게시글삭제시  첨부 파일 전체 삭제 처리
	int deleteAllFiles(Integer good_indexkey);

	//게시글 수정시 첨부파일 삭제할때
	void deleteFile(String fileName);
	//게시글 수정시 첨부파일 수정
	void modifyFile(Integer good_indexkey , String fileName);
	//첨부파일 갯수 갱신 시키기
	void updateFile_count(Integer good_indexkey);
	
	List<UploadFileDTO> getListImg(Integer good_indexkey);
}
