package by.htp.onlinestore.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp.onlinestore.dao.DAOFactory;
import by.htp.onlinestore.entity.Basket;
import by.htp.onlinestore.entity.Buyer;
import by.htp.onlinestore.util.BuyerFieldConstantDeclaration;
import by.htp.onlinestore.util.FormUtil;
import by.htp.onlinestore.util.ValidationRegex;

import java.util.List;

class CommandProfile extends Action {
    @Override
    Action execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HttpSession session = req.getSession();
        Object o = session.getAttribute("buyer");
        Buyer buyer;
        if (o!=null){
            buyer=(Buyer) o;
        }
        else
            return Actions.LOGIN.command;

        //обновление данных пользователя
        if (FormUtil.isPost(req)) {
        	String nickname = FormUtil.getString(req, BuyerFieldConstantDeclaration.REQUEST_PARAM_LOGIN, ValidationRegex.REGEX_LOGIN);
            String password = FormUtil.getString(req,BuyerFieldConstantDeclaration.REQUEST_PARAM_PASS, ValidationRegex.REGEX_PASS);
            String email = FormUtil.getString(req,BuyerFieldConstantDeclaration.REQUEST_PARAM_EMAIL, ValidationRegex.REGEX_EMAIL);       
            String mobile = FormUtil.getString(req,BuyerFieldConstantDeclaration.REQUEST_PARAM_MOBILE, ValidationRegex.REGEX_MOBILE);
            String address = FormUtil.getString(req,BuyerFieldConstantDeclaration.REQUEST_PARAM_ADDRESS, ValidationRegex.REGEX_ALL_SYMBOL);

           buyer = Buyer.newBuilder().setNickname(nickname)
            		.setPassword(password)
            		.setEmail(email)
            		.setMobile(mobile)
            		.setAddress(address)
            		.build(); 
            DAOFactory.getDAO().buyerDAO.update(buyer);

        }

        List<Basket> baskets = DAOFactory.getDAO().basketDAO.getAll(
                "where fk_buyers='+ buyer.getId() +'"
        );
        req.setAttribute("baskets", baskets);
        return null;
    }
}