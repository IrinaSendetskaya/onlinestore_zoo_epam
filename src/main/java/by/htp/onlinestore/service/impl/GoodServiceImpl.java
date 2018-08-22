package by.htp.onlinestore.service.impl;

import java.util.List;

import by.htp.onlinestore.dao.GoodDao;
import by.htp.onlinestore.entity.Good;
import by.htp.onlinestore.service.GoodService;

public class GoodServiceImpl implements GoodService {
	
	private GoodDao goodDao;

	public GoodServiceImpl() {

	}

	
	public GoodDao getGoodDao() {
		return goodDao;
	}


	public void setGoodDao(GoodDao goodDao) {
		this.goodDao = goodDao;
	}


	@Override
	public List<Good> getGoodList() {

		return goodDao.readAll();
	}

}
