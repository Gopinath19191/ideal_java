
package com.defiance.ideal.qpd.admin.bo;

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
public class AdminBOBean
extends org.apache.beehive.controls.runtime.bean.ControlBean
implements com.defiance.ideal.qpd.admin.bo.AdminBO
{
    static final Method _getEmployeeNameMethod;
    static final Method _filterAppraiserMethod;
    static final Method _filterEmployeesMethod;
    static final Method _triggerAppraisalMethod;
    static final Method _getCompanyStructureMethod;
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
            _getEmployeeNameMethod = com.defiance.ideal.qpd.admin.bo.AdminBO.class.getMethod("getEmployeeName", new Class [] {java.lang.String.class});
            _methodParamMap.put(_getEmployeeNameMethod, new String [] { "searchString" });
            _filterAppraiserMethod = com.defiance.ideal.qpd.admin.bo.AdminBO.class.getMethod("filterAppraiser", new Class [] {java.lang.String.class});
            _methodParamMap.put(_filterAppraiserMethod, new String [] { "changeRequest" });
            _filterEmployeesMethod = com.defiance.ideal.qpd.admin.bo.AdminBO.class.getMethod("filterEmployees", new Class [] {javax.servlet.http.HttpServletRequest.class, com.defiance.ideal.qpd.admin.dto.AdminFilterDTO.class, java.lang.String.class});
            _methodParamMap.put(_filterEmployeesMethod, new String [] { "requestObj", "formData", "changeRequest" });
            _triggerAppraisalMethod = com.defiance.ideal.qpd.admin.bo.AdminBO.class.getMethod("triggerAppraisal", new Class [] {com.defiance.ideal.qpd.admin.dto.AdminDTO.class, int.class});
            _methodParamMap.put(_triggerAppraisalMethod, new String [] { "formData", "empId" });
            _getCompanyStructureMethod = com.defiance.ideal.qpd.admin.bo.AdminBO.class.getMethod("getCompanyStructure", new Class [] {});
            _methodParamMap.put(_getCompanyStructureMethod, new String [] {  });
            _getBandDataMethod = com.defiance.ideal.qpd.admin.bo.AdminBO.class.getMethod("getBandData", new Class [] {});
            _methodParamMap.put(_getBandDataMethod, new String [] {  });
            _updateAppraisalMethod = com.defiance.ideal.qpd.admin.bo.AdminBO.class.getMethod("updateAppraisal", new Class [] {com.defiance.ideal.qpd.admin.dto.AdminDTO.class, int.class});
            _methodParamMap.put(_updateAppraisalMethod, new String [] { "formData", "parseInt" });
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
    public AdminBOBean(ControlBeanContext context, String id, PropertyMap props)
    {
        this(context, id, props, com.defiance.ideal.qpd.admin.bo.AdminBO.class);
    }
    
    /**
    * This is the public null-arg constructor for this ControlBean.  If a control id
    * is not provided, a unique value will be auto-generated.
    */
    public AdminBOBean()
    {
        this(null, null, null);
    }
    
    /**
    * This is the protected version that is used by any ControlBean subclass
    */
    protected AdminBOBean(ControlBeanContext context, String id, PropertyMap props, Class controlClass)
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
    * Implements com.defiance.ideal.qpd.admin.bo.AdminBO.getEmployeeName
    */
    public  com.defiance.ideal.qpd.admin.dto.AdminDTO[] getEmployeeName(java.lang.String searchString) 
    {
        Object [] __bc_argArray = new Object[] { searchString };
        Throwable __bc_thrown = null;
        com.defiance.ideal.qpd.admin.bo.AdminBO __bc_target = (com.defiance.ideal.qpd.admin.bo.AdminBO)ensureControl();
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
    * Implements com.defiance.ideal.qpd.admin.bo.AdminBO.filterAppraiser
    */
    public  com.defiance.ideal.qpd.admin.dto.AdminDTO[] filterAppraiser(java.lang.String changeRequest) 
    {
        Object [] __bc_argArray = new Object[] { changeRequest };
        Throwable __bc_thrown = null;
        com.defiance.ideal.qpd.admin.bo.AdminBO __bc_target = (com.defiance.ideal.qpd.admin.bo.AdminBO)ensureControl();
        com.defiance.ideal.qpd.admin.dto.AdminDTO[] __bc_retval = null;
        
        try
        {
            preInvoke(_filterAppraiserMethod, __bc_argArray);
            
            __bc_retval =
            __bc_target.filterAppraiser(changeRequest)
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
            postInvoke(_filterAppraiserMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
        return __bc_retval;
    }
    
    /**
    * Implements com.defiance.ideal.qpd.admin.bo.AdminBO.filterEmployees
    */
    public  com.defiance.ideal.qpd.admin.dto.AdminDTO[] filterEmployees(javax.servlet.http.HttpServletRequest requestObj, com.defiance.ideal.qpd.admin.dto.AdminFilterDTO formData, java.lang.String changeRequest) 
    {
        Object [] __bc_argArray = new Object[] { requestObj, formData, changeRequest };
        Throwable __bc_thrown = null;
        com.defiance.ideal.qpd.admin.bo.AdminBO __bc_target = (com.defiance.ideal.qpd.admin.bo.AdminBO)ensureControl();
        com.defiance.ideal.qpd.admin.dto.AdminDTO[] __bc_retval = null;
        
        try
        {
            preInvoke(_filterEmployeesMethod, __bc_argArray);
            
            __bc_retval =
            __bc_target.filterEmployees(requestObj, formData, changeRequest)
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
            postInvoke(_filterEmployeesMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
        return __bc_retval;
    }
    
    /**
    * Implements com.defiance.ideal.qpd.admin.bo.AdminBO.triggerAppraisal
    */
    public  void triggerAppraisal(com.defiance.ideal.qpd.admin.dto.AdminDTO formData, int empId) 
    {
        Object [] __bc_argArray = new Object[] { formData, empId };
        Throwable __bc_thrown = null;
        com.defiance.ideal.qpd.admin.bo.AdminBO __bc_target = (com.defiance.ideal.qpd.admin.bo.AdminBO)ensureControl();
        
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
    * Implements com.defiance.ideal.qpd.admin.bo.AdminBO.getCompanyStructure
    */
    public  com.defiance.ideal.qpd.admin.dto.AdminDTO[] getCompanyStructure() 
    {
        Object [] __bc_argArray = new Object[] {  };
        Throwable __bc_thrown = null;
        com.defiance.ideal.qpd.admin.bo.AdminBO __bc_target = (com.defiance.ideal.qpd.admin.bo.AdminBO)ensureControl();
        com.defiance.ideal.qpd.admin.dto.AdminDTO[] __bc_retval = null;
        
        try
        {
            preInvoke(_getCompanyStructureMethod, __bc_argArray);
            
            __bc_retval =
            __bc_target.getCompanyStructure()
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
            postInvoke(_getCompanyStructureMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
        return __bc_retval;
    }
    
    /**
    * Implements com.defiance.ideal.qpd.admin.bo.AdminBO.getBandData
    */
    public  com.defiance.ideal.qpd.admin.dto.AdminDTO[] getBandData() 
    {
        Object [] __bc_argArray = new Object[] {  };
        Throwable __bc_thrown = null;
        com.defiance.ideal.qpd.admin.bo.AdminBO __bc_target = (com.defiance.ideal.qpd.admin.bo.AdminBO)ensureControl();
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
    * Implements com.defiance.ideal.qpd.admin.bo.AdminBO.updateAppraisal
    */
    public  void updateAppraisal(com.defiance.ideal.qpd.admin.dto.AdminDTO formData, int parseInt) 
    {
        Object [] __bc_argArray = new Object[] { formData, parseInt };
        Throwable __bc_thrown = null;
        com.defiance.ideal.qpd.admin.bo.AdminBO __bc_target = (com.defiance.ideal.qpd.admin.bo.AdminBO)ensureControl();
        
        try
        {
            preInvoke(_updateAppraisalMethod, __bc_argArray);
            
            __bc_target.updateAppraisal(formData, parseInt)
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
