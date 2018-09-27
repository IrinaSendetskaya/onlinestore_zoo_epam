package by.htp.onlinestore.service.impl;

import java.util.List;

import by.htp.onlinestore.dao.SectionDao;
import by.htp.onlinestore.entity.Section;
import by.htp.onlinestore.service.SectionService;

/**
 *Class provides methods for working with Sections table.
 * @author Iryna Siandzetskaya
 *
 */
public class SectionServiceImpl implements SectionService {
	
	/**
	 * Declares a object
	 */
	private SectionDao sectionDao;

	/**
	 * constructor without parameter
	 */
	public SectionServiceImpl() {

	}
	

	/**
	 * getters and setters
	 * @return instance
	 */
	public SectionDao getSectionDao() {
		return sectionDao;
	}


	public void setSectionDao(SectionDao sectionDao) {
		this.sectionDao = sectionDao;
	}


	/* (non-Javadoc)
	 * @see by.htp.onlinestore.service.SectionService#getSectionList()
	 */
	@Override
	public List<Section> getSectionList() {

		return sectionDao.readAll();
	}

}
