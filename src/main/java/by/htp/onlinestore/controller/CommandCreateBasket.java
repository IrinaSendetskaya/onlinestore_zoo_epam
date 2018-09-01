package by.htp.onlinestore.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp.onlinestore.dao.DAOFactory;
import by.htp.onlinestore.entity.Basket;
import by.htp.onlinestore.entity.Buyer;
import by.htp.onlinestore.entity.GoodListForJsp;
import by.htp.onlinestore.util.constants.BasketFieldConstantDeclaration;
import by.htp.onlinestore.util.constants.ButtonNameConstantDeclaration;
import by.htp.onlinestore.util.constants.EntityNameConstantDeclaration;
import by.htp.onlinestore.util.CurrentDateUtilClass;
import by.htp.onlinestore.util.FormUtil;
import by.htp.onlinestore.util.constants.GoodFieldConstantDeclaration;
import by.htp.onlinestore.util.constants.ListConstantDeclaration;
import by.htp.onlinestore.util.constants.MessageConstantDeclaration;
import by.htp.onlinestore.util.ValidationRegex;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class CommandCreateBasket extends Command {
	
	@Override
	Command execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		BigDecimal sumReady=BigDecimal.ONE;
		List<Basket> baskets = new ArrayList<>();
		String name="";

		HttpSession session = req.getSession();
		Object o = session.getAttribute(EntityNameConstantDeclaration.REQUEST_PARAM_BUYER);
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
			Date dateOrder = CurrentDateUtilClass.returnCurrentDate();
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
			
			
			Iterator<Basket> iterator = baskets.iterator();
			while (iterator.hasNext()) {
				sumReady.add(iterator.next().getSum());
				name=DAOFactory.getDAO().specificationGoodDAO.read(DAOFactory.getDAO().goodDAO.read(idGood).getSpecificationGoodId()).getName();
			}
			req.setAttribute("nameGood", name);	

			if (req.getParameter(ButtonNameConstantDeclaration.REQUEST_PARAM_BTN_UPDATE_GOOD_IN_BASKET) != null) {
				DAOFactory.getDAO().basketDAO.update(basket);
			} else if (req.getParameter(ButtonNameConstantDeclaration.REQUEST_PARAM_BTN_DELETE_GOOD_IN_BASKET) != null) {
				DAOFactory.getDAO().basketDAO.delete(basket);
			}
			
			
			if (req.getParameter(ButtonNameConstantDeclaration.REQUEST_PARAM_BTN_CONFIRM_ORDER) != null) {
				if(sumReady!=null) {
				String sumReadyAll = String.format("Ваш заказ принят, сумма к оплате: %5.2f рублей", sumReady);
				req.setAttribute(MessageConstantDeclaration.MSG_MESSAGE, sumReadyAll);
				req.setAttribute("sumReady", sumReadyAll);
				status="Доставлен";
				basket = Basket.newBuilder()
						.setStatusOrders(status)
						.build();
			}
			DAOFactory.getDAO().basketDAO.update(basket);
			}
		}
		

		baskets= DAOFactory.getDAO().basketDAO.getAll(buyer.getId());	
		
		
//		int idGood = FormUtil.getInt(req, BasketFieldConstantDeclaration.REQUEST_PARAM_GOOD_ID);
//		name=DAOFactory.getDAO().specificationGoodDAO.read(DAOFactory.getDAO().goodDAO.read(idGood).getSpecificationGoodId()).getName();
		req.setAttribute("nameGood", name);	
		baskets= DAOFactory.getDAO().basketDAO.getAll(buyer.getId());		
		List<GoodListForJsp> goods = DAOFactory.getDAO().goodDAO.findAllGoodsJoinTables();
		
		req.setAttribute(ListConstantDeclaration.REQUEST_PARAM_BASKETS_LIST, baskets);	
		req.setAttribute(ListConstantDeclaration.REQUEST_PARAM_GOODS_LIST_FOR_JSP, goods);
		req.setAttribute(EntityNameConstantDeclaration.REQUEST_PARAM_BUYER, buyer);
		
		if (baskets.size() < 1)
			req.setAttribute(MessageConstantDeclaration.MSG_ERROR, "Ваша корзина пуста!");
	


		return null;
	}

}