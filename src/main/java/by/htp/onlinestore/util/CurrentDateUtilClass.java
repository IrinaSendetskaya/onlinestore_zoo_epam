package by.htp.onlinestore.util;

import java.sql.Date;
import java.util.Calendar;

/**
 * Util class provides methods for getting a current date
 * @author Iryna Siandzetskaya
 *
 */
public class CurrentDateUtilClass {

	/**
	 * constructor without parameter
	 */
	public CurrentDateUtilClass() {
	}
	
	/**
	 * static method for getting a current date
	 * @return a current date
	 */
	public static Date returnCurrentDate() {

		Calendar calendar = Calendar.getInstance();
		Date curDate = new Date(calendar.getTime().getTime());
		return curDate;
	}


}
