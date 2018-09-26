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
	Buyer read(final String login, final String password);
}
