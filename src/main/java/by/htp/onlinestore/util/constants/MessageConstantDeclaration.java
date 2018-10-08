package by.htp.onlinestore.util.constants;

/**
 * Util class provides methods for declaration a message constants
 * @author Iryna Siandzetskaya
 *
 */
public final class MessageConstantDeclaration {

	/**
	 * constructor without parameter
	 */
	private MessageConstantDeclaration() {

	}

	public static final String MSG_SUCCESS = "msg_success";
	public static final String MSG_ERROR = "error";
	public static final String MSG_MESSAGE = "message";
	
	public static final String MSG_CHECK_IF_LOGIN_FREE = "Этот логин свободен";
	public static final String MSG_CHECK_IF_LOGIN_VALIDE = "Логин должен иметь только буквы и цифры, размер от 3 до 15 символов";
	public static final String MSG_CHECK_IF_LOGIN_NOT_FREE = "Пользователь с таким логином уже зарегистрирован!";
	
	public static final String MSG_CHECK_IF_PASS_FREE = "Этот пароль свободен";
	public static final String MSG_CHECK_IF_PASS_VALIDE = "Пароль должен иметь только латинские буквы и цифры, размер от 4 до 12 символов";
	public static final String MSG_CHECK_IF_PASS_NOT_FREE = "Пользователь с таким паролем уже зарегистрирован!";

	public static final String MSG_CHECK_IF_EMAIL_FREE = "Этот email свободен";
	public static final String MSG_CHECK_IF_EMAIL_VALIDE = "email должен иметь только латинские буквы и цифры, соответствующей email структуры";
	public static final String MSG_CHECK_IF_EMAIL_NOT_FREE = "Пользователь с таким email уже зарегистрирован!";
	
	public static final String MSG_CHECK_SIGNUP_OK = "Пользователь зарегестрирован";
	public static final String MSG_CHECK_SIGNUP_ERROR = "Ошибка добавления пользователя";
	public static final String MSG_CHECK_SIGNUP_VALIDE = "Логин или пароль не верной структуры";
	public static final String MSG_CHECK_SIGNUP_LOGIN_NOT_FREE = "Такой пользователь уже существует!";
	
	public static final String MSG_CHECK_LOGIN_OK = "Пользователь найден";
	public static final String MSG_CHECK_LOGIN_ERROR = "Неверный логин или пароль!";
	public static final String MSG_CHECK_LOGIN_NOT_FREE = "Пользователь не найден!";

}
