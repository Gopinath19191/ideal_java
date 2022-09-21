package com.defiance.ideal.ojf.shared;

import java.io.IOException;
import javax.mail.Session;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;



public class Authenticator implements Filter {

        private transient final Logger logger = Logger.getLogger(this.getClass().getName());

        private String allowedURL;
        private String allowedURL2;
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest requestObj = (HttpServletRequest) req;
		HttpServletResponse responseObj = (HttpServletResponse) res;
                HttpSession sessionObj = requestObj.getSession();
//              Get the IP address of client machine.
		String ipAddress = requestObj.getRemoteAddr();
                String url = requestObj.getServletPath();

                
                if(requestObj.getSession().getAttribute("loginId")==null && !url.contains(allowedURL) && !url.contains(allowedURL2)){
                    responseObj.sendRedirect(requestObj.getContextPath()+"/index.jsp");
                }
               System.out.println("::::"+requestObj.getServletPath()+"--"+requestObj.getContextPath());
                chain.doFilter(requestObj, responseObj);
//                if(!requestObj.getServletPath().contains("/begin.do"))
//                {
//                    sessionObj.removeAttribute("successMsg");
//                }

	}
        
	public void init(FilterConfig config) throws ServletException {

		//Get init parameter
		allowedURL = config.getInitParameter("allowedURL");
		allowedURL2 = config.getInitParameter("allowedURL2");
		//Print the init parameter
	}
	public void destroy() {
		//add code to release any resource
	}
}
