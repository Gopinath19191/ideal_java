/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideal.faq.controller;

import com.ideal.faq.dao.AuthenticatorDaoImpl;
import com.ideal.faq.dto.LoginDTO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 *
 * @author 16711
 */
public class AuthenticationController extends MultiActionController {
    
    public ModelAndView authenticate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = new ModelAndView("/unauthorised");
        HttpSession session = request.getSession();
        final WebApplicationContext ctx = getWebApplicationContext();
        AuthenticatorDaoImpl authenticationDao = (AuthenticatorDaoImpl) ctx.getBean("AuthenticatorDao");
        LoginDTO authenParams = new LoginDTO();
        //String strEmpId = request.getParameter("empId");

        if (request.getParameter("empId") == null) {
            mv.addObject("ErrorMessage", "Module Id Not Present");
        } else {    
            authenParams = authenticationDao.getUserDetails(request.getParameter("empId"));
        }
        session.setAttribute("employee_no", request.getParameter("empId"));
        if (request.getParameter("modId") == null) {
            mv.addObject("ErrorMessage", "Module Id Not Present");
        } else {
            authenParams.setModuleId(request.getParameter("modId"));

        }

        // Edited by anbu
        boolean authenticated = authenticationDao.authenticate(authenParams);
        //boolean authenticated;
        authenticated = true;
        if (authenticated) {

            session.setAttribute("EMP_ID", authenParams.getEmpId());
            //session.setAttribute("EMP_ID", strEmpId);

            if ("185".equals(authenParams.getModuleId())) {
//                mv = new ModelAndView("redirect:feedback_user.htm");
                mv = new ModelAndView("redirect:index.htm");
            }
            if ("191".equals(authenParams.getModuleId())) {
                mv = new ModelAndView("redirect:showEmployees.htm");
            }
            if("664".equals(authenParams.getModuleId())){
                mv = new ModelAndView("redirect:showFeedback.htm");
            }
        } else {
            session.invalidate();
        }
        return mv;
    }
}
