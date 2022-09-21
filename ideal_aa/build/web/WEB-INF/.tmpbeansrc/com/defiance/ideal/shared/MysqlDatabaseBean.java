
package com.defiance.ideal.shared;

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
public class MysqlDatabaseBean
extends com.defiance.ideal.shared.DBConnectivityBean
implements com.defiance.ideal.shared.MysqlDatabase
{
    static final Method _authenticateUserMethod;
    static final Method _getHrEmailFromIdMethod;
    static final Method _removeFileDbMethod;
    static final Method _authenticateGroupMethod;
    static final Method _getLastAppraisalYearMethod;
    static final Method _menuAuthenticationMethod;
    static final Method _managerSurveyMethod;
    static final Method _AuthenticateUserMethod;
    static final Method _getEmailFromIdMethod;
    static final Method _addFileDbMethod;
    static final Method _getEmployeeEmailFromIdMethod;
    static final Method _getLastAppraisalQuarterMethod;
    static final Method _updateManagerSurveyMethod;
    static final Method _getFilesList0Method;
    static final Method _getFilesList1Method;
    
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
            _authenticateUserMethod = com.defiance.ideal.shared.MysqlDatabase.class.getMethod("authenticateUser", new Class [] {java.lang.String.class, java.lang.String.class});
            _methodParamMap.put(_authenticateUserMethod, new String [] { "empId", "moduleId" });
            _getHrEmailFromIdMethod = com.defiance.ideal.shared.MysqlDatabase.class.getMethod("getHrEmailFromId", new Class [] {int.class, int.class});
            _methodParamMap.put(_getHrEmailFromIdMethod, new String [] { "employeeId", "year" });
            _removeFileDbMethod = com.defiance.ideal.shared.MysqlDatabase.class.getMethod("removeFileDb", new Class [] {int.class});
            _methodParamMap.put(_removeFileDbMethod, new String [] { "fileId" });
            _authenticateGroupMethod = com.defiance.ideal.shared.MysqlDatabase.class.getMethod("authenticateGroup", new Class [] {java.lang.String.class, java.lang.String.class});
            _methodParamMap.put(_authenticateGroupMethod, new String [] { "groupId", "moduleId" });
            _getLastAppraisalYearMethod = com.defiance.ideal.shared.MysqlDatabase.class.getMethod("getLastAppraisalYear", new Class [] {});
            _methodParamMap.put(_getLastAppraisalYearMethod, new String [] {  });
            _menuAuthenticationMethod = com.defiance.ideal.shared.MysqlDatabase.class.getMethod("menuAuthentication", new Class [] {java.lang.String.class, int.class, int.class, int.class});
            _methodParamMap.put(_menuAuthenticationMethod, new String [] { "referenceName", "currentYear", "employeeId", "triggeredStatus" });
            _managerSurveyMethod = com.defiance.ideal.shared.MysqlDatabase.class.getMethod("managerSurvey", new Class [] {int.class, int.class});
            _methodParamMap.put(_managerSurveyMethod, new String [] { "currentYear", "employeeId" });
            _AuthenticateUserMethod = com.defiance.ideal.shared.MysqlDatabase.class.getMethod("AuthenticateUser", new Class [] {java.lang.String.class, java.lang.String.class});
            _methodParamMap.put(_AuthenticateUserMethod, new String [] { "userName", "userPassword" });
            _getEmailFromIdMethod = com.defiance.ideal.shared.MysqlDatabase.class.getMethod("getEmailFromId", new Class [] {int.class});
            _methodParamMap.put(_getEmailFromIdMethod, new String [] { "employeeId" });
            _addFileDbMethod = com.defiance.ideal.shared.MysqlDatabase.class.getMethod("addFileDb", new Class [] {java.lang.String.class, java.lang.String.class, java.lang.String.class, int.class, java.lang.String.class});
            _methodParamMap.put(_addFileDbMethod, new String [] { "fileName", "contentType", "referenceName", "jfId", "moduleName" });
            _getEmployeeEmailFromIdMethod = com.defiance.ideal.shared.MysqlDatabase.class.getMethod("getEmployeeEmailFromId", new Class [] {int.class, int.class});
            _methodParamMap.put(_getEmployeeEmailFromIdMethod, new String [] { "employeeId", "year" });
            _getLastAppraisalQuarterMethod = com.defiance.ideal.shared.MysqlDatabase.class.getMethod("getLastAppraisalQuarter", new Class [] {});
            _methodParamMap.put(_getLastAppraisalQuarterMethod, new String [] {  });
            _updateManagerSurveyMethod = com.defiance.ideal.shared.MysqlDatabase.class.getMethod("updateManagerSurvey", new Class [] {int.class, int.class, int.class});
            _methodParamMap.put(_updateManagerSurveyMethod, new String [] { "currentYear", "employeeId", "surveyStatus" });
            _getFilesList0Method = com.defiance.ideal.shared.MysqlDatabase.class.getMethod("getFilesList", new Class [] {int.class, java.lang.String.class});
            _methodParamMap.put(_getFilesList0Method, new String [] { "referenceId", "moduleName" });
            _getFilesList1Method = com.defiance.ideal.shared.MysqlDatabase.class.getMethod("getFilesList", new Class [] {int.class, java.lang.String.class, int.class});
            _methodParamMap.put(_getFilesList1Method, new String [] { "referenceId", "moduleName", "appraisalYear" });
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
    public MysqlDatabaseBean(ControlBeanContext context, String id, PropertyMap props)
    {
        this(context, id, props, com.defiance.ideal.shared.MysqlDatabase.class);
    }
    
    /**
    * This is the public null-arg constructor for this ControlBean.  If a control id
    * is not provided, a unique value will be auto-generated.
    */
    public MysqlDatabaseBean()
    {
        this(null, null, null);
    }
    
    /**
    * This is the protected version that is used by any ControlBean subclass
    */
    protected MysqlDatabaseBean(ControlBeanContext context, String id, PropertyMap props, Class controlClass)
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
    * Implements com.defiance.ideal.shared.MysqlDatabase.authenticateUser
    */
    public  com.defiance.ideal.login.dto.LoginFormDTO authenticateUser(java.lang.String empId, java.lang.String moduleId) 
    {
        Object [] __bc_argArray = new Object[] { empId, moduleId };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        com.defiance.ideal.login.dto.LoginFormDTO __bc_retval = null;
        
        try
        {
            preInvoke(_authenticateUserMethod, __bc_argArray);
            
            __bc_retval =
            (com.defiance.ideal.login.dto.LoginFormDTO)
            __bc_target.invoke(_authenticateUserMethod, __bc_argArray)
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
    * Implements com.defiance.ideal.shared.MysqlDatabase.getHrEmailFromId
    */
    public  com.defiance.ideal.shared.SendMailDTO getHrEmailFromId(int employeeId, int year) throws java.lang.Exception
    {
        Object [] __bc_argArray = new Object[] { employeeId, year };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        com.defiance.ideal.shared.SendMailDTO __bc_retval = null;
        
        try
        {
            preInvoke(_getHrEmailFromIdMethod, __bc_argArray);
            
            __bc_retval =
            (com.defiance.ideal.shared.SendMailDTO)
            __bc_target.invoke(_getHrEmailFromIdMethod, __bc_argArray)
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
            postInvoke(_getHrEmailFromIdMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
        return __bc_retval;
    }
    
    /**
    * Implements com.defiance.ideal.shared.MysqlDatabase.removeFileDb
    */
    public  void removeFileDb(int fileId) 
    {
        Object [] __bc_argArray = new Object[] { fileId };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        
        try
        {
            preInvoke(_removeFileDbMethod, __bc_argArray);
            
            __bc_target.invoke(_removeFileDbMethod, __bc_argArray)
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
            postInvoke(_removeFileDbMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
    }
    
    /**
    * Implements com.defiance.ideal.shared.MysqlDatabase.authenticateGroup
    */
    public  com.defiance.ideal.login.dto.LoginFormDTO authenticateGroup(java.lang.String groupId, java.lang.String moduleId) 
    {
        Object [] __bc_argArray = new Object[] { groupId, moduleId };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        com.defiance.ideal.login.dto.LoginFormDTO __bc_retval = null;
        
        try
        {
            preInvoke(_authenticateGroupMethod, __bc_argArray);
            
            __bc_retval =
            (com.defiance.ideal.login.dto.LoginFormDTO)
            __bc_target.invoke(_authenticateGroupMethod, __bc_argArray)
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
    * Implements com.defiance.ideal.shared.MysqlDatabase.getLastAppraisalYear
    */
    public  com.defiance.ideal.shared.SendMailDTO getLastAppraisalYear() 
    {
        Object [] __bc_argArray = new Object[] {  };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        com.defiance.ideal.shared.SendMailDTO __bc_retval = null;
        
        try
        {
            preInvoke(_getLastAppraisalYearMethod, __bc_argArray);
            
            __bc_retval =
            (com.defiance.ideal.shared.SendMailDTO)
            __bc_target.invoke(_getLastAppraisalYearMethod, __bc_argArray)
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
            postInvoke(_getLastAppraisalYearMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
        return __bc_retval;
    }
    
    /**
    * Implements com.defiance.ideal.shared.MysqlDatabase.menuAuthentication
    */
    public  com.defiance.ideal.login.dto.LoginFormDTO menuAuthentication(java.lang.String referenceName, int currentYear, int employeeId, int triggeredStatus) 
    {
        Object [] __bc_argArray = new Object[] { referenceName, currentYear, employeeId, triggeredStatus };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        com.defiance.ideal.login.dto.LoginFormDTO __bc_retval = null;
        
        try
        {
            preInvoke(_menuAuthenticationMethod, __bc_argArray);
            
            __bc_retval =
            (com.defiance.ideal.login.dto.LoginFormDTO)
            __bc_target.invoke(_menuAuthenticationMethod, __bc_argArray)
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
            postInvoke(_menuAuthenticationMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
        return __bc_retval;
    }
    
    /**
    * Implements com.defiance.ideal.shared.MysqlDatabase.managerSurvey
    */
    public  com.defiance.ideal.login.dto.LoginFormDTO managerSurvey(int currentYear, int employeeId) 
    {
        Object [] __bc_argArray = new Object[] { currentYear, employeeId };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        com.defiance.ideal.login.dto.LoginFormDTO __bc_retval = null;
        
        try
        {
            preInvoke(_managerSurveyMethod, __bc_argArray);
            
            __bc_retval =
            (com.defiance.ideal.login.dto.LoginFormDTO)
            __bc_target.invoke(_managerSurveyMethod, __bc_argArray)
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
    * Implements com.defiance.ideal.shared.MysqlDatabase.AuthenticateUser
    */
    public  com.defiance.ideal.login.dto.LoginFormDTO AuthenticateUser(java.lang.String userName, java.lang.String userPassword) throws java.lang.Exception
    {
        Object [] __bc_argArray = new Object[] { userName, userPassword };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        com.defiance.ideal.login.dto.LoginFormDTO __bc_retval = null;
        
        try
        {
            preInvoke(_AuthenticateUserMethod, __bc_argArray);
            
            __bc_retval =
            (com.defiance.ideal.login.dto.LoginFormDTO)
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
    * Implements com.defiance.ideal.shared.MysqlDatabase.getEmailFromId
    */
    public  com.defiance.ideal.shared.SendMailDTO getEmailFromId(int employeeId) throws java.lang.Exception
    {
        Object [] __bc_argArray = new Object[] { employeeId };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        com.defiance.ideal.shared.SendMailDTO __bc_retval = null;
        
        try
        {
            preInvoke(_getEmailFromIdMethod, __bc_argArray);
            
            __bc_retval =
            (com.defiance.ideal.shared.SendMailDTO)
            __bc_target.invoke(_getEmailFromIdMethod, __bc_argArray)
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
            postInvoke(_getEmailFromIdMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
        return __bc_retval;
    }
    
    /**
    * Implements com.defiance.ideal.shared.MysqlDatabase.addFileDb
    */
    public  void addFileDb(java.lang.String fileName, java.lang.String contentType, java.lang.String referenceName, int jfId, java.lang.String moduleName) 
    {
        Object [] __bc_argArray = new Object[] { fileName, contentType, referenceName, jfId, moduleName };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        
        try
        {
            preInvoke(_addFileDbMethod, __bc_argArray);
            
            __bc_target.invoke(_addFileDbMethod, __bc_argArray)
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
            postInvoke(_addFileDbMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
    }
    
    /**
    * Implements com.defiance.ideal.shared.MysqlDatabase.getEmployeeEmailFromId
    */
    public  com.defiance.ideal.shared.SendMailDTO getEmployeeEmailFromId(int employeeId, int year) throws java.lang.Exception
    {
        Object [] __bc_argArray = new Object[] { employeeId, year };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        com.defiance.ideal.shared.SendMailDTO __bc_retval = null;
        
        try
        {
            preInvoke(_getEmployeeEmailFromIdMethod, __bc_argArray);
            
            __bc_retval =
            (com.defiance.ideal.shared.SendMailDTO)
            __bc_target.invoke(_getEmployeeEmailFromIdMethod, __bc_argArray)
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
            postInvoke(_getEmployeeEmailFromIdMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
        return __bc_retval;
    }
    
    /**
    * Implements com.defiance.ideal.shared.MysqlDatabase.getLastAppraisalQuarter
    */
    public  com.defiance.ideal.shared.SendMailDTO getLastAppraisalQuarter() 
    {
        Object [] __bc_argArray = new Object[] {  };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        com.defiance.ideal.shared.SendMailDTO __bc_retval = null;
        
        try
        {
            preInvoke(_getLastAppraisalQuarterMethod, __bc_argArray);
            
            __bc_retval =
            (com.defiance.ideal.shared.SendMailDTO)
            __bc_target.invoke(_getLastAppraisalQuarterMethod, __bc_argArray)
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
            postInvoke(_getLastAppraisalQuarterMethod, __bc_argArray, __bc_rv, __bc_thrown);
        }
        return __bc_retval;
    }
    
    /**
    * Implements com.defiance.ideal.shared.MysqlDatabase.updateManagerSurvey
    */
    public  void updateManagerSurvey(int currentYear, int employeeId, int surveyStatus) 
    {
        Object [] __bc_argArray = new Object[] { currentYear, employeeId, surveyStatus };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        
        try
        {
            preInvoke(_updateManagerSurveyMethod, __bc_argArray);
            
            __bc_target.invoke(_updateManagerSurveyMethod, __bc_argArray)
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
    * Implements com.defiance.ideal.shared.MysqlDatabase.getFilesList
    */
    public  com.defiance.ideal.shared.SendMailDTO[] getFilesList(int referenceId, java.lang.String moduleName) 
    {
        Object [] __bc_argArray = new Object[] { referenceId, moduleName };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        com.defiance.ideal.shared.SendMailDTO[] __bc_retval = null;
        
        try
        {
            preInvoke(_getFilesList0Method, __bc_argArray);
            
            __bc_retval =
            (com.defiance.ideal.shared.SendMailDTO[])
            __bc_target.invoke(_getFilesList0Method, __bc_argArray)
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
            postInvoke(_getFilesList0Method, __bc_argArray, __bc_rv, __bc_thrown);
        }
        return __bc_retval;
    }
    
    /**
    * Implements com.defiance.ideal.shared.MysqlDatabase.getFilesList
    */
    public  com.defiance.ideal.shared.SendMailDTO[] getFilesList(int referenceId, java.lang.String moduleName, int appraisalYear) 
    {
        Object [] __bc_argArray = new Object[] { referenceId, moduleName, appraisalYear };
        Throwable __bc_thrown = null;
        Extensible __bc_target = (Extensible)ensureControl();
        com.defiance.ideal.shared.SendMailDTO[] __bc_retval = null;
        
        try
        {
            preInvoke(_getFilesList1Method, __bc_argArray);
            
            __bc_retval =
            (com.defiance.ideal.shared.SendMailDTO[])
            __bc_target.invoke(_getFilesList1Method, __bc_argArray)
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
            postInvoke(_getFilesList1Method, __bc_argArray, __bc_rv, __bc_thrown);
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
