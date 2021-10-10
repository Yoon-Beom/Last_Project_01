package com.spring.test.pet.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.spring.test.pet.vo.PetVO;

@Repository("petDAO")
public class PetDAOImpl implements PetDAO{
	

	@Autowired
	private SqlSession sqlSession;

	@Autowired
	PetVO petVO;

	
	@Override
	public List selectPetList(int member_NO) throws DataAccessException {
		List<PetVO> petList = null;
		System.out.println("member_NO :" + member_NO);
		petList = sqlSession.selectList("mapper.pet.selectPetList",member_NO);
		return petList;
	}

	@Override
	public int insertPet(Map articleMap) throws DataAccessException {
		String pet_image = (String) articleMap.get("pet_image");
		String member_id = (String)articleMap.get("member_id");
		String pet_name = petVO.getPet_name();
		String pet_scale = petVO.getPet_scale();
		sqlSession.insert("mapper.pet.insertPet", articleMap);
		int pet_NO = selectPetNO();
		return pet_NO;
	}

	@Override
	public PetVO selectPet(int pet_NO) throws DataAccessException {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("mapper.pet.selectPet", pet_NO);
	}

	private int selectPetNO() throws DataAccessException {
		return sqlSession.selectOne("mapper.pet.selectPetNO");
	}
	

	@Override
	public int updatePet(Map articleMap) throws DataAccessException {
		System.out.println("DAOImpl : updatePet : "+articleMap);
		sqlSession.update("mapper.pet.updatePet", articleMap);
		int pet_NO = selectPetNO();
		return pet_NO;
	}
	
	@Override
	public int deletePet(int pet_NO) throws DataAccessException {
		// TODO Auto-generated method stub
		int result = sqlSession.delete("mapper.pet.deletePet", pet_NO);
		return result;
	}
}
