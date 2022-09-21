/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.qpd.appraisee.db;

/**
 *
 * @author Administrator
 */
import com.defiance.ideal.qpd.appraisee.dto.AppraiseeDetailsDTO;
import com.defiance.ideal.qpd.appraisee.dto.MyAppraisalFormDTO;
import com.defiance.ideal.shared.DBConnectivity;
import org.apache.beehive.controls.api.bean.ControlExtension;
import org.apache.beehive.controls.system.jdbc.JdbcControl.SQL;

@ControlExtension
public interface AppraiseeDB extends DBConnectivity{
    
    @SQL(statement="select qpd.submitStatus as submitStausInitial,qpd.qpdId,qpd.quarter,qpd.bandId,qpd.departmentId,qpd.appraiserId," +
    "qpd.reviewerId,qpd.hrId,DATE_FORMAT(emp.joined_date,'%d-%m-%Y') as joinedDate ,CONCAT(emp.first_name,' ',emp.last_name) as appraiseeName,emp.work_email_address as appraiseeEmail," +
            "CONCAT(appraiser.first_name,' ',appraiser.last_name) as appraiserName,appraiser.work_email_address as appraiserEmail," +
            "CONCAT(reviewer.first_name,' ',reviewer.last_name) as reviewerName,reviewer.work_email_address as reviewerEmail," +
            "qpd.submitStatus,CASE WHEN qpd.sendbackbyhr = 1 THEN aes_decrypt(qpd.correctedNormalizerRating,qpd.appraiseeId) " +
            "ELSE aes_decrypt(qpd.normalisedReviewerRating,qpd.appraiseeId) END AS finalRating,areasOfImprovement,technologyTraining,softskillTraining,appraiserComments,DATE_FORMAT(qpd.discussion_date,'%d/%m/%Y') as discussionDate FROM aa_eligible_associates qpd " +
            "LEFT JOIN employees AS emp on (qpd.appraiseeId=emp.id) LEFT JOIN employees AS appraiser on " +
            "(appraiser.id=qpd.appraiserId) LEFT JOIN employees AS reviewer on (reviewer.id=qpd.reviewerId) " +
            "where qpd.appraiseeId={empNum} and qpd.year={appraiseeYear}")
    public AppraiseeDetailsDTO authenAppraisee(String empNum,int appraiseeYear);

    @SQL(statement="select first_name as appraiserName from employees where id={appraiserId}")
    public AppraiseeDetailsDTO appraiserName(int appraiserId);

    @SQL(statement="select first_name as reviewerName from employees where id={reviewerId}")
    public AppraiseeDetailsDTO reviewerName(int reviewerId);

    @SQL(statement="select name as appraiseeSBU from company_structures where id={deptId}")
    public AppraiseeDetailsDTO appraiseeSBU(int deptId);

    @SQL(statement="insert into aa_associate_kra_map(appraiseeId,selfComments) values({appraiseeId},{comment})")
    public void appraiseeCommentsInsert(int appraiseeId,String comment);

    @SQL(statement="update associate_kra_map set selfComments={comment} where appraiseeId={appraiseeId} and kraQtrId={kraQtrId}")
    public void appraiseeCommentsUpdate(int appraiseeId,String comment,int kraQtrId);

    @SQL(statement="update aa_eligible_associates set submitStatus={submitStatus} where appraiseeId={appraiseeId} and year={quarterYear}")
    public void updateAppraiseeStatus(int appraiseeId,int quarterYear,int submitStatus);

    @SQL(statement="select distinct appraiseeId from aa_associate_kra_map where appraiseeId={appraiseeId}")
    public AppraiseeDetailsDTO chkAppraiseeComments(int appraiseeId);

    @SQL(statement="select kraId,attributes,performanceCriteria,measurementCriteria,weightage from aa_kra_master where kra={kraQuality}")
    public MyAppraisalFormDTO[] qualAttribs(String kraQuality);

    @SQL(statement="select kraId,attributes,performanceCriteria,measurementCriteria,weightage from aa_kra_master where kra={kraCost}")
    public MyAppraisalFormDTO[] costAttribs(String kraCost);

    @SQL(statement="select kraId,attributes,performanceCriteria,measurementCriteria,weightage from aa_kra_master where kra={kraCustomer}")
    public MyAppraisalFormDTO[] customerAttribs(String kraCustomer);

    @SQL(statement="select kraId,attributes,performanceCriteria,measurementCriteria,weightage from aa_kra_master where kra={kraDevlop}")
    public MyAppraisalFormDTO[] devlopAttribs(String kraDevlop);


    @SQL(statement="Select qk.kraDescription,qkm.attributes,qkm.weightage,qkm.performanceCriteria,qkm.measurementCriteria,qkam.qpdKraId,qkam.selfComments,qkam.appraiserCommentsNew,qkqm.kraQtrId FROM aa_kra_quarter_mapping as qkqm LEFT JOIN  aa_kra_master as qkm ON (qkm.kraId = qkqm.kraId) LEFT JOIN aa_kra as qk ON (qk.kra = qkm.kra AND qk.bandId = qkqm.bandId) LEFT JOIN aa_associate_kra_map as qkam ON (qkqm.kraQtrId = qkam.kraQtrId AND qkam.appraiseeId={appraiseeId}) LEFT JOIN aa_eligible_associates as qea ON (qea.appraiseeId ={appraiseeId} AND qea.year={appraisalYear}) WHERE qkqm.year = {appraisalYear} AND qkqm.bandId = {bandId} AND qkqm.departmentId={departmentId} AND qea.appraisalTriggered = {triggerStatus}")
    public MyAppraisalFormDTO[] getKraData(int bandId, int appraisalYear,String appraiseeId,int departmentId,int triggerStatus);

