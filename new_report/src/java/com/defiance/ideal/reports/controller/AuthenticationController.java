/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.controller;

import com.defiance.ideal.reports.dao.AuthenticatorDaoImpl;
import com.defiance.ideal.reports.dto.LoginDTO;
import com.defiance.ideal.reports.util.CommonConfigurations;
import java.util.ArrayList;
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
        LoginDTO authenParams = new LoginDTO();
        if (request.getParameter("empId") == null) {
            mv.addObject("ErrorMessage", "Module Id Not Present");
        } else {
            authenParams = authenticationDao.getUserDetails(request.getParameter("empId"));
            System.out.println("Employee Id = "+ authenParams.getEmpId());
        }
        session.setAttribute("employee_no", request.getParameter("empId"));
        if (request.getParameter("modId") == null) {
            mv.addObject("ErrorMessage", "Module Id Not Present");
        } else {
            authenParams.setModuleId(request.getParameter("modId"));
            System.out.println("Module Id = "+ authenParams.getModuleId());
        }

       boolean authenticated = authenticationDao.authenticate(authenParams);
       //Remove this line
       //boolean authenticated = true;
        if (authenticated) {

            session.setAttribute("EMP_ID", authenParams.getEmpId());
            // System.out.println("User Acc Id = "+ authenParams.getUserAccountId());
            //  System.out.println("Group Id = "+ authenParams.getGroupId());
            System.out.println("inside authenticated");

            if ("476".equals(authenParams.getModuleId())) {
                mv = new ModelAndView("redirect:timesheetreport.htm");
            }
            if ("488".equals(authenParams.getModuleId())) {
                mv = new ModelAndView("redirect:accrualreport.htm");
            }
            if ("600".equals(authenParams.getModuleId())) {
                mv = new ModelAndView("redirect:associatetimesheetreport.htm");
            }
            if ("391".equals(authenParams.getModuleId())) {
                System.out.println("here it comes");
                mv = new ModelAndView("redirect:projectlist.htm");
            }
            if ("716".equals(authenParams.getModuleId())) {
                System.out.println("here it comes");
                mv = new ModelAndView("redirect:delinquencyReportList.htm");
            }
            if ("747".equals(authenParams.getModuleId())) {
                mv = new ModelAndView("redirect:effortsLeakage.htm");
            }
            if ("748".equals(authenParams.getModuleId())) {
                mv = new ModelAndView("redirect:projectUnbilledReport.htm?page=1");
            }
            if ("749".equals(authenParams.getModuleId())) {
                mv = new ModelAndView("redirect:vendorContractExport.htm?page=1");
            }
            if ("807".equals(authenParams.getModuleId())) {
                mv = new ModelAndView("redirect:franceLeaveReport.htm");
            }
            if ("808".equals(authenParams.getModuleId())) {
                mv = new ModelAndView("redirect:timesheetAttendanceReport.htm");
            }
            if ("601".equals(authenParams.getModuleId())) {
                mv = new ModelAndView("redirect:projectReport.htm");
            }
            if ("604".equals(authenParams.getModuleId())) {
                mv = new ModelAndView("redirect:travelReport.htm");
            }
            if ("605".equals(authenParams.getModuleId())) {
                mv = new ModelAndView("redirect:projectFinanceReport.htm?page=1");
            }
            if ("607".equals(authenParams.getModuleId())) {
                mv = new ModelAndView("redirect:associateTimeSheetChart.htm");
            }
            if ("608".equals(authenParams.getModuleId())) {
                mv = new ModelAndView("redirect:showEffortAnalysisFilter.htm");
            }
            if ("432".equals(authenParams.getModuleId())) {
                mv = new ModelAndView("redirect:phasetasklist.htm");
            }
            if ("382".equals(authenParams.getModuleId())) {
                mv = new ModelAndView("redirect:associatelist.htm");
            }

            if ("454".equals(authenParams.getModuleId())) {
                mv = new ModelAndView("redirect:billablereport.htm");
            }
            if ("491".equals(authenParams.getModuleId())) {
                mv = new ModelAndView("redirect:billablehoursreport.htm");
            }
            if ("492".equals(authenParams.getModuleId())) {
                mv = new ModelAndView("redirect:projectcompletion.htm");
            }
            if ("493".equals(authenParams.getModuleId())) {
                mv = new ModelAndView("redirect:monthlyleavereport.htm");
            }
            if ("546".equals(authenParams.getModuleId())) {
                mv = new ModelAndView("redirect:earnedleave.htm");
            }
            if ("547".equals(authenParams.getModuleId())) {
              mv = new ModelAndView("redirect:encashment.htm");
            }
            if ("689".equals(authenParams.getModuleId())) {
                mv = new ModelAndView("redirect:variance.htm");
            }
            
            if ("548".equals(authenParams.getModuleId())) {
                mv = new ModelAndView("redirect:lop.htm");
            }
            if ("565".equals(authenParams.getModuleId())) {
                debtorsAuthentication(authenParams.getEmpId(),session);
                 authenticationDao.insertLog(authenParams.getEmpId());
                mv = new ModelAndView("redirect:CustagingReport.htm");
            }
            if ("566".equals(authenParams.getModuleId())) {
                debtorsAuthentication(authenParams.getEmpId(),session);
                mv = new ModelAndView("redirect:CollectionReport.htm");
            }
            if ("567".equals(authenParams.getModuleId())) {
                mv = new ModelAndView("redirect:benchReport.htm");
            }
            if ("612".equals(authenParams.getModuleId())) {
                mv = new ModelAndView("redirect:rolloutlist.htm");
            }
            if ("189".equals(authenParams.getModuleId())) {
                mv = new ModelAndView("redirect:getBirthdayAnniversary.htm");
            }
            if ("616".equals(authenParams.getModuleId())) {
                mv = new ModelAndView("redirect:purchaseOrderReport.htm?page=1");
            }
            if ("617".equals(authenParams.getModuleId())) {
                mv = new ModelAndView("redirect:exitReport.htm");
            }
            if ("635".equals(authenParams.getModuleId())) {
		mv = new ModelAndView("redirect:annualAppraisalReport.htm?page=1");
            }
            if ("636".equals(authenParams.getModuleId())) {
                mv = new ModelAndView("redirect:timesheethoursreport.htm?page=1");
            }
            if ("628".equals(authenParams.getModuleId())) {
                mv = new ModelAndView("redirect:subordinateLeaveSummeryReport.htm?page=1");
            }
            if ("447".equals(authenParams.getModuleId())) {
                mv = new ModelAndView("redirect:billingInvoiceReport.htm?flag=1&page=0");
            }
            if ("661".equals(authenParams.getModuleId())) {
                mv = new ModelAndView("redirect:addressReport.htm");
            }
            if ("654".equals(authenParams.getModuleId())) {
                mv = new ModelAndView("redirect:teamAllocationReport.htm?page=1");
            }
            if ("708".equals(authenParams.getModuleId())) {
                mv = new ModelAndView("redirect:pmApproval.htm?list=pending");
            }
            if ("740".equals(authenParams.getModuleId())) {
                mv = new ModelAndView("redirect:exEmployeeList.htm");
            }
//            if ("774".equals(authenParams.getModuleId())) {
//                mv = new ModelAndView("redirect:projectsUnbilledReport.htm");
//            }
//            if ("773".equals(authenParams.getModuleId())) {
//                mv = new ModelAndView("redirect:monthlyLeaveReport.htm");
//            }
            if ("790".equals(authenParams.getModuleId())) {
                mv = new ModelAndView("redirect:getPersonalDetails.htm?page=1");
            } 
            if ("789".equals(authenParams.getModuleId())) {
                mv = new ModelAndView("redirect:getSkillDomainDetails.htm?page=1");
            }
            if ("821".equals(authenParams.getModuleId())) {
                mv = new ModelAndView("redirect:invoiceAgingReport.htm");
            }
            if ("551".equals(authenParams.getModuleId())) {
                mv = new ModelAndView("redirect:reimbursementReport.htm");
            }
            if("836".equals(authenParams.getModuleId())){
                mv = new ModelAndView("redirect:effortsLeakageMonthly.htm?pm=1");
            }
            if("835".equals(authenParams.getModuleId())){
                mv = new ModelAndView("redirect:effortsLeakageMonthly.htm?pm=0");
            }
            if("850".equals(authenParams.getModuleId())){
                mv = new ModelAndView("redirect:getTimesheetlekageCustomerList.htm");
            }
            if("852".equals(authenParams.getModuleId())){
                mv = new ModelAndView("redirect:getUtilizationReport.htm");            
            }
            if("853".equals(authenParams.getModuleId())){
                mv = new ModelAndView("redirect:getOJSSurveyReport.htm");
            }
            if("854".equals(authenParams.getModuleId())){
                 mv = new ModelAndView("redirect:getInvoiceAnnexureCustomerList.htm");
            }
            if("855".equals(authenParams.getModuleId())){
                mv = new ModelAndView("redirect:getWorkLocationReportXL.htm");
            }
            if("856".equals(authenParams.getModuleId())){
                mv = new ModelAndView("redirect:getPrLastBilledReportXL.htm");
            }
            if("857".equals(authenParams.getModuleId())){
                mv = new ModelAndView("redirect:getEmployeeExperienceReportXL.htm");
                
            }
        } else {
            session.invalidate();
        }

