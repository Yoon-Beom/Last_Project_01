package com.spring.test.board.controller;

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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.spring.test.board.controller.BoardController;
import com.spring.test.board.service.BoardService;
import com.spring.test.board.vo.BoardVO;
import com.spring.test.board.vo.Criteria;
import com.spring.test.board.vo.PageMaker;
import com.spring.test.board.vo.SearchCriteria;
import com.spring.test.comment.service.CommentService;
import com.spring.test.comment.vo.CommentVO;
import com.spring.test.member.vo.MemberVO;

@Controller("boardController")
public class BoardControllerImpl implements BoardController{
	private static final String ARTICLE_IMAGE_REPO = "C:\\workspace\\article_image";
	@Autowired
	private BoardService boardService;
	@Autowired
	private BoardVO boardVO;
	@Autowired
	private CommentService commentService;
	@Autowired
	private CommentVO commentVO;

	// 게시판 목록 페이징
	@RequestMapping(value = "/board/*Board.do", method = { RequestMethod.GET, RequestMethod.POST })
	public Model list(@RequestParam("board_code") String board_code, Model model, Criteria cri) throws Exception{
		System.out.println("BoardControllerImpl : list start");

		model.addAttribute("list", boardService.list(cri, board_code));
		int page = cri.getPage();
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(boardService.listCount(board_code));

		System.out.println(cri.toString());

		model.addAttribute("pageMaker", pageMaker);
		model.addAttribute("page", page);
		model.addAttribute("search", "AllList");

		System.out.println("BoardControllerImpl : list end");
		return model;
	}

	//게시판 글쓰기
	@Override
	@RequestMapping(value = "/board/addBoard.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public ResponseEntity addNewFreeBoard(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
		throws Exception {
		System.out.println("BoardControllerImpl : addNewFreeBoard start");

		multipartRequest.setCharacterEncoding("utf-8");
		Map<String, Object> articleMap = new HashMap<String, Object>();
		Enumeration enu = multipartRequest.getParameterNames();

		while(enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			System.out.println("enu name : " + name);
			String value = multipartRequest.getParameter(name);
			System.out.println("value : " + value);
			articleMap.put(name, value);
		}

		String board_image = load(multipartRequest);
		HttpSession session = multipartRequest.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("member");
		String member_name = memberVO.getMember_name();
		int member_NO = memberVO.getMember_NO();

		articleMap.put("board_name", member_name);
		articleMap.put("member_NO", member_NO);
		articleMap.put("board_image", board_image);
		
		System.out.println("imageFileName : " + board_image);
		String board_level = (String) articleMap.get("board_level");

		if(board_level == null) { articleMap.put("board_level", "0"); }

		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html;charset=utf-8");

		try {
			System.out.println("BoardControllerImpl : addNewFreeBoard try");

			int board_NO = boardService.addNewBoard(articleMap);
			System.out.println("board_NO : " + board_NO);

			if(board_image != null && board_image.length() != 0) {
				File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + board_image);
				File destDir = new File(ARTICLE_IMAGE_REPO + "\\" + board_NO);
				FileUtils.moveFileToDirectory(srcFile, destDir, true);
			}

			message = "<script>";
			message += " alert('새글을 추가했습니다.');";
			message += " location.href='" + multipartRequest.getContextPath() + "/board/freeBoard.do?board_code=1';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			System.out.println("articleMap : " + articleMap);

		} catch(Exception e) {
			System.out.println("BoardControllerImpl : addNewFreeBoard catch");
			
			File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + board_image);
			srcFile.delete();

			message = " <script>";
			message += " alert('오류가 발생했습니다. 다시 시도해 주세요');";
			message += " location.href='" + multipartRequest.getContextPath() + "/board/freeBoardWriting.do'; ";
			message += "</script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			System.out.println("articleMap : " + articleMap);
			e.printStackTrace();
			
		}

