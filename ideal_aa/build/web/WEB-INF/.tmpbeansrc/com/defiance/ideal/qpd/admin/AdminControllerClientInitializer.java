
package com.defiance.ideal.qpd.admin;

import java.lang.reflect.Field;
import org.apache.beehive.controls.api.ControlException;
import org.apache.beehive.controls.api.bean.Controls;
import org.apache.beehive.controls.api.versioning.VersionRequired;
import org.apache.beehive.controls.api.context.ControlBeanContext;
import org.apache.beehive.controls.runtime.bean.EventAdaptor;
import org.apache.beehive.controls.runtime.bean.AdaptorPersistenceDelegate;

@SuppressWarnings("all")
public class AdminControllerClientInitializer
extends org.apache.beehive.controls.runtime.bean.ClientInitializer
{
    static final Field _controlObjField;
    static
    {
        try
        {
            _controlObjField = com.defiance.ideal.qpd.admin.AdminController.class.getDeclaredField("controlObj");
            _controlObjField.setAccessible(true);
        }
        catch (NoSuchFieldException __bc_nsfe)
        {
            throw new ExceptionInInitializerError(__bc_nsfe);
        }
    }
    
    
    private static void initializeFields(ControlBeanContext cbc,
    com.defiance.ideal.qpd.admin.AdminController client)
    {
        try
        {
            String __bc_id;
            //
            // Initialize any nested controls used by the client
            //
            __bc_id = client.getClass() + "@" + client.hashCode() + ".com.defiance.ideal.qpd.admin.bo.AdminBO.controlObj";
            com.defiance.ideal.qpd.admin.bo.AdminBOBean _controlObj = (cbc == null ? null : (com.defiance.ideal.qpd.admin.bo.AdminBOBean)cbc.getBean(__bc_id));
            if (_controlObj == null)
            _controlObj = (com.defiance.ideal.qpd.admin.bo.AdminBOBean) Controls.instantiate(com.defiance.ideal.qpd.admin.bo.AdminBOBean.class, getAnnotationMap(cbc, _controlObjField), cbc, __bc_id );
            
            
            _controlObjField.set(client, _controlObj);
        }
        catch (RuntimeException __bc_re) { throw __bc_re; }
        catch (Exception __bc_e)
        {
            __bc_e.printStackTrace();
            throw new ControlException("Initializer failure", __bc_e);
        }
    }
    
    public static void initialize(ControlBeanContext cbc, com.defiance.ideal.qpd.admin.AdminController client)
    {
        
        initializeFields( cbc, client );
    }
}
