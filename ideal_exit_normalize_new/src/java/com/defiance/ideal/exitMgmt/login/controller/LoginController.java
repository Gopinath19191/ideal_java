/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.exitMgmt.login.controller;

import com.defiance.ideal.exitMgmt.employee.dto.EmployeeDTO;
import com.defiance.ideal.exitMgmt.employee.services.ExitEmployeeServiceImpl;
import com.defiance.ideal.exitMgmt.login.dto.LoginDTO;
import com.defiance.ideal.exitMgmt.login.services.LoginServiceImpl;
import com.defiance.ideal.exitMgmt.utils.CommonConfigurations;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 *
 * @author 14583
 * Karthikeyan K @ 27-09-2011
 */
public class LoginController extends MultiActionController {
    
     @Override
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        CustomDateEditor dateEditor = new CustomDateEditor(new SimpleDateFormat("dd-MM-yyyy"), true);
        binder.registerCustomEditor(Date.class, dateEditor);
    }

    // Method for integration with iDeal
    public ModelAndView tokenValidation(HttpServletRequest request, HttpServletResponse response, LoginDTO loginFormData) throws Exception {
        ModelAndView mv = null;
        String idealToken = request.getParameter("idealToken");
        System.out.println("idealToken = " + idealToken);
        final WebApplicationContext ctx = getWebApplicationContext();
        LoginServiceImpl loginService = (LoginServiceImpl) ctx.getBean("LoginService");
        LoginDTO idealUserName = loginService.getIdealUserName(idealToken);
        System.out.println("idealUserName = " + idealUserName);
        if (idealUserName == null) {
            mv = new ModelAndView("unauthorisedaccess");
        } else {
            System.out.println("iDealToken:::" + idealToken + "----DB---" + loginService.getIdealUserName(idealToken));
            mv = new ModelAndView("redirect:loginAuthenticate.htm");
            mv.addObject("idealUserName", idealUserName.getIdealUserName());
        }
        return mv;
    }

    public ModelAndView loginAuthenticate(HttpServletRequest request, HttpServletResponse response, LoginDTO loginFormData) throws Exception {
        HttpSession session = request.getSession();
        ModelAndView mv = null;
        String gAuthenticatedFlag = null;
        String uAuthenticatedFlag = null;
        try {
            final WebApplicationContext ctx = getWebApplicationContext();
            LoginServiceImpl loginService = (LoginServiceImpl) ctx.getBean("LoginService");
            String idealUserName = request.getParameter("idealUserName");
            System.out.println("idealUserName"+idealUserName);
            List menuNames = new ArrayList();
            for (int i = 0; i < CommonConfigurations.EXIT_MENU_ID.length; i++) {
                menuNames.add(CommonConfigurations.EXIT_MENU_ID[i]);
            }
            loginFormData.setIdealUserName(idealUserName);
            LoginDTO loginDetails = loginService.getLoginDetails(loginFormData);
            List<LoginDTO> userAuthentication = null;
//            System.out.println(":::::empId::::" + loginDetails.getEmpId());
            if (loginDetails != null) {
                System.out.println("Authentication Success" + loginDetails.getUserAccountId());
                userAuthentication = (List<LoginDTO>) loginService.authenticateUser(loginDetails.getUserAccountId(), menuNames);
                System.out.println("UserAuthe Size:::" + userAuthentication.size());
                List<LoginDTO> groupAuthentication = (List<LoginDTO>) loginService.authenticateGroup(loginDetails.getGroupId(), menuNames);
                EmployeeDTO employeeDetails = (EmployeeDTO) ((ExitEmployeeServiceImpl) this.getBean("EmployeeService")).getEmployeeDetails(loginDetails.getEmpId());
                System.out.println("Group Authentication size:::" + groupAuthentication.size());
                if(loginDetails.getManager()>0){
                    loginFormData.setRm_approval(1);
                    loginFormData.setRm_clearance(1);
                    loginFormData.setRm_approvalId(CommonConfigurations.EXIT_RM_APPROVAL_MODULE_ID);
                    loginFormData.setRm_clearanceId(CommonConfigurations.EXIT_RM_CLERANCE_MODULE_ID);
                }
                gAuthenticatedFlag = this.setMenuForUser(groupAuthentication, loginFormData);
                uAuthenticatedFlag = this.setMenuForUser(userAuthentication, loginFormData);
                if (loginFormData.getRm_approval_view() == 1 || loginFormData.getRm_clearance_view() == 1 || loginFormData.getFin_approval_view() == 1 || loginFormData.getAdmin_approval_view() == 1 || loginFormData.getNetwork_approval_view() == 1 || loginFormData.getHr_approval_view() == 1) {
                    loginFormData.setView_status(1);
                }
                if (gAuthenticatedFlag != null || uAuthenticatedFlag != null) {
                    if (gAuthenticatedFlag.equals("1") || uAuthenticatedFlag.equals("1")) {
                        if (employeeDetails != null && employeeDetails.getHrApprovedDate() != null) {
                            session.setAttribute("hrApproved", "yes");
                        }
                        session.setAttribute("loginId", loginDetails.getUserName());
                        session.setAttribute("groupId", loginDetails.getGroupId());
                        session.setAttribute("employeeId", loginDetails.getEmpId());
                        session.setAttribute("menuDetails", loginFormData);
                        mv = new ModelAndView("redirect:exitWelcome.htm");
                    }
                }
                 else {
                    mv = new ModelAndView("unauthorisedaccess");
                }
            } else {
                mv = new ModelAndView("login", "errorMsg", "Invalid Credentials");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }

    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = null;
        HttpSession session = request.getSession();
        session.invalidate();
        mv = new ModelAndView("login");
        return mv;
    }

    public ModelAndView redirect(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = null;
        mv = new ModelAndView("login");
        return mv;
    }

    public String setMenuForUser(List<LoginDTO> listName, LoginDTO loginFormData) {
        String authenticatedFlag = null;
        String permissions = null;
        for (int i = 0; i < listName.size(); i++) {
            if (listName == null) {
                authenticatedFlag = "0";
            } else if (listName.get(i).getgCreate() != null) {
                permissions = listName.get(i).getgCreate();
            } else if (listName.get(i).getuCreate() != null) {
                permissions = listName.get(i).getuCreate();
            }
           
            if (permissions != null && (permissions.trim().equals("1"))) {
                switch (Integer.parseInt(listName.get(i).getMenuId())) {
                    case CommonConfigurations.EXIT_EMPLOYEE_MODULE_ID:
                        System.out.println("inside switch :::" + CommonConfigurations.EXIT_EMPLOYEE_MODULE_ID);
                        loginFormData.setResignation_form(1);
                        break;
                    case CommonConfigurations.EXIT_FINANCE_APPROVAL_MODULE_ID:
                        System.out.println("inside switch :::" + CommonConfigurations.EXIT_FINANCE_APPROVAL_MODULE_ID);
                        loginFormData.setFin_approval(1);
                        loginFormData.setFin_approvalId(CommonConfigurations.EXIT_FINANCE_APPROVAL_MODULE_ID);
                        break;
                    case CommonConfigurations.EXIT_ADMIN_APPROVAL_MODULE_ID:
                        System.out.println("inside switch :::" + CommonConfigurations.EXIT_ADMIN_APPROVAL_MODULE_ID);
                        loginFormData.setAdmin_approval(1);
                        loginFormData.setAdmin_approvalId(CommonConfigurations.EXIT_ADMIN_APPROVAL_MODULE_ID);
                        break;
                    case CommonConfigurations.EXIT_NETWORK_APPROVAL_MODULE_ID:
                        System.out.println("inside switch :::" + CommonConfigurations.EXIT_NETWORK_APPROVAL_MODULE_ID);
                        loginFormData.setNetwork_approval(1);
                        loginFormData.setNetwork_approvalId(CommonConfigurations.EXIT_NETWORK_APPROVAL_MODULE_ID);
                        break;
                    case CommonConfigurations.EXIT_HR_APPROVAL_MODULE_ID:
                        System.out.println("inside switch :::" + CommonConfigurations.EXIT_HR_APPROVAL_MODULE_ID);
                        loginFormData.setHr_approval(1);
                        loginFormData.setHr_approvalId(CommonConfigurations.EXIT_HR_APPROVAL_MODULE_ID);
                        break;
                    case CommonConfigurations.EXIT_SURVEY_MODULE_ID:
                        System.out.println("inside switch :::" + CommonConfigurations.EXIT_SURVEY_MODULE_ID);
                        loginFormData.setExit_survey(1);
                        break;
                    case CommonConfigurations.EXIT_RM_APPROVAL_VIEW_MODULE_ID:
                        System.out.println("inside switch :::" + CommonConfigurations.EXIT_RM_APPROVAL_VIEW_MODULE_ID);
                        loginFormData.setRm_approval_view(1);
                        break;
                    case CommonConfigurations.EXIT_RM_CLERANCE_VIEW_MODULE_ID:
                        System.out.println("inside switch :::" + CommonConfigurations.EXIT_RM_CLERANCE_VIEW_MODULE_ID);
                        loginFormData.setRm_clearance_view(1);
                        break;
                    case CommonConfigurations.EXIT_FINANCE_APPROVAL_VIEW_MODULE_ID:
                        System.out.println("inside switch :::" + CommonConfigurations.EXIT_FINANCE_APPROVAL_VIEW_MODULE_ID);
                        loginFormData.setFin_approval_view(1);
                        break;
                    case CommonConfigurations.EXIT_ADMIN_APPROVAL_VIEW_MODULE_ID:
                        System.out.println("inside switch :::" + CommonConfigurations.EXIT_ADMIN_APPROVAL_VIEW_MODULE_ID);
                        loginFormData.setAdmin_approval_view(1);
                        break;
                    case CommonConfigurations.EXIT_NETWORK_APPROVAL_VIEW_MODULE_ID:
                        System.out.println("inside switch :::" + CommonConfigurations.EXIT_NETWORK_APPROVAL_VIEW_MODULE_ID);
                        loginFormData.setNetwork_approval_view(1);
                        break;
                    case CommonConfigurations.EXIT_HR_APPROVAL_VIEW_MODULE_ID:
                        System.out.println("inside switch :::" + CommonConfigurations.EXIT_HR_APPROVAL_VIEW_MODULE_ID);
                        loginFormData.setHr_approval_view(1);
                        break;
                    default:
                        System.out.println("Invalid Menu");
                        break;
                }
                System.out.println("setting Auth Flag to 1::: Moduleid:::" + listName.get(i).getMenuId() + ":::Menu Name::" + listName.get(i).getMenuName());
                authenticatedFlag = "1";
            } else {
                authenticatedFlag = "0";
            }
            System.out.println("authenticatedFlag after gAuth = " + authenticatedFlag);
        }
        return authenticatedFlag;
    }

    public Object getBean(String beanName){
        Object o=null;
        try {
        final WebApplicationContext ctx = getWebApplicationContext();
        return ctx.getBean(beanName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return o;
    }
}
