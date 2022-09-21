package com.defiance.ideal.qpd.managers.db;
import com.defiance.ideal.qpd.appraiser.dto.AppraiseeListDTO;
import com.defiance.ideal.qpd.appraiser.dto.AppraiserFormDTO;
import com.defiance.ideal.qpd.managers.dto.ManagerDTO;
import com.defiance.ideal.shared.DBConnectivity;
import org.apache.beehive.controls.api.bean.ControlExtension;
import org.apache.beehive.controls.system.jdbc.JdbcControl.SQL;
/**
 *
 * @author Administrator
 */

@ControlExtension
public interface ManagerDB extends DBConnectivity{
    
//    @SQL(statement="SELECT qpd.appraiseeId,qpd.appraiserId,qpd.appraiserRating,qpd.appraiserComments,qpd.reviewerRating,qpd.reviewerComments,qpd.normalisedReviewerRating,qpd.submitStatus,qpd.sendbackbyhr,qpd.quarter as reviewerQuarter,qpd.year as reviewerYear,emp.employee_number as appraiseeEmpId,CONCAT(emp.first_name,' ',emp.last_name) as appraiseeName,emp.work_email_address as appraiseeEmail,appraiser.employee_number as appraiserEmpId,CONCAT(appraiser.first_name,' ',appraiser.last_name) as appraiserName,appraiser.work_email_address as appraiserEmail from aa_eligible_associates qpd LEFT JOIN employees as emp on(qpd.appraiseeId=emp.id) LEFT JOIN employees as appraiser on (appraiser.id=qpd.appraiserId) WHERE qpd.reviewerID={reviewerId} AND qpd.quarter={appraisalQuarter} AND qpd.year={appraisalYear} AND submitStatus>={submitStatus}")
    @SQL(statement="SELECT qpd.qpdid,band.name AS bandName,qpd.departmentId,qpd.appraiseeId,qpd.appraiserId,qpd.bandId,aes_decrypt(qpd.appraiserRating,qpd.appraiseeId) as appraiserRating,qpd.appraiserComments," +
    "aes_decrypt(qpd.reviewerRating,qpd.appraiseeId) as reviewerRating,qpd.reviewerComments,aes_decrypt(qpd.normalisedReviewerRating," +
            "qpd.appraiseeId) as normalisedReviewerRating  ,qpd.submitStatus,qpd.sendbackbyhr,qpd.quarter as reviewerQuarter," +
            "qpd.year as reviewerYear,emp.employee_number as appraiseeEmpId,CONCAT(emp.first_name,' ',emp.last_name) as appraiseeName," +
            "emp.work_email_address as appraiseeEmail,appraiser.employee_number as appraiserEmpId,CONCAT(appraiser.first_name,' '," +
            "appraiser.last_name) as appraiserName,appraiser.work_email_address as appraiserEmail,reviewer.employee_number as reviewerEmpId," +
            "CONCAT(reviewer.first_name,' ',reviewer.last_name) as reviewerName,reviewer.work_email_address as reviewerEmail," +
            "normalizer.employee_number as normalizerEmpId,CONCAT(normalizer.first_name,' ',normalizer.last_name) as normalizerName," +
            "normalizer.work_email_address as normalizerEmail,qpd.appraiserPromotionRec as appraiserPromotionRecommeded,qpd.reviewerPromotionRec as reviewerPromotionRec from aa_eligible_associates qpd LEFT JOIN employees as emp " +
            "on(qpd.appraiseeId=emp.id) LEFT JOIN employees as appraiser on (appraiser.id=qpd.appraiserId) LEFT JOIN employees as " +
            "reviewer on (reviewer.id=qpd.reviewerId) LEFT JOIN employees as normalizer on (normalizer.id=qpd.normalizerId) LEFT JOIN bands AS band ON ( band.id = qpd.bandId ) WHERE " +
            "qpd.reviewerID={reviewerId} AND qpd.year={appraisalYear} AND" +
            " qpd.appraisalTriggered={triggerStatus}")
    public ManagerDTO[] getAppraiseesByReviewer(String reviewerId,int appraisalYear,int submitStatus,int triggerStatus);

//    @SQL(statement="SELECT qpd.appraiseeId,qpd.appraiserId,qpd.appraiserRating,qpd.reviewerRating,qpd.normalisedReviewerRating,qpd.submitStatus,qpd.sendbackbyhr,qpd.quarter as reviewerQuarter,qpd.year as reviewerYear,emp.employee_number as appraiseeEmpId,CONCAT(emp.first_name,' ',emp.last_name) as appraiseeName,emp.work_email_address as appraiseeEmail,appraiser.employee_number as appraiserEmpId,CONCAT(appraiser.first_name,' ',appraiser.last_name) as appraiserName,appraiser.work_email_address as appraiserEmail from aa_eligible_associates qpd LEFT JOIN employees as emp on(qpd.appraiseeId=emp.id) LEFT JOIN employees as appraiser on (appraiser.id=qpd.appraiserId) WHERE qpd.reviewerID={reviewerId} AND qpd.quarter={appraisalQuarter} AND qpd.year={appraisalYear} AND submitStatus>={submitStatus} group by qpd.appraiserId")
    @SQL(statement="SELECT qpd.departmentId,qpd.appraiseeId,qpd.appraiserId,aes_decrypt(qpd.appraiserRating,qpd.appraiseeId) as appraiserRating," +
    "aes_decrypt(qpd.reviewerRating,qpd.appraiseeId) as reviewerRating,aes_decrypt(qpd.normalisedReviewerRating,qpd.appraiseeId) as normalisedReviewerRating" +
    ",qpd.submitStatus,qpd.sendbackbyhr,qpd.quarter as reviewerQuarter,qpd.year as reviewerYear,emp.employee_number as appraiseeEmpId," +
            "CONCAT(emp.first_name,' ',emp.last_name) as appraiseeName,emp.work_email_address as appraiseeEmail,appraiser.employee_number" +
            " as appraiserEmpId,CONCAT(appraiser.first_name,' ',appraiser.last_name) as appraiserName,appraiser.work_email_address as" +
            " appraiserEmail from aa_eligible_associates qpd LEFT JOIN employees as emp on(qpd.appraiseeId=emp.id) LEFT JOIN employees" +
            " as appraiser on (appraiser.id=qpd.appraiserId) WHERE qpd.reviewerID={reviewerId} AND" +
            " qpd.year={appraisalYear} AND qpd.appraisalTriggered={triggerStatus} group by qpd.appraiserId")
    public ManagerDTO[] getAppraisersByReviewer(String reviewerId, int appraisalYear,int submitStatus,int triggerStatus);

//    @SQL(statement="SELECT qpd.appraiseeId,qpd.appraiserId,qpd.appraiserRating,qpd.reviewerRating,qpd.normalisedReviewerRating,qpd.submitStatus,qpd.sendbackbyhr,qpd.quarter as reviewerQuarter,qpd.year as reviewerYear,emp.employee_number as appraiseeEmpId,CONCAT(emp.first_name,' ',emp.last_name) as appraiseeName,emp.work_email_address as appraiseeEmail,appraiser.employee_number as appraiserEmpId,CONCAT(appraiser.first_name,' ',appraiser.last_name) as appraiserName,appraiser.work_email_address as appraiserEmail from aa_eligible_associates qpd LEFT JOIN employees as emp on(qpd.appraiseeId=emp.id) LEFT JOIN employees as appraiser on (appraiser.id=qpd.appraiserId) WHERE qpd.reviewerID={reviewerId} AND qpd.quarter={appraisalQuarter} AND qpd.year={appraisalYear} AND qpd.appraiserId={selectedAppraiserId} AND submitStatus>={submitStatus} ")
//    @SQL(statement="SELECT qpd.departmentId,qpd.appraiseeId,qpd.appraiserId,qpd.appraiserRating,qpd.reviewerRating,qpd.normalisedReviewerRating,qpd.appraiserComments,qpd.reviewerComments,qpd.submitStatus,qpd.sendbackbyhr,qpd.quarter as reviewerQuarter,qpd.year as reviewerYear,emp.employee_number as appraiseeEmpId,CONCAT(emp.first_name,' ',emp.last_name) as appraiseeName,emp.work_email_address as appraiseeEmail,appraiser.employee_number as appraiserEmpId,CONCAT(appraiser.first_name,' ',appraiser.last_name) as appraiserName,appraiser.work_email_address as appraiserEmail from aa_eligible_associates qpd LEFT JOIN employees as emp on(qpd.appraiseeId=emp.id) LEFT JOIN employees as appraiser on (appraiser.id=qpd.appraiserId) WHERE qpd.reviewerID={reviewerId} AND qpd.quarter={appraisalQuarter} AND qpd.year={appraisalYear} AND qpd.appraiserId={selectedAppraiserId}")
    @SQL(statement="SELECT band.name AS bandName,qpd.departmentId,qpd.appraiseeId,qpd.appraiserId,qpd.bandId,aes_decrypt(qpd.appraiserRating,qpd.appraiseeId) as appraiserRating," +
    "qpd.appraiserComments,aes_decrypt(qpd.reviewerRating,qpd.appraiseeId) as reviewerRating,qpd.reviewerComments,aes_decrypt(qpd.normalisedReviewerRating,qpd.appraiseeId) as normalisedReviewerRating," +
            "qpd.submitStatus,qpd.sendbackbyhr,qpd.quarter as reviewerQuarter,qpd.year as reviewerYear,emp.employee_number as appraiseeEmpId," +
            "CONCAT(emp.first_name,' ',emp.last_name) as appraiseeName,emp.work_email_address as appraiseeEmail,appraiser.employee_number" +
            " as appraiserEmpId,CONCAT(appraiser.first_name,' ',appraiser.last_name) as appraiserName,appraiser.work_email_address as" +
            " appraiserEmail,reviewer.employee_number as reviewerEmpId,CONCAT(reviewer.first_name,' ',reviewer.last_name) as reviewerName," +
            "reviewer.work_email_address as reviewerEmail,normalizer.employee_number as normalizerEmpId," +
            "CONCAT(normalizer.first_name,' ',normalizer.last_name) as normalizerName," +
            "normalizer.work_email_address as normalizerEmail from aa_eligible_associates qpd" +
            " LEFT JOIN employees as emp on(qpd.appraiseeId=emp.id) LEFT JOIN employees as appraiser" +
            " on (appraiser.id=qpd.appraiserId) LEFT JOIN employees as reviewer on (reviewer.id=qpd.reviewerId) " +
            "LEFT JOIN employees as normalizer on (normalizer.id=qpd.normalizerId) LEFT JOIN bands AS band ON ( band.id = qpd.bandId ) WHERE qpd.reviewerID={reviewerId}" +
            " AND qpd.year={appraisalYear} AND qpd.appraisalTriggered={triggerStatus} " +
            "AND qpd.appraiserId={selectedAppraiserId}")
    public ManagerDTO[] getAppraiseesByReviewerAndAppraiser(String selectedAppraiserId, String reviewerId, int appraisalYear,int submitStatus,int triggerStatus);

//    @SQL(statement="SELECT qpd.appraiseeId,qpd.appraiserId,qpd.appraiserRating,qpd.reviewerRating,qpd.normalisedReviewerRating,qpd.submitStatus,qpd.sendbackbyhr,qpd.quarter as reviewerQuarter,qpd.year as reviewerYear,emp.employee_number as appraiseeEmpId,CONCAT(emp.first_name,' ',emp.last_name) as appraiseeName,emp.work_email_address as appraiseeEmail,appraiser.employee_number as appraiserEmpId,CONCAT(appraiser.first_name,' ',appraiser.last_name) as appraiserName,appraiser.work_email_address as appraiserEmail from aa_eligible_associates qpd LEFT JOIN employees as emp on(qpd.appraiseeId=emp.id) LEFT JOIN employees as appraiser on (appraiser.id=qpd.appraiserId) WHERE qpd.hrId={hrId} AND qpd.quarter={appraisalQuarter} AND qpd.year={appraisalYear} AND submitStatus>={submitStatus}")
    //@SQL(statement="SELECT qpd.appraiseeId,qpd.appraiserId,qpd.bandId,qpd.appraiserRating,qpd.appraiserComments,qpd.reviewerRating,qpd.reviewerComments,qpd.normalisedReviewerRating,qpd.submitStatus,qpd.sendbackbyhr,qpd.quarter as reviewerQuarter,qpd.year as reviewerYear,emp.employee_number as appraiseeEmpId,CONCAT(emp.first_name,' ',emp.last_name) as appraiseeName,emp.work_email_address as appraiseeEmail,appraiser.employee_number as appraiserEmpId,CONCAT(appraiser.first_name,' ',appraiser.last_name) as appraiserName,appraiser.work_email_address as appraiserEmail from aa_eligible_associates qpd LEFT JOIN employees as emp on(qpd.appraiseeId=emp.id) LEFT JOIN employees as appraiser on (appraiser.id=qpd.appraiserId) WHERE qpd.hrId={hrId} AND qpd.quarter={appraisalQuarter} AND qpd.year={appraisalYear}")
    @SQL(statement="SELECT band.name AS bandName,qpd.departmentId,qpd.appraiseeId,qpd.appraiserId,qpd.reviewerId,qpd.normalizerId,qpd.bandId" +
    ",aes_decrypt(qpd.appraiserRating,qpd.appraiseeId) as appraiserRating,qpd.appraiserComments,aes_decrypt(qpd.reviewerRating,qpd.appraiseeId) as reviewerRating,qpd.reviewerComments," +
            "aes_decrypt(qpd.normalisedReviewerRating,qpd.appraiseeId) as normalisedReviewerRating,qpd.normalizerComments,aes_decrypt(qpd.correctedNormalizerRating,qpd.appraiseeId) as correctedNormalizerRating,qpd.submitStatus," +
            "qpd.sendbackbyhr,qpd.quarter as reviewerQuarter,qpd.year as reviewerYear,emp.employee_number as appraiseeEmpId," +
            "CONCAT(emp.first_name,' ',emp.last_name) as appraiseeName,emp.work_email_address as appraiseeEmail," +
            "appraiser.employee_number as appraiserEmpId,CONCAT(appraiser.first_name,' ',appraiser.last_name) as appraiserName," +
            "appraiser.work_email_address as appraiserEmail,reviewer.employee_number as reviewerEmpId,CONCAT(reviewer.first_name,' '," +
            "reviewer.last_name) as reviewerName,reviewer.work_email_address as reviewerEmail,normalizer.employee_number as normalizerEmpId," +
            "CONCAT(normalizer.first_name,' ',normalizer.last_name) as normalizerName,normalizer.work_email_address as normalizerEmail,qpd.appraiserPromotionRec as appraiserPromotionRecommeded,qpd.reviewerPromotionRec as reviewerPromotionRec,normalizerPromotionRec as normalizerPromotionRec " +
            "from aa_eligible_associates qpd LEFT JOIN employees as emp on(qpd.appraiseeId=emp.id) LEFT JOIN employees as appraiser on " +
            "(appraiser.id=qpd.appraiserId) LEFT JOIN employees as reviewer on (reviewer.id=qpd.reviewerId) LEFT JOIN employees as normalizer " +
            "on (normalizer.id=qpd.normalizerId) LEFT JOIN bands AS band ON ( band.id = qpd.bandId ) WHERE qpd.hrId={hrId} AND qpd.year={appraisalYear} AND" +
            " qpd.appraisalTriggered={triggerStatus}")
    public ManagerDTO[] getAppraiseesByHr(String hrId, int appraisalYear,int submitStatus,int triggerStatus);

