package com.defiance.ideal.qpd.admin;

import com.defiance.ideal.qpd.admin.bo.AdminBO;
import com.defiance.ideal.qpd.admin.dto.AdminDTO;
import com.defiance.ideal.qpd.admin.dto.AdminFilterDTO;
import com.defiance.ideal.shared.CommonConfigurations;
import com.defiance.ideal.shared.CommonFunctions;
import java.util.ArrayList;
import java.util.Calendar;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.beehive.controls.api.bean.Control;
import org.apache.beehive.netui.pageflow.Forward;
import org.apache.beehive.netui.pageflow.PageFlowController;
import org.apache.beehive.netui.pageflow.annotations.Jpf;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author HARIHARAN.C
 */
@Jpf.Controller()
public class AdminController extends PageFlowController {

    @Control
    private AdminBO controlObj;

    @Jpf.Action(forwards = {
        @Jpf.Forward(name = "appraisaltrigger", path = "appraisaltrigger.jsp")
    })
    public Forward begin(AdminFilterDTO formData) throws Exception {
        Forward fwd = null;
        HttpServletRequest request = this.getRequest();
        HttpSession sessionObj = this.getRequest().getSession();
        System.out.println("sessionObj = " + request.getAttribute("filterData") + " " + request.getSession().getAttribute("filterData"));
        if (request.getAttribute("filterData") != null) {
            formData = (AdminFilterDTO) request.getAttribute("filterData");
        }
        if (request.getParameter("msg") != null && request.getParameter("msg").equals("scss")) {
            formData = (AdminFilterDTO) request.getSession().getAttribute("filterData");
            request.setAttribute("successMsg", new String("Appraisal Triggered Successfully"));
        }
        else if (request.getParameter("msg") != null && request.getParameter("msg").equals("sucss")) {
            formData = (AdminFilterDTO) request.getSession().getAttribute("filterData");
            request.setAttribute("successMsg", new String("Appraisee Data Saved Successfully"));
        }
 System.out.println("formData.getAppraisalYearFilter() = " + formData.getAppraisalYearFilter());
        AdminDTO[] appraiseeData = controlObj.filterEmployees(request, formData, null);
        AdminDTO[] appraiserData = controlObj.filterAppraiser(new String("before"));
        AdminDTO[] companyStructure = controlObj.getCompanyStructure();
        AdminDTO[] bandData = controlObj.getBandData();

        int currentYear = CommonFunctions.getAppraisalYear(sessionObj);
        int previousYear = (CommonFunctions.getAppraisalYear() - 1);
        ArrayList<Integer> myArrrayList = new ArrayList<Integer>();
        myArrrayList.add(CommonFunctions.getAppraisalYear());
        myArrrayList.add(previousYear);
        int appraisalQuarter = CommonFunctions.getAppraisalQuarter(this.getRequest().getSession());
        if (formData.getAppraisalYearFilter() == null) {
            request.setAttribute("appraisalYear", currentYear);
        } else {
            request.setAttribute("appraisalYear", formData.getAppraisalYearFilter());
        }

        if (formData.getAppraisalPeriodFilter() == null) {
            request.setAttribute("appraisalQuarter", appraisalQuarter);
        } else {
            request.setAttribute("appraisalQuarter", formData.getAppraisalPeriodFilter());
        }

        request.setAttribute("bandData", bandData);
        request.setAttribute("appraiserData", appraiserData);
        sessionObj.setAttribute("filterData", formData);
        request.setAttribute("appraiseeData", appraiseeData);
        request.setAttribute("companyStructure", companyStructure);
        request.setAttribute("employeeStatus", new CommonConfigurations().employeeStatus);
        request.setAttribute("yearData", myArrrayList);
        fwd = new Forward("appraisaltrigger");
        return fwd;
    }

