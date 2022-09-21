/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.qpd.managers.dto;
import java.io.Serializable;
import org.apache.beehive.netui.pageflow.annotations.Jpf;
/**
 *
 * @author Administrator
 */
@Jpf.FormBean()
public class ManagerDTO implements Serializable{

    public String reviewerMenuCheck;
    public String bandName;
    public int appraiseeId;
    public int appraiserId;
    public int bandId;
    public int qpdId;
    public String appraiserRating;
                public String reviewerRating;
    public int[] reviewerRatingForm;
    public int[] correctedReviewerRatingForm;
    public int[] appraiseeIdForm;
    public int[] submitStatusForm;
    public String[] justifyRatingReviewer;
    public String reviewerComments;
    public String appraiserComments;
    public String[] justifyRatingNormalizer;
    public String normalizerComments;
    public String[] normalizerEmailForm;
    public String[] hrEmailForm;
                 public String correctedNormalizerRating;
                 public String normalisedReviewerRating;
    public String[] normalizerNameForm;
    public String[] hrNameForm;
    public String submitToNormalizer;
    public String appraiseesCount;
    public String button;
    public String appraiserCommentsNew;

    public int myappraiserId;
    public int myreviewerId;
    public int mynormalizerId;


    public String[] reviewerPromotionRecommended;
    public String reviewerPromotionRec;
    public String[] normalizerPromotionRecommended;
    public String normalizerPromotionRec;

    public int getQpdId() {
        return qpdId;
    }

    public void setQpdId(int qpdId) {
        this.qpdId = qpdId;
    }

    
    
    public int[] getCorrectedReviewerRatingForm() {
        return correctedReviewerRatingForm;
    }

    public void setCorrectedReviewerRatingForm(int[] correctedReviewerRatingForm) {
        this.correctedReviewerRatingForm = correctedReviewerRatingForm;
    }

    public String appraiserPromotionRecommeded;

    private int qpdKraId;

    public int getQpdKraId() {
        return qpdKraId;
    }

    public void setQpdKraId(int qpdKraId) {
        this.qpdKraId = qpdKraId;
    }

    public String getNormalizerPromotionRec() {
        return normalizerPromotionRec;
    }

    public void setNormalizerPromotionRec(String normalizerPromotionRec) {
        this.normalizerPromotionRec = normalizerPromotionRec;
    }

    public String[] getNormalizerPromotionRecommended() {
        return normalizerPromotionRecommended;
    }

    public void setNormalizerPromotionRecommended(String[] normalizerPromotionRecommended) {
        this.normalizerPromotionRecommended = normalizerPromotionRecommended;
    }


    public String getReviewerPromotionRec() {
        return reviewerPromotionRec;
    }

    public void setReviewerPromotionRec(String reviewerPromotionRec) {
        this.reviewerPromotionRec = reviewerPromotionRec;
    }

    public String[] getReviewerPromotionRecommended() {
        return reviewerPromotionRecommended;
    }

    public void setReviewerPromotionRecommended(String[] reviewerPromotionRecommended) {
        this.reviewerPromotionRecommended = reviewerPromotionRecommended;
    }

    public String getAppraiserPromotionRecommeded() {
        return appraiserPromotionRecommeded;
    }

    public void setAppraiserPromotionRecommeded(String appraiserPromotionRecommeded) {
        this.appraiserPromotionRecommeded = appraiserPromotionRecommeded;
    }

    public String getAppraiseesCount() {
        return appraiseesCount;
    }

    public void setAppraiseesCount(String appraiseesCount) {
        this.appraiseesCount = appraiseesCount;
    }


    public String getSubmitToNormalizer() {
        return submitToNormalizer;
    }

    public void setSubmitToNormalizer(String submitToNormalizer) {
        this.submitToNormalizer = submitToNormalizer;
    }

    public String[] getHrNameForm() {
        return hrNameForm;
    }

    public void setHrNameForm(String[] hrNameForm) {
        this.hrNameForm = hrNameForm;
    }

    public String[] getNormalizerNameForm() {
        return normalizerNameForm;
    }

    public void setNormalizerNameForm(String[] normalizerNameForm) {
        this.normalizerNameForm = normalizerNameForm;
    }

    public String[] getHrEmailForm() {
        return hrEmailForm;
    }

    public void setHrEmailForm(String[] hrEmailForm) {
        this.hrEmailForm = hrEmailForm;
    }

