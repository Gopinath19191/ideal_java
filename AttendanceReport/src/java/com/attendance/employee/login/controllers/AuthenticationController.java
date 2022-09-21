/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.attendance.employee.login.controllers;

import com.attendance.employee.login.dto.AuthenticateDto;
import com.attendance.employee.login.service.AuthenticateServiceImpl;
import com.attendance.employee.util.CommonConfigurations;
import java.io.FileInputStream;
import java.util.Properties;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 *
 * @author 16047
 */
public class AuthenticationController extends MultiActionController {

    public ModelAndView authenticate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = null;
        mv = new ModelAndView("/unauthorised");
        Properties configFile = new Properties();
        configFile.load(new FileInputStream(CommonConfigurations.ExternalModuleId));
        HttpSession session = request.getSession();
        try {
            final WebApplicationContext ctx = getWebApplicationContext();
            AuthenticateServiceImpl AuthenticateService = (AuthenticateServiceImpl) ctx.getBean("AuthenticateService");
            AuthenticateDto authenParams = new AuthenticateDto();
            if (request.getParameter("empId") == null) {
                mv.addObject("ErrorMessage", "Module Id Not Present");
            } else {
                authenParams = AuthenticateService.getUserDetails(request.getParameter("empId"));

            }
            session.setAttribute("employee_no", request.getParameter("empId"));
            if (request.getParameter("modId") == null) {
                mv.addObject("ErrorMessage", "Module Id Not Present");
            } else {
                authenParams.setModuleId(request.getParameter("modId"));

            }
            boolean authenticated = AuthenticateService.authenticate(authenParams);
            //authenticated = true;
            if (authenticated) {
                session.setAttribute("EMP_ID", authenParams.getEmpId());
                session.setAttribute("MODULE_ID", authenParams.getModuleId());
                if ("665".equals(authenParams.getModuleId())) {
                    mv = new ModelAndView("redirect:employeeList.htm?empId=" + authenParams.getEmpId());
                } else if (configFile.getProperty("AttendanceReport_RM").equals(authenParams.getModuleId())) {
                    mv = new ModelAndView("redirect:employeeList.htm?empId=" + authenParams.getEmpId());
                }else if("707".equals(authenParams.getModuleId())){
                    //System.out.println("project report "+authenParams.getModuleId());
                    mv = new ModelAndView("redirect:getPmTeamAttendance.htm?page=1");
                } else {
                    mv = new ModelAndView("redirect:getAttendanceDetails.htm?page=1");
                }
            } else {
                session.invalidate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }
}
