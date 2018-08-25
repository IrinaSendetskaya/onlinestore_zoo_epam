package by.htp.onlinestore.util;

public final class ValidationRegex {

	
	private ValidationRegex() {

	}
	
	public static final String REGEX_LOGIN = "[\\wА-Яа-я]+";
	public static final String REGEX_EMAIL = "[\\w]+[@]{1}[a-z]{3,6}\\.[a-z]{2,3}";
	public static final String REGEX_PASS = "[\\w]{4,12}";
	public static final String REGEX_ALL_SYMBOL = "[\\w\\s\\/А-Яа-яЁё&.,!\"№%:;()+=?—-]+";
	public static final String REGEX_MOBILE="[\\+]+[\\d]{12}";
	public static final String REGEX_SYMBOL_DIGITAL_AND_ = "[\\w]+";

}
