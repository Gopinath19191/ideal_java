/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.controller;

import com.defiance.ideal.reports.dto.AssociateDataDTO;
import com.defiance.ideal.reports.dto.EmployeeBasicReportDTO;
import com.defiance.ideal.reports.service.EmployeeBasicReportServiceImpl;
import com.defiance.ideal.reports.util.CommonConfigurations;
import com.defiance.ideal.reports.util.CommonMethods;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 *
 * @author 16113
 */
public class EmployeeBasicReportController extends MultiActionController {

    public EmployeeBasicReportController() {
    }

    public ModelAndView exEmployeeList(HttpServletRequest request, HttpServletResponse response, AssociateDataDTO dto) throws Exception {

        ModelAndView mvc = new ModelAndView("employeeBasicReport");
        int flag = 0;
        final WebApplicationContext ctx = getWebApplicationContext();
        EmployeeBasicReportServiceImpl bo = (EmployeeBasicReportServiceImpl) ctx.getBean("EmployeeBasicReportService");
        List<EmployeeBasicReportDTO> details = null;
        String status = dto.getStatus();

        String PES = CommonConfigurations.PES;
        String TS = CommonConfigurations.TS;
        String HR = CommonConfigurations.HR;
        String Operations = CommonConfigurations.Operations;
        String Finance = CommonConfigurations.Finance;
        String Corporate = CommonConfigurations.Corporate;
        String Sales = CommonConfigurations.Sales;
        String parentId = bo.getParent_id();
        
        List<EmployeeBasicReportDTO> Sbu = bo.getSbu("1,12");
        List<EmployeeBasicReportDTO> subSbu = bo.getSubSbu(parentId);
        List<EmployeeBasicReportDTO> empStatus = bo.getEmpStatus();
        int page = 0;
        if ("Go".equals(request.getParameter("go"))) {
            page = 1;
            flag = 1;
        } else if (request.getParameter("page") != null) {

            System.out.println("page count" + dto.getPage());
            page = dto.getPage();
            flag = 1;

        } else {
            page = 1;
            flag = 0;
        }
        int rows = CommonConfigurations.pageCount;
        int start;
        start = ((page - 1) * rows);
        dto.setStart_page(start);
        dto.setEnd_page(rows);
        int recordCount = 0;
        if (dto.getJoinedStartDate() != null && !"".equals(dto.getJoinedStartDate())) {
            String[] start_date = dto.getJoinedStartDate().split("-");
            dto.setJoinedStartDate(start_date[2] + "-" + start_date[1] + "-" + start_date[0]);
        }
        if (dto.getJoinedEndDate() != null && !"".equals(dto.getJoinedEndDate())) {
            String[] end_date = dto.getJoinedEndDate().split("-");
            dto.setJoinedEndDate(end_date[2] + "-" + end_date[1] + "-" + end_date[0]);
        }
        System.out.println("start date " + dto.getJoinedStartDate());
        System.out.println("end date " + dto.getJoinedEndDate());


        if (flag == 1) {
            details = bo.fetchAssociate(dto);
            recordCount = bo.fetchAssociateListCount(dto);
        }

        int end = (start + rows) - 1;
        int[] paging = CommonMethods.paging(recordCount, page, rows);
        mvc.addObject("paging", paging);
        /*----------------*/

        mvc.addObject("list", details);
        if (dto.getJoinedStartDate() != null && !"".equals(dto.getJoinedStartDate())) {
            String[] start_date = dto.getJoinedStartDate().split("-");
            dto.setJoinedStartDate(start_date[2] + "-" + start_date[1] + "-" + start_date[0]);
        }
        if (dto.getJoinedEndDate() != null && !"".equals(dto.getJoinedEndDate())) {
            String[] end_date = dto.getJoinedEndDate().split("-");
            dto.setJoinedEndDate(end_date[2] + "-" + end_date[1] + "-" + end_date[0]);
        }
        mvc.addObject("result", dto);
        mvc.addObject("sbu", Sbu);
        mvc.addObject("subSbu", subSbu);
        mvc.addObject("empStatus", empStatus);
        mvc.addObject("status", status);
        return mvc;

    }

