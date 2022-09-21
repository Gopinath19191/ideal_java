/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.qpd.appraiser.dto;

import java.util.Date;

/**
 *
 * @author HARIHARAN.C
 */
public class AppraiserRatingFormDTO {
    public int appraiseeYear;
    public String appraiserId;
    public String buttonAction;
    public int appraiseeQuarter;
    public int appraiseeId;
    public int[] qpdKraIdHidden;
    public int[] kraQtrIdHidden;
    public String[] appraiseeComments;
    public int[] appraiserCommentsRating;
    public String[] appraiserCommentsNew;
    public int finalRating;
    public int bandId;
    public int submitStatus;
    private String appraiseeName;
    private String appraiseeJoiningDate;
    private String appraiseeStatus;
    private String appraiseeNumber;
    public String justifyRatingAppraiser;
    public String reasonSendbackAppraiser;
 private String dsdate;
    public String areasOfImprovement;
    public String technologyTraining;
    public String softskillTraining;
    public int qpdId;
    
    public int[] achievementId;
    public String[] appraiserRemarks;
    
    public int[] developmentNeedsId;
    public String[] apprDevelopmentRemarks;

    public int[] goalsId;
    public String[] apprGoalRemarks;
                                                                      

    // For Promotion recommended by the appraiser need the below variable
    public String appraiserPromotionRecommeded;

    public String qpdQuarter;
    public String qpdYear;
    public int yearQPD;
    public String qpdRating;
    private String datePicker; 

    public String getAppraiserPromotionRecommeded() {
        return appraiserPromotionRecommeded;
    }

    public void setAppraiserPromotionRecommeded(String appraiserPromotionRecommeded) {
        this.appraiserPromotionRecommeded = appraiserPromotionRecommeded;
    }

    public String getReasonSendbackAppraiser() {
        return reasonSendbackAppraiser;
    }

    public void setReasonSendbackAppraiser(String reasonSendbackAppraiser) {
        this.reasonSendbackAppraiser = reasonSendbackAppraiser;
    }

    public String getJustifyRatingAppraiser() {
        return justifyRatingAppraiser;
    }

    public void setJustifyRatingAppraiser(String justifyRatingAppraiser) {
        this.justifyRatingAppraiser = justifyRatingAppraiser;
    }

    public String getAppraiseeJoiningDate() {
        return appraiseeJoiningDate;
    }

    public void setAppraiseeJoiningDate(String appraiseeJoiningDate) {
        this.appraiseeJoiningDate = appraiseeJoiningDate;
    }

    public String getAppraiseeName() {
        return appraiseeName;
    }

    public void setAppraiseeName(String appraiseeName) {
        this.appraiseeName = appraiseeName;
    }

    public String getAppraiseeNumber() {
        return appraiseeNumber;
    }

    public void setAppraiseeNumber(String appraiseeNumber) {
        this.appraiseeNumber = appraiseeNumber;
    }

    public String getAppraiseeStatus() {
        return appraiseeStatus;
    }

    public void setAppraiseeStatus(String appraiseeStatus) {
        this.appraiseeStatus = appraiseeStatus;
    }



    public int fetchApprasalQuarter;
    public int fetchApprasalYear;

    public int getFetchApprasalQuarter() {
        return fetchApprasalQuarter;
    }

    public void setFetchApprasalQuarter(int fetchApprasalQuarter) {
        this.fetchApprasalQuarter = fetchApprasalQuarter;
    }

    public int getFetchApprasalYear() {
        return fetchApprasalYear;
    }

    public void setFetchApprasalYear(int fetchApprasalYear) {
        this.fetchApprasalYear = fetchApprasalYear;
    }

    public int getSubmitStatus() {
        return submitStatus;
    }

    public void setSubmitStatus(int submitStatus) {
        this.submitStatus = submitStatus;
    }


    public int getBandId() {
        return bandId;
    }

    public void setBandId(int bandId) {
        this.bandId = bandId;
    }

    public int getAppraiseeId() {
        return appraiseeId;
    }

    public void setAppraiseeId(int appraiseeId) {
        this.appraiseeId = appraiseeId;
    }

    public String[] getAppraiseeComments() {
        return appraiseeComments;
    }

    public void setAppraiseeComments(String[] appraiseeComments) {
        this.appraiseeComments = appraiseeComments;
    }

    public int getAppraiseeQuarter() {
        return appraiseeQuarter;
    }

    public void setAppraiseeQuarter(int appraiseeQuarter) {
        this.appraiseeQuarter = appraiseeQuarter;
    }

