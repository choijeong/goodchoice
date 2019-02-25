package com.hoon.goodchoice.goodcommend;

import com.hoon.goodchoice.gooddto.HitDTO;

public interface HitCommend {

	
	//좋아요 hitcheck 관련 sql
	//hit db가 만들어졌는 지 확인
		String hit(int member_indexkey, int good_indexkey);
	//hit db만들기
		int hitInsert(int member_indexkey, int good_indexkey);
	//hit_check +1 좋아요
		int hitCheck(HitDTO hitdto);
	//hit_check -1 좋아요 취소 
		int hitCheck_cancel(HitDTO hitdto);
	//hit 정보 가져오기	
		HitDTO hitInfo(int member_indexkey, int good_indexkey);
}


// 댓글에도 좋아요 할거면 member_indexkey가 아닌 member_nickname 값을 갖는 테이블 새로만들어야 하는듯// **이거틀린생각

// 로그인 사용자가 한 게시물에 한번만 좋아요 할 수 있는게 핵심 기능 
// 아무개닉네임이 작성한 댓글 // 좋아요 할 때 필요한건 로그인한 유저key와 댓글게시글replykey good_indexkey가 아니라 replykey 칼럼필요
// good_indexkey는 해당 리플게시판이 게시글 안에 들어있기때문에 자연스레 가져올 수 있기는 하다 