package com.spring.test.reserve.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spring.test.reserve.dao.ReserveDAO;
import com.spring.test.reserve.vo.ReserveVO;

@Service("reserveService")
@Transactional(propagation = Propagation.REQUIRED)
public class ReserveServiceImpl implements ReserveService {
	@Autowired
	private ReserveDAO reserveDAO;
	
	@Override
	public int insertReserve(ReserveVO reserveVO) throws Exception {
		System.out.println("ReserveServiceImpl : insertReserve start");
		return reserveDAO.insertReserve(reserveVO);
	}
	
	@Override
	public List<String> selectDateCount(HashMap<String, Object> hashMap) throws Exception {
		System.out.println("ReserveServiceImpl : selectDateCount start");
		return reserveDAO.selectDateCount(hashMap);
	}
	
	@Override
	public List<String> selectTimeByDate(HashMap<String, Object> hashMap) throws Exception {
		System.out.println("ReserveServiceImpl : selectTimeByDate start");
		return reserveDAO.selectTimeByDate(hashMap);
	}
}
