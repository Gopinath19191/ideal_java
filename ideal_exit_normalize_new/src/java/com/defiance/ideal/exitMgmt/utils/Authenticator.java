package com.defiance.ideal.exitMgmt.utils;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Authenticator implements Filter {

        private String allowedURL;
        private String allowedURL2;
        private String allowedURL3;
        private transient FilterConfig filterCfg = null;
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest requestObj = (HttpServletRequest) req;
		HttpServletResponse responseObj = (HttpServletResponse) res;
//              Get the IP address of client machine.
//		String ipAddress = requestObj.getRemoteAddr();
                String url = requestObj.getServletPath();
		//Log the IP address and current timestamp.
                System.out.println("mySession = "+requestObj.getSession().getAttribute("employeeId"));
                System.out.println("testParam = "+allowedURL);
                System.out.println("testParam2= "+allowedURL2);
                System.out.println("testParam3= "+allowedURL3);

                if(requestObj.getSession().getAttribute("employeeId")==null && !url.contains(allowedURL) && !url.contains(allowedURL2) && !url.contains(allowedURL3)){
           //        System.out.println(":::::::::::::::::::"+requestObj.getContextPath()+"/redirect.htm");
                     filterCfg.getServletContext().getRequestDispatcher("/redirect.htm").forward(requestObj, responseObj);
                }
//              System.out.println("IP "+ipAddress + ",Time "+ new Date().toString());
                System.out.println("inFilter");
                chain.doFilter(requestObj, responseObj);
	}
        
	public void init(final FilterConfig config) throws ServletException {

		//Get init parameter
		allowedURL = config.getInitParameter("allowedURL");
		allowedURL2 = config.getInitParameter("allowedURL2");
		allowedURL3 = config.getInitParameter("allowedURL3");
                filterCfg = config;
		//Print the init parameter
//		System.out.println("Test Param: " + allowedURL);
	}
	public void destroy() {
		//add code to release any resource
	}
}
