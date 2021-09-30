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

@Repository("boardDAO")
public class BoardDAOImpl implements BoardDAO{
	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	BoardVO boardVO;
	
	@Override
	public List selectAllArticlesList(String board_code) throws DataAccessException {
		
		System.out.println("dao code : "+board_code);
		List<BoardVO> articlesList = sqlSession.selectList("mapper.board.selectAllArticlesList",board_code);
		
		return articlesList;
	}
	
	@Override
	public int insertNewArticle(Map articleMap) throws DataAccessException {
		System.out.println("insertNewArticlestart");

		String board_code = (String) articleMap.get("board_code");
		String board_image = (String) articleMap.get("board_image");
		String board_name = (String) articleMap.get("board_name");
		int board_NO = selectNewArticleNO();
		String board_title = boardVO.getBoard_title();
		String board_content=boardVO.getBoard_content();
		System.out.println("board_name : "+board_name);
		System.out.println("board_title : "+board_title);
		System.out.println("board_content : "+board_content);
		System.out.println("board_image : "+board_image);
		System.out.println("board_code : "+board_code);
		
		sqlSession.insert("mapper.board.insertNewArticle",articleMap);
		System.out.println("insert articleMap : "+articleMap);
		
		return board_NO;
		
}
	
	@Override
	public BoardVO selectArticle(int board_NO) throws DataAccessException {
		return sqlSession.selectOne("mapper.board.selectArticle", board_NO);
	}
	
	private int selectNewArticleNO() throws DataAccessException {
		return sqlSession.selectOne("mapper.board.selectNewArticleNO");
	}
	@Override
	public void deleteArticle(int board_NO) throws DataAccessException {
		sqlSession.delete("mapper.board.deleteArticle", board_NO);
		
	}
	@Override
	public void updateArticle(Map articleMap) throws DataAccessException {
		System.out.println("DAO : updateArticle START");
		sqlSession.update("mapper.board.updateArticle", articleMap);
		System.out.println("DAO : updateArticle END");
	}
	
}
