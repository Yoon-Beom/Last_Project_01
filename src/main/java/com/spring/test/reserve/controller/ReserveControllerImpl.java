package com.spring.test.reserve.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spring.test.member.vo.MemberVO;
import com.spring.test.pet.service.PetService;
import com.spring.test.pet.vo.PetVO;

@Controller("reserveController")
public class ReserveControllerImpl implements ReserveController {
	@Autowired
	private PetService petService;
	@Autowired
	private PetVO petVO;
	@Autowired
	private MemberVO memberVO;
	
	@Override
	@RequestMapping(value = "/reserve.do", method = RequestMethod.GET)
	public String reservation(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		memberVO = (MemberVO) session.getAttribute("member");
		
		
		return "reserve";
	}
	
//	@Override
//	@RequestMapping(value = "/mypage/myPage.do", method = RequestMethod.POST)
//	public ModelAndView listPet(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		HttpSession session = request.getSession();
//		memberVO = (MemberVO) session.getAttribute("member");
//		int member_NO = memberVO.getMember_NO();
//		System.out.println("listPet member_NO : " + member_NO);
//		String viewName = (String) request.getAttribute("viewName");
//		List petList = petService.listPet(member_NO);
//		ModelAndView mav = new ModelAndView(viewName);
//		mav.addObject("member", memberVO);
//		mav.addObject("petList", petList);
//
//		return mav;
//	}
}
