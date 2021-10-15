package com.spring.test.reserve.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ReserveController {
	public String reservation(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
