package com.spring.test.member.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.spring.test.member.vo.MemberVO;

public interface MemberDAO {

	public List selectAllMemberList() throws DataAccessException;
	public int insertMember(MemberVO member) throws DataAccessException;
	public int deleteMember(String member_id) throws DataAccessException;
	public MemberVO loginById(MemberVO memberVO) throws DataAccessException;
	
	
}