    @SQL(statement="SELECT band.name AS bandName,qpd.departmentId,qpd.appraiseeId,qpd.appraiserId,qpd.reviewerId,qpd.normalizerId,qpd.bandId" +
    ",aes_decrypt(qpd.appraiserRating,qpd.appraiseeId) as appraiserRating,qpd.appraiserComments,aes_decrypt(qpd.reviewerRating,qpd.appraiseeId) as reviewerRating,qpd.reviewerComments," +
            "aes_decrypt(qpd.normalisedReviewerRating,qpd.appraiseeId) as normalisedReviewerRating,qpd.normalizerComments,aes_decrypt(qpd.correctedNormalizerRating,qpd.appraiseeId) as correctedNormalizerRating,qpd.submitStatus," +
            "qpd.sendbackbyhr,qpd.quarter as reviewerQuarter,qpd.year as reviewerYear,emp.employee_number as appraiseeEmpId," +
            "CONCAT(emp.first_name,' ',emp.last_name) as appraiseeName,emp.work_email_address as appraiseeEmail," +
            "appraiser.employee_number as appraiserEmpId,CONCAT(appraiser.first_name,' ',appraiser.last_name) as appraiserName," +
            "appraiser.work_email_address as appraiserEmail,reviewer.employee_number as reviewerEmpId,CONCAT(reviewer.first_name,' '," +
            "reviewer.last_name) as reviewerName,reviewer.work_email_address as reviewerEmail,normalizer.employee_number as normalizerEmpId," +
            "CONCAT(normalizer.first_name,' ',normalizer.last_name) as normalizerName,normalizer.work_email_address as normalizerEmail,qpd.appraiserPromotionRec as appraiserPromotionRecommeded,qpd.reviewerPromotionRec as reviewerPromotionRec,normalizerPromotionRec as normalizerPromotionRec  " +
            "from aa_eligible_associates qpd LEFT JOIN employees as emp on(qpd.appraiseeId=emp.id) LEFT JOIN employees as appraiser on " +
            "(appraiser.id=qpd.appraiserId) LEFT JOIN employees as reviewer on (reviewer.id=qpd.reviewerId) LEFT JOIN employees as normalizer " +
            "on (normalizer.id=qpd.normalizerId) LEFT JOIN bands AS band ON ( band.id = qpd.bandId ) WHERE qpd.hrId={hrId} AND qpd.year={appraisalYear} AND" +
            " qpd.appraisalTriggered={triggerStatus} {sql: whereCondition}")
    public ManagerDTO[] getAppraiseesByHr(String hrId, int appraisalYear,int submitStatus,int triggerStatus,String whereCondition);

