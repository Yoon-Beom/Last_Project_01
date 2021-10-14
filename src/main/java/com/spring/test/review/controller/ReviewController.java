package com.spring.test.review.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spring.test.review.vo.Criteria1;

public interface ReviewController {
	public Model list( Model model, Criteria1 cri,
			 HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ResponseEntity reviewWrite(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) throws Exception;
	public ResponseEntity  removeBoard(@RequestParam("review_NO") int review_NO,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
}
