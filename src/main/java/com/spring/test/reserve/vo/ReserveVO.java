package com.spring.test.reserve.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("reserveVO")
public class ReserveVO {			// 예약 테이블
	private int reserve_NO;			// 예약 일련번호
	private int shop_NO;			// 매장 일련번호*
	private int member_NO;			// 회원 일련번호*
	private int pet_NO;				// 반려동물 일련번호*
	private Date reserve_Date;		// 예약 날짜
	private String reserve_TimeA;	// 예약 시간
	private String res_spot;		// 미용 부위(전체, 부분 : 1, 2)
	private String res_option;		// 옵션 (목욕 : 0, 1)
	private String res_status;		// 예약상태 (예약 상태 : 0, 1)


	ReserveVO() { }
	
	ReserveVO(int reserve_NO, int shop_NO, int member_NO, 
		int pet_NO, Date reserve_Date, String reserve_TimeA,
		String res_spot, String res_option, String res_status) {
		this.reserve_NO = reserve_NO;
		this.shop_NO = shop_NO;
		this.member_NO = member_NO;
		this.pet_NO = pet_NO;
		this.reserve_Date = reserve_Date;
		this.reserve_TimeA = reserve_TimeA;
		this.res_spot = res_spot;
		this.res_option = res_option;
		this.res_status = res_status;
	}
	
	public void setReserve_NO(int reserve_NO) { this.reserve_NO = reserve_NO; }
	public void setShop_NO(int shop_NO) { this.shop_NO = shop_NO; }
	public void setMember_NO(int member_NO) { this.member_NO = member_NO; }
	public void setPet_NO(int pet_NO) { this.pet_NO = pet_NO; }
	public void setReserve_Date(Date reserve_Date) { this.reserve_Date = reserve_Date; }
	public void setReserve_TimeA(String reserve_TimeA) { this.reserve_TimeA = reserve_TimeA; }
	public void setRes_spot(String res_spot) { this.res_spot = res_spot; }
	public void setRes_option(String res_option) { this.res_option = res_option; }
	public void setRes_status(String res_status) { this.res_status = res_status; }
	
	public int getReserve_NO() { return reserve_NO; }
	public int getShop_NO() { return shop_NO; }
	public int getMember_NO() { return member_NO; }
	public int getPet_NO() { return pet_NO; }
	public Date getReserve_Date() { return reserve_Date; }
	public String getReserve_TimeA() { return reserve_TimeA; }
	public String getRes_spot() { return res_spot; }
	public String getRes_option() { return res_option; }
	public String getRes_status() { return res_status; }
}
