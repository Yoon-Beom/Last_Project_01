package com.spring.test.member.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tiles.template.AddAttributeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.test.member.dao.MemberDAO;
import com.spring.test.member.hash.Hash;
import com.spring.test.member.service.MemberService;
import com.spring.test.member.vo.MemberVO;

@Controller("memberController")
public class MemberControllerImpl implements MemberController {

	@Autowired
	private MemberService memberService;
	@Autowired
	MemberVO memberVO;
	
	@Autowired
	MemberDAO memberDAO;

	@RequestMapping(value = { "/", "/main.do" }, method = RequestMethod.GET)
	private ModelAndView main(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("MemberControllerImpl : main start");
		System.out.println("인정 : 나는 바보다!");
		System.out.println("윤범 : 인정님은 천재에요!!");
		System.out.println("윤범 : 해수님은 바보에요!");
		System.out.println("가연 : 오프라인가고시퍼요");
		System.out.println("윤범 : 가연님 패치해요!");

		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);

		System.out.println("MemberControllerImpl : main end");
		return mav;
	}

	@Override
	@RequestMapping(value = "/member/listMembers.do", method = RequestMethod.GET)
	public ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberControllerImpl : listMembers start");

		String viewName = (String) request.getAttribute("viewName");
		List membersList = memberService.listMembers();
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("membersList", membersList);

		System.out.println("MemberControllerImpl : listMembers end");
		return mav;
	}

	@Override
	@RequestMapping(value="/member/addMember.do", method = RequestMethod.POST)
	public ModelAndView addMember(@ModelAttribute("member") MemberVO member,
			                  HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberControllerImpl : addMember start");
		
		request.setCharacterEncoding("euc-kr");
		
		int result = 0;
				
		String yy = request.getParameter("member_yy");
		String mm = request.getParameter("member_mm");
		String dd = request.getParameter("member_dd");

		String member_phone1 = request.getParameter("member_phone1");
		String member_phone2 = request.getParameter("member_phone2");
		String member_phone3 = request.getParameter("member_phone3");
		
		String member_post = request.getParameter("member_post");
		String member_addr = request.getParameter("member_addr");
		String member_detailAddr = request.getParameter("member_detailAddr");
		
		member.setMember_birth(yy + "-" + mm + "-" + dd);
		member.setMember_phone(member_phone1 + "-" + member_phone2 + "-" + member_phone3);
		member.setMember_address(member_post + "," + member_addr + "," + member_detailAddr);
		System.out.println("---------- member VO ----------");
		System.out.println("member_id : " + member.getMember_id());
		System.out.println("member_pwd : " + member.getMember_pwd());
		System.out.println("member_salt : " + member.getMember_salt());
		System.out.println("member_name : " + member.getMember_name());
		System.out.println("member_birth : " + member.getMember_birth());
		System.out.println("member_phone : " + member.getMember_phone());
		System.out.println("member_email : " + member.getMember_email());
		System.out.println("member_gender : " + member.getMember_gender());
		System.out.println("member_address : " + member.getMember_address());
		System.out.println("-------------------------------");	
		result = memberService.addMember(member);	
		System.out.println("result : " + result); 
		ModelAndView mav = new ModelAndView("redirect:/main.do");
		System.out.println("MemberControllerImpl : addMember end");
		return mav;
	}

	@Override
	@RequestMapping(value="/member/removeMember.do" ,method = RequestMethod.GET)
	public ModelAndView removeMember(@RequestParam("id") String member_id, 
			           HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("MemberControllerImpl : removeMember start");
		
		System.out.println(member_id);
		request.setCharacterEncoding("euc-kr");
		memberService.removeMember(member_id);
		ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
		
		System.out.println("MemberControllerImpl : removeMember end");
		return mav;
	}

	@Override
	@RequestMapping(value = "/member/login.do", method = RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("member") MemberVO member,
			RedirectAttributes rAttr,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberControllerImpl : login start");

		String id = member.getMember_id();
		String pwd = member.getMember_pwd();

		ModelAndView mav = new ModelAndView();
		memberVO = memberService.login(member);
		
		if(memberVO != null) {
			int memberNO = memberVO.getMember_NO();
			HttpSession session = request.getSession();
			session.setAttribute("member", memberVO);
			session.setAttribute("isLogOn", true);
			System.out.println("login member : " + memberNO);
			//mav.setViewName("redirect:/member/listMembers.do");
			String action = (String)session.getAttribute("action");
			session.removeAttribute("action");

			if(action!= null) {
				mav.setViewName("redirect:" + action);
			} else {
				mav.setViewName("redirect:/main.do");	
			}
		} else {
			rAttr.addFlashAttribute("result", "loginFailed");
			mav.setViewName("redirect:/login.do");
		}

		System.out.println("MemberControllerImpl : login end");
		return mav;
	}

	@Override
	@RequestMapping(value = "/member/logout.do", method =  RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberControllerImpl : logout start");
		
		HttpSession session = request.getSession();
		session.removeAttribute("member");
		session.removeAttribute("isLogOn");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/main.do");
		
		System.out.println("MemberControllerImpl : logout end");
		return mav;
	}
	
	// 마이 페이지 회원정보 수정 비밀번호 확인
	 @RequestMapping(value = "/mypage/checkMem.do", method = RequestMethod.POST)
	   public ModelAndView pwdCheckMem(@ModelAttribute("member") MemberVO member, RedirectAttributes rAttr,
	         HttpServletRequest request, HttpServletResponse response) throws Exception {
	      HttpSession session= request.getSession();
	      MemberVO vo = (MemberVO) session.getAttribute("member");
	      System.out.println("mypage id : " + vo.getMember_id());
	      System.out.println("mypage pwd : " + vo.getMember_pwd());
	      Hash hash = new Hash();
	      String salt = memberDAO.selectSaltById(vo.getMember_id());
	      String pwd = vo.getMember_pwd();
	      String pwdcheck = request.getParameter("pwdcheck");
	      String hash_pwd = hash.Hashing(pwdcheck.getBytes(), salt);
	      vo.setMember_pwd(hash_pwd);
	      System.out.println("pwd : " + pwd);
	      System.out.println("pwdcheck : " + hash_pwd);
	      ModelAndView mav = new ModelAndView();
	      if(hash_pwd != null) {
	         if(pwd.equals(hash_pwd)) {
	            mav.setViewName("redirect:/mypage/membershipMod.do");
	         } else {
	            rAttr.addFlashAttribute("result", "pwdCheckFail");
	            mav.setViewName("redirect:/mypage/pwdCheckMem.do");
	         }
	      }
	      return mav;
	   }

	 
		// 마이 페이지 비밀번호 수정
	 @RequestMapping(value = "/mypage/checkPwd.do", method = RequestMethod.POST)
	   public ModelAndView pwdCheckPwd(@ModelAttribute("member") MemberVO member, RedirectAttributes rAttr,
	         HttpServletRequest request, HttpServletResponse response) throws Exception {
	      HttpSession session= request.getSession();
	      MemberVO vo = (MemberVO) session.getAttribute("member");
	      int result = 0;
	      System.out.println("mypage id : " + vo.getMember_id());
	      System.out.println("mypage pwd : " + vo.getMember_pwd());
	      Hash hash = new Hash();
	      String salt = memberDAO.selectSaltById(vo.getMember_id());
	      String pwd = vo.getMember_pwd();
	      String pwdNow = request.getParameter("pwdNow");
	      String hash_pwd = hash.Hashing(pwdNow.getBytes(), salt);
	      String member_pwd = request.getParameter("member_pwd");
	      String member_pwd2 = request.getParameter("member_pwd2");
	      vo.setMember_pwd(hash_pwd);
	      System.out.println("pwd : " + pwd);
	      System.out.println("pwdNow : " + hash_pwd);
	      ModelAndView mav = new ModelAndView();
	      if(hash_pwd != null) {
	         if(!(pwd.equals(hash_pwd))) {
	            rAttr.addFlashAttribute("result", "pwdCheckFail");
	            mav.setViewName("redirect:/mypage/pwdCheckPwd.do");
	         } else {
	        	
		        		vo.setMember_pwd(member_pwd);
		        		result = memberService.updateMemPwd(vo);
		        		rAttr.addFlashAttribute("result", "pwdChange");
						mav.setViewName("redirect:/mypage/myPage.do");
		        
	         }
	      }
	      return mav;
	   }
	 
	 // 마이페이지 회원정보 수정
	@Override
	@RequestMapping(value = "/mypage/update.do", method = RequestMethod.POST)
	public ModelAndView updateMember(@ModelAttribute("member") MemberVO vo, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String birth = vo.getMember_birth();
		System.out.println("member수정 시작");
		int result = 0;
		String phone1 = request.getParameter("member_phone1");
		String phone2 = request.getParameter("member_phone2");
		String phone3 = request.getParameter("member_phone3");
		
		String member_post = request.getParameter("member_post");
		String member_addr = request.getParameter("member_addr");
		String member_detailAddr = request.getParameter("member_detailAddr");
		vo.setMember_phone(phone1 + "-" + phone2 + "-" + phone3);
		vo.setMember_address(member_post + "," + member_addr + "," + member_detailAddr);
		result = memberService.updateMember(vo);
		HttpSession session = request.getSession();
		/* session.invalidate();  세션 제거*/	
		request.getSession().setAttribute("member", vo);
		ModelAndView mav = new ModelAndView("redirect:/mypage/myPage.do");
		System.out.println("member수정 끝");
		return mav;
	}

	@Override
	@RequestMapping(value = "/*/memberIdCheckAction.do", method =  RequestMethod.POST)
	public ModelAndView memberIdCheckAction (HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberControllerImpl : memberIdCheckAction start");
		
		String id = request.getParameter("id");
		int result = memberService.selectById(id);
		
		response.setContentType("text/html;charset=euc-kr");
		PrintWriter out = response.getWriter();
		System.out.println("result : " + result);
		
		if(result == 1) { out.println("0"); } // 아이디 중복
		else { out.println("1"); } // 아이디 없음
		
		out.close();
		
		System.out.println("MemberControllerImpl : memberIdCheckAction end");
		return null;
	}

}
