package by.htp.onlinestore.service.impl;

import java.util.List;

import by.htp.onlinestore.dao.SectionDao;
import by.htp.onlinestore.entity.Section;
import by.htp.onlinestore.service.SectionService;

public class SectionServiceImpl implements SectionService {
	
	private SectionDao sectionDao;

	public SectionServiceImpl() {

	}
	

	public SectionDao getSectionDao() {
		return sectionDao;
	}


	public void setSectionDao(SectionDao sectionDao) {
		this.sectionDao = sectionDao;
	}


	@Override
	public List<Section> getSectionList() {

		return sectionDao.readAll();
	}

}