    @SQL(statement = "UPDATE aa_eligible_associates SET  reviewerRating = aes_encrypt({reviewerRating},{appraiseeId}) ,reviewerPromotionRec={reviewerPromotion}," +
    "reviewerComments={justifyRatingReviewer} WHERE appraiseeId={appraiseeId} AND year={appraisalYear} AND reviewerId={reviewerId}")
    public void updateReviewerRating(String reviewerId, int appraisalYear, int appraiseeId, int reviewerRating,String justifyRatingReviewer,String reviewerPromotion);

    @SQL(statement = "UPDATE aa_eligible_associates SET  reviewerRating = aes_encrypt({reviewerRating},{appraiseeId}) ,submitStatus={submitStatus} ,reviewerComments={justifyRatingReviewer},reviewerPromotionRec={reviewerPromotion} WHERE appraiseeId={appraiseeId} AND year={appraisalYear} AND reviewerId={reviewerId}")
    public void updateSubmitStatus(String reviewerId, int appraisalYear, int appraiseeId, int reviewerRating, int submitStatus,String justifyRatingReviewer,String reviewerPromotion);

    @SQL(statement = "UPDATE aa_eligible_associates SET  normalisedReviewerRating = aes_encrypt({reviewerRating},{appraiseeId}),reviewerComments={justifyRatingReviewer} WHERE appraiseeId={appraiseeId} AND year={appraisalYear} AND reviewerId={reviewerId}")
    public void updateNormalisedReviewerRating(String reviewerId, int appraisalYear, int appraiseeId, int reviewerRating,String justifyRatingReviewer);

    @SQL(statement = "UPDATE aa_eligible_associates SET  normalisedReviewerRating = aes_encrypt({reviewerRating},{appraiseeId}),submitStatus={submitStatus} ,reviewerComments={justifyRatingReviewer} WHERE appraiseeId={appraiseeId}  AND year={appraisalYear} AND reviewerId={reviewerId}")
    public void updateNormalisedSubmitStatus(String reviewerId, int appraisalYear, int appraiseeId, int reviewerRating, int submitStatus,String justifyRatingReviewer);

    @SQL(statement = "UPDATE aa_eligible_associates SET  submitStatus={submitStatus} WHERE appraiseeId={appraiseeId} AND year={appraisalYear} AND hrId={hrId}")
    public void updateFinalSubmitStatus(String hrId, int appraisalYear, int appraiseeId, int submitStatus);

    @SQL(statement = "UPDATE aa_eligible_associates SET  submitStatus={submitStatusSendback},sendbackbyhr={sendbackStatus} WHERE appraiseeId={appraiseeId}  AND year={appraisalYear} AND hrId={hrId}")
    public void updateSendbackStatus(String hrId, int appraisalYear, int appraiseeId, int submitStatusSendback, int sendbackStatus);

//    @SQL(statement="SELECT qpd.appraiseeId,qpd.appraiserId,qpd.appraiserRating,qpd.reviewerRating,qpd.normalisedReviewerRating,qpd.submitStatus,qpd.sendbackbyhr,qpd.quarter as reviewerQuarter,qpd.year as reviewerYear,emp.employee_number as appraiseeEmpId,CONCAT(emp.first_name,' ',emp.last_name) as appraiseeName,emp.work_email_address as appraiseeEmail,appraiser.employee_number as appraiserEmpId,CONCAT(appraiser.first_name,' ',appraiser.last_name) as appraiserName,appraiser.work_email_address as appraiserEmail from aa_eligible_associates qpd LEFT JOIN employees as emp on(qpd.appraiseeId=emp.id) LEFT JOIN employees as appraiser on (appraiser.id=qpd.appraiserId) WHERE qpd.financeId={financeId} AND qpd.quarter={appraisalQuarter} AND qpd.year={appraisalYear} AND qpd.submitStatus>={submitStatusCheck}")
    @SQL(statement="SELECT qpd.departmentId,qpd.appraiseeId,qpd.appraiserId,qpd.bandId,aes_decrypt(qpd.appraiserRating,qpd.appraiseeId) as appraiserRating," +
    "aes_decrypt(qpd.reviewerRating,qpd.appraiseeId) as reviewerRating,aes_decrypt(qpd.normalisedReviewerRating,qpd.appraiseeId) as normalisedReviewerRating," +
            "aes_decrypt(qpd.correctedNormalizerRating,qpd.appraiseeId) as correctedNormalizerRating,qpd.submitStatus,qpd.sendbackbyhr,qpd.quarter as reviewerQuarter,qpd.year as reviewerYear," +
            "emp.employee_number as appraiseeEmpId,CONCAT(emp.first_name,' ',emp.last_name) as appraiseeName," +
            "emp.work_email_address as appraiseeEmail,appraiser.employee_number as appraiserEmpId," +
            "CONCAT(appraiser.first_name,' ',appraiser.last_name) as appraiserName,appraiser.work_email_address as appraiserEmail " +
            "from aa_eligible_associates qpd LEFT JOIN employees as emp on(qpd.appraiseeId=emp.id) " +
            "LEFT JOIN employees as appraiser on (appraiser.id=qpd.appraiserId) WHERE qpd.financeId={financeId} " +
            "AND qpd.year={appraisalYear} AND qpd.appraisalTriggered={triggerStatus} " +
            "AND qpd.submitStatus>={submitStatusCheck}")
    public ManagerDTO[] getAppraiseesByFinance(String financeId, int appraisalYear, int submitStatusCheck,int triggerStatus);

    @SQL(statement="Select qk.kraDescription,qkm.attributes,qkm.weightage,qkm.performanceCriteria,qkm.measurementCriteria,qkam.selfComments,qkam.qpdKraId," +
    "qkam.appraiserComments as appraiserIntitialRatings,qkam.appraiserCommentsNew,aes_decrypt(qea.appraiserRating,qea.appraiseeId) as appraiserRating," +
            "qea.appraiserComments FROM aa_kra_quarter_mapping as qkqm " +
            "LEFT JOIN  aa_kra_master as qkm ON (qkm.kraId = qkqm.kraId) LEFT JOIN aa_kra as qk ON " +
            "(qk.kra = qkm.kra AND qk.bandId = qkqm.bandId) LEFT JOIN aa_associate_kra_map as qkam ON " +
            "(qkqm.kraQtrId = qkam.kraQtrId AND qkam.appraiseeId={appraiseeId}) LEFT JOIN aa_eligible_associates as qea ON " +
            "(qea.appraiseeId ={appraiseeId} AND qea.year={appraisalYear}) " +
            "WHERE qkqm.year = {appraisalYear} AND qkqm.bandId = {bandId} AND " +
            "qkqm.departmentId={departmentId} AND qea.appraisalTriggered = 1")
    public ManagerDTO[] getKraData(int bandId, int appraisalYear, int appraiseeId,int departmentId);

