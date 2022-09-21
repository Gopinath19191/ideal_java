package com.defiance.ideal.qpd.managers.dao;

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
public class ManagerDAOBeanBeanInfo
extends org.apache.beehive.controls.runtime.bean.ControlBeanInfo
{
    static final Method _getAppraiseesByNormalizerAndAppraiserReviewerMethod;
    static final Method _getAppraiseesByNormalizer0Method;
    static final Method _getAppraiseesByNormalizer1Method;
    static final Method _getAppraiseeCountMethod;
    static final Method _getCompanyStructureBrHrMethod;
    static final Method _getAppraiseesByNormalizerAndReviewerMethod;
    static final Method _updateNormalizerRatingMethod;
    static final Method _getDevelopmentDataMethod;
    static final Method _getSubmitStatusMethod;
    static final Method _updateFinalStatusMethod;
    static final Method _getAppraiseesByHr1Method;
    static final Method _getAppraiseesByReviewerMethod;
    static final Method _getGoalDataMethod;
    static final Method _getAppraiseesByHr0Method;
    static final Method _getAppraisersByReviewerMethod;
    static final Method _getAppraiseesByHrAndNormalizerMethod;
    static final Method _getNormalizersByHrMethod;
    static final Method _getAppraiseesByFinanceMethod;
    static final Method _getKraDataMethod;
    static final Method _getReviewersByNormalizerMethod;
    static final Method _getAppraiseesByHrAndSBUMethod;
    static final Method _getAppraiseesByHrAndNormalizerAndSBUMethod;
    static final Method _updateReviewerRatingMethod;
    static final Method _getAchievementsDataMethod;
    static final Method _getAppraiseesByReviewerAndAppraiserMethod;
    static final Method _getAppraisersByNormalizerMethod;
    static final Method _getAppraiseesByNormalizerAndAppraiserMethod;
    
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
            _getAppraiseesByNormalizerAndAppraiserReviewerMethod = com.defiance.ideal.qpd.managers.dao.ManagerDAO.class.getMethod("getAppraiseesByNormalizerAndAppraiserReviewer", new Class [] {java.lang.String.class, java.lang.String.class, java.lang.String.class, int.class, int.class});
            _methodParamMap.put(_getAppraiseesByNormalizerAndAppraiserReviewerMethod, new String [] { "selectedAppraiserId", "selectedReviewerId", "normalizerId", "appraisalQuarter", "appraisalYear" });
            _getAppraiseesByNormalizer0Method = com.defiance.ideal.qpd.managers.dao.ManagerDAO.class.getMethod("getAppraiseesByNormalizer", new Class [] {java.lang.String.class, int.class, int.class});
            _methodParamMap.put(_getAppraiseesByNormalizer0Method, new String [] { "normalizerId", "appraisalQuarter", "appraisalYear" });
            _getAppraiseesByNormalizer1Method = com.defiance.ideal.qpd.managers.dao.ManagerDAO.class.getMethod("getAppraiseesByNormalizer", new Class [] {java.lang.String.class, int.class, int.class, java.lang.String.class});
            _methodParamMap.put(_getAppraiseesByNormalizer1Method, new String [] { "normalizerId", "appraisalQuarter", "appraisalYear", "whereCondition" });
            _getAppraiseeCountMethod = com.defiance.ideal.qpd.managers.dao.ManagerDAO.class.getMethod("getAppraiseeCount", new Class [] {java.lang.String.class, int.class, int.class, java.lang.String.class});
            _methodParamMap.put(_getAppraiseeCountMethod, new String [] { "reviewerId", "appraisalQuarter", "appraisalYear", "referenceType" });
            _getCompanyStructureBrHrMethod = com.defiance.ideal.qpd.managers.dao.ManagerDAO.class.getMethod("getCompanyStructureBrHr", new Class [] {java.lang.String.class, int.class, int.class});
            _methodParamMap.put(_getCompanyStructureBrHrMethod, new String [] { "hrId", "appraisalQuarter", "appraisalYear" });
            _getAppraiseesByNormalizerAndReviewerMethod = com.defiance.ideal.qpd.managers.dao.ManagerDAO.class.getMethod("getAppraiseesByNormalizerAndReviewer", new Class [] {java.lang.String.class, java.lang.String.class, int.class, int.class});
            _methodParamMap.put(_getAppraiseesByNormalizerAndReviewerMethod, new String [] { "selectedReviewerId", "normalizerId", "appraisalQuarter", "appraisalYear" });
            _updateNormalizerRatingMethod = com.defiance.ideal.qpd.managers.dao.ManagerDAO.class.getMethod("updateNormalizerRating", new Class [] {java.lang.String.class, int.class, int.class, com.defiance.ideal.qpd.managers.dto.ManagerDTO.class, java.lang.String.class});
            _methodParamMap.put(_updateNormalizerRatingMethod, new String [] { "normalizerId", "appraisalQuarter", "appraisalYear", "formData", "button" });
            _getDevelopmentDataMethod = com.defiance.ideal.qpd.managers.dao.ManagerDAO.class.getMethod("getDevelopmentData", new Class [] {int.class, int.class});
            _methodParamMap.put(_getDevelopmentDataMethod, new String [] { "appraisalYear", "appraiseeId" });
            _getSubmitStatusMethod = com.defiance.ideal.qpd.managers.dao.ManagerDAO.class.getMethod("getSubmitStatus", new Class [] {int.class, int.class, int.class});
            _methodParamMap.put(_getSubmitStatusMethod, new String [] { "appraiseeId", "appraisalQuarter", "appraisalYear" });
            _updateFinalStatusMethod = com.defiance.ideal.qpd.managers.dao.ManagerDAO.class.getMethod("updateFinalStatus", new Class [] {java.lang.String.class, int.class, int.class, com.defiance.ideal.qpd.managers.dto.ManagerDTO.class, java.lang.String.class});
            _methodParamMap.put(_updateFinalStatusMethod, new String [] { "hrId", "appraisalQuarter", "appraisalYear", "appraiseesbyHr", "button" });
            _getAppraiseesByHr1Method = com.defiance.ideal.qpd.managers.dao.ManagerDAO.class.getMethod("getAppraiseesByHr", new Class [] {java.lang.String.class, int.class, int.class, java.lang.String.class});
            _methodParamMap.put(_getAppraiseesByHr1Method, new String [] { "hrId", "appraisalQuarter", "appraisalYear", "whereCondition" });
            _getAppraiseesByReviewerMethod = com.defiance.ideal.qpd.managers.dao.ManagerDAO.class.getMethod("getAppraiseesByReviewer", new Class [] {java.lang.String.class, int.class, int.class});
            _methodParamMap.put(_getAppraiseesByReviewerMethod, new String [] { "reviewerId", "appraisalQuarter", "appraisalYear" });
            _getGoalDataMethod = com.defiance.ideal.qpd.managers.dao.ManagerDAO.class.getMethod("getGoalData", new Class [] {int.class, int.class});
            _methodParamMap.put(_getGoalDataMethod, new String [] { "appraisalYear", "appraiseeId" });
            _getAppraiseesByHr0Method = com.defiance.ideal.qpd.managers.dao.ManagerDAO.class.getMethod("getAppraiseesByHr", new Class [] {java.lang.String.class, int.class, int.class});
            _methodParamMap.put(_getAppraiseesByHr0Method, new String [] { "hrId", "appraisalQuarter", "appraisalYear" });
            _getAppraisersByReviewerMethod = com.defiance.ideal.qpd.managers.dao.ManagerDAO.class.getMethod("getAppraisersByReviewer", new Class [] {java.lang.String.class, int.class, int.class});
            _methodParamMap.put(_getAppraisersByReviewerMethod, new String [] { "reviewerId", "appraisalQuarter", "appraisalYear" });
            _getAppraiseesByHrAndNormalizerMethod = com.defiance.ideal.qpd.managers.dao.ManagerDAO.class.getMethod("getAppraiseesByHrAndNormalizer", new Class [] {java.lang.String.class, int.class, int.class, java.lang.String.class});
            _methodParamMap.put(_getAppraiseesByHrAndNormalizerMethod, new String [] { "hrId", "appraisalQuarter", "appraisalYear", "selectedNormalizerId" });
            _getNormalizersByHrMethod = com.defiance.ideal.qpd.managers.dao.ManagerDAO.class.getMethod("getNormalizersByHr", new Class [] {java.lang.String.class, int.class, int.class});
            _methodParamMap.put(_getNormalizersByHrMethod, new String [] { "hrId", "appraisalQuarter", "appraisalYear" });
            _getAppraiseesByFinanceMethod = com.defiance.ideal.qpd.managers.dao.ManagerDAO.class.getMethod("getAppraiseesByFinance", new Class [] {java.lang.String.class, int.class, int.class});
            _methodParamMap.put(_getAppraiseesByFinanceMethod, new String [] { "financeId", "appraisalQuarter", "appraisalYear" });
            _getKraDataMethod = com.defiance.ideal.qpd.managers.dao.ManagerDAO.class.getMethod("getKraData", new Class [] {int.class, int.class, int.class, int.class, int.class});
            _methodParamMap.put(_getKraDataMethod, new String [] { "bandId", "appraisalYear", "appraisalQuarter", "appraiseeId", "departmentId" });
            _getReviewersByNormalizerMethod = com.defiance.ideal.qpd.managers.dao.ManagerDAO.class.getMethod("getReviewersByNormalizer", new Class [] {java.lang.String.class, int.class, int.class});
            _methodParamMap.put(_getReviewersByNormalizerMethod, new String [] { "normalizerId", "appraisalQuarter", "appraisalYear" });
            _getAppraiseesByHrAndSBUMethod = com.defiance.ideal.qpd.managers.dao.ManagerDAO.class.getMethod("getAppraiseesByHrAndSBU", new Class [] {java.lang.String.class, int.class, int.class, java.lang.String.class});
            _methodParamMap.put(_getAppraiseesByHrAndSBUMethod, new String [] { "hrId", "appraisalQuarter", "appraisalYear", "selectedSBUDepartmentId" });
            _getAppraiseesByHrAndNormalizerAndSBUMethod = com.defiance.ideal.qpd.managers.dao.ManagerDAO.class.getMethod("getAppraiseesByHrAndNormalizerAndSBU", new Class [] {java.lang.String.class, int.class, int.class, java.lang.String.class, java.lang.String.class});
            _methodParamMap.put(_getAppraiseesByHrAndNormalizerAndSBUMethod, new String [] { "hrId", "appraisalQuarter", "appraisalYear", "selectedNormalizerId", "selectedSBUDepartmentId" });
            _updateReviewerRatingMethod = com.defiance.ideal.qpd.managers.dao.ManagerDAO.class.getMethod("updateReviewerRating", new Class [] {java.lang.String.class, int.class, int.class, com.defiance.ideal.qpd.managers.dto.ManagerDTO.class, java.lang.String.class});
            _methodParamMap.put(_updateReviewerRatingMethod, new String [] { "reviewerId", "appraisalQuarter", "appraisalYear", "formData", "button" });
            _getAchievementsDataMethod = com.defiance.ideal.qpd.managers.dao.ManagerDAO.class.getMethod("getAchievementsData", new Class [] {int.class, int.class});
            _methodParamMap.put(_getAchievementsDataMethod, new String [] { "appraisalYear", "appraiseeId" });
            _getAppraiseesByReviewerAndAppraiserMethod = com.defiance.ideal.qpd.managers.dao.ManagerDAO.class.getMethod("getAppraiseesByReviewerAndAppraiser", new Class [] {java.lang.String.class, java.lang.String.class, int.class, int.class});
            _methodParamMap.put(_getAppraiseesByReviewerAndAppraiserMethod, new String [] { "selectedAppraiserId", "reviewerId", "appraisalQuarter", "appraisalYear" });
            _getAppraisersByNormalizerMethod = com.defiance.ideal.qpd.managers.dao.ManagerDAO.class.getMethod("getAppraisersByNormalizer", new Class [] {java.lang.String.class, int.class, int.class});
            _methodParamMap.put(_getAppraisersByNormalizerMethod, new String [] { "normalizerId", "appraisalQuarter", "appraisalYear" });
            _getAppraiseesByNormalizerAndAppraiserMethod = com.defiance.ideal.qpd.managers.dao.ManagerDAO.class.getMethod("getAppraiseesByNormalizerAndAppraiser", new Class [] {java.lang.String.class, java.lang.String.class, int.class, int.class});
            _methodParamMap.put(_getAppraiseesByNormalizerAndAppraiserMethod, new String [] { "selectedAppraiserId", "normalizerId", "appraisalQuarter", "appraisalYear" });
        }
        catch (NoSuchMethodException __bc_nsme)
        {
            throw new ExceptionInInitializerError(__bc_nsme);
        }
    }
    
    /**
    * Default null-arg constructor used to create a new BeanInfo instance
    */
    public ManagerDAOBeanBeanInfo()
    {
        super(com.defiance.ideal.qpd.managers.dao.ManagerDAOBean.class);
    }
    
    /**
    * Protected constructor used if this BeanInfo class is extended.
    */
    protected ManagerDAOBeanBeanInfo(Class beanClass)
    {
        super(beanClass);
    }
    
    // java.beans.BeanInfo.getBeanDescriptor
    public BeanDescriptor getBeanDescriptor()
    {
        BeanDescriptor bd = new BeanDescriptor(com.defiance.ideal.qpd.managers.dao.ManagerDAOBean.class);
        bd.setName( "ManagerDAOBean" );
        bd.setDisplayName( "ManagerDAOBean" );
        
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
        // Declare MethodDescriptor for getAppraiseesByNormalizerAndAppraiserReviewer(selectedAppraiserId, selectedReviewerId, normalizerId, appraisalQuarter, appraisalYear)
        //
        __bc_paramNames = _methodParamMap.get(_getAppraiseesByNormalizerAndAppraiserReviewerMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_getAppraiseesByNormalizerAndAppraiserReviewerMethod, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for getAppraiseesByNormalizer(normalizerId, appraisalQuarter, appraisalYear)
        //
        __bc_paramNames = _methodParamMap.get(_getAppraiseesByNormalizer0Method);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_getAppraiseesByNormalizer0Method, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for getAppraiseesByNormalizer(normalizerId, appraisalQuarter, appraisalYear, whereCondition)
        //
        __bc_paramNames = _methodParamMap.get(_getAppraiseesByNormalizer1Method);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_getAppraiseesByNormalizer1Method, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for getAppraiseeCount(reviewerId, appraisalQuarter, appraisalYear, referenceType)
        //
        __bc_paramNames = _methodParamMap.get(_getAppraiseeCountMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_getAppraiseeCountMethod, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for getCompanyStructureBrHr(hrId, appraisalQuarter, appraisalYear)
        //
        __bc_paramNames = _methodParamMap.get(_getCompanyStructureBrHrMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_getCompanyStructureBrHrMethod, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for getAppraiseesByNormalizerAndReviewer(selectedReviewerId, normalizerId, appraisalQuarter, appraisalYear)
        //
        __bc_paramNames = _methodParamMap.get(_getAppraiseesByNormalizerAndReviewerMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_getAppraiseesByNormalizerAndReviewerMethod, __bc_paramDescriptors);
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
        // Declare MethodDescriptor for getAppraiseesByHr(hrId, appraisalQuarter, appraisalYear, whereCondition)
        //
        __bc_paramNames = _methodParamMap.get(_getAppraiseesByHr1Method);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_getAppraiseesByHr1Method, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for getAppraiseesByReviewer(reviewerId, appraisalQuarter, appraisalYear)
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
        // Declare MethodDescriptor for getAppraiseesByHr(hrId, appraisalQuarter, appraisalYear)
        //
        __bc_paramNames = _methodParamMap.get(_getAppraiseesByHr0Method);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_getAppraiseesByHr0Method, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for getAppraisersByReviewer(reviewerId, appraisalQuarter, appraisalYear)
        //
        __bc_paramNames = _methodParamMap.get(_getAppraisersByReviewerMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_getAppraisersByReviewerMethod, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for getAppraiseesByHrAndNormalizer(hrId, appraisalQuarter, appraisalYear, selectedNormalizerId)
        //
        __bc_paramNames = _methodParamMap.get(_getAppraiseesByHrAndNormalizerMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_getAppraiseesByHrAndNormalizerMethod, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for getNormalizersByHr(hrId, appraisalQuarter, appraisalYear)
        //
        __bc_paramNames = _methodParamMap.get(_getNormalizersByHrMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_getNormalizersByHrMethod, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for getAppraiseesByFinance(financeId, appraisalQuarter, appraisalYear)
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
        // Declare MethodDescriptor for getReviewersByNormalizer(normalizerId, appraisalQuarter, appraisalYear)
        //
        __bc_paramNames = _methodParamMap.get(_getReviewersByNormalizerMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_getReviewersByNormalizerMethod, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for getAppraiseesByHrAndSBU(hrId, appraisalQuarter, appraisalYear, selectedSBUDepartmentId)
        //
        __bc_paramNames = _methodParamMap.get(_getAppraiseesByHrAndSBUMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_getAppraiseesByHrAndSBUMethod, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for getAppraiseesByHrAndNormalizerAndSBU(hrId, appraisalQuarter, appraisalYear, selectedNormalizerId, selectedSBUDepartmentId)
        //
        __bc_paramNames = _methodParamMap.get(_getAppraiseesByHrAndNormalizerAndSBUMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_getAppraiseesByHrAndNormalizerAndSBUMethod, __bc_paramDescriptors);
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
        // Declare MethodDescriptor for getAppraiseesByReviewerAndAppraiser(selectedAppraiserId, reviewerId, appraisalQuarter, appraisalYear)
        //
        __bc_paramNames = _methodParamMap.get(_getAppraiseesByReviewerAndAppraiserMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_getAppraiseesByReviewerAndAppraiserMethod, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for getAppraisersByNormalizer(normalizerId, appraisalQuarter, appraisalYear)
        //
        __bc_paramNames = _methodParamMap.get(_getAppraisersByNormalizerMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_getAppraisersByNormalizerMethod, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for getAppraiseesByNormalizerAndAppraiser(selectedAppraiserId, normalizerId, appraisalQuarter, appraisalYear)
        //
        __bc_paramNames = _methodParamMap.get(_getAppraiseesByNormalizerAndAppraiserMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_getAppraiseesByNormalizerAndAppraiserMethod, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        
    }
    
    public MethodDescriptor [] getMethodDescriptors()
    {
        MethodDescriptor [] __bc_methodDescriptors = new MethodDescriptor[27];
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
        
        pd = new PropertyDescriptor( "controlImplementation" , com.defiance.ideal.qpd.managers.dao.ManagerDAOBean.class, "getControlImplementation", "setControlImplementation");
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
