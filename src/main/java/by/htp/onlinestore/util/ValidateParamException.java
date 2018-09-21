package by.htp.onlinestore.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Exception class
 * 
 * @author Sendetskaya Iryna
 *
 */

public class ValidateParamException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 446175648349264409L;

	private static final Logger logger = LoggerFactory.getLogger(ValidateParamException.class);

	/**
	 * constructor without parameters
	 */
	public ValidateParamException() {
	}

	/**
	 * constructor with parameters
	 * 
	 * @param message
	 *            the detail message.
	 * @param cause
	 *            the cause
	 */
	public ValidateParamException(String message, Throwable cause) {
		super(message, cause);
		logger.error(message + " " + cause);
	}

	/**
	 * constructor with parameter
	 * 
	 * @param message
	 *            the detail message.
	 */
	public ValidateParamException(String message) {
		super(message);
		logger.error(message);
	}

	/**
	 * constructor with parameter
	 * 
	 * @param cause
	 *            the cause
	 */
	public ValidateParamException(Throwable cause) {
		super(cause);
		logger.error("{}", cause);
	}
}
