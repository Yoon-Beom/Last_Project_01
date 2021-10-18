package com.spring.test.reserve.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.spring.test.reserve.vo.ReserveVO;
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
	@Autowired
	private ReserveVO reserveVO;
	
	@Override
	@RequestMapping(value = "/reserve/reserve.do", method = RequestMethod.GET)
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
	
	@Override
	@RequestMapping(value = "/reserve/reserveAction.do", method = RequestMethod.POST)
	public ModelAndView reserveAction(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ReserveControllerImpl : reserveAction start");
		
		ModelAndView mav = new ModelAndView("redirect:/main.do");
		
		HttpSession session = request.getSession();
		memberVO = (MemberVO) session.getAttribute("member");
		
		String shop_NO = request.getParameter("shop_NO");
		String pet_select = request.getParameter("pet_select");
		
		System.out.println("shop_NO : " + shop_NO);
		System.out.println("pet_select : " + pet_select);

		if(pet_select.equals("0")) {
			String pet_name = request.getParameter("pet_name");
			String pet_age = request.getParameter("pet_age");
			String pet_scale = request.getParameter("pet_scale");

			System.out.println("pet_name : " + pet_name);
			System.out.println("pet_age : " + pet_age);
			System.out.println("pet_scale : " + pet_scale);

			Map<String, Object> petMap = new HashMap<String, Object>();
			petMap.put("member_NO", memberVO.getMember_NO());
			petMap.put("pet_name", pet_name);
			petMap.put("pet_age", Integer.parseInt(pet_age));
			petMap.put("pet_scale", pet_scale);

			
		} else if (pet_select.equals("0")) {
			String pet_NO = request.getParameter("pet_NO");
			System.out.println("pet_NO : " + pet_NO);
		}
		
		String res_spot = request.getParameter("res_spot");
		String res_option = request.getParameter("res_option");
		String reserve_Date = request.getParameter("reserve_Date");
		String reserve_TimeA = request.getParameter("reserve_TimeA");
		
		System.out.println("res_spot : " + res_spot);
		System.out.println("res_option : " + res_option);
		System.out.println("reserve_Date : " + reserve_Date);
		System.out.println("reserve_TimeA : " + reserve_TimeA);
		
		System.out.println("ReserveControllerImpl : reserveAction end");
		return mav;
	}

}
