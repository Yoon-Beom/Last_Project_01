package com.spring.test.member.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.spring.test.member.vo.MemberVO;

public interface MemberService {
	
	public List listMembers() throws DataAccessException;
	public int addMember(MemberVO member) throws DataAccessException;
	public int removeMember(String member_id) throws DataAccessException;
	public MemberVO login(MemberVO memberVO) throws Exception;
	public int updateMember(MemberVO vo) throws Exception;
	public int selectById(String member_id) throws DataAccessException;
	
}
