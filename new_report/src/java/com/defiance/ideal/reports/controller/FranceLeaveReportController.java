/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.controller;

import com.defiance.ideal.reports.dto.FranceLeaveReportDTO;
import com.defiance.ideal.reports.service.FranceLeaveReportService;
import com.defiance.ideal.reports.service.FranceLeaveReportServiceImpl;
import com.defiance.ideal.reports.util.CommonMethods;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 *
 * @author 16656
 */
public class FranceLeaveReportController extends MultiActionController {

    public ModelAndView franceLeaveReport(HttpServletRequest request, HttpServletResponse response, FranceLeaveReportDTO dto) {
        int[] paging = null;
        ModelAndView mvc = new ModelAndView("franceLeaveReport");
        System.out.println("Hello france.....");
        final WebApplicationContext ctx = getWebApplicationContext();
        FranceLeaveReportService service = (FranceLeaveReportServiceImpl) ctx.getBean("FranceLeaveReportService");
        int page = 0;
//        int page = Integer.parseInt(request.getParameter("page"));
        String pg = request.getParameter("page");
        if (pg == null || pg.equals("")) {
            page = 1;
        } else {
            page = Integer.parseInt(pg);
        }
        int rows = 15;
        int start;
        start = ((page - 1) * rows);
        dto.setStart_page(Integer.toString(start));
        dto.setEnd_page(Integer.toString(rows));
        String start_date="";
        String end_date="";
        start_date = request.getParameter("atartDate");
        end_date = request.getParameter("endDate");
        System.out.println("Start date is..."+start_date);
        if (start_date!=null && !start_date.isEmpty()){
//            Calendar cal = Calendar.getInstance();
//            cal.add(Calendar.MONTH, -5);
//            int day=cal.get(Calendar.DATE);
//            int month=cal.get(Calendar.MONTH);
//            int year=cal.get(Calendar.YEAR);
//            dto.setAtartDate(year+"-"+month+"-"+day);
//            dto.setDisplayFromDate(day+"-"+month+"-"+year);
//        }else{
            dto.setAtartDate(start_date.split("-")[2]+"-"+start_date.split("-")[1]+"-"+start_date.split("-")[0]);
            dto.setDisplayFromDate(start_date);
        }
        if (end_date!=null && !end_date.isEmpty()) {
//            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//            DateFormat dateFormatJsp = new SimpleDateFormat("d-M-yyyy");
//            Date date = new Date();
//            System.out.println(dateFormat.format(date));
//            dto.setEndDate(dateFormat.format(date));
//            dto.setDisplayToDate(dateFormatJsp.format(date));
//        }else{
            dto.setEndDate(end_date.split("-")[2]+"-"+end_date.split("-")[1]+"-"+end_date.split("-")[0]);
            dto.setDisplayToDate(end_date);
        }
        int recordCount = service.getLeaveCount(dto);
        System.out.println("Total records are....." + recordCount);
        int[] pageNo = paging(rows, Integer.toString(recordCount), page);
        List<FranceLeaveReportDTO> franceLeavelist = new ArrayList();
        if ((start_date!=null && end_date!=null) || dto.getEmployee_no()!=null ){
            franceLeavelist = service.getFranceLeaveDetails(dto);
        }
        mvc.addObject("franceLeave", franceLeavelist);
        mvc.addObject("filterData", dto);
        mvc.addObject("paging", pageNo);
        return mvc;
    }

