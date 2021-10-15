package com.spring.test.shop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.spring.test.member.vo.MemberVO;
import com.spring.test.shop.vo.ShopDetailVO;
import com.spring.test.shop.vo.ShopVO;

public interface ShopController {
	public ModelAndView listShop(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView addShop(MemberVO member, ShopVO shop, ShopDetailVO shopDetail, HttpServletRequest request,
			HttpServletResponse response) throws Exception;
	public ModelAndView listShopAndDetail(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView listShopView(int shop_NO, HttpServletRequest request, HttpServletResponse response) throws Exception;
}