package com.spring.test.board.service;

import java.util.List;
import java.util.Map;


import com.spring.test.board.vo.BoardVO;
import com.spring.test.board.vo.Criteria;



public interface BoardService {
	//public List<BoardVO> listArticles(String board_code) throws Exception;
	public List list(Criteria cri, String board_code) throws Exception;
	public int addNewBoard(Map articleMap) throws Exception;
	public BoardVO viewBoard(int board_NO) throws Exception;
	public void removeBoard(int board_NO) throws Exception;
	public void modBoard(Map articleMap) throws Exception;
	List listsearch(Map<String, Object> searchMap, Criteria cri);
	public int listCount(String board_code) throws Exception;
	public int searchCount(Map<String, Object> searchMap);
}
