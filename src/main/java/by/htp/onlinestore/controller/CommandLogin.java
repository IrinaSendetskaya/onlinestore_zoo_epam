package by.htp.onlinestore.controller;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp.onlinestore.entity.Buyer;
import by.htp.onlinestore.service.ServiceFactory;
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

/**
 * Class CommandLogin implementing Command interface
 * 
 * @author Sendetskaya Iryna
 *
 */
public class CommandLogin extends Command{
	
    /* (non-Javadoc)
     * @see by.htp.onlinestore.controller.Command#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public Command execute(HttpServletRequest req, HttpServletResponse resp) throws ParseException, SQLException, NoSuchPaddingException, BadPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, UnsupportedEncodingException, InvalidKeyException {

        if (!FormUtil.isPost(req))
        	return NameCommands.LOGIN.command;

        if (req.getParameter(BuyerFieldConstantDeclaration.REQUEST_PARAM_LOGIN).equals("")) {
            req.setAttribute(MessageConstantDeclaration.MSG_MESSAGE, "Введите имя пользователя и пароль");
            return NameCommands.LOGIN.command;
        }

        String nickname= FormUtil.getString(req,BuyerFieldConstantDeclaration.REQUEST_PARAM_LOGIN, ValidationRegex.REGEX_LOGIN);
        String password=FormUtil.getString(req,BuyerFieldConstantDeclaration.REQUEST_PARAM_PASS,ValidationRegex.REGEX_PASS);

        Buyer currentBuyer=ServiceFactory.getService().getBuyerDAO().getBuyerByLoginAndPassword(nickname, password); 

        if (currentBuyer!=null) {
            req.setAttribute(MessageConstantDeclaration.MSG_MESSAGE, "пользователь " + nickname + " найден");
            req.setAttribute(BuyerFieldConstantDeclaration.REQUEST_PARAM_ROLE_ID, currentBuyer.getRoleId());
            HttpSession session = req.getSession();
            session.setAttribute(EntityNameConstantDeclaration.REQUEST_PARAM_BUYER,currentBuyer);
           // Cookies.setCookie(resp, list.get(0));
            return NameCommands.INDEX.command;
        }
        else {
            req.setAttribute(MessageConstantDeclaration.MSG_MESSAGE, "пользователь " + nickname + " НЕ найден");
            req.setAttribute(MessageConstantDeclaration.MSG_MESSAGE, "Неверный логин или пароль");
            return NameCommands.LOGIN.command;
        }
    }
}