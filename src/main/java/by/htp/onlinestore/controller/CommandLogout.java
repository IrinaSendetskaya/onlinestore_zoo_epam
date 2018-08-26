package by.htp.onlinestore.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommandLogout extends Command {
	
    @Override
    public Command execute(HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().invalidate();
        return NameCommands.LOGIN.command;
    }
}