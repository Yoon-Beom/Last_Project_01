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
import com.spring.test.board.vo.Criteria;

@Service("boardService")
@Transactional(propagation = Propagation.REQUIRED)
public class BoardServiceImpl implements BoardService{
	@Autowired
	BoardDAO boardDAO;
	
/*	@Override
	public List<BoardVO> listArticles(String board_code) throws Exception {
		List<BoardVO> articlesList = boardDAO.selectAllArticlesList(board_code);
		return articlesList;
	}*/
	
	@Override
	public List list(Criteria cri, String board_code) throws Exception {
		List list = boardDAO.List(cri, board_code);
		return list;
	}
	
	 @Override
		public int addNewBoard(Map articleMap) throws Exception {
		
			return boardDAO.insertNewBoard(articleMap);
		}
	 @Override
		public BoardVO viewBoard(int board_NO) throws Exception {
			BoardVO boardVO = boardDAO.selectBoard(board_NO);
			return boardVO;
		}
	 @Override
		public void removeBoard(int board_NO) throws Exception {
			boardDAO.deleteBoard(board_NO);
		}
	 @Override
		public void modBoard(Map articleMap) throws Exception {
		 System.out.println("service : modArticleStart");
			boardDAO.updateBoard(articleMap);
		}
	
	@Override
		public List listsearch(Map<String, Object> searchMap, Criteria cri) {
			List searchList = boardDAO.selectAllsearchList(searchMap,cri);
			return searchList;
		}

	@Override
	public int listCount(String board_code) throws Exception {
		// TODO Auto-generated method stub
		return boardDAO.listCount(board_code);
	}
	

	@Override
	public int searchCount(Map<String, Object> searchMap) {
		// TODO Auto-generated method stub
		return boardDAO.searchCount(searchMap);
	}
	
}
