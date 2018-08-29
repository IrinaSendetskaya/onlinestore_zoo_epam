package by.htp.onlinestore.controller;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp.onlinestore.dao.DAOFactory;
import by.htp.onlinestore.entity.Buyer;
import by.htp.onlinestore.util.constants.BuyerFieldConstantDeclaration;
import by.htp.onlinestore.util.constants.EntityNameConstantDeclaration;
import by.htp.onlinestore.util.FormUtil;
import by.htp.onlinestore.util.constants.MessageConstantDeclaration;
import by.htp.onlinestore.util.ValidationRegex;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;

public class CommandLogin extends Command{
	
    @Override
    public Command execute(HttpServletRequest req, HttpServletResponse resp) throws ParseException, SQLException, NoSuchPaddingException, BadPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, UnsupportedEncodingException, InvalidKeyException {

        if (!FormUtil.isPost(req))
            return null;

        if (req.getParameter(BuyerFieldConstantDeclaration.REQUEST_PARAM_LOGIN).equals("")) {
            req.setAttribute(MessageConstantDeclaration.MSG_MESSAGE, "Введите имя пользователя и пароль");
            return NameCommands.LOGIN.command;
        }

        String nickname= FormUtil.getString(req,BuyerFieldConstantDeclaration.REQUEST_PARAM_LOGIN, ValidationRegex.REGEX_LOGIN);
        String password=FormUtil.getString(req,BuyerFieldConstantDeclaration.REQUEST_PARAM_PASS,ValidationRegex.REGEX_PASS);

        Buyer currentBuyer=DAOFactory.getDAO().buyerDAO.read(nickname, password); 

        if (currentBuyer.getId() > 0) {
            req.setAttribute(MessageConstantDeclaration.MSG_MESSAGE, "пользователь " + nickname + " найден");
            req.setAttribute(BuyerFieldConstantDeclaration.REQUEST_PARAM_ROLE_ID, currentBuyer.getRoleId());
            HttpSession session = req.getSession();
            session.setAttribute(EntityNameConstantDeclaration.REQUEST_PARAM_BUYER,currentBuyer);
           // Cookies.setCookie(resp, list.get(0));
            return NameCommands.INDEX.command;
        }
        else {
            req.setAttribute(MessageConstantDeclaration.MSG_ERROR, "пользователь " + nickname + " НЕ найден");
            req.setAttribute(MessageConstantDeclaration.MSG_ERROR, "Неверный логин или пароль");
            return NameCommands.LOGIN.command;
        }
    }
}