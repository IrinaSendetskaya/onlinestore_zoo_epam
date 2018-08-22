package by.htp.onlinestore.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ActionFactory {
	
	private static final Logger logger = LoggerFactory.getLogger(ActionFactory.class);

    Action defineCommand(HttpServletRequest request){
    	
    	

        Action command;
        String action=request.getParameter("command");
        if(action==null)
        {
            action="Index";
        }
        try {
            Actions currentEnum = Actions.valueOf(action.toUpperCase());
            command=currentEnum.command;
        }
        catch (IllegalArgumentException e)
        {
            command=Actions.ERROR.command;    //по умолчанию будет стр error
            logger.error("IllegalArgumentException in defineCommand method of ActionFactory class", e);
        }

        return command;
    }
}
