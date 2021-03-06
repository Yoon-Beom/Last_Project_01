package com.spring.test.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

public interface BoardController {
	// public ModelAndView listBoard(@RequestParam("board_code") String board_code, HttpServletRequest request, HttpServletResponse response)throws Exception;
	public ResponseEntity addNewFreeBoard(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) throws Exception;
	// public ModelAndView viewArticle(@RequestParam("board_NO") int articleNO, HttpServletRequest request, HttpServletResponse response)throws Exception;
	public ResponseEntity  removeBoard(@RequestParam("board_NO") int board_NO, HttpServletRequest request, HttpServletResponse response) throws Exception;
}
