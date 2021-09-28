package com.spring.test.board.service;

import java.util.List;
import java.util.Map;


import com.spring.test.board.vo.BoardVO;



public interface BoardService {
	public List<BoardVO> listArticles(String board_code) throws Exception;
	public int addNewArticle(Map articleMap) throws Exception;
	public BoardVO viewArticle(int board_NO) throws Exception;
}
