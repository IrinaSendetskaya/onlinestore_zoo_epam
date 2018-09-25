package by.htp.onlinestore.service;

import java.util.List;

import by.htp.onlinestore.entity.Good;
import by.htp.onlinestore.entity.Image;
import by.htp.onlinestore.entity.SpecificationGood;

public interface GoodService {

	List<Good> getGoodList();
	void createNewGood(Good good, SpecificationGood specificationGood, Image image, int measureId,int sectionId);

}
