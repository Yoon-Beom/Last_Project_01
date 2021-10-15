package com.spring.test.shop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spring.test.kakao.KakaoAddr;
import com.spring.test.kakao.KakaoGeoRes;
import com.spring.test.member.service.MemberService;
import com.spring.test.member.vo.MemberVO;
import com.spring.test.review.service.ReviewService;
import com.spring.test.shop.dao.ShopDAO;
import com.spring.test.shop.service.ShopService;
import com.spring.test.shop.vo.ShopDetailVO;
import com.spring.test.shop.vo.ShopVO;

@Controller("shopController")
public class ShopControllerImpl implements ShopController{

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
	@RequestMapping(value = "map.do", method = RequestMethod.GET)
	public ModelAndView listShop(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ShopControllerImpl : listShop start");
		
		String viewName = (String)request.getAttribute("viewName");
		List shopList = shopService.listShop();
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("shopList", shopList);
		System.out.println("리턴 값 : " + mav);
		HttpSession session = request.getSession();
		session.setAttribute("shopList", shopList);
		String a = shopList.get(0).toString();
		System.out.println("ShopControllerImpl : listShop end");
		return mav;
	}
	
	@Override
	@RequestMapping(value="/shop/shopMyPage.do", method = RequestMethod.GET)
	public ModelAndView listShopAndDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ShopControllerImpl : listShopAndDetail start");
		
		HttpSession session = request.getSession();
		memberVO = (MemberVO) session.getAttribute("member");
		int member_NO = memberVO.getMember_NO();
		System.out.println("/shopMyPage : memberNO = " + member_NO);
		Map<String, Object> shopMap = new HashMap<String, Object>();
		shopMap = (Map<String, Object>) shopService.listShop(member_NO);
		Object shop_NO = shopMap.get("SHOP_NO");
		System.out.println("shopController : "+shop_NO);
		String viewName = (String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("member", memberVO);
		mav.addObject("reviewList", reviewService.ShopReview(shop_NO));
		mav.addObject("shop", shopMap);
		System.out.println("shopComtroller : "+shopMap);
		
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
}
