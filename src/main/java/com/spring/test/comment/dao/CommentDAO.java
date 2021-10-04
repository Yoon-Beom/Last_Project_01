package com.spring.test.comment.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.spring.test.comment.vo.CommentVO;

public interface CommentDAO {
	public List selectAllArticlesList(int board_NO)throws DataAccessException;
	public int insertNewComment(Map articleMap) throws DataAccessException;
	public CommentVO selectArticle(int board_NO) throws DataAccessException;
	public void deleteComment(Object comment_NO) throws DataAccessException;
}
