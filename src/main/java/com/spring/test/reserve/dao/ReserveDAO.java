package com.spring.test.reserve.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.spring.test.reserve.vo.ReserveVO;

public interface ReserveDAO {
	
	public int insertReserve(ReserveVO reserveVO) throws DataAccessException;
	List<String> selectDateCount(HashMap<String, Object> hashMap) throws DataAccessException;
	List<String> selectTimeByDate(HashMap<String, Object> hashMap) throws DataAccessException;

}
