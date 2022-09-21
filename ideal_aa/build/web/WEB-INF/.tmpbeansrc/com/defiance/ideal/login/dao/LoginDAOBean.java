
package com.defiance.ideal.login.dao;

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
public class LoginDAOBean
extends org.apache.beehive.controls.runtime.bean.ControlBean
implements com.defiance.ideal.login.dao.LoginDAO
{
    static final Method _menuCheckMethod;
    static final Method _authenticateLoginDetailsMethod;
    static final Method _updateManagerSurveyMethod;
    static final Method _authenticateUserMethod;
    static final Method _authenticateGroupMethod;
    static final Method _managerSurveyMethod;
    
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
            _menuCheckMethod = com.defiance.ideal.login.dao.LoginDAO.class.getMethod("menuCheck", new Class [] {java.lang.String.class, int.class, int.class});
            _methodParamMap.put(_menuCheckMethod, new String [] { "referenceName", "currentYear", "employeeId" });
            _authenticateLoginDetailsMethod = com.defiance.ideal.login.dao.LoginDAO.class.getMethod("authenticateLoginDetails", new Class [] {javax.servlet.http.HttpServletRequest.class});
            _methodParamMap.put(_authenticateLoginDetailsMethod, new String [] { "request" });
            _updateManagerSurveyMethod = com.defiance.ideal.login.dao.LoginDAO.class.getMethod("updateManagerSurvey", new Class [] {int.class, int.class, int.class, int.class});
            _methodParamMap.put(_updateManagerSurveyMethod, new String [] { "currentYear", "currentQuarter", "employeeId", "surveyStatus" });
            _authenticateUserMethod = com.defiance.ideal.login.dao.LoginDAO.class.getMethod("authenticateUser", new Class [] {java.lang.String.class, java.lang.String.class});
            _methodParamMap.put(_authenticateUserMethod, new String [] { "empId", "MODULE_ID" });
            _authenticateGroupMethod = com.defiance.ideal.login.dao.LoginDAO.class.getMethod("authenticateGroup", new Class [] {java.lang.String.class, java.lang.String.class});
            _methodParamMap.put(_authenticateGroupMethod, new String [] { "groupId", "MODULE_ID" });
            _managerSurveyMethod = com.defiance.ideal.login.dao.LoginDAO.class.getMethod("managerSurvey", new Class [] {int.class, int.class});
            _methodParamMap.put(_managerSurveyMethod, new String [] { "currentYear", "employeeId" });
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
    public LoginDAOBean(ControlBeanContext context, String id, PropertyMap props)
    {
        this(context, id, props, com.defiance.ideal.login.dao.LoginDAO.class);
    }
    
    /**
    * This is the public null-arg constructor for this ControlBean.  If a control id
    * is not provided, a unique value will be auto-generated.
    */
    public LoginDAOBean()
    {
        this(null, null, null);
    }
    
    /**
    * This is the protected version that is used by any ControlBean subclass
    */
    protected LoginDAOBean(ControlBeanContext context, String id, PropertyMap props, Class controlClass)
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
    * Implements com.defiance.ideal.login.dao.LoginDAO.menuCheck
    */
    public  com.defiance.ideal.login.dto.LoginFormDTO menuCheck(java.lang.String referenceName, int currentYear, int employeeId) 
    {
        Object [] __bc_argArray = new Object[] { referenceName, currentYear, employeeId };
        Throwable __bc_thrown = null;
        com.defiance.ideal.login.dao.LoginDAO __bc_target = (com.defiance.ideal.login.dao.LoginDAO)ensureControl();
        com.defiance.ideal.login.dto.LoginFormDTO __bc_retval = null;
        
        try
        {
            preInvoke(_menuCheckMethod, __bc_argArray);
            
            __bc_retval =
            __bc_target.menuCheck(referenceName, currentYear, employeeId)
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
            postInvoke(_menuCheckMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
        return __bc_retval;
    }
    
    /**
    * Implements com.defiance.ideal.login.dao.LoginDAO.authenticateLoginDetails
    */
    public  com.defiance.ideal.login.dto.LoginFormDTO authenticateLoginDetails(javax.servlet.http.HttpServletRequest request) throws java.lang.Exception
    {
        Object [] __bc_argArray = new Object[] { request };
        Throwable __bc_thrown = null;
        com.defiance.ideal.login.dao.LoginDAO __bc_target = (com.defiance.ideal.login.dao.LoginDAO)ensureControl();
        com.defiance.ideal.login.dto.LoginFormDTO __bc_retval = null;
        
        try
        {
            preInvoke(_authenticateLoginDetailsMethod, __bc_argArray);
            
            __bc_retval =
            __bc_target.authenticateLoginDetails(request)
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
            else if (__bc_t instanceof java.lang.Exception) throw (java.lang.Exception)__bc_t;
            
            throw new UndeclaredThrowableException(__bc_t);
        }
        finally
        {
            Object __bc_rv = __bc_retval;
            postInvoke(_authenticateLoginDetailsMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
        return __bc_retval;
    }
    
    /**
    * Implements com.defiance.ideal.login.dao.LoginDAO.updateManagerSurvey
    */
    public  void updateManagerSurvey(int currentYear, int currentQuarter, int employeeId, int surveyStatus) 
    {
        Object [] __bc_argArray = new Object[] { currentYear, currentQuarter, employeeId, surveyStatus };
        Throwable __bc_thrown = null;
        com.defiance.ideal.login.dao.LoginDAO __bc_target = (com.defiance.ideal.login.dao.LoginDAO)ensureControl();
        
        try
        {
            preInvoke(_updateManagerSurveyMethod, __bc_argArray);
            
            __bc_target.updateManagerSurvey(currentYear, currentQuarter, employeeId, surveyStatus)
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
            postInvoke(_updateManagerSurveyMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
    }
    
    /**
    * Implements com.defiance.ideal.login.dao.LoginDAO.authenticateUser
    */
    public  com.defiance.ideal.login.dto.LoginFormDTO authenticateUser(java.lang.String empId, java.lang.String MODULE_ID) 
    {
        Object [] __bc_argArray = new Object[] { empId, MODULE_ID };
        Throwable __bc_thrown = null;
        com.defiance.ideal.login.dao.LoginDAO __bc_target = (com.defiance.ideal.login.dao.LoginDAO)ensureControl();
        com.defiance.ideal.login.dto.LoginFormDTO __bc_retval = null;
        
        try
        {
            preInvoke(_authenticateUserMethod, __bc_argArray);
            
            __bc_retval =
            __bc_target.authenticateUser(empId, MODULE_ID)
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
            postInvoke(_authenticateUserMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
        return __bc_retval;
    }
    
    /**
    * Implements com.defiance.ideal.login.dao.LoginDAO.authenticateGroup
    */
    public  com.defiance.ideal.login.dto.LoginFormDTO authenticateGroup(java.lang.String groupId, java.lang.String MODULE_ID) 
    {
        Object [] __bc_argArray = new Object[] { groupId, MODULE_ID };
        Throwable __bc_thrown = null;
        com.defiance.ideal.login.dao.LoginDAO __bc_target = (com.defiance.ideal.login.dao.LoginDAO)ensureControl();
        com.defiance.ideal.login.dto.LoginFormDTO __bc_retval = null;
        
        try
        {
            preInvoke(_authenticateGroupMethod, __bc_argArray);
            
            __bc_retval =
            __bc_target.authenticateGroup(groupId, MODULE_ID)
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
            postInvoke(_authenticateGroupMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
        return __bc_retval;
    }
    
    /**
    * Implements com.defiance.ideal.login.dao.LoginDAO.managerSurvey
    */
    public  com.defiance.ideal.login.dto.LoginFormDTO managerSurvey(int currentYear, int employeeId) 
    {
        Object [] __bc_argArray = new Object[] { currentYear, employeeId };
        Throwable __bc_thrown = null;
        com.defiance.ideal.login.dao.LoginDAO __bc_target = (com.defiance.ideal.login.dao.LoginDAO)ensureControl();
        com.defiance.ideal.login.dto.LoginFormDTO __bc_retval = null;
        
        try
        {
            preInvoke(_managerSurveyMethod, __bc_argArray);
            
            __bc_retval =
            __bc_target.managerSurvey(currentYear, employeeId)
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
            postInvoke(_managerSurveyMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
        return __bc_retval;
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
