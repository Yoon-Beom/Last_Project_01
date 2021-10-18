package com.spring.test.review.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.spring.test.review.vo.Criteria1;

public interface ReviewDAO {
	public List List(Criteria1 cri, int member_NO) throws DataAccessException;
	public int listCount(int member_NO) throws DataAccessException;
	public int insertNewReview(Map<String, Object> reviewMap);
	public void deleteReview(int review_NO);
	public List myPageReview(int member_NO);
	public List ShopReview(Object shop_NO);
	public List shopReviewList(Criteria1 cri, int member_NO);
	public int shoplistCount(int member_NO);
	public java.util.List myPageReserve(int member_NO);
	public java.util.List visit(Criteria1 cri, int member_NO);
	public int visitCount(int member_NO);
}
