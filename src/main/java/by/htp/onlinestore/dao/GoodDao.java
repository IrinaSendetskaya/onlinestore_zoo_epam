package by.htp.onlinestore.dao;

import java.util.List;

import by.htp.onlinestore.entity.Good;

public interface GoodDao extends BaseDao<Good> {

	List<Good> searchGoods(int specGoodId);
}
