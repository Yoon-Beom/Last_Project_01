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
		System.out.println("MemberDAOImpl : selectAllMemberList start");
		
		List<MemberVO> membersList = null;
		membersList = sqlSession.selectList("mapper.member.selectAllMemberList");
		
		System.out.println("membersList : " + membersList);
		System.out.println("MemberDAOImpl : selectAllMemberList end");
		return membersList;
	}

	@Override
	public int insertMember(MemberVO member) throws DataAccessException {
		System.out.println("MemberDAOImpl : insertMember start");
		
		int result = sqlSession.insert("mapper.member.insertMember", member);
		
		System.out.println("MemberDAOImpl : insertMember end");
		return result;
	}

	@Override
	public int deleteMember(String member_id) throws DataAccessException {
		System.out.println("MemberDAOImpl : deleteMember start");
		System.out.println("deleteMember : " + member_id);

		int result = sqlSession.delete("mapper.member.deleteMember", member_id);
		System.out.println(result);
		
		System.out.println("MemberDAOImpl : deleteMember end");
		return result;
	}

	@Override
	public MemberVO loginById(MemberVO memberVO) throws DataAccessException{
		System.out.println("MemberDAOImpl : loginById start");
		
		String id = memberVO.getMember_id();
		String pwd = memberVO.getMember_pwd();

		System.out.println("id : " + id);
		System.out.println("pwd : " + pwd);
		MemberVO vo = sqlSession.selectOne("mapper.member.loginById", memberVO);

		System.out.println("MemberDAOImpl : loginById end");
		return vo;
	}

	@Override
	public int updateMember(MemberVO vo) throws Exception {
		System.out.println("MemberDAOImpl : updateMember start");
		
		int result = sqlSession.update("mapper.member.updateMember", vo);
		
		System.out.println("MemberDAOImpl : updateMember end");
		return result;
	}
	
	@Override
	public int updateMemPwd(MemberVO vo) throws Exception {
		System.out.println("MemberDAOImpl : updateMemPwd start");
		
		int result = sqlSession.update("mapper.member.updateMemPwd", vo);
		
		System.out.println("MemberDAOImpl : updateMemPwd end");
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
	
	@Override
	public int selectMemberNoById(String member_id) throws DataAccessException {
		System.out.println("MemberDAOImpl : selectMemberNoById start");

		int result = sqlSession.selectOne("mapper.member.selectMemberNoById", member_id);
		
		System.out.println("MemberDAOImpl : selectMemberNoById end");
		return result;
	}
	
	@Override
	public int deleteMem(String member_id) throws DataAccessException {
		// TODO Auto-generated method stub
		int result = sqlSession.delete("mapper.member.deleteMem", member_id);
		return result;
	}
}
