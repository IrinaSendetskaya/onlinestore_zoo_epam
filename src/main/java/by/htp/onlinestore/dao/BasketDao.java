package by.htp.onlinestore.dao;

import java.util.List;

import by.htp.onlinestore.entity.Basket;

public interface BasketDao extends BaseDao<Basket> {

	List<Basket> getAll(int buyerId);
	List<Basket> findAllBasketsWithPages(int buyerId, int beginGood,int endGood);
}
