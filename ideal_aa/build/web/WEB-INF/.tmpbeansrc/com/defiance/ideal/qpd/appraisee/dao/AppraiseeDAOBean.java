
package com.defiance.ideal.qpd.appraisee.dao;

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
public class AppraiseeDAOBean
extends org.apache.beehive.controls.runtime.bean.ControlBean
implements com.defiance.ideal.qpd.appraisee.dao.AppraiseeDAO
{
    static final Method _getDevlopAttributesMethod;
    static final Method _getCostAttributesMethod;
    static final Method _checkAppraiseeCommentsMethod;
    static final Method _getQualAttributesMethod;
    static final Method _authenticateAppraiseeMethod;
    static final Method _updateAppraiseeStatusMethod;
    static final Method _updateAppraiseeCommentsMethod;
    static final Method _getAppraiseeSBUMethod;
    static final Method _getAchievementsDataMethod;
    static final Method _getReviewerNameMethod;
    static final Method _getGoalDataMethod;
    static final Method _getAppraiserNameMethod;
    static final Method _getKraDataMethod;
    static final Method _insertAppraiseeCommentsMethod;
    static final Method _getCustomerAttributesMethod;
    static final Method _getDevNeedsDataMethod;
    static final Method _insertOrUpdateAppraiseeDataMethod;
    
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
            _getDevlopAttributesMethod = com.defiance.ideal.qpd.appraisee.dao.AppraiseeDAO.class.getMethod("getDevlopAttributes", new Class [] {java.lang.String.class});
            _methodParamMap.put(_getDevlopAttributesMethod, new String [] { "kraDevlop" });
            _getCostAttributesMethod = com.defiance.ideal.qpd.appraisee.dao.AppraiseeDAO.class.getMethod("getCostAttributes", new Class [] {java.lang.String.class});
            _methodParamMap.put(_getCostAttributesMethod, new String [] { "kraCost" });
            _checkAppraiseeCommentsMethod = com.defiance.ideal.qpd.appraisee.dao.AppraiseeDAO.class.getMethod("checkAppraiseeComments", new Class [] {int.class});
            _methodParamMap.put(_checkAppraiseeCommentsMethod, new String [] { "appraiseeId" });
            _getQualAttributesMethod = com.defiance.ideal.qpd.appraisee.dao.AppraiseeDAO.class.getMethod("getQualAttributes", new Class [] {java.lang.String.class});
            _methodParamMap.put(_getQualAttributesMethod, new String [] { "kraQuality" });
            _authenticateAppraiseeMethod = com.defiance.ideal.qpd.appraisee.dao.AppraiseeDAO.class.getMethod("authenticateAppraisee", new Class [] {java.lang.String.class, int.class, int.class});
            _methodParamMap.put(_authenticateAppraiseeMethod, new String [] { "empNum", "appraiseeQuarter", "appraiseeYear" });
            _updateAppraiseeStatusMethod = com.defiance.ideal.qpd.appraisee.dao.AppraiseeDAO.class.getMethod("updateAppraiseeStatus", new Class [] {int.class, int.class, int.class});
            _methodParamMap.put(_updateAppraiseeStatusMethod, new String [] { "appraiseeId", "quarter", "quarterYear" });
            _updateAppraiseeCommentsMethod = com.defiance.ideal.qpd.appraisee.dao.AppraiseeDAO.class.getMethod("updateAppraiseeComments", new Class [] {java.util.ArrayList.class, java.lang.String.class});
            _methodParamMap.put(_updateAppraiseeCommentsMethod, new String [] { "appraiseeComments", "empNum" });
            _getAppraiseeSBUMethod = com.defiance.ideal.qpd.appraisee.dao.AppraiseeDAO.class.getMethod("getAppraiseeSBU", new Class [] {int.class});
            _methodParamMap.put(_getAppraiseeSBUMethod, new String [] { "deptId" });
            _getAchievementsDataMethod = com.defiance.ideal.qpd.appraisee.dao.AppraiseeDAO.class.getMethod("getAchievementsData", new Class [] {int.class, int.class});
            _methodParamMap.put(_getAchievementsDataMethod, new String [] { "appraiseeId", "appraisalYear" });
            _getReviewerNameMethod = com.defiance.ideal.qpd.appraisee.dao.AppraiseeDAO.class.getMethod("getReviewerName", new Class [] {int.class});
            _methodParamMap.put(_getReviewerNameMethod, new String [] { "reviewerId" });
            _getGoalDataMethod = com.defiance.ideal.qpd.appraisee.dao.AppraiseeDAO.class.getMethod("getGoalData", new Class [] {int.class, int.class});
            _methodParamMap.put(_getGoalDataMethod, new String [] { "appraiseeId", "appraisalYear" });
            _getAppraiserNameMethod = com.defiance.ideal.qpd.appraisee.dao.AppraiseeDAO.class.getMethod("getAppraiserName", new Class [] {int.class});
            _methodParamMap.put(_getAppraiserNameMethod, new String [] { "appraiserId" });
            _getKraDataMethod = com.defiance.ideal.qpd.appraisee.dao.AppraiseeDAO.class.getMethod("getKraData", new Class [] {int.class, int.class, int.class, java.lang.String.class, int.class});
            _methodParamMap.put(_getKraDataMethod, new String [] { "bandId", "appraisalYear", "appraisalQuarter", "appraiseeId", "departmentId" });
            _insertAppraiseeCommentsMethod = com.defiance.ideal.qpd.appraisee.dao.AppraiseeDAO.class.getMethod("insertAppraiseeComments", new Class [] {java.util.ArrayList.class, java.lang.String.class});
            _methodParamMap.put(_insertAppraiseeCommentsMethod, new String [] { "appraiseeComments", "empNum" });
            _getCustomerAttributesMethod = com.defiance.ideal.qpd.appraisee.dao.AppraiseeDAO.class.getMethod("getCustomerAttributes", new Class [] {java.lang.String.class});
            _methodParamMap.put(_getCustomerAttributesMethod, new String [] { "kraCustomer" });
            _getDevNeedsDataMethod = com.defiance.ideal.qpd.appraisee.dao.AppraiseeDAO.class.getMethod("getDevNeedsData", new Class [] {int.class, int.class});
            _methodParamMap.put(_getDevNeedsDataMethod, new String [] { "appraiseeId", "appraisalYear" });
            _insertOrUpdateAppraiseeDataMethod = com.defiance.ideal.qpd.appraisee.dao.AppraiseeDAO.class.getMethod("insertOrUpdateAppraiseeData", new Class [] {com.defiance.ideal.qpd.appraisee.dto.MyAppraisalFormDTO.class, int.class});
            _methodParamMap.put(_insertOrUpdateAppraiseeDataMethod, new String [] { "formData", "appraiseeId" });
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
    public AppraiseeDAOBean(ControlBeanContext context, String id, PropertyMap props)
    {
        this(context, id, props, com.defiance.ideal.qpd.appraisee.dao.AppraiseeDAO.class);
    }
    
    /**
    * This is the public null-arg constructor for this ControlBean.  If a control id
    * is not provided, a unique value will be auto-generated.
    */
    public AppraiseeDAOBean()
    {
        this(null, null, null);
    }
    
    /**
    * This is the protected version that is used by any ControlBean subclass
    */
    protected AppraiseeDAOBean(ControlBeanContext context, String id, PropertyMap props, Class controlClass)
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
    * Implements com.defiance.ideal.qpd.appraisee.dao.AppraiseeDAO.getDevlopAttributes
    */
    public  com.defiance.ideal.qpd.appraisee.dto.MyAppraisalFormDTO[] getDevlopAttributes(java.lang.String kraDevlop) 
    {
        Object [] __bc_argArray = new Object[] { kraDevlop };
        Throwable __bc_thrown = null;
        com.defiance.ideal.qpd.appraisee.dao.AppraiseeDAO __bc_target = (com.defiance.ideal.qpd.appraisee.dao.AppraiseeDAO)ensureControl();
        com.defiance.ideal.qpd.appraisee.dto.MyAppraisalFormDTO[] __bc_retval = null;
        
        try
        {
            preInvoke(_getDevlopAttributesMethod, __bc_argArray);
            
            __bc_retval =
            __bc_target.getDevlopAttributes(kraDevlop)
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
            postInvoke(_getDevlopAttributesMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
        return __bc_retval;
    }
    
    /**
    * Implements com.defiance.ideal.qpd.appraisee.dao.AppraiseeDAO.getCostAttributes
    */
    public  com.defiance.ideal.qpd.appraisee.dto.MyAppraisalFormDTO[] getCostAttributes(java.lang.String kraCost) 
    {
        Object [] __bc_argArray = new Object[] { kraCost };
        Throwable __bc_thrown = null;
        com.defiance.ideal.qpd.appraisee.dao.AppraiseeDAO __bc_target = (com.defiance.ideal.qpd.appraisee.dao.AppraiseeDAO)ensureControl();
        com.defiance.ideal.qpd.appraisee.dto.MyAppraisalFormDTO[] __bc_retval = null;
        
        try
        {
            preInvoke(_getCostAttributesMethod, __bc_argArray);
            
            __bc_retval =
            __bc_target.getCostAttributes(kraCost)
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
            postInvoke(_getCostAttributesMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
        return __bc_retval;
    }
    
    /**
    * Implements com.defiance.ideal.qpd.appraisee.dao.AppraiseeDAO.checkAppraiseeComments
    */
    public  com.defiance.ideal.qpd.appraisee.dto.AppraiseeDetailsDTO checkAppraiseeComments(int appraiseeId) 
    {
        Object [] __bc_argArray = new Object[] { appraiseeId };
        Throwable __bc_thrown = null;
        com.defiance.ideal.qpd.appraisee.dao.AppraiseeDAO __bc_target = (com.defiance.ideal.qpd.appraisee.dao.AppraiseeDAO)ensureControl();
        com.defiance.ideal.qpd.appraisee.dto.AppraiseeDetailsDTO __bc_retval = null;
        
        try
        {
            preInvoke(_checkAppraiseeCommentsMethod, __bc_argArray);
            
            __bc_retval =
            __bc_target.checkAppraiseeComments(appraiseeId)
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
            postInvoke(_checkAppraiseeCommentsMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
        return __bc_retval;
    }
    
    /**
    * Implements com.defiance.ideal.qpd.appraisee.dao.AppraiseeDAO.getQualAttributes
    */
    public  com.defiance.ideal.qpd.appraisee.dto.MyAppraisalFormDTO[] getQualAttributes(java.lang.String kraQuality) 
    {
        Object [] __bc_argArray = new Object[] { kraQuality };
        Throwable __bc_thrown = null;
        com.defiance.ideal.qpd.appraisee.dao.AppraiseeDAO __bc_target = (com.defiance.ideal.qpd.appraisee.dao.AppraiseeDAO)ensureControl();
        com.defiance.ideal.qpd.appraisee.dto.MyAppraisalFormDTO[] __bc_retval = null;
        
        try
        {
            preInvoke(_getQualAttributesMethod, __bc_argArray);
            
            __bc_retval =
            __bc_target.getQualAttributes(kraQuality)
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
            postInvoke(_getQualAttributesMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
        return __bc_retval;
    }
    
    /**
    * Implements com.defiance.ideal.qpd.appraisee.dao.AppraiseeDAO.authenticateAppraisee
    */
    public  com.defiance.ideal.qpd.appraisee.dto.AppraiseeDetailsDTO authenticateAppraisee(java.lang.String empNum, int appraiseeQuarter, int appraiseeYear) throws java.lang.Exception
    {
        Object [] __bc_argArray = new Object[] { empNum, appraiseeQuarter, appraiseeYear };
        Throwable __bc_thrown = null;
        com.defiance.ideal.qpd.appraisee.dao.AppraiseeDAO __bc_target = (com.defiance.ideal.qpd.appraisee.dao.AppraiseeDAO)ensureControl();
        com.defiance.ideal.qpd.appraisee.dto.AppraiseeDetailsDTO __bc_retval = null;
        
        try
        {
            preInvoke(_authenticateAppraiseeMethod, __bc_argArray);
            
            __bc_retval =
            __bc_target.authenticateAppraisee(empNum, appraiseeQuarter, appraiseeYear)
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
            postInvoke(_authenticateAppraiseeMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
        return __bc_retval;
    }
    
    /**
    * Implements com.defiance.ideal.qpd.appraisee.dao.AppraiseeDAO.updateAppraiseeStatus
    */
    public  void updateAppraiseeStatus(int appraiseeId, int quarter, int quarterYear) 
    {
        Object [] __bc_argArray = new Object[] { appraiseeId, quarter, quarterYear };
        Throwable __bc_thrown = null;
        com.defiance.ideal.qpd.appraisee.dao.AppraiseeDAO __bc_target = (com.defiance.ideal.qpd.appraisee.dao.AppraiseeDAO)ensureControl();
        
        try
        {
            preInvoke(_updateAppraiseeStatusMethod, __bc_argArray);
            
            __bc_target.updateAppraiseeStatus(appraiseeId, quarter, quarterYear)
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
    * Implements com.defiance.ideal.qpd.appraisee.dao.AppraiseeDAO.updateAppraiseeComments
    */
    public  void updateAppraiseeComments(java.util.ArrayList<java.lang.String> appraiseeComments, java.lang.String empNum) 
    {
        Object [] __bc_argArray = new Object[] { appraiseeComments, empNum };
        Throwable __bc_thrown = null;
        com.defiance.ideal.qpd.appraisee.dao.AppraiseeDAO __bc_target = (com.defiance.ideal.qpd.appraisee.dao.AppraiseeDAO)ensureControl();
        
        try
        {
            preInvoke(_updateAppraiseeCommentsMethod, __bc_argArray);
            
            __bc_target.updateAppraiseeComments(appraiseeComments, empNum)
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
            postInvoke(_updateAppraiseeCommentsMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
    }
    
    /**
    * Implements com.defiance.ideal.qpd.appraisee.dao.AppraiseeDAO.getAppraiseeSBU
    */
    public  com.defiance.ideal.qpd.appraisee.dto.AppraiseeDetailsDTO getAppraiseeSBU(int deptId) 
    {
        Object [] __bc_argArray = new Object[] { deptId };
        Throwable __bc_thrown = null;
        com.defiance.ideal.qpd.appraisee.dao.AppraiseeDAO __bc_target = (com.defiance.ideal.qpd.appraisee.dao.AppraiseeDAO)ensureControl();
        com.defiance.ideal.qpd.appraisee.dto.AppraiseeDetailsDTO __bc_retval = null;
        
        try
        {
            preInvoke(_getAppraiseeSBUMethod, __bc_argArray);
            
            __bc_retval =
            __bc_target.getAppraiseeSBU(deptId)
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
            postInvoke(_getAppraiseeSBUMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
        return __bc_retval;
    }
    
    /**
    * Implements com.defiance.ideal.qpd.appraisee.dao.AppraiseeDAO.getAchievementsData
    */
    public  com.defiance.ideal.qpd.appraisee.dto.MyAppraisalFormDTO[] getAchievementsData(int appraiseeId, int appraisalYear) 
    {
        Object [] __bc_argArray = new Object[] { appraiseeId, appraisalYear };
        Throwable __bc_thrown = null;
        com.defiance.ideal.qpd.appraisee.dao.AppraiseeDAO __bc_target = (com.defiance.ideal.qpd.appraisee.dao.AppraiseeDAO)ensureControl();
        com.defiance.ideal.qpd.appraisee.dto.MyAppraisalFormDTO[] __bc_retval = null;
        
        try
        {
            preInvoke(_getAchievementsDataMethod, __bc_argArray);
            
            __bc_retval =
            __bc_target.getAchievementsData(appraiseeId, appraisalYear)
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
    * Implements com.defiance.ideal.qpd.appraisee.dao.AppraiseeDAO.getReviewerName
    */
    public  com.defiance.ideal.qpd.appraisee.dto.AppraiseeDetailsDTO getReviewerName(int reviewerId) 
    {
        Object [] __bc_argArray = new Object[] { reviewerId };
        Throwable __bc_thrown = null;
        com.defiance.ideal.qpd.appraisee.dao.AppraiseeDAO __bc_target = (com.defiance.ideal.qpd.appraisee.dao.AppraiseeDAO)ensureControl();
        com.defiance.ideal.qpd.appraisee.dto.AppraiseeDetailsDTO __bc_retval = null;
        
        try
        {
            preInvoke(_getReviewerNameMethod, __bc_argArray);
            
            __bc_retval =
            __bc_target.getReviewerName(reviewerId)
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
            postInvoke(_getReviewerNameMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
        return __bc_retval;
    }
    
    /**
    * Implements com.defiance.ideal.qpd.appraisee.dao.AppraiseeDAO.getGoalData
    */
    public  com.defiance.ideal.qpd.appraisee.dto.MyAppraisalFormDTO[] getGoalData(int appraiseeId, int appraisalYear) 
    {
        Object [] __bc_argArray = new Object[] { appraiseeId, appraisalYear };
        Throwable __bc_thrown = null;
        com.defiance.ideal.qpd.appraisee.dao.AppraiseeDAO __bc_target = (com.defiance.ideal.qpd.appraisee.dao.AppraiseeDAO)ensureControl();
        com.defiance.ideal.qpd.appraisee.dto.MyAppraisalFormDTO[] __bc_retval = null;
        
        try
        {
            preInvoke(_getGoalDataMethod, __bc_argArray);
            
            __bc_retval =
            __bc_target.getGoalData(appraiseeId, appraisalYear)
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
    * Implements com.defiance.ideal.qpd.appraisee.dao.AppraiseeDAO.getAppraiserName
    */
    public  com.defiance.ideal.qpd.appraisee.dto.AppraiseeDetailsDTO getAppraiserName(int appraiserId) 
    {
        Object [] __bc_argArray = new Object[] { appraiserId };
        Throwable __bc_thrown = null;
        com.defiance.ideal.qpd.appraisee.dao.AppraiseeDAO __bc_target = (com.defiance.ideal.qpd.appraisee.dao.AppraiseeDAO)ensureControl();
        com.defiance.ideal.qpd.appraisee.dto.AppraiseeDetailsDTO __bc_retval = null;
        
        try
        {
            preInvoke(_getAppraiserNameMethod, __bc_argArray);
            
            __bc_retval =
            __bc_target.getAppraiserName(appraiserId)
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
            postInvoke(_getAppraiserNameMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
        return __bc_retval;
    }
    
    /**
    * Implements com.defiance.ideal.qpd.appraisee.dao.AppraiseeDAO.getKraData
    */
    public  com.defiance.ideal.qpd.appraisee.dto.MyAppraisalFormDTO[] getKraData(int bandId, int appraisalYear, int appraisalQuarter, java.lang.String appraiseeId, int departmentId) 
    {
        Object [] __bc_argArray = new Object[] { bandId, appraisalYear, appraisalQuarter, appraiseeId, departmentId };
        Throwable __bc_thrown = null;
        com.defiance.ideal.qpd.appraisee.dao.AppraiseeDAO __bc_target = (com.defiance.ideal.qpd.appraisee.dao.AppraiseeDAO)ensureControl();
        com.defiance.ideal.qpd.appraisee.dto.MyAppraisalFormDTO[] __bc_retval = null;
        
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
    * Implements com.defiance.ideal.qpd.appraisee.dao.AppraiseeDAO.insertAppraiseeComments
    */
    public  void insertAppraiseeComments(java.util.ArrayList<java.lang.String> appraiseeComments, java.lang.String empNum) 
    {
        Object [] __bc_argArray = new Object[] { appraiseeComments, empNum };
        Throwable __bc_thrown = null;
        com.defiance.ideal.qpd.appraisee.dao.AppraiseeDAO __bc_target = (com.defiance.ideal.qpd.appraisee.dao.AppraiseeDAO)ensureControl();
        
        try
        {
            preInvoke(_insertAppraiseeCommentsMethod, __bc_argArray);
            
            __bc_target.insertAppraiseeComments(appraiseeComments, empNum)
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
            postInvoke(_insertAppraiseeCommentsMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
    }
    
    /**
    * Implements com.defiance.ideal.qpd.appraisee.dao.AppraiseeDAO.getCustomerAttributes
    */
    public  com.defiance.ideal.qpd.appraisee.dto.MyAppraisalFormDTO[] getCustomerAttributes(java.lang.String kraCustomer) 
    {
        Object [] __bc_argArray = new Object[] { kraCustomer };
        Throwable __bc_thrown = null;
        com.defiance.ideal.qpd.appraisee.dao.AppraiseeDAO __bc_target = (com.defiance.ideal.qpd.appraisee.dao.AppraiseeDAO)ensureControl();
        com.defiance.ideal.qpd.appraisee.dto.MyAppraisalFormDTO[] __bc_retval = null;
        
        try
        {
            preInvoke(_getCustomerAttributesMethod, __bc_argArray);
            
            __bc_retval =
            __bc_target.getCustomerAttributes(kraCustomer)
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
            postInvoke(_getCustomerAttributesMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
        return __bc_retval;
    }
    
    /**
    * Implements com.defiance.ideal.qpd.appraisee.dao.AppraiseeDAO.getDevNeedsData
    */
    public  com.defiance.ideal.qpd.appraisee.dto.MyAppraisalFormDTO[] getDevNeedsData(int appraiseeId, int appraisalYear) 
    {
        Object [] __bc_argArray = new Object[] { appraiseeId, appraisalYear };
        Throwable __bc_thrown = null;
        com.defiance.ideal.qpd.appraisee.dao.AppraiseeDAO __bc_target = (com.defiance.ideal.qpd.appraisee.dao.AppraiseeDAO)ensureControl();
        com.defiance.ideal.qpd.appraisee.dto.MyAppraisalFormDTO[] __bc_retval = null;
        
        try
        {
            preInvoke(_getDevNeedsDataMethod, __bc_argArray);
            
            __bc_retval =
            __bc_target.getDevNeedsData(appraiseeId, appraisalYear)
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
            postInvoke(_getDevNeedsDataMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
        return __bc_retval;
    }
    
    /**
    * Implements com.defiance.ideal.qpd.appraisee.dao.AppraiseeDAO.insertOrUpdateAppraiseeData
    */
    public  void insertOrUpdateAppraiseeData(com.defiance.ideal.qpd.appraisee.dto.MyAppraisalFormDTO formData, int appraiseeId) 
    {
        Object [] __bc_argArray = new Object[] { formData, appraiseeId };
        Throwable __bc_thrown = null;
        com.defiance.ideal.qpd.appraisee.dao.AppraiseeDAO __bc_target = (com.defiance.ideal.qpd.appraisee.dao.AppraiseeDAO)ensureControl();
        
        try
        {
            preInvoke(_insertOrUpdateAppraiseeDataMethod, __bc_argArray);
            
            __bc_target.insertOrUpdateAppraiseeData(formData, appraiseeId)
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
            postInvoke(_insertOrUpdateAppraiseeDataMethod, __bc_argArray, __bc_rv, __bc_thrown);
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
