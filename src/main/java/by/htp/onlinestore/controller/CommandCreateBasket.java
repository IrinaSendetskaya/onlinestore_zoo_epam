package by.htp.onlinestore.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.onlinestore.dao.DAOFactory;
import by.htp.onlinestore.entity.Basket;
import by.htp.onlinestore.entity.BasketListForJsp;
import by.htp.onlinestore.entity.Buyer;
import by.htp.onlinestore.util.constants.BasketFieldConstantDeclaration;
import by.htp.onlinestore.util.constants.ButtonNameConstantDeclaration;
import by.htp.onlinestore.util.constants.EntityNameConstantDeclaration;
import by.htp.onlinestore.util.CurrentDateUtilClass;
import by.htp.onlinestore.util.FormUtil;
import by.htp.onlinestore.util.PaginationUtilClass;
import by.htp.onlinestore.util.SessionUtilClass;
import by.htp.onlinestore.util.constants.ListConstantDeclaration;
import by.htp.onlinestore.util.constants.MessageConstantDeclaration;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class CommandCreateBasket extends Command {

	@Override
	Command execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		BigDecimal sumReady = BigDecimal.ZERO;
		List<Basket> baskets = new ArrayList<>();
		List<BasketListForJsp> basketListForJsp = new ArrayList<>();
		BigDecimal sumNew = BigDecimal.ONE;

		Buyer buyer=SessionUtilClass.findInSession(req, EntityNameConstantDeclaration.REQUEST_PARAM_BUYER);
		if (buyer == null) {
			req.setAttribute(MessageConstantDeclaration.MSG_MESSAGE, "Вам необходимо войти либо зарегистрироваться!");
			return NameCommands.LOGIN.command;
		}
		
		if (FormUtil.isPost(req)) {
			int id = FormUtil.getInt(req, BasketFieldConstantDeclaration.REQUEST_PARAM_BASKET_ID);
			int quantity = FormUtil.getInt(req, BasketFieldConstantDeclaration.REQUEST_PARAM_QUANTITY);
			int idGood = FormUtil.getInt(req, BasketFieldConstantDeclaration.REQUEST_PARAM_GOOD_ID);
			BigDecimal price = DAOFactory.getDao().getGoodDAO().read(idGood).getPrice();
			sumNew = price.multiply(new BigDecimal(quantity));
			Date dateOrder = CurrentDateUtilClass.returnCurrentDate();
			String status = "товар в корзине";

			Basket basket = Basket.newBuilder().setId(id).setQuantity(quantity).setSum(sumNew).setDateOrders(dateOrder)
					.setStatusOrders(status).setBuyerId(buyer.getId()).setGoodId(idGood).build();

			if (req.getParameter(ButtonNameConstantDeclaration.REQUEST_PARAM_BTN_UPDATE_GOOD_IN_BASKET) != null) {
				DAOFactory.getDao().getBasketDAO().update(basket);
			} else if (req
					.getParameter(ButtonNameConstantDeclaration.REQUEST_PARAM_BTN_DELETE_GOOD_IN_BASKET) != null) {
				DAOFactory.getDao().getBasketDAO().delete(basket);
			}	
		}
		
		baskets = DAOFactory.getDao().getBasketDAO().getAll(buyer.getId());
		int startGood = PaginationUtilClass.makePagination(req, baskets);
		basketListForJsp = DAOFactory.getDao().getBasketDAO().findAllBasketsJoinTablesWithPages(buyer.getId(), startGood,
				startGood + 5);

		req.setAttribute(ListConstantDeclaration.REQUEST_PARAM_BASKETS_LIST, baskets);
		req.setAttribute(ListConstantDeclaration.REQUEST_PARAM_BASKETS_LIST_FOR_JSP, basketListForJsp);
		req.setAttribute(EntityNameConstantDeclaration.REQUEST_PARAM_BUYER, buyer);

		Iterator<BasketListForJsp> iterator = basketListForJsp.iterator();
		while (iterator.hasNext()) {
			BasketListForJsp newBasket = iterator.next();
			sumReady = sumReady.add(newBasket.getSum());
		}
		req.setAttribute("sumReady", sumReady);
		
		if (baskets.isEmpty()) {
			req.setAttribute(MessageConstantDeclaration.MSG_ERROR, "Ваша корзина пуста!");
		}
		

		
		return null;
	}

}