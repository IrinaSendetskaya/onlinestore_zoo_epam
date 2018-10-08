package by.htp.onlinestore.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.onlinestore.service.BuyerService;
import by.htp.onlinestore.service.ServiceFactory;
import by.htp.onlinestore.util.constants.BuyerFieldConstantDeclaration;
import by.htp.onlinestore.util.constants.MessageConstantDeclaration;

public class CommandCheckSignup extends Command{

	/**
	 * userService field
	 */
	private BuyerService buyerService = ServiceFactory.getService().getBuyerDAO();
	
	public Command execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String checkingParamValue = null;
		PrintWriter out = response.getWriter();
		if ((checkingParamValue = request.getParameter(BuyerFieldConstantDeclaration.REQUEST_PARAM_LOGIN)) != null) {
			out.write(buyerService.checkBuyerByLogin(checkingParamValue));
		} else if ((checkingParamValue = request.getParameter(BuyerFieldConstantDeclaration.REQUEST_PARAM_EMAIL)) != null) {
			out.write(buyerService.checkBuyerByEmail(checkingParamValue));
		} else if ((checkingParamValue = request.getParameter(BuyerFieldConstantDeclaration.REQUEST_PARAM_PASS)) != null) {
			out.write(buyerService.checkBuyerByPassword(checkingParamValue));
		} else {
			request.setAttribute(MessageConstantDeclaration.MSG_MESSAGE,
					MessageConstantDeclaration.MSG_CHECK_SIGNUP_ERROR);
			request.getRequestDispatcher(NameCommands.SIGNUP.command.getJsp()).forward(request, response);
		}
		return null; 
	}	

}
