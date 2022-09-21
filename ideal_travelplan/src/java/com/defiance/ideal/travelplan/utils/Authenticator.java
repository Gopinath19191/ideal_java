
package com.defiance.ideal.travelplan.utils;

import java.io.IOException;
import java.util.Date;
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
    
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest requestObj = (HttpServletRequest) req;
        HttpServletResponse responseObj = (HttpServletResponse) res;
        // Get the IP address of client machine.
        //String ipAddress = requestObj.getRemoteAddr();
        String url = requestObj.getServletPath();

        if( ( requestObj.getSession().getAttribute("employeeId")==null || requestObj.getSession().getAttribute("moduleId")==null )&& !url.contains(allowedURL) && !url.contains(allowedURL2) && !url.contains(allowedURL3) ){
            //responseObj.sendRedirect(requestObj.getContextPath()+"/sessionExpired.htm");
            System.out.println("some thing wrong here ");
            System.out.println("some thing wrong employee id "+requestObj.getSession().getAttribute("employeeId"));
            System.out.println("some thing wrong module id "+requestObj.getSession().getAttribute("moduleId"));
            System.out.println("some thing wrong url1 "+url.contains(allowedURL));
            System.out.println("some thing wrong url2 "+url.contains(allowedURL2));
            System.out.println("some thing wrong url3 "+url.contains(allowedURL3));
        }
        
        System.out.println("inFilter");
        chain.doFilter(requestObj, responseObj);
  }

  public void init(FilterConfig config) throws ServletException {

    //Get init parameter
    allowedURL = config.getInitParameter("allowedURL");
    allowedURL2 = config.getInitParameter("allowedURL2");
    allowedURL3 = config.getInitParameter("allowedURL3");
    //Print the init parameter
    System.out.println("Test Param: " + allowedURL);
    System.out.println("Test Param: " + allowedURL2);
    System.out.println("Test Param: " + allowedURL3);
  }
  
  public void destroy() {
    //add code to release any resource
  }
}

