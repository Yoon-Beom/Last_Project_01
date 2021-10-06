package com.spring.test.shop.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.spring.test.shop.vo.ShopVO;

public interface ShopDAO {
	public List selectAllShopList() throws DataAccessException;
	public int insertShop(ShopVO shop) throws DataAccessException;
}
