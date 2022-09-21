
package com.defiance.ideal.qpd.appraiser;

import java.lang.reflect.Field;
import org.apache.beehive.controls.api.ControlException;
import org.apache.beehive.controls.api.bean.Controls;
import org.apache.beehive.controls.api.versioning.VersionRequired;
import org.apache.beehive.controls.api.context.ControlBeanContext;
import org.apache.beehive.controls.runtime.bean.EventAdaptor;
import org.apache.beehive.controls.runtime.bean.AdaptorPersistenceDelegate;

@SuppressWarnings("all")
public class AppraiserControllerClientInitializer
extends org.apache.beehive.controls.runtime.bean.ClientInitializer
{
    static final Field _controlObjField;
    static final Field _sharedControlObjField;
    static
    {
        try
        {
            _controlObjField = com.defiance.ideal.qpd.appraiser.AppraiserController.class.getDeclaredField("controlObj");
            _controlObjField.setAccessible(true);
            _sharedControlObjField = com.defiance.ideal.qpd.appraiser.AppraiserController.class.getDeclaredField("sharedControlObj");
            _sharedControlObjField.setAccessible(true);
        }
        catch (NoSuchFieldException __bc_nsfe)
        {
            throw new ExceptionInInitializerError(__bc_nsfe);
        }
    }
    
    
    private static void initializeFields(ControlBeanContext cbc,
    com.defiance.ideal.qpd.appraiser.AppraiserController client)
    {
        try
        {
            String __bc_id;
            //
            // Initialize any nested controls used by the client
            //
            __bc_id = client.getClass() + "@" + client.hashCode() + ".com.defiance.ideal.qpd.appraiser.bo.AppraiserBO.controlObj";
            com.defiance.ideal.qpd.appraiser.bo.AppraiserBOBean _controlObj = (cbc == null ? null : (com.defiance.ideal.qpd.appraiser.bo.AppraiserBOBean)cbc.getBean(__bc_id));
            if (_controlObj == null)
            _controlObj = (com.defiance.ideal.qpd.appraiser.bo.AppraiserBOBean) Controls.instantiate(com.defiance.ideal.qpd.appraiser.bo.AppraiserBOBean.class, getAnnotationMap(cbc, _controlObjField), cbc, __bc_id );
            
            
            _controlObjField.set(client, _controlObj);
            __bc_id = client.getClass() + "@" + client.hashCode() + ".com.defiance.ideal.shared.MysqlDatabase.sharedControlObj";
            com.defiance.ideal.shared.MysqlDatabaseBean _sharedControlObj = (cbc == null ? null : (com.defiance.ideal.shared.MysqlDatabaseBean)cbc.getBean(__bc_id));
            if (_sharedControlObj == null)
            _sharedControlObj = (com.defiance.ideal.shared.MysqlDatabaseBean) Controls.instantiate(com.defiance.ideal.shared.MysqlDatabaseBean.class, getAnnotationMap(cbc, _sharedControlObjField), cbc, __bc_id );
            
            
            _sharedControlObjField.set(client, _sharedControlObj);
        }
        catch (RuntimeException __bc_re) { throw __bc_re; }
        catch (Exception __bc_e)
        {
            __bc_e.printStackTrace();
            throw new ControlException("Initializer failure", __bc_e);
        }
    }
    
    public static void initialize(ControlBeanContext cbc, com.defiance.ideal.qpd.appraiser.AppraiserController client)
    {
        
        initializeFields( cbc, client );
    }
}