		System.out.println("BoardControllerImpl : addNewFreeBoard end");
		return resEnt;
	}

	// 게시판 QnA 글쓰기 -글 추가(에러)후 주소값 달라서 따로 구분함.
	@RequestMapping(value = "/board/addQnABoard.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public ResponseEntity addNewQnABoard(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
		throws Exception {
		System.out.println("BoardControllerImpl : addNewQnABoard start");

		multipartRequest.setCharacterEncoding("utf-8");
		Map<String, Object> articleMap = new HashMap<String, Object>();
		Enumeration enu = multipartRequest.getParameterNames();

		while(enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			System.out.println("enu : " + name);
			String value = multipartRequest.getParameter(name);
			System.out.println("value : " + value);
			articleMap.put(name, value);
		}

		String board_image = load(multipartRequest);
		HttpSession session = multipartRequest.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("member");
		String member_name = memberVO.getMember_name();
		int member_NO = memberVO.getMember_NO();

		articleMap.put("board_name", member_name);
		articleMap.put("member_NO", member_NO);
		articleMap.put("board_image", board_image);

		System.out.println("imageFileName : " + board_image);
		String board_level = (String) articleMap.get("board_level");

		if(board_level == null) {
			articleMap.put("board_level", "0");
		}

		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html;charset=utf-8");

		try {
			System.out.println("BoardControllerImpl : addNewQnABoard try");

			int board_NO = boardService.addNewBoard(articleMap);
			System.out.println("board_NO : " + board_NO);

			if(board_image != null && board_image.length() != 0) {
				File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + board_image);
				File destDir = new File(ARTICLE_IMAGE_REPO + "\\" + board_NO);
				FileUtils.moveFileToDirectory(srcFile, destDir, true);
			}

			message = "<script>";
			message += " alert('새글을 추가했습니다.');";
			message += " location.href='" + multipartRequest.getContextPath() + "/board/qnaBoard.do?board_code=2';";
			message += " </script>";
			resEnt = new ResponseEntity(message,responseHeaders,HttpStatus.CREATED);
			System.out.println("articleMap : " + articleMap);

		} catch(Exception e) {			
			System.out.println("BoardControllerImpl : addNewQnABoard catch");

			File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + board_image);
			srcFile.delete();

			message = " <script>";
			message += " alert('오류가 발생했습니다. 다시 시도해 주세요');";
			message += " location.href='" + multipartRequest.getContextPath() + "/board/qnaBoardWriting.do'; ";
			message += "</script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			System.out.println("articleMap : " + articleMap);
			e.printStackTrace();

		}

		System.out.println("BoardControllerImpl : addNewQnABoard end");
		return resEnt;
	}

	// 게시판 공지사항 글쓰기 -글 추가(에러)후 주소값 달라서 따로 구분함.
	@RequestMapping(value = "/board/addNoticeBoard.do", method= { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public ResponseEntity addNewNoticeBoard(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
		throws Exception {
		System.out.println("BoardControllerImpl : addNewNoticeBoard start");

		multipartRequest.setCharacterEncoding("utf-8");
		Map<String, Object> articleMap = new HashMap<String, Object>();
		Enumeration enu = multipartRequest.getParameterNames();

		while(enu.hasMoreElements()) {
			String name = (String)enu.nextElement();
			System.out.println("enu name : " + name);
			String value = multipartRequest.getParameter(name);
			System.out.println("value : " + value);
			articleMap.put(name, value);
		}

		String board_image = load(multipartRequest);
		HttpSession session= multipartRequest.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("member");
		String member_name = memberVO.getMember_name();
		int member_NO = memberVO.getMember_NO();

		articleMap.put("board_name", member_name);
		articleMap.put("member_NO", member_NO);
		articleMap.put("board_image", board_image);

		System.out.println("imageFileName : " + board_image);
		String board_level = (String) articleMap.get("board_level");

		if(board_level == null) {
			articleMap.put("board_level", "0");
		}

		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html;charset=utf-8");

		try {
			System.out.println("BoardControllerImpl : addNewNoticeBoard try");

			int board_NO = boardService.addNewBoard(articleMap);
			System.out.println("board_NO : " + board_NO);
			
			if(board_image != null && board_image.length() != 0) {
				File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + board_image);
				File destDir = new File(ARTICLE_IMAGE_REPO + "\\" + board_NO);
				FileUtils.moveFileToDirectory(srcFile, destDir, true);
			}
			
			message = "<script>";
			message += " alert('새글을 추가했습니다.');";
			message += " location.href='" + multipartRequest.getContextPath() + "/board/noticeBoard.do?board_code=3';";
			message += " </script>";
			resEnt = new ResponseEntity(message,responseHeaders,HttpStatus.CREATED);
			System.out.println("articleMap : " + articleMap);
			
		} catch(Exception e) {
			System.out.println("BoardControllerImpl : addNewNoticeBoard catch");

			File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + board_image);
			srcFile.delete();

			message = " <script>";
			message += " alert('오류가 발생했습니다. 다시 시도해 주세요');";
			message += " location.href='" + multipartRequest.getContextPath() + "/board/noticeBoardWriting.do'; ";
			message += "</script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			
			System.out.println("articleMap : " + articleMap);
			e.printStackTrace();

		}

		System.out.println("BoardControllerImpl : addNewNoticeBoard end");
		return resEnt;
	}

	private String load(MultipartHttpServletRequest multipartRequest) throws Exception {
		System.out.println("BoardControllerImpl : load start");
		
		String board_image = null;
		Map<String, String> articleMap = new HashMap<String, String>();
		Iterator<String> fileNames = multipartRequest.getFileNames();
		
		while(fileNames.hasNext()) {
			String fileName = fileNames.next();
			MultipartFile mFile = multipartRequest.getFile(fileName);
			board_image = mFile.getOriginalFilename();
			File file = new File(ARTICLE_IMAGE_REPO+"\\"+fileName);
			
			if(mFile.getSize()!=0) { //File Null Check
				if(! file.exists()) {
					if(file.getParentFile().mkdirs()) {
						file.createNewFile();

					}
				}
				mFile.transferTo(new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + board_image));
			}
		}
		
		System.out.println("BoardControllerImpl : load end");
		return board_image;
	}

	@RequestMapping(value="board/*Content.do" ,method = RequestMethod.GET)
	public ModelAndView viewBoard(@RequestParam("board_NO") int board_NO,
		HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("BoardControllerImpl : viewBoard start");
		
		String viewName = (String)request.getAttribute("viewName");
		List commentList = commentService.listArticles(board_NO);
		
		boardVO = boardService.viewBoard(board_NO);
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		mav.addObject("board", boardVO);
		mav.addObject("commentList", commentList);
		
		String name = boardVO.getBoard_name();
		String title = boardVO.getBoard_title();
		int comment_NO = commentVO.getComment_NO();
		
		System.out.println("comment_NO : " + comment_NO);
		System.out.println("name : " + name);
		System.out.println("title : " + title);
		System.out.println("board_score : " + boardVO.getBoard_score());

		System.out.println("BoardControllerImpl : viewBoard end");
		return mav;
	}

	@Override
	@RequestMapping(value="/board/removeBoard.do" ,method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity  removeBoard(@RequestParam("board_NO") int board_NO,
		HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("BoardControllerImpl : removeBoard start");
		
		response.setContentType("text/html; charset=UTF-8");
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		try {
			System.out.println("BoardControllerImpl : removeBoard try");
			
			boardService.removeBoard(board_NO);
			File destDir = new File(ARTICLE_IMAGE_REPO+"\\"+board_NO);
			FileUtils.deleteDirectory(destDir);

			message = "<script>";
			message += " alert('글을 삭제했습니다.');";
			message += " location.href='" + request.getContextPath() + "/board/freeBoard.do?board_code=1';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);

		} catch(Exception e) {
			System.out.println("BoardControllerImpl : removeBoard catch");
			
			message = "<script>";
			message += " alert('작업중 오류가 발생했습니다.다시 시도해 주세요.');";
			message += " location.href='" + request.getContextPath() + "/board/freeBoard.do?board_code=1';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}

		System.out.println("BoardControllerImpl : removeBoard end");
		return resEnt;
	}
	
	@RequestMapping(value="/board/modBoard.do", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity modBoard(
		MultipartHttpServletRequest multipartRequest,  
		HttpServletResponse response) throws Exception{
		System.out.println("BoardControllerImpl : modBoard start");
		
		multipartRequest.setCharacterEncoding("utf-8");
		Map<String,Object> articleMap = new HashMap<String, Object>();
		Enumeration enu = multipartRequest.getParameterNames();
		
		while(enu.hasMoreElements()){
			String name = (String) enu.nextElement();
			System.out.println("name : " + name);
			String value = multipartRequest.getParameter(name);
			System.out.println("value : " + value);
			articleMap.put(name, value);
		}

		String board_image = load(multipartRequest);
		HttpSession session = multipartRequest.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("member");
		String member_id = memberVO.getMember_id();
		
		articleMap.put("member_id", member_id);
		articleMap.put("board_image", board_image);
		System.out.println("articleMAP : " + articleMap);
		
		String board_NO = (String) articleMap.get("board_NO");
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		try {
			System.out.println("BoardControllerImpl : modBoard try");
			
			boardService.modBoard(articleMap);
			
			if(board_image != null && board_image.length() != 0) {
				File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + board_image);
				File destDir = new File(ARTICLE_IMAGE_REPO + "\\" + board_NO);
				FileUtils.moveFileToDirectory(srcFile, destDir, true);

				String originalFileName = (String)articleMap.get("originalFileName");
				File oldFile = new File(ARTICLE_IMAGE_REPO + "\\" + board_NO + "\\" + originalFileName);
				oldFile.delete();
			}	
			message = "<script>";
			message += "  alert('글을 수정했습니다.');";
			message += " location.href='" + multipartRequest.getContextPath() + "/board/freeContent.do?board_NO=" + board_NO + "';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		}catch(Exception e) {
			File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + board_image);
			srcFile.delete();
			message = "<script>";
			message += " alert('작업중 오류가 발생했습니다.다시 시도해 주세요.');";
			message += " location.href='" + multipartRequest.getContextPath() + "/board/freeContent.do?board_NO=" + board_NO + "';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		}

		System.out.println("BoardControllerImpl : modBoard end");
		return resEnt;
	}
	
	@RequestMapping(value = "/board/modQnABoard.do", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity modQnABoard(
		MultipartHttpServletRequest multipartRequest,  
		HttpServletResponse response) throws Exception{
		System.out.println("BoardControllerImpl : modQnABoard start");
		
		multipartRequest.setCharacterEncoding("utf-8");
		Map<String,Object> articleMap = new HashMap<String, Object>();
		Enumeration enu = multipartRequest.getParameterNames();
		
		while(enu.hasMoreElements()){
			String name=(String)enu.nextElement();
			System.out.println("name : "+name);
			String value=multipartRequest.getParameter(name);
			System.out.println("value : "+value);
			articleMap.put(name,value);
		}

		String board_image = load(multipartRequest);
		HttpSession session = multipartRequest.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("member");
		String member_id = memberVO.getMember_id();
		
		articleMap.put("member_id", member_id);
		articleMap.put("board_image", board_image);
		
		System.out.println("articleMAP : " + articleMap);
		String board_NO = (String) articleMap.get("board_NO");
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		try {
			System.out.println("BoardControllerImpl : modQnABoard try");
			
			boardService.modBoard(articleMap);
			
			if(board_image != null && board_image.length() != 0) {
				File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + board_image);
				File destDir = new File(ARTICLE_IMAGE_REPO + "\\" + board_NO);
				FileUtils.moveFileToDirectory(srcFile, destDir, true);

				String originalFileName = (String)articleMap.get("originalFileName");
				File oldFile = new File(ARTICLE_IMAGE_REPO + "\\" + board_NO + "\\" + originalFileName);
				oldFile.delete();
			}	
			
			message = "<script>";
			message += "  alert('글을 수정했습니다.');";
			message += " location.href='"+multipartRequest.getContextPath()+"/board/freeContent.do?board_NO="+board_NO+"';";
			message +=" </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			
		}catch(Exception e) {
			
			System.out.println("BoardControllerImpl : modQnABoard catch");
			
			File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + board_image);
			
			srcFile.delete();
			
			message = "<script>";
			message += " alert('작업중 오류가 발생했습니다.다시 시도해 주세요.');";
			message += " location.href='" + multipartRequest.getContextPath() + "/board/freeContent.do?board_NO=" + board_NO + "';";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		}

		System.out.println("BoardControllerImpl : modQnABoard end");
		return resEnt;
	}
	
	@RequestMapping(value="/board/search*.do", method= {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Model searchList(@RequestParam("search") String search,
		@RequestParam("board_code") String board_code,
		Model model, SearchCriteria cri,Criteria cr, HttpServletRequest request) throws Exception {
		System.out.println("BoardControllerImpl : searchList start");

		if (search == null || search == "") {
			model = list(board_code, model, cr);
		} else {
			Map<String,Object> searchMap = new HashMap<String, Object>();
			Enumeration enu=request.getParameterNames();
			
			while(enu.hasMoreElements()){
				String name = (String)enu.nextElement();
				System.out.println("name : " + name);
				String value=request.getParameter(name);
				System.out.println("value : " + value);
				searchMap.put(name,value);
			}
			
			System.out.println(cri.toString());
			cri.setBoard_code(board_code);
			
			//searchMap.put("board_code", board_code);
			searchMap.put("search", search);
			searchMap.put("cri", cri);
			
			System.out.println("searchMap : " + searchMap);		
			model.addAttribute("list", boardService.listsearch(searchMap,cri));

			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(cri);
			pageMaker.setTotalCount(boardService.searchCount(searchMap));
			int page = cri.getPage();
			
			System.out.println("controller Search : " + cri.getSearch());
			System.out.println("controller OptionContent : " + cri.getOptionContent());
			
			model.addAttribute("pageMaker", pageMaker);
			model.addAttribute("page",page);
			model.addAttribute("search", search);
			model.addAttribute("optionContent", searchMap.get("optionContent"));
		}

		System.out.println("BoardControllerImpl : searchList end");
		return model;
	}

}
