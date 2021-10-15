package com.spring.test.shop.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.spring.test.shop.vo.ShopDetailVO;
import com.spring.test.shop.vo.ShopVO;

public interface ShopDAO {
	public List selectAllShopList() throws DataAccessException;
	public int insertShop(ShopVO shop) throws DataAccessException;
	public int insertShopDetail(ShopDetailVO shopD) throws DataAccessException;
	public int selectShop_NO(int member_NO);
	public Map selectShopList(int member_NO) throws DataAccessException;
	public ShopVO selectShopView(int shop_NO) throws DataAccessException;
}
