package by.htp.onlinestore.service.impl;

import java.util.List;

import by.htp.onlinestore.dao.BasketDao;
import by.htp.onlinestore.dao.DAOFactory;
import by.htp.onlinestore.entity.Basket;
import by.htp.onlinestore.service.BasketService;

/**
 * Class provides methods for working with Baskets table.
 * @author Iryna Siandzetskaya
 *
 */
public class BasketServiceImpl implements BasketService {
	
	/**
	 * Declares a object and initialize
	 */
	private BasketDao basketDao=DAOFactory.getDao().getBasketDAO();

	/**
	 * constructor without parameter
	 */
	public BasketServiceImpl() {

	}

	/**
	 * getters and setters
	 * @return instance
	 */
	public BasketDao getBasketDao() {
		return basketDao;
	}

	public void setBasketDao(BasketDao basketDao) {
		this.basketDao = basketDao;
	}

	/* (non-Javadoc)
	 * @see by.htp.onlinestore.service.BasketService#getBasketList()
	 */
	@Override
	public List<Basket> getBasketList() {
		
		return basketDao.readAll();
	}

	/* (non-Javadoc)
	 * @see by.htp.onlinestore.service.BasketService#getAll(int)
	 */
	@Override
	public List<Basket> getAll(int buyerId) {
		
		return basketDao.getAll(buyerId);
	}

	/* (non-Javadoc)
	 * @see by.htp.onlinestore.service.BasketService#update(by.htp.onlinestore.entity.Basket)
	 */
	@Override
	public void update(Basket entity) {
		
		basketDao.update(entity);	
	}

	/* (non-Javadoc)
	 * @see by.htp.onlinestore.service.BasketService#delete(by.htp.onlinestore.entity.Basket)
	 */
	@Override
	public void delete(Basket entity) {
		
		basketDao.delete(entity);
	}

	/* (non-Javadoc)
	 * @see by.htp.onlinestore.service.BasketService#create(by.htp.onlinestore.entity.Basket)
	 */
	@Override
	public void create(Basket entity) {

		basketDao.create(entity);
	}

	/* (non-Javadoc)
	 * @see by.htp.onlinestore.service.BasketService#findAllBasketsWithPages(int, int, int)
	 */
	@Override
	public List<Basket> findAllBasketsWithPages(int buyerId, int beginGood, int endGood) {

		return basketDao.findAllBasketsWithPages(buyerId, beginGood, endGood);
	}

}
