package by.htp.onlinestore.service.impl;

import java.util.List;

import by.htp.onlinestore.dao.SpecificationGoodDao;
import by.htp.onlinestore.entity.SpecificationGood;
import by.htp.onlinestore.service.SpecificationGoodService;

public class SpecificationGoodServiceImpl implements SpecificationGoodService {
	
	private SpecificationGoodDao specificationGoodDao;

	public SpecificationGoodServiceImpl() {

	}
	

	public SpecificationGoodDao getSpecificationGoodDao() {
		return specificationGoodDao;
	}



	public void setSpecificationGoodDao(SpecificationGoodDao specificationGoodDao) {
		this.specificationGoodDao = specificationGoodDao;
	}



	@Override
	public List<SpecificationGood> getSpecificationGoodList() {

		return specificationGoodDao.readAll();
	}

}
