/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.qpd.appraiser.dto;
import org.apache.beehive.netui.pageflow.annotations.Jpf;
/**
 *
 * @author Administrator
 */
@Jpf.FormBean()
public class AppraiserFormDTO {

    private int achievementId;
    private String keyResultArea,appraiserRemarks;

    private int needsId;
    private String developmentNeeds,apprDevRemarks;

    private int goalId;
    private String goalData,appGoalRemarks;


    private String kraDescription;
    private String attributes;
    private int weightage;
    private String performanceCriteria;
    private String measurementCriteria;
    private int qpdKraId;
    private String selfComments;
    private int kraQtrId;
    private int appraiserComments;
    private String appraiserCommentsNew;

    public String qpdQuarter;
    public String qpdYear;
    public int yearQPD;
    public String qpdRating;

     private String dsdate;
    public int getAppraiserComments() {
        return appraiserComments;
    }

    public void setAppraiserComments(int appraiserComments) {
        this.appraiserComments = appraiserComments;
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

    public int getKraQtrId() {
        return kraQtrId;
    }

    public void setKraQtrId(int kraQtrId) {
        this.kraQtrId = kraQtrId;
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

    public int getQpdKraId() {
        return qpdKraId;
    }

    public void setQpdKraId(int qpdKraId) {
        this.qpdKraId = qpdKraId;
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

    public String getAppraiserCommentsNew() {
        return appraiserCommentsNew;
    }

    public void setAppraiserCommentsNew(String appraiserCommentsNew) {
        this.appraiserCommentsNew = appraiserCommentsNew;
    }

    public int getAchievementId() {
        return achievementId;
    }

    public void setAchievementId(int achievementId) {
        this.achievementId = achievementId;
    }

    public String getAppraiserRemarks() {
        return appraiserRemarks;
    }

    public void setAppraiserRemarks(String appraiserRemarks) {
        this.appraiserRemarks = appraiserRemarks;
    }

    public String getKeyResultArea() {
        return keyResultArea;
    }

    public void setKeyResultArea(String keyResultArea) {
        this.keyResultArea = keyResultArea;
    }

    public String getApprDevRemarks() {
        return apprDevRemarks;
    }

    public void setApprDevRemarks(String apprDevRemarks) {
        this.apprDevRemarks = apprDevRemarks;
    }

    public String getDevelopmentNeeds() {
        return developmentNeeds;
    }

    public void setDevelopmentNeeds(String developmentNeeds) {
        this.developmentNeeds = developmentNeeds;
    }

    public int getNeedsId() {
        return needsId;
    }

    public void setNeedsId(int needsId) {
        this.needsId = needsId;
    }

    public String getAppGoalRemarks() {
        return appGoalRemarks;
    }

    public void setAppGoalRemarks(String appGoalRemarks) {
        this.appGoalRemarks = appGoalRemarks;
    }

    public String getGoalData() {
        return goalData;
    }

    public void setGoalData(String goalData) {
        this.goalData = goalData;
    }

    public int getGoalId() {
        return goalId;
    }

    public void setGoalId(int goalId) {
        this.goalId = goalId;
    }

    /**
     * @return the dsdate
     */
    public String getDsdate() {
        return dsdate;
    }

    /**
     * @param dsdate the dsdate to set
     */
    public void setDsdate(String dsdate) {
        this.dsdate = dsdate;
    }
    

}
