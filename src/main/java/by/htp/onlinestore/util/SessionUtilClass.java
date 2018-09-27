package by.htp.onlinestore.util;

import javax.servlet.http.HttpServletRequest;


/**
 * Util class provides methods for finding data in session
 * @author Iryna Siandzetskaya
 *
 */
public class SessionUtilClass {

	/**
	 * constructor without parameter
	 */
	public SessionUtilClass() {

	}
	
	/**
	 * finds data in session
	 * @param req
	 * @param name
	 * @return a data in session
	 */
	public static<T> T findInSession(HttpServletRequest req, String name){
        T result=null;
        Object object = req.getSession().getAttribute(name);
        if (object!=null){
            result=(T)object;
        }
        return result;
    }

}
