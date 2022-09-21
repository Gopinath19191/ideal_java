/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.controller;

import com.defiance.ideal.reports.dto.AssociateFilterDTO;
import com.defiance.ideal.reports.dto.TravelReportDetails;
import com.defiance.ideal.reports.dto.TravelReportFilterDTO;
import com.defiance.ideal.reports.service.AssociateServiceImpl;
import com.defiance.ideal.reports.service.TravelReportService;
import com.defiance.ideal.reports.service.TravelReportServiceImpl;
import com.defiance.ideal.reports.util.CommonConfigurations;
import com.defiance.ideal.reports.util.CommonMethods;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 *
 * @author 15261
 */
public class TravelReportController extends MultiActionController {

    public TravelReportController() {
    }

    public ModelAndView travelReport_search(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mvc = null;
        mvc = new ModelAndView("/travelReport_search");
        System.out.println("ajaxsearch Called");
        try {
            final WebApplicationContext ctx = getWebApplicationContext();
            TravelReportService travelReportService = (TravelReportServiceImpl) ctx.getBean("TravelReportService");
            List<TravelReportDetails> travelReportDetails = travelReportService.getProjectDetails();
            mvc.addObject("projectDetails", travelReportDetails);
            request.setAttribute("projectDetailsList", travelReportDetails);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mvc;
    }

    public ModelAndView travelReport(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mvc = null;
        mvc = new ModelAndView("/travelReport");
        try {
            final WebApplicationContext ctx = getWebApplicationContext();
            TravelReportService travelReportService = (TravelReportServiceImpl) ctx.getBean("TravelReportService");
            String projectString = request.getParameter("project_search");
            String fromDate = request.getParameter("fromDate");
            String toDate = request.getParameter("toDate");
            String projectCode = null;
            String sbuId = request.getParameter("sbu");
            String subSbuId = request.getParameter("subSbu");
            String status = request.getParameter("status_search");
            String sbu = CommonConfigurations.PES +','+ CommonConfigurations.TS;


            if (sbuId != null) {
                if (sbuId.equalsIgnoreCase("All")) {
                    sbuId = "";
                }
            }
            if (subSbuId != null) {
                if (subSbuId.equalsIgnoreCase("All")) {
                    subSbuId = "";
                }
            }


            TravelReportFilterDTO dto = new TravelReportFilterDTO();
            dto.setFromDate(fromDate);
            dto.setToDate(toDate);
            dto.setSbuId(sbuId);
            dto.setSubSbuId(subSbuId);
            dto.setStatus_search(status);

            if (projectString != null) {
                StringTokenizer str = new StringTokenizer(projectString, "-");
                for (int i = 0; i < str.countTokens() - 1; i++) {
                    projectCode = str.nextToken().trim();
                }
                dto.setProjectCode(projectCode);
            }

            List<TravelReportDetails> travelReportlist = travelReportService.getTravelReportList(dto);
            
//            Iterator it = travelReportlist.iterator();
//            while(it.hasNext()){
//            TravelReportDetails travelReport = (TravelReportDetails)it.next();
//                System.out.println("from city"+travelReport.getDestination_from());
//                System.out.println("to city"+travelReport.getDestination_to());
//            }

            mvc.addObject("travelReportList", travelReportlist);
            request.setAttribute("travelReportList", travelReportlist);
            dto.setParentId(CommonConfigurations.SBU);
            System.out.println("dto:"+dto.getParentId());
            List<TravelReportDetails> sbuList = travelReportService.getSbuList(dto);

            mvc.addObject("sbuList", sbuList);
            request.setAttribute("sbuList", sbuList);
            mvc.addObject("status", status);
            mvc.addObject("projectString", projectString);
            mvc.addObject("fromDate", fromDate);
            mvc.addObject("toDate", toDate);
            mvc.addObject("selectedSbuId", sbuId);
            request.setAttribute("selectedSubSbu", subSbuId);
            mvc.addObject("selectedSubSbu", subSbuId);
            mvc.addObject("sbu",sbu);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mvc;
    }

    public ModelAndView travelReportXL(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("travelReportExport Method Called");
        try {

            ArrayList entireList = new ArrayList();
            ArrayList rowDataList;
            final WebApplicationContext ctx = getWebApplicationContext();
            TravelReportService travelReportService = (TravelReportServiceImpl) ctx.getBean("TravelReportService");
            String projectString = request.getParameter("project_search");
            String fromDate = request.getParameter("fromDate");
            String toDate = request.getParameter("toDate");
            String projectCode = null;
            String sbuId = request.getParameter("sbu");
            String subSbuId = request.getParameter("subSbu");
            String status = request.getParameter("status_search");


            if (sbuId != null) {
                if (sbuId.equalsIgnoreCase("All")) {
                    sbuId = "";
                }
            }
            if (subSbuId != null) {
                if (subSbuId.equalsIgnoreCase("All")) {
                    subSbuId = "";
                }
            }
            TravelReportFilterDTO dto = new TravelReportFilterDTO();
            dto.setFromDate(fromDate);
            dto.setToDate(toDate);
            dto.setSbuId(sbuId);
            dto.setSubSbuId(subSbuId);
            dto.setStatus_search(status);

            if (projectString != null) {
                StringTokenizer str = new StringTokenizer(projectString, "-");
                for (int i = 0; i < str.countTokens() - 1; i++) {
                    projectCode = str.nextToken().trim();
                }
                dto.setProjectCode(projectCode);
            }

            // Get Travel details
            List<TravelReportDetails> travelReportlist = travelReportService.getTravelReportList(dto);
            request.setAttribute("travelReportList", travelReportlist);
            List<TravelReportDetails> sbuList = travelReportService.getSbuList(dto);
            request.setAttribute("sbuList", sbuList);
            request.setAttribute("selectedSubSbu", subSbuId);

            //Get SBU description.


            List<TravelReportDetails> sbuDesc = travelReportService.getSBUDescription(dto);
            TravelReportDetails td = null;
            String sbu = null;
            if (sbuDesc != null && sbuDesc.size() > 0) {
                td = sbuDesc.get(0);
                sbu = td.getSbu_name();
            }

            // Get Sub SBU description.
            List<TravelReportDetails> subSbuDesc = travelReportService.getSubSBUDescription(dto);
            String subSbu = null;
            if (subSbuDesc != null && subSbuDesc.size() > 0) {
                td = subSbuDesc.get(0);
                subSbu = td.getSbu_name();
            }


            // Set all data in Arraylist and send to exportToExcel() function.

            rowDataList = new ArrayList();
            rowDataList.add(new String("SBU"));
            rowDataList.add(sbu);
            rowDataList.add(new String("Sub SBU"));
            rowDataList.add(subSbu);
            entireList.add(rowDataList);

            rowDataList = new ArrayList();
            rowDataList.add(new String("From Date"));
            rowDataList.add(fromDate);
            rowDataList.add(new String("To Date"));
            rowDataList.add(toDate);
            entireList.add(rowDataList);

            rowDataList = new ArrayList();
            rowDataList.add(new String("Project Code/Name"));
            rowDataList.add(projectString);
            entireList.add(rowDataList);
            
            String statusName = "";
            
            if(status.equals("b")){
             statusName = "Pending with BUH";
            }else if(status.equals("r")){
             statusName = "Pending with RM";
            }else if(status.equals("f")){
             statusName = "Pending with Finance";
            }else if(status.equals("o")){
             statusName = "Rejected";
            }else if(status.equals("x")){
             statusName = "Cancelled";
            }else if(status.equals("s")){
             statusName = "Saved";
            }else if(status.equals("c")){
             statusName = "Pending with CFO";
            }else if(status.equals("m")){
             statusName = "Pending with MD";
            }else if(status.equals("a")){
             statusName = "Pending with Admin";
            }else if(status.equals("t")){
             statusName = "Pending with Treasury";
            }else if(status.equals("tc")){
             statusName = "Ticket Booked";
            }else if(status.equals("mn")){
             statusName = "Money Deposited";
            }else{
            }
            
            rowDataList = new ArrayList();
            rowDataList.add(new String("Status"));
            rowDataList.add(statusName);
            entireList.add(rowDataList);

            //empty row.
            rowDataList = new ArrayList();
            rowDataList.add(new String(""));
            rowDataList.add(new String(""));
            rowDataList.add(new String(""));
            rowDataList.add(new String(""));
            entireList.add(rowDataList);

            //header row.
            rowDataList = new ArrayList();
            rowDataList.add(new String("Travel Id"));
            rowDataList.add(new String("Project Id"));
            rowDataList.add(new String("Project Name"));
            rowDataList.add(new String("Employee Number"));
            rowDataList.add(new String("Employee Name"));
            rowDataList.add(new String("RM"));
            rowDataList.add(new String("SBU"));
            rowDataList.add(new String("Sub SBU"));
            rowDataList.add(new String("Travel Type"));
            rowDataList.add(new String("Travel Request Date"));
            rowDataList.add(new String("Travel Start Date"));
            rowDataList.add(new String("Travel End Date"));
            rowDataList.add(new String("Project Travel"));
            rowDataList.add(new String("Client Reimbursement"));
            rowDataList.add(new String("Customer Name"));
//            rowDataList.add(new String("Travle Approved Date"));
            rowDataList.add(new String("Travel From"));
            rowDataList.add(new String("Travle To"));
            rowDataList.add(new String("Travel Purpose"));
            rowDataList.add(new String("Status"));
            rowDataList.add(new String("RM Action Date"));
            rowDataList.add(new String("BUH Action Date"));
            rowDataList.add(new String("MD Action Date"));
            rowDataList.add(new String("Finance Action Date"));
            rowDataList.add(new String("CFO Action Date"));
            rowDataList.add(new String("Admin Action Date"));
            rowDataList.add(new String("Treasury Action Date"));
            entireList.add(rowDataList);

            String loopRmNo = null, loopRmFirstName = null, loopRmLastName = null;
            for (int i = 0; i < travelReportlist.size(); i++) {
                rowDataList = new ArrayList();
                rowDataList.add(new String("" + travelReportlist.get(i).getTravel_requestid()));
                rowDataList.add(new String("" + travelReportlist.get(i).getProject_code()));
                rowDataList.add(new String("" + travelReportlist.get(i).getProject_name()));
                rowDataList.add(new String("" + travelReportlist.get(i).getEmployee_number()));
                rowDataList.add(new String("" + travelReportlist.get(i).getFirst_name() + " " + travelReportlist.get(i).getLast_name()));
                loopRmNo = travelReportlist.get(i).getRm_emp_num();
                if (loopRmNo == null) {
                    loopRmNo = "";
                }
                loopRmFirstName = travelReportlist.get(i).getRm_first_name();
                if (loopRmFirstName == null) {
                    loopRmFirstName = "";
                }
                loopRmLastName = travelReportlist.get(i).getRm_last_name();
                if (loopRmLastName == null) {
                    loopRmLastName = "";
                }
//                String[] getterMethodName = {"getSbu_name", "getAddressHide", "getCity", "getDistrictHide", "getStateHide", "getPhone", "getFax", "getEmailHide", "getPinHide", "getLattidueHide", "getLongtitudeHide", "getType", "getContactPersonName"};
//                try {
//                    for (int j = 0; j < travelReportlist.size(); j++) {
//                        rowDataList = new ArrayList();
//                        for (int k = 0; k < getterMethodName.length; k++) {
//                            Method method = travelReportlist.get(j).getClass().getMethod(getterMethodName[k]);
//                            Object dataObj = method.invoke(travelReportlist.get(j));
//                            rowDataList.add(dataObj.toString());
//                        }
//                         entireList.add(rowDataList);
//                    }
//
//                } catch (Exception e) {
//                    System.out.println("## Exception in updateSqlString" + e.getMessage());
//                }
                rowDataList.add(new String("" + loopRmNo + " " + loopRmFirstName + " " + loopRmLastName));
                rowDataList.add(new String("" + travelReportlist.get(i).getSbu_name()));
                rowDataList.add(new String("" + travelReportlist.get(i).getSub_sbu_name()));
                rowDataList.add(new String("" + travelReportlist.get(i).getTravelType()));
                rowDataList.add(new String("" + travelReportlist.get(i).getRequest_date()));
                rowDataList.add(new String("" + travelReportlist.get(i).getTravel_startdate()));
                rowDataList.add(new String("" + travelReportlist.get(i).getTravel_enddate()));
                rowDataList.add(new String("" + travelReportlist.get(i).getProjectTravel()));
                rowDataList.add(new String("" + travelReportlist.get(i).getClientReimbursement()));
                rowDataList.add(new String("" + travelReportlist.get(i).getCustomerName()));
             //   rowDataList.add(new String("" + travelReportlist.get(i).getApproved_date()));
                rowDataList.add(new String("" + travelReportlist.get(i).getDestination_from() ));
                rowDataList.add(new String("" + travelReportlist.get(i).getDestination_to() ));
                rowDataList.add(new String("" + travelReportlist.get(i).getTravel_purpose()));
                rowDataList.add(new String("" + travelReportlist.get(i).getStatus()));
                rowDataList.add(new String("" + travelReportlist.get(i).getRmActionDate()));
                rowDataList.add(new String("" + travelReportlist.get(i).getBuhActionDate()));
                rowDataList.add(new String("" + travelReportlist.get(i).getMdActionDate()));
                rowDataList.add(new String("" + travelReportlist.get(i).getFinanceActionDate()));
                rowDataList.add(new String("" + travelReportlist.get(i).getCfoActionDate()));
                rowDataList.add(new String("" + travelReportlist.get(i).getAdminActionDate()));
                rowDataList.add(new String("" + travelReportlist.get(i).getTreasuryActionDate()));
                loopRmNo = travelReportlist.get(i).getRm_emp_num();

                entireList.add(rowDataList);
            }
            CommonMethods.exportToExcel(response, entireList, "travelReport.xls", "Travel Report", null);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ModelAndView getSubSbuList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final WebApplicationContext ctx = getWebApplicationContext();
        AssociateServiceImpl bo = (AssociateServiceImpl) ctx.getBean("AssociateService");
        String sbuId = request.getParameter("id");
        response.getWriter().println("<option value='All'>--Select--</option>");
        for (AssociateFilterDTO dTO : ((bo).getSubSbu(sbuId))) {
            System.out.println("----" + dTO);
            response.getWriter().println("<option value='" + dTO.getId() + "'>" + dTO.getName() + "</option>");
        }
        return null;
    }
}
