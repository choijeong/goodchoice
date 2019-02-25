package com.hoon.goodchoice.gooddao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.hoon.goodchoice.goodcommend.GoodCommend;
import com.hoon.goodchoice.gooddto.GoodDTO;
import com.hoon.goodchoice.gooddto.HitDTO;
import com.hoon.goodchoice.gooddto.PagingBase;
import com.sun.org.apache.bcel.internal.generic.NEW;

@Repository
public class GoodDAO implements GoodCommend {
	
	@Inject
	private SqlSession sqlSession;
	
	@Override
	public List<GoodDTO> GoodList(PagingBase pBase) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("goodTable.GoodSearchList", pBase);
	}

	@Override
	public int GoodWrite(GoodDTO gooddto) {
		// TODO Auto-generated method stub
		return sqlSession.insert("goodTable.GoodWrite",gooddto);
	}

	@Override
	public GoodDTO GoodChoiceView(int indexkey) {
		// TODO Auto-generated method stub
		int result=upView(indexkey);
		return sqlSession.selectOne("goodTable.GoodChoiceView", indexkey);
	}

	@Override
	public int upView(int indexkey) {
		// TODO Auto-generated method stub
		return sqlSession.update("goodTable.upView", indexkey);
	}

	@Override
	public GoodDTO GoodModifyView(int indexkey) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("goodTable.GoodModifyView", indexkey);
	}

	@Override
	public int GoodModify(GoodDTO gooddto) {
		// TODO Auto-generated method stub
		return sqlSession.update("goodTable.GoodModify", gooddto);
	}

	@Override
	public int GoodDelete(int indexkey) {
		// TODO Auto-generated method stub
		return sqlSession.delete("goodTable.GoodDelete", indexkey);
	}

	@Override
	public int totalRec(PagingBase pBase) {
		// TODO Auto-generated method stub
		System.out.println("getType="+pBase.getType());
		return sqlSession.selectOne("goodTable.getTotalRec", pBase);
	}

	@Override
	public int hit_up(int indexkey) {
		// TODO Auto-generated method stub
		return sqlSession.update("goodTable.hit_up", indexkey);
	}
	@Override
	public int hit_down(int indexkey) {
		// TODO Auto-generated method stub
		return sqlSession.update("goodTable.hit_down", indexkey);
	}

	@Override
	public List<GoodDTO> hitList() {
		// TODO Auto-generated method stub
		return sqlSession.selectList("goodTable.hitList");
	}

	@Override
	public List<GoodDTO> viewList() {
		// TODO Auto-generated method stub
		return sqlSession.selectList("goodTable.viewList");
	}

	@Override
	public List<GoodDTO> dttmList() {
		// TODO Auto-generated method stub
		return sqlSession.selectList("goodTable.dttmList");
	}

/*	@Override
	public List<GoodDTO> GoodList2(PagingBase pBase, int type) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("goodTable.typeList", pBase,type);
	}*/

}
