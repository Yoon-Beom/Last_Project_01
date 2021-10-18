package com.spring.test.shop.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.spring.test.shop.vo.ShopDetailVO;
import com.spring.test.shop.vo.ShopVO;

public interface ShopService {
	
	public ShopVO listShop(int member_NO) throws DataAccessException;

	public List listShop() throws DataAccessException; 
	public int insertShop(ShopVO shop) throws DataAccessException;
	public int insertShopDetail(ShopDetailVO shopD) throws DataAccessException;
	public int selectShop_No(int member_NO);
	public ShopVO selectShopByShopNO(int shop_NO) throws DataAccessException;
	public ShopVO ViewShop(int shop_NO) throws DataAccessException;
	public void updateShopDetail(Map<String, Object> articleMap) throws Exception;

	public int updateShop(ShopVO shopVO) throws DataAccessException;
	
}
