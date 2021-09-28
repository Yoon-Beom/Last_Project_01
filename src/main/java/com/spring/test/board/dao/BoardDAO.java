package com.spring.test.board.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.test.board.vo.BoardVO;

public interface BoardDAO {
	public List selectAllArticlesList(String board_code)throws DataAccessException;
	public int insertNewArticle(Map articleMap) throws DataAccessException;
	public BoardVO selectArticle(int board_NO) throws DataAccessException;
}
