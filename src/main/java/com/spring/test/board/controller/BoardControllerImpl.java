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
import com.spring.test.member.vo.MemberVO;
@Controller("boardController")
public class BoardControllerImpl implements BoardController{
	private static final String ARTICLE_IMAGE_REPO = "C:\\workspace\\article_image";
	@Autowired
	private BoardService boardService;
	@Autowired
	private BoardVO boardVO;
	
	@Override
	@RequestMapping(value="/board/*Board.do", method= {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView listBoard(@RequestParam("board_code") String board_code,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("listBoardStart");
		String viewName = (String)request.getAttribute("viewName");
		System.out.println("board_code : "+board_code);
		List articlesList = boardService.listArticles(board_code);
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("articlesList",articlesList);
		
		return mav;
	}
	
	@Override
	@RequestMapping(value="/board/addBoard.do", method= {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public ResponseEntity addNewArticle(
			MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
			throws Exception {
		
		System.out.println("addNewArticle start");
		multipartRequest.setCharacterEncoding("utf-8");
		Map<String, Object> articleMap = new HashMap<String, Object>();
		Enumeration enu = multipartRequest.getParameterNames();
		while(enu.hasMoreElements()) {
			String name=(String)enu.nextElement();
			System.out.println("enu : "+name);
			String value=multipartRequest.getParameter(name);
			System.out.println("value : "+value);
			articleMap.put(name, value);
		}
		String board_image = load(multipartRequest);
		HttpSession session= multipartRequest.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("member");
		String member_name = memberVO.getMember_name();
		int member_NO = memberVO.getMember_NO();
		articleMap.put("board_name",member_name);
		articleMap.put("member_NO",member_NO);
		articleMap.put("board_image",board_image);
		System.out.println("imageFileName : "+board_image);
		String message;
		ResponseEntity resEnt=null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html;charset=utf-8");
		try {
			int board_NO = boardService.addNewArticle(articleMap);
			
			System.out.println("NO : "+board_NO);
			if(board_image!=null&& board_image.length()!=0) {
				File srcFile = new File(ARTICLE_IMAGE_REPO+"\\"+"temp"+"\\"+board_image);
				File destDir = new File(ARTICLE_IMAGE_REPO+"\\"+board_NO);
				FileUtils.moveFileToDirectory(srcFile, destDir, true);
			}
			message = "<script>";
			message += " alert('새글을 추가했습니다.');";
			message += " location.href='"+multipartRequest.getContextPath()+"/board/freeBoard.do?board_code=1';";
			message +=" </script>";
			resEnt = new ResponseEntity(message,responseHeaders,HttpStatus.CREATED);
			System.out.println(articleMap);
		}catch(Exception e) {
			File srcFile = new File(ARTICLE_IMAGE_REPO+"\\"+"temp"+"\\"+board_image);
			srcFile.delete();
			
			message = " <script>";
			message +=" alert('오류가 발생했습니다. 다시 시도해 주세요');";
			message +=" location.href='"+multipartRequest.getContextPath()+"/board/freeBoardWriting.do'; ";
			message +="</script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			System.out.println("articleMap : "+articleMap);
			e.printStackTrace();
		}
		
		return resEnt;

}@RequestMapping(value="/board/addQnABoard.do", method= {RequestMethod.GET,RequestMethod.POST})
@ResponseBody
public ResponseEntity addNewQnABoard(
		MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
		throws Exception {
	
	System.out.println("addNewArticle start");
	multipartRequest.setCharacterEncoding("utf-8");
	Map<String, Object> articleMap = new HashMap<String, Object>();
	Enumeration enu = multipartRequest.getParameterNames();
	while(enu.hasMoreElements()) {
		String name=(String)enu.nextElement();
		System.out.println("enu : "+name);
		String value=multipartRequest.getParameter(name);
		System.out.println("value : "+value);
		articleMap.put(name, value);
	}
	String board_image = load(multipartRequest);
	HttpSession session= multipartRequest.getSession();
	MemberVO memberVO = (MemberVO) session.getAttribute("member");
	String member_name = memberVO.getMember_name();
	int member_NO = memberVO.getMember_NO();
	articleMap.put("board_name",member_name);
	articleMap.put("member_NO",member_NO);
	articleMap.put("board_image",board_image);
	System.out.println("imageFileName : "+board_image);
	String message;
	ResponseEntity resEnt=null;
	HttpHeaders responseHeaders = new HttpHeaders();
	responseHeaders.add("Content-Type", "text/html;charset=utf-8");
	try {
		int board_NO = boardService.addNewArticle(articleMap);
		
		System.out.println("NO : "+board_NO);
		if(board_image!=null&& board_image.length()!=0) {
			File srcFile = new File(ARTICLE_IMAGE_REPO+"\\"+"temp"+"\\"+board_image);
			File destDir = new File(ARTICLE_IMAGE_REPO+"\\"+board_NO);
			FileUtils.moveFileToDirectory(srcFile, destDir, true);
		}
		message = "<script>";
		message += " alert('새글을 추가했습니다.');";
		message += " location.href='"+multipartRequest.getContextPath()+"/board/qnaBoard.do?board_code=2';";
		message +=" </script>";
		resEnt = new ResponseEntity(message,responseHeaders,HttpStatus.CREATED);
		System.out.println(articleMap);
	}catch(Exception e) {
		File srcFile = new File(ARTICLE_IMAGE_REPO+"\\"+"temp"+"\\"+board_image);
		srcFile.delete();
		
		message = " <script>";
		message +=" alert('오류가 발생했습니다. 다시 시도해 주세요');";
		message +=" location.href='"+multipartRequest.getContextPath()+"/board/qnaBoardWriting.do'; ";
		message +="</script>";
		resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		System.out.println("articleMap : "+articleMap);
		e.printStackTrace();
	}
	
	return resEnt;

}

	private String load(MultipartHttpServletRequest multipartRequest) throws Exception {
		String board_image = null;
		Map<String,String>articleMap = new HashMap<String, String>();
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
				mFile.transferTo(new File(ARTICLE_IMAGE_REPO+"\\"+"temp"+"\\"+board_image));
			}
		}
		return board_image;
	}
	
	@RequestMapping(value="board/*Content.do" ,method = RequestMethod.GET)
	public ModelAndView viewArticle(@RequestParam("board_NO") int board_NO,
                                    HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("viewArticleSTART");
		String viewName = (String)request.getAttribute("viewName");
		boardVO=boardService.viewArticle(board_NO);
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		mav.addObject("board", boardVO);
		String name = boardVO.getBoard_name();
		String title = boardVO.getBoard_title();
		System.out.println("name : "+name);
		System.out.println("title : "+title);
		return mav;
	}
	
	@Override
	  @RequestMapping(value="/board/removeArticle.do" ,method = RequestMethod.POST)
	  @ResponseBody
	  public ResponseEntity  removeArticle(@RequestParam("board_NO") int board_NO,
	                              HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setContentType("text/html; charset=UTF-8");
		String message;
		ResponseEntity resEnt=null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		try {
			boardService.removeArticle(board_NO);
			File destDir = new File(ARTICLE_IMAGE_REPO+"\\"+board_NO);
			FileUtils.deleteDirectory(destDir);
			
			message = "<script>";
			message += " alert('글을 삭제했습니다.');";
			message += " location.href='"+request.getContextPath()+"/board/freeBoard.do?board_code=1';";
			message +=" </script>";
		    resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		       
		}catch(Exception e) {
			message = "<script>";
			message += " alert('작업중 오류가 발생했습니다.다시 시도해 주세요.');";
			message += " location.href='"+request.getContextPath()+"/board/freeBoard.do?board_code=1';";
			message +=" </script>";
		    resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		    e.printStackTrace();
		}
		return resEnt;
	  } 
	 @RequestMapping(value="/board/modArticle.do" ,method = RequestMethod.POST)
	  @ResponseBody
	  public ResponseEntity modArticle(
			  MultipartHttpServletRequest multipartRequest,  
	    HttpServletResponse response) throws Exception{
	    multipartRequest.setCharacterEncoding("utf-8");
		Map<String,Object> articleMap = new HashMap<String, Object>();
		Enumeration enu=multipartRequest.getParameterNames();
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
		System.out.println("articleMAP : "+articleMap);
		String board_NO=(String)articleMap.get("board_NO");
		String message;
		ResponseEntity resEnt=null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
	    try {
	       boardService.modArticle(articleMap);
	       if(board_image!=null && board_image.length()!=0) {
	         File srcFile = new File(ARTICLE_IMAGE_REPO+"\\"+"temp"+"\\"+board_image);
	         File destDir = new File(ARTICLE_IMAGE_REPO+"\\"+board_NO);
	         FileUtils.moveFileToDirectory(srcFile, destDir, true);
	         
	         String originalFileName = (String)articleMap.get("originalFileName");
	         File oldFile = new File(ARTICLE_IMAGE_REPO+"\\"+board_NO+"\\"+originalFileName);
	         oldFile.delete();
	       }	
	       message = "<script>";
		   message += "  alert('글을 수정했습니다.');";
		   message += " location.href='"+multipartRequest.getContextPath()+"/board/freeContent.do?board_NO="+board_NO+"';";
		   message +=" </script>";
	       resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
	    }catch(Exception e) {
	      File srcFile = new File(ARTICLE_IMAGE_REPO+"\\"+"temp"+"\\"+board_image);
	      srcFile.delete();
	      message = "<script>";
		  message += " alert('작업중 오류가 발생했습니다.다시 시도해 주세요.');";
		  message += " location.href='"+multipartRequest.getContextPath()+"/board/freeContent.do?board_NO="+board_NO+"';";
		  message +=" </script>";
	      resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
	    }
	    return resEnt;
	  }
	 @RequestMapping(value="/board/modQnABoard.do" ,method = RequestMethod.POST)
	  @ResponseBody
	  public ResponseEntity modQnABoard(
			  MultipartHttpServletRequest multipartRequest,  
	    HttpServletResponse response) throws Exception{
	    multipartRequest.setCharacterEncoding("utf-8");
		Map<String,Object> articleMap = new HashMap<String, Object>();
		Enumeration enu=multipartRequest.getParameterNames();
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
		System.out.println("articleMAP : "+articleMap);
		String board_NO=(String)articleMap.get("board_NO");
		String message;
		ResponseEntity resEnt=null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
	    try {
	       boardService.modArticle(articleMap);
	       if(board_image!=null && board_image.length()!=0) {
	         File srcFile = new File(ARTICLE_IMAGE_REPO+"\\"+"temp"+"\\"+board_image);
	         File destDir = new File(ARTICLE_IMAGE_REPO+"\\"+board_NO);
	         FileUtils.moveFileToDirectory(srcFile, destDir, true);
	         
	         String originalFileName = (String)articleMap.get("originalFileName");
	         File oldFile = new File(ARTICLE_IMAGE_REPO+"\\"+board_NO+"\\"+originalFileName);
	         oldFile.delete();
	       }	
	       message = "<script>";
		   message += "  alert('글을 수정했습니다.');";
		   message += " location.href='"+multipartRequest.getContextPath()+"/board/freeContent.do?board_NO="+board_NO+"';";
		   message +=" </script>";
	       resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
	    }catch(Exception e) {
	      File srcFile = new File(ARTICLE_IMAGE_REPO+"\\"+"temp"+"\\"+board_image);
	      srcFile.delete();
	      message = "<script>";
		  message += " alert('작업중 오류가 발생했습니다.다시 시도해 주세요.');";
		  message += " location.href='"+multipartRequest.getContextPath()+"/board/freeContent.do?board_NO="+board_NO+"';";
		  message +=" </script>";
	      resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
	    }
	    return resEnt;
	  }
	  
}
