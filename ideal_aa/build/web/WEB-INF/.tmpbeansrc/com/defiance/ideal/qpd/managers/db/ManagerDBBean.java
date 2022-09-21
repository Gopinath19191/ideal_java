
package com.defiance.ideal.qpd.managers.db;

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
public class ManagerDBBean
extends com.defiance.ideal.shared.DBConnectivityBean
implements com.defiance.ideal.qpd.managers.db.ManagerDB
{
    static final Method _updateNormalisedSubmitStatusMethod;
    static final Method _getAppraiseesByHrAndNormalizerAndSBUMethod;
    static final Method _getDevelopmentDataMethod;
    static final Method _getAppraiseesByNormalizer1Method;
    static final Method _updateNormalizerRatingMethod;
    static final Method _getAppraiseesByFinanceMethod;
    static final Method _updateNormalisedReviewerRatingMethod;
    static final Method _getAppraiseesByReviewerMethod;
    static final Method _getAppraiseesByNormalizerAndReviewerMethod;
    static final Method _getKraDataMethod;
    static final Method _getAppraisersByReviewerMethod;
    static final Method _getGoalDataMethod;
    static final Method _getCompanyStructureBrHrMethod;
    static final Method _updateSendbackStatusMethod;
    static final Method _getAppraisersByNormalizerMethod;
    static final Method _getAppraiseesByNormalizerAndAppraiserReviewerMethod;
    static final Method _getAppraiseesByHrAndSBUMethod;
    static final Method _getAppraiseesByHrAndNormalizerMethod;
    static final Method _updateSubmitStatusCorrectedNormalizerRatingMethod;
    static final Method _getAppraiseesByHr0Method;
    static final Method _getNormalizersByHrMethod;
    static final Method _updateFinalSubmitStatusMethod;
    static final Method _updateCorrectedNormalizerRatingMethod;
    static final Method _getAppraiseesByNormalizerAndAppraiserMethod;
    static final Method _updateSubmitStatusMethod;
    static final Method _getAppraiseesByNormalizer0Method;
    static final Method _getAchievementsDataMethod;
    static final Method _getSelectedAppraiseeDetailsMethod;
    static final Method _updateReviewerRatingMethod;
    static final Method _getAppraiseesByHr1Method;
    static final Method _getReviewersByNormalizerMethod;
    static final Method _updateSubmitStatusNormalizerMethod;
    static final Method _getAppraiseeCountMethod;
    static final Method _getAppraiseesByReviewerAndAppraiserMethod;
    
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
            _updateNormalisedSubmitStatusMethod = com.defiance.ideal.qpd.managers.db.ManagerDB.class.getMethod("updateNormalisedSubmitStatus", new Class [] {java.lang.String.class, int.class, int.class, int.class, int.class, java.lang.String.class});
            _methodParamMap.put(_updateNormalisedSubmitStatusMethod, new String [] { "reviewerId", "appraisalYear", "appraiseeId", "reviewerRating", "submitStatus", "justifyRatingReviewer" });
            _getAppraiseesByHrAndNormalizerAndSBUMethod = com.defiance.ideal.qpd.managers.db.ManagerDB.class.getMethod("getAppraiseesByHrAndNormalizerAndSBU", new Class [] {java.lang.String.class, int.class, java.lang.String.class, java.lang.String.class, int.class});
            _methodParamMap.put(_getAppraiseesByHrAndNormalizerAndSBUMethod, new String [] { "hrId", "appraisalYear", "selectedNormalizerId", "selectedSBUDepartmentId", "triggerStatus" });
            _getDevelopmentDataMethod = com.defiance.ideal.qpd.managers.db.ManagerDB.class.getMethod("getDevelopmentData", new Class [] {int.class, int.class});
            _methodParamMap.put(_getDevelopmentDataMethod, new String [] { "appraisalYear", "appraiseeId" });
            _getAppraiseesByNormalizer1Method = com.defiance.ideal.qpd.managers.db.ManagerDB.class.getMethod("getAppraiseesByNormalizer", new Class [] {java.lang.String.class, int.class, int.class, int.class, java.lang.String.class});
            _methodParamMap.put(_getAppraiseesByNormalizer1Method, new String [] { "normalizerId", "appraisalYear", "submitStatusCheck", "triggerStatus", "whereCondition" });
            _updateNormalizerRatingMethod = com.defiance.ideal.qpd.managers.db.ManagerDB.class.getMethod("updateNormalizerRating", new Class [] {java.lang.String.class, int.class, int.class, int.class, int.class, java.lang.String.class, java.lang.String.class});
            _methodParamMap.put(_updateNormalizerRatingMethod, new String [] { "normalizerId", "appraisalQuarter", "appraisalYear", "appraiseeId", "normalizerRating", "justifyRatingReviewer", "normalizerPromotionRec" });
            _getAppraiseesByFinanceMethod = com.defiance.ideal.qpd.managers.db.ManagerDB.class.getMethod("getAppraiseesByFinance", new Class [] {java.lang.String.class, int.class, int.class, int.class});
            _methodParamMap.put(_getAppraiseesByFinanceMethod, new String [] { "financeId", "appraisalYear", "submitStatusCheck", "triggerStatus" });
            _updateNormalisedReviewerRatingMethod = com.defiance.ideal.qpd.managers.db.ManagerDB.class.getMethod("updateNormalisedReviewerRating", new Class [] {java.lang.String.class, int.class, int.class, int.class, java.lang.String.class});
            _methodParamMap.put(_updateNormalisedReviewerRatingMethod, new String [] { "reviewerId", "appraisalYear", "appraiseeId", "reviewerRating", "justifyRatingReviewer" });
            _getAppraiseesByReviewerMethod = com.defiance.ideal.qpd.managers.db.ManagerDB.class.getMethod("getAppraiseesByReviewer", new Class [] {java.lang.String.class, int.class, int.class, int.class});
            _methodParamMap.put(_getAppraiseesByReviewerMethod, new String [] { "reviewerId", "appraisalYear", "submitStatus", "triggerStatus" });
            _getAppraiseesByNormalizerAndReviewerMethod = com.defiance.ideal.qpd.managers.db.ManagerDB.class.getMethod("getAppraiseesByNormalizerAndReviewer", new Class [] {java.lang.String.class, java.lang.String.class, int.class, int.class, int.class});
            _methodParamMap.put(_getAppraiseesByNormalizerAndReviewerMethod, new String [] { "selectedReviewerId", "normalizerId", "appraisalYear", "submitStatusCheck", "triggerStatus" });
            _getKraDataMethod = com.defiance.ideal.qpd.managers.db.ManagerDB.class.getMethod("getKraData", new Class [] {int.class, int.class, int.class, int.class});
            _methodParamMap.put(_getKraDataMethod, new String [] { "bandId", "appraisalYear", "appraiseeId", "departmentId" });
            _getAppraisersByReviewerMethod = com.defiance.ideal.qpd.managers.db.ManagerDB.class.getMethod("getAppraisersByReviewer", new Class [] {java.lang.String.class, int.class, int.class, int.class});
            _methodParamMap.put(_getAppraisersByReviewerMethod, new String [] { "reviewerId", "appraisalYear", "submitStatus", "triggerStatus" });
            _getGoalDataMethod = com.defiance.ideal.qpd.managers.db.ManagerDB.class.getMethod("getGoalData", new Class [] {int.class, int.class});
            _methodParamMap.put(_getGoalDataMethod, new String [] { "appraisalYear", "appraiseeId" });
            _getCompanyStructureBrHrMethod = com.defiance.ideal.qpd.managers.db.ManagerDB.class.getMethod("getCompanyStructureBrHr", new Class [] {java.lang.String.class, int.class, int.class, int.class});
            _methodParamMap.put(_getCompanyStructureBrHrMethod, new String [] { "hrId", "appraisalQuarter", "appraisalYear", "triggerStatus" });
            _updateSendbackStatusMethod = com.defiance.ideal.qpd.managers.db.ManagerDB.class.getMethod("updateSendbackStatus", new Class [] {java.lang.String.class, int.class, int.class, int.class, int.class});
            _methodParamMap.put(_updateSendbackStatusMethod, new String [] { "hrId", "appraisalYear", "appraiseeId", "submitStatusSendback", "sendbackStatus" });
            _getAppraisersByNormalizerMethod = com.defiance.ideal.qpd.managers.db.ManagerDB.class.getMethod("getAppraisersByNormalizer", new Class [] {java.lang.String.class, int.class, int.class, int.class});
            _methodParamMap.put(_getAppraisersByNormalizerMethod, new String [] { "normalizerId", "appraisalYear", "submitStatusCheck", "triggerStatus" });
            _getAppraiseesByNormalizerAndAppraiserReviewerMethod = com.defiance.ideal.qpd.managers.db.ManagerDB.class.getMethod("getAppraiseesByNormalizerAndAppraiserReviewer", new Class [] {java.lang.String.class, java.lang.String.class, java.lang.String.class, int.class, int.class, int.class});
            _methodParamMap.put(_getAppraiseesByNormalizerAndAppraiserReviewerMethod, new String [] { "selectedAppraiserId", "selectedReviewerId", "normalizerId", "appraisalYear", "submitStatusCheck", "triggerStatus" });
            _getAppraiseesByHrAndSBUMethod = com.defiance.ideal.qpd.managers.db.ManagerDB.class.getMethod("getAppraiseesByHrAndSBU", new Class [] {java.lang.String.class, int.class, java.lang.String.class, int.class});
            _methodParamMap.put(_getAppraiseesByHrAndSBUMethod, new String [] { "hrId", "appraisalYear", "selectedSBUDepartmentId", "triggerStatus" });
            _getAppraiseesByHrAndNormalizerMethod = com.defiance.ideal.qpd.managers.db.ManagerDB.class.getMethod("getAppraiseesByHrAndNormalizer", new Class [] {java.lang.String.class, int.class, java.lang.String.class, int.class});
            _methodParamMap.put(_getAppraiseesByHrAndNormalizerMethod, new String [] { "hrId", "appraisalYear", "selectedNormalizerId", "triggerStatus" });
            _updateSubmitStatusCorrectedNormalizerRatingMethod = com.defiance.ideal.qpd.managers.db.ManagerDB.class.getMethod("updateSubmitStatusCorrectedNormalizerRating", new Class [] {java.lang.String.class, int.class, int.class, int.class, int.class, java.lang.String.class, java.lang.String.class});
            _methodParamMap.put(_updateSubmitStatusCorrectedNormalizerRatingMethod, new String [] { "normalizerId", "appraisalYear", "appraiseeId", "normalizerRating", "submitStatus", "normalizerComments", "normalizerPromotionRec" });
            _getAppraiseesByHr0Method = com.defiance.ideal.qpd.managers.db.ManagerDB.class.getMethod("getAppraiseesByHr", new Class [] {java.lang.String.class, int.class, int.class, int.class});
            _methodParamMap.put(_getAppraiseesByHr0Method, new String [] { "hrId", "appraisalYear", "submitStatus", "triggerStatus" });
            _getNormalizersByHrMethod = com.defiance.ideal.qpd.managers.db.ManagerDB.class.getMethod("getNormalizersByHr", new Class [] {java.lang.String.class, int.class, int.class});
            _methodParamMap.put(_getNormalizersByHrMethod, new String [] { "hrId", "appraisalYear", "triggerStatus" });
            _updateFinalSubmitStatusMethod = com.defiance.ideal.qpd.managers.db.ManagerDB.class.getMethod("updateFinalSubmitStatus", new Class [] {java.lang.String.class, int.class, int.class, int.class});
            _methodParamMap.put(_updateFinalSubmitStatusMethod, new String [] { "hrId", "appraisalYear", "appraiseeId", "submitStatus" });
            _updateCorrectedNormalizerRatingMethod = com.defiance.ideal.qpd.managers.db.ManagerDB.class.getMethod("updateCorrectedNormalizerRating", new Class [] {java.lang.String.class, int.class, int.class, int.class, java.lang.String.class, java.lang.String.class});
            _methodParamMap.put(_updateCorrectedNormalizerRatingMethod, new String [] { "normalizerId", "appraisalYear", "appraiseeId", "normalizerRating", "justifyRatingReviewer", "normalizerPromotionRec" });
            _getAppraiseesByNormalizerAndAppraiserMethod = com.defiance.ideal.qpd.managers.db.ManagerDB.class.getMethod("getAppraiseesByNormalizerAndAppraiser", new Class [] {java.lang.String.class, java.lang.String.class, int.class, int.class, int.class});
            _methodParamMap.put(_getAppraiseesByNormalizerAndAppraiserMethod, new String [] { "selectedAppraiserId", "normalizerId", "appraisalYear", "submitStatusCheck", "triggerStatus" });
            _updateSubmitStatusMethod = com.defiance.ideal.qpd.managers.db.ManagerDB.class.getMethod("updateSubmitStatus", new Class [] {java.lang.String.class, int.class, int.class, int.class, int.class, java.lang.String.class, java.lang.String.class});
            _methodParamMap.put(_updateSubmitStatusMethod, new String [] { "reviewerId", "appraisalYear", "appraiseeId", "reviewerRating", "submitStatus", "justifyRatingReviewer", "reviewerPromotion" });
            _getAppraiseesByNormalizer0Method = com.defiance.ideal.qpd.managers.db.ManagerDB.class.getMethod("getAppraiseesByNormalizer", new Class [] {java.lang.String.class, int.class, int.class, int.class});
            _methodParamMap.put(_getAppraiseesByNormalizer0Method, new String [] { "normalizerId", "appraisalYear", "submitStatusCheck", "triggerStatus" });
            _getAchievementsDataMethod = com.defiance.ideal.qpd.managers.db.ManagerDB.class.getMethod("getAchievementsData", new Class [] {int.class, int.class});
            _methodParamMap.put(_getAchievementsDataMethod, new String [] { "appraisalYear", "appraiseeId" });
            _getSelectedAppraiseeDetailsMethod = com.defiance.ideal.qpd.managers.db.ManagerDB.class.getMethod("getSelectedAppraiseeDetails", new Class [] {int.class, int.class});
            _methodParamMap.put(_getSelectedAppraiseeDetailsMethod, new String [] { "appraiseeId", "appraisalYear" });
            _updateReviewerRatingMethod = com.defiance.ideal.qpd.managers.db.ManagerDB.class.getMethod("updateReviewerRating", new Class [] {java.lang.String.class, int.class, int.class, int.class, java.lang.String.class, java.lang.String.class});
            _methodParamMap.put(_updateReviewerRatingMethod, new String [] { "reviewerId", "appraisalYear", "appraiseeId", "reviewerRating", "justifyRatingReviewer", "reviewerPromotion" });
            _getAppraiseesByHr1Method = com.defiance.ideal.qpd.managers.db.ManagerDB.class.getMethod("getAppraiseesByHr", new Class [] {java.lang.String.class, int.class, int.class, int.class, java.lang.String.class});
            _methodParamMap.put(_getAppraiseesByHr1Method, new String [] { "hrId", "appraisalYear", "submitStatus", "triggerStatus", "whereCondition" });
            _getReviewersByNormalizerMethod = com.defiance.ideal.qpd.managers.db.ManagerDB.class.getMethod("getReviewersByNormalizer", new Class [] {java.lang.String.class, int.class, int.class, int.class});
            _methodParamMap.put(_getReviewersByNormalizerMethod, new String [] { "normalizerId", "appraisalYear", "submitStatusCheck", "triggerStatus" });
            _updateSubmitStatusNormalizerMethod = com.defiance.ideal.qpd.managers.db.ManagerDB.class.getMethod("updateSubmitStatusNormalizer", new Class [] {java.lang.String.class, int.class, int.class, int.class, int.class, java.lang.String.class, java.lang.String.class});
            _methodParamMap.put(_updateSubmitStatusNormalizerMethod, new String [] { "normalizerId", "appraisalYear", "appraiseeId", "normalizerRating", "submitStatus", "normalizerComments", "normalizerPromotionRec" });
            _getAppraiseeCountMethod = com.defiance.ideal.qpd.managers.db.ManagerDB.class.getMethod("getAppraiseeCount", new Class [] {java.lang.String.class, int.class, java.lang.String.class, int.class});
            _methodParamMap.put(_getAppraiseeCountMethod, new String [] { "referenceId", "appraisalYear", "referenceType", "triggerStatus" });
            _getAppraiseesByReviewerAndAppraiserMethod = com.defiance.ideal.qpd.managers.db.ManagerDB.class.getMethod("getAppraiseesByReviewerAndAppraiser", new Class [] {java.lang.String.class, java.lang.String.class, int.class, int.class, int.class});
            _methodParamMap.put(_getAppraiseesByReviewerAndAppraiserMethod, new String [] { "selectedAppraiserId", "reviewerId", "appraisalYear", "submitStatus", "triggerStatus" });
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
    public ManagerDBBean(ControlBeanContext context, String id, PropertyMap props)
    {
        this(context, id, props, com.defiance.ideal.qpd.managers.db.ManagerDB.class);
    }
    
    /**
    * This is the public null-arg constructor for this ControlBean.  If a control id
    * is not provided, a unique value will be auto-generated.
    */
    public ManagerDBBean()
    {
        this(null, null, null);
    }
    
    /**
    * This is the protected version that is used by any ControlBean subclass
    */
    protected ManagerDBBean(ControlBeanContext context, String id, PropertyMap props, Class controlClass)
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
    * Implements com.defiance.ideal.qpd.managers.db.ManagerDB.updateNormalisedSubmitStatus
    */
    public  void updateNormalisedSubmitStatus(java.lang.String reviewerId, int appraisalYear, int appraiseeId, int reviewerRating, int submitStatus, java.lang.String justifyRatingReviewer) 
    {
        Object [] __bc_argArray = new Object[] { reviewerId, appraisalYear, appraiseeId, reviewerRating, submitStatus, justifyRatingReviewer };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        
        try
        {
            preInvoke(_updateNormalisedSubmitStatusMethod, __bc_argArray);
            
            __bc_target.invoke(_updateNormalisedSubmitStatusMethod, __bc_argArray)
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
            postInvoke(_updateNormalisedSubmitStatusMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
    }
    
    /**
    * Implements com.defiance.ideal.qpd.managers.db.ManagerDB.getAppraiseesByHrAndNormalizerAndSBU
    */
    public  com.defiance.ideal.qpd.managers.dto.ManagerDTO[] getAppraiseesByHrAndNormalizerAndSBU(java.lang.String hrId, int appraisalYear, java.lang.String selectedNormalizerId, java.lang.String selectedSBUDepartmentId, int triggerStatus) 
    {
        Object [] __bc_argArray = new Object[] { hrId, appraisalYear, selectedNormalizerId, selectedSBUDepartmentId, triggerStatus };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        com.defiance.ideal.qpd.managers.dto.ManagerDTO[] __bc_retval = null;
        
        try
        {
            preInvoke(_getAppraiseesByHrAndNormalizerAndSBUMethod, __bc_argArray);
            
            __bc_retval =
            (com.defiance.ideal.qpd.managers.dto.ManagerDTO[])
            __bc_target.invoke(_getAppraiseesByHrAndNormalizerAndSBUMethod, __bc_argArray)
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
    * Implements com.defiance.ideal.qpd.managers.db.ManagerDB.getDevelopmentData
    */
    public  com.defiance.ideal.qpd.appraiser.dto.AppraiserFormDTO[] getDevelopmentData(int appraisalYear, int appraiseeId) 
    {
        Object [] __bc_argArray = new Object[] { appraisalYear, appraiseeId };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        com.defiance.ideal.qpd.appraiser.dto.AppraiserFormDTO[] __bc_retval = null;
        
        try
        {
            preInvoke(_getDevelopmentDataMethod, __bc_argArray);
            
            __bc_retval =
            (com.defiance.ideal.qpd.appraiser.dto.AppraiserFormDTO[])
            __bc_target.invoke(_getDevelopmentDataMethod, __bc_argArray)
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
    * Implements com.defiance.ideal.qpd.managers.db.ManagerDB.getAppraiseesByNormalizer
    */
    public  com.defiance.ideal.qpd.managers.dto.ManagerDTO[] getAppraiseesByNormalizer(java.lang.String normalizerId, int appraisalYear, int submitStatusCheck, int triggerStatus, java.lang.String whereCondition) 
    {
        Object [] __bc_argArray = new Object[] { normalizerId, appraisalYear, submitStatusCheck, triggerStatus, whereCondition };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        com.defiance.ideal.qpd.managers.dto.ManagerDTO[] __bc_retval = null;
        
        try
        {
            preInvoke(_getAppraiseesByNormalizer1Method, __bc_argArray);
            
            __bc_retval =
            (com.defiance.ideal.qpd.managers.dto.ManagerDTO[])
            __bc_target.invoke(_getAppraiseesByNormalizer1Method, __bc_argArray)
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
    * Implements com.defiance.ideal.qpd.managers.db.ManagerDB.updateNormalizerRating
    */
    public  void updateNormalizerRating(java.lang.String normalizerId, int appraisalQuarter, int appraisalYear, int appraiseeId, int normalizerRating, java.lang.String justifyRatingReviewer, java.lang.String normalizerPromotionRec) 
    {
        Object [] __bc_argArray = new Object[] { normalizerId, appraisalQuarter, appraisalYear, appraiseeId, normalizerRating, justifyRatingReviewer, normalizerPromotionRec };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        
        try
        {
            preInvoke(_updateNormalizerRatingMethod, __bc_argArray);
            
            __bc_target.invoke(_updateNormalizerRatingMethod, __bc_argArray)
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
    * Implements com.defiance.ideal.qpd.managers.db.ManagerDB.getAppraiseesByFinance
    */
    public  com.defiance.ideal.qpd.managers.dto.ManagerDTO[] getAppraiseesByFinance(java.lang.String financeId, int appraisalYear, int submitStatusCheck, int triggerStatus) 
    {
        Object [] __bc_argArray = new Object[] { financeId, appraisalYear, submitStatusCheck, triggerStatus };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        com.defiance.ideal.qpd.managers.dto.ManagerDTO[] __bc_retval = null;
        
        try
        {
            preInvoke(_getAppraiseesByFinanceMethod, __bc_argArray);
            
            __bc_retval =
            (com.defiance.ideal.qpd.managers.dto.ManagerDTO[])
            __bc_target.invoke(_getAppraiseesByFinanceMethod, __bc_argArray)
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
    * Implements com.defiance.ideal.qpd.managers.db.ManagerDB.updateNormalisedReviewerRating
    */
    public  void updateNormalisedReviewerRating(java.lang.String reviewerId, int appraisalYear, int appraiseeId, int reviewerRating, java.lang.String justifyRatingReviewer) 
    {
        Object [] __bc_argArray = new Object[] { reviewerId, appraisalYear, appraiseeId, reviewerRating, justifyRatingReviewer };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        
        try
        {
            preInvoke(_updateNormalisedReviewerRatingMethod, __bc_argArray);
            
            __bc_target.invoke(_updateNormalisedReviewerRatingMethod, __bc_argArray)
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
            postInvoke(_updateNormalisedReviewerRatingMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
    }
    
    /**
    * Implements com.defiance.ideal.qpd.managers.db.ManagerDB.getAppraiseesByReviewer
    */
    public  com.defiance.ideal.qpd.managers.dto.ManagerDTO[] getAppraiseesByReviewer(java.lang.String reviewerId, int appraisalYear, int submitStatus, int triggerStatus) 
    {
        Object [] __bc_argArray = new Object[] { reviewerId, appraisalYear, submitStatus, triggerStatus };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        com.defiance.ideal.qpd.managers.dto.ManagerDTO[] __bc_retval = null;
        
        try
        {
            preInvoke(_getAppraiseesByReviewerMethod, __bc_argArray);
            
            __bc_retval =
            (com.defiance.ideal.qpd.managers.dto.ManagerDTO[])
            __bc_target.invoke(_getAppraiseesByReviewerMethod, __bc_argArray)
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
    * Implements com.defiance.ideal.qpd.managers.db.ManagerDB.getAppraiseesByNormalizerAndReviewer
    */
    public  com.defiance.ideal.qpd.managers.dto.ManagerDTO[] getAppraiseesByNormalizerAndReviewer(java.lang.String selectedReviewerId, java.lang.String normalizerId, int appraisalYear, int submitStatusCheck, int triggerStatus) 
    {
        Object [] __bc_argArray = new Object[] { selectedReviewerId, normalizerId, appraisalYear, submitStatusCheck, triggerStatus };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        com.defiance.ideal.qpd.managers.dto.ManagerDTO[] __bc_retval = null;
        
        try
        {
            preInvoke(_getAppraiseesByNormalizerAndReviewerMethod, __bc_argArray);
            
            __bc_retval =
            (com.defiance.ideal.qpd.managers.dto.ManagerDTO[])
            __bc_target.invoke(_getAppraiseesByNormalizerAndReviewerMethod, __bc_argArray)
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
    * Implements com.defiance.ideal.qpd.managers.db.ManagerDB.getKraData
    */
    public  com.defiance.ideal.qpd.managers.dto.ManagerDTO[] getKraData(int bandId, int appraisalYear, int appraiseeId, int departmentId) 
    {
        Object [] __bc_argArray = new Object[] { bandId, appraisalYear, appraiseeId, departmentId };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        com.defiance.ideal.qpd.managers.dto.ManagerDTO[] __bc_retval = null;
        
        try
        {
            preInvoke(_getKraDataMethod, __bc_argArray);
            
            __bc_retval =
            (com.defiance.ideal.qpd.managers.dto.ManagerDTO[])
            __bc_target.invoke(_getKraDataMethod, __bc_argArray)
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
    * Implements com.defiance.ideal.qpd.managers.db.ManagerDB.getAppraisersByReviewer
    */
    public  com.defiance.ideal.qpd.managers.dto.ManagerDTO[] getAppraisersByReviewer(java.lang.String reviewerId, int appraisalYear, int submitStatus, int triggerStatus) 
    {
        Object [] __bc_argArray = new Object[] { reviewerId, appraisalYear, submitStatus, triggerStatus };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        com.defiance.ideal.qpd.managers.dto.ManagerDTO[] __bc_retval = null;
        
        try
        {
            preInvoke(_getAppraisersByReviewerMethod, __bc_argArray);
            
            __bc_retval =
            (com.defiance.ideal.qpd.managers.dto.ManagerDTO[])
            __bc_target.invoke(_getAppraisersByReviewerMethod, __bc_argArray)
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
    * Implements com.defiance.ideal.qpd.managers.db.ManagerDB.getGoalData
    */
    public  com.defiance.ideal.qpd.appraiser.dto.AppraiserFormDTO[] getGoalData(int appraisalYear, int appraiseeId) 
    {
        Object [] __bc_argArray = new Object[] { appraisalYear, appraiseeId };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        com.defiance.ideal.qpd.appraiser.dto.AppraiserFormDTO[] __bc_retval = null;
        
        try
        {
            preInvoke(_getGoalDataMethod, __bc_argArray);
            
            __bc_retval =
            (com.defiance.ideal.qpd.appraiser.dto.AppraiserFormDTO[])
            __bc_target.invoke(_getGoalDataMethod, __bc_argArray)
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
    * Implements com.defiance.ideal.qpd.managers.db.ManagerDB.getCompanyStructureBrHr
    */
    public  com.defiance.ideal.qpd.managers.dto.ManagerDTO[] getCompanyStructureBrHr(java.lang.String hrId, int appraisalQuarter, int appraisalYear, int triggerStatus) 
    {
        Object [] __bc_argArray = new Object[] { hrId, appraisalQuarter, appraisalYear, triggerStatus };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        com.defiance.ideal.qpd.managers.dto.ManagerDTO[] __bc_retval = null;
        
        try
        {
            preInvoke(_getCompanyStructureBrHrMethod, __bc_argArray);
            
            __bc_retval =
            (com.defiance.ideal.qpd.managers.dto.ManagerDTO[])
            __bc_target.invoke(_getCompanyStructureBrHrMethod, __bc_argArray)
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
    * Implements com.defiance.ideal.qpd.managers.db.ManagerDB.updateSendbackStatus
    */
    public  void updateSendbackStatus(java.lang.String hrId, int appraisalYear, int appraiseeId, int submitStatusSendback, int sendbackStatus) 
    {
        Object [] __bc_argArray = new Object[] { hrId, appraisalYear, appraiseeId, submitStatusSendback, sendbackStatus };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        
        try
        {
            preInvoke(_updateSendbackStatusMethod, __bc_argArray);
            
            __bc_target.invoke(_updateSendbackStatusMethod, __bc_argArray)
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
            postInvoke(_updateSendbackStatusMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
    }
    
    /**
    * Implements com.defiance.ideal.qpd.managers.db.ManagerDB.getAppraisersByNormalizer
    */
    public  com.defiance.ideal.qpd.managers.dto.ManagerDTO[] getAppraisersByNormalizer(java.lang.String normalizerId, int appraisalYear, int submitStatusCheck, int triggerStatus) 
    {
        Object [] __bc_argArray = new Object[] { normalizerId, appraisalYear, submitStatusCheck, triggerStatus };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        com.defiance.ideal.qpd.managers.dto.ManagerDTO[] __bc_retval = null;
        
        try
        {
            preInvoke(_getAppraisersByNormalizerMethod, __bc_argArray);
            
            __bc_retval =
            (com.defiance.ideal.qpd.managers.dto.ManagerDTO[])
            __bc_target.invoke(_getAppraisersByNormalizerMethod, __bc_argArray)
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
    * Implements com.defiance.ideal.qpd.managers.db.ManagerDB.getAppraiseesByNormalizerAndAppraiserReviewer
    */
    public  com.defiance.ideal.qpd.managers.dto.ManagerDTO[] getAppraiseesByNormalizerAndAppraiserReviewer(java.lang.String selectedAppraiserId, java.lang.String selectedReviewerId, java.lang.String normalizerId, int appraisalYear, int submitStatusCheck, int triggerStatus) 
    {
        Object [] __bc_argArray = new Object[] { selectedAppraiserId, selectedReviewerId, normalizerId, appraisalYear, submitStatusCheck, triggerStatus };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        com.defiance.ideal.qpd.managers.dto.ManagerDTO[] __bc_retval = null;
        
        try
        {
            preInvoke(_getAppraiseesByNormalizerAndAppraiserReviewerMethod, __bc_argArray);
            
            __bc_retval =
            (com.defiance.ideal.qpd.managers.dto.ManagerDTO[])
            __bc_target.invoke(_getAppraiseesByNormalizerAndAppraiserReviewerMethod, __bc_argArray)
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
    * Implements com.defiance.ideal.qpd.managers.db.ManagerDB.getAppraiseesByHrAndSBU
    */
    public  com.defiance.ideal.qpd.managers.dto.ManagerDTO[] getAppraiseesByHrAndSBU(java.lang.String hrId, int appraisalYear, java.lang.String selectedSBUDepartmentId, int triggerStatus) 
    {
        Object [] __bc_argArray = new Object[] { hrId, appraisalYear, selectedSBUDepartmentId, triggerStatus };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        com.defiance.ideal.qpd.managers.dto.ManagerDTO[] __bc_retval = null;
        
        try
        {
            preInvoke(_getAppraiseesByHrAndSBUMethod, __bc_argArray);
            
            __bc_retval =
            (com.defiance.ideal.qpd.managers.dto.ManagerDTO[])
            __bc_target.invoke(_getAppraiseesByHrAndSBUMethod, __bc_argArray)
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
    * Implements com.defiance.ideal.qpd.managers.db.ManagerDB.getAppraiseesByHrAndNormalizer
    */
    public  com.defiance.ideal.qpd.managers.dto.ManagerDTO[] getAppraiseesByHrAndNormalizer(java.lang.String hrId, int appraisalYear, java.lang.String selectedNormalizerId, int triggerStatus) 
    {
        Object [] __bc_argArray = new Object[] { hrId, appraisalYear, selectedNormalizerId, triggerStatus };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        com.defiance.ideal.qpd.managers.dto.ManagerDTO[] __bc_retval = null;
        
        try
        {
            preInvoke(_getAppraiseesByHrAndNormalizerMethod, __bc_argArray);
            
            __bc_retval =
            (com.defiance.ideal.qpd.managers.dto.ManagerDTO[])
            __bc_target.invoke(_getAppraiseesByHrAndNormalizerMethod, __bc_argArray)
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
    * Implements com.defiance.ideal.qpd.managers.db.ManagerDB.updateSubmitStatusCorrectedNormalizerRating
    */
    public  void updateSubmitStatusCorrectedNormalizerRating(java.lang.String normalizerId, int appraisalYear, int appraiseeId, int normalizerRating, int submitStatus, java.lang.String normalizerComments, java.lang.String normalizerPromotionRec) 
    {
        Object [] __bc_argArray = new Object[] { normalizerId, appraisalYear, appraiseeId, normalizerRating, submitStatus, normalizerComments, normalizerPromotionRec };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        
        try
        {
            preInvoke(_updateSubmitStatusCorrectedNormalizerRatingMethod, __bc_argArray);
            
            __bc_target.invoke(_updateSubmitStatusCorrectedNormalizerRatingMethod, __bc_argArray)
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
            postInvoke(_updateSubmitStatusCorrectedNormalizerRatingMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
    }
    
    /**
    * Implements com.defiance.ideal.qpd.managers.db.ManagerDB.getAppraiseesByHr
    */
    public  com.defiance.ideal.qpd.managers.dto.ManagerDTO[] getAppraiseesByHr(java.lang.String hrId, int appraisalYear, int submitStatus, int triggerStatus) 
    {
        Object [] __bc_argArray = new Object[] { hrId, appraisalYear, submitStatus, triggerStatus };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        com.defiance.ideal.qpd.managers.dto.ManagerDTO[] __bc_retval = null;
        
        try
        {
            preInvoke(_getAppraiseesByHr0Method, __bc_argArray);
            
            __bc_retval =
            (com.defiance.ideal.qpd.managers.dto.ManagerDTO[])
            __bc_target.invoke(_getAppraiseesByHr0Method, __bc_argArray)
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
    * Implements com.defiance.ideal.qpd.managers.db.ManagerDB.getNormalizersByHr
    */
    public  com.defiance.ideal.qpd.managers.dto.ManagerDTO[] getNormalizersByHr(java.lang.String hrId, int appraisalYear, int triggerStatus) 
    {
        Object [] __bc_argArray = new Object[] { hrId, appraisalYear, triggerStatus };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        com.defiance.ideal.qpd.managers.dto.ManagerDTO[] __bc_retval = null;
        
        try
        {
            preInvoke(_getNormalizersByHrMethod, __bc_argArray);
            
            __bc_retval =
            (com.defiance.ideal.qpd.managers.dto.ManagerDTO[])
            __bc_target.invoke(_getNormalizersByHrMethod, __bc_argArray)
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
    * Implements com.defiance.ideal.qpd.managers.db.ManagerDB.updateFinalSubmitStatus
    */
    public  void updateFinalSubmitStatus(java.lang.String hrId, int appraisalYear, int appraiseeId, int submitStatus) 
    {
        Object [] __bc_argArray = new Object[] { hrId, appraisalYear, appraiseeId, submitStatus };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        
        try
        {
            preInvoke(_updateFinalSubmitStatusMethod, __bc_argArray);
            
            __bc_target.invoke(_updateFinalSubmitStatusMethod, __bc_argArray)
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
            postInvoke(_updateFinalSubmitStatusMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
    }
    
    /**
    * Implements com.defiance.ideal.qpd.managers.db.ManagerDB.updateCorrectedNormalizerRating
    */
    public  void updateCorrectedNormalizerRating(java.lang.String normalizerId, int appraisalYear, int appraiseeId, int normalizerRating, java.lang.String justifyRatingReviewer, java.lang.String normalizerPromotionRec) 
    {
        Object [] __bc_argArray = new Object[] { normalizerId, appraisalYear, appraiseeId, normalizerRating, justifyRatingReviewer, normalizerPromotionRec };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        
        try
        {
            preInvoke(_updateCorrectedNormalizerRatingMethod, __bc_argArray);
            
            __bc_target.invoke(_updateCorrectedNormalizerRatingMethod, __bc_argArray)
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
            postInvoke(_updateCorrectedNormalizerRatingMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
    }
    
    /**
    * Implements com.defiance.ideal.qpd.managers.db.ManagerDB.getAppraiseesByNormalizerAndAppraiser
    */
    public  com.defiance.ideal.qpd.managers.dto.ManagerDTO[] getAppraiseesByNormalizerAndAppraiser(java.lang.String selectedAppraiserId, java.lang.String normalizerId, int appraisalYear, int submitStatusCheck, int triggerStatus) 
    {
        Object [] __bc_argArray = new Object[] { selectedAppraiserId, normalizerId, appraisalYear, submitStatusCheck, triggerStatus };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        com.defiance.ideal.qpd.managers.dto.ManagerDTO[] __bc_retval = null;
        
        try
        {
            preInvoke(_getAppraiseesByNormalizerAndAppraiserMethod, __bc_argArray);
            
            __bc_retval =
            (com.defiance.ideal.qpd.managers.dto.ManagerDTO[])
            __bc_target.invoke(_getAppraiseesByNormalizerAndAppraiserMethod, __bc_argArray)
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
    * Implements com.defiance.ideal.qpd.managers.db.ManagerDB.updateSubmitStatus
    */
    public  void updateSubmitStatus(java.lang.String reviewerId, int appraisalYear, int appraiseeId, int reviewerRating, int submitStatus, java.lang.String justifyRatingReviewer, java.lang.String reviewerPromotion) 
    {
        Object [] __bc_argArray = new Object[] { reviewerId, appraisalYear, appraiseeId, reviewerRating, submitStatus, justifyRatingReviewer, reviewerPromotion };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        
        try
        {
            preInvoke(_updateSubmitStatusMethod, __bc_argArray);
            
            __bc_target.invoke(_updateSubmitStatusMethod, __bc_argArray)
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
            postInvoke(_updateSubmitStatusMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
    }
    
    /**
    * Implements com.defiance.ideal.qpd.managers.db.ManagerDB.getAppraiseesByNormalizer
    */
    public  com.defiance.ideal.qpd.managers.dto.ManagerDTO[] getAppraiseesByNormalizer(java.lang.String normalizerId, int appraisalYear, int submitStatusCheck, int triggerStatus) 
    {
        Object [] __bc_argArray = new Object[] { normalizerId, appraisalYear, submitStatusCheck, triggerStatus };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        com.defiance.ideal.qpd.managers.dto.ManagerDTO[] __bc_retval = null;
        
        try
        {
            preInvoke(_getAppraiseesByNormalizer0Method, __bc_argArray);
            
            __bc_retval =
            (com.defiance.ideal.qpd.managers.dto.ManagerDTO[])
            __bc_target.invoke(_getAppraiseesByNormalizer0Method, __bc_argArray)
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
    * Implements com.defiance.ideal.qpd.managers.db.ManagerDB.getAchievementsData
    */
    public  com.defiance.ideal.qpd.appraiser.dto.AppraiserFormDTO[] getAchievementsData(int appraisalYear, int appraiseeId) 
    {
        Object [] __bc_argArray = new Object[] { appraisalYear, appraiseeId };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        com.defiance.ideal.qpd.appraiser.dto.AppraiserFormDTO[] __bc_retval = null;
        
        try
        {
            preInvoke(_getAchievementsDataMethod, __bc_argArray);
            
            __bc_retval =
            (com.defiance.ideal.qpd.appraiser.dto.AppraiserFormDTO[])
            __bc_target.invoke(_getAchievementsDataMethod, __bc_argArray)
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
    * Implements com.defiance.ideal.qpd.managers.db.ManagerDB.getSelectedAppraiseeDetails
    */
    public  com.defiance.ideal.qpd.appraiser.dto.AppraiseeListDTO getSelectedAppraiseeDetails(int appraiseeId, int appraisalYear) 
    {
        Object [] __bc_argArray = new Object[] { appraiseeId, appraisalYear };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        com.defiance.ideal.qpd.appraiser.dto.AppraiseeListDTO __bc_retval = null;
        
        try
        {
            preInvoke(_getSelectedAppraiseeDetailsMethod, __bc_argArray);
            
            __bc_retval =
            (com.defiance.ideal.qpd.appraiser.dto.AppraiseeListDTO)
            __bc_target.invoke(_getSelectedAppraiseeDetailsMethod, __bc_argArray)
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
            postInvoke(_getSelectedAppraiseeDetailsMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
        return __bc_retval;
    }
    
    /**
    * Implements com.defiance.ideal.qpd.managers.db.ManagerDB.updateReviewerRating
    */
    public  void updateReviewerRating(java.lang.String reviewerId, int appraisalYear, int appraiseeId, int reviewerRating, java.lang.String justifyRatingReviewer, java.lang.String reviewerPromotion) 
    {
        Object [] __bc_argArray = new Object[] { reviewerId, appraisalYear, appraiseeId, reviewerRating, justifyRatingReviewer, reviewerPromotion };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        
        try
        {
            preInvoke(_updateReviewerRatingMethod, __bc_argArray);
            
            __bc_target.invoke(_updateReviewerRatingMethod, __bc_argArray)
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
    * Implements com.defiance.ideal.qpd.managers.db.ManagerDB.getAppraiseesByHr
    */
    public  com.defiance.ideal.qpd.managers.dto.ManagerDTO[] getAppraiseesByHr(java.lang.String hrId, int appraisalYear, int submitStatus, int triggerStatus, java.lang.String whereCondition) 
    {
        Object [] __bc_argArray = new Object[] { hrId, appraisalYear, submitStatus, triggerStatus, whereCondition };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        com.defiance.ideal.qpd.managers.dto.ManagerDTO[] __bc_retval = null;
        
        try
        {
            preInvoke(_getAppraiseesByHr1Method, __bc_argArray);
            
            __bc_retval =
            (com.defiance.ideal.qpd.managers.dto.ManagerDTO[])
            __bc_target.invoke(_getAppraiseesByHr1Method, __bc_argArray)
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
    * Implements com.defiance.ideal.qpd.managers.db.ManagerDB.getReviewersByNormalizer
    */
    public  com.defiance.ideal.qpd.managers.dto.ManagerDTO[] getReviewersByNormalizer(java.lang.String normalizerId, int appraisalYear, int submitStatusCheck, int triggerStatus) 
    {
        Object [] __bc_argArray = new Object[] { normalizerId, appraisalYear, submitStatusCheck, triggerStatus };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        com.defiance.ideal.qpd.managers.dto.ManagerDTO[] __bc_retval = null;
        
        try
        {
            preInvoke(_getReviewersByNormalizerMethod, __bc_argArray);
            
            __bc_retval =
            (com.defiance.ideal.qpd.managers.dto.ManagerDTO[])
            __bc_target.invoke(_getReviewersByNormalizerMethod, __bc_argArray)
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
    * Implements com.defiance.ideal.qpd.managers.db.ManagerDB.updateSubmitStatusNormalizer
    */
    public  void updateSubmitStatusNormalizer(java.lang.String normalizerId, int appraisalYear, int appraiseeId, int normalizerRating, int submitStatus, java.lang.String normalizerComments, java.lang.String normalizerPromotionRec) 
    {
        Object [] __bc_argArray = new Object[] { normalizerId, appraisalYear, appraiseeId, normalizerRating, submitStatus, normalizerComments, normalizerPromotionRec };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        
        try
        {
            preInvoke(_updateSubmitStatusNormalizerMethod, __bc_argArray);
            
            __bc_target.invoke(_updateSubmitStatusNormalizerMethod, __bc_argArray)
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
            postInvoke(_updateSubmitStatusNormalizerMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
    }
    
    /**
    * Implements com.defiance.ideal.qpd.managers.db.ManagerDB.getAppraiseeCount
    */
    public  com.defiance.ideal.qpd.managers.dto.ManagerDTO getAppraiseeCount(java.lang.String referenceId, int appraisalYear, java.lang.String referenceType, int triggerStatus) 
    {
        Object [] __bc_argArray = new Object[] { referenceId, appraisalYear, referenceType, triggerStatus };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        com.defiance.ideal.qpd.managers.dto.ManagerDTO __bc_retval = null;
        
        try
        {
            preInvoke(_getAppraiseeCountMethod, __bc_argArray);
            
            __bc_retval =
            (com.defiance.ideal.qpd.managers.dto.ManagerDTO)
            __bc_target.invoke(_getAppraiseeCountMethod, __bc_argArray)
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
    * Implements com.defiance.ideal.qpd.managers.db.ManagerDB.getAppraiseesByReviewerAndAppraiser
    */
    public  com.defiance.ideal.qpd.managers.dto.ManagerDTO[] getAppraiseesByReviewerAndAppraiser(java.lang.String selectedAppraiserId, java.lang.String reviewerId, int appraisalYear, int submitStatus, int triggerStatus) 
    {
        Object [] __bc_argArray = new Object[] { selectedAppraiserId, reviewerId, appraisalYear, submitStatus, triggerStatus };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        com.defiance.ideal.qpd.managers.dto.ManagerDTO[] __bc_retval = null;
        
        try
        {
            preInvoke(_getAppraiseesByReviewerAndAppraiserMethod, __bc_argArray);
            
            __bc_retval =
            (com.defiance.ideal.qpd.managers.dto.ManagerDTO[])
            __bc_target.invoke(_getAppraiseesByReviewerAndAppraiserMethod, __bc_argArray)
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
