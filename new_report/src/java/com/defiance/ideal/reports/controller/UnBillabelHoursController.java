/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.controller;

import com.defiance.ideal.reports.dto.UnBillabelHoursDTO;
import com.defiance.ideal.reports.service.UnBillabelHoursServiceImpl;
import com.defiance.ideal.reports.util.CommonMethods;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
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
public class UnBillabelHoursController extends MultiActionController {
    public UnBillabelHoursController(){
        
    }
        public ModelAndView projectsUnbilledReport(HttpServletRequest request, HttpServletResponse response,UnBillabelHoursDTO filterData) throws Exception {
            ModelAndView mv = null;
            HttpSession session = request.getSession();
            String employeeId = (String) session.getAttribute("EMP_ID");
            filterData.setManager_id(employeeId);
//            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//            Calendar cal = Calendar.getInstance();
//            cal.add(Calendar.MONTH, -1);
//            cal.set(Calendar.DATE, 1);
//            Date firstDateOfPreviousMonth = cal.getTime();
//            System.out.println(dateFormat.format(firstDateOfPreviousMonth));
//            cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
//            Date lastDateOfPreviousMonth = cal.getTime();
//            System.out.println(dateFormat.format(lastDateOfPreviousMonth));
//            filterData.setFrom_date(dateFormat.format(firstDateOfPreviousMonth));
//            filterData.setTo_date(dateFormat.format(lastDateOfPreviousMonth));
            try{
                final WebApplicationContext ctx = getWebApplicationContext();
                UnBillabelHoursServiceImpl service=(UnBillabelHoursServiceImpl) ctx.getBean("unBillableHoursService");
                List<UnBillabelHoursDTO> filterdata = service.projectsUnbilledReport(filterData);
                mv = new ModelAndView("unBillableHoursReports");
                mv.addObject("filterdata",filterdata);
            }catch(Exception e){
                e.printStackTrace();
            }
            return mv;
        }
        public ModelAndView monthlyBillReport(HttpServletRequest request, HttpServletResponse response,UnBillabelHoursDTO filterData) throws Exception {
            ModelAndView mv = null;
            String project_id=request.getParameter("p_id");
            HttpSession session = request.getSession();
            String employeeId = (String) session.getAttribute("EMP_ID");
            filterData.setManager_id(employeeId);
            try{
                mv  = new ModelAndView("monthly_unbilled_report");
                 final WebApplicationContext ctx = getWebApplicationContext();
                UnBillabelHoursServiceImpl service=(UnBillabelHoursServiceImpl) ctx.getBean("unBillableHoursService");
                UnBillabelHoursDTO datesData = service.getProjectBillDates(project_id);
                String minDate  = datesData.getMinDate();
                String maxDate = datesData.getMaxDate();
                String splt1[] = minDate.split("-");
                String splt2[] = maxDate.split("-");
                int splitYear1 = Integer.parseInt(splt1[0]);
                int splitYear2 = Integer.parseInt(splt2[0]);
                int splitMonth1 = Integer.parseInt(splt1[1]);
                int splitMonth2 = Integer.parseInt(splt2[1]);
                
                System.out.println("dates  "+minDate+"  "+maxDate);
                String mnDate = null;
                String mxDate = null;
                List<UnBillabelHoursDTO> datelist = new ArrayList<UnBillabelHoursDTO>();
                List<UnBillabelHoursDTO> contentList = new ArrayList<UnBillabelHoursDTO>();
                UnBillabelHoursDTO dto = null;               
                if(splitYear1 == splitYear2 ){
                    if(splitMonth1 == splitMonth2){
                        dto = new UnBillabelHoursDTO();
                        mnDate =  splitYear1+"-"+splitMonth1+"-01";
                        mxDate = getMaxDate(mnDate);
                        dto.setMinDate(mnDate);
                        dto.setMaxDate(mxDate);
                        datelist.add(dto);
                    }else if(splitMonth1 <= splitMonth2){
                       for(int i = splitMonth1; i< splitMonth2; i++ ){
                            dto = new UnBillabelHoursDTO();
                            mnDate = splitYear1+"-"+i+"-01";
                            mxDate = getMaxDate(mnDate);
                            dto.setMinDate(mnDate);
                            dto.setMaxDate(mxDate);
                            datelist.add(dto);
                       }
                    }
                }else if(splitYear1 < splitYear2 ){
                    for(int i = splitYear1; i<= splitYear2; i++ ){
                        if(i == splitYear2){
                            for(int j = 1; j<= splitMonth2; j++ ){
                                dto = new UnBillabelHoursDTO();
                                mnDate = i+"-"+j+"-01";
                                mxDate = getMaxDate(mnDate);
                                dto.setMinDate(mnDate);
                                dto.setMaxDate(mxDate);
                                datelist.add(dto);
                            }
                        }else{
                            for(int j = splitMonth1; j<= 12; j++ ){
                                dto = new UnBillabelHoursDTO();
                                mnDate = i+"-"+j+"-01";
                                mxDate = getMaxDate(mnDate);
                                dto.setMinDate(mnDate);
                                dto.setMaxDate(mxDate);
                                datelist.add(dto);
                            }
                        }
                    }
            }               
                if(datelist != null){
                    for (UnBillabelHoursDTO unBillabelHoursDTO : datelist) {
                        unBillabelHoursDTO.setPrjt_id(project_id);
                        unBillabelHoursDTO.setManager_id(employeeId);
                        unBillabelHoursDTO.setReport_type("1");                        
                        String date = unBillabelHoursDTO.getMinDate();
                        int year = Integer.parseInt(date.split("-")[0]);
                        if( year > 2016){ 
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        Date convertedDate = dateFormat.parse(date);
                        dateFormat = new SimpleDateFormat("MMMM"); // full month name
                        String month  = dateFormat.format(convertedDate);
                        System.out.println(month);
                        unBillabelHoursDTO.setEmpl_id(null);
                       UnBillabelHoursDTO data = service.monthlyReport(unBillabelHoursDTO);
                       if(data != null ){
                           data.setMonth(month);
                           data.setMinDate(date);
                           data.setYear(date.split("-")[0]);
                           contentList.add(data);
                       }
                       }
                    }
                }
                mv.addObject("datalist",contentList);
                
            }catch(Exception e){
                e.printStackTrace();
            }
            return mv;
        }
        private String getMaxDate(String mndate) throws Exception{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date convertedDate = dateFormat.parse(mndate);
            Calendar c = Calendar.getInstance();
            c.setTime(convertedDate);
            c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
            Date mxDate = c.getTime();
            String md = dateFormat.format(mxDate);
            System.out.println(dateFormat.format(mxDate));
           return md;
        }
       public ModelAndView singleMonthReport(HttpServletRequest request, HttpServletResponse response,UnBillabelHoursDTO filterData) throws Exception {
         ModelAndView mv = null;
            HttpSession session = request.getSession();
            String employeeId = (String) session.getAttribute("EMP_ID");
            String mndate = request.getParameter("minDate");
            System.out.println("minimum date in single month");
            String prjId = request.getParameter("p_id");
            filterData.setPrjt_id(prjId);
            filterData.setMinDate(mndate);
            String mxDate = getMaxDate(mndate);
            filterData.setMaxDate(mxDate);
            filterData.setManager_id(employeeId);
            filterData.setReport_type("2");
            filterData.setEmpl_id(null);
            try{
                mv  = new ModelAndView("month_report");
                 final WebApplicationContext ctx = getWebApplicationContext();
                UnBillabelHoursServiceImpl service=(UnBillabelHoursServiceImpl) ctx.getBean("unBillableHoursService");
                List<UnBillabelHoursDTO> details = service.singleMonthReport(filterData);
                mv.addObject("details",details);
                mv.addObject("prjId",prjId);
                mv.addObject("minDate",mndate);
            }catch(Exception e){
                e.printStackTrace();
            }
           return mv;
       }
        public ModelAndView employeeReport(HttpServletRequest request, HttpServletResponse response,UnBillabelHoursDTO filterData) throws Exception {
         ModelAndView mv = null;
         HttpSession session = request.getSession();
         String employeeId = (String) session.getAttribute("EMP_ID");
         String mndate = request.getParameter("minDate");
         String prjId = request.getParameter("projectId");
         String empl_id = request.getParameter("empl_id");
         String mxDate = getMaxDate(mndate);
         filterData.setEmpl_id(empl_id);
         filterData.setMaxDate(mxDate);
         filterData.setPrjt_id(prjId);
         filterData.setMinDate(mndate);
         filterData.setManager_id(employeeId);
         filterData.setReport_type("1");
          try{
               mv  = new ModelAndView("empployee_month_report");
               final WebApplicationContext ctx = getWebApplicationContext();
               UnBillabelHoursServiceImpl service=(UnBillabelHoursServiceImpl) ctx.getBean("unBillableHoursService");
               List<UnBillabelHoursDTO> empReport = service.employeeMonthReport(filterData);
               mv.addObject("empReport",empReport);
               mv.addObject("prjId",prjId);
               mv.addObject("minDate",mndate);
               mv.addObject("empl_id",empl_id);
               
            }catch(Exception e){
            e.printStackTrace();
        }
         return mv;
        }
         public ModelAndView exportdataunBilledReport(HttpServletRequest request, HttpServletResponse response,UnBillabelHoursDTO filterData) throws Exception {
               HttpSession session = request.getSession();
               String employeeId = (String) session.getAttribute("EMP_ID");
               filterData.setManager_id(employeeId);
                try{
                final WebApplicationContext ctx = getWebApplicationContext();
                UnBillabelHoursServiceImpl service=(UnBillabelHoursServiceImpl) ctx.getBean("unBillableHoursService");
                List<UnBillabelHoursDTO> filterdata = service.projectsUnbilledReport(filterData);
                ArrayList entireList = new ArrayList();
                ArrayList headerlist = new ArrayList();
                headerlist.add("Project Code");
                headerlist.add("Project Name");
                headerlist.add("Currency");
                headerlist.add("Accrued Amount");
                headerlist.add("Invoiced Amount");
                headerlist.add("To Be Invoiced Amount");
                entireList.add(headerlist);
                for (int i = 0; i < filterdata.size(); i++) {
                    ArrayList datalist = new ArrayList();
                    datalist.add(""+ filterdata.get(i).getProject_code());
                    datalist.add(""+ filterdata.get(i).getProject_name());
                    datalist.add(""+ filterdata.get(i).getCurrency_code());
                    datalist.add(""+ filterdata.get(i).getAccrued_amount());
                    datalist.add(""+ filterdata.get(i).getInvoiced_amount());
                    datalist.add(""+ filterdata.get(i).getTo_be_invoiced());
                    entireList.add(datalist);
                }
                CommonMethods.exportToExcel(response, entireList,"un_billed_report","unBilled","");
                }catch(Exception e){
                    e.printStackTrace();
                }
                 return null;
             }
         public ModelAndView exportMonthReport(HttpServletRequest request, HttpServletResponse response,UnBillabelHoursDTO filterData) throws Exception {
               HttpSession session = request.getSession();
               String employeeId = (String) session.getAttribute("EMP_ID");
               filterData.setManager_id(employeeId);
               String prjId = filterData.getProjectId();
               String mndate = filterData.getMinDate();                
               System.out.println("prj id "+prjId+"  date "+mndate);            
            filterData.setPrjt_id(prjId);
            filterData.setMinDate(mndate);
            String mxDate = getMaxDate(mndate);
            filterData.setMaxDate(mxDate);
            filterData.setReport_type("2");            
                try{
                final WebApplicationContext ctx = getWebApplicationContext();
                UnBillabelHoursServiceImpl service=(UnBillabelHoursServiceImpl) ctx.getBean("unBillableHoursService");
                List<UnBillabelHoursDTO> filterdata = service.singleMonthReport(filterData);
                ArrayList entireList = new ArrayList();
                ArrayList headerlist = new ArrayList();
                headerlist.add("Employee Name");
                headerlist.add("Role");
                headerlist.add("Available Hours");
                headerlist.add("Approved Hours");
                headerlist.add("Accrued Hours");
                headerlist.add("Currency");
                headerlist.add("UOM");
                headerlist.add("Billing Rate");
                headerlist.add("To Invoiced Amount");
                entireList.add(headerlist);
                for (int i = 0; i < filterdata.size(); i++) {
                    ArrayList datalist = new ArrayList();
                    datalist.add(""+ filterdata.get(i).getEmployeeName());
                    datalist.add(""+ filterdata.get(i).getRole());
                    datalist.add(""+ filterdata.get(i).getAvailableHours());
                    datalist.add(""+ filterdata.get(i).getApprovedHours());
                    datalist.add(""+ filterdata.get(i).getAccruedHours());
                    datalist.add(""+ filterdata.get(i).getCurrency());
                    datalist.add(""+ filterdata.get(i).getUom());
                    datalist.add(""+ filterdata.get(i).getBillingRate());
                    datalist.add(""+ filterdata.get(i).getTo_invoiced_amount());
                    entireList.add(datalist);
                }
                CommonMethods.exportToExcel(response, entireList,"un_billed_report","unBilled","");
                }catch(Exception e){
                    e.printStackTrace();
                }
               return null;
         }
          public ModelAndView exportEmployeeReport(HttpServletRequest request, HttpServletResponse response,UnBillabelHoursDTO filterData) throws Exception {
                HttpSession session = request.getSession();
                String employeeId = (String) session.getAttribute("EMP_ID");
                filterData.setManager_id(employeeId);
                String mndate = request.getParameter("minDate");
                String prjId = request.getParameter("projectId");
                String empl_id = request.getParameter("empl_id");
                String mxDate = getMaxDate(mndate);
                filterData.setEmpl_id(empl_id);
                filterData.setMaxDate(mxDate);
                filterData.setPrjt_id(prjId);
                filterData.setMinDate(mndate);                
                filterData.setReport_type("1");
                try{
                final WebApplicationContext ctx = getWebApplicationContext();
                UnBillabelHoursServiceImpl service=(UnBillabelHoursServiceImpl) ctx.getBean("unBillableHoursService");
                List<UnBillabelHoursDTO> filterdata = service.employeeMonthReport(filterData);
                ArrayList titleList = new ArrayList();
                ArrayList entireList = new ArrayList();
                ArrayList headerlist = new ArrayList();
                if(filterdata != null && filterdata.size() > 0){
                    titleList.add("project Name");
                    titleList.add(""+ filterdata.get(0).getProjectName());
                    titleList.add("Customer Name");
                    titleList.add(""+ filterdata.get(0).getCustomerName());
                    entireList.add(titleList);
                }
                headerlist.add("Timesheet Date");
                headerlist.add("Role");
                headerlist.add("Availble Hours");
                headerlist.add("Approved Hours");
                headerlist.add("Accrued Hours");
                headerlist.add("Currency");
                headerlist.add("UOM");
                headerlist.add("Billing Rate");
                headerlist.add("To Invoiced Amount");
                entireList.add(headerlist);
                 for (int i = 0; i < filterdata.size(); i++) {
                    ArrayList datalist = new ArrayList();
                    datalist.add(""+ filterdata.get(i).getTimesheetDate());
                    datalist.add(""+ filterdata.get(i).getRole());
                    datalist.add(""+ filterdata.get(i).getAvailableHours());
                    datalist.add(""+ filterdata.get(i).getApprovedHours());
                    datalist.add(""+ filterdata.get(i).getAccruedHours());
                    datalist.add(""+ filterdata.get(i).getCurrency());
                    datalist.add(""+ filterdata.get(i).getUom());
                    datalist.add(""+ filterdata.get(i).getBillingRate());
                    datalist.add(""+ filterdata.get(i).getTo_invoiced_amount());
                    entireList.add(datalist);
                }
                CommonMethods.exportToExcel(response, entireList,"un_billed_report","unBilled","");
                 }catch(Exception e){
                    e.printStackTrace();
                }
               return null;
         }
}
