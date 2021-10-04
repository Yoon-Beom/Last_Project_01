package com.spring.test.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.spring.test.member.vo.MemberVO;

@Repository("memberDAO")
public class MemberDAOImpl implements MemberDAO{
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List selectAllMemberList() throws DataAccessException {
		List<MemberVO> membersList = null;
		membersList = sqlSession.selectList("mapper.member.selectAllMemberList");
		System.out.println("membersList"+membersList);
		return membersList;
	}
	@Override
	public int insertMember(MemberVO member) throws DataAccessException {
		int result = sqlSession.insert("mapper.member.insertMember", member);
		return result;
	}
	@Override
	public int deleteMember(String member_id) throws DataAccessException {
		System.out.println("deleteMember : "+member_id);
		
		int result = sqlSession.delete("mapper.member.deleteMember",member_id);
		System.out.println(result);
		System.out.println("deleteMember start");
		return result;
	}
	@Override
	public MemberVO loginById(MemberVO memberVO) throws DataAccessException{
		System.out.println("loginById");
		String id = memberVO.getMember_id();
		String pwd = memberVO.getMember_pwd();
		
		System.out.println("id"+id+"pwd:"+pwd);
		  MemberVO vo = sqlSession.selectOne("mapper.member.loginById",memberVO);
		return vo;
	}

	@Override
	public int updateMember(MemberVO vo) throws Exception {
		int result =sqlSession.update("mapper.member.updateMember", vo);
		return result;
		
	}
	@Override
	public int selectById(String member_id) throws DataAccessException {
		System.out.println("MemberDAOImpl : selectById start");
		
		int result = sqlSession.selectOne("mapper.member.selectById", member_id);
		
		System.out.println("MemberDAOImpl : selectById end");
		return result;
	}

	@Override
	public String selectSaltById(String member_id) throws DataAccessException {
		System.out.println("MemberDAOImpl : selectSaltById start");
		
		String result = sqlSession.selectOne("mapper.member.selectSaltById", member_id);
		
		System.out.println("MemberDAOImpl : selectSaltById end");
		return result;
	}

}
