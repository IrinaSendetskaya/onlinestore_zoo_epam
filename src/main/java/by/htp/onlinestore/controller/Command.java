package by.htp.onlinestore.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

abstract class Command {

    abstract  Command execute(HttpServletRequest request, HttpServletResponse response) throws Exception;

    String getJsp(){
    	
    	String inputAction=this.toString().toLowerCase();
    	if("index".equalsIgnoreCase(inputAction)|| "".equalsIgnoreCase(inputAction)) {
    		return "/"+inputAction+".jsp";
    	}
        return "/WEB-INF/views/"+inputAction+".jsp";
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName().replaceFirst("Command","");

    }
}
