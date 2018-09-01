package by.htp.onlinestore.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp.onlinestore.dao.DAOFactory;
import by.htp.onlinestore.entity.Basket;
import by.htp.onlinestore.entity.Buyer;
import by.htp.onlinestore.util.constants.ButtonNameConstantDeclaration;
import by.htp.onlinestore.util.constants.BuyerFieldConstantDeclaration;
import by.htp.onlinestore.util.constants.EntityNameConstantDeclaration;
import by.htp.onlinestore.util.FormUtil;
import by.htp.onlinestore.util.PaginationUtilClass;
import by.htp.onlinestore.util.constants.ListConstantDeclaration;
import by.htp.onlinestore.util.ValidationRegex;

import java.util.List;

class CommandProfile extends Command {

	@Override
	Command execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		HttpSession session = req.getSession();
		Object o = session.getAttribute(EntityNameConstantDeclaration.REQUEST_PARAM_BUYER);
		Buyer buyer;
		if (o != null) {
			buyer = (Buyer) o;
		} else
			return NameCommands.LOGIN.command;

		// обновление данных пользователя
		if (FormUtil.isPost(req)) {
			String nickname = FormUtil.getString(req, BuyerFieldConstantDeclaration.REQUEST_PARAM_LOGIN,
					ValidationRegex.REGEX_LOGIN);
			String password = FormUtil.getString(req, BuyerFieldConstantDeclaration.REQUEST_PARAM_PASS,
					ValidationRegex.REGEX_PASS);
			String email = FormUtil.getString(req, BuyerFieldConstantDeclaration.REQUEST_PARAM_EMAIL,
					ValidationRegex.REGEX_EMAIL);
			String mobile = FormUtil.getString(req, BuyerFieldConstantDeclaration.REQUEST_PARAM_MOBILE,
					ValidationRegex.REGEX_MOBILE);
			String address = FormUtil.getString(req, BuyerFieldConstantDeclaration.REQUEST_PARAM_ADDRESS,
					ValidationRegex.REGEX_ALL_SYMBOL);

			buyer = Buyer.newBuilder()
					.setId(buyer.getId())
					.setNickname(nickname)
					.setPassword(password)
					.setEmail(email)
					.setMobile(mobile)
					.setAddress(address)
					.setRoleId(buyer.getRoleId())
					.build();
			
			if (req.getParameter(ButtonNameConstantDeclaration.REQUEST_PARAM_BTN_CHANGE_PROFILE)!=null){
                DAOFactory.getDAO().buyerDAO.update(buyer);
               
                session.setAttribute(EntityNameConstantDeclaration.REQUEST_PARAM_BUYER, buyer);
            }
		}

		List<Basket> baskets = DAOFactory.getDAO().basketDAO.getAll(buyer.getId());
		int startGood = PaginationUtilClass.makePagination(req, baskets);
		baskets=DAOFactory.getDAO().basketDAO.findAllBasketsWithPages(buyer.getId(),startGood, startGood+5);
		req.setAttribute(ListConstantDeclaration.REQUEST_PARAM_BASKETS_LIST, baskets);
		List<Buyer> buyers = DAOFactory.getDAO().buyerDAO.readAll();
        req.setAttribute(ListConstantDeclaration.REQUEST_PARAM_BUYERS_LIST,buyers);
		
		return null;
	}
}