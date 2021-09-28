package com.spring.test.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spring.test.shop.dao.ShopDAO;

@Service("shopService")
@Transactional(propagation = Propagation.REQUIRED)
public class ShopServiceImpl implements ShopService{
	
	@Autowired
	private ShopDAO shopDAO;

	@Override
	public List listMembers() throws DataAccessException {
		List shopList = null;
		shopList = shopDAO.selectAllShopList();
		return shopList;
	
	}

}
