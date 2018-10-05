package by.htp.onlinestore.dao;

import java.util.List;

import by.htp.onlinestore.entity.Basket;
import by.htp.onlinestore.entity.BasketListForJsp;

/**
 * Interface provides specific methods for access to data in Baskets table.
 * @author Iryna Siandzetskaya
 *
 */
public interface BasketDao extends BaseDao<Basket> {

	/**
	 * Gets all baskets from database
	 * @param buyerId
	 * @return list of baskets
	 */
	List<Basket> getAll(int buyerId);
	/**
	 * Gets all baskets from database with pagination
	 * @param buyerId
	 * @param beginGood
	 * @param endGood
	 * @return list of baskets
	 */
	List<Basket> findAllBasketsWithPages(int buyerId, int beginGood,int endGood);
	/**
	 *  Gets all baskets from database with pagination only for jsp page
	 * @param buyerId
	 * @param beginGood
	 * @param endGood
	 * @return list of baskets only for jsp page
	 */
	List<BasketListForJsp> findAllBasketsJoinTablesWithPages(int buyerId, int beginGood,int endGood);
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
