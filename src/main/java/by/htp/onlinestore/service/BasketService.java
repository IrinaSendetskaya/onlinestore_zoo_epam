package by.htp.onlinestore.service;

import java.util.List;

import by.htp.onlinestore.entity.Basket;
import by.htp.onlinestore.entity.BasketListForJsp;

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
	/**
	 * Gets all baskets from database by status
	 * @param buyerId
	 * @param status
	 * @return list of baskets
	 */
	List<Basket> getAllByStatus(int buyerId, String status);
	/**
	 * Gets all baskets from database with pagination by status
	 * @param buyerId
	 * @param beginGood
	 * @param endGood
	 * @param status
	 * @return list of baskets
	 */
	List<Basket> findAllBasketsWithPagesByStatus(int buyerId, int beginGood,int endGood, String status);
	/**
	 *  Gets all baskets from database with pagination only for jsp page by status
	 * @param buyerId
	 * @param beginGood
	 * @param endGood
	 * @param status
	 * @return list of baskets only for jsp page
	 */
	List<BasketListForJsp> findAllBasketsJoinTablesWithPagesByStatus(int buyerId, int beginGood,int endGood, String status);
}
