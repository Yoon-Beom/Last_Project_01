package com.spring.test.review.service;

import java.util.List;
import java.util.Map;

import com.spring.test.review.vo.Criteria1;

public interface ReviewService {

	public List list(Criteria1 cri, int member_NO) throws Exception;
	public int listCount(int member_NO) throws Exception;
	public int addNewReview(Map<String, Object> reviewMap);
	public void removeReview(int review_NO);
	public Object myPageReview(int member_NO)throws Exception;
	public Object ShopReview(Object shop_NO);
	public Object shopReviewList(Criteria1 cri, int member_NO);
	public int reviewListCount(int member_NO);
}
