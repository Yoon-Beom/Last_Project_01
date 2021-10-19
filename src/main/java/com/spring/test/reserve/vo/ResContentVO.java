package com.spring.test.reserve.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("resContentVO")
public class ResContentVO {				// 예약 디테일 테이블
	private int resContent_NO;			// 예약 디테일 일련번호
	private int member_NO;				// 회원 테이블 : 회원 일련번호
	private String member_id;			// 회원 테이블 : 아이디
	private String member_name;			// 회원 테이블 : 이름
	private String member_birth;		// 회원 테이블 : 생년월일
	private String member_phone;		// 회원 테이블 : 연락처
	private String member_email;		// 회원 테이블 : 이메일
	private String member_gender;		// 회원 테이블 : 성별
	private String member_address;		// 회원 테이블 : 주소
	private Date member_joinDate;		// 회원 테이블 : 가입일자
	private String member_code;			// 회원 테이블 : 구분 (회원, 매장, 관리자 : 1, 2, 3)

	private int pet_NO;					// 반려동물 테이블 : 반려동물 일련번호
	private String pet_name;			// 반려동물 테이블 : 반려동물 이름
	private int pet_age;				// 반려동물 나이
	private String pet_scale;			// 반려동물 테이블 : 반려동물 크기 (소, 중, 대 : 1, 2, 3)
	private String pet_image;			// 반려동물 테이블 : 반려동물 사진
	private Date pet_joinDate;			// 반려동물 테이블 : 등록일자

	private int shop_NO;				// 매장 테이블 : 매장 일련번호
	private int shopDetail_NO;			// 매장 테이블 : 매장 디테일 일련번호
	private String shop_name;			// 매장 테이블 : 매장 이름
	private String shop_address;		// 매장 테이블 : 매장 주소
	private double shop_latitude;		// 매장 테이블 : 사업자 지도 위도 latitude
	private double shop_longitude;		// 매장 테이블 : 사업자 지도 경도 longitude
	private Date shop_joinDate;			// 매장 테이블 : 등록날짜
	
	private int reserve_NO;				// 예약 테이블 : 예약 일련번호*
	private Date reserve_Date;			// 예약 테이블 : 날짜
	private String reserve_TimeA;		// 예약 테이블 : 시간
	private String res_spot;			// 예약 테이블 : 미용 부위(전체, 부분 : 1, 2)
	private String res_option;			// 예약 테이블 : 옵션 (목욕 : 0, 1)
	private String res_status;			// 예약 테이블 : 예약상태 (예약 상태 : 0, 1)
	
	private int rnum;					// 방문내역 번호 용도

	ResContentVO() { }
	
	public void setResContent_NO(int resContent_NO) { this.resContent_NO = resContent_NO; }
	public void setMember_NO(int member_NO) { this.member_NO = member_NO; }
	public void setMember_id(String member_id) { this.member_id = member_id; }
	public void setMember_name(String member_name) { this.member_name = member_name; }
	public void setMember_birth(String member_birth) { this.member_birth = member_birth; }
	public void setMember_phone(String member_phone) { this.member_phone = member_phone; }
	public void setMember_email(String member_email) { this.member_email = member_email; }
	public void setMember_gender(String member_gender) { this.member_gender = member_gender; }
	public void setMember_address(String member_address) { this.member_address = member_address; }
	public void setMember_joinDate(Date member_joinDate) { this.member_joinDate = member_joinDate; }
	public void setMember_code(String member_code) { this.member_code = member_code; }
	
	public void setPet_NO(int pet_NO) { this.pet_NO = pet_NO; }
	public void setPet_name(String pet_name) { this.pet_name = pet_name; }
	public void setPet_age(int pet_age) { this.pet_age = pet_age; }
	public void setPet_scale(String pet_scale) { this.pet_scale = pet_scale; }
	public void setPet_image(String pet_image) { this.pet_image = pet_image; }
	public void setPet_joinDate(Date pet_joinDate) { this.pet_joinDate = pet_joinDate; }
	
	public void setShop_NO(int shop_NO) { this.shop_NO = shop_NO; }
	public void setShopDetail_NO(int shopDetail_NO) { this.shopDetail_NO = shopDetail_NO; }
	public void setShop_name(String shop_name) { this.shop_name = shop_name; }
	public void setShop_address(String shop_address) { this.shop_address = shop_address; }
	public void setShop_latitude(double shop_latitude) { this.shop_latitude = shop_latitude; }
	public void setShop_longitude(double shop_longitude) { this.shop_longitude = shop_longitude; }
	public void setShop_joinDate(Date shop_joinDate) { this.shop_joinDate = shop_joinDate; }
	
	public void setReserve_NO(int reserve_NO) { this.reserve_NO = reserve_NO; }
	public void setReserve_Date(Date reserve_Date) { this.reserve_Date = reserve_Date; }
	public void setReserve_TimeA(String reserve_TimeA) { this.reserve_TimeA = reserve_TimeA; }
	public void setRes_spot(String res_spot) { this.res_spot = res_spot; }
	public void setRes_option(String res_option) { this.res_option = res_option; }
	public void setRes_status(String res_status) { this.res_status = res_status; }
	
	public void setRnum(int rnum) { this.rnum = rnum; }
	 
	
	public int getResContent_NO() { return resContent_NO; }
	public int getMember_NO() { return member_NO; }
	public String getMember_id() { return member_id; }
	public String getMember_name() { return member_name; }
	public String getMember_birth() { return member_birth; }
	public String getMember_phone() { return member_phone; }
	public String getMember_email() { return member_email; }
	public String getMember_gender() { return member_gender; }
	public String getMember_address() { return member_address; }
	public Date getMember_joinDate() { return member_joinDate; }
	public String getMember_code() { return member_code; }
	
	public int getPet_NO() { return pet_NO; }
	public String getPet_name() { return pet_name; }
	public int getPet_age() { return pet_age; }
	public String getPet_scale() { return pet_scale; }
	public String getPet_image() { return pet_image; }
	public Date getPet_joinDate() { return pet_joinDate; }
	
	public int getShop_NO() { return shop_NO; }
	public int getShopDetail_NO() { return shopDetail_NO; }
	public String getShop_name() { return shop_name; }
	public String getShop_address() { return shop_address; }
	public double getShop_latitude() { return shop_latitude; }
	public double getShop_longitude() { return shop_longitude; }
	public Date getShop_joinDate() { return shop_joinDate; }
	
	public int getReserve_NO() { return reserve_NO; }
	public Date getReserve_Date() { return reserve_Date; }
	public String getReserve_TimeA() { return reserve_TimeA; }
	public String getRes_spot() { return res_spot; }
	public String getRes_option() { return res_option; }
	public String getRes_status() { return res_status; }
	
	public int getRnum() { return rnum; }
}
