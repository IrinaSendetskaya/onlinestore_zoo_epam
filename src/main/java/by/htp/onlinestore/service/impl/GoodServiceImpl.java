package by.htp.onlinestore.service.impl;

import java.util.List;

import by.htp.onlinestore.dao.DAOFactory;
import by.htp.onlinestore.dao.GoodDao;
import by.htp.onlinestore.entity.Good;
import by.htp.onlinestore.entity.GoodListForJsp;
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
	 * Declares a object and initialize
	 */
	private GoodDao goodDao=DAOFactory.getDao().getGoodDAO();

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


	/* (non-Javadoc)
	 * @see by.htp.onlinestore.service.GoodService#findAllGoodsJoinTables()
	 */
	@Override
	public List<GoodListForJsp> findAllGoodsJoinTables() {		
		return goodDao.findAllGoodsJoinTables();
	}


	/* (non-Javadoc)
	 * @see by.htp.onlinestore.service.GoodService#findAllGoodsJoinTablesWithPages(int, int)
	 */
	@Override
	public List<GoodListForJsp> findAllGoodsJoinTablesWithPages(int startGood, int endGood) {
		return goodDao.findAllGoodsJoinTablesWithPages(startGood, endGood);
	}


	/* (non-Javadoc)
	 * @see by.htp.onlinestore.service.GoodService#searchGoods(java.lang.String)
	 */
	@Override
	public List<Good> searchGoods(String searchInput) {

		return goodDao.searchGoods(searchInput);
	}


	/* (non-Javadoc)
	 * @see by.htp.onlinestore.service.GoodService#searchGoodsWithPages(java.lang.String, int, int)
	 */
	@Override
	public List<GoodListForJsp> searchGoodsWithPages(String searchInput, int beginGood, int endGood) {

		return goodDao.searchGoodsWithPages(searchInput, beginGood, endGood);
	}


	/* (non-Javadoc)
	 * @see by.htp.onlinestore.service.GoodService#read(int)
	 */
	@Override
	public Good read(int id) {

		return goodDao.read(id);
	}

}
