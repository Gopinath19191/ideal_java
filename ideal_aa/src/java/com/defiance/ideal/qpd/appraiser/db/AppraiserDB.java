/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.qpd.appraiser.db;

/**
 *
 * @author Administrator
 */
import com.defiance.ideal.qpd.appraiser.dto.AppraiseeListDTO;
import com.defiance.ideal.qpd.appraiser.dto.AppraiserFormDTO;
import com.defiance.ideal.qpd.appraiser.dto.AppraiserRatingFormDTO;
import com.defiance.ideal.shared.DBConnectivity;
import org.apache.beehive.controls.api.bean.ControlExtension;
import org.apache.beehive.controls.system.jdbc.JdbcControl.SQL;

@ControlExtension
public interface AppraiserDB extends DBConnectivity{
    
    @SQL(statement="SELECT qea.qpdId,CONCAT(emp.first_name,' ',emp.last_name) as appraiseeName,date_format(joined_date,'%d-%m-%Y') as appraiseeJoiningDate," +
    "employment_status as appraiseeStatus,emp.employee_number as appraiseeNumber,qea.appraiseeId,qea.submitStatus,qea.bandId" +
            " From aa_eligible_associates as qea LEFT JOIN employees AS emp ON (qea.appraiseeId = emp.id) " +
            "WHERE qea.appraiserId = {employeeId} AND qea.year = {appraisalYear} AND " +
            "qea.appraisalTriggered={triggerStatus}")
    public AppraiseeListDTO[] getAppraiseeList(int employeeId, int appraisalYear,int triggerStatus);

    @SQL(statement="Select qk.kraDescription,qkm.attributes,qkm.weightage,qkm.performanceCriteria,qkm.measurementCriteria,qakm.qpdKraId," +
    "qakm.selfComments,qkqm.kraQtrId,qakm.appraiserComments as appraiserComments,qakm.appraiserCommentsNew" +
            " as appraiserCommentsNew FROM aa_associate_kra_map as qakm LEFT JOIN aa_kra_quarter_mapping as qkqm ON " +
            "(qkqm.kraQtrId = qakm.kraQtrId) LEFT JOIN  aa_kra_master as qkm ON (qkm.kraId = qkqm.kraId) LEFT JOIN aa_kra as qk ON " +
            "(qk.kra = qkm.kra AND qk.bandId = qkqm.bandId) WHERE qkqm.year = {appraisalYear} AND " +
            "qkqm.bandId = {bandId} AND qakm.appraiseeId = {appraiseeId} AND qkqm.departmentId={departmentId}")
    public AppraiserFormDTO[] getKraData(int bandId, int appraisalYear, int appraiseeId,int departmentId);


    @SQL(statement="UPDATE aa_associate_kra_map SET selfComments={appraiseeComments},appraiserComments={appraiserRating},appraiserCommentsNew={appraiserCommentsNew} WHERE qpdKraId = {qpdKraId} AND kraQtrId={kraQtrId} AND appraiseeId={appraiseeId}")
    public void updateAppraiseeData(int appraiseeId, int kraQtrId, int qpdKraId, String appraiseeComments, int appraiserRating,String appraiserCommentsNew);

    @SQL(statement="UPDATE aa_eligible_associates SET appraiserRating =  aes_encrypt({finalRating},{appraiseeId}) ," +
    "appraiserComments={appraiserComments},areasOfImprovement={areasOfImprovement},technologyTraining={technologyTraining}," +
    "softskillTraining={softskillTraining},appraiserPromotionRec={appraiserPromotionRecommeded},discussion_date={datePicker} WHERE appraiseeId ={appraiseeId} AND year={appraiseeYear}")
    public void updateAppraiserRating(int finalRating, int appraiseeId, int appraiseeYear,String appraiserComments,String areasOfImprovement,String technologyTraining,String softskillTraining,String appraiserPromotionRecommeded,String datePicker);

     @SQL(statement="UPDATE aa_eligible_associates SET appraiserRating =  aes_encrypt({finalRating},{appraiseeId}) ," +
    "appraiserComments={appraiserComments},areasOfImprovement={areasOfImprovement},technologyTraining={technologyTraining}," +
    "softskillTraining={softskillTraining},appraiserPromotionRec={appraiserPromotionRecommeded},discussion_date={datePicker} WHERE appraiseeId ={appraiseeId} AND year={appraiseeYear}")
    public void updateAppraiserrrRating(int finalRating, int appraiseeId, int appraiseeYear,String appraiserComments,String areasOfImprovement,String technologyTraining,String softskillTraining,String appraiserPromotionRecommeded,String datePicker);

    
    @SQL(statement="update aa_eligible_associates set submitStatus={submitStatus} where appraiseeId={appraiseeId} and year={appraiseeYear}")
    public void updateAppraiseeStatus(int appraiseeId, int appraiseeYear, int submitStatus);
    
    @SQL(statement="select band.name AS bandName,qpdId,submitStatus as appraisalSubmitStatus,appraiserComments,departmentId,areasOfImprovement,technologyTraining," +
    "softskillTraining,appraiserPromotionRec as appraiserPromotionRecommeded,appraiserComments,DATE_FORMAT(qpd.discussion_date,'%d/%m/%Y')as discussionDate from aa_eligible_associates as qpd LEFT JOIN bands AS band ON ( band.id = qpd.bandId ) where qpd.appraiseeId={appraiseeId} and qpd.year={appraisalYear}")
    public AppraiseeListDTO getSelectedAppraiseeDetails(int appraiseeId, int appraisalYear);

    @SQL(statement="Select achievementId,keyResultArea,appraiserRemarks from aa_top_acheivements where appraiseeId={appraiseeId} AND appraisalYear={appraisalYear} AND deleted=0")
    public AppraiserFormDTO[] getAchievementsData(int appraisalYear, int appraiseeId);

   @SQL(statement="update aa_top_acheivements set appraiserRemarks={appraiserRemarks} where achievementId={achievementId}")
    public void updateAchievementsData(int achievementId, String appraiserRemarks);  
    

    @SQL(statement="Select needsId,developmentNeeds,apprDevRemarks from aa_dev_needs where appraiseeId={appraiseeId} AND appraisalYear={appraisalYear} AND deleted=0")
    public AppraiserFormDTO[] getDevelopmentData(int appraisalYear, int appraiseeId);

    @SQL(statement="update aa_dev_needs set apprDevRemarks={apprDevRemarks} where needsId={needsId}")
    public void updateDevelopmentData(int needsId, String apprDevRemarks);

    @SQL(statement="Select goalId,goalData,appGoalRemarks from aa_goal_sheet where appraiseeId={appraiseeId} AND appraisalYear={appraisalYear} AND deleted=0")
    public AppraiserFormDTO[] getGoalData(int appraisalYear, int appraiseeId);

    @SQL(statement="update aa_goal_sheet set appGoalRemarks={goalRemarks} where goalId={goalId}")
    public void updateGoalData(int goalId, String goalRemarks);

     @SQL(statement = "SELECT quarter AS qpdQuarter, year AS yearQPD, CASE sendbackbyhr WHEN 0 THEN aes_decrypt( normalisedReviewerRating, appraiseeId ) "
    + "WHEN 1 THEN aes_decrypt( correctedNormalizerRating, appraiseeId ) ELSE 'N/A' END AS qpdRating FROM qpd_eligible_associates "
    + "WHERE appraiseeId ={appraiseeId} ORDER BY qpdId DESC LIMIT 1 , 4")
    public AppraiserRatingFormDTO[] getLastFourQPDRating(int appraiseeId);

}

