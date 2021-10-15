package com.spring.test.shop.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.spring.test.shop.vo.ShopDetailVO;
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
	
	  @Override public ShopVO selectShopView(int shop_NO) throws
	  DataAccessException { return
	  sqlSession.selectOne("mapper.shop.selectShopView", shop_NO); }
	 

	@Override
	public Map<String, Object> selectShopList(int member_NO) throws DataAccessException {
		System.out.println("ShopDAOImpl : selectShopList start");

		Map<String, Object> shopMap = sqlSession.selectOne("mapper.shop.selectShopList", member_NO);
		System.out.println("shopDAO : "+shopMap);
		System.out.println("ShopDAOImpl : selectShopList end");
		return shopMap;
	}
	
	
	@Override
	public int insertShop(ShopVO shop) throws DataAccessException {
		System.out.println("ShopDAOImpl : insertShop start");
		
		int result = sqlSession.insert("mapper.shop.insertShop", shop);
		
		System.out.println("ShopDAOImpl : insertShop start");
		return result;
	}
	
	@Override
	public int insertShopDetail(ShopDetailVO shopDetail) throws DataAccessException {
		System.out.println("ShopDAOImpl : insertShopDetail start");
		
		int result = sqlSession.insert("mapper.shop.insertShopDetail", shopDetail);
		
		System.out.println("ShopDAOImpl : insertShopDetail end");
		return result;
	}

	@Override
	public int selectShop_NO(int member_NO) {
		int result = sqlSession.selectOne("mapper.shop.selectShop_NO", member_NO);
		return result;
	}

}
