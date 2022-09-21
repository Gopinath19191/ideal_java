
package com.defiance.ideal.qpd.managers.bo;

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
public class ManagerBOBean
extends org.apache.beehive.controls.runtime.bean.ControlBean
implements com.defiance.ideal.qpd.managers.bo.ManagerBO
{
    static final Method _getAppraiseesByHrMethod;
    static final Method _getAppraiseesByFinanceMethod;
    static final Method _getAppraiseesByNormalizerMethod;
    static final Method _getAppraiseesByReviewerMethod;
    static final Method _updateNormalizerRatingMethod;
    static final Method _getDevelopmentDataMethod;
    static final Method _getKraDataMethod;
    static final Method _updateReviewerRatingMethod;
    static final Method _getSubmitStatusMethod;
    static final Method _updateFinalStatusMethod;
    static final Method _getEmployeesCountMethod;
    static final Method _getAchievementsDataMethod;
    static final Method _getGoalDataMethod;
    
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
            _getAppraiseesByHrMethod = com.defiance.ideal.qpd.managers.bo.ManagerBO.class.getMethod("getAppraiseesByHr", new Class [] {javax.servlet.http.HttpServletRequest.class, java.lang.String.class, int.class, int.class});
            _methodParamMap.put(_getAppraiseesByHrMethod, new String [] { "request", "hrId", "appraisalQuarter", "appraisalYear" });
            _getAppraiseesByFinanceMethod = com.defiance.ideal.qpd.managers.bo.ManagerBO.class.getMethod("getAppraiseesByFinance", new Class [] {javax.servlet.http.HttpServletRequest.class, java.lang.String.class, int.class, int.class});
            _methodParamMap.put(_getAppraiseesByFinanceMethod, new String [] { "request", "financeId", "appraisalQuarter", "appraisalYear" });
            _getAppraiseesByNormalizerMethod = com.defiance.ideal.qpd.managers.bo.ManagerBO.class.getMethod("getAppraiseesByNormalizer", new Class [] {javax.servlet.http.HttpServletRequest.class, java.lang.String.class, int.class, int.class});
            _methodParamMap.put(_getAppraiseesByNormalizerMethod, new String [] { "request", "normalizerId", "appraisalQuarter", "appraisalYear" });
            _getAppraiseesByReviewerMethod = com.defiance.ideal.qpd.managers.bo.ManagerBO.class.getMethod("getAppraiseesByReviewer", new Class [] {javax.servlet.http.HttpServletRequest.class, java.lang.String.class, int.class, int.class});
            _methodParamMap.put(_getAppraiseesByReviewerMethod, new String [] { "request", "reviewerId", "appraisalQuarter", "appraisalYear" });
            _updateNormalizerRatingMethod = com.defiance.ideal.qpd.managers.bo.ManagerBO.class.getMethod("updateNormalizerRating", new Class [] {java.lang.String.class, int.class, int.class, com.defiance.ideal.qpd.managers.dto.ManagerDTO.class, java.lang.String.class});
            _methodParamMap.put(_updateNormalizerRatingMethod, new String [] { "normalizerId", "appraisalQuarter", "appraisalYear", "formData", "button" });
            _getDevelopmentDataMethod = com.defiance.ideal.qpd.managers.bo.ManagerBO.class.getMethod("getDevelopmentData", new Class [] {int.class, int.class});
            _methodParamMap.put(_getDevelopmentDataMethod, new String [] { "appraisalYear", "appraiseeId" });
            _getKraDataMethod = com.defiance.ideal.qpd.managers.bo.ManagerBO.class.getMethod("getKraData", new Class [] {int.class, int.class, int.class, int.class, int.class});
            _methodParamMap.put(_getKraDataMethod, new String [] { "bandId", "appraisalQuarter", "appraisalYear", "appraiseeId", "departmentId" });
            _updateReviewerRatingMethod = com.defiance.ideal.qpd.managers.bo.ManagerBO.class.getMethod("updateReviewerRating", new Class [] {java.lang.String.class, int.class, int.class, com.defiance.ideal.qpd.managers.dto.ManagerDTO.class, java.lang.String.class});
            _methodParamMap.put(_updateReviewerRatingMethod, new String [] { "reviewerId", "appraisalQuarter", "appraisalYear", "formData", "button" });
            _getSubmitStatusMethod = com.defiance.ideal.qpd.managers.bo.ManagerBO.class.getMethod("getSubmitStatus", new Class [] {int.class, int.class, int.class});
            _methodParamMap.put(_getSubmitStatusMethod, new String [] { "appraiseeId", "appraisalQuarter", "appraisalYear" });
            _updateFinalStatusMethod = com.defiance.ideal.qpd.managers.bo.ManagerBO.class.getMethod("updateFinalStatus", new Class [] {java.lang.String.class, int.class, int.class, com.defiance.ideal.qpd.managers.dto.ManagerDTO.class, java.lang.String.class});
            _methodParamMap.put(_updateFinalStatusMethod, new String [] { "hrId", "appraisalQuarter", "appraisalYear", "appraiseesbyHr", "button" });
            _getEmployeesCountMethod = com.defiance.ideal.qpd.managers.bo.ManagerBO.class.getMethod("getEmployeesCount", new Class [] {java.lang.String.class, int.class, int.class, java.lang.String.class});
            _methodParamMap.put(_getEmployeesCountMethod, new String [] { "reviewerId", "appraisalQuarter", "appraisalYear", "referenceType" });
            _getAchievementsDataMethod = com.defiance.ideal.qpd.managers.bo.ManagerBO.class.getMethod("getAchievementsData", new Class [] {int.class, int.class});
            _methodParamMap.put(_getAchievementsDataMethod, new String [] { "appraisalYear", "appraiseeId" });
            _getGoalDataMethod = com.defiance.ideal.qpd.managers.bo.ManagerBO.class.getMethod("getGoalData", new Class [] {int.class, int.class});
            _methodParamMap.put(_getGoalDataMethod, new String [] { "appraisalYear", "appraiseeId" });
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
    public ManagerBOBean(ControlBeanContext context, String id, PropertyMap props)
    {
        this(context, id, props, com.defiance.ideal.qpd.managers.bo.ManagerBO.class);
    }
    
    /**
    * This is the public null-arg constructor for this ControlBean.  If a control id
    * is not provided, a unique value will be auto-generated.
    */
    public ManagerBOBean()
    {
        this(null, null, null);
    }
    
    /**
    * This is the protected version that is used by any ControlBean subclass
    */
    protected ManagerBOBean(ControlBeanContext context, String id, PropertyMap props, Class controlClass)
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
    * Implements com.defiance.ideal.qpd.managers.bo.ManagerBO.getAppraiseesByHr
    */
    public  com.defiance.ideal.qpd.managers.dto.ManagerDTO[] getAppraiseesByHr(javax.servlet.http.HttpServletRequest request, java.lang.String hrId, int appraisalQuarter, int appraisalYear) 
    {
        Object [] __bc_argArray = new Object[] { request, hrId, appraisalQuarter, appraisalYear };
        Throwable __bc_thrown = null;
        com.defiance.ideal.qpd.managers.bo.ManagerBO __bc_target = (com.defiance.ideal.qpd.managers.bo.ManagerBO)ensureControl();
        com.defiance.ideal.qpd.managers.dto.ManagerDTO[] __bc_retval = null;
        
        try
        {
            preInvoke(_getAppraiseesByHrMethod, __bc_argArray);
            
            __bc_retval =
            __bc_target.getAppraiseesByHr(request, hrId, appraisalQuarter, appraisalYear)
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
            postInvoke(_getAppraiseesByHrMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
        return __bc_retval;
    }
    
    /**
    * Implements com.defiance.ideal.qpd.managers.bo.ManagerBO.getAppraiseesByFinance
    */
    public  com.defiance.ideal.qpd.managers.dto.ManagerDTO[] getAppraiseesByFinance(javax.servlet.http.HttpServletRequest request, java.lang.String financeId, int appraisalQuarter, int appraisalYear) 
    {
        Object [] __bc_argArray = new Object[] { request, financeId, appraisalQuarter, appraisalYear };
        Throwable __bc_thrown = null;
        com.defiance.ideal.qpd.managers.bo.ManagerBO __bc_target = (com.defiance.ideal.qpd.managers.bo.ManagerBO)ensureControl();
        com.defiance.ideal.qpd.managers.dto.ManagerDTO[] __bc_retval = null;
        
        try
        {
            preInvoke(_getAppraiseesByFinanceMethod, __bc_argArray);
            
            __bc_retval =
            __bc_target.getAppraiseesByFinance(request, financeId, appraisalQuarter, appraisalYear)
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
            postInvoke(_getAppraiseesByFinanceMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
        return __bc_retval;
    }
    
    /**
    * Implements com.defiance.ideal.qpd.managers.bo.ManagerBO.getAppraiseesByNormalizer
    */
    public  com.defiance.ideal.qpd.managers.dto.ManagerDTO[] getAppraiseesByNormalizer(javax.servlet.http.HttpServletRequest request, java.lang.String normalizerId, int appraisalQuarter, int appraisalYear) 
    {
        Object [] __bc_argArray = new Object[] { request, normalizerId, appraisalQuarter, appraisalYear };
        Throwable __bc_thrown = null;
        com.defiance.ideal.qpd.managers.bo.ManagerBO __bc_target = (com.defiance.ideal.qpd.managers.bo.ManagerBO)ensureControl();
        com.defiance.ideal.qpd.managers.dto.ManagerDTO[] __bc_retval = null;
        
        try
        {
            preInvoke(_getAppraiseesByNormalizerMethod, __bc_argArray);
            
            __bc_retval =
            __bc_target.getAppraiseesByNormalizer(request, normalizerId, appraisalQuarter, appraisalYear)
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
            postInvoke(_getAppraiseesByNormalizerMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
        return __bc_retval;
    }
    
    /**
    * Implements com.defiance.ideal.qpd.managers.bo.ManagerBO.getAppraiseesByReviewer
    */
    public  com.defiance.ideal.qpd.managers.dto.ManagerDTO[] getAppraiseesByReviewer(javax.servlet.http.HttpServletRequest request, java.lang.String reviewerId, int appraisalQuarter, int appraisalYear) 
    {
        Object [] __bc_argArray = new Object[] { request, reviewerId, appraisalQuarter, appraisalYear };
        Throwable __bc_thrown = null;
        com.defiance.ideal.qpd.managers.bo.ManagerBO __bc_target = (com.defiance.ideal.qpd.managers.bo.ManagerBO)ensureControl();
        com.defiance.ideal.qpd.managers.dto.ManagerDTO[] __bc_retval = null;
        
        try
        {
            preInvoke(_getAppraiseesByReviewerMethod, __bc_argArray);
            
            __bc_retval =
            __bc_target.getAppraiseesByReviewer(request, reviewerId, appraisalQuarter, appraisalYear)
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
            postInvoke(_getAppraiseesByReviewerMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
        return __bc_retval;
    }
    
    /**
    * Implements com.defiance.ideal.qpd.managers.bo.ManagerBO.updateNormalizerRating
    */
    public  void updateNormalizerRating(java.lang.String normalizerId, int appraisalQuarter, int appraisalYear, com.defiance.ideal.qpd.managers.dto.ManagerDTO formData, java.lang.String button) 
    {
        Object [] __bc_argArray = new Object[] { normalizerId, appraisalQuarter, appraisalYear, formData, button };
        Throwable __bc_thrown = null;
        com.defiance.ideal.qpd.managers.bo.ManagerBO __bc_target = (com.defiance.ideal.qpd.managers.bo.ManagerBO)ensureControl();
        
        try
        {
            preInvoke(_updateNormalizerRatingMethod, __bc_argArray);
            
            __bc_target.updateNormalizerRating(normalizerId, appraisalQuarter, appraisalYear, formData, button)
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
            postInvoke(_updateNormalizerRatingMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
    }
    
    /**
    * Implements com.defiance.ideal.qpd.managers.bo.ManagerBO.getDevelopmentData
    */
    public  com.defiance.ideal.qpd.appraiser.dto.AppraiserFormDTO[] getDevelopmentData(int appraisalYear, int appraiseeId) 
    {
        Object [] __bc_argArray = new Object[] { appraisalYear, appraiseeId };
        Throwable __bc_thrown = null;
        com.defiance.ideal.qpd.managers.bo.ManagerBO __bc_target = (com.defiance.ideal.qpd.managers.bo.ManagerBO)ensureControl();
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
    * Implements com.defiance.ideal.qpd.managers.bo.ManagerBO.getKraData
    */
    public  com.defiance.ideal.qpd.managers.dto.ManagerDTO[] getKraData(int bandId, int appraisalQuarter, int appraisalYear, int appraiseeId, int departmentId) 
    {
        Object [] __bc_argArray = new Object[] { bandId, appraisalQuarter, appraisalYear, appraiseeId, departmentId };
        Throwable __bc_thrown = null;
        com.defiance.ideal.qpd.managers.bo.ManagerBO __bc_target = (com.defiance.ideal.qpd.managers.bo.ManagerBO)ensureControl();
        com.defiance.ideal.qpd.managers.dto.ManagerDTO[] __bc_retval = null;
        
        try
        {
            preInvoke(_getKraDataMethod, __bc_argArray);
            
            __bc_retval =
            __bc_target.getKraData(bandId, appraisalQuarter, appraisalYear, appraiseeId, departmentId)
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
    * Implements com.defiance.ideal.qpd.managers.bo.ManagerBO.updateReviewerRating
    */
    public  void updateReviewerRating(java.lang.String reviewerId, int appraisalQuarter, int appraisalYear, com.defiance.ideal.qpd.managers.dto.ManagerDTO formData, java.lang.String button) 
    {
        Object [] __bc_argArray = new Object[] { reviewerId, appraisalQuarter, appraisalYear, formData, button };
        Throwable __bc_thrown = null;
        com.defiance.ideal.qpd.managers.bo.ManagerBO __bc_target = (com.defiance.ideal.qpd.managers.bo.ManagerBO)ensureControl();
        
        try
        {
            preInvoke(_updateReviewerRatingMethod, __bc_argArray);
            
            __bc_target.updateReviewerRating(reviewerId, appraisalQuarter, appraisalYear, formData, button)
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
            postInvoke(_updateReviewerRatingMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
    }
    
    /**
    * Implements com.defiance.ideal.qpd.managers.bo.ManagerBO.getSubmitStatus
    */
    public  com.defiance.ideal.qpd.appraiser.dto.AppraiseeListDTO getSubmitStatus(int appraiseeId, int appraisalQuarter, int appraisalYear) 
    {
        Object [] __bc_argArray = new Object[] { appraiseeId, appraisalQuarter, appraisalYear };
        Throwable __bc_thrown = null;
        com.defiance.ideal.qpd.managers.bo.ManagerBO __bc_target = (com.defiance.ideal.qpd.managers.bo.ManagerBO)ensureControl();
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
    * Implements com.defiance.ideal.qpd.managers.bo.ManagerBO.updateFinalStatus
    */
    public  void updateFinalStatus(java.lang.String hrId, int appraisalQuarter, int appraisalYear, com.defiance.ideal.qpd.managers.dto.ManagerDTO appraiseesbyHr, java.lang.String button) 
    {
        Object [] __bc_argArray = new Object[] { hrId, appraisalQuarter, appraisalYear, appraiseesbyHr, button };
        Throwable __bc_thrown = null;
        com.defiance.ideal.qpd.managers.bo.ManagerBO __bc_target = (com.defiance.ideal.qpd.managers.bo.ManagerBO)ensureControl();
        
        try
        {
            preInvoke(_updateFinalStatusMethod, __bc_argArray);
            
            __bc_target.updateFinalStatus(hrId, appraisalQuarter, appraisalYear, appraiseesbyHr, button)
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
            postInvoke(_updateFinalStatusMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
    }
    
    /**
    * Implements com.defiance.ideal.qpd.managers.bo.ManagerBO.getEmployeesCount
    */
    public  com.defiance.ideal.qpd.managers.dto.ManagerDTO getEmployeesCount(java.lang.String reviewerId, int appraisalQuarter, int appraisalYear, java.lang.String referenceType) 
    {
        Object [] __bc_argArray = new Object[] { reviewerId, appraisalQuarter, appraisalYear, referenceType };
        Throwable __bc_thrown = null;
        com.defiance.ideal.qpd.managers.bo.ManagerBO __bc_target = (com.defiance.ideal.qpd.managers.bo.ManagerBO)ensureControl();
        com.defiance.ideal.qpd.managers.dto.ManagerDTO __bc_retval = null;
        
        try
        {
            preInvoke(_getEmployeesCountMethod, __bc_argArray);
            
            __bc_retval =
            __bc_target.getEmployeesCount(reviewerId, appraisalQuarter, appraisalYear, referenceType)
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
            postInvoke(_getEmployeesCountMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
        return __bc_retval;
    }
    
    /**
    * Implements com.defiance.ideal.qpd.managers.bo.ManagerBO.getAchievementsData
    */
    public  com.defiance.ideal.qpd.appraiser.dto.AppraiserFormDTO[] getAchievementsData(int appraisalYear, int appraiseeId) 
    {
        Object [] __bc_argArray = new Object[] { appraisalYear, appraiseeId };
        Throwable __bc_thrown = null;
        com.defiance.ideal.qpd.managers.bo.ManagerBO __bc_target = (com.defiance.ideal.qpd.managers.bo.ManagerBO)ensureControl();
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
    * Implements com.defiance.ideal.qpd.managers.bo.ManagerBO.getGoalData
    */
    public  com.defiance.ideal.qpd.appraiser.dto.AppraiserFormDTO[] getGoalData(int appraisalYear, int appraiseeId) 
    {
        Object [] __bc_argArray = new Object[] { appraisalYear, appraiseeId };
        Throwable __bc_thrown = null;
        com.defiance.ideal.qpd.managers.bo.ManagerBO __bc_target = (com.defiance.ideal.qpd.managers.bo.ManagerBO)ensureControl();
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
