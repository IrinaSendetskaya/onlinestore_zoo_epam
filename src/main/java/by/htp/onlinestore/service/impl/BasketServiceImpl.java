package by.htp.onlinestore.service.impl;

import java.util.List;

import by.htp.onlinestore.dao.BasketDao;
import by.htp.onlinestore.entity.Basket;
import by.htp.onlinestore.service.BasketService;

public class BasketServiceImpl implements BasketService {
	
	private BasketDao basketDao;

	public BasketServiceImpl() {

	}

	public BasketDao getBasketDao() {
		return basketDao;
	}

	public void setBasketDao(BasketDao basketDao) {
		this.basketDao = basketDao;
	}

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
