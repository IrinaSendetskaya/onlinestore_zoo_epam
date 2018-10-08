package by.htp.onlinestore.controller;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.onlinestore.entity.Basket;
import by.htp.onlinestore.entity.Buyer;
import by.htp.onlinestore.service.ServiceFactory;
import by.htp.onlinestore.util.FormUtil;
import by.htp.onlinestore.util.SessionUtilClass;
import by.htp.onlinestore.util.constants.ButtonNameConstantDeclaration;
import by.htp.onlinestore.util.constants.EntityNameConstantDeclaration;
import by.htp.onlinestore.util.constants.MessageConstantDeclaration;

/**
 * Class CommandConfirmOrder implementing Command interface
 * 
 * @author Sendetskaya Iryna
 *
 */
public class CommandConfirmOrder extends Command {

	/* (non-Javadoc)
	 * @see by.htp.onlinestore.controller.Command#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	Command execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		Basket currentBasket = null;
		Buyer buyer = SessionUtilClass.findInSession(req, EntityNameConstantDeclaration.REQUEST_PARAM_BUYER);
		if (buyer == null) {
			req.setAttribute(MessageConstantDeclaration.MSG_MESSAGE, "Вам необходимо войти либо зарегистрироваться!");
			return NameCommands.LOGIN.command;
		}

		BigDecimal sumReady = BigDecimal.ZERO;
		/**
		 * updates status data
		 */
		if (FormUtil.isPost(req)) {
			if (req.getParameter(ButtonNameConstantDeclaration.REQUEST_PARAM_BTN_CONFIRM_ORDER) != null) {
				String status = "завершен";

				List<Basket> baskets = ServiceFactory.getService().getBasketDAO().getAll(buyer.getId());

				Iterator<Basket> iterator = baskets.iterator();
				while (iterator.hasNext()) {
					currentBasket = iterator.next();
					if(!"завершен".equalsIgnoreCase(currentBasket.getStatusOrders())) {
					sumReady = sumReady.add(currentBasket.getSum());
					}
					currentBasket.setStatusOrders(status);
					ServiceFactory.getService().getBasketDAO().update(currentBasket);			
				}
				if (sumReady != BigDecimal.ZERO) {
					String sumReadyAll = String.format("Ваш заказ принят, сумма к оплате: %5.2f рублей",
							sumReady);
					req.setAttribute(MessageConstantDeclaration.MSG_MESSAGE, sumReadyAll);
				}
			} else {
				req.setAttribute(MessageConstantDeclaration.MSG_MESSAGE, "Ваша корзина пуста, добавьте товары!");
			}
		}
		return NameCommands.CONFIRMORDER.command;
	}
}
