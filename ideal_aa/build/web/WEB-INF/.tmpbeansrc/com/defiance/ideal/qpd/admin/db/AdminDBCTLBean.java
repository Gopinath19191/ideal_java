
package com.defiance.ideal.qpd.admin.db;

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
public class AdminDBCTLBean
extends com.defiance.ideal.shared.DBConnectivityBean
implements com.defiance.ideal.qpd.admin.db.AdminDBCTL
{
    static final Method _filterEmployeeDataChangeRequestMethod;
    static final Method _updateAppraisal1Method;
    static final Method _getDepartmentDetailsMethod;
    static final Method _filterEmployeeDataMethod;
    static final Method _getEmployeeNameMethod;
    static final Method _triggerAppraisal1Method;
    static final Method _getBandDetailsMethod;
    static final Method _getBandDataMethod;
    static final Method _AuthenticateUserMethod;
    static final Method _getAppraiserDataAfterTriggerMethod;
    static final Method _updateAppraisal0Method;
    static final Method _getAppraiserDataBeforeTriggerMethod;
    static final Method _getStructureDetailsMethod;
    static final Method _triggerAppraisal0Method;
    
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
            _filterEmployeeDataChangeRequestMethod = com.defiance.ideal.qpd.admin.db.AdminDBCTL.class.getMethod("filterEmployeeDataChangeRequest", new Class [] {java.lang.String.class, int.class, int.class, java.lang.String.class});
            _methodParamMap.put(_filterEmployeeDataChangeRequestMethod, new String [] { "dojCheck", "appraisalYear", "aprraisalTriggerStatus", "whereCondition" });
            _updateAppraisal1Method = com.defiance.ideal.qpd.admin.db.AdminDBCTL.class.getMethod("updateAppraisal", new Class [] {int.class, int.class, int.class, int.class, int.class, int.class, int.class, int.class, int.class, int.class, int.class});
            _methodParamMap.put(_updateAppraisal1Method, new String [] { "quarter", "year", "bandId", "depId", "appraiseeId", "appraiserId", "reviewerId", "normalizerId", "hrId", "financeId", "qpdId" });
            _getDepartmentDetailsMethod = com.defiance.ideal.qpd.admin.db.AdminDBCTL.class.getMethod("getDepartmentDetails", new Class [] {int.class});
            _methodParamMap.put(_getDepartmentDetailsMethod, new String [] { "depId" });
            _filterEmployeeDataMethod = com.defiance.ideal.qpd.admin.db.AdminDBCTL.class.getMethod("filterEmployeeData", new Class [] {java.lang.String.class, int.class, java.lang.String.class, java.lang.String.class});
            _methodParamMap.put(_filterEmployeeDataMethod, new String [] { "dojCheck", "appraisalYear", "whereCondition", "whereCondition2" });
            _getEmployeeNameMethod = com.defiance.ideal.qpd.admin.db.AdminDBCTL.class.getMethod("getEmployeeName", new Class [] {java.lang.String.class});
            _methodParamMap.put(_getEmployeeNameMethod, new String [] { "searchString" });
            _triggerAppraisal1Method = com.defiance.ideal.qpd.admin.db.AdminDBCTL.class.getMethod("triggerAppraisal", new Class [] {int.class, int.class, int.class, int.class, int.class, int.class, int.class, int.class, int.class, int.class, int.class, java.lang.String.class, int.class});
            _methodParamMap.put(_triggerAppraisal1Method, new String [] { "quarter", "year", "bandId", "depId", "appraiseeId", "appraiserId", "reviewerId", "normalizerId", "hrId", "financeId", "submitStatus", "qpdId", "triggerStatus" });
            _getBandDetailsMethod = com.defiance.ideal.qpd.admin.db.AdminDBCTL.class.getMethod("getBandDetails", new Class [] {int.class});
            _methodParamMap.put(_getBandDetailsMethod, new String [] { "bandId" });
            _getBandDataMethod = com.defiance.ideal.qpd.admin.db.AdminDBCTL.class.getMethod("getBandData", new Class [] {});
            _methodParamMap.put(_getBandDataMethod, new String [] {  });
            _AuthenticateUserMethod = com.defiance.ideal.qpd.admin.db.AdminDBCTL.class.getMethod("AuthenticateUser", new Class [] {java.lang.String.class, java.lang.String.class});
            _methodParamMap.put(_AuthenticateUserMethod, new String [] { "userName", "userPassword" });
            _getAppraiserDataAfterTriggerMethod = com.defiance.ideal.qpd.admin.db.AdminDBCTL.class.getMethod("getAppraiserDataAfterTrigger", new Class [] {});
            _methodParamMap.put(_getAppraiserDataAfterTriggerMethod, new String [] {  });
            _updateAppraisal0Method = com.defiance.ideal.qpd.admin.db.AdminDBCTL.class.getMethod("updateAppraisal", new Class [] {int.class, int.class, int.class, int.class, int.class, int.class, int.class, int.class, int.class, int.class, int.class, int.class});
            _methodParamMap.put(_updateAppraisal0Method, new String [] { "quarter", "year", "bandId", "depId", "appraiseeId", "appraiserId", "reviewerId", "normalizerId", "hrId", "financeId", "submitStatus", "qpdId" });
            _getAppraiserDataBeforeTriggerMethod = com.defiance.ideal.qpd.admin.db.AdminDBCTL.class.getMethod("getAppraiserDataBeforeTrigger", new Class [] {});
            _methodParamMap.put(_getAppraiserDataBeforeTriggerMethod, new String [] {  });
            _getStructureDetailsMethod = com.defiance.ideal.qpd.admin.db.AdminDBCTL.class.getMethod("getStructureDetails", new Class [] {});
            _methodParamMap.put(_getStructureDetailsMethod, new String [] {  });
            _triggerAppraisal0Method = com.defiance.ideal.qpd.admin.db.AdminDBCTL.class.getMethod("triggerAppraisal", new Class [] {int.class, int.class, int.class, int.class, int.class, int.class, int.class, int.class, int.class, int.class, int.class, java.lang.String.class});
            _methodParamMap.put(_triggerAppraisal0Method, new String [] { "quarter", "year", "bandId", "depId", "appraiseeId", "appraiserId", "reviewerId", "normalizerId", "hrId", "financeId", "submitStatus", "qpdId" });
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
    public AdminDBCTLBean(ControlBeanContext context, String id, PropertyMap props)
    {
        this(context, id, props, com.defiance.ideal.qpd.admin.db.AdminDBCTL.class);
    }
    
    /**
    * This is the public null-arg constructor for this ControlBean.  If a control id
    * is not provided, a unique value will be auto-generated.
    */
    public AdminDBCTLBean()
    {
        this(null, null, null);
    }
    
    /**
    * This is the protected version that is used by any ControlBean subclass
    */
    protected AdminDBCTLBean(ControlBeanContext context, String id, PropertyMap props, Class controlClass)
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
    * Implements com.defiance.ideal.qpd.admin.db.AdminDBCTL.filterEmployeeDataChangeRequest
    */
    public  com.defiance.ideal.qpd.admin.dto.AdminDTO[] filterEmployeeDataChangeRequest(java.lang.String dojCheck, int appraisalYear, int aprraisalTriggerStatus, java.lang.String whereCondition) 
    {
        Object [] __bc_argArray = new Object[] { dojCheck, appraisalYear, aprraisalTriggerStatus, whereCondition };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        com.defiance.ideal.qpd.admin.dto.AdminDTO[] __bc_retval = null;
        
        try
        {
            preInvoke(_filterEmployeeDataChangeRequestMethod, __bc_argArray);
            
            __bc_retval =
            (com.defiance.ideal.qpd.admin.dto.AdminDTO[])
            __bc_target.invoke(_filterEmployeeDataChangeRequestMethod, __bc_argArray)
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
            postInvoke(_filterEmployeeDataChangeRequestMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
        return __bc_retval;
    }
    
    /**
    * Implements com.defiance.ideal.qpd.admin.db.AdminDBCTL.updateAppraisal
    */
    public  void updateAppraisal(int quarter, int year, int bandId, int depId, int appraiseeId, int appraiserId, int reviewerId, int normalizerId, int hrId, int financeId, int qpdId) 
    {
        Object [] __bc_argArray = new Object[] { quarter, year, bandId, depId, appraiseeId, appraiserId, reviewerId, normalizerId, hrId, financeId, qpdId };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        
        try
        {
            preInvoke(_updateAppraisal1Method, __bc_argArray);
            
            __bc_target.invoke(_updateAppraisal1Method, __bc_argArray)
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
            postInvoke(_updateAppraisal1Method, __bc_argArray, __bc_rv, __bc_thrown);
        }
    }
    
    /**
    * Implements com.defiance.ideal.qpd.admin.db.AdminDBCTL.getDepartmentDetails
    */
    public  com.defiance.ideal.qpd.admin.dto.AdminDTO getDepartmentDetails(int depId) 
    {
        Object [] __bc_argArray = new Object[] { depId };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        com.defiance.ideal.qpd.admin.dto.AdminDTO __bc_retval = null;
        
        try
        {
            preInvoke(_getDepartmentDetailsMethod, __bc_argArray);
            
            __bc_retval =
            (com.defiance.ideal.qpd.admin.dto.AdminDTO)
            __bc_target.invoke(_getDepartmentDetailsMethod, __bc_argArray)
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
            postInvoke(_getDepartmentDetailsMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
        return __bc_retval;
    }
    
    /**
    * Implements com.defiance.ideal.qpd.admin.db.AdminDBCTL.filterEmployeeData
    */
    public  com.defiance.ideal.qpd.admin.dto.AdminDTO[] filterEmployeeData(java.lang.String dojCheck, int appraisalYear, java.lang.String whereCondition, java.lang.String whereCondition2) 
    {
        Object [] __bc_argArray = new Object[] { dojCheck, appraisalYear, whereCondition, whereCondition2 };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        com.defiance.ideal.qpd.admin.dto.AdminDTO[] __bc_retval = null;
        
        try
        {
            preInvoke(_filterEmployeeDataMethod, __bc_argArray);
            
            __bc_retval =
            (com.defiance.ideal.qpd.admin.dto.AdminDTO[])
            __bc_target.invoke(_filterEmployeeDataMethod, __bc_argArray)
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
    * Implements com.defiance.ideal.qpd.admin.db.AdminDBCTL.getEmployeeName
    */
    public  com.defiance.ideal.qpd.admin.dto.AdminDTO[] getEmployeeName(java.lang.String searchString) throws java.lang.Exception
    {
        Object [] __bc_argArray = new Object[] { searchString };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        com.defiance.ideal.qpd.admin.dto.AdminDTO[] __bc_retval = null;
        
        try
        {
            preInvoke(_getEmployeeNameMethod, __bc_argArray);
            
            __bc_retval =
            (com.defiance.ideal.qpd.admin.dto.AdminDTO[])
            __bc_target.invoke(_getEmployeeNameMethod, __bc_argArray)
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
            postInvoke(_getEmployeeNameMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
        return __bc_retval;
    }
    
    /**
    * Implements com.defiance.ideal.qpd.admin.db.AdminDBCTL.triggerAppraisal
    */
    public  void triggerAppraisal(int quarter, int year, int bandId, int depId, int appraiseeId, int appraiserId, int reviewerId, int normalizerId, int hrId, int financeId, int submitStatus, java.lang.String qpdId, int triggerStatus) throws java.lang.Exception
    {
        Object [] __bc_argArray = new Object[] { quarter, year, bandId, depId, appraiseeId, appraiserId, reviewerId, normalizerId, hrId, financeId, submitStatus, qpdId, triggerStatus };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        
        try
        {
            preInvoke(_triggerAppraisal1Method, __bc_argArray);
            
            __bc_target.invoke(_triggerAppraisal1Method, __bc_argArray)
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
            Object __bc_rv = null;
            postInvoke(_triggerAppraisal1Method, __bc_argArray, __bc_rv, __bc_thrown);
        }
    }
    
    /**
    * Implements com.defiance.ideal.qpd.admin.db.AdminDBCTL.getBandDetails
    */
    public  com.defiance.ideal.qpd.admin.dto.AdminDTO getBandDetails(int bandId) 
    {
        Object [] __bc_argArray = new Object[] { bandId };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        com.defiance.ideal.qpd.admin.dto.AdminDTO __bc_retval = null;
        
        try
        {
            preInvoke(_getBandDetailsMethod, __bc_argArray);
            
            __bc_retval =
            (com.defiance.ideal.qpd.admin.dto.AdminDTO)
            __bc_target.invoke(_getBandDetailsMethod, __bc_argArray)
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
            postInvoke(_getBandDetailsMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
        return __bc_retval;
    }
    
    /**
    * Implements com.defiance.ideal.qpd.admin.db.AdminDBCTL.getBandData
    */
    public  com.defiance.ideal.qpd.admin.dto.AdminDTO[] getBandData() 
    {
        Object [] __bc_argArray = new Object[] {  };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        com.defiance.ideal.qpd.admin.dto.AdminDTO[] __bc_retval = null;
        
        try
        {
            preInvoke(_getBandDataMethod, __bc_argArray);
            
            __bc_retval =
            (com.defiance.ideal.qpd.admin.dto.AdminDTO[])
            __bc_target.invoke(_getBandDataMethod, __bc_argArray)
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
    * Implements com.defiance.ideal.qpd.admin.db.AdminDBCTL.AuthenticateUser
    */
    public  com.defiance.ideal.qpd.admin.dto.AdminDTO AuthenticateUser(java.lang.String userName, java.lang.String userPassword) throws java.lang.Exception
    {
        Object [] __bc_argArray = new Object[] { userName, userPassword };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        com.defiance.ideal.qpd.admin.dto.AdminDTO __bc_retval = null;
        
        try
        {
            preInvoke(_AuthenticateUserMethod, __bc_argArray);
            
            __bc_retval =
            (com.defiance.ideal.qpd.admin.dto.AdminDTO)
            __bc_target.invoke(_AuthenticateUserMethod, __bc_argArray)
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
            postInvoke(_AuthenticateUserMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
        return __bc_retval;
    }
    
    /**
    * Implements com.defiance.ideal.qpd.admin.db.AdminDBCTL.getAppraiserDataAfterTrigger
    */
    public  com.defiance.ideal.qpd.admin.dto.AdminDTO[] getAppraiserDataAfterTrigger() 
    {
        Object [] __bc_argArray = new Object[] {  };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        com.defiance.ideal.qpd.admin.dto.AdminDTO[] __bc_retval = null;
        
        try
        {
            preInvoke(_getAppraiserDataAfterTriggerMethod, __bc_argArray);
            
            __bc_retval =
            (com.defiance.ideal.qpd.admin.dto.AdminDTO[])
            __bc_target.invoke(_getAppraiserDataAfterTriggerMethod, __bc_argArray)
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
            postInvoke(_getAppraiserDataAfterTriggerMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
        return __bc_retval;
    }
    
    /**
    * Implements com.defiance.ideal.qpd.admin.db.AdminDBCTL.updateAppraisal
    */
    public  void updateAppraisal(int quarter, int year, int bandId, int depId, int appraiseeId, int appraiserId, int reviewerId, int normalizerId, int hrId, int financeId, int submitStatus, int qpdId) 
    {
        Object [] __bc_argArray = new Object[] { quarter, year, bandId, depId, appraiseeId, appraiserId, reviewerId, normalizerId, hrId, financeId, submitStatus, qpdId };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        
        try
        {
            preInvoke(_updateAppraisal0Method, __bc_argArray);
            
            __bc_target.invoke(_updateAppraisal0Method, __bc_argArray)
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
            postInvoke(_updateAppraisal0Method, __bc_argArray, __bc_rv, __bc_thrown);
        }
    }
    
    /**
    * Implements com.defiance.ideal.qpd.admin.db.AdminDBCTL.getAppraiserDataBeforeTrigger
    */
    public  com.defiance.ideal.qpd.admin.dto.AdminDTO[] getAppraiserDataBeforeTrigger() 
    {
        Object [] __bc_argArray = new Object[] {  };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        com.defiance.ideal.qpd.admin.dto.AdminDTO[] __bc_retval = null;
        
        try
        {
            preInvoke(_getAppraiserDataBeforeTriggerMethod, __bc_argArray);
            
            __bc_retval =
            (com.defiance.ideal.qpd.admin.dto.AdminDTO[])
            __bc_target.invoke(_getAppraiserDataBeforeTriggerMethod, __bc_argArray)
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
            postInvoke(_getAppraiserDataBeforeTriggerMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
        return __bc_retval;
    }
    
    /**
    * Implements com.defiance.ideal.qpd.admin.db.AdminDBCTL.getStructureDetails
    */
    public  com.defiance.ideal.qpd.admin.dto.AdminDTO[] getStructureDetails() throws java.lang.Exception
    {
        Object [] __bc_argArray = new Object[] {  };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        com.defiance.ideal.qpd.admin.dto.AdminDTO[] __bc_retval = null;
        
        try
        {
            preInvoke(_getStructureDetailsMethod, __bc_argArray);
            
            __bc_retval =
            (com.defiance.ideal.qpd.admin.dto.AdminDTO[])
            __bc_target.invoke(_getStructureDetailsMethod, __bc_argArray)
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
            postInvoke(_getStructureDetailsMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
        return __bc_retval;
    }
    
    /**
    * Implements com.defiance.ideal.qpd.admin.db.AdminDBCTL.triggerAppraisal
    */
    public  void triggerAppraisal(int quarter, int year, int bandId, int depId, int appraiseeId, int appraiserId, int reviewerId, int normalizerId, int hrId, int financeId, int submitStatus, java.lang.String qpdId) throws java.lang.Exception
    {
        Object [] __bc_argArray = new Object[] { quarter, year, bandId, depId, appraiseeId, appraiserId, reviewerId, normalizerId, hrId, financeId, submitStatus, qpdId };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        
        try
        {
            preInvoke(_triggerAppraisal0Method, __bc_argArray);
            
            __bc_target.invoke(_triggerAppraisal0Method, __bc_argArray)
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
            Object __bc_rv = null;
            postInvoke(_triggerAppraisal0Method, __bc_argArray, __bc_rv, __bc_thrown);
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
