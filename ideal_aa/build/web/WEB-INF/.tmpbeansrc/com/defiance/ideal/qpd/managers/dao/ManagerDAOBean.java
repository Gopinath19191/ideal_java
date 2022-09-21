
package com.defiance.ideal.qpd.managers.dao;

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
public class ManagerDAOBean
extends org.apache.beehive.controls.runtime.bean.ControlBean
implements com.defiance.ideal.qpd.managers.dao.ManagerDAO
{
    static final Method _getAppraiseesByNormalizerAndAppraiserReviewerMethod;
    static final Method _getAppraiseesByNormalizer0Method;
    static final Method _getAppraiseesByNormalizer1Method;
    static final Method _getAppraiseeCountMethod;
    static final Method _getCompanyStructureBrHrMethod;
    static final Method _getAppraiseesByNormalizerAndReviewerMethod;
    static final Method _updateNormalizerRatingMethod;
    static final Method _getDevelopmentDataMethod;
    static final Method _getSubmitStatusMethod;
    static final Method _updateFinalStatusMethod;
    static final Method _getAppraiseesByHr1Method;
    static final Method _getAppraiseesByReviewerMethod;
    static final Method _getGoalDataMethod;
    static final Method _getAppraiseesByHr0Method;
    static final Method _getAppraisersByReviewerMethod;
    static final Method _getAppraiseesByHrAndNormalizerMethod;
    static final Method _getNormalizersByHrMethod;
    static final Method _getAppraiseesByFinanceMethod;
    static final Method _getKraDataMethod;
    static final Method _getReviewersByNormalizerMethod;
    static final Method _getAppraiseesByHrAndSBUMethod;
    static final Method _getAppraiseesByHrAndNormalizerAndSBUMethod;
    static final Method _updateReviewerRatingMethod;
    static final Method _getAchievementsDataMethod;
    static final Method _getAppraiseesByReviewerAndAppraiserMethod;
    static final Method _getAppraisersByNormalizerMethod;
    static final Method _getAppraiseesByNormalizerAndAppraiserMethod;
    
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
            _getAppraiseesByNormalizerAndAppraiserReviewerMethod = com.defiance.ideal.qpd.managers.dao.ManagerDAO.class.getMethod("getAppraiseesByNormalizerAndAppraiserReviewer", new Class [] {java.lang.String.class, java.lang.String.class, java.lang.String.class, int.class, int.class});
            _methodParamMap.put(_getAppraiseesByNormalizerAndAppraiserReviewerMethod, new String [] { "selectedAppraiserId", "selectedReviewerId", "normalizerId", "appraisalQuarter", "appraisalYear" });
            _getAppraiseesByNormalizer0Method = com.defiance.ideal.qpd.managers.dao.ManagerDAO.class.getMethod("getAppraiseesByNormalizer", new Class [] {java.lang.String.class, int.class, int.class});
            _methodParamMap.put(_getAppraiseesByNormalizer0Method, new String [] { "normalizerId", "appraisalQuarter", "appraisalYear" });
            _getAppraiseesByNormalizer1Method = com.defiance.ideal.qpd.managers.dao.ManagerDAO.class.getMethod("getAppraiseesByNormalizer", new Class [] {java.lang.String.class, int.class, int.class, java.lang.String.class});
            _methodParamMap.put(_getAppraiseesByNormalizer1Method, new String [] { "normalizerId", "appraisalQuarter", "appraisalYear", "whereCondition" });
            _getAppraiseeCountMethod = com.defiance.ideal.qpd.managers.dao.ManagerDAO.class.getMethod("getAppraiseeCount", new Class [] {java.lang.String.class, int.class, int.class, java.lang.String.class});
            _methodParamMap.put(_getAppraiseeCountMethod, new String [] { "reviewerId", "appraisalQuarter", "appraisalYear", "referenceType" });
            _getCompanyStructureBrHrMethod = com.defiance.ideal.qpd.managers.dao.ManagerDAO.class.getMethod("getCompanyStructureBrHr", new Class [] {java.lang.String.class, int.class, int.class});
            _methodParamMap.put(_getCompanyStructureBrHrMethod, new String [] { "hrId", "appraisalQuarter", "appraisalYear" });
            _getAppraiseesByNormalizerAndReviewerMethod = com.defiance.ideal.qpd.managers.dao.ManagerDAO.class.getMethod("getAppraiseesByNormalizerAndReviewer", new Class [] {java.lang.String.class, java.lang.String.class, int.class, int.class});
            _methodParamMap.put(_getAppraiseesByNormalizerAndReviewerMethod, new String [] { "selectedReviewerId", "normalizerId", "appraisalQuarter", "appraisalYear" });
            _updateNormalizerRatingMethod = com.defiance.ideal.qpd.managers.dao.ManagerDAO.class.getMethod("updateNormalizerRating", new Class [] {java.lang.String.class, int.class, int.class, com.defiance.ideal.qpd.managers.dto.ManagerDTO.class, java.lang.String.class});
            _methodParamMap.put(_updateNormalizerRatingMethod, new String [] { "normalizerId", "appraisalQuarter", "appraisalYear", "formData", "button" });
            _getDevelopmentDataMethod = com.defiance.ideal.qpd.managers.dao.ManagerDAO.class.getMethod("getDevelopmentData", new Class [] {int.class, int.class});
            _methodParamMap.put(_getDevelopmentDataMethod, new String [] { "appraisalYear", "appraiseeId" });
            _getSubmitStatusMethod = com.defiance.ideal.qpd.managers.dao.ManagerDAO.class.getMethod("getSubmitStatus", new Class [] {int.class, int.class, int.class});
            _methodParamMap.put(_getSubmitStatusMethod, new String [] { "appraiseeId", "appraisalQuarter", "appraisalYear" });
            _updateFinalStatusMethod = com.defiance.ideal.qpd.managers.dao.ManagerDAO.class.getMethod("updateFinalStatus", new Class [] {java.lang.String.class, int.class, int.class, com.defiance.ideal.qpd.managers.dto.ManagerDTO.class, java.lang.String.class});
            _methodParamMap.put(_updateFinalStatusMethod, new String [] { "hrId", "appraisalQuarter", "appraisalYear", "appraiseesbyHr", "button" });
            _getAppraiseesByHr1Method = com.defiance.ideal.qpd.managers.dao.ManagerDAO.class.getMethod("getAppraiseesByHr", new Class [] {java.lang.String.class, int.class, int.class, java.lang.String.class});
            _methodParamMap.put(_getAppraiseesByHr1Method, new String [] { "hrId", "appraisalQuarter", "appraisalYear", "whereCondition" });
            _getAppraiseesByReviewerMethod = com.defiance.ideal.qpd.managers.dao.ManagerDAO.class.getMethod("getAppraiseesByReviewer", new Class [] {java.lang.String.class, int.class, int.class});
            _methodParamMap.put(_getAppraiseesByReviewerMethod, new String [] { "reviewerId", "appraisalQuarter", "appraisalYear" });
            _getGoalDataMethod = com.defiance.ideal.qpd.managers.dao.ManagerDAO.class.getMethod("getGoalData", new Class [] {int.class, int.class});
            _methodParamMap.put(_getGoalDataMethod, new String [] { "appraisalYear", "appraiseeId" });
            _getAppraiseesByHr0Method = com.defiance.ideal.qpd.managers.dao.ManagerDAO.class.getMethod("getAppraiseesByHr", new Class [] {java.lang.String.class, int.class, int.class});
            _methodParamMap.put(_getAppraiseesByHr0Method, new String [] { "hrId", "appraisalQuarter", "appraisalYear" });
            _getAppraisersByReviewerMethod = com.defiance.ideal.qpd.managers.dao.ManagerDAO.class.getMethod("getAppraisersByReviewer", new Class [] {java.lang.String.class, int.class, int.class});
            _methodParamMap.put(_getAppraisersByReviewerMethod, new String [] { "reviewerId", "appraisalQuarter", "appraisalYear" });
            _getAppraiseesByHrAndNormalizerMethod = com.defiance.ideal.qpd.managers.dao.ManagerDAO.class.getMethod("getAppraiseesByHrAndNormalizer", new Class [] {java.lang.String.class, int.class, int.class, java.lang.String.class});
            _methodParamMap.put(_getAppraiseesByHrAndNormalizerMethod, new String [] { "hrId", "appraisalQuarter", "appraisalYear", "selectedNormalizerId" });
            _getNormalizersByHrMethod = com.defiance.ideal.qpd.managers.dao.ManagerDAO.class.getMethod("getNormalizersByHr", new Class [] {java.lang.String.class, int.class, int.class});
            _methodParamMap.put(_getNormalizersByHrMethod, new String [] { "hrId", "appraisalQuarter", "appraisalYear" });
            _getAppraiseesByFinanceMethod = com.defiance.ideal.qpd.managers.dao.ManagerDAO.class.getMethod("getAppraiseesByFinance", new Class [] {java.lang.String.class, int.class, int.class});
            _methodParamMap.put(_getAppraiseesByFinanceMethod, new String [] { "financeId", "appraisalQuarter", "appraisalYear" });
            _getKraDataMethod = com.defiance.ideal.qpd.managers.dao.ManagerDAO.class.getMethod("getKraData", new Class [] {int.class, int.class, int.class, int.class, int.class});
            _methodParamMap.put(_getKraDataMethod, new String [] { "bandId", "appraisalYear", "appraisalQuarter", "appraiseeId", "departmentId" });
            _getReviewersByNormalizerMethod = com.defiance.ideal.qpd.managers.dao.ManagerDAO.class.getMethod("getReviewersByNormalizer", new Class [] {java.lang.String.class, int.class, int.class});
            _methodParamMap.put(_getReviewersByNormalizerMethod, new String [] { "normalizerId", "appraisalQuarter", "appraisalYear" });
            _getAppraiseesByHrAndSBUMethod = com.defiance.ideal.qpd.managers.dao.ManagerDAO.class.getMethod("getAppraiseesByHrAndSBU", new Class [] {java.lang.String.class, int.class, int.class, java.lang.String.class});
            _methodParamMap.put(_getAppraiseesByHrAndSBUMethod, new String [] { "hrId", "appraisalQuarter", "appraisalYear", "selectedSBUDepartmentId" });
            _getAppraiseesByHrAndNormalizerAndSBUMethod = com.defiance.ideal.qpd.managers.dao.ManagerDAO.class.getMethod("getAppraiseesByHrAndNormalizerAndSBU", new Class [] {java.lang.String.class, int.class, int.class, java.lang.String.class, java.lang.String.class});
            _methodParamMap.put(_getAppraiseesByHrAndNormalizerAndSBUMethod, new String [] { "hrId", "appraisalQuarter", "appraisalYear", "selectedNormalizerId", "selectedSBUDepartmentId" });
            _updateReviewerRatingMethod = com.defiance.ideal.qpd.managers.dao.ManagerDAO.class.getMethod("updateReviewerRating", new Class [] {java.lang.String.class, int.class, int.class, com.defiance.ideal.qpd.managers.dto.ManagerDTO.class, java.lang.String.class});
            _methodParamMap.put(_updateReviewerRatingMethod, new String [] { "reviewerId", "appraisalQuarter", "appraisalYear", "formData", "button" });
            _getAchievementsDataMethod = com.defiance.ideal.qpd.managers.dao.ManagerDAO.class.getMethod("getAchievementsData", new Class [] {int.class, int.class});
            _methodParamMap.put(_getAchievementsDataMethod, new String [] { "appraisalYear", "appraiseeId" });
            _getAppraiseesByReviewerAndAppraiserMethod = com.defiance.ideal.qpd.managers.dao.ManagerDAO.class.getMethod("getAppraiseesByReviewerAndAppraiser", new Class [] {java.lang.String.class, java.lang.String.class, int.class, int.class});
            _methodParamMap.put(_getAppraiseesByReviewerAndAppraiserMethod, new String [] { "selectedAppraiserId", "reviewerId", "appraisalQuarter", "appraisalYear" });
            _getAppraisersByNormalizerMethod = com.defiance.ideal.qpd.managers.dao.ManagerDAO.class.getMethod("getAppraisersByNormalizer", new Class [] {java.lang.String.class, int.class, int.class});
            _methodParamMap.put(_getAppraisersByNormalizerMethod, new String [] { "normalizerId", "appraisalQuarter", "appraisalYear" });
            _getAppraiseesByNormalizerAndAppraiserMethod = com.defiance.ideal.qpd.managers.dao.ManagerDAO.class.getMethod("getAppraiseesByNormalizerAndAppraiser", new Class [] {java.lang.String.class, java.lang.String.class, int.class, int.class});
            _methodParamMap.put(_getAppraiseesByNormalizerAndAppraiserMethod, new String [] { "selectedAppraiserId", "normalizerId", "appraisalQuarter", "appraisalYear" });
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
    public ManagerDAOBean(ControlBeanContext context, String id, PropertyMap props)
    {
        this(context, id, props, com.defiance.ideal.qpd.managers.dao.ManagerDAO.class);
    }
    
    /**
    * This is the public null-arg constructor for this ControlBean.  If a control id
    * is not provided, a unique value will be auto-generated.
    */
    public ManagerDAOBean()
    {
        this(null, null, null);
    }
    
    /**
    * This is the protected version that is used by any ControlBean subclass
    */
    protected ManagerDAOBean(ControlBeanContext context, String id, PropertyMap props, Class controlClass)
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
    * Implements com.defiance.ideal.qpd.managers.dao.ManagerDAO.getAppraiseesByNormalizerAndAppraiserReviewer
    */
    public  com.defiance.ideal.qpd.managers.dto.ManagerDTO[] getAppraiseesByNormalizerAndAppraiserReviewer(java.lang.String selectedAppraiserId, java.lang.String selectedReviewerId, java.lang.String normalizerId, int appraisalQuarter, int appraisalYear) 
    {
        Object [] __bc_argArray = new Object[] { selectedAppraiserId, selectedReviewerId, normalizerId, appraisalQuarter, appraisalYear };
        Throwable __bc_thrown = null;
        com.defiance.ideal.qpd.managers.dao.ManagerDAO __bc_target = (com.defiance.ideal.qpd.managers.dao.ManagerDAO)ensureControl();
        com.defiance.ideal.qpd.managers.dto.ManagerDTO[] __bc_retval = null;
        
        try
        {
            preInvoke(_getAppraiseesByNormalizerAndAppraiserReviewerMethod, __bc_argArray);
            
            __bc_retval =
            __bc_target.getAppraiseesByNormalizerAndAppraiserReviewer(selectedAppraiserId, selectedReviewerId, normalizerId, appraisalQuarter, appraisalYear)
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
            postInvoke(_getAppraiseesByNormalizerAndAppraiserReviewerMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
        return __bc_retval;
    }
    
    /**
    * Implements com.defiance.ideal.qpd.managers.dao.ManagerDAO.getAppraiseesByNormalizer
    */
    public  com.defiance.ideal.qpd.managers.dto.ManagerDTO[] getAppraiseesByNormalizer(java.lang.String normalizerId, int appraisalQuarter, int appraisalYear) 
    {
        Object [] __bc_argArray = new Object[] { normalizerId, appraisalQuarter, appraisalYear };
        Throwable __bc_thrown = null;
        com.defiance.ideal.qpd.managers.dao.ManagerDAO __bc_target = (com.defiance.ideal.qpd.managers.dao.ManagerDAO)ensureControl();
        com.defiance.ideal.qpd.managers.dto.ManagerDTO[] __bc_retval = null;
        
        try
        {
            preInvoke(_getAppraiseesByNormalizer0Method, __bc_argArray);
            
            __bc_retval =
            __bc_target.getAppraiseesByNormalizer(normalizerId, appraisalQuarter, appraisalYear)
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
            postInvoke(_getAppraiseesByNormalizer0Method, __bc_argArray, __bc_rv, __bc_thrown);
        }
        return __bc_retval;
    }
    
    /**
    * Implements com.defiance.ideal.qpd.managers.dao.ManagerDAO.getAppraiseesByNormalizer
    */
    public  com.defiance.ideal.qpd.managers.dto.ManagerDTO[] getAppraiseesByNormalizer(java.lang.String normalizerId, int appraisalQuarter, int appraisalYear, java.lang.String whereCondition) 
    {
        Object [] __bc_argArray = new Object[] { normalizerId, appraisalQuarter, appraisalYear, whereCondition };
        Throwable __bc_thrown = null;
        com.defiance.ideal.qpd.managers.dao.ManagerDAO __bc_target = (com.defiance.ideal.qpd.managers.dao.ManagerDAO)ensureControl();
        com.defiance.ideal.qpd.managers.dto.ManagerDTO[] __bc_retval = null;
        
        try
        {
            preInvoke(_getAppraiseesByNormalizer1Method, __bc_argArray);
            
            __bc_retval =
            __bc_target.getAppraiseesByNormalizer(normalizerId, appraisalQuarter, appraisalYear, whereCondition)
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
            postInvoke(_getAppraiseesByNormalizer1Method, __bc_argArray, __bc_rv, __bc_thrown);
        }
        return __bc_retval;
    }
    
    /**
    * Implements com.defiance.ideal.qpd.managers.dao.ManagerDAO.getAppraiseeCount
    */
    public  com.defiance.ideal.qpd.managers.dto.ManagerDTO getAppraiseeCount(java.lang.String reviewerId, int appraisalQuarter, int appraisalYear, java.lang.String referenceType) 
    {
        Object [] __bc_argArray = new Object[] { reviewerId, appraisalQuarter, appraisalYear, referenceType };
        Throwable __bc_thrown = null;
        com.defiance.ideal.qpd.managers.dao.ManagerDAO __bc_target = (com.defiance.ideal.qpd.managers.dao.ManagerDAO)ensureControl();
        com.defiance.ideal.qpd.managers.dto.ManagerDTO __bc_retval = null;
        
        try
        {
            preInvoke(_getAppraiseeCountMethod, __bc_argArray);
            
            __bc_retval =
            __bc_target.getAppraiseeCount(reviewerId, appraisalQuarter, appraisalYear, referenceType)
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
            postInvoke(_getAppraiseeCountMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
        return __bc_retval;
    }
    
    /**
    * Implements com.defiance.ideal.qpd.managers.dao.ManagerDAO.getCompanyStructureBrHr
    */
    public  java.lang.Object getCompanyStructureBrHr(java.lang.String hrId, int appraisalQuarter, int appraisalYear) 
    {
        Object [] __bc_argArray = new Object[] { hrId, appraisalQuarter, appraisalYear };
        Throwable __bc_thrown = null;
        com.defiance.ideal.qpd.managers.dao.ManagerDAO __bc_target = (com.defiance.ideal.qpd.managers.dao.ManagerDAO)ensureControl();
        java.lang.Object __bc_retval = null;
        
        try
        {
            preInvoke(_getCompanyStructureBrHrMethod, __bc_argArray);
            
            __bc_retval =
            __bc_target.getCompanyStructureBrHr(hrId, appraisalQuarter, appraisalYear)
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
            postInvoke(_getCompanyStructureBrHrMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
        return __bc_retval;
    }
    
    /**
    * Implements com.defiance.ideal.qpd.managers.dao.ManagerDAO.getAppraiseesByNormalizerAndReviewer
    */
    public  com.defiance.ideal.qpd.managers.dto.ManagerDTO[] getAppraiseesByNormalizerAndReviewer(java.lang.String selectedReviewerId, java.lang.String normalizerId, int appraisalQuarter, int appraisalYear) 
    {
        Object [] __bc_argArray = new Object[] { selectedReviewerId, normalizerId, appraisalQuarter, appraisalYear };
        Throwable __bc_thrown = null;
        com.defiance.ideal.qpd.managers.dao.ManagerDAO __bc_target = (com.defiance.ideal.qpd.managers.dao.ManagerDAO)ensureControl();
        com.defiance.ideal.qpd.managers.dto.ManagerDTO[] __bc_retval = null;
        
        try
        {
            preInvoke(_getAppraiseesByNormalizerAndReviewerMethod, __bc_argArray);
            
            __bc_retval =
            __bc_target.getAppraiseesByNormalizerAndReviewer(selectedReviewerId, normalizerId, appraisalQuarter, appraisalYear)
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
            postInvoke(_getAppraiseesByNormalizerAndReviewerMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
        return __bc_retval;
    }
    
    /**
    * Implements com.defiance.ideal.qpd.managers.dao.ManagerDAO.updateNormalizerRating
    */
    public  void updateNormalizerRating(java.lang.String normalizerId, int appraisalQuarter, int appraisalYear, com.defiance.ideal.qpd.managers.dto.ManagerDTO formData, java.lang.String button) 
    {
        Object [] __bc_argArray = new Object[] { normalizerId, appraisalQuarter, appraisalYear, formData, button };
        Throwable __bc_thrown = null;
        com.defiance.ideal.qpd.managers.dao.ManagerDAO __bc_target = (com.defiance.ideal.qpd.managers.dao.ManagerDAO)ensureControl();
        
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
    * Implements com.defiance.ideal.qpd.managers.dao.ManagerDAO.getDevelopmentData
    */
    public  com.defiance.ideal.qpd.appraiser.dto.AppraiserFormDTO[] getDevelopmentData(int appraisalYear, int appraiseeId) 
    {
        Object [] __bc_argArray = new Object[] { appraisalYear, appraiseeId };
        Throwable __bc_thrown = null;
        com.defiance.ideal.qpd.managers.dao.ManagerDAO __bc_target = (com.defiance.ideal.qpd.managers.dao.ManagerDAO)ensureControl();
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
    * Implements com.defiance.ideal.qpd.managers.dao.ManagerDAO.getSubmitStatus
    */
    public  com.defiance.ideal.qpd.appraiser.dto.AppraiseeListDTO getSubmitStatus(int appraiseeId, int appraisalQuarter, int appraisalYear) 
    {
        Object [] __bc_argArray = new Object[] { appraiseeId, appraisalQuarter, appraisalYear };
        Throwable __bc_thrown = null;
        com.defiance.ideal.qpd.managers.dao.ManagerDAO __bc_target = (com.defiance.ideal.qpd.managers.dao.ManagerDAO)ensureControl();
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
    * Implements com.defiance.ideal.qpd.managers.dao.ManagerDAO.updateFinalStatus
    */
    public  void updateFinalStatus(java.lang.String hrId, int appraisalQuarter, int appraisalYear, com.defiance.ideal.qpd.managers.dto.ManagerDTO appraiseesbyHr, java.lang.String button) 
    {
        Object [] __bc_argArray = new Object[] { hrId, appraisalQuarter, appraisalYear, appraiseesbyHr, button };
        Throwable __bc_thrown = null;
        com.defiance.ideal.qpd.managers.dao.ManagerDAO __bc_target = (com.defiance.ideal.qpd.managers.dao.ManagerDAO)ensureControl();
        
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
    * Implements com.defiance.ideal.qpd.managers.dao.ManagerDAO.getAppraiseesByHr
    */
    public  com.defiance.ideal.qpd.managers.dto.ManagerDTO[] getAppraiseesByHr(java.lang.String hrId, int appraisalQuarter, int appraisalYear, java.lang.String whereCondition) 
    {
        Object [] __bc_argArray = new Object[] { hrId, appraisalQuarter, appraisalYear, whereCondition };
        Throwable __bc_thrown = null;
        com.defiance.ideal.qpd.managers.dao.ManagerDAO __bc_target = (com.defiance.ideal.qpd.managers.dao.ManagerDAO)ensureControl();
        com.defiance.ideal.qpd.managers.dto.ManagerDTO[] __bc_retval = null;
        
        try
        {
            preInvoke(_getAppraiseesByHr1Method, __bc_argArray);
            
            __bc_retval =
            __bc_target.getAppraiseesByHr(hrId, appraisalQuarter, appraisalYear, whereCondition)
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
            postInvoke(_getAppraiseesByHr1Method, __bc_argArray, __bc_rv, __bc_thrown);
        }
        return __bc_retval;
    }
    
    /**
    * Implements com.defiance.ideal.qpd.managers.dao.ManagerDAO.getAppraiseesByReviewer
    */
    public  com.defiance.ideal.qpd.managers.dto.ManagerDTO[] getAppraiseesByReviewer(java.lang.String reviewerId, int appraisalQuarter, int appraisalYear) 
    {
        Object [] __bc_argArray = new Object[] { reviewerId, appraisalQuarter, appraisalYear };
        Throwable __bc_thrown = null;
        com.defiance.ideal.qpd.managers.dao.ManagerDAO __bc_target = (com.defiance.ideal.qpd.managers.dao.ManagerDAO)ensureControl();
        com.defiance.ideal.qpd.managers.dto.ManagerDTO[] __bc_retval = null;
        
        try
        {
            preInvoke(_getAppraiseesByReviewerMethod, __bc_argArray);
            
            __bc_retval =
            __bc_target.getAppraiseesByReviewer(reviewerId, appraisalQuarter, appraisalYear)
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
    * Implements com.defiance.ideal.qpd.managers.dao.ManagerDAO.getGoalData
    */
    public  com.defiance.ideal.qpd.appraiser.dto.AppraiserFormDTO[] getGoalData(int appraisalYear, int appraiseeId) 
    {
        Object [] __bc_argArray = new Object[] { appraisalYear, appraiseeId };
        Throwable __bc_thrown = null;
        com.defiance.ideal.qpd.managers.dao.ManagerDAO __bc_target = (com.defiance.ideal.qpd.managers.dao.ManagerDAO)ensureControl();
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
    * Implements com.defiance.ideal.qpd.managers.dao.ManagerDAO.getAppraiseesByHr
    */
    public  com.defiance.ideal.qpd.managers.dto.ManagerDTO[] getAppraiseesByHr(java.lang.String hrId, int appraisalQuarter, int appraisalYear) 
    {
        Object [] __bc_argArray = new Object[] { hrId, appraisalQuarter, appraisalYear };
        Throwable __bc_thrown = null;
        com.defiance.ideal.qpd.managers.dao.ManagerDAO __bc_target = (com.defiance.ideal.qpd.managers.dao.ManagerDAO)ensureControl();
        com.defiance.ideal.qpd.managers.dto.ManagerDTO[] __bc_retval = null;
        
        try
        {
            preInvoke(_getAppraiseesByHr0Method, __bc_argArray);
            
            __bc_retval =
            __bc_target.getAppraiseesByHr(hrId, appraisalQuarter, appraisalYear)
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
            postInvoke(_getAppraiseesByHr0Method, __bc_argArray, __bc_rv, __bc_thrown);
        }
        return __bc_retval;
    }
    
    /**
    * Implements com.defiance.ideal.qpd.managers.dao.ManagerDAO.getAppraisersByReviewer
    */
    public  com.defiance.ideal.qpd.managers.dto.ManagerDTO[] getAppraisersByReviewer(java.lang.String reviewerId, int appraisalQuarter, int appraisalYear) 
    {
        Object [] __bc_argArray = new Object[] { reviewerId, appraisalQuarter, appraisalYear };
        Throwable __bc_thrown = null;
        com.defiance.ideal.qpd.managers.dao.ManagerDAO __bc_target = (com.defiance.ideal.qpd.managers.dao.ManagerDAO)ensureControl();
        com.defiance.ideal.qpd.managers.dto.ManagerDTO[] __bc_retval = null;
        
        try
        {
            preInvoke(_getAppraisersByReviewerMethod, __bc_argArray);
            
            __bc_retval =
            __bc_target.getAppraisersByReviewer(reviewerId, appraisalQuarter, appraisalYear)
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
            postInvoke(_getAppraisersByReviewerMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
        return __bc_retval;
    }
    
    /**
    * Implements com.defiance.ideal.qpd.managers.dao.ManagerDAO.getAppraiseesByHrAndNormalizer
    */
    public  com.defiance.ideal.qpd.managers.dto.ManagerDTO[] getAppraiseesByHrAndNormalizer(java.lang.String hrId, int appraisalQuarter, int appraisalYear, java.lang.String selectedNormalizerId) 
    {
        Object [] __bc_argArray = new Object[] { hrId, appraisalQuarter, appraisalYear, selectedNormalizerId };
        Throwable __bc_thrown = null;
        com.defiance.ideal.qpd.managers.dao.ManagerDAO __bc_target = (com.defiance.ideal.qpd.managers.dao.ManagerDAO)ensureControl();
        com.defiance.ideal.qpd.managers.dto.ManagerDTO[] __bc_retval = null;
        
        try
        {
            preInvoke(_getAppraiseesByHrAndNormalizerMethod, __bc_argArray);
            
            __bc_retval =
            __bc_target.getAppraiseesByHrAndNormalizer(hrId, appraisalQuarter, appraisalYear, selectedNormalizerId)
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
            postInvoke(_getAppraiseesByHrAndNormalizerMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
        return __bc_retval;
    }
    
    /**
    * Implements com.defiance.ideal.qpd.managers.dao.ManagerDAO.getNormalizersByHr
    */
    public  java.lang.Object getNormalizersByHr(java.lang.String hrId, int appraisalQuarter, int appraisalYear) 
    {
        Object [] __bc_argArray = new Object[] { hrId, appraisalQuarter, appraisalYear };
        Throwable __bc_thrown = null;
        com.defiance.ideal.qpd.managers.dao.ManagerDAO __bc_target = (com.defiance.ideal.qpd.managers.dao.ManagerDAO)ensureControl();
        java.lang.Object __bc_retval = null;
        
        try
        {
            preInvoke(_getNormalizersByHrMethod, __bc_argArray);
            
            __bc_retval =
            __bc_target.getNormalizersByHr(hrId, appraisalQuarter, appraisalYear)
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
            postInvoke(_getNormalizersByHrMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
        return __bc_retval;
    }
    
    /**
    * Implements com.defiance.ideal.qpd.managers.dao.ManagerDAO.getAppraiseesByFinance
    */
    public  com.defiance.ideal.qpd.managers.dto.ManagerDTO[] getAppraiseesByFinance(java.lang.String financeId, int appraisalQuarter, int appraisalYear) 
    {
        Object [] __bc_argArray = new Object[] { financeId, appraisalQuarter, appraisalYear };
        Throwable __bc_thrown = null;
        com.defiance.ideal.qpd.managers.dao.ManagerDAO __bc_target = (com.defiance.ideal.qpd.managers.dao.ManagerDAO)ensureControl();
        com.defiance.ideal.qpd.managers.dto.ManagerDTO[] __bc_retval = null;
        
        try
        {
            preInvoke(_getAppraiseesByFinanceMethod, __bc_argArray);
            
            __bc_retval =
            __bc_target.getAppraiseesByFinance(financeId, appraisalQuarter, appraisalYear)
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
    * Implements com.defiance.ideal.qpd.managers.dao.ManagerDAO.getKraData
    */
    public  com.defiance.ideal.qpd.managers.dto.ManagerDTO[] getKraData(int bandId, int appraisalYear, int appraisalQuarter, int appraiseeId, int departmentId) 
    {
        Object [] __bc_argArray = new Object[] { bandId, appraisalYear, appraisalQuarter, appraiseeId, departmentId };
        Throwable __bc_thrown = null;
        com.defiance.ideal.qpd.managers.dao.ManagerDAO __bc_target = (com.defiance.ideal.qpd.managers.dao.ManagerDAO)ensureControl();
        com.defiance.ideal.qpd.managers.dto.ManagerDTO[] __bc_retval = null;
        
        try
        {
            preInvoke(_getKraDataMethod, __bc_argArray);
            
            __bc_retval =
            __bc_target.getKraData(bandId, appraisalYear, appraisalQuarter, appraiseeId, departmentId)
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
    * Implements com.defiance.ideal.qpd.managers.dao.ManagerDAO.getReviewersByNormalizer
    */
    public  java.lang.Object getReviewersByNormalizer(java.lang.String normalizerId, int appraisalQuarter, int appraisalYear) 
    {
        Object [] __bc_argArray = new Object[] { normalizerId, appraisalQuarter, appraisalYear };
        Throwable __bc_thrown = null;
        com.defiance.ideal.qpd.managers.dao.ManagerDAO __bc_target = (com.defiance.ideal.qpd.managers.dao.ManagerDAO)ensureControl();
        java.lang.Object __bc_retval = null;
        
        try
        {
            preInvoke(_getReviewersByNormalizerMethod, __bc_argArray);
            
            __bc_retval =
            __bc_target.getReviewersByNormalizer(normalizerId, appraisalQuarter, appraisalYear)
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
            postInvoke(_getReviewersByNormalizerMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
        return __bc_retval;
    }
    
    /**
    * Implements com.defiance.ideal.qpd.managers.dao.ManagerDAO.getAppraiseesByHrAndSBU
    */
    public  com.defiance.ideal.qpd.managers.dto.ManagerDTO[] getAppraiseesByHrAndSBU(java.lang.String hrId, int appraisalQuarter, int appraisalYear, java.lang.String selectedSBUDepartmentId) 
    {
        Object [] __bc_argArray = new Object[] { hrId, appraisalQuarter, appraisalYear, selectedSBUDepartmentId };
        Throwable __bc_thrown = null;
        com.defiance.ideal.qpd.managers.dao.ManagerDAO __bc_target = (com.defiance.ideal.qpd.managers.dao.ManagerDAO)ensureControl();
        com.defiance.ideal.qpd.managers.dto.ManagerDTO[] __bc_retval = null;
        
        try
        {
            preInvoke(_getAppraiseesByHrAndSBUMethod, __bc_argArray);
            
            __bc_retval =
            __bc_target.getAppraiseesByHrAndSBU(hrId, appraisalQuarter, appraisalYear, selectedSBUDepartmentId)
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
            postInvoke(_getAppraiseesByHrAndSBUMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
        return __bc_retval;
    }
    
    /**
    * Implements com.defiance.ideal.qpd.managers.dao.ManagerDAO.getAppraiseesByHrAndNormalizerAndSBU
    */
    public  com.defiance.ideal.qpd.managers.dto.ManagerDTO[] getAppraiseesByHrAndNormalizerAndSBU(java.lang.String hrId, int appraisalQuarter, int appraisalYear, java.lang.String selectedNormalizerId, java.lang.String selectedSBUDepartmentId) 
    {
        Object [] __bc_argArray = new Object[] { hrId, appraisalQuarter, appraisalYear, selectedNormalizerId, selectedSBUDepartmentId };
        Throwable __bc_thrown = null;
        com.defiance.ideal.qpd.managers.dao.ManagerDAO __bc_target = (com.defiance.ideal.qpd.managers.dao.ManagerDAO)ensureControl();
        com.defiance.ideal.qpd.managers.dto.ManagerDTO[] __bc_retval = null;
        
        try
        {
            preInvoke(_getAppraiseesByHrAndNormalizerAndSBUMethod, __bc_argArray);
            
            __bc_retval =
            __bc_target.getAppraiseesByHrAndNormalizerAndSBU(hrId, appraisalQuarter, appraisalYear, selectedNormalizerId, selectedSBUDepartmentId)
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
            postInvoke(_getAppraiseesByHrAndNormalizerAndSBUMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
        return __bc_retval;
    }
    
    /**
    * Implements com.defiance.ideal.qpd.managers.dao.ManagerDAO.updateReviewerRating
    */
    public  void updateReviewerRating(java.lang.String reviewerId, int appraisalQuarter, int appraisalYear, com.defiance.ideal.qpd.managers.dto.ManagerDTO formData, java.lang.String button) 
    {
        Object [] __bc_argArray = new Object[] { reviewerId, appraisalQuarter, appraisalYear, formData, button };
        Throwable __bc_thrown = null;
        com.defiance.ideal.qpd.managers.dao.ManagerDAO __bc_target = (com.defiance.ideal.qpd.managers.dao.ManagerDAO)ensureControl();
        
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
    * Implements com.defiance.ideal.qpd.managers.dao.ManagerDAO.getAchievementsData
    */
    public  com.defiance.ideal.qpd.appraiser.dto.AppraiserFormDTO[] getAchievementsData(int appraisalYear, int appraiseeId) 
    {
        Object [] __bc_argArray = new Object[] { appraisalYear, appraiseeId };
        Throwable __bc_thrown = null;
        com.defiance.ideal.qpd.managers.dao.ManagerDAO __bc_target = (com.defiance.ideal.qpd.managers.dao.ManagerDAO)ensureControl();
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
    * Implements com.defiance.ideal.qpd.managers.dao.ManagerDAO.getAppraiseesByReviewerAndAppraiser
    */
    public  com.defiance.ideal.qpd.managers.dto.ManagerDTO[] getAppraiseesByReviewerAndAppraiser(java.lang.String selectedAppraiserId, java.lang.String reviewerId, int appraisalQuarter, int appraisalYear) 
    {
        Object [] __bc_argArray = new Object[] { selectedAppraiserId, reviewerId, appraisalQuarter, appraisalYear };
        Throwable __bc_thrown = null;
        com.defiance.ideal.qpd.managers.dao.ManagerDAO __bc_target = (com.defiance.ideal.qpd.managers.dao.ManagerDAO)ensureControl();
        com.defiance.ideal.qpd.managers.dto.ManagerDTO[] __bc_retval = null;
        
        try
        {
            preInvoke(_getAppraiseesByReviewerAndAppraiserMethod, __bc_argArray);
            
            __bc_retval =
            __bc_target.getAppraiseesByReviewerAndAppraiser(selectedAppraiserId, reviewerId, appraisalQuarter, appraisalYear)
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
            postInvoke(_getAppraiseesByReviewerAndAppraiserMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
        return __bc_retval;
    }
    
    /**
    * Implements com.defiance.ideal.qpd.managers.dao.ManagerDAO.getAppraisersByNormalizer
    */
    public  java.lang.Object getAppraisersByNormalizer(java.lang.String normalizerId, int appraisalQuarter, int appraisalYear) 
    {
        Object [] __bc_argArray = new Object[] { normalizerId, appraisalQuarter, appraisalYear };
        Throwable __bc_thrown = null;
        com.defiance.ideal.qpd.managers.dao.ManagerDAO __bc_target = (com.defiance.ideal.qpd.managers.dao.ManagerDAO)ensureControl();
        java.lang.Object __bc_retval = null;
        
        try
        {
            preInvoke(_getAppraisersByNormalizerMethod, __bc_argArray);
            
            __bc_retval =
            __bc_target.getAppraisersByNormalizer(normalizerId, appraisalQuarter, appraisalYear)
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
            postInvoke(_getAppraisersByNormalizerMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
        return __bc_retval;
    }
    
    /**
    * Implements com.defiance.ideal.qpd.managers.dao.ManagerDAO.getAppraiseesByNormalizerAndAppraiser
    */
    public  com.defiance.ideal.qpd.managers.dto.ManagerDTO[] getAppraiseesByNormalizerAndAppraiser(java.lang.String selectedAppraiserId, java.lang.String normalizerId, int appraisalQuarter, int appraisalYear) 
    {
        Object [] __bc_argArray = new Object[] { selectedAppraiserId, normalizerId, appraisalQuarter, appraisalYear };
        Throwable __bc_thrown = null;
        com.defiance.ideal.qpd.managers.dao.ManagerDAO __bc_target = (com.defiance.ideal.qpd.managers.dao.ManagerDAO)ensureControl();
        com.defiance.ideal.qpd.managers.dto.ManagerDTO[] __bc_retval = null;
        
        try
        {
            preInvoke(_getAppraiseesByNormalizerAndAppraiserMethod, __bc_argArray);
            
            __bc_retval =
            __bc_target.getAppraiseesByNormalizerAndAppraiser(selectedAppraiserId, normalizerId, appraisalQuarter, appraisalYear)
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
            postInvoke(_getAppraiseesByNormalizerAndAppraiserMethod, __bc_argArray, __bc_rv, __bc_thrown);
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
