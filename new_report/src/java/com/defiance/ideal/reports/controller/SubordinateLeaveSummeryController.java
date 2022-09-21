/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.controller;

import com.defiance.ideal.reports.dto.SubordinateLeaveDataDTO;
import com.defiance.ideal.reports.dto.SubordinateLeaveFilterDTO;
import com.defiance.ideal.reports.service.SubordinateLeaveService;
import com.defiance.ideal.reports.service.SubordinateLeaveServiceImpl;
import com.defiance.ideal.reports.util.CommonConfigurations;
import com.defiance.ideal.reports.util.CommonMethods;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author 16047
 */
public class SubordinateLeaveSummeryController extends MultiActionController {
    public SubordinateLeaveSummeryController(){
        
    }
    public ModelAndView subordinateLeaveSummeryReport(HttpServletRequest request, HttpServletResponse response, SubordinateLeaveFilterDTO filterData){
        ModelAndView mvc = new ModelAndView("/subordinateLeaveSummery");
        HttpSession session = request.getSession();
        List<SubordinateLeaveDataDTO> leaveRecord = null;
        String rmId = "";
        try{
            rmId = (String)session.getAttribute("EMP_ID");
            filterData.setRmId(rmId);
            int page;
            int india_count = 0;
            if(request.getParameter("submitbtn") != null && !"1".equals(request.getParameter("flag"))){
                System.out.println("MUNIIIII "+request.getParameter("flag"));
                page = 1;
            }
            else{
                System.out.println("PAGE "+request.getParameter("page"));
                page = Integer.parseInt(request.getParameter("page"));
            }
            int rows = CommonConfigurations.pageCount;
            int start;
            start = ((page - 1) * rows);
            filterData.setStart_page(start);
            filterData.setEnd_page(rows);
            int recordCount = 0;
            final WebApplicationContext ctx = getWebApplicationContext();
            SubordinateLeaveService subordinateLeaveService = (SubordinateLeaveService)ctx.getBean("Subordinate_LeaveService");
            System.out.println("From Date "+filterData.getFromDate());
            System.out.println("To Date "+filterData.getToDate());
            System.out.println("Employee Id "+filterData.getEmployee_id());
            System.out.println("Start Page "+filterData.getStart_page());
            System.out.println("End Page "+filterData.getEnd_page());
            leaveRecord = subordinateLeaveService.getSubordinateLeaveRecord(filterData);
            india_count = subordinateLeaveService.getSubordinateLeaveRecordCount(filterData);
            System.out.println("india_count"+india_count);
            recordCount = india_count;
            System.out.println("Record Count "+recordCount);
            int end = (start+rows)-1;
            int[] paging = CommonMethods.paging(recordCount, page, rows);
            mvc.addObject("paging", paging);
            mvc.addObject("leaveDetails", leaveRecord);
            mvc.addObject("filterData", filterData);
        }catch(Exception e){
            e.printStackTrace();
        }
        return mvc;
    }
    
    public ModelAndView subordinateLeaveSummeryExport(HttpServletRequest request, HttpServletResponse response, SubordinateLeaveFilterDTO filterData){
        System.out.println("in side export controller");
        ModelAndView mvc = null;
        HttpSession session = request.getSession();
        List<SubordinateLeaveDataDTO> leaveRecord = null;
        String rmId = "";
        try{
            rmId = (String)session.getAttribute("EMP_ID");
            filterData.setRmId(rmId);
            final WebApplicationContext ctx = getWebApplicationContext();
            SubordinateLeaveService subordinateLeaveService = (SubordinateLeaveService)ctx.getBean("Subordinate_LeaveService");
            leaveRecord = subordinateLeaveService.getSubordinateLeaveRecord(filterData);
            ArrayList entireList = new ArrayList();
            for (int i = 0; i < leaveRecord.size(); i++) {
                ArrayList rowDataList = new ArrayList();
                rowDataList.add(new String("" + leaveRecord.get(i).getEmployeeNumber()));
                rowDataList.add(new String("" + leaveRecord.get(i).getEmpName()));
                rowDataList.add(new String("" + leaveRecord.get(i).getLeaveCategory()));
                rowDataList.add(new String("" + leaveRecord.get(i).getDateApplied()));
                rowDataList.add(new String("" + leaveRecord.get(i).getFromDate()));
                rowDataList.add(new String("" + leaveRecord.get(i).getToDate()));
                rowDataList.add(new String("" + leaveRecord.get(i).getLengthDays()));
                rowDataList.add(new String("" + leaveRecord.get(i).getLeaveStatus()));
                entireList.add(rowDataList);
            }
            CommonMethods.exportToExcel(response, entireList, "subordinate_leave_summary.xls", "Subordinate Leave Summary", null);
        }catch(Exception e){
            e.printStackTrace();
        }
        return mvc;
    }
    
    public ModelAndView employeesearch(HttpServletRequest request, HttpServletResponse response, SubordinateLeaveFilterDTO filterData) {
        ModelAndView mvc = null;
        mvc = new ModelAndView("/ajaxsearch");
        HttpSession session = request.getSession();
        try {
            final WebApplicationContext ctx = getWebApplicationContext();
            SubordinateLeaveService subordinateLeaveService = (SubordinateLeaveServiceImpl) ctx.getBean("Subordinate_LeaveService");
            String empVal = request.getParameter("q");
            String rmId = (String)session.getAttribute("EMP_ID");
           // System.out.println("fefefe"+empVal);
//            Map<String, String> empRes = earnedService.getSearchList(empVal);
            List<SubordinateLeaveDataDTO> empRes = subordinateLeaveService.getSearchList(empVal,rmId);
           // System.out.println("dgdgdg"+empRes.size());
            mvc.addObject("empRes", empRes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mvc.addObject("filterData", filterData);
        return mvc;
    } 
}
