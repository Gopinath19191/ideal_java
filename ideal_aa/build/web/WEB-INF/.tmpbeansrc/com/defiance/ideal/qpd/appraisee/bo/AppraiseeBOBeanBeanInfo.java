package com.defiance.ideal.qpd.appraisee.bo;

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
public class AppraiseeBOBeanBeanInfo
extends org.apache.beehive.controls.runtime.bean.ControlBeanInfo
{
    static final Method _getDevlopAttributesMethod;
    static final Method _getCostAttributesMethod;
    static final Method _checkAppraiseeCommentsMethod;
    static final Method _getQualAttributesMethod;
    static final Method _authenticateAppraiseeMethod;
    static final Method _updateAppraiseeStatusMethod;
    static final Method _updateAppraiseeCommentsMethod;
    static final Method _getAppraiseeSBUMethod;
    static final Method _getAchievementsDataMethod;
    static final Method _getReviewerNameMethod;
    static final Method _getGoalDataMethod;
    static final Method _getAppraiserNameMethod;
    static final Method _insertAppraiseeCommentsMethod;
    static final Method _getKraDataMethod;
    static final Method _getCustomerAttributesMethod;
    static final Method _getDevNeedsDataMethod;
    static final Method _insertOrUpdateAppraiseeDataMethod;
    
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
            _getDevlopAttributesMethod = com.defiance.ideal.qpd.appraisee.bo.AppraiseeBO.class.getMethod("getDevlopAttributes", new Class [] {java.lang.String.class});
            _methodParamMap.put(_getDevlopAttributesMethod, new String [] { "kraDevlop" });
            _getCostAttributesMethod = com.defiance.ideal.qpd.appraisee.bo.AppraiseeBO.class.getMethod("getCostAttributes", new Class [] {java.lang.String.class});
            _methodParamMap.put(_getCostAttributesMethod, new String [] { "kraCost" });
            _checkAppraiseeCommentsMethod = com.defiance.ideal.qpd.appraisee.bo.AppraiseeBO.class.getMethod("checkAppraiseeComments", new Class [] {int.class});
            _methodParamMap.put(_checkAppraiseeCommentsMethod, new String [] { "appraiseeId" });
            _getQualAttributesMethod = com.defiance.ideal.qpd.appraisee.bo.AppraiseeBO.class.getMethod("getQualAttributes", new Class [] {java.lang.String.class});
            _methodParamMap.put(_getQualAttributesMethod, new String [] { "kraQuality" });
            _authenticateAppraiseeMethod = com.defiance.ideal.qpd.appraisee.bo.AppraiseeBO.class.getMethod("authenticateAppraisee", new Class [] {java.lang.String.class, int.class, int.class});
            _methodParamMap.put(_authenticateAppraiseeMethod, new String [] { "empNum", "appraiseeQuarter", "appraiseeYear" });
            _updateAppraiseeStatusMethod = com.defiance.ideal.qpd.appraisee.bo.AppraiseeBO.class.getMethod("updateAppraiseeStatus", new Class [] {int.class, int.class, int.class});
            _methodParamMap.put(_updateAppraiseeStatusMethod, new String [] { "appraiseeId", "quarter", "quarterYear" });
            _updateAppraiseeCommentsMethod = com.defiance.ideal.qpd.appraisee.bo.AppraiseeBO.class.getMethod("updateAppraiseeComments", new Class [] {java.util.ArrayList.class, java.lang.String.class});
            _methodParamMap.put(_updateAppraiseeCommentsMethod, new String [] { "appraiseeComments", "empNum" });
            _getAppraiseeSBUMethod = com.defiance.ideal.qpd.appraisee.bo.AppraiseeBO.class.getMethod("getAppraiseeSBU", new Class [] {int.class});
            _methodParamMap.put(_getAppraiseeSBUMethod, new String [] { "deptId" });
            _getAchievementsDataMethod = com.defiance.ideal.qpd.appraisee.bo.AppraiseeBO.class.getMethod("getAchievementsData", new Class [] {int.class, int.class});
            _methodParamMap.put(_getAchievementsDataMethod, new String [] { "appraiseeId", "appraisalYear" });
            _getReviewerNameMethod = com.defiance.ideal.qpd.appraisee.bo.AppraiseeBO.class.getMethod("getReviewerName", new Class [] {int.class});
            _methodParamMap.put(_getReviewerNameMethod, new String [] { "reviewerId" });
            _getGoalDataMethod = com.defiance.ideal.qpd.appraisee.bo.AppraiseeBO.class.getMethod("getGoalData", new Class [] {int.class, int.class});
            _methodParamMap.put(_getGoalDataMethod, new String [] { "appraiseeId", "appraisalYear" });
            _getAppraiserNameMethod = com.defiance.ideal.qpd.appraisee.bo.AppraiseeBO.class.getMethod("getAppraiserName", new Class [] {int.class});
            _methodParamMap.put(_getAppraiserNameMethod, new String [] { "appraiserId" });
            _insertAppraiseeCommentsMethod = com.defiance.ideal.qpd.appraisee.bo.AppraiseeBO.class.getMethod("insertAppraiseeComments", new Class [] {java.util.ArrayList.class, java.lang.String.class});
            _methodParamMap.put(_insertAppraiseeCommentsMethod, new String [] { "appraiseeComments", "empNum" });
            _getKraDataMethod = com.defiance.ideal.qpd.appraisee.bo.AppraiseeBO.class.getMethod("getKraData", new Class [] {int.class, int.class, int.class, java.lang.String.class, int.class});
            _methodParamMap.put(_getKraDataMethod, new String [] { "bandId", "appraisalYear", "appraisalQuarter", "appraiseeId", "departmentId" });
            _getCustomerAttributesMethod = com.defiance.ideal.qpd.appraisee.bo.AppraiseeBO.class.getMethod("getCustomerAttributes", new Class [] {java.lang.String.class});
            _methodParamMap.put(_getCustomerAttributesMethod, new String [] { "kraCustomer" });
            _getDevNeedsDataMethod = com.defiance.ideal.qpd.appraisee.bo.AppraiseeBO.class.getMethod("getDevNeedsData", new Class [] {int.class, int.class});
            _methodParamMap.put(_getDevNeedsDataMethod, new String [] { "appraiseeId", "appraisalYear" });
            _insertOrUpdateAppraiseeDataMethod = com.defiance.ideal.qpd.appraisee.bo.AppraiseeBO.class.getMethod("insertOrUpdateAppraiseeData", new Class [] {com.defiance.ideal.qpd.appraisee.dto.MyAppraisalFormDTO.class, int.class});
            _methodParamMap.put(_insertOrUpdateAppraiseeDataMethod, new String [] { "formData", "appraiseeId" });
        }
        catch (NoSuchMethodException __bc_nsme)
        {
            throw new ExceptionInInitializerError(__bc_nsme);
        }
    }
    
    /**
    * Default null-arg constructor used to create a new BeanInfo instance
    */
    public AppraiseeBOBeanBeanInfo()
    {
        super(com.defiance.ideal.qpd.appraisee.bo.AppraiseeBOBean.class);
    }
    
    /**
    * Protected constructor used if this BeanInfo class is extended.
    */
    protected AppraiseeBOBeanBeanInfo(Class beanClass)
    {
        super(beanClass);
    }
    
    // java.beans.BeanInfo.getBeanDescriptor
    public BeanDescriptor getBeanDescriptor()
    {
        BeanDescriptor bd = new BeanDescriptor(com.defiance.ideal.qpd.appraisee.bo.AppraiseeBOBean.class);
        bd.setName( "AppraiseeBOBean" );
        bd.setDisplayName( "AppraiseeBOBean" );
        
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
        // Declare MethodDescriptor for getDevlopAttributes(kraDevlop)
        //
        __bc_paramNames = _methodParamMap.get(_getDevlopAttributesMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_getDevlopAttributesMethod, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for getCostAttributes(kraCost)
        //
        __bc_paramNames = _methodParamMap.get(_getCostAttributesMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_getCostAttributesMethod, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for checkAppraiseeComments(appraiseeId)
        //
        __bc_paramNames = _methodParamMap.get(_checkAppraiseeCommentsMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_checkAppraiseeCommentsMethod, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for getQualAttributes(kraQuality)
        //
        __bc_paramNames = _methodParamMap.get(_getQualAttributesMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_getQualAttributesMethod, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for authenticateAppraisee(empNum, appraiseeQuarter, appraiseeYear)
        //
        __bc_paramNames = _methodParamMap.get(_authenticateAppraiseeMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_authenticateAppraiseeMethod, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for updateAppraiseeStatus(appraiseeId, quarter, quarterYear)
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
        // Declare MethodDescriptor for updateAppraiseeComments(appraiseeComments, empNum)
        //
        __bc_paramNames = _methodParamMap.get(_updateAppraiseeCommentsMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_updateAppraiseeCommentsMethod, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for getAppraiseeSBU(deptId)
        //
        __bc_paramNames = _methodParamMap.get(_getAppraiseeSBUMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_getAppraiseeSBUMethod, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for getAchievementsData(appraiseeId, appraisalYear)
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
        // Declare MethodDescriptor for getReviewerName(reviewerId)
        //
        __bc_paramNames = _methodParamMap.get(_getReviewerNameMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_getReviewerNameMethod, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for getGoalData(appraiseeId, appraisalYear)
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
        // Declare MethodDescriptor for getAppraiserName(appraiserId)
        //
        __bc_paramNames = _methodParamMap.get(_getAppraiserNameMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_getAppraiserNameMethod, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for insertAppraiseeComments(appraiseeComments, empNum)
        //
        __bc_paramNames = _methodParamMap.get(_insertAppraiseeCommentsMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_insertAppraiseeCommentsMethod, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for getKraData(bandId, appraisalYear, appraisalQuarter, appraiseeId, departmentId)
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
        // Declare MethodDescriptor for getCustomerAttributes(kraCustomer)
        //
        __bc_paramNames = _methodParamMap.get(_getCustomerAttributesMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_getCustomerAttributesMethod, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for getDevNeedsData(appraiseeId, appraisalYear)
        //
        __bc_paramNames = _methodParamMap.get(_getDevNeedsDataMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_getDevNeedsDataMethod, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for insertOrUpdateAppraiseeData(formData, appraiseeId)
        //
        __bc_paramNames = _methodParamMap.get(_insertOrUpdateAppraiseeDataMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_insertOrUpdateAppraiseeDataMethod, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        
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
        
        pd = new PropertyDescriptor( "controlImplementation" , com.defiance.ideal.qpd.appraisee.bo.AppraiseeBOBean.class, "getControlImplementation", "setControlImplementation");
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
