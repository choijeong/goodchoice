package com.hoon.goodchoice.gooddao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.hoon.goodchoice.goodcommend.GoodReplyCommend;
import com.hoon.goodchoice.gooddto.GoodReplyDTO;
import com.hoon.goodchoice.gooddto.PagingBase;

@Repository
public class GoodReplyDAO implements GoodReplyCommend{

	@Inject
	private SqlSession sqlSession;

	@Override
	public int GoodReplyWrite(GoodReplyDTO goodreplydto) {
		// TODO Auto-generated method stub
		return sqlSession.insert("goodReplyTable.goodReplyWrite", goodreplydto);
	}

	@Override
	public int getreply_origin(int good_indexkey, String member_nickname) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("goodReplyTable.getReply_origin" ,new GoodReplyDTO(good_indexkey, member_nickname));
	}
	
	@Override
	public int setreply_origin(int indexkey) {
		// TODO Auto-generated method stub
		return sqlSession.update("goodReplyTable.setReply_origin", indexkey);
	}
	
	@Override
	public GoodReplyDTO GoodReplyModifyView(int indexkey) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("goodReplyTable.goodReplyModifyView", indexkey);
	}

	@Override
	public int GoodReplyModify(GoodReplyDTO goodreplydto) {
		// TODO Auto-generated method stub
		return sqlSession.update("goodReplyTable.goodReplyModify", goodreplydto);
	}

	@Override
	public int GoodReplyDelete(int indexkey) {
		// TODO Auto-generated method stub
		return sqlSession.delete("goodReplyTable.goodReplyDelete", indexkey);
	}

	@Override
	public List<GoodReplyDTO> GoodReplyList(Integer good_indexkey, PagingBase pBase) {
		// TODO Auto-generated method stub
		Map<String, Object> map= new HashMap<>();
		map.put("good_indexkey", good_indexkey);
		map.put("pBase", pBase);
		System.out.println("replyDao_Listmap="+map.get(good_indexkey));
		System.out.println("replyDao_Listmap="+map.get(pBase));
		
		return sqlSession.selectList("goodReplyTable.goodReplyList", map);
	}

	@Override
	public int replytotalRec(Integer good_indexkey) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("goodReplyTable.getTotalRec", good_indexkey);
	}
	
	//대댓글 작성 
	@Override
	public int GoodReplyReply(GoodReplyDTO goodreplydto) {
		// TODO Auto-generated method stub
		
		return sqlSession.insert("goodReplyTable.goodReplyReply", goodreplydto);
	}
	//그룹 가져와서 대댓글 정렬할때 사용
	@Override
	public int getReplygroup(int good_indexkey, int reply_origin, int reply_group) {
		// TODO Auto-generated method stub
		return sqlSession.update("goodReplyTable.getReplygroup", new GoodReplyDTO(good_indexkey, reply_origin, reply_group));
	}



	
	
	
}
