package com.defiance.ideal.reports.controller;

import com.defiance.ideal.reports.dto.OJFSurveyReportDTO;
import com.defiance.ideal.reports.dto.PrLastBilledReportDTO;
import com.defiance.ideal.reports.dto.PrTimesheetLekageReportDTO;
import com.defiance.ideal.reports.dto.UtilizationReportDTO;
import com.defiance.ideal.reports.service.PrTimesheetLekageReportService;
import com.defiance.ideal.reports.service.PrTimesheetLekageReportServiceImpl;
import com.defiance.ideal.reports.util.CommonMethods;
import com.lowagie.text.DocumentException;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class PrTimesheetLekageReportController extends MultiActionController {

    public ModelAndView getTimesheetlekageCustomerList(HttpServletRequest request, HttpServletResponse response, PrTimesheetLekageReportDTO filterData) throws Exception {
        ModelAndView mvc = null;
        try {
            mvc = new ModelAndView("/timesheet_Lekage_Report");
            final WebApplicationContext ctx = getWebApplicationContext();
            PrTimesheetLekageReportService PrTimesheetLekageReportService = (PrTimesheetLekageReportServiceImpl) ctx.getBean("PrTimesheetLekageReportService");
            List<PrTimesheetLekageReportDTO> customerList = PrTimesheetLekageReportService.getCustomerList();
            mvc.addObject("customerList", customerList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mvc;
    }

    public ModelAndView getTimesheetlekageProjectList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = null;
        PrintWriter out = response.getWriter();
        String customer_id = null;
        try {
            final WebApplicationContext ctx = getWebApplicationContext();
            PrTimesheetLekageReportService PrTimesheetLekageReportService = (PrTimesheetLekageReportServiceImpl) ctx.getBean("PrTimesheetLekageReportService");
            PrTimesheetLekageReportDTO filterData = new PrTimesheetLekageReportDTO();
            customer_id = request.getParameter("customer_id");
            if (customer_id != null) {
                filterData.setCust_name(customer_id);
                List<PrTimesheetLekageReportDTO> projectList = PrTimesheetLekageReportService.getProjectList(filterData);
                Iterator itr = projectList.iterator();
                out.println("<option value='0'>--Select--</option>");
                while (itr.hasNext()) {
                    PrTimesheetLekageReportDTO dto = (PrTimesheetLekageReportDTO) itr.next();
                    out.println("<option value=" + dto.getProject_id() + ">" + dto.getProject_name() + "</option>");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }

    public ModelAndView getTimesheetLekageReportXL(HttpServletRequest request, HttpServletResponse response, PrTimesheetLekageReportDTO filterData) throws Exception {
        ModelAndView mvc = null;
        try {
            mvc = new ModelAndView("/timesheet_Lekage_Report");
            String customer_id = request.getParameter("customer_id");
            String project_id = request.getParameter("project_id");
            String from_date = request.getParameter("from_date");
            String to_date = request.getParameter("to_date");
            if (customer_id.equals("0")) {
                filterData.setCust_id(null);
            } else {
                filterData.setCust_id(customer_id);
            }
            if (project_id.equals("0")) {
                filterData.setProject_id(null);
            } else {
                filterData.setProject_id(project_id);
            }
            filterData.setFrom_date(from_date);
            filterData.setTo_date(to_date);
            final WebApplicationContext ctx = getWebApplicationContext();
            PrTimesheetLekageReportService PrTimesheetLekageReportService = (PrTimesheetLekageReportServiceImpl) ctx.getBean("PrTimesheetLekageReportService");
            List<PrTimesheetLekageReportDTO> PrTimesheetLekageReportList = PrTimesheetLekageReportService.getPrTimesheetLekageReport(filterData);
            String excelFileName = "D:/delinquency/timesheet_lekage_report.xls";//name of excel file
            String sheetName = "timesheet_lekage_report";//name of sheet
            File file = new File("D://delinquency//timesheet_lekage_report.xls");
            file.delete();
            file.createNewFile();
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet(sheetName);
            String headerArray[] = new String[]{"Employee Id", "Employe Name", "Unit name", "Sub unit name", "Project id",
                "Project Name", "Uom", "Pricing model", "Business model", "Role name", "Billing Rate","Currency",
                "Project manager Name", "Reporting Manager Name", "Customer Name", "Billable", "Location Name",
                "Timesheet Date", "Avaiable Hours", "Working Days", "Working hours", "Apporved Hours", "Accured hours",
                "Invoice Hours", "Accured Date", "Accural number", "No Working Days"};

            for (int i = 0; i < PrTimesheetLekageReportList.size(); i++) {
                String rowArray[] = new String[27];
                rowArray[0] = PrTimesheetLekageReportList.get(i).getEmployee_id();
                rowArray[1] = PrTimesheetLekageReportList.get(i).getEmployee_name();
                rowArray[2] = PrTimesheetLekageReportList.get(i).getUnit_name();
                rowArray[3] = PrTimesheetLekageReportList.get(i).getSub_unit_name();
                rowArray[4] = PrTimesheetLekageReportList.get(i).getProject_id();
                rowArray[5] = PrTimesheetLekageReportList.get(i).getProject_name();
                rowArray[6] = PrTimesheetLekageReportList.get(i).getUom();
                rowArray[7] = PrTimesheetLekageReportList.get(i).getPricing_model();
                rowArray[8] = PrTimesheetLekageReportList.get(i).getBusiness_model();
                rowArray[9] = PrTimesheetLekageReportList.get(i).getRole_name();
                rowArray[10] = PrTimesheetLekageReportList.get(i).getBilling_rate();
                rowArray[11] = PrTimesheetLekageReportList.get(i).getCurrency();
                rowArray[12] = PrTimesheetLekageReportList.get(i).getProject_manager_name();
                rowArray[13] = PrTimesheetLekageReportList.get(i).getReporting_manager_name();
                rowArray[14] = PrTimesheetLekageReportList.get(i).getCustomer_name();
                rowArray[15] = PrTimesheetLekageReportList.get(i).getBillable();
                rowArray[16] = PrTimesheetLekageReportList.get(i).getLocation_name();
                rowArray[17] = PrTimesheetLekageReportList.get(i).getTimesheet_date();
                rowArray[18] = PrTimesheetLekageReportList.get(i).getAvailable_hours();
                rowArray[19] = PrTimesheetLekageReportList.get(i).getWorking_days();
                rowArray[20] = PrTimesheetLekageReportList.get(i).getWorked_hours();
                rowArray[21] = PrTimesheetLekageReportList.get(i).getApproved_hours();
                rowArray[22] = PrTimesheetLekageReportList.get(i).getAccrued_hours();
                rowArray[23] = PrTimesheetLekageReportList.get(i).getInvoiced_hours();
                rowArray[24] = PrTimesheetLekageReportList.get(i).getAccrued_date();
                rowArray[25] = PrTimesheetLekageReportList.get(i).getAccrual_number();
                rowArray[26] = PrTimesheetLekageReportList.get(i).getNo_working_days();

                if (i == 0) {
                    HSSFRow row = sheet.createRow(i);
                    for (short c = 0; c < rowArray.length; c++) {
                        HSSFCell cell = row.createCell(c);
                        cell.setCellValue(headerArray[c]);
                    }
                }
                HSSFRow row = sheet.createRow(i + 1);
                for (short c = 0; c < rowArray.length; c++) {
                    HSSFCell cell = row.createCell(c);
                    cell.setCellValue(rowArray[c]);
                }
            }

            FileOutputStream fileOut = new FileOutputStream(excelFileName);
            wb.write(fileOut);
            fileOut.flush();
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mvc;
    }
    
     public  ModelAndView timesheetLekageReportDownload(HttpServletRequest request, HttpServletResponse response) throws IOException, DocumentException {
       
        System.out.println("inside userManual");
        String filepath = "D:/delinquency/"; 
        String filename = "timesheet_lekage_report.xls";  
        BufferedInputStream buf = null;
        ServletOutputStream myOut = null;
            try{
                myOut = response.getOutputStream(); 
                File myfile = new File(filepath + filename);
                response.setContentType("application/ms-excel");        

                response.setHeader("Content-Disposition", "attachment; filename=" + filename); // for downloading the pdf format important
//                response.setHeader("Content-Disposition", "attachment; filename=\"" + myfile + "\"");
                response.setHeader("Content-Type", "application/force-download");
                response.setContentLength((int) myfile.length());
                FileInputStream input = new FileInputStream(myfile);
                buf = new BufferedInputStream(input);
                int readBytes = 0;
                while ((readBytes = buf.read()) != -1) {
                    myOut.write(readBytes);
                }
            
            }catch(Exception e){
                System.out.println("error "+e);
            }
        return null;        
    }
     
    public ModelAndView getUtilizationReport(HttpServletRequest request, HttpServletResponse response, UtilizationReportDTO filterData) throws Exception {
        ModelAndView mvc = null;
        try {
            Map<String, String> monthList = CommonMethods.getNewMonthsList();
            Map<Integer, Integer> yearList = CommonMethods.getYearsList(1);
            mvc = new ModelAndView("/utilizationReport");
            mvc.addObject("monthList", monthList);
            mvc.addObject("yearList", yearList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mvc;
    }

    public ModelAndView utilizationReportSearch(HttpServletRequest request, HttpServletResponse response, UtilizationReportDTO filterData) throws ParseException, Exception {

        ModelAndView mvc = null;
        try {

            String selectedMonth = request.getParameter("monthId");
            String selectedYear = request.getParameter("yearId");
            Calendar startEnd = Calendar.getInstance();
            startEnd.set(Integer.parseInt(selectedYear), Integer.parseInt(selectedMonth) - 1, 1);
            Date fromDate = startEnd.getTime();
            startEnd.set(Calendar.DAY_OF_MONTH, startEnd.getActualMaximum(Calendar.DAY_OF_MONTH));
            Date toDate = startEnd.getTime();
            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            filterData.setFrom_date(sdf.format(fromDate));
            filterData.setTo_date(sdf.format(toDate));
            final WebApplicationContext ctx = getWebApplicationContext();
            PrTimesheetLekageReportService PrTimesheetLekageReportService = (PrTimesheetLekageReportServiceImpl) ctx.getBean("PrTimesheetLekageReportService");
            List<UtilizationReportDTO> UtilizationReportList = PrTimesheetLekageReportService.getUtilizationReport(filterData);
            String excelFileName = "D:/delinquency/utilization_report.xls";//name of excel file
            String sheetName = "utilization_report";//name of sheet
            File file = new File("D://delinquency//utilization_report.xls");
            file.delete();
            file.createNewFile();
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet(sheetName);
            String headerArray[] = new String[]{"Employee Id", "Employee Name", "Joined Date", "Relived Date",
                "BU", "Practice", "sub Practices", "Band", "Category", "Revised Status", "Trainee Tenure(Months)",
                "Customer Name", "Location", "Billed Hours", "Work Hours", "Working Days", "H&S", "Leave Taken",
                "Actual Working Days", "Actual Leave Taken", "UOM", "Actual Avaible Hours Monthly", "Annualised AH",
                "monthly AH", "Final Monthly AH", "UT Annualised AH", "UT Actual AH", "Employee Actual Hours UT",
                "UT Category", "Base Location"};
            for (int i = 0; i < UtilizationReportList.size(); i++) {
                String rowArray[] = new String[30];
                rowArray[0] = UtilizationReportList.get(i).getEmployee_id();
                rowArray[1] = UtilizationReportList.get(i).getEmployee_name();
                rowArray[2] = UtilizationReportList.get(i).getJoined_date();
                rowArray[3] = UtilizationReportList.get(i).getRelieved_date();
                rowArray[4] = UtilizationReportList.get(i).getBu();
                rowArray[5] = UtilizationReportList.get(i).getPractice();
                rowArray[6] = UtilizationReportList.get(i).getSub_practice();
                rowArray[7] = UtilizationReportList.get(i).getBand();
                rowArray[8] = UtilizationReportList.get(i).getCategory();
                rowArray[9] = UtilizationReportList.get(i).getRevised_status();
                rowArray[10] = UtilizationReportList.get(i).getTrainees_tenure_months();
                rowArray[11] = UtilizationReportList.get(i).getCustomer_name();
                rowArray[12] = UtilizationReportList.get(i).getLocation();
                rowArray[13] = UtilizationReportList.get(i).getBilled_hours();
                rowArray[14] = UtilizationReportList.get(i).getWork_hours();
                rowArray[15] = UtilizationReportList.get(i).getWorking_days();
                rowArray[16] = UtilizationReportList.get(i).getH_and_s();
                rowArray[17] = UtilizationReportList.get(i).getLeave_taken();
                rowArray[18] = UtilizationReportList.get(i).getActual_working_days();
                rowArray[19] = UtilizationReportList.get(i).getActual_leave_taken();
                rowArray[20] = UtilizationReportList.get(i).getUOM();
                rowArray[21] = UtilizationReportList.get(i).getActual_avaiable_hours_monthly();
                rowArray[22] = UtilizationReportList.get(i).getAnnualised_avaiable_hours();
                rowArray[23] = UtilizationReportList.get(i).getMonthly_avaiable_hours();
                rowArray[24] = UtilizationReportList.get(i).getFinal_monthly_AH();
                rowArray[25] = UtilizationReportList.get(i).getUT_annualised_AH();
                rowArray[26] = UtilizationReportList.get(i).getUT_actual_AH();
                rowArray[27] = UtilizationReportList.get(i).getEmployee_Actual_Hours_UT();
                rowArray[28] = UtilizationReportList.get(i).getUT_category();
                rowArray[29] = UtilizationReportList.get(i).getBase_location();

                if (i == 0) {
                    HSSFRow row = sheet.createRow(i);
                    for (short c = 0; c < rowArray.length; c++) {
                        HSSFCell cell = row.createCell(c);
                        cell.setCellValue(headerArray[c]);
                    }
                }
                HSSFRow row = sheet.createRow(i + 1);
                for (short c = 0; c < rowArray.length; c++) {
                    HSSFCell cell = row.createCell(c);
                    cell.setCellValue(rowArray[c]);
                }
            }

            FileOutputStream fileOut = new FileOutputStream(excelFileName);
            wb.write(fileOut);
            fileOut.flush();
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mvc;
    }

    public ModelAndView utilizationReportDownload(HttpServletRequest request, HttpServletResponse response) throws IOException, DocumentException {

        System.out.println("inside userManual");
        String filepath = "D:/delinquency/";
        String filename = "utilization_report.xls";
        BufferedInputStream buf = null;
        ServletOutputStream myOut = null;
        try {
            myOut = response.getOutputStream();
            File myfile = new File(filepath + filename);
            response.setContentType("application/ms-excel");

            response.setHeader("Content-Disposition", "attachment; filename=" + filename); // for downloading the pdf format important
//                response.setHeader("Content-Disposition", "attachment; filename=\"" + myfile + "\"");
            response.setHeader("Content-Type", "application/force-download");
            response.setContentLength((int) myfile.length());
            FileInputStream input = new FileInputStream(myfile);
            buf = new BufferedInputStream(input);
            int readBytes = 0;
            while ((readBytes = buf.read()) != -1) {
                myOut.write(readBytes);
            }

        } catch (Exception e) {
            System.out.println("error " + e);
        }
        return null;
    }

    public ModelAndView getOJSSurveyReport(HttpServletRequest request, HttpServletResponse response, OJFSurveyReportDTO filterData) throws Exception {
        ModelAndView mvc = null;
        try {
            mvc = new ModelAndView("/OJF_Survey_Report");
            String from_date = request.getParameter("from_date");
            String to_date = request.getParameter("to_date");

            filterData.setFrom_date(from_date);
            filterData.setTo_date(to_date);
            final WebApplicationContext ctx = getWebApplicationContext();
            PrTimesheetLekageReportService PrTimesheetLekageReportService = (PrTimesheetLekageReportServiceImpl) ctx.getBean("PrTimesheetLekageReportService");
            List<OJFSurveyReportDTO> OJFSurveyList = PrTimesheetLekageReportService.getOJFSurveyReport(filterData);
            String excelFileName = "D:/delinquency/OJF_Survey_Report.xls";//name of excel file
            String sheetName = "OJF_Survey_Report";//name of sheet
            File file = new File("D://delinquency//OJF_Survey_Report.xls");
            file.delete();
            file.createNewFile();
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet(sheetName);
            String headerArray[] = new String[]{"Employee Id", "Employee Name", "Joined Date", "Band_name", "Question Category", "Question", "Answer", "City"};
            if (OJFSurveyList.isEmpty()) {
                short c = 0;
                HSSFRow row = sheet.createRow(c);
                HSSFCell cell = row.createCell(c);
                cell.setCellValue("No Data to Display");
            } else {
                for (int i = 0; i < OJFSurveyList.size(); i++) {

                    String rowArray[] = new String[8];
                    rowArray[0] = OJFSurveyList.get(i).getEmployee_id();
                    rowArray[1] = OJFSurveyList.get(i).getEmployee_name();
                    rowArray[2] = OJFSurveyList.get(i).getJoined_date();
                    rowArray[3] = OJFSurveyList.get(i).getBand_name();
                    rowArray[4] = OJFSurveyList.get(i).getQuestion_category();
                    rowArray[5] = OJFSurveyList.get(i).getQuestion();
                    rowArray[6] = OJFSurveyList.get(i).getAnswer();
                    rowArray[7] = OJFSurveyList.get(i).getCity();

                    if (i == 0) {
                        HSSFRow row = sheet.createRow(i);
                        for (short c = 0; c < rowArray.length; c++) {
                            HSSFCell cell = row.createCell(c);
                            cell.setCellValue(headerArray[c]);
                        }
                    }
                    HSSFRow row = sheet.createRow(i + 1);
                    for (short c = 0; c < rowArray.length; c++) {
                        HSSFCell cell = row.createCell(c);
                        cell.setCellValue(rowArray[c]);
                    }
                }
            }

            FileOutputStream fileOut = new FileOutputStream(excelFileName);
            wb.write(fileOut);
            fileOut.flush();
            fileOut.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mvc;
    }

    public ModelAndView getOJFSurveryReportDownload(HttpServletRequest request, HttpServletResponse response) throws IOException, DocumentException {

        System.out.println("inside userManual");
        String filepath = "D:/delinquency/";
        String filename = "OJF_Survey_Report.xls";
        BufferedInputStream buf = null;
        ServletOutputStream myOut = null;
        try {
            myOut = response.getOutputStream();
            File myfile = new File(filepath + filename);
            response.setContentType("application/ms-excel");

            response.setHeader("Content-Disposition", "attachment; filename=" + filename); // for downloading the pdf format important
//                response.setHeader("Content-Disposition", "attachment; filename=\"" + myfile + "\"");
            response.setHeader("Content-Type", "application/force-download");
            response.setContentLength((int) myfile.length());
            FileInputStream input = new FileInputStream(myfile);
            buf = new BufferedInputStream(input);
            int readBytes = 0;
            while ((readBytes = buf.read()) != -1) {
                myOut.write(readBytes);
            }

        } catch (Exception e) {
            System.out.println("error " + e);
        }
        return null;
    }
    
    public ModelAndView getInvoiceAnnexureCustomerList(HttpServletRequest request, HttpServletResponse response, PrTimesheetLekageReportDTO filterData) throws Exception {
        ModelAndView mvc = null;
        try {
            mvc = new ModelAndView("/invoice_Annexure_Report");
            final WebApplicationContext ctx = getWebApplicationContext();
            PrTimesheetLekageReportService PrTimesheetLekageReportService = (PrTimesheetLekageReportServiceImpl) ctx.getBean("PrTimesheetLekageReportService");
            List<PrTimesheetLekageReportDTO> customerList = PrTimesheetLekageReportService.getCustomerList();
            mvc.addObject("customerList", customerList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mvc;
    }

    public ModelAndView getInvoiceAnnexureProjectList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = null;
        PrintWriter out = response.getWriter();
        String customer_id = null;
        try {
            final WebApplicationContext ctx = getWebApplicationContext();
            PrTimesheetLekageReportService PrTimesheetLekageReportService = (PrTimesheetLekageReportServiceImpl) ctx.getBean("PrTimesheetLekageReportService");
            PrTimesheetLekageReportDTO filterData = new PrTimesheetLekageReportDTO();
            customer_id = request.getParameter("customer_id");
            if (customer_id != null) {
                filterData.setCust_id(customer_id);
                List<PrTimesheetLekageReportDTO> projectList = PrTimesheetLekageReportService.getProjectList(filterData);
                Iterator itr = projectList.iterator();
                out.println("<option value='0'>--Select--</option>");
                while (itr.hasNext()) {
                    PrTimesheetLekageReportDTO dto = (PrTimesheetLekageReportDTO) itr.next();
                    out.println("<option value=" + dto.getProject_id() + ">" + dto.getProject_name() + "</option>");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }

    public ModelAndView getInvoiceAnnexureReportXL(HttpServletRequest request, HttpServletResponse response, PrTimesheetLekageReportDTO filterData) throws Exception {
        ModelAndView mvc = null;
        try {
            mvc = new ModelAndView("/invoice_Annexure_Report");
            String customer_id = request.getParameter("customer_id");
            String project_id = request.getParameter("project_id");
            String from_date = request.getParameter("from_date");
            String to_date = request.getParameter("to_date");
            if (customer_id.equals("0")) {
                filterData.setCust_id(null);
            } else {
                filterData.setCust_id(customer_id);
            }
            if (project_id.equals("0")) {
                filterData.setProject_id(null);
            } else {
                filterData.setProject_id(project_id);
            }

            filterData.setFrom_date(from_date);
            filterData.setTo_date(to_date);
            final WebApplicationContext ctx = getWebApplicationContext();
            PrTimesheetLekageReportService PrTimesheetLekageReportService = (PrTimesheetLekageReportServiceImpl) ctx.getBean("PrTimesheetLekageReportService");
            List<PrTimesheetLekageReportDTO> invoiceAnnexureReportList = PrTimesheetLekageReportService.getInvoiceAnnexureReport(filterData);
            String excelFileName = "D:/delinquency/invoice_Annexure_Report.xls";//name of excel file
            String sheetName = "invoice_Annexure_Report";//name of sheet
            File file = new File("D://delinquency//invoice_Annexure_Report.xls");
            file.delete();
            file.createNewFile();
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet(sheetName);
            String headerArray[] = new String[]{"Invoice Code", "Invoice Date", "Customer Name", "Project Name", "Attachment Name"};

            for (int i = 0; i < invoiceAnnexureReportList.size(); i++) {
                String rowArray[] = new String[5];
                rowArray[0] = invoiceAnnexureReportList.get(i).getInvoice_code();
                rowArray[1] = invoiceAnnexureReportList.get(i).getInvoice_date();
                rowArray[2] = invoiceAnnexureReportList.get(i).getCustomer_name();
                rowArray[3] = invoiceAnnexureReportList.get(i).getProject_name();
                rowArray[4] = invoiceAnnexureReportList.get(i).getAttachment_name();
                if (i == 0) {
                    HSSFRow row = sheet.createRow(i);
                    for (short c = 0; c < rowArray.length; c++) {
                        HSSFCell cell = row.createCell(c);
                        cell.setCellValue(headerArray[c]);
                    }
                }
                HSSFRow row = sheet.createRow(i + 1);
                for (short c = 0; c < rowArray.length; c++) {
                    HSSFCell cell = row.createCell(c);
                    cell.setCellValue(rowArray[c]);
                }
            }

            FileOutputStream fileOut = new FileOutputStream(excelFileName);
            wb.write(fileOut);
            fileOut.flush();
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mvc;
    }

    public ModelAndView getInvoiceAnnexureReportDownload(HttpServletRequest request, HttpServletResponse response) throws IOException, DocumentException {

        System.out.println("inside userManual");
        String filepath = "D:/delinquency/";
        String filename = "invoice_Annexure_Report.xls";
        BufferedInputStream buf = null;
        ServletOutputStream myOut = null;
        try {
            myOut = response.getOutputStream();
            File myfile = new File(filepath + filename);
            response.setContentType("application/ms-excel");

            response.setHeader("Content-Disposition", "attachment; filename=" + filename); // for downloading the pdf format important
//                response.setHeader("Content-Disposition", "attachment; filename=\"" + myfile + "\"");
            response.setHeader("Content-Type", "application/force-download");
            response.setContentLength((int) myfile.length());
            FileInputStream input = new FileInputStream(myfile);
            buf = new BufferedInputStream(input);
            int readBytes = 0;
            while ((readBytes = buf.read()) != -1) {
                myOut.write(readBytes);
            }

        } catch (Exception e) {
            System.out.println("error " + e);
        }
        return null;
    }
    
    public ModelAndView getWorkLocationReportXL(HttpServletRequest request, HttpServletResponse response, PrTimesheetLekageReportDTO filterData) throws Exception {
        int page;
        ModelAndView mvc = null;
        try {
            mvc = new ModelAndView("/workLocation_report");
            final WebApplicationContext ctx = getWebApplicationContext();

            PrTimesheetLekageReportService PrTimesheetLekageReportService = (PrTimesheetLekageReportServiceImpl) ctx.getBean("PrTimesheetLekageReportService");
            List<PrTimesheetLekageReportDTO> WorkLocationReport = PrTimesheetLekageReportService.getWorkLocationReport(filterData);

            mvc.addObject("WorkLocationReport", WorkLocationReport);


            String excelFileName = "D:/delinquency/workLocation_report.xls";//name of excel file
            String sheetName = "workLocation_report";//name of sheet
            File file = new File("D://delinquency//work_location_report.xls");
            file.delete();
            file.createNewFile();
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet(sheetName);
            String headerArray[] = new String[]{"Employee Id", "Employee Name", "Work Location",
                "Last update date"};

            for (int i = 0; i < WorkLocationReport.size(); i++) {
                String rowArray[] = new String[4];
                rowArray[0] = WorkLocationReport.get(i).getEmployee_id();
                rowArray[1] = WorkLocationReport.get(i).getEmployee_name();
                rowArray[3] = WorkLocationReport.get(i).getLast_update_date();
                rowArray[2] = WorkLocationReport.get(i).getWork_location();

                if (i == 0) {
                    HSSFRow row = sheet.createRow(i);
                    for (short c = 0; c < rowArray.length; c++) {
                        HSSFCell cell = row.createCell(c);
                        cell.setCellValue(headerArray[c]);
                    }
                }
                HSSFRow row = sheet.createRow(i + 1);
                for (short c = 0; c < rowArray.length; c++) {
                    HSSFCell cell = row.createCell(c);
                    cell.setCellValue(rowArray[c]);
                }
            }

            FileOutputStream fileOut = new FileOutputStream(excelFileName);
            wb.write(fileOut);
            fileOut.flush();
            fileOut.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mvc;
    }

    public ModelAndView workLocationReportDownload(HttpServletRequest request, HttpServletResponse response) throws IOException, DocumentException {

        System.out.println("inside userManual");
        String filepath = "D:/delinquency/";
        String filename = "workLocation_report.xls";
        BufferedInputStream buf = null;
        ServletOutputStream myOut = null;
        try {
            myOut = response.getOutputStream();
            // File myfile = new File(filepath + filename);
            File myfile = new File("D://delinquency//workLocation_report.xls");
            response.setContentType("application/ms-excel");

            response.setHeader("Content-Disposition", "attachment; filename=" + filename); // for downloading the pdf format important
//                response.setHeader("Content-Disposition", "attachment; filename=\"" + myfile + "\"");
            response.setHeader("Content-Type", "application/force-download");
            response.setContentLength((int) myfile.length());
            FileInputStream input = new FileInputStream(myfile);
            buf = new BufferedInputStream(input);
            int readBytes = 0;
            while ((readBytes = buf.read()) != -1) {
                myOut.write(readBytes);
            }

        } catch (Exception e) {
            System.out.println("error " + e);
        }
        return null;
    }
    public ModelAndView getPrLastBilledReportXL(HttpServletRequest request, HttpServletResponse response, PrLastBilledReportDTO filterData) throws Exception {
        int page;
        ModelAndView mvc = null;
        try {
            mvc = new ModelAndView("/prLastBilled_report");
            final WebApplicationContext ctx = getWebApplicationContext();
            
            PrTimesheetLekageReportService prLastBilledReportService = ( PrTimesheetLekageReportServiceImpl) ctx.getBean("PrTimesheetLekageReportService");
            List<PrLastBilledReportDTO> prLastBilledReport;
            prLastBilledReport = prLastBilledReportService.getPrLastBilledReport(filterData);

            mvc.addObject("prLastBilledReport", prLastBilledReport);


            String excelFileName = "D:/delinquency/prLastBilled_report.xls";//name of excel file
            String sheetName = "prLastBilled_report";//name of sheet
            File file = new File("D://delinquency//prLastBilled_report.xls");
            file.delete();
            file.createNewFile();
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet(sheetName);
            String headerArray[] = new String[]{"Employee Id", "Employee Name", "Band", "Designation","Unit","Practice",
                "Sub Practice","Employment Status","Billable","Last Billed Date"};
            for (int i = 0; i < prLastBilledReport.size(); i++) {
                String rowArray[] = new String[10];
                rowArray[0] = prLastBilledReport.get(i).getEmployee_id();
                rowArray[1] = prLastBilledReport.get(i).getEmployee_name();
                rowArray[2] = prLastBilledReport.get(i).getBand();
                rowArray[3] = prLastBilledReport.get(i).getDesignation();
                rowArray[4] = prLastBilledReport.get(i).getUnit();
                rowArray[5] = prLastBilledReport.get(i).getPractice();
                rowArray[6] = prLastBilledReport.get(i).getSub_practice();
                rowArray[7] = prLastBilledReport.get(i).getEmployment_status();
                rowArray[8] = prLastBilledReport.get(i).getBillable();
                rowArray[9] = prLastBilledReport.get(i).getLast_billed_date();

                if (i == 0) {
                    HSSFRow row = sheet.createRow(i);
                    for (short c = 0; c < rowArray.length; c++) {
                        HSSFCell cell = row.createCell(c);
                        cell.setCellValue(headerArray[c]);
                    }
                }
                HSSFRow row = sheet.createRow(i + 1);
                for (short c = 0; c < rowArray.length; c++) {
                    HSSFCell cell = row.createCell(c);
                    cell.setCellValue(rowArray[c]);
                }
            }
            

            FileOutputStream fileOut = new FileOutputStream(excelFileName);
            wb.write(fileOut);
            fileOut.flush();
            fileOut.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mvc;
    }
    
    public ModelAndView prLastBilledReportDownload(HttpServletRequest request, HttpServletResponse response) throws IOException, DocumentException {

        System.out.println("inside userManual");
        String filepath = "D:/delinquency/";
        String filename = "prLastBilled_report.xls";
        BufferedInputStream buf = null;
        ServletOutputStream myOut = null;
        try {
            myOut = response.getOutputStream();
            // File myfile = new File(filepath + filename);
            File myfile = new File("D://delinquency//prLastBilled_report.xls");
            response.setContentType("application/ms-excel");

            response.setHeader("Content-Disposition", "attachment; filename=" + filename); // for downloading the pdf format important
//                response.setHeader("Content-Disposition", "attachment; filename=\"" + myfile + "\"");
            response.setHeader("Content-Type", "application/force-download");
            response.setContentLength((int) myfile.length());
            FileInputStream input = new FileInputStream(myfile);
            buf = new BufferedInputStream(input);
            int readBytes = 0;
            while ((readBytes = buf.read()) != -1) {
                myOut.write(readBytes);
            }

        } catch (Exception e) {
            System.out.println("error " + e);
        }
        return null;
    }

//      public ModelAndView ReportDownload(HttpServletRequest request, HttpServletResponse response) throws IOException, DocumentException {
//
//        System.out.println("inside userManual");
//        String filePath = "D:/delinquency/";
//        String fileName = "prLastBilled_report.xls";
//         
//        
//        BufferedInputStream buf = null;
//        ServletOutputStream myOut = null;
//        try {
//            myOut = response.getOutputStream();
//            // File myfile = new File(filepath + filename);
//           File myfile = new File("D://delinquency//prLastBilled_report.xls");
//            // File myfile = new File(filePath + fileName);
//         //    File myfile = new File(fileName);
//            response.setContentType("application/ms-excel");
//
//            response.setHeader("Content-Disposition", "attachment; filename=" + fileName); // for downloading the pdf format important
////                response.setHeader("Content-Disposition", "attachment; filename=\"" + myfile + "\"");
//            response.setHeader("Content-Type", "application/force-download");
//            response.setContentLength((int) myfile.length());
//            FileInputStream input = new FileInputStream(myfile);
//            buf = new BufferedInputStream(input);
//            int readBytes = 0;
//            while ((readBytes = buf.read()) != -1) {
//                myOut.write(readBytes);
//            }
//
//        } catch (Exception e) {
//            System.out.println("error " + e);
//        }
//        return null;
//    }
}
