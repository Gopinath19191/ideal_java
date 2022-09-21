
package com.defiance.ideal.qpd.appraiser.bo;

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
public class AppraiserBOBean
extends org.apache.beehive.controls.runtime.bean.ControlBean
implements com.defiance.ideal.qpd.appraiser.bo.AppraiserBO
{
    static final Method _getSubmitStatusMethod;
    static final Method _getAchievementsDataMethod;
    static final Method _getAppraiseeListMethod;
    static final Method _getGoalDataMethod;
    static final Method _getDevelopmentDataMethod;
    static final Method _updateAppraiseeStatusMethod;
    static final Method _getKraDataMethod;
    static final Method _updateAppraiseeDataMethod;
    
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
            _getSubmitStatusMethod = com.defiance.ideal.qpd.appraiser.bo.AppraiserBO.class.getMethod("getSubmitStatus", new Class [] {int.class, int.class, int.class});
            _methodParamMap.put(_getSubmitStatusMethod, new String [] { "appraiseeId", "appraisalQuarter", "appraisalYear" });
            _getAchievementsDataMethod = com.defiance.ideal.qpd.appraiser.bo.AppraiserBO.class.getMethod("getAchievementsData", new Class [] {int.class, int.class});
            _methodParamMap.put(_getAchievementsDataMethod, new String [] { "appraisalYear", "appraiseeId" });
            _getAppraiseeListMethod = com.defiance.ideal.qpd.appraiser.bo.AppraiserBO.class.getMethod("getAppraiseeList", new Class [] {int.class, int.class, int.class});
            _methodParamMap.put(_getAppraiseeListMethod, new String [] { "employeeId", "appraisalYear", "appraisalQuarter" });
            _getGoalDataMethod = com.defiance.ideal.qpd.appraiser.bo.AppraiserBO.class.getMethod("getGoalData", new Class [] {int.class, int.class});
            _methodParamMap.put(_getGoalDataMethod, new String [] { "appraisalYear", "appraiseeId" });
            _getDevelopmentDataMethod = com.defiance.ideal.qpd.appraiser.bo.AppraiserBO.class.getMethod("getDevelopmentData", new Class [] {int.class, int.class});
            _methodParamMap.put(_getDevelopmentDataMethod, new String [] { "appraisalYear", "appraiseeId" });
            _updateAppraiseeStatusMethod = com.defiance.ideal.qpd.appraiser.bo.AppraiserBO.class.getMethod("updateAppraiseeStatus", new Class [] {int.class, int.class, int.class, int.class, java.lang.String.class});
            _methodParamMap.put(_updateAppraiseeStatusMethod, new String [] { "appraiseeId", "appraiseeQuarter", "appraiseeYear", "sendBackStatus", "reasonAppraiser" });
            _getKraDataMethod = com.defiance.ideal.qpd.appraiser.bo.AppraiserBO.class.getMethod("getKraData", new Class [] {int.class, int.class, int.class, int.class, int.class});
            _methodParamMap.put(_getKraDataMethod, new String [] { "bandIdForm", "appraisalQuarterForm", "appraisalYearForm", "appraiseeIdForm", "departmentId" });
            _updateAppraiseeDataMethod = com.defiance.ideal.qpd.appraiser.bo.AppraiserBO.class.getMethod("updateAppraiseeData", new Class [] {com.defiance.ideal.qpd.appraiser.dto.AppraiserRatingFormDTO.class});
            _methodParamMap.put(_updateAppraiseeDataMethod, new String [] { "formData" });
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
    public AppraiserBOBean(ControlBeanContext context, String id, PropertyMap props)
    {
        this(context, id, props, com.defiance.ideal.qpd.appraiser.bo.AppraiserBO.class);
    }
    
    /**
    * This is the public null-arg constructor for this ControlBean.  If a control id
    * is not provided, a unique value will be auto-generated.
    */
    public AppraiserBOBean()
    {
        this(null, null, null);
    }
    
    /**
    * This is the protected version that is used by any ControlBean subclass
    */
    protected AppraiserBOBean(ControlBeanContext context, String id, PropertyMap props, Class controlClass)
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
    * Implements com.defiance.ideal.qpd.appraiser.bo.AppraiserBO.getSubmitStatus
    */
    public  com.defiance.ideal.qpd.appraiser.dto.AppraiseeListDTO getSubmitStatus(int appraiseeId, int appraisalQuarter, int appraisalYear) 
    {
        Object [] __bc_argArray = new Object[] { appraiseeId, appraisalQuarter, appraisalYear };
        Throwable __bc_thrown = null;
        com.defiance.ideal.qpd.appraiser.bo.AppraiserBO __bc_target = (com.defiance.ideal.qpd.appraiser.bo.AppraiserBO)ensureControl();
        com.defiance.ideal.qpd.appraiser.dto.AppraiseeListDTO __bc_retval = null;
        
        try
        {
            preInvoke(_getSubmitStatusMethod, __bc_argArray);
            
            __bc_retval =
            __bc_target.getSubmitStatus(appraiseeId, appraisalQuarter, appraisalYear)
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
            postInvoke(_getSubmitStatusMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
        return __bc_retval;
    }
    
    /**
    * Implements com.defiance.ideal.qpd.appraiser.bo.AppraiserBO.getAchievementsData
    */
    public  com.defiance.ideal.qpd.appraiser.dto.AppraiserFormDTO[] getAchievementsData(int appraisalYear, int appraiseeId) 
    {
        Object [] __bc_argArray = new Object[] { appraisalYear, appraiseeId };
        Throwable __bc_thrown = null;
        com.defiance.ideal.qpd.appraiser.bo.AppraiserBO __bc_target = (com.defiance.ideal.qpd.appraiser.bo.AppraiserBO)ensureControl();
        com.defiance.ideal.qpd.appraiser.dto.AppraiserFormDTO[] __bc_retval = null;
        
        try
        {
            preInvoke(_getAchievementsDataMethod, __bc_argArray);
            
            __bc_retval =
            __bc_target.getAchievementsData(appraisalYear, appraiseeId)
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
            postInvoke(_getAchievementsDataMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
        return __bc_retval;
    }
    
    /**
    * Implements com.defiance.ideal.qpd.appraiser.bo.AppraiserBO.getAppraiseeList
    */
    public  com.defiance.ideal.qpd.appraiser.dto.AppraiseeListDTO[] getAppraiseeList(int employeeId, int appraisalYear, int appraisalQuarter) 
    {
        Object [] __bc_argArray = new Object[] { employeeId, appraisalYear, appraisalQuarter };
        Throwable __bc_thrown = null;
        com.defiance.ideal.qpd.appraiser.bo.AppraiserBO __bc_target = (com.defiance.ideal.qpd.appraiser.bo.AppraiserBO)ensureControl();
        com.defiance.ideal.qpd.appraiser.dto.AppraiseeListDTO[] __bc_retval = null;
        
        try
        {
            preInvoke(_getAppraiseeListMethod, __bc_argArray);
            
            __bc_retval =
            __bc_target.getAppraiseeList(employeeId, appraisalYear, appraisalQuarter)
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
            postInvoke(_getAppraiseeListMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
        return __bc_retval;
    }
    
    /**
    * Implements com.defiance.ideal.qpd.appraiser.bo.AppraiserBO.getGoalData
    */
    public  com.defiance.ideal.qpd.appraiser.dto.AppraiserFormDTO[] getGoalData(int appraisalYear, int appraiseeId) 
    {
        Object [] __bc_argArray = new Object[] { appraisalYear, appraiseeId };
        Throwable __bc_thrown = null;
        com.defiance.ideal.qpd.appraiser.bo.AppraiserBO __bc_target = (com.defiance.ideal.qpd.appraiser.bo.AppraiserBO)ensureControl();
        com.defiance.ideal.qpd.appraiser.dto.AppraiserFormDTO[] __bc_retval = null;
        
        try
        {
            preInvoke(_getGoalDataMethod, __bc_argArray);
            
            __bc_retval =
            __bc_target.getGoalData(appraisalYear, appraiseeId)
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
            postInvoke(_getGoalDataMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
        return __bc_retval;
    }
    
    /**
    * Implements com.defiance.ideal.qpd.appraiser.bo.AppraiserBO.getDevelopmentData
    */
    public  com.defiance.ideal.qpd.appraiser.dto.AppraiserFormDTO[] getDevelopmentData(int appraisalYear, int appraiseeId) 
    {
        Object [] __bc_argArray = new Object[] { appraisalYear, appraiseeId };
        Throwable __bc_thrown = null;
        com.defiance.ideal.qpd.appraiser.bo.AppraiserBO __bc_target = (com.defiance.ideal.qpd.appraiser.bo.AppraiserBO)ensureControl();
        com.defiance.ideal.qpd.appraiser.dto.AppraiserFormDTO[] __bc_retval = null;
        
        try
        {
            preInvoke(_getDevelopmentDataMethod, __bc_argArray);
            
            __bc_retval =
            __bc_target.getDevelopmentData(appraisalYear, appraiseeId)
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
            postInvoke(_getDevelopmentDataMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
        return __bc_retval;
    }
    
    /**
    * Implements com.defiance.ideal.qpd.appraiser.bo.AppraiserBO.updateAppraiseeStatus
    */
    public  void updateAppraiseeStatus(int appraiseeId, int appraiseeQuarter, int appraiseeYear, int sendBackStatus, java.lang.String reasonAppraiser) 
    {
        Object [] __bc_argArray = new Object[] { appraiseeId, appraiseeQuarter, appraiseeYear, sendBackStatus, reasonAppraiser };
        Throwable __bc_thrown = null;
        com.defiance.ideal.qpd.appraiser.bo.AppraiserBO __bc_target = (com.defiance.ideal.qpd.appraiser.bo.AppraiserBO)ensureControl();
        
        try
        {
            preInvoke(_updateAppraiseeStatusMethod, __bc_argArray);
            
            __bc_target.updateAppraiseeStatus(appraiseeId, appraiseeQuarter, appraiseeYear, sendBackStatus, reasonAppraiser)
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
            postInvoke(_updateAppraiseeStatusMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
    }
    
    /**
    * Implements com.defiance.ideal.qpd.appraiser.bo.AppraiserBO.getKraData
    */
    public  com.defiance.ideal.qpd.appraiser.dto.AppraiserFormDTO[] getKraData(int bandIdForm, int appraisalQuarterForm, int appraisalYearForm, int appraiseeIdForm, int departmentId) 
    {
        Object [] __bc_argArray = new Object[] { bandIdForm, appraisalQuarterForm, appraisalYearForm, appraiseeIdForm, departmentId };
        Throwable __bc_thrown = null;
        com.defiance.ideal.qpd.appraiser.bo.AppraiserBO __bc_target = (com.defiance.ideal.qpd.appraiser.bo.AppraiserBO)ensureControl();
        com.defiance.ideal.qpd.appraiser.dto.AppraiserFormDTO[] __bc_retval = null;
        
        try
        {
            preInvoke(_getKraDataMethod, __bc_argArray);
            
            __bc_retval =
            __bc_target.getKraData(bandIdForm, appraisalQuarterForm, appraisalYearForm, appraiseeIdForm, departmentId)
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
            postInvoke(_getKraDataMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
        return __bc_retval;
    }
    
    /**
    * Implements com.defiance.ideal.qpd.appraiser.bo.AppraiserBO.updateAppraiseeData
    */
    public  void updateAppraiseeData(com.defiance.ideal.qpd.appraiser.dto.AppraiserRatingFormDTO formData) 
    {
        Object [] __bc_argArray = new Object[] { formData };
        Throwable __bc_thrown = null;
        com.defiance.ideal.qpd.appraiser.bo.AppraiserBO __bc_target = (com.defiance.ideal.qpd.appraiser.bo.AppraiserBO)ensureControl();
        
        try
        {
            preInvoke(_updateAppraiseeDataMethod, __bc_argArray);
            
            __bc_target.updateAppraiseeData(formData)
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
            postInvoke(_updateAppraiseeDataMethod, __bc_argArray, __bc_rv, __bc_thrown);
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
