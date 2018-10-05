package by.htp.onlinestore.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.htp.onlinestore.util.constants.MessageConstantDeclaration;

import java.io.IOException;

/**
 * Controller class for processing requests
 * @author Iryna Siandzetskaya
 *
 */
public class FrontController extends HttpServlet {
	
    /**
	 * An unique serial version identifier
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(FrontController.class);

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            process(req,resp);
    }

    /* (non-Javadoc)
     * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
            process(req,resp);
    }

    /**
     * defines a command and executes the desires logic Command after then forwards or redirects on the desired jsp-pages
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void process (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	CommandFactory actionFactory=new CommandFactory();
        Command command = actionFactory.defineCommand(req);
        Command nextStep=null;
        RequestDispatcher dispatcher=null;
        ServletContext servletContext=getServletContext();

        try {
            nextStep = command.execute(req,resp);
        } catch (Exception e) {
            req.setAttribute(MessageConstantDeclaration.MSG_ERROR,"Ошибка:"+e.getMessage());
            req.setAttribute(MessageConstantDeclaration.MSG_MESSAGE,"Ошибка:"+e.getMessage());
            String errorJsp=NameCommands.ERROR.command.getJsp();
            dispatcher=servletContext.getRequestDispatcher(errorJsp);
            logger.error("Exception in process method of FrontController class", e);
            dispatcher.forward(req,resp);
        }
        if(nextStep==null || nextStep.equals(command))
        {
            String viewJsp=command.getJsp();
            dispatcher=servletContext.getRequestDispatcher(viewJsp);
            dispatcher.forward(req,resp); 
        }
        /**
    	 * redirect a command
    	 */
        else
        {
            resp.sendRedirect("do?command="+nextStep);
        }

    }
}