    @SQL(statement="SELECT band.name AS bandName,qpd.departmentId,qpd.appraiseeId,qpd.appraiserId,qpd.reviewerId,qpd.hrId,qpd.bandId,aes_decrypt(qpd.appraiserRating,qpd.appraiseeId) as appraiserRating," +
    "qpd.appraiserComments,aes_decrypt(qpd.reviewerRating,qpd.appraiseeId) as reviewerRating,qpd.reviewerComments,aes_decrypt(qpd.normalisedReviewerRating,qpd.appraiseeId) as normalisedReviewerRating," +
            "qpd.normalizerComments,aes_decrypt(qpd.correctedNormalizerRating,qpd.appraiseeId) as correctedNormalizerRating,qpd.submitStatus,qpd.sendbackbyhr,qpd.quarter as reviewerQuarter,qpd.year as reviewerYear," +
            "emp.employee_number as appraiseeEmpId,CONCAT(emp.first_name,' ',emp.last_name) as appraiseeName,emp.work_email_address as appraiseeEmail," +
            "appraiser.employee_number as appraiserEmpId,CONCAT(appraiser.first_name,' ',appraiser.last_name) as appraiserName," +
            "appraiser.work_email_address as appraiserEmail,reviewer.employee_number as reviewerEmpId,CONCAT(reviewer.first_name,' ',reviewer.last_name)" +
            " as reviewerName,reviewer.work_email_address as reviewerEmail,normalizer.employee_number as normalizerEmpId,CONCAT(normalizer.first_name,' '," +
            "normalizer.last_name) as normalizerName,normalizer.work_email_address as normalizerEmail,hr.employee_number as hrEmpId," +
            "CONCAT(hr.first_name,' ',hr.last_name) as hrName,hr.work_email_address as hrEmail,qpd.appraiserPromotionRec as appraiserPromotionRecommeded,qpd.reviewerPromotionRec as reviewerPromotionRec  from aa_eligible_associates qpd " +
            "LEFT JOIN employees as emp on(qpd.appraiseeId=emp.id) LEFT JOIN employees as appraiser on (appraiser.id=qpd.appraiserId) " +
            "LEFT JOIN employees as reviewer on (reviewer.id=qpd.reviewerId) LEFT JOIN employees as normalizer on (normalizer.id=qpd.normalizerId) " +
            "LEFT JOIN employees as hr on (hr.id=qpd.hrId) LEFT JOIN bands AS band ON ( band.id = qpd.bandId ) WHERE qpd.normalizerId={normalizerId} " +
            "AND qpd.year={appraisalYear} AND qpd.appraisalTriggered={triggerStatus}")
    public ManagerDTO[] getAppraiseesByNormalizer(String normalizerId, int appraisalYear, int submitStatusCheck,int triggerStatus);

//    @SQL(statement="SELECT qpd.departmentId,qpd.appraiseeId,qpd.appraiserId,qpd.reviewerId,qpd.hrId,qpd.appraiserRating,qpd.reviewerRating,qpd.normalisedReviewerRating,qpd.normalizerComments,qpd.submitStatus,qpd.sendbackbyhr,qpd.quarter as reviewerQuarter,qpd.year as reviewerYear,emp.employee_number as appraiseeEmpId,CONCAT(emp.first_name,' ',emp.last_name) as appraiseeName,emp.work_email_address as appraiseeEmail,appraiser.employee_number as appraiserEmpId,CONCAT(appraiser.first_name,' ',appraiser.last_name) as appraiserName,appraiser.work_email_address as appraiserEmail,reviewer.employee_number as reviewerEmpId,CONCAT(reviewer.first_name,' ',reviewer.last_name) as reviewerName,reviewer.work_email_address as reviewerEmail from aa_eligible_associates qpd LEFT JOIN employees as emp on(qpd.appraiseeId=emp.id) LEFT JOIN employees as appraiser on (appraiser.id=qpd.appraiserId) LEFT JOIN employees as reviewer on (reviewer.id=qpd.reviewerId) LEFT JOIN employees as normalizer on (normalizer.id=qpd.normalizerId) LEFT JOIN employees as hr on (hr.id=qpd.hrId) WHERE qpd.normalizerId={normalizerId} AND qpd.quarter={appraisalQuarter} AND qpd.year={appraisalYear} AND qpd.appraiserId={selectedAppraiserId}")
    @SQL(statement="SELECT band.name AS bandName,qpd.departmentId,qpd.appraiseeId,qpd.appraiserId,qpd.reviewerId,qpd.hrId,qpd.bandId,aes_decrypt(qpd.appraiserRating,qpd.appraiseeId) as appraiserRating," +
    "qpd.appraiserComments,aes_decrypt(qpd.reviewerRating,qpd.appraiseeId) as reviewerRating,qpd.reviewerComments,aes_decrypt(qpd.normalisedReviewerRating,qpd.appraiseeId) as normalisedReviewerRating," +
            "qpd.normalizerComments,aes_decrypt(qpd.correctedNormalizerRating,qpd.appraiseeId) as correctedNormalizerRating,qpd.submitStatus,qpd.sendbackbyhr,qpd.quarter as reviewerQuarter," +
            "qpd.year as reviewerYear,emp.employee_number as appraiseeEmpId,CONCAT(emp.first_name,' ',emp.last_name) as appraiseeName," +
            "emp.work_email_address as appraiseeEmail,appraiser.employee_number as appraiserEmpId,CONCAT(appraiser.first_name,' '," +
            "appraiser.last_name) as appraiserName,appraiser.work_email_address as appraiserEmail,reviewer.employee_number as reviewerEmpId," +
            "CONCAT(reviewer.first_name,' ',reviewer.last_name) as reviewerName,reviewer.work_email_address as reviewerEmail," +
            "normalizer.employee_number as normalizerEmpId,CONCAT(normalizer.first_name,' ',normalizer.last_name) as normalizerName," +
            "normalizer.work_email_address as normalizerEmail,hr.employee_number as hrEmpId,CONCAT(hr.first_name,' ',hr.last_name) as hrName," +
            "hr.work_email_address as hrEmail from aa_eligible_associates qpd LEFT JOIN employees as emp on(qpd.appraiseeId=emp.id) " +
            "LEFT JOIN employees as appraiser on (appraiser.id=qpd.appraiserId) LEFT JOIN employees as reviewer on " +
            "(reviewer.id=qpd.reviewerId) LEFT JOIN employees as normalizer on (normalizer.id=qpd.normalizerId) " +
            "LEFT JOIN employees as hr on (hr.id=qpd.hrId) LEFT JOIN bands AS band ON ( band.id = qpd.bandId ) WHERE qpd.normalizerId={normalizerId} " +
            "AND qpd.year={appraisalYear} AND qpd.appraisalTriggered={triggerStatus} AND qpd.appraiserId={selectedAppraiserId}")
    public ManagerDTO[] getAppraiseesByNormalizerAndAppraiser(String selectedAppraiserId, String normalizerId, int appraisalYear, int submitStatusCheck,int triggerStatus);

    @SQL(statement="SELECT band.name AS bandName,qpd.departmentId,qpd.appraiseeId,qpd.appraiserId,qpd.reviewerId,aes_decrypt(qpd.appraiserRating,qpd.appraiseeId) as appraiserRating,aes_decrypt(qpd.reviewerRating,qpd.appraiseeId) as reviewerRating," +
    "aes_decrypt(qpd.normalisedReviewerRating,qpd.appraiseeId) as normalisedReviewerRating,qpd.normalizerComments,qpd.submitStatus,qpd.sendbackbyhr," +
            "qpd.quarter as reviewerQuarter,qpd.year as reviewerYear,emp.employee_number as appraiseeEmpId," +
            "CONCAT(emp.first_name,' ',emp.last_name) as appraiseeName,emp.work_email_address as appraiseeEmail," +
            "appraiser.employee_number as appraiserEmpId,CONCAT(appraiser.first_name,' ',appraiser.last_name) as appraiserName," +
            "appraiser.work_email_address as appraiserEmail,reviewer.employee_number as reviewerEmpId,CONCAT(reviewer.first_name,' '," +
            "reviewer.last_name) as reviewerName,reviewer.work_email_address as reviewerEmail from aa_eligible_associates qpd " +
            "LEFT JOIN employees as emp on(qpd.appraiseeId=emp.id) LEFT JOIN employees as appraiser on (appraiser.id=qpd.appraiserId) " +
            "LEFT JOIN employees as reviewer on (reviewer.id=qpd.reviewerId) LEFT JOIN bands AS band ON ( band.id = qpd.bandId ) WHERE qpd.normalizerId={normalizerId} " +
            " AND qpd.year={appraisalYear} AND qpd.appraisalTriggered={triggerStatus} " +
            "group by qpd.appraiserId")
    public ManagerDTO[] getAppraisersByNormalizer(String normalizerId, int appraisalYear, int submitStatusCheck,int triggerStatus);
    
    @SQL(statement="SELECT band.name AS bandName,qpd.departmentId,qpd.appraiseeId,qpd.appraiserId,qpd.reviewerId,aes_decrypt(qpd.appraiserRating,qpd.appraiseeId) as appraiserRating," +
    "aes_decrypt(qpd.reviewerRating,qpd.appraiseeId) as reviewerRating,aes_decrypt(qpd.normalisedReviewerRating,qpd.appraiseeId) as normalisedReviewerRating,qpd.normalizerComments," +
            "qpd.submitStatus,qpd.sendbackbyhr,qpd.quarter as reviewerQuarter,qpd.year as reviewerYear,emp.employee_number as appraiseeEmpId," +
            "CONCAT(emp.first_name,' ',emp.last_name) as appraiseeName,emp.work_email_address as appraiseeEmail," +
            "appraiser.employee_number as appraiserEmpId,CONCAT(appraiser.first_name,' ',appraiser.last_name) as appraiserName," +
            "appraiser.work_email_address as appraiserEmail,reviewer.employee_number as reviewerEmpId,CONCAT(reviewer.first_name,' '," +
            "reviewer.last_name) as reviewerName,reviewer.work_email_address as reviewerEmail from aa_eligible_associates qpd " +
            "LEFT JOIN employees as emp on(qpd.appraiseeId=emp.id) LEFT JOIN employees as appraiser on (appraiser.id=qpd.appraiserId) " +
            "LEFT JOIN employees as reviewer on (reviewer.id=qpd.reviewerId)  LEFT JOIN bands AS band ON ( band.id = qpd.bandId ) WHERE qpd.normalizerId={normalizerId} " +
            "AND qpd.year={appraisalYear} AND qpd.appraisalTriggered={triggerStatus} group by qpd.reviewerId")
    public ManagerDTO[] getReviewersByNormalizer(String normalizerId, int appraisalYear, int submitStatusCheck,int triggerStatus);
    
