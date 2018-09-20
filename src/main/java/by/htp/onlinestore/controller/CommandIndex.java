package by.htp.onlinestore.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.onlinestore.dao.DAOFactory;
import by.htp.onlinestore.entity.Basket;
import by.htp.onlinestore.entity.Buyer;
import by.htp.onlinestore.entity.Good;
import by.htp.onlinestore.entity.GoodListForJsp;
import by.htp.onlinestore.util.constants.ButtonNameConstantDeclaration;
import by.htp.onlinestore.util.constants.EntityNameConstantDeclaration;
import by.htp.onlinestore.util.CurrentDateUtilClass;
import by.htp.onlinestore.util.FormUtil;
import by.htp.onlinestore.util.PaginationUtilClass;
import by.htp.onlinestore.util.SessionUtilClass;
import by.htp.onlinestore.util.constants.GoodFieldConstantDeclaration;
import by.htp.onlinestore.util.constants.ListConstantDeclaration;
import by.htp.onlinestore.util.constants.MessageConstantDeclaration;
import by.htp.onlinestore.util.ValidationRegex;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

class CommandIndex extends Command {

	@Override
	Command execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		Buyer buyer=SessionUtilClass.findInSession(req, EntityNameConstantDeclaration.REQUEST_PARAM_BUYER);
		if (buyer == null) {
			req.setAttribute(MessageConstantDeclaration.MSG_MESSAGE, "Вам необходимо войти либо зарегистрироваться!");
			return NameCommands.LOGIN.command;
		} 

		List<Good> goods = new ArrayList<>();
		List<GoodListForJsp> goodsListForJsp = new ArrayList<>();

		// если нажата кнопка "Поиск"
		if (req.getParameter(ButtonNameConstantDeclaration.REQUEST_PARAM_BTN_SEARCH_GOOD) != null) {

			String nameSearch = FormUtil.getString(req, "searchInput", ValidationRegex.REGEX_ALL_SYMBOL);
			// выводит список товаро только по части слова Поиска
			goods = DAOFactory.getDao().getGoodDAO().searchGoods("%" + nameSearch + "%");
			int startGood = PaginationUtilClass.makePagination(req, goods);
			goodsListForJsp = DAOFactory.getDao().getGoodDAO().searchGoodsWithPages("%" + nameSearch + "%", startGood,
					startGood + 5);
			req.setAttribute(ListConstantDeclaration.REQUEST_PARAM_GOODS_LIST, goods);
			req.setAttribute(ListConstantDeclaration.REQUEST_PARAM_GOODS_LIST_FOR_JSP, goodsListForJsp);

			if (goodsListForJsp.isEmpty()) {
				req.setAttribute(MessageConstantDeclaration.MSG_MESSAGE, "товар по Вашему поиску не найден!");
			} else {
				req.setAttribute(MessageConstantDeclaration.MSG_MESSAGE,
						"товар по Вашему поиску найдено: " + goodsListForJsp.size() + "товаров!");
			}
			return null;
		} else {
			// выводит весь список товаров
			goodsListForJsp = DAOFactory.getDao().getGoodDAO().findAllGoodsJoinTables();
			int startGood = PaginationUtilClass.makePagination(req, goodsListForJsp);
			goodsListForJsp = DAOFactory.getDao().getGoodDAO().findAllGoodsJoinTablesWithPages(startGood, 10);

			req.setAttribute(ListConstantDeclaration.REQUEST_PARAM_GOODS_LIST, goods);
			req.setAttribute(ListConstantDeclaration.REQUEST_PARAM_GOODS_LIST_FOR_JSP, goodsListForJsp);
		}

		// добавление в корзину
		if (FormUtil.isPost(req)) {
			// если нажата кнопка "В корзину"
			if (req.getParameter(ButtonNameConstantDeclaration.REQUEST_PARAM_BTN_IN_BASKET) != null
					&& buyer.getRoleId() != 3) {
				int id = 0;
				int idGood = FormUtil.getInt(req, GoodFieldConstantDeclaration.REQUEST_PARAM_GOOD_ID);
				BigDecimal price = DAOFactory.getDao().getGoodDAO().read(idGood).getPrice();
				String name = DAOFactory.getDao().getSpecificationGoodDAO().read(DAOFactory.getDao().getGoodDAO().read(idGood).getSpecificationGoodId())
						.getName();
				int quantity = 1;
				BigDecimal sum = price.multiply(new BigDecimal(quantity));
				Date dateOrder = CurrentDateUtilClass.returnCurrentDate();
				String status = "товар в корзине";

				Basket basket = Basket.newBuilder().setId(id).setQuantity(quantity).setSum(sum).setDateOrders(dateOrder)
						.setStatusOrders(status).setBuyerId(buyer.getId()).setGoodId(idGood).build();

				if (basket != null) {
					DAOFactory.getDao().getBasketDAO().create(basket);
					req.setAttribute(MessageConstantDeclaration.MSG_MESSAGE, "товар " + name + " добавлен в корзину");
				} else {
					req.setAttribute(MessageConstantDeclaration.MSG_MESSAGE,
							"товар " + name + " не добавлен в корзину");
				}
				return null;
			}		
		}
		return null;
	}

}