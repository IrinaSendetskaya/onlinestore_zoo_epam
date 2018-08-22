package by.htp.onlinestore.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.htp.onlinestore.util.MessageConstantDeclaration;

import java.io.IOException;

public class FrontController extends HttpServlet {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(FrontController.class);

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            process(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
            process(req,resp);
    }

    private void process (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ActionFactory actionFactory=new ActionFactory();
        Action command = actionFactory.defineCommand(req);
        Action nextStep=null;
        RequestDispatcher dispatcher=null;
        ServletContext servletContext=getServletContext();

        try {
            nextStep = command.execute(req,resp);
        } catch (Exception e) {
            req.setAttribute(MessageConstantDeclaration.MSG_ERROR,"Ошибка:"+e.getMessage());
            String errorJsp=Actions.ERROR.command.getJsp();
            dispatcher=servletContext.getRequestDispatcher(errorJsp);
            logger.error("Exception in process method of FrontController class", e);
        }
        if(nextStep==null||nextStep==command)
        {
            String viewJsp=command.getJsp();
            dispatcher=servletContext.getRequestDispatcher(viewJsp);
            dispatcher.forward(req,resp);
        }
        else //redirect
        {
            resp.sendRedirect("do?command="+nextStep);
        }

    }
}
