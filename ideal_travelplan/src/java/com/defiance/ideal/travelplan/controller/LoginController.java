
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.travelplan.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 *
 * @author 14578
 */
public class LoginController extends MultiActionController {

    private ModelAndView mv;
    public ModelAndView authenticate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        mv = new ModelAndView("/test");
        System.out.println("Hello");
        return mv;
    }

}