    @SQL(statement="SELECT band.name AS bandName,qpd.departmentId,qpd.appraiseeId,qpd.appraiserId,qpd.reviewerId,aes_decrypt(qpd.appraiserRating,qpd.appraiseeId) as appraiserRating,aes_decrypt(qpd.reviewerRating,qpd.appraiseeId) as reviewerRating," +
    "aes_decrypt(qpd.normalisedReviewerRating,qpd.appraiseeId) as normalisedReviewerRating,qpd.normalizerComments,qpd.submitStatus,qpd.sendbackbyhr," +
            "qpd.quarter as reviewerQuarter,qpd.year as reviewerYear,emp.employee_number as appraiseeEmpId," +
            "CONCAT(emp.first_name,' ',emp.last_name) as appraiseeName,emp.work_email_address as appraiseeEmail," +
            "appraiser.employee_number as appraiserEmpId,CONCAT(appraiser.first_name,' ',appraiser.last_name) as appraiserName," +
            "appraiser.work_email_address as appraiserEmail,reviewer.employee_number as reviewerEmpId,CONCAT(reviewer.first_name,' '," +
            "reviewer.last_name) as reviewerName,reviewer.work_email_address as reviewerEmail from aa_eligible_associates qpd " +
            "LEFT JOIN employees as emp on(qpd.appraiseeId=emp.id) LEFT JOIN employees as appraiser on (appraiser.id=qpd.appraiserId) " +
            "LEFT JOIN employees as reviewer on (reviewer.id=qpd.reviewerId) LEFT JOIN bands AS band ON ( band.id = qpd.bandId ) WHERE qpd.buhId={buhId} " +
            " AND qpd.year={appraisalYear} AND qpd.appraisalTriggered={triggerStatus} " +
            "group by qpd.appraiserId")
    public ManagerDTO[] getAppraisersByBuh(String buhId, int appraisalYear, int submitStatusCheck,int triggerStatus);

    @SQL(statement="SELECT band.name AS bandName,qpd.departmentId,qpd.appraiseeId,qpd.appraiserId,qpd.reviewerId,aes_decrypt(qpd.appraiserRating,qpd.appraiseeId) as appraiserRating," +
    "aes_decrypt(qpd.reviewerRating,qpd.appraiseeId) as reviewerRating,aes_decrypt(qpd.normalisedReviewerRating,qpd.appraiseeId) as normalisedReviewerRating,qpd.normalizerComments," +
            "qpd.submitStatus,qpd.sendbackbyhr,qpd.quarter as reviewerQuarter,qpd.year as reviewerYear,emp.employee_number as appraiseeEmpId," +
            "CONCAT(emp.first_name,' ',emp.last_name) as appraiseeName,emp.work_email_address as appraiseeEmail," +
            "appraiser.employee_number as appraiserEmpId,CONCAT(appraiser.first_name,' ',appraiser.last_name) as appraiserName," +
            "appraiser.work_email_address as appraiserEmail,reviewer.employee_number as reviewerEmpId,CONCAT(reviewer.first_name,' '," +
            "reviewer.last_name) as reviewerName,reviewer.work_email_address as reviewerEmail from aa_eligible_associates qpd " +
            "LEFT JOIN employees as emp on(qpd.appraiseeId=emp.id) LEFT JOIN employees as appraiser on (appraiser.id=qpd.appraiserId) " +
            "LEFT JOIN employees as reviewer on (reviewer.id=qpd.reviewerId)  LEFT JOIN bands AS band ON ( band.id = qpd.bandId ) WHERE qpd.buhId={buhId} " +
            "AND qpd.year={appraisalYear} AND qpd.appraisalTriggered={triggerStatus} group by qpd.reviewerId")
    public ManagerDTO[] getReviewersByBuh(String buhId, int appraisalYear, int submitStatusCheck,int triggerStatus);
    
