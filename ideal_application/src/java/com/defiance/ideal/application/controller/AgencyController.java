/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.application.controller;

import com.defiance.ideal.application.dto.AgencyDTO;
import com.defiance.ideal.application.dto.CustomerAddDTO;
import com.defiance.ideal.application.service.AgencyServiceImpl;
import com.defiance.ideal.application.service.CustomerServiceImpl;
import com.defiance.ideal.application.util.CommonConfigurations;
import java.util.List;
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
public class AgencyController extends MultiActionController{
   
    public ModelAndView agencyList(HttpServletRequest request, HttpServletResponse response, AgencyDTO filterData) throws Exception {
        System.out.println("::Inside the agency controller:::");
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
            AgencyServiceImpl agencyService=(AgencyServiceImpl) ctx.getBean("AgencyService");
            CustomerServiceImpl customerService=(CustomerServiceImpl) ctx.getBean("CustomerService");
            filterData.setCreatorId(employeeId);
            List<AgencyDTO> agencyData = agencyService.fetchAgencyList(filterData);
            List<CustomerAddDTO> agencyCategory = customerService.getConfigValueData(CommonConfigurations.customerCategory);
            mv = new ModelAndView("/agency/agencyList");
            mv.addObject("agencyData",agencyData);
            mv.addObject("agencyCategory",agencyCategory);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }

    public ModelAndView updateAgency(HttpServletRequest request, HttpServletResponse response, AgencyDTO formData) throws Exception {
        ModelAndView mv = null;
        final WebApplicationContext ctx = getWebApplicationContext();
        AgencyServiceImpl agencyService=(AgencyServiceImpl) ctx.getBean("AgencyService");
        agencyService.updateAgency(formData);
        mv =agencyList(request, response, formData);
        return mv;
    }
    
    public ModelAndView getAgencyDetails(HttpServletRequest request, HttpServletResponse response, AgencyDTO formData) throws Exception {
        ModelAndView mv = null;
        final WebApplicationContext ctx = getWebApplicationContext();
        AgencyServiceImpl agencyService = (AgencyServiceImpl) ctx.getBean("AgencyService");
        CustomerServiceImpl customerService = (CustomerServiceImpl) ctx.getBean("CustomerService");
        formData.setAgencyId(request.getParameter("agencyId"));
        AgencyDTO agencyData = agencyService.getAgencyDetails(formData);
        List<CustomerAddDTO> agencyCategory = customerService.getConfigValueData(CommonConfigurations.customerCategory);
        mv = new ModelAndView("/agency/agencyEdit");
        mv.addObject("agencyData", agencyData);
        mv.addObject("agencyCategory", agencyCategory);
        return mv;
    }

}
