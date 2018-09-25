package by.htp.onlinestore.dao;

import java.util.List;

import by.htp.onlinestore.entity.Good;
import by.htp.onlinestore.entity.GoodListForJsp;
import by.htp.onlinestore.entity.Image;
import by.htp.onlinestore.entity.SpecificationGood;

public interface GoodDao extends BaseDao<Good> {

	List<Good> searchGoods(String searchInput);
	List<GoodListForJsp> searchGoodsWithPages(String searchInput,int beginGood,int endGood);
	List<GoodListForJsp> findAllGoodsJoinTables();
	List<GoodListForJsp> findAllGoodsJoinTablesWithPages(int beginGood,int endGood);
	
	void createNewGood(Good good, SpecificationGood specificationGood, Image image, int measureId,int sectionId);
}
