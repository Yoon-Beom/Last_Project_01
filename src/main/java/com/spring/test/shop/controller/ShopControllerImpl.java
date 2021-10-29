package com.spring.test.shop.controller;

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
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.spring.test.kakao.KakaoAddr;
import com.spring.test.kakao.KakaoGeoRes;
import com.spring.test.member.service.MemberService;
import com.spring.test.member.vo.MemberVO;
import com.spring.test.reserve.vo.ReserveVO;
import com.spring.test.review.service.ReviewService;
import com.spring.test.shop.dao.ShopDAO;
import com.spring.test.shop.service.ShopService;
import com.spring.test.shop.vo.ShopDetailVO;
import com.spring.test.shop.vo.ShopVO;

@Controller("shopController")
public class ShopControllerImpl implements ShopController{
	private static final String ARTICLE_IMAGE_SHOP_Main = "C:\\workspace\\shop_imageMain";
	@Autowired
	private MemberService memberService;
	@Autowired
	private ShopService shopService;
	@Autowired
	MemberVO memberVO;
	@Autowired
	ShopVO shopVO;
	@Autowired
	ShopDetailVO shopDetailVO;
	@Autowired
	ReviewService reviewService;
	
	@Override
	@RequestMapping(value = "/shop/shopMap.do", method = RequestMethod.GET)
	public ModelAndView listShop(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ShopControllerImpl : listShop start");
		
		String viewName = (String)request.getAttribute("viewName"); 
		List shopList =	shopService.listShop(); 
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("shopList", shopList);
		HttpSession session = request.getSession();
		
		memberVO = (MemberVO) session.getAttribute("member");
		if(memberVO == null) {
			
		} else if(memberVO.getMember_code().equals("2")) {
			mav.addObject("memberCode", "2");
		}
		
		session.setAttribute("shopList", shopList);
		

		System.out.println("리턴 값 : " + mav);
		System.out.println("ShopControllerImpl : listShop end"); 
		return mav; 
	}
	
	@Override
	@RequestMapping(value="/shop/shopDetail.do", method= RequestMethod.GET) 
	public ModelAndView listShopView(@RequestParam("shop_NO") int shop_NO,HttpServletRequest request, HttpServletResponse response) throws
	Exception {
		System.out.println("ShopControllerImpl : listShopAndDetail start"); 
		String viewName = (String)request.getAttribute("viewName");
		
		shopVO = shopService.ViewShop(shop_NO);
		String phone = shopVO.getShopDetailVO().getShop_phone();
		String openTime = shopVO.getShopDetailVO().getShop_open_time();
		String closeTime = shopVO.getShopDetailVO().getShop_close_time();
		String introduce =  shopVO.getShopDetailVO().getShop_introduce();
		int starScore =  shopVO.getShopDetailVO().getShop_starScore();
		int heartScore =  shopVO.getShopDetailVO().getShop_heartScore();
		int reserveScore =  shopVO.getShopDetailVO().getShop_reserveScore();
		
		shopVO.getShopDetailVO().setShop_phone(phone);
		shopVO.getShopDetailVO().setShop_open_time(openTime);
		shopVO.getShopDetailVO().setShop_close_time(closeTime);
		shopVO.getShopDetailVO().setShop_introduce(introduce);
		shopVO.getShopDetailVO().setShop_introduce(introduce);
		shopVO.getShopDetailVO().setShop_starScore(starScore);
		shopVO.getShopDetailVO().setShop_heartScore(heartScore);
		shopVO.getShopDetailVO().setShop_reserveScore(reserveScore);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName); 
		mav.addObject("shop", shopVO); 

