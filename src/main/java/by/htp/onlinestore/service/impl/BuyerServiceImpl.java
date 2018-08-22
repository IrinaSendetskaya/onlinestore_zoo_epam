package by.htp.onlinestore.service.impl;

import java.util.List;

import by.htp.onlinestore.dao.BuyerDao;
import by.htp.onlinestore.entity.Buyer;
import by.htp.onlinestore.service.BuyerService;

public class BuyerServiceImpl implements BuyerService {
	
	private BuyerDao buyerDao;

	public BuyerServiceImpl() {

	}
	

	public BuyerDao getBuyerDao() {
		return buyerDao;
	}



	public void setBuyerDao(BuyerDao buyerDao) {
		this.buyerDao = buyerDao;
	}



	@Override
	public List<Buyer> getBuyerList() {

		return buyerDao.readAll();
	}


	@Override
	public Buyer getBuyerByLoginAndPassword(final String login, final String password) {
		return buyerDao.read(login, password);
	}

}
