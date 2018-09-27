package by.htp.onlinestore.util;

import java.util.List;

import javax.servlet.http.HttpServletRequest;


/**
 * Util class provides methods for getting a start page
 * @author Iryna Siandzetskaya
 *
 */
public class PaginationUtilClass {

	/**
	 * constructor without parameter
	 */
	public PaginationUtilClass() {

	}

	/**
	 * static method for getting a start page
	 * @param req
	 * @param listPages
	 * @return
	 */
	public static <T> int makePagination(HttpServletRequest req, List<T> listPages) {
		
		req.setAttribute("goodsSize", listPages.size());
		String strStart = req.getParameter("start");
		int startGood = 0;
		if (strStart != null) {
			startGood = Integer.parseInt(strStart);
		} 
		return startGood;
	}
	
	

}
