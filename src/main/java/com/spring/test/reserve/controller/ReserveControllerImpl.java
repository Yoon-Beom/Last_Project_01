package com.spring.test.reserve.controller;

import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring.test.member.vo.MemberVO;
import com.spring.test.pet.service.PetService;
import com.spring.test.pet.vo.PetVO;
import com.spring.test.reserve.service.ReserveService;
import com.spring.test.reserve.vo.ReserveVO;
import com.spring.test.shop.service.ShopService;
import com.spring.test.shop.service.ShopServiceImpl;
import com.spring.test.shop.vo.ShopVO;

@Controller("reserveController")
public class ReserveControllerImpl implements ReserveController {
	@Autowired
	private PetService petService;
	@Autowired
	private PetVO petVO;
	@Autowired
	private MemberVO memberVO;
	@Autowired
	private ShopService shopService;
	@Autowired
	private ReserveVO reserveVO;
	@Autowired
	private ReserveService reserveService;
	
	@Override
	@RequestMapping(value = "/reserve/reserve.do", method = RequestMethod.GET)
	public ModelAndView reservation(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ReserveControllerImpl : reservation start");
		
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		
		HttpSession session = request.getSession();
		memberVO = (MemberVO) session.getAttribute("member");
		
		if(memberVO == null) {
			mav.setViewName("login");
			mav.addObject("memberCode", "0");
			return mav;
		}
		
		int member_NO = memberVO.getMember_NO();
		List petList = petService.listPet(member_NO);
		String str = request.getParameter("shop_NO");
		int shop_NO = Integer.parseInt(str);

		System.out.println("str : " + str);
		System.out.println("shop_NO : " + shop_NO);
		
		// 날짜 관련 코드
		Calendar cal = Calendar.getInstance();
		
		int yy = cal.get(java.util.Calendar.YEAR);
		int mm = cal.get(java.util.Calendar.MONTH) + 1; 
		int dd = cal.get(java.util.Calendar.DATE);
		
		String startDay = Integer.toString(yy) + '-' + Integer.toString(mm) + '-' + Integer.toString(dd);
			
		if(mm > 11) {
			cal.set(cal.get(java.util.Calendar.YEAR) + 1, 0, 1);
		} else {
			cal.set(cal.get(java.util.Calendar.YEAR), mm, 1);
		}
		
		yy = cal.get(java.util.Calendar.YEAR);
		mm = cal.get(java.util.Calendar.MONTH) + 1; 
		dd = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		String endDay = Integer.toString(yy) + '-' + Integer.toString(mm) + '-' + Integer.toString(dd);

		HashMap<String, Object> dayMap = new HashMap<String, Object>();

		dayMap.put("startDay", startDay);
		dayMap.put("endDay", endDay);
		dayMap.put("shop_NO", 1);

		//date, count(timeA) 형태로 list 로 반환
		List<String> dayList = reserveService.selectDateCount(dayMap);
		
		// shop_NO로 shopVO 받아 오는 곳
		ShopVO shopVO = shopService.selectShopByShopNO(1);
		
		mav.addObject("petList", petList);
		mav.addObject("shopVO", shopVO);
		mav.addObject("dayList", dayList);
		
		
		System.out.println("ReserveControllerImpl : reservation end");
		return mav;
	}
	
	@Override
	@RequestMapping(value = "/reserve/reserveAction.do", method = RequestMethod.POST)
	public ModelAndView reserveAction(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ReserveControllerImpl : reserveAction start");
		
		ModelAndView mav = new ModelAndView("redirect:/main.do");
		
		HttpSession session = request.getSession();
		memberVO = (MemberVO) session.getAttribute("member");
		
		String shop_NO = request.getParameter("shop_NO");
		String pet_select = request.getParameter("pet_select");
		int pet_NO = 0;

		if(pet_select.equals("0")) {
			String pet_name = request.getParameter("pet_name");
			String pet_age = request.getParameter("pet_age");
			String pet_scale = request.getParameter("pet_scale");


			Map<String, Object> petMap = new HashMap<String, Object>();
			petMap.put("member_NO", memberVO.getMember_NO());
			petMap.put("pet_name", pet_name);
			petMap.put("pet_age", Integer.parseInt(pet_age));
			petMap.put("pet_scale", pet_scale);
			petMap.put("pet_image", "");
			
			pet_NO = petService.addPet(petMap);
			
			
		} else if (pet_select.equals("1")) {
			String temp = request.getParameter("pet_NO");
			if(temp != "") {
				pet_NO = Integer.parseInt(temp);
			}
		}

		String res_spot = request.getParameter("res_spot");
		String res_option = request.getParameter("res_option");
		String reserve_Date = request.getParameter("reserve_Date");
		String reserve_TimeA = request.getParameter("reserve_TimeA");
		
		Date date = Date.valueOf(reserve_Date);
		
		System.out.println("date : " + date);
		
		reserveVO.setShop_NO(Integer.parseInt(shop_NO));
		reserveVO.setMember_NO(memberVO.getMember_NO());
		reserveVO.setPet_NO(pet_NO);
		reserveVO.setReserve_Date(date);
		reserveVO.setReserve_TimeA(reserve_TimeA);
		reserveVO.setRes_spot(res_spot);
		reserveVO.setRes_option(res_option);

		System.out.println("Shop_NO : " + reserveVO.getShop_NO());
		System.out.println("Member_NO : " + reserveVO.getMember_NO());
		System.out.println("Pet_NO : " + reserveVO.getPet_NO());
		System.out.println("Reserve_Date : " + reserveVO.getReserve_Date());
		System.out.println("Reserve_TimeA : " + reserveVO.getReserve_TimeA());
		System.out.println("Res_spot : " + reserveVO.getRes_spot());
		System.out.println("Res_option : " + reserveVO.getRes_option());
		
		int reserve_NO = reserveService.insertReserve(reserveVO);
		
		System.out.println("reserve_NO : " + reserve_NO);
		
		System.out.println("ReserveControllerImpl : reserveAction end");
		return mav;
	}

	@ResponseBody
	@RequestMapping(value = "/reserve/timeList.do", method = RequestMethod.POST)
	public void selectTimeList(@RequestParam(value="shop_NO") int shop_NO,
		@RequestParam(value="reserve_Date") String reserve_Date,
		HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ReserveControllerImpl : selectTimeList start");

		System.out.println("shop_NO : " + shop_NO);
		System.out.println("reserve_Date : " + reserve_Date);
		
		HashMap<String, Object> timeMap = new HashMap<String, Object>();

		timeMap.put("shop_NO", shop_NO);
		timeMap.put("reserve_Date", reserve_Date);
		
		List<String> timeList = reserveService.selectTimeByDate(timeMap);
		
		PrintWriter out = response.getWriter();
		for(int i = 0; i < timeList.size(); i++) {
			out.print(timeList.get(i) + ',');
		}
		System.out.println(timeList);
		System.out.println("ReserveControllerImpl : selectTimeList end");
		
	}
}
