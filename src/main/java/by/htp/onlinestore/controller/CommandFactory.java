package by.htp.onlinestore.controller;


import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.htp.onlinestore.util.constants.MessageConstantDeclaration;

public class CommandFactory {
	
	private static final Logger logger = LoggerFactory.getLogger(CommandFactory.class);

    Command defineCommand(HttpServletRequest req){

        Command command;
        String action=req.getParameter("command");
        
        if(action==null || "".equals(action))
        {
            action="Index";
        }
        
        try {
        	//if()
        	//добавить проверку на неизв строку
        	NameCommands currentAction = NameCommands.valueOf(action.toUpperCase());          
            command=currentAction.command;
        }
        catch (IllegalArgumentException e)
        {
            command=NameCommands.ERROR.command;   
            req.setAttribute(MessageConstantDeclaration.MSG_ERROR, "Команда " + action + " неизвестна!");
            logger.error("IllegalArgumentException in defineCommand method of CommandFactory class", e);
        }

        return command;
    }
}
