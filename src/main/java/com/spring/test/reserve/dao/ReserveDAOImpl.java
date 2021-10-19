package com.spring.test.reserve.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.spring.test.reserve.vo.ReserveVO;

@Repository("reserveDAO")
public class ReserveDAOImpl implements ReserveDAO {
	@Autowired
	private SqlSession sqlSession;
	@Autowired
	private ReserveVO reserveVO;
	
	@Override
	public int insertReserve(ReserveVO reserveVO) throws DataAccessException {
		System.out.println("ReserveDAOImpl : insertReserve start");
		
		sqlSession.insert("mapper.reserve.insertReserve", reserveVO);
		
		int reserve_NO = sqlSession.selectOne("mapper.reserve.selectMaxResNO");

		System.out.println("ReserveDAOImpl : insertReserve end");
		return reserve_NO;
	}

	@Override
	public List<String> selectDateCount(HashMap<String, Object> hashMap) throws DataAccessException {
		System.out.println("ReserveDAOImpl : selectDateCount start");
		return sqlSession.selectList("selectDateCount", hashMap);
	}

	@Override
	public List<String> selectTimeByDate(HashMap<String, Object> hashMap) throws DataAccessException {
		System.out.println("ReserveDAOImpl : selectTimeByDate start");
		return sqlSession.selectList("selectTimeByDate", hashMap);
	}
	
}
