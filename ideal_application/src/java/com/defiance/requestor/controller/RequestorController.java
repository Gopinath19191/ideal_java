/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.requestor.controller;

import com.defiance.ideal.application.dto.LoginDTO;
import com.defiance.requestor.dto.RequestorDTO;
import com.defiance.requestor.service.RequestServiceImpl;
import com.defiance.requestor.service.RequestorService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 *
 * @author 14355
 */
public class RequestorController extends MultiActionController {
         ModelAndView mv = null;
private RequestorService requestorservice;
public void setRequestorService(RequestorService requestorservice) {
    this.requestorservice = requestorservice;
}
   public RequestorController() {
    }
public ModelAndView newRequest(HttpServletRequest request,HttpServletResponse response,RequestorDTO requestformData) throws Exception{
        LoginDTO sessionObj = this.getSessionValues(request);
        System.out.println("ctrlll");
        requestformData.setEmpId(sessionObj.getEmpId());
        System.out.println(":::::::::::"+requestformData);
        ((RequestServiceImpl) this.getBean("RequestorService")).insertRequestDetails(requestformData);
      //RequestorDTO requestDetails = (RequestorDTO) ((RequestServiceImpl) this.getBean("RequestorService")).getRequestDetails(sessionObj.getEmpId());
        System.out.println("Inside 1---");
         System.out.println("Inside 2---");
        mv = new ModelAndView("/newRequest");
       // mv.addObject("requestDetails",requestDetails);
      //  mv.addObject("requestStatus",CommonConfigurations.EMP_SUBMIT_STATUS);
       // mv.addObject("requestorName",requestorName);
        return mv;
    }
//    public ModelAndView requestProcess(HttpServletRequest request,HttpServletResponse response)throws Exception{
//        LoginDTO sessionObj = this.getSessionValues(request);
//        RequestorDTO requestDetails = (RequestorDTO) ((RequestServiceImpl) this.getBean("RequestorService")).getEmployeeDetails(sessionObj.getEmpId());
//        //requestDetails.setRequestDate(CommonFunctions.getSystemDate("dd-MM-yyyy"));
//        mv = new ModelAndView("/requestProcess");
//        mv.addObject("requestDetails", requestDetails);
//        //mv.addObject("requestStatus",CommonConfigurations.EMP_SUBMIT_STATUS);
//        mv.addObject("employeeId", sessionObj.getEmpId());
//        return mv;
//    }

//    public ModelAndView newRequest(HttpServletRequest request, HttpServletResponse response,RequestorDTO filterData) throws Exception {
//
// ModelAndView mv = null;
//       HttpSession session = request.getSession();
//        System.out.println("ctrlll");
//        final WebApplicationContext ctx = getWebApplicationContext();
//     RequestServiceImpl requestorService= (RequestServiceImpl) ctx.getBean("RequestorService");
//        List<RequestorDTO> referenceNo = requestorService.getReferenceNo();
//        System.out.println("Inside 1---");
//
//        mv = new ModelAndView("/newRequest");
//        mv.addObject("referenceNo",referenceNo);
//
//        try{
//            ModelAndView requestList = this.getRequestList(request,response,filterData);
//            mv.addObject("requestList",requestList);
//            System.out.println(":RequestList:"+requestList);
//
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
//       return mv;
//    }


    public LoginDTO getSessionValues(HttpServletRequest request){
        LoginDTO sessionObj = new LoginDTO();
        String employeeId = (String)request.getSession().getAttribute("employeeId");
        String moduleId = (String)request.getSession().getAttribute("moduleId");
        sessionObj.setEmpId(employeeId);
        sessionObj.setModuleId(moduleId);
        return sessionObj;
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
    private ModelAndView getRequestList(HttpServletRequest request, HttpServletResponse response, RequestorDTO filterData) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}