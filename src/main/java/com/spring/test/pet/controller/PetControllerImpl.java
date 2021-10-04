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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.spring.test.member.vo.MemberVO;
import com.spring.test.pet.service.PetService;
import com.spring.test.pet.vo.PetVO;

@Controller("petController")
public class PetControllerImpl implements PetController {
	private static final String ARTICLE_IMAGE_REPO = "C:\\workspace\\pet_image";
	@Autowired
	private PetService petService;
	@Autowired
	private PetVO petVO;
	@Autowired
	private MemberVO memberVO;

	
	@Override
	@RequestMapping(value = "/mypage/myPage.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView listPet(
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			System.out.println("이거되나");
			HttpSession session = request.getSession();
			memberVO = (MemberVO) session.getAttribute("member");
			int member_NO = memberVO.getMember_NO();
			System.out.println("listPet member_NO : "+member_NO);
		String viewName = (String) request.getAttribute("viewName");
		List petList = petService.listPet(member_NO);
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("member", memberVO);
		mav.addObject("petList", petList);
		String a = petVO.getPet_name();
		System.out.println("이거 나오나 : " + a);
		return mav;
	}

	@Override
	@RequestMapping(value = "/mypage/petAdd.do", method = RequestMethod.POST)
	public ResponseEntity addPet(@ModelAttribute("member") MemberVO member,
			MultipartHttpServletRequest multipartRequest, HttpServletResponse response) throws Exception {
		System.out.println("petAdd 시작");
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
				File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + pet_image);
				File destDir = new File(ARTICLE_IMAGE_REPO + "\\" + pet_NO);
				FileUtils.moveFileToDirectory(srcFile, destDir, true);
			}
			message = "<script>";
			message += " alert('반려동물을 추가했습니다.');";
			message += " location.href='" + multipartRequest.getContextPath() + "/mypage/myPage.do'; ";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			System.out.println(articleMap);
		} catch (Exception e) {
			File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + pet_image);
			srcFile.delete();

			message = " <script>";
			message += " alert('오류가 발생했습니다. 다시 시도해 주세요');";
			message += " location.href='" + multipartRequest.getContextPath() + "/mypage/petAdd.do'; ";
			message += "</script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			System.out.println("articleMap : " + articleMap);
			e.printStackTrace();
		}

		return resEnt;
	}

	@RequestMapping(value = "/mypage/*.do", method = RequestMethod.GET)
	public ModelAndView viewPet(@RequestParam("member_NO") int member_NO, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		petVO = petService.viewPet(member_NO);
	ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		mav.addObject("pet", "petVO");
		String name = petVO.getPet_name();
		String age = petVO.getPet_age();
		String scale = petVO.getPet_scale();
		String image = petVO.getPet_image();
		System.out.println("펫 이름 : " + name);
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
		String pet_image = null;
		Map<String, String> articleMap = new HashMap<String, String>();
		Iterator<String> pet_fileNames = multipartRequest.getFileNames();

		while (pet_fileNames.hasNext()) {
			String pet_fileName = pet_fileNames.next();
			MultipartFile pet_mFile = multipartRequest.getFile(pet_fileName);
			pet_image = pet_mFile.getOriginalFilename();
			File file = new File(ARTICLE_IMAGE_REPO + "\\" + pet_fileName);
			if (pet_mFile.getSize() != 0) {
				if (!file.exists()) {
					if (file.getParentFile().mkdirs()) {
						file.createNewFile();
					}
				}
				pet_mFile.transferTo(new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + pet_image));
			}
		}
		return pet_image;
	}



}
