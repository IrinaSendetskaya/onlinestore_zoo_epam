package by.htp.onlinestore.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class CommandError implementing Command interface
 * 
 * @author Sendetskaya Iryna
 */
public class CommandError extends Command{
	
    /* (non-Javadoc)
     * @see by.htp.onlinestore.controller.Command#execute(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public Command execute(HttpServletRequest req, HttpServletResponse resp) {
        return null;
    }
}
