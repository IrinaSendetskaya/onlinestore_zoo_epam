package by.htp.onlinestore.service.impl;

import java.util.List;

import by.htp.onlinestore.dao.DAOFactory;
import by.htp.onlinestore.dao.SpecificationGoodDao;
import by.htp.onlinestore.entity.SpecificationGood;
import by.htp.onlinestore.service.SpecificationGoodService;

/**
 * Class provides methods for working with SpecificationGoods table.
 * @author Iryna Siandzetskaya
 *
 */
public class SpecificationGoodServiceImpl implements SpecificationGoodService {
	
	/**
	 * Declares a object and initialize
	 */
	private SpecificationGoodDao specificationGoodDao=DAOFactory.getDao().getSpecificationGoodDAO();

	/**
	 * constructor without parameter
	 */
	public SpecificationGoodServiceImpl() {

	}
	

	/**
	 * getters and setters
	 * @return instance
	 */
	public SpecificationGoodDao getSpecificationGoodDao() {
		return specificationGoodDao;
	}



	public void setSpecificationGoodDao(SpecificationGoodDao specificationGoodDao) {
		this.specificationGoodDao = specificationGoodDao;
	}



	/* (non-Javadoc)
	 * @see by.htp.onlinestore.service.SpecificationGoodService#getSpecificationGoodList()
	 */
	@Override
	public List<SpecificationGood> getSpecificationGoodList() {

		return specificationGoodDao.readAll();
	}


	/* (non-Javadoc)
	 * @see by.htp.onlinestore.service.SpecificationGoodService#read(int)
	 */
	@Override
	public SpecificationGood read(int id) {

		return specificationGoodDao.read(id);
	}

}
