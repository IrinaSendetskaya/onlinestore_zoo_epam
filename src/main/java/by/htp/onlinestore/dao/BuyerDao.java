package by.htp.onlinestore.dao;

import by.htp.onlinestore.entity.Buyer;

public interface BuyerDao extends BaseDao <Buyer>{

	Buyer read(final String login, final String password);
}
