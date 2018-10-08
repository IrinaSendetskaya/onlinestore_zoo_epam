package by.htp.onlinestore.dao;

import by.htp.onlinestore.entity.Buyer;

/**
 * Interface provides specific methods for access to data in Buyers table.
 * @author Iryna Siandzetskaya
 *
 */
public interface BuyerDao extends BaseDao <Buyer>{

	/**
	 * it gets a buyer by validate login and password
	 * @param login
	 * @param password
	 * @return entity buyer
	 */
	Buyer read(String login, String password);
	/**
	 * it gets a buyer by validate login
	 * @param login
	 * @return entity buyer
	 */
	Buyer readByLogin(String login);
	/**
	 * it gets a buyer by validate email
	 * @param email
	 * @return entity buyer
	 */
	Buyer readByEmail(String email);
	/**
	 * it gets a buyer by validate password
	 * @param password
	 * @return entity buyer
	 */
	Buyer readByPassword(String password);
}
