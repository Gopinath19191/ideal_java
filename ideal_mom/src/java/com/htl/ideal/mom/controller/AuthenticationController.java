/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.htl.ideal.mom.controller;

import com.htl.ideal.mom.dao.AuthenticationDaoImpl;
import com.htl.ideal.mom.dto.AuthenticationDto;
import com.htl.ideal.mom.service.AuthenticationService;
import com.htl.ideal.mom.service.AuthenticationServiceImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 *
 * @author 16656
 */
public class AuthenticationController extends MultiActionController{
    public ModelAndView authentication(HttpServletRequest req, HttpServletResponse res) throws Exception{
        ModelAndView mv = new ModelAndView("/unauthorised");
        HttpSession session = req.getSession();
        final WebApplicationContext ctx = getWebApplicationContext();
        AuthenticationService service = (AuthenticationService) ctx.getBean("AuthenticationService");
        AuthenticationDto authenParams = new AuthenticationDto();
        if (req.getParameter("empId") == null) {
            mv.addObject("ErrorMessage", "employee Id invalid");
        } else {
            authenParams = service.getUserDetails(req.getParameter("empId"));
            System.out.println("Employee Id = "+ authenParams.getEmpId());
        }
        session.setAttribute("employee_no", req.getParameter("empId"));
        if (req.getParameter("modId") == null) {
            mv.addObject("ErrorMessage", "Module Id Not Present");
        } else {
            authenParams.setModuleId(req.getParameter("modId"));
            System.out.println("Module Id = "+ authenParams.getModuleId());
        }

       boolean authenticated = service.authenticate(authenParams);
       //Remove this line
     authenticated = true;
        if (authenticated) {
            session.setAttribute("EMP_ID", authenParams.getEmpId());
            System.out.println("inside authenticated");
            if ("768".equals(authenParams.getModuleId())) {
//                mv = new ModelAndView("redirect:/minutesOfMeeting.htm?page=1");
               mv = new ModelAndView("redirect:/getAllDetails.htm?page=1");
            }
            if ("769".equals(authenParams.getModuleId())) {
                mv = new ModelAndView("redirect:/getActionListDetails.htm?page=1");
//               mv = new ModelAndView("redirect:/minutesOfMeeting.htm?page=1");
            }
        } else {
            session.invalidate();
        }
        return mv;
        
    }
}