    public ModelAndView ajaxsearchFranceEmp(HttpServletRequest request, HttpServletResponse response, FranceLeaveReportDTO filterData) {
        ModelAndView mvc = null;
        mvc = new ModelAndView("/ajaxsearch_1");
        try {
            final WebApplicationContext ctx = getWebApplicationContext();
            FranceLeaveReportService service = (FranceLeaveReportServiceImpl) ctx.getBean("FranceLeaveReportService");
            String empVal = request.getParameter("q");
            System.out.println("ajax search----" + empVal);
            List<FranceLeaveReportDTO> empRes = service.getSearchList(empVal);
            mvc.addObject("empRes", empRes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mvc.addObject("filterData", filterData);
        return mvc;
    }

    public ModelAndView getfranceLeaveReportXL(HttpServletRequest request, HttpServletResponse response, FranceLeaveReportDTO dto) throws Exception {

        final WebApplicationContext ctx = getWebApplicationContext();
        FranceLeaveReportService service = (FranceLeaveReportServiceImpl) ctx.getBean("FranceLeaveReportService");
        
        String start_date="";
        String end_date="";
        start_date = request.getParameter("atartDate");
        end_date = request.getParameter("endDate");
        if (start_date!=null){
            dto.setAtartDate(start_date.split("-")[2]+"-"+start_date.split("-")[1]+"-"+start_date.split("-")[0]);
            dto.setDisplayFromDate(start_date);
        }
        if (end_date!=null) {
            dto.setEndDate(end_date.split("-")[2]+"-"+end_date.split("-")[1]+"-"+end_date.split("-")[0]);
            dto.setDisplayToDate(end_date);
        }
        List<FranceLeaveReportDTO> franceLeavelist = new ArrayList();
        if (start_date!=null && end_date!=null){
            franceLeavelist = service.getFranceLeaveDetails(dto);
        }
//        List<FranceLeaveReportDTO> franceLeavelist = service.getFranceLeaveDetails(dto);

        ArrayList entireList = new ArrayList();
        for (int i = 0; i < franceLeavelist.size(); i++) {
            ArrayList rowDataList = new ArrayList();
//            rowDataList.add(new String("" + franceLeavelist.get(i).getEmpNumber()));
            rowDataList.add(new String("" + franceLeavelist.get(i).getEmpName()));
            rowDataList.add(new String("" + franceLeavelist.get(i).getLeave_type()));
            rowDataList.add(new String("" + franceLeavelist.get(i).getLeave_reason()));
            rowDataList.add(new String("" + franceLeavelist.get(i).getAppliedDate()));
            rowDataList.add(new String("" + franceLeavelist.get(i).getDate_approved()));
            rowDataList.add(new String("" + franceLeavelist.get(i).getAtartDate()));
            rowDataList.add(new String("" + franceLeavelist.get(i).getEndDate()));
            rowDataList.add(new String("" + franceLeavelist.get(i).getHalf_day()));
            rowDataList.add(new String("" + franceLeavelist.get(i).getDuration()));
            rowDataList.add(new String("" + franceLeavelist.get(i).getLeave_status()));
            rowDataList.add(new String("" + franceLeavelist.get(i).getApprovedBy()));
            rowDataList.add(new String("" + franceLeavelist.get(i).getReason_for_rejection()));

            entireList.add(rowDataList);
        }
//        Calendar cal=Calendar.getInstance();
//        String[] months = {"January", "February","March", "April", "May", "June", "July","August", "September", "October", "November","December"};
//        String month = null;
//        if(cal.get(Calendar.MONTH) == 0) {
//           month = months[(cal.get(Calendar.MONTH))];
//        } else {
//            month = months[(cal.get(Calendar.MONTH))-1];
//        }
//        String year=Integer.toString(cal.get(Calendar.YEAR));
        CommonMethods.exportToExcel(response, entireList, "France_Leave_Report.xls", "France_Leave_Report", null);
        return null;
    }

    public int[] paging(int rows, String recordCount, int current_page) {
        int start = 1;
        int end = 1;
        int next = current_page + 1;
        int prev = current_page - 1;
        int[] pageArr = new int[10];
        int totalPage = Integer.parseInt(recordCount) / rows;
        if (Integer.parseInt(recordCount) % rows != 0) {
            totalPage = totalPage + 1;
        }
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
        pageArr[0] = totalPage;
        pageArr[1] = current_page;
        pageArr[2] = start;
        pageArr[3] = end;
        pageArr[4] = prev;
        pageArr[5] = next;
        return pageArr;
    }
}
