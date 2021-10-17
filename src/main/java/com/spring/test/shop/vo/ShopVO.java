package com.spring.test.shop.vo;

import java.sql.Date;
import org.springframework.stereotype.Component;


@Component("shopVO")
public class ShopVO {
	private int shop_NO;    		// 매장 일련번호
	private int member_NO;          // 회원 일련번호*
	private String shop_name;       // 매장 이름
	private String shop_address;    // 매장 주소 (다음 주소 찾기 API)
	private String shop_code;       // 사업자 등록 번호
	private double shop_latitude;	// 사업자 지도 위도 latitude
	private double shop_longitude; 	// 사업자 지도 경도 longitude
	private Date shop_joinDate;		// 등록날짜

	private ShopDetailVO shopDetailVO;

	public ShopVO() { }
	
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
}
