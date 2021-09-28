package com.spring.test.shop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

public interface ShopController {
	public ModelAndView listShop(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
