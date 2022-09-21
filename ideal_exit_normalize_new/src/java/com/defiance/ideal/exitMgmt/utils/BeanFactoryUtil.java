/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.exitMgmt.utils;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
/**
 *
 * @author 14583
 */
public class BeanFactoryUtil extends MultiActionController{

    public BeanFactoryUtil() {
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
    
}
