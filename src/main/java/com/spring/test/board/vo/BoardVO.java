package com.spring.test.board.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("boardVO")
public class BoardVO {
	private int board_NO; // 게시판 일련 번호
	private int member_NO; // 회원 일련 번호
	private String board_code; //게시판 분류 번호 (자유게시판, QnA, 공지사항 : 1, 2, 3)
	private String board_screct;// 비밀글 처리 (일반 : 0, 비밀글 : 1)
	private String board_title;// 제목
	private String board_name ;// 작성자 (세션 처리)
	private String board_content ;// 글 내용
    private Date board_Date ; // 작성일자
    private String board_image ;// 사진
    private int board_score ; // 조회수
    private String board_level ;// 상단 공지(일반 : 0, 공지 : 1)
    private int rnum;
    public int getRnum() {
		return rnum;
	}
	public void setRnum(int rnum) {
		this.rnum = rnum;
	}
	public BoardVO() {
		 
	 }
	 public BoardVO(String board_code, String board_screct, String board_title, String board_name,
			 String board_content, String board_image, int board_score, String board_level) {
		this.board_code = board_code;
		this.board_screct = board_screct;
		this.board_title = board_title;
		this.board_name = board_name;
		this.board_content = board_content;
		this.board_image = board_image;
		this.board_score = board_score;
		this.board_level = board_level;
		 
		 
	 }
    
	public int getBoard_NO() {
		return board_NO;
	}
	public void setBoard_NO(int board_NO) {
		this.board_NO = board_NO;
	}
	public int getMember_NO() {
		return member_NO;
	}
	public void setMember_NO(int member_NO) {
		this.member_NO = member_NO;
	}
	public String getBoard_code() {
		return board_code;
	}
	public void setBoard_code(String board_code) {
		this.board_code = board_code;
	}
	public String getBoard_screct() {
		return board_screct;
	}
	public void setBoard_screct(String board_screct) {
		this.board_screct = board_screct;
	}
	public String getBoard_title() {
		return board_title;
	}
	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}
	public String getBoard_name() {
		return board_name;
	}
	public void setBoard_name(String board_name) {
		this.board_name = board_name;
	}
	public String getBoard_content() {
		return board_content;
	}
	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}
	public Date getBoard_Date() {
		return board_Date;
	}
	public void setBoard_Date(Date board_Date) {
		this.board_Date = board_Date;
	}
	public String getBoard_image() {
		return board_image;
	}
	public void setBoard_image(String board_image) {
		this.board_image = board_image;
	}
	public int getBoard_score() {
		return board_score;
	}
	public void setBoard_score(int board_score) {
		this.board_score = board_score;
	}
	public String getBoard_level() {
		return board_level;
	}
	public void setBoard_level(String board_level) {
		this.board_level = board_level;
	}
}
