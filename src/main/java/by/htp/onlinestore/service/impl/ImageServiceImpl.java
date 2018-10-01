package by.htp.onlinestore.service.impl;

import java.util.List;

import by.htp.onlinestore.dao.DAOFactory;
import by.htp.onlinestore.dao.ImageDao;
import by.htp.onlinestore.entity.Image;
import by.htp.onlinestore.service.ImageService;

/**
 *  Class provides methods for working with Images table.
 * @author Iryna Siandzetskaya
 *
 */
public class ImageServiceImpl implements ImageService {
	
	/**
	 * Declares a object and initialize
	 */
	private ImageDao imageDao=DAOFactory.getDao().getImageDAO();

	/**
	 * constructor without parameter
	 */
	public ImageServiceImpl() {

	}
	
	

	/**
	 * getters and setters
	 * @return instance
	 */
	public ImageDao getImageDao() {
		return imageDao;
	}



	public void setImageDao(ImageDao imageDao) {
		this.imageDao = imageDao;
	}



	/* (non-Javadoc)
	 * @see by.htp.onlinestore.service.ImageService#getImageList()
	 */
	@Override
	public List<Image> getImageList() {

		return imageDao.readAll();
	}

}
