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
import com.spring.test.shop.service.ShopService;
import com.spring.test.shop.service.ShopServiceImpl;
import com.spring.test.shop.vo.ShopVO;

@Controller("reserveController")
public class ReserveControllerImpl implements ReserveController {
	@Autowired
	private PetService petService;
	@Autowired
	private PetVO petVO;
	@Autowired
	private MemberVO memberVO;
	@Autowired
	private ShopService shopService;
	
	@Override
	@RequestMapping(value = "/reserve.do", method = RequestMethod.GET)
	public ModelAndView reservation(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ReserveControllerImpl : reservation start");
		
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		
		HttpSession session = request.getSession();
		memberVO = (MemberVO) session.getAttribute("member");
		
		int member_NO = memberVO.getMember_NO();
		List petList = petService.listPet(member_NO);
		
		System.out.println("petList : " + petList);
		
		ShopVO shopVO = shopService.selectShopByShopNO(1);
		
		mav.addObject("petList", petList);
		mav.addObject("shopVO", shopVO);
		
		
		System.out.println("ReserveControllerImpl : reservation end");
		return mav;
	}
	

}
