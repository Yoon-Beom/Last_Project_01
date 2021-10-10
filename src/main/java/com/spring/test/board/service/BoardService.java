package com.spring.test.board.service;

import java.util.List;
import java.util.Map;


import com.spring.test.board.vo.BoardVO;
import com.spring.test.board.vo.Criteria;



public interface BoardService {
	//public List<BoardVO> listArticles(String board_code) throws Exception;
	public List list(Criteria cri, String board_code) throws Exception;
	public int addNewArticle(Map articleMap) throws Exception;
	public BoardVO viewArticle(int board_NO) throws Exception;
	public void removeArticle(int board_NO) throws Exception;
	public void modArticle(Map articleMap) throws Exception;
	List listsearch(Map<String, Object> searchMap, Criteria cri);
	public int listCount(String board_code) throws Exception;
	public int searchCount(Map<String, Object> searchMap);
}
