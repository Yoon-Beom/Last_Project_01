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
		return sqlSession.selectList("mapper.shop.selectAllShopList");
	}
	
	@Override
	public ShopVO selectShopAndDetailByShopNO(int shop_NO) throws DataAccessException {
		System.out.println("ShopDAOImpl : selectShopAndDetailByShopNO start");
		return sqlSession.selectOne("mapper.shop.selectShopAndDetailByShopNO", shop_NO); 		
	}
	
	@Override
	public ShopVO selectShopByMemberNO(int shop_NO) throws DataAccessException {
		System.out.println("ShopDAOImpl : selectShopByMemberNO start");
		return sqlSession.selectOne("mapper.shop.selectShopByMemberNO", shop_NO); 		
	}
	
	@Override
	public ShopVO selectShopAndDetailByMemberNO(int member_NO) throws DataAccessException {
		System.out.println("ShopDAOImpl : selectShopAndDetailByMemberNO start");
		ShopVO shopVO = sqlSession.selectOne("mapper.shop.selectShopAndDetailByMemberNO", member_NO);
		System.out.println("ShopDAOImpl : selectShopAndDetailByMemberNO end");
		return shopVO;
	}
	
	@Override
	public ShopVO selectShopByShopNO(int member_NO) throws DataAccessException {
		System.out.println("ShopDAOImpl : selectShopByShopNO start");
		return sqlSession.selectOne("mapper.shop.selectShopByShopNO", member_NO);
	}
	
	@Override
	public int insertShop(ShopVO shop) throws DataAccessException {
		System.out.println("ShopDAOImpl : insertShop start");
		return sqlSession.insert("mapper.shop.insertShop", shop);
	}
	
	@Override
	public int insertShopDetail(ShopDetailVO shopDetail) throws DataAccessException {
		System.out.println("ShopDAOImpl : insertShopDetail start");
		return sqlSession.insert("mapper.shop.insertShopDetail", shopDetail);
	}

	@Override
	public int selectShop_NO(int member_NO) {
		System.out.println("ShopDAOImpl : selectShop_NO start");
		return sqlSession.selectOne("mapper.shop.selectShop_NO", member_NO);
	}

}
