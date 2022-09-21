package com.defiance.ideal.login.bo;

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
public class LoginBOBeanBeanInfo
extends org.apache.beehive.controls.runtime.bean.ControlBeanInfo
{
    static final Method _menuCheckMethod;
    static final Method _authenticateLoginDetailsMethod;
    static final Method _updateManagerSurveyMethod;
    static final Method _authenticateUserMethod;
    static final Method _authenticateGroupMethod;
    static final Method _managerSurveyMethod;
    
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
            _menuCheckMethod = com.defiance.ideal.login.bo.LoginBO.class.getMethod("menuCheck", new Class [] {java.lang.String.class, int.class, int.class});
            _methodParamMap.put(_menuCheckMethod, new String [] { "referenceName", "currentYear", "employeeId" });
            _authenticateLoginDetailsMethod = com.defiance.ideal.login.bo.LoginBO.class.getMethod("authenticateLoginDetails", new Class [] {javax.servlet.http.HttpServletRequest.class});
            _methodParamMap.put(_authenticateLoginDetailsMethod, new String [] { "request" });
            _updateManagerSurveyMethod = com.defiance.ideal.login.bo.LoginBO.class.getMethod("updateManagerSurvey", new Class [] {int.class, int.class, int.class, int.class});
            _methodParamMap.put(_updateManagerSurveyMethod, new String [] { "currentYear", "currentQuarter", "employeeId", "surveyStatus" });
            _authenticateUserMethod = com.defiance.ideal.login.bo.LoginBO.class.getMethod("authenticateUser", new Class [] {java.lang.String.class, java.lang.String.class});
            _methodParamMap.put(_authenticateUserMethod, new String [] { "userAccountId", "MODULE_ID" });
            _authenticateGroupMethod = com.defiance.ideal.login.bo.LoginBO.class.getMethod("authenticateGroup", new Class [] {java.lang.String.class, java.lang.String.class});
            _methodParamMap.put(_authenticateGroupMethod, new String [] { "groupId", "MODULE_ID" });
            _managerSurveyMethod = com.defiance.ideal.login.bo.LoginBO.class.getMethod("managerSurvey", new Class [] {int.class, int.class});
            _methodParamMap.put(_managerSurveyMethod, new String [] { "currentYear", "employeeId" });
        }
        catch (NoSuchMethodException __bc_nsme)
        {
            throw new ExceptionInInitializerError(__bc_nsme);
        }
    }
    
    /**
    * Default null-arg constructor used to create a new BeanInfo instance
    */
    public LoginBOBeanBeanInfo()
    {
        super(com.defiance.ideal.login.bo.LoginBOBean.class);
    }
    
    /**
    * Protected constructor used if this BeanInfo class is extended.
    */
    protected LoginBOBeanBeanInfo(Class beanClass)
    {
        super(beanClass);
    }
    
    // java.beans.BeanInfo.getBeanDescriptor
    public BeanDescriptor getBeanDescriptor()
    {
        BeanDescriptor bd = new BeanDescriptor(com.defiance.ideal.login.bo.LoginBOBean.class);
        bd.setName( "LoginBOBean" );
        bd.setDisplayName( "LoginBOBean" );
        
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
        // Declare MethodDescriptor for menuCheck(referenceName, currentYear, employeeId)
        //
        __bc_paramNames = _methodParamMap.get(_menuCheckMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_menuCheckMethod, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for authenticateLoginDetails(request)
        //
        __bc_paramNames = _methodParamMap.get(_authenticateLoginDetailsMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_authenticateLoginDetailsMethod, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for updateManagerSurvey(currentYear, currentQuarter, employeeId, surveyStatus)
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
        // Declare MethodDescriptor for authenticateUser(userAccountId, MODULE_ID)
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
        // Declare MethodDescriptor for authenticateGroup(groupId, MODULE_ID)
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
        
        
    }
    
    public MethodDescriptor [] getMethodDescriptors()
    {
        MethodDescriptor [] __bc_methodDescriptors = new MethodDescriptor[6];
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
        
        pd = new PropertyDescriptor( "controlImplementation" , com.defiance.ideal.login.bo.LoginBOBean.class, "getControlImplementation", "setControlImplementation");
        propDescriptors[index++] = pd;
        
        
    }
    
    // java.beans.BeanInfo.getPropertyDescriptors
    public PropertyDescriptor [] getPropertyDescriptors()
    {
        PropertyDescriptor [] __bc_propDescriptors = new PropertyDescriptor[1];
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
