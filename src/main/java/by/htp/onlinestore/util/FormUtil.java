package by.htp.onlinestore.util;

import javax.servlet.http.HttpServletRequest;


import java.math.BigDecimal;
import java.text.ParseException;

public class FormUtil {
	
	//private static final Logger logger = LoggerFactory.getLogger(FormUtil.class);

   public static String getString(HttpServletRequest req, String field, String regxp) throws ParseException {
        String value=req.getParameter(field);
        if(value.matches(regxp))
            return value;
        else
        {
            throw new ParseException("Неверные данные",1);
            //logger.error("IllegalArgumentException in ParseException method of FormUtil class");
        }
    }
   public static int getInt(HttpServletRequest req, String field) throws ParseException {
        String value=req.getParameter(field);
        return Integer.parseInt(value);
    }
   public static double getDouble(HttpServletRequest req, String field) throws ParseException {
        String value=req.getParameter(field);
        return Double.parseDouble(value);
    }
   public static BigDecimal getBigDecimal(HttpServletRequest req, String field) throws ParseException {
        String value=req.getParameter(field);
        return BigDecimal.valueOf(Double.parseDouble(value));
    }

   public static boolean isPost(HttpServletRequest reg){
        return reg.getMethod().equalsIgnoreCase("POST");
    }
}
