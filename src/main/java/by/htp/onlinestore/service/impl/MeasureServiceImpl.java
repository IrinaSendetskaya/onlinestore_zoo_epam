package by.htp.onlinestore.service.impl;

import java.util.List;

import by.htp.onlinestore.dao.MeasureDao;
import by.htp.onlinestore.entity.Measure;
import by.htp.onlinestore.service.MeasureService;

public class MeasureServiceImpl implements MeasureService {
	
	private MeasureDao measureDao;

	public MeasureServiceImpl() {

	}
	

	public MeasureDao getMeasureDao() {
		return measureDao;
	}



	public void setMeasureDao(MeasureDao measureDao) {
		this.measureDao = measureDao;
	}



	@Override
	public List<Measure> getMeasureList() {

		return measureDao.readAll();
	}

}
