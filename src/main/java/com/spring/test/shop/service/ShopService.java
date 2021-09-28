package com.spring.test.shop.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface ShopService {
	
	public List listMembers() throws DataAccessException;
}
