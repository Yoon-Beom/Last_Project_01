package com.spring.test.pet.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.spring.test.pet.vo.PetVO;

public interface PetDAO {

	public List selectPetList(int member_NO) throws DataAccessException;
	public int insertPet(Map aticleMap) throws DataAccessException;
	public PetVO selectPet(int pet_NO) throws DataAccessException;
	public int updatePet(Map<String, Object> articleMap) throws DataAccessException;

}
