package by.htp.onlinestore.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class CommandLogout implementing Command interface
 * 
 * @author Sendetskaya Iryna
 *
 */
public class CommandLogout extends Command {
	
    /* (non-Javadoc)
     * @see by.htp.onlinestore.controller.Command#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public Command execute(HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().invalidate();
        return NameCommands.LOGIN.command;
    }
}