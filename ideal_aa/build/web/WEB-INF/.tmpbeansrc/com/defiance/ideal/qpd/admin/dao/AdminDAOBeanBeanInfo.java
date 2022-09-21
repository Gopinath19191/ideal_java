package com.defiance.ideal.qpd.admin.dao;

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
public class AdminDAOBeanBeanInfo
extends org.apache.beehive.controls.runtime.bean.ControlBeanInfo
{
    static final Method _filterEmployeeDataChangeReqMethod;
    static final Method _filterEmployeeDataMethod;
    static final Method _getEmployeeNameMethod;
    static final Method _triggerAppraisalMethod;
    static final Method _getStructureDetailsMethod;
    static final Method _getBandDataMethod;
    static final Method _updateAppraisalMethod;
    
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
            _filterEmployeeDataChangeReqMethod = com.defiance.ideal.qpd.admin.dao.AdminDAO.class.getMethod("filterEmployeeDataChangeReq", new Class [] {java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, int.class, int.class});
            _methodParamMap.put(_filterEmployeeDataChangeReqMethod, new String [] { "appraiserData", "concatentedBand", "dojCheck", "concatenatedStatus", "concatenatedStructure", "appraisalYear", "appraisalPeriod" });
            _filterEmployeeDataMethod = com.defiance.ideal.qpd.admin.dao.AdminDAO.class.getMethod("filterEmployeeData", new Class [] {java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, int.class, int.class});
            _methodParamMap.put(_filterEmployeeDataMethod, new String [] { "appraiserData", "concatenatedBand", "dojCheck", "concatenatedStatus", "concatenatedStructure", "appraisalYear", "appraisalPeriod" });
            _getEmployeeNameMethod = com.defiance.ideal.qpd.admin.dao.AdminDAO.class.getMethod("getEmployeeName", new Class [] {java.lang.String.class});
            _methodParamMap.put(_getEmployeeNameMethod, new String [] { "searchString" });
            _triggerAppraisalMethod = com.defiance.ideal.qpd.admin.dao.AdminDAO.class.getMethod("triggerAppraisal", new Class [] {com.defiance.ideal.qpd.admin.dto.AdminDTO.class, int.class});
            _methodParamMap.put(_triggerAppraisalMethod, new String [] { "formData", "empId" });
            _getStructureDetailsMethod = com.defiance.ideal.qpd.admin.dao.AdminDAO.class.getMethod("getStructureDetails", new Class [] {});
            _methodParamMap.put(_getStructureDetailsMethod, new String [] {  });
            _getBandDataMethod = com.defiance.ideal.qpd.admin.dao.AdminDAO.class.getMethod("getBandData", new Class [] {});
            _methodParamMap.put(_getBandDataMethod, new String [] {  });
            _updateAppraisalMethod = com.defiance.ideal.qpd.admin.dao.AdminDAO.class.getMethod("updateAppraisal", new Class [] {com.defiance.ideal.qpd.admin.dto.AdminDTO.class, int.class});
            _methodParamMap.put(_updateAppraisalMethod, new String [] { "formData", "empId" });
        }
        catch (NoSuchMethodException __bc_nsme)
        {
            throw new ExceptionInInitializerError(__bc_nsme);
        }
    }
    
    /**
    * Default null-arg constructor used to create a new BeanInfo instance
    */
    public AdminDAOBeanBeanInfo()
    {
        super(com.defiance.ideal.qpd.admin.dao.AdminDAOBean.class);
    }
    
    /**
    * Protected constructor used if this BeanInfo class is extended.
    */
    protected AdminDAOBeanBeanInfo(Class beanClass)
    {
        super(beanClass);
    }
    
    // java.beans.BeanInfo.getBeanDescriptor
    public BeanDescriptor getBeanDescriptor()
    {
        BeanDescriptor bd = new BeanDescriptor(com.defiance.ideal.qpd.admin.dao.AdminDAOBean.class);
        bd.setName( "AdminDAOBean" );
        bd.setDisplayName( "AdminDAOBean" );
        
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
        // Declare MethodDescriptor for filterEmployeeDataChangeReq(appraiserData, concatentedBand, dojCheck, concatenatedStatus, concatenatedStructure, appraisalYear, appraisalPeriod)
        //
        __bc_paramNames = _methodParamMap.get(_filterEmployeeDataChangeReqMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_filterEmployeeDataChangeReqMethod, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for filterEmployeeData(appraiserData, concatenatedBand, dojCheck, concatenatedStatus, concatenatedStructure, appraisalYear, appraisalPeriod)
        //
        __bc_paramNames = _methodParamMap.get(_filterEmployeeDataMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_filterEmployeeDataMethod, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for getEmployeeName(searchString)
        //
        __bc_paramNames = _methodParamMap.get(_getEmployeeNameMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_getEmployeeNameMethod, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for triggerAppraisal(formData, empId)
        //
        __bc_paramNames = _methodParamMap.get(_triggerAppraisalMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_triggerAppraisalMethod, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for getStructureDetails()
        //
        __bc_paramNames = _methodParamMap.get(_getStructureDetailsMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_getStructureDetailsMethod, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for getBandData()
        //
        __bc_paramNames = _methodParamMap.get(_getBandDataMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_getBandDataMethod, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for updateAppraisal(formData, empId)
        //
        __bc_paramNames = _methodParamMap.get(_updateAppraisalMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_updateAppraisalMethod, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        
    }
    
    public MethodDescriptor [] getMethodDescriptors()
    {
        MethodDescriptor [] __bc_methodDescriptors = new MethodDescriptor[7];
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
        
        pd = new PropertyDescriptor( "controlImplementation" , com.defiance.ideal.qpd.admin.dao.AdminDAOBean.class, "getControlImplementation", "setControlImplementation");
        propDescriptors[index++] = pd;
        
        //
        // add property descriptors for any getter/setters defined in control interface
        //
        pd = new PropertyDescriptor( "bandData" , com.defiance.ideal.qpd.admin.dao.AdminDAOBean.class, "getBandData", null);
        propDescriptors[index++] = pd;
        pd = new PropertyDescriptor( "structureDetails" , com.defiance.ideal.qpd.admin.dao.AdminDAOBean.class, "getStructureDetails", null);
        propDescriptors[index++] = pd;
        
    }
    
    // java.beans.BeanInfo.getPropertyDescriptors
    public PropertyDescriptor [] getPropertyDescriptors()
    {
        PropertyDescriptor [] __bc_propDescriptors = new PropertyDescriptor[3];
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
