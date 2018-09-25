package by.htp.onlinestore.util;

import javax.servlet.http.HttpServletRequest;

import by.htp.onlinestore.exception.ValidateParamException;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class FormUtil {
	
	//private static final Logger logger = LoggerFactory.getLogger(FormUtil.class);

   public static String getString(HttpServletRequest req, String field, String regxp) throws ParseException {
        String value=req.getParameter(field);
        if(value.matches(regxp))
            return value;
        else
        {
            throw new ParseException("Неверные данные. Вводите только корректные символы!",1);
            //logger.error("IllegalArgumentException in ParseException method of FormUtil class");
        }
    }
   
   public static List<String> getStringToArray(HttpServletRequest req, String field, String regxp) throws ParseException {
       List<String> valueList=new ArrayList<>();
	   String [] value=req.getParameterValues(field);
       for (String string : value) {
    	   if(string.matches(regxp)) {
    		   valueList.add(string);
    	   }  
           else
           {
               throw new ParseException("Неверные данные. Вводите только корректные символы!",1);
           }
	}
       return valueList;  
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
        Double doubleValue=Double.parseDouble(value);
        return BigDecimal.valueOf(doubleValue);
    }

   public static boolean isPost(HttpServletRequest reg){
        return reg.getMethod().equalsIgnoreCase("POST");
    }
   
   public static String fixGoogleDriveUrl(String url) {
		if (url.contains("open?id="))
			url = url.replace("open?id=", "uc?id=");
		if (url.contains("file/d/"))
			url = url.replace("file/d/", "uc?id=");
		if (url.contains("/edit?usp=sharing"))
			url = url.replace("/edit?usp=sharing", "");
		if (url.contains("/view?usp=sharing"))
			url = url.replace("/view?usp=sharing", "");
		return url;
	}
   
   public static void validateRequestParamLocale(String locale) {
		if (locale == null || !Pattern.matches(ValidationRegex.REGEX_LOCALE_PARAM, locale))
			throw new ValidateParamException("Undefined locale!");
	}

  
}