    public String[] getNormalizerEmailForm() {
        return normalizerEmailForm;
    }

    public void setNormalizerEmailForm(String[] normalizerEmailForm) {
        this.normalizerEmailForm = normalizerEmailForm;
    }

    public String getNormalizerComments() {
        return normalizerComments;
    }

    public void setNormalizerComments(String normalizerComments) {
        this.normalizerComments = normalizerComments;
    }

    public String[] getJustifyRatingNormalizer() {
        return justifyRatingNormalizer;
    }

    public void setJustifyRatingNormalizer(String[] justifyRatingNormalizer) {
        this.justifyRatingNormalizer = justifyRatingNormalizer;
    }
    
    public int getBandId() {
        return bandId;
    }

    public void setBandId(int bandId) {
        this.bandId = bandId;
    }

    public String getAppraiserComments() {
        return appraiserComments;
    }

    public void setAppraiserComments(String appraiserComments) {
        this.appraiserComments = appraiserComments;
    }

    public String getReviewerComments() {
        return reviewerComments;
    }

    public void setReviewerComments(String reviewerComments) {
        this.reviewerComments = reviewerComments;
    }

    public String[] getJustifyRatingReviewer() {
        return justifyRatingReviewer;
    }

    public void setJustifyRatingReviewer(String[] justifyRatingReviewer) {
        this.justifyRatingReviewer = justifyRatingReviewer;
    }

    public int[] getSubmitStatusForm() {
        return submitStatusForm;
    }

    public void setSubmitStatusForm(int[] submitStatusForm) {
        this.submitStatusForm = submitStatusForm;
    }

    public int[] getAppraiseeIdForm() {
        return appraiseeIdForm;
    }

    public void setAppraiseeIdForm(int[] appraiseeIdForm) {
        this.appraiseeIdForm = appraiseeIdForm;
    }

    public int[] getReviewerRatingForm() {
        return reviewerRatingForm;
    }

    public void setReviewerRatingForm(int[] reviewerRatingForm) {
        this.reviewerRatingForm = reviewerRatingForm;
    }
                    
    public int submitStatus;
    public int sendbackbyhr;
    public String appraiseeName;
    public String appraiseeEmail;
    public String appraiserName;
    public String appraiserEmail;
    public String appraiseeEmpId;
    public String appraiserEmpId;
    public int reviewerQuarter;
    public int reviewerYear;
    public String reviewerEmpId;
    public String reviewerName;
    public String reviewerEmail;
    public int reviewerId;
    public int normalizerId;
    public String normalizerName;
    public String normalizerEmail;
    public String normalizerEmpId;
    public int departmentId;
    public int hrId;
    public String hrEmpId;
    public String hrName;
    public String hrEmail;
    public int[] sendbackbyhrForm;
    public String appraiseeSBU;

    public String getAppraiseeSBU() {
        return appraiseeSBU;
    }

    public void setAppraiseeSBU(String appraiseeSBU) {
        this.appraiseeSBU = appraiseeSBU;
    }

    public int[] getSendbackbyhrForm() {
        return sendbackbyhrForm;
    }

    public void setSendbackbyhrForm(int[] sendbackbyhrForm) {
        this.sendbackbyhrForm = sendbackbyhrForm;
    }

    public String getHrEmail() {
        return hrEmail;
    }

    public void setHrEmail(String hrEmail) {
        this.hrEmail = hrEmail;
    }

    public String getHrEmpId() {
        return hrEmpId;
    }

    public void setHrEmpId(String hrEmpId) {
        this.hrEmpId = hrEmpId;
    }

    public int getHrId() {
        return hrId;
    }

    public void setHrId(int hrId) {
        this.hrId = hrId;
    }

    public String getHrName() {
        return hrName;
    }

