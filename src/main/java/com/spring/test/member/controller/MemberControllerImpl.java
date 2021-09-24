package com.spring.test.member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.test.member.service.MemberService;
import com.spring.test.member.vo.MemberVO;



@Controller("memberController")
public class MemberControllerImpl implements MemberController{
	
	@Autowired
	private MemberService memberService;
	@Autowired
	MemberVO memberVO ;
	
	@RequestMapping(value = {"/", "/main.do"}, method = RequestMethod.GET)
	private ModelAndView main(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("MemberControllerImpl : main start");
		System.out.println("인정 : 나는 바보다!");
		System.out.println("윤범 : 인정님은 천재에요!!");
		System.out.println("윤범 : 해수님은 바보에요!");
		System.out.println("가연 : 오프라인가고시퍼요");
		System.out.println("윤범 : 가연님 패치해요!");
		
		String viewName = (String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		
		System.out.println("MemberControllerImpl : main end");
		return mav;
	}
	
	
	@Override
	@RequestMapping(value="/member/listMembers.do", method = RequestMethod.GET)
	public ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberControllerImpl : listMembers start");
		
		String viewName = (String)request.getAttribute("viewName");
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
}