    public void updateAppraiseeData(MyAppraisalFormDTO formData,int appraiseeId);

    @SQL(statement="insert into aa_associate_kra_map(kraQtrId,selfComments,appraiseeId) values({kraQtrId},{selfComments},{appraiseeId})")
    public void insertAppraiseeData(int kraQtrId, String selfComments, int appraiseeId);

    @SQL(statement="UPDATE aa_associate_kra_map set kraQtrId={kraQtrId},selfComments={selfComments},appraiseeId={appraiseeId} WHERE qpdKraId={qpdKraId}")
    public void updateAppraiseeData(int kraQtrId, String selfComments, int appraiseeId, int qpdKraId);

   
   
   
   @SQL(statement=" Insert INTO aa_top_acheivements(achievementId,keyResultArea,appraisalYear,appraiseeId,deleted)" +
    " VALUES({achievementId},{kra},{appraisalYear},{appraiseeId},{deleted}) ON DUPLICATE KEY UPDATE" +
    " achievementId={achievementId},keyResultArea={kra},appraisalYear={appraisalYear},appraiseeId={appraiseeId},deleted={deleted}")
    public void insertOrUpdateAchievements(int achievementId, String kra, int appraisalYear, int appraiseeId,int deleted);
   
    
    /* @SQL(statement=" Insert INTO aa_top_acheivements(keyResultArea,achievementId,appraisalYear,appraiseeId,deleted) VALUES({kra},{achievementId},{appraisalYear},{appraiseeId},{deleted})")
    public void insertAchievements(String kra,int achievementId , int appraisalYear, int appraiseeId,int deleted);
    
     @SQL(statement=" UPDATE aa_top_acheivements SET keyResultArea={kra},appraisalYear={appraisalYear},appraiseeId={appraiseeId},deleted={deleted} where achievementId={achievementId}")
    public void updateAchievements(String kra , int appraisalYear, int appraiseeId,int deleted,int achievementId);*/
    
     @SQL(statement="UPDATE aa_top_acheivements SET deleted={deleted} WHERE achievementId={achievementId}")
    public void setDeleteAchievements(int achievementId,int deleted);

    @SQL(statement="Select achievementId,keyResultArea,employeeRemarks as employeeRemark from aa_top_acheivements where appraiseeId={appraiseeId} AND appraisalYear={appraisalYear} AND deleted=0")
    public MyAppraisalFormDTO[] getAchievementsData(int appraiseeId, int appraisalYear);

    @SQL(statement=" Insert INTO aa_dev_needs(needsId,developmentNeeds,appraisalYear,appraiseeId,deleted)" +
    " VALUES({needsId},{developmentNeeds},{appraisalYear},{appraiseeId},{deleted}) ON DUPLICATE KEY UPDATE" +
    " needsId={needsId},developmentNeeds={developmentNeeds},appraisalYear={appraisalYear},appraiseeId={appraiseeId},deleted={deleted}")
    public void insertOrUpdateDevNeeds(int needsId, String developmentNeeds, int appraisalYear, int appraiseeId, int deleted);

    @SQL(statement="UPDATE aa_dev_needs SET deleted={deletedStatus} WHERE needsId={needsId}")
    public void setDeleteDevNeeds(int needsId, int deletedStatus);

    @SQL(statement="Select needsId,developmentNeeds as developmentNeed from aa_dev_needs where appraiseeId={appraiseeId} AND appraisalYear={appraisalYear} AND deleted=0")
    public MyAppraisalFormDTO[] getDevNeedsData(int appraiseeId, int appraisalYear);


    @SQL(statement=" Insert INTO aa_goal_sheet(goalId,goalData,appraiseeId,appraisalYear,deleted)" +
    " VALUES({goalId},{goalData},{appraiseeId},{appraisalYear},{deleted}) ON DUPLICATE KEY UPDATE" +
    " goalId={goalId},goalData={goalData},appraisalYear={appraisalYear},appraiseeId={appraiseeId},deleted={deleted}")
    public void insertOrUpdateGoals(int goalId, String goalData, int appraisalYear, int appraiseeId, int deleted);

    @SQL(statement="UPDATE aa_goal_sheet SET deleted={deletedStatus} WHERE goalId={goalId}")
    public void setDeleteGoal(int goalId, int deletedStatus);

    @SQL(statement="Select goalId as glId,goalData as glData from aa_goal_sheet where appraiseeId={appraiseeId} AND appraisalYear={appraisalYear} AND deleted=0")
    public MyAppraisalFormDTO[] getGoalData(int appraiseeId, int appraisalYear);

//    @SQL(statement="SELECT LAST_INSERT_ID()")
//    public int getLastInsertId();
}
