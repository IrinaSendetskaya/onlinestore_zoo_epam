package by.htp.onlinestore.service;

import java.util.List;

import by.htp.onlinestore.entity.Image;

/**
 * Interface provides methods for working with Images table.
 * @author Iryna Siandzetskaya
 *
 */
public interface ImageService {

	/**
	 * gets all images
	 * @return list of images
	 */
	List<Image> getImageList();
}