    public void setHrName(String hrName) {
        this.hrName = hrName;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getNormalizerEmail() {
        return normalizerEmail;
    }

    public void setNormalizerEmail(String normalizerEmail) {
        this.normalizerEmail = normalizerEmail;
    }

    public String getNormalizerName() {
        return normalizerName;
    }

    public void setNormalizerName(String normalizerName) {
        this.normalizerName = normalizerName;
    }

    public int getNormalizerId() {
        return normalizerId;
    }

    public void setNormalizerId(int normalizerId) {
        this.normalizerId = normalizerId;
    }

    public int getReviewerId() {
        return reviewerId;
    }

    public void setReviewerId(int reviewerId) {
        this.reviewerId = reviewerId;
    }

    public String getReviewerEmail() {
        return reviewerEmail;
    }

    public void setReviewerEmail(String reviewerEmail) {
        this.reviewerEmail = reviewerEmail;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }

    public int getReviewerQuarter() {
        return reviewerQuarter;
    }

    public void setReviewerQuarter(int reviewerQuarter) {
        this.reviewerQuarter = reviewerQuarter;
    }

    public int getReviewerYear() {
        return reviewerYear;
    }

    public void setReviewerYear(int reviewerYear) {
        this.reviewerYear = reviewerYear;
    }

    public String getAppraiseeEmail() {
        return appraiseeEmail;
    }

    public void setAppraiseeEmail(String appraiseeEmail) {
        this.appraiseeEmail = appraiseeEmail;
    }

    public int getAppraiseeId() {
        return appraiseeId;
    }

    public void setAppraiseeId(int appraiseeId) {
        this.appraiseeId = appraiseeId;
    }

    public String getAppraiseeName() {
        return appraiseeName;
    }

    public void setAppraiseeName(String appraiseeName) {
        this.appraiseeName = appraiseeName;
    }

    public String getAppraiserEmail() {
        return appraiserEmail;
    }

    public void setAppraiserEmail(String appraiserEmail) {
        this.appraiserEmail = appraiserEmail;
    }

    public int getAppraiserId() {
        return appraiserId;
    }

    public void setAppraiserId(int appraiserId) {
        this.appraiserId = appraiserId;
    }

    public String getAppraiserName() {
        return appraiserName;
    }

    public void setAppraiserName(String appraiserName) {
        this.appraiserName = appraiserName;
    }

    public int getSendbackbyhr() {
        return sendbackbyhr;
    }

    public void setSendbackbyhr(int sendbackbyhr) {
        this.sendbackbyhr = sendbackbyhr;
    }

    public int getSubmitStatus() {
        return submitStatus;
    }

    public void setSubmitStatus(int submitStatus) {
        this.submitStatus = submitStatus;
    }

    public int appraiseeIdSelected;
    public int qpdIdSelected;
    public int appraiseeEmpIdSelected;
    public int bandIdSelected;
    public int appraisalQuarterSelected;
    public int appraisalYearSelected;
    public String appraiserNameSelected;
    public int departmentIdSelected;
    public String reasonSendbackHr;

    public int getQpdIdSelected() {
        return qpdIdSelected;
    }

    public void setQpdIdSelected(int qpdIdSelected) {
        this.qpdIdSelected = qpdIdSelected;
    }

    
    public String getReasonSendbackHr() {
        return reasonSendbackHr;
    }

    public void setReasonSendbackHr(String reasonSendbackHr) {
        this.reasonSendbackHr = reasonSendbackHr;
    }

    public int getDepartmentIdSelected() {
        return departmentIdSelected;
    }

    public void setDepartmentIdSelected(int departmentIdSelected) {
        this.departmentIdSelected = departmentIdSelected;
    }

    public int getAppraisalQuarterSelected() {
        return appraisalQuarterSelected;
    }

    public void setAppraisalQuarterSelected(int appraisalQuarterSelected) {
        this.appraisalQuarterSelected = appraisalQuarterSelected;
    }

    public int getAppraisalYearSelected() {
        return appraisalYearSelected;
    }

    public void setAppraisalYearSelected(int appraisalYearSelected) {
        this.appraisalYearSelected = appraisalYearSelected;
    }

    public int getAppraiseeEmpIdSelected() {
        return appraiseeEmpIdSelected;
    }

    public void setAppraiseeEmpIdSelected(int appraiseeEmpIdSelected) {
        this.appraiseeEmpIdSelected = appraiseeEmpIdSelected;
    }

    public int getAppraiseeIdSelected() {
        return appraiseeIdSelected;
    }

    public void setAppraiseeIdSelected(int appraiseeIdSelected) {
        this.appraiseeIdSelected = appraiseeIdSelected;
    }

    public String getAppraiserNameSelected() {
        return appraiserNameSelected;
    }

    public void setAppraiserNameSelected(String appraiserNameSelected) {
        this.appraiserNameSelected = appraiserNameSelected;
    }

    public int getBandIdSelected() {
        return bandIdSelected;
    }

    public void setBandIdSelected(int bandIdSelected) {
        this.bandIdSelected = bandIdSelected;
    }

    public String kraDescription;
    public String attributes;
    public int weightage;
    public String performanceCriteria;
    public String measurementCriteria;
    public String selfComments;
    public int appraiserIntitialRatings;
    public String appraiseeInfoReviewer;

    public String getAppraiseeInfoReviewer() {
        return appraiseeInfoReviewer;
    }

    public void setAppraiseeInfoReviewer(String appraiseeInfoReviewer) {
        this.appraiseeInfoReviewer = appraiseeInfoReviewer;
    }

    public int getAppraiserIntitialRatings() {
        return appraiserIntitialRatings;
    }

    public void setAppraiserIntitialRatings(int appraiserIntitialRatings) {
        this.appraiserIntitialRatings = appraiserIntitialRatings;
    }

    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    public String getKraDescription() {
        return kraDescription;
    }

    public void setKraDescription(String kraDescription) {
        this.kraDescription = kraDescription;
    }

    public String getMeasurementCriteria() {
        return measurementCriteria;
    }

    public void setMeasurementCriteria(String measurementCriteria) {
        this.measurementCriteria = measurementCriteria;
    }

    public String getPerformanceCriteria() {
        return performanceCriteria;
    }

    public void setPerformanceCriteria(String performanceCriteria) {
        this.performanceCriteria = performanceCriteria;
    }

    public String getSelfComments() {
        return selfComments;
    }

    public void setSelfComments(String selfComments) {
        this.selfComments = selfComments;
    }

    public int getWeightage() {
        return weightage;
    }

    public void setWeightage(int weightage) {
        this.weightage = weightage;
    }

    public String getAppraiserRating() {
        return appraiserRating;
    }

    public void setAppraiserRating(String appraiserRating) {
        this.appraiserRating = appraiserRating;
    }

    public String getCorrectedNormalizerRating() {
        return correctedNormalizerRating;
    }

    public void setCorrectedNormalizerRating(String correctedNormalizerRating) {
        this.correctedNormalizerRating = correctedNormalizerRating;
    }

    public String getNormalisedReviewerRating() {
        return normalisedReviewerRating;
    }

    public void setNormalisedReviewerRating(String normalisedReviewerRating) {
        this.normalisedReviewerRating = normalisedReviewerRating;
    }

    public String getReviewerRating() {
        return reviewerRating;
    }

    public void setReviewerRating(String reviewerRating) {
        this.reviewerRating = reviewerRating;
    }

    public int getMyappraiserId() {
        return myappraiserId;
    }

    public void setMyappraiserId(int myappraiserId) {
        this.myappraiserId = myappraiserId;
    }

    public int getMyreviewerId() {
        return myreviewerId;
    }

    public void setMyreviewerId(int myreviewerId) {
        this.myreviewerId = myreviewerId;
    }

    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }

