package com.defiance.ideal.shared;

import java.beans.BeanDescriptor;
import java.beans.EventSetDescriptor;
import java.beans.IntrospectionException;
import java.beans.MethodDescriptor;
import java.beans.ParameterDescriptor;
import java.beans.PropertyDescriptor;
import java.beans.PropertyEditor;
import java.lang.reflect.Method;
import java.util.HashMap;

import org.apache.beehive.controls.api.ControlException;
import org.apache.beehive.controls.runtime.bean.BeanPersistenceDelegate;
import org.apache.beehive.controls.runtime.packaging.ControlEventSetDescriptor;

@SuppressWarnings("all")
public class MysqlDatabaseBeanBeanInfo
extends com.defiance.ideal.shared.DBConnectivityBeanBeanInfo
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
    
    /**
    * Default null-arg constructor used to create a new BeanInfo instance
    */
    public MysqlDatabaseBeanBeanInfo()
    {
        super(com.defiance.ideal.shared.MysqlDatabaseBean.class);
    }
    
    /**
    * Protected constructor used if this BeanInfo class is extended.
    */
    protected MysqlDatabaseBeanBeanInfo(Class beanClass)
    {
        super(beanClass);
    }
    
    // java.beans.BeanInfo.getBeanDescriptor
    public BeanDescriptor getBeanDescriptor()
    {
        BeanDescriptor bd = new BeanDescriptor(com.defiance.ideal.shared.MysqlDatabaseBean.class);
        bd.setName( "MysqlDatabaseBean" );
        bd.setDisplayName( "MysqlDatabaseBean" );
        
        //
        // The org.apache.beehive.controls.runtime.bean.BeanPersistenceDelegate class
        // implements the XMLDecode delegation model for all Control JavaBean types. It
        // provides optimized XML persistance based upon the Control runtime architecture.
        // The 'persistenceDelegate' attribute of a BeanDescriptor is used by XMLEncoder to
        // locate a delegate for a particular JavaBean type.
        //
        bd.setValue("persistenceDelegate", new BeanPersistenceDelegate());
        
        return bd;
    }
    
    /**
    * Stores MethodDescriptor descriptors for this bean and its superclasses into
    * an array, starting at the specified index
    */
    protected void initMethodDescriptors(MethodDescriptor [] methodDescriptors, int index)
    throws java.beans.IntrospectionException
    {
        String [] __bc_paramNames;
        ParameterDescriptor [] __bc_paramDescriptors;
        MethodDescriptor md;
        
        //
        // Declare MethodDescriptor for authenticateUser(empId, moduleId)
        //
        __bc_paramNames = _methodParamMap.get(_authenticateUserMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_authenticateUserMethod, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for getHrEmailFromId(employeeId, year)
        //
        __bc_paramNames = _methodParamMap.get(_getHrEmailFromIdMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_getHrEmailFromIdMethod, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for removeFileDb(fileId)
        //
        __bc_paramNames = _methodParamMap.get(_removeFileDbMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_removeFileDbMethod, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for authenticateGroup(groupId, moduleId)
        //
        __bc_paramNames = _methodParamMap.get(_authenticateGroupMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_authenticateGroupMethod, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for getLastAppraisalYear()
        //
        __bc_paramNames = _methodParamMap.get(_getLastAppraisalYearMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_getLastAppraisalYearMethod, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for menuAuthentication(referenceName, currentYear, employeeId, triggeredStatus)
        //
        __bc_paramNames = _methodParamMap.get(_menuAuthenticationMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_menuAuthenticationMethod, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for managerSurvey(currentYear, employeeId)
        //
        __bc_paramNames = _methodParamMap.get(_managerSurveyMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_managerSurveyMethod, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for AuthenticateUser(userName, userPassword)
        //
        __bc_paramNames = _methodParamMap.get(_AuthenticateUserMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_AuthenticateUserMethod, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for getEmailFromId(employeeId)
        //
        __bc_paramNames = _methodParamMap.get(_getEmailFromIdMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_getEmailFromIdMethod, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for addFileDb(fileName, contentType, referenceName, jfId, moduleName)
        //
        __bc_paramNames = _methodParamMap.get(_addFileDbMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_addFileDbMethod, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for getEmployeeEmailFromId(employeeId, year)
        //
        __bc_paramNames = _methodParamMap.get(_getEmployeeEmailFromIdMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_getEmployeeEmailFromIdMethod, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for getLastAppraisalQuarter()
        //
        __bc_paramNames = _methodParamMap.get(_getLastAppraisalQuarterMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_getLastAppraisalQuarterMethod, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for updateManagerSurvey(currentYear, employeeId, surveyStatus)
        //
        __bc_paramNames = _methodParamMap.get(_updateManagerSurveyMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_updateManagerSurveyMethod, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for getFilesList(referenceId, moduleName)
        //
        __bc_paramNames = _methodParamMap.get(_getFilesList0Method);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_getFilesList0Method, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for getFilesList(referenceId, moduleName, appraisalYear)
        //
        __bc_paramNames = _methodParamMap.get(_getFilesList1Method);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_getFilesList1Method, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        
        //
        // Add method descriptors from parent BeanInfo
        //
        super.initMethodDescriptors(methodDescriptors, index);
    }
    
    public MethodDescriptor [] getMethodDescriptors()
    {
        MethodDescriptor [] __bc_methodDescriptors = new MethodDescriptor[18];
        try
        {
            initMethodDescriptors(__bc_methodDescriptors, 0);
        }
        catch (java.beans.IntrospectionException __bc_ie)
        {
            throw new ControlException("Unable to create MethodDescriptor", __bc_ie);
        }
        return __bc_methodDescriptors;
    }
    
    /**
    * Stores PropertyDescriptor descriptors for this bean and its superclasses into
    * an array, starting at the specified index
    */
    protected void initPropertyDescriptors(PropertyDescriptor [] propDescriptors, int index)
    throws java.beans.IntrospectionException
    {
        PropertyDescriptor pd;
        
        pd = new PropertyDescriptor( "controlImplementation" , com.defiance.ideal.shared.MysqlDatabaseBean.class, "getControlImplementation", "setControlImplementation");
        propDescriptors[index++] = pd;
        
        //
        // add property descriptors for any getter/setters defined in control interface
        //
        pd = new PropertyDescriptor( "lastAppraisalQuarter" , com.defiance.ideal.shared.MysqlDatabaseBean.class, "getLastAppraisalQuarter", null);
        propDescriptors[index++] = pd;
        pd = new PropertyDescriptor( "lastAppraisalYear" , com.defiance.ideal.shared.MysqlDatabaseBean.class, "getLastAppraisalYear", null);
        propDescriptors[index++] = pd;
        
        //
        // Add property descriptors from parent BeanInfo
        //
        super.initPropertyDescriptors(propDescriptors, index);
    }
    
    // java.beans.BeanInfo.getPropertyDescriptors
    public PropertyDescriptor [] getPropertyDescriptors()
    {
        PropertyDescriptor [] __bc_propDescriptors = new PropertyDescriptor[33];
        try
        {
            initPropertyDescriptors(__bc_propDescriptors, 0);
        }
        catch (java.beans.IntrospectionException __bc_ie)
        {
            throw new ControlException("Unable to create PropertyDescriptor", __bc_ie);
        }
        return __bc_propDescriptors;
    }
    
    
    protected void initEventSetDescriptors(EventSetDescriptor [] eventSetDescriptors, int index)
    throws java.beans.IntrospectionException
    {
        
        
        //
        // Add event set descriptors from parent BeanInfo
        //
        super.initEventSetDescriptors(eventSetDescriptors, index);
    }
    
    // java.beans.BeanInfo.getEventSetDescriptors
    public EventSetDescriptor [] getEventSetDescriptors()
    {
        EventSetDescriptor [] __bc_eventSetDescriptors = new EventSetDescriptor[0];
        try
        {
            initEventSetDescriptors(__bc_eventSetDescriptors, 0);
        }
        catch (java.beans.IntrospectionException __bc_ie)
        {
            throw new ControlException("Unable to create EventSetDescriptor", __bc_ie);
        }
        return __bc_eventSetDescriptors;
    }
}
