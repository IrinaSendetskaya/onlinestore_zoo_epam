package by.htp.onlinestore.listeners;

import java.util.Locale;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import by.htp.onlinestore.util.constants.WebConstantDeclaration;
/**
 * Listener class for setting default locale in session
 * 
 * @author Sendetskaya Iryna
 *
 */
public class LocaleListener implements HttpSessionListener {
	
	/**
	 * Default locale instance
	 */
	private static Locale defaultLocale = new Locale("ru", "BY");

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		se.getSession().setAttribute(WebConstantDeclaration.SESSION_PARAM_CURRENT_LOCALES, defaultLocale);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		HttpSessionListener.super.sessionDestroyed(se);
	}
	
	

}
