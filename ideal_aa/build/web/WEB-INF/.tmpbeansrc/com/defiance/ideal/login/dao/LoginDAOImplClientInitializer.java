
package com.defiance.ideal.login.dao;

import java.lang.reflect.Field;
import org.apache.beehive.controls.api.ControlException;
import org.apache.beehive.controls.api.bean.Controls;
import org.apache.beehive.controls.api.versioning.VersionRequired;
import org.apache.beehive.controls.api.context.ControlBeanContext;
import org.apache.beehive.controls.runtime.bean.EventAdaptor;
import org.apache.beehive.controls.runtime.bean.AdaptorPersistenceDelegate;

@SuppressWarnings("all")
public class LoginDAOImplClientInitializer
extends org.apache.beehive.controls.runtime.bean.ClientInitializer
{
    static final Field _dbCTRLField;
    static
    {
        try
        {
            _dbCTRLField = com.defiance.ideal.login.dao.LoginDAOImpl.class.getDeclaredField("dbCTRL");
            _dbCTRLField.setAccessible(true);
        }
        catch (NoSuchFieldException __bc_nsfe)
        {
            throw new ExceptionInInitializerError(__bc_nsfe);
        }
    }
    
    
    private static void initializeFields(ControlBeanContext cbc,
    com.defiance.ideal.login.dao.LoginDAOImpl client)
    {
        try
        {
            String __bc_id;
            //
            // Initialize any nested controls used by the client
            //
            __bc_id = "dbCTRL";
            com.defiance.ideal.shared.MysqlDatabaseBean _dbCTRL = (cbc == null ? null : (com.defiance.ideal.shared.MysqlDatabaseBean)cbc.getBean(__bc_id));
            if (_dbCTRL == null)
            _dbCTRL = (com.defiance.ideal.shared.MysqlDatabaseBean) Controls.instantiate(com.defiance.ideal.shared.MysqlDatabaseBean.class, getAnnotationMap(cbc, _dbCTRLField), cbc, __bc_id );
            
            
            _dbCTRLField.set(client, _dbCTRL);
        }
        catch (RuntimeException __bc_re) { throw __bc_re; }
        catch (Exception __bc_e)
        {
            __bc_e.printStackTrace();
            throw new ControlException("Initializer failure", __bc_e);
        }
    }
    
    public static void initialize(ControlBeanContext cbc, com.defiance.ideal.login.dao.LoginDAOImpl client)
    {
        
        initializeFields( cbc, client );
    }
}
