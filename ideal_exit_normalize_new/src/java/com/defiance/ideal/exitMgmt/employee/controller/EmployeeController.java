/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.exitMgmt.employee.controller;

import com.defiance.ideal.exitMgmt.approval.dto.ApprovalDTO;
import com.defiance.ideal.exitMgmt.approval.services.ApprovalServiceImpl;
import com.defiance.ideal.exitMgmt.employee.dto.EmployeeDTO;
import com.defiance.ideal.exitMgmt.employee.services.ExitEmployeeServiceImpl;
import com.defiance.ideal.exitMgmt.login.dto.LoginDTO;
import com.defiance.ideal.exitMgmt.utils.CommonConfigurations;
import com.defiance.ideal.exitMgmt.utils.CommonFunctions;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 *
 * @author 14583
 */
public class EmployeeController extends MultiActionController {

     @Override
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        CustomDateEditor dateEditor = new CustomDateEditor(new SimpleDateFormat("dd-MM-yyyy"), true);
        binder.registerCustomEditor(Date.class, dateEditor);
    }

     ModelAndView mv = null;

    public ModelAndView employeeHome(HttpServletRequest request,HttpServletResponse response) throws Exception{
        LoginDTO sessionObj = this.getSessionValues(request);
        EmployeeDTO employeeDetails = (EmployeeDTO) ((ExitEmployeeServiceImpl) this.getBean("EmployeeService")).getEmployeeDetails(sessionObj.getEmpId());
        String empName  = (String) ((ExitEmployeeServiceImpl) this.getBean("EmployeeService")).getEmpName(sessionObj.getEmpId());
        mv = new ModelAndView("employee/employeeHome");
        mv.addObject("employeeDetails",employeeDetails);
        mv.addObject("empName",empName);
        mv.addObject("employeeHomeContent",CommonFunctions.getStringFromProperty(CommonConfigurations.ExternalConfigMailFile,"employeeHomeContent"));
        return mv;
    }

    public ModelAndView exitWelcome(HttpServletRequest request,HttpServletResponse response) throws Exception{
        LoginDTO sessionObj = this.getSessionValues(request);
        EmployeeDTO employeeDetails = (EmployeeDTO) ((ExitEmployeeServiceImpl) this.getBean("EmployeeService")).getEmployeeDetails(sessionObj.getEmpId());
        String empName  = (String) ((ExitEmployeeServiceImpl) this.getBean("EmployeeService")).getEmpName(sessionObj.getEmpId());
        mv = new ModelAndView("employee/exitWelcome");
        mv.addObject("employeeDetails",employeeDetails);
        mv.addObject("empName",empName);
        return mv;
    }

    public ModelAndView proceedToExit(HttpServletRequest request,HttpServletResponse response)throws Exception{
        LoginDTO sessionObj = this.getSessionValues(request);
        String empName  = (String) ((ExitEmployeeServiceImpl) this.getBean("EmployeeService")).getEmpName(sessionObj.getEmpId());
        mv = new ModelAndView("employee/userAcceptance");
        mv.addObject("empName",empName);
        return mv;
    }

    public ModelAndView exitProcess(HttpServletRequest request,HttpServletResponse response)throws Exception{
        LoginDTO sessionObj = this.getSessionValues(request);
        System.out.println("sessionObj 11 "+sessionObj.getEmpId());
        EmployeeDTO employeeDetails = (EmployeeDTO) ((ExitEmployeeServiceImpl) this.getBean("EmployeeService")).getEmployeeDetails(sessionObj.getEmpId());
        List<EmployeeDTO> empAddress = (List<EmployeeDTO>)((ExitEmployeeServiceImpl) this.getBean("EmployeeService")).getEmpAddressDetails(sessionObj.getEmpId());
         List<ApprovalDTO> exitEmployeeStatus=((ApprovalServiceImpl) this.getBean("ApprovalService")).getExitEmployeeStatus();
        employeeDetails.setResignedDate(new Date());
        mv = new ModelAndView("employee/exitProcess");
        mv.addObject("succcessMsg", request.getAttribute("succcessMsg"));
        mv.addObject("empDetails", employeeDetails);
        mv.addObject("empAddress", empAddress);
        mv.addObject("exitEmployeeStatus", exitEmployeeStatus );
        mv.addObject("empSubmitStatus",CommonConfigurations.EMP_SUBMIT_STATUS);
        mv.addObject("employeeId", sessionObj.getEmpId());
        return mv;
    }

    public ModelAndView saveExitProcess(HttpServletRequest request,HttpServletResponse response,EmployeeDTO employeeFormData) throws Exception{
        LoginDTO sessionObj = this.getSessionValues(request);
        employeeFormData.setEmpId(sessionObj.getEmpId());
        System.out.println(":::::::::::"+employeeFormData);
		//System.out.println("employeeFormData.getAdditionalTriggerMailIds()--------------------->"+employeeFormData.getAdditionalTriggerMailIds());
		
        String succcessMsg = null;
        if(employeeFormData.getAddressType().equals("")){
            employeeFormData.setAddressType(null);
        }
        ((ExitEmployeeServiceImpl) this.getBean("EmployeeService")).insertExitEmpDetails(employeeFormData);
        EmployeeDTO employeeDetails = (EmployeeDTO) ((ExitEmployeeServiceImpl) this.getBean("EmployeeService")).getEmployeeDetails(sessionObj.getEmpId());
        String empName  = (String) ((ExitEmployeeServiceImpl) this.getBean("EmployeeService")).getEmpName(sessionObj.getEmpId());
        mv = new ModelAndView("employee/employeeHome");
        System.out.println("::::"+employeeFormData.getButtonName());
        if (employeeFormData.getButtonName().equals("Save")) {
            succcessMsg = "Data Saved Successfully";
        }else if(employeeFormData.getButtonName().equals("Submit")){
            succcessMsg = "Data Submitted Successfully";
        }
        mv.addObject("employeeDetails",employeeDetails);
        mv.addObject("empSubmitStatus",CommonConfigurations.EMP_SUBMIT_STATUS);
        mv.addObject("empName",empName);
        request.setAttribute("succcessMsg", succcessMsg);
        return exitProcess(request, response);
    }

    public ModelAndView getEmployeeAddress(HttpServletRequest request,HttpServletResponse response)throws Exception{
        LoginDTO sessionObj = this.getSessionValues(request);
        String addressType = request.getParameter("addressType");
        System.out.println(":::"+addressType+"::::"+sessionObj.getEmpId());
        EmployeeDTO formData = new EmployeeDTO();
        formData.setEmpId(sessionObj.getEmpId());
        formData.setAddressType(addressType);
        EmployeeDTO empAddressDetails = (EmployeeDTO)((ExitEmployeeServiceImpl) this.getBean("EmployeeService")).getEmployeeAddress(formData);
        List<EmployeeDTO> countryList = (List<EmployeeDTO>)((ExitEmployeeServiceImpl) this.getBean("EmployeeService")).getCountryList();
//        mv = new ModelAndView("employee/ajax");
//        mv.addObject("empAddressDetails",empAddressDetails);
//        mv.addObject("countryList",countryList);
//        mv.addObject("getDataFor","addressDetails");
//        response.getWriter().println(empAddressDetails.getZipCode());
        ServletOutputStream os = response.getOutputStream();
        os.print("{\"empAddress\":[{\"addressLine1\":\"" + empAddressDetails.getAddressLine1() + "\",\"addressLine2\":\"" + empAddressDetails.getAddressLine2() + "\",\"zipCode\":\"" + empAddressDetails.getZipCode() + "\"]},");
              os.print("\"country\":[");
              for(EmployeeDTO country:countryList){
                os.print("{\"name\":"+country.getCountryName()+"}");
              }
              os.print("]}");               
        return null;
    }


    public Object getBean(String beanName){
        Object o=null;
        try {
        final WebApplicationContext ctx = getWebApplicationContext();
        return ctx.getBean(beanName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return o;
    }

    public LoginDTO getSessionValues(HttpServletRequest request){
        LoginDTO sessionObj = new LoginDTO();
        String employeeId = (String)request.getSession().getAttribute("employeeId");
        String moduleId = (String)request.getSession().getAttribute("moduleId");
        sessionObj.setEmpId(employeeId);
        sessionObj.setModuleId(moduleId);
        return sessionObj;
    }

}
