package by.htp.onlinestore.service.impl;

import java.util.List;

import by.htp.onlinestore.dao.BuyerDao;
import by.htp.onlinestore.entity.Buyer;
import by.htp.onlinestore.service.BuyerService;

/**
 * Class provides methods for working with Buyers table.
 * @author Iryna Siandzetskaya
 *
 */
public class BuyerServiceImpl implements BuyerService {
	
	/**
	 * Declares a object
	 */
	private BuyerDao buyerDao;

	/**
	 * constructor without parameter
	 */
	public BuyerServiceImpl() {

	}
	

	/**
	 * getters and setters
	 * @return instance
	 */
	public BuyerDao getBuyerDao() {
		return buyerDao;
	}



	public void setBuyerDao(BuyerDao buyerDao) {
		this.buyerDao = buyerDao;
	}



	/* (non-Javadoc)
	 * @see by.htp.onlinestore.service.BuyerService#getBuyerList()
	 */
	@Override
	public List<Buyer> getBuyerList() {

		return buyerDao.readAll();
	}


	/* (non-Javadoc)
	 * @see by.htp.onlinestore.service.BuyerService#getBuyerByLoginAndPassword(java.lang.String, java.lang.String)
	 */
	@Override
	public Buyer getBuyerByLoginAndPassword(final String login, final String password) {
		return buyerDao.read(login, password);
	}

}
