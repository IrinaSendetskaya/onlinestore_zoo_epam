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

	/**
	 * Gets all baskets from database
	 * @param buyerId
	 * @return list of baskets
	 */
	List<Basket> getAll(int buyerId);
	/**
	 * it creates a new entity in database
	 *
	 * @param entity
	 */
	void create(Basket entity);
	/**
	 * it updates an entity in database
	 * @param entity
	 */
	void update(Basket entity);

	/**
	 * it deletes an entity from database
	 * @param entity
	 */
	void delete(Basket entity);
	/**
	 * Gets all baskets from database with pagination
	 * @param buyerId
	 * @param beginGood
	 * @param endGood
	 * @return list of baskets
	 */
	List<Basket> findAllBasketsWithPages(int buyerId, int beginGood,int endGood);
}
