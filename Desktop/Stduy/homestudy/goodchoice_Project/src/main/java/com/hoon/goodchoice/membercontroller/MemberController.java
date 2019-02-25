package com.hoon.goodchoice.membercontroller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hoon.goodchoice.memberdao.MemberDAO;
import com.hoon.goodchoice.memberdto.MemberDTO;

@Controller
public class MemberController {

	@Autowired
	MemberDAO memberdao;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String basic(Model model) {
		return "home";
	}
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String Home(Model model) {
		return "home";
	}
	@RequestMapping(value="/portfolio", method=RequestMethod.GET)
	public String portfolio(Model model) {
		return "portfolio";
	}
	@RequestMapping(value="/jusoPopup", method= {RequestMethod.GET , RequestMethod.POST})
	public String jusoPopup(Model model) {
		return "member/jusoPopup";
	}
	@RequestMapping(value="/memberLoginView", method=RequestMethod.GET)
	public String memberLoginView(Model model){
		return "member/mLogin";
	}
	@RequestMapping(value="/memberLogin", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> memberLogin(@RequestParam("id_email") String id_email, 
			@RequestParam("password") String password, HttpServletRequest request){
		
		Map<String, Object> map=new HashMap<>();
		System.out.println("membercontroler_login_id_email="+id_email);
		System.out.println("membercontroler_login_password="+password);
		//히든유저정보 indexkey값을 활용하기 위해서 sessionId에 담는다 (indexkey값은 모든 게시판및 댓글에 사용될거 같다) 
		MemberDTO hidden=memberdao.memberHidden(id_email, password);
		System.out.println("membercontroler_login_hidden="+hidden);
		//로그인 결과
		int result=memberdao.memberLogin(id_email, password);
		System.out.println("membercontroler_login_result="+result);
		HttpSession session=request.getSession();
		if(result==1) {
			session.setAttribute("sessionId",hidden);
			session.setMaxInactiveInterval(60*10);
		}
		System.out.println("controller.login.result="+result);
		map.put("result", result);
		
		return map;
	}
	//로그인시 indexkey값을 받아서 회원정보를 불러온다 // 회원정보를 계속 해서 유지하기 위함이다
	@RequestMapping(value="/memberHidden", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> memberhidden(@RequestParam("indexkey") String indexkey , Model model){
		System.out.println("controller_hidden_indexkey="+indexkey);
		Map<String, Object> map=new HashMap<>();
		int indexkey2= Integer.parseInt(indexkey);
		map.put("hidden2", memberdao.memberHidden2(indexkey2));
		return map;
	}
	
	@RequestMapping(value="/memberLogout", method=RequestMethod.GET)
	public String memberLogout(HttpServletRequest request, Model model){
		HttpSession session=request.getSession();
		session.invalidate();
		return "redirect:home";
	}
	
	
	@RequestMapping(value="/memberJoinView", method=RequestMethod.GET)
	public String memberJoinView(Model model){
		return "member/mJoin";
	}
	
	@RequestMapping(value="/memberIdChecked", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> memberIdCheck(@RequestParam("id_email") String id_email, Model model){
		Map<String, Object> map=new HashMap<>();
		map.put("result", memberdao.memberIdCheck(id_email));
		return map;
	}
	@RequestMapping(value="/nicknameChecked", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> nicknameCheck(@RequestParam("nickname") String nickname, Model model){
		Map<String, Object> map=new HashMap<>();
		map.put("result", memberdao.nicknameCheck(nickname));
		return map;
	}
	
	//회원가입 되면 로그인화면으로 이동 
	@RequestMapping(value="/memberJoin", method=RequestMethod.POST)
	public String memberInsert(@ModelAttribute MemberDTO memberdto, Model model){
		memberdao.memberInsert(memberdto);
		System.out.println("회원 가입 완료 ");
		return "redirect:memberLoginView";
	}
	
	//다른사람이 회원 프로필보기 가능
	@RequestMapping(value="/memberInfoView", method=RequestMethod.GET)
	public String memberInfoView(@RequestParam("nickname") String nickname, HttpServletRequest request,  Model model){
		MemberDTO profile=memberdao.memberInfoView(nickname);
		/*model.addAttribute("profile", profile);*/
		request.setAttribute("profile", profile);
		return "member/mInfo";
	}
	
	
	@RequestMapping(value="/memberModifyView", method=RequestMethod.GET)
	public String mModify(@RequestParam("indexkey") String indexkey,Model model){
		int indexkey2=Integer.parseInt(indexkey);
		MemberDTO member=memberdao.memberModifyView(indexkey2);
		model.addAttribute("member", member);
		return "member/mModify";
	}
	
	
	@RequestMapping(value="/memberModify", method=RequestMethod.POST)
	public String memberModify(@ModelAttribute MemberDTO memberdto , Model model){
		memberdao.memberModify(memberdto);

		return "home";
	}
	
	
	@RequestMapping(value="/memberDeleteView", method=RequestMethod.GET)
	public String memberDeleteView(Model model) {
		
		return "member/mDelete";
	}
	
	@RequestMapping(value="/memberDelete", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> memberDelete(@ModelAttribute MemberDTO memberdto, HttpServletRequest request){
		Map<String, Object> map = new HashMap<>();
		
		int result=memberdao.memberDelete(memberdto);
		HttpSession session=request.getSession();
		if(result==1) {
			session.invalidate();
		}
		map.put("result", result);
		return map;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
