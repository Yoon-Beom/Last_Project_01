package com.spring.test.shop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spring.test.shop.service.ShopService;
import com.spring.test.shop.vo.ShopVO;

@Controller("shopController")
public class ShopControllerImpl implements ShopController{
	
	@Autowired
	private ShopService shopService;
	@Autowired
	private ShopVO shopVO;
	
	@Override
	@RequestMapping(value = "map.do", method = RequestMethod.GET)
	public ModelAndView listShop(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String viewName = (String)request.getAttribute("viewName");
		List shopList = shopService.listMembers();
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("shopList", shopList);
		System.out.println("리턴 값 : " + mav);
		HttpSession session = request.getSession();
		session.setAttribute("shopList", shopList);
		
		return mav;
	}
}
