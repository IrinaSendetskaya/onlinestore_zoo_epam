package by.htp.onlinestore.service.impl;

import java.util.List;

import by.htp.onlinestore.dao.GoodDao;
import by.htp.onlinestore.entity.Good;
import by.htp.onlinestore.entity.Image;
import by.htp.onlinestore.entity.SpecificationGood;
import by.htp.onlinestore.service.GoodService;

/**
 *Class provides methods for working with Goods table.
 * @author Iryna Siandzetskaya
 *
 */
public class GoodServiceImpl implements GoodService {
	
	/**
	 * Declares a object
	 */
	private GoodDao goodDao;

	/**
	 * constructor without parameter
	 */
	public GoodServiceImpl() {

	}

	
	/**
	 * getters and setters
	 * @return instance
	 */
	public GoodDao getGoodDao() {
		return goodDao;
	}


	public void setGoodDao(GoodDao goodDao) {
		this.goodDao = goodDao;
	}


	/* (non-Javadoc)
	 * @see by.htp.onlinestore.service.GoodService#getGoodList()
	 */
	@Override
	public List<Good> getGoodList() {

		return goodDao.readAll();
	}


	/* (non-Javadoc)
	 * @see by.htp.onlinestore.service.GoodService#createNewGood(by.htp.onlinestore.entity.Good, by.htp.onlinestore.entity.SpecificationGood, by.htp.onlinestore.entity.Image, int, int)
	 */
	@Override
	public void createNewGood(Good good, SpecificationGood specificationGood, Image image, int measureId,
			int sectionId) {
		goodDao.createNewGood(good, specificationGood, image, measureId, sectionId);
		
	}

}