//            session.setAttribute("EMP_ID","123213");
//            mv = new ModelAndView("redirect:accrualreport.htm");

//      authenticationDao.authenticateUser(authenParams);

        return mv;
    }

    public void debtorsAuthentication(String emp_id,HttpSession session) {
        //System.out.println("EmployeeID-------------------------->"+emp_id);
        final WebApplicationContext ctx = getWebApplicationContext();
        AuthenticatorDaoImpl authenticationDao = (AuthenticatorDaoImpl) ctx.getBean("AuthenticatorDao");
        List<LoginDTO> authenticators = new ArrayList();
        authenticators = authenticationDao.getDebtorsAuthenticatorList();
        String authenticatorName = "";
        String access = "BDM";
        for(int k=0;k<1;k++) {
            authenticatorName = authenticators.get(k).getAuthenticatorName();
        }
        if(!authenticatorName.isEmpty()) {
            String nameList[];
            nameList = authenticatorName.split(",");
            for(int j=0;j<nameList.length;j++) {
                if(nameList[j].equals(emp_id)) {
                    access = "FULL";
                }
            }
        }
        if(!access.isEmpty() && !access.equals("FULL")) {
            List<LoginDTO> businessLeaders = new ArrayList();
            businessLeaders = authenticationDao.fetchBusinessLeader();
            for(int i=0;i<businessLeaders.size();i++) {
                if(businessLeaders.get(i).getBusiness_leader_id().equals(emp_id)) {
                    access = "BUSINESS_LEADER";
                }
            }
        }
        session.setAttribute("DebtorAccess", access);
    }
}
