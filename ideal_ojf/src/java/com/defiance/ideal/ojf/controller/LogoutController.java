/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.ojf.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 *
 * @author 15850
 */

public class LogoutController extends MultiActionController {

    public ModelAndView logout(HttpServletRequest request,HttpServletResponse response) throws Exception {
        
         HttpSession session=request.getSession();
        ModelAndView mv=null;       

        System.out.println("employeeId at logout +++++++++++= "+session.getAttribute("loginId"));
        request.setAttribute("empid",session.getAttribute("loginId"));
        session.invalidate();
        mv = new ModelAndView("index");
        
        
        return mv;
        
        
    }
}
