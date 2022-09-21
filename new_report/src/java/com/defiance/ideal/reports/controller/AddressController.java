/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.controller;

import com.defiance.ideal.reports.dto.AddressDataDTO;
import com.defiance.ideal.reports.dto.AddressFilterDTO;
import com.defiance.ideal.reports.dto.EarnedLeaveDataDTO;
import com.defiance.ideal.reports.dto.EarnedLeaveFilterDTO;
import com.defiance.ideal.reports.service.AddressService;
import com.defiance.ideal.reports.service.AddressServiceImpl;
import com.defiance.ideal.reports.service.EarnedLeaveService;
import com.defiance.ideal.reports.service.EarnedLeaveServiceImpl;
import com.defiance.ideal.reports.util.CommonMethods;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 *
 * @author 8000246
 */
public class AddressController extends MultiActionController{
    public AddressController() {
    }
    
     
   public ModelAndView addressReport(HttpServletRequest request, HttpServletResponse response, AddressFilterDTO filterData) {
        ModelAndView mvc = null;
        mvc = new ModelAndView("/address");
        List<AddressDataDTO> addressRecord = null;
        try {
           final WebApplicationContext ctx= getWebApplicationContext();
            AddressService addressService =  (AddressServiceImpl) ctx.getBean("AddressService");
            
            addressRecord = addressService.getAddressRecord(filterData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("filterData.getEmployee_search()"+filterData.getEmployee_search());
        mvc.addObject("filterData", filterData);
        mvc.addObject("empName", filterData.getEmployee_search());
        mvc.addObject("address", addressRecord);
        return mvc;
    }
   public ModelAndView addressajaxsearch(HttpServletRequest request, HttpServletResponse response, AddressFilterDTO filterData) {
        ModelAndView mvc = null;
        mvc = new ModelAndView("/ajaxsearch");
        try {
            final WebApplicationContext ctx = getWebApplicationContext();
           AddressService addressService =  (AddressServiceImpl) ctx.getBean("AddressService");
            String empVal = request.getParameter("q");
      
            List<AddressDataDTO> empRes = addressService.getSearchList(empVal);
      
            mvc.addObject("empRes", empRes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mvc.addObject("filterData", filterData);
        return mvc;
    }
   public ModelAndView getAddressXL(HttpServletRequest request, HttpServletResponse response, AddressFilterDTO filterData) throws Exception {
        try {
         
         
            final WebApplicationContext ctx = getWebApplicationContext();
            AddressService addressService =  (AddressServiceImpl) ctx.getBean("AddressService");
            List<AddressDataDTO> addressRecordsList = addressService.getAddressRecord(filterData);

            Iterator itr = addressRecordsList.iterator();
        while (itr.hasNext()) {
            AddressDataDTO dtoj = (AddressDataDTO) itr.next();
           if(dtoj.getAddress_typeX()=="c" || dtoj.getAddress_typeX().equalsIgnoreCase("c")){
               dtoj.setAddress_typeX("Current");
           }
           if(dtoj.getAddress_typeX()=="o" || dtoj.getAddress_typeX().equalsIgnoreCase("o")){
               dtoj.setAddress_typeX("Office");
           }
           if(dtoj.getAddress_typeX()=="T" || dtoj.getAddress_typeX().equalsIgnoreCase("T")){
               dtoj.setAddress_typeX("Temporary");
           }
           if(dtoj.getAddress_typeX()=="p" || dtoj.getAddress_typeX().equalsIgnoreCase("p")){
               dtoj.setAddress_typeX("Permanent");
           }
        }
       
            ArrayList entireList = new ArrayList();
            for (int i = 0; i < addressRecordsList.size(); i++) {
                ArrayList rowDataList = new ArrayList();
              
                rowDataList.add(new String("" + addressRecordsList.get(i).getEmpId()));
                rowDataList.add(new String("" + addressRecordsList.get(i).getEmpName()));
                rowDataList.add(new String("" + addressRecordsList.get(i).getRmId()));
                rowDataList.add(new String("" + addressRecordsList.get(i).getRmName()));
              
                rowDataList.add(new String("" + addressRecordsList.get(i).getAddress_typeX()));
                rowDataList.add(new String("" + addressRecordsList.get(i).getAddress_line1X()));
                rowDataList.add(new String("" + addressRecordsList.get(i).getAddress_line2X()));
                rowDataList.add(new String("" + addressRecordsList.get(i).getCountry_idXY()));
                rowDataList.add(new String("" + addressRecordsList.get(i).getRegion_idXY()));
                rowDataList.add(new String("" + addressRecordsList.get(i).getCity_idXY()));
                rowDataList.add(new String("" + addressRecordsList.get(i).getZip_codeX()));
                rowDataList.add(new String("" + addressRecordsList.get(i).getHome_phone_numberX()));
                entireList.add(rowDataList);
            }
            CommonMethods.exportToExcel(response, entireList, "employee_address_report.xls", "Employee Address Report", null);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
   
}


