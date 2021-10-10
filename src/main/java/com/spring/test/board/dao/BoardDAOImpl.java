package com.spring.test.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.test.board.dao.BoardDAO;
import com.spring.test.board.vo.BoardVO;
import com.spring.test.board.vo.Criteria;

@Repository("boardDAO")
public class BoardDAOImpl implements BoardDAO{
	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	BoardVO boardVO;
	
	@Override
	public List List(Criteria cri, String board_code) throws DataAccessException {
		
		System.out.println("dao code : "+board_code);

		 cri.setBoard_code(board_code);
		 List List = sqlSession.selectList("mapper.board.listPage",cri);
		 System.out.println(List);
		return List;
	}
	
/*	@Override
	public List selectAllArticlesList(String board_code) throws DataAccessException {
		
		System.out.println("dao code : "+board_code);
		List<BoardVO> articlesList = sqlSession.selectList("mapper.board.selectAllArticlesList",board_code);
		return articlesList;
	}*/
	
	@Override
	public int insertNewArticle(Map articleMap) throws DataAccessException {
		System.out.println("insertNewArticlestart");

		String board_code = (String) articleMap.get("board_code");
		String board_image = (String) articleMap.get("board_image");
		String board_name = (String) articleMap.get("board_name");
		
		String board_title = boardVO.getBoard_title();
		String board_content=boardVO.getBoard_content();
		System.out.println("board_name : "+board_name);
		System.out.println("board_title : "+board_title);
		System.out.println("board_content : "+board_content);
		System.out.println("board_image : "+board_image);
		System.out.println("board_code : "+board_code);
		
		sqlSession.insert("mapper.board.insertNewArticle",articleMap);
		int board_NO = selectNewBoardNO();
		return board_NO;
		
}
	
	@Override
	public BoardVO selectArticle(int board_NO) throws DataAccessException {
		return sqlSession.selectOne("mapper.board.selectArticle", board_NO);
	}
	
	private int selectNewBoardNO() throws DataAccessException {
		return sqlSession.selectOne("mapper.board.selectNewBoardNO");
	}
	@Override
	public void deleteArticle(int board_NO) throws DataAccessException {
		sqlSession.delete("mapper.comment.deleteBoard", board_NO);
		sqlSession.delete("mapper.board.deleteBoard", board_NO);
	
	}
	@Override
	public void updateArticle(Map articleMap) throws DataAccessException {
		System.out.println("DAO : updateArticle START");
		sqlSession.update("mapper.board.updateArticle", articleMap);
		System.out.println("DAO : updateArticle END");
	}

	@Override
	public List selectAllsearchList(Map<String, Object> searchMap, Criteria cri) {
		System.out.println("search : "+searchMap);
		String option = (String) searchMap.get("optionContent");
		List searchList=null;

		System.out.println(searchMap.get("cri"));
		System.out.println(searchMap.get("board_code"));
		searchMap.put("rowStart", cri.getRowStart());
		searchMap.put("rowEnd", cri.getRowEnd());
		System.out.println("DAO : "+cri);
		if(option.equals("board_name")) {
			searchList = sqlSession.selectList("mapper.board.selectAllsearchList",searchMap);
		}else if(option.equals("board_title")) {
			searchList = sqlSession.selectList("mapper.board.selectTitlesearchList",searchMap);
		}
		
		
		System.out.println("searchList : "+searchList);
		
		return searchList;
	}

	@Override
	public int listCount(String board_code) throws DataAccessException {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("mapper.board.BoardCount", board_code);
	}
	
	@Override
	public int searchCount(Map<String, Object> searchMap) {
		int searchCount = 0;
		
		String option = (String) searchMap.get("optionContent");
		if(option.equals("board_name")) {
			searchCount = sqlSession.selectOne("mapper.board.nameCount",searchMap);
		}else if(option.equals("board_title")) {
			searchCount = sqlSession.selectOne("mapper.board.titleCount",searchMap);
		}
		return searchCount;
	}
	
}
