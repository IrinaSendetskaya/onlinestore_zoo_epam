package by.htp.onlinestore.service.impl;

import java.util.List;

import by.htp.onlinestore.dao.ImageDao;
import by.htp.onlinestore.entity.Image;
import by.htp.onlinestore.service.ImageService;

public class ImageServiceImpl implements ImageService {
	
	private ImageDao imageDao;

	public ImageServiceImpl() {

	}
	
	

	public ImageDao getImageDao() {
		return imageDao;
	}



	public void setImageDao(ImageDao imageDao) {
		this.imageDao = imageDao;
	}



	@Override
	public List<Image> getImageList() {

		return imageDao.readAll();
	}

}
