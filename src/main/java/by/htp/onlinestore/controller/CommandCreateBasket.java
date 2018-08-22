package by.htp.onlinestore.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp.onlinestore.dao.DAOFactory;
import by.htp.onlinestore.entity.Basket;
import by.htp.onlinestore.entity.Buyer;
import by.htp.onlinestore.entity.Good;
import by.htp.onlinestore.util.FormUtil;
import by.htp.onlinestore.util.MessageConstantDeclaration;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

class CommandCreateBasket extends Action {
	@Override
	Action execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		double sumReady = 0;

		HttpSession session = req.getSession();
		Object o = session.getAttribute("buyer");
		Buyer buyer;
		if (o != null) {
			buyer = (Buyer) o;
		} else
			return Actions.LOGIN.command;

		if (FormUtil.isPost(req)) {
			int idGood = FormUtil.getInt(req, "FK_goods");
			Good good = DAOFactory.getDAO().goodDAO.getAll("where ID='" + idGood + "'").get(0);

			int id = FormUtil.getInt(req, "ID");
			int quantity = FormUtil.getInt(req, "Quantity");
			double sum = FormUtil.getDouble(req, "Sum");
			//sum = quantity * good.getPrice();
			Date dateOrder = returnCurrentDate();

			int fk_buyers = buyer.getId();
			int fk_goods = idGood;

			//Basket basket = new Basket(id,quantity,sum,dateOrder,fk_buyers,fk_goods);

			if (req.getParameter("Update") != null) {
		//		DAOFactory.getDAO().basketDAO.update(basket);
			} else if (req.getParameter("Delete") != null) {
		//		DAOFactory.getDAO().basketDAO.delete(basket);
			}

		}

		List<Basket> baskets = DAOFactory.getDAO().basketDAO.getAll("where fk_buyers='" + buyer.getId() + "'");
		List<Good> goods = DAOFactory.getDAO().goodDAO.readAll();

		req.setAttribute("baskets", baskets);
		req.setAttribute("goods", goods);

		if (goods.size() < 1)
			req.setAttribute(MessageConstantDeclaration.MSG_ERROR, "Ваша корзина пуста!");

		Iterator<Basket> iterator = baskets.iterator();
		while (iterator.hasNext()) {
		//	sumReady += iterator.next().getSum();
		}

		if (req.getParameter("ready") != null) {
			String sum = String.format("Ваш заказ принят, сумма к оплате: %5.2f рублей", sumReady);
			req.setAttribute(MessageConstantDeclaration.MSG_ERROR, sum);
		}

		return null;
	}

	private Date returnCurrentDate() {

		Calendar calendar = Calendar.getInstance();
		Date curDate = new Date(calendar.getTime().getTime());
		// LocalDate dateTime=curDate.toLocalDate();
		return curDate;
	}
}