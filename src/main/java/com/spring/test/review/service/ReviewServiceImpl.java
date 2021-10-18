package com.spring.test.review.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.test.review.vo.Criteria1;
import com.spring.test.review.dao.ReviewDAO;

@Service("reviewService")
public class ReviewServiceImpl implements ReviewService{
	@Autowired
	ReviewDAO reviewDAO;
	
	@Override
	public List list(Criteria1 cri, int member_NO) throws Exception {
		System.out.println("review Service : list start");
		List list = reviewDAO.List(cri, member_NO);
		System.out.println("review Service : list end");
		return list;
	}
	@Override
	public int listCount(int member_NO) throws Exception {
		// TODO Auto-generated method stub
		return reviewDAO.listCount(member_NO);
	}
	@Override
	public int addNewReview(Map<String, Object> reviewMap) {
		// TODO Auto-generated method stub
		return reviewDAO.insertNewReview(reviewMap);
	}
	@Override
	public void removeReview(int review_NO) {
		reviewDAO.deleteReview(review_NO);
		
	}
	@Override
	public Object myPageReview(int member_NO) {
		List list = reviewDAO.myPageReview(member_NO);
		return list;
	}
	@Override
	public Object ShopReview(Object shop_NO) {
		List list = reviewDAO.ShopReview(shop_NO);
		return list;
	}
	@Override
	public Object shopReviewList(Criteria1 cri, int member_NO) {
		List list = reviewDAO.shopReviewList(cri, member_NO);
		System.out.println("review Service : list end");
		return list;
	}
	@Override
	public int reviewListCount(int member_NO) {
		// TODO Auto-generated method stub
		return reviewDAO.shoplistCount(member_NO);
	}
	@Override
	public Object myPageReserve(int member_NO) {
		List list = reviewDAO.myPageReserve(member_NO);
		System.out.println("mypageReserve service : "+list);
		return list;
	}
	@Override
	public Object visit(Criteria1 cri, int member_NO) {
		List list = reviewDAO.visit(cri, member_NO);
		System.out.println("review Service : list end");
		return list;
	}
	@Override
	public int visitCount(int member_NO) {
		// TODO Auto-generated method stub
		return reviewDAO.visitCount(member_NO);
	}
}
