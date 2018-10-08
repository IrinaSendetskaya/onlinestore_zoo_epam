package by.htp.onlinestore.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class CommandHome implementing Command interface
 * 
 * @author Sendetskaya Iryna
 *
 */
public class CommandHome extends Command {

	/* (non-Javadoc)
	 * @see by.htp.onlinestore.controller.Command#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	Command execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return NameCommands.HOME.command;
	}

}
