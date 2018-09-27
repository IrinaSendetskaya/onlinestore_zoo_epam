package by.htp.onlinestore.service;

import java.util.List;

import by.htp.onlinestore.entity.Basket;

/**
 * Interface provides methods for working with Baskets table.
 * @author Iryna Siandzetskaya
 *
 */
public interface BasketService {

	/**
	 * gets all baskets
	 * @return list of baskets
	 */
	List <Basket> getBasketList();
	//void orderGood (int buyerId, int goodId);
}
