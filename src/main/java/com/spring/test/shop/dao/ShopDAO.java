package com.spring.test.shop.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface ShopDAO {
	public List selectAllShopList() throws DataAccessException;
}
