package com.spring.test.comment.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("commentVO")
public class CommentVO {
	private int comment_NO; // 댓글 일련번호
	private int member_NO; //회원 일련번호*
	private String member_name;//회원 이름
	private int board_NO; //게시글 일련번호
	private String comment_content; // 댓글 내용
	private Date comment_Date;
	
	public CommentVO() {
		
	}
	public CommentVO(int comment_NO, int member_NO, int board_NO, String comment_content, String member_name) {
		this.comment_NO = comment_NO;
		this.member_NO = member_NO;
		this.board_NO = board_NO;
		this.comment_content = comment_content;
		this.member_name = member_name;
	}
	public String getmember_name() {
		return member_name;
	}
	public void setmember_name(String member_name) {
		this.member_name = member_name;
	}
	public int getComment_NO() {
		return comment_NO;
	}
	public void setComment_NO(int comment_NO) {
		this.comment_NO = comment_NO;
	}
	public int getMember_NO() {
		return member_NO;
	}
	public void setMember_NO(int member_NO) {
		this.member_NO = member_NO;
	}
	public int getBoard_NO() {
		return board_NO;
	}
	public void setBoard_NO(int board_NO) {
		this.board_NO = board_NO;
	}
	public String getComment_content() {
		return comment_content;
	}
	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}
	public Date getComment_Date() {
		return comment_Date;
	}
	public void setComment_Date(Date comment_Date) {
		this.comment_Date = comment_Date;
	}
}
