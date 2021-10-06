package com.spring.test.shop.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.spring.test.shop.vo.ShopVO;

@Repository("shopDAO")
public class ShopDAOImpl implements ShopDAO{
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List selectAllShopList() throws DataAccessException {
		System.out.println("ShopDAOImpl : selectAllShopList start");
		
		List<ShopVO> shopList = null;
		shopList = sqlSession.selectList("mapper.shop.selectAllShopList");
		
		System.out.println("ShopDAOImpl : selectAllShopList end");
		return shopList;
	}
	
	@Override
	public int insertShop(ShopVO shop) throws DataAccessException {
		System.out.println("ShopDAOImpl : insertShop start");
		
		int result = sqlSession.insert("mapper.shop.insertShop", shop);
		
		System.out.println("ShopDAOImpl : insertShop start");
		return result;
	}
}
