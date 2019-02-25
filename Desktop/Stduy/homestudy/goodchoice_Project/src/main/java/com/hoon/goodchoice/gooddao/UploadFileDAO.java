package com.hoon.goodchoice.gooddao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.hoon.goodchoice.goodcommend.UploadFileCommend;
import com.hoon.goodchoice.gooddto.UploadFileDTO;

@Repository
public class UploadFileDAO  implements UploadFileCommend{

	@Inject
	SqlSession sqlSession;
	//파일 추가
	@Override
	public void addFile(int good_indexkey, String fileName) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("업로드파일테이블 저장 시작 ");
		int good_indexkey2=sqlSession.selectOne("UploadFileTable.getGood_indexkey");
		System.out.println("UploadFileTable.getGood_indexkey="+good_indexkey2);
		if(good_indexkey2 > 0  ) {
			Map<Object, Object> map=new HashMap<>();
			map.put("good_indexkey", good_indexkey2);
			map.put("fileName", fileName);
			System.out.println(map);
			sqlSession.insert("UploadFileTable.addFile", map);
		}
		
	}
	//파일 목록 부르기
	@Override
	public List<String> getUploadList(Integer good_indexkey) {
		// TODO Auto-generated method stub
		System.out.println("getUploadList.dao");
		return sqlSession.selectList("UploadFileTable.getUploadList", good_indexkey);
	}
	//게시글삭제시  첨부 파일 전체 삭제 처리
	@Override
	public int deleteAllFiles(Integer good_indexkey) {
		// TODO Auto-generated method stub
		System.out.println("uploadTable delete_good_indexkey="+good_indexkey);
		return sqlSession.delete("UploadFileTable.deleteAllFiles", good_indexkey);
	}
	//게시글 수정시 첨부파일 삭제할때
	@Override
	public void deleteFile(String fileName) {
		sqlSession.delete("UploadFileTable.deleteFile", fileName);
		
	}
	//게시글 수정시 첨부파일 수정
	@Override
	public void modifyFile(Integer good_indexkey, String fileName) {
		Map<String, Object> map= new HashMap<>();
		map.put("good_indexkey", good_indexkey);
		map.put("fileName", fileName);
		sqlSession.insert("UploadFileTable.modifyFile", map);
		
	}
	//첨부파일 갯수 갱신 시키기
	@Override
	public void updateFile_count(Integer good_indexkey) {
		// TODO Auto-generated method stub
		sqlSession.update("UploadFileTable.updateFile_count", good_indexkey);
		
	}
	
	//게시글 목록에서 이미지 부르기
	@Override
	public List<UploadFileDTO> getListImg(Integer good_indexkey) {
		// TODO Auto-generated method stub
		System.out.println("getListImg.dao");
		return sqlSession.selectList("UploadFileTable.getListImg", good_indexkey);

	}

	
}
