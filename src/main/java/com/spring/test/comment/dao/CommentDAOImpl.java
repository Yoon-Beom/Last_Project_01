package com.spring.test.comment.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.spring.test.comment.vo.CommentVO;

@Repository("commentDAO")
public class CommentDAOImpl implements CommentDAO{
	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	CommentVO commentVO;
	
	@Override
	public List selectAllArticlesList(int board_NO) throws DataAccessException {
		
		System.out.println("dao NO : "+board_NO);
		List<CommentVO> commentList = sqlSession.selectList("mapper.comment.selectAllArticlesList",board_NO);
		System.out.println("commentList : "+commentList);
		
		return commentList;
	}
	
	@Override
	public int insertNewComment(Map commentMap) throws DataAccessException {
		System.out.println("insertNewArticlestart");
		System.out.println("commentMap : "+commentMap);
		sqlSession.insert("mapper.comment.insertNewComment",commentMap);
		int comment_NO = selectNewArticleNO();
		System.out.println("articleMap : "+commentMap);
		
		return comment_NO;
		
}
	
	@Override
	public CommentVO selectArticle(int board_NO) throws DataAccessException {
		return sqlSession.selectOne("mapper.comment.selectArticle", board_NO);
	}
	
	private int selectNewArticleNO() throws DataAccessException {
		return sqlSession.selectOne("mapper.comment.selectNewCommentNO");
	}
	@Override
	public void deleteComment(Object comment_NO) throws DataAccessException {
		sqlSession.delete("mapper.comment.deleteComment", comment_NO);
		
	}
	
	
}