    @SQL(statement="SELECT band.name AS bandName,qpd.departmentId,qpd.appraiseeId,qpd.appraiserId,qpd.reviewerId,qpd.normalizerId,aes_decrypt(qpd.appraiserRating,qpd.appraiseeId) as appraiserRating," +
    "aes_decrypt(qpd.reviewerRating,qpd.appraiseeId) as reviewerRating,aes_decrypt(qpd.normalisedReviewerRating,qpd.appraiseeId) as normalisedReviewerRating,qpd.normalizerComments," +
            "qpd.submitStatus,qpd.sendbackbyhr,qpd.quarter as reviewerQuarter,qpd.year as reviewerYear,emp.employee_number as appraiseeEmpId," +
            "CONCAT(emp.first_name,' ',emp.last_name) as appraiseeName,emp.work_email_address as appraiseeEmail," +
            "appraiser.employee_number as appraiserEmpId,CONCAT(appraiser.first_name,' ',appraiser.last_name) as appraiserName," +
            "appraiser.work_email_address as appraiserEmail,reviewer.employee_number as reviewerEmpId,CONCAT(reviewer.first_name,' '," +
            "reviewer.last_name) as reviewerName,reviewer.work_email_address as reviewerEmail,normalizer.employee_number as normalizerEmpId,CONCAT(normalizer.first_name,' '," +
            "normalizer.last_name) as normalizerName,normalizer.work_email_address as normalizerEmail from aa_eligible_associates qpd " +
            "LEFT JOIN employees as emp on(qpd.appraiseeId=emp.id) LEFT JOIN employees as appraiser on (appraiser.id=qpd.appraiserId) " +
            "LEFT JOIN employees as reviewer on (reviewer.id=qpd.reviewerId) LEFT JOIN employees as normalizer on (normalizer.id=qpd.normalizerId) LEFT JOIN bands AS band ON ( band.id = qpd.bandId ) WHERE qpd.buhId={buhId} " +
            "AND qpd.year={appraisalYear} AND qpd.appraisalTriggered={triggerStatus} group by qpd.normalizerId")
    public ManagerDTO[] getNormalizersByBuh(String buhId, int appraisalYear, int submitStatusCheck,int triggerStatus);

//    @SQL(statement="SELECT qpd.departmentId,qpd.appraiseeId,qpd.appraiserId,qpd.reviewerId,qpd.hrId,qpd.appraiserRating,qpd.reviewerRating,qpd.normalisedReviewerRating,qpd.normalizerComments,qpd.submitStatus,qpd.sendbackbyhr,qpd.quarter as reviewerQuarter,qpd.year as reviewerYear,emp.employee_number as appraiseeEmpId,CONCAT(emp.first_name,' ',emp.last_name) as appraiseeName,emp.work_email_address as appraiseeEmail,appraiser.employee_number as appraiserEmpId,CONCAT(appraiser.first_name,' ',appraiser.last_name) as appraiserName,appraiser.work_email_address as appraiserEmail,reviewer.employee_number as reviewerEmpId,CONCAT(reviewer.first_name,' ',reviewer.last_name) as reviewerName,reviewer.work_email_address as reviewerEmail from aa_eligible_associates qpd LEFT JOIN employees as emp on(qpd.appraiseeId=emp.id) LEFT JOIN employees as appraiser on (appraiser.id=qpd.appraiserId) LEFT JOIN employees as reviewer on (reviewer.id=qpd.reviewerId) LEFT JOIN employees as normalizer on (normalizer.id=qpd.normalizerId) LEFT JOIN employees as hr on (hr.id=qpd.hrId) WHERE qpd.normalizerId={normalizerId} AND qpd.quarter={appraisalQuarter} AND qpd.year={appraisalYear} AND qpd.reviewerId={selectedReviewerId}")
    @SQL(statement="SELECT qpd.departmentId,qpd.appraiseeId,qpd.appraiserId,qpd.reviewerId,qpd.hrId,qpd.bandId,aes_decrypt(qpd.appraiserRating,qpd.appraiseeId) as appraiserRating," +
    "qpd.appraiserComments,aes_decrypt(qpd.reviewerRating,qpd.appraiseeId) as reviewerRating,qpd.reviewerComments,aes_decrypt(qpd.normalisedReviewerRating,qpd.appraiseeId) as normalisedReviewerRating," +
            "qpd.normalizerComments,aes_decrypt(qpd.correctedNormalizerRating,qpd.appraiseeId) as correctedNormalizerRating,qpd.submitStatus,qpd.sendbackbyhr,qpd.quarter as reviewerQuarter,qpd.year as " +
            "reviewerYear,emp.employee_number as appraiseeEmpId,CONCAT(emp.first_name,' ',emp.last_name) as appraiseeName," +
            "emp.work_email_address as appraiseeEmail,appraiser.employee_number as appraiserEmpId," +
            "CONCAT(appraiser.first_name,' ',appraiser.last_name) as appraiserName,appraiser.work_email_address as appraiserEmail," +
            "reviewer.employee_number as reviewerEmpId,CONCAT(reviewer.first_name,' ',reviewer.last_name) as reviewerName," +
            "reviewer.work_email_address as reviewerEmail,normalizer.employee_number as normalizerEmpId," +
            "CONCAT(normalizer.first_name,' ',normalizer.last_name) as normalizerName,normalizer.work_email_address as normalizerEmail," +
            "hr.employee_number as hrEmpId,CONCAT(hr.first_name,' ',hr.last_name) as hrName,hr.work_email_address as hrEmail " +
            "from aa_eligible_associates qpd LEFT JOIN employees as emp on(qpd.appraiseeId=emp.id) LEFT JOIN employees as appraiser on" +
            " (appraiser.id=qpd.appraiserId) LEFT JOIN employees as reviewer on (reviewer.id=qpd.reviewerId) LEFT JOIN employees as normalizer on" +
            " (normalizer.id=qpd.normalizerId) LEFT JOIN employees as hr on (hr.id=qpd.hrId) WHERE qpd.normalizerId={normalizerId} AND" +
            " AND qpd.year={appraisalYear} AND qpd.appraisalTriggered={triggerStatus} AND" +
            " qpd.reviewerId={selectedReviewerId}")
    public ManagerDTO[] getAppraiseesByNormalizerAndReviewer(String selectedReviewerId, String normalizerId, int appraisalYear, int submitStatusCheck,int triggerStatus);

//    @SQL(statement="SELECT qpd.departmentId,qpd.appraiseeId,qpd.appraiserId,qpd.reviewerId,qpd.hrId,qpd.appraiserRating,qpd.reviewerRating,qpd.normalisedReviewerRating,qpd.normalizerComments,qpd.submitStatus,qpd.sendbackbyhr,qpd.quarter as reviewerQuarter,qpd.year as reviewerYear,emp.employee_number as appraiseeEmpId,CONCAT(emp.first_name,' ',emp.last_name) as appraiseeName,emp.work_email_address as appraiseeEmail,appraiser.employee_number as appraiserEmpId,CONCAT(appraiser.first_name,' ',appraiser.last_name) as appraiserName,appraiser.work_email_address as appraiserEmail,reviewer.employee_number as reviewerEmpId,CONCAT(reviewer.first_name,' ',reviewer.last_name) as reviewerName,reviewer.work_email_address as reviewerEmail from aa_eligible_associates qpd LEFT JOIN employees as emp on(qpd.appraiseeId=emp.id) LEFT JOIN employees as appraiser on (appraiser.id=qpd.appraiserId) LEFT JOIN employees as reviewer on (reviewer.id=qpd.reviewerId) LEFT JOIN employees as normalizer on (normalizer.id=qpd.normalizerId) LEFT JOIN employees as hr on (hr.id=qpd.hrId) WHERE qpd.normalizerId={normalizerId} AND qpd.quarter={appraisalQuarter} AND qpd.year={appraisalYear} AND qpd.appraiserId={selectedAppraiserId} AND qpd.reviewerId={selectedReviewerId}")
    @SQL(statement="SELECT qpd.departmentId,qpd.appraiseeId,qpd.appraiserId,qpd.reviewerId,qpd.hrId,qpd.bandId,aes_decrypt(qpd.appraiserRating,qpd.appraiseeId) as appraiserRating" +
    ",qpd.appraiserComments,aes_decrypt(qpd.reviewerRating,qpd.appraiseeId) as reviewerRating,qpd.reviewerComments,aes_decrypt(qpd.normalisedReviewerRating,qpd.appraiseeId) as normalisedReviewerRating," +
            "qpd.normalizerComments,aes_decrypt(qpd.correctedNormalizerRating,qpd.appraiseeId) as correctedNormalizerRating,qpd.submitStatus,qpd.sendbackbyhr,qpd.quarter as reviewerQuarter," +
            "qpd.year as reviewerYear,emp.employee_number as appraiseeEmpId,CONCAT(emp.first_name,' ',emp.last_name) as appraiseeName," +
            "emp.work_email_address as appraiseeEmail,appraiser.employee_number as appraiserEmpId,CONCAT(appraiser.first_name,' '," +
            "appraiser.last_name) as appraiserName,appraiser.work_email_address as appraiserEmail,reviewer.employee_number as reviewerEmpId," +
            "CONCAT(reviewer.first_name,' ',reviewer.last_name) as reviewerName,reviewer.work_email_address as reviewerEmail," +
            "normalizer.employee_number as normalizerEmpId,CONCAT(normalizer.first_name,' ',normalizer.last_name) as normalizerName," +
            "normalizer.work_email_address as normalizerEmail,hr.employee_number as hrEmpId,CONCAT(hr.first_name,' ',hr.last_name) as hrName," +
            "hr.work_email_address as hrEmail from aa_eligible_associates qpd LEFT JOIN employees as emp on(qpd.appraiseeId=emp.id) " +
            "LEFT JOIN employees as appraiser on (appraiser.id=qpd.appraiserId) LEFT JOIN employees as reviewer on (reviewer.id=qpd.reviewerId) LEFT JOIN employees as normalizer on (normalizer.id=qpd.normalizerId) LEFT JOIN employees as hr on (hr.id=qpd.hrId) WHERE qpd.normalizerId={normalizerId} AND qpd.year={appraisalYear} AND qpd.appraisalTriggered={triggerStatus} AND qpd.appraiserId={selectedAppraiserId} AND qpd.reviewerId={selectedReviewerId}")
    public ManagerDTO[] getAppraiseesByNormalizerAndAppraiserReviewer(String selectedAppraiserId, String selectedReviewerId, String normalizerId, int appraisalYear, int submitStatusCheck,int triggerStatus);

    @SQL(statement = "UPDATE aa_eligible_associates SET  normalisedReviewerRating = aes_encrypt({normalizerRating},{appraiseeId})," +
    "normalizerComments={justifyRatingReviewer},normalizerPromotionRec={normalizerPromotionRec} WHERE appraiseeId={appraiseeId} AND year={appraisalYear} AND normalizerId={normalizerId}")
    public void updateNormalizerRating(String normalizerId, int appraisalQuarter, int appraisalYear, int appraiseeId, int normalizerRating, String justifyRatingReviewer,String normalizerPromotionRec);

    @SQL(statement="SELECT qpd.departmentId,qpd.appraiseeId,qpd.appraiserId,qpd.reviewerId,qpd.normalizerId,qpd.bandId,aes_decrypt(qpd.appraiserRating,qpd.appraiseeId) as appraiserRating," +
    "qpd.appraiserComments,aes_decrypt(qpd.reviewerRating,qpd.appraiseeId) as reviewerRating,qpd.reviewerComments,aes_decrypt(qpd.normalisedReviewerRating,qpd.appraiseeId) as normalisedReviewerRating," +
            "qpd.normalizerComments,aes_decrypt(qpd.correctedNormalizerRating,qpd.appraiseeId) as correctedNormalizerRating,qpd.submitStatus,qpd.sendbackbyhr,qpd.quarter as reviewerQuarter,qpd.year as reviewerYear," +
            "emp.employee_number as appraiseeEmpId,CONCAT(emp.first_name,' ',emp.last_name) as appraiseeName,emp.work_email_address as appraiseeEmail," +
            "appraiser.employee_number as appraiserEmpId,CONCAT(appraiser.first_name,' ',appraiser.last_name) as appraiserName," +
            "appraiser.work_email_address as appraiserEmail,reviewer.employee_number as reviewerEmpId,CONCAT(reviewer.first_name,' ',reviewer.last_name) as" +
            " reviewerName,reviewer.work_email_address as reviewerEmail,normalizer.employee_number as normalizerEmpId," +
            "CONCAT(normalizer.first_name,' ',normalizer.last_name) as normalizerName,normalizer.work_email_address as normalizerEmail " +
            "from aa_eligible_associates qpd LEFT JOIN employees as emp on(qpd.appraiseeId=emp.id) LEFT JOIN employees as appraiser on " +
            "(appraiser.id=qpd.appraiserId) LEFT JOIN employees as reviewer on (reviewer.id=qpd.reviewerId) LEFT JOIN employees as normalizer on " +
            "(normalizer.id=qpd.normalizerId) WHERE qpd.hrId={hrId} AND qpd.year={appraisalYear} AND " +
            "qpd.appraisalTriggered={triggerStatus} AND qpd.normalizerId={selectedNormalizerId}")
    public ManagerDTO[] getAppraiseesByHrAndNormalizer(String hrId, int appraisalYear, String selectedNormalizerId,int triggerStatus);

