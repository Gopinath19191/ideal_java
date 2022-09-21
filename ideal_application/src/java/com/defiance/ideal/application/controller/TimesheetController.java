/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.application.controller;

import com.defiance.ideal.application.util.CommonMethods;
import com.defiance.ideal.application.dto.CustomerDataDTO;
import com.defiance.ideal.application.dto.CustomerAddDTO;
import com.defiance.ideal.application.service.CustomerServiceImpl;
import com.defiance.ideal.application.util.CommonConfigurations;
import com.defiance.ideal.application.util.Config;
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
public class TimesheetController extends MultiActionController{
    public TimesheetController() {
    }
    public ModelAndView newTimesheetEntry(HttpServletRequest request, HttpServletResponse response, CustomerDataDTO filterData) throws Exception {
        ModelAndView mv = null;
        HttpSession session = request.getSession();
        String employeeId=null;
        //String selectedMonth,selectedYear,;

          Calendar calndr = Calendar.getInstance();
          calndr.get(Calendar.MONTH);
          calndr.get(Calendar.YEAR);

        if(session.getAttribute("EMP_ID")==null){
            employeeId = request.getParameter("empid");
            session.setAttribute("EMP_ID", employeeId);
        }else{
            employeeId = (String) session.getAttribute("EMP_ID");
        }
        Calendar calendar = Calendar.getInstance();
        try {
            final WebApplicationContext ctx = getWebApplicationContext();
            CustomerServiceImpl customerService=(CustomerServiceImpl) ctx.getBean("CustomerService");
            List<CustomerDataDTO> customerData = customerService.fetchCustomerData(filterData);

            Map<String,String> monthsMap = CommonMethods.getMonthsList();
            Map<Integer,Integer> yearsMap = CommonMethods.getYearsList(2);
            Map<String,String> pjctStatsMap = CommonMethods.getPjtStatusList();

            mv = new ModelAndView("/customerList");
            mv.addObject("monthsMap",monthsMap);
            mv.addObject("yearsMap",yearsMap);
            mv.addObject("pjctStatsMap",pjctStatsMap);
            mv.addObject("customerData",customerData);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }
}
