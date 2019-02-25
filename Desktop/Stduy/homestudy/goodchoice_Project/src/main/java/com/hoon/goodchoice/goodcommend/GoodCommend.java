package com.hoon.goodchoice.goodcommend;

import java.util.List;

import com.hoon.goodchoice.gooddto.GoodDTO;
import com.hoon.goodchoice.gooddto.HitDTO;
import com.hoon.goodchoice.gooddto.PagingBase;

public interface GoodCommend {
	
	
	//글 등록
	int GoodWrite(GoodDTO gooddto);
	//글 보기(읽기)
	GoodDTO GoodChoiceView(int indexkey);
	// 조회수 올리기
	int upView(int indexkey);
	// 글 수정정보 가져오기
	GoodDTO GoodModifyView(int indexkey);
	// 글 수정하기
	int GoodModify(GoodDTO gooddto);
	// 글 삭제
	int GoodDelete(int indexkey);
	
	//좋아요
	int hit_up(int indexkey);
	int hit_down(int indexkey);

	//리스트조회
	List<GoodDTO> GoodList(PagingBase pBase);

	/*List<GoodDTO> GoodList2(PagingBase pBase);*/
	
	//page처리에 필요한 총 레코드수 구하는 메서드
	int totalRec(PagingBase pBase);
	
	
	List<GoodDTO> hitList();
	List<GoodDTO> viewList();
	List<GoodDTO> dttmList();
	
}
