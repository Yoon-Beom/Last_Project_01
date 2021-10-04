package com.spring.test.comment.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

public interface CommentController {
	public ResponseEntity addComment(@RequestParam("board_NO") int board_NO, MultipartHttpServletRequest multipartRequest, HttpServletResponse response)throws Exception;
	public ResponseEntity  removeComment(@RequestParam("board_NO") int board_NO,
			MultipartHttpServletRequest multipartRequest, HttpServletResponse response) throws Exception;
}
