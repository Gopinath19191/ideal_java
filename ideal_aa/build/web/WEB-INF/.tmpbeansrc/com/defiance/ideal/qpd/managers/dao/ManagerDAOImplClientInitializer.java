
package com.defiance.ideal.qpd.managers.dao;

import java.lang.reflect.Field;
import org.apache.beehive.controls.api.ControlException;
import org.apache.beehive.controls.api.bean.Controls;
import org.apache.beehive.controls.api.versioning.VersionRequired;
import org.apache.beehive.controls.api.context.ControlBeanContext;
import org.apache.beehive.controls.runtime.bean.EventAdaptor;
import org.apache.beehive.controls.runtime.bean.AdaptorPersistenceDelegate;

@SuppressWarnings("all")
public class ManagerDAOImplClientInitializer
extends org.apache.beehive.controls.runtime.bean.ClientInitializer
{
    static final Field _dbCTRLField;
    static final Field _sharedDbField;
    static
    {
        try
        {
            _dbCTRLField = com.defiance.ideal.qpd.managers.dao.ManagerDAOImpl.class.getDeclaredField("dbCTRL");
            _dbCTRLField.setAccessible(true);
            _sharedDbField = com.defiance.ideal.qpd.managers.dao.ManagerDAOImpl.class.getDeclaredField("sharedDb");
            _sharedDbField.setAccessible(true);
        }
        catch (NoSuchFieldException __bc_nsfe)
        {
            throw new ExceptionInInitializerError(__bc_nsfe);
        }
    }
    
    
    private static void initializeFields(ControlBeanContext cbc,
    com.defiance.ideal.qpd.managers.dao.ManagerDAOImpl client)
    {
        try
        {
            String __bc_id;
            //
            // Initialize any nested controls used by the client
            //
            __bc_id = "dbCTRL";
            com.defiance.ideal.qpd.managers.db.ManagerDBBean _dbCTRL = (cbc == null ? null : (com.defiance.ideal.qpd.managers.db.ManagerDBBean)cbc.getBean(__bc_id));
            if (_dbCTRL == null)
            _dbCTRL = (com.defiance.ideal.qpd.managers.db.ManagerDBBean) Controls.instantiate(com.defiance.ideal.qpd.managers.db.ManagerDBBean.class, getAnnotationMap(cbc, _dbCTRLField), cbc, __bc_id );
            
            
            _dbCTRLField.set(client, _dbCTRL);
            __bc_id = "sharedDb";
            com.defiance.ideal.shared.MysqlDatabaseBean _sharedDb = (cbc == null ? null : (com.defiance.ideal.shared.MysqlDatabaseBean)cbc.getBean(__bc_id));
            if (_sharedDb == null)
            _sharedDb = (com.defiance.ideal.shared.MysqlDatabaseBean) Controls.instantiate(com.defiance.ideal.shared.MysqlDatabaseBean.class, getAnnotationMap(cbc, _sharedDbField), cbc, __bc_id );
            
            
            _sharedDbField.set(client, _sharedDb);
        }
        catch (RuntimeException __bc_re) { throw __bc_re; }
        catch (Exception __bc_e)
        {
            __bc_e.printStackTrace();
            throw new ControlException("Initializer failure", __bc_e);
        }
    }
    
    public static void initialize(ControlBeanContext cbc, com.defiance.ideal.qpd.managers.dao.ManagerDAOImpl client)
    {
        
        initializeFields( cbc, client );
    }
}
