/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.application.controller;

import com.defiance.ideal.application.dto.CustomerMappingDTO;
import com.defiance.ideal.application.service.CustomerMappingServiceImpl;
import com.defiance.ideal.application.util.CommonConfigurations;
import com.defiance.ideal.application.util.CommonMethods;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 *
 * @author 14583
 */
public class CustomerMappingController extends MultiActionController {
    
    public ModelAndView mv = null;
    
    public ModelAndView custMapList(HttpServletRequest request,HttpServletResponse response,CustomerMappingDTO formData)throws Exception{
        mv = new ModelAndView("/customerMapping/customerMappingList");
        HttpSession session = request.getSession();
        String employeeId=null;
        if(session.getAttribute("EMP_ID")==null){
            employeeId = request.getParameter("empid");
            session.setAttribute("EMP_ID", employeeId);
        }else{
            employeeId = (String) session.getAttribute("EMP_ID");
        }
        try {
            final WebApplicationContext ctx = getWebApplicationContext();
            CustomerMappingServiceImpl custMapService=(CustomerMappingServiceImpl) ctx.getBean("CustomerMappingService");
            List<CustomerMappingDTO> custMapData = custMapService.fetchCustMapList(formData);
            mv.addObject("custMapData",custMapData);
            if(formData.getSuccessMsg()!=null){
                mv.addObject("successMsg",formData.getSuccessMsg());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }
    
     public ModelAndView addOrEditCustMap(HttpServletRequest request, HttpServletResponse response, CustomerMappingDTO formData) throws Exception {
        mv = new ModelAndView("/customerMapping/customerMappingAddEdit");
         final WebApplicationContext ctx = getWebApplicationContext();
        CustomerMappingServiceImpl custMapService = (CustomerMappingServiceImpl) ctx.getBean("CustomerMappingService");
        CustomerMappingDTO custMapData = new CustomerMappingDTO();
        if(formData.getCustomerMapId()!=null && !formData.getCustomerMapId().equals("")){
            custMapData = custMapService.getCustMapDetails(formData);
            List<CustomerMappingDTO> subRBUList = custMapService.getSubRbuList(custMapData);
            mv.addObject("subRBUList",subRBUList);
        }else{
            formData.setCustomerListCheck("add");
        }
        List<CustomerMappingDTO> customerList = custMapService.getCustomerList(formData);
        List<CustomerMappingDTO> bdmList = custMapService.getBdmList();
        List<CustomerMappingDTO> leaderList = custMapService.getLeaderList();
        List<CustomerMappingDTO> rbuList = custMapService.getRBUList();
        mv.addObject("custMapData",custMapData);
        mv.addObject("customerList",customerList);
        mv.addObject("bdmList",bdmList);
        mv.addObject("leaderList",leaderList);
        mv.addObject("rbuList",rbuList);
        return mv;
    }
     
     public ModelAndView insertOrUpdateCustMap(HttpServletRequest request, HttpServletResponse response, CustomerMappingDTO formData) throws Exception {
        mv = new ModelAndView("redirect:/custMap/custMapList.htm");
        final WebApplicationContext ctx = getWebApplicationContext();
        CustomerMappingServiceImpl custMapService = (CustomerMappingServiceImpl) ctx.getBean("CustomerMappingService");
        formData.setMappedDate(new Date());
        formData.setDeleted(CommonConfigurations.DELETED_VALUE);
        custMapService.updateCustMapetails(formData);
        formData.setDeleted(CommonConfigurations.UN_DELETED_VALUE);
        custMapService.insertCustMap(formData);
        mv.addObject("successMsg", "Customer Mapping Updated Successfully");
        return mv;
    }
     

     public ModelAndView customerMappingExport(HttpServletRequest request, HttpServletResponse response, CustomerMappingDTO formData) throws Exception {
        final WebApplicationContext ctx = getWebApplicationContext();
        CustomerMappingServiceImpl custMapService = (CustomerMappingServiceImpl) ctx.getBean("CustomerMappingService");
        List<CustomerMappingDTO> custMapData = custMapService.fetchCustMapList(formData);
        ArrayList entireList = new ArrayList();
        for (int i = 0; i < custMapData.size(); i++) {
            ArrayList rowDataList = new ArrayList();
            rowDataList.add(new String("" + custMapData.get(i).getCustomerCode()));
            rowDataList.add(new String("" + custMapData.get(i).getCustomerName()));
            rowDataList.add(new String("" + custMapData.get(i).getBdmEmpName()));
            rowDataList.add(new String("" + custMapData.get(i).getLeaderEmpName()));
            rowDataList.add(new String("" + custMapData.get(i).getRegion()));
            rowDataList.add(new String("" + custMapData.get(i).getSubRbu()));
            entireList.add(rowDataList);
        }
        CommonMethods.exportToExcel(response, entireList, "Customer_Mapping_Report.xls", "Customer Mapping Report",null);
        return null;
    }
    
}
