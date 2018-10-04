package by.htp.onlinestore.service;

import java.util.List;

import by.htp.onlinestore.entity.Good;
import by.htp.onlinestore.entity.GoodListForJsp;
import by.htp.onlinestore.entity.Image;
import by.htp.onlinestore.entity.SpecificationGood;

/**
 * Interface provides methods for working with Goods table.
 * @author Iryna Siandzetskaya
 *
 */
public interface GoodService {

	/**
	 * gets all goods
	 * @return list of goods
	 */
	List<Good> getGoodList();
	/**
	 * creates new good in several tables in a transaction
	 * @param good entity
	 * @param specificationGood entity
	 * @param image entity
	 * @param measureId
	 * @param sectionId
	 */
	void createNewGood(Good good, SpecificationGood specificationGood, Image image, int measureId,int sectionId);
	
	/**
	 * gets all goods by join tables
	 * @return list of goods
	 */
	List<GoodListForJsp> findAllGoodsJoinTables();
	
	/**
	 * gets all goods by join tables with pagination
	 * @param startGood
	 * @param endGood
	 * @return list of goods
	 */
	List<GoodListForJsp> findAllGoodsJoinTablesWithPages(int startGood, int endGood);
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
	 * it gets a Good from database
	 * @param id
	 * @return an entity
	 */
	Good read(int id);

}
