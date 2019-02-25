package com.hoon.goodchoice.gooddao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.hoon.goodchoice.goodcommend.HitCommend;
import com.hoon.goodchoice.gooddto.HitDTO;

@Repository
public class HitDAO implements HitCommend{

	@Inject
	private SqlSession sqlSession;
	@Override
	public int hitCheck(HitDTO hitdto) {
		// TODO Auto-generated method stub
		return sqlSession.update("hitTable.hitCheck", hitdto);
	}
	@Override
	public int hitCheck_cancel(HitDTO hitdto) {
		// TODO Auto-generated method stub
		return sqlSession.update("hitTable.hitCheck_cancel", hitdto);
	}
	@Override
	public HitDTO hitInfo(int member_indexkey, int good_indexkey) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("hitTable.hitInfo",new HitDTO(member_indexkey, good_indexkey));
	}
	@Override
	public int hitInsert(int member_indexkey, int good_indexkey) {
		// TODO Auto-generated method stub
		return sqlSession.insert("hitTable.hitInsert", new HitDTO(member_indexkey, good_indexkey));
	}
	@Override
	public String hit(int member_indexkey, int good_indexkey) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("hitTable.hit",  new HitDTO(member_indexkey, good_indexkey));
	}

}
