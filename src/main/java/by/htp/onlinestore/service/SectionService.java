package by.htp.onlinestore.service;

import java.util.List;

import by.htp.onlinestore.entity.Section;

/**
 * Interface provides methods for working with Sections table.
 * @author Iryna Siandzetskaya
 *
 */
public interface SectionService {

	/**
	 *gets all sections
	 * @return list of sections
	 */
	List<Section> getSectionList();
}
