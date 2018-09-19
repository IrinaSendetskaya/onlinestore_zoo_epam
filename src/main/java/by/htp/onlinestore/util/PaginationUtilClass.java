package by.htp.onlinestore.util;

import java.util.List;

import javax.servlet.http.HttpServletRequest;


public class PaginationUtilClass {

	public PaginationUtilClass() {

	}

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
