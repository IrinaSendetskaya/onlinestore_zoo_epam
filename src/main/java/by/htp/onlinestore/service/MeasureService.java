package by.htp.onlinestore.service;

import java.util.List;

import by.htp.onlinestore.entity.Measure;

/**
 * Interface provides methods for working with Measures table.
 * @author Iryna Siandzetskaya
 *
 */
public interface MeasureService {

	/**
	 * gets all measures
	 * @return list of measures
	 */
	List<Measure> getMeasureList();
}
