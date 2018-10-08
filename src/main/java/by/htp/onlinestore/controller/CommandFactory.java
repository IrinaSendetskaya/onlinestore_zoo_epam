package by.htp.onlinestore.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.htp.onlinestore.util.constants.MessageConstantDeclaration;

/**
 * Class factory provides commands
 * 
 * @author Iryna Siandzetskaya
 *
 */
public class CommandFactory {

	private static final Logger logger = LoggerFactory.getLogger(CommandFactory.class);

	/**
	 * Defines a command and compares with ENUM NameCommands
	 * 
	 * @param req
	 * @return defining command
	 */
	Command defineCommand(HttpServletRequest req) {

		Command command = null;
		String action = req.getParameter("command");

		if (action == null || "".equals(action)) {
			action = "Index";
		}

		try {
			NameCommands[] currentActionArray = NameCommands.values();
			for (NameCommands nameCommands : currentActionArray) {
				if (action.equalsIgnoreCase(nameCommands.toString())) {
					NameCommands currentAction = NameCommands.valueOf(action.toUpperCase());				
					command = currentAction.command;
				}
			}

		} catch (IllegalArgumentException e) {
			command = NameCommands.ERROR.command;
			req.setAttribute(MessageConstantDeclaration.MSG_ERROR, "Команда " + action + " неизвестна!");
			req.setAttribute(MessageConstantDeclaration.MSG_MESSAGE, "Команда " + action + " неизвестна!");
			logger.error("IllegalArgumentException in defineCommand method of CommandFactory class", e);
		}
		return command;
	}


}
