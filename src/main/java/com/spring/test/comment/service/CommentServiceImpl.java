package com.spring.test.comment.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spring.test.comment.dao.CommentDAO;
import com.spring.test.comment.vo.CommentVO;


@Service("commentService")
@Transactional(propagation = Propagation.REQUIRED)
public class CommentServiceImpl implements CommentService{
	@Autowired
	CommentDAO commentDAO;
	
	@Override
	public List<CommentVO> listArticles(int board_NO) throws Exception {
		System.out.println("commentService START");
		List<CommentVO> commentList = commentDAO.selectAllArticlesList(board_NO);
		return commentList;
	}
	 @Override
		public int addComment(Map commentMap) throws Exception {
		
			return commentDAO.insertNewComment(commentMap);
		}
	 @Override
		public CommentVO viewArticle(int board_NO) throws Exception {
			CommentVO commentVO = commentDAO.selectArticle(board_NO);
			return commentVO;
		}
	 @Override
		public void removeComment(Object comment_NO) throws Exception {
			commentDAO.deleteComment(comment_NO);
		}
	
}
