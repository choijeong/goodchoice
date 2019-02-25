package com.hoon.goodchoice.membercommend;

import java.util.List;

import com.hoon.goodchoice.memberdto.MemberDTO;


public interface MemberCommend {
	
	int memberIdCheck(String id_email);
	int nicknameCheck(String nickname);
	int memberInsert(MemberDTO memberdto);
	int memberLogin(String id_email, String password);
	MemberDTO memberHidden(String id_email, String password);
	MemberDTO memberHidden2(int indexkey);
	MemberDTO memberInfoView(String nickname);
	int memberDelete(MemberDTO memberdto);
	MemberDTO memberModifyView(int indexkey);
	int memberModify(MemberDTO memberdto);
	List<MemberDTO> memberList();
}