    @SQL(statement="SELECT qpd.departmentId,qpd.appraiseeId,qpd.appraiserId,qpd.reviewerId,qpd.normalizerId,qpd.bandId,aes_decrypt(qpd.appraiserRating,qpd.appraiseeId) as appraiserRating," +
    "qpd.appraiserComments,aes_decrypt(qpd.reviewerRating,qpd.appraiseeId) as reviewerRating,qpd.reviewerComments,aes_decrypt(qpd.normalisedReviewerRating,qpd.appraiseeId) as normalisedReviewerRating," +
            "qpd.normalizerComments,qpd.submitStatus,qpd.sendbackbyhr,qpd.quarter as reviewerQuarter,qpd.year as reviewerYear," +
            "emp.employee_number as appraiseeEmpId,CONCAT(emp.first_name,' ',emp.last_name) as appraiseeName,emp.work_email_address as appraiseeEmail," +
            "appraiser.employee_number as appraiserEmpId,CONCAT(appraiser.first_name,' ',appraiser.last_name) as appraiserName," +
            "appraiser.work_email_address as appraiserEmail,reviewer.employee_number as reviewerEmpId,CONCAT(reviewer.first_name,' ',reviewer.last_name)" +
            " as reviewerName,reviewer.work_email_address as reviewerEmail,normalizer.employee_number as normalizerEmpId," +
            "CONCAT(normalizer.first_name,' ',normalizer.last_name) as normalizerName,normalizer.work_email_address as normalizerEmail " +
            "from aa_eligible_associates qpd LEFT JOIN employees as emp on(qpd.appraiseeId=emp.id) LEFT JOIN employees as appraiser " +
            "on (appraiser.id=qpd.appraiserId) LEFT JOIN employees as reviewer on (reviewer.id=qpd.reviewerId) LEFT JOIN employees as normalizer " +
            "on (normalizer.id=qpd.normalizerId) WHERE qpd.hrId={hrId} AND qpd.year={appraisalYear} " +
            "AND qpd.appraisalTriggered={triggerStatus} group by qpd.normalizerId")
    public ManagerDTO[] getNormalizersByHr(String hrId, int appraisalYear,int triggerStatus);

    @SQL(statement = "UPDATE aa_eligible_associates SET  normalisedReviewerRating = aes_encrypt({normalizerRating},{appraiseeId}) ,submitStatus={submitStatus},normalizerComments={normalizerComments},normalizerPromotionRec={normalizerPromotionRec}  WHERE appraiseeId={appraiseeId} AND year={appraisalYear} AND normalizerId={normalizerId}")
    public void updateSubmitStatusNormalizer(String normalizerId, int appraisalYear, int appraiseeId, int normalizerRating, int submitStatus, String normalizerComments,String normalizerPromotionRec);

//    @SQL(statement = "UPDATE aa_eligible_associates SET  correctedNormalizerRating = {normalizerRating} ,normalizerComments={justifyRatingReviewer} WHERE appraiseeId={appraiseeId} AND quarter={appraisalQuarter} AND year={appraisalYear} AND normalizerId={normalizerId}")
    @SQL(statement = "UPDATE aa_eligible_associates SET  correctedNormalizerRating = aes_encrypt({normalizerRating},{appraiseeId}),normalizerComments={justifyRatingReviewer},normalizerPromotionRec={normalizerPromotionRec} WHERE appraiseeId={appraiseeId}  AND year={appraisalYear} AND normalizerId={normalizerId}")
    public void updateCorrectedNormalizerRating(String normalizerId, int appraisalYear, int appraiseeId, int normalizerRating, String justifyRatingReviewer,String normalizerPromotionRec);

//    @SQL(statement = "UPDATE aa_eligible_associates SET correctedNormalizerRating = {normalizerRating} ,normalizerComments={normalizerComments}, submitStatus={submitStatus} WHERE appraiseeId={appraiseeId} AND quarter={appraisalQuarter} AND year={appraisalYear} AND normalizerId={normalizerId}")
    @SQL(statement = "UPDATE aa_eligible_associates SET correctedNormalizerRating = aes_encrypt({normalizerRating},{appraiseeId}) , submitStatus={submitStatus},normalizerComments={normalizerComments},normalizerPromotionRec={normalizerPromotionRec} WHERE appraiseeId={appraiseeId} AND year={appraisalYear} AND normalizerId={normalizerId}")
    public void updateSubmitStatusCorrectedNormalizerRating(String normalizerId, int appraisalYear, int appraiseeId, int normalizerRating, int submitStatus,String normalizerComments,String normalizerPromotionRec);

    @SQL(statement = "select cs.name AS appraiseeSBU, qpd.departmentId from aa_eligible_associates qpd LEFT JOIN company_structures cs ON (qpd.departmentId=cs.id) WHERE qpd.appraisalTriggered={triggerStatus} group by qpd.departmentId ")
    public ManagerDTO[] getCompanyStructureBrHr(String hrId, int appraisalQuarter, int appraisalYear,int triggerStatus);

    @SQL(statement="SELECT qpd.departmentId,qpd.appraiseeId,qpd.appraiserId,qpd.reviewerId,qpd.normalizerId,qpd.bandId,aes_decrypt(qpd.appraiserRating,qpd.appraiseeId) as appraiserRating," +
    "qpd.appraiserComments,aes_decrypt(qpd.reviewerRating,qpd.appraiseeId) as reviewerRating,qpd.reviewerComments,aes_decrypt(qpd.normalisedReviewerRating,qpd.appraiseeId) as normalisedReviewerRating," +
            "qpd.normalizerComments,aes_decrypt(qpd.correctedNormalizerRating,qpd.appraiseeId) as correctedNormalizerRating,qpd.submitStatus,qpd.sendbackbyhr,qpd.quarter as reviewerQuarter," +
            "qpd.year as reviewerYear,emp.employee_number as appraiseeEmpId,CONCAT(emp.first_name,' ',emp.last_name) as appraiseeName," +
            "emp.work_email_address as appraiseeEmail,appraiser.employee_number as appraiserEmpId,CONCAT(appraiser.first_name,' ',appraiser.last_name)" +
            " as appraiserName,appraiser.work_email_address as appraiserEmail,reviewer.employee_number as reviewerEmpId," +
            "CONCAT(reviewer.first_name,' ',reviewer.last_name) as reviewerName,reviewer.work_email_address as reviewerEmail," +
            "normalizer.employee_number as normalizerEmpId,CONCAT(normalizer.first_name,' ',normalizer.last_name) as normalizerName," +
            "normalizer.work_email_address as normalizerEmail from aa_eligible_associates qpd LEFT JOIN employees as emp on(qpd.appraiseeId=emp.id) " +
            "LEFT JOIN employees as appraiser on (appraiser.id=qpd.appraiserId) LEFT JOIN employees as reviewer on (reviewer.id=qpd.reviewerId) " +
            "LEFT JOIN employees as normalizer on (normalizer.id=qpd.normalizerId) WHERE qpd.hrId={hrId} " +
            "AND qpd.year={appraisalYear} AND qpd.appraisalTriggered={triggerStatus} AND qpd.departmentId={selectedSBUDepartmentId}")
    public ManagerDTO[] getAppraiseesByHrAndSBU(String hrId, int appraisalYear, String selectedSBUDepartmentId,int triggerStatus);

    @SQL(statement="SELECT qpd.departmentId,qpd.appraiseeId,qpd.appraiserId,qpd.reviewerId,qpd.normalizerId,qpd.bandId,aes_decrypt(qpd.appraiserRating,qpd.appraiseeId) as appraiserRating," +
    "qpd.appraiserComments,aes_decrypt(qpd.reviewerRating,qpd.appraiseeId) as reviewerRating,qpd.reviewerComments,aes_decrypt(qpd.normalisedReviewerRating,qpd.appraiseeId) as normalisedReviewerRating," +
            "qpd.normalizerComments,aes_decrypt(qpd.correctedNormalizerRating,qpd.appraiseeId) as correctedNormalizerRating,qpd.submitStatus,qpd.sendbackbyhr,qpd.quarter as reviewerQuarter," +
            "qpd.year as reviewerYear,emp.employee_number as appraiseeEmpId,CONCAT(emp.first_name,' ',emp.last_name) as appraiseeName," +
            "emp.work_email_address as appraiseeEmail,appraiser.employee_number as appraiserEmpId," +
            "CONCAT(appraiser.first_name,' ',appraiser.last_name) as appraiserName,appraiser.work_email_address as appraiserEmail," +
            "reviewer.employee_number as reviewerEmpId,CONCAT(reviewer.first_name,' ',reviewer.last_name) as reviewerName," +
            "reviewer.work_email_address as reviewerEmail,normalizer.employee_number as normalizerEmpId," +
            "CONCAT(normalizer.first_name,' ',normalizer.last_name) as normalizerName,normalizer.work_email_address as normalizerEmail " +
            "from aa_eligible_associates qpd LEFT JOIN employees as emp on(qpd.appraiseeId=emp.id) LEFT JOIN employees as appraiser" +
            " on (appraiser.id=qpd.appraiserId) LEFT JOIN employees as reviewer on (reviewer.id=qpd.reviewerId) LEFT JOIN employees as " +
            "normalizer on (normalizer.id=qpd.normalizerId) WHERE qpd.hrId={hrId} AND " +
            "qpd.year={appraisalYear} AND qpd.appraisalTriggered={triggerStatus} AND qpd.normalizerId={selectedNormalizerId} " +
            "AND qpd.departmentId={selectedSBUDepartmentId}")
    public ManagerDTO[] getAppraiseesByHrAndNormalizerAndSBU(String hrId, int appraisalYear, String selectedNormalizerId, String selectedSBUDepartmentId,int triggerStatus);


