package com.spring.test.pet.vo;


import java.sql.Date;

import org.springframework.stereotype.Component;

import com.spring.test.member.vo.MemberVO;

@Component("petVO")
public class PetVO {
	private int pet_NO; // 반려동물 일련번호
	private int member_NO; // 회원 일련 번호
	private String pet_name; // 반려동물 이름
	private int pet_age; // 반려동물 나이
	private String pet_scale; // 반려동물 크기 (소, 중, 대 : 1, 2, 3)
	private String pet_image; // 반려동물 사진
	private Date pet_joinDate; // 등록일자
	
	private MemberVO memberVO; //멤버 테이블 가져오기
	
	public PetVO() {

	}

	public PetVO(int pet_NO, String pet_name, int pet_age, String pet_scale, String pet_image) {
		this.pet_NO = pet_NO;
		this.pet_name = pet_name;
		this.pet_age = pet_age;
		this.pet_scale = pet_scale;
		this.pet_image = pet_image;
	}
	
	public MemberVO getMemberVO() {
		return memberVO;
	}

	public void setMemberVO(MemberVO memberVO) {
		this.memberVO = memberVO;
	}

	public int getPet_NO() {
		return pet_NO;
	}
	public void setPet_NO(int pet_NO) {
		this.pet_NO = pet_NO;
	}
	public int getMember_NO() {
		return member_NO;
	}
	public void setMember_NO(int member_NO) {
		this.member_NO = member_NO;
	}
	public String getPet_name() {
		return pet_name;
	}
	public void setPet_name(String pet_name) {
		this.pet_name = pet_name;
	}
	public int getPet_age() {
		return pet_age;
	}
	public void setPet_age(int pet_age) {
		this.pet_age = pet_age;
	}
	public String getPet_scale() {
		return pet_scale;
	}
	public void setPet_scale(String pet_scale) {
		this.pet_scale = pet_scale;
	}
	public String getPet_image() {
		return pet_image;
	}
	public void setPet_image(String pet_image) {
		this.pet_image = pet_image;
	}
	public Date getPet_joinDate() {
		return pet_joinDate;
	}
	public void setPet_joinDate(Date pet_joinDate) {
		this.pet_joinDate = pet_joinDate;
	}

	
}
