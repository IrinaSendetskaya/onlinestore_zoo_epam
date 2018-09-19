package by.htp.onlinestore.util;

import javax.servlet.http.HttpServletRequest;


public class SessionUtilClass {

	public SessionUtilClass() {

	}
	
	public static<Type> Type findInSession(HttpServletRequest req, String name){
        Type result=null;
        Object object = req.getSession().getAttribute(name);
        if (object!=null){
            result=(Type) object;
        }
        return result;
    }

}
