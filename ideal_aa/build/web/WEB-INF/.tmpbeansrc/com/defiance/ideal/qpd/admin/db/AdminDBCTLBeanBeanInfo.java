package com.defiance.ideal.qpd.admin.db;

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
public class AdminDBCTLBeanBeanInfo
extends com.defiance.ideal.shared.DBConnectivityBeanBeanInfo
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
    
    /**
    * Default null-arg constructor used to create a new BeanInfo instance
    */
    public AdminDBCTLBeanBeanInfo()
    {
        super(com.defiance.ideal.qpd.admin.db.AdminDBCTLBean.class);
    }
    
    /**
    * Protected constructor used if this BeanInfo class is extended.
    */
    protected AdminDBCTLBeanBeanInfo(Class beanClass)
    {
        super(beanClass);
    }
    
    // java.beans.BeanInfo.getBeanDescriptor
    public BeanDescriptor getBeanDescriptor()
    {
        BeanDescriptor bd = new BeanDescriptor(com.defiance.ideal.qpd.admin.db.AdminDBCTLBean.class);
        bd.setName( "AdminDBCTLBean" );
        bd.setDisplayName( "AdminDBCTLBean" );
        
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
        // Declare MethodDescriptor for filterEmployeeDataChangeRequest(dojCheck, appraisalYear, aprraisalTriggerStatus, whereCondition)
        //
        __bc_paramNames = _methodParamMap.get(_filterEmployeeDataChangeRequestMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_filterEmployeeDataChangeRequestMethod, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for updateAppraisal(quarter, year, bandId, depId, appraiseeId, appraiserId, reviewerId, normalizerId, hrId, financeId, qpdId)
        //
        __bc_paramNames = _methodParamMap.get(_updateAppraisal1Method);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_updateAppraisal1Method, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for getDepartmentDetails(depId)
        //
        __bc_paramNames = _methodParamMap.get(_getDepartmentDetailsMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_getDepartmentDetailsMethod, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for filterEmployeeData(dojCheck, appraisalYear, whereCondition, whereCondition2)
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
        // Declare MethodDescriptor for triggerAppraisal(quarter, year, bandId, depId, appraiseeId, appraiserId, reviewerId, normalizerId, hrId, financeId, submitStatus, qpdId, triggerStatus)
        //
        __bc_paramNames = _methodParamMap.get(_triggerAppraisal1Method);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_triggerAppraisal1Method, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for getBandDetails(bandId)
        //
        __bc_paramNames = _methodParamMap.get(_getBandDetailsMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_getBandDetailsMethod, __bc_paramDescriptors);
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
        // Declare MethodDescriptor for getAppraiserDataAfterTrigger()
        //
        __bc_paramNames = _methodParamMap.get(_getAppraiserDataAfterTriggerMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_getAppraiserDataAfterTriggerMethod, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for updateAppraisal(quarter, year, bandId, depId, appraiseeId, appraiserId, reviewerId, normalizerId, hrId, financeId, submitStatus, qpdId)
        //
        __bc_paramNames = _methodParamMap.get(_updateAppraisal0Method);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_updateAppraisal0Method, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for getAppraiserDataBeforeTrigger()
        //
        __bc_paramNames = _methodParamMap.get(_getAppraiserDataBeforeTriggerMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_getAppraiserDataBeforeTriggerMethod, __bc_paramDescriptors);
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
        // Declare MethodDescriptor for triggerAppraisal(quarter, year, bandId, depId, appraiseeId, appraiserId, reviewerId, normalizerId, hrId, financeId, submitStatus, qpdId)
        //
        __bc_paramNames = _methodParamMap.get(_triggerAppraisal0Method);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_triggerAppraisal0Method, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        
        //
        // Add method descriptors from parent BeanInfo
        //
        super.initMethodDescriptors(methodDescriptors, index);
    }
    
    public MethodDescriptor [] getMethodDescriptors()
    {
        MethodDescriptor [] __bc_methodDescriptors = new MethodDescriptor[17];
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
        
        pd = new PropertyDescriptor( "controlImplementation" , com.defiance.ideal.qpd.admin.db.AdminDBCTLBean.class, "getControlImplementation", "setControlImplementation");
        propDescriptors[index++] = pd;
        
        //
        // add property descriptors for any getter/setters defined in control interface
        //
        pd = new PropertyDescriptor( "bandData" , com.defiance.ideal.qpd.admin.db.AdminDBCTLBean.class, "getBandData", null);
        propDescriptors[index++] = pd;
        pd = new PropertyDescriptor( "appraiserDataBeforeTrigger" , com.defiance.ideal.qpd.admin.db.AdminDBCTLBean.class, "getAppraiserDataBeforeTrigger", null);
        propDescriptors[index++] = pd;
        pd = new PropertyDescriptor( "appraiserDataAfterTrigger" , com.defiance.ideal.qpd.admin.db.AdminDBCTLBean.class, "getAppraiserDataAfterTrigger", null);
        propDescriptors[index++] = pd;
        pd = new PropertyDescriptor( "structureDetails" , com.defiance.ideal.qpd.admin.db.AdminDBCTLBean.class, "getStructureDetails", null);
        propDescriptors[index++] = pd;
        
        //
        // Add property descriptors from parent BeanInfo
        //
        super.initPropertyDescriptors(propDescriptors, index);
    }
    
    // java.beans.BeanInfo.getPropertyDescriptors
    public PropertyDescriptor [] getPropertyDescriptors()
    {
        PropertyDescriptor [] __bc_propDescriptors = new PropertyDescriptor[35];
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
