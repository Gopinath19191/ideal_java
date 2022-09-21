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
import com.defiance.ideal.reports.dto.PurchaseOrderReportDTO;
import com.defiance.ideal.reports.dto.PurchaseOrderReportDetails;
import com.defiance.ideal.reports.service.AssociateServiceImpl;
import com.defiance.ideal.reports.service.PurchaseOrderReportService;
import com.defiance.ideal.reports.service.PurchaseOrderReportServiceImpl;
import com.defiance.ideal.reports.util.CommonConfigurations;
import com.defiance.ideal.reports.util.CommonMethods;

/**
 * 
 * @author 15261
 */
public class PurchaseOrderReportController extends MultiActionController {

    public ModelAndView purchaseOrderReport(HttpServletRequest request,
            HttpServletResponse response) {
        ModelAndView mvc = null;
        mvc = new ModelAndView("/purchaseOrderReport");
         try {
            final WebApplicationContext ctx = getWebApplicationContext();
            PurchaseOrderReportService PurchaseOrderReportService = (PurchaseOrderReportServiceImpl) ctx.getBean("PurchaseOrderReportService");

            String projectString = request.getParameter("project_search");
            String customerString = request.getParameter("customer_search");
            String fromDate = request.getParameter("from_date");
            String toDate = request.getParameter("to_date");
            String pmString = request.getParameter("pm_search");
           
            PurchaseOrderReportDetails dto = new PurchaseOrderReportDetails();

            projectString = ("Search by Project Id or Project Name").equalsIgnoreCase(projectString) ? null : projectString;
            customerString = ("Search by Customer Id or Customer Name").equalsIgnoreCase(customerString) ? null : customerString;
            pmString = ("Search by Employee Id or Employee Name").equalsIgnoreCase(pmString) ? null : pmString;

            if (projectString != null) {
                StringTokenizer str = new StringTokenizer(projectString, "-");
                for (int i = 0; i < str.countTokens() - 1; i++) {
                    projectString = str.nextToken().trim();
                }
                dto.setPid_Num(projectString);
            }
            String customerCode = "";
            if (customerString != null) {
                StringTokenizer str = new StringTokenizer(customerString, "-");
                for (int i = 0; i < str.countTokens() - 1; i++) {
                    customerCode = str.nextToken().trim();
                }
                dto.setCustomerName(customerCode);
            }

            if (fromDate != null) {
                dto.setFromDate(fromDate);
            }
            if (toDate != null) {
                dto.setToDate(toDate);
            }


            String pmEmpNumberString = pmString;
            String pmEmpNumber = null;
            if (pmEmpNumberString != null) {
                StringTokenizer str = new StringTokenizer(pmEmpNumberString,
                        "-");
                for (int i = 0; i < str.countTokens() - 1; i++) {
                    pmEmpNumber = str.nextToken().trim();
                }
                dto.setProjectManager(pmEmpNumber);
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
            List<PurchaseOrderReportDetails> purchaseOrderReportDetails = PurchaseOrderReportService.getPurchaseOrderReportDetails(dto);
            recordCount = PurchaseOrderReportService.getPurchaseOrderReportDetailsCount(dto);
            int end = (start+rows)-1;
            int[] paging = CommonMethods.paging(recordCount, page, rows);

            for (int i = 0; i < purchaseOrderReportDetails.size(); i++) {
                System.out.println("::::" + purchaseOrderReportDetails.get(i).getCustomer());
            }
            mvc.addObject("paging", paging);
            mvc.addObject("projectString", projectString);
            mvc.addObject("customerString", customerString);
            mvc.addObject("pmString", pmString);
            mvc.addObject("fromDate", fromDate);
            mvc.addObject("toDate", toDate);
            mvc.addObject("purchaseOrderReportDetails", purchaseOrderReportDetails);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mvc;
    }

    public ModelAndView purchaseOrderReportXL(HttpServletRequest request,
            HttpServletResponse response, PurchaseOrderReportDetails formData) {
        System.out.println("purchaseOrderReportXL Method Called");


        try {
            final WebApplicationContext ctx = getWebApplicationContext();
            PurchaseOrderReportService purchaseOrderReportService = (PurchaseOrderReportServiceImpl) ctx.getBean("PurchaseOrderReportService");

            ArrayList entireList = new ArrayList();
            ArrayList<String> rowDataList;

            String projectString = request.getParameter("project_search");
            String customerString = request.getParameter("customer_search");
            String fromDate = request.getParameter("from_date");
            String toDate = request.getParameter("to_date");
            String pmString = request.getParameter("pm_search");
            
            projectString = ("Search by Project Id or Project Name").equalsIgnoreCase(projectString) ? null : projectString;
            customerString = ("Search by Customer Id or Customer Name").equalsIgnoreCase(customerString) ? null : customerString;
            pmString = ("Search by Employee Id or Employee Name").equalsIgnoreCase(pmString) ? null : pmString;

            PurchaseOrderReportDetails dto = new PurchaseOrderReportDetails();

            if (projectString != null) {
                StringTokenizer str = new StringTokenizer(projectString, "-");
                for (int i = 0; i < str.countTokens() - 1; i++) {
                    projectString = str.nextToken().trim();
                }
                dto.setPid_Num(projectString);
            }
            String customerCode = "";
            if (customerString != null) {
                StringTokenizer str = new StringTokenizer(customerString, "-");
                for (int i = 0; i < str.countTokens() - 1; i++) {
                    customerCode = str.nextToken().trim();
                }
                dto.setCustomerName(customerCode);
            }

            if (fromDate != null) {
                dto.setFromDate(fromDate);
            }
            if (toDate != null) {
                dto.setToDate(toDate);
            }


            String pmEmpNumberString = pmString;
            String pmEmpNumber = null;
            if (pmEmpNumberString != null) {
                StringTokenizer str = new StringTokenizer(pmEmpNumberString,"-");
                for (int i = 0; i < str.countTokens() - 1; i++) {
                    pmEmpNumber = str.nextToken().trim();
                }
                dto.setProjectManager(pmEmpNumber);
            }

            List<PurchaseOrderReportDetails> reportDetailsList = purchaseOrderReportService.getPurchaseOrderReportDetails(dto);
            if (reportDetailsList != null && reportDetailsList.size() > 0) {

                // Set all data in Arraylist and send to exportToExcel() function.

                rowDataList = new ArrayList<String>();
                rowDataList.add(new String("Customer"));
                rowDataList.add(new String("PID_Num"));
                rowDataList.add(new String("PID_Description"));
                rowDataList.add(new String("SBU"));
                rowDataList.add(new String("BDM"));
                rowDataList.add(new String("PM"));
                rowDataList.add(new String("PO_Reference_Number"));
                rowDataList.add(new String("PO_Date"));
                rowDataList.add(new String("Currency"));
                rowDataList.add(new String("PO_Value"));
               
                entireList.add(rowDataList);

                Iterator<PurchaseOrderReportDetails> itr = reportDetailsList.iterator();
                PurchaseOrderReportDetails pd = null;
                while (itr.hasNext()) {
                    pd = itr.next();

                    rowDataList = new ArrayList<String>();


                    if (pd.getCustomer() == null) {
                        rowDataList.add("");
                    } else {
                        rowDataList.add(pd.getCustomer());
                    }

                    if (pd.getPid_Num() == null) {
                        rowDataList.add("");
                    } else {
                        rowDataList.add(pd.getPid_Num());
                    }

                    if (pd.getPid_Description() == null) {
                        rowDataList.add("");
                    } else {
                        rowDataList.add(pd.getPid_Description());
                    }

                    rowDataList.add(pd.getSbu());

                    if (pd.getBdm() == null) {
                        rowDataList.add("");
                    } else {
                        rowDataList.add(pd.getBdm());
                    }

                    if (pd.getProjectManager() == null) {
                        rowDataList.add("");
                    } else {
                        rowDataList.add(pd.getProjectManager());
                    }

                    if (pd.getPo_Reference_Number() == null) {
                        rowDataList.add("");
                    } else {
                        rowDataList.add(pd.getPo_Reference_Number());
                    }
                     if (pd.getPo_Date() == null) {
                        rowDataList.add("");
                    } else {
                        rowDataList.add(pd.getPo_Date());
                    }
                    if (pd.getCurrencies() == null) {
                        rowDataList.add("");
                    } else {
                        rowDataList.add(pd.getCurrencies());
                    }
                    
                    if (pd.getPo_Value() == null) {
                        rowDataList.add("");
                    } else {
                        rowDataList.add(pd.getPo_Value());
                    }
                    entireList.add(rowDataList);

                }

            }

            request.setAttribute("reportDetailsList", reportDetailsList);



            CommonMethods.exportToExcel(response, entireList,
                    "projectFinanceReport.xls", "Project Finance Report", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
