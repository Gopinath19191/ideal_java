/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.application.controller;

import com.defiance.ideal.application.util.CommonMethods;
import com.defiance.ideal.application.dto.CustomerGroupDTO;
import com.defiance.ideal.application.service.CustomerGroupServiceImpl;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
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
 * @author 14058
 */
public class CustomerGroupController extends MultiActionController {
    public CustomerGroupController() {
    }
    public ModelAndView customersGroupList(HttpServletRequest request, HttpServletResponse response, CustomerGroupDTO filterData) throws Exception {
        ModelAndView mv = null;
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
            CustomerGroupServiceImpl customerGroupService=(CustomerGroupServiceImpl) ctx.getBean("CustomerGroupService");
            List<CustomerGroupDTO> customerGroupData = customerGroupService.fetchCustomerGroupData(filterData);

            Map<String,String> monthsMap = CommonMethods.getMonthsList();
            Map<Integer,Integer> yearsMap = CommonMethods.getYearsList(2);
            Map<String,String> pjctStatsMap = CommonMethods.getPjtStatusList();

            mv = new ModelAndView("/customersGroupList");
            mv.addObject("customerGroupData",customerGroupData);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }
    public ModelAndView customersGroupAdd(HttpServletRequest request, HttpServletResponse response, CustomerGroupDTO filterData) throws Exception {
        ModelAndView mv = null;
        try {
            mv = new ModelAndView("/customersGroupAdd");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }
    public ModelAndView saveCustomerGroup(HttpServletRequest request,HttpServletResponse response,CustomerGroupDTO customerFormData) throws Exception{
        System.out.println(":::::::::::"+customerFormData.getGroupName());
        final WebApplicationContext ctx = getWebApplicationContext();
        CustomerGroupServiceImpl customerGroupService=(CustomerGroupServiceImpl) ctx.getBean("CustomerGroupService");
        customerGroupService.insertCustomerGroupDetails(customerFormData);
        ModelAndView mv = null;
        mv = new ModelAndView("redirect:customersGroupList.htm");
        return mv;
    }
    public ModelAndView customersGroupEdit(HttpServletRequest request, HttpServletResponse response, CustomerGroupDTO filterData) throws Exception {
        ModelAndView mv = null;
        HttpSession session = request.getSession();

        final WebApplicationContext ctx = getWebApplicationContext();
        CustomerGroupServiceImpl customerGroupService=(CustomerGroupServiceImpl) ctx.getBean("CustomerGroupService");
        filterData.setGroupId(request.getParameter("customerGroupId"));
        List<CustomerGroupDTO> selectedCustomerGroupData = customerGroupService.fetchSelectedCustomerGroup(filterData);
        mv = new ModelAndView("/customersGroupEdit");
        mv.addObject("selectedCustomerGroupData",selectedCustomerGroupData);

        return mv;
    }
    public ModelAndView getDuplicateCustomerGroupName(HttpServletRequest request, HttpServletResponse response, CustomerGroupDTO filterData) throws Exception {
        final WebApplicationContext ctx = getWebApplicationContext();
        CustomerGroupServiceImpl customerGroupService=(CustomerGroupServiceImpl) ctx.getBean("CustomerGroupService");
        filterData.setGroupName(request.getParameter("customerGroupName"));
        if(request.getParameter("groupIdVar") != null && !(request.getParameter("groupIdVar").equals(""))) {
            filterData.setGroupId(request.getParameter("groupIdVar"));
        }
        List<CustomerGroupDTO> regionList = customerGroupService.getDuplicateCustomerGroupName(filterData);
        ModelAndView mv = new ModelAndView("/getDuplicateCustomerGroupName");
        mv.addObject("regionList",regionList);
        return mv;
    }
}
