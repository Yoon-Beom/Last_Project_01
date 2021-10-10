package com.spring.test.pet.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.spring.test.pet.vo.PetVO;

public interface PetService {

	public List<PetVO> listPet(int member_NO) throws Exception;
	public int addPet(Map petMap) throws Exception;
	public PetVO viewPet(int pet_NO) throws Exception;
	public void updatePet(Map<String, Object> articleMap) throws Exception;
	public int removePet(int pet_NO) throws DataAccessException;

}
