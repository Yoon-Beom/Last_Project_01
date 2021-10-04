package com.spring.test.comment.service;

import java.util.List;
import java.util.Map;



import com.spring.test.comment.vo.CommentVO;



public interface CommentService {
	public List<CommentVO> listArticles(int board_NO) throws Exception;
	public int addComment(Map commentMap) throws Exception;
	public CommentVO viewArticle(int board_NO) throws Exception;
	public void removeComment(Object comment_NO) throws Exception;

}
