package com.hoon.goodchoice.memberdao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.hoon.goodchoice.membercommend.MemberCommend;
import com.hoon.goodchoice.memberdto.MemberDTO;
import com.sun.org.apache.bcel.internal.generic.NEW;

@Repository
public class MemberDAO implements MemberCommend{

	@Inject
	private SqlSession sqlSession;

	@Override
	public int memberIdCheck(String id_email) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("memberTable.memberIdCheck", id_email);
	}
	@Override
	public int nicknameCheck(String nickname) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("memberTable.nicknameCheck", nickname);
	}

	@Override
	public int memberInsert(MemberDTO memberdto) {
		// TODO Auto-generated method stub
		return sqlSession.insert("memberTable.memberInsert", memberdto);
	}

	@Override
	public int memberLogin(String id_email, String password) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("memberTable.memberLogin", new MemberDTO(id_email, password));
	}
	
	@Override
	public MemberDTO memberHidden(String id_email, String password) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("memberTable.memberHidden", new MemberDTO(id_email, password));
	}
	@Override
	public MemberDTO memberHidden2(int indexkey) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("memberTable.memberHidden2", indexkey);
	}
	@Override
	public MemberDTO memberInfoView(String nickname) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("memberTable.memberInfoView", nickname);
	}

	@Override
	public int memberDelete(MemberDTO memberdto) {
		// TODO Auto-generated method stub
		return sqlSession.delete("memberTable.memberDelete",memberdto);
	}

	@Override
	public MemberDTO memberModifyView(int indexkey) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("memberTable.memberModifyView", indexkey);
	}

	@Override
	public int memberModify(MemberDTO memberdto) {
		// TODO Auto-generated method stub
		return sqlSession.update("memberTable.memberModify", memberdto);
	}

	@Override
	public List<MemberDTO> memberList() {
		// TODO Auto-generated method stub
		return sqlSession.selectList("memberTable.memberList");
	}

	
	

}
