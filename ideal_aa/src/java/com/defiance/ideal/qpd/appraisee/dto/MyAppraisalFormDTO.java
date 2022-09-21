/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.qpd.appraisee.dto;
import org.apache.beehive.netui.pageflow.annotations.Jpf;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.apache.struts.upload.FormFile;
import org.apache.struts.action.ActionForm;
/**
 *
 * @author Administrator
 */
@Jpf.FormBean()
public class MyAppraisalFormDTO extends ActionForm{

    public String[] keyResultAreas;
    public String[] employeeRemarks;
    public String[] topAchievementsStatus;
    public int[] topAchievementsId;
    public int topAchievementsCount;

    public String keyResultArea;
    public String employeeRemark;
    public int achievementId;


    public String[] devNeedData;
    public int[] developmentNeedsId;
    public String[] developmentNeedsStatus;
    public int developmentNeedsCount;
    
    public int needsId;
    public String developmentNeed;
    
    public String[] goalData;
    public int[] goalId;
    public String[] goalSheetStatus;
    public int goalSheetCount;

    public int glId;
    public String glData;
    
    public int kraId;
    public int qpdId;
    public String attributes;
    public String performanceCriteria;
    public String measurementCriteria;
    public String kraDescription;
    public int weightage;
    public int qpdKraId;
    public int kraQtrId;
    public String[] appraiseeComments;
    public String appraiserCommentsNew;
    public int[] qpdKraIdHidden;
    public int[] kraQtrIdHidden;
    public String selfComments;
    public int appraiseeYear;
    public int appraiseeQuarter;
    public int fetchApprasalYear;
    public int fetchApprasalQuarter;
    public int finalRating;

    public FormFile fileUpload;
    public File upload;
    //public String attachFileName;
    public List attachment=new ArrayList();
    public List attachFileNames=new ArrayList();

    public List getAttachFileNames() {
        return attachFileNames;
    }

    public void setAttachFileNames(String attachFileNames) {
        this.attachFileNames.add(attachFileNames);
    }

    public List getAttachment() {
        return attachment;
    }

    public void setAttachment(int i,FormFile attachFile) {
        this.attachment.add(attachFile);
        setAttachFileNames(attachFile.getFileName());
    }
    
    public File getUpload() {
        return upload;
    }

    public void setUpload(File upload) {
        this.upload = upload;
    }

    public FormFile getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(FormFile fileUpload) {
        this.fileUpload = fileUpload;
    }
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

    public String getSelfComments() {
        return selfComments;
    }

    public void setSelfComments(String selfComments) {
        this.selfComments = selfComments;
    }


    public String[] getAppraiseeComments() {
        return appraiseeComments;
    }

