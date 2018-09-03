package by.htp.onlinestore.dao;

import java.util.List;

import by.htp.onlinestore.entity.Basket;
import by.htp.onlinestore.entity.BasketListForJsp;

public interface BasketDao extends BaseDao<Basket> {

	List<Basket> getAll(int buyerId);
	List<Basket> findAllBasketsWithPages(int buyerId, int beginGood,int endGood);
	List<BasketListForJsp> findAllBasketsJoinTablesWithPages(int buyerId, int beginGood,int endGood);
}
