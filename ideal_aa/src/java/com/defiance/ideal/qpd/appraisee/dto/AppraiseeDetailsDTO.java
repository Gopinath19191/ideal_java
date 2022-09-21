/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.qpd.appraisee.dto;
import org.apache.beehive.netui.pageflow.annotations.Jpf;
/**
 *
 * @author Administrator
 */
@Jpf.FormBean()
public class AppraiseeDetailsDTO {

    public int qpdId;
    public int quarter;
    public int bandId;
    public int departmentId;
    public int appraiserId;
    public int reviewerId;
    public int hrId;
    public String appraiseeName;
    public int submitStatus;
    public int submitStausInitial;
    public String appraiserName;
    public String reviewerName;
    public String appraiseeSBU;
    public String appraiseeEmail;
    public String appraiserEmail;
    public String reviewerEmail;
    public String joinedDate;
    public int finalRating;
    public String areasOfImprovement;
    public String technologyTraining;
    public String softskillTraining;
    public String appraiserComments;
    public String discussionDate;
    
    public String getAppraiseeEmail() {
        return appraiseeEmail;
    }

    public void setAppraiseeEmail(String appraiseeEmail) {
        this.appraiseeEmail = appraiseeEmail;
    }

    public String getAppraiserEmail() {
        return appraiserEmail;
    }

    public void setAppraiserEmail(String appraiserEmail) {
        this.appraiserEmail = appraiserEmail;
    }

    public String getReviewerEmail() {
        return reviewerEmail;
    }

    public void setReviewerEmail(String reviewerEmail) {
        this.reviewerEmail = reviewerEmail;
    }

    public String getAppraiseeSBU() {
        return appraiseeSBU;
    }

    public void setAppraiseeSBU(String appraiseeSBU) {
        this.appraiseeSBU = appraiseeSBU;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }
    

    public String getAppraiserName() {
        return appraiserName;
    }

    public void setAppraiserName(String appraiserName) {
        this.appraiserName = appraiserName;
    }
    


    public int getAppraiserId() {
        return appraiserId;
    }

    public void setAppraiserId(int appraiserId) {
        this.appraiserId = appraiserId;
    }

    public int getBandId() {
        return bandId;
    }

    public void setBandId(int bandId) {
        this.bandId = bandId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public int getHrId() {
        return hrId;
    }

    public void setHrId(int hrId) {
        this.hrId = hrId;
    }

    public int getQpdId() {
        return qpdId;
    }

    public void setQpdId(int qpdId) {
        this.qpdId = qpdId;
    }

    public int getQuarter() {
        return quarter;
    }

    public void setQuarter(int quarter) {
        this.quarter = quarter;
    }

    public int getReviewerId() {
        return reviewerId;
    }

    public void setReviewerId(int reviewerId) {
        this.reviewerId = reviewerId;
    }

        public String getAppraiseeName() {
        return appraiseeName;
    }

    public void setAppraiseeName(String appraiseeName) {
        this.appraiseeName = appraiseeName;
    }
    
    public int getSubmitStatus() {
        return submitStatus;
    }

    public void setSubmitStatus(int submitStatus) {
        this.submitStatus = submitStatus;
    }
    public int getSubmitStausInitial() {
        return submitStausInitial;
    }

    public void setSubmitStausInitial(int submitStausInitial) {
        this.submitStausInitial = submitStausInitial;
    }

    public int getFinalRating() {
        return finalRating;
    }

    public void setFinalRating(int finalRating) {
        this.finalRating = finalRating;
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

    public String getAppraiserComments() {
        return appraiserComments;
    }

    public void setAppraiserComments(String appraiserComments) {
        this.appraiserComments = appraiserComments;
    }

    public String getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(String joinedDate) {
        this.joinedDate = joinedDate;
    }

    public String getDiscussionDate() {
        return discussionDate;
    }

    public void setDiscussionDate(String discussionDate) {
        this.discussionDate = discussionDate;
    }  
    
}


