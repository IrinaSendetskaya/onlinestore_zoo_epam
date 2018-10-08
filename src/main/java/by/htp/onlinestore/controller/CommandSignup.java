package by.htp.onlinestore.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp.onlinestore.dao.DAOFactory;
import by.htp.onlinestore.entity.Buyer;
import by.htp.onlinestore.service.ServiceFactory;
import by.htp.onlinestore.util.constants.BuyerFieldConstantDeclaration;
import by.htp.onlinestore.util.constants.EntityNameConstantDeclaration;
import by.htp.onlinestore.util.FormUtil;
import by.htp.onlinestore.util.constants.MessageConstantDeclaration;
import by.htp.onlinestore.util.ValidationRegex;

import java.text.ParseException;

/**
 * Class CommandSignup implementing Command interface
 * 
 * @author Sendetskaya Iryna
 *
 */
public class CommandSignup extends Command {

    /* (non-Javadoc)
     * @see by.htp.onlinestore.controller.Command#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public Command execute(HttpServletRequest req, HttpServletResponse resp) throws ParseException {

        if (!FormUtil.isPost(req))
        	return NameCommands.SIGNUP.command;

        req.setAttribute(MessageConstantDeclaration.MSG_MESSAGE, "Зарегистрируйтесь");
        String nickname = FormUtil.getString(req, BuyerFieldConstantDeclaration.REQUEST_PARAM_LOGIN, ValidationRegex.REGEX_LOGIN);
        String password = FormUtil.getString(req,BuyerFieldConstantDeclaration.REQUEST_PARAM_PASS, ValidationRegex.REGEX_PASS);
        String email = FormUtil.getString(req,BuyerFieldConstantDeclaration.REQUEST_PARAM_EMAIL, ValidationRegex.REGEX_EMAIL);       
        String mobile = FormUtil.getString(req,BuyerFieldConstantDeclaration.REQUEST_PARAM_MOBILE, ValidationRegex.REGEX_MOBILE);
        String address = FormUtil.getString(req,BuyerFieldConstantDeclaration.REQUEST_PARAM_ADDRESS, ValidationRegex.REGEX_ALL_SYMBOL);
        
        Buyer newBuyer=ServiceFactory.getService().getBuyerDAO().getBuyerByLoginAndPassword(nickname, password);
        
        if(newBuyer==null&&DAOFactory.getDao().getBuyerDAO().readByLogin(nickname)==null
        		&&DAOFactory.getDao().getBuyerDAO().readByPassword(password)==null
        		&&DAOFactory.getDao().getBuyerDAO().readByEmail(email)==null)
        {

        newBuyer = Buyer.newBuilder().setNickname(nickname)
        		.setPassword(password)
        		.setEmail(email)
        		.setMobile(mobile)
        		.setAddress(address)
        		.setRoleId(2)
        		.build();      		

       ServiceFactory.getService().getBuyerDAO().create(newBuyer);

        if (newBuyer.getNickname() !=null){
            req.setAttribute(MessageConstantDeclaration.MSG_MESSAGE, MessageConstantDeclaration.MSG_CHECK_SIGNUP_OK);
            HttpSession session = req.getSession();
            session.setAttribute(EntityNameConstantDeclaration.REQUEST_PARAM_BUYER, newBuyer);
            return NameCommands.LOGIN.command;
        }

        else {
            req.setAttribute(MessageConstantDeclaration.MSG_MESSAGE, MessageConstantDeclaration.MSG_CHECK_SIGNUP_ERROR);
            return NameCommands.SIGNUP.command;
        }
        }
        else {
            req.setAttribute(MessageConstantDeclaration.MSG_MESSAGE, MessageConstantDeclaration.MSG_CHECK_SIGNUP_LOGIN_NOT_FREE);
            return NameCommands.SIGNUP.command;
        }

    }
}