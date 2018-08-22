package by.htp.onlinestore.service;

import java.util.List;

import by.htp.onlinestore.entity.Basket;

public interface BasketService {

	List <Basket> getBasketList();
	//void orderGood (int buyerId, int goodId);
}
