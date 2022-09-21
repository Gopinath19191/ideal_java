
package com.defiance.ideal.qpd.admin.dao;

import java.beans.*;

import java.lang.reflect.Method;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.HashMap;
import java.util.Map;

import org.apache.beehive.controls.api.bean.*;
import org.apache.beehive.controls.api.context.ControlBeanContext;
import org.apache.beehive.controls.api.properties.PropertyKey;
import org.apache.beehive.controls.api.properties.PropertyMap;
import org.apache.beehive.controls.api.versioning.*;

@SuppressWarnings("all")
public class AdminDAOBean
extends org.apache.beehive.controls.runtime.bean.ControlBean
implements com.defiance.ideal.qpd.admin.dao.AdminDAO
{
    static final Method _filterEmployeeDataChangeReqMethod;
    static final Method _filterEmployeeDataMethod;
    static final Method _getEmployeeNameMethod;
    static final Method _triggerAppraisalMethod;
    static final Method _getStructureDetailsMethod;
    static final Method _getBandDataMethod;
    static final Method _updateAppraisalMethod;
    
    //
    // This HashMap will map from a Method to the array of names for parameters of the
    // method.  This is necessary because parameter name data isn't carried along in the
    // class file, but if available can enable ease of use by referencing parameters by
    // the declared name (vs. by index).
    //
    // This map should be read-only after its initialization in the static block, hence
    // using a plain HashMap is thread-safe.
    //
    static HashMap<Method, String []> _methodParamMap = new HashMap<Method, String []>();
    
    static
    {
        
        
        try
        {
            _filterEmployeeDataChangeReqMethod = com.defiance.ideal.qpd.admin.dao.AdminDAO.class.getMethod("filterEmployeeDataChangeReq", new Class [] {java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, int.class, int.class});
            _methodParamMap.put(_filterEmployeeDataChangeReqMethod, new String [] { "appraiserData", "concatentedBand", "dojCheck", "concatenatedStatus", "concatenatedStructure", "appraisalYear", "appraisalPeriod" });
            _filterEmployeeDataMethod = com.defiance.ideal.qpd.admin.dao.AdminDAO.class.getMethod("filterEmployeeData", new Class [] {java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, int.class, int.class});
            _methodParamMap.put(_filterEmployeeDataMethod, new String [] { "appraiserData", "concatenatedBand", "dojCheck", "concatenatedStatus", "concatenatedStructure", "appraisalYear", "appraisalPeriod" });
            _getEmployeeNameMethod = com.defiance.ideal.qpd.admin.dao.AdminDAO.class.getMethod("getEmployeeName", new Class [] {java.lang.String.class});
            _methodParamMap.put(_getEmployeeNameMethod, new String [] { "searchString" });
            _triggerAppraisalMethod = com.defiance.ideal.qpd.admin.dao.AdminDAO.class.getMethod("triggerAppraisal", new Class [] {com.defiance.ideal.qpd.admin.dto.AdminDTO.class, int.class});
            _methodParamMap.put(_triggerAppraisalMethod, new String [] { "formData", "empId" });
            _getStructureDetailsMethod = com.defiance.ideal.qpd.admin.dao.AdminDAO.class.getMethod("getStructureDetails", new Class [] {});
            _methodParamMap.put(_getStructureDetailsMethod, new String [] {  });
            _getBandDataMethod = com.defiance.ideal.qpd.admin.dao.AdminDAO.class.getMethod("getBandData", new Class [] {});
            _methodParamMap.put(_getBandDataMethod, new String [] {  });
            _updateAppraisalMethod = com.defiance.ideal.qpd.admin.dao.AdminDAO.class.getMethod("updateAppraisal", new Class [] {com.defiance.ideal.qpd.admin.dto.AdminDTO.class, int.class});
            _methodParamMap.put(_updateAppraisalMethod, new String [] { "formData", "empId" });
        }
        catch (NoSuchMethodException __bc_nsme)
        {
            throw new ExceptionInInitializerError(__bc_nsme);
        }
    }
    
    
    
    static
    {
        
    }
    
    
    
    /**
    * This is the public constructor for the class.  A client-defined control ID may
    * be provided.  This ID must be unique within the nesting ControlBeanContext.
    * @param context The containing ControlBeanContext
    * @param id The control identifier (or null to autogenerate a unique value)
    * @param props The initialization Properties for the new instance (or null for defaults)
    */
    public AdminDAOBean(ControlBeanContext context, String id, PropertyMap props)
    {
        this(context, id, props, com.defiance.ideal.qpd.admin.dao.AdminDAO.class);
    }
    
    /**
    * This is the public null-arg constructor for this ControlBean.  If a control id
    * is not provided, a unique value will be auto-generated.
    */
    public AdminDAOBean()
    {
        this(null, null, null);
    }
    
    /**
    * This is the protected version that is used by any ControlBean subclass
    */
    protected AdminDAOBean(ControlBeanContext context, String id, PropertyMap props, Class controlClass)
    {
        super(context, id, props, controlClass);
        
    }
    
    
    /**
    * Returns an array of parameter names for the request method, or null if no parameter
    * data is available.
    */
    protected String [] getParameterNames(Method m)
    {
        // Check the local map for operations on this bean type
        if (_methodParamMap.containsKey(m))
        {
            return _methodParamMap.get(m);
        }
        
        // Delegate up if not found locally
        return super.getParameterNames(m);
    }
    
    /**
    * Implements com.defiance.ideal.qpd.admin.dao.AdminDAO.filterEmployeeDataChangeReq
    */
    public  com.defiance.ideal.qpd.admin.dto.AdminDTO[] filterEmployeeDataChangeReq(java.lang.String appraiserData, java.lang.String concatentedBand, java.lang.String dojCheck, java.lang.String concatenatedStatus, java.lang.String concatenatedStructure, int appraisalYear, int appraisalPeriod) 
    {
        Object [] __bc_argArray = new Object[] { appraiserData, concatentedBand, dojCheck, concatenatedStatus, concatenatedStructure, appraisalYear, appraisalPeriod };
        Throwable __bc_thrown = null;
        com.defiance.ideal.qpd.admin.dao.AdminDAO __bc_target = (com.defiance.ideal.qpd.admin.dao.AdminDAO)ensureControl();
        com.defiance.ideal.qpd.admin.dto.AdminDTO[] __bc_retval = null;
        
        try
        {
            preInvoke(_filterEmployeeDataChangeReqMethod, __bc_argArray);
            
            __bc_retval =
            __bc_target.filterEmployeeDataChangeReq(appraiserData, concatentedBand, dojCheck, concatenatedStatus, concatenatedStructure, appraisalYear, appraisalPeriod)
            ;
        }
        catch (Throwable __bc_t)
        {
            //
            // All exceptions are caught here, so postInvoke processing has visibility into
            // the exception status.  Errors, RuntimExceptions, or declared checked exceptions will
            // be rethrown.
            //
            __bc_thrown = __bc_t;
            
            if (__bc_t instanceof Error) throw (Error)__bc_t;
            else if (__bc_t instanceof RuntimeException) throw (RuntimeException)__bc_t;
            
            throw new UndeclaredThrowableException(__bc_t);
        }
        finally
        {
            Object __bc_rv = __bc_retval;
            postInvoke(_filterEmployeeDataChangeReqMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
        return __bc_retval;
    }
    
    /**
    * Implements com.defiance.ideal.qpd.admin.dao.AdminDAO.filterEmployeeData
    */
    public  com.defiance.ideal.qpd.admin.dto.AdminDTO[] filterEmployeeData(java.lang.String appraiserData, java.lang.String concatenatedBand, java.lang.String dojCheck, java.lang.String concatenatedStatus, java.lang.String concatenatedStructure, int appraisalYear, int appraisalPeriod) 
    {
        Object [] __bc_argArray = new Object[] { appraiserData, concatenatedBand, dojCheck, concatenatedStatus, concatenatedStructure, appraisalYear, appraisalPeriod };
        Throwable __bc_thrown = null;
        com.defiance.ideal.qpd.admin.dao.AdminDAO __bc_target = (com.defiance.ideal.qpd.admin.dao.AdminDAO)ensureControl();
        com.defiance.ideal.qpd.admin.dto.AdminDTO[] __bc_retval = null;
        
        try
        {
            preInvoke(_filterEmployeeDataMethod, __bc_argArray);
            
            __bc_retval =
            __bc_target.filterEmployeeData(appraiserData, concatenatedBand, dojCheck, concatenatedStatus, concatenatedStructure, appraisalYear, appraisalPeriod)
            ;
        }
        catch (Throwable __bc_t)
        {
            //
            // All exceptions are caught here, so postInvoke processing has visibility into
            // the exception status.  Errors, RuntimExceptions, or declared checked exceptions will
            // be rethrown.
            //
            __bc_thrown = __bc_t;
            
            if (__bc_t instanceof Error) throw (Error)__bc_t;
            else if (__bc_t instanceof RuntimeException) throw (RuntimeException)__bc_t;
            
            throw new UndeclaredThrowableException(__bc_t);
        }
        finally
        {
            Object __bc_rv = __bc_retval;
            postInvoke(_filterEmployeeDataMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
        return __bc_retval;
    }
    
    /**
    * Implements com.defiance.ideal.qpd.admin.dao.AdminDAO.getEmployeeName
    */
    public  com.defiance.ideal.qpd.admin.dto.AdminDTO[] getEmployeeName(java.lang.String searchString) 
    {
        Object [] __bc_argArray = new Object[] { searchString };
        Throwable __bc_thrown = null;
        com.defiance.ideal.qpd.admin.dao.AdminDAO __bc_target = (com.defiance.ideal.qpd.admin.dao.AdminDAO)ensureControl();
        com.defiance.ideal.qpd.admin.dto.AdminDTO[] __bc_retval = null;
        
        try
        {
            preInvoke(_getEmployeeNameMethod, __bc_argArray);
            
            __bc_retval =
            __bc_target.getEmployeeName(searchString)
            ;
        }
        catch (Throwable __bc_t)
        {
            //
            // All exceptions are caught here, so postInvoke processing has visibility into
            // the exception status.  Errors, RuntimExceptions, or declared checked exceptions will
            // be rethrown.
            //
            __bc_thrown = __bc_t;
            
            if (__bc_t instanceof Error) throw (Error)__bc_t;
            else if (__bc_t instanceof RuntimeException) throw (RuntimeException)__bc_t;
            
            throw new UndeclaredThrowableException(__bc_t);
        }
        finally
        {
            Object __bc_rv = __bc_retval;
            postInvoke(_getEmployeeNameMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
        return __bc_retval;
    }
    
    /**
    * Implements com.defiance.ideal.qpd.admin.dao.AdminDAO.triggerAppraisal
    */
    public  void triggerAppraisal(com.defiance.ideal.qpd.admin.dto.AdminDTO formData, int empId) 
    {
        Object [] __bc_argArray = new Object[] { formData, empId };
        Throwable __bc_thrown = null;
        com.defiance.ideal.qpd.admin.dao.AdminDAO __bc_target = (com.defiance.ideal.qpd.admin.dao.AdminDAO)ensureControl();
        
        try
        {
            preInvoke(_triggerAppraisalMethod, __bc_argArray);
            
            __bc_target.triggerAppraisal(formData, empId)
            ;
        }
        catch (Throwable __bc_t)
        {
            //
            // All exceptions are caught here, so postInvoke processing has visibility into
            // the exception status.  Errors, RuntimExceptions, or declared checked exceptions will
            // be rethrown.
            //
            __bc_thrown = __bc_t;
            
            if (__bc_t instanceof Error) throw (Error)__bc_t;
            else if (__bc_t instanceof RuntimeException) throw (RuntimeException)__bc_t;
            
            throw new UndeclaredThrowableException(__bc_t);
        }
        finally
        {
            Object __bc_rv = null;
            postInvoke(_triggerAppraisalMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
    }
    
    /**
    * Implements com.defiance.ideal.qpd.admin.dao.AdminDAO.getStructureDetails
    */
    public  com.defiance.ideal.qpd.admin.dto.AdminDTO[] getStructureDetails() 
    {
        Object [] __bc_argArray = new Object[] {  };
        Throwable __bc_thrown = null;
        com.defiance.ideal.qpd.admin.dao.AdminDAO __bc_target = (com.defiance.ideal.qpd.admin.dao.AdminDAO)ensureControl();
        com.defiance.ideal.qpd.admin.dto.AdminDTO[] __bc_retval = null;
        
        try
        {
            preInvoke(_getStructureDetailsMethod, __bc_argArray);
            
            __bc_retval =
            __bc_target.getStructureDetails()
            ;
        }
        catch (Throwable __bc_t)
        {
            //
            // All exceptions are caught here, so postInvoke processing has visibility into
            // the exception status.  Errors, RuntimExceptions, or declared checked exceptions will
            // be rethrown.
            //
            __bc_thrown = __bc_t;
            
            if (__bc_t instanceof Error) throw (Error)__bc_t;
            else if (__bc_t instanceof RuntimeException) throw (RuntimeException)__bc_t;
            
            throw new UndeclaredThrowableException(__bc_t);
        }
        finally
        {
            Object __bc_rv = __bc_retval;
            postInvoke(_getStructureDetailsMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
        return __bc_retval;
    }
    
    /**
    * Implements com.defiance.ideal.qpd.admin.dao.AdminDAO.getBandData
    */
    public  com.defiance.ideal.qpd.admin.dto.AdminDTO[] getBandData() 
    {
        Object [] __bc_argArray = new Object[] {  };
        Throwable __bc_thrown = null;
        com.defiance.ideal.qpd.admin.dao.AdminDAO __bc_target = (com.defiance.ideal.qpd.admin.dao.AdminDAO)ensureControl();
        com.defiance.ideal.qpd.admin.dto.AdminDTO[] __bc_retval = null;
        
        try
        {
            preInvoke(_getBandDataMethod, __bc_argArray);
            
            __bc_retval =
            __bc_target.getBandData()
            ;
        }
        catch (Throwable __bc_t)
        {
            //
            // All exceptions are caught here, so postInvoke processing has visibility into
            // the exception status.  Errors, RuntimExceptions, or declared checked exceptions will
            // be rethrown.
            //
            __bc_thrown = __bc_t;
            
            if (__bc_t instanceof Error) throw (Error)__bc_t;
            else if (__bc_t instanceof RuntimeException) throw (RuntimeException)__bc_t;
            
            throw new UndeclaredThrowableException(__bc_t);
        }
        finally
        {
            Object __bc_rv = __bc_retval;
            postInvoke(_getBandDataMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
        return __bc_retval;
    }
    
    /**
    * Implements com.defiance.ideal.qpd.admin.dao.AdminDAO.updateAppraisal
    */
    public  void updateAppraisal(com.defiance.ideal.qpd.admin.dto.AdminDTO formData, int empId) 
    {
        Object [] __bc_argArray = new Object[] { formData, empId };
        Throwable __bc_thrown = null;
        com.defiance.ideal.qpd.admin.dao.AdminDAO __bc_target = (com.defiance.ideal.qpd.admin.dao.AdminDAO)ensureControl();
        
        try
        {
            preInvoke(_updateAppraisalMethod, __bc_argArray);
            
            __bc_target.updateAppraisal(formData, empId)
            ;
        }
        catch (Throwable __bc_t)
        {
            //
            // All exceptions are caught here, so postInvoke processing has visibility into
            // the exception status.  Errors, RuntimExceptions, or declared checked exceptions will
            // be rethrown.
            //
            __bc_thrown = __bc_t;
            
            if (__bc_t instanceof Error) throw (Error)__bc_t;
            else if (__bc_t instanceof RuntimeException) throw (RuntimeException)__bc_t;
            
            throw new UndeclaredThrowableException(__bc_t);
        }
        finally
        {
            Object __bc_rv = null;
            postInvoke(_updateAppraisalMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
    }
    
    
    /**
    * A PropertyKey that can be used to access the controlImplementation property of the
    * BaseProperties PropertySet
    */
    public static final PropertyKey ControlImplementationKey = new PropertyKey(org.apache.beehive.controls.api.properties.BaseProperties.class, "controlImplementation");
    
    public synchronized void setControlImplementation(java.lang.String value)
    {
        
        setControlProperty(ControlImplementationKey, value);
    }
    
    public java.lang.String getControlImplementation()
    {
        return (java.lang.String)getControlProperty(ControlImplementationKey);
    }
    
    
    
    
    
    /**
    * The _annotCache maintains a lookup cache from AnnotatedElements to an associated
    * PropertyMap.  This enables these maps to be shared across multiple beans.
    */
    static private HashMap __bc_annotCache = new HashMap();
    
    protected Map getPropertyMapCache() { return __bc_annotCache; }
    
    private static final long serialVersionUID = 1L;
}
