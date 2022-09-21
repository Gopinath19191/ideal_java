package com.defiance.ideal.qpd.admin.dao;

import java.lang.reflect.Field;
import org.apache.beehive.controls.api.ControlException;
import org.apache.beehive.controls.api.context.ControlBeanContext;
import org.apache.beehive.controls.runtime.bean.ControlBean;

@SuppressWarnings("all")
public class AdminDAOImplInitializer
extends org.apache.beehive.controls.runtime.bean.ImplInitializer
{
    
    
    
    /**
    * Initializes the nested client event proxies required by the implementation
    */
    public void initControls(ControlBean bean, Object target)
    {
        com.defiance.ideal.qpd.admin.dao.AdminDAOImpl __bc_impl = (com.defiance.ideal.qpd.admin.dao.AdminDAOImpl)target;
        // DO NOT DELEGATE TO SUPERCLASS HERE.  THE CONTROL CLIENT INIT HIERARCHY
        // WILL INITIALIZE ALL THE WAY DOWN TO THE BASE CLASS!
        
        try
        {
            com.defiance.ideal.qpd.admin.dao.AdminDAOImplClientInitializer.initialize(bean.getControlBeanContext(), __bc_impl);
        }
        catch (RuntimeException __bc_re) { throw __bc_re; }
        catch (Exception __bc_e)
        {
            throw new ControlException("Client event proxy initialization failure", __bc_e);
        }
    }
    
}