    public int getAppraiseeYear() {
        return appraiseeYear;
    }

    public void setAppraiseeYear(int appraiseeYear) {
        this.appraiseeYear = appraiseeYear;
    }

    public int[] getAppraiserCommentsRating() {
        return appraiserCommentsRating;
    }

    public void setAppraiserCommentsRating(int[] appraiserCommentsRating) {
        this.appraiserCommentsRating = appraiserCommentsRating;
    }

    public int getFinalRating() {
        return finalRating;
    }

    public void setFinalRating(int finalRating) {
        this.finalRating = finalRating;
    }

    public int[] getKraQtrIdHidden() {
        return kraQtrIdHidden;
    }

    public void setKraQtrIdHidden(int[] kraQtrIdHidden) {
        this.kraQtrIdHidden = kraQtrIdHidden;
    }

    public int[] getQpdKraIdHidden() {
        return qpdKraIdHidden;
    }

    public void setQpdKraIdHidden(int[] qpdKraIdHidden) {
        this.qpdKraIdHidden = qpdKraIdHidden;
    }

    public String[] getAppraiserCommentsNew() {
        return appraiserCommentsNew;
    }

    public void setAppraiserCommentsNew(String[] appraiserCommentsNew) {
        this.appraiserCommentsNew = appraiserCommentsNew;
    }

    public String getAreasOfImprovement() {
        return areasOfImprovement;
    }

    public void setAreasOfImprovement(String areasOfImprovement) {
        this.areasOfImprovement = areasOfImprovement;
    }

    public String getSoftskillTraining() {
        return softskillTraining;
    }

    public void setSoftskillTraining(String softskillTraining) {
        this.softskillTraining = softskillTraining;
    }

    public String getTechnologyTraining() {
        return technologyTraining;
    }

    public void setTechnologyTraining(String technologyTraining) {
        this.technologyTraining = technologyTraining;
    }

    public int getQpdId() {
        return qpdId;
    }

    public void setQpdId(int qpdId) {
        this.qpdId = qpdId;
    }

    public int[] getAchievementId() {
        return achievementId;
    }

    public void setAchievementId(int[] achievementId) {
        this.achievementId = achievementId;
    }

    public String[] getAppraiserRemarks() {
        return appraiserRemarks;
    }

    public void setAppraiserRemarks(String[] appraiserRemarks) {
        this.appraiserRemarks = appraiserRemarks;
    }

    public String[] getApprDevelopmentRemarks() {
        return apprDevelopmentRemarks;
    }

    public void setApprDevelopmentRemarks(String[] apprDevelopmentRemarks) {
        this.apprDevelopmentRemarks = apprDevelopmentRemarks;
    }

    public int[] getDevelopmentNeedsId() {
        return developmentNeedsId;
    }

    public void setDevelopmentNeedsId(int[] developmentNeedsId) {
        this.developmentNeedsId = developmentNeedsId;
    }

    public String[] getApprGoalRemarks() {
        return apprGoalRemarks;
    }

    public void setApprGoalRemarks(String[] apprGoalRemarks) {
        this.apprGoalRemarks = apprGoalRemarks;
    }

    public int[] getGoalsId() {
        return goalsId;
    }

    public void setGoalsId(int[] goalsId) {
        this.goalsId = goalsId;
    }

    public String getQpdQuarter() {
        return qpdQuarter;
    }

    public void setQpdQuarter(String qpdQuarter) {
        this.qpdQuarter = qpdQuarter;
    }

    public String getQpdRating() {
        return qpdRating;
    }

    public void setQpdRating(String qpdRating) {
        this.qpdRating = qpdRating;
    }

    public String getQpdYear() {
        return qpdYear;
    }

    public void setQpdYear(String qpdYear) {
        this.qpdYear = qpdYear;
    }

    public int getYearQPD() {
        return yearQPD;
    }

    public void setYearQPD(int yearQPD) {
        this.yearQPD = yearQPD;
    }

    public String getAppraiserId() {
        return appraiserId;
    }

    public void setAppraiserId(String appraiserId) {
        this.appraiserId = appraiserId;
    }

    public String getButtonAction() {
        return buttonAction;
    }

    public void setButtonAction(String buttonAction) {
        this.buttonAction = buttonAction;
    }

    public String getDatePicker() {
        return datePicker;
    }

    public void setDatePicker(String datePicker) {
        this.datePicker = datePicker;
    }

    public String getDsdate() {
        return dsdate;
    }

    public void setDsdate(String dsdate) {
        this.dsdate = dsdate;
    }
   
}
