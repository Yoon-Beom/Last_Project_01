package com.spring.test.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spring.test.shop.dao.ShopDAO;
import com.spring.test.shop.vo.ShopDetailVO;
import com.spring.test.shop.vo.ShopVO;

@Service("shopService")
@Transactional(propagation = Propagation.REQUIRED)
public class ShopServiceImpl implements ShopService {

	@Autowired
	private ShopDAO shopDAO;

	@Override
	public List listShop() throws DataAccessException {
		System.out.println("ShopServiceImpl : listShop start");

		List shopList = null;
		shopList = shopDAO.selectAllShopList();

		System.out.println("ShopServiceImpl : listShop end");
		return shopList;
	}
	@Override
	public List listShop(int member_NO) throws DataAccessException {
		System.out.println("ShopServiceImpl : listShop start");
		
		List shopList = null;
		shopList = shopDAO.selectShopList(member_NO);
		
		System.out.println("ShopServiceImpl : listShop end");
		return shopList;
	}
	
	@Override
	public List listDShop(int member_NO) throws DataAccessException {
		System.out.println("ShopServiceImpl : listDShop start");

		List shopDList = null;
		shopDList = shopDAO.selectShopDList(member_NO);

		System.out.println("ShopServiceImpl : listDShop end");
		return shopDList;
	}

	/*
	 * @Override public List listShopAndDetail() {
	 * System.out.println("ShopServiceImpl : listShopAndDetail start");
	 * 
	 * List list = null; list = shopDAO.selectShopAndDetailList();
	 * 
	 * List shopAndDetailList = null; shopAndDetailList =
	 * shopDAO.selectShopAndDetailList();
	 * 
	 * 
	 * System.out.println("ShopServiceImpl : listShopAndDetail end"); return list; }
	 */

	@Override
	public int insertShop(ShopVO shop) throws DataAccessException {
		System.out.println("ShopServiceImpl : insertShop start");

		int result = shopDAO.insertShop(shop);

		System.out.println("ShopServiceImpl : insertShop end");
		return result;
	}

	@Override
	public int insertShopDetail(ShopDetailVO shopD) throws DataAccessException {
		System.out.println("ShopServiceImpl : insertShop start");

		int result = shopDAO.insertShopDetail(shopD);

		System.out.println("ShopServiceImpl : insertShop end");
		return result;
	}

	@Override
	public int selectShop_No(int member_NO) {
		// TODO Auto-generated method stub
		return shopDAO.selectShop_NO(member_NO);
	}

}
