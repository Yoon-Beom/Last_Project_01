package com.spring.test.review.controller;

import java.io.File;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
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

import com.spring.test.review.vo.Criteria1;
import com.spring.test.review.vo.PageMaker;
import com.spring.test.member.vo.MemberVO;
import com.spring.test.review.dao.ReviewDAO;
import com.spring.test.review.service.ReviewService;

@Controller("reviewController")
public class ReviewControllerImpl implements ReviewController{
	private static final String ARTICLE_IMAGE_REPO = "C:\\workspace\\review_image";
	@Autowired
	ReviewDAO reviewDAO;
	@Autowired
	ReviewService reviewService;
	@Autowired
	Criteria1 cri;
	
	@RequestMapping(value="/mypage/review.do", method= {RequestMethod.GET,RequestMethod.POST})
	public Model list(Model model, Criteria1 cri, HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		System.out.println("reviewController : list start");
		HttpSession session= request.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("member");
		int member_NO = memberVO.getMember_NO();
		cri.setMember_NO(member_NO);
		System.out.println("reviewController member_NO: "+member_NO);
		model.addAttribute("list", reviewService.list(cri,member_NO));
		int page = cri.getPage();
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);

		pageMaker.setTotalCount(reviewService.listCount(member_NO));
		model.addAttribute("page",page);
		System.out.println(cri.toString());

		model.addAttribute("pageMaker", pageMaker);
		System.out.println("reviewController : list end");
		return model;
	}
	
	@RequestMapping(value="/mypage/shopReview.do", method= {RequestMethod.GET,RequestMethod.POST})
	public Model shopReviewlist(Model model, Criteria1 cri, HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		System.out.println("reviewController : list start");
		HttpSession session= request.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("member");
		int member_NO = memberVO.getMember_NO();
		cri.setMember_NO(member_NO);
		System.out.println("reviewController member_NO: "+member_NO);
		model.addAttribute("list", reviewService.shopReviewList(cri,member_NO));
		int page = cri.getPage();
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);

		pageMaker.setTotalCount(reviewService.reviewListCount(member_NO));
		model.addAttribute("page",page);
		System.out.println(cri.toString());

		model.addAttribute("pageMaker", pageMaker);
		System.out.println("reviewController : list end");
		return model;
	}
	
	@RequestMapping(value="/mypage/visit.do", method= {RequestMethod.GET,RequestMethod.POST})
	public Model visit(Model model, Criteria1 cri, HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		System.out.println("reviewController : list start");
		HttpSession session= request.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("member");
		int member_NO = memberVO.getMember_NO();
		cri.setMember_NO(member_NO);
		System.out.println("reviewController member_NO: "+member_NO);
		model.addAttribute("list", reviewService.visit(cri,member_NO));
		int page = cri.getPage();
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);

		pageMaker.setTotalCount(reviewService.visitCount(member_NO));
		model.addAttribute("page",page);
		System.out.println(cri.toString());

		model.addAttribute("pageMaker", pageMaker);
		System.out.println("reviewController : list end");
		return model;
	}
	
	@RequestMapping(value="mypage/addReview.do", method= {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public ResponseEntity reviewWrite(
			MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
					throws Exception {

		System.out.println("ReviewController : reviewWrite start");

		multipartRequest.setCharacterEncoding("utf-8");
		Map<String, Object> reviewMap = new HashMap<String, Object>();
		Enumeration enu = multipartRequest.getParameterNames();
		while(enu.hasMoreElements()) {
			String name=(String)enu.nextElement();
			System.out.println("enu : "+name);
			String value=multipartRequest.getParameter(name);
			System.out.println("value : "+value);
			reviewMap.put(name, value);
		}
		String review_image = load(multipartRequest);
		HttpSession session= multipartRequest.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("member");
		String member_name = memberVO.getMember_name();
		int member_NO = memberVO.getMember_NO();
		reviewMap.put("member_NO",member_NO);
		reviewMap.put("review_image",review_image);
		System.out.println("imageFileName : "+review_image);
		String message;
		ResponseEntity resEnt=null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html;charset=utf-8");
		try {
			int review_NO = reviewService.addNewReview(reviewMap);

			System.out.println("NO : "+review_NO);
			if(review_image!=null&& review_image.length()!=0) {
				File srcFile = new File(ARTICLE_IMAGE_REPO+"\\"+"temp"+"\\"+review_image);
				File destDir = new File(ARTICLE_IMAGE_REPO+"\\"+review_NO);
				FileUtils.moveFileToDirectory(srcFile, destDir, true);
			}
			message = "<script>";
			message += " alert('새글을 추가했습니다.');";
			message += " location.href='"+multipartRequest.getContextPath()+"/mypage/review.do';";
			message +=" </script>";
			resEnt = new ResponseEntity(message,responseHeaders,HttpStatus.CREATED);
			System.out.println(reviewMap);
		}catch(Exception e) {
			File srcFile = new File(ARTICLE_IMAGE_REPO+"\\"+"temp"+"\\"+review_image);
			srcFile.delete();

			message = " <script>";
			message +=" alert('오류가 발생했습니다. 다시 시도해 주세요');";
			message +=" location.href='"+multipartRequest.getContextPath()+"/mypage/reviewWrite.do'; ";
			message +="</script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			System.out.println("reviewMap : "+reviewMap);
			e.printStackTrace();
		}

		return resEnt;

	}

	private String load(MultipartHttpServletRequest multipartRequest) throws Exception {
		String review_image = null;
		Map<String,String>articleMap = new HashMap<String, String>();
		Iterator<String> fileNames = multipartRequest.getFileNames();
		while(fileNames.hasNext()) {
			String fileName = fileNames.next();
			MultipartFile mFile = multipartRequest.getFile(fileName);
			review_image = mFile.getOriginalFilename();
			File file = new File(ARTICLE_IMAGE_REPO+"\\"+fileName);
			if(mFile.getSize()!=0) { //File Null Check
				if(! file.exists()) {
					if(file.getParentFile().mkdirs()) {
						file.createNewFile();

					}
				}
				mFile.transferTo(new File(ARTICLE_IMAGE_REPO+"\\"+"temp"+"\\"+review_image));
			}
		}
		return review_image;
	}
	
	@Override
	@RequestMapping(value="/mypage/deleteReview.do" , method= {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public ResponseEntity  removeBoard(@RequestParam("review_NO") int review_NO,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setContentType("text/html; charset=UTF-8");
		String message;
		ResponseEntity resEnt=null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		try {
			reviewService.removeReview(review_NO);
			message = "<script>";
			message += " alert('글을 삭제했습니다.');";
			message += " location.href='"+request.getContextPath()+"/mypage/review.do';";
			message +=" </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);

		}catch(Exception e) {
			message = "<script>";
			message += " alert('작업중 오류가 발생했습니다.다시 시도해 주세요.');";
			message += " location.href='"+request.getContextPath()+"/mypage/review.do';";
			message +=" </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}
		return resEnt;
	}

	
}
