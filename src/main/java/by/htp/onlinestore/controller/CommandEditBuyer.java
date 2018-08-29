package by.htp.onlinestore.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.onlinestore.dao.DAOFactory;
import by.htp.onlinestore.entity.Buyer;
import by.htp.onlinestore.entity.Role;
import by.htp.onlinestore.util.constants.ButtonNameConstantDeclaration;
import by.htp.onlinestore.util.constants.BuyerFieldConstantDeclaration;
import by.htp.onlinestore.util.FormUtil;
import by.htp.onlinestore.util.constants.ListConstantDeclaration;
import by.htp.onlinestore.util.ValidationRegex;

import java.util.List;

class CommandEditBuyer extends Command {
	
    @Override
    Command execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
    	
        if (FormUtil.isPost(req)){
            int id = FormUtil.getInt(req, BuyerFieldConstantDeclaration.REQUEST_PARAM_BUYER_ID);
            String nickname = FormUtil.getString(req, BuyerFieldConstantDeclaration.REQUEST_PARAM_LOGIN, ValidationRegex.REGEX_LOGIN);
            String password = FormUtil.getString(req,BuyerFieldConstantDeclaration.REQUEST_PARAM_PASS, ValidationRegex.REGEX_PASS);
            String email = FormUtil.getString(req,BuyerFieldConstantDeclaration.REQUEST_PARAM_EMAIL, ValidationRegex.REGEX_EMAIL);       
            String mobile = FormUtil.getString(req,BuyerFieldConstantDeclaration.REQUEST_PARAM_MOBILE, ValidationRegex.REGEX_MOBILE);
            String address = FormUtil.getString(req,BuyerFieldConstantDeclaration.REQUEST_PARAM_ADDRESS, ValidationRegex.REGEX_ALL_SYMBOL);
            int roleId = FormUtil.getInt(req,BuyerFieldConstantDeclaration.REQUEST_PARAM_ROLE_ID);

            Buyer buyer = Buyer.newBuilder().setNickname(nickname)
            		.setId(id)
            		.setPassword(password)
            		.setEmail(email)
            		.setMobile(mobile)
            		.setAddress(address)
            		.setRoleId(roleId)
            		.build(); 
            if (req.getParameter(ButtonNameConstantDeclaration.REQUEST_PARAM_BTN_UPDATE_BUYER_FROM_ADMIN)!=null){
                DAOFactory.getDAO().buyerDAO.update(buyer);
            }
            else if (req.getParameter(ButtonNameConstantDeclaration.REQUEST_PARAM_BTN_DELETE_BUYER_FROM_ADMIN)!=null){
                DAOFactory.getDAO().buyerDAO.delete(buyer);
            }
        }
        List<Buyer> buyers = DAOFactory.getDAO().buyerDAO.readAll();
        List<Role> roles = DAOFactory.getDAO().roleDAO.readAll();
        req.setAttribute(ListConstantDeclaration.REQUEST_PARAM_BUYERS_LIST,buyers);
        req.setAttribute(ListConstantDeclaration.REQUEST_PARAM_ROLES_LIST,roles);
        return null;
    }
}