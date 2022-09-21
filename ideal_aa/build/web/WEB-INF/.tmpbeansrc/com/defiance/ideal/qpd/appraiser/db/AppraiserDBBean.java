
package com.defiance.ideal.qpd.appraiser.db;

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
public class AppraiserDBBean
extends com.defiance.ideal.shared.DBConnectivityBean
implements com.defiance.ideal.qpd.appraiser.db.AppraiserDB
{
    static final Method _updateAchievementsDataMethod;
    static final Method _getKraDataMethod;
    static final Method _updateDevelopmentDataMethod;
    static final Method _getAchievementsDataMethod;
    static final Method _getAppraiseeListMethod;
    static final Method _getGoalDataMethod;
    static final Method _getSelectedAppraiseeDetailsMethod;
    static final Method _updateAppraiserRatingMethod;
    static final Method _updateGoalDataMethod;
    static final Method _getDevelopmentDataMethod;
    static final Method _updateAppraiseeDataMethod;
    static final Method _updateAppraiseeStatusMethod;
    
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
            _updateAchievementsDataMethod = com.defiance.ideal.qpd.appraiser.db.AppraiserDB.class.getMethod("updateAchievementsData", new Class [] {int.class, java.lang.String.class});
            _methodParamMap.put(_updateAchievementsDataMethod, new String [] { "achievementId", "appraiserRemarks" });
            _getKraDataMethod = com.defiance.ideal.qpd.appraiser.db.AppraiserDB.class.getMethod("getKraData", new Class [] {int.class, int.class, int.class, int.class});
            _methodParamMap.put(_getKraDataMethod, new String [] { "bandId", "appraisalYear", "appraiseeId", "departmentId" });
            _updateDevelopmentDataMethod = com.defiance.ideal.qpd.appraiser.db.AppraiserDB.class.getMethod("updateDevelopmentData", new Class [] {int.class, java.lang.String.class});
            _methodParamMap.put(_updateDevelopmentDataMethod, new String [] { "needsId", "apprDevRemarks" });
            _getAchievementsDataMethod = com.defiance.ideal.qpd.appraiser.db.AppraiserDB.class.getMethod("getAchievementsData", new Class [] {int.class, int.class});
            _methodParamMap.put(_getAchievementsDataMethod, new String [] { "appraisalYear", "appraiseeId" });
            _getAppraiseeListMethod = com.defiance.ideal.qpd.appraiser.db.AppraiserDB.class.getMethod("getAppraiseeList", new Class [] {int.class, int.class, int.class});
            _methodParamMap.put(_getAppraiseeListMethod, new String [] { "employeeId", "appraisalYear", "triggerStatus" });
            _getGoalDataMethod = com.defiance.ideal.qpd.appraiser.db.AppraiserDB.class.getMethod("getGoalData", new Class [] {int.class, int.class});
            _methodParamMap.put(_getGoalDataMethod, new String [] { "appraisalYear", "appraiseeId" });
            _getSelectedAppraiseeDetailsMethod = com.defiance.ideal.qpd.appraiser.db.AppraiserDB.class.getMethod("getSelectedAppraiseeDetails", new Class [] {int.class, int.class});
            _methodParamMap.put(_getSelectedAppraiseeDetailsMethod, new String [] { "appraiseeId", "appraisalYear" });
            _updateAppraiserRatingMethod = com.defiance.ideal.qpd.appraiser.db.AppraiserDB.class.getMethod("updateAppraiserRating", new Class [] {int.class, int.class, int.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class});
            _methodParamMap.put(_updateAppraiserRatingMethod, new String [] { "finalRating", "appraiseeId", "appraiseeYear", "appraiserComments", "areasOfImprovement", "technologyTraining", "softskillTraining", "appraiserPromotionRecommeded" });
            _updateGoalDataMethod = com.defiance.ideal.qpd.appraiser.db.AppraiserDB.class.getMethod("updateGoalData", new Class [] {int.class, java.lang.String.class});
            _methodParamMap.put(_updateGoalDataMethod, new String [] { "goalId", "goalRemarks" });
            _getDevelopmentDataMethod = com.defiance.ideal.qpd.appraiser.db.AppraiserDB.class.getMethod("getDevelopmentData", new Class [] {int.class, int.class});
            _methodParamMap.put(_getDevelopmentDataMethod, new String [] { "appraisalYear", "appraiseeId" });
            _updateAppraiseeDataMethod = com.defiance.ideal.qpd.appraiser.db.AppraiserDB.class.getMethod("updateAppraiseeData", new Class [] {int.class, int.class, int.class, java.lang.String.class, int.class, java.lang.String.class});
            _methodParamMap.put(_updateAppraiseeDataMethod, new String [] { "appraiseeId", "kraQtrId", "qpdKraId", "appraiseeComments", "appraiserRating", "appraiserCommentsNew" });
            _updateAppraiseeStatusMethod = com.defiance.ideal.qpd.appraiser.db.AppraiserDB.class.getMethod("updateAppraiseeStatus", new Class [] {int.class, int.class, int.class});
            _methodParamMap.put(_updateAppraiseeStatusMethod, new String [] { "appraiseeId", "appraiseeYear", "submitStatus" });
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
    public AppraiserDBBean(ControlBeanContext context, String id, PropertyMap props)
    {
        this(context, id, props, com.defiance.ideal.qpd.appraiser.db.AppraiserDB.class);
    }
    
    /**
    * This is the public null-arg constructor for this ControlBean.  If a control id
    * is not provided, a unique value will be auto-generated.
    */
    public AppraiserDBBean()
    {
        this(null, null, null);
    }
    
    /**
    * This is the protected version that is used by any ControlBean subclass
    */
    protected AppraiserDBBean(ControlBeanContext context, String id, PropertyMap props, Class controlClass)
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
    * Implements com.defiance.ideal.qpd.appraiser.db.AppraiserDB.updateAchievementsData
    */
    public  void updateAchievementsData(int achievementId, java.lang.String appraiserRemarks) 
    {
        Object [] __bc_argArray = new Object[] { achievementId, appraiserRemarks };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        
        try
        {
            preInvoke(_updateAchievementsDataMethod, __bc_argArray);
            
            __bc_target.invoke(_updateAchievementsDataMethod, __bc_argArray)
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
            postInvoke(_updateAchievementsDataMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
    }
    
    /**
    * Implements com.defiance.ideal.qpd.appraiser.db.AppraiserDB.getKraData
    */
    public  com.defiance.ideal.qpd.appraiser.dto.AppraiserFormDTO[] getKraData(int bandId, int appraisalYear, int appraiseeId, int departmentId) 
    {
        Object [] __bc_argArray = new Object[] { bandId, appraisalYear, appraiseeId, departmentId };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        com.defiance.ideal.qpd.appraiser.dto.AppraiserFormDTO[] __bc_retval = null;
        
        try
        {
            preInvoke(_getKraDataMethod, __bc_argArray);
            
            __bc_retval =
            (com.defiance.ideal.qpd.appraiser.dto.AppraiserFormDTO[])
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
    * Implements com.defiance.ideal.qpd.appraiser.db.AppraiserDB.updateDevelopmentData
    */
    public  void updateDevelopmentData(int needsId, java.lang.String apprDevRemarks) 
    {
        Object [] __bc_argArray = new Object[] { needsId, apprDevRemarks };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        
        try
        {
            preInvoke(_updateDevelopmentDataMethod, __bc_argArray);
            
            __bc_target.invoke(_updateDevelopmentDataMethod, __bc_argArray)
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
            postInvoke(_updateDevelopmentDataMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
    }
    
    /**
    * Implements com.defiance.ideal.qpd.appraiser.db.AppraiserDB.getAchievementsData
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
    * Implements com.defiance.ideal.qpd.appraiser.db.AppraiserDB.getAppraiseeList
    */
    public  com.defiance.ideal.qpd.appraiser.dto.AppraiseeListDTO[] getAppraiseeList(int employeeId, int appraisalYear, int triggerStatus) 
    {
        Object [] __bc_argArray = new Object[] { employeeId, appraisalYear, triggerStatus };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        com.defiance.ideal.qpd.appraiser.dto.AppraiseeListDTO[] __bc_retval = null;
        
        try
        {
            preInvoke(_getAppraiseeListMethod, __bc_argArray);
            
            __bc_retval =
            (com.defiance.ideal.qpd.appraiser.dto.AppraiseeListDTO[])
            __bc_target.invoke(_getAppraiseeListMethod, __bc_argArray)
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
    * Implements com.defiance.ideal.qpd.appraiser.db.AppraiserDB.getGoalData
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
    * Implements com.defiance.ideal.qpd.appraiser.db.AppraiserDB.getSelectedAppraiseeDetails
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
    * Implements com.defiance.ideal.qpd.appraiser.db.AppraiserDB.updateAppraiserRating
    */
    public  void updateAppraiserRating(int finalRating, int appraiseeId, int appraiseeYear, java.lang.String appraiserComments, java.lang.String areasOfImprovement, java.lang.String technologyTraining, java.lang.String softskillTraining, java.lang.String appraiserPromotionRecommeded) 
    {
        Object [] __bc_argArray = new Object[] { finalRating, appraiseeId, appraiseeYear, appraiserComments, areasOfImprovement, technologyTraining, softskillTraining, appraiserPromotionRecommeded };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        
        try
        {
            preInvoke(_updateAppraiserRatingMethod, __bc_argArray);
            
            __bc_target.invoke(_updateAppraiserRatingMethod, __bc_argArray)
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
            postInvoke(_updateAppraiserRatingMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
    }
    
    /**
    * Implements com.defiance.ideal.qpd.appraiser.db.AppraiserDB.updateGoalData
    */
    public  void updateGoalData(int goalId, java.lang.String goalRemarks) 
    {
        Object [] __bc_argArray = new Object[] { goalId, goalRemarks };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        
        try
        {
            preInvoke(_updateGoalDataMethod, __bc_argArray);
            
            __bc_target.invoke(_updateGoalDataMethod, __bc_argArray)
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
            postInvoke(_updateGoalDataMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
    }
    
    /**
    * Implements com.defiance.ideal.qpd.appraiser.db.AppraiserDB.getDevelopmentData
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
    * Implements com.defiance.ideal.qpd.appraiser.db.AppraiserDB.updateAppraiseeData
    */
    public  void updateAppraiseeData(int appraiseeId, int kraQtrId, int qpdKraId, java.lang.String appraiseeComments, int appraiserRating, java.lang.String appraiserCommentsNew) 
    {
        Object [] __bc_argArray = new Object[] { appraiseeId, kraQtrId, qpdKraId, appraiseeComments, appraiserRating, appraiserCommentsNew };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        
        try
        {
            preInvoke(_updateAppraiseeDataMethod, __bc_argArray);
            
            __bc_target.invoke(_updateAppraiseeDataMethod, __bc_argArray)
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
    * Implements com.defiance.ideal.qpd.appraiser.db.AppraiserDB.updateAppraiseeStatus
    */
    public  void updateAppraiseeStatus(int appraiseeId, int appraiseeYear, int submitStatus) 
    {
        Object [] __bc_argArray = new Object[] { appraiseeId, appraiseeYear, submitStatus };
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
