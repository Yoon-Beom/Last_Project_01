package com.spring.test.comment.controller;

import java.io.File;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.spring.test.comment.service.CommentService;
import com.spring.test.comment.vo.CommentVO;
import com.spring.test.member.vo.MemberVO;

@Controller("commentController")
public class CommentControllerImpl implements CommentController{
	private static final String ARTICLE_IMAGE_REPO = "C:\\workspace\\article_image";
	@Autowired
	private CommentService commentService;
	@Autowired
	private CommentVO commentVO;

	@Override
	@RequestMapping(value="/board/addcomment.do", method= {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public ResponseEntity addComment(
			@RequestParam("board_NO") int board_NO,
			MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
					throws Exception {

		System.out.println("addcomment start");
		multipartRequest.setCharacterEncoding("utf-8");
		Map<String, Object> commentMap = new HashMap<String, Object>();
		Enumeration enu = multipartRequest.getParameterNames();
		while(enu.hasMoreElements()) {
			String name=(String)enu.nextElement();
			System.out.println("enu : "+name);
			String value=multipartRequest.getParameter(name);
			System.out.println("value : "+value);
			commentMap.put(name, value);
		}
		
		HttpSession session= multipartRequest.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("member");
		int member_NO = memberVO.getMember_NO();
		commentMap.put("member_NO",member_NO);
		String message;
		ResponseEntity resEnt=null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html;charset=utf-8");
		try {
			int comment_NO = commentService.addComment(commentMap);

			System.out.println("comment_NO : "+comment_NO);

			message = "<script>";
			message += " alert('댓글을 추가했습니다.');";
			message += " location.href='"+multipartRequest.getContextPath()+"/board/freeContent.do?board_NO="+board_NO+"';";
			message +=" </script>";
			resEnt = new ResponseEntity(message,responseHeaders,HttpStatus.CREATED);
			System.out.println(commentMap);
		}catch(Exception e) {
			message = " <script>";
			message +=" alert('오류가 발생했습니다. 다시 시도해 주세요');";
			message +=" location.href='"+multipartRequest.getContextPath()+"/board/freeContent.do?board_NO="+board_NO+"';";
			message +="</script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			System.out.println("commentMap : "+commentMap);
			e.printStackTrace();
		}

		return resEnt;

	}



	//@RequestMapping(value="board/*Content.do" ,method = RequestMethod.GET)
	public ModelAndView viewArticle(@RequestParam("board_NO") int board_NO,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("viewArticleSTART");
		String viewName = (String)request.getAttribute("viewName");
		commentVO=commentService.viewArticle(board_NO);
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		mav.addObject("comment", commentVO);
		int NO = commentVO.getMember_NO();
		/*String  = commentVO.getBoard_title();
		System.out.println("name : "+name);
		System.out.println("title : "+title);*/
		return mav;
	}

	@Override
	@RequestMapping(value="/board/removecomment.do" ,method= {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public ResponseEntity  removeComment(
			@RequestParam("board_NO") int board_NO,
			MultipartHttpServletRequest multipartRequest, HttpServletResponse response) throws Exception{
		response.setContentType("text/html; charset=UTF-8");
		multipartRequest.setCharacterEncoding("utf-8");
		Map<String, Object> commentMap = new HashMap<String, Object>();
		Enumeration enu = multipartRequest.getParameterNames();
		while(enu.hasMoreElements()) {
			String name=(String)enu.nextElement();
			System.out.println("enu : "+name);
			String value=multipartRequest.getParameter(name);
			System.out.println("value : "+value);
			commentMap.put(name, value);
		}
		Object comment_NO = commentMap.get("comment_NO");
		System.out.println(comment_NO);
		String message;
		ResponseEntity resEnt=null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html;charset=utf-8");
		try {
			commentService.removeComment(comment_NO);
		
			message = "<script>";
			message += " alert('댓글을 삭제했습니다.');";
			message += " location.href='"+multipartRequest.getContextPath()+"/board/freeContent.do?board_NO="+board_NO+"';";
			message +=" </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);

		}catch(Exception e) {
			message = "<script>";
			message += " alert('작업중 오류가 발생했습니다.다시 시도해 주세요.');";
			message += " location.href='"+multipartRequest.getContextPath()+"/board/freeContent.do?board_NO="+board_NO+"';";
			message +=" </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}
		return resEnt;
	} 



}
