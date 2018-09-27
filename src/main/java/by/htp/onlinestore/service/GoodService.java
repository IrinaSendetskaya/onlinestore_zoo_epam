package by.htp.onlinestore.service;

import java.util.List;

import by.htp.onlinestore.entity.Good;
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

}
