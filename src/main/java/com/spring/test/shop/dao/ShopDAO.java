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
	public ShopVO selectShopAndDetailByMemberNO(int member_NO) throws DataAccessException;
	public ShopVO selectShopByMemberNO(int shop_NO) throws DataAccessException;
	public ShopVO selectShopAndDetailByShopNO(int shop_NO) throws DataAccessException;
	public ShopVO selectShopByShopNO(int member_NO) throws DataAccessException;
}
