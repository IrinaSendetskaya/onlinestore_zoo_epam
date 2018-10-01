package by.htp.onlinestore.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.onlinestore.dao.DAOFactory;
import by.htp.onlinestore.entity.Good;
import by.htp.onlinestore.entity.Image;
import by.htp.onlinestore.entity.Measure;
import by.htp.onlinestore.entity.Section;
import by.htp.onlinestore.entity.SpecificationGood;
import by.htp.onlinestore.service.ServiceFactory;
import by.htp.onlinestore.util.FormUtil;
import by.htp.onlinestore.util.ValidationRegex;
import by.htp.onlinestore.util.constants.GoodFieldConstantDeclaration;
import by.htp.onlinestore.util.constants.ImageFieldConstantDeclaration;
import by.htp.onlinestore.util.constants.ListConstantDeclaration;
import by.htp.onlinestore.util.constants.MeasureFieldConstantDeclaration;
import by.htp.onlinestore.util.constants.MessageConstantDeclaration;
import by.htp.onlinestore.util.constants.SectionFieldConstantDeclaration;
import by.htp.onlinestore.util.constants.SpecificationGoodFieldConstantDeclaration;

/**
 * Class CommandCreateGood implementing Command interface
 * 
 * @author Sendetskaya Iryna
 *
 */
public class CommandCreateGood extends Command {

	/* (non-Javadoc)
	 * @see by.htp.onlinestore.controller.Command#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	Command execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		if (FormUtil.isPost(req)) {

			String nameGood = FormUtil.getString(req, SpecificationGoodFieldConstantDeclaration.REQUEST_PARAM_NAME,
					ValidationRegex.REGEX_ALL_SYMBOL);
			String description = req.getParameter(SpecificationGoodFieldConstantDeclaration.REQUEST_PARAM_DESCRIPTION);
			String imageUrl = req.getParameter(ImageFieldConstantDeclaration.REQUEST_PARAM_IMAGE_URL);
			BigDecimal price = FormUtil.getBigDecimal(req, GoodFieldConstantDeclaration.REQUEST_PARAM_PRICE);
			int measureId = FormUtil.getInt(req, MeasureFieldConstantDeclaration.REQUEST_PARAM_MEASURE_ID);
			int sectionId = FormUtil.getInt(req, SectionFieldConstantDeclaration.REQUEST_PARAM_SECTION_ID);

			Image image=new Image();
			image.setImageUrl(imageUrl);
			SpecificationGood specificationGood=new SpecificationGood();
			specificationGood.setName(nameGood);
			specificationGood.setDescription(description);
			specificationGood.setSectionId(sectionId);
			specificationGood.setImageId(image.getId());
			Good good=new Good();
			good.setPrice(price);
			good.setMeasureId(measureId);
			good.setSpecificationGoodId(specificationGood.getId());

			//DAOFactory.getDao().getGoodDAO().createNewGood(good, specificationGood, image, measureId, sectionId);
			ServiceFactory.getService().getGoodDAO().createNewGood(good, specificationGood, image, measureId, sectionId);

				if (good.getId() != 0) {
					req.setAttribute(MessageConstantDeclaration.MSG_MESSAGE, "Товар создан!");
				} else {
					req.setAttribute(MessageConstantDeclaration.MSG_MESSAGE, "Ошибка добавления товара");
					return null;
				}
		}
		
		List<Measure> measures = DAOFactory.getDao().getMeasureDAO().readAll();
		List<Section> sections = DAOFactory.getDao().getSectionDAO().readAll();
        req.setAttribute(ListConstantDeclaration.REQUEST_PARAM_MEASURES_LIST,measures);
        req.setAttribute(ListConstantDeclaration.REQUEST_PARAM_SECTIONS_LIST,sections);
        return null;
	}
}
