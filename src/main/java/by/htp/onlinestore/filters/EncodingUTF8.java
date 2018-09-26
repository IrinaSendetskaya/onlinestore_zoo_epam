package by.htp.onlinestore.filters;

import javax.servlet.*;
import java.io.IOException;

/**
 * Encoding class for filters
 * 
 * @author Sendetskaya Iryna
 *
 */
public class EncodingUTF8  implements Filter{
    private String encoding;

    /* (non-Javadoc)
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        encoding=filterConfig.getInitParameter("encoding");
    }

    /* (non-Javadoc)
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        if (encoding!=null){
            if(!encoding.equalsIgnoreCase(servletRequest.getCharacterEncoding())){
                servletRequest.setCharacterEncoding(encoding);
                }
                if(!encoding.equalsIgnoreCase(servletResponse.getCharacterEncoding())){
                servletResponse.setCharacterEncoding(encoding);
                }
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    /* (non-Javadoc)
     * @see javax.servlet.Filter#destroy()
     */
    @Override
    public void destroy() {
        encoding=null;

    }
}
