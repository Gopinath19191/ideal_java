package com.defiance.ideal.qpd.managers.db;

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
public class ManagerDBBeanBeanInfo
extends com.defiance.ideal.shared.DBConnectivityBeanBeanInfo
{
    static final Method _updateNormalisedSubmitStatusMethod;
    static final Method _getAppraiseesByHrAndNormalizerAndSBUMethod;
    static final Method _getDevelopmentDataMethod;
    static final Method _getAppraiseesByNormalizer1Method;
    static final Method _updateNormalizerRatingMethod;
    static final Method _getAppraiseesByFinanceMethod;
    static final Method _updateNormalisedReviewerRatingMethod;
    static final Method _getAppraiseesByReviewerMethod;
    static final Method _getAppraiseesByNormalizerAndReviewerMethod;
    static final Method _getKraDataMethod;
    static final Method _getAppraisersByReviewerMethod;
    static final Method _getGoalDataMethod;
    static final Method _getCompanyStructureBrHrMethod;
    static final Method _updateSendbackStatusMethod;
    static final Method _getAppraisersByNormalizerMethod;
    static final Method _getAppraiseesByNormalizerAndAppraiserReviewerMethod;
    static final Method _getAppraiseesByHrAndSBUMethod;
    static final Method _getAppraiseesByHrAndNormalizerMethod;
    static final Method _updateSubmitStatusCorrectedNormalizerRatingMethod;
    static final Method _getAppraiseesByHr0Method;
    static final Method _getNormalizersByHrMethod;
    static final Method _updateFinalSubmitStatusMethod;
    static final Method _updateCorrectedNormalizerRatingMethod;
    static final Method _getAppraiseesByNormalizerAndAppraiserMethod;
    static final Method _updateSubmitStatusMethod;
    static final Method _getAppraiseesByNormalizer0Method;
    static final Method _getAchievementsDataMethod;
    static final Method _getSelectedAppraiseeDetailsMethod;
    static final Method _updateReviewerRatingMethod;
    static final Method _getAppraiseesByHr1Method;
    static final Method _getReviewersByNormalizerMethod;
    static final Method _updateSubmitStatusNormalizerMethod;
    static final Method _getAppraiseeCountMethod;
    static final Method _getAppraiseesByReviewerAndAppraiserMethod;
    
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
            _updateNormalisedSubmitStatusMethod = com.defiance.ideal.qpd.managers.db.ManagerDB.class.getMethod("updateNormalisedSubmitStatus", new Class [] {java.lang.String.class, int.class, int.class, int.class, int.class, java.lang.String.class});
            _methodParamMap.put(_updateNormalisedSubmitStatusMethod, new String [] { "reviewerId", "appraisalYear", "appraiseeId", "reviewerRating", "submitStatus", "justifyRatingReviewer" });
            _getAppraiseesByHrAndNormalizerAndSBUMethod = com.defiance.ideal.qpd.managers.db.ManagerDB.class.getMethod("getAppraiseesByHrAndNormalizerAndSBU", new Class [] {java.lang.String.class, int.class, java.lang.String.class, java.lang.String.class, int.class});
            _methodParamMap.put(_getAppraiseesByHrAndNormalizerAndSBUMethod, new String [] { "hrId", "appraisalYear", "selectedNormalizerId", "selectedSBUDepartmentId", "triggerStatus" });
            _getDevelopmentDataMethod = com.defiance.ideal.qpd.managers.db.ManagerDB.class.getMethod("getDevelopmentData", new Class [] {int.class, int.class});
            _methodParamMap.put(_getDevelopmentDataMethod, new String [] { "appraisalYear", "appraiseeId" });
            _getAppraiseesByNormalizer1Method = com.defiance.ideal.qpd.managers.db.ManagerDB.class.getMethod("getAppraiseesByNormalizer", new Class [] {java.lang.String.class, int.class, int.class, int.class, java.lang.String.class});
            _methodParamMap.put(_getAppraiseesByNormalizer1Method, new String [] { "normalizerId", "appraisalYear", "submitStatusCheck", "triggerStatus", "whereCondition" });
            _updateNormalizerRatingMethod = com.defiance.ideal.qpd.managers.db.ManagerDB.class.getMethod("updateNormalizerRating", new Class [] {java.lang.String.class, int.class, int.class, int.class, int.class, java.lang.String.class, java.lang.String.class});
            _methodParamMap.put(_updateNormalizerRatingMethod, new String [] { "normalizerId", "appraisalQuarter", "appraisalYear", "appraiseeId", "normalizerRating", "justifyRatingReviewer", "normalizerPromotionRec" });
            _getAppraiseesByFinanceMethod = com.defiance.ideal.qpd.managers.db.ManagerDB.class.getMethod("getAppraiseesByFinance", new Class [] {java.lang.String.class, int.class, int.class, int.class});
            _methodParamMap.put(_getAppraiseesByFinanceMethod, new String [] { "financeId", "appraisalYear", "submitStatusCheck", "triggerStatus" });
            _updateNormalisedReviewerRatingMethod = com.defiance.ideal.qpd.managers.db.ManagerDB.class.getMethod("updateNormalisedReviewerRating", new Class [] {java.lang.String.class, int.class, int.class, int.class, java.lang.String.class});
            _methodParamMap.put(_updateNormalisedReviewerRatingMethod, new String [] { "reviewerId", "appraisalYear", "appraiseeId", "reviewerRating", "justifyRatingReviewer" });
            _getAppraiseesByReviewerMethod = com.defiance.ideal.qpd.managers.db.ManagerDB.class.getMethod("getAppraiseesByReviewer", new Class [] {java.lang.String.class, int.class, int.class, int.class});
            _methodParamMap.put(_getAppraiseesByReviewerMethod, new String [] { "reviewerId", "appraisalYear", "submitStatus", "triggerStatus" });
            _getAppraiseesByNormalizerAndReviewerMethod = com.defiance.ideal.qpd.managers.db.ManagerDB.class.getMethod("getAppraiseesByNormalizerAndReviewer", new Class [] {java.lang.String.class, java.lang.String.class, int.class, int.class, int.class});
            _methodParamMap.put(_getAppraiseesByNormalizerAndReviewerMethod, new String [] { "selectedReviewerId", "normalizerId", "appraisalYear", "submitStatusCheck", "triggerStatus" });
            _getKraDataMethod = com.defiance.ideal.qpd.managers.db.ManagerDB.class.getMethod("getKraData", new Class [] {int.class, int.class, int.class, int.class});
            _methodParamMap.put(_getKraDataMethod, new String [] { "bandId", "appraisalYear", "appraiseeId", "departmentId" });
            _getAppraisersByReviewerMethod = com.defiance.ideal.qpd.managers.db.ManagerDB.class.getMethod("getAppraisersByReviewer", new Class [] {java.lang.String.class, int.class, int.class, int.class});
            _methodParamMap.put(_getAppraisersByReviewerMethod, new String [] { "reviewerId", "appraisalYear", "submitStatus", "triggerStatus" });
            _getGoalDataMethod = com.defiance.ideal.qpd.managers.db.ManagerDB.class.getMethod("getGoalData", new Class [] {int.class, int.class});
            _methodParamMap.put(_getGoalDataMethod, new String [] { "appraisalYear", "appraiseeId" });
            _getCompanyStructureBrHrMethod = com.defiance.ideal.qpd.managers.db.ManagerDB.class.getMethod("getCompanyStructureBrHr", new Class [] {java.lang.String.class, int.class, int.class, int.class});
            _methodParamMap.put(_getCompanyStructureBrHrMethod, new String [] { "hrId", "appraisalQuarter", "appraisalYear", "triggerStatus" });
            _updateSendbackStatusMethod = com.defiance.ideal.qpd.managers.db.ManagerDB.class.getMethod("updateSendbackStatus", new Class [] {java.lang.String.class, int.class, int.class, int.class, int.class});
            _methodParamMap.put(_updateSendbackStatusMethod, new String [] { "hrId", "appraisalYear", "appraiseeId", "submitStatusSendback", "sendbackStatus" });
            _getAppraisersByNormalizerMethod = com.defiance.ideal.qpd.managers.db.ManagerDB.class.getMethod("getAppraisersByNormalizer", new Class [] {java.lang.String.class, int.class, int.class, int.class});
            _methodParamMap.put(_getAppraisersByNormalizerMethod, new String [] { "normalizerId", "appraisalYear", "submitStatusCheck", "triggerStatus" });
            _getAppraiseesByNormalizerAndAppraiserReviewerMethod = com.defiance.ideal.qpd.managers.db.ManagerDB.class.getMethod("getAppraiseesByNormalizerAndAppraiserReviewer", new Class [] {java.lang.String.class, java.lang.String.class, java.lang.String.class, int.class, int.class, int.class});
            _methodParamMap.put(_getAppraiseesByNormalizerAndAppraiserReviewerMethod, new String [] { "selectedAppraiserId", "selectedReviewerId", "normalizerId", "appraisalYear", "submitStatusCheck", "triggerStatus" });
            _getAppraiseesByHrAndSBUMethod = com.defiance.ideal.qpd.managers.db.ManagerDB.class.getMethod("getAppraiseesByHrAndSBU", new Class [] {java.lang.String.class, int.class, java.lang.String.class, int.class});
            _methodParamMap.put(_getAppraiseesByHrAndSBUMethod, new String [] { "hrId", "appraisalYear", "selectedSBUDepartmentId", "triggerStatus" });
            _getAppraiseesByHrAndNormalizerMethod = com.defiance.ideal.qpd.managers.db.ManagerDB.class.getMethod("getAppraiseesByHrAndNormalizer", new Class [] {java.lang.String.class, int.class, java.lang.String.class, int.class});
            _methodParamMap.put(_getAppraiseesByHrAndNormalizerMethod, new String [] { "hrId", "appraisalYear", "selectedNormalizerId", "triggerStatus" });
            _updateSubmitStatusCorrectedNormalizerRatingMethod = com.defiance.ideal.qpd.managers.db.ManagerDB.class.getMethod("updateSubmitStatusCorrectedNormalizerRating", new Class [] {java.lang.String.class, int.class, int.class, int.class, int.class, java.lang.String.class, java.lang.String.class});
            _methodParamMap.put(_updateSubmitStatusCorrectedNormalizerRatingMethod, new String [] { "normalizerId", "appraisalYear", "appraiseeId", "normalizerRating", "submitStatus", "normalizerComments", "normalizerPromotionRec" });
            _getAppraiseesByHr0Method = com.defiance.ideal.qpd.managers.db.ManagerDB.class.getMethod("getAppraiseesByHr", new Class [] {java.lang.String.class, int.class, int.class, int.class});
            _methodParamMap.put(_getAppraiseesByHr0Method, new String [] { "hrId", "appraisalYear", "submitStatus", "triggerStatus" });
            _getNormalizersByHrMethod = com.defiance.ideal.qpd.managers.db.ManagerDB.class.getMethod("getNormalizersByHr", new Class [] {java.lang.String.class, int.class, int.class});
            _methodParamMap.put(_getNormalizersByHrMethod, new String [] { "hrId", "appraisalYear", "triggerStatus" });
            _updateFinalSubmitStatusMethod = com.defiance.ideal.qpd.managers.db.ManagerDB.class.getMethod("updateFinalSubmitStatus", new Class [] {java.lang.String.class, int.class, int.class, int.class});
            _methodParamMap.put(_updateFinalSubmitStatusMethod, new String [] { "hrId", "appraisalYear", "appraiseeId", "submitStatus" });
            _updateCorrectedNormalizerRatingMethod = com.defiance.ideal.qpd.managers.db.ManagerDB.class.getMethod("updateCorrectedNormalizerRating", new Class [] {java.lang.String.class, int.class, int.class, int.class, java.lang.String.class, java.lang.String.class});
            _methodParamMap.put(_updateCorrectedNormalizerRatingMethod, new String [] { "normalizerId", "appraisalYear", "appraiseeId", "normalizerRating", "justifyRatingReviewer", "normalizerPromotionRec" });
            _getAppraiseesByNormalizerAndAppraiserMethod = com.defiance.ideal.qpd.managers.db.ManagerDB.class.getMethod("getAppraiseesByNormalizerAndAppraiser", new Class [] {java.lang.String.class, java.lang.String.class, int.class, int.class, int.class});
            _methodParamMap.put(_getAppraiseesByNormalizerAndAppraiserMethod, new String [] { "selectedAppraiserId", "normalizerId", "appraisalYear", "submitStatusCheck", "triggerStatus" });
            _updateSubmitStatusMethod = com.defiance.ideal.qpd.managers.db.ManagerDB.class.getMethod("updateSubmitStatus", new Class [] {java.lang.String.class, int.class, int.class, int.class, int.class, java.lang.String.class, java.lang.String.class});
            _methodParamMap.put(_updateSubmitStatusMethod, new String [] { "reviewerId", "appraisalYear", "appraiseeId", "reviewerRating", "submitStatus", "justifyRatingReviewer", "reviewerPromotion" });
            _getAppraiseesByNormalizer0Method = com.defiance.ideal.qpd.managers.db.ManagerDB.class.getMethod("getAppraiseesByNormalizer", new Class [] {java.lang.String.class, int.class, int.class, int.class});
            _methodParamMap.put(_getAppraiseesByNormalizer0Method, new String [] { "normalizerId", "appraisalYear", "submitStatusCheck", "triggerStatus" });
            _getAchievementsDataMethod = com.defiance.ideal.qpd.managers.db.ManagerDB.class.getMethod("getAchievementsData", new Class [] {int.class, int.class});
            _methodParamMap.put(_getAchievementsDataMethod, new String [] { "appraisalYear", "appraiseeId" });
            _getSelectedAppraiseeDetailsMethod = com.defiance.ideal.qpd.managers.db.ManagerDB.class.getMethod("getSelectedAppraiseeDetails", new Class [] {int.class, int.class});
            _methodParamMap.put(_getSelectedAppraiseeDetailsMethod, new String [] { "appraiseeId", "appraisalYear" });
            _updateReviewerRatingMethod = com.defiance.ideal.qpd.managers.db.ManagerDB.class.getMethod("updateReviewerRating", new Class [] {java.lang.String.class, int.class, int.class, int.class, java.lang.String.class, java.lang.String.class});
            _methodParamMap.put(_updateReviewerRatingMethod, new String [] { "reviewerId", "appraisalYear", "appraiseeId", "reviewerRating", "justifyRatingReviewer", "reviewerPromotion" });
            _getAppraiseesByHr1Method = com.defiance.ideal.qpd.managers.db.ManagerDB.class.getMethod("getAppraiseesByHr", new Class [] {java.lang.String.class, int.class, int.class, int.class, java.lang.String.class});
            _methodParamMap.put(_getAppraiseesByHr1Method, new String [] { "hrId", "appraisalYear", "submitStatus", "triggerStatus", "whereCondition" });
            _getReviewersByNormalizerMethod = com.defiance.ideal.qpd.managers.db.ManagerDB.class.getMethod("getReviewersByNormalizer", new Class [] {java.lang.String.class, int.class, int.class, int.class});
            _methodParamMap.put(_getReviewersByNormalizerMethod, new String [] { "normalizerId", "appraisalYear", "submitStatusCheck", "triggerStatus" });
            _updateSubmitStatusNormalizerMethod = com.defiance.ideal.qpd.managers.db.ManagerDB.class.getMethod("updateSubmitStatusNormalizer", new Class [] {java.lang.String.class, int.class, int.class, int.class, int.class, java.lang.String.class, java.lang.String.class});
            _methodParamMap.put(_updateSubmitStatusNormalizerMethod, new String [] { "normalizerId", "appraisalYear", "appraiseeId", "normalizerRating", "submitStatus", "normalizerComments", "normalizerPromotionRec" });
            _getAppraiseeCountMethod = com.defiance.ideal.qpd.managers.db.ManagerDB.class.getMethod("getAppraiseeCount", new Class [] {java.lang.String.class, int.class, java.lang.String.class, int.class});
            _methodParamMap.put(_getAppraiseeCountMethod, new String [] { "referenceId", "appraisalYear", "referenceType", "triggerStatus" });
            _getAppraiseesByReviewerAndAppraiserMethod = com.defiance.ideal.qpd.managers.db.ManagerDB.class.getMethod("getAppraiseesByReviewerAndAppraiser", new Class [] {java.lang.String.class, java.lang.String.class, int.class, int.class, int.class});
            _methodParamMap.put(_getAppraiseesByReviewerAndAppraiserMethod, new String [] { "selectedAppraiserId", "reviewerId", "appraisalYear", "submitStatus", "triggerStatus" });
        }
        catch (NoSuchMethodException __bc_nsme)
        {
            throw new ExceptionInInitializerError(__bc_nsme);
        }
    }
    
    /**
    * Default null-arg constructor used to create a new BeanInfo instance
    */
    public ManagerDBBeanBeanInfo()
    {
        super(com.defiance.ideal.qpd.managers.db.ManagerDBBean.class);
    }
    
    /**
    * Protected constructor used if this BeanInfo class is extended.
    */
    protected ManagerDBBeanBeanInfo(Class beanClass)
    {
        super(beanClass);
    }
    
    // java.beans.BeanInfo.getBeanDescriptor
    public BeanDescriptor getBeanDescriptor()
    {
        BeanDescriptor bd = new BeanDescriptor(com.defiance.ideal.qpd.managers.db.ManagerDBBean.class);
        bd.setName( "ManagerDBBean" );
        bd.setDisplayName( "ManagerDBBean" );
        
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
        // Declare MethodDescriptor for updateNormalisedSubmitStatus(reviewerId, appraisalYear, appraiseeId, reviewerRating, submitStatus, justifyRatingReviewer)
        //
        __bc_paramNames = _methodParamMap.get(_updateNormalisedSubmitStatusMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_updateNormalisedSubmitStatusMethod, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for getAppraiseesByHrAndNormalizerAndSBU(hrId, appraisalYear, selectedNormalizerId, selectedSBUDepartmentId, triggerStatus)
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
        // Declare MethodDescriptor for getAppraiseesByNormalizer(normalizerId, appraisalYear, submitStatusCheck, triggerStatus, whereCondition)
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
        // Declare MethodDescriptor for updateNormalizerRating(normalizerId, appraisalQuarter, appraisalYear, appraiseeId, normalizerRating, justifyRatingReviewer, normalizerPromotionRec)
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
        // Declare MethodDescriptor for getAppraiseesByFinance(financeId, appraisalYear, submitStatusCheck, triggerStatus)
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
        // Declare MethodDescriptor for updateNormalisedReviewerRating(reviewerId, appraisalYear, appraiseeId, reviewerRating, justifyRatingReviewer)
        //
        __bc_paramNames = _methodParamMap.get(_updateNormalisedReviewerRatingMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_updateNormalisedReviewerRatingMethod, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for getAppraiseesByReviewer(reviewerId, appraisalYear, submitStatus, triggerStatus)
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
        // Declare MethodDescriptor for getAppraiseesByNormalizerAndReviewer(selectedReviewerId, normalizerId, appraisalYear, submitStatusCheck, triggerStatus)
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
        // Declare MethodDescriptor for getKraData(bandId, appraisalYear, appraiseeId, departmentId)
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
        // Declare MethodDescriptor for getAppraisersByReviewer(reviewerId, appraisalYear, submitStatus, triggerStatus)
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
        // Declare MethodDescriptor for getCompanyStructureBrHr(hrId, appraisalQuarter, appraisalYear, triggerStatus)
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
        // Declare MethodDescriptor for updateSendbackStatus(hrId, appraisalYear, appraiseeId, submitStatusSendback, sendbackStatus)
        //
        __bc_paramNames = _methodParamMap.get(_updateSendbackStatusMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_updateSendbackStatusMethod, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for getAppraisersByNormalizer(normalizerId, appraisalYear, submitStatusCheck, triggerStatus)
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
        // Declare MethodDescriptor for getAppraiseesByNormalizerAndAppraiserReviewer(selectedAppraiserId, selectedReviewerId, normalizerId, appraisalYear, submitStatusCheck, triggerStatus)
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
        // Declare MethodDescriptor for getAppraiseesByHrAndSBU(hrId, appraisalYear, selectedSBUDepartmentId, triggerStatus)
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
        // Declare MethodDescriptor for getAppraiseesByHrAndNormalizer(hrId, appraisalYear, selectedNormalizerId, triggerStatus)
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
        // Declare MethodDescriptor for updateSubmitStatusCorrectedNormalizerRating(normalizerId, appraisalYear, appraiseeId, normalizerRating, submitStatus, normalizerComments, normalizerPromotionRec)
        //
        __bc_paramNames = _methodParamMap.get(_updateSubmitStatusCorrectedNormalizerRatingMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_updateSubmitStatusCorrectedNormalizerRatingMethod, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for getAppraiseesByHr(hrId, appraisalYear, submitStatus, triggerStatus)
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
        // Declare MethodDescriptor for getNormalizersByHr(hrId, appraisalYear, triggerStatus)
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
        // Declare MethodDescriptor for updateFinalSubmitStatus(hrId, appraisalYear, appraiseeId, submitStatus)
        //
        __bc_paramNames = _methodParamMap.get(_updateFinalSubmitStatusMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_updateFinalSubmitStatusMethod, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for updateCorrectedNormalizerRating(normalizerId, appraisalYear, appraiseeId, normalizerRating, justifyRatingReviewer, normalizerPromotionRec)
        //
        __bc_paramNames = _methodParamMap.get(_updateCorrectedNormalizerRatingMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_updateCorrectedNormalizerRatingMethod, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for getAppraiseesByNormalizerAndAppraiser(selectedAppraiserId, normalizerId, appraisalYear, submitStatusCheck, triggerStatus)
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
        
        //
        // Declare MethodDescriptor for updateSubmitStatus(reviewerId, appraisalYear, appraiseeId, reviewerRating, submitStatus, justifyRatingReviewer, reviewerPromotion)
        //
        __bc_paramNames = _methodParamMap.get(_updateSubmitStatusMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_updateSubmitStatusMethod, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for getAppraiseesByNormalizer(normalizerId, appraisalYear, submitStatusCheck, triggerStatus)
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
        // Declare MethodDescriptor for getSelectedAppraiseeDetails(appraiseeId, appraisalYear)
        //
        __bc_paramNames = _methodParamMap.get(_getSelectedAppraiseeDetailsMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_getSelectedAppraiseeDetailsMethod, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for updateReviewerRating(reviewerId, appraisalYear, appraiseeId, reviewerRating, justifyRatingReviewer, reviewerPromotion)
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
        // Declare MethodDescriptor for getAppraiseesByHr(hrId, appraisalYear, submitStatus, triggerStatus, whereCondition)
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
        // Declare MethodDescriptor for getReviewersByNormalizer(normalizerId, appraisalYear, submitStatusCheck, triggerStatus)
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
        // Declare MethodDescriptor for updateSubmitStatusNormalizer(normalizerId, appraisalYear, appraiseeId, normalizerRating, submitStatus, normalizerComments, normalizerPromotionRec)
        //
        __bc_paramNames = _methodParamMap.get(_updateSubmitStatusNormalizerMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_updateSubmitStatusNormalizerMethod, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for getAppraiseeCount(referenceId, appraisalYear, referenceType, triggerStatus)
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
        // Declare MethodDescriptor for getAppraiseesByReviewerAndAppraiser(selectedAppraiserId, reviewerId, appraisalYear, submitStatus, triggerStatus)
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
        // Add method descriptors from parent BeanInfo
        //
        super.initMethodDescriptors(methodDescriptors, index);
    }
    
    public MethodDescriptor [] getMethodDescriptors()
    {
        MethodDescriptor [] __bc_methodDescriptors = new MethodDescriptor[37];
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
        
        pd = new PropertyDescriptor( "controlImplementation" , com.defiance.ideal.qpd.managers.db.ManagerDBBean.class, "getControlImplementation", "setControlImplementation");
        propDescriptors[index++] = pd;
        
        
        //
        // Add property descriptors from parent BeanInfo
        //
        super.initPropertyDescriptors(propDescriptors, index);
    }
    
    // java.beans.BeanInfo.getPropertyDescriptors
    public PropertyDescriptor [] getPropertyDescriptors()
    {
        PropertyDescriptor [] __bc_propDescriptors = new PropertyDescriptor[31];
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
