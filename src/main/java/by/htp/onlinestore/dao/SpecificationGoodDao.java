package by.htp.onlinestore.dao;

import java.util.List;

import by.htp.onlinestore.entity.SpecificationGood;

/**
 * Interface provides specific methods for access to data in SpecificationGoods table.
 * @author Iryna Siandzetskaya
 *
 */
public interface SpecificationGoodDao extends BaseDao<SpecificationGood> {

	/**
	 * it gets list of searching goods by some criteria
	 * @param searchInput
	 * @return list of specificationGoods
	 */
	List<SpecificationGood> searchGoods(String searchInput);
}
