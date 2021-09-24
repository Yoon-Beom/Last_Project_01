package com.spring.test.member.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.spring.test.member.vo.MemberVO;

public interface MemberService {
	
	public List listMembers() throws DataAccessException;
	public int addMember(MemberVO member) throws DataAccessException;
	

}
