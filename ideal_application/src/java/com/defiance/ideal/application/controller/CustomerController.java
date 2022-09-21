/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.application.controller;

import com.defiance.ideal.application.dto.CustomerAddDTO;
import com.defiance.ideal.application.dto.CustomerDataDTO;
import com.defiance.ideal.application.dto.CustomerGroupDTO;
import com.defiance.ideal.application.dto.CustomerMappingDTO;
import com.defiance.ideal.application.service.CustomerMappingServiceImpl;
import com.defiance.ideal.application.service.CustomerServiceImpl;
import com.defiance.ideal.application.util.CommonConfigurations;
import com.defiance.ideal.application.util.CommonMethods;
import com.defiance.ideal.application.util.Config;
import com.lowagie.text.DocumentException;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Integer.parseInt;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static org.apache.commons.lang.StringUtils.trim;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 *
 * @author 14058
 */
public class CustomerController extends MultiActionController {

    public CustomerController() {
    }

    public ModelAndView customersList(HttpServletRequest request, HttpServletResponse response, CustomerDataDTO filterData) throws Exception {
        Config t = new Config();
        t.getParentId("mail_details");
        ModelAndView mv = null;
        HttpSession session = request.getSession();
        String employeeId = null;
        //String selectedMonth,selectedYear,;
        String customAdd = "";
        Calendar calndr = Calendar.getInstance();
        calndr.get(Calendar.MONTH);
        calndr.get(Calendar.YEAR);

        if (session.getAttribute("EMP_ID") == null) {
            employeeId = request.getParameter("empid");
            System.out.println("employeeID+++"+employeeId);
            session.setAttribute("EMP_ID", employeeId);
        } else {
            employeeId = (String) session.getAttribute("EMP_ID");
            System.out.println("employeeID+++"+employeeId);
        }

        try {
            final WebApplicationContext ctx = getWebApplicationContext();
            CustomerServiceImpl customerService = (CustomerServiceImpl) ctx.getBean("CustomerService");
            List<CustomerDataDTO> customerData = customerService.fetchCustomerData(filterData);

            mv = new ModelAndView("/customerList");
            mv.addObject("customerData", customerData);
            customAdd = request.getParameter("customAdd");
            if(customAdd!= null){
                if( customAdd.equals("1")){
                    mv.addObject("Success_message","Customer Submitted Successfully");
                    mv.addObject("customAdd","0");
                }else if( customAdd.equals("2")){
                    mv.addObject("Success_message","Customer Saved Successfully");
                    mv.addObject("customAdd","0");
                }else if( customAdd.equals("3")){
                    mv.addObject("Success_message","Please make any changes and submit the customer");
                    mv.addObject("customAdd","0");
                }else{
                    mv.addObject("Success_message","");
                    mv.addObject("customAdd","0");
                }
            }
 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }
    public ModelAndView financeView(HttpServletRequest request, HttpServletResponse response, CustomerDataDTO filterData){
        Config t = new Config();
        t.getParentId("mail_details");
        ModelAndView mv = null;
        HttpSession session = request.getSession();
        String employeeId = null;
        if (session.getAttribute("EMP_ID") == null) {
            employeeId = request.getParameter("empid");
            System.out.println("employeeID+++"+employeeId);
            session.setAttribute("EMP_ID", employeeId);
        } else {
            employeeId = (String) session.getAttribute("EMP_ID");
            System.out.println("employeeID+++"+employeeId);
        }
        try {
            final WebApplicationContext ctx = getWebApplicationContext();
            CustomerServiceImpl customerService = (CustomerServiceImpl) ctx.getBean("CustomerService");
            filterData.setDeleted("0");
            List<CustomerDataDTO> customerData = customerService.fetchActiveInactiveCustomer(filterData);
            List<CustomerDataDTO> customerDeletedStatus = customerService.fetchCustomerActiveInactive();
            mv = new ModelAndView("/financeView");
            mv.addObject("customerData", customerData);
            mv.addObject("customerActiveList", customerDeletedStatus);
            mv.addObject("customerStatus", filterData.getDeleted());
        
 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }
    
    public ModelAndView financeViewSearchCustomer(HttpServletRequest request, HttpServletResponse response, CustomerDataDTO filterData){
        Config t = new Config();
        t.getParentId("mail_details");
        ModelAndView mv = null;
        HttpSession session = request.getSession();
        String employeeId = null;
        if (session.getAttribute("EMP_ID") == null) {
            employeeId = request.getParameter("empid");
            System.out.println("employeeID+++"+employeeId);
            session.setAttribute("EMP_ID", employeeId);
        } else {
            employeeId = (String) session.getAttribute("EMP_ID");
            System.out.println("employeeID+++"+employeeId);
        }
        try {
            final WebApplicationContext ctx = getWebApplicationContext();
            CustomerServiceImpl customerService = (CustomerServiceImpl) ctx.getBean("CustomerService");
            String deleted = request.getParameter("customerStatus");
            filterData.setDeleted(deleted);
            List<CustomerDataDTO> customerData = customerService.fetchActiveInactiveCustomer(filterData);
            List<CustomerDataDTO> customerDeletedStatus = customerService.fetchCustomerActiveInactive();
            mv = new ModelAndView("/financeView");
            mv.addObject("customerData", customerData);
            mv.addObject("customerActiveList", customerDeletedStatus);
            mv.addObject("customerStatus", filterData.getDeleted());
        
 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }
    
    public ModelAndView updateCustomerActiveInactive(HttpServletRequest request, HttpServletResponse response, CustomerDataDTO filterData){
        Config t = new Config();
        t.getParentId("mail_details");
        ModelAndView mv = null;
        HttpSession session = request.getSession();
        String employeeId = null;
        if (session.getAttribute("EMP_ID") == null) {
            employeeId = request.getParameter("empid");
            session.setAttribute("EMP_ID", employeeId);
        } else {
            employeeId = (String) session.getAttribute("EMP_ID");
        }
        try {
            final WebApplicationContext ctx = getWebApplicationContext();
            CustomerServiceImpl customerService = (CustomerServiceImpl) ctx.getBean("CustomerService");
            String deleted = request.getParameter("deleted");
            String custID = request.getParameter("custId");
            filterData.setDeleted(deleted);
            filterData.setCustID(custID);
            CustomerAddDTO customerDataCode = new CustomerAddDTO();
            customerDataCode.setCustomerID(custID);
            String customerActivatingCode = customerService.getCustomerActivatingCode(custID);
            if(customerActivatingCode==null){
                if(deleted.equals("0")){
                    List<CustomerAddDTO> customerCodeId = customerService.getMaxCustomerCode(customerDataCode);
                    System.out.println(customerCodeId+"::MAXID ::"+customerCodeId.get(0).getCustomerMaxId()+":COND:");
                    String customerMaxId = null;
                    if(customerCodeId.get(0).getCustomerMaxId() == null || customerCodeId.get(0).getCustomerMaxId().equals("")) {
                        //customerMaxId = "C00001";
                        customerMaxId = CommonConfigurations.customerCode;
                    } else {
                        String customerMaxTempId = null;
                        customerMaxTempId = customerCodeId.get(0).getCustomerMaxId();
                        Integer customerCodeInt = Integer.parseInt(customerMaxTempId)+1;
                        String outputCustomerCode = Integer.toString(customerCodeInt);
                        while (outputCustomerCode.length() < 5) outputCustomerCode = "0" + outputCustomerCode;
                        customerMaxId = outputCustomerCode;
                    }
                    List<CustomerAddDTO> customerDivisionCount = customerService.getDivisionCount(customerDataCode);

                    if(Integer.parseInt(customerDivisionCount.get(0).getCustomerDivision()) > 1){
                        List<CustomerAddDTO> customerCodeName = customerService.getCustomerCode(customerDataCode);
                        List<CustomerAddDTO> customerDivisionApproved = customerService.getCustomerDivisionApprovedCount(customerDataCode);
                        System.out.println("count>>>>>>>>>>>"+Integer.parseInt(customerDivisionCount.get(0).getCustomerDivision())+"code>>>>>>>"+customerCodeName.get(0).getCustomerCode());
                        if(Integer.parseInt(customerDivisionApproved.get(0).getCustomerDivision())>0){
                            customerDataCode.setCustomerCodelike(customerCodeName.get(0).getCustomerCode());
                            List<CustomerAddDTO> customerMaxLength = customerService.getCustomerCodeMaxLike(customerDataCode);
                            String[] splitCode = customerMaxLength.get(0).getCustomerCode().split("-");
                            if(splitCode.length == 1){
                                customerMaxId = customerCodeName.get(0).getCustomerCode()+"-1";
                            }
                            else{
                                customerMaxId = customerCodeName.get(0).getCustomerCode()+"-"+(Integer.parseInt(splitCode[1])+1);
                            }
                        }
                        else{
                            String customerMaxTempId = null;
                            customerMaxTempId = customerCodeId.get(0).getCustomerMaxId();
                            String[] temp = customerMaxTempId.split("[a-zA-Z]");
                            String[] tempNum = customerMaxTempId.split("\\d");
                            Integer customerCodeInt = Integer.parseInt(temp[1])+1;
                            String outputCustomerCode = Integer.toString(customerCodeInt);
                            while (outputCustomerCode.length() < 5) outputCustomerCode = "0" + outputCustomerCode;
                            customerMaxId = tempNum[0] + outputCustomerCode;
                            customerMaxId = customerMaxId+"-1";
                        }
                    }
                    customerDataCode.setCustomerCode(customerMaxId);
                    customerService.updateCustomerCode(customerDataCode);
                }
            }
            customerService.updateCustomerActiveInactive(filterData);
            mv = new ModelAndView("/financeView");
            if(deleted.equals("0")){
                mv.addObject("updatedStatus","Customer Activated Successfully");
            }else if(deleted.equals("1")){
                mv.addObject("updatedStatus","Customer Inactivated Successfully");
            }
            if(deleted.equals("0")){
                filterData.setDeleted("1");
            }else if(deleted.equals("1")){
                filterData.setDeleted("0");
            }
            List<CustomerDataDTO> customerData = customerService.fetchActiveInactiveCustomer(filterData);
            List<CustomerDataDTO> customerDeletedStatus = customerService.fetchCustomerActiveInactive();
            mv.addObject("customerData", customerData);
            mv.addObject("customerActiveList", customerDeletedStatus);
            mv.addObject("customerStatus", filterData.getDeleted());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }
    
//    public ModelAndView customerDivisionList(HttpServletRequest request, HttpServletResponse response, CustomerAddDTO filterData) {
//        System.out.println("inside division list");
//        ModelAndView mv = null;
//        String customAdd = "";
//        HttpSession session = request.getSession();
//        final WebApplicationContext ctx = getWebApplicationContext();
//        CustomerServiceImpl customerService = (CustomerServiceImpl) ctx.getBean("CustomerService");
////        filterData.setCustomerId(request.getParameter("customerId"));
//        filterData.setParentId(Integer.parseInt(request.getParameter("parentId")));
//        System.out.println(request.getParameter("parentId")+"parent id>>>>>>");
//        try {
//            List<CustomerDataDTO> salesPersonIdList = new ArrayList();
//            salesPersonIdList = customerService.getSalesPersonRefId();
//            String salesPersonNameList = "";
//            for (int k = 0; k < 1; k++) {
//                salesPersonNameList = salesPersonIdList.get(k).getSalesPersonRef();
//            }
//            String salesPersonName = "";
//            if (!salesPersonNameList.isEmpty()) {
//                String nameList[];
//                nameList = salesPersonNameList.split(",");
//
//                for (int j = 0; j < nameList.length; j++) {
//
//                    salesPersonName = salesPersonName.concat(nameList[j]);
//                    if (j < nameList.length - 1) {
//                        salesPersonName = salesPersonName + ",";
//                    }
//                }
//                filterData.setSalesPersonRefId(salesPersonName);
//            }
//            List<CustomerAddDTO> selectedCustomerDivision = customerService.fetchSelectedCustomerDivisions(filterData);
//            mv = new ModelAndView("/customerDivisionList");
//            mv.addObject("customerData", selectedCustomerDivision);
//            System.out.println("customer Name >>>>>>>>>>>>"+selectedCustomerDivision.get(0).getCustomerName()+"id>>>>>>>>"+selectedCustomerDivision.get(0).getCustomerID());
//            mv.addObject("customerID",selectedCustomerDivision.get(0).getCustomerID());
//            mv.addObject("parentId",request.getParameter("parentId"));
//            customAdd = request.getParameter("customAdd");
//            
//            if(customAdd != null || customAdd != ""){
//                if( customAdd.equals("1")){
//                    mv.addObject("Success_message","Customer Added Successfully");
//                    mv.addObject("customAdd","0");
//                }else{
//                    mv.addObject("Success_message","");
//                    mv.addObject("customAdd","0");
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return mv;
//    }
//    
//    
//    public ModelAndView customerAddDivision(HttpServletRequest request, HttpServletResponse response, CustomerAddDTO filterData) {
//        System.out.println("inside division list");
//        ModelAndView mv = null;
//        String customAdd ="";
//        HttpSession session = request.getSession();
//        final WebApplicationContext ctx = getWebApplicationContext();
//        CustomerServiceImpl customerService = (CustomerServiceImpl) ctx.getBean("CustomerService");
////        filterData.setCustomerID(request.getParameter("customerId"));
//        if(request.getParameter("customerId") == null){
//            //filterData.setCustomerID(request.getParameter("parentId"));
//            filterData.setParentId(Integer.parseInt(request.getParameter("parentId")));
//            System.out.println("hhhhhhhh"+filterData.getParentId());
//        }
////        filterData.setParentId(Integer.parseInt(request.getParameter("parentId")));
////        System.out.println("hhhhhhhhhhhhhhlll"+request.getParameter("parentId")+"ddddddddddd"+request.getParameter("customerId"));
//        
//        try {
//            List<CustomerDataDTO> salesPersonIdList = new ArrayList();
//            salesPersonIdList = customerService.getSalesPersonRefId();
//            String salesPersonNameList = "";
//            for (int k = 0; k < 1; k++) {
//                salesPersonNameList = salesPersonIdList.get(k).getSalesPersonRef();
//            }
//            String salesPersonName = "";
//            if (!salesPersonNameList.isEmpty()) {
//                String nameList[];
//                nameList = salesPersonNameList.split(",");
//
//                for (int j = 0; j < nameList.length; j++) {
//
//                    salesPersonName = salesPersonName.concat(nameList[j]);
//                    if (j < nameList.length - 1) {
//                        salesPersonName = salesPersonName + ",";
//                    }
//                }
//                filterData.setSalesPersonRefId(salesPersonName);
//            }
//            List<CustomerAddDTO> salesPerson = customerService.getSalesPersonRef(filterData);
//            List<CustomerAddDTO> attachmentType = customerService.getConfigValueData(CommonConfigurations.attachmentType);
//            //List<CustomerAddDTO> selectedCustomer = customerService.fetchSelectedCustomer(filterData);
//            List<CustomerAddDTO> selectedCustomer = customerService.fetchSelectedParent(filterData);
//            
//            List<CustomerAddDTO> countryList = customerService.getCountryList();
//            List<CustomerAddDTO> regionList = customerService.getRegionList(filterData);
//            List<CustomerAddDTO> cityList = customerService.getCityList(filterData);
//            selectedCustomer.get(0).setFileinsertId(selectedCustomer.get(0).getAttchedFileName());
//            String splitfile = null;
//            splitfile = selectedCustomer.get(0).getFileName();
//            System.out.println("hhhhhhhhhh spli file>>>>"+splitfile);
//            String file_name[] = splitfile.split("~");
//            selectedCustomer.get(0).setAttchedFileName(file_name[2]);
//            mv = new ModelAndView("/customerAddDivision");
//            mv.addObject("customerData", selectedCustomer);
//            mv.addObject("salesPerson", salesPerson);
//            mv.addObject("attachmentType", attachmentType);
//            mv.addObject("countryList", countryList);
//            mv.addObject("regionList", regionList);
//            mv.addObject("cityList", cityList);
//            mv.addObject("parentId",filterData.getParentId());
//            customAdd = request.getParameter("customAdd");
//            if(customAdd!= null){
//                if( customAdd.equals("1")){
//                    mv.addObject("Success_message","Customer Division Added Successfully");
//                    mv.addObject("customAdd","0");
//                }else{
//                    mv.addObject("Success_message","Customer Division not Added");
//                    mv.addObject("customAdd","0");
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return mv;
//    }
//            
    
    public ModelAndView customerView(HttpServletRequest request, HttpServletResponse response, CustomerAddDTO filterData) {
        ModelAndView mv = null;
        HttpSession session = request.getSession();
        final WebApplicationContext ctx = getWebApplicationContext();
        CustomerServiceImpl customerService = (CustomerServiceImpl) ctx.getBean("CustomerService");
        filterData.setCustomerID(request.getParameter("customerID"));
        String editOption = request.getParameter("editId");
        try {
            //List<CustomerDataDTO> customerGroup = customerService.getCustomerGroup();
            List<CustomerDataDTO> salesPersonIdList = new ArrayList();
            salesPersonIdList = customerService.getSalesPersonRefId();
            String salesPersonNameList = "";
            for (int k = 0; k < 1; k++) {
                salesPersonNameList = salesPersonIdList.get(k).getSalesPersonRef();
            }
            String salesPersonName = "";
            if (!salesPersonNameList.isEmpty()) {
                String nameList[];
                nameList = salesPersonNameList.split(",");

                for (int j = 0; j < nameList.length; j++) {

                    salesPersonName = salesPersonName.concat(nameList[j]);
                    if (j < nameList.length - 1) {
                        salesPersonName = salesPersonName + ",";
                    }
                }
                filterData.setSalesPersonRefId(salesPersonName);
            }
            List<CustomerAddDTO> selectedCustomerData = customerService.fetchSelectedCustomer(filterData);
            String splitfile = null;
            if(selectedCustomerData.get(0).getFileName() != null){
                splitfile = selectedCustomerData.get(0).getFileName();
                String file_name[] = splitfile.split("~");
                selectedCustomerData.get(0).setAttchedFileName(file_name[2]);
            }
            
            List<CustomerAddDTO> selectedBillingAddress = customerService.fetchBillingAddress(filterData);
            List<CustomerAddDTO> selectedCustomerWorkLocations = customerService.fetchCustomerWorkLocations(filterData);
            List<CustomerAddDTO> selectedCustomerContactDetails = customerService.fetchCustomerContactDetails(filterData);
            List<CustomerAddDTO> selectedFianceContactDetails = customerService.fetchCustomerFinanceContactDetails(filterData);
            List<CustomerAddDTO> selectedDunningContactDetails = customerService.fetchCustomerDunningContactDetails(filterData);
            mv = new ModelAndView("/customerView");
            mv.addObject("selectedBillingAddress", selectedBillingAddress);
            mv.addObject("selectedCustomerWorkLocations", selectedCustomerWorkLocations);
            mv.addObject("selectedCustomerContactDetails", selectedCustomerContactDetails);
            mv.addObject("selectedFianceContactDetails", selectedFianceContactDetails);
            mv.addObject("selectedDunningContactDetails", selectedDunningContactDetails);
            mv.addObject("selectedCustomerData", selectedCustomerData);
            //mv.addObject("customerGroup", customerGroup);
            if(editOption.equals("0")){
                mv.addObject("editOption", "0");
            }else if(editOption.equals("1")){
                mv.addObject("editOption", "1");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }

    public ModelAndView customersAdd(HttpServletRequest request, HttpServletResponse response, CustomerAddDTO filterData) throws Exception {
        ModelAndView mv = null;
        String employeeId = null;
        HttpSession session = request.getSession();
        if (session.getAttribute("EMP_ID") == null) {
            employeeId = request.getParameter("empid");
            session.setAttribute("EMP_ID", employeeId);
        } else {
            employeeId = (String) session.getAttribute("EMP_ID");
        }
        final WebApplicationContext ctx = getWebApplicationContext();
        CustomerServiceImpl customerService = (CustomerServiceImpl) ctx.getBean("CustomerService");

        List<CustomerDataDTO> salesPersonIdList = new ArrayList();
        salesPersonIdList = customerService.getSalesPersonRefId();
        String salesPersonNameList = "";
        for (int k = 0; k < 1; k++) {
            salesPersonNameList = salesPersonIdList.get(k).getSalesPersonRef();
        }
        String salesPersonName = "";
        if (!salesPersonNameList.isEmpty()) {
            String nameList[];
            nameList = salesPersonNameList.split(",");

            for (int j = 0; j < nameList.length; j++) {

                salesPersonName = salesPersonName.concat(nameList[j]);
                if (j < nameList.length - 1) {
                    salesPersonName = salesPersonName + ",";
                }
            }
            filterData.setSalesPersonRefId(salesPersonName);

        }
        List<CustomerAddDTO> salesPerson = customerService.getSalesPersonRef(filterData);
        List<CustomerAddDTO> countryList = customerService.getCountryList();
        List<CustomerAddDTO> invoiceList = customerService.getInvoiceList(); 
        List<CustomerAddDTO> attachmentType = customerService.getConfigValueData(CommonConfigurations.attachmentType);
        int salesPersonHit=0;
        mv = new ModelAndView("/customersAdd");
        for(int i=0;i<salesPerson.size();i++){
            if(employeeId.equals(salesPerson.get(i).getSalesManId())){
                List<CustomerAddDTO> salesPersonList = new ArrayList<CustomerAddDTO>();
                salesPersonList.add(salesPerson.get(i));
                mv.addObject("salesPerson", salesPersonList);
                salesPersonHit++;
            }
        }
        if(salesPersonHit==0){
            mv.addObject("salesPerson", salesPerson);
        }
        mv.addObject("attachmentType", attachmentType);
        mv.addObject("countryList", countryList);
         mv.addObject("invoiceList", invoiceList);
        try {
            ModelAndView regionList = this.getRegionList(request, response, filterData);
            mv.addObject("regionList", regionList);
            ModelAndView cityList = this.getCityList(request, response, filterData);
            mv.addObject("cityList", cityList);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }

    public ModelAndView getRegionList(HttpServletRequest request, HttpServletResponse response, CustomerAddDTO filterData) throws Exception {
        final WebApplicationContext ctx = getWebApplicationContext();
        CustomerServiceImpl customerService = (CustomerServiceImpl) ctx.getBean("CustomerService");
        filterData.setCountryFilter(request.getParameter("countryId"));
        List<CustomerAddDTO> regionList = customerService.getRegionList(filterData);
        ModelAndView mv = new ModelAndView("/getRegionList");
        mv.addObject("regionList", regionList);
        mv.addObject("id", request.getParameter("id"));
        return mv;
    }

    public ModelAndView getCityList(HttpServletRequest request, HttpServletResponse response, CustomerAddDTO filterData) throws Exception {
        final WebApplicationContext ctx = getWebApplicationContext();
        CustomerServiceImpl customerService = (CustomerServiceImpl) ctx.getBean("CustomerService");
        filterData.setCountryFilter(request.getParameter("countryId"));
        filterData.setRegionFilter(request.getParameter("regionId"));
        List<CustomerAddDTO> cityList = customerService.getCityList(filterData);
        ModelAndView mv = new ModelAndView("/getCityList");
        mv.addObject("cityList", cityList);
        mv.addObject("id", request.getParameter("id"));
        return mv;
    }
    
    public ModelAndView getGstCode(HttpServletRequest request, HttpServletResponse response, CustomerAddDTO filterData) throws Exception {
        final WebApplicationContext ctx = getWebApplicationContext();
        CustomerServiceImpl customerService = (CustomerServiceImpl) ctx.getBean("CustomerService");
        filterData.setRegionFilter(request.getParameter("regionId"));
        String gstCode = customerService.getGstCode(filterData);
        response.getWriter().println(gstCode);
        return null;
    }
    
    public ModelAndView getSalesPersonPhoneNumber(HttpServletRequest request, HttpServletResponse response, CustomerAddDTO filterData) throws Exception {
        final WebApplicationContext ctx = getWebApplicationContext();
        CustomerServiceImpl customerService = (CustomerServiceImpl) ctx.getBean("CustomerService");
        filterData.setSalesPersonRefId(request.getParameter("id"));
        String phoneNumber = customerService.getSalesPersonPhoneNumber(filterData);
        if(phoneNumber == null || phoneNumber == ""){
            phoneNumber="";
        }
        response.getWriter().println(phoneNumber);
        return null;
    }
    
    
    public ModelAndView customerProjectMapping(HttpServletRequest request, HttpServletResponse response, CustomerDataDTO filterData) throws Exception {
        final WebApplicationContext ctx = getWebApplicationContext();
        CustomerServiceImpl customerService = (CustomerServiceImpl) ctx.getBean("CustomerService");
        filterData.setCustID(request.getParameter("custID"));
        List<CustomerDataDTO> projectList = customerService.customerProjectMapping(filterData);
        if(projectList.size()==0){
            response.getWriter().println("0");
        }else{
            String printTable = "<tr><th>Project Name</th><th>Status</th></tr>";
            response.getWriter().println();
            for(int i=0;i<projectList.size();i++){
                printTable += "<tr><td>" + projectList.get(i).getProjectName() + "</td><td>" + projectList.get(i).getProjectStatus() + "</td></tr>";
            }
            System.out.println("table"+printTable);
            response.getWriter().println(printTable);
        }
        
        return null;
//        return mv;
    }
    
    
    
//    public ModelAndView getCityListByCountry(HttpServletRequest request, HttpServletResponse response, CustomerAddDTO filterData) throws Exception {        
//        final WebApplicationContext ctx = getWebApplicationContext();
//        CustomerServiceImpl customerService=(CustomerServiceImpl) ctx.getBean("CustomerService");
//        filterData.setCountryFilter(request.getParameter("countryId"));
//        response.getWriter().println("<option value=''>" + "-- Select City --"+ "</option>");
//        for( CustomerAddDTO dto : (customerService.getCityList(filterData))){
//            response.getWriter().println("<option value='" + dto.getCityID()+ "'>" + dto.getCityName() + "</option>");            
//        }
//        ModelAndView mv = new ModelAndView("/getCityList");
//        mv.addObject("cityList",cityList);
//        return null;
//    }

    public ModelAndView getsubRBUList(HttpServletRequest request, HttpServletResponse response, CustomerAddDTO filterData) throws Exception {
        final WebApplicationContext ctx = getWebApplicationContext();
        CustomerServiceImpl customerService = (CustomerServiceImpl) ctx.getBean("CustomerService");
        filterData.setRBUFilter(request.getParameter("RBU"));
        System.out.println(": RBU ID :" + (request.getParameter("RBU")) + ": RNU UUU:" + filterData.getRBUFilter());
        List<CustomerAddDTO> subRBUList = customerService.getsubRBUList(filterData);
        ModelAndView mv = new ModelAndView("/getsubRBUList");
        mv.addObject("subRBUList", subRBUList);
        return mv;
    }

    public ModelAndView customersEdit(HttpServletRequest request, HttpServletResponse response, CustomerAddDTO filterData) throws Exception {
        ModelAndView mv = null;
        String employeeId=null;       
        HttpSession session = request.getSession();
        String customAdd ="";
        if (session.getAttribute("EMP_ID") == null) {
            employeeId = request.getParameter("empid");
            session.setAttribute("EMP_ID", employeeId);
        } else {
            employeeId = (String) session.getAttribute("EMP_ID");
        }
        final WebApplicationContext ctx = getWebApplicationContext();
        CustomerServiceImpl customerService = (CustomerServiceImpl) ctx.getBean("CustomerService");
        filterData.setCustomerID(request.getParameter("customerId"));
        try {
            List<CustomerDataDTO> customerGroup = customerService.getCustomerGroup();
            List<CustomerDataDTO> salesPersonIdList = new ArrayList();
            salesPersonIdList = customerService.getSalesPersonRefId();
            String salesPersonNameList = "";
            for (int k = 0; k < 1; k++) {
                salesPersonNameList = salesPersonIdList.get(k).getSalesPersonRef();
            }
            String salesPersonName = "";
            if (!salesPersonNameList.isEmpty()) {
                String nameList[];
                nameList = salesPersonNameList.split(",");

                for (int j = 0; j < nameList.length; j++) {

                    salesPersonName = salesPersonName.concat(nameList[j]);
                    if (j < nameList.length - 1) {
                        salesPersonName = salesPersonName + ",";
                    }
                }
                filterData.setSalesPersonRefId(salesPersonName);

            }
            List<CustomerAddDTO> countryList = customerService.getCountryList();
            List<CustomerAddDTO> salesPerson = customerService.getSalesPersonRef(filterData);
            List<CustomerAddDTO> attachmentType = customerService.getConfigValueData(CommonConfigurations.attachmentType);
            List<CustomerAddDTO> selectedCustomerData = customerService.fetchSelectedCustomer(filterData);
            List<CustomerAddDTO> invoiceList = customerService.getInvoiceList(); 
            selectedCustomerData.get(0).setFileinsertId(selectedCustomerData.get(0).getAttchedFileName());
            String splitfile = null;
            if(selectedCustomerData.get(0).getFileName() != null){
                splitfile = selectedCustomerData.get(0).getFileName();
                String file_name[] = splitfile.split("~");
                selectedCustomerData.get(0).setAttchedFileName(file_name[2]);
            }                      
            
            List<CustomerAddDTO> selectedBillingAddress = customerService.fetchBillingAddress(filterData);
            List<CustomerAddDTO> selectedCustomerWorkLocations = customerService.fetchCustomerWorkLocations(filterData);
            List<CustomerAddDTO> selectedCustomerContactDetails = customerService.fetchCustomerContactDetails(filterData);
            List<CustomerAddDTO> selectedFianceContactDetails = customerService.fetchCustomerFinanceContactDetails(filterData);
            List<CustomerAddDTO> selectedDunningContactDetails = customerService.fetchCustomerDunningContactDetails(filterData);
            List<CustomerAddDTO> selectedWorkLocationShortCode = customerService.fetchWorkLocationShortCode(filterData);
            List<CustomerAddDTO> selectedBillingShortCode = customerService.fetchBillingShortCode(filterData);
            mv = new ModelAndView("/customersEdit");
            int salesPersonHit=0;
            for(int i=0;i<salesPerson.size();i++){
                if(employeeId.equals(salesPerson.get(i).getSalesManId())){
                    List<CustomerAddDTO> salesPersonList = new ArrayList<CustomerAddDTO>();
                    salesPersonList.add(salesPerson.get(i));
                    mv.addObject("salesPerson", salesPersonList);
                    salesPersonHit++;
                }
            
            }
            if(salesPersonHit==0){
                mv.addObject("salesPerson", salesPerson);
            }
            mv.addObject("selectedBillingAddress", selectedBillingAddress);
            mv.addObject("selectedCustomerWorkLocations", selectedCustomerWorkLocations);
            mv.addObject("selectedCustomerContactDetails", selectedCustomerContactDetails);
            mv.addObject("selectedFianceContactDetails", selectedFianceContactDetails);
            mv.addObject("selectedDunningContactDetails", selectedDunningContactDetails);
            mv.addObject("selectedCustomerData", selectedCustomerData);
            mv.addObject("customerGroup", customerGroup);
            mv.addObject("workLocationShortCode",selectedWorkLocationShortCode);
            mv.addObject("billingShortCode",selectedBillingShortCode);
            mv.addObject("countryList", countryList);
            mv.addObject("attachmentType", attachmentType);
            mv.addObject("invoiceList", invoiceList);                      
            List<CustomerAddDTO> regionList = customerService.getRegionList(filterData);
            mv.addObject("regionList", regionList);
            List<CustomerAddDTO> cityList = customerService.getCityList(filterData);
            mv.addObject("cityList", cityList);
            customAdd = request.getParameter("customEdit");
            if(customAdd!= null){
                if( customAdd.equals("1")){
                    mv.addObject("Success_message","Please make any change and submit the customer");
                    mv.addObject("customEdit","0");
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }

    public ModelAndView customersApprovalList(HttpServletRequest request, HttpServletResponse response, CustomerDataDTO filterData) throws Exception {
        Config t = new Config();
        t.getParentId("mail_details");
        ModelAndView mv = null;
        HttpSession session = request.getSession();
        String employeeId = null;
        //String selectedMonth,selectedYear,;

        Calendar calndr = Calendar.getInstance();
        calndr.get(Calendar.MONTH);
        calndr.get(Calendar.YEAR);

        if (session.getAttribute("EMP_ID") == null) {
            employeeId = request.getParameter("empid");
            session.setAttribute("EMP_ID", employeeId);
        } else {
            employeeId = (String) session.getAttribute("EMP_ID");
        }
        Calendar calendar = Calendar.getInstance();
        try {
            final WebApplicationContext ctx = getWebApplicationContext();
            CustomerServiceImpl customerService = (CustomerServiceImpl) ctx.getBean("CustomerService");
            List<CustomerDataDTO> customerData = customerService.fetchCustomerApproveData(filterData);

            mv = new ModelAndView("/customersApprovalList");
            mv.addObject("customerData", customerData);
            String custStatus = request.getParameter("cust_data");
            if(custStatus!=null){
                if(custStatus.equals("1")){
                    mv.addObject("statusmessage", "Customer Approved Successfully");
                }
                else if(custStatus.equals("2")){
                    mv.addObject("statusmessage", "Customer Details Sent back");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }

    public ModelAndView customersRMGApprovalList(HttpServletRequest request, HttpServletResponse response, CustomerDataDTO filterData) throws Exception {
        Config t = new Config();
        System.out.println("rmgApproval customer List entry >>>>>>>>>>>>>>>>>");
        t.getParentId("mail_details");
        ModelAndView mv = null;
        HttpSession session = request.getSession();
        String employeeId = null;
        //String selectedMonth,selectedYear,;

        Calendar calndr = Calendar.getInstance();
        calndr.get(Calendar.MONTH);
        calndr.get(Calendar.YEAR);

        if (session.getAttribute("EMP_ID") == null) {
            employeeId = request.getParameter("empid");
            session.setAttribute("EMP_ID", employeeId);
        } else {
            employeeId = (String) session.getAttribute("EMP_ID");
        }
        Calendar calendar = Calendar.getInstance();
        try {
            final WebApplicationContext ctx = getWebApplicationContext();
            CustomerServiceImpl customerService = (CustomerServiceImpl) ctx.getBean("CustomerService");
            List<CustomerDataDTO> customerData = customerService.fetchCustomerData(filterData);

            Map<String, String> monthsMap = CommonMethods.getMonthsList();
            Map<Integer, Integer> yearsMap = CommonMethods.getYearsList(2);
            Map<String, String> pjctStatsMap = CommonMethods.getPjtStatusList();
            System.out.println("rmgApproval customer List>>>>>>>>>>>>>>>>>");
            mv = new ModelAndView("/customersRMGApprovalList");
            mv.addObject("monthsMap", monthsMap);
            mv.addObject("yearsMap", yearsMap);
            mv.addObject("pjctStatsMap", pjctStatsMap);
            mv.addObject("customerData", customerData);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }

    public ModelAndView rmgApproveCustomer(HttpServletRequest request, HttpServletResponse response, CustomerAddDTO filterData) throws Exception {
        ModelAndView mv = null;
        HttpSession session = request.getSession();

        final WebApplicationContext ctx = getWebApplicationContext();
        CustomerServiceImpl customerService = (CustomerServiceImpl) ctx.getBean("CustomerService");
        filterData.setCustomerID(request.getParameter("customerId"));

        try {
            List<CustomerDataDTO> customerGroup = customerService.getCustomerGroup();
            List<CustomerDataDTO> salesPersonIdList = new ArrayList();
            salesPersonIdList = customerService.getSalesPersonRefId();
            String salesPersonNameList = "";
            for (int k = 0; k < 1; k++) {
                salesPersonNameList = salesPersonIdList.get(k).getSalesPersonRef();
            }
            String salesPersonName = "";
            if (!salesPersonNameList.isEmpty()) {
                String nameList[];
                nameList = salesPersonNameList.split(",");

                for (int j = 0; j < nameList.length; j++) {

                    salesPersonName = salesPersonName.concat(nameList[j]);
                    if (j < nameList.length - 1) {
                        salesPersonName = salesPersonName + ",";
                    }
                }
                filterData.setSalesPersonRefId(salesPersonName);

            }
            List<CustomerAddDTO> salesPerson = customerService.getSalesPersonRef(filterData);
            List<CustomerAddDTO> currencyList = customerService.getCurrencyList();
            List<CustomerAddDTO> businessLeader = customerService.getBusinessLeader();
            List<CustomerAddDTO> rbuList = customerService.getRBUList();
            List<CustomerAddDTO> sbuList = customerService.getSBUList();
            List<CustomerAddDTO> countryList = customerService.getCountryList();
            List<CustomerAddDTO> selectedCustomerData = customerService.fetchSelectedCustomer(filterData);
            List<CustomerAddDTO> invoiceCodeList = customerService.fetchInvoiceCode(filterData);
            if (selectedCustomerData.size() != 0) {
                filterData.setCountryFilter(selectedCustomerData.get(0).getCountry());
                filterData.setRegionFilter(selectedCustomerData.get(0).getRegion());
            }

            mv = new ModelAndView("/rmgApproveCustomer");
            mv.addObject("selectedCustomerData", selectedCustomerData);
            mv.addObject("customerGroup", customerGroup);
            mv.addObject("invoiceCodeList", invoiceCodeList);
            mv.addObject("salesPerson", salesPerson);
            mv.addObject("currencyList", currencyList);
            mv.addObject("businessLeader", businessLeader);
            mv.addObject("rbuList", rbuList);
            mv.addObject("sbuList", sbuList);
            List<CustomerAddDTO> customerCategory = customerService.getConfigValueData(CommonConfigurations.customerCategory);
            mv.addObject("customerCategory", customerCategory);
            mv.addObject("countryList", countryList);
            List<CustomerAddDTO> regionList = customerService.getRegionList(filterData);
            mv.addObject("regionList", regionList);
            List<CustomerAddDTO> cityList = customerService.getCityList(filterData);
            mv.addObject("cityList", cityList);
            if (selectedCustomerData.size() != 0) {
                filterData.setRBUFilter(selectedCustomerData.get(0).getRBU());
            }
            List<CustomerAddDTO> subRBUList = customerService.getsubRBUList(filterData);
            mv.addObject("subRBUList", subRBUList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }

    public ModelAndView approveCustomer(HttpServletRequest request, HttpServletResponse response, CustomerAddDTO filterData) throws Exception {
        ModelAndView mv = null;
        HttpSession session = request.getSession();

        final WebApplicationContext ctx = getWebApplicationContext();
        CustomerServiceImpl customerService = (CustomerServiceImpl) ctx.getBean("CustomerService");
        filterData.setCustomerID(request.getParameter("customerId"));

        try {
            List<CustomerDataDTO> salesPersonIdList = new ArrayList();
            salesPersonIdList = customerService.getSalesPersonRefId();
            String salesPersonNameList = "";
            for (int k = 0; k < 1; k++) {
                salesPersonNameList = salesPersonIdList.get(k).getSalesPersonRef();
            }
            String salesPersonName = "";
            if (!salesPersonNameList.isEmpty()) {
                String nameList[];
                nameList = salesPersonNameList.split(",");

                for (int j = 0; j < nameList.length; j++) {

                    salesPersonName = salesPersonName.concat(nameList[j]);
                    if (j < nameList.length - 1) {
                        salesPersonName = salesPersonName + ",";
                    }
                }
                filterData.setSalesPersonRefId(salesPersonName);

            }
            List<CustomerAddDTO> selectedCustomerData = customerService.fetchSelectedCustomer(filterData);
            String splitfile = null;
            if(selectedCustomerData.get(0).getFileName()!=null){
                splitfile = selectedCustomerData.get(0).getFileName();
                String file_name[] = splitfile.split("~");
                selectedCustomerData.get(0).setAttchedFileName(file_name[2]);
            }
            
            List<CustomerAddDTO> selectedBillingAddress = customerService.fetchBillingAddress(filterData);
            List<CustomerAddDTO> selectedCustomerWorkLocations = customerService.fetchCustomerWorkLocations(filterData);
            List<CustomerAddDTO> selectedCustomerContactDetails = customerService.fetchCustomerContactDetails(filterData);
            List<CustomerAddDTO> selectedFianceContactDetails = customerService.fetchCustomerFinanceContactDetails(filterData);

            mv = new ModelAndView("/approveCustomer");
            mv.addObject("selectedBillingAddress", selectedBillingAddress);
            mv.addObject("selectedCustomerWorkLocations", selectedCustomerWorkLocations);
            mv.addObject("selectedCustomerContactDetails", selectedCustomerContactDetails);
            mv.addObject("selectedFianceContactDetails", selectedFianceContactDetails);
            mv.addObject("selectedCustomerData", selectedCustomerData);
  
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }

    public ModelAndView saveCustomer(HttpServletRequest request, HttpServletResponse response, CustomerAddDTO customerFormData) throws Exception {
        ModelAndView mv = null;
        String lastInsertId = null;
        String lastFileInsertId = null;
        int referenceId = 0;
        HttpSession session = request.getSession();
        try {
            CustomerMappingDTO custMapFormData = new CustomerMappingDTO();
            final WebApplicationContext ctx = getWebApplicationContext();
            CustomerServiceImpl customerService = (CustomerServiceImpl) ctx.getBean("CustomerService");
            CustomerMappingServiceImpl custMapService = (CustomerMappingServiceImpl) ctx.getBean("CustomerMappingService");
            if (customerFormData.getCustomerID() != null && !customerFormData.getCustomerID().equals("") && customerFormData.getCustomerSubmitButton() != null) {
                custMapFormData.setDeleted(CommonConfigurations.DELETED_VALUE);
                custMapFormData.setCustomerId(customerFormData.getCustomerID());
                custMapService.updateCustMapetails(custMapFormData);
            }

            customerFormData.setStatus(request.getParameter("buttonStatus"));
            MultipartFile uploadedFile = customerFormData.getAttachmentValue();
            String attchedFileName = uploadedFile.getOriginalFilename();
            customerFormData.setAttchedFileName(attchedFileName);
            if(customerFormData.getStatus().equals("s")){
                if(customerFormData.getCustomerName()!= null){
                    if (uploadedFile != null) {
                        referenceId = Integer.parseInt(CommonConfigurations.customerModuleReferenceId);
                        lastFileInsertId = CommonMethods.fileUpload(uploadedFile, referenceId, customerFormData.getAttachmentType(), CommonConfigurations.customerModuleName, customerService);
                         if (customerFormData.getStatus().equals("m") || customerFormData.getStatus().equals("s")){
                             customerFormData.setAttchedFileName(lastFileInsertId);                        
                         }
                         if(lastFileInsertId==null || lastFileInsertId == ""){
                             customerFormData.setAttchedFileName(request.getParameter("attchedFileName"));  
                         }
                     }else{
                         customerFormData.setAttchedFileName(request.getParameter("attchedFileName"));                      
                     }
                    for(int i=0;i<customerFormData.getCustomerDivisionListName().length;i++){
                                                               
                            customerFormData.setCustomerDivision(customerFormData.getCustomerDivisionListName()[i]);
                            if(customerFormData.getCustomerDivisionListName()[i]!= null){
                                String invoice = request.getParameter("invoiceTo_"+(i+1));
                                customerFormData.setInvoiceTo(invoice);
                                lastInsertId = customerService.insertCustomerDetails(customerFormData);
                                
                                if(i== 0)
                                {
                                    customerFormData.setParentId(Integer.parseInt(lastInsertId));
                                }
                                customerService.updateParentId(customerFormData);
                            }
                            
                            int workLocationLength,billingLength, businessLength, financeLength, dunningLength = 0; 
                            
                            workLocationLength = Integer.parseInt(request.getParameter("workLocationCount_"+(i+1)));
                            String[] workLocationAddress = new String[workLocationLength];
                            String[] workLocationShortCode = new String[workLocationLength];
                            String[] workLocationCountry = new String[workLocationLength];
                            String[] workLocationRegion = new String[workLocationLength];
                            String[] workLocationCity = new String[workLocationLength];
                            String[] workLocationPincode = new String[workLocationLength];
                            String[] workLocationWorkingHrs = new String[workLocationLength];
                            String[] workLocationIsCompanyLocation = new String[workLocationLength];
                            for(int j=0;j<workLocationLength;j++){
                                workLocationAddress[j]=request.getParameter("customerWorkAddressNew_"+(i+1)+"_"+(j+1));
                                workLocationShortCode[j]=request.getParameter("customerWorkShortCodeNew_"+(i+1)+"_"+(j+1));
                                workLocationCountry[j] = request.getParameter("customerWorkCountryNew_"+(i+1)+"_"+(j+1));
                                workLocationRegion[j] = request.getParameter("customerWorkRegionNew_"+(i+1)+"_"+(j+1));
                                workLocationCity[j] = request.getParameter("customerWorkCityNew_"+(i+1)+"_"+(j+1));
                                workLocationPincode[j] = request.getParameter("customerWorkPincodeNew_"+(i+1)+"_"+(j+1));
                                workLocationWorkingHrs[j] = request.getParameter("workingHoursPerDayNew_"+(i+1)+"_"+(j+1));
                                workLocationIsCompanyLocation[j] = request.getParameter("workingLocationIsCompanyLocationNew_"+(i+1)+"_"+(j+1));
                            }
                            customerFormData.setCustomerWorkAddressNew(workLocationAddress);
                            customerFormData.setCustomerWorkShortCodeNew(workLocationShortCode);
                            customerFormData.setCustomerWorkCountryNew(workLocationCountry);
                            customerFormData.setCustomerWorkRegionNew(workLocationRegion);
                            customerFormData.setCustomerWorkCityNew(workLocationCity);
                            customerFormData.setCustomerWorkPincodeNew(workLocationPincode);
                            customerFormData.setWorkingHoursPerDayNew(workLocationWorkingHrs);
                            customerFormData.setWorkLocationIscompanyLocationNew(workLocationIsCompanyLocation);
                                    
                            billingLength = Integer.parseInt(request.getParameter("businessAddressCount_"+(i+1)));
                            String[] billingAddress = new String[billingLength];
                            String[] billingShortCode = new String[billingLength];
                            String[] billingCountry = new String[billingLength];
                            String[] billingRegion = new String[billingLength];
                            String[] billingCity = new String[billingLength];
                            String[] billingPincode = new String[billingLength];
                            String[] billingGstCode = new String[billingLength];
                            String[] billingGstNumber = new String[billingLength];
                            for(int j=0;j<billingLength;j++){
                                System.out.println("short code bill"+request.getParameter("customerBillingShortCodeNew_"+(i+1)+"_"+(j+1)));
                                billingAddress[j] = request.getParameter("customerBillingAddressNew_"+(i+1)+"_"+(j+1));
                                billingShortCode[j]= request.getParameter("customerBillingShortCodeNew_"+(i+1)+"_"+(j+1));
                                billingCountry[j] = request.getParameter("customerBillingCountryNew_"+(i+1)+"_"+(j+1));
                                billingRegion[j] = request.getParameter("customerBillingRegionNew_"+(i+1)+"_"+(j+1));
                                billingCity[j] = request.getParameter("customerBillingCityNew_"+(i+1)+"_"+(j+1));
                                billingPincode[j] = request.getParameter("customerBillingPincodeNew_"+(i+1)+"_"+(j+1));
                                billingGstCode[j] = request.getParameter("customerBillingGstCodeNew_"+(i+1)+"_"+(j+1));
                                billingGstNumber[j] = request.getParameter("customerBillingGstNumberNew_"+(i+1)+"_"+(j+1));
                            }
                            customerFormData.setCustomerBillingAddressNew(billingAddress);
                            customerFormData.setCustomerBillingShortCodeNew(billingShortCode);
                            customerFormData.setCustomerBillingCountryNew(billingCountry);
                            customerFormData.setCustomerBillingRegionNew(billingRegion);
                            customerFormData.setCustomerBillingCityNew(billingCity);
                            customerFormData.setCustomerBillingPincodeNew(billingPincode);
                            customerFormData.setCustomerBillingGstCodeNew(billingGstCode);
                            customerFormData.setCustomerBillingGstNumberNew(billingGstNumber);
                            
                            businessLength = Integer.parseInt(request.getParameter("businessContactCount_"+(i+1)));
                            String[] businessPerson = new String[businessLength];
                            String[] businessDesignation = new String[businessLength];
                            String[] businessPhone = new String[businessLength];
                            String[] businessEmail = new String[businessLength];
                            for(int j=0;j<businessLength;j++){
                                businessPerson[j] = request.getParameter("customerContactPersonNew_"+(i+1)+"_"+(j+1));
                                businessDesignation[j] = request.getParameter("customerDesignationNew_"+(i+1)+"_"+(j+1));
                                businessPhone[j] = request.getParameter("customerContactPhoneNew_"+(i+1)+"_"+(j+1));
                                businessEmail[j] = request.getParameter("customerContactEmailNew_"+(i+1)+"_"+(j+1));
                            }
                            customerFormData.setCustomerContactPersonNew(businessPerson);
                            customerFormData.setCustomerDesignationNew(businessDesignation);
                            customerFormData.setCustomerContactPhoneNew(businessPhone);
                            customerFormData.setCustomerContactEmailNew(businessEmail);
                            
                            financeLength = Integer.parseInt(request.getParameter("financeContactCount_"+(i+1)));
                            String[] financePerson = new String[financeLength];
                            String[] financeDesignation = new String[financeLength];
                            String[] financePhone = new String[financeLength];
                            String[] financeEmail = new String[financeLength];
                            for(int j=0;j<financeLength;j++){
                                financePerson[j] = request.getParameter("customerFinanceContactPersonNew_"+(i+1)+"_"+(j+1));
                                financeDesignation[j] = request.getParameter("customerFinanceDesignationNew_"+(i+1)+"_"+(j+1));
                                financePhone[j] = request.getParameter("customerFinanceContactPhoneNew_"+(i+1)+"_"+(j+1));
                                financeEmail[j] = request.getParameter("customerFinanceContactEmailNew_"+(i+1)+"_"+(j+1));
                            }
                            customerFormData.setCustomerFinanceContactPersonNew(financePerson);
                            customerFormData.setCustomerFinanceDesignationNew(financeDesignation);
                            customerFormData.setCustomerFinanceContactPhoneNew(financePhone);
                            customerFormData.setCustomerFinanceContactEmailNew(financeEmail);
                            customerFormData.setCustomerID(lastInsertId);
                            //dunning contact
                            dunningLength = Integer.parseInt(request.getParameter("dunningContactCount_"+(i+1)));
                            String[] dunningPerson = new String[dunningLength];
                            String[] dunningDesignation = new String[dunningLength];
                            String[] dunningPhone = new String[dunningLength];
                            String[] dunningEmail = new String[dunningLength];
                            for(int j=0;j<financeLength;j++){
                                dunningPerson[j] = request.getParameter("customerDunningContactPersonNew_"+(i+1)+"_"+(j+1));
                                dunningDesignation[j] = request.getParameter("customerDunningDesignationNew_"+(i+1)+"_"+(j+1));
                                dunningPhone[j] = request.getParameter("customerDunningContactPhoneNew_"+(i+1)+"_"+(j+1));
                                dunningEmail[j] = request.getParameter("customerDunningContactEmailNew_"+(i+1)+"_"+(j+1));
                            }
                            customerFormData.setCustomerDunningContactPersonNew(dunningPerson);
                            customerFormData.setCustomerDunningDesignationNew(dunningDesignation);
                            customerFormData.setCustomerDunningContactPhoneNew(dunningPhone);
                            customerFormData.setCustomerDunningContactEmailNew(dunningEmail);
                            customerFormData.setCustomerID(lastInsertId);
                            
                            customerFormData.setInvoiceTo(request.getParameter("invoiceEmailedTo"));
                            customerService.addCustomerDetails(customerFormData);
                        }
                   
                   mv = new ModelAndView("redirect:customersList.htm");
                   mv.addObject("customAdd", "2");

               } else {
                   mv = new ModelAndView("redirect:customersList.htm");
                   mv.addObject("customAdd", "0");

               }
                
            }else if(customerFormData.getStatus().equals("m")){
                    if(customerFormData.getCustomerName()!= null 
                       && customerFormData.getSalesPerson()!= null 
                       && customerFormData.getAttachmentValue()!= null 
                       && customerFormData.getSalesPersonContactNo()!= null 
                       && customerFormData.getAttachmentType()!= null){
                       if (uploadedFile != null) {
                           referenceId = Integer.parseInt(CommonConfigurations.customerModuleReferenceId);
                           lastFileInsertId = CommonMethods.fileUpload(uploadedFile, referenceId, customerFormData.getAttachmentType(), CommonConfigurations.customerModuleName, customerService);
                            if (customerFormData.getStatus().equals("m") || customerFormData.getStatus().equals("s")){
                                customerFormData.setAttchedFileName(lastFileInsertId);                        
                            }
                            if(lastFileInsertId==null || lastFileInsertId == ""){
                                customerFormData.setAttchedFileName(request.getParameter("attchedFileName"));  
                            }
                        }else{
                            customerFormData.setAttchedFileName(request.getParameter("attchedFileName"));                      
                        }
                       /*** Multiple division inserting ***/
                        for(int i=0;i<customerFormData.getCustomerDivisionListName().length;i++){
                                                               
                            customerFormData.setCustomerDivision(customerFormData.getCustomerDivisionListName()[i]);
                            if(customerFormData.getCustomerDivisionListName()[i]!= null){
                                 String invoice = request.getParameter("invoiceTo_"+(i+1));
                                customerFormData.setInvoiceTo(invoice);
                                lastInsertId = customerService.insertCustomerDetails(customerFormData);
                                
                                if(i== 0)
                                {
                                    customerFormData.setParentId(Integer.parseInt(lastInsertId));
                                }
                                customerService.updateParentId(customerFormData);
                            }
                            
                            int workLocationLength,billingLength, businessLength, financeLength,dunningLength = 0; 
                            
                            workLocationLength = Integer.parseInt(request.getParameter("workLocationCount_"+(i+1)));
                            String[] workLocationAddress = new String[workLocationLength];
                            String[] workLocationShortCode = new String[workLocationLength];
                            String[] workLocationCountry = new String[workLocationLength];
                            String[] workLocationRegion = new String[workLocationLength];
                            String[] workLocationCity = new String[workLocationLength];
                            String[] workLocationPincode = new String[workLocationLength];
                            String[] workLocationWorkingHrs = new String[workLocationLength];
                            String[] workLocationIsCompanyLocation = new String[workLocationLength];
                            for(int j=0;j<workLocationLength;j++){
                                
                                workLocationAddress[j]=request.getParameter("customerWorkAddressNew_"+(i+1)+"_"+(j+1));
                                workLocationShortCode[j]=request.getParameter("customerWorkShortCodeNew_"+(i+1)+"_"+(j+1));
                                workLocationCountry[j] = request.getParameter("customerWorkCountryNew_"+(i+1)+"_"+(j+1));
                                workLocationRegion[j] = request.getParameter("customerWorkRegionNew_"+(i+1)+"_"+(j+1));
                                workLocationCity[j] = request.getParameter("customerWorkCityNew_"+(i+1)+"_"+(j+1));
                                workLocationPincode[j] = request.getParameter("customerWorkPincodeNew_"+(i+1)+"_"+(j+1));
                                workLocationWorkingHrs[j] = request.getParameter("workingHoursPerDayNew_"+(i+1)+"_"+(j+1));
                                workLocationIsCompanyLocation[j] = request.getParameter("workingLocationIsCompanyLocationNew_"+(i+1)+"_"+(j+1));
                            }
                            customerFormData.setCustomerWorkAddressNew(workLocationAddress);
                            customerFormData.setCustomerWorkShortCodeNew(workLocationShortCode);
                            customerFormData.setCustomerWorkCountryNew(workLocationCountry);
                            customerFormData.setCustomerWorkRegionNew(workLocationRegion);
                            customerFormData.setCustomerWorkCityNew(workLocationCity);
                            customerFormData.setCustomerWorkPincodeNew(workLocationPincode);
                            customerFormData.setWorkingHoursPerDayNew(workLocationWorkingHrs);
                            customerFormData.setWorkLocationIscompanyLocationNew(workLocationIsCompanyLocation);
                            
                            billingLength = Integer.parseInt(request.getParameter("businessAddressCount_"+(i+1)));
                            String[] billingAddress = new String[billingLength];
                            String[] billingShortCode = new String[billingLength];
                            String[] billingCountry = new String[billingLength];
                            String[] billingRegion = new String[billingLength];
                            String[] billingCity = new String[billingLength];
                            String[] billingPincode = new String[billingLength];
                            String[] billingGstCode = new String[billingLength];
                            String[] billingGstNumber = new String[billingLength];
                            for(int j=0;j<billingLength;j++){
                                billingAddress[j] = request.getParameter("customerBillingAddressNew_"+(i+1)+"_"+(j+1));
                                billingShortCode[j]= request.getParameter("customerBillingShortCodeNew_"+(i+1)+"_"+(j+1));
                                billingCountry[j] = request.getParameter("customerBillingCountryNew_"+(i+1)+"_"+(j+1));
                                billingRegion[j] = request.getParameter("customerBillingRegionNew_"+(i+1)+"_"+(j+1));
                                billingCity[j] = request.getParameter("customerBillingCityNew_"+(i+1)+"_"+(j+1));
                                billingPincode[j] = request.getParameter("customerBillingPincodeNew_"+(i+1)+"_"+(j+1));
                                billingGstCode[j] = request.getParameter("customerBillingGstCodeNew_"+(i+1)+"_"+(j+1));
                                billingGstNumber[j] = request.getParameter("customerBillingGstNumberNew_"+(i+1)+"_"+(j+1));
                            }
                            customerFormData.setCustomerBillingAddressNew(billingAddress);
                            customerFormData.setCustomerBillingShortCodeNew(billingShortCode);
                            customerFormData.setCustomerBillingCountryNew(billingCountry);
                            customerFormData.setCustomerBillingRegionNew(billingRegion);
                            customerFormData.setCustomerBillingCityNew(billingCity);
                            customerFormData.setCustomerBillingPincodeNew(billingPincode);
                            customerFormData.setCustomerBillingGstCodeNew(billingGstCode);
                            customerFormData.setCustomerBillingGstNumberNew(billingGstNumber);
                            
                            businessLength = Integer.parseInt(request.getParameter("businessContactCount_"+(i+1)));
                            String[] businessPerson = new String[businessLength];
                            String[] businessDesignation = new String[businessLength];
                            String[] businessPhone = new String[businessLength];
                            String[] businessEmail = new String[businessLength];
                            for(int j=0;j<businessLength;j++){
                                businessPerson[j] = request.getParameter("customerContactPersonNew_"+(i+1)+"_"+(j+1));
                                businessDesignation[j] = request.getParameter("customerDesignationNew_"+(i+1)+"_"+(j+1));
                                businessPhone[j] = request.getParameter("customerContactPhoneNew_"+(i+1)+"_"+(j+1));
                                businessEmail[j] = request.getParameter("customerContactEmailNew_"+(i+1)+"_"+(j+1));
                            }
                            customerFormData.setCustomerContactPersonNew(businessPerson);
                            customerFormData.setCustomerDesignationNew(businessDesignation);
                            customerFormData.setCustomerContactPhoneNew(businessPhone);
                            customerFormData.setCustomerContactEmailNew(businessEmail);
                            
                            financeLength = Integer.parseInt(request.getParameter("financeContactCount_"+(i+1)));
                            String[] financePerson = new String[financeLength];
                            String[] financeDesignation = new String[financeLength];
                            String[] financePhone = new String[financeLength];
                            String[] financeEmail = new String[financeLength];
                            for(int j=0;j<financeLength;j++){
                                financePerson[j] = request.getParameter("customerFinanceContactPersonNew_"+(i+1)+"_"+(j+1));
                                financeDesignation[j] = request.getParameter("customerFinanceDesignationNew_"+(i+1)+"_"+(j+1));
                                financePhone[j] = request.getParameter("customerFinanceContactPhoneNew_"+(i+1)+"_"+(j+1));
                                financeEmail[j] = request.getParameter("customerFinanceContactEmailNew_"+(i+1)+"_"+(j+1));
                            }
                            customerFormData.setCustomerFinanceContactPersonNew(financePerson);
                            customerFormData.setCustomerFinanceDesignationNew(financeDesignation);
                            customerFormData.setCustomerFinanceContactPhoneNew(financePhone);
                            customerFormData.setCustomerFinanceContactEmailNew(financeEmail);
                            customerFormData.setCustomerID(lastInsertId);
                            
                            dunningLength = Integer.parseInt(request.getParameter("dunningContactCount_"+(i+1)));
                            String[] dunningPerson = new String[dunningLength];
                            String[] dunningDesignation = new String[dunningLength];
                            String[] dunningPhone = new String[dunningLength];
                            String[] dunningEmail = new String[dunningLength];
                            for(int j=0;j<dunningLength;j++){
                                dunningPerson[j] = request.getParameter("customerDunningContactPersonNew_"+(i+1)+"_"+(j+1));
                                dunningDesignation[j] = request.getParameter("customerDunningDesignationNew_"+(i+1)+"_"+(j+1));
                                dunningPhone[j] = request.getParameter("customerDunningContactPhoneNew_"+(i+1)+"_"+(j+1));
                                dunningEmail[j] = request.getParameter("customerDunningContactEmailNew_"+(i+1)+"_"+(j+1));
                            }
                            customerFormData.setCustomerDunningContactPersonNew(dunningPerson);
                            customerFormData.setCustomerDunningDesignationNew(dunningDesignation);
                            customerFormData.setCustomerDunningContactPhoneNew(dunningPhone);
                            customerFormData.setCustomerDunningContactEmailNew(dunningEmail);
                            customerFormData.setCustomerID(lastInsertId);
                            
                            customerFormData.setInvoiceTo(request.getParameter("invoiceEmailedTo"));
                            customerService.addCustomerDetails(customerFormData);
                        }
                        
                        
                   List<String> approvalMails = customerService.getApproverMailId();
                   String[] toMailApprovalMails ={approvalMails.get(0)};
                   if(customerFormData.getStatus().equals("m")){
                       customerService.triggerMail(toMailApprovalMails, customerFormData, "customerSubmit", "submit", customerFormData.getSalesPerson());
                   }
                   mv = new ModelAndView("redirect:customersList.htm");
                   mv.addObject("customAdd", "1");

               } else {
                   mv = new ModelAndView("redirect:customersList.htm");
                   mv.addObject("customAdd", "0");

               }
            }
           
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }

    public ModelAndView rmgApproveCustomerProcess(HttpServletRequest request, HttpServletResponse response, CustomerAddDTO customerFormData) throws Exception {
        ModelAndView mv = null;
        try {
            final WebApplicationContext ctx = getWebApplicationContext();
            CustomerServiceImpl customerService = (CustomerServiceImpl) ctx.getBean("CustomerService");
            customerService.rmgApproveCustomerDetails(customerFormData);
            System.out.println("rmgApproveCustomer Proces>>>>>>>>>>>>>>>>>");
            mv = new ModelAndView("redirect:customersRMGApprovalList.htm");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }

    public ModelAndView approveCustomerProcess(HttpServletRequest request, HttpServletResponse response, CustomerAddDTO customerFormData) throws Exception {
        ModelAndView mv = null;
        try {
            final WebApplicationContext ctx = getWebApplicationContext();
            CustomerServiceImpl customerService = (CustomerServiceImpl) ctx.getBean("CustomerService");
            customerService.approveCustomerDetails(customerFormData);
            System.out.println("status++++++"+customerFormData.getCustomerApproveButton()+"  submit+++"+customerFormData.getCustomerRejectButton());
            
            mv = new ModelAndView("redirect:customersApprovalList.htm");
            if(customerFormData.getCustomerApproveButton()!= null){
                mv.addObject("cust_data", "1");
            }else if(customerFormData.getCustomerRejectButton()!=null){
                mv.addObject("cust_data", "2");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }

    public ModelAndView getDuplicateCustomerName(HttpServletRequest request, HttpServletResponse response, CustomerAddDTO filterData) throws Exception {
        ModelAndView mv = new ModelAndView("/getDuplicateCustomerName");
        PrintWriter out = response.getWriter();
        try {
            final WebApplicationContext ctx = getWebApplicationContext();
            CustomerServiceImpl customerService = (CustomerServiceImpl) ctx.getBean("CustomerService");
            filterData.setCustomerName(request.getParameter("customerName"));
            if (request.getParameter("customerId") != null && !(request.getParameter("customerId").equals(""))) {
                filterData.setCustomerID(request.getParameter("customerId"));
            }
            List<CustomerAddDTO> custList = customerService.getDuplicateCustomerName(filterData);
            int recordCount = custList.size();
            out.println(recordCount);
            mv.addObject("regionList", custList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    

    public ModelAndView getCustomerXL(HttpServletRequest request, HttpServletResponse response, CustomerDataDTO filterData) throws Exception {
        final WebApplicationContext ctx = getWebApplicationContext();
        CustomerServiceImpl customerService = (CustomerServiceImpl) ctx.getBean("CustomerService");
        List<CustomerDataDTO> customerData = customerService.fetchCustomerDataForExcel(filterData);

        ArrayList entireList = new ArrayList();
        if(customerData != null){
       
        for (int i = 0; i < customerData.size(); i++) {
            ArrayList rowDataList = new ArrayList();
            rowDataList.add(new String("" + customerData.get(i).getCustomerCode()));
            rowDataList.add(new String("" + customerData.get(i).getCustomerName()));
            rowDataList.add(new String("" + customerData.get(i).getCustomerGroupName()));
            rowDataList.add(new String("" + customerData.get(i).getBillingAddress()));
            rowDataList.add(new String("" + customerData.get(i).getBillingAddressShortCode()));
            rowDataList.add(new String("" + customerData.get(i).getBillingCity()));
            rowDataList.add(new String("" + customerData.get(i).getBillingRegion()));
            rowDataList.add(new String("" + customerData.get(i).getBillingCountry()));
            rowDataList.add(new String("" + customerData.get(i).getBillingPincode()));
            rowDataList.add(new String("" + customerData.get(i).getBillingGstCode()));
            rowDataList.add(new String("" + customerData.get(i).getBillingGstNumber()));
            rowDataList.add(new String("" + customerData.get(i).getWorkLocationAddress()));
            rowDataList.add(new String("" + customerData.get(i).getWorkLocationShortCode()));
            rowDataList.add(new String("" + customerData.get(i).getWorkLocationCity()));
            rowDataList.add(new String("" + customerData.get(i).getWorkLocationRegion()));
            rowDataList.add(new String("" + customerData.get(i).getWorkLocationCountry()));
            rowDataList.add(new String("" + customerData.get(i).getWorkLocationPincode()));
            rowDataList.add(new String("" + customerData.get(i).getWorkingHrs()));
            rowDataList.add(new String("" + customerData.get(i).getBusinessContactPerson()));
            rowDataList.add(new String("" + customerData.get(i).getBusinessContactDesignation()));
            rowDataList.add(new String("" + customerData.get(i).getBusinessContactPhone()));
            rowDataList.add(new String("" + customerData.get(i).getBusinessEmail()));
            rowDataList.add(new String("" + customerData.get(i).getFinanceContactPerson()));
            rowDataList.add(new String("" + customerData.get(i).getFinanceContactDesignation()));
            rowDataList.add(new String("" + customerData.get(i).getFinanceContactPhone()));
            rowDataList.add(new String("" + customerData.get(i).getFinanceEmail()));
            
            entireList.add(rowDataList);
        }
        
        Calendar cal = Calendar.getInstance();
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        String month = null;
        if (cal.get(Calendar.MONTH) == 0) {
            month = months[(cal.get(Calendar.MONTH))];
        } else {
            month = months[(cal.get(Calendar.MONTH)) - 1];
        }
        String year = Integer.toString(cal.get(Calendar.YEAR));
            CommonMethods.exportToExcel(response, entireList, "Customer_Report_as_on_", "Customer Report", month + "_" + year);
        }
        return null;
    }

    public ModelAndView attachmentDownload(HttpServletRequest request, HttpServletResponse response, CustomerAddDTO filterData) throws Exception {
        System.out.println("---" + filterData.getFile());
        String fileName = filterData.getFileName();
        String filePath = CommonConfigurations.fileUploadPath;
        String fileType = filterData.getFileType();
        System.out.println("file name " + fileName + " file path " + filePath + " file type " + fileType);
        CommonMethods.fileDownload(fileName, filePath, fileType, response);
        return null;
    }

    public ModelAndView getRegion(HttpServletRequest request, HttpServletResponse response, CustomerAddDTO filterData) throws Exception {
        System.out.println("Inside GetReason Method");
        final WebApplicationContext ctx = getWebApplicationContext();
        CustomerServiceImpl customerService = (CustomerServiceImpl) ctx.getBean("CustomerService");
        response.getWriter().println("<option value=''>-Select Region--</option>");
        System.out.println("hhhhhhhhhh==>" + filterData.getRegionID());
        String selected = "";
        for (CustomerAddDTO dTO : customerService.getRegionList(filterData)) {
            if (filterData.getRegionID() != null && filterData.getRegionID() != "") {
                if (dTO.getRegionID().equals(filterData.getRegionID())) {
                    selected = "selected";
                }
            }
            response.getWriter().println("<option value='" + dTO.getRegionID() + "'   " + selected + ">" + dTO.getRegionName() + "</option>");
            selected = "";
        }
        return null;
    }

    public ModelAndView getCity(HttpServletRequest request, HttpServletResponse response, CustomerAddDTO filterData) throws Exception {
        final WebApplicationContext ctx = getWebApplicationContext();
        CustomerServiceImpl customerService = (CustomerServiceImpl) ctx.getBean("CustomerService");
        response.getWriter().println("<option value=''>-Select City -</option>");
        String selected = "";
        for (CustomerAddDTO dTO : customerService.getCityList(filterData)) {
            if (filterData.getCityID() != null && filterData.getCityID() != "") {
                if (dTO.getCityID().equals(filterData.getCityID())) {
                    selected = "selected";
                }
            }
            response.getWriter().println("<option value='" + dTO.getCityID() + "'   " + selected + ">" + dTO.getCityName() + "</option>");
            selected = "";
        }
        return null;
    }

    public ModelAndView saveEditCustomer(HttpServletRequest request, HttpServletResponse response, CustomerAddDTO filterData) throws Exception {
        ModelAndView mv = new ModelAndView();
        String lastFileInsertId = null;
        String process = null;
        String subject = null;
        int referenceId = 0;
        HttpSession session = request.getSession();
        final WebApplicationContext ctx = getWebApplicationContext();
        CustomerServiceImpl customerService = (CustomerServiceImpl) ctx.getBean("CustomerService");
        int auditChangeCount=0;
        //----------------------------------------------
        
        //checking customer basic details for change
//        List<CustomerAddDTO> selectedCustomerData = customerService.fetchSelectedCustomer(filterData);
        if(filterData.getPrevStatus() != null){
        if (filterData.getCustomerID() != null && filterData.getCustomerID() != "") {
            MultipartFile uploadedFile = filterData.getAttachmentValue();
            referenceId = Integer.parseInt(filterData.getCustomerID());
            if (uploadedFile != null) {
                lastFileInsertId = CommonMethods.fileUpload(uploadedFile, referenceId, filterData.getAddressType(), CommonConfigurations.customerModuleName, customerService);
                filterData.setAttchedFileName(lastFileInsertId);
            }else{
                filterData.setAttchedFileName(request.getParameter("attachmentValue1"));
                
            }
            if(filterData.getPrevStatus().equals("s") && filterData.getAction().equals("m")){
                auditChangeCount=1;
                filterData.setStatus("m");
                process = "submit";
                subject = "customerSubmit";
                
            }else  if(filterData.getPrevStatus().equals("a") && filterData.getAction().equals("m")){
                auditChangeCount=auditTracking(filterData);
                if(auditChangeCount>=0){
                    if(auditChangeCount > 0){
                        filterData.setStatus("d");
                        process = "Amended";
                        subject = "customerAmended";
                    }else if(auditChangeCount==0){
                        filterData.setStatus(filterData.getPrevStatus());
                    }else{
                        filterData.setStatus("d");
                        process = "Amended";
                        subject = "customerAmended";
                    }
                }else{
                    filterData.setStatus(filterData.getPrevStatus());
                }
                
            }else  if(filterData.getPrevStatus().equals("r")&& filterData.getAction().equals("m")){
                auditChangeCount=auditTracking(filterData);
                if(auditChangeCount>=0){
                    if(auditChangeCount == 0){
                        if(filterData.getCustomerCode()!= null&& filterData.getCustomerCode().length()>5){
                            filterData.setStatus("d");
                            process = "Amended";
                            subject = "customerAmended";
                        }
                        else{
                            filterData.setStatus("m");
                            process = "Resubmitted";
                            subject = "customerResubmitted";
                        }
                    }else{
                        if(filterData.getCustomerCode()!= null && filterData.getCustomerCode().length()>5){
                            filterData.setStatus("d");
                            process = "Amended";
                            subject = "customerAmended";
                        }
                        else{
                            filterData.setStatus("m");
                            process = "Resubmitted";
                            subject = "customerResubmitted";
                        }
                    }
                    
                }else{
                    filterData.setStatus(filterData.getPrevStatus());
                }
                
            }else  if(filterData.getPrevStatus().equals("s") && filterData.getAction().equals("s")){
                filterData.setStatus("s");
            }
            if(filterData.getCustomerDivisionListName()!= null){
                for(int i=0;i<filterData.getCustomerDivisionListName().length;i++){
                    if(filterData.getCustomerDivisionListName()[i]!= null){
                        filterData.setCustomerDivision(filterData.getCustomerDivisionListName()[i]);
                    }
                    String lastInsertId=null;
                    if(filterData.getCustomerDivisionListName()[i]!= null){
                        if(i==0 && auditChangeCount>=0){
                            String invoice = request.getParameter("invoiceTo_"+(i+1));
                                filterData.setInvoiceTo(invoice);
                            customerService.updateCustomerDetails(filterData);
                            customerService.updateCustomerMaster(filterData);
//                            if(auditChangeCount>0){
//                                String[] toMailApprovalMails = CommonConfigurations.approvalMailList;
//                                if(filterData.getStatus() != "s"){
//                                    customerService.triggerMail(toMailApprovalMails, filterData, subject, process, filterData.getSalesPerson());
//                                }
//                            }
                        }
                        if(i>0){
                            filterData.setParentId(Integer.parseInt(request.getParameter("parentId")));
//                            filterData.setAttchedFileName(request.getParameter("attchedFileName"));
                            if(!request.getParameter("attchedFileName").isEmpty()){
                                filterData.setAttchedFileName(request.getParameter("attchedFileName"));
                            }else{
                                filterData.setAttchedFileName(lastFileInsertId);
                            }
                            String invoice = request.getParameter("invoiceTo_"+(i+1));
                                filterData.setInvoiceTo(invoice);
                            lastInsertId = customerService.insertCustomerDetails(filterData);
                            filterData.setCustomerID(lastInsertId);
                            filterData.setStatus("m");
                            process = "submit";
                            subject = "customerSubmit";
                            if((filterData.getPrevStatus().equals("s")||filterData.getPrevStatus().equals("a")) && filterData.getAction().equals("m")){
                                filterData.setStatus("m");
                                process = "submit";
                                subject = "customerSubmit";

                            }else  if(filterData.getPrevStatus().equals("s") && filterData.getAction().equals("s")){
                                filterData.setStatus("s");
                            }
                        }
                    }

                    int workLocationLength,billingLength, businessLength, financeLength, dunningLength = 0; 

                    if(request.getParameter("workLocationCount_"+(i+1))!= null || request.getParameter("workLocationCount_"+(i+1))!=""){
                        workLocationLength = Integer.parseInt(request.getParameter("workLocationCount_"+(i+1)));
                        String[] workLocationAddress = new String[workLocationLength];
                        String[] workLocationShortCode = new String[workLocationLength];
                        String[] workLocationCountry = new String[workLocationLength];
                        String[] workLocationRegion = new String[workLocationLength];
                        String[] workLocationCity = new String[workLocationLength];
                        String[] workLocationPincode = new String[workLocationLength];
                        String[] workLocationWorkingHrs = new String[workLocationLength];
                        String[] workLocationIsCompanyLocation = new String[workLocationLength];
                        for(int j=0;j<workLocationLength;j++){
                            workLocationAddress[j]= request.getParameter("customerWorkAddressNew_"+(i+1)+"_"+(j+1));
                            workLocationShortCode[j]=request.getParameter("customerWorkShortCodeNew_"+(i+1)+"_"+(j+1));
                            workLocationCountry[j] = request.getParameter("customerWorkCountryNew_"+(i+1)+"_"+(j+1));
                            workLocationRegion[j] = request.getParameter("customerWorkRegionNew_"+(i+1)+"_"+(j+1));
                            workLocationCity[j] = request.getParameter("customerWorkCityNew_"+(i+1)+"_"+(j+1));
                            workLocationPincode[j] = request.getParameter("customerWorkPincodeNew_"+(i+1)+"_"+(j+1));
                            workLocationWorkingHrs[j] = request.getParameter("workingHoursPerDayNew_"+(i+1)+"_"+(j+1));
                            workLocationIsCompanyLocation[j] = request.getParameter("workingLocationIsCompanyLocationNew_"+(i+1)+"_"+(j+1));
                        }
                        filterData.setCustomerWorkAddressNew(workLocationAddress);
                        filterData.setCustomerWorkShortCodeNew(workLocationShortCode);
                        filterData.setCustomerWorkCountryNew(workLocationCountry);
                        filterData.setCustomerWorkRegionNew(workLocationRegion);
                        filterData.setCustomerWorkCityNew(workLocationCity);
                        filterData.setCustomerWorkPincodeNew(workLocationPincode);
                        filterData.setWorkingHoursPerDayNew(workLocationWorkingHrs);
                        filterData.setWorkLocationIscompanyLocationNew(workLocationIsCompanyLocation);
                    }
                   if(request.getParameter("businessAddressCount_"+(i+1))!= null || request.getParameter("businessAddressCount_"+(i+1))!= ""){
                        billingLength = Integer.parseInt(request.getParameter("businessAddressCount_"+(i+1)));
                        String[] billingAddress = new String[billingLength];
                        String[] billingShortCode = new String[billingLength];
                        String[] billingCountry = new String[billingLength];
                        String[] billingRegion = new String[billingLength];
                        String[] billingCity = new String[billingLength];
                        String[] billingPincode = new String[billingLength];
                        String[] billingGstCode = new String[billingLength];
                        String[] billingGstNumber = new String[billingLength];
                        for(int j=0;j<billingLength;j++){
                            billingAddress[j] = request.getParameter("customerBillingAddressNew_"+(i+1)+"_"+(j+1));
                            billingShortCode[j]= request.getParameter("customerBillingShortCodeNew_"+(i+1)+"_"+(j+1));
                            billingCountry[j] = request.getParameter("customerBillingCountryNew_"+(i+1)+"_"+(j+1));
                            billingRegion[j] = request.getParameter("customerBillingRegionNew_"+(i+1)+"_"+(j+1));
                            billingCity[j] = request.getParameter("customerBillingCityNew_"+(i+1)+"_"+(j+1));
                            billingPincode[j] = request.getParameter("customerBillingPincodeNew_"+(i+1)+"_"+(j+1));
                            billingGstCode[j] = request.getParameter("customerBillingGstCodeNew_"+(i+1)+"_"+(j+1));
                            billingGstNumber[j] = request.getParameter("customerBillingGstNumberNew_"+(i+1)+"_"+(j+1));
                        }
                        filterData.setCustomerBillingAddressNew(billingAddress);
                        filterData.setCustomerBillingShortCodeNew(billingShortCode);
                        filterData.setCustomerBillingCountryNew(billingCountry);
                        filterData.setCustomerBillingRegionNew(billingRegion);
                        filterData.setCustomerBillingCityNew(billingCity);
                        filterData.setCustomerBillingPincodeNew(billingPincode);
                        filterData.setCustomerBillingGstCodeNew(billingGstCode);
                        filterData.setCustomerBillingGstNumberNew(billingGstNumber);
                   }

                   if(request.getParameter("businessContactCount_"+(i+1))!= null || request.getParameter("businessContactCount_"+(i+1))!=""){
                        businessLength = Integer.parseInt(request.getParameter("businessContactCount_"+(i+1)));
                        String[] businessPerson = new String[businessLength];
                        String[] businessDesignation = new String[businessLength];
                        String[] businessPhone = new String[businessLength];
                        String[] businessEmail = new String[businessLength];
                        for(int j=0;j<businessLength;j++){
                            businessPerson[j] = request.getParameter("customerContactPersonNew_"+(i+1)+"_"+(j+1));
                            businessDesignation[j] = request.getParameter("customerContactDesignationNew_"+(i+1)+"_"+(j+1));
                            businessPhone[j] = request.getParameter("customerContactPhoneNew_"+(i+1)+"_"+(j+1));
                            businessEmail[j] = request.getParameter("customerContactEmailNew_"+(i+1)+"_"+(j+1));
                            
                        }
                        filterData.setCustomerContactPersonNew(businessPerson);
                        filterData.setCustomerDesignationNew(businessDesignation);
                        filterData.setCustomerContactPhoneNew(businessPhone);
                        filterData.setCustomerContactEmailNew(businessEmail);
                   }
                   if(request.getParameter("financeContactCount_"+(i+1))!= null || request.getParameter("financeContactCount_"+(i+1))!=""){
                        financeLength = Integer.parseInt(request.getParameter("financeContactCount_"+(i+1)));
                        String[] financePerson = new String[financeLength];
                        String[] financeDesignation = new String[financeLength];
                        String[] financePhone = new String[financeLength];
                        String[] financeEmail = new String[financeLength];
                        for(int j=0;j<financeLength;j++){
                            financePerson[j] = request.getParameter("customerFinanceContactPersonNew_"+(i+1)+"_"+(j+1));
                            financeDesignation[j] = request.getParameter("customerFinanceContactDesignationNew_"+(i+1)+"_"+(j+1));
                            financePhone[j] = request.getParameter("customerFinanceContactPhoneNew_"+(i+1)+"_"+(j+1));
                            financeEmail[j] = request.getParameter("customerFinanceContactEmailNew_"+(i+1)+"_"+(j+1));
                            
                        }
                        filterData.setCustomerFinanceContactPersonNew(financePerson);
                        filterData.setCustomerFinanceDesignationNew(financeDesignation);
                        filterData.setCustomerFinanceContactPhoneNew(financePhone);
                        filterData.setCustomerFinanceContactEmailNew(financeEmail);
                    }
                   if(request.getParameter("dunningContactCount_"+(i+1))!= null || request.getParameter("dunningContactCount_"+(i+1))!=""){
                        dunningLength = Integer.parseInt(request.getParameter("dunningContactCount_"+(i+1)));
                        String[] dunningPerson = new String[dunningLength];
                        String[] dunningDesignation = new String[dunningLength];
                        String[] dunningPhone = new String[dunningLength];
                        String[] dunningEmail = new String[dunningLength];
                        for(int j=0;j<dunningLength;j++){
                            dunningPerson[j] = request.getParameter("customerDunningContactPersonNew_"+(i+1)+"_"+(j+1));
                            dunningDesignation[j] = request.getParameter("customerDunningContactDesignationNew_"+(i+1)+"_"+(j+1));
                            dunningPhone[j] = request.getParameter("customerDunningContactPhoneNew_"+(i+1)+"_"+(j+1));
                            dunningEmail[j] = request.getParameter("customerDunningContactEmailNew_"+(i+1)+"_"+(j+1));
                            
                        }
                        filterData.setCustomerDunningContactPersonNew(dunningPerson);
                        filterData.setCustomerDunningDesignationNew(dunningDesignation);
                        filterData.setCustomerDunningContactPhoneNew(dunningPhone);
                        filterData.setCustomerDunningContactEmailNew(dunningEmail);
                    }
                    auditChangeCount += customerService.addCustomerDetails(filterData);
                    if(i==0){
                        if(filterData.getPrevStatus().equals("a") && filterData.getAction().equals("m")){
                            if(auditChangeCount>=0){
                                if(auditChangeCount > 0){
                                    filterData.setStatus("d");
                                    process = "Amended";
                                    subject = "customerAmended";
                                }else if(auditChangeCount==0){
                                    filterData.setStatus(filterData.getPrevStatus());
                                }else{
                                    filterData.setStatus("d");
                                    process = "Amended";
                                    subject = "customerAmended";
                                }
                            }else{
                                filterData.setStatus(filterData.getPrevStatus());
                            }

                        }
                        if(auditChangeCount>0){
                            customerService.updateCustomerDetails(filterData);
                            customerService.updateCustomerMaster(filterData);
                        }
                        if(auditChangeCount>=0){
                            List<String> approvalMails = customerService.getApproverMailId();
                            String[] toMailApprovalMails ={approvalMails.get(0)};
                            if(filterData.getStatus() != "s" || filterData.getStatus() != "a"){
                                customerService.triggerMail(toMailApprovalMails, filterData, subject, process, filterData.getSalesPerson());
                            }
                        }
                    }
                    
                    if((filterData.getStatus() != null || filterData.getStatus() !="")&&i > 0){
                        customerService.updateCustomerDetails(filterData);
                        customerService.updateCustomerMaster(filterData);
                        List<String> approvalMails = customerService.getApproverMailId();
                        String[] toMailApprovalMails ={approvalMails.get(0)};
                        if(filterData.getStatus() != "s"){
                            customerService.triggerMail(toMailApprovalMails, filterData, subject, process, filterData.getSalesPerson());
                        }
                    }
                }
            }
            mv = new ModelAndView("redirect:customersList.htm");
            if(filterData.getStatus()!="s"){
                mv.addObject("customAdd", "1");
            }else{
                mv.addObject("customAdd", "2");
            }
            if(auditChangeCount==0 && filterData.getCustomerDivisionListName().length==1){
                mv = new ModelAndView("redirect:customersEdit.htm?customerId="+referenceId);
                mv.addObject("customEdit", "1");
            }
        }
        }
        return mv;

    }
    
    public ModelAndView getDuplicateCustomerDivisionName(HttpServletRequest request, HttpServletResponse response, CustomerAddDTO filterData) throws Exception {
        ModelAndView mv = new ModelAndView("/getDuplicateCustomerDivisionName");
        PrintWriter out = response.getWriter();
        try {
            final WebApplicationContext ctx = getWebApplicationContext();
            CustomerServiceImpl customerService = (CustomerServiceImpl) ctx.getBean("CustomerService");
            filterData.setCustomerName(request.getParameter("customerName"));
            filterData.setCustomerDivisionName(request.getParameter("customerDivision"));
            if (request.getParameter("customerId") != null && !(request.getParameter("customerId").equals(""))) {
                filterData.setCustomerID(request.getParameter("customerId"));
            }
            List<CustomerAddDTO> custList = customerService.getDuplicateCustomerDivisionName(filterData);
            int recordCount = custList.size();
            out.println(recordCount);
            mv.addObject("regionList", custList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public int auditTracking(CustomerAddDTO filterData){
        //audit logs----------------------
     
        
        final WebApplicationContext ctx = getWebApplicationContext();
        CustomerServiceImpl customerService = (CustomerServiceImpl) ctx.getBean("CustomerService");
        int changeCount = 0 ;
        //-----------------------
                Map<String,List> newHash=new HashMap<String,List>();
                List<CustomerAddDTO> selectedCustomerData = customerService.fetchSelectedCustomer(filterData);
                //1
                if(selectedCustomerData.get(0).getCustomerName().equals(filterData.getCustomerName())){

                }else{
                    List customerName = new ArrayList();
                    filterData.setTableName("customers");
                    filterData.setTableId(selectedCustomerData.get(0).getCustID());
                    filterData.setChangedBy(selectedCustomerData.get(0).getSalesPersonRefId());
                    filterData.setChangedFrom(selectedCustomerData.get(0).getCustomerName());
                    filterData.setChangedTo(filterData.getCustomerName());
                    filterData.setFieldChanged("customer_name");
                    customerService.insertCustomerInfoHistory(filterData);
                    customerName.add(selectedCustomerData.get(0).getCustomerName());
                    customerName.add(filterData.getCustomerName());
                    newHash.put("Customer Name", customerName);
                    filterData.setChangedData(newHash);
                    changeCount++;
                }
 
                //2
                String salesPersonNewName = customerService.getSalesPersonName(filterData.getSalesPerson());
                String salesPersonOldName = customerService.getSalesPersonName(selectedCustomerData.get(0).getSalesPersonRefId());
                if(selectedCustomerData.get(0).getSalesPersonRefId().equals(filterData.getSalesPerson())){

                }else{
                    List SalesPerson = new ArrayList();
                    filterData.setTableName("customers");
                    filterData.setTableId(selectedCustomerData.get(0).getCustID());
                    filterData.setChangedBy(selectedCustomerData.get(0).getSalesPersonRefId());
                    filterData.setChangedFrom(selectedCustomerData.get(0).getSalesPersonRefId());
                    filterData.setChangedTo(filterData.getSalesPerson());
                    filterData.setFieldChanged("sales_person_reference");
                    customerService.insertCustomerInfoHistory(filterData);
                    
                    SalesPerson.add(salesPersonNewName);
                    SalesPerson.add(salesPersonOldName);
//                    newHash.put("BDM/Sales Person", SalesPerson);
//                    filterData.setChangedData(newHash);
                    changeCount++;
                }
                //3
                if((selectedCustomerData.get(0).getSalesPersonContactNo().equals(filterData.getSalesPersonContactNo()))){

                }else{
                    List SalesPersonNo = new ArrayList();
                    filterData.setTableName("customers");
                    filterData.setTableId(selectedCustomerData.get(0).getCustID());
                    filterData.setChangedBy(selectedCustomerData.get(0).getSalesPersonRefId());
                    filterData.setChangedFrom(selectedCustomerData.get(0).getSalesPersonContactNo());
                    filterData.setChangedTo(filterData.getSalesPersonContactNo());
                    filterData.setFieldChanged("sales_person_contact_no");
                    customerService.insertCustomerInfoHistory(filterData);
                    SalesPersonNo.add(selectedCustomerData.get(0).getSalesPersonContactNo());
                    SalesPersonNo.add(filterData.getSalesPersonContactNo());
//                    newHash.put("BDM/Sales Person No", SalesPersonNo);
//                    filterData.setChangedData(newHash);
                    changeCount++;
                }
    //            //4
                List CustomerUrl = new ArrayList();
                if(selectedCustomerData.get(0).getCustomerURL() == null && filterData.getCustomerURL() != null){
                    filterData.setTableName("customers");
                    filterData.setTableId(selectedCustomerData.get(0).getCustID());
                    filterData.setChangedBy(selectedCustomerData.get(0).getSalesPersonRefId());
                    filterData.setChangedFrom("");
                    filterData.setChangedTo(filterData.getCustomerURL());
                    filterData.setFieldChanged("customer_url");
                    customerService.insertCustomerInfoHistory(filterData);
                    CustomerUrl.add("");
                    CustomerUrl.add(filterData.getCustomerURL());
                    newHash.put("Customer URL", CustomerUrl);
                    filterData.setChangedData(newHash);
                    changeCount++;
                }else if(selectedCustomerData.get(0).getCustomerURL() != null && filterData.getCustomerURL() != null){
                    if(selectedCustomerData.get(0).getCustomerURL().equals(filterData.getCustomerURL())){

                    }else{
                        filterData.setTableName("customers");
                        filterData.setTableId(selectedCustomerData.get(0).getCustID());
                        filterData.setChangedBy(selectedCustomerData.get(0).getSalesPersonRefId());
                        filterData.setChangedFrom(selectedCustomerData.get(0).getCustomerURL());
                        filterData.setChangedTo(filterData.getCustomerURL());
                        filterData.setFieldChanged("customer_url");
                        customerService.insertCustomerInfoHistory(filterData);
                        CustomerUrl.add(selectedCustomerData.get(0).getCustomerURL());
                        CustomerUrl.add(filterData.getCustomerURL());
                        newHash.put("Customer URL", CustomerUrl);
                        filterData.setChangedData(newHash);
                        changeCount++;
                    }
                }
                //5 -- Attachment Type
                
                 List AttachmentType = new ArrayList();
                 if(selectedCustomerData.get(0).getAttachmentType() == null && filterData.getAttachmentType() != null){
                    filterData.setTableName("customers");
                    filterData.setTableId(selectedCustomerData.get(0).getCustID());
                    filterData.setChangedBy(selectedCustomerData.get(0).getSalesPersonRefId());
                    filterData.setChangedFrom(selectedCustomerData.get(0).getAttachmentType());
                    filterData.setChangedTo(filterData.getAttachmentType());
                    filterData.setFieldChanged("attachment_type");
                    customerService.insertCustomerInfoHistory(filterData);
                    AttachmentType.add("");
                    AttachmentType.add(filterData.getAttachmentType());
                    newHash.put("Attachment Type", AttachmentType);
                    filterData.setChangedData(newHash);
                    changeCount++;
                 
                }else if(selectedCustomerData.get(0).getAttachmentType() != null && filterData.getAttachmentType() != null){
                        if(selectedCustomerData.get(0).getAttachmentType().equals(filterData.getAttachmentType())){

                        }else{

                        filterData.setTableName("customers");
                        filterData.setTableId(selectedCustomerData.get(0).getCustID());
                        filterData.setChangedBy(selectedCustomerData.get(0).getSalesPersonRefId());
                        filterData.setChangedFrom(selectedCustomerData.get(0).getAttachmentType());
                        filterData.setChangedTo(filterData.getAttachmentType());
                        filterData.setFieldChanged("attachment_type");
                        customerService.insertCustomerInfoHistory(filterData);
                        AttachmentType.add(selectedCustomerData.get(0).getAttachmentType());
                        AttachmentType.add(filterData.getAttachmentType());
                        newHash.put("Attachment Type", AttachmentType);
                        filterData.setChangedData(newHash);
                        changeCount++;
                    }
                }
               // 6 - Attachments
                 
                 List AttachmentValue = new ArrayList();
                 
                 if(selectedCustomerData.get(0).getFileName() == null && filterData.getAttchedFileName() != null && filterData.getAttachmentValue() != null){
                    String newFileName = filterData.getAttachmentValue().getOriginalFilename();
//                    String oldFile[] = selectedCustomerData.get(0).getFileName().split("~");
//                    String oldFileName = oldFile[2];
                    filterData.setTableName("customers");
                    filterData.setTableId(selectedCustomerData.get(0).getCustID());
                    filterData.setChangedBy(selectedCustomerData.get(0).getSalesPersonRefId());
                    filterData.setChangedFrom(" ");
                    filterData.setChangedTo(newFileName);
                    filterData.setFieldChanged("attachments");
                    customerService.insertCustomerInfoHistory(filterData);
                    AttachmentValue.add(" ");
                    AttachmentValue.add(newFileName);
                    newHash.put("Attachment Value", AttachmentValue);
                    filterData.setChangedData(newHash);
                    changeCount++;
                 
                }else if(selectedCustomerData.get(0).getFileName() != null && filterData.getAttachmentValue() != null && filterData.getAttchedFileName() != null){
                        String newFileName = filterData.getAttachmentValue().getOriginalFilename();
                        String oldFile[] = selectedCustomerData.get(0).getFileName().split("~");
                        String oldFileName = oldFile[2];
                        if(oldFileName.equals(newFileName)){

                        }else{
                            filterData.setTableName("customers");
                            filterData.setTableId(selectedCustomerData.get(0).getCustID());
                            filterData.setChangedBy(selectedCustomerData.get(0).getSalesPersonRefId());
                            filterData.setChangedFrom(oldFileName);
                            filterData.setChangedTo(newFileName);
                            filterData.setFieldChanged("attachments");
                            customerService.insertCustomerInfoHistory(filterData);
                            AttachmentValue.add(oldFileName);
                            AttachmentValue.add(newFileName);
                            newHash.put("Attachment Type", AttachmentValue);
                            filterData.setChangedData(newHash);
                            changeCount++;
                        }
                }
                // -- Invoice email to
                
                 List invoiceTo = new ArrayList();
                 if(selectedCustomerData.get(0).getInvoiceTo() == null && filterData.getInvoiceTo() != null){
                    filterData.setTableName("customers");
                    filterData.setTableId(selectedCustomerData.get(0).getCustID());
                    filterData.setChangedBy(selectedCustomerData.get(0).getSalesPersonRefId());
                    filterData.setChangedFrom(selectedCustomerData.get(0).getInvoiceTo());
                    filterData.setChangedTo(filterData.getInvoiceTo());
                    filterData.setFieldChanged("invoice_to");
                    String invoiceValue = customerService.getInvoiceValue(filterData.getInvoiceTo()).get(0);
                    customerService.insertCustomerInfoHistory(filterData);
                    invoiceTo.add("");
                    invoiceTo.add(invoiceValue);
                    newHash.put("Invoice To", invoiceTo);
                    filterData.setChangedData(newHash);
                    changeCount++;
                 
                }else if(selectedCustomerData.get(0).getInvoiceTo() != null && filterData.getInvoiceTo() != null){
                        if(selectedCustomerData.get(0).getInvoiceTo().equals(filterData.getInvoiceTo())){

                        }else{

                        filterData.setTableName("customers");
                        filterData.setTableId(selectedCustomerData.get(0).getCustID());
                        filterData.setChangedBy(selectedCustomerData.get(0).getSalesPersonRefId());
                        filterData.setChangedFrom(selectedCustomerData.get(0).getInvoiceTo());
                        filterData.setChangedTo(filterData.getInvoiceTo());
                        filterData.setFieldChanged("invoice_to");
                        String invoiceValue = customerService.getInvoiceValue(filterData.getInvoiceTo()).get(0);
                        String oldInvoice = customerService.getInvoiceValue(selectedCustomerData.get(0).getInvoiceTo()).get(0);
                        customerService.insertCustomerInfoHistory(filterData);
                        invoiceTo.add(oldInvoice);
                        invoiceTo.add(invoiceValue);
                        newHash.put("Invoice To", invoiceTo);
                        filterData.setChangedData(newHash);
                        changeCount++;
                    }
                }
                 
                 
                 
//                 -------------------------------------
//                 System.out.println("new  file name>>>>>>>"+filterData.getAttachmentValue().getOriginalFilename());
//                 System.out.println("selected file name "+selectedCustomerData.get(0).getAttchedFileName());
//                 System.out.println("file name>>>>>>>>>"+selectedCustomerData.get(0).getFileName());
//                 String [] fileTe4sting = selectedCustomerData.get(0).getFileName().split("~");
//                 System.out.println("aaa 1"+fileTe4sting[0]+"2nd>>>>>"+fileTe4sting[1]+"3rd>>>>"+fileTe4sting[2]);
////                 String filename = selectedCustomerData.get(0).getAttachmentValue().getOriginalFilename();
////                 System.out.println("hhhhhhhhhhhhhh"+filename);
//                 //System.out.println("old file nasme>>>>"+fileTe4sting);
//                 System.out.println("inserted ccccc AttachmentValue ............history old value as empty"+selectedCustomerData.get(0).getAttchedFileName()+"file name"+selectedCustomerData.get(0).getFileName());
//                 
//                 if(selectedCustomerData.get(0).getAttchedFileName() == null && filterData.getAttchedFileName() != null){
//                     System.out.println("new  file name>>>>>>>"+filterData.getAttchedFileName());
//                     System.out.println("inserted ccccc AttachmentValue ............history old value as empty"+filterData.getAttachmentValue().toString());
//                    filterData.setTableName("customers");
//                    filterData.setTableId(selectedCustomerData.get(0).getCustID());
//                    filterData.setChangedBy(selectedCustomerData.get(0).getSalesPersonRefId());
//                    filterData.setChangedFrom(selectedCustomerData.get(0).getAttchedFileName());
//                    filterData.setChangedTo(filterData.getAttchedFileName());
//                    filterData.setFieldChanged("attachments");
//                    System.out.println("end inserted AttachmentValue ............history");
//                    customerService.insertCustomerInfoHistory(filterData);
//                    AttachmentValue.add("");
//                    AttachmentValue.add(filterData.getAttchedFileName());
//                    newHash.put("Attachment Value", AttachmentValue);
//                    filterData.setChangedData(newHash);
//                    changeCount++;
//                 
//                }else if(selectedCustomerData.get(0).getAttchedFileName() != null && filterData.getAttchedFileName() != null){
//                        if(selectedCustomerData.get(0).getAttchedFileName().equals(filterData.getAttchedFileName())){
//
//                        }else{
//                            System.out.println("new  file name>>>>>>>"+filterData.getAttchedFileName());
//                            System.out.println("inserted AttachmentValue ............history new value and old value are not same"+selectedCustomerData.get(0).getAttchedFileName()+"~~~~~~~~~~~~~~~~~~~~~~~~~"+filterData.getAttachmentValue().toString()+"filename"+filterData.getFileName());
//                            filterData.setTableName("customers");
//                            filterData.setTableId(selectedCustomerData.get(0).getCustID());
//                            filterData.setChangedBy(selectedCustomerData.get(0).getSalesPersonRefId());
//                            filterData.setChangedFrom(selectedCustomerData.get(0).getAttchedFileName());
//                            filterData.setChangedTo(filterData.getAttchedFileName());
//                            filterData.setFieldChanged("attachments");
//                            System.out.println("end inserted AttachmentValue ............history");
//                            customerService.insertCustomerInfoHistory(filterData);
//                            AttachmentValue.add(selectedCustomerData.get(0).getAttchedFileName());
//                            AttachmentValue.add(filterData.getAttchedFileName());
//                            newHash.put("Attachment Type", AttachmentValue);
//                            filterData.setChangedData(newHash);
//                            changeCount++;
//                        }
//                }
                
//                List CustomerDivision = new ArrayList();
//                System.out.println("length>>>>>"+filterData.getCustomerDivisionListName().length);
//                for(int i=0;i<filterData.getCustomerDivisionListName().length;i++){
//                    if(trim(selectedCustomerData.get(0).getCustomerDivision()) == null && trim(filterData.getCustomerDivisionListName()[0]) != null){
//                        System.out.println("inserted division1 ............history");
//                        filterData.setTableName("customers");
//                        filterData.setTableId(selectedCustomerData.get(0).getCustID());
//                        filterData.setChangedBy(selectedCustomerData.get(0).getSalesPersonRefId());
//                        filterData.setChangedFrom("");
//                        filterData.setChangedTo(filterData.getCustomerDivisionListName()[0]);
//                        filterData.setFieldChanged("division");
//                        System.out.println("end division1 ............history");
//                        customerService.insertCustomerInfoHistory(filterData);
//                        CustomerDivision.add("");
//                        CustomerDivision.add(filterData.getCustomerDivisionListName()[0]);
//                        newHash.put("Customer Division", CustomerDivision);
//                        filterData.setChangedData(newHash);
//                    }else if(selectedCustomerData.get(0).getCustomerDivision() != null && filterData.getCustomerDivisionListName()[0] != null){
//                        if(selectedCustomerData.get(0).getCustomerDivision().equals(filterData.getCustomerDivisionListName()[0])){
//
//                        }else{
//
//
//                            System.out.println("inserted division ............history");
//                            filterData.setTableName("customers");
//                            filterData.setTableId(selectedCustomerData.get(0).getCustID());
//                            filterData.setChangedBy(selectedCustomerData.get(0).getSalesPersonRefId());
//                            filterData.setChangedFrom(selectedCustomerData.get(0).getCustomerDivision());
//                            filterData.setChangedTo(filterData.getCustomerDivisionListName()[0]);
//                            filterData.setFieldChanged("division");
//                            System.out.println("end division ............history");
//                            customerService.insertCustomerInfoHistory(filterData);
//                            CustomerDivision.add(selectedCustomerData.get(0).getCustomerDivision());
//                            CustomerDivision.add(filterData.getCustomerDivisionListName()[0]);
//                            newHash.put("Customer Division", CustomerDivision);
//                            filterData.setChangedData(newHash);
//                        }
//                    }
//                }



               
    //            //7
//                if(selectedCustomerData.get(0).getTermsOfPayment()==null && filterData.getTermsOfPayment() != null){
//                    if(selectedCustomerData.get(0).getTermsOfPayment().equals(filterData.getTermsOfPayment())){
//
//                    }else{
//                        List termsPayment = new ArrayList();
//                        System.out.println("inserted terms_of_payment ............history");
//                        filterData.setTableName("customers");
//                        filterData.setTableId(selectedCustomerData.get(0).getCustID());
//                        filterData.setChangedBy(selectedCustomerData.get(0).getSalesPersonRefId());
//                        filterData.setChangedFrom("");
//                        filterData.setChangedTo(filterData.getTermsOfPayment());
//                        filterData.setFieldChanged("terms_of_payment");
//                        System.out.println("end terms_of_payment ............history");
//                        customerService.insertCustomerInfoHistory(filterData);
//                        termsPayment.add("");
//                        termsPayment.add(filterData.getTermsOfPayment());
//                        newHash.put("Terms of Payment", termsPayment);
//                        filterData.setChangedData(newHash);
//                    }
//                }else if(selectedCustomerData.get(0).getTermsOfPayment() != null && filterData.getTermsOfPayment() != null){
//                    if(selectedCustomerData.get(0).getTermsOfPayment().equals(filterData.getTermsOfPayment())){
//                        
//                    }else{
//                        List termsPayment = new ArrayList();
//                        System.out.println("inserted terms_of_payment ............history");
//                        filterData.setTableName("customers");
//                        filterData.setTableId(selectedCustomerData.get(0).getCustID());
//                        filterData.setChangedBy(selectedCustomerData.get(0).getSalesPersonRefId());
//                        filterData.setChangedFrom(selectedCustomerData.get(0).getTermsOfPayment());
//                        filterData.setChangedTo(filterData.getTermsOfPayment());
//                        filterData.setFieldChanged("terms_of_payment");
//                        System.out.println("end terms_of_payment ............history");
//                        customerService.insertCustomerInfoHistory(filterData);
//                        termsPayment.add(selectedCustomerData.get(0).getTermsOfPayment());
//                        termsPayment.add(filterData.getTermsOfPayment());
//                        newHash.put("Terms of Payment", termsPayment);
//                        filterData.setChangedData(newHash);
//                    }
//                }
                //TAN Number
//                if(selectedCustomerData.get(0).getTanNumber()==null && filterData.getTanNumber() != null){
//                    if(selectedCustomerData.get(0).getTanNumber().equals(filterData.getTanNumber())){
//
//                    }else{
//                        List tanNumber = new ArrayList();
//                        System.out.println("inserted tan_number ............history");
//                        filterData.setTableName("customers");
//                        filterData.setTableId(selectedCustomerData.get(0).getCustID());
//                        filterData.setChangedBy(selectedCustomerData.get(0).getSalesPersonRefId());
//                        filterData.setChangedFrom("");
//                        filterData.setChangedTo(filterData.getTanNumber());
//                        filterData.setFieldChanged("tan_number");
//                        System.out.println("end tan_number ............history");
//                        customerService.insertCustomerInfoHistory(filterData);
//                        tanNumber.add("");
//                        tanNumber.add(filterData.getTanNumber());
//                        newHash.put("Tan Number", tanNumber);
//                        filterData.setChangedData(newHash);
//                        changeCount++;
//                    }
//                }else if(selectedCustomerData.get(0).getTanNumber() != null && filterData.getTanNumber() != null){
//                    if(selectedCustomerData.get(0).getTanNumber().equals(filterData.getTanNumber())){
//                        
//                    }else{
//                        List tanNumber = new ArrayList();
//                        System.out.println("inserted tan_number ............history");
//                        filterData.setTableName("customers");
//                        filterData.setTableId(selectedCustomerData.get(0).getCustID());
//                        filterData.setChangedBy(selectedCustomerData.get(0).getSalesPersonRefId());
//                        filterData.setChangedFrom(selectedCustomerData.get(0).getTanNumber());
//                        filterData.setChangedTo(filterData.getTanNumber());
//                        filterData.setFieldChanged("tan_number");
//                        System.out.println("end tan_number ............history");
//                        customerService.insertCustomerInfoHistory(filterData);
//                        tanNumber.add(selectedCustomerData.get(0).getTanNumber());
//                        tanNumber.add(filterData.getTanNumber());
//                        newHash.put("Terms of Payment", tanNumber);
//                        filterData.setChangedData(newHash);
//                        changeCount++;
//                    }
//                }
    //            //8
                List aboutCustomer = new ArrayList();
                if((selectedCustomerData.get(0).getAboutCustomer() == null && filterData.getAboutCustomer() !=null)){
                    filterData.setTableName("customers");
                    filterData.setTableId(selectedCustomerData.get(0).getCustID());
                    filterData.setChangedBy(selectedCustomerData.get(0).getSalesPersonRefId());
                    filterData.setChangedFrom("");
                    filterData.setChangedTo(filterData.getAboutCustomer());
                    filterData.setFieldChanged("about_customer");
                    customerService.insertCustomerInfoHistory(filterData);
                    
                    aboutCustomer.add("");
                    aboutCustomer.add(filterData.getAboutCustomer());
                    newHash.put("About Customer", aboutCustomer);
                    filterData.setChangedData(newHash);
                    changeCount++;
                }else if((selectedCustomerData.get(0).getAboutCustomer() != null && filterData.getAboutCustomer() !=null)){
                    if(selectedCustomerData.get(0).getAboutCustomer().equals(filterData.getAboutCustomer())){

                    }else{
                        filterData.setTableName("customers");
                        filterData.setTableId(selectedCustomerData.get(0).getCustID());
                        filterData.setChangedBy(selectedCustomerData.get(0).getSalesPersonRefId());
                        filterData.setChangedFrom(selectedCustomerData.get(0).getAboutCustomer());
                        filterData.setChangedTo(filterData.getAboutCustomer());
                        filterData.setFieldChanged("about_customer");
                        customerService.insertCustomerInfoHistory(filterData);
                        aboutCustomer.add(selectedCustomerData.get(0).getAboutCustomer());
                        aboutCustomer.add(filterData.getAboutCustomer());
                        newHash.put("About Customer", aboutCustomer);
                        filterData.setChangedData(newHash);
                        changeCount++;
                    }
                }
                //9
                
//                for (int i = 0; i < filterData.getCustomerContactPersonNew().length; i++) {
//                    
//                }
//             
  
        // getting the contact details changed data for mail content
        
        List<CustomerAddDTO> selectedCustomerContactDetails = customerService.fetchCustomerContactDetails(filterData);
        List ContactDetailsName = new ArrayList();
        List ContactDetailsDesignation = new ArrayList();
        List ContactDetailsEmail = new ArrayList();
        List ContactDetailsPhoneNo = new ArrayList();
        if(filterData.getCustomerContactID()!=null){
            for (int i = 0; i < filterData.getCustomerContactID().length; i++) {
                if(filterData.getCustomerContactPerson()[i].equals(selectedCustomerContactDetails.get(i).getContactPerson())){

                }else{
                       filterData.setTableName("customer_contacts");
                       filterData.setTableId(selectedCustomerContactDetails.get(0).getId());
                       filterData.setChangedBy(selectedCustomerData.get(0).getSalesPersonRefId());
                       filterData.setChangedFrom(selectedCustomerContactDetails.get(i).getContactPerson());
                       filterData.setChangedTo(filterData.getCustomerContactPerson()[i]);
                       filterData.setFieldChanged("contact_person_name");
                       customerService.insertCustomerInfoHistory(filterData);
                       ContactDetailsName.add(selectedCustomerContactDetails.get(i).getContactPerson());
                       ContactDetailsName.add(filterData.getCustomerContactPerson()[i]);
                       newHash.put("Contact Name", ContactDetailsName);
                       filterData.setChangedData(newHash);
                       changeCount++;
                }

                if(filterData.getCustomerContactEmail()[i].equals(selectedCustomerContactDetails.get(i).getContactEmail())){

                }else{
                    filterData.setTableName("customer_contacts");
                    filterData.setTableId(selectedCustomerContactDetails.get(0).getId());
                    filterData.setChangedBy(selectedCustomerData.get(0).getSalesPersonRefId());
                    filterData.setChangedFrom(selectedCustomerContactDetails.get(i).getContactEmail());
                    filterData.setChangedTo(filterData.getCustomerContactEmail()[i]);
                    filterData.setFieldChanged("contact_person_email");
                    customerService.insertCustomerInfoHistory(filterData);

                    ContactDetailsEmail.add(selectedCustomerContactDetails.get(i).getContactEmail());
                    ContactDetailsEmail.add(filterData.getCustomerContactEmail()[i]);
                    newHash.put("Contact Email", ContactDetailsEmail);
                    filterData.setChangedData(newHash);
                    changeCount++;
                }

                if(filterData.getCustomerContactDesignation()[i].equals(selectedCustomerContactDetails.get(i).getContactDesignation())){

                }else{
                    filterData.setTableName("customer_contacts");
                    filterData.setTableId(selectedCustomerContactDetails.get(0).getId());
                    filterData.setChangedBy(selectedCustomerData.get(0).getSalesPersonRefId());
                    filterData.setChangedFrom(selectedCustomerContactDetails.get(i).getContactDesignation());
                    filterData.setChangedTo(filterData.getCustomerContactDesignation()[i]);
                    filterData.setFieldChanged("designation");
                    customerService.insertCustomerInfoHistory(filterData);

                    ContactDetailsDesignation.add(selectedCustomerContactDetails.get(i).getContactDesignation());
                    ContactDetailsDesignation.add(filterData.getCustomerContactDesignation()[i]);
                    newHash.put("Contact Designation", ContactDetailsDesignation);
                    filterData.setChangedData(newHash);
                    changeCount++;
                }


                if(filterData.getCustomerContactPhone()[i].equals(selectedCustomerContactDetails.get(i).getContactPhone())){

                }else{
                    filterData.setTableName("customer_contacts");
                    filterData.setTableId(selectedCustomerContactDetails.get(0).getId());
                    filterData.setChangedBy(selectedCustomerData.get(0).getSalesPersonRefId());
                    filterData.setChangedFrom(selectedCustomerContactDetails.get(i).getContactPhone());
                    filterData.setChangedTo(filterData.getCustomerContactPhone()[i]);
                    filterData.setFieldChanged("contact_person_mobile");
                    customerService.insertCustomerInfoHistory(filterData);

                    ContactDetailsPhoneNo.add(selectedCustomerContactDetails.get(i).getContactPhone());
                    ContactDetailsPhoneNo.add(filterData.getCustomerContactPhone()[i]);
                    newHash.put("Contact Phone No", ContactDetailsPhoneNo);
                    filterData.setChangedData(newHash);
                    changeCount++;
                }
            }
        }

        //getting customer finance contact changed content
        List<CustomerAddDTO> selectedFianceContactDetails = customerService.fetchCustomerFinanceContactDetails(filterData);
        List financeDetailsName = new ArrayList();
        List financeDetailsDesignation = new ArrayList();
        List financeDetailsEmail = new ArrayList();
        List financeDetailsPhoneNo = new ArrayList();
        if (filterData.getCustomerFinanceContactID() != null){
            for (int i = 0; i < filterData.getCustomerFinanceContactID().length; i++) {
                if(filterData.getCustomerFinanceContactPerson()[i].equals(selectedFianceContactDetails.get(i).getContactPerson())){

                }else{
                    System.out.println("inserted Customer contact_person_name ............history");
                    filterData.setTableName("customer_contacts");
                    filterData.setTableId(selectedFianceContactDetails.get(0).getId());
                    filterData.setChangedBy(selectedCustomerData.get(0).getSalesPersonRefId());
                    filterData.setChangedFrom(selectedFianceContactDetails.get(i).getContactPerson());
                    filterData.setChangedTo(filterData.getCustomerFinanceContactPerson()[i]);
                    filterData.setFieldChanged("contact_person_name");
                    System.out.println("end Customer contact_person_name ............history");
                    customerService.insertCustomerInfoHistory(filterData);

                    financeDetailsName.add(selectedFianceContactDetails.get(i).getContactPerson());
                    financeDetailsName.add(filterData.getCustomerFinanceContactPerson()[i]);
                    newHash.put("Finance Contact Name", financeDetailsName);
                    filterData.setChangedData(newHash);
                    changeCount++;
                }

                if(filterData.getCustomerFinanceContactEmail()[i].equals(selectedFianceContactDetails.get(i).getContactEmail())){

                }else{
                    System.out.println("inserted Customer contact_person_email ............history");
                    filterData.setTableName("customer_contacts");
                    filterData.setTableId(selectedFianceContactDetails.get(0).getId());
                    filterData.setChangedBy(selectedCustomerData.get(0).getSalesPersonRefId());
                    filterData.setChangedFrom(selectedFianceContactDetails.get(i).getContactEmail());
                    filterData.setChangedTo(filterData.getCustomerFinanceContactEmail()[i]);
                    filterData.setFieldChanged("contact_person_email");
                    System.out.println("end Customer contact_person_email ............history");
                    customerService.insertCustomerInfoHistory(filterData);

                    financeDetailsEmail.add(selectedFianceContactDetails.get(i).getContactEmail());
                    financeDetailsEmail.add(filterData.getCustomerFinanceContactEmail()[i]);
                    newHash.put("Finance Contact Email", financeDetailsEmail);
                    filterData.setChangedData(newHash);
                    changeCount++;
                }

                if(filterData.getCustomerFinanceContactDesignation()[i].equals(selectedFianceContactDetails.get(i).getContactDesignation())){

                }else{
                    System.out.println("inserted Customer contact_person_email ............history");
                    filterData.setTableName("customer_contacts");
                    filterData.setTableId(selectedFianceContactDetails.get(0).getId());
                    filterData.setChangedBy(selectedCustomerData.get(0).getSalesPersonRefId());
                    filterData.setChangedFrom(selectedFianceContactDetails.get(i).getContactDesignation());
                    filterData.setChangedTo(filterData.getCustomerFinanceContactDesignation()[i]);
                    filterData.setFieldChanged("contact_person_email");
                    System.out.println("end Customer contact_person_email ............history");
                    customerService.insertCustomerInfoHistory(filterData);

                    financeDetailsDesignation.add(selectedFianceContactDetails.get(i).getContactDesignation());
                    financeDetailsDesignation.add(filterData.getCustomerFinanceContactDesignation()[i]);
                    newHash.put("Finance Contact Email", financeDetailsDesignation);
                    filterData.setChangedData(newHash);
                    changeCount++;
                }

                if(filterData.getCustomerFinanceContactPhone()[i].equals(selectedFianceContactDetails.get(i).getContactPhone())){

                }else{
                    System.out.println("inserted Customer contact_person_mobile ............history");
                    filterData.setTableName("customer_contacts");
                    filterData.setTableId(selectedFianceContactDetails.get(0).getId());
                    filterData.setChangedBy(selectedCustomerData.get(0).getSalesPersonRefId());
                    filterData.setChangedFrom(selectedFianceContactDetails.get(i).getContactPhone());
                    filterData.setChangedTo(filterData.getCustomerFinanceContactPhone()[i]);
                    filterData.setFieldChanged("contact_person_mobile");
                    System.out.println("end Customer contact_person_mobile ............history");
                    customerService.insertCustomerInfoHistory(filterData);

                    financeDetailsPhoneNo.add(selectedFianceContactDetails.get(i).getContactPhone());
                    financeDetailsPhoneNo.add(filterData.getCustomerFinanceContactPhone()[i]);
                    newHash.put("Finance Contact Phone No", financeDetailsPhoneNo);
                    filterData.setChangedData(newHash);
                    changeCount++;
                }
            }
        }

        //getting the customer work location changed details
        List<CustomerAddDTO> selectedCustomerWorkLocations = customerService.fetchCustomerWorkLocations(filterData);
        List ContactWorkAddress = new ArrayList();
        List ContactWorkShortCode = new ArrayList();
        List ContactWorkCountry = new ArrayList();
        List ContactWorkRegion = new ArrayList();
        List ContactWorkCity = new ArrayList();
        List ContactWorkPincode = new ArrayList();
        List ContactWorkHours = new ArrayList();
        System.out.println("length  "+filterData.getCustomerWorkLocationID().length);
        if (filterData.getCustomerWorkLocationID() != null) {
            for(int i = 0; i < filterData.getCustomerWorkLocationID().length; i++){
                if(filterData.getCustomerWorkAddress()[i].equals(selectedCustomerWorkLocations.get(i).getCustomerAddress())){

                }else{
                       System.out.println("inserted Customer address_line ............history");
                       filterData.setTableName("customer_work_locations");
                       filterData.setTableId(selectedCustomerWorkLocations.get(0).getId());
                       filterData.setChangedBy(selectedCustomerData.get(0).getSalesPersonRefId());
                       filterData.setChangedFrom(selectedCustomerWorkLocations.get(i).getCustomerAddress());
                       filterData.setChangedTo(filterData.getCustomerWorkAddress()[i]);
                       filterData.setFieldChanged("address_line");
                       System.out.println("end Customer address_line ............history");
                       customerService.insertCustomerInfoHistory(filterData);

                       ContactWorkAddress.add(selectedCustomerWorkLocations.get(i).getCustomerAddress());
                       ContactWorkAddress.add(filterData.getCustomerWorkAddress()[i]);
                       newHash.put("Customer Work Address", ContactWorkAddress);
                       filterData.setChangedData(newHash);
                       changeCount++;
                }
                if(filterData.getCustomerWorkShortCode()[i].equals(selectedCustomerWorkLocations.get(i).getAddressShortCode())){

                }else{
                       System.out.println("inserted Customer address_shortcode ............history");
                       filterData.setTableName("customer_work_locations");
                       filterData.setTableId(selectedCustomerWorkLocations.get(0).getId());
                       filterData.setChangedBy(selectedCustomerData.get(0).getSalesPersonRefId());
                       filterData.setChangedFrom(selectedCustomerWorkLocations.get(i).getAddressShortCode());
                       filterData.setChangedTo(filterData.getCustomerWorkShortCode()[i]);
                       filterData.setFieldChanged("address_shortcode");
                       System.out.println("end Customer address_shortcode ............history");
                       customerService.insertCustomerInfoHistory(filterData);

                       ContactWorkShortCode.add(selectedCustomerWorkLocations.get(i).getAddressShortCode());
                       ContactWorkShortCode.add(filterData.getCustomerWorkShortCode()[i]);
                       newHash.put("Customer Work Short Code", ContactWorkShortCode);
                       filterData.setChangedData(newHash);
                       changeCount++;
                }
//                List<CustomerAddDTO> countryName = customerService.getCountryName(filterData.getCustomerWorkCountry()[i]);
//                if(countryName.get(0).getCountryName().equals(selectedCustomerWorkLocations.get(i).getCountry())){
//
//                }else{
//                    System.out.println("inserted Customer country_id ............history");
//                    filterData.setTableName("customer_work_locations");
//                    filterData.setTableId(selectedCustomerWorkLocations.get(0).getId());
//                    filterData.setChangedBy(selectedCustomerData.get(0).getSalesPersonRefId());
//                    filterData.setChangedFrom(selectedCustomerWorkLocations.get(i).getCountry());
//                    filterData.setChangedTo(filterData.getCustomerWorkCountry()[i]);
//                    filterData.setFieldChanged("country_id");
//                    System.out.println("end Customer country_id ............history");
//                    customerService.insertCustomerInfoHistory(filterData);
//
//                    ContactWorkCountry.add(selectedCustomerWorkLocations.get(i).getCountry());
//                    ContactWorkCountry.add(countryName.get(0).getCountryName());
//                    newHash.put("Customer Work Country", ContactWorkCountry);
//                    filterData.setChangedData(newHash);
//                    changeCount++;
//                }
//                System.out.println("region "+filterData.getCustomerWorkRegion()[i]+" "+i);
//                List<CustomerAddDTO> regionName = customerService.getRegionName(filterData.getCustomerWorkRegion()[i]);
//                if(regionName.get(0).getRegionName().equals(selectedCustomerWorkLocations.get(i).getRegion())){
//
//                }else{
//                    System.out.println("inserted Customer region_id ............history");
//                    filterData.setTableName("customer_work_locations");
//                    filterData.setTableId(selectedCustomerWorkLocations.get(0).getId());
//                    filterData.setChangedBy(selectedCustomerData.get(0).getSalesPersonRefId());
//                    filterData.setChangedFrom(selectedCustomerWorkLocations.get(i).getRegion());
//                    filterData.setChangedTo(filterData.getCustomerWorkRegion()[i]);
//                    filterData.setFieldChanged("region_id");
//                    System.out.println("end Customer region_id ............history");
//                    customerService.insertCustomerInfoHistory(filterData);
//
//
//                    ContactWorkRegion.add(selectedCustomerWorkLocations.get(i).getRegion());
//                    ContactWorkRegion.add(regionName.get(0).getRegionName());
//                    newHash.put("Customer Work Region", ContactWorkRegion);
//                    filterData.setChangedData(newHash);
//                    changeCount++;
//                }
//
//                List<CustomerAddDTO> cityName = customerService.getCityName(filterData.getCustomerWorkCity()[i]);
//                if(cityName.get(0).getCityName().equals(selectedCustomerWorkLocations.get(i).getCity())){
//
//                }else{
//                    System.out.println("inserted Customer city_id ............history");
//                    filterData.setTableName("customer_work_locations");
//                    filterData.setTableId(selectedCustomerWorkLocations.get(0).getId());
//                    filterData.setChangedBy(selectedCustomerData.get(0).getSalesPersonRefId());
//                    filterData.setChangedFrom(selectedCustomerWorkLocations.get(i).getCity());
//                    filterData.setChangedTo(filterData.getCustomerWorkCity()[i]);
//                    filterData.setFieldChanged("city_id");
//                    System.out.println("end Customer city_id ............history");
//                    customerService.insertCustomerInfoHistory(filterData);
//
//                    ContactWorkCity.add(selectedCustomerWorkLocations.get(i).getCity());
//                    ContactWorkCity.add(cityName.get(0).getCityName());
//                    newHash.put("Customer Work City", ContactWorkCity);
//                    filterData.setChangedData(newHash);
//                    changeCount++;
//                }

                if(filterData.getCustomerWorkPincode()[i].equals(selectedCustomerWorkLocations.get(i).getPincode())){

                }else{
                    System.out.println("inserted Customer zip_code ............history");
                    filterData.setTableName("customer_work_locations");
                    filterData.setTableId(selectedCustomerWorkLocations.get(0).getId());
                    filterData.setChangedBy(selectedCustomerData.get(0).getSalesPersonRefId());
                    filterData.setChangedFrom(selectedCustomerWorkLocations.get(i).getPincode());
                    filterData.setChangedTo(filterData.getCustomerWorkPincode()[i]);
                    filterData.setFieldChanged("zip_code");
                    System.out.println("end Customer zip_code ............history");
                    customerService.insertCustomerInfoHistory(filterData);

                    ContactWorkPincode.add(selectedCustomerWorkLocations.get(i).getPincode());
                    ContactWorkPincode.add(filterData.getCustomerWorkPincode()[i]);
                    newHash.put("Customer Work Pincode", ContactWorkPincode);
                    filterData.setChangedData(newHash);
                    changeCount++;
                }

                if(filterData.getWorkingHoursPerDay()[i].equals(selectedCustomerWorkLocations.get(i).getWorkLocationWorkingHours())){

                }else{
                    System.out.println("inserted Customer customer_working_hrs ............history");
                    filterData.setTableName("customer_work_locations");
                    filterData.setTableId(selectedCustomerWorkLocations.get(0).getId());
                    filterData.setChangedBy(selectedCustomerData.get(0).getSalesPersonRefId());
                    filterData.setChangedFrom(selectedCustomerWorkLocations.get(i).getWorkLocationWorkingHours());
                    filterData.setChangedTo(filterData.getWorkingHoursPerDay()[i]);
                    filterData.setFieldChanged("customer_working_hrs");
                    System.out.println("end Customer customer_working_hrs ............history");
                    customerService.insertCustomerInfoHistory(filterData);

                    ContactWorkHours.add(selectedCustomerWorkLocations.get(i).getWorkLocationWorkingHours());
                    ContactWorkHours.add(filterData.getWorkingHoursPerDay()[i]);
                    newHash.put("Customer Work Hours", ContactWorkHours);
                    filterData.setChangedData(newHash);
                    changeCount++;
                }
            }
        }

        //checking billing address details
        List<CustomerAddDTO> selectedBillingAddress = customerService.fetchBillingAddress(filterData);
        List billingkAddress = new ArrayList();
        List billingShortCode = new ArrayList();
        List billingCountry = new ArrayList();
        List billingRegion = new ArrayList();
        List billingCity = new ArrayList();
        List billingPincode = new ArrayList();
        if (filterData.getCustomerBillingID() != null) {
            System.out.println("billing length "+filterData.getCustomerBillingID().length);
            for(int i = 0; i < filterData.getCustomerBillingID().length; i++){
                if(filterData.getCustomerBillingAddress()[i].equals(selectedBillingAddress.get(i).getCustomerAddress())){

                }else{
                    System.out.println("inserted Customer address_line ............history");
                    filterData.setTableName("customer_billing_addresses");
                    filterData.setTableId(selectedBillingAddress.get(0).getId());
                    filterData.setChangedBy(selectedCustomerData.get(0).getSalesPersonRefId());
                    filterData.setChangedFrom(selectedBillingAddress.get(i).getCustomerAddress());
                    filterData.setChangedTo(filterData.getCustomerBillingAddress()[i]);
                    filterData.setFieldChanged("address_line");
                    System.out.println("end Customer address_line ............history");
                    customerService.insertCustomerInfoHistory(filterData);

                    billingkAddress.add(selectedBillingAddress.get(i).getCustomerAddress());
                    billingkAddress.add(filterData.getCustomerBillingAddress()[i]);
                    newHash.put("Customer Billing Address", billingkAddress);
                    filterData.setChangedData(newHash);
                    changeCount++;
                }
                System.out.println("short code new"+filterData.getCustomerBillingShortCode()[i]);
                System.out.println("short code new"+selectedBillingAddress.get(i).getAddressShortCode());
                if(filterData.getCustomerBillingShortCode()[i].equals(selectedBillingAddress.get(i).getAddressShortCode())){

                }else{
                    System.out.println("inserted Customer address_shortcode ............history");
                    filterData.setTableName("customer_billing_addresses");
                    filterData.setTableId(selectedBillingAddress.get(0).getId());
                    filterData.setChangedBy(selectedCustomerData.get(0).getSalesPersonRefId());
                    filterData.setChangedFrom(selectedBillingAddress.get(i).getAddressShortCode());
                    filterData.setChangedTo(filterData.getCustomerBillingShortCode()[i]);
                    filterData.setFieldChanged("address_shortcode");
                    System.out.println("end Customer address_shortcode ............history");
                    customerService.insertCustomerInfoHistory(filterData);

                    billingShortCode.add(selectedBillingAddress.get(i).getAddressShortCode());
                    billingShortCode.add(filterData.getCustomerBillingShortCode()[i]);
                    newHash.put("Customer Billing Short Code", billingShortCode);
                    filterData.setChangedData(newHash);
                    changeCount++;
                }
                List<CustomerAddDTO> countryName = customerService.getCountryName(filterData.getCustomerBillingCountry()[i]);
                if(countryName.get(0).getCountryName().equals(selectedBillingAddress.get(i).getCountry())){

                }else{
                    System.out.println("inserted Customer country_id ............history");
                    filterData.setTableName("customer_billing_addresses");
                    filterData.setTableId(selectedBillingAddress.get(0).getId());
                    filterData.setChangedBy(selectedCustomerData.get(0).getSalesPersonRefId());
                    filterData.setChangedFrom(selectedBillingAddress.get(i).getCountry());
                    filterData.setChangedTo(filterData.getCustomerBillingCountry()[i]);
                    filterData.setFieldChanged("country_id");
                    System.out.println("end Customer country_id ............history");
                    customerService.insertCustomerInfoHistory(filterData);

                    billingCountry.add(selectedBillingAddress.get(i).getCountry());
                    billingCountry.add(countryName.get(0).getCountryName());
                    newHash.put("Customer Billing Country", billingCountry);
                    filterData.setChangedData(newHash);
                    changeCount++;
                }

                List<CustomerAddDTO> regionName = customerService.getRegionName(filterData.getCustomerBillingRegion()[i]);
                if(regionName.get(0).getRegionName().equals(selectedBillingAddress.get(i).getRegion())){

                }else{
                    System.out.println("inserted Customer region_id ............history");
                    filterData.setTableName("customer_billing_addresses");
                    filterData.setTableId(selectedBillingAddress.get(0).getId());
                    filterData.setChangedBy(selectedCustomerData.get(0).getSalesPersonRefId());
                    filterData.setChangedFrom(selectedBillingAddress.get(i).getRegion());
                    filterData.setChangedTo(filterData.getCustomerBillingRegion()[i]);
                    filterData.setFieldChanged("region_id");
                    System.out.println("end Customer region_id ............history");
                    customerService.insertCustomerInfoHistory(filterData);

                    billingRegion.add(selectedBillingAddress.get(i).getRegion());
                    billingRegion.add(regionName.get(0).getRegionName());
                    newHash.put("Customer Billing Region", billingRegion);
                    filterData.setChangedData(newHash);
                    changeCount++;
                }

                List<CustomerAddDTO> cityName = customerService.getCityName(filterData.getCustomerBillingCity()[i]);
                if(cityName.get(0).getCityName().equals(selectedBillingAddress.get(i).getCity())){

                }else{
                    System.out.println("inserted Customer city_id ............history");
                    filterData.setTableName("customer_billing_addresses");
                    filterData.setTableId(selectedBillingAddress.get(0).getId());
                    filterData.setChangedBy(selectedCustomerData.get(0).getSalesPersonRefId());
                    filterData.setChangedFrom(selectedBillingAddress.get(i).getCity());
                    filterData.setChangedTo(filterData.getCustomerBillingCity()[i]);
                    filterData.setFieldChanged("city_id");
                    System.out.println("end Customer city_id ............history");
                    customerService.insertCustomerInfoHistory(filterData);

                    billingCity.add(selectedBillingAddress.get(i).getCity());
                    billingCity.add(cityName.get(0).getCityName());
                    newHash.put("Customer Billing City", billingCity);
                    filterData.setChangedData(newHash);
                    changeCount++;
                }

                if(filterData.getCustomerBillingPincode()[i].equals(selectedBillingAddress.get(i).getPincode())){

                }else{
                    System.out.println("inserted Customer zip_code ............history");
                    filterData.setTableName("customer_billing_addresses");
                    filterData.setTableId(selectedBillingAddress.get(0).getId());
                    filterData.setChangedBy(selectedCustomerData.get(0).getSalesPersonRefId());
                    filterData.setChangedFrom(selectedBillingAddress.get(i).getPincode());
                    filterData.setChangedTo(filterData.getCustomerBillingPincode()[i]);
                    filterData.setFieldChanged("zip_code");
                    System.out.println("end Customer zip_code ............history");
                    customerService.insertCustomerInfoHistory(filterData);

                    billingPincode.add(selectedBillingAddress.get(i).getPincode());
                    billingPincode.add(filterData.getCustomerBillingPincode()[i]);
                    newHash.put("Customer Billing Pincode", billingPincode);
                    filterData.setChangedData(newHash);
                    changeCount++;
                }
            }
        }
        //--------------------------------
        
        return changeCount;
    }
    
    public  ModelAndView userManualDownload(HttpServletRequest request, HttpServletResponse response) throws IOException, DocumentException {
        //Blank Document
        System.out.println("inside userManual");
        String filepath = "C:/wamp/www/iDeal/app/webroot/user_manual/"; 
        String filename = "CustomerMasterUserManual.pdf";  
        BufferedInputStream buf = null;
        ServletOutputStream myOut = null;
        System.out.println("file name "+filepath+filename);
            try{
                 myOut = response.getOutputStream(); 
                 File myfile = new File(filepath + filename);
                 //set response headers
            response.setContentType("application/pdf");         //I want to download a PDF file
           // response.addHeader("Content-Disposition", "inline;attachment; filename=" + filename);
		     response.setHeader("Content-Disposition", "inline; filename=" + filename); // for downloading the pdf format important
            response.setContentLength((int) myfile.length());
            FileInputStream input = new FileInputStream(myfile);
            buf = new BufferedInputStream(input);
            int readBytes = 0;
            
            //read from the file; write to the ServletOutputStream
            while ((readBytes = buf.read()) != -1) {
                myOut.write(readBytes);
            }
            
            }catch(Exception e){
                
            }
        return null;
        
    }
    
    public ModelAndView dunningReportList(HttpServletRequest request, HttpServletResponse response, CustomerGroupDTO filterData){
        ModelAndView mv = null;
        String employeeId = null;
        HttpSession session = request.getSession();
        if (session.getAttribute("EMP_ID") == null) {
            employeeId = request.getParameter("empid");
            session.setAttribute("EMP_ID", employeeId);
        } else {
            employeeId = (String) session.getAttribute("EMP_ID");
        }
        final WebApplicationContext ctx = getWebApplicationContext();
        CustomerServiceImpl customerService = (CustomerServiceImpl) ctx.getBean("CustomerService");
        mv = new ModelAndView("/dunningReportView");
        Map<String, String> month_list = CommonMethods.getMonthsList();
        Map<Integer, Integer> year_list = CommonMethods.getYearsList(5);
        Date curdate = new Date();
        DateFormat dateFrmt = new SimpleDateFormat("MM-yyyy");
        String current_date[] = dateFrmt.format(curdate).toString().split("-");
        String month = request.getParameter("month");
        String year = request.getParameter("year");
        String date = request.getParameter("date");
        String user_type = request.getParameter("user_type");
        if(month == null || month == ""){
            filterData.setCurrentMonth(current_date[0]);
        }else{
            filterData.setCurrentMonth(month);
        }
        if(year == null || year == ""){
            filterData.setCurrentYear(current_date[1]);
        }else{
            filterData.setCurrentYear(year);
        }
        if(date == null || date == ""){
            String max_dunning_date = customerService.getDunningMaxDate();
            filterData.setSelected_date(max_dunning_date);
        }else{
            filterData.setSelected_date(date);
        }
        if(user_type.equals("bdm")){
            filterData.setBdm_id(employeeId);
        }else{
            filterData.setBdm_id(null);
        }
        List<CustomerGroupDTO> dunning_report = customerService.getDunningList(filterData);
        List<CustomerGroupDTO> dunning_date = customerService.getDunningDate(filterData);
        mv.addObject("monthList", month_list);
        mv.addObject("yearList", year_list);
        mv.addObject("currentMonth", filterData.getCurrentMonth());
        mv.addObject("currentYear", filterData.getCurrentYear());
        mv.addObject("dunningReport", dunning_report);
        mv.addObject("dunning_date",dunning_date);
        mv.addObject("user_type",user_type);
        mv.addObject("selected_date", filterData.getSelected_date());
        return mv;
    }
    
    public ModelAndView dunningFileDownload(HttpServletRequest request, HttpServletResponse response, CustomerGroupDTO filterData) throws Exception {
        System.out.println("---" + filterData.getFile());
        String fileName = filterData.getFileName();
        String filePath = "E:\\dunning_report\\"+filterData.getDunningFolder();
        String fileType = filterData.getFileType();
        System.out.println("file name " + fileName + " file path " + filePath + " file type " + fileType);
        CommonMethods.fileDownload(fileName, filePath, fileType, response);
        return null;
    }
    
    public ModelAndView getDunningDate(HttpServletRequest request, HttpServletResponse response, CustomerGroupDTO objSearch) throws Exception {
        ModelAndView mv = null;
        mv = new ModelAndView("/dunningReportView");
        final WebApplicationContext ctx = getWebApplicationContext();
        CustomerServiceImpl customerService = (CustomerServiceImpl) ctx.getBean("CustomerService");
        objSearch.setCurrentMonth(request.getParameter("month"));
        objSearch.setCurrentYear(request.getParameter("year"));
        response.getWriter().println("<option value=''>" + "--Select Date--" + "</option>");
        for (CustomerGroupDTO dTO : (customerService.getDunningDate(objSearch))) {
            response.getWriter().println("<option value='" + dTO.getDunningDate() + "'>" + dTO.getDunningDate() + "</option>");
        }
        mv = new ModelAndView("/dunningReportView");
        return null;
    }
    
    public ModelAndView dunningStatusList(HttpServletRequest request, HttpServletResponse response, CustomerGroupDTO filterData){
        ModelAndView mv = null;
        mv = new ModelAndView("/dunningCustomerMapping");
        final WebApplicationContext ctx = getWebApplicationContext();
        CustomerServiceImpl customerService = (CustomerServiceImpl) ctx.getBean("CustomerService");
        String selected_status = request.getParameter("status");
        String selected_bdm = request.getParameter("bdm_id");
        filterData.setDunning_status(selected_status);
        filterData.setBdm_id(selected_bdm);
        List<CustomerGroupDTO> dunning_customer_list = customerService.getDunningCustomerList(filterData);
        List<CustomerGroupDTO> bdm_list = customerService.getBdmList();
        mv.addObject("customer_list", dunning_customer_list);
        mv.addObject("bdm_list",bdm_list);
        mv.addObject("selected_status",selected_status);
        mv.addObject("selected_bdm_id",selected_bdm);
        return mv;
    }
    
    public ModelAndView updateDunningStatus(HttpServletRequest request, HttpServletResponse response, CustomerGroupDTO filterData){
        ModelAndView mv = null;
        mv = new ModelAndView("/dunningCustomerMapping");
        final WebApplicationContext ctx = getWebApplicationContext();
        CustomerServiceImpl customerService = (CustomerServiceImpl) ctx.getBean("CustomerService");
        String customer_id = request.getParameter("customer_id");
        String dunning_status = request.getParameter("status");
        String bdm_id = request.getParameter("bdm_id");
        filterData.setCustomer_id(customer_id);
        filterData.setDunning_status(dunning_status);
        filterData.setBdm_id(bdm_id);
        customerService.updateDunningStatus(filterData);
        List<CustomerGroupDTO> dunning_customer_list = customerService.getDunningCustomerList(filterData);
        List<CustomerGroupDTO> bdm_list = customerService.getBdmList();
        mv.addObject("customer_list", dunning_customer_list);
        mv.addObject("bdm_list",bdm_list);
        mv.addObject("selected_status",dunning_status);
        mv.addObject("selected_bdm_id",bdm_id);
        return mv;
    }
    
    public ModelAndView dunningExport(HttpServletRequest request, HttpServletResponse response, CustomerGroupDTO filterData) throws Exception {
        final WebApplicationContext ctx = getWebApplicationContext();
        CustomerServiceImpl customerService = (CustomerServiceImpl) ctx.getBean("CustomerService");
        List<CustomerGroupDTO> customerData = customerService.getDebtorsExcel(filterData);

        ArrayList entireList = new ArrayList();
        if(customerData != null){
            for (int i = 0; i < customerData.size(); i++) {
                ArrayList rowDataList = new ArrayList();
                rowDataList.add(new String("" + customerData.get(i).getCustomer_code()));
                rowDataList.add(new String("" + customerData.get(i).getCustomer_name()));
                rowDataList.add(new String("" + customerData.get(i).getEntity_name()));
                rowDataList.add(new String("" + customerData.get(i).getInvoice_code()));
                rowDataList.add(new String("" + customerData.get(i).getInvoice_date()));
                rowDataList.add(new String("" + customerData.get(i).getDue_date()));
                rowDataList.add(new String("" + customerData.get(i).getDays_count()));
                rowDataList.add(new String("" + customerData.get(i).getCurrency_name()));
                rowDataList.add(new String("" + customerData.get(i).getTotal_amount()));
                rowDataList.add(new String("" + customerData.get(i).getBalance_amount()));
                rowDataList.add(new String("" + customerData.get(i).getBdm_name()));
                rowDataList.add(new String("" + customerData.get(i).getCustomer_email()));
                entireList.add(rowDataList);
            }
            Date curdate = new Date();
            DateFormat datetime = new SimpleDateFormat("dd_MM_yyyy_hh_mm");
            String current_date = datetime.format(curdate).toString();
            CommonMethods.exportToExcel(response, entireList, "Dunning_Report_as_on", "Customer Report", "_"+current_date );
        }
        return null;
    }
}
