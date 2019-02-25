package com.hoon.goodchoice.goodcontroller;

import java.util.ArrayList;
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

import com.hoon.goodchoice.gooddao.GoodDAO;
import com.hoon.goodchoice.gooddao.HitDAO;
import com.hoon.goodchoice.gooddao.UploadFileDAO;
import com.hoon.goodchoice.gooddto.GoodDTO;
import com.hoon.goodchoice.gooddto.HitDTO;
import com.hoon.goodchoice.gooddto.PageClass;
import com.hoon.goodchoice.gooddto.PagingBase;

@Controller
public class GoodController {

	@Autowired
	GoodDAO gooddao;
	@Autowired
	HitDAO hitdao;
	@Autowired
	UploadFileDAO uploadfiledao;
	

	// 부트스트랩 test
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String main(Model model) {
		return "test";
	}

	// 내장객체로 보낸경우
	// 페이징리스트 jstl
	@RequestMapping(value = "goodList", method = RequestMethod.GET)
	public String goodList(PagingBase pBase, Model model) {
		System.out.println("goodcontoller_goodList");
		System.out.println("getSearchType="+pBase.getSearchType());
		System.out.println("getKeyword="+pBase.getKeyword());
		System.out.println("getUserPage="+pBase.getUserPage());
		System.out.println("getPageRec="+pBase.getPageRec());
		System.out.println("getGoodorder="+pBase.getGoodorder());
		System.out.println("getType="+pBase.getType());
		
		// 현재 페이지에 보여줄 게시글 목록
		List<GoodDTO> goodList = gooddao.GoodList(pBase);

	
		
		model.addAttribute("goodList", goodList);

		// pageClass 객체 생성
		PageClass pageClass = new PageClass(pBase);

		// 전체 게시물 수를 구함
		int totalRec = gooddao.totalRec(pBase);
		// pageClass로 토탈값을 넘겨서 시작페이지번호,끝페이지번호, 이전,다음 값 계산
		pageClass.setTotalRec(totalRec);
		System.out.println(pageClass);
		System.out.println("================");
		System.out.println(pageClass.getPage_startNum());
		System.out.println(pageClass.getpBase().getUserPage());
		System.out.println(pageClass.getTotalRec());

		System.out.println(pageClass.makeQuery(pageClass.getpBase().getUserPage(),pageClass.isNeedOrder(),pageClass.isNeedSearch(),pageClass.getNeedType()));
		System.out.println(pageClass.getpBase().getKeyword());
		System.out.println(pageClass.getpBase().getSearchType());
		System.out.println(pageClass.getpBase().getType());
		System.out.println("controller.goodlist============");
		model.addAttribute("pageClass", pageClass);

		return "goodchoiceboard/gList";
	}
	/*
	 * //내장객체로 보낸경우
	 * 
	 * @RequestMapping(value="goodList", method=RequestMethod.GET) public String
	 * goodList(PagingBase pBase, Model model) { //현재 페이지에 보여줄 게시글 목록 List<GoodDTO>
	 * goodList=gooddao.GoodList(pBase);
	 * 
	 * model.addAttribute("goodList", goodList);
	 * 
	 * //pageClass 객체 생성 PageClass pageClass=new PageClass(pBase);
	 * 
	 * //전체 게시물 수를 구함 int totalRec=gooddao.totalRec(pBase); //pageClass로 토탈값을 넘겨서
	 * 시작페이지번호,끝페이지번호, 이전,다음 값 계산 pageClass.setTotalRec(totalRec);
	 * System.out.println(pageClass); System.out.println("================");
	 * System.out.println(pageClass.getPage_startNum());
	 * System.out.println(pageClass.getpBase().getUserPage());
	 * System.out.println("controller.goodlist============");
	 * model.addAttribute("pageClass", pageClass);
	 * 
	 * return "goodchoiceboard/gList"; }
	 */
	//gWrite.jsp 페이지 이동
	@RequestMapping(value = "/goodWriteView", method = RequestMethod.GET)
	public String goodWriteView(Model model) {
		return "goodchoiceboard/gWrite";
	}

	

	
	// ajax사용한 경우
	// 글 작성
	@RequestMapping(value = "/goodWrite", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> goodWrite(@ModelAttribute GoodDTO gooddto) throws Exception {
		Map<String, Object> map = new HashMap<>();
		System.out.println("게시글 작성시작 " );
		int result = 0;
		System.out.println("gooddto.getFiles()="+gooddto.getFiles());
		System.out.println("gooddto.getFil_count()="+gooddto.getFile_count());
		// 게시글작성
		result = gooddao.GoodWrite(gooddto);
		System.out.println("1이면 게시글저장은 성공="+result);
		
		String[] files = gooddto.getFiles();
		
		
		// 파일업로드 // 파일 업로드한 정보가 있다면 
		if (files != null) {
			for (String fileName : files) {
					System.out.println("파일즈에 담긴 파일이름들");
					System.out.println(fileName);
				
					if(result==1) {
					//good_indexkey 임의 지정 아마 0일것이다 
					int good_indexkey=gooddto.getIndexkey();
					System.out.println("controller_good_indexkey="+good_indexkey);
					uploadfiledao.addFile(good_indexkey, fileName);;
				}
			}
		}
		map.put("result", result);
		return map;
	}

	// gView.jsp 이동 
	@RequestMapping(value = "/goodChoiceView", method = RequestMethod.GET)
	public String goodChoiceView(@RequestParam("indexkey") String indexkey, @ModelAttribute("pBase") PagingBase pBase,
			Model model) {
		model.addAttribute("indexkey", indexkey);
		model.addAttribute("pBase", pBase);
		System.out.println(pBase);
		System.out.println("0000000000000000000");
		return "goodchoiceboard/gView";
	}

	//글 상세 보기 ajax로 값 받음
	@RequestMapping(value = "/goodChoiceContent", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> goodChoiceContent(@RequestParam("good_indexkey") int good_indexkey,
			@RequestParam("member_indexkey") String member_indexkey, Model model) {
		Map<String, Object> map = new HashMap<>();
		//로그인 안할시에 공백으로 날아오는데 int형은 null을 취급하지 않으니까 String받아서 null확인후
		// 값이 존재한다면 int로 형변환
		int member_indexkey2=Integer.parseInt(member_indexkey);
		HitDTO hitdto;
		String result="";
		if(member_indexkey2 != 0) {
			// hit db가 있는지 확인
			result = hitdao.hit(member_indexkey2, good_indexkey);
			// hit db가 없다면
			if (result == null) {
				// hit db 만들기
				hitdao.hitInsert(member_indexkey2, good_indexkey);
				// db가 있다면
			} else {
				// hit db 가져오기
				hitdto = hitdao.hitInfo(member_indexkey2, good_indexkey);
			}
			// hit db를 만들었다면 hit db 가져오기
			hitdto = hitdao.hitInfo(member_indexkey2, good_indexkey);
			map.put("hitinfo", hitdto);
		}
		map.put("content", gooddao.GoodChoiceView(good_indexkey));
		//map.put(file) 파일 정보 담아서 보내고 받아야 해 
		return map;
	}

	//gModify.jsp 이동
	@RequestMapping(value = "/goodModifyView", method = RequestMethod.GET)
	public String boardModifyView(@RequestParam("indexkey") int indexkey, @ModelAttribute("pBase") PagingBase pBase,
			Model model) {
		System.out.println("controller modifyView pBase");
		System.out.println(pBase.getKeyword());
		System.out.println(pBase.getUserPage());
		model.addAttribute("indexkey", indexkey);
		model.addAttribute("pBase", pBase);
		return "goodchoiceboard/gModify";
	}

	//수정폼으로 정보 불러오기
	@RequestMapping(value = "/goodModify", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> goodModify(@RequestParam("indexkey") int indexkey, Model model) {
		Map<String, Object> map = new HashMap<>();
		map.put("content", gooddao.GoodModifyView(indexkey));
		return map;
	}
	
	//수정값 받아서 db에 저장 수정완료
	@RequestMapping(value = "/goodModifyOk", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> goodModifyOk(@ModelAttribute GoodDTO gooddto) {
		System.out.println("게시글 수정 요청 받았다~");
		Map<String, Object> map = new HashMap<>();
		
		Integer good_indexkey= gooddto.getIndexkey();
		String[] files= gooddto.getFiles();
		
		int result=0;
		result= gooddao.GoodModify(gooddto);
		System.out.println("1이라면 성공 result="+result);
		map.put("result", result);
		if(result==1) {
			System.out.println("게시글 업로드 파일 전부 삭제 시켜버리기");
			uploadfiledao.deleteAllFiles(good_indexkey);
		}
		
		if(files==null) {
			return map;
		}
		
		for(String fileName: files) {
			System.out.println("첨부파일이 있다면 새로 만들어버리기~");
			uploadfiledao.modifyFile(good_indexkey, fileName);
		}
			
		
		
		return map;
	}

	//글 삭제
	@RequestMapping(value = "/goodDelete", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> goodDelete(@RequestParam("indexkey") int indexkey) {
		Map<String, Object> map = new HashMap<>();
		System.out.println("게시글 삭제 처리 요청 받았다 ");
		//파일 먼저 삭제 하고
		int rs= uploadfiledao.deleteAllFiles(indexkey);
		System.out.println("파일먼저 삭제했다 ");
		System.out.println("실패는  null 성공은 파일갯수 파일 전체 삭제 성공했으면 ="+rs);
		int result=0;
		/*if(rs != 0) {
			result =gooddao.GoodDelete(indexkey);
		}*/
		//게시글도 삭제
		result =gooddao.GoodDelete(indexkey);
		
		map.put("result", result);
		return map;
		//그러나 db 구조상 good테이블의 indekxey Pk는 업로드파일테이블의 good_indexkey가 외래키로 지정되있다
		//on delete cascade 구문으로 처리해두었음으로 게시글이 삭제되면 DB상에 업로드파일 데이터는 자동 삭제 된다 .
	}

	/*
	 * @RequestMapping(value="/goodUphit", method=RequestMethod.POST)
	 * 
	 * @ResponseBody public Map<String, Object> upHit(@RequestParam("indexkey") int
	 * indexkey) { System.out.println("controller.uphit.indexkey="+indexkey);
	 * Map<String, Object> map=new HashMap<>(); map.put("hit",
	 * gooddao.upHit(indexkey)); return map; }
	 */

	//1 계정 1게시글 1좋아요 
	@RequestMapping(value = "/goodUphit2", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> upHit2(@ModelAttribute HitDTO hitdto) {

		Map<String, Object> map = new HashMap<>();
		ArrayList<String> msgs = new ArrayList<>();

		int member_indexkey = hitdto.getMember_indexkey();
		int good_indexkey = hitdto.getGood_indexkey();

		// 히트 check 가져오기
		String hit = hitdao.hit(member_indexkey, good_indexkey);
		int hit_check = Integer.parseInt(hit);
		// 히트체크 가져오기 2
		HitDTO hit2 = hitdao.hitInfo(member_indexkey, good_indexkey);
		int hit_check2 = hit2.getHit_check();
		System.out.println("controller_hit_check=" + hit_check);
		System.out.println("controller_hit_check2=" + hit_check2);

		// 게시판 hit_count 가져오기
		GoodDTO good = gooddao.GoodChoiceView(good_indexkey);
		// good게시판의 좋아요 숫자
		int hit_count = good.getHit_count();
		System.out.println("controller_good_hit_count=" + hit_count);

		if (hit_check == 0) {
			// hit_check =1
			int result = hitdao.hitCheck(hitdto);
			System.out.println("hit_0_result=" + result);
			// good_hitcount +1
			int rs = gooddao.hit_up(good_indexkey);
			System.out.println("hit_0_rs=" + rs);
			msgs.add("좋아요!");

			//ajax 구현 페이지 이동이 일어나지않기때문에 view에 바로 값을 넣어서 보여줘야함
			hit_check = 1;
			hit_count++;

		} else if (hit_check == 1) {
			// hit_check 0
			int result = hitdao.hitCheck_cancel(hitdto);
			System.out.println("hit_1_result=" + result);
			// good_hit_count -1
			int rs = gooddao.hit_down(good_indexkey);
			System.out.println("hit_1_rs=" + rs);
			msgs.add("좋아요 취소");

			hit_check = 0;
			hit_count--;
		}

		map.put("hit_check", hit_check);
		map.put("hit_count", hit_count);
		map.put("msg", msgs);

		return map;
	}
	
	
	@RequestMapping(value = "/hitList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> hitList(Model model) {
		Map<String, Object> map = new HashMap<>();
		map.put("hitList",gooddao.hitList());
		return map;
	}
	@RequestMapping(value = "/viewList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> viewList(Model model) {
		Map<String, Object> map = new HashMap<>();
		map.put("viewList",gooddao.viewList());
		return map;
	}
	@RequestMapping(value = "/dttmList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> dttmList(Model model) {
		Map<String, Object> map = new HashMap<>();
		map.put("dttmList",gooddao.dttmList());
		return map;
	}
	
}
