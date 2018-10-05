package by.htp.onlinestore.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp.onlinestore.entity.Basket;
import by.htp.onlinestore.entity.Buyer;
import by.htp.onlinestore.service.ServiceFactory;
import by.htp.onlinestore.util.constants.ButtonNameConstantDeclaration;
import by.htp.onlinestore.util.constants.BuyerFieldConstantDeclaration;
import by.htp.onlinestore.util.constants.EntityNameConstantDeclaration;
import by.htp.onlinestore.util.FormUtil;
import by.htp.onlinestore.util.PaginationUtilClass;
import by.htp.onlinestore.util.SessionUtilClass;
import by.htp.onlinestore.util.constants.ListConstantDeclaration;
import by.htp.onlinestore.util.constants.MessageConstantDeclaration;
import by.htp.onlinestore.util.ValidationRegex;

import java.util.List;

/**
 * Class CommandProfile implementing Command interface
 * 
 * @author Sendetskaya Iryna
 *
 */
class CommandProfile extends Command {

	/* (non-Javadoc)
	 * @see by.htp.onlinestore.controller.Command#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	Command execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		HttpSession session = req.getSession();
		Buyer buyer=SessionUtilClass.findInSession(req, EntityNameConstantDeclaration.REQUEST_PARAM_BUYER);
		if (buyer == null) {
			req.setAttribute(MessageConstantDeclaration.MSG_MESSAGE, "Вам необходимо войти либо зарегистрироваться!");
			return NameCommands.LOGIN.command;
		}

		/**
		 * updates user data
		 */
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
                ServiceFactory.getService().getBuyerDAO().update(buyer);
               
                session.setAttribute(EntityNameConstantDeclaration.REQUEST_PARAM_BUYER, buyer);
            }
		}

		List<Basket> baskets =ServiceFactory.getService().getBasketDAO().getAllByStatus(buyer.getId(), "завершен");
		int startGood = PaginationUtilClass.makePagination(req, baskets);
		baskets=ServiceFactory.getService().getBasketDAO().findAllBasketsWithPagesByStatus(buyer.getId(),startGood, startGood+5, "завершен");
		req.setAttribute(ListConstantDeclaration.REQUEST_PARAM_BASKETS_LIST, baskets);
		
		return null;
	}
}