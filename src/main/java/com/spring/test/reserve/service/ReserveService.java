package com.spring.test.reserve.service;

import java.util.HashMap;
import java.util.List;

import com.spring.test.reserve.vo.ReserveVO;

public interface ReserveService {
	
	int insertReserve(ReserveVO reserveVO) throws Exception;
	List<String> selectDateCount(HashMap<String, Object> hashMap) throws Exception;
	List<String> selectTimeByDate(HashMap<String, Object> hashMap) throws Exception;

}
