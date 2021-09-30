package com.spring.test.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import com.spring.test.board.dao.BoardDAO;
import com.spring.test.board.service.BoardService;
import com.spring.test.board.vo.BoardVO;

@Service("boardService")
@Transactional(propagation = Propagation.REQUIRED)
public class BoardServiceImpl implements BoardService{
	@Autowired
	BoardDAO boardDAO;
	
	@Override
	public List<BoardVO> listArticles(String board_code) throws Exception {
		List<BoardVO> articlesList = boardDAO.selectAllArticlesList(board_code);
		return articlesList;
	}
	 @Override
		public int addNewArticle(Map articleMap) throws Exception {
		
			return boardDAO.insertNewArticle(articleMap);
		}
	 @Override
		public BoardVO viewArticle(int board_NO) throws Exception {
			BoardVO boardVO = boardDAO.selectArticle(board_NO);
			return boardVO;
		}
	 @Override
		public void removeArticle(int board_NO) throws Exception {
			boardDAO.deleteArticle(board_NO);
		}
	 @Override
		public void modArticle(Map articleMap) throws Exception {
		 System.out.println("service : modArticleStart");
			boardDAO.updateArticle(articleMap);
		}
}
