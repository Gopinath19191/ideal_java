/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideal.hrMaster.controller;

import com.ideal.hrMaster.dao.AuthenticatorDaoImpl;
import com.ideal.hrMaster.dto.LoginDTO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 *
 * @author 16113
 */
public class AuthenticationController extends MultiActionController{
    
    public ModelAndView authenticate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = new ModelAndView("/unauthorised");
        HttpSession session = request.getSession();
        final WebApplicationContext ctx = getWebApplicationContext();
        AuthenticatorDaoImpl authenticationDao = (AuthenticatorDaoImpl) ctx.getBean("AuthenticatorDao");
        LoginDTO authenParams = new LoginDTO();

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

        boolean authenticated = authenticationDao.authenticate(authenParams);
        //Remove this line
        authenticated = true;
        if (authenticated) {

            session.setAttribute("EMP_ID", authenParams.getEmpId());
            
            if("658".equals(authenParams.getModuleId())){
               // mv = new ModelAndView("redirect:showFeedback.htm");
                System.out.println("conws here");
                mv = new ModelAndView("bulkUpload");
            }
            if("741".equals(authenParams.getModuleId())){
               // mv = new ModelAndView("redirect:showFeedback.htm");
                mv = new ModelAndView("ojfUpload");
            }
            if("659".equals(authenParams.getModuleId())){
                mv = new ModelAndView("redirect:showAuditList.htm");
            }
            if("731".equals(authenParams.getModuleId())){
                mv = new ModelAndView("redirect:pcList.htm");
            }
            if("732".equals(authenParams.getModuleId())){
                mv = new ModelAndView("redirect:pcListNs.htm");
            }else{
               // mv = new ModelAndView("redirect:salesRevenueUnitView.htm?rsh=0");
                //mv = new ModelAndView("redirect:salesRevenueUnitView.htm");
            }
        } else {
            session.invalidate();
        }
        return mv;
    }
    
}
