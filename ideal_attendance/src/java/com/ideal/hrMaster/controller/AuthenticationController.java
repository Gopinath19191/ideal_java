/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ideal.hrMaster.controller;

import com.ideal.hrMaster.dao.AuthenticatorDaoImpl;
import com.ideal.hrMaster.dto.LoginDTO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 *
 * @author 16113
 */
public class AuthenticationController extends MultiActionController{
    
    public ModelAndView authenticate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = new ModelAndView("/unauthorised");
        HttpSession session = request.getSession();
        final WebApplicationContext ctx = getWebApplicationContext();
        AuthenticatorDaoImpl authenticationDao = (AuthenticatorDaoImpl) ctx.getBean("AuthenticatorDao");
        LoginDTO authenParams = new LoginDTO();

        if (request.getParameter("empId") == null) {
            mv.addObject("ErrorMessage", "Module Id Not Present");
        } else {
            authenParams = authenticationDao.getUserDetails(request.getParameter("empId"));

        }
        session.setAttribute("employee_no", request.getParameter("empId"));
        if (request.getParameter("modId") == null) {
            mv.addObject("ErrorMessage", "Module Id Not Present");
        } else {
            authenParams.setModuleId(request.getParameter("modId"));

        }

       boolean authenticated = authenticationDao.authenticate(authenParams);
        //Remove this line
        //authenticated = true;
        if (authenticated) {
            session.setAttribute("EMP_ID", authenParams.getEmpId());
            if("666".equals(authenParams.getModuleId())){
                mv = new ModelAndView("swipeUpload");
            }else if("743".equals(authenParams.getModuleId())){
                mv = new ModelAndView("redirect:procurementList.htm");
            }else if("744".equals(authenParams.getModuleId())){
                mv = new ModelAndView("redirect:procurementRMList.htm?status=m");
            }else if("745".equals(authenParams.getModuleId())){
                mv = new ModelAndView("redirect:procurementBUHList.htm?status=a");
            }else if("746".equals(authenParams.getModuleId())){
                mv = new ModelAndView("redirect:procurementAdminList.htm?status=e");
            }else if("814".equals(authenParams.getModuleId())){
                mv = new ModelAndView("redirect:getEmployeeIdCardList.htm?type=i");
            }else if("816".equals(authenParams.getModuleId())){
                mv = new ModelAndView("redirect:getEmployeeIdCardList.htm?type=n");
            }else if("815".equals(authenParams.getModuleId())){
                mv = new ModelAndView("redirect:getEmployeeIdCardList.htm?type=b");
            }else if("841".equals(authenParams.getModuleId())){
                mv = new ModelAndView("redirect:getEmployeeIdCardDownload.htm?employee_id="+authenParams.getEmpId());
            }else if("842".equals(authenParams.getModuleId())){
                mv = new ModelAndView("redirect:getDcEmployeeList.htm?type=e");
            }else if("843".equals(authenParams.getModuleId())){
                mv = new ModelAndView("redirect:getDcEmployeeList.htm?type=s");
            }else if("844".equals(authenParams.getModuleId())){
                mv = new ModelAndView("redirect:getActiveEmployeeList.htm?type=p");
            }else{
                mv = new ModelAndView("unauthorised");
            }
           
        } else {
            session.invalidate();
        }
        return mv;
    }
    
}
