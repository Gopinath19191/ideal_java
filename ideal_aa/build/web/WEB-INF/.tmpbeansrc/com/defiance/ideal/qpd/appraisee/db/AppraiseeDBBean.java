
package com.defiance.ideal.qpd.appraisee.db;

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
public class AppraiseeDBBean
extends com.defiance.ideal.shared.DBConnectivityBean
implements com.defiance.ideal.qpd.appraisee.db.AppraiseeDB
{
    static final Method _updateAppraiseeData1Method;
    static final Method _costAttribsMethod;
    static final Method _appraiseeCommentsInsertMethod;
    static final Method _devlopAttribsMethod;
    static final Method _updateAppraiseeData0Method;
    static final Method _insertOrUpdateGoalsMethod;
    static final Method _appraiseeCommentsUpdateMethod;
    static final Method _chkAppraiseeCommentsMethod;
    static final Method _authenAppraiseeMethod;
    static final Method _insertAppraiseeDataMethod;
    static final Method _getKraDataMethod;
    static final Method _getGoalDataMethod;
    static final Method _appraiseeSBUMethod;
    static final Method _insertOrUpdateDevNeedsMethod;
    static final Method _getDevNeedsDataMethod;
    static final Method _setDeleteAchievementsMethod;
    static final Method _reviewerNameMethod;
    static final Method _insertOrUpdateAchievementsMethod;
    static final Method _setDeleteGoalMethod;
    static final Method _updateAppraiseeStatusMethod;
    static final Method _customerAttribsMethod;
    static final Method _qualAttribsMethod;
    static final Method _getAchievementsDataMethod;
    static final Method _appraiserNameMethod;
    static final Method _setDeleteDevNeedsMethod;
    
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
            _updateAppraiseeData1Method = com.defiance.ideal.qpd.appraisee.db.AppraiseeDB.class.getMethod("updateAppraiseeData", new Class [] {int.class, java.lang.String.class, int.class, int.class});
            _methodParamMap.put(_updateAppraiseeData1Method, new String [] { "kraQtrId", "selfComments", "appraiseeId", "qpdKraId" });
            _costAttribsMethod = com.defiance.ideal.qpd.appraisee.db.AppraiseeDB.class.getMethod("costAttribs", new Class [] {java.lang.String.class});
            _methodParamMap.put(_costAttribsMethod, new String [] { "kraCost" });
            _appraiseeCommentsInsertMethod = com.defiance.ideal.qpd.appraisee.db.AppraiseeDB.class.getMethod("appraiseeCommentsInsert", new Class [] {int.class, java.lang.String.class});
            _methodParamMap.put(_appraiseeCommentsInsertMethod, new String [] { "appraiseeId", "comment" });
            _devlopAttribsMethod = com.defiance.ideal.qpd.appraisee.db.AppraiseeDB.class.getMethod("devlopAttribs", new Class [] {java.lang.String.class});
            _methodParamMap.put(_devlopAttribsMethod, new String [] { "kraDevlop" });
            _updateAppraiseeData0Method = com.defiance.ideal.qpd.appraisee.db.AppraiseeDB.class.getMethod("updateAppraiseeData", new Class [] {com.defiance.ideal.qpd.appraisee.dto.MyAppraisalFormDTO.class, int.class});
            _methodParamMap.put(_updateAppraiseeData0Method, new String [] { "formData", "appraiseeId" });
            _insertOrUpdateGoalsMethod = com.defiance.ideal.qpd.appraisee.db.AppraiseeDB.class.getMethod("insertOrUpdateGoals", new Class [] {int.class, java.lang.String.class, int.class, int.class, int.class});
            _methodParamMap.put(_insertOrUpdateGoalsMethod, new String [] { "goalId", "goalData", "appraisalYear", "appraiseeId", "deleted" });
            _appraiseeCommentsUpdateMethod = com.defiance.ideal.qpd.appraisee.db.AppraiseeDB.class.getMethod("appraiseeCommentsUpdate", new Class [] {int.class, java.lang.String.class, int.class});
            _methodParamMap.put(_appraiseeCommentsUpdateMethod, new String [] { "appraiseeId", "comment", "kraQtrId" });
            _chkAppraiseeCommentsMethod = com.defiance.ideal.qpd.appraisee.db.AppraiseeDB.class.getMethod("chkAppraiseeComments", new Class [] {int.class});
            _methodParamMap.put(_chkAppraiseeCommentsMethod, new String [] { "appraiseeId" });
            _authenAppraiseeMethod = com.defiance.ideal.qpd.appraisee.db.AppraiseeDB.class.getMethod("authenAppraisee", new Class [] {java.lang.String.class, int.class});
            _methodParamMap.put(_authenAppraiseeMethod, new String [] { "empNum", "appraiseeYear" });
            _insertAppraiseeDataMethod = com.defiance.ideal.qpd.appraisee.db.AppraiseeDB.class.getMethod("insertAppraiseeData", new Class [] {int.class, java.lang.String.class, int.class});
            _methodParamMap.put(_insertAppraiseeDataMethod, new String [] { "kraQtrId", "selfComments", "appraiseeId" });
            _getKraDataMethod = com.defiance.ideal.qpd.appraisee.db.AppraiseeDB.class.getMethod("getKraData", new Class [] {int.class, int.class, java.lang.String.class, int.class, int.class});
            _methodParamMap.put(_getKraDataMethod, new String [] { "bandId", "appraisalYear", "appraiseeId", "departmentId", "triggerStatus" });
            _getGoalDataMethod = com.defiance.ideal.qpd.appraisee.db.AppraiseeDB.class.getMethod("getGoalData", new Class [] {int.class, int.class});
            _methodParamMap.put(_getGoalDataMethod, new String [] { "appraiseeId", "appraisalYear" });
            _appraiseeSBUMethod = com.defiance.ideal.qpd.appraisee.db.AppraiseeDB.class.getMethod("appraiseeSBU", new Class [] {int.class});
            _methodParamMap.put(_appraiseeSBUMethod, new String [] { "deptId" });
            _insertOrUpdateDevNeedsMethod = com.defiance.ideal.qpd.appraisee.db.AppraiseeDB.class.getMethod("insertOrUpdateDevNeeds", new Class [] {int.class, java.lang.String.class, int.class, int.class, int.class});
            _methodParamMap.put(_insertOrUpdateDevNeedsMethod, new String [] { "needsId", "developmentNeeds", "appraisalYear", "appraiseeId", "deleted" });
            _getDevNeedsDataMethod = com.defiance.ideal.qpd.appraisee.db.AppraiseeDB.class.getMethod("getDevNeedsData", new Class [] {int.class, int.class});
            _methodParamMap.put(_getDevNeedsDataMethod, new String [] { "appraiseeId", "appraisalYear" });
            _setDeleteAchievementsMethod = com.defiance.ideal.qpd.appraisee.db.AppraiseeDB.class.getMethod("setDeleteAchievements", new Class [] {int.class, int.class});
            _methodParamMap.put(_setDeleteAchievementsMethod, new String [] { "achievementId", "deleted" });
            _reviewerNameMethod = com.defiance.ideal.qpd.appraisee.db.AppraiseeDB.class.getMethod("reviewerName", new Class [] {int.class});
            _methodParamMap.put(_reviewerNameMethod, new String [] { "reviewerId" });
            _insertOrUpdateAchievementsMethod = com.defiance.ideal.qpd.appraisee.db.AppraiseeDB.class.getMethod("insertOrUpdateAchievements", new Class [] {int.class, java.lang.String.class, int.class, int.class, int.class});
            _methodParamMap.put(_insertOrUpdateAchievementsMethod, new String [] { "achievementId", "kra", "appraisalYear", "appraiseeId", "deleted" });
            _setDeleteGoalMethod = com.defiance.ideal.qpd.appraisee.db.AppraiseeDB.class.getMethod("setDeleteGoal", new Class [] {int.class, int.class});
            _methodParamMap.put(_setDeleteGoalMethod, new String [] { "goalId", "deletedStatus" });
            _updateAppraiseeStatusMethod = com.defiance.ideal.qpd.appraisee.db.AppraiseeDB.class.getMethod("updateAppraiseeStatus", new Class [] {int.class, int.class, int.class});
            _methodParamMap.put(_updateAppraiseeStatusMethod, new String [] { "appraiseeId", "quarterYear", "submitStatus" });
            _customerAttribsMethod = com.defiance.ideal.qpd.appraisee.db.AppraiseeDB.class.getMethod("customerAttribs", new Class [] {java.lang.String.class});
            _methodParamMap.put(_customerAttribsMethod, new String [] { "kraCustomer" });
            _qualAttribsMethod = com.defiance.ideal.qpd.appraisee.db.AppraiseeDB.class.getMethod("qualAttribs", new Class [] {java.lang.String.class});
            _methodParamMap.put(_qualAttribsMethod, new String [] { "kraQuality" });
            _getAchievementsDataMethod = com.defiance.ideal.qpd.appraisee.db.AppraiseeDB.class.getMethod("getAchievementsData", new Class [] {int.class, int.class});
            _methodParamMap.put(_getAchievementsDataMethod, new String [] { "appraiseeId", "appraisalYear" });
            _appraiserNameMethod = com.defiance.ideal.qpd.appraisee.db.AppraiseeDB.class.getMethod("appraiserName", new Class [] {int.class});
            _methodParamMap.put(_appraiserNameMethod, new String [] { "appraiserId" });
            _setDeleteDevNeedsMethod = com.defiance.ideal.qpd.appraisee.db.AppraiseeDB.class.getMethod("setDeleteDevNeeds", new Class [] {int.class, int.class});
            _methodParamMap.put(_setDeleteDevNeedsMethod, new String [] { "needsId", "deletedStatus" });
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
    public AppraiseeDBBean(ControlBeanContext context, String id, PropertyMap props)
    {
        this(context, id, props, com.defiance.ideal.qpd.appraisee.db.AppraiseeDB.class);
    }
    
    /**
    * This is the public null-arg constructor for this ControlBean.  If a control id
    * is not provided, a unique value will be auto-generated.
    */
    public AppraiseeDBBean()
    {
        this(null, null, null);
    }
    
    /**
    * This is the protected version that is used by any ControlBean subclass
    */
    protected AppraiseeDBBean(ControlBeanContext context, String id, PropertyMap props, Class controlClass)
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
    * Implements com.defiance.ideal.qpd.appraisee.db.AppraiseeDB.updateAppraiseeData
    */
    public  void updateAppraiseeData(int kraQtrId, java.lang.String selfComments, int appraiseeId, int qpdKraId) 
    {
        Object [] __bc_argArray = new Object[] { kraQtrId, selfComments, appraiseeId, qpdKraId };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        
        try
        {
            preInvoke(_updateAppraiseeData1Method, __bc_argArray);
            
            __bc_target.invoke(_updateAppraiseeData1Method, __bc_argArray)
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
            postInvoke(_updateAppraiseeData1Method, __bc_argArray, __bc_rv, __bc_thrown);
        }
    }
    
    /**
    * Implements com.defiance.ideal.qpd.appraisee.db.AppraiseeDB.costAttribs
    */
    public  com.defiance.ideal.qpd.appraisee.dto.MyAppraisalFormDTO[] costAttribs(java.lang.String kraCost) 
    {
        Object [] __bc_argArray = new Object[] { kraCost };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        com.defiance.ideal.qpd.appraisee.dto.MyAppraisalFormDTO[] __bc_retval = null;
        
        try
        {
            preInvoke(_costAttribsMethod, __bc_argArray);
            
            __bc_retval =
            (com.defiance.ideal.qpd.appraisee.dto.MyAppraisalFormDTO[])
            __bc_target.invoke(_costAttribsMethod, __bc_argArray)
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
            postInvoke(_costAttribsMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
        return __bc_retval;
    }
    
    /**
    * Implements com.defiance.ideal.qpd.appraisee.db.AppraiseeDB.appraiseeCommentsInsert
    */
    public  void appraiseeCommentsInsert(int appraiseeId, java.lang.String comment) 
    {
        Object [] __bc_argArray = new Object[] { appraiseeId, comment };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        
        try
        {
            preInvoke(_appraiseeCommentsInsertMethod, __bc_argArray);
            
            __bc_target.invoke(_appraiseeCommentsInsertMethod, __bc_argArray)
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
            postInvoke(_appraiseeCommentsInsertMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
    }
    
    /**
    * Implements com.defiance.ideal.qpd.appraisee.db.AppraiseeDB.devlopAttribs
    */
    public  com.defiance.ideal.qpd.appraisee.dto.MyAppraisalFormDTO[] devlopAttribs(java.lang.String kraDevlop) 
    {
        Object [] __bc_argArray = new Object[] { kraDevlop };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        com.defiance.ideal.qpd.appraisee.dto.MyAppraisalFormDTO[] __bc_retval = null;
        
        try
        {
            preInvoke(_devlopAttribsMethod, __bc_argArray);
            
            __bc_retval =
            (com.defiance.ideal.qpd.appraisee.dto.MyAppraisalFormDTO[])
            __bc_target.invoke(_devlopAttribsMethod, __bc_argArray)
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
            postInvoke(_devlopAttribsMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
        return __bc_retval;
    }
    
    /**
    * Implements com.defiance.ideal.qpd.appraisee.db.AppraiseeDB.updateAppraiseeData
    */
    public  void updateAppraiseeData(com.defiance.ideal.qpd.appraisee.dto.MyAppraisalFormDTO formData, int appraiseeId) 
    {
        Object [] __bc_argArray = new Object[] { formData, appraiseeId };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        
        try
        {
            preInvoke(_updateAppraiseeData0Method, __bc_argArray);
            
            __bc_target.invoke(_updateAppraiseeData0Method, __bc_argArray)
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
            postInvoke(_updateAppraiseeData0Method, __bc_argArray, __bc_rv, __bc_thrown);
        }
    }
    
    /**
    * Implements com.defiance.ideal.qpd.appraisee.db.AppraiseeDB.insertOrUpdateGoals
    */
    public  void insertOrUpdateGoals(int goalId, java.lang.String goalData, int appraisalYear, int appraiseeId, int deleted) 
    {
        Object [] __bc_argArray = new Object[] { goalId, goalData, appraisalYear, appraiseeId, deleted };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        
        try
        {
            preInvoke(_insertOrUpdateGoalsMethod, __bc_argArray);
            
            __bc_target.invoke(_insertOrUpdateGoalsMethod, __bc_argArray)
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
            postInvoke(_insertOrUpdateGoalsMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
    }
    
    /**
    * Implements com.defiance.ideal.qpd.appraisee.db.AppraiseeDB.appraiseeCommentsUpdate
    */
    public  void appraiseeCommentsUpdate(int appraiseeId, java.lang.String comment, int kraQtrId) 
    {
        Object [] __bc_argArray = new Object[] { appraiseeId, comment, kraQtrId };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        
        try
        {
            preInvoke(_appraiseeCommentsUpdateMethod, __bc_argArray);
            
            __bc_target.invoke(_appraiseeCommentsUpdateMethod, __bc_argArray)
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
            postInvoke(_appraiseeCommentsUpdateMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
    }
    
    /**
    * Implements com.defiance.ideal.qpd.appraisee.db.AppraiseeDB.chkAppraiseeComments
    */
    public  com.defiance.ideal.qpd.appraisee.dto.AppraiseeDetailsDTO chkAppraiseeComments(int appraiseeId) 
    {
        Object [] __bc_argArray = new Object[] { appraiseeId };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        com.defiance.ideal.qpd.appraisee.dto.AppraiseeDetailsDTO __bc_retval = null;
        
        try
        {
            preInvoke(_chkAppraiseeCommentsMethod, __bc_argArray);
            
            __bc_retval =
            (com.defiance.ideal.qpd.appraisee.dto.AppraiseeDetailsDTO)
            __bc_target.invoke(_chkAppraiseeCommentsMethod, __bc_argArray)
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
            postInvoke(_chkAppraiseeCommentsMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
        return __bc_retval;
    }
    
    /**
    * Implements com.defiance.ideal.qpd.appraisee.db.AppraiseeDB.authenAppraisee
    */
    public  com.defiance.ideal.qpd.appraisee.dto.AppraiseeDetailsDTO authenAppraisee(java.lang.String empNum, int appraiseeYear) 
    {
        Object [] __bc_argArray = new Object[] { empNum, appraiseeYear };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        com.defiance.ideal.qpd.appraisee.dto.AppraiseeDetailsDTO __bc_retval = null;
        
        try
        {
            preInvoke(_authenAppraiseeMethod, __bc_argArray);
            
            __bc_retval =
            (com.defiance.ideal.qpd.appraisee.dto.AppraiseeDetailsDTO)
            __bc_target.invoke(_authenAppraiseeMethod, __bc_argArray)
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
            postInvoke(_authenAppraiseeMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
        return __bc_retval;
    }
    
    /**
    * Implements com.defiance.ideal.qpd.appraisee.db.AppraiseeDB.insertAppraiseeData
    */
    public  void insertAppraiseeData(int kraQtrId, java.lang.String selfComments, int appraiseeId) 
    {
        Object [] __bc_argArray = new Object[] { kraQtrId, selfComments, appraiseeId };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        
        try
        {
            preInvoke(_insertAppraiseeDataMethod, __bc_argArray);
            
            __bc_target.invoke(_insertAppraiseeDataMethod, __bc_argArray)
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
            postInvoke(_insertAppraiseeDataMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
    }
    
    /**
    * Implements com.defiance.ideal.qpd.appraisee.db.AppraiseeDB.getKraData
    */
    public  com.defiance.ideal.qpd.appraisee.dto.MyAppraisalFormDTO[] getKraData(int bandId, int appraisalYear, java.lang.String appraiseeId, int departmentId, int triggerStatus) 
    {
        Object [] __bc_argArray = new Object[] { bandId, appraisalYear, appraiseeId, departmentId, triggerStatus };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        com.defiance.ideal.qpd.appraisee.dto.MyAppraisalFormDTO[] __bc_retval = null;
        
        try
        {
            preInvoke(_getKraDataMethod, __bc_argArray);
            
            __bc_retval =
            (com.defiance.ideal.qpd.appraisee.dto.MyAppraisalFormDTO[])
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
    * Implements com.defiance.ideal.qpd.appraisee.db.AppraiseeDB.getGoalData
    */
    public  com.defiance.ideal.qpd.appraisee.dto.MyAppraisalFormDTO[] getGoalData(int appraiseeId, int appraisalYear) 
    {
        Object [] __bc_argArray = new Object[] { appraiseeId, appraisalYear };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        com.defiance.ideal.qpd.appraisee.dto.MyAppraisalFormDTO[] __bc_retval = null;
        
        try
        {
            preInvoke(_getGoalDataMethod, __bc_argArray);
            
            __bc_retval =
            (com.defiance.ideal.qpd.appraisee.dto.MyAppraisalFormDTO[])
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
    * Implements com.defiance.ideal.qpd.appraisee.db.AppraiseeDB.appraiseeSBU
    */
    public  com.defiance.ideal.qpd.appraisee.dto.AppraiseeDetailsDTO appraiseeSBU(int deptId) 
    {
        Object [] __bc_argArray = new Object[] { deptId };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        com.defiance.ideal.qpd.appraisee.dto.AppraiseeDetailsDTO __bc_retval = null;
        
        try
        {
            preInvoke(_appraiseeSBUMethod, __bc_argArray);
            
            __bc_retval =
            (com.defiance.ideal.qpd.appraisee.dto.AppraiseeDetailsDTO)
            __bc_target.invoke(_appraiseeSBUMethod, __bc_argArray)
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
            postInvoke(_appraiseeSBUMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
        return __bc_retval;
    }
    
    /**
    * Implements com.defiance.ideal.qpd.appraisee.db.AppraiseeDB.insertOrUpdateDevNeeds
    */
    public  void insertOrUpdateDevNeeds(int needsId, java.lang.String developmentNeeds, int appraisalYear, int appraiseeId, int deleted) 
    {
        Object [] __bc_argArray = new Object[] { needsId, developmentNeeds, appraisalYear, appraiseeId, deleted };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        
        try
        {
            preInvoke(_insertOrUpdateDevNeedsMethod, __bc_argArray);
            
            __bc_target.invoke(_insertOrUpdateDevNeedsMethod, __bc_argArray)
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
            postInvoke(_insertOrUpdateDevNeedsMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
    }
    
    /**
    * Implements com.defiance.ideal.qpd.appraisee.db.AppraiseeDB.getDevNeedsData
    */
    public  com.defiance.ideal.qpd.appraisee.dto.MyAppraisalFormDTO[] getDevNeedsData(int appraiseeId, int appraisalYear) 
    {
        Object [] __bc_argArray = new Object[] { appraiseeId, appraisalYear };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        com.defiance.ideal.qpd.appraisee.dto.MyAppraisalFormDTO[] __bc_retval = null;
        
        try
        {
            preInvoke(_getDevNeedsDataMethod, __bc_argArray);
            
            __bc_retval =
            (com.defiance.ideal.qpd.appraisee.dto.MyAppraisalFormDTO[])
            __bc_target.invoke(_getDevNeedsDataMethod, __bc_argArray)
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
    * Implements com.defiance.ideal.qpd.appraisee.db.AppraiseeDB.setDeleteAchievements
    */
    public  void setDeleteAchievements(int achievementId, int deleted) 
    {
        Object [] __bc_argArray = new Object[] { achievementId, deleted };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        
        try
        {
            preInvoke(_setDeleteAchievementsMethod, __bc_argArray);
            
            __bc_target.invoke(_setDeleteAchievementsMethod, __bc_argArray)
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
            postInvoke(_setDeleteAchievementsMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
    }
    
    /**
    * Implements com.defiance.ideal.qpd.appraisee.db.AppraiseeDB.reviewerName
    */
    public  com.defiance.ideal.qpd.appraisee.dto.AppraiseeDetailsDTO reviewerName(int reviewerId) 
    {
        Object [] __bc_argArray = new Object[] { reviewerId };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        com.defiance.ideal.qpd.appraisee.dto.AppraiseeDetailsDTO __bc_retval = null;
        
        try
        {
            preInvoke(_reviewerNameMethod, __bc_argArray);
            
            __bc_retval =
            (com.defiance.ideal.qpd.appraisee.dto.AppraiseeDetailsDTO)
            __bc_target.invoke(_reviewerNameMethod, __bc_argArray)
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
            postInvoke(_reviewerNameMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
        return __bc_retval;
    }
    
    /**
    * Implements com.defiance.ideal.qpd.appraisee.db.AppraiseeDB.insertOrUpdateAchievements
    */
    public  void insertOrUpdateAchievements(int achievementId, java.lang.String kra, int appraisalYear, int appraiseeId, int deleted) 
    {
        Object [] __bc_argArray = new Object[] { achievementId, kra, appraisalYear, appraiseeId, deleted };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        
        try
        {
            preInvoke(_insertOrUpdateAchievementsMethod, __bc_argArray);
            
            __bc_target.invoke(_insertOrUpdateAchievementsMethod, __bc_argArray)
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
            postInvoke(_insertOrUpdateAchievementsMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
    }
    
    /**
    * Implements com.defiance.ideal.qpd.appraisee.db.AppraiseeDB.setDeleteGoal
    */
    public  void setDeleteGoal(int goalId, int deletedStatus) 
    {
        Object [] __bc_argArray = new Object[] { goalId, deletedStatus };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        
        try
        {
            preInvoke(_setDeleteGoalMethod, __bc_argArray);
            
            __bc_target.invoke(_setDeleteGoalMethod, __bc_argArray)
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
            postInvoke(_setDeleteGoalMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
    }
    
    /**
    * Implements com.defiance.ideal.qpd.appraisee.db.AppraiseeDB.updateAppraiseeStatus
    */
    public  void updateAppraiseeStatus(int appraiseeId, int quarterYear, int submitStatus) 
    {
        Object [] __bc_argArray = new Object[] { appraiseeId, quarterYear, submitStatus };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        
        try
        {
            preInvoke(_updateAppraiseeStatusMethod, __bc_argArray);
            
            __bc_target.invoke(_updateAppraiseeStatusMethod, __bc_argArray)
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
    * Implements com.defiance.ideal.qpd.appraisee.db.AppraiseeDB.customerAttribs
    */
    public  com.defiance.ideal.qpd.appraisee.dto.MyAppraisalFormDTO[] customerAttribs(java.lang.String kraCustomer) 
    {
        Object [] __bc_argArray = new Object[] { kraCustomer };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        com.defiance.ideal.qpd.appraisee.dto.MyAppraisalFormDTO[] __bc_retval = null;
        
        try
        {
            preInvoke(_customerAttribsMethod, __bc_argArray);
            
            __bc_retval =
            (com.defiance.ideal.qpd.appraisee.dto.MyAppraisalFormDTO[])
            __bc_target.invoke(_customerAttribsMethod, __bc_argArray)
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
            postInvoke(_customerAttribsMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
        return __bc_retval;
    }
    
    /**
    * Implements com.defiance.ideal.qpd.appraisee.db.AppraiseeDB.qualAttribs
    */
    public  com.defiance.ideal.qpd.appraisee.dto.MyAppraisalFormDTO[] qualAttribs(java.lang.String kraQuality) 
    {
        Object [] __bc_argArray = new Object[] { kraQuality };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        com.defiance.ideal.qpd.appraisee.dto.MyAppraisalFormDTO[] __bc_retval = null;
        
        try
        {
            preInvoke(_qualAttribsMethod, __bc_argArray);
            
            __bc_retval =
            (com.defiance.ideal.qpd.appraisee.dto.MyAppraisalFormDTO[])
            __bc_target.invoke(_qualAttribsMethod, __bc_argArray)
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
            postInvoke(_qualAttribsMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
        return __bc_retval;
    }
    
    /**
    * Implements com.defiance.ideal.qpd.appraisee.db.AppraiseeDB.getAchievementsData
    */
    public  com.defiance.ideal.qpd.appraisee.dto.MyAppraisalFormDTO[] getAchievementsData(int appraiseeId, int appraisalYear) 
    {
        Object [] __bc_argArray = new Object[] { appraiseeId, appraisalYear };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        com.defiance.ideal.qpd.appraisee.dto.MyAppraisalFormDTO[] __bc_retval = null;
        
        try
        {
            preInvoke(_getAchievementsDataMethod, __bc_argArray);
            
            __bc_retval =
            (com.defiance.ideal.qpd.appraisee.dto.MyAppraisalFormDTO[])
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
    * Implements com.defiance.ideal.qpd.appraisee.db.AppraiseeDB.appraiserName
    */
    public  com.defiance.ideal.qpd.appraisee.dto.AppraiseeDetailsDTO appraiserName(int appraiserId) 
    {
        Object [] __bc_argArray = new Object[] { appraiserId };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        com.defiance.ideal.qpd.appraisee.dto.AppraiseeDetailsDTO __bc_retval = null;
        
        try
        {
            preInvoke(_appraiserNameMethod, __bc_argArray);
            
            __bc_retval =
            (com.defiance.ideal.qpd.appraisee.dto.AppraiseeDetailsDTO)
            __bc_target.invoke(_appraiserNameMethod, __bc_argArray)
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
            postInvoke(_appraiserNameMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
        return __bc_retval;
    }
    
    /**
    * Implements com.defiance.ideal.qpd.appraisee.db.AppraiseeDB.setDeleteDevNeeds
    */
    public  void setDeleteDevNeeds(int needsId, int deletedStatus) 
    {
        Object [] __bc_argArray = new Object[] { needsId, deletedStatus };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        
        try
        {
            preInvoke(_setDeleteDevNeedsMethod, __bc_argArray);
            
            __bc_target.invoke(_setDeleteDevNeedsMethod, __bc_argArray)
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
            postInvoke(_setDeleteDevNeedsMethod, __bc_argArray, __bc_rv, __bc_thrown);
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
