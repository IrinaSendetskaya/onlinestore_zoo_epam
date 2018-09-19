package by.htp.onlinestore.controller;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.onlinestore.dao.DAOFactory;
import by.htp.onlinestore.entity.Basket;
import by.htp.onlinestore.entity.Buyer;
import by.htp.onlinestore.util.SessionUtilClass;
import by.htp.onlinestore.util.constants.ButtonNameConstantDeclaration;
import by.htp.onlinestore.util.constants.EntityNameConstantDeclaration;
import by.htp.onlinestore.util.constants.MessageConstantDeclaration;

public class CommandConfirmOrder extends Command {

	@Override
	Command execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		Basket currentBasket=null;
		Buyer buyer = SessionUtilClass.findInSession(req, EntityNameConstantDeclaration.REQUEST_PARAM_BUYER);
		if (buyer == null) {
			req.setAttribute(MessageConstantDeclaration.MSG_MESSAGE, "Вам необходимо войти либо зарегистрироваться!");
			return NameCommands.LOGIN.command;
		}

		BigDecimal sumReady = BigDecimal.ZERO;
		if (req.getParameter(ButtonNameConstantDeclaration.REQUEST_PARAM_BTN_CONFIRM_ORDER) != null) {
			sumReady = (BigDecimal) req.getAttribute("sumReady");
			if (sumReady != BigDecimal.ZERO) {
				String sumReadyAll = String.format("Ваш заказ принят, сумма к оплате: %5.2f рублей",
						sumReady.doubleValue());
				req.setAttribute(MessageConstantDeclaration.MSG_MESSAGE, sumReadyAll);
				//req.setAttribute("sumReady", sumReadyAll);

				String status = "Завершен";

				List<Basket> baskets = DAOFactory.getDao().getBasketDAO().getAll(buyer.getId());

				Iterator<Basket> iterator = baskets.iterator();
				while (iterator.hasNext()) {
					currentBasket=iterator.next();
					currentBasket.setStatusOrders(status);
					DAOFactory.getDao().getBasketDAO().update(currentBasket);
				}
				return null;
			}
		}
		else {
			req.setAttribute(MessageConstantDeclaration.MSG_MESSAGE, "Ваша корзина пуста, добавьте товары!");
		}
		return null;
	}

}
