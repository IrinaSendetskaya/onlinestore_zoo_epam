package by.htp.onlinestore.util;
/**
 * Util class provides a CheckBuyer method
 * @author Iryna Siandzetskaya
 *
 */
public class CheckBuyerUtil {

	/**
	 * constructor without parameter
	 */
	private CheckBuyerUtil() {
	
	}

	/**
	 * constants
	 */
	private static final String SIGN_UP_CHECK_USER_DATA_RESULT_STRING = "<span style=\"color:%s;\">%s</span>";
	private static final String SIGN_UP_CHECK_USER_DATA_ERROR_COLOR = "#FF0000";
	private static final String SIGN_UP_CHECK_USER_DATA_SUCCESS_COLOR = "#008B00";

	/**
	 * paints result of user data check to red or green color
	 * 
	 * @param result
	 * @param isSuccess
	 * @return colored result message
	 */
	public static String styleCheckUserDataResult(String result, boolean isSuccess) {
		return String.format(SIGN_UP_CHECK_USER_DATA_RESULT_STRING,
				isSuccess ? SIGN_UP_CHECK_USER_DATA_SUCCESS_COLOR : SIGN_UP_CHECK_USER_DATA_ERROR_COLOR, result);
	}
}
