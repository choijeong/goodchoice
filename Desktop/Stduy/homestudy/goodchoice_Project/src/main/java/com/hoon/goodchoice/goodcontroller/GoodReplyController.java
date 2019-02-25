package com.hoon.goodchoice.goodcontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hoon.goodchoice.gooddao.GoodReplyDAO;
import com.hoon.goodchoice.gooddto.GoodReplyDTO;
import com.hoon.goodchoice.gooddto.PageClass;
import com.hoon.goodchoice.gooddto.PagingBase;

@Controller
public class GoodReplyController {

	@Autowired
	GoodReplyDAO goodreplydao;

	@RequestMapping(value = "/goodReplyListView", method = RequestMethod.GET)
	public String goodReplyListView(Model model) {
		return "goodchoiceboard/gView";
	}
	
	// 댓글 리스트//
	@RequestMapping(value = "goodReplyList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> goodRepltList(@ModelAttribute("good_indexkey") int good_indexkey,
			@ModelAttribute PagingBase pBase) {
		Map<String, Object> map = new HashMap<>();
		List<GoodReplyDTO> goodReplyList = (List<GoodReplyDTO>) goodreplydao.GoodReplyList(good_indexkey, pBase);
		map.put("goodReplyList", goodReplyList);
		System.out.println("reply_controller_list page="+pBase.getUserPage());
		
		PageClass pageClass = new PageClass(pBase);
		int replytotalRec = goodreplydao.replytotalRec(good_indexkey);
		System.out.println("reply_controller_list_totalREc");
		pageClass.setTotalRec(replytotalRec);
		map.put("pageClass", pageClass);

		return map;
	}

	// 댓글 작성//
	@RequestMapping(value = "goodReplyWrite", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> goodReplyWrite(@ModelAttribute GoodReplyDTO goodreplydto) {
		Map<String, Object> map = new HashMap<>();
		System.out.println("replyController_write getMembernickname="+goodreplydto.getMember_nickname());
		System.out.println("getgoodindexkey="+goodreplydto.getGood_indexkey());
		System.out.println("getcontents="+goodreplydto.getContents());
		

		
		
		int result=goodreplydao.GoodReplyWrite(goodreplydto);
		System.out.println("원글 작성 했다="+result);
		
		/*
		 	mybatis  selectKey 를 사용해서 sql문에서 바로 가져올수 있었다 
		 	if(result==1) {
			System.out.println("게시판인덱스"+goodreplydto.getGood_indexkey());
			System.out.println("회원닉네임"+goodreplydto.getMember_nickname());
				int good_indexkey=goodreplydto.getGood_indexkey();
				String member_nickname=goodreplydto.getMember_nickname();
				int indexkey=goodreplydao.getreply_origin(good_indexkey, member_nickname);
				System.out.println("인덱스키 가져오기="+indexkey);
				int rs=goodreplydao.setreply_origin(indexkey);
				System.out.println("가져온 인덱스키 오리진에 넣었다="+rs);
			
		}*/
		map.put("result", result);
		return map;
	}

	// 계층형 대댓글 작성하기 위한 폼 부르기//
			@RequestMapping(value = "goodReplyReply", method = RequestMethod.POST)
			@ResponseBody
			public Map<String, Object> goodReplyReply(@ModelAttribute("indexkey") int indexkey) {
				Map<String, Object> map = new HashMap<>();
							//모디파이뷰 사용해서 불러오기
				GoodReplyDTO goodReplydto = goodreplydao.GoodReplyModifyView(indexkey);
				map.put("replyinfo", goodReplydto);
				return map;
			}
			
	// 계층형 대댓글 작성//
		@RequestMapping(value = "goodReplyReplyOk", method = RequestMethod.POST)
		@ResponseBody
		public Map<String, Object> goodReplyReplyOk(@ModelAttribute GoodReplyDTO goodreplydto) {
			Map<String, Object> map = new HashMap<>();
			
			System.out.println("게시판인덱스"+goodreplydto.getGood_indexkey());
			System.out.println("오리진"+goodreplydto.getReply_origin());
			System.out.println("그룹"+goodreplydto.getReply_group());
			int good_indexkey=goodreplydto.getGood_indexkey();
			int reply_origin=goodreplydto.getReply_origin();
			int reply_group=goodreplydto.getReply_group();
			int rs=goodreplydao.getReplygroup(good_indexkey, reply_origin, reply_group);
			int result=0;
			if(rs==1) {
				System.out.println("그룹정렬 적용1="+rs);
				result = goodreplydao.GoodReplyReply(goodreplydto);
				System.out.println("댓글작성했다");
			}else {
				System.out.println("그룹정렬 적용0="+rs);
				result = goodreplydao.GoodReplyReply(goodreplydto);
				System.out.println("댓글작성했다");
			}
			map.put("result", result);
			return map;
		}
	
	// 댓글 수정 폼 이동//
		@RequestMapping(value = "goodReplyModifyView", method = RequestMethod.POST)
		@ResponseBody
		public Map<String, Object> goodReplyModifyView(@ModelAttribute("indexkey") int indexkey) {
			Map<String, Object> map = new HashMap<>();
			GoodReplyDTO goodReplydto = goodreplydao.GoodReplyModifyView(indexkey);
			
			map.put("replyinfo", goodReplydto);
			return map;
		}
	
	// 댓글 수정 완료//
	@RequestMapping(value = "goodReplyModify", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> goodReplyModify(@ModelAttribute GoodReplyDTO goodreplydto) {
		Map<String, Object> map = new HashMap<>();
		int result = goodreplydao.GoodReplyModify(goodreplydto);
		map.put("result", result);
		return map;
	}
	
	// 댓글 삭제 완료//
	@RequestMapping(value = "goodReplyDelete", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> goodReplyDelete(@ModelAttribute("indexkey") String indexkey) {
		Map<String, Object> map = new HashMap<>();
		int indexkey2 = Integer.parseInt(indexkey);
		int result = goodreplydao.GoodReplyDelete(indexkey2);
		map.put("result", result);
		return map;
	}
	

	
}
