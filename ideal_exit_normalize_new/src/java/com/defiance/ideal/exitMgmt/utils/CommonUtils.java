/*
 * @(#)CommonUtils.java june 10, 2010
 *
 * Copyright (c) 2010-2011 Defiance, Inc.
 *
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of Defiance Ltd.
 * ("Confidential Information").  You shall not disclose such Confidential
 * Information and shall use it only  in accordance with the terms of the license
 * agreement  you  entered into with Defiance.
 *
 */
/***************************************************************
 *       Copyright  2010 DefianceTech Pvt Ltd
 *       Source control $Revision:
 *	$Author:Sathishkumar Vaiyapuri
 *	$Date:10-06-10
 *
 **************************************************************/
package com.defiance.ideal.exitMgmt.utils;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 *
 * This is the class having common methods and used by all the modules.
 */
public class CommonUtils extends MultiActionController{

    public CommonUtils() {
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
