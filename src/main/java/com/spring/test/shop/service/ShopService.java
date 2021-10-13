package com.spring.test.shop.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.spring.test.shop.vo.ShopDetailVO;
import com.spring.test.shop.vo.ShopVO;

public interface ShopService {
	
	public List listMembers() throws DataAccessException;
	public int insertShop(ShopVO shop) throws DataAccessException;
	public int insertShopDetail(ShopDetailVO shopD) throws DataAccessException;
	public int selectShop_No(int member_NO);
}