    public ModelAndView EmployeeExcelexport(HttpServletRequest request, HttpServletResponse response, AssociateDataDTO dto) throws Exception {
        ModelAndView mvc = new ModelAndView("associate");
        final WebApplicationContext ctx = getWebApplicationContext();
        EmployeeBasicReportServiceImpl bo = (EmployeeBasicReportServiceImpl) ctx.getBean("EmployeeBasicReportService");
        if (dto.getJoinedStartDate() != null && !"".equals(dto.getJoinedStartDate())) {
            String[] start_date = dto.getJoinedStartDate().split("-");
            dto.setJoinedStartDate(start_date[2] + "-" + start_date[1] + "-" + start_date[0]);
        }
        if (dto.getJoinedEndDate() != null && !"".equals(dto.getJoinedEndDate())) {
            String[] end_date = dto.getJoinedEndDate().split("-");
            dto.setJoinedEndDate(end_date[2] + "-" + end_date[1] + "-" + end_date[0]);
        }
        List<EmployeeBasicReportDTO> details = bo.fetchAssociate(dto);
        List<EmployeeBasicReportDTO> joiner = bo.getJoinerDetails();
        List<EmployeeBasicReportDTO> released = bo.getRelievedDetails();
        List<EmployeeBasicReportDTO> employee_count = bo.fetchEmployeeCount();
        List<EmployeeBasicReportDTO> employee_total_count = bo.fetchEmployeeTotalCount();
        ArrayList entireList = new ArrayList();
        ArrayList rowDataHead = new ArrayList();
        rowDataHead.add("BU");
        rowDataHead.add("Billable");
        rowDataHead.add("DNB");
        rowDataHead.add("General Administration");
        rowDataHead.add("Sales");
        rowDataHead.add("Grand Total");
        entireList.add(rowDataHead);
        for (int j = 0; j < employee_count.size(); j++) {
            ArrayList rowData = new ArrayList();
            rowData.add(new String("" + employee_count.get(j).getUnit_name()));
            rowData.add(new String("" + employee_count.get(j).getEmp_billable()));
            rowData.add(new String("" + employee_count.get(j).getEmp_dnb()));
            rowData.add(new String("" + employee_count.get(j).getEmp_general()));
            rowData.add(new String("" + employee_count.get(j).getEmp_sales()));
            rowData.add(new String("" + employee_count.get(j).getEmp_total()));
            entireList.add(rowData);
        }

        for (int k = 0; k < employee_total_count.size(); k++) {
            ArrayList rowDataTotal = new ArrayList();
            rowDataTotal.add(employee_total_count.get(k).getUnit_name());
            rowDataTotal.add(employee_total_count.get(k).getEmp_billable());
            rowDataTotal.add(employee_total_count.get(k).getEmp_dnb());
            rowDataTotal.add(employee_total_count.get(k).getEmp_general());
            rowDataTotal.add(employee_total_count.get(k).getEmp_sales());
            rowDataTotal.add(employee_total_count.get(k).getEmp_total());
            entireList.add(rowDataTotal);
        }
        
        ArrayList joinerEmpty = new ArrayList();
        joinerEmpty.add("");
        entireList.add(joinerEmpty);
        entireList.add(joinerEmpty);
        ArrayList rowJoinerHeader = new ArrayList();
        ArrayList rowJoinerHeader1 = new ArrayList();
        rowJoinerHeader1.add("New Joiner");
        entireList.add(rowJoinerHeader1);
        rowJoinerHeader.add("Employee Number");
        rowJoinerHeader.add("Employee Name");
        rowJoinerHeader.add("Join Date");
        rowJoinerHeader.add("BU");
        rowJoinerHeader.add("Practice");
        rowJoinerHeader.add("Sub Practice");
        rowJoinerHeader.add("Band Name");
        rowJoinerHeader.add("RM Name");
        rowJoinerHeader.add("Designation");
        rowJoinerHeader.add("Category");
        rowJoinerHeader.add("Status");
        rowJoinerHeader.add("Location");
        rowJoinerHeader.add("Country");
        entireList.add(rowJoinerHeader);
        System.out.println("new joiner "+joiner.size());
        for (int i = 0; i < joiner.size(); i++) {
            ArrayList rowDataJoinerList = new ArrayList();
            rowDataJoinerList.add(new String("" + joiner.get(i).getEmpNumber()));
            rowDataJoinerList.add(new String("" + joiner.get(i).getEmpName()));
            rowDataJoinerList.add(new String("" + joiner.get(i).getJoinDate()));
            rowDataJoinerList.add(new String("" + joiner.get(i).getSbu()));
            rowDataJoinerList.add(new String("" + joiner.get(i).getSubSbu()));
            rowDataJoinerList.add(new String("" + joiner.get(i).getSubPractice()));
            rowDataJoinerList.add(new String("" + joiner.get(i).getBandName()));
            rowDataJoinerList.add(new String("" + joiner.get(i).getRmName()));
            rowDataJoinerList.add(new String("" + joiner.get(i).getDesignation()));
            rowDataJoinerList.add(new String("" + joiner.get(i).getBillable()));
            rowDataJoinerList.add(new String("" + joiner.get(i).getStatus()));
            rowDataJoinerList.add(new String("" + joiner.get(i).getCurrentLocation()));
            rowDataJoinerList.add(new String("" + joiner.get(i).getCurrentWrkLocation()));
            entireList.add(rowDataJoinerList);
        }
        if(joiner.size()==0){
            ArrayList rowDataJoinerList = new ArrayList();
            rowDataJoinerList.add("No Joiners yesterday");
            entireList.add(rowDataJoinerList);
        }
        
        ArrayList relievedEmpty = new ArrayList();
        relievedEmpty.add("");
        entireList.add(relievedEmpty);
        entireList.add(relievedEmpty);
        ArrayList rowRelievedHeader = new ArrayList();
        ArrayList rowRelievedHeader1 = new ArrayList();
        rowRelievedHeader1.add("Reliever Details");
        entireList.add(rowRelievedHeader1);
        rowRelievedHeader.add("Employee Number");
        rowRelievedHeader.add("Employee Name");
        rowRelievedHeader.add("Join Date");
        rowRelievedHeader.add("Relieved Date");
        rowRelievedHeader.add("BU");
        rowRelievedHeader.add("Practice");
        rowRelievedHeader.add("Sub Practice");
        rowRelievedHeader.add("Band Name");
        rowRelievedHeader.add("RM Name");
        rowRelievedHeader.add("Designation");
        rowRelievedHeader.add("Category");
        rowRelievedHeader.add("Status");
        rowRelievedHeader.add("Location");
        rowRelievedHeader.add("Country");
        entireList.add(rowRelievedHeader);
        System.out.println("new joiner "+released.size());
        for (int i = 0; i < released.size(); i++) {
            ArrayList rowDataRelievedList = new ArrayList();
            rowDataRelievedList.add(new String("" + released.get(i).getEmpNumber()));
            rowDataRelievedList.add(new String("" + released.get(i).getEmpName()));
            rowDataRelievedList.add(new String("" + released.get(i).getJoinDate()));
            rowDataRelievedList.add(new String("" + released.get(i).getRelieved()));
            rowDataRelievedList.add(new String("" + released.get(i).getSbu()));
            rowDataRelievedList.add(new String("" + released.get(i).getSubSbu()));
            rowDataRelievedList.add(new String("" + released.get(i).getSubPractice()));
            rowDataRelievedList.add(new String("" + released.get(i).getBandName()));
            rowDataRelievedList.add(new String("" + released.get(i).getRmName()));
            rowDataRelievedList.add(new String("" + released.get(i).getDesignation()));
            rowDataRelievedList.add(new String("" + released.get(i).getBillable()));
            rowDataRelievedList.add(new String("" + released.get(i).getStatus()));
            rowDataRelievedList.add(new String("" + released.get(i).getCurrentLocation()));
            rowDataRelievedList.add(new String("" + released.get(i).getCurrentWrkLocation()));
            entireList.add(rowDataRelievedList);
        }
        if(released.size()==0){
            ArrayList rowDataJoinerList = new ArrayList();
            rowDataJoinerList.add("No Relievers yesterday");
            entireList.add(rowDataJoinerList);
        }
        ArrayList rowEmpty = new ArrayList();
        rowEmpty.add("");
        entireList.add(rowEmpty);
        entireList.add(rowEmpty);
        ArrayList rowDataHeadList = new ArrayList();
        rowDataHeadList.add("Employee Number");
        rowDataHeadList.add("Employee Name");
//        rowDataHeadList.add("Gender");
        rowDataHeadList.add("Join Date");
        rowDataHeadList.add("Org Unit");
        rowDataHeadList.add("BU");
        rowDataHeadList.add("Practice");
        rowDataHeadList.add("Sub Practice");
        rowDataHeadList.add("Band Name");
        rowDataHeadList.add("Total Experience(Years)");
        rowDataHeadList.add("Total Experience(Months)");
        rowDataHeadList.add("RM ID");
        rowDataHeadList.add("RM Name");
        rowDataHeadList.add("Designation");
        rowDataHeadList.add("Category");
        rowDataHeadList.add("Status");
        rowDataHeadList.add("Email");
        rowDataHeadList.add("Base Location");
        rowDataHeadList.add("Country");
        rowDataHeadList.add("Current Work Locations");
        rowDataHeadList.add("Exit Status");
        rowDataHeadList.add("Resigned Date");
        rowDataHeadList.add("Relieved Date");
        rowDataHeadList.add("Contract Start Date");
        rowDataHeadList.add("Contract End Date");
        entireList.add(rowDataHeadList);
        for (int i = 0; i < details.size(); i++) {
            ArrayList rowDataList = new ArrayList();
            rowDataList.add(new String("" + details.get(i).getEmpNumber()));
            rowDataList.add(new String("" + details.get(i).getEmpName()));
//            rowDataList.add(new String("" + details.get(i).getGender()));
            rowDataList.add(new String("" + details.get(i).getJoinDate()));
            rowDataList.add(new String("" + details.get(i).getOrgUnit()));
            rowDataList.add(new String("" + details.get(i).getSbu()));
            rowDataList.add(new String("" + details.get(i).getSubSbu()));
            rowDataList.add(new String("" + details.get(i).getSubPractice()));
            rowDataList.add(new String("" + details.get(i).getBandName()));
            rowDataList.add(new String("" + details.get(i).getTotalYearExp()));
            rowDataList.add(new String("" + details.get(i).getTotalMonthExp()));
            rowDataList.add(new String("" + details.get(i).getRmId()));
            rowDataList.add(new String("" + details.get(i).getRmName()));
            rowDataList.add(new String("" + details.get(i).getDesignation()));
            rowDataList.add(new String("" + details.get(i).getBillable()));
            rowDataList.add(new String("" + details.get(i).getStatus()));
            rowDataList.add(new String("" + details.get(i).getEmail()));
            rowDataList.add(new String("" + details.get(i).getCurrentLocation()));
            rowDataList.add(new String("" + details.get(i).getCurrentcountry()));
            rowDataList.add(new String("" + details.get(i).getCurrentWrkLocation()));
            rowDataList.add(new String("" + details.get(i).getExitStatus()));
            rowDataList.add(new String("" + details.get(i).getResigned()));
            rowDataList.add(new String("" + details.get(i).getRelieved()));
            rowDataList.add(new String("" + details.get(i).getContractStartDate()));
            rowDataList.add(new String("" + details.get(i).getContractExpiryDate()));
            entireList.add(rowDataList);
        }
        DateFormat dateFormat = new SimpleDateFormat("_dd_MM_yyyy_HH_mm_ss");
        Date date = new Date();
        CommonMethods.exportToExcel(response, entireList, "employee_report", "employee", dateFormat.format(date));

        return null;
    }
//	public ModelAndView getSubPracticeGroup(HttpServletRequest request, HttpServletResponse response) throws IOException {
//		final WebApplicationContext ctx = getWebApplicationContext();
//		AssociateServiceImpl bo = (AssociateServiceImpl) ctx.getBean("AssociateService");
//		//  System.out.println("Id value In Associate Controller---->"+request.getParameter("id"));
//		response.getWriter().println("<option value='All'>All</option>");
//		for (AssociateFilterDTO dTO : ((bo).getSubSbu((request.getParameter("id"))))) {
//			//   System.out.println("----" + dTO);
//                    response.getWriter().println("<option value='" + dTO.getId() + "'>" + dTO.getName() + "</option>");
//		}
//		return null;
//	}
}