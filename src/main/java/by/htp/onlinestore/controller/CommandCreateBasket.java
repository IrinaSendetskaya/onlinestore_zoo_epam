package by.htp.onlinestore.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

		BigDecimal sumReady = BigDecimal.ONE;
		List<Basket> baskets = new ArrayList<>();
		List<BasketListForJsp> basketListForJsp = new ArrayList<>();
		BigDecimal sumNew = BigDecimal.ONE;

		HttpSession session = req.getSession();
		Object o = session.getAttribute(EntityNameConstantDeclaration.REQUEST_PARAM_BUYER);
		Buyer buyer;
		if (o != null) {
			buyer = (Buyer) o;
		} else
			return NameCommands.LOGIN.command;

		if (FormUtil.isPost(req)) {
			int id = FormUtil.getInt(req, BasketFieldConstantDeclaration.REQUEST_PARAM_BASKET_ID);
			int quantity = FormUtil.getInt(req, BasketFieldConstantDeclaration.REQUEST_PARAM_QUANTITY);
			int idGood = FormUtil.getInt(req, BasketFieldConstantDeclaration.REQUEST_PARAM_GOOD_ID);
			BigDecimal price = DAOFactory.getDAO().goodDAO.read(idGood).getPrice();
			sumNew = price.multiply(new BigDecimal(quantity));
			Date dateOrder = CurrentDateUtilClass.returnCurrentDate();
			String status = "товар изменен";

			Basket basket = Basket.newBuilder().setId(id).setQuantity(quantity).setSum(sumNew).setDateOrders(dateOrder)
					.setStatusOrders(status).setBuyerId(buyer.getId()).setGoodId(idGood).build();

			if (req.getParameter(ButtonNameConstantDeclaration.REQUEST_PARAM_BTN_UPDATE_GOOD_IN_BASKET) != null) {
				DAOFactory.getDAO().basketDAO.update(basket);
			} else if (req
					.getParameter(ButtonNameConstantDeclaration.REQUEST_PARAM_BTN_DELETE_GOOD_IN_BASKET) != null) {
				DAOFactory.getDAO().basketDAO.delete(basket);
			}
		}

		baskets = DAOFactory.getDAO().basketDAO.getAll(buyer.getId());
		int startGood = PaginationUtilClass.makePagination(req, baskets);
		basketListForJsp = DAOFactory.getDAO().basketDAO.findAllBasketsJoinTablesWithPages(buyer.getId(), startGood, startGood + 5);

		req.setAttribute(ListConstantDeclaration.REQUEST_PARAM_BASKETS_LIST, baskets);
		req.setAttribute(ListConstantDeclaration.REQUEST_PARAM_BASKETS_LIST_FOR_JSP, basketListForJsp);
		req.setAttribute(EntityNameConstantDeclaration.REQUEST_PARAM_BUYER, buyer);

		if (baskets.isEmpty()) {
			req.setAttribute(MessageConstantDeclaration.MSG_ERROR, "Ваша корзина пуста!");
		}
		Iterator<Basket> iterator = baskets.iterator();
		while (iterator.hasNext()) {
			Basket newBasket = iterator.next();
			sumReady=sumReady.add(newBasket.getSum());
		}
		req.setAttribute("sumReady", sumReady);

		if (req.getParameter(ButtonNameConstantDeclaration.REQUEST_PARAM_BTN_CONFIRM_ORDER)!= null) {
			if(sumReady!=BigDecimal.ONE) {

				String sumReadyAll = String.format("Ваш заказ принят, сумма к оплате: %5.2f рублей",
						sumReady.doubleValue());
				req.setAttribute(MessageConstantDeclaration.MSG_MESSAGE, sumReadyAll);
				req.setAttribute("sumReady", sumReadyAll);
				
				int id = FormUtil.getInt(req, BasketFieldConstantDeclaration.REQUEST_PARAM_BASKET_ID);
				int quantity = FormUtil.getInt(req, BasketFieldConstantDeclaration.REQUEST_PARAM_QUANTITY);
				int idGood = FormUtil.getInt(req, BasketFieldConstantDeclaration.REQUEST_PARAM_GOOD_ID);
				BigDecimal price = DAOFactory.getDAO().goodDAO.read(idGood).getPrice();
				sumNew = price.multiply(new BigDecimal(quantity));
				Date dateOrder = CurrentDateUtilClass.returnCurrentDate();
				String status = "Завершен";

				Basket basket = Basket.newBuilder().setId(id).setQuantity(quantity).setSum(sumNew).setDateOrders(dateOrder)
						.setStatusOrders(status).setBuyerId(buyer.getId()).setGoodId(idGood).build();

			
			 DAOFactory.getDAO().basketDAO.update(basket);
				return null;
			}
		}

		return null;
	}

}