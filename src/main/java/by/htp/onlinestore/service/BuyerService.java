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
	Buyer getBuyerByLoginAndPassword(final String login, final String password);
}
