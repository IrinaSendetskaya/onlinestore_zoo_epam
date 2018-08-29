package by.htp.onlinestore.util;

import java.awt.print.PageFormat;
import java.awt.print.Pageable;
import java.awt.print.Printable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.htp.onlinestore.entity.Good;
import by.htp.onlinestore.util.constants.ListConstantDeclaration;

public class PaginationUtilClass implements Pageable {

	public PaginationUtilClass() {

	}

	@Override
	public int getNumberOfPages() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public PageFormat getPageFormat(int arg0) throws IndexOutOfBoundsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Printable getPrintable(int arg0) throws IndexOutOfBoundsException {
		// TODO Auto-generated method stub
		return null;
	}
	
	static<Type> Type findInSession(HttpServletRequest req, String name){
        Type result=null;
        Object object = req.getSession().getAttribute(name);
        if (object!=null){
            result=(Type)object;
        }
        return result;
    }
	
	public static int makePagination(HttpServletRequest req, List<Good> listPages) {
		
		req.setAttribute("goodsSize", listPages.size());
		String strStart = req.getParameter("start");
		int startGood = 0;
		if (strStart != null) {
			startGood = Integer.parseInt(strStart);
		} 
		return startGood;
	}
	
	

}
