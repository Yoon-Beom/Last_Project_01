package com.spring.test.review.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.spring.test.review.vo.Criteria1;

@Repository("reviewDAO")
public class ReviewDAOImpl implements ReviewDAO{
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List List(Criteria1 cri, int member_NO) throws DataAccessException {
		System.out.println("review DAO : list start");
		System.out.println("dao code : "+member_NO);
		cri.setMember_NO(member_NO);	
		List list = sqlSession.selectList("mapper.review.ReviewPage",cri);
		System.out.println("review DAO : list end");


		return list;
	}

	@Override
	public int listCount(int member_NO) throws DataAccessException {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("mapper.review.ReviewCount", member_NO);
	}

	@Override
	public int insertNewReview(Map<String, Object> reviewMap) {
		System.out.println("insertNewArticlestart");

		System.out.println("ReviewDAO : "+reviewMap);

		sqlSession.insert("mapper.review.insertNewReview", reviewMap);
		sqlSession.update("mapper.review.starScore", reviewMap);
		int Review_NO = selectNewReviewNO();
		return Review_NO;
	}
	private int selectNewReviewNO() throws DataAccessException {
		return sqlSession.selectOne("mapper.review.selectNewReviewNO");
	}

	@Override
	public void deleteReview(int review_NO) {
		sqlSession.delete("mapper.review.deleteReview",review_NO);

	}

	@Override
	public List myPageReview(int member_NO) {
		List list = sqlSession.selectList("mapper.review.mypageReview",member_NO);
		return list;
	}

}
