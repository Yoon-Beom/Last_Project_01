package com.spring.test.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spring.test.member.vo.MemberVO;
import com.spring.test.member.dao.MemberDAO;
@Service("memberService")
@Transactional(propagation = Propagation.REQUIRED)
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberDAO memberDAO;

	@Override
	public List listMembers() throws DataAccessException {
		List membersList = null;
		membersList = memberDAO.selectAllMemberList();
		return membersList;
	}
	
	@Override
	public int addMember(MemberVO member) throws DataAccessException {
		return memberDAO.insertMember(member);
	}
	@Override
	public int removeMember(String member_id) throws DataAccessException {
		System.out.println("removeMemberstart");
		return memberDAO.deleteMember(member_id);
	}
	@Override
	public MemberVO login(MemberVO memberVO) throws Exception{
		System.out.println("MemberService login");
		return memberDAO.loginById(memberVO);
	}
}