    @SQL(statement = "SELECT count({sql: referenceType}) as appraiseesCount FROM aa_eligible_associates where year= {appraisalYear} AND {sql: referenceType} = {referenceId} AND appraisalTriggered={triggerStatus}")
    public ManagerDTO getAppraiseeCount(String referenceId, int appraisalYear,String referenceType,int triggerStatus);
//    @SQL(statement = "UPDATE aa_eligible_associates SET  reviewerRating = {reviewerRating} WHERE appraiseeId={empid} AND quarter={appraisalQuarter} AND year={appraisalYear}")
//    public void updateReviewerRating(String reviewerId, int appraisalQuarter, int appraisalYear, int reviewerRating);

    
    
    
    
    
     @SQL(statement="SELECT band.name AS bandName,qpd.departmentId,qpd.appraiseeId,qpd.appraiserId,qpd.reviewerId,qpd.hrId,qpd.bandId,aes_decrypt(qpd.appraiserRating,qpd.appraiseeId) as appraiserRating," +
    "qpd.appraiserComments,aes_decrypt(qpd.reviewerRating,qpd.appraiseeId) as reviewerRating,qpd.reviewerComments,aes_decrypt(qpd.normalisedReviewerRating,qpd.appraiseeId) as normalisedReviewerRating," +
            "qpd.normalizerComments,aes_decrypt(qpd.correctedNormalizerRating,qpd.appraiseeId) as correctedNormalizerRating,qpd.submitStatus,qpd.sendbackbyhr,qpd.quarter as reviewerQuarter,qpd.year as reviewerYear," +
            "emp.employee_number as appraiseeEmpId,CONCAT(emp.first_name,' ',emp.last_name) as appraiseeName,emp.work_email_address as appraiseeEmail," +
            "appraiser.employee_number as appraiserEmpId,CONCAT(appraiser.first_name,' ',appraiser.last_name) as appraiserName," +
            "appraiser.work_email_address as appraiserEmail,reviewer.employee_number as reviewerEmpId,CONCAT(reviewer.first_name,' ',reviewer.last_name)" +
            " as reviewerName,reviewer.work_email_address as reviewerEmail,normalizer.employee_number as normalizerEmpId,CONCAT(normalizer.first_name,' '," +
            "normalizer.last_name) as normalizerName,normalizer.work_email_address as normalizerEmail,hr.employee_number as hrEmpId," +
            "CONCAT(hr.first_name,' ',hr.last_name) as hrName,hr.work_email_address as hrEmail,qpd.appraiserPromotionRec as appraiserPromotionRecommeded,qpd.reviewerPromotionRec as reviewerPromotionRec,normalizerPromotionRec as normalizerPromotionRec  from aa_eligible_associates qpd " +
            "LEFT JOIN employees as emp on(qpd.appraiseeId=emp.id) LEFT JOIN employees as appraiser on (appraiser.id=qpd.appraiserId) " +
            "LEFT JOIN employees as reviewer on (reviewer.id=qpd.reviewerId) LEFT JOIN employees as normalizer on (normalizer.id=qpd.normalizerId) " +
            "LEFT JOIN employees as hr on (hr.id=qpd.hrId) LEFT JOIN bands AS band ON ( band.id = qpd.bandId ) WHERE qpd.normalizerId={normalizerId} " +
            "AND qpd.year={appraisalYear} AND qpd.appraisalTriggered={triggerStatus} {sql: whereCondition}")
    public ManagerDTO[] getAppraiseesByNormalizer(String normalizerId, int appraisalYear, int submitStatusCheck,int triggerStatus, String whereCondition);
     
     @SQL(statement="SELECT band.name AS bandName,qpd.departmentId,qpd.appraiseeId,qpd.appraiserId,qpd.reviewerId,qpd.hrId,qpd.bandId,aes_decrypt(qpd.appraiserRating,qpd.appraiseeId) as appraiserRating," +
    "qpd.appraiserComments,aes_decrypt(qpd.reviewerRating,qpd.appraiseeId) as reviewerRating,qpd.reviewerComments,aes_decrypt(qpd.normalisedReviewerRating,qpd.appraiseeId) as normalisedReviewerRating," +
            "qpd.normalizerComments,aes_decrypt(qpd.correctedNormalizerRating,qpd.appraiseeId) as correctedNormalizerRating,qpd.submitStatus,qpd.sendbackbyhr,qpd.quarter as reviewerQuarter,qpd.year as reviewerYear," +
            "emp.employee_number as appraiseeEmpId,CONCAT(emp.first_name,' ',emp.last_name) as appraiseeName,emp.work_email_address as appraiseeEmail," +
            "appraiser.employee_number as appraiserEmpId,CONCAT(appraiser.first_name,' ',appraiser.last_name) as appraiserName," +
            "appraiser.work_email_address as appraiserEmail,reviewer.employee_number as reviewerEmpId,CONCAT(reviewer.first_name,' ',reviewer.last_name)" +
            " as reviewerName,reviewer.work_email_address as reviewerEmail,normalizer.employee_number as normalizerEmpId,CONCAT(normalizer.first_name,' '," +
            "normalizer.last_name) as normalizerName,normalizer.work_email_address as normalizerEmail,hr.employee_number as hrEmpId," +
            "CONCAT(hr.first_name,' ',hr.last_name) as hrName,hr.work_email_address as hrEmail,qpd.appraiserPromotionRec as appraiserPromotionRecommeded,qpd.reviewerPromotionRec as reviewerPromotionRec,normalizerPromotionRec as normalizerPromotionRec  from aa_eligible_associates qpd " +
            "LEFT JOIN employees as emp on(qpd.appraiseeId=emp.id) LEFT JOIN employees as appraiser on (appraiser.id=qpd.appraiserId) " +
            "LEFT JOIN employees as reviewer on (reviewer.id=qpd.reviewerId) LEFT JOIN employees as normalizer on (normalizer.id=qpd.normalizerId) " +
            "LEFT JOIN employees as hr on (hr.id=qpd.hrId) LEFT JOIN bands AS band ON ( band.id = qpd.bandId ) WHERE qpd.buhId={buhId} " +
            "AND qpd.year={appraisalYear} AND qpd.appraisalTriggered={triggerStatus} {sql: whereCondition}")
    public ManagerDTO[] getAppraiseesByBuh(String buhId, int appraisalYear, int submitStatusCheck,int triggerStatus, String whereCondition);

    @SQL(statement="Select achievementId,keyResultArea,appraiserRemarks from aa_top_acheivements where appraiseeId={appraiseeId} AND appraisalYear={appraisalYear} AND deleted=0")
    public AppraiserFormDTO[] getAchievementsData(int appraisalYear, int appraiseeId);

    @SQL(statement="Select needsId,developmentNeeds,apprDevRemarks from aa_dev_needs where appraiseeId={appraiseeId} AND appraisalYear={appraisalYear} AND deleted=0")
    public AppraiserFormDTO[] getDevelopmentData(int appraisalYear, int appraiseeId);

    @SQL(statement="Select goalId,goalData,appGoalRemarks from aa_goal_sheet where appraiseeId={appraiseeId} AND appraisalYear={appraisalYear} AND deleted=0")
    public AppraiserFormDTO[] getGoalData(int appraisalYear, int appraiseeId);

    @SQL(statement="select band.name AS bandName,qpdId,submitStatus as appraisalSubmitStatus,appraiserComments,departmentId,areasOfImprovement,technologyTraining," +
    "softskillTraining,appraiserPromotionRec as appraiserPromotionRecommeded , emp.employee_number AS appraiseeNumberForm," +
    " CONCAT( emp.first_name, '', emp.last_name ) AS appraiseeNameForm,CASE emp.employment_status WHEN 'p' THEN 'Permanant' END AS appraiseeStatusForm, " +
    "date_format( emp.joined_date, '%d-%m-%Y' ) AS appraiseeJoiningDateForm from aa_eligible_associates as qpd " +
    " LEFT JOIN employees AS emp ON ( emp.id = qpd.appraiseeId ) LEFT JOIN bands AS band ON ( band.id = qpd.bandId ) where qpd.appraiseeId={appraiseeId} and qpd.year={appraisalYear}")
    public AppraiseeListDTO getSelectedAppraiseeDetails(int appraiseeId, int appraisalYear);


}
