package com.spring.test.shop.vo;

import java.sql.Date;
import com.spring.test.shop.vo.ShopDetailVO;
import org.springframework.stereotype.Component;


@Component("shopVO")
public class ShopVO {
	private int shop_NO;    		// 매장 일련번호
	private int member_NO;          // 회원 일련번호*
	private String shop_name;       // 매장 이름
	private String shop_address;    // 매장 주소 (다음 주소 찾기 API)
	private String shop_code;       // 사업자 등록 번호
	private double shop_latitude;      //사업자 지도 위도 latitude
	private double shop_longitude; 	// 사업자 지도 경도 longitude
	private Date shop_joinDate;		// 등록날짜

	private ShopDetailVO shopDetailVO;
	private int shopDetail_NO;  			//매장 디테일 일련번호
	private String shop_ceo;                //대표 이름
	private String shop_phone;              // 매장 전화번호
	private String shop_open_time;          //오픈 시간
	private String shop_close_time;         //마감 시간
	private String shop_introduce;          //소개글
	private int shop_starScore;             //별점
	private String shop_imageMain;          //메인 사진
	private String shop_imageSub;           //서브 사진
	private int shop_heartScore;            //관심 수
	private int shop_reserveScore;          //예약 수 (마이페이지)
	private Date shopDetail_joinDate;       //등록날짜	

	public ShopVO() {

	}
	
	public ShopVO(int shop_NO,int member_NO, String shop_name, String shop_address, String shop_code,
			double shop_latitude, double shop_longitude, ShopDetailVO shopDetailVO) {
		this.shop_NO = shop_NO;
		this.member_NO = member_NO;
		this.shop_name = shop_name;
		this.shop_address = shop_address;
		this.shop_code = shop_code;
		this.shop_latitude = shop_latitude;
		this.shop_longitude = shop_longitude;
		this.shopDetailVO = shopDetailVO;
	}

	public int getShop_NO() {
		return shop_NO;
	}

	public void setShop_NO(int shop_NO) {
		this.shop_NO = shop_NO;
	}

	public int getMember_NO() {
		return member_NO;
	}

	public void setMember_NO(int member_NO) {
		this.member_NO = member_NO;
	}

	public String getShop_name() {
		return shop_name;
	}

	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}

	public String getShop_address() {
		return shop_address;
	}

	public void setShop_address(String shop_address) {
		this.shop_address = shop_address;
	}

	public String getShop_code() {
		return shop_code;
	}

	public void setShop_code(String shop_code) {
		this.shop_code = shop_code;
	}

	public double getShop_latitude() {
		return shop_latitude;
	}

	public void setShop_latitude(double shop_latitude) {
		this.shop_latitude = shop_latitude;
	}

	public double getShop_longitude() {
		return shop_longitude;
	}

	public void setShop_longitude(double shop_longitude) {
		this.shop_longitude = shop_longitude;
	}

	public Date getShop_joinDate() {
		return shop_joinDate;
	}

	public void setShop_joinDate(Date shop_joinDate) {
		this.shop_joinDate = shop_joinDate;
	}

	public ShopDetailVO getShopDetailVO() {
		return shopDetailVO;
	}

	public void setShopDetailVO(ShopDetailVO shopDetailVO) {
		this.shopDetailVO = shopDetailVO;
	}

	public int getShopDetail_NO() {
		return shopDetail_NO;
	}

	public void setShopDetail_NO(int shopDetail_NO) {
		this.shopDetail_NO = shopDetail_NO;
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
