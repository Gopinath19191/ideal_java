package com.defiance.ideal.qpd.appraisee.db;

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
public class AppraiseeDBBeanBeanInfo
extends com.defiance.ideal.shared.DBConnectivityBeanBeanInfo
{
    static final Method _updateAppraiseeData1Method;
    static final Method _costAttribsMethod;
    static final Method _appraiseeCommentsInsertMethod;
    static final Method _devlopAttribsMethod;
    static final Method _updateAppraiseeData0Method;
    static final Method _insertOrUpdateGoalsMethod;
    static final Method _appraiseeCommentsUpdateMethod;
    static final Method _chkAppraiseeCommentsMethod;
    static final Method _authenAppraiseeMethod;
    static final Method _insertAppraiseeDataMethod;
    static final Method _getKraDataMethod;
    static final Method _getGoalDataMethod;
    static final Method _appraiseeSBUMethod;
    static final Method _insertOrUpdateDevNeedsMethod;
    static final Method _getDevNeedsDataMethod;
    static final Method _setDeleteAchievementsMethod;
    static final Method _reviewerNameMethod;
    static final Method _insertOrUpdateAchievementsMethod;
    static final Method _setDeleteGoalMethod;
    static final Method _updateAppraiseeStatusMethod;
    static final Method _customerAttribsMethod;
    static final Method _qualAttribsMethod;
    static final Method _getAchievementsDataMethod;
    static final Method _appraiserNameMethod;
    static final Method _setDeleteDevNeedsMethod;
    
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
            _updateAppraiseeData1Method = com.defiance.ideal.qpd.appraisee.db.AppraiseeDB.class.getMethod("updateAppraiseeData", new Class [] {int.class, java.lang.String.class, int.class, int.class});
            _methodParamMap.put(_updateAppraiseeData1Method, new String [] { "kraQtrId", "selfComments", "appraiseeId", "qpdKraId" });
            _costAttribsMethod = com.defiance.ideal.qpd.appraisee.db.AppraiseeDB.class.getMethod("costAttribs", new Class [] {java.lang.String.class});
            _methodParamMap.put(_costAttribsMethod, new String [] { "kraCost" });
            _appraiseeCommentsInsertMethod = com.defiance.ideal.qpd.appraisee.db.AppraiseeDB.class.getMethod("appraiseeCommentsInsert", new Class [] {int.class, java.lang.String.class});
            _methodParamMap.put(_appraiseeCommentsInsertMethod, new String [] { "appraiseeId", "comment" });
            _devlopAttribsMethod = com.defiance.ideal.qpd.appraisee.db.AppraiseeDB.class.getMethod("devlopAttribs", new Class [] {java.lang.String.class});
            _methodParamMap.put(_devlopAttribsMethod, new String [] { "kraDevlop" });
            _updateAppraiseeData0Method = com.defiance.ideal.qpd.appraisee.db.AppraiseeDB.class.getMethod("updateAppraiseeData", new Class [] {com.defiance.ideal.qpd.appraisee.dto.MyAppraisalFormDTO.class, int.class});
            _methodParamMap.put(_updateAppraiseeData0Method, new String [] { "formData", "appraiseeId" });
            _insertOrUpdateGoalsMethod = com.defiance.ideal.qpd.appraisee.db.AppraiseeDB.class.getMethod("insertOrUpdateGoals", new Class [] {int.class, java.lang.String.class, int.class, int.class, int.class});
            _methodParamMap.put(_insertOrUpdateGoalsMethod, new String [] { "goalId", "goalData", "appraisalYear", "appraiseeId", "deleted" });
            _appraiseeCommentsUpdateMethod = com.defiance.ideal.qpd.appraisee.db.AppraiseeDB.class.getMethod("appraiseeCommentsUpdate", new Class [] {int.class, java.lang.String.class, int.class});
            _methodParamMap.put(_appraiseeCommentsUpdateMethod, new String [] { "appraiseeId", "comment", "kraQtrId" });
            _chkAppraiseeCommentsMethod = com.defiance.ideal.qpd.appraisee.db.AppraiseeDB.class.getMethod("chkAppraiseeComments", new Class [] {int.class});
            _methodParamMap.put(_chkAppraiseeCommentsMethod, new String [] { "appraiseeId" });
            _authenAppraiseeMethod = com.defiance.ideal.qpd.appraisee.db.AppraiseeDB.class.getMethod("authenAppraisee", new Class [] {java.lang.String.class, int.class});
            _methodParamMap.put(_authenAppraiseeMethod, new String [] { "empNum", "appraiseeYear" });
            _insertAppraiseeDataMethod = com.defiance.ideal.qpd.appraisee.db.AppraiseeDB.class.getMethod("insertAppraiseeData", new Class [] {int.class, java.lang.String.class, int.class});
            _methodParamMap.put(_insertAppraiseeDataMethod, new String [] { "kraQtrId", "selfComments", "appraiseeId" });
            _getKraDataMethod = com.defiance.ideal.qpd.appraisee.db.AppraiseeDB.class.getMethod("getKraData", new Class [] {int.class, int.class, java.lang.String.class, int.class, int.class});
            _methodParamMap.put(_getKraDataMethod, new String [] { "bandId", "appraisalYear", "appraiseeId", "departmentId", "triggerStatus" });
            _getGoalDataMethod = com.defiance.ideal.qpd.appraisee.db.AppraiseeDB.class.getMethod("getGoalData", new Class [] {int.class, int.class});
            _methodParamMap.put(_getGoalDataMethod, new String [] { "appraiseeId", "appraisalYear" });
            _appraiseeSBUMethod = com.defiance.ideal.qpd.appraisee.db.AppraiseeDB.class.getMethod("appraiseeSBU", new Class [] {int.class});
            _methodParamMap.put(_appraiseeSBUMethod, new String [] { "deptId" });
            _insertOrUpdateDevNeedsMethod = com.defiance.ideal.qpd.appraisee.db.AppraiseeDB.class.getMethod("insertOrUpdateDevNeeds", new Class [] {int.class, java.lang.String.class, int.class, int.class, int.class});
            _methodParamMap.put(_insertOrUpdateDevNeedsMethod, new String [] { "needsId", "developmentNeeds", "appraisalYear", "appraiseeId", "deleted" });
            _getDevNeedsDataMethod = com.defiance.ideal.qpd.appraisee.db.AppraiseeDB.class.getMethod("getDevNeedsData", new Class [] {int.class, int.class});
            _methodParamMap.put(_getDevNeedsDataMethod, new String [] { "appraiseeId", "appraisalYear" });
            _setDeleteAchievementsMethod = com.defiance.ideal.qpd.appraisee.db.AppraiseeDB.class.getMethod("setDeleteAchievements", new Class [] {int.class, int.class});
            _methodParamMap.put(_setDeleteAchievementsMethod, new String [] { "achievementId", "deleted" });
            _reviewerNameMethod = com.defiance.ideal.qpd.appraisee.db.AppraiseeDB.class.getMethod("reviewerName", new Class [] {int.class});
            _methodParamMap.put(_reviewerNameMethod, new String [] { "reviewerId" });
            _insertOrUpdateAchievementsMethod = com.defiance.ideal.qpd.appraisee.db.AppraiseeDB.class.getMethod("insertOrUpdateAchievements", new Class [] {int.class, java.lang.String.class, int.class, int.class, int.class});
            _methodParamMap.put(_insertOrUpdateAchievementsMethod, new String [] { "achievementId", "kra", "appraisalYear", "appraiseeId", "deleted" });
            _setDeleteGoalMethod = com.defiance.ideal.qpd.appraisee.db.AppraiseeDB.class.getMethod("setDeleteGoal", new Class [] {int.class, int.class});
            _methodParamMap.put(_setDeleteGoalMethod, new String [] { "goalId", "deletedStatus" });
            _updateAppraiseeStatusMethod = com.defiance.ideal.qpd.appraisee.db.AppraiseeDB.class.getMethod("updateAppraiseeStatus", new Class [] {int.class, int.class, int.class});
            _methodParamMap.put(_updateAppraiseeStatusMethod, new String [] { "appraiseeId", "quarterYear", "submitStatus" });
            _customerAttribsMethod = com.defiance.ideal.qpd.appraisee.db.AppraiseeDB.class.getMethod("customerAttribs", new Class [] {java.lang.String.class});
            _methodParamMap.put(_customerAttribsMethod, new String [] { "kraCustomer" });
            _qualAttribsMethod = com.defiance.ideal.qpd.appraisee.db.AppraiseeDB.class.getMethod("qualAttribs", new Class [] {java.lang.String.class});
            _methodParamMap.put(_qualAttribsMethod, new String [] { "kraQuality" });
            _getAchievementsDataMethod = com.defiance.ideal.qpd.appraisee.db.AppraiseeDB.class.getMethod("getAchievementsData", new Class [] {int.class, int.class});
            _methodParamMap.put(_getAchievementsDataMethod, new String [] { "appraiseeId", "appraisalYear" });
            _appraiserNameMethod = com.defiance.ideal.qpd.appraisee.db.AppraiseeDB.class.getMethod("appraiserName", new Class [] {int.class});
            _methodParamMap.put(_appraiserNameMethod, new String [] { "appraiserId" });
            _setDeleteDevNeedsMethod = com.defiance.ideal.qpd.appraisee.db.AppraiseeDB.class.getMethod("setDeleteDevNeeds", new Class [] {int.class, int.class});
            _methodParamMap.put(_setDeleteDevNeedsMethod, new String [] { "needsId", "deletedStatus" });
        }
        catch (NoSuchMethodException __bc_nsme)
        {
            throw new ExceptionInInitializerError(__bc_nsme);
        }
    }
    
    /**
    * Default null-arg constructor used to create a new BeanInfo instance
    */
    public AppraiseeDBBeanBeanInfo()
    {
        super(com.defiance.ideal.qpd.appraisee.db.AppraiseeDBBean.class);
    }
    
    /**
    * Protected constructor used if this BeanInfo class is extended.
    */
    protected AppraiseeDBBeanBeanInfo(Class beanClass)
    {
        super(beanClass);
    }
    
    // java.beans.BeanInfo.getBeanDescriptor
    public BeanDescriptor getBeanDescriptor()
    {
        BeanDescriptor bd = new BeanDescriptor(com.defiance.ideal.qpd.appraisee.db.AppraiseeDBBean.class);
        bd.setName( "AppraiseeDBBean" );
        bd.setDisplayName( "AppraiseeDBBean" );
        
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
        // Declare MethodDescriptor for updateAppraiseeData(kraQtrId, selfComments, appraiseeId, qpdKraId)
        //
        __bc_paramNames = _methodParamMap.get(_updateAppraiseeData1Method);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_updateAppraiseeData1Method, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for costAttribs(kraCost)
        //
        __bc_paramNames = _methodParamMap.get(_costAttribsMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_costAttribsMethod, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for appraiseeCommentsInsert(appraiseeId, comment)
        //
        __bc_paramNames = _methodParamMap.get(_appraiseeCommentsInsertMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_appraiseeCommentsInsertMethod, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for devlopAttribs(kraDevlop)
        //
        __bc_paramNames = _methodParamMap.get(_devlopAttribsMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_devlopAttribsMethod, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for updateAppraiseeData(formData, appraiseeId)
        //
        __bc_paramNames = _methodParamMap.get(_updateAppraiseeData0Method);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_updateAppraiseeData0Method, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for insertOrUpdateGoals(goalId, goalData, appraisalYear, appraiseeId, deleted)
        //
        __bc_paramNames = _methodParamMap.get(_insertOrUpdateGoalsMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_insertOrUpdateGoalsMethod, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for appraiseeCommentsUpdate(appraiseeId, comment, kraQtrId)
        //
        __bc_paramNames = _methodParamMap.get(_appraiseeCommentsUpdateMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_appraiseeCommentsUpdateMethod, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for chkAppraiseeComments(appraiseeId)
        //
        __bc_paramNames = _methodParamMap.get(_chkAppraiseeCommentsMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_chkAppraiseeCommentsMethod, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for authenAppraisee(empNum, appraiseeYear)
        //
        __bc_paramNames = _methodParamMap.get(_authenAppraiseeMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_authenAppraiseeMethod, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for insertAppraiseeData(kraQtrId, selfComments, appraiseeId)
        //
        __bc_paramNames = _methodParamMap.get(_insertAppraiseeDataMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_insertAppraiseeDataMethod, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for getKraData(bandId, appraisalYear, appraiseeId, departmentId, triggerStatus)
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
        // Declare MethodDescriptor for appraiseeSBU(deptId)
        //
        __bc_paramNames = _methodParamMap.get(_appraiseeSBUMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_appraiseeSBUMethod, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for insertOrUpdateDevNeeds(needsId, developmentNeeds, appraisalYear, appraiseeId, deleted)
        //
        __bc_paramNames = _methodParamMap.get(_insertOrUpdateDevNeedsMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_insertOrUpdateDevNeedsMethod, __bc_paramDescriptors);
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
        // Declare MethodDescriptor for setDeleteAchievements(achievementId, deleted)
        //
        __bc_paramNames = _methodParamMap.get(_setDeleteAchievementsMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_setDeleteAchievementsMethod, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for reviewerName(reviewerId)
        //
        __bc_paramNames = _methodParamMap.get(_reviewerNameMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_reviewerNameMethod, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for insertOrUpdateAchievements(achievementId, kra, appraisalYear, appraiseeId, deleted)
        //
        __bc_paramNames = _methodParamMap.get(_insertOrUpdateAchievementsMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_insertOrUpdateAchievementsMethod, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for setDeleteGoal(goalId, deletedStatus)
        //
        __bc_paramNames = _methodParamMap.get(_setDeleteGoalMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_setDeleteGoalMethod, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for updateAppraiseeStatus(appraiseeId, quarterYear, submitStatus)
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
        // Declare MethodDescriptor for customerAttribs(kraCustomer)
        //
        __bc_paramNames = _methodParamMap.get(_customerAttribsMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_customerAttribsMethod, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for qualAttribs(kraQuality)
        //
        __bc_paramNames = _methodParamMap.get(_qualAttribsMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_qualAttribsMethod, __bc_paramDescriptors);
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
        // Declare MethodDescriptor for appraiserName(appraiserId)
        //
        __bc_paramNames = _methodParamMap.get(_appraiserNameMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_appraiserNameMethod, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for setDeleteDevNeeds(needsId, deletedStatus)
        //
        __bc_paramNames = _methodParamMap.get(_setDeleteDevNeedsMethod);
        __bc_paramDescriptors = new ParameterDescriptor[__bc_paramNames.length];
        for (int j = 0; j < __bc_paramNames.length; j++)
        {
            __bc_paramDescriptors[j] = new ParameterDescriptor();
            __bc_paramDescriptors[j].setName(__bc_paramNames[j]);
            __bc_paramDescriptors[j].setDisplayName(__bc_paramNames[j]);
        }
        md = new MethodDescriptor(_setDeleteDevNeedsMethod, __bc_paramDescriptors);
        methodDescriptors[index++] = md;
        
        
        //
        // Add method descriptors from parent BeanInfo
        //
        super.initMethodDescriptors(methodDescriptors, index);
    }
    
    public MethodDescriptor [] getMethodDescriptors()
    {
        MethodDescriptor [] __bc_methodDescriptors = new MethodDescriptor[28];
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
        
        pd = new PropertyDescriptor( "controlImplementation" , com.defiance.ideal.qpd.appraisee.db.AppraiseeDBBean.class, "getControlImplementation", "setControlImplementation");
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