		return mav; 
	}
	 

	 @Override
	 @RequestMapping(value={"/shop/shop*.do","/shop/shopMod.do"}, method = RequestMethod.GET)
	 public ModelAndView listShopAndDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 System.out.println("ShopControllerImpl : listShopAndDetail start");

		 HttpSession session = request.getSession();
		 memberVO = (MemberVO) session.getAttribute("member");
		 int member_NO = memberVO.getMember_NO();
		 System.out.println("/shopMyPage : memberNO = " + member_NO);
		 
		 ShopVO shopVO = shopService.listShop(member_NO);
		 Object shop_NO = shopVO.getShop_NO();
		 String viewName = (String)request.getAttribute("viewName");
		 ModelAndView mav = new ModelAndView(viewName);
		 mav.addObject("shop", shopVO);
		 mav.addObject("reviewList", reviewService.ShopReview(shop_NO));
		 System.out.println("shopComtroller : " + shopVO);

		 System.out.println("ShopControllerImpl : listShopAndDetail end");
		 return mav;
	 }

	
	
	@Override
	@RequestMapping(value="/shop/addShop.do", method = RequestMethod.POST)
	public ModelAndView addShop(@ModelAttribute("member") MemberVO member, @ModelAttribute("shop") ShopVO shop,
			 @ModelAttribute("shopDetail") ShopDetailVO shopDetail,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ShopControllerImpl : addShop start");

		request.setCharacterEncoding("euc-kr");
		// response.setCharacterEncoding("text/html; charset=utf-8");

		int member_result = 0;
		int shop_result = 0;
		int shopDetail_result = 0;

		String yy = request.getParameter("member_yy");
		String mm = request.getParameter("member_mm");
		String dd = request.getParameter("member_dd");

		String member_phone1 = request.getParameter("member_phone1");
		String member_phone2 = request.getParameter("member_phone2");
		String member_phone3 = request.getParameter("member_phone3");

		String member_post = request.getParameter("member_post");
		String member_addr = request.getParameter("member_addr");
		String member_detailAddr = request.getParameter("member_detailAddr");

		member.setMember_birth(yy + "-" + mm + "-" + dd);
		member.setMember_phone(member_phone1 + "-" + member_phone2 + "-" + member_phone3);
		member.setMember_address(member_post + "," + member_addr + "," + member_detailAddr);
		member.setMember_code("2");
		System.out.println("---------- member VO ----------");
		System.out.println("member_id : " + member.getMember_id());
		System.out.println("member_pwd : " + member.getMember_pwd());
		System.out.println("member_salt : " + member.getMember_salt());
		System.out.println("member_name : " + member.getMember_name());
		System.out.println("member_birth : " + member.getMember_birth());
		System.out.println("member_phone : " + member.getMember_phone());
		System.out.println("member_email : " + member.getMember_email());
		System.out.println("member_gender : " + member.getMember_gender());
		System.out.println("member_address : " + member.getMember_address());
		System.out.println("-------------------------------");


		member_result = memberService.addMember(member);
		int member_NO = memberService.selectMemberNoById(member.getMember_id());
		
		String shop_post = request.getParameter("shop_post");
		String shop_addr = request.getParameter("shop_addr");
		String shop_detailAddr = request.getParameter("shop_detailAddr");
		
		KakaoAddr kAddr = new KakaoAddr();
		
		KakaoGeoRes bodyJson = kAddr.getPoint(shop_addr);

		double latitude = bodyJson.getDocuments().get(0).getY();
		double longitude = bodyJson.getDocuments().get(0).getX();
		shop.setMember_NO(member_NO);
		shop.setShop_address(shop_post + "," + shop_addr + "," + shop_detailAddr);
		shop.setShop_latitude(latitude);
		shop.setShop_longitude(longitude);
		
		System.out.println("---------- member VO ----------");
		System.out.println("shop_NO : " + shop.getShop_NO());
		System.out.println("member_NO : " + shop.getMember_NO());
		System.out.println("shop_name : " + shop.getShop_name());
		System.out.println("shop_address : " + shop.getShop_address());
		System.out.println("shop_code : " + shop.getShop_code());
		System.out.println("shop_latitude : " + shop.getShop_latitude());
		System.out.println("shop_longitude : " + shop.getShop_longitude());
		System.out.println("-------------------------------");

		shop_result = shopService.insertShop(shop);
		int shop_NO = shopService.selectShop_No(shop.getMember_NO());
		
		String shop_phone1 = request.getParameter("shop_phone1");
		String shop_phone2 = request.getParameter("shop_phone2");
		String shop_phone3 = request.getParameter("shop_phone3");
		String shop_ceo = request.getParameter("shop_ceo");
		String open = request.getParameter("open_time");
		String close = request.getParameter("close_time");
		
		shopDetail.setShop_NO(shop_NO);
		shopDetail.setShop_phone(shop_phone1 + "-" + shop_phone2 + "-" + shop_phone3);
		shopDetail.setShop_ceo(shop_ceo);
		shopDetail.setShop_open_time(open);
		shopDetail.setShop_close_time(close);
		
		shopDetail_result = shopService.insertShopDetail(shopDetail);
		ModelAndView mav = new ModelAndView("redirect:/main.do");

		System.out.println("ShopControllerImpl : addShop end");
		return mav;
	}
	
	private String upload(MultipartHttpServletRequest multipartRequest) throws Exception {
		System.out.println("ShopControllerImpl : upload start");
		String shop_imageMain = null;
		Map<String, String> articleMap = new HashMap<String, String>();
		Iterator<String> fileNames = multipartRequest.getFileNames();

		while (fileNames.hasNext()) {
			String fileName = fileNames.next();
			MultipartFile mFile = multipartRequest.getFile(fileName);
			shop_imageMain = mFile.getOriginalFilename();
			File file = new File(ARTICLE_IMAGE_SHOP_Main + "\\" + fileName);
			if (mFile.getSize() != 0) {
				if (!file.exists()) {
					if (file.getParentFile().mkdirs()) {
						file.createNewFile();
					}
				}
				mFile.transferTo(new File(ARTICLE_IMAGE_SHOP_Main + "\\" + "temp" + "\\" + shop_imageMain));
			}
		}
		System.out.println("ShopControllerImpl : upload end");
		return shop_imageMain;
	}

	@RequestMapping(value = "/shop/updateShop.do", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity updateShop(@ModelAttribute("shopVO") ShopVO shopVO,MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
			throws Exception {
		System.out.println("shopUpdate 시작");
		multipartRequest.setCharacterEncoding("utf-8");
		Map<String, Object> articleMap = new HashMap<String, Object>();
		Enumeration enu = multipartRequest.getParameterNames();
		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			System.out.println("shop_name : " + name);
			String value = multipartRequest.getParameter(name);
			articleMap.put(name, value);
			System.out.println("shop_value : " + value);
		}

		String shop_imageMain = upload(multipartRequest);
		HttpSession session = multipartRequest.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("member");	
		String member_id = memberVO.getMember_id();
		int member_NO = memberVO.getMember_NO();
		String shopDetail_NO = multipartRequest.getParameter("shopDetail_NO");
		
		String shop_ceo = multipartRequest.getParameter("shop_ceo");
		
		String shop_phone1 = multipartRequest.getParameter("shop_phone1");
		String shop_phone2 = multipartRequest.getParameter("shop_phone2");
		String shop_phone3 = multipartRequest.getParameter("shop_phone3");
		String shop_phone= shop_phone1 + "-" + shop_phone2 + "-" + shop_phone3;
		String shop_open_time = multipartRequest.getParameter("open_time");
		String shop_close_time = multipartRequest.getParameter("close_time");
		
		String shop_introduce = multipartRequest.getParameter("shop_introduce");
		articleMap.put("member_NO", member_NO);
		articleMap.put("shopDetail_NO", shopDetail_NO);
		articleMap.put("shop_imageMain", shop_imageMain);
		articleMap.put("shop_phone",shop_phone);
		articleMap.put("shop_open_time",shop_open_time);
		articleMap.put("shop_close_time",shop_close_time);
		articleMap.put("shop_introduce", shop_introduce);
		System.out.println("shop_controller : update : " + articleMap);
		shopDetail_NO = (String) articleMap.get("shopDetail_NO");	
		
		  int result = 0; 
		  int shop_NO =Integer.parseInt(multipartRequest.getParameter("shop_NO")); 
		  String shop_name = multipartRequest.getParameter("shop_name"); 
		  String shop_post = multipartRequest.getParameter("shop_post"); 
		  String shop_addr = multipartRequest.getParameter("shop_addr"); 
		  String shop_detailAddr = multipartRequest.getParameter("shop_detailAddr"); 
		  String shop_code = multipartRequest.getParameter("shop_code");
		 
		 KakaoAddr kAddr = new KakaoAddr();
		
		 KakaoGeoRes bodyJson = kAddr.getPoint(shop_addr);
		 
		 double latitude = bodyJson.getDocuments().get(0).getY(); 
		 double longitude =  bodyJson.getDocuments().get(0).getX();
		 
		 shopVO.setShop_name(shop_name); 
		 shopVO.setShop_address(shop_post + "," + shop_addr + "," + shop_detailAddr); 
		 shopVO.setShop_latitude(latitude);
		 shopVO.setShop_longitude(longitude); 
		 shopVO.setShop_code(shop_code);
		 
		 result = shopService.updateShop(shopVO);
		 
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		try {
			shopService.updateShopDetail(articleMap);
			
			if (shop_imageMain != null && shop_imageMain.length() != 0) {
				File srcFile = new File(ARTICLE_IMAGE_SHOP_Main + "\\" + "temp" + "\\" + shop_imageMain);
				File destDir = new File(ARTICLE_IMAGE_SHOP_Main + "\\" + shopDetail_NO);
				FileUtils.moveFileToDirectory(srcFile, destDir, true);

				String originalFileName = (String) articleMap.get("originalFileName");
				File oldFile = new File(ARTICLE_IMAGE_SHOP_Main + "\\" + shopDetail_NO + "\\" + originalFileName);
				oldFile.delete();
			}
			message = "<script>";
			message += " alert('매장정보를 수정했습니다.');";
			message += " location.href='" + multipartRequest.getContextPath() + "/shop/shopMyPage.do'; ";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		} catch (Exception e) {
			File srcFile = new File(ARTICLE_IMAGE_SHOP_Main + "\\" + "temp" + "\\" + shop_imageMain);
			srcFile.delete();
			message = "<script>";
			message += " alert('오류가 발생했습니다.다시 수정해주세요');";
			message += " location.href='" + multipartRequest.getContextPath() + "/shop/shopMyPage.do'; ";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		}
		return resEnt;
	}
}
