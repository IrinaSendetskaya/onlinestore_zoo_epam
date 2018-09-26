package by.htp.onlinestore.dao;

import java.util.List;

import by.htp.onlinestore.entity.Good;
import by.htp.onlinestore.entity.GoodListForJsp;
import by.htp.onlinestore.entity.Image;
import by.htp.onlinestore.entity.SpecificationGood;

/**
 * Interface provides specific methods for access to data in Goods table.
 * @author Iryna Siandzetskaya
 *
 */
public interface GoodDao extends BaseDao<Good> {

	/**
	 * it gets list of searching goods by some criteria
	 * @param searchInput
	 * @return list of goods
	 */
	List<Good> searchGoods(String searchInput);
	/**
	 * it gets list of searching goods by some criteria with pagination
	 * @param searchInput
	 * @param beginGood
	 * @param endGood
	 * @return list of goods 
	 */
	List<GoodListForJsp> searchGoodsWithPages(String searchInput,int beginGood,int endGood);
	/**
	 * it gets list of goods from several tables
	 * @return list of goods
	 */
	List<GoodListForJsp> findAllGoodsJoinTables();
	/**
	 * it gets list of goods from several tables with pagination
	 * @param beginGood
	 * @param endGood
	 * @return list of goods
	 */
	List<GoodListForJsp> findAllGoodsJoinTablesWithPages(int beginGood,int endGood);
	
	/**
	 * it creates several entity in a transaction
	 * @param good
	 * @param specificationGood
	 * @param image
	 * @param measureId
	 * @param sectionId
	 */
	void createNewGood(Good good, SpecificationGood specificationGood, Image image, int measureId,int sectionId);
}
