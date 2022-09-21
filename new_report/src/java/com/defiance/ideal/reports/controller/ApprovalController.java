/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.controller;

import com.defiance.ideal.reports.dto.ApprovalDTO;
import com.defiance.ideal.reports.service.ApprovalServiceImpl;
import com.defiance.ideal.reports.util.CommonFunctions;
import com.defiance.ideal.reports.util.CommonMethods;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 *
 * @author 16364
 */
public class ApprovalController extends MultiActionController{
     ModelAndView mv = null;
    public synchronized ModelAndView pmApproval(HttpServletRequest request, HttpServletResponse response, ApprovalDTO appDTO) throws IllegalAccessException, IOException {
        try {
            HttpSession session = request.getSession();
            List<ApprovalDTO> projectList = null;
            List<ApprovalDTO> workTypeList = null;
          //  String employeeId = (String) session.getAttribute("accessId");
            String employeeId=null;
            System.out.println("session getatt*** "+session.getAttribute("EMP_ID"));
            if(session.getAttribute("EMP_ID")==null){
            employeeId = request.getParameter("empid");
            session.setAttribute("EMP_ID", employeeId);
            }else{
            employeeId = (String) session.getAttribute("EMP_ID");
            }
            String status = request.getParameter("status");
            String projectId = request.getParameter("prjId");

            appDTO.setEmployeeId(employeeId);
            ApprovalServiceImpl serviceImpl = (ApprovalServiceImpl) getApplicationContext().getBean("ApprovalService");
            String approveType = request.getParameter("list");
            appDTO.setApproveType(approveType);
            projectList = serviceImpl.getProjectList(appDTO);
           


            response.getWriter().println("<option value=''>-   Select Employee  -</option>");
            if (projectId != "") {
                for (ApprovalDTO dTO : this.getEmployeeList(appDTO)) {
                    if (dTO.getEmpId() != null) {
                        response.getWriter().println("<option title = '" + dTO.getEmployeeNumber() + ' ' + dTO.getEmpName() + "' value='" + dTO.getEmpId() + "'>" + dTO.getEmployeeNumber() + ' ' + dTO.getEmpName() + "</option>");
                    }
                }
            }
            mv = new ModelAndView("/approval");
            mv.addObject("employeeList", this.getEmployeeList(appDTO));
            mv.addObject("projectList", projectList);
           
            mv.addObject("selectedList", approveType);
            mv.addObject("status", status);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }
    public synchronized ModelAndView filterData(HttpServletRequest request, HttpServletResponse response, ApprovalDTO filterDTO) throws Exception {
        try {

            HttpSession session = request.getSession(true);
            List<ApprovalDTO> projectList = null;
            List<ApprovalDTO> workTypeList = null;

            List<ApprovalDTO> filterDataList = null;
          //  String employeeId = (String) session.getAttribute("accessId");
            String employeeId=null;
            System.out.println("session getatt*** "+session.getAttribute("EMP_ID"));
               System.out.println("$$$$ "+request.getParameter("empid"));
            if(session.getAttribute("EMP_ID")==null){
            employeeId = request.getParameter("empid");
                System.out.println("$$$$ "+request.getParameter("empid"));
            session.setAttribute("EMP_ID", employeeId);
            }else{
            employeeId = (String) session.getAttribute("EMP_ID");
            }
            String shiftType = CommonFunctions.shiftType;
            String pricingType = CommonFunctions.pricingType;
            String locationType = CommonFunctions.locationType;
            filterDTO.setShiftKey(shiftType);
            filterDTO.setLocationKey(locationType);
            filterDTO.setPricingKey(pricingType);
            filterDTO.setEmployeeId(employeeId);
            filterDTO.setProjectId(filterDTO.getProjectName());
            System.out.println("proejct id  name*** "+filterDTO.getProjectName());
            filterDTO.setEmpId(filterDTO.getEmpName());
            filterDTO.setApproveType(request.getParameter("approveType"));
            filterDTO.setWorkTypeKey(filterDTO.getRegularName());
            String selectedValue = " ";
            String selectedAppHrsValue =" ";
            if (filterDTO.getProjectName() != "Non_Project_Activity" && !"Non_Project_Activity".equals(filterDTO.getProjectName())) {
                
                if (filterDTO.getEmpName() == null || "".equals(filterDTO.getEmpName())) {
                    System.out.println("inside iif "+filterDTO.getEmpName());
                    selectedValue = selectedValue+"" + "AND ((tep.project_id='" + filterDTO.getProjectId() +"' AND ProjectRole.status = 'a' )"+" )";
                }else{
                    System.out.println("inside else "+filterDTO.getEmpName());
                    selectedValue = selectedValue+"" + "AND (( ProjectRole.status = 'a' )"+" OR tep.project_id='Non_Project_Activity')";
                }
                
            }
            if (filterDTO.getProjectName() == "Non_Project_Activity" || "Non_Project_Activity".equals(filterDTO.getProjectName())) {
                if (filterDTO.getEmpName() == null || "".equals(filterDTO.getEmpName())) {
                     selectedValue = selectedValue + "AND tep.project_id='" + filterDTO.getProjectId() + "' ";

                }else{
                     selectedValue = selectedValue ;
                }

            }
             
              
            if (filterDTO.getEmpName() != null && !"".equals(filterDTO.getEmpName())) {
                
                selectedValue = selectedValue + "AND te.employee_id=" + filterDTO.getEmpName() + " ";
            }
            if (filterDTO.getFromDate() != null && !"".equals(filterDTO.getFromDate())) {
                selectedValue = selectedValue + "AND te.timesheet_date >=STR_TO_DATE(" + "'" + filterDTO.getFromDate() + "','%d-%m-%Y')" + " ";
            }
            if (filterDTO.getToDate() != null && !"".equals(filterDTO.getToDate())) {
                selectedValue = selectedValue + "AND te.timesheet_date <=STR_TO_DATE(" + "'" + filterDTO.getToDate() + "','%d-%m-%Y')" + " ";
            }
            
            if (filterDTO.getEmpName() != null && !"".equals(filterDTO.getEmpName())) {
                
                selectedAppHrsValue = selectedAppHrsValue + "AND te.employee_id=" + filterDTO.getEmpName() + " ";
            }
            if (filterDTO.getFromDate() != null && !"".equals(filterDTO.getFromDate())) {
                selectedAppHrsValue = selectedAppHrsValue + "AND te.timesheet_date >=STR_TO_DATE(" + "'" + filterDTO.getFromDate() + "','%d-%m-%Y')" + " ";
            }
            if (filterDTO.getToDate() != null && !"".equals(filterDTO.getToDate())) {
                selectedAppHrsValue = selectedAppHrsValue + "AND te.timesheet_date <=STR_TO_DATE(" + "'" + filterDTO.getToDate() + "','%d-%m-%Y')" + " ";
            }
            
            selectedAppHrsValue=selectedAppHrsValue;
            filterDTO.setValue(selectedValue);
            filterDTO.setAppHrsVal(selectedAppHrsValue);
            System.out.println("filterdto selectedValue"+selectedValue);
            ApprovalServiceImpl serviceImpl = (ApprovalServiceImpl) getApplicationContext().getBean("ApprovalService");
            projectList = serviceImpl.getProjectList(filterDTO);
            workTypeList=serviceImpl.getWorkTypeList(filterDTO);
            int page = Integer.parseInt(request.getParameter("page"));
            int rows = 60;
            int start;
            start = ((page - 1) * rows);
            filterDTO.setStart_page(start);
            filterDTO.setEnd_page(rows);
            filterDataList = serviceImpl.filterDataList(filterDTO);
//              System.out.println("filterDataList --"+filterDataList.get(0).getEmployeeNumber());
            String recordCount = serviceImpl.recordCount(filterDTO);
       //  String appHrs = serviceImpl.getApprovedHrsProjects(filterDTO);
         
           
            mv = new ModelAndView("/approval");
            int[] pageNo = paging(rows, recordCount, page);
          //    System.out.println("page No length----" + pageNo.length + "****page number***" + pageNo);
            mv.addObject("paging", paging(rows, recordCount, page));
            mv.addObject("tableHeader", filterDTO.getProjectName());
            mv.addObject("projectList", projectList);
            mv.addObject("workTypeList", workTypeList);
            mv.addObject("requsetorDTO", filterDTO);
            mv.addObject("approverList", filterDataList);
           
            mv.addObject("employeeList", this.getEmployeeList(filterDTO));
            mv.addObject("fromDate", filterDTO.getFromDate());
            mv.addObject("toDate", filterDTO.getToDate());
            mv.addObject("selectedList", filterDTO.getApproveType());
           
            if (filterDTO.getSuccessMessage() != null) {
                mv.addObject("success_msg", filterDTO.getSuccessMessage());
            }
           //  System.out.println("Approver List lengrh===" + filterDataList.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }
    
    public synchronized ModelAndView filterDataExport(HttpServletRequest request, HttpServletResponse response, ApprovalDTO filterDTO) throws Exception {
        mv = new ModelAndView("/approval");
            HttpSession session = request.getSession(true);
            List<ApprovalDTO> projectList = null;
            List<ApprovalDTO> workTypeList = null;

            List<ApprovalDTO> filterDataList = null;
          //String employeeId = (String) session.getAttribute("accessId");
            
             String employeeId=null;
            System.out.println("session getatt*** "+session.getAttribute("EMP_ID"));
               System.out.println("$$$$ "+request.getParameter("empid"));
            if(session.getAttribute("EMP_ID")==null){
            employeeId = request.getParameter("empid");
                System.out.println("$$$$ "+request.getParameter("empid"));
            session.setAttribute("EMP_ID", employeeId);
            }else{
            employeeId = (String) session.getAttribute("EMP_ID");
            }

            String shiftType = CommonFunctions.shiftType;
            String pricingType = CommonFunctions.pricingType;
            String locationType = CommonFunctions.locationType;
            filterDTO.setShiftKey(shiftType);
            filterDTO.setLocationKey(locationType);
            filterDTO.setPricingKey(pricingType);
            filterDTO.setEmployeeId(employeeId);
            filterDTO.setProjectId(filterDTO.getProjectName());
            filterDTO.setEmpId(filterDTO.getEmpName());
            filterDTO.setApproveType(request.getParameter("approveType"));
            filterDTO.setWorkTypeKey(filterDTO.getRegularName());
            
            
            String selectedValue = " ";
            String selectedAppHrsValue=" ";
            if (filterDTO.getProjectName() != "Non_Project_Activity" && !"Non_Project_Activity".equals(filterDTO.getProjectName())) {
                
                if (filterDTO.getEmpName() == null || "".equals(filterDTO.getEmpName())) {
                    System.out.println("inside iif "+filterDTO.getEmpName());
                    selectedValue = selectedValue+"" + "AND ((tep.project_id='" + filterDTO.getProjectId() +"' AND ProjectRole.status = 'a' )"+" )";
                }else{
                    System.out.println("inside else "+filterDTO.getEmpName());
                    selectedValue = selectedValue+"" + "AND (( ProjectRole.status = 'a' )"+" OR tep.project_id='Non_Project_Activity')";
                }
                
            }
            if (filterDTO.getProjectName() == "Non_Project_Activity" || "Non_Project_Activity".equals(filterDTO.getProjectName())) {
                if (filterDTO.getEmpName() == null || "".equals(filterDTO.getEmpName())) {
                     selectedValue = selectedValue + "AND tep.project_id='" + filterDTO.getProjectId() + "' ";

                }else{
                     selectedValue = selectedValue ;
                }

            }
             
              
            if (filterDTO.getEmpName() != null && !"".equals(filterDTO.getEmpName())) {
                
                selectedValue = selectedValue + "AND te.employee_id=" + filterDTO.getEmpName() + " ";
            }
            if (filterDTO.getFromDate() != null && !"".equals(filterDTO.getFromDate())) {
                selectedValue = selectedValue + "AND te.timesheet_date >=STR_TO_DATE(" + "'" + filterDTO.getFromDate() + "','%d-%m-%Y')" + " ";
            }
            if (filterDTO.getToDate() != null && !"".equals(filterDTO.getToDate())) {
                selectedValue = selectedValue + "AND te.timesheet_date <=STR_TO_DATE(" + "'" + filterDTO.getToDate() + "','%d-%m-%Y')" + " ";
            }
            
            if (filterDTO.getEmpName() != null && !"".equals(filterDTO.getEmpName())) {
                
                selectedAppHrsValue = selectedAppHrsValue + "AND te.employee_id=" + filterDTO.getEmpName() + " ";
            }
            if (filterDTO.getFromDate() != null && !"".equals(filterDTO.getFromDate())) {
                selectedAppHrsValue = selectedAppHrsValue + "AND te.timesheet_date >=STR_TO_DATE(" + "'" + filterDTO.getFromDate() + "','%d-%m-%Y')" + " ";
            }
            if (filterDTO.getToDate() != null && !"".equals(filterDTO.getToDate())) {
                selectedAppHrsValue = selectedAppHrsValue + "AND te.timesheet_date <=STR_TO_DATE(" + "'" + filterDTO.getToDate() + "','%d-%m-%Y')" + " ";
            }
            filterDTO.setValue(selectedValue);
          //  System.out.println("filterdto"+filterDTO.getValue());
            ApprovalServiceImpl serviceImpl = (ApprovalServiceImpl) getApplicationContext().getBean("ApprovalService");
            projectList = serviceImpl.getProjectList(filterDTO);
            workTypeList=serviceImpl.getWorkTypeList(filterDTO);
           int page = Integer.parseInt(request.getParameter("page"));
            int rows = 60;
            int start;
            start = ((page - 1) * rows);
            filterDTO.setStart_page(start);
            filterDTO.setEnd_page(rows);
            filterDataList = serviceImpl.filterDataListExport(filterDTO);
        
          ArrayList entireList = new ArrayList();
         
        for (int i = 0; i < filterDataList.size(); i++) {
            ArrayList rowDataList = new ArrayList();
            rowDataList.add(new String("" + filterDataList.get(i).getProjectName()));
            rowDataList.add(new String("" + filterDataList.get(i).getProjectCode()));
            rowDataList.add(new String("" + filterDataList.get(i).getPricingType()));
            rowDataList.add(new String("" + filterDataList.get(i).getEmployeeNumber()+" "+filterDataList.get(i).getEmployeeName()));
             System.out.println("timesheet date "+filterDataList.get(i).getTimesheetDate());
         //   Date strDate=filterDataList.get(i).getTimesheetDate();
           
           
            rowDataList.add(new String("" + filterDataList.get(i).getTimesheetDate()));
            rowDataList.add(new String("" + filterDataList.get(i).getRoleName()));
            rowDataList.add(new String("" + filterDataList.get(i).getRegReason()));
            rowDataList.add(new String("" + filterDataList.get(i).getPhaseName()));
            rowDataList.add(new String("" + filterDataList.get(i).getTaskNamee()));
            rowDataList.add(new String("" + filterDataList.get(i).getAvailableHours()));
            rowDataList.add(new String("" + filterDataList.get(i).getAttendance_hours()));
            rowDataList.add(new String("" + filterDataList.get(i).getWorkedHours()));
             rowDataList.add(new String("" + filterDataList.get(i).getStatuss()));
            rowDataList.add(new String("" + filterDataList.get(i).getApprovedHrs()));
           rowDataList.add(new String("" + filterDataList.get(i).getTaskRemarks()));
            System.out.println("remanrs --"+filterDataList.get(i).getTaskRemarks()); 
            entireList.add(rowDataList);
        }
        System.out.println("size   "+entireList.size());
      // CommonFunctions.exportToExcelApproval(response, entireList, "timesheet_report.xls", "Time Sheet Report",null);
       CommonMethods.exportToExcel(response, entireList, "timesheet_report.xls","Time Sheet Report",null);
      // CommonMethods.exportToExcel(response, entireList, "Project_Release_Report.xls", "allocation", null);
        return null;
           //  System.out.println("Approver List lengrh===" + filterDataList.size());
       
    }
    public synchronized List<ApprovalDTO> getEmployeeList(ApprovalDTO appDTO) {
        List<ApprovalDTO> employeeList = ((ApprovalServiceImpl) getWebApplicationContext().getBean("ApprovalService")).getEmployeeList(appDTO);
        return employeeList;
    }
public  int[] paging(int rows, String recordCount, int current_page) {
        int start = 1;
        int end = 1;
        int next = current_page + 1;
        int prev = current_page - 1;
        int[] pageArr = new int[10];
        // System.out.println("RECORD cOUNT******" + recordCount);
        int totalPage = Integer.parseInt(recordCount) / rows;
        if (Integer.parseInt(recordCount) % rows != 0) {
            totalPage = totalPage + 1;
        }
      //   System.out.println("totalPage" + totalPage);
        if (totalPage > 9) {
            int minus = current_page - 4;
            int plus = current_page + 4;
            if (minus > 0) {
                start = minus;
            } else {
                start = 1;
            }
            if ((plus - start) < 8) {
                plus = start + 8;
            }
            if (plus > totalPage) {
                end = totalPage;
            } else {
                end = plus;
            }
        } else {
            start = 1;
            end = totalPage;
        }
        if (prev < 1) {
            prev = 1;
        }
        if (next > totalPage) {
            next = totalPage;
        }
        // System.out.println("recordCount" + recordCount);
        // System.out.println("current_page" + current_page);
        // System.out.println("start" + start);
        // System.out.println("end" + end);
        // System.out.println("Totalpage++++" + totalPage);
        pageArr[0] = totalPage;
        pageArr[1] = current_page;
        pageArr[2] = start;
        pageArr[3] = end;
        pageArr[4] = prev;
        pageArr[5] = next;
        return pageArr;
    }
    public synchronized ModelAndView employeeTimesheetReport(HttpServletRequest request, HttpServletResponse response, ApprovalDTO filterDTO) throws Exception {
        System.out.println("testng her it comes");
        return null;
    }
}
