package by.htp.onlinestore.dao;

import java.util.List;

import by.htp.onlinestore.entity.SpecificationGood;

public interface SpecificationGoodDao extends BaseDao<SpecificationGood> {

	List<SpecificationGood> searchGoods(String searchInput);
}
