package com.spring.test.pet.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.spring.test.member.vo.MemberVO;

public interface PetController {
	public ModelAndView listPet( HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ResponseEntity addPet(@ModelAttribute("member") MemberVO member, MultipartHttpServletRequest multipartRequest,HttpServletResponse response) throws Exception;
	
//	  public ModelAndView viewPet(@RequestParam("pet_NO") int pet_NO,
//	 HttpServletRequest request, HttpServletResponse response) throws Exception;
	 
}
