/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.controller;

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

import com.defiance.ideal.reports.dto.AssociateFilterDTO;
import com.defiance.ideal.reports.dto.ProjectFinanceReportDTO;
import com.defiance.ideal.reports.dto.ProjectFinanceReportDetails;
import com.defiance.ideal.reports.dto.TravelReportDetails;
import com.defiance.ideal.reports.dto.TravelReportFilterDTO;
import com.defiance.ideal.reports.service.AssociateServiceImpl;
import com.defiance.ideal.reports.service.ProjectFinanceReportService;
import com.defiance.ideal.reports.service.ProjectFinanceReportServiceImpl;
import com.defiance.ideal.reports.service.TravelReportService;
import com.defiance.ideal.reports.service.TravelReportServiceImpl;
import com.defiance.ideal.reports.util.CommonConfigurations;
import com.defiance.ideal.reports.util.CommonMethods;

/**
 *
 * @author 15261
 */
public class ProjectFinanceReportController extends MultiActionController {

    public ProjectFinanceReportController() {
    }

    public ModelAndView projectFinanceReport_search(HttpServletRequest request,
            HttpServletResponse response) {
        ModelAndView mvc = null;
        mvc = new ModelAndView("/projectFinanceReport_search");
//        System.out.println("ajaxsearch Called" + request.getParameter("q"));
        try {
            String searchKey = "%" + request.getParameter("q") + "%";
//            System.out.println("searchKey " + searchKey);
            final WebApplicationContext ctx = getWebApplicationContext();
            ProjectFinanceReportService projectFinanceReportService = (ProjectFinanceReportServiceImpl) ctx
                    .getBean("ProjectFinanceReportService");
            List<ProjectFinanceReportDetails> projectDetailsList = projectFinanceReportService
                    .getProjectDetails(searchKey);
            mvc.addObject("projectDetails", projectDetailsList);
            request.setAttribute("projectDetailsList", projectDetailsList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mvc;
    }

    public ModelAndView projectFinanceReport_pm_search(
            HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mvc = null;
        mvc = new ModelAndView("/projectFinanceReport_pm_search");
        try {

            String searchKey = "%" + request.getParameter("q") + "%";
//            System.out.println("searchKey " + searchKey);
            final WebApplicationContext ctx = getWebApplicationContext();
            ProjectFinanceReportService projectFinanceReportService = (ProjectFinanceReportServiceImpl) ctx
                    .getBean("ProjectFinanceReportService");
            List<ProjectFinanceReportDetails> pmList = projectFinanceReportService
                    .getPMList(searchKey);
            mvc.addObject("pmList", pmList);
            request.setAttribute("pmList", pmList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mvc;
    }

    public ModelAndView projectFinanceReport_customer_search(
            HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mvc = null;
        mvc = new ModelAndView("/projectFinanceReport_customer_search");
//        System.out.println("ajaxsearch Called::customer search");
        try {
            String searchKey = "%" + request.getParameter("q") + "%";
//            System.out.println("searchKey " + searchKey);
            final WebApplicationContext ctx = getWebApplicationContext();
            //System.out.println(":::Inside The customer search:::"+searchKey);
            ProjectFinanceReportService projectFinanceReportService = (ProjectFinanceReportServiceImpl) ctx
                    .getBean("ProjectFinanceReportService");
            List<ProjectFinanceReportDetails> customerDetailsList = projectFinanceReportService
                    .getCustomerDetails(searchKey);
            mvc.addObject("customerDetailsList", customerDetailsList);
            request.setAttribute("customerDetailsList", customerDetailsList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mvc;
    }

    public ModelAndView projectFinanceReport(HttpServletRequest request,
            HttpServletResponse response) {
        ModelAndView mvc = null;
        mvc = new ModelAndView("/projectFinanceReport");
        try {
            final WebApplicationContext ctx = getWebApplicationContext();
            ProjectFinanceReportService projectFinanceReportService = (ProjectFinanceReportServiceImpl) ctx
                    .getBean("ProjectFinanceReportService");
            String projectString = request.getParameter("project_search");
            String customerString = request.getParameter("customer_search");
            String fromDate = request.getParameter("from_date");
            String toDate = request.getParameter("to_date");
            String poFromDate = request.getParameter("po_from_date");
            if(poFromDate == null){
                poFromDate = "";
            }
            String poToDate = request.getParameter("po_to_date");
            if(poToDate == null){
                poToDate = "";
            }
            String projectCode = null;
            String sbuId = request.getParameter("sbu");
            String subSbuId = request.getParameter("subSbu");
            String rbu = request.getParameter("rbu");
            String subRbu = request.getParameter("subRbu");
            String pmString = request.getParameter("pm_search");
            String pjtModel = request.getParameter("pjt_model");


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
            if (subRbu != null) {
                if (subRbu.equalsIgnoreCase("All")) {
                    subRbu = "";
                }
            }
            if (rbu != null) {
                if (rbu.equalsIgnoreCase("All")) {
                    rbu = "";
                }
            }
            ProjectFinanceReportDTO dto = new ProjectFinanceReportDTO();
            dto.setFromDate(fromDate);
            dto.setToDate(toDate);
            dto.setPoFromDate(poFromDate);
            dto.setPoToDate(poToDate);
            dto.setSbuId(sbuId);
            dto.setSubSbuId(subSbuId);
            dto.setRbu(rbu);
            dto.setSubRbu(subRbu);
            dto.setProjectModel(pjtModel);

            if (projectString != null) {
                StringTokenizer str = new StringTokenizer(projectString, "-");
                for (int i = 0; i < str.countTokens() - 1; i++) {
                    projectCode = str.nextToken().trim();
                }
                dto.setProjectCode(projectCode);
            }
            String customerCode = "";
            if (customerString != null) {
                StringTokenizer str = new StringTokenizer(customerString, "-");
                for (int i = 0; i < str.countTokens() - 1; i++) {
                    customerCode = str.nextToken().trim();
                }
                dto.setCustomerCode(customerCode);
            }

            String pmEmpNumberString = request.getParameter("pm_search");
            String pmEmpNumber = null;
            if (pmEmpNumberString != null) {
                StringTokenizer str = new StringTokenizer(pmEmpNumberString,
                        "-");
                for (int i = 0; i < str.countTokens() - 1; i++) {
                    pmEmpNumber = str.nextToken().trim();
                }
                dto.setPmEmpNumber(pmEmpNumber);
            }

            int page;
            int recordCount;
            if (request.getParameter("submit") != null) {
                page = 1;
            } else {
                page = Integer.parseInt(request.getParameter("page"));
            }
            int rows = CommonConfigurations.pageCount;
            int start;
            start = ((page - 1) * rows);
            dto.setStart_page(start);
            dto.setEnd_page(rows);
            List<ProjectFinanceReportDetails> reportDetailsList = projectFinanceReportService
                    .getReportDetails(dto);
            recordCount = projectFinanceReportService.getReportDetailsCount(dto);
            int end = (start+rows)-1;
            int[] paging = CommonMethods.paging(recordCount, page, rows);
            mvc.addObject("reportDetailsList", reportDetailsList);
            request.setAttribute("reportDetailsList", reportDetailsList);

            List<ProjectFinanceReportDetails> sbuList = projectFinanceReportService.getSbuList();
//            System.out.println("sbu list from prject finance:::" + sbuList);
            mvc.addObject("sbuList", sbuList);
            request.setAttribute("sbuList", sbuList);

            List<ProjectFinanceReportDetails> rbuList = projectFinanceReportService.getRbuList();
            List<ProjectFinanceReportDetails> projectModelList = projectFinanceReportService.getProjectModelList();
//			List<ProjectFinanceReportDetails> projectModelList = new ArrayList<ProjectFinanceReportDetails>();
//                        
//                        ProjectFinanceReportDetails list = new ProjectFinanceReportDetails();
//                        
//                        
//                        list.setProjectModelKey("c");
//                        list.setProjectModelValue("Competency");
//                        
//                        for(int i=0;i<=4;i++){
//                           projectModelList.add(list);
//                        }
            mvc.addObject("paging", paging);
            mvc.addObject("rbuList", rbuList);
            request.setAttribute("rbuList", rbuList);
            mvc.addObject("projectString", projectString);
            mvc.addObject("customerString", customerString);
            mvc.addObject("pmString", pmString);
            mvc.addObject("fromDate", fromDate);
            mvc.addObject("toDate", toDate);
            mvc.addObject("poFromDate", poFromDate);
            mvc.addObject("poToDate", poToDate);
            mvc.addObject("projectModelList", projectModelList);

            mvc.addObject("selectedSbuId", sbuId);
            request.setAttribute("selectedSbuId", sbuId);
            request.setAttribute("selectedRbuId", rbu);
            mvc.addObject("selectedRbuId", rbu);

            request.setAttribute("selectedSubSbu", subSbuId);
            mvc.addObject("selectedSubSbu", subSbuId);

            request.setAttribute("selectedSubRbu", subRbu);
            mvc.addObject("selectedSubRbu", subRbu);

            request.setAttribute("selectedPjtModel", pjtModel);
            mvc.addObject("selectedPjtModel", pjtModel);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mvc;
    }

     public ModelAndView projectFinanceReportXL(HttpServletRequest request,
            HttpServletResponse response) {
//        System.out.println("projectFinanceReportXL Method Called");

        try {
            final WebApplicationContext ctx = getWebApplicationContext();
            ProjectFinanceReportService projectFinanceReportService = (ProjectFinanceReportServiceImpl) ctx
                    .getBean("ProjectFinanceReportService");

            ArrayList entireList = new ArrayList();
            ArrayList<String> rowDataList;
            String projectString = request.getParameter("project_search");
            String fromDate = request.getParameter("from_date");
            String toDate = request.getParameter("to_date");
            String poFromDate = request.getParameter("po_from_date");
            if(poFromDate == null){
                poFromDate = "";
            }
            String poToDate = request.getParameter("po_to_date");
            if(poToDate == null){
                poToDate = "";
            }
            String projectCode = null;
            String sbuId = request.getParameter("sbu");
            String subSbuId = request.getParameter("subSbu");
            String rbu = request.getParameter("rbu");
            String subRbu = request.getParameter("subRbu");
            String pmString = request.getParameter("pm_search");
            String pjt_model = request.getParameter("pjt_model");

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
            if (subRbu != null) {
                if (subRbu.equalsIgnoreCase("All")) {
                    subRbu = "";
                }
            }
            if (rbu != null) {
                if (rbu.equalsIgnoreCase("All")) {
                    rbu = "";
                }
            }
            ProjectFinanceReportDTO dto = new ProjectFinanceReportDTO();
            dto.setFromDate(fromDate);
            dto.setToDate(toDate);
            dto.setPoFromDate(poFromDate);
            dto.setPoToDate(poToDate);
            dto.setSbuId(sbuId);
            dto.setSubSbuId(subSbuId);
            dto.setRbu(rbu);
            dto.setSubRbu(subRbu);
            dto.setProjectModel(pjt_model);

            if (projectString != null) {
                StringTokenizer str = new StringTokenizer(projectString, "-");
                for (int i = 0; i < str.countTokens() - 1; i++) {
                    projectCode = str.nextToken().trim();
                }
                dto.setProjectCode(projectCode);
            }

            String pmEmpNumberString = request.getParameter("pm_search");
            String pmEmpNumber = null;
            if (pmEmpNumberString != null) {
                StringTokenizer str = new StringTokenizer(pmEmpNumberString,
                        "-");
                for (int i = 0; i < str.countTokens() - 1; i++) {
                    pmEmpNumber = str.nextToken().trim();
                }
                dto.setPmEmpNumber(pmEmpNumber);
            }
            String customerString = request.getParameter("customer_search");
            String customerCode = "";
            if (customerString != null) {
                StringTokenizer str = new StringTokenizer(customerString, "-");
                for (int i = 0; i < str.countTokens() - 1; i++) {
                    customerCode = str.nextToken().trim();
                }
                dto.setCustomerCode(customerCode);
            }

            List<ProjectFinanceReportDetails> reportDetailsList = projectFinanceReportService.getReportDetails(dto);
            if (reportDetailsList != null && reportDetailsList.size() > 0) {

                // Set all data in Arraylist and send to exportToExcel() function.

                rowDataList = new ArrayList<String>();
                rowDataList.add(new String("Project Code"));
                rowDataList.add(new String("PO Number"));
                rowDataList.add(new String("PO Date"));
                rowDataList.add(new String("Customer Group"));
                rowDataList.add(new String("Customer Code"));
                rowDataList.add(new String("Customer Name"));
                rowDataList.add(new String("RBU"));
                rowDataList.add(new String("Sub RBU"));
                rowDataList.add(new String("Region"));
                rowDataList.add(new String("Country"));
                rowDataList.add(new String("Registered Address"));
                rowDataList.add(new String("Customer Contact"));
                rowDataList.add(new String("Customer Phone No."));
                rowDataList.add(new String("BDM ID"));
                rowDataList.add(new String("Hinduja Tech Contact"));
                rowDataList.add(new String("Hinduja Tech Contact No."));
                rowDataList.add(new String("SBU"));
                rowDataList.add(new String("Sub SBU"));
                rowDataList.add(new String("Project Name"));
                rowDataList.add(new String("Pricing Model"));
                rowDataList.add(new String("Currency"));
                rowDataList.add(new String("PO Amount"));
                rowDataList.add(new String("SO Value"));
                rowDataList.add(new String("Project Start Date"));
                rowDataList.add(new String("Project End Date"));
                rowDataList.add(new String("Remarks"));
                rowDataList.add(new String("Project Status"));
                rowDataList.add(new String("Project Manager"));
                rowDataList.add(new String("Billing UOM"));
                rowDataList.add(new String("Project Model"));
                rowDataList.add(new String("Entity"));
                rowDataList.add(new String("Business Model"));
                entireList.add(rowDataList);

                Iterator<ProjectFinanceReportDetails> itr = reportDetailsList.iterator();
                ProjectFinanceReportDetails pd = null;
                while (itr.hasNext()) {
                    pd = itr.next();
                    rowDataList = new ArrayList<String>();
                    rowDataList.add(pd.getProject_code());
                    rowDataList.add(pd.getPoNumber());
                    rowDataList.add(pd.getPoDate());
                    if (pd.getCustomerGroup() == null) {
                        rowDataList.add("");
                    } else {
                        rowDataList.add(pd.getCustomerGroup());
                    }
                    if (pd.getCustomer_code() == null) {
                        rowDataList.add("");
                    } else {
                        rowDataList.add(pd.getCustomer_code());
                    }
                    if (pd.getCustomer_name() == null) {
                        rowDataList.add("");
                    } else {
                        rowDataList.add(pd.getCustomer_name());
                    }
                    rowDataList.add(pd.getRbu());
                    if (pd.getSubRbu() == null) {
                        rowDataList.add("");
                    } else {
                        rowDataList.add(pd.getSubRbu());
                    }
                    if (pd.getRegion() == null) {
                        rowDataList.add("");
                    } else {
                        rowDataList.add(pd.getRegion());
                    }
                    if (pd.getCountry() == null) {
                        rowDataList.add("");
                    } else {
                        rowDataList.add(pd.getCountry());
                    }
                    if (pd.getRegisteredAddress() == null) {
                        rowDataList.add("");
                    } else {
                        rowDataList.add(pd.getRegisteredAddress());
                    }
                    if (pd.getCustomerContact() == null) {
                        rowDataList.add("");
                    } else {
                        rowDataList.add(pd.getCustomerContact());
                    }
                    if (pd.getCustomerPhoneNo() == null) {
                        rowDataList.add("");
                    } else {
                        rowDataList.add(pd.getCustomerPhoneNo());
                    }
                    if (pd.getBdmId() == null) {
                        rowDataList.add("");
                    } else {
                        rowDataList.add(pd.getBdmId());
                    }
                    if (pd.getDefianceContact() == null) {
                        rowDataList.add("");
                    } else {
                        rowDataList.add(pd.getDefianceContact());
                    }
                    if (pd.getDefianceContactNo() == null) {
                        rowDataList.add("");
                    } else {
                        rowDataList.add(pd.getDefianceContactNo());
                    }
                    rowDataList.add(pd.getSbu());
                    if (pd.getSubSbu() == null) {
                        rowDataList.add("");
                    } else {
                        rowDataList.add(pd.getSubSbu());
                    }
                    rowDataList.add(pd.getProject_name());
                    rowDataList.add(pd.getPricingModel());
                    rowDataList.add(pd.getCurrency());
                    rowDataList.add(pd.getPoAmount());
                    if (pd.getCreditPeriod() == null) {
                        rowDataList.add("");
                    } else {
                        rowDataList.add(pd.getCreditPeriod());
                    }
                    rowDataList.add(pd.getProjectStartDate());
                    rowDataList.add(pd.getProjectEndDate());
                    if (pd.getRemarks() == null) {
                        rowDataList.add("");
                    } else {
                        rowDataList.add(pd.getRemarks());
                    }
                    rowDataList.add(pd.getProjectStatus());
                    rowDataList.add(pd.getProjectManager());
                    rowDataList.add(pd.getBillingUOM());
                    rowDataList.add(pd.getPrjModel());
                    rowDataList.add(pd.getLegal_entity());
                    rowDataList.add(pd.getBusiness_model());
                    entireList.add(rowDataList);
                }
            }

            request.setAttribute("reportDetailsList", reportDetailsList);

            List<ProjectFinanceReportDetails> sbuList = projectFinanceReportService
                    .getSbuList();
            request.setAttribute("sbuList", sbuList);

            List<ProjectFinanceReportDetails> rbuList = projectFinanceReportService
                    .getRbuList();
            request.setAttribute("rbuList", rbuList);
            request.setAttribute("projectString", projectString);
            request.setAttribute("pmString", pmString);
            request.setAttribute("fromDate", fromDate);
            request.setAttribute("toDate", toDate);
            request.setAttribute("poFromDate", poFromDate);
            request.setAttribute("poToDate", poToDate);

            request.setAttribute("selectedSbuId", sbuId);
            request.setAttribute("selectedRbuId", rbu);

            request.setAttribute("selectedSubSbu", subSbuId);
            request.setAttribute("selectedSubRbu", subRbu);

            CommonMethods.exportToExcel(response, entireList,
                    "projectFinanceReport.xls", "Project Finance Report", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ModelAndView getSubSbuList(HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        final WebApplicationContext ctx = getWebApplicationContext();
        AssociateServiceImpl bo = (AssociateServiceImpl) ctx
                .getBean("AssociateService");
        String sbuId = request.getParameter("id");
        response.getWriter().println("<option value='All'>--Select--</option>");
        for (AssociateFilterDTO dTO : ((bo).getSubSbu(sbuId))) {
//            System.out.println("----" + dTO);
            response.getWriter().println(
                    "<option value='" + dTO.getId() + "'>" + dTO.getName()
                    + "</option>");
        }
        return null;
    }

    public ModelAndView getSubRbuList(HttpServletRequest request,
            HttpServletResponse response) throws IOException {
//        System.out.println("Inside getSubRbuList");
        final WebApplicationContext ctx = getWebApplicationContext();
        ProjectFinanceReportService projectFinanceReportService = (ProjectFinanceReportServiceImpl) ctx
                .getBean("ProjectFinanceReportService");
        String rbuId = request.getParameter("id");
        response.getWriter().println("<option value='All'>--Select--</option>");
        for (ProjectFinanceReportDetails dTO : ((projectFinanceReportService)
                .getSubRbu(rbuId))) {
//            System.out.println("----" + dTO);
            response.getWriter().println(
                    "<option value='" + dTO.getId() + "'>" + dTO.getName()
                    + "</option>");
        }
        return null;
    }
}
