package com.spring.test.reserve.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

public interface ReserveController {
	public ModelAndView reservation(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
