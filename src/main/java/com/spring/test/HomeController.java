package com.spring.test;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);


//	@RequestMapping(value = "/main.do", method = RequestMethod.GET)
//	public String home(Locale locale, Model model) {
//
//
//		return "main";
//	}

	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String login(Locale locale, Model model) {


		return "login";
	}

	@RequestMapping(value = "/member/membership.do", method = RequestMethod.GET)
	public String membership(Locale locale, Model model) {


		return "member/membership";
	}
	
	@RequestMapping(value = "/member/membershipForm.do", method = RequestMethod.GET)
	public String membershipForm(Locale locale, Model model) {


		return "member/membershipForm";
	}
	
	@RequestMapping(value = "/shop/shopmembershipForm.do", method = RequestMethod.GET)
	public String shopmembershipForm(Locale locale, Model model) {


		return "shop/shopmembershipForm";
	}
	
	@RequestMapping(value = "map.do", method = RequestMethod.GET)
	public String map(Locale locale, Model model) {


		return "map";
	}
	
	@RequestMapping(value = "/board/freeboard.do", method = RequestMethod.GET)
	public String freeboard(Locale locale, Model model) {


		return "board/freeboard";
	}
	
	@RequestMapping(value = "/board/freeContent.do", method = RequestMethod.GET)
	public String freeContent(Locale locale, Model model) {


		return "board/freeContent";
	}
	
	@RequestMapping(value = "/board/freeBoardWrting.do", method = RequestMethod.GET)
	public String freeboardWrting(Locale locale, Model model) {


		return "board/freeBoardWrting";
	}
	
	@RequestMapping(value = "/board/qnaBoard.do", method = RequestMethod.GET)
	public String QnABoard(Locale locale, Model model) {


		return "board/qnaBoard";
	}
	
	@RequestMapping(value = "/board/qnaContent.do", method = RequestMethod.GET)
	public String QnAContent(Locale locale, Model model) {


		return "board/qnaContent";
	}
	
	@RequestMapping(value = "/board/qnaBoardWrting.do", method = RequestMethod.GET)
	public String qnaboardWrting(Locale locale, Model model) {


		return "board/qnaBoardWrting";
	}
	
	@RequestMapping(value = "/board/noticeBoard.do", method = RequestMethod.GET)
	public String noticeBoard(Locale locale, Model model) {


		return "board/noticeBoard";
	}
	
	@RequestMapping(value = "/board/noticeContent.do", method = RequestMethod.GET)
	public String noticeContent(Locale locale, Model model) {


		return "board/noticeContent";
	}

}
