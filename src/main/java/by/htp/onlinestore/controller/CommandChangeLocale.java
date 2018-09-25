package by.htp.onlinestore.controller;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.onlinestore.exception.ValidateParamException;
import by.htp.onlinestore.util.FormUtil;
import by.htp.onlinestore.util.constants.MessageConstantDeclaration;
import by.htp.onlinestore.util.constants.WebConstantDeclaration;
/**
 * Class CommandChangeLocale implementing Command interface
 * 
 * @author Sendetskaya Iryna
 *
 */
public class CommandChangeLocale extends Command {
	
	private static final String LOCALE_MESSAGES = "localization.local";
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	Command execute(HttpServletRequest request, HttpServletResponse response) throws Exception {	
		
		String locale = request.getParameter(WebConstantDeclaration.REQUEST_PARAM_LOCALE);
		try {
			FormUtil.validateRequestParamLocale(locale);
			String[] localArr = locale.split("_");
			ResourceBundle.getBundle(LOCALE_MESSAGES, new Locale(localArr[0], localArr[1]));
			request.getSession().setAttribute(WebConstantDeclaration.SESSION_PARAM_CURRENT_LOCALES, locale);
			return NameCommands.INDEX.command;
		} catch (ValidateParamException e) {
			request.setAttribute(MessageConstantDeclaration.MSG_ERROR,
					"Undefined locale!");
			return NameCommands.ERROR.command;
		}
		
		//return NameCommands.INDEX.command;
	}

}