    public void setAppraiseeComments(String[] appraiseeComments) {
        this.appraiseeComments = appraiseeComments;
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


    public int getKraQtrId() {
        return kraQtrId;
    }

    public void setKraQtrId(int kraQtrId) {
        this.kraQtrId = kraQtrId;
    }

    public int getQpdKraId() {
        return qpdKraId;
    }

    public void setQpdKraId(int qpdKraId) {
        this.qpdKraId = qpdKraId;
    }

    public String getKraDescription() {
        return kraDescription;
    }

    public void setKraDescription(String kraDescription) {
        this.kraDescription = kraDescription;
    }

    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    public int getKraId() {
        return kraId;
    }

    public void setKraId(int kraId) {
        this.kraId = kraId;
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

    public int getWeightage() {
        return weightage;
    }

    public void setWeightage(int weightage) {
        this.weightage = weightage;
    }

    public int getFinalRating() {
        return finalRating;
    }

    public void setFinalRating(int finalRating) {
        this.finalRating = finalRating;
    }

    public int getQpdId() {
        return qpdId;
    }

    public void setQpdId(int qpdId) {
        this.qpdId = qpdId;
    }

    public String getAppraiserCommentsNew() {
        return appraiserCommentsNew;
    }

    public void setAppraiserCommentsNew(String appraiserCommentsNew) {
        this.appraiserCommentsNew = appraiserCommentsNew;
    }

    public String[] getEmployeeRemarks() {
        return employeeRemarks;
    }

    public void setEmployeeRemarks(String[] employeeRemarks) {
        this.employeeRemarks = employeeRemarks;
    }

    public String[] getKeyResultAreas() {
        return keyResultAreas;
    }

    public void setKeyResultAreas(String[] keyResultAreas) {
        this.keyResultAreas = keyResultAreas;
    }

    public int getTopAchievementsCount() {
        return topAchievementsCount;
    }

    public void setTopAchievementsCount(int topAchievementsCount) {
        this.topAchievementsCount = topAchievementsCount;
    }

    public int[] getTopAchievementsId() {
        return topAchievementsId;
    }

    public void setTopAchievementsId(int[] topAchievementsId) {
        this.topAchievementsId = topAchievementsId;
    }

    public String[] getTopAchievementsStatus() {
        return topAchievementsStatus;
    }

    public void setTopAchievementsStatus(String[] topAchievementsStatus) {
        this.topAchievementsStatus = topAchievementsStatus;
    }

    public int getAchievementId() {
        return achievementId;
    }

    public void setAchievementId(int achievementId) {
        this.achievementId = achievementId;
    }

    public String getEmployeeRemark() {
        return employeeRemark;
    }

    public void setEmployeeRemark(String employeeRemark) {
        this.employeeRemark = employeeRemark;
    }

    public String getKeyResultArea() {
        return keyResultArea;
    }

    public void setKeyResultArea(String keyResultArea) {
        this.keyResultArea = keyResultArea;
    }

    public String[] getDevNeedData() {
        return devNeedData;
    }

    public void setDevNeedData(String[] devNeedData) {
        this.devNeedData = devNeedData;
    }

    public int getDevelopmentNeedsCount() {
        return developmentNeedsCount;
    }

    public void setDevelopmentNeedsCount(int developmentNeedsCount) {
        this.developmentNeedsCount = developmentNeedsCount;
    }

    public int[] getDevelopmentNeedsId() {
        return developmentNeedsId;
    }

    public void setDevelopmentNeedsId(int[] developmentNeedsId) {
        this.developmentNeedsId = developmentNeedsId;
    }

    public String[] getDevelopmentNeedsStatus() {
        return developmentNeedsStatus;
    }

    public void setDevelopmentNeedsStatus(String[] developmentNeedsStatus) {
        this.developmentNeedsStatus = developmentNeedsStatus;
    }

    public String getDevelopmentNeed() {
        return developmentNeed;
    }

    public void setDevelopmentNeed(String developmentNeed) {
        this.developmentNeed = developmentNeed;
    }

    public int getNeedsId() {
        return needsId;
    }

    public void setNeedsId(int needsId) {
        this.needsId = needsId;
    }

    public String[] getGoalData() {
        return goalData;
    }

    public void setGoalData(String[] goalData) {
        this.goalData = goalData;
    }

    public int[] getGoalId() {
        return goalId;
    }

    public void setGoalId(int[] goalId) {
        this.goalId = goalId;
    }

    public int getGoalSheetCount() {
        return goalSheetCount;
    }

    public void setGoalSheetCount(int goalSheetCount) {
        this.goalSheetCount = goalSheetCount;
    }

    public String[] getGoalSheetStatus() {
        return goalSheetStatus;
    }

    public void setGoalSheetStatus(String[] goalSheetStatus) {
        this.goalSheetStatus = goalSheetStatus;
    }

    public String getGlData() {
        return glData;
    }

    public void setGlData(String glData) {
        this.glData = glData;
    }

    public int getGlId() {
        return glId;
    }

    public void setGlId(int glId) {
        this.glId = glId;
    }

}
