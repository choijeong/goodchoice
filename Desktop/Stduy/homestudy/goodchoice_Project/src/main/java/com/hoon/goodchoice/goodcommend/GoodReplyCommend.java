package com.hoon.goodchoice.goodcommend;

import java.util.List;

import com.hoon.goodchoice.gooddto.GoodReplyDTO;
import com.hoon.goodchoice.gooddto.PagingBase;

public interface GoodReplyCommend {

	
	//글 등록
	int GoodReplyWrite(GoodReplyDTO goodreplydto);
	//원댓글 작성시 auto_increment값 가져오기
	int getreply_origin(int good_indexkey, String member_nickname);
	//가져온 id값을 업데이트 셋 하기
	int setreply_origin(int indexkey);

	//계층형 댓글 대댓글
	int GoodReplyReply(GoodReplyDTO goodreplydto);
	int getReplygroup(int good_indexkey, int reply_origin, int reply_group);
	// 글 수정정보 가져오기
	GoodReplyDTO GoodReplyModifyView(int indexkey);
	
	// 글 수정하기
	int GoodReplyModify(GoodReplyDTO goodreplydto);
	// 글 삭제
	int GoodReplyDelete(int indexkey);
	
	
	//리스트조회
	List<GoodReplyDTO> GoodReplyList(Integer good_indexkey, PagingBase pBase);

	
	
	//page처리에 필요한 총 레코드수 구하는 메서드
	int replytotalRec(Integer good_indexkey);
}
