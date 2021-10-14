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
}
