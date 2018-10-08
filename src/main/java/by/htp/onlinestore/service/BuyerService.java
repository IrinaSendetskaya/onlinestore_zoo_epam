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
	
	/**
	 * it check a login from database
	 * @param login
	 * @return msg
	 */
	String checkBuyerByLogin(String login);
	/**
	 * it check an email from database
	 * @param login
	 * @return msg
	 */
	String checkBuyerByEmail(String email);
	/**
	 * it check login and password from database
	 * @param login
	 * @param password
	 * @return msg
	 */
	String checkBuyerByLoginAndPassword(String login, String password);
	/**
	 * it check password from database
	 * @param login
	 * @param password
	 * @return msg
	 */
	String checkBuyerByPassword(String password);
}