    @Jpf.Action(forwards = {
        @Jpf.Forward(name = "triggersuccess", path = "begin.do?msg=scss"),
        @Jpf.Forward(name = "filterpage", path = "begin.do?msg=sucss"),
        @Jpf.Forward(name = "excelexport", path = "excelExport.do")
    })
    public Forward save(AdminDTO formData) throws Exception {
        Forward fwd = null;
        HttpSession sessionObj = this.getRequest().getSession();
        HttpServletRequest requestObj = this.getRequest();

        System.out.println("Submit--" + formData.getSubmitAppraisal());
        System.out.println("Save--" + formData.getSaveAppraisal());
        System.out.println("Excel Export--" + formData.getExcelExport());
        AdminFilterDTO filterData = null;

        if (formData.getSubmitAppraisal() != null) {
            controlObj.triggerAppraisal(formData, Integer.parseInt((String) sessionObj.getAttribute("employeeId")));
            filterData = (AdminFilterDTO) sessionObj.getAttribute("filterData");
            sessionObj.setAttribute("filterData", filterData);
            
            fwd = new Forward("triggersuccess");
            fwd.setRedirect(true);

        } else if (formData.getSaveAppraisal() != null) {
            controlObj.triggerAppraisal(formData, Integer.parseInt((String) sessionObj.getAttribute("employeeId")));
            filterData = (AdminFilterDTO) sessionObj.getAttribute("filterData");
            sessionObj.setAttribute("filterData", filterData);
            
            fwd = new Forward("filterpage");
            fwd.setRedirect(true);

        } else if (formData.getExcelExport() != null) {
            System.out.println("In Here buddy.");
            filterData = (AdminFilterDTO) sessionObj.getAttribute("filterData");
            sessionObj.setAttribute("filterData", filterData);
            AdminDTO[] exportAppraiseeData = controlObj.filterEmployees(requestObj, filterData, null);
            requestObj.setAttribute("exportAppraiseeData", exportAppraiseeData);

            fwd = new Forward("excelexport");
//            fwd.setRedirect(true);
        }
        return fwd;
    }
    

    @Jpf.Action(forwards = {
        @Jpf.Forward(name = "updatesuccess", path = "change.do?msg=scss"),
        @Jpf.Forward(name = "excelexport", path = "excelExport.do?frm=update")
    })
    public Forward update(AdminDTO formData) throws Exception {
        Forward fwd = null;
        HttpSession sessionObj = this.getRequest().getSession();
        HttpServletRequest requestObj = this.getRequest();
        AdminFilterDTO filterData = null;

        System.out.println("Submit--" + formData.getSubmitAppraisal());
        System.out.println("Excel Export--" + formData.getExcelExport());

        if(formData.getSubmitAppraisal()!=null){

            
            controlObj.updateAppraisal(formData, Integer.parseInt((String) sessionObj.getAttribute("employeeId")));
            fwd = new Forward("updatesuccess");
            fwd.setRedirect(true);
                
        }else if(formData.getExcelExport()!=null){

            filterData = (AdminFilterDTO) sessionObj.getAttribute("filterData");
            sessionObj.setAttribute("filterData", filterData);
            AdminDTO[] exportAppraiseeData = controlObj.filterEmployees(requestObj, filterData, new String("ChangeRequest"));
            requestObj.setAttribute("exportAppraiseeData", exportAppraiseeData);
            fwd = new Forward("excelexport");
//            fwd.setRedirect(true);
        }
        
        return fwd;
    }

    @Jpf.Action(forwards = {
        @Jpf.Forward(name = "changeappraisee", path = "changerequest.jsp")
    })
    public Forward change(AdminFilterDTO formData) throws Exception {
        Forward fwd = null;
        HttpServletRequest requestObj = this.getRequest();
        System.out.println("sessionObj = " + requestObj.getAttribute("filterData") + " " + requestObj.getSession().getAttribute("filterData"));
        if (requestObj.getAttribute("filterData") != null) {
            formData = (AdminFilterDTO) requestObj.getAttribute("filterData");
        }
        if (requestObj.getParameter("msg") != null && requestObj.getParameter("msg").equals("scss")) {
            formData = (AdminFilterDTO) requestObj.getSession().getAttribute("filterData");
        }
        AdminDTO[] bandData = controlObj.getBandData();
        AdminDTO[] companyStructure = controlObj.getCompanyStructure();
        AdminDTO[] appraiseeData = controlObj.filterEmployees(requestObj, formData, new String("ChangeRequest"));
        AdminDTO[] appraiserData = controlObj.filterAppraiser(new String("after"));
//        System.out.println("appraiseeData=" + appraiseeData);
//        System.out.println("companyStructure=" + companyStructure);

//        Calendar apprCalendar = Calendar.getInstance();
        int currentYear = CommonFunctions.getAppraisalYear(requestObj.getSession());
        int previousYear = (currentYear - 1);
        ArrayList<Integer> myArrrayList = new ArrayList<Integer>();
        myArrrayList.add(CommonFunctions.getAppraisalYear());
        myArrrayList.add(CommonFunctions.getAppraisalYear() - 1);
        int appraisalQuarter = CommonFunctions.getAppraisalQuarter(this.getRequest().getSession());

        if (formData.getAppraisalYearFilter() == null) {
            requestObj.setAttribute("appraisalYear", currentYear);
        } else {
            requestObj.setAttribute("appraisalYear", formData.getAppraisalYearFilter());
        }

        if (formData.getAppraisalPeriodFilter() == null) {
            requestObj.setAttribute("appraisalPeriod", appraisalQuarter);
        } else {
            requestObj.setAttribute("appraisalPeriod", formData.getAppraisalPeriodFilter());
        }

        requestObj.setAttribute("bandData", bandData);
        requestObj.setAttribute("appraiserData", appraiserData);
        requestObj.getSession().setAttribute("filterData", formData);
        requestObj.setAttribute("appraiseeData", appraiseeData);
        requestObj.setAttribute("companyStructure", companyStructure);
        requestObj.setAttribute("employeeStatus", new CommonConfigurations().employeeStatus);
        requestObj.setAttribute("yearData", myArrrayList);

        fwd = new Forward("changeappraisee");
        return fwd;
    }

