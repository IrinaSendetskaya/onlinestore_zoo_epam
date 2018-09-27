package by.htp.onlinestore.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Abstract class provides a execute Command method
 * @author Iryna Siandzetskaya
 *
 */
abstract class Command {

    /**
     * gets http request and forms http response
     * @param request
     * @param response
     * @return a command
     * @throws Exception
     */
    abstract  Command execute(HttpServletRequest request, HttpServletResponse response) throws Exception;

    /**
     * @return a transforming string jsp
     */
    String getJsp(){
    	
    	String inputAction=this.toString().toLowerCase();
    	if("index".equalsIgnoreCase(inputAction)|| "".equalsIgnoreCase(inputAction)) {
    		return "/"+inputAction+".jsp";
    	}
        return "/WEB-INF/views/"+inputAction+".jsp";
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return this.getClass().getSimpleName().replaceFirst("Command","");

    }
}
