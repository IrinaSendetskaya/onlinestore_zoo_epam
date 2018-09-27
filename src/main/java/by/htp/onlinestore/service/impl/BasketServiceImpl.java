package by.htp.onlinestore.service.impl;

import java.util.List;

import by.htp.onlinestore.dao.BasketDao;
import by.htp.onlinestore.entity.Basket;
import by.htp.onlinestore.service.BasketService;

/**
 * Class provides methods for working with Baskets table.
 * @author Iryna Siandzetskaya
 *
 */
public class BasketServiceImpl implements BasketService {
	
	/**
	 * Declares a object
	 */
	private BasketDao basketDao;

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

//	@Override
//	public void orderGood(int buyerId, int goodId) {
//		
//		basketDao.insertNewOrder(buyerId, goodId);
//	}

}
