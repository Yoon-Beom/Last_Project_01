package com.spring.test.shop.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("shopDetailVO")
public class ShopDetailVO {

	private int shopDetail_NO;  			// 매장 디테일 일련번호
	private int shop_NO;   			        // 매장 일련번호*
	private String shop_ceo;                // 대표 이름
	private String shop_phone;              // 매장 전화번호
	private String shop_open_time;          // 오픈 시간
	private String shop_close_time;         // 마감 시간
	private String shop_introduce;          // 소개글
	private int shop_starScore;             // 별점
	private String shop_imageMain;          // 메인 사진
	private String shop_imageSub;           // 서브 사진
	private int shop_heartScore;            // 관심 수
	private int shop_reserveScore;          // 예약 수 (마이페이지)
	private Date shopDetail_joinDate;       // 등록날짜	
	
	public ShopDetailVO() {}
	
	public ShopDetailVO(String shop_ceo, String shop_phone, String shop_open_time, String shop_close_time, String shop_introduce,
				int shop_starScore, String shop_imageMain, String shop_imageSub, int shop_heartScore, int shop_reserveScore) {
		this.shop_ceo = shop_ceo;
		this.shop_phone = shop_phone;
		this.shop_open_time = shop_open_time;
		this.shop_close_time = shop_close_time;
		this.shop_introduce = shop_introduce;
		this.shop_starScore = shop_starScore;
		this.shop_imageMain = shop_imageMain;
		this.shop_imageSub = shop_imageSub;
		this.shop_heartScore = shop_heartScore;
		this.shop_reserveScore = shop_reserveScore;
	}
	
	
	public int getShopDetail_NO() {
		return shopDetail_NO;
	}
	public void setShopDetail_NO(int shopDetail_NO) {
		this.shopDetail_NO = shopDetail_NO;
	}
	public int getShop_NO() {
		return shop_NO;
	}
	public void setShop_NO(int shop_NO) {
		this.shop_NO = shop_NO;
	}
	public String getShop_ceo() {
		return shop_ceo;
	}
	public void setShop_ceo(String shop_ceo) {
		this.shop_ceo = shop_ceo;
	}
	public String getShop_phone() {
		return shop_phone;
	}
	public void setShop_phone(String shop_phone) {
		this.shop_phone = shop_phone;
	}
	public String getShop_open_time() {
		return shop_open_time;
	}
	public void setShop_open_time(String shop_open_time) {
		this.shop_open_time = shop_open_time;
	}
	public String getShop_close_time() {
		return shop_close_time;
	}
	public void setShop_close_time(String shop_close_time) {
		this.shop_close_time = shop_close_time;
	}
	public String getShop_introduce() {
		return shop_introduce;
	}
	public void setShop_introduce(String shop_introduce) {
		this.shop_introduce = shop_introduce;
	}
	public int getShop_starScore() {
		return shop_starScore;
	}
	public void setShop_starScore(int shop_starScore) {
		this.shop_starScore = shop_starScore;
	}
	public String getShop_imageMain() {
		return shop_imageMain;
	}
	public void setShop_imageMain(String shop_imageMain) {
		this.shop_imageMain = shop_imageMain;
	}
	public String getShop_imageSub() {
		return shop_imageSub;
	}
	public void setShop_imageSub(String shop_imageSub) {
		this.shop_imageSub = shop_imageSub;
	}
	public int getShop_heartScore() {
		return shop_heartScore;
	}
	public void setShop_heartScore(int shop_heartScore) {
		this.shop_heartScore = shop_heartScore;
	}
	public int getShop_reserveScore() {
		return shop_reserveScore;
	}
	public void setShop_reserveScore(int shop_reserveScore) {
		this.shop_reserveScore = shop_reserveScore;
	}
	public Date getShopDetail_joinDate() {
		return shopDetail_joinDate;
	}
	public void setShopDetail_joinDate(Date shopDetail_joinDate) {
		this.shopDetail_joinDate = shopDetail_joinDate;
	}
	
	
}
