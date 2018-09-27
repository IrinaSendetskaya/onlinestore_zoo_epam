package by.htp.onlinestore.service.impl;

import java.util.List;

import by.htp.onlinestore.dao.MeasureDao;
import by.htp.onlinestore.entity.Measure;
import by.htp.onlinestore.service.MeasureService;

/**
 * Class provides methods for working with Measures table.
 * @author Iryna Siandzetskaya
 *
 */
public class MeasureServiceImpl implements MeasureService {
	
	/**
	 * Declares a object
	 */
	private MeasureDao measureDao;

	/**
	 * constructor without parameter
	 */
	public MeasureServiceImpl() {

	}
	

	/**
	 * getters and setters
	 * @return instance
	 */
	public MeasureDao getMeasureDao() {
		return measureDao;
	}



	public void setMeasureDao(MeasureDao measureDao) {
		this.measureDao = measureDao;
	}



	/* (non-Javadoc)
	 * @see by.htp.onlinestore.service.MeasureService#getMeasureList()
	 */
	@Override
	public List<Measure> getMeasureList() {

		return measureDao.readAll();
	}

}
