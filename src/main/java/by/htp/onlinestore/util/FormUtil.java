package by.htp.onlinestore.util;

import javax.servlet.http.HttpServletRequest;

import by.htp.onlinestore.exception.ValidateParamException;
import by.htp.onlinestore.util.constants.MessageConstantDeclaration;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Util class provides methods for parsing data
 * 
 * @author Iryna Siandzetskaya
 *
 */
public class FormUtil {

	/**
	 * parsing to validate the string
	 * @param req
	 * @param field
	 * @param regxp
	 * @return parsing data
	 * @throws ParseException
	 */
	public static String getString(HttpServletRequest req, String field, String regxp) throws ParseException {
		String value = req.getParameter(field);
		if (value.matches(regxp))
			return value;
		else {
			req.setAttribute(MessageConstantDeclaration.MSG_MESSAGE,"Неверные данные. Вводите только корректные символы!");
			throw new ParseException("Неверные данные. Вводите только корректные символы!", 1);		
		}
	}

	/**
	 * parsing to validate array of string
	 * @param req
	 * @param field
	 * @param regxp
	 * @return parsing list
	 * @throws ParseException
	 */
	public static List<String> getStringToArray(HttpServletRequest req, String field, String regxp)
			throws ParseException {
		List<String> valueList = new ArrayList<>();
		String[] value = req.getParameterValues(field);
		for (String string : value) {
			if (string.matches(regxp)) {
				valueList.add(string);
			} else {
				req.setAttribute(MessageConstantDeclaration.MSG_MESSAGE,"Неверные данные. Вводите только корректные символы!");
				throw new ParseException("Неверные данные. Вводите только корректные символы!", 1);
			}
		}
		return valueList;
	}

	/**
	 * parsing to validate Integer
	 * @param req
	 * @param field
	 * @return parsing data
	 * @throws ParseException
	 */
	public static int getInt(HttpServletRequest req, String field) throws ParseException {
		String value = req.getParameter(field);
		return Integer.parseInt(value);
	}

	/**
	 * parsing to validate Double
	 * @param req
	 * @param field
	 * @return parsing data
	 * @throws ParseException
	 */
	public static double getDouble(HttpServletRequest req, String field) throws ParseException {
		String value = req.getParameter(field);
		return Double.parseDouble(value);
	}

	/**
	 * parsing to validate BigDecimal
	 * @param req
	 * @param field
	 * @return parsing data
	 * @throws ParseException
	 */
	public static BigDecimal getBigDecimal(HttpServletRequest req, String field) throws ParseException {
		String value = req.getParameter(field);
		BigDecimal bigValue = new BigDecimal(value);
		return bigValue;
	}

	/**
	 * gets if POST query
	 * @param reg
	 * @return true or false
	 */
	public static boolean isPost(HttpServletRequest reg) {
		return reg.getMethod().equalsIgnoreCase("POST");
	}

	/**
	 * Transforms url string of images 
	 * @param url
	 * @return a transforming url
	 */
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

	/**
	 * validates a locale
	 * @param locale
	 */
	public static void validateRequestParamLocale(HttpServletRequest req, String locale) {
		if (locale == null || !Pattern.matches(ValidationRegex.REGEX_LOCALE_PARAM, locale))
		{
			req.setAttribute(MessageConstantDeclaration.MSG_MESSAGE,"Неизвестный язык!");
			throw new ValidateParamException("Undefined locale!");
		}
			
	}

}
