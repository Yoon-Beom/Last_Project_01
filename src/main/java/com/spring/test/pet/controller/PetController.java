package com.spring.test.pet.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.spring.test.member.vo.MemberVO;
import com.spring.test.pet.vo.PetVO;

public interface PetController {
	public ModelAndView listPet( HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ResponseEntity addPet(@ModelAttribute("member") MemberVO member, MultipartHttpServletRequest multipartRequest,HttpServletResponse response) throws Exception;
	/*
	 * public ModelAndView updatePet(PetVO vo, HttpServletRequest request,
	 * HttpServletResponse response, MultipartHttpServletRequest multipartRequest)
	 * throws Exception;
	 */

	 
}
