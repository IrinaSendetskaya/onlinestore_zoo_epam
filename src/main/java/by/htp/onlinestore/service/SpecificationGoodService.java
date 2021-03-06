package by.htp.onlinestore.service;

import java.util.List;

import by.htp.onlinestore.entity.SpecificationGood;

/**
 * Interface provides methods for working with SpecificationGoods table.
 * @author Iryna Siandzetskaya
 *
 */
public interface SpecificationGoodService {
	/**
	 * gets all specificationGoods
	 * @return list of specificationGoods
	 */
	List<SpecificationGood> getSpecificationGoodList();
	/**
	 * it gets a SpecificationGood from database
	 * @param id
	 * @return an entity
	 */
	SpecificationGood read(int id);
}
