package by.htp.onlinestore.service;

import java.util.List;

import by.htp.onlinestore.entity.Buyer;

/**
 * Interface provides methods for working with Buyer table.
 * @author Iryna Siandzetskaya
 *
 */
public interface BuyerService {

	/**
	 * gets all buyers
	 * @return list of buyers
	 */
	List<Buyer> getBuyerList();
	/**
	 * gets a desired buyer by login and password
	 * @param login
	 * @param password
	 * @return a desired buyer
	 */
	Buyer getBuyerByLoginAndPassword(String login, String password);
	/**
	 * it creates a new Buyer in database
	 *
	 * @param entity
	 */
	void create(Buyer entity);
	/**
	 * it updates a Buyer in database
	 * @param entity
	 */
	void update(Buyer entity);

	/**
	 * it deletes a Buyer from database
	 * @param entity
	 */
	void delete(Buyer entity);
}
