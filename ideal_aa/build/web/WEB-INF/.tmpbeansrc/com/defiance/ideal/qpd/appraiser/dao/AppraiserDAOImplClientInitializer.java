
package com.defiance.ideal.qpd.appraiser.dao;

import java.lang.reflect.Field;
import org.apache.beehive.controls.api.ControlException;
import org.apache.beehive.controls.api.bean.Controls;
import org.apache.beehive.controls.api.versioning.VersionRequired;
import org.apache.beehive.controls.api.context.ControlBeanContext;
import org.apache.beehive.controls.runtime.bean.EventAdaptor;
import org.apache.beehive.controls.runtime.bean.AdaptorPersistenceDelegate;

@SuppressWarnings("all")
public class AppraiserDAOImplClientInitializer
extends org.apache.beehive.controls.runtime.bean.ClientInitializer
{
    static final Field _dbAppraiserField;
    static final Field _sharedDbField;
    static
    {
        try
        {
            _dbAppraiserField = com.defiance.ideal.qpd.appraiser.dao.AppraiserDAOImpl.class.getDeclaredField("dbAppraiser");
            _dbAppraiserField.setAccessible(true);
            _sharedDbField = com.defiance.ideal.qpd.appraiser.dao.AppraiserDAOImpl.class.getDeclaredField("sharedDb");
            _sharedDbField.setAccessible(true);
        }
        catch (NoSuchFieldException __bc_nsfe)
        {
            throw new ExceptionInInitializerError(__bc_nsfe);
        }
    }
    
    
    private static void initializeFields(ControlBeanContext cbc,
    com.defiance.ideal.qpd.appraiser.dao.AppraiserDAOImpl client)
    {
        try
        {
            String __bc_id;
            //
            // Initialize any nested controls used by the client
            //
            __bc_id = "dbAppraiser";
            com.defiance.ideal.qpd.appraiser.db.AppraiserDBBean _dbAppraiser = (cbc == null ? null : (com.defiance.ideal.qpd.appraiser.db.AppraiserDBBean)cbc.getBean(__bc_id));
            if (_dbAppraiser == null)
            _dbAppraiser = (com.defiance.ideal.qpd.appraiser.db.AppraiserDBBean) Controls.instantiate(com.defiance.ideal.qpd.appraiser.db.AppraiserDBBean.class, getAnnotationMap(cbc, _dbAppraiserField), cbc, __bc_id );
            
            
            _dbAppraiserField.set(client, _dbAppraiser);
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
    
    public static void initialize(ControlBeanContext cbc, com.defiance.ideal.qpd.appraiser.dao.AppraiserDAOImpl client)
    {
        
        initializeFields( cbc, client );
    }
}
