package com.defiance.ideal.qpd.managers.bo;

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
public class ManagerBOBeanBeanInfo
extends org.apache.beehive.controls.runtime.bean.ControlBeanInfo
{
    static final Method _getAppraiseesByHrMethod;
    static final Method _getAppraiseesByFinanceMethod;
    static final Method _getAppraiseesByNormalizerMethod;
    static final Method _getAppraiseesByReviewerMethod;
    static final Method _updateNormalizerRatingMethod;
    static final Method _getDevelopmentDataMethod;
    static final Method _getKraDataMethod;
    static final Method _updateReviewerRatingMethod;
    static final Method _getSubmitStatusMethod;
    static final Method _updateFinalStatusMethod;
    static final Method _getEmployeesCountMethod;
    static final Method _getAchievementsDataMethod;
    static final Method _getGoalDataMethod;
    
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
            _getAppraiseesByHrMethod = com.defiance.ideal.qpd.managers.bo.ManagerBO.class.getMethod("getAppraiseesByHr", new Class [] {javax.servlet.http.HttpServletRequest.class, java.lang.String.class, int.class, int.class});
            _methodParamMap.put(_getAppraiseesByHrMethod, new String [] { "request", "hrId", "appraisalQuarter", "appraisalYear" });
            _getAppraiseesByFinanceMethod = com.defiance.ideal.qpd.managers.bo.ManagerBO.class.getMethod("getAppraiseesByFinance", new Class [] {javax.servlet.http.HttpServletRequest.class, java.lang.String.class, int.class, int.class});
            _methodParamMap.put(_getAppraiseesByFinanceMethod, new String [] { "request", "financeId", "appraisalQuarter", "appraisalYear" });
            _getAppraiseesByNormalizerMethod = com.defiance.ideal.qpd.managers.bo.ManagerBO.class.getMethod("getAppraiseesByNormalizer", new Class [] {javax.servlet.http.HttpServletRequest.class, java.lang.String.class, int.class, int.class});
            _methodParamMap.put(_getAppraiseesByNormalizerMethod, new String [] { "request", "normalizerId", "appraisalQuarter", "appraisalYear" });
            _getAppraiseesByReviewerMethod = com.defiance.ideal.qpd.managers.bo.ManagerBO.class.getMethod("getAppraiseesByReviewer", new Class [] {javax.servlet.http.HttpServletRequest.class, java.lang.String.class, int.class, int.class});
            _methodParamMap.put(_getAppraiseesByReviewerMethod, new String [] { "request", "reviewerId", "appraisalQuarter", "appraisalYear" });
            _updateNormalizerRatingMethod = com.defiance.ideal.qpd.managers.bo.ManagerBO.class.getMethod("updateNormalizerRating", new Class [] {java.lang.String.class, int.class, int.class, com.defiance.ideal.qpd.managers.dto.ManagerDTO.class, java.lang.String.class});
            _methodParamMap.put(_updateNormalizerRatingMethod, new String [] { "normalizerId", "appraisalQuarter", "appraisalYear", "formData", "button" });
            _getDevelopmentDataMethod = com.defiance.ideal.qpd.managers.bo.ManagerBO.class.getMethod("getDevelopmentData", new Class [] {int.class, int.class});
            _methodParamMap.put(_getDevelopmentDataMethod, new String [] { "appraisalYear", "appraiseeId" });
            _getKraDataMethod = com.defiance.ideal.qpd.managers.bo.ManagerBO.class.getMethod("getKraData", new Class [] {int.class, int.class, int.class, int.class, int.class});
            _methodParamMap.put(_getKraDataMethod, new String [] { "bandId", "appraisalQuarter", "appraisalYear", "appraiseeId", "departmentId" });
            _updateReviewerRatingMethod = com.defiance.ideal.qpd.managers.bo.ManagerBO.class.getMethod("updateReviewerRating", new Class [] {java.lang.String.class, int.class, int.class, com.defiance.ideal.qpd.managers.dto.ManagerDTO.class, java.lang.String.class});
            _methodParamMap.put(_updateReviewerRatingMethod, new String [] { "reviewerId", "appraisalQuarter", "appraisalYear", "formData", "button" });
            _getSubmitStatusMethod = com.defiance.ideal.qpd.managers.bo.ManagerBO.class.getMethod("getSubmitStatus", new Class [] {int.class, int.class, int.class});
            _methodParamMap.put(_getSubmitStatusMethod, new String [] { "appraiseeId", "appraisalQuarter", "appraisalYear" });
            _updateFinalStatusMethod = com.defiance.ideal.qpd.managers.bo.ManagerBO.class.getMethod("updateFinalStatus", new Class [] {java.lang.String.class, int.class, int.class, com.defiance.ideal.qpd.managers.dto.ManagerDTO.class, java.lang.String.class});
            _methodParamMap.put(_updateFinalStatusMethod, new String [] { "hrId", "appraisalQuarter", "appraisalYear", "appraiseesbyHr", "button" });
            _getEmployeesCountMethod = com.defiance.ideal.qpd.managers.bo.ManagerBO.class.getMethod("getEmployeesCount", new Class [] {java.lang.String.class, int.class, int.class, java.lang.String.class});
            _methodParamMap.put(_getEmployeesCountMethod, new String [] { "reviewerId", "appraisalQuarter", "appraisalYear", "referenceType" });
            _getAchievementsDataMethod = com.defiance.ideal.qpd.managers.bo.ManagerBO.class.getMethod("getAchievementsData", new Class [] {int.class, int.class});
            _methodParamMap.put(_getAchievementsDataMethod, new String [] { "appraisalYear", "appraiseeId" });
            _getGoalDataMethod = com.defiance.ideal.qpd.managers.bo.ManagerBO.class.getMethod("getGoalData", new Class [] {int.class, int.class});
            _methodParamMap.put(_getGoalDataMethod, new String [] { "appraisalYear", "appraiseeId" });
        }
        catch (NoSuchMethodException __bc_nsme)
        {
            throw new ExceptionInInitializerError(__bc_nsme);
        }
    }
    
    /**
    * Default null-arg constructor used to create a new BeanInfo instance
    */
    public ManagerBOBeanBeanInfo()
    {
        super(com.defiance.ideal.qpd.managers.bo.ManagerBOBean.class);
    }
    
    /**
    * Protected constructor used if this BeanInfo class is extended.
    */
    protected ManagerBOBeanBeanInfo(Class beanClass)
    {
        super(beanClass);
    }
    
    // java.beans.BeanInfo.getBeanDescriptor
    public BeanDescriptor getBeanDescriptor()
    {
        BeanDescriptor bd = new BeanDescriptor(com.defiance.ideal.qpd.managers.bo.ManagerBOBean.class);
        bd.setName( "ManagerBOBean" );
        bd.setDisplayName( "ManagerBOBean" );
        
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
        // Declare MethodDescriptor for getAppraiseesByHr(request, hrId, appraisalQuarter, appraisalYear)
        //
        __bc_paramNames = _methodParamMap.get(_getAppraiseesByHrMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_getAppraiseesByHrMethod, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for getAppraiseesByFinance(request, financeId, appraisalQuarter, appraisalYear)
        //
        __bc_paramNames = _methodParamMap.get(_getAppraiseesByFinanceMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_getAppraiseesByFinanceMethod, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for getAppraiseesByNormalizer(request, normalizerId, appraisalQuarter, appraisalYear)
        //
        __bc_paramNames = _methodParamMap.get(_getAppraiseesByNormalizerMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_getAppraiseesByNormalizerMethod, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for getAppraiseesByReviewer(request, reviewerId, appraisalQuarter, appraisalYear)
        //
        __bc_paramNames = _methodParamMap.get(_getAppraiseesByReviewerMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_getAppraiseesByReviewerMethod, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for updateNormalizerRating(normalizerId, appraisalQuarter, appraisalYear, formData, button)
        //
        __bc_paramNames = _methodParamMap.get(_updateNormalizerRatingMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_updateNormalizerRatingMethod, __bc_paramDescriptors);
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
        // Declare MethodDescriptor for getKraData(bandId, appraisalQuarter, appraisalYear, appraiseeId, departmentId)
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
        // Declare MethodDescriptor for updateReviewerRating(reviewerId, appraisalQuarter, appraisalYear, formData, button)
        //
        __bc_paramNames = _methodParamMap.get(_updateReviewerRatingMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_updateReviewerRatingMethod, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
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
        // Declare MethodDescriptor for updateFinalStatus(hrId, appraisalQuarter, appraisalYear, appraiseesbyHr, button)
        //
        __bc_paramNames = _methodParamMap.get(_updateFinalStatusMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_updateFinalStatusMethod, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for getEmployeesCount(reviewerId, appraisalQuarter, appraisalYear, referenceType)
        //
        __bc_paramNames = _methodParamMap.get(_getEmployeesCountMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_getEmployeesCountMethod, __bc_paramDescriptors);
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
        
        
    }
    
    public MethodDescriptor [] getMethodDescriptors()
    {
        MethodDescriptor [] __bc_methodDescriptors = new MethodDescriptor[13];
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
        
        pd = new PropertyDescriptor( "controlImplementation" , com.defiance.ideal.qpd.managers.bo.ManagerBOBean.class, "getControlImplementation", "setControlImplementation");
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
