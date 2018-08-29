package by.htp.onlinestore.util;

import java.sql.Date;
import java.util.Calendar;

public class CurrentDateUtilClass {

	public CurrentDateUtilClass() {
	}
	
	public static Date returnCurrentDate() {

		Calendar calendar = Calendar.getInstance();
		Date curDate = new Date(calendar.getTime().getTime());
		return curDate;
	}


}
