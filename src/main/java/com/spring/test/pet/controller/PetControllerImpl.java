package com.spring.test.pet.controller;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.spring.test.member.vo.MemberVO;
import com.spring.test.pet.service.PetService;
import com.spring.test.pet.vo.PetVO;
import com.spring.test.review.service.ReviewService;

@Controller("petController")
public class PetControllerImpl implements PetController {
	private static final String ARTICLE_IMAGE_PET = "C:\\workspace\\pet_image";

	@Autowired
	private PetService petService;
	@Autowired
	private PetVO petVO;
	@Autowired
	private MemberVO memberVO;
	@Autowired
	private ReviewService reviewService;

	@Override
	@RequestMapping(value = "/mypage/myPage.do", method= {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView listPet(HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		System.out.println("PetControllerImpl : listPet start");
		
		HttpSession session = request.getSession();
		memberVO = (MemberVO) session.getAttribute("member");
		int member_NO = memberVO.getMember_NO();
		System.out.println("listPet member_NO : "+member_NO);
		String viewName = (String) request.getAttribute("viewName");
		
		List petList = petService.listPet(member_NO);
		ModelAndView mav = new ModelAndView(viewName);
		
		mav.addObject("reviewList", reviewService.myPageReview(member_NO));
		mav.addObject("reserveList", reviewService.myPageReserve(member_NO));
		mav.addObject("member", memberVO);
		mav.addObject("petList", petList);
	
		System.out.println("PetControllerImpl : listPet end");
		return mav;
	}

	@Override
	@RequestMapping(value = "/mypage/petAdd.do", method = RequestMethod.POST)
	public ResponseEntity addPet(@ModelAttribute("member") MemberVO member,
		MultipartHttpServletRequest multipartRequest, HttpServletResponse response) throws Exception {
		System.out.println("PetControllerImpl : addPet start");
		
		multipartRequest.setCharacterEncoding("utf-8");
		Map<String, Object> articleMap = new HashMap<String, Object>();
		Enumeration enu = multipartRequest.getParameterNames();
		
		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			System.out.println("enu : " + name);
			String value = multipartRequest.getParameter(name);
			System.out.println("value : " + value);
			articleMap.put(name, value);
		}
		
		String pet_image = upload(multipartRequest);
		HttpSession session = multipartRequest.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("member");
		String member_id = memberVO.getMember_id();
		int member_NO = memberVO.getMember_NO();
		String member_name = memberVO.getMember_name();
		int pet_NO = petVO.getPet_NO();
		
		articleMap.put("member_name",member_name);
		articleMap.put("member_NO",member_NO);
		articleMap.put("member_id", member_id);
		articleMap.put("pet_image", pet_image);

		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		try {
			pet_NO = petService.addPet(articleMap);

			System.out.println("NO : " + pet_NO);
			
			if (pet_image != null && pet_image.length() != 0) {
				File srcFile = new File(ARTICLE_IMAGE_PET + "\\" + "temp" + "\\" + pet_image);
				File destDir = new File(ARTICLE_IMAGE_PET + "\\" + pet_NO);
				FileUtils.moveFileToDirectory(srcFile, destDir, true);
			}
			
			message = "<script>";
			message += " alert('반려동물을 추가했습니다.');";
			message += " location.href='" + multipartRequest.getContextPath() + "/mypage/myPage.do'; ";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			System.out.println("articleMap : " + articleMap);
		} catch (Exception e) {
			File srcFile = new File(ARTICLE_IMAGE_PET + "\\" + "temp" + "\\" + pet_image);
			srcFile.delete();

			message = " <script>";
			message += " alert('오류가 발생했습니다. 다시 시도해 주세요');";
			message += " location.href='" + multipartRequest.getContextPath() + "/mypage/petAdd.do'; ";
			message += "</script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			System.out.println("articleMap : " + articleMap);
			e.printStackTrace();
		}
		
		System.out.println("PetControllerImpl : addPet end");
		return resEnt;
	}

	@RequestMapping(value = "/mypage/*Form.do", method = RequestMethod.GET)
	public ModelAndView viewPet(@RequestParam("member_NO") int member_NO, HttpServletRequest request,
		HttpServletResponse response) throws Exception {
		System.out.println("PetControllerImpl : viewPet start");
		
		String viewName = (String) request.getAttribute("viewName");
		petVO = petService.viewPet(member_NO);
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName(viewName);
		mav.addObject("pet", petVO);
		
		String name = petVO.getPet_name();
		int age = petVO.getPet_age();
		String scale = petVO.getPet_scale();
		String image = petVO.getPet_image();
		System.out.println("펫 이름 : " + name);
		
		System.out.println("PetControllerImpl : viewPet end");
		return mav;
	}

	//	@RequestMapping(value = "/mypage/*Form.do", method = RequestMethod.GET)
	//	private ModelAndView form(HttpServletRequest request, HttpServletResponse response) throws Exception {
	//		String viewName = (String) request.getAttribute("viewName");
	//		ModelAndView mav = new ModelAndView();
	//		mav.setViewName(viewName);
	//		return mav;
	//	}

	private String upload(MultipartHttpServletRequest multipartRequest) throws Exception {
		System.out.println("PetControllerImpl : upload start");
		
		String pet_image = null;
		Map<String, String> articleMap = new HashMap<String, String>();
		Iterator<String> fileNames = multipartRequest.getFileNames();

		while (fileNames.hasNext()) {
			String fileName = fileNames.next();
			MultipartFile mFile = multipartRequest.getFile(fileName);
			pet_image = mFile.getOriginalFilename();
			File file = new File(ARTICLE_IMAGE_PET + "\\" + fileName);
			if (mFile.getSize() != 0) {
				if (!file.exists()) {
					if (file.getParentFile().mkdirs()) {
						file.createNewFile();
					}
				}
				mFile.transferTo(new File(ARTICLE_IMAGE_PET + "\\" + "temp" + "\\" + pet_image));
			}
		}
		
		System.out.println("PetControllerImpl : upload end");
		return pet_image;
	}

	/*
	 * @Override
	 * 
	 * @RequestMapping(value = "/mypage/updatePet.do", method = RequestMethod.POST)
	 * public ModelAndView updatePet(PetVO vo, HttpServletRequest request,
	 * HttpServletResponse response, MultipartHttpServletRequest multipartRequest)
	 * throws Exception {
	 * 
	 * int result = 0; int pet_NO =
	 * Integer.parseInt(request.getParameter("pet_NO")); String pet_scale =
	 * request.getParameter("pet_scale"+ pet_NO); int member_NO =
	 * Integer.parseInt(request.getParameter("member_NO")); String pet_name =
	 * request.getParameter("pet_name"); int pet_age =
	 * Integer.parseInt(request.getParameter("pet_age"));
	 * vo.setPet_scale(pet_scale); vo.setPet_age(pet_age); vo.setPet_name(pet_name);
	 * vo.setPet_NO(pet_NO); vo.setMember_NO(member_NO); result =
	 * petService.updatePet(vo);
	 * 
	 * 
	 * ModelAndView mav = new ModelAndView("redirect:/mypage/myPage.do"); return
	 * mav; }
	 */


	@RequestMapping(value="/mypage/updatePet.do" ,method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity updatePet(MultipartHttpServletRequest multipartRequest,  
		HttpServletResponse response) throws Exception{
		System.out.println("PetControllerImpl : updatePet start");
		
		multipartRequest.setCharacterEncoding("utf-8");
		Map<String,Object> articleMap = new HashMap<String, Object>();
		Enumeration enu=multipartRequest.getParameterNames();
		while(enu.hasMoreElements()){
			String name=(String)enu.nextElement();
			System.out.println("pet_name : "+name);
			String value=multipartRequest.getParameter(name);
			articleMap.put(name,value);
			System.out.println("pet_value : "+value);
		}

		String pet_image= upload(multipartRequest);
		HttpSession session = multipartRequest.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("member");
		String member_id = memberVO.getMember_id();
		int member_NO = memberVO.getMember_NO();
		String pet_NO = multipartRequest.getParameter("pet_NO");
		String pet_scale = multipartRequest.getParameter("pet_scale"+ pet_NO); 
		String pet_name = multipartRequest.getParameter("pet_name"); 
		int pet_age = Integer.parseInt(multipartRequest.getParameter("pet_age"));

		articleMap.put("pet_age",pet_age);
		articleMap.put("pet_NO",pet_NO); 
		articleMap.put("pet_scale",pet_scale);
		articleMap.put("pet_name",pet_name);
		articleMap.put("member_NO",member_NO);
		articleMap.put("member_id", member_id);
		articleMap.put("pet_image", pet_image);
		System.out.println("pet_controller : update : "+articleMap);
		
		pet_NO =(String)articleMap.get("pet_NO");
		
		String message;
		ResponseEntity resEnt=null;
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		try {
			petService.updatePet(articleMap);
			
			if(pet_image!=null && pet_image.length()!=0) {
				File srcFile = new File(ARTICLE_IMAGE_PET+"\\"+"temp"+"\\"+pet_image);
				File destDir = new File(ARTICLE_IMAGE_PET+"\\"+pet_NO);
				FileUtils.moveFileToDirectory(srcFile, destDir, true);

				String originalFileName = (String)articleMap.get("originalFileName");
				File oldFile = new File(ARTICLE_IMAGE_PET+"\\"+pet_NO+"\\"+originalFileName);
				oldFile.delete();
			}	
			
			message = "<script>";
			message += " alert('반려동물 정보를 수정했습니다.');";
			message += " location.href='" + multipartRequest.getContextPath() + "/mypage/myPage.do'; ";
			message +=" </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		}catch(Exception e) {
			File srcFile = new File(ARTICLE_IMAGE_PET+"\\"+"temp"+"\\"+pet_image);
			srcFile.delete();
			message = "<script>";
			message += " alert('오류가 발생했습니다.다시 수정해주세요');";
			message += " location.href='" + multipartRequest.getContextPath() + "/mypage/myPage.do'; ";
			message +=" </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		}
		
		System.out.println("PetControllerImpl : updatePet end");
		return resEnt;
	}



	@Override
	@RequestMapping(value="/mypage/removePet.do" ,method = RequestMethod.GET)
	public ModelAndView removePet(@RequestParam("pet_NO") int pet_NO, 
		HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("PetControllerImpl : removePet start");
		
		request.setCharacterEncoding("utf-8");
		/*
		 * System.out.println("pet_NO : "+pet_NO); pet_NO =
		 * Integer.parseInt(request.getParameter("pet_NO"));
		 */
		System.out.println("remove_pet 시작");
		System.out.println("pet_NO : " + pet_NO);
		petService.removePet(pet_NO);
		ModelAndView mav = new ModelAndView("redirect:/mypage/myPage.do");
		
		System.out.println("PetControllerImpl : removePet end");
		return mav;
	}
}
