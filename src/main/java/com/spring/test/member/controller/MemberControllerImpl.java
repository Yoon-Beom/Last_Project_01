package com.spring.test.member.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tiles.template.AddAttributeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.test.member.service.MemberService;
import com.spring.test.member.vo.MemberVO;

@Controller("memberController")
public class MemberControllerImpl implements MemberController {

	@Autowired
	private MemberService memberService;
	@Autowired
	MemberVO memberVO;

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
	@RequestMapping(value = "/member/addMember.do", method = RequestMethod.POST)
	public ModelAndView addMember(@ModelAttribute("member") MemberVO member, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("MemberControllerImpl : addMember start");
		request.setCharacterEncoding("euc-kr");
		response.setCharacterEncoding("text/html; charset=utf-8");
		int result = 0;
		String yy = request.getParameter("member_yy");
		String mm = request.getParameter("member_mm");
		String dd = request.getParameter("member_dd");
		member.setMember_birth(yy + "-" + mm + "-" + dd);
		System.out.println(member.getMember_birth());
		System.out.println(member);
		result = memberService.addMember(member);
		ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
		System.out.println("MemberControllerImpl : addMember end");
		return mav;
	}

	@Override
	@RequestMapping(value = "/member/removeMember.do", method = RequestMethod.GET)
	public ModelAndView removeMember(@RequestParam("id") String member_id, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
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
	public ModelAndView login(@ModelAttribute("member") MemberVO member, RedirectAttributes rAttr,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = member.getMember_id();
		String pwd = member.getMember_pwd();

		System.out.println("id :" + id + "pwd:" + pwd);
		System.out.println("login start");
		ModelAndView mav = new ModelAndView();
		memberVO = memberService.login(member);
		if (memberVO != null) {
			HttpSession session = request.getSession();
			session.setAttribute("member", memberVO);
			session.setAttribute("isLogOn", true);
			// mav.setViewName("redirect:/member/listMembers.do");
			String action = (String) session.getAttribute("action");
			session.removeAttribute("action");
			if (action != null) {
				mav.setViewName("redirect:" + action);
			} else {
				mav.setViewName("redirect:/main.do");
			}

		} else {
			rAttr.addFlashAttribute("result", "loginFailed");
			mav.setViewName("redirect:/login.do");
			/*
			 * PrintWriter out = response.getWriter();
			 * out.println("<script>alert('로그인 정보를 확인해주세요.'); history.go(-1);</script>");
			 * out.flush();
			 */
		}

		System.out.println("login end");
		return mav;
	}

	@Override
	@RequestMapping(value = "/member/logout.do", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		session.removeAttribute("member");
		session.removeAttribute("isLogOn");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/main.do");
		return mav;
	}

	@RequestMapping(value = "/mypage/check.do", method = RequestMethod.POST)
	public ModelAndView pwdCheck(@ModelAttribute("member") MemberVO member, RedirectAttributes rAttr,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session= request.getSession();
		MemberVO vo = (MemberVO) session.getAttribute("member");
		String pwd = vo.getMember_pwd();
		String pwdcheck = request.getParameter("pwdcheck");
		System.out.println("pwd : " + pwd);
		System.out.println("pwdcheck : " + pwdcheck);
		ModelAndView mav = new ModelAndView();
		if(pwdcheck != null) {
			if(pwd.equals(pwdcheck)) {
				mav.setViewName("redirect:/mypage/membershipMod.do");
			} else {
				rAttr.addFlashAttribute("result", "pwdCheckFail");
				mav.setViewName("redirect:/mypage/pwdCheck.do");
			}
		}
		return mav;

	}

	@Override
	@RequestMapping(value = "/mypage/update.do", method = RequestMethod.POST)
	public ModelAndView updateMember(@ModelAttribute("member") MemberVO vo, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String birth = vo.getMember_birth();
		System.out.println("birth : " + birth);
		System.out.println("member수정 시작");
		int result = 0;
		String phone1 = request.getParameter("member_phone1");
		String phone2 = request.getParameter("member_phone2");
		String phone3 = request.getParameter("member_phone3");
		System.out.println("phone1 : " + phone1);
		vo.setMember_phone(phone1 + "-" + phone2 + "-" + phone3);
		result = memberService.updateMember(vo);
		
		HttpSession session = request.getSession();
		/*
		 * session.removeAttribute("member"); session.setAttribute("member", vo);
		 */
		  session.invalidate();		
		request.getSession().setAttribute("member", vo);
		ModelAndView mav = new ModelAndView("redirect:/mypage/myPage.do");
		System.out.println("member수정 끝");
		return mav;
	}

}
