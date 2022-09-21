package com.ideal.shared;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class Authenticator implements Filter {

    private String allowedURL;
    private String allowedURL2;

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest requestObj = (HttpServletRequest) req;
        HttpServletResponse responseObj = (HttpServletResponse) res;
        String ipAddress = requestObj.getRemoteAddr();
        String url = requestObj.getServletPath();
        if (!url.contains("unauthorised") && !url.contains(allowedURL2) && !url.contains(allowedURL) && requestObj.getSession().getAttribute("EMP_ID") == null) {
            responseObj.sendRedirect(requestObj.getContextPath() + "/authenticate.htm");
        }
        chain.doFilter(requestObj, responseObj);
    }

    public void init(FilterConfig config) throws ServletException {
        allowedURL = config.getInitParameter("allowedURL");
        allowedURL2 = config.getInitParameter("allowedURL2");
    }

    public void destroy() {    
    }

}
