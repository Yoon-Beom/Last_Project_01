package com.spring.test.board.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.test.board.vo.BoardVO;
import com.spring.test.board.vo.Criteria;

public interface BoardDAO {
	public List List(Criteria cri, String board_code)throws DataAccessException;
	//public List selectAllArticlesList(String board_code)throws DataAccessException;
	public int insertNewArticle(Map articleMap) throws DataAccessException;
	public BoardVO selectArticle(int board_NO) throws DataAccessException;
	public void deleteArticle(int board_NO) throws DataAccessException;
	public void updateArticle(Map articleMap) throws DataAccessException;
	List selectAllsearchList(Map<String, Object> searchMap,Criteria cri);
	public int listCount(String board_code) throws DataAccessException;
	public int searchCount(Map<String, Object> searchMap);
}
