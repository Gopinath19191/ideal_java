/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.controller;

import com.defiance.ideal.reports.dto.AssociateFilterDTO;
import com.defiance.ideal.reports.dto.LeaveReportDTO;
import com.defiance.ideal.reports.service.AssociateServiceImpl;
import com.defiance.ideal.reports.service.LeaveReportServiceImpl;
import com.defiance.ideal.reports.util.CommonMethods;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;


/**
 *
 * @author 16113
 */
public class LeaveReportController extends MultiActionController {
    public LeaveReportController(){
    }
    public static int pageCount = 100;
        public ModelAndView monthlyLeaveReport(HttpServletRequest request, HttpServletResponse response,LeaveReportDTO filterData) throws Exception {
            ModelAndView mv = null;
            HttpSession session = request.getSession();
            String employeeId = (String) session.getAttribute("EMP_ID");
            filterData.setLogin_emp_id(employeeId);
            System.out.println("from controller  "+employeeId);
            List<LeaveReportDTO> subSbu = null;
             List<LeaveReportDTO> filterdata = null;
            int recordCount = 0;
            int month=0;
            int year=0;
            int flag = 0;
            try{
                Map<String,String> monthList = CommonMethods.getNewMonthsList();
                Map<Integer,Integer> yearList = CommonMethods.getYearsList(0);
                final WebApplicationContext ctx = getWebApplicationContext();
                LeaveReportServiceImpl service=(LeaveReportServiceImpl) ctx.getBean("leaveReportService");
                 if(filterData.getSbu() != null && !"".equals(filterData.getSbu())){
                     subSbu = service.getSubSbu(filterData.getSbu());
                 }
                 if(request.getParameter("selectMonth") != null && request.getParameter("selectYear") != null){
                    year = Integer.parseInt(request.getParameter("selectYear"));
                    month = Integer.parseInt(request.getParameter("selectMonth"));
                    filterData.setYear(String.valueOf(year));
                    filterData.setMonth(String.valueOf(month));
                }else{
                    Calendar c = Calendar.getInstance();
                    year = c.get(Calendar.YEAR);
                    month = c.get(Calendar.MONTH);
                    filterData.setYear(String.valueOf(year));
                    filterData.setMonth(String.valueOf(month+1));
                 }
                  recordCount =service.getReportCount(filterData);
                 //List<LeaveReportDTO> list = service.getReportCount(filterData);
                 List<LeaveReportDTO> Sbu = service.getSbu();
                 String parentId =  filterData.getSbu();
//                 if(parentId != null && !parentId.equals("")){
//                        subSbu = service.getSubSbu(parentId);
//                }
		//List<AssociateFilterDTO> subSbu = bo.getSubSbu(parentId);
                
                int page=0;
                if ("Go".equals(request.getParameter("go"))) {
			page = 1;
                        flag = 1;
		} else if (request.getParameter("page") != null ) {
			System.out.println("page count"+filterData.getPage());
			page = filterData.getPage();
                        flag = 1;			
		}else{
                     page = 1;
                     flag = 0;
                }

            int rows = pageCount;
            int start;
            start = ((page - 1) * rows);
            filterData.setStart_page(start);
            filterData.setEnd_page(rows);
            filterdata = service.monthlyLeaveDetails(filterData);                         
                int[] paging = CommonMethods.paging(recordCount, page, rows);
                mv = new ModelAndView("monthly_leave");
                mv.addObject("filterdata",filterdata);
                mv.addObject("sbuList",Sbu);
                mv.addObject("ssubList",subSbu);
                mv.addObject("paging", paging);
                mv.addObject("monthList", monthList);
                mv.addObject("yearList", yearList);
                mv.addObject("selectedSbu",filterData.getSbu());
                mv.addObject("selectedSubSbu",filterData.getSubSbu());
                mv.addObject("selectedYear",filterData.getYear());
                mv.addObject("selectedMonth",filterData.getMonth());
            }catch(Exception e){
                e.printStackTrace();
            }
            return mv;
        }
        
        public ModelAndView getSubPractice(HttpServletRequest request, HttpServletResponse response) throws IOException {
		final WebApplicationContext ctx = getWebApplicationContext();
                LeaveReportServiceImpl service=(LeaveReportServiceImpl) ctx.getBean("leaveReportService");
		
		response.getWriter().println("<option value='All'>All</option>");
		for (LeaveReportDTO dTO : (service.getSubSbu((request.getParameter("id"))))) {			
                    response.getWriter().println("<option value='" + dTO.getId() + "'>" + dTO.getName() + "</option>");
		}
		return null;
	}
        public ModelAndView exportLeaveReport(HttpServletRequest request, HttpServletResponse response, LeaveReportDTO filterData) throws Exception {
        try {
            final WebApplicationContext ctx = getWebApplicationContext();
            LeaveReportServiceImpl service = (LeaveReportServiceImpl) ctx.getBean("leaveReportService");
            List<LeaveReportDTO> filterdata = null;
            int month = 0;
            int year = 0;
            if (request.getParameter("selectMonth") != null && request.getParameter("selectYear") != null) {
                year = Integer.parseInt(request.getParameter("selectYear"));
                month = Integer.parseInt(request.getParameter("selectMonth"));
                filterData.setYear(String.valueOf(year));
                filterData.setMonth(String.valueOf(month));
            } else {
                Calendar c = Calendar.getInstance();
                year = c.get(Calendar.YEAR);
                month = c.get(Calendar.MONTH);
                filterData.setYear(String.valueOf(year));
                filterData.setMonth(String.valueOf(month+1));
            }
            filterdata = service.monthlyLeaveDetails(filterData);
              ArrayList entireList = new ArrayList();
              for(int i=0;i<filterdata.size();i++){
                  ArrayList rowData = new ArrayList();
                  rowData.add(new String(""+filterdata.get(i).getEmployee_name()));
                  rowData.add(new String(""+filterdata.get(i).getOpening_balence()));
                  rowData.add(new String(""+filterdata.get(i).getLop_days()));
                  rowData.add(new String(""+filterdata.get(i).getLeave_accrued()));
                  rowData.add(new String(""+filterdata.get(i).getLeave_taken()));
                  rowData.add(new String(""+filterdata.get(i).getLeave_cancelled()));
                  rowData.add(new String(""+filterdata.get(i).getLeave_arrear()));
                  rowData.add(new String(""+filterdata.get(i).getLeave_encashed()));
                  rowData.add(new String(""+filterdata.get(i).getClosing_balence()));
                  entireList.add(rowData);
              }
            CommonMethods.exportToExcel(response, entireList, "leave_report", "leave_report", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
       
}