    public String getAppraiserCommentsNew() {
        return appraiserCommentsNew;
    }

    public void setAppraiserCommentsNew(String appraiserCommentsNew) {
        this.appraiserCommentsNew = appraiserCommentsNew;
    }

    public String getAppraiserEmpId() {
        return appraiserEmpId;
    }

    public void setAppraiserEmpId(String appraiserEmpId) {
        this.appraiserEmpId = appraiserEmpId;
    }

    public String getNormalizerEmpId() {
        return normalizerEmpId;
    }

    public void setNormalizerEmpId(String normalizerEmpId) {
        this.normalizerEmpId = normalizerEmpId;
    }

    public String getReviewerEmpId() {
        return reviewerEmpId;
    }

    public void setReviewerEmpId(String reviewerEmpId) {
        this.reviewerEmpId = reviewerEmpId;
    }

    public String getAppraiseeEmpId() {
        return appraiseeEmpId;
    }

    public void setAppraiseeEmpId(String appraiseeEmpId) {
        this.appraiseeEmpId = appraiseeEmpId;
    }

    public String getReviewerMenuCheck() {
        return reviewerMenuCheck;
    }

    public void setReviewerMenuCheck(String reviewerMenuCheck) {
        this.reviewerMenuCheck = reviewerMenuCheck;
    }

    public String getBandName() {
        return bandName;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }

    public int getMynormalizerId() {
        return mynormalizerId;
    }

    public void setMynormalizerId(int mynormalizerId) {
        this.mynormalizerId = mynormalizerId;
    }
    
}