    @Jpf.Action(forwards = {
        @Jpf.Forward(name = "ajaxCall", path = "ajax.jsp")
    })
    public Forward getEmployeeName() throws Exception {
        HttpServletRequest requestObj = getRequest();
        String searchString = requestObj.getParameter("q");
        System.out.println("queryString Typed = " + searchString);
        requestObj.setAttribute("employeeName", controlObj.getEmployeeName(searchString));
        requestObj.setAttribute("getDataFor", "employeeName");
        return new Forward("ajaxCall");
    }

    @Jpf.Action(forwards = {
        @Jpf.Forward(name = "excelExport", path = "begin.do?msg=scss"),
        @Jpf.Forward(name = "excelExportChange", path = "change.do")
    })
    public Forward excelExport() throws Exception {

        Calendar currentTime = Calendar.getInstance();

        String fileName = "Trigger" + currentTime.getTime().toString() + ".xls";

        HttpServletRequest requestObj = getRequest();
        HttpSession sessionObj = this.getSession();
        HttpServletResponse responseObj = getResponse();

        AdminDTO[] exportAppraiseeData = (AdminDTO[]) requestObj.getAttribute("exportAppraiseeData");

        ArrayList entireList = new ArrayList();
        ArrayList headerList = new ArrayList();
        ArrayList headerData;

        for (int test = 0; test < 2; test++) {
            headerData = new ArrayList();
            headerData.add(new String(""));
            headerList.add(headerData);
        }
        headerData = new ArrayList();
        headerData.add(new String("Appraisee Number"));
        headerData.add(new String("Appraisee Name"));
        headerData.add(new String("Band"));
        headerData.add(new String("Department"));
        headerData.add(new String("Date of Join"));
        headerData.add(new String("Appraiser Number"));
        headerData.add(new String("Appraiser Name"));
        headerData.add(new String("Reviewer Number"));
        headerData.add(new String("Reviewer Name"));
//        headerData.add(new String("Normalizer Number"));
        headerData.add(new String("Final Reviewer Number"));
//        headerData.add(new String("Normalizer Name"));
        headerData.add(new String("Final Reviewer Name"));
        headerList.add(headerData);
        if (exportAppraiseeData != null) {

            for (int i = 0; i < exportAppraiseeData.length; i++) {
                ArrayList rowDataList = new ArrayList();
                rowDataList.add(new String(""+exportAppraiseeData[i].getAppraiseeNumber()));
                rowDataList.add(new String(""+exportAppraiseeData[i].getAppraiseeName()));
                rowDataList.add(new String(""+exportAppraiseeData[i].getBandName()));
                rowDataList.add(new String(""+exportAppraiseeData[i].getDepartmentName()));
                rowDataList.add(new String(""+exportAppraiseeData[i].getDateOfJoin()));
                rowDataList.add(new String(""+exportAppraiseeData[i].getAppraiserNumber()));
                rowDataList.add(new String(""+exportAppraiseeData[i].getAppraiserName()));
                rowDataList.add(new String(""+exportAppraiseeData[i].getReviewerNumber()));
                rowDataList.add(new String(""+exportAppraiseeData[i].getReviewerName()));
                rowDataList.add(new String(""+exportAppraiseeData[i].getNormalizerNumber()));
                rowDataList.add(new String(""+exportAppraiseeData[i].getNormalizerName()));

                entireList.add(rowDataList);
            }
        }

        CommonFunctions.exportToExcel(responseObj, headerList, entireList, fileName);

        AdminFilterDTO filterData = null;
        filterData = (AdminFilterDTO) sessionObj.getAttribute("filterData");
        requestObj.setAttribute("filterData", filterData);
        requestObj.setAttribute("successMsg", new String("Appraisee Data Saved Successfully"));

        Forward fwd = null;
        return fwd;
    }
}
