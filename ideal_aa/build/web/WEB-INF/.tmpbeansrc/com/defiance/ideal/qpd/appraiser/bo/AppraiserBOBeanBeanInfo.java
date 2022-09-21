package com.defiance.ideal.qpd.appraiser.bo;

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
public class AppraiserBOBeanBeanInfo
extends org.apache.beehive.controls.runtime.bean.ControlBeanInfo
{
    static final Method _getSubmitStatusMethod;
    static final Method _getAchievementsDataMethod;
    static final Method _getAppraiseeListMethod;
    static final Method _getGoalDataMethod;
    static final Method _getDevelopmentDataMethod;
    static final Method _updateAppraiseeStatusMethod;
    static final Method _getKraDataMethod;
    static final Method _updateAppraiseeDataMethod;
    
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
            _getSubmitStatusMethod = com.defiance.ideal.qpd.appraiser.bo.AppraiserBO.class.getMethod("getSubmitStatus", new Class [] {int.class, int.class, int.class});
            _methodParamMap.put(_getSubmitStatusMethod, new String [] { "appraiseeId", "appraisalQuarter", "appraisalYear" });
            _getAchievementsDataMethod = com.defiance.ideal.qpd.appraiser.bo.AppraiserBO.class.getMethod("getAchievementsData", new Class [] {int.class, int.class});
            _methodParamMap.put(_getAchievementsDataMethod, new String [] { "appraisalYear", "appraiseeId" });
            _getAppraiseeListMethod = com.defiance.ideal.qpd.appraiser.bo.AppraiserBO.class.getMethod("getAppraiseeList", new Class [] {int.class, int.class, int.class});
            _methodParamMap.put(_getAppraiseeListMethod, new String [] { "employeeId", "appraisalYear", "appraisalQuarter" });
            _getGoalDataMethod = com.defiance.ideal.qpd.appraiser.bo.AppraiserBO.class.getMethod("getGoalData", new Class [] {int.class, int.class});
            _methodParamMap.put(_getGoalDataMethod, new String [] { "appraisalYear", "appraiseeId" });
            _getDevelopmentDataMethod = com.defiance.ideal.qpd.appraiser.bo.AppraiserBO.class.getMethod("getDevelopmentData", new Class [] {int.class, int.class});
            _methodParamMap.put(_getDevelopmentDataMethod, new String [] { "appraisalYear", "appraiseeId" });
            _updateAppraiseeStatusMethod = com.defiance.ideal.qpd.appraiser.bo.AppraiserBO.class.getMethod("updateAppraiseeStatus", new Class [] {int.class, int.class, int.class, int.class, java.lang.String.class});
            _methodParamMap.put(_updateAppraiseeStatusMethod, new String [] { "appraiseeId", "appraiseeQuarter", "appraiseeYear", "sendBackStatus", "reasonAppraiser" });
            _getKraDataMethod = com.defiance.ideal.qpd.appraiser.bo.AppraiserBO.class.getMethod("getKraData", new Class [] {int.class, int.class, int.class, int.class, int.class});
            _methodParamMap.put(_getKraDataMethod, new String [] { "bandIdForm", "appraisalQuarterForm", "appraisalYearForm", "appraiseeIdForm", "departmentId" });
            _updateAppraiseeDataMethod = com.defiance.ideal.qpd.appraiser.bo.AppraiserBO.class.getMethod("updateAppraiseeData", new Class [] {com.defiance.ideal.qpd.appraiser.dto.AppraiserRatingFormDTO.class});
            _methodParamMap.put(_updateAppraiseeDataMethod, new String [] { "formData" });
        }
        catch (NoSuchMethodException __bc_nsme)
        {
            throw new ExceptionInInitializerError(__bc_nsme);
        }
    }
    
    /**
    * Default null-arg constructor used to create a new BeanInfo instance
    */
    public AppraiserBOBeanBeanInfo()
    {
        super(com.defiance.ideal.qpd.appraiser.bo.AppraiserBOBean.class);
    }
    
    /**
    * Protected constructor used if this BeanInfo class is extended.
    */
    protected AppraiserBOBeanBeanInfo(Class beanClass)
    {
        super(beanClass);
    }
    
    // java.beans.BeanInfo.getBeanDescriptor
    public BeanDescriptor getBeanDescriptor()
    {
        BeanDescriptor bd = new BeanDescriptor(com.defiance.ideal.qpd.appraiser.bo.AppraiserBOBean.class);
        bd.setName( "AppraiserBOBean" );
        bd.setDisplayName( "AppraiserBOBean" );
        
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
        // Declare MethodDescriptor for getSubmitStatus(appraiseeId, appraisalQuarter, appraisalYear)
        //
        __bc_paramNames = _methodParamMap.get(_getSubmitStatusMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_getSubmitStatusMethod, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for getAchievementsData(appraisalYear, appraiseeId)
        //
        __bc_paramNames = _methodParamMap.get(_getAchievementsDataMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_getAchievementsDataMethod, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for getAppraiseeList(employeeId, appraisalYear, appraisalQuarter)
        //
        __bc_paramNames = _methodParamMap.get(_getAppraiseeListMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_getAppraiseeListMethod, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for getGoalData(appraisalYear, appraiseeId)
        //
        __bc_paramNames = _methodParamMap.get(_getGoalDataMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_getGoalDataMethod, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for getDevelopmentData(appraisalYear, appraiseeId)
        //
        __bc_paramNames = _methodParamMap.get(_getDevelopmentDataMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_getDevelopmentDataMethod, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for updateAppraiseeStatus(appraiseeId, appraiseeQuarter, appraiseeYear, sendBackStatus, reasonAppraiser)
        //
        __bc_paramNames = _methodParamMap.get(_updateAppraiseeStatusMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_updateAppraiseeStatusMethod, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for getKraData(bandIdForm, appraisalQuarterForm, appraisalYearForm, appraiseeIdForm, departmentId)
        //
        __bc_paramNames = _methodParamMap.get(_getKraDataMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_getKraDataMethod, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for updateAppraiseeData(formData)
        //
        __bc_paramNames = _methodParamMap.get(_updateAppraiseeDataMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_updateAppraiseeDataMethod, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        
    }
    
    public MethodDescriptor [] getMethodDescriptors()
    {
        MethodDescriptor [] __bc_methodDescriptors = new MethodDescriptor[8];
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
        
        pd = new PropertyDescriptor( "controlImplementation" , com.defiance.ideal.qpd.appraiser.bo.AppraiserBOBean.class, "getControlImplementation", "setControlImplementation");
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
