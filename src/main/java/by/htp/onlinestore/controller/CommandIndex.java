package by.htp.onlinestore.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp.onlinestore.dao.DAOFactory;
import by.htp.onlinestore.entity.Basket;
import by.htp.onlinestore.entity.Buyer;
import by.htp.onlinestore.entity.Good;
import by.htp.onlinestore.entity.Image;
import by.htp.onlinestore.entity.SpecificationGood;
import by.htp.onlinestore.util.BasketFieldConstantDeclaration;
import by.htp.onlinestore.util.ButtonNameConstantDeclaration;
import by.htp.onlinestore.util.EntityNameConstantDeclaration;
import by.htp.onlinestore.util.FormUtil;
import by.htp.onlinestore.util.GoodFieldConstantDeclaration;
import by.htp.onlinestore.util.ListConstantDeclaration;
import by.htp.onlinestore.util.MessageConstantDeclaration;
import by.htp.onlinestore.util.ValidationRegex;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

class CommandIndex extends Command {

	@Override
	Command execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		HttpSession session = req.getSession();
		Object o = session.getAttribute(EntityNameConstantDeclaration.REQUEST_PARAM_BUYER); // извлекаем из сессии
																							// покупателя
		Buyer buyer;
		if (o != null) {
			buyer = (Buyer) o;
		} else {
			req.setAttribute(MessageConstantDeclaration.MSG_MESSAGE, "Вам необходимо войти либо зарегистрироваться!");
			return NameCommands.LOGIN.command;
		}

		List<Good> goods = new ArrayList<>();
		List<SpecificationGood> goodsSpecification = new ArrayList<>();
		List<Image> images = new ArrayList<>();

		// если нажата кнопка "Поиск"
		if (req.getParameter(ButtonNameConstantDeclaration.REQUEST_PARAM_BTN_SEARCH_GOOD) != null) {

			String nameSearch = FormUtil.getString(req, "searchInput", ValidationRegex.REGEX_ALL_SYMBOL);
			// выводит список товаро только по части слова Поиска
			goodsSpecification = DAOFactory.getDAO().specificationGoodDAO.searchGoods("%"+nameSearch+"%");
			for (SpecificationGood goodSpecification : goodsSpecification) {
				Good good = DAOFactory.getDAO().goodDAO.searchGoods(goodSpecification.getId()).get(0);
				goods.add(good);
				Image image = DAOFactory.getDAO().imageDAO.read(goodSpecification.getId());
				images.add(image);
			}

			req.setAttribute(ListConstantDeclaration.REQUEST_PARAM_GOODS_LIST, goods);
			req.setAttribute(ListConstantDeclaration.REQUEST_PARAM_SPECIFICATION_GOODS_LIST, goodsSpecification);
			req.setAttribute(ListConstantDeclaration.REQUEST_PARAM_IMAGES_LIST, images);
			if(goodsSpecification.isEmpty()) {
				req.setAttribute(MessageConstantDeclaration.MSG_MESSAGE, "товар по Вашему поиску не найден!");
			}else {
			req.setAttribute(MessageConstantDeclaration.MSG_MESSAGE, "товар по Вашему поиску найден!");
			}
			return null;
		} else {
			// выводит весь список товаров
			goods = DAOFactory.getDAO().goodDAO.readAll();

			goodsSpecification = DAOFactory.getDAO().specificationGoodDAO.readAll(); 
			images = DAOFactory.getDAO().imageDAO.readAll();
			req.setAttribute(ListConstantDeclaration.REQUEST_PARAM_GOODS_LIST, goods);
			req.setAttribute(ListConstantDeclaration.REQUEST_PARAM_SPECIFICATION_GOODS_LIST, goodsSpecification);
			req.setAttribute(ListConstantDeclaration.REQUEST_PARAM_IMAGES_LIST, images);
		}

		// если нажата кнопка "В корзину"
		if (req.getParameter(ButtonNameConstantDeclaration.REQUEST_PARAM_BTN_IN_BASKET) != null
				&& buyer.getRoleId() != 3) {
			int idGood = FormUtil.getInt(req, GoodFieldConstantDeclaration.REQUEST_PARAM_GOOD_ID);
			BigDecimal price = FormUtil.getBigDecimal(req, GoodFieldConstantDeclaration.REQUEST_PARAM_PRICE);
			String name = goodsSpecification.get(0).getName();
			
			int id = FormUtil.getInt(req, BasketFieldConstantDeclaration.REQUEST_PARAM_BASKET_ID);
			int quantity = 1;
			BigDecimal sum = price.multiply(new BigDecimal(quantity));
			Date dateOrder = returnCurrentDate();
			String status="в обработке";
			

			Basket basket = Basket.newBuilder()
					.setId(id)
					.setQuantity(quantity)
					.setSum(sum)
					.setDateOrders(dateOrder)
					.setStatusOrders(status)
					.setBuyerId(buyer.getId())
					.setGoodId(idGood)
					.build();
	

			DAOFactory.getDAO().basketDAO.create(basket);

			req.setAttribute(MessageConstantDeclaration.MSG_MESSAGE, "товар " + name + " добавлен в корзину");
			return null;

		}
		return null;
	}

	private Date returnCurrentDate() {

		Calendar calendar = Calendar.getInstance();
		Date curDate = new Date(calendar.getTime().getTime());
		return curDate;
	}
}