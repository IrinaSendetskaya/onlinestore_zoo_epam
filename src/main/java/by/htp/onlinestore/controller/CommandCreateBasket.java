package by.htp.onlinestore.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp.onlinestore.dao.DAOFactory;
import by.htp.onlinestore.entity.Basket;
import by.htp.onlinestore.entity.Buyer;
import by.htp.onlinestore.entity.Good;
import by.htp.onlinestore.util.BasketFieldConstantDeclaration;
import by.htp.onlinestore.util.ButtonNameConstantDeclaration;
import by.htp.onlinestore.util.FormUtil;
import by.htp.onlinestore.util.GoodFieldConstantDeclaration;
import by.htp.onlinestore.util.MessageConstantDeclaration;
import by.htp.onlinestore.util.ValidationRegex;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

class CommandCreateBasket extends Command {
	
	@Override
	Command execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		BigDecimal sumReady=BigDecimal.ZERO;

		HttpSession session = req.getSession();
		Object o = session.getAttribute("buyer");
		Buyer buyer;
		if (o != null) {
			buyer = (Buyer) o;
		} else
			return NameCommands.LOGIN.command;

		if (FormUtil.isPost(req)) {
			int idGood = FormUtil.getInt(req, BasketFieldConstantDeclaration.REQUEST_PARAM_GOOD_ID);
			BigDecimal price=FormUtil.getBigDecimal(req, GoodFieldConstantDeclaration.REQUEST_PARAM_PRICE);
			
			int id = FormUtil.getInt(req, BasketFieldConstantDeclaration.REQUEST_PARAM_BASKET_ID);
			int quantity = FormUtil.getInt(req, BasketFieldConstantDeclaration.REQUEST_PARAM_QUANTITY);
			BigDecimal sum = price.multiply(new BigDecimal(quantity));
			Date dateOrder = returnCurrentDate();
			String status=FormUtil.getString(req, BasketFieldConstantDeclaration.REQUEST_PARAM_STATUS, ValidationRegex.REGEX_ALL_SYMBOL);
			

			Basket basket = Basket.newBuilder()
					.setId(id)
					.setQuantity(quantity)
					.setSum(sum)
					.setDateOrders(dateOrder)
					.setStatusOrders(status)
					.setBuyerId(buyer.getId())
					.setGoodId(idGood)
					.build();

			if (req.getParameter(ButtonNameConstantDeclaration.REQUEST_PARAM_BTN_UPDATE_GOOD_IN_BASKET) != null) {

				DAOFactory.getDAO().basketDAO.update(basket);
			} else if (req.getParameter(ButtonNameConstantDeclaration.REQUEST_PARAM_BTN_DELETE_GOOD_IN_BASKET) != null) {
				DAOFactory.getDAO().basketDAO.delete(basket);
			}

		}

		List<Basket> baskets = DAOFactory.getDAO().basketDAO.getAll(buyer.getId());
		List<Good> goods = DAOFactory.getDAO().goodDAO.readAll();

		req.setAttribute("baskets", baskets);
		req.setAttribute("goods", goods);

		if (goods.size() < 1)
			req.setAttribute(MessageConstantDeclaration.MSG_ERROR, "Ваша корзина пуста!");

		Iterator<Basket> iterator = baskets.iterator();
		while (iterator.hasNext()) {
			sumReady.add(iterator.next().getSum());
		}

		if (req.getParameter(ButtonNameConstantDeclaration.REQUEST_PARAM_BTN_CONFIRM_ORDER) != null) {
			String sum = String.format("Ваш заказ принят, сумма к оплате: %5.2f рублей", sumReady);
			req.setAttribute(MessageConstantDeclaration.MSG_ERROR, sum);
		}

		return null;
	}

	private Date returnCurrentDate() {

		Calendar calendar = Calendar.getInstance();
		Date curDate = new Date(calendar.getTime().getTime());
		return curDate;
	}
}