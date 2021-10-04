package com.spring.test.pet.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spring.test.pet.dao.PetDAO;
import com.spring.test.pet.vo.PetVO;

@Service("petService")
@Transactional(propagation = Propagation.REQUIRED)
public class PetServiceImpl implements PetService {

	@Autowired
	PetDAO petDAO;

	@Override
	public List<PetVO> listPet(int member_NO) throws Exception {
		List petList = null;
		petList = petDAO.selectPetList(member_NO);
		return petList;
	}

	@Override
	public int addPet(Map articleMap) throws Exception {
		// TODO Auto-generated method stub
		return petDAO.insertPet(articleMap);
	}

	@Override
	public PetVO viewPet(int pet_NO) throws Exception {
		PetVO petVO = petDAO.selectPet(pet_NO);
		return petVO;
	}


}
