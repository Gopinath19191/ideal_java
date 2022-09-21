/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.application.controller;

import com.defiance.ideal.application.dao.AuthenticatorDaoImpl;
import com.defiance.ideal.application.dto.LoginDTO;
import com.defiance.ideal.application.util.CommonConfigurations;
import java.util.LinkedHashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 *
 * @author 14053
 */
public class AuthenticationController extends MultiActionController {

    public ModelAndView authenticate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = new ModelAndView("/unauthorised");
        HttpSession session = request.getSession();
        final WebApplicationContext ctx = getWebApplicationContext();
        AuthenticatorDaoImpl authenticationDao = (AuthenticatorDaoImpl) ctx.getBean("AuthenticatorDao");
        System.out.println("::DAO:" + authenticationDao);
        LoginDTO authenParams = new LoginDTO();

        if (request.getParameter("empId") == null) {
            mv.addObject("ErrorMessage", "Module Id Not Present");
        } else {
            authenParams = authenticationDao.getUserDetails(request.getParameter("empId"));
//            System.out.println("Employee Id = " + authenParams.getEmpId());
        }
        System.out.println("::EMP ID::" + request.getParameter("empId"));
        session.setAttribute("employee_no", request.getParameter("empId"));
        if (request.getParameter("modId") == null) {
            mv.addObject("ErrorMessage", "Module Id Not Present");
        } else {
            authenParams.setModuleId(request.getParameter("modId"));
            System.out.println("Module Id = " + authenParams.getModuleId());
        }

        boolean authenticated = authenticationDao.authenticate(authenParams);
//          Remove this line
//        authenticated = true;
//        if(authenticated){
        if (authenticated) {

            session.setAttribute("EMP_ID", authenParams.getEmpId());
            session.setAttribute("Module_ID", authenParams.getModuleId());
            System.out.println("User Acc Id = " + authenParams.getUserAccountId());
            System.out.println("Group Id = " + authenParams.getGroupId());
            if(CommonConfigurations.CUSTOMER_MAPPING_MODULE_ID.equals(authenParams.getModuleId())){
                mv = new ModelAndView("redirect:/custMap/custMapList.htm");
            }
            if ("613".equals(authenParams.getModuleId())) {
                mv = new ModelAndView("redirect:/agency/agencyList.htm");
            }
            if ("614".equals(authenParams.getModuleId())) {
                mv = new ModelAndView("redirect:/agency/getAgencyDetails.htm?agencyId=" + request.getParameter("agencyId"));
            }
            if ("73".equals(authenParams.getModuleId())) {
                mv = new ModelAndView("redirect:customersList.htm");
                //mv = new ModelAndView("redirect:dunningReportList.htm?user_type=bdm");
            }
            if ("75".equals(authenParams.getModuleId())) {
                mv = new ModelAndView("redirect:customersAdd.htm");
            }
            if ("79".equals(authenParams.getModuleId())) {
                mv = new ModelAndView("redirect:customersEdit.htm?customerId=" + request.getParameter("customerId"));
            }
            if ("76".equals(authenParams.getModuleId())) {
                mv = new ModelAndView("redirect:customersApprovalList.htm");
            }
            if ("667".equals(authenParams.getModuleId())) {
                mv = new ModelAndView("redirect:financeView.htm");
            }
            if ("602".equals(authenParams.getModuleId())) {
                mv = new ModelAndView("redirect:customersRMGApprovalList.htm");
            }
            if ("603".equals(authenParams.getModuleId())) {
                mv = new ModelAndView("redirect:rmgApproveCustomer.htm?customerId=" + request.getParameter("customerId"));
            }
            if ("77".equals(authenParams.getModuleId())) {
                mv = new ModelAndView("redirect:approveCustomer.htm?customerId=" + request.getParameter("customerId"));
            }
            if ("529".equals(authenParams.getModuleId())) {
                mv = new ModelAndView("redirect:customersGroupList.htm");
            }
            if ("530".equals(authenParams.getModuleId())) {
                mv = new ModelAndView("redirect:customersGroupAdd.htm");
            }
            if ("531".equals(authenParams.getModuleId())) {
                mv = new ModelAndView("redirect:customersGroupEdit.htm?customerGroupId=" + request.getParameter("customerGroupId"));
            }
            if ("562".equals(authenParams.getModuleId())) {
                mv = new ModelAndView("redirect:OJFSurveyInfo.htm");
            }
            if ("563".equals(authenParams.getModuleId())) {
                mv = new ModelAndView("redirect:OJFSurveyList.htm");
            }
            if ("564".equals(authenParams.getModuleId())) {
                mv = new ModelAndView("redirect:OJFSurveyView.htm?surveyEmployeeId=" + request.getParameter("surveyEmployeeId"));
            }

            if ("569".equals(authenParams.getModuleId())) {
                mv = new ModelAndView("redirect:qualityCustomerList.htm?list=requestor");
            }
            if ("570".equals(authenParams.getModuleId())) {
                mv = new ModelAndView("redirect:qualityCustomerList.htm?list=pending");
            }
            if ("571".equals(authenParams.getModuleId())) {
                mv = new ModelAndView("redirect:qualityCustomerList.htm?list=reports");
            }
            if("833".equals(authenParams.getModuleId())){
                String file_name = request.getParameter("fileName");
                String file_type = request.getParameter("fileType");
                String folder = request.getParameter("dunningFolder");
                mv = new ModelAndView("redirect:dunningFileDownload.htm?fileName="+file_name+"&fileType="+file_type+"&dunningFolder="+folder);
            }

        } else {
            System.out.println("comes her");
            session.invalidate();
        }

//            session.setAttribute("EMP_ID","123213");
//            mv = new ModelAndView("redirect:accrualreport.htm");

//      authenticationDao.authenticateUser(authenParams);

        return mv;
    }

    public List<LoginDTO> fetchConfigList(LoginDTO filterData) {
        List<LoginDTO> template = null;

        try {
            final WebApplicationContext ctx = getWebApplicationContext();
            AuthenticatorDaoImpl authenticationDao = (AuthenticatorDaoImpl) ctx.getBean("AuthenticatorDao");
            template = authenticationDao.fetchConfigList(filterData);
            LinkedHashMap<String, String> children = new LinkedHashMap<String, String>();
            String key, value;
//            for(int i=0;i<questionId.length;i++){
//                key = rs.getString("k");
//                value = rs.getString("v");
//                children.put(key, value);
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return template;
    }
}
