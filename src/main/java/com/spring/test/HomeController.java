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
	
	@RequestMapping(value = "/board/freeBoardWriting.do", method = RequestMethod.GET)
	public String freeboardWriting(Locale locale, Model model) {


		return "board/freeBoardWriting";
	}
	
	@RequestMapping(value = "/board/qnaBoard.do", method = RequestMethod.GET)
	public String QnABoard(Locale locale, Model model) {


		return "board/qnaBoard";
	}
	
	@RequestMapping(value = "/board/qnaContent.do", method = RequestMethod.GET)
	public String QnAContent(Locale locale, Model model) {


		return "board/qnaContent";
	}
	
	@RequestMapping(value = "/board/qnaBoardWriting.do", method = RequestMethod.GET)
	public String qnaboardWriting(Locale locale, Model model) {


		return "board/qnaBoardWriting";
	}
	
	@RequestMapping(value = "/board/noticeBoard.do", method = RequestMethod.GET)
	public String noticeBoard(Locale locale, Model model) {


		return "board/noticeBoard";
	}
	
	@RequestMapping(value = "/board/noticeContent.do", method = RequestMethod.GET)
	public String noticeContent(Locale locale, Model model) {


		return "board/noticeContent";
	}

	@RequestMapping(value = "/mypage/myPage.do", method = RequestMethod.GET)
	public String myPage(Locale locale, Model model) {
		
		
		return "mypage/myPage";
}
	@RequestMapping(value = "/mypage/pwdCheck.do", method = RequestMethod.GET)
	public String pwdCheck(Locale locale, Model model) {
		
		
		return "mypage/pwdCheck";
}
	@RequestMapping(value = "/mypage/membershipMod.do", method = RequestMethod.GET)
	public String membershipMod(Locale locale, Model model) {
		
		
		return "mypage/membershipMod";
}
	@RequestMapping(value = "/mypage/petAdd.do", method = RequestMethod.GET)
	public String petAdd(Locale locale, Model model) {
		
		
		return "mypage/petAdd";
}
	@RequestMapping(value = "/mypage/review.do", method = RequestMethod.GET)
	public String review(Locale locale, Model model) {
		
		
		return "mypage/review";
}
	@RequestMapping(value = "/mypage/reviewWrite.do", method = RequestMethod.GET)
	public String reviewWrite(Locale locale, Model model) {
		
		
		return "mypage/reviewWrite";
}
	@RequestMapping(value = "/mypage/visit.do", method = RequestMethod.GET)
	public String visit(Locale locale, Model model) {
		
		
		return "mypage/visit";
}
}
