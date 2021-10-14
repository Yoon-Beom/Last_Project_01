package com.spring.test.review.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("reviewVO")
public class ReviewVO {
	private int review_NO;
	private int member_NO;
	private int shop_NO;
	private String review_title;
	private String review_content;
	private Date review_Date;
	private String review_starScore;
	private String review_image;
	private int rnum;
	private String shop_name;
	private String pet_name;
	public int getRnum() {
		return rnum;
	}
	public void setRnum(int rnum) {
		this.rnum = rnum;
	}
	public String getShop_name() {
		return shop_name;
	}
	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}
	public String getPet_name() {
		return pet_name;
	}
	public void setPet_name(String pet_name) {
		this.pet_name = pet_name;
	}
	public ReviewVO() {
		
	}
	public ReviewVO(int review_NO, int member_NO, int shop_NO, String review_title, String review_content, 
			String review_starScore, String review_image) {
		this.review_NO = review_NO;
		this.member_NO = member_NO;
		this.shop_NO = shop_NO;
		this.review_title = review_title;
		this.review_content = review_content;
		this.review_starScore = review_starScore;
		this.review_image = review_image;
		
	}
	public int getReview_NO() {
		return review_NO;
	}
	public void setReview_NO(int review_NO) {
		this.review_NO = review_NO;
	}
	public int getMember_NO() {
		return member_NO;
	}
	public void setMember_NO(int member_NO) {
		this.member_NO = member_NO;
	}
	public int getShop_NO() {
		return shop_NO;
	}
	public void setShop_NO(int shop_NO) {
		this.shop_NO = shop_NO;
	}
	public String getReview_title() {
		return review_title;
	}
	public void setReview_title(String review_title) {
		this.review_title = review_title;
	}
	public String getReview_content() {
		return review_content;
	}
	public void setReview_content(String review_content) {
		this.review_content = review_content;
	}
	public Date getReview_Date() {
		return review_Date;
	}
	public void setReview_Date(Date review_Date) {
		this.review_Date = review_Date;
	}
	public String getReview_starScore() {
		return review_starScore;
	}
	public void setReview_starScore(String review_starScore) {
		this.review_starScore = review_starScore;
	}
	public String getReview_image() {
		return review_image;
	}
	public void setReview_image(String review_image) {
		this.review_image = review_image;
	}
	
}
