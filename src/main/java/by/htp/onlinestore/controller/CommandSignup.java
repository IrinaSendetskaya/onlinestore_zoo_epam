package by.htp.onlinestore.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp.onlinestore.dao.DAOFactory;
import by.htp.onlinestore.entity.Buyer;
import by.htp.onlinestore.util.BuyerFieldConstantDeclaration;
import by.htp.onlinestore.util.FormUtil;
import by.htp.onlinestore.util.MessageConstantDeclaration;
import by.htp.onlinestore.util.ValidationRegex;

import java.text.ParseException;

public class CommandSignup extends Action {

    @Override
    public Action execute(HttpServletRequest req, HttpServletResponse resp) throws ParseException {

        if (!FormUtil.isPost(req))
            return null;

        req.setAttribute(MessageConstantDeclaration.MSG_MESSAGE, "Зарегистрируйтесь");
        String nickname = FormUtil.getString(req, BuyerFieldConstantDeclaration.REQUEST_PARAM_LOGIN, ValidationRegex.REGEX_LOGIN);
        String password = FormUtil.getString(req,BuyerFieldConstantDeclaration.REQUEST_PARAM_PASS, ValidationRegex.REGEX_PASS);
        String email = FormUtil.getString(req,BuyerFieldConstantDeclaration.REQUEST_PARAM_EMAIL, ValidationRegex.REGEX_EMAIL);       
        String mobile = FormUtil.getString(req,BuyerFieldConstantDeclaration.REQUEST_PARAM_MOBILE, ValidationRegex.REGEX_MOBILE);
        String address = FormUtil.getString(req,BuyerFieldConstantDeclaration.REQUEST_PARAM_ADDRESS, ValidationRegex.REGEX_ALL_SYMBOL);
        
        Buyer newBuyer=DAOFactory.getDAO().buyerDAO.read(nickname, password);
        
        if(newBuyer==null)
        {

        newBuyer = Buyer.newBuilder().setNickname(nickname)
        		.setPassword(password)
        		.setEmail(email)
        		.setMobile(mobile)
        		.setAddress(address)
        		.setRoleId(2)
        		.build();      		

        DAOFactory dao = DAOFactory.getDAO();
        dao.buyerDAO.create(newBuyer);

        if (newBuyer.getNickname() !=null){
            req.setAttribute(MessageConstantDeclaration.MSG_MESSAGE, "Пользователь зарегестрирован");
            HttpSession session = req.getSession();
            session.setAttribute("buyer", newBuyer);
            return Actions.LOGIN.command;
        }

        else {
            req.setAttribute(MessageConstantDeclaration.MSG_ERROR, "Ошибка добавления пользователя");
            return null;
        }
        }
        else {
            req.setAttribute(MessageConstantDeclaration.MSG_ERROR, "Такой пользователь уже существует!");
            return null;
        }

    }
}