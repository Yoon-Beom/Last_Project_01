package com.spring.test.common.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class FileDownloadController {
	private static final String ARTICLE_IMAGE_REPO = "C:\\workspace\\article_image";
	private static final String ARTICLE_IMAGE_PET = "C:\\workspace\\pet_image";
	@RequestMapping("/download.do")
	protected void download(@RequestParam("board_image") String board_image,
							@RequestParam("board_NO") String board_NO,
			                 HttpServletResponse response)throws Exception {
		System.out.println("FileDownloadController : download start");
		OutputStream out = response.getOutputStream();
		String downFile = ARTICLE_IMAGE_REPO + "\\" +board_NO+"\\"+ board_image;
		File file = new File(downFile);
		System.out.println("downFile : "+downFile);
		response.setHeader("Cache-Control", "no-cache");
		response.addHeader("Content-disposition", "attachment; fileName=" + board_image);
		FileInputStream in = new FileInputStream(file);
		byte[] buffer = new byte[1024 * 8];
		while (true) {
			int count = in.read(buffer); 
			if (count == -1) 
				break;
			out.write(buffer, 0, count);
		}
		in.close();
		out.close();
		System.out.println("FileDownloadController : download end");
	}
	@RequestMapping("/downloadPet.do")
	protected void downloadPet(@RequestParam("pet_image") String pet_image,
							@RequestParam("pet_NO") String pet_NO,
			                 HttpServletResponse response)throws Exception {
		System.out.println("FileDownloadController : downloadPet start");
		OutputStream out = response.getOutputStream();
		String downFile = ARTICLE_IMAGE_PET + "\\" +pet_NO+"\\"+ pet_image;
		File file = new File(downFile);
		System.out.println("downFile : "+downFile);
		response.setHeader("Cache-Control", "no-cache");
		response.addHeader("Content-disposition", "attachment; fileName=" + pet_image);
		FileInputStream in = new FileInputStream(file);
		byte[] buffer = new byte[1024 * 8];
		while (true) {
			int count = in.read(buffer); 
			if (count == -1) 
				break;
			out.write(buffer, 0, count);
		}
		in.close();
		out.close();
		System.out.println("FileDownloadController : downloadPet end");
	}
}
