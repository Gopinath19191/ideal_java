package com.defiance.ideal.shared;

import com.defiance.ideal.reports.dto.LoginDTO;
import java.io.IOException;
import java.util.List;
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
                
//  Get the IP address of client machine.
		String ipAddress = requestObj.getRemoteAddr();
                
//		String userAuthentication = requestObj.getRemoteUser();
                String url = requestObj.getServletPath();
                
//  Log the IP address and current timestamp.
                if(!url.contains("unauthorised") && !url.contains(allowedURL2) && !url.contains(allowedURL) && requestObj.getSession().getAttribute("EMP_ID")==null){
                    responseObj.sendRedirect(requestObj.getContextPath()+"/authenticate.htm");
                }
                
//              System.out.println("IP "+ipAddress + ",Time "+ new Date().toString());
                
                chain.doFilter(requestObj, responseObj);
	}
        
	public void init(FilterConfig config) throws ServletException {

		//Get init parameter
		allowedURL = config.getInitParameter("allowedURL");
		allowedURL2 = config.getInitParameter("allowedURL2");
		//Print the init parameter
//		System.out.println("Test Param: " + allowedURL);
	}
	public void destroy() {
		//add code to release any resource
	}
}
