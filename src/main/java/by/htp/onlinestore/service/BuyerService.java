package by.htp.onlinestore.service;

import java.util.List;

import by.htp.onlinestore.entity.Buyer;

public interface BuyerService {

	List<Buyer> getBuyerList();
	Buyer getBuyerByLoginAndPassword(final String login, final String password);
}
