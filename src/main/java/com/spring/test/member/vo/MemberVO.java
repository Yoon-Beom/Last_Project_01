package com.spring.test.member.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("memberVO")
public class MemberVO {

	private int member_NO;					// 회원 일련번호
	private String member_id; 				// 아이디 (중복확인)
	private String member_pwd;				// 비밀번호 (비밀번호 규제, 암호화 & 복호화)
	private String member_salt;				// 비밀번호 솔트
	private String member_name;				// 이름
	private String member_birth;			// 생년월일
	private String member_phone;			// 연락처
	private String member_email;			// 이메일
	private String member_gender;			// 성별 (남자, 여자 : m, f)
	private String member_address;			// 주소 (다음 주소 찾기 API)
	private Date member_joinDate;			// 가입일자
	private String member_status;			// 상태 (탈퇴 : 0, 가입 : 1)
	private String member_code;				// 구분 (회원, 매장, 관리자 : 1, 2, 3)
	private String member_heartShop;		// (관심 샵 번호 : ,으로 구분)

	public MemberVO() { }
	
	public MemberVO(String member_id, String member_pwd, String member_salt, String member_name,
			String member_birth, String member_phone, String member_email, String member_gender,
			String member_address) {
		this.member_id = member_id;
		this.member_pwd = member_pwd;
		this.member_salt = member_salt;
		this.member_name = member_name;
		this.member_birth = member_birth;
		this.member_phone = member_phone;
		this.member_email = member_email;
		this.member_gender = member_gender;
		this.member_address = member_address;
	}

	public void setMember_NO(int member_NO) { this.member_NO = member_NO; }
	public void setMember_id(String member_id) { this.member_id = member_id; }
	public void setMember_pwd(String member_pwd) { this.member_pwd = member_pwd; }
	public void setMember_salt(String member_salt) { this.member_salt = member_salt; }
 	public void setMember_name(String member_name) { this.member_name = member_name; }
	public void setMember_birth(String member_birth) { this.member_birth = member_birth; }
	public void setMember_phone(String member_phone) { this.member_phone = member_phone; }
	public void setMember_email(String member_email) { this.member_email = member_email; }
	public void setMember_gender(String member_gender) { this.member_gender = member_gender; }
	public void setMember_address(String member_address) { this.member_address = member_address; }
	public void setMember_joinDate(Date member_joinDate) { this.member_joinDate = member_joinDate; }
	public void setMember_status(String member_status) { this.member_status = member_status; }
	public void setMember_code(String member_code) { this.member_code = member_code; }
	public void setMember_heartShop(String member_heartShop) { this.member_heartShop = member_heartShop; }

	public int getMember_NO() { return member_NO; }
 	public String getMember_id() { return member_id; }
	public String getMember_pwd() { return member_pwd; }
	public String getMember_salt() { return member_salt; }
	public String getMember_name() { return member_name; }
	public String getMember_birth() { return member_birth; }
	public String getMember_phone() { return member_phone; }
	public String getMember_email() { return member_email; }
	public String getMember_gender() { return member_gender; }
	public String getMember_address() { return member_address; }
	public Date getMember_joinDate() { return member_joinDate; }
	public String getMember_status() { return member_status; }
	public String getMember_code() { return member_code; }
	public String getMember_heartShop() { return member_heartShop; }
	
}

