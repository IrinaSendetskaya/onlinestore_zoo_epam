package by.htp.onlinestore.listeners;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

public class SessionAttributeListener implements HttpSessionAttributeListener {
    public void attributeRemoved(HttpSessionBindingEvent ev) {
        // обработка события
        System.out.println(String.format("Session removed %s : %s",ev.getName(), ev.getValue()));
    }

    public void attributeAdded(HttpSessionBindingEvent ev) {
        // обработка события
        System.out.println(String.format("Session add %s : %s",ev.getName(), ev.getValue()));
    }

    public void attributeReplaced(HttpSessionBindingEvent ev) {
        // обработка события
        System.out.println(String.format("Session replaced %s : %s",ev.getName(), ev.getValue()));
    }
}
/* Аналогично можно подключить другие слушатели.


Интерфейсы слушателей:
ServletContextListener contextInitialized(ServletContextEvent e)
       contextDestroyed(ServletContextEvent e)
HttpSessionListener
       sessionCreated(HttpSessionEvent e)
       sessionDestroyed(HttpSessionEvent e)
ServletContextAttributeListener
       attributeAdded(ServletContextAttributeEvent e)
       attributeRemoved(ServletContextAttributeEvent e)
       attributeReplaced(ServletContextAttributeEvent e)
HttpSessionAttributeListener
       attributeAdded(HttpSessionBindingEvent e)
       attributeRemoved(HttpSessionBindingEvent e)
       attributeReplaced(HttpSessionBindingEvent e)
HttpSessionBindingListener
       valueBound(HttpSessionBindingEvent e)
       valueUnbound(HttpSessionBindingEvent e)
HttpSessionActivationListener
   sessionWillPassivate(HttpSessionEvent e)
       sessionDidActivate(HttpSessionEvent e)
ServletRequestListener
       requestDestroyed(ServletRequestEvent e)
       requestInitialized(ServletRequestEvent e)
ServletRequestAttributeListener
       attributeAdded(ServletRequestAttributeEvent e)
       attributeRemoved(ServletRequestAttributeEvent e)
       attributeReplaced(ServletRequestAttributeEvent e)
*/
